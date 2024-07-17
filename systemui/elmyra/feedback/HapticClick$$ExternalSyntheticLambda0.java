package com.google.android.systemui.elmyra.feedback;

import android.os.Vibrator;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class HapticClick$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ HapticClick f$0;

    public /* synthetic */ HapticClick$$ExternalSyntheticLambda0(HapticClick hapticClick, int i) {
        this.$r8$classId = i;
        this.f$0 = hapticClick;
    }

    public final void accept(Object obj) {
        int i = this.$r8$classId;
        HapticClick hapticClick = this.f$0;
        Vibrator vibrator = (Vibrator) obj;
        switch (i) {
            case 0:
                vibrator.vibrate(hapticClick.mProgressVibrationEffect, HapticClick.TOUCH_VIBRATION_ATTRIBUTES);
                return;
            default:
                vibrator.vibrate(hapticClick.mResolveVibrationEffect, HapticClick.TOUCH_VIBRATION_ATTRIBUTES);
                return;
        }
    }
}
