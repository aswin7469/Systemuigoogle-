package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ScrimController$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ScrimController f$0;

    public /* synthetic */ ScrimController$$ExternalSyntheticLambda0(ScrimController scrimController) {
        this.f$0 = scrimController;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        ScrimController scrimController = this.f$0;
        scrimController.getClass();
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        scrimController.mScrimView.setAlpha(floatValue);
        scrimController.mOverlappedElement.setAlpha(1.0f - floatValue);
    }
}
