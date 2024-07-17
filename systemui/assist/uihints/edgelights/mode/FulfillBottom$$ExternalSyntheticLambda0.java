package com.google.android.systemui.assist.uihints.edgelights.mode;

import android.animation.ValueAnimator;
import android.util.MathUtils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class FulfillBottom$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ FulfillBottom f$0;

    public /* synthetic */ FulfillBottom$$ExternalSyntheticLambda0(FulfillBottom fulfillBottom, int i) {
        this.$r8$classId = i;
        this.f$0 = fulfillBottom;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.$r8$classId;
        FulfillBottom fulfillBottom = this.f$0;
        switch (i) {
            case 0:
                fulfillBottom.mEdgeLightsView.setAlpha(1.0f - valueAnimator.getAnimatedFraction());
                return;
            default:
                fulfillBottom.getClass();
                float animatedFraction = valueAnimator.getAnimatedFraction();
                if (!fulfillBottom.mSwingLeft) {
                    animatedFraction = 1.0f - animatedFraction;
                }
                fulfillBottom.setRelativePoints(MathUtils.lerp(0.69f, 0.035f, animatedFraction), MathUtils.lerp(0.87f, 0.13f, animatedFraction), MathUtils.lerp(0.965f, 0.31f, animatedFraction));
                return;
        }
    }
}
