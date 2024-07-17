package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;
import android.util.MathUtils;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class GlowController$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ GlowController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ GlowController$$ExternalSyntheticLambda1(GlowController glowController, int i, int i2) {
        this.f$0 = glowController;
        this.f$1 = i;
        this.f$2 = i2;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        GlowController glowController = this.f$0;
        int i = this.f$1;
        int i2 = this.f$2;
        glowController.getClass();
        int lerp = (int) MathUtils.lerp(i, i2, valueAnimator.getAnimatedFraction());
        GlowView glowView = glowController.mGlowView;
        if (glowView.mBlurRadius != lerp) {
            glowView.setBlurredImageOnViews(lerp);
        }
    }
}
