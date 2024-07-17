package com.google.android.systemui.elmyra.feedback;

import android.content.res.Resources;
import android.os.VibrationAttributes;
import android.os.Vibrator;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
            vibrator.setAlwaysOnEffect(resources.getInteger(2131427416), hapticClick.mProgressVibrationEffect, vibrationAttributes);
        } catch (Resources.NotFoundException unused) {
        }
        try {
            vibrator.setAlwaysOnEffect(resources.getInteger(2131427417), hapticClick.mResolveVibrationEffect, vibrationAttributes);
        } catch (Resources.NotFoundException unused2) {
        }
    }
}
