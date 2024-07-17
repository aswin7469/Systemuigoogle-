package com.google.android.systemui.elmyra.feedback;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
