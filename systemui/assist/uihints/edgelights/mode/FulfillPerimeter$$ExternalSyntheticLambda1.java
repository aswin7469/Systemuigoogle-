package com.google.android.systemui.assist.uihints.edgelights.mode;

import android.animation.ValueAnimator;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.assist.ui.PerimeterPathGuide;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class FulfillPerimeter$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ FulfillPerimeter f$0;
    public final /* synthetic */ EdgeLight f$1;
    public final /* synthetic */ float f$2;
    public final /* synthetic */ PerimeterPathGuide f$3;
    public final /* synthetic */ EdgeLightsView f$4;

    public /* synthetic */ FulfillPerimeter$$ExternalSyntheticLambda1(FulfillPerimeter fulfillPerimeter, EdgeLight edgeLight, float f, PerimeterPathGuide perimeterPathGuide, EdgeLightsView edgeLightsView) {
        this.f$0 = fulfillPerimeter;
        this.f$1 = edgeLight;
        this.f$2 = f;
        this.f$3 = perimeterPathGuide;
        this.f$4 = edgeLightsView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        FulfillPerimeter fulfillPerimeter = this.f$0;
        EdgeLight edgeLight = this.f$1;
        float f = this.f$2;
        PerimeterPathGuide perimeterPathGuide = this.f$3;
        EdgeLightsView edgeLightsView = this.f$4;
        fulfillPerimeter.getClass();
        float animatedFraction = valueAnimator.getAnimatedFraction();
        if (animatedFraction != 0.0f) {
            fulfillPerimeter.mDisappearing = true;
            EdgeLight edgeLight2 = fulfillPerimeter.mBlueLight;
            EdgeLight edgeLight3 = fulfillPerimeter.mRedLight;
            if (edgeLight == edgeLight3) {
                edgeLight3.mLength = Math.max(((0.0f - f) * animatedFraction) + f, 0.0f);
                edgeLight2.mLength = Math.abs(edgeLight2.mStart) - Math.abs(edgeLight3.mStart);
            } else {
                EdgeLight edgeLight4 = fulfillPerimeter.mYellowLight;
                if (edgeLight == edgeLight4) {
                    PerimeterPathGuide.Region region = PerimeterPathGuide.Region.BOTTOM;
                    float f2 = edgeLight3.mStart;
                    float f3 = edgeLight3.mLength;
                    edgeLight4.mStart = (perimeterPathGuide.getRegionCenter(region) * 2.0f) - (f2 + f3);
                    edgeLight4.mLength = f3;
                    float f4 = edgeLight2.mStart;
                    float f5 = edgeLight2.mLength;
                    float regionCenter = (perimeterPathGuide.getRegionCenter(region) * 2.0f) - (f4 + f5);
                    EdgeLight edgeLight5 = fulfillPerimeter.mGreenLight;
                    edgeLight5.mStart = regionCenter;
                    edgeLight5.mLength = f5;
                }
            }
            edgeLightsView.setAssistLights(fulfillPerimeter.mLights);
        }
    }
}
