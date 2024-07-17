package com.google.android.systemui.coversheet;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.Utils$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.systemui.Dependency;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CoversheetService {
    public static final boolean DEBUG = Log.isLoggable("Coversheet", 3);
    public final String mBuildId;
    public final KeyguardUpdateMonitorCallback mCallback;
    public final Context mContext;
    public boolean mKeyguardShowing;
    public boolean mUserUnlocked;

    /* renamed from: -$$Nest$mstartCoversheetIfNeeded  reason: not valid java name */
    public static void m920$$Nest$mstartCoversheetIfNeeded(CoversheetService coversheetService) {
        boolean z;
        Context context = coversheetService.mContext;
        boolean z2 = DEBUG;
        if (z2) {
            StringBuilder sb = new StringBuilder("mKeyguardShowing: ");
            sb.append(coversheetService.mKeyguardShowing);
            sb.append(", mUserUnlocked: ");
            CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb, coversheetService.mUserUnlocked, "Coversheet");
        }
        if (!coversheetService.mKeyguardShowing && coversheetService.mUserUnlocked) {
            ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.sInstance.getRunningTask();
            if (runningTask == null) {
                Log.w("Coversheet", "Not able to get any running task");
                return;
            }
            if (runningTask.configuration.windowConfiguration.getActivityType() == 2) {
                z = true;
            } else {
                z = false;
            }
            if (z2) {
                Utils$$ExternalSyntheticOutline0.m("Going to home now? ", "Coversheet", z);
            }
            if (z) {
                try {
                    Intent intent = new Intent();
                    intent.setAction("com.google.android.gms.setupservices.COVERSHEET_WELCOME");
                    intent.setPackage("com.google.android.gms");
                    intent.setFlags(335544320);
                    if (z2) {
                        Log.d("Coversheet", "start el-cap coversheet page: " + intent.toURI());
                    }
                    context.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    Log.w("Coversheet", "el-cap coversheet page was not found");
                    try {
                        Intent intent2 = new Intent("com.google.android.apps.tips.action.COVERSHEET");
                        intent2.setPackage("com.google.android.apps.tips");
                        intent2.setFlags(335544320);
                        if (z2) {
                            Log.d("Coversheet", "start coversheet: " + intent2.toURI());
                        }
                        context.startActivity(intent2);
                    } catch (ActivityNotFoundException unused2) {
                        Log.w("Coversheet", "Coversheet was not found");
                    }
                }
                Settings.System.putString(context.getContentResolver(), "coversheet_id", coversheetService.mBuildId);
                ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).removeCallback(coversheetService.mCallback);
            }
        }
    }

    public CoversheetService(Context context) {
        AnonymousClass1 r0 = new KeyguardUpdateMonitorCallback() {
            public final void onKeyguardVisibilityChanged(boolean z) {
                if (CoversheetService.DEBUG) {
                    Log.d("Coversheet", "onKeyguardVisibilityChanged");
                }
                CoversheetService coversheetService = CoversheetService.this;
                coversheetService.mKeyguardShowing = z;
                CoversheetService.m920$$Nest$mstartCoversheetIfNeeded(coversheetService);
            }

            public final void onUserUnlocked() {
                if (CoversheetService.DEBUG) {
                    Log.d("Coversheet", "onUserUnlocked");
                }
                CoversheetService coversheetService = CoversheetService.this;
                coversheetService.mUserUnlocked = true;
                CoversheetService.m920$$Nest$mstartCoversheetIfNeeded(coversheetService);
            }
        };
        this.mCallback = r0;
        String str = Build.ID.split("\\.")[0];
        this.mBuildId = str;
        this.mContext = context;
        boolean z = ((DeviceProvisionedControllerImpl) ((DeviceProvisionedController) Dependency.sDependency.getDependencyInner(DeviceProvisionedController.class))).deviceProvisioned.get();
        boolean z2 = DEBUG;
        if (!z) {
            if (z2) {
                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Store initial ID: ", str, "Coversheet");
            }
            Settings.System.putString(context.getContentResolver(), "coversheet_id", str);
        } else if (!TextUtils.equals(str, Settings.System.getString(context.getContentResolver(), "coversheet_id"))) {
            if (z2) {
                Log.d("Coversheet", "Register callback.");
            }
            ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).registerCallback(r0);
        }
    }
}
