package com.google.android.systemui.columbus.legacy.feedback;

import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public interface FeedbackEffect {
    void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties);
}
