package com.google.android.systemui.columbus.legacy.feedback;

import android.os.PowerManager;
import android.os.SystemClock;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
