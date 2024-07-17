package com.google.android.systemui.elmyra.gates;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.TaskStackListener;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.elmyra.actions.Action;
import com.google.android.systemui.elmyra.actions.CameraAction;
import com.google.android.systemui.elmyra.gates.Gate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CameraVisibility extends Gate {
    public final IActivityManager mActivityManager;
    public final CameraAction mCameraAction;
    public final String mCameraPackageName;
    public boolean mCameraShowing;
    public final Set mExceptions;
    public final AnonymousClass2 mGateListener;
    public final KeyguardVisibility mKeyguardGate;
    public final PackageManager mPackageManager;
    public final PowerState mPowerState;
    public final AnonymousClass1 mTaskStackListener = new TaskStackListener() {
        public final void onTaskStackChanged() {
            CameraVisibility cameraVisibility = CameraVisibility.this;
            cameraVisibility.mUpdateExecutor.execute(new CameraVisibility$1$$ExternalSyntheticLambda0(cameraVisibility, 0));
        }
    };
    public final Executor mUpdateExecutor;

    public CameraVisibility(Context context, Executor executor, CameraAction cameraAction, PowerState powerState, PackageManager packageManager, KeyguardVisibility keyguardVisibility, Set set) {
        super(executor);
        AnonymousClass2 r0 = new Gate.Listener() {
            public final void onGateChanged(Gate gate) {
                CameraVisibility cameraVisibility = CameraVisibility.this;
                cameraVisibility.mUpdateExecutor.execute(new CameraVisibility$1$$ExternalSyntheticLambda0(cameraVisibility, 1));
            }
        };
        this.mCameraAction = cameraAction;
        this.mExceptions = set;
        this.mPackageManager = packageManager;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        this.mActivityManager = ActivityManager.getService();
        this.mKeyguardGate = keyguardVisibility;
        this.mPowerState = powerState;
        keyguardVisibility.mListener = r0;
        powerState.mListener = r0;
        this.mCameraPackageName = context.getResources().getString(2131952581);
        this.mUpdateExecutor = executor;
    }

    public final boolean isBlocked() {
        for (Action isAvailable : this.mExceptions) {
            if (isAvailable.isAvailable()) {
                return false;
            }
        }
        if (!this.mCameraShowing || this.mCameraAction.isAvailable()) {
            return false;
        }
        return true;
    }

    public final boolean isCameraShowing() {
        int i;
        String str = this.mCameraPackageName;
        try {
            List tasks = ActivityManager.getService().getTasks(1);
            if (tasks.isEmpty()) {
                return false;
            }
            if (!((ActivityManager.RunningTaskInfo) tasks.get(0)).topActivity.getPackageName().equalsIgnoreCase(str)) {
                return false;
            }
            IActivityManager iActivityManager = this.mActivityManager;
            try {
                UserInfo currentUser = iActivityManager.getCurrentUser();
                PackageManager packageManager = this.mPackageManager;
                if (currentUser != null) {
                    i = currentUser.id;
                } else {
                    i = 0;
                }
                int i2 = packageManager.getApplicationInfoAsUser(str, 0, i).uid;
                List runningAppProcesses = iActivityManager.getRunningAppProcesses();
                int i3 = 0;
                while (i3 < runningAppProcesses.size()) {
                    ActivityManager.RunningAppProcessInfo runningAppProcessInfo = (ActivityManager.RunningAppProcessInfo) runningAppProcesses.get(i3);
                    if (runningAppProcessInfo.uid != i2 || !runningAppProcessInfo.processName.equalsIgnoreCase(str)) {
                        i3++;
                    } else if (runningAppProcessInfo.importance != 100 || this.mPowerState.isBlocking()) {
                        return false;
                    } else {
                        return true;
                    }
                }
                return false;
            } catch (PackageManager.NameNotFoundException unused) {
                return false;
            } catch (RemoteException e) {
                Log.e("Elmyra/CameraVisibility", "Could not check camera foreground status", e);
                return false;
            }
        } catch (RemoteException e2) {
            Log.e("Elmyra/CameraVisibility", "unable to check task stack", e2);
            return false;
        }
    }

    public final void onActivate() {
        this.mKeyguardGate.activate();
        this.mPowerState.activate();
        this.mCameraShowing = isCameraShowing();
        try {
            this.mActivityManager.registerTaskStackListener(this.mTaskStackListener);
        } catch (RemoteException e) {
            Log.e("Elmyra/CameraVisibility", "Could not register task stack listener", e);
        }
    }

    public final void onDeactivate() {
        this.mKeyguardGate.deactivate();
        this.mPowerState.deactivate();
        try {
            this.mActivityManager.unregisterTaskStackListener(this.mTaskStackListener);
        } catch (RemoteException e) {
            Log.e("Elmyra/CameraVisibility", "Could not unregister task stack listener", e);
        }
    }
}
