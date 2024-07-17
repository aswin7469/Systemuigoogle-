package com.google.android.systemui.elmyra.feedback;

import android.os.Vibrator;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
