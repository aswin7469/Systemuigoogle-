package com.google.android.systemui.elmyra.feedback;

import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public interface FeedbackEffect {
    void onProgress(int i, float f);

    void onRelease();

    void onResolve(GestureSensor.DetectionProperties detectionProperties);
}
