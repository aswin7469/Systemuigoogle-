package com.google.android.systemui.elmyra.feedback;

import android.content.res.Resources;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.Optional;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HapticClick implements FeedbackEffect {
    public static final VibrationAttributes TOUCH_VIBRATION_ATTRIBUTES = new VibrationAttributes.Builder().setUsage(34).build();
    public int mLastGestureStage;
    public final VibrationEffect mProgressVibrationEffect = VibrationEffect.get(5);
    public final VibrationEffect mResolveVibrationEffect = VibrationEffect.get(0);
    public final Optional mVibratorOptional;

    public HapticClick(Resources resources, Optional optional) {
        this.mVibratorOptional = optional;
        optional.ifPresent(new HapticClick$$ExternalSyntheticLambda1(this, resources));
    }

    public final void onProgress(int i, float f) {
        if (this.mLastGestureStage != 2 && i == 2) {
            this.mVibratorOptional.ifPresent(new HapticClick$$ExternalSyntheticLambda0(this, 0));
        }
        this.mLastGestureStage = i;
    }

    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        if (detectionProperties == null || !detectionProperties.mHapticConsumed) {
            this.mVibratorOptional.ifPresent(new HapticClick$$ExternalSyntheticLambda0(this, 1));
        }
    }

    public final void onRelease() {
    }
}
