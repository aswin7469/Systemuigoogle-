package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;
import android.util.MathUtils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
