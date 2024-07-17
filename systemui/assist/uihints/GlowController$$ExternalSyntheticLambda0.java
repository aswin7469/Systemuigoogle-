package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;
import com.android.systemui.assist.ui.EdgeLight;
import com.google.android.systemui.assist.uihints.edgelights.mode.FullListening;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class GlowController$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ GlowController f$0;

    public /* synthetic */ GlowController$$ExternalSyntheticLambda0(GlowController glowController) {
        this.f$0 = glowController;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        EdgeLight[] edgeLightArr;
        GlowController glowController = this.f$0;
        glowController.getClass();
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        glowController.mGlowsY = intValue;
        int minTranslationY = glowController.getMinTranslationY();
        if (glowController.mEdgeLightsMode instanceof FullListening) {
            edgeLightArr = glowController.mEdgeLights;
        } else {
            edgeLightArr = null;
        }
        glowController.mGlowView.setGlowsY(intValue, minTranslationY, edgeLightArr);
    }
}
