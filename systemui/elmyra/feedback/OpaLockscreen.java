package com.google.android.systemui.elmyra.feedback;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class OpaLockscreen implements FeedbackEffect {
    static {
        new DecelerateInterpolator();
        new AccelerateInterpolator();
    }

    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
    }

    public final void onRelease() {
    }

    public final void onProgress(int i, float f) {
    }
}
