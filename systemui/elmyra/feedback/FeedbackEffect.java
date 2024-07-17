package com.google.android.systemui.elmyra.feedback;

import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public interface FeedbackEffect {
    void onProgress(int i, float f);

    void onRelease();

    void onResolve(GestureSensor.DetectionProperties detectionProperties);
}
