package com.google.android.systemui.assist.uihints.edgelights.mode;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.animation.PathInterpolator;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.assist.ui.PerimeterPathGuide;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class FulfillPerimeter implements EdgeLightsView.Mode {
    public static final PathInterpolator FULFILL_PERIMETER_INTERPOLATOR = new PathInterpolator(0.2f, 0.0f, 0.2f, 1.0f);
    public final EdgeLight mBlueLight;
    public boolean mDisappearing = false;
    public final EdgeLight mGreenLight;
    public final EdgeLight[] mLights;
    public EdgeLightsView.Mode mNextMode;
    public final EdgeLight mRedLight;
    public final EdgeLight mYellowLight;

    public FulfillPerimeter(Context context) {
        EdgeLight edgeLight = new EdgeLight(context.getResources().getColor(2131099879, (Resources.Theme) null));
        this.mBlueLight = edgeLight;
        EdgeLight edgeLight2 = new EdgeLight(context.getResources().getColor(2131099881, (Resources.Theme) null));
        this.mRedLight = edgeLight2;
        EdgeLight edgeLight3 = new EdgeLight(context.getResources().getColor(2131099882, (Resources.Theme) null));
        this.mYellowLight = edgeLight3;
        EdgeLight edgeLight4 = new EdgeLight(context.getResources().getColor(2131099880, (Resources.Theme) null));
        this.mGreenLight = edgeLight4;
        this.mLights = new EdgeLight[]{edgeLight, edgeLight2, edgeLight4, edgeLight3};
    }

    public final int getSubType() {
        return 4;
    }

    public final void onNewModeRequest(EdgeLightsView edgeLightsView, EdgeLightsView.Mode mode) {
        this.mNextMode = mode;
    }

    public final void start(EdgeLightsView edgeLightsView, PerimeterPathGuide perimeterPathGuide, EdgeLightsView.Mode mode) {
        boolean z;
        boolean z2;
        float f;
        long j;
        final EdgeLightsView edgeLightsView2 = edgeLightsView;
        PerimeterPathGuide perimeterPathGuide2 = perimeterPathGuide;
        boolean z3 = false;
        edgeLightsView2.setVisibility(0);
        final AnimatorSet animatorSet = new AnimatorSet();
        EdgeLight[] edgeLightArr = this.mLights;
        int length = edgeLightArr.length;
        int i = 0;
        while (i < length) {
            EdgeLight edgeLight = edgeLightArr[i];
            EdgeLight edgeLight2 = this.mBlueLight;
            EdgeLight edgeLight3 = this.mRedLight;
            if (edgeLight == edgeLight2 || edgeLight == edgeLight3) {
                z = true;
            } else {
                z = z3;
            }
            if (edgeLight == edgeLight3 || edgeLight == this.mYellowLight) {
                z2 = true;
            } else {
                z2 = z3;
            }
            PerimeterPathGuide.Region region = PerimeterPathGuide.Region.BOTTOM;
            float regionCenter = perimeterPathGuide2.getRegionCenter(region);
            PerimeterPathGuide.Region region2 = PerimeterPathGuide.Region.TOP;
            if (z) {
                f = perimeterPathGuide2.getRegionCenter(region2) - 1.0f;
            } else {
                f = regionCenter;
            }
            float f2 = f - regionCenter;
            float regionCenter2 = perimeterPathGuide2.getRegionCenter(region2) - perimeterPathGuide2.getRegionCenter(region);
            float f3 = regionCenter2 - 0.0f;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            if (z2) {
                j = 100;
            } else {
                j = 0;
            }
            ofFloat.setStartDelay(j);
            ofFloat.setDuration(433);
            PathInterpolator pathInterpolator = FULFILL_PERIMETER_INTERPOLATOR;
            ofFloat.setInterpolator(pathInterpolator);
            FulfillPerimeter$$ExternalSyntheticLambda0 fulfillPerimeter$$ExternalSyntheticLambda0 = r0;
            PathInterpolator pathInterpolator2 = pathInterpolator;
            ValueAnimator valueAnimator = ofFloat;
            EdgeLight edgeLight4 = edgeLight;
            EdgeLight edgeLight5 = edgeLight;
            float f4 = f3;
            int i2 = i;
            FulfillPerimeter$$ExternalSyntheticLambda0 fulfillPerimeter$$ExternalSyntheticLambda02 = new FulfillPerimeter$$ExternalSyntheticLambda0(this, edgeLight4, f2, regionCenter, f4, edgeLightsView);
            valueAnimator.addUpdateListener(fulfillPerimeter$$ExternalSyntheticLambda02);
            if (!z2) {
                animatorSet.play(valueAnimator);
            } else {
                float interpolation = valueAnimator.getInterpolator().getInterpolation(100.0f / ((float) valueAnimator.getDuration())) * regionCenter2;
                ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                ofFloat2.setStartDelay(valueAnimator.getStartDelay() + 100);
                ofFloat2.setDuration(733);
                ofFloat2.setInterpolator(pathInterpolator2);
                ofFloat2.addUpdateListener(new FulfillPerimeter$$ExternalSyntheticLambda1(this, edgeLight5, interpolation, perimeterPathGuide, edgeLightsView));
                animatorSet.play(valueAnimator);
                animatorSet.play(ofFloat2);
            }
            i = i2 + 1;
            z3 = false;
        }
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public final void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                FulfillPerimeter fulfillPerimeter = FulfillPerimeter.this;
                EdgeLightsView.Mode mode = fulfillPerimeter.mNextMode;
                if (mode == null) {
                    fulfillPerimeter.mDisappearing = false;
                    animatorSet.start();
                } else if (mode != null) {
                    new Handler().postDelayed(new FulfillPerimeter$1$$ExternalSyntheticLambda0(this, edgeLightsView2), 500);
                }
            }
        });
        animatorSet.start();
    }
}
