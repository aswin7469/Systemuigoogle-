package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ChipsContainer$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ChipsContainer f$0;

    public /* synthetic */ ChipsContainer$$ExternalSyntheticLambda0(ChipsContainer chipsContainer) {
        this.f$0 = chipsContainer;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        ChipsContainer chipsContainer = this.f$0;
        chipsContainer.setTranslationY((1.0f - ((Float) valueAnimator.getAnimatedValue()).floatValue()) * ((float) chipsContainer.START_DELTA));
    }
}
