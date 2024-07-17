package com.google.android.systemui.columbus.legacy.feedback;

import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public interface FeedbackEffect {
    void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties);
}
