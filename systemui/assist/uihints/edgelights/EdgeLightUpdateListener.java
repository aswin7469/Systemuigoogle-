package com.google.android.systemui.assist.uihints.edgelights;

import android.animation.ValueAnimator;
import android.util.MathUtils;
import com.android.systemui.assist.ui.EdgeLight;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class EdgeLightUpdateListener implements ValueAnimator.AnimatorUpdateListener {
    public final EdgeLight[] mFinalLights;
    public final EdgeLight[] mInitialLights;
    public final EdgeLight[] mLights;
    public final EdgeLightsView mView;

    public EdgeLightUpdateListener(EdgeLight[] edgeLightArr, EdgeLight[] edgeLightArr2, EdgeLight[] edgeLightArr3, EdgeLightsView edgeLightsView) {
        if (edgeLightArr.length == edgeLightArr2.length && edgeLightArr3.length == edgeLightArr2.length) {
            this.mFinalLights = edgeLightArr2;
            this.mInitialLights = edgeLightArr;
            this.mLights = edgeLightArr3;
            this.mView = edgeLightsView;
            return;
        }
        throw new IllegalArgumentException("Lights arrays must be the same length");
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        int i = 0;
        while (true) {
            EdgeLight[] edgeLightArr = this.mLights;
            if (i < edgeLightArr.length) {
                float f = this.mInitialLights[i].mLength;
                float f2 = this.mFinalLights[i].mLength;
                edgeLightArr[i].mLength = MathUtils.lerp(f, f2, animatedFraction);
                float f3 = this.mInitialLights[i].mStart;
                float f4 = this.mFinalLights[i].mStart;
                this.mLights[i].mStart = MathUtils.lerp(f3, f4, animatedFraction);
                i++;
            } else {
                this.mView.setAssistLights(edgeLightArr);
                return;
            }
        }
    }
}
