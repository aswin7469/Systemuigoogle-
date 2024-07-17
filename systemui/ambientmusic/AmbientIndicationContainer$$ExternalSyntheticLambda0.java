package com.google.android.systemui.ambientmusic;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class AmbientIndicationContainer$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AmbientIndicationContainer f$0;

    public /* synthetic */ AmbientIndicationContainer$$ExternalSyntheticLambda0(AmbientIndicationContainer ambientIndicationContainer) {
        this.f$0 = ambientIndicationContainer;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        AmbientIndicationContainer ambientIndicationContainer = this.f$0;
        int i = AmbientIndicationContainer.$r8$clinit;
        ambientIndicationContainer.getClass();
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        ambientIndicationContainer.mTextView.setTextColor(intValue);
        ambientIndicationContainer.mIconView.setImageTintList(ColorStateList.valueOf(intValue));
    }
}
