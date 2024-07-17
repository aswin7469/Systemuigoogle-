package com.google.android.systemui.columbus.legacy.feedback;

import android.media.AudioAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
