package com.google.android.systemui.columbus.legacy.feedback;

import android.os.PowerManager;
import android.os.SystemClock;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UserActivity implements FeedbackEffect {
    public final Lazy powerManager;

    public UserActivity(Lazy lazy) {
        this.powerManager = lazy;
    }

    public final void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties) {
        if (i != 0) {
            ((PowerManager) this.powerManager.get()).userActivity(SystemClock.uptimeMillis(), 0, 0);
        }
    }
}
