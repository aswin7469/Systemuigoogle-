package com.google.android.systemui.elmyra;

import android.app.StatsManager;
import android.content.Context;
import android.util.Log;
import com.android.internal.util.ConcurrentUtils;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Snapshot;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import java.util.concurrent.CountDownLatch;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class WestworldLogger implements GestureSensor.Listener {
    public ChassisProtos$Chassis mChassis = null;
    public CountDownLatch mCountDownLatch;
    public final GestureConfiguration mGestureConfiguration;
    public final Object mMutex;
    public SnapshotProtos$Snapshot mSnapshot;
    public final SnapshotController mSnapshotController;
    public final WestworldLogger$$ExternalSyntheticLambda0 mWestworldCallback;

    public WestworldLogger(Context context, GestureConfiguration gestureConfiguration, SnapshotController snapshotController) {
        WestworldLogger$$ExternalSyntheticLambda0 westworldLogger$$ExternalSyntheticLambda0 = new WestworldLogger$$ExternalSyntheticLambda0(this);
        this.mGestureConfiguration = gestureConfiguration;
        this.mSnapshotController = snapshotController;
        this.mSnapshot = null;
        this.mMutex = new Object();
        StatsManager statsManager = (StatsManager) context.getSystemService("stats");
        if (statsManager == null) {
            Log.d("Elmyra/Logger", "Failed to get StatsManager");
        }
        try {
            statsManager.setPullAtomCallback(150000, (StatsManager.PullAtomMetadata) null, ConcurrentUtils.DIRECT_EXECUTOR, westworldLogger$$ExternalSyntheticLambda0);
        } catch (RuntimeException e) {
            Log.d("Elmyra/Logger", "Failed to register callback with StatsManager");
            e.printStackTrace();
        }
    }

    public final void onGestureDetected(GestureSensor.DetectionProperties detectionProperties) {
        SysUiStatsLog.write(174, 3);
    }

    public final void onGestureProgress(int i, float f) {
        SysUiStatsLog.write(176, (int) (f * 100.0f));
        SysUiStatsLog.write(174, i);
    }
}
