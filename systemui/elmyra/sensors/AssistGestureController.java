package com.google.android.systemui.elmyra.sensors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Binder;
import android.os.SystemClock;
import android.util.TypedValue;
import androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.google.android.systemui.elmyra.SnapshotConfiguration;
import com.google.android.systemui.elmyra.SnapshotController;
import com.google.android.systemui.elmyra.SnapshotLogger;
import com.google.android.systemui.elmyra.WestworldLogger;
import com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Snapshot;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Snapshots;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import com.google.protobuf.nano.MessageNano;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AssistGestureController implements Dumpable {
    public ChassisProtos$Chassis mChassis;
    public final SnapshotLogger mCompleteGestures;
    public final long mFalsePrimeWindow;
    public final GestureConfiguration mGestureConfiguration;
    public final long mGestureCooldownTime;
    public GestureSensor.Listener mGestureListener;
    public float mGestureProgress;
    public final GestureSensor mGestureSensor;
    public final SnapshotLogger mIncompleteGestures;
    public boolean mIsFalsePrimed;
    public long mLastDetectionTime;
    public final float mProgressAlpha;
    public final float mProgressReportThreshold;
    public final SnapshotController mSnapshotController;
    public final WestworldLogger mWestworldLogger;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class OPAQueryReceiver extends BroadcastReceiver {
        public OPAQueryReceiver() {
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.google.android.systemui.OPA_ELMYRA_QUERY_SUBMITTED")) {
                ArrayList arrayList = (ArrayList) AssistGestureController.this.mCompleteGestures.mSnapshots;
                if (arrayList.size() > 0) {
                    ((SnapshotLogger.Snapshot) CascadingMenuPopup$$ExternalSyntheticOutline0.m(arrayList, 1)).mSnapshot.header.feedback = 1;
                }
                AssistGestureController.this.mWestworldLogger.getClass();
                SysUiStatsLog.write(175, 2);
            }
        }
    }

    public AssistGestureController(Context context, GestureSensor gestureSensor, GestureConfiguration gestureConfiguration, SnapshotConfiguration snapshotConfiguration) {
        int i;
        OPAQueryReceiver oPAQueryReceiver = new OPAQueryReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.systemui.OPA_ELMYRA_QUERY_SUBMITTED");
        context.registerReceiver(oPAQueryReceiver, intentFilter, 2);
        this.mGestureSensor = gestureSensor;
        this.mGestureConfiguration = gestureConfiguration;
        Resources resources = context.getResources();
        TypedValue typedValue = new TypedValue();
        int i2 = 0;
        if (snapshotConfiguration != null) {
            i = snapshotConfiguration.mCompleteGestures;
        } else {
            i = 0;
        }
        this.mCompleteGestures = new SnapshotLogger(i);
        this.mIncompleteGestures = new SnapshotLogger(snapshotConfiguration != null ? snapshotConfiguration.mIncompleteGestures : i2);
        resources.getValue(2131165905, typedValue, true);
        this.mProgressAlpha = typedValue.getFloat();
        resources.getValue(2131165907, typedValue, true);
        this.mProgressReportThreshold = typedValue.getFloat();
        long integer = (long) resources.getInteger(2131427417);
        this.mGestureCooldownTime = integer;
        this.mFalsePrimeWindow = integer + ((long) resources.getInteger(2131427416));
        if (snapshotConfiguration != null) {
            this.mSnapshotController = new SnapshotController(snapshotConfiguration);
        } else {
            this.mSnapshotController = null;
        }
        this.mWestworldLogger = new WestworldLogger(context, gestureConfiguration, this.mSnapshotController);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        if (this.mChassis != null) {
            for (int i = 0; i < this.mChassis.sensors.length; i++) {
                printWriter.print("sensors {");
                printWriter.print("  source: " + this.mChassis.sensors[i].source);
                printWriter.print("  gain: " + this.mChassis.sensors[i].gain);
                printWriter.print("  sensitivity: " + this.mChassis.sensors[i].sensitivity);
                printWriter.print("}");
            }
            printWriter.println();
        }
        boolean z = false;
        boolean z2 = false;
        for (String str : strArr) {
            if (str.equals("GoogleServices")) {
                z = true;
            } else if (str.equals("proto")) {
                z2 = true;
            }
        }
        SnapshotLogger snapshotLogger = this.mCompleteGestures;
        SnapshotLogger snapshotLogger2 = this.mIncompleteGestures;
        if (!z || !z2) {
            snapshotLogger.dump(printWriter, strArr);
            snapshotLogger2.dump(printWriter, strArr);
        } else {
            ArrayList arrayList = (ArrayList) snapshotLogger2.mSnapshots;
            ArrayList arrayList2 = (ArrayList) snapshotLogger.mSnapshots;
            if (arrayList2.size() + arrayList.size() != 0) {
                SnapshotProtos$Snapshots snapshotProtos$Snapshots = new SnapshotProtos$Snapshots();
                snapshotProtos$Snapshots.snapshots = new SnapshotProtos$Snapshot[(arrayList2.size() + arrayList.size())];
                int i2 = 0;
                while (i2 < arrayList.size()) {
                    snapshotProtos$Snapshots.snapshots[i2] = ((SnapshotLogger.Snapshot) arrayList.get(i2)).mSnapshot;
                    i2++;
                }
                for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                    snapshotProtos$Snapshots.snapshots[i2 + i3] = ((SnapshotLogger.Snapshot) arrayList2.get(i3)).mSnapshot;
                }
                byte[] byteArray = MessageNano.toByteArray(snapshotProtos$Snapshots);
                long clearCallingIdentity = Binder.clearCallingIdentity();
                printWriter.write(Base64.getEncoder().encodeToString(byteArray));
                snapshotLogger.mSnapshots.clear();
                snapshotLogger2.mSnapshots.clear();
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
        printWriter.println("user_sensitivity: " + this.mGestureConfiguration.getSensitivity());
    }

    public final void onGestureDetected(GestureSensor.DetectionProperties detectionProperties) {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - this.mLastDetectionTime >= this.mGestureCooldownTime && !this.mIsFalsePrimed) {
            GestureSensor.Listener listener = this.mGestureListener;
            if (listener != null) {
                listener.onGestureDetected(detectionProperties);
            }
            SnapshotController snapshotController = this.mSnapshotController;
            if (snapshotController != null) {
                snapshotController.onGestureDetected(detectionProperties);
            }
            this.mWestworldLogger.onGestureDetected(detectionProperties);
            this.mLastDetectionTime = uptimeMillis;
        }
    }

    public final void onGestureProgress(float f) {
        if (f == 0.0f) {
            this.mGestureProgress = 0.0f;
            this.mIsFalsePrimed = false;
        } else {
            float f2 = this.mProgressAlpha;
            this.mGestureProgress = ((1.0f - f2) * this.mGestureProgress) + (f2 * f);
        }
        long uptimeMillis = SystemClock.uptimeMillis() - this.mLastDetectionTime;
        if (uptimeMillis >= this.mGestureCooldownTime && !this.mIsFalsePrimed) {
            int i = (uptimeMillis > this.mFalsePrimeWindow ? 1 : (uptimeMillis == this.mFalsePrimeWindow ? 0 : -1));
            int i2 = 1;
            if (i >= 0 || f != 1.0f) {
                float f3 = this.mGestureProgress;
                float f4 = this.mProgressReportThreshold;
                int i3 = (f3 > f4 ? 1 : (f3 == f4 ? 0 : -1));
                WestworldLogger westworldLogger = this.mWestworldLogger;
                SnapshotController snapshotController = this.mSnapshotController;
                if (i3 < 0) {
                    GestureSensor.Listener listener = this.mGestureListener;
                    if (listener != null) {
                        listener.onGestureProgress(0, 0.0f);
                    }
                    if (snapshotController != null) {
                        snapshotController.onGestureProgress(0, 0.0f);
                    }
                    westworldLogger.onGestureProgress(0, 0.0f);
                    return;
                }
                float f5 = (f3 - f4) / (1.0f - f4);
                if (f == 1.0f) {
                    i2 = 2;
                }
                GestureSensor.Listener listener2 = this.mGestureListener;
                if (listener2 != null) {
                    listener2.onGestureProgress(i2, f5);
                }
                if (snapshotController != null) {
                    snapshotController.onGestureProgress(i2, f5);
                }
                westworldLogger.onGestureProgress(i2, f5);
                return;
            }
            this.mIsFalsePrimed = true;
        }
    }

    public final void onSnapshotReceived(SnapshotProtos$Snapshot snapshotProtos$Snapshot) {
        int i = snapshotProtos$Snapshot.header.gestureType;
        if (i == 4) {
            WestworldLogger westworldLogger = this.mWestworldLogger;
            synchronized (westworldLogger.mMutex) {
                try {
                    westworldLogger.mSnapshot = snapshotProtos$Snapshot;
                    CountDownLatch countDownLatch = westworldLogger.mCountDownLatch;
                    if (countDownLatch != null) {
                        countDownLatch.countDown();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else if (i == 1) {
            SnapshotLogger snapshotLogger = this.mCompleteGestures;
            long currentTimeMillis = System.currentTimeMillis();
            List list = snapshotLogger.mSnapshots;
            if (((ArrayList) list).size() == snapshotLogger.mSnapshotCapacity) {
                list.remove(0);
            }
            list.add(new SnapshotLogger.Snapshot(snapshotProtos$Snapshot, currentTimeMillis));
        } else {
            SnapshotLogger snapshotLogger2 = this.mIncompleteGestures;
            long currentTimeMillis2 = System.currentTimeMillis();
            List list2 = snapshotLogger2.mSnapshots;
            if (((ArrayList) list2).size() == snapshotLogger2.mSnapshotCapacity) {
                list2.remove(0);
            }
            list2.add(new SnapshotLogger.Snapshot(snapshotProtos$Snapshot, currentTimeMillis2));
        }
    }
}
