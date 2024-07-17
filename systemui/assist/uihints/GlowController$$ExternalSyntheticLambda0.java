package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;
import com.android.systemui.assist.ui.EdgeLight;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class GlowController$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ GlowController f$0;

    public /* synthetic */ GlowController$$ExternalSyntheticLambda0(GlowController glowController) {
        this.f$0 = glowController;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        GlowController glowController = this.f$0;
        glowController.getClass();
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        glowController.mGlowsY = intValue;
        glowController.mGlowView.setGlowsY(intValue, glowController.getMinTranslationY(), (EdgeLight[]) null);
    }
}
