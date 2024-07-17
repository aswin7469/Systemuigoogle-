package com.google.android.systemui.columbus.legacy.feedback;

import android.media.AudioAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HapticClick implements FeedbackEffect {
    public static final VibrationEffect GESTURE_DETECTED_VIBRATION_EFFECT = VibrationEffect.get(5);
    public static final AudioAttributes SONIFICATION_AUDIO_ATTRIBUTES = new AudioAttributes.Builder().setContentType(4).setUsage(13).build();
    public final Lazy vibrator;

    public HapticClick(Lazy lazy) {
        this.vibrator = lazy;
    }

    public final void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties) {
        if ((detectionProperties == null || !detectionProperties.isHapticConsumed) && i == 1) {
            ((Vibrator) this.vibrator.get()).vibrate(GESTURE_DETECTED_VIBRATION_EFFECT, SONIFICATION_AUDIO_ATTRIBUTES);
        }
    }
}
