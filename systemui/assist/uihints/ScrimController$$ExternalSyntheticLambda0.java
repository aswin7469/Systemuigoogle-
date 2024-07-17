package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
