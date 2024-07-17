package com.google.android.systemui.ambientmusic;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
