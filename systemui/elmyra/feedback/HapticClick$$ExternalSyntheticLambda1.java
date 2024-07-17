package com.google.android.systemui.elmyra.feedback;

import android.content.res.Resources;
import android.os.VibrationAttributes;
import android.os.Vibrator;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class HapticClick$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ HapticClick f$0;
    public final /* synthetic */ Resources f$1;

    public /* synthetic */ HapticClick$$ExternalSyntheticLambda1(HapticClick hapticClick, Resources resources) {
        this.f$0 = hapticClick;
        this.f$1 = resources;
    }

    public final void accept(Object obj) {
        HapticClick hapticClick = this.f$0;
        Resources resources = this.f$1;
        Vibrator vibrator = (Vibrator) obj;
        hapticClick.getClass();
        VibrationAttributes vibrationAttributes = HapticClick.TOUCH_VIBRATION_ATTRIBUTES;
        try {
            vibrator.setAlwaysOnEffect(resources.getInteger(2131427419), hapticClick.mProgressVibrationEffect, vibrationAttributes);
        } catch (Resources.NotFoundException unused) {
        }
        try {
            vibrator.setAlwaysOnEffect(resources.getInteger(2131427420), hapticClick.mResolveVibrationEffect, vibrationAttributes);
        } catch (Resources.NotFoundException unused2) {
        }
    }
}
