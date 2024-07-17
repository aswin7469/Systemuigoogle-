package com.google.android.systemui.assist.uihints.edgelights.mode;

import android.animation.ValueAnimator;
import com.android.systemui.assist.ui.EdgeLight;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class FulfillPerimeter$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ FulfillPerimeter f$0;
    public final /* synthetic */ EdgeLight f$1;
    public final /* synthetic */ float f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ float f$4;
    public final /* synthetic */ EdgeLightsView f$6;

    public /* synthetic */ FulfillPerimeter$$ExternalSyntheticLambda0(FulfillPerimeter fulfillPerimeter, EdgeLight edgeLight, float f, float f2, float f3, EdgeLightsView edgeLightsView) {
        this.f$0 = fulfillPerimeter;
        this.f$1 = edgeLight;
        this.f$2 = f;
        this.f$3 = f2;
        this.f$4 = f3;
        this.f$6 = edgeLightsView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        FulfillPerimeter fulfillPerimeter = this.f$0;
        EdgeLight edgeLight = this.f$1;
        float f = this.f$2;
        float f2 = this.f$3;
        float f3 = this.f$4;
        EdgeLightsView edgeLightsView = this.f$6;
        fulfillPerimeter.getClass();
        float animatedFraction = valueAnimator.getAnimatedFraction();
        edgeLight.mStart = (f * animatedFraction) + f2;
        if (!fulfillPerimeter.mDisappearing) {
            edgeLight.mLength = (f3 * animatedFraction) + 0.0f;
        }
        edgeLightsView.setAssistLights(fulfillPerimeter.mLights);
    }
}
