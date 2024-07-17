package com.google.android.systemui.assist.uihints.edgelights.mode;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.assist.ui.PerimeterPathGuide;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightUpdateListener;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;
import java.util.Random;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class FulfillBottom implements EdgeLightsView.Mode {
    public static final PathInterpolator CRADLE_INTERPOLATOR = new PathInterpolator(0.4f, 0.0f, 0.6f, 1.0f);
    public static final LinearInterpolator EXIT_FADE_INTERPOLATOR = new LinearInterpolator();
    public static final PathInterpolator EXIT_TO_CORNER_INTERPOLATOR = new PathInterpolator(0.1f, 0.0f, 0.5f, 1.0f);
    public EdgeLight mBlueLight;
    public AnimatorSet mCradleAnimations = new AnimatorSet();
    public EdgeLightsView mEdgeLightsView = null;
    public AnimatorSet mExitAnimations = new AnimatorSet();
    public EdgeLight mGreenLight;
    public PerimeterPathGuide mGuide = null;
    public final boolean mIsListening;
    public EdgeLight[] mLightsArray;
    public EdgeLightsView.Mode mNextMode = null;
    public final Random mRandom = new Random();
    public EdgeLight mRedLight;
    public final Resources mResources;
    public boolean mSwingLeft = false;
    public EdgeLight mYellowLight;

    public FulfillBottom(Context context, boolean z) {
        this.mResources = context.getResources();
        this.mIsListening = z;
    }

    public final int getSubType() {
        return 3;
    }

    public final void onConfigurationChanged() {
        if (this.mNextMode == null) {
            start(this.mEdgeLightsView, this.mGuide, this);
            return;
        }
        if (this.mExitAnimations.isRunning()) {
            this.mExitAnimations.cancel();
        }
        onNewModeRequest(this.mEdgeLightsView, this.mNextMode);
    }

    public final void onNewModeRequest(EdgeLightsView edgeLightsView, EdgeLightsView.Mode mode) {
        this.mNextMode = mode;
        if (this.mCradleAnimations.isRunning()) {
            this.mCradleAnimations.cancel();
        }
        mode.getClass();
        if (!(mode instanceof Gone)) {
            if (this.mExitAnimations.isRunning()) {
                this.mExitAnimations.cancel();
            }
            this.mEdgeLightsView.commitModeTransition(this.mNextMode);
        } else if (!this.mExitAnimations.isRunning()) {
            EdgeLight[] copy = EdgeLight.copy(this.mLightsArray);
            EdgeLight[] copy2 = EdgeLight.copy(this.mLightsArray);
            PerimeterPathGuide.RegionAttributes[] regionAttributesArr = this.mGuide.mRegions;
            float f = regionAttributesArr[7].normalizedLength * 0.8f;
            float f2 = -1.0f * f;
            float f3 = regionAttributesArr[0].normalizedLength;
            copy2[0].setEndpoints(f2, f2);
            copy2[1].setEndpoints(f2, f2);
            float f4 = f3 + f;
            copy2[2].setEndpoints(f4, f4);
            copy2[3].setEndpoints(f4, f4);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            ofFloat.setInterpolator(EXIT_TO_CORNER_INTERPOLATOR);
            ofFloat.setDuration(350);
            ofFloat.addUpdateListener(new EdgeLightUpdateListener(copy, copy2, this.mLightsArray, this.mEdgeLightsView));
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
            ofFloat2.setInterpolator(EXIT_FADE_INTERPOLATOR);
            ofFloat2.setDuration(350);
            ofFloat2.addUpdateListener(new FulfillBottom$$ExternalSyntheticLambda0(this, 0));
            ofFloat2.addListener(new AnimatorListenerAdapter() {
                public final void onAnimationEnd(Animator animator) {
                    FulfillBottom.this.mEdgeLightsView.setAssistLights(new EdgeLight[0]);
                    FulfillBottom.this.mEdgeLightsView.setAlpha(1.0f);
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(ofFloat);
            animatorSet.play(ofFloat2);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public boolean mCancelled = false;

                public final void onAnimationCancel(Animator animator) {
                    super.onAnimationCancel(animator);
                    this.mCancelled = true;
                }

                public final void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    FulfillBottom.this.mEdgeLightsView.setVisibility(8);
                    FulfillBottom fulfillBottom = FulfillBottom.this;
                    EdgeLightsView.Mode mode = fulfillBottom.mNextMode;
                    if (mode != null && !this.mCancelled) {
                        fulfillBottom.mEdgeLightsView.commitModeTransition(mode);
                    }
                }
            });
            this.mExitAnimations = animatorSet;
            animatorSet.start();
        }
    }

    public final void setRelativePoints(float f, float f2, float f3) {
        float f4 = this.mGuide.mRegions[0].normalizedLength;
        float f5 = f * f4;
        this.mBlueLight.setEndpoints(0.0f, f5);
        float f6 = f2 * f4;
        this.mRedLight.setEndpoints(f5, f6);
        float f7 = f3 * f4;
        this.mYellowLight.setEndpoints(f6, f7);
        this.mGreenLight.setEndpoints(f7, f4);
        this.mEdgeLightsView.setAssistLights(this.mLightsArray);
    }

    public final void start(EdgeLightsView edgeLightsView, PerimeterPathGuide perimeterPathGuide, EdgeLightsView.Mode mode) {
        boolean z;
        this.mEdgeLightsView = edgeLightsView;
        this.mGuide = perimeterPathGuide;
        edgeLightsView.setVisibility(0);
        EdgeLight[] assistLights = edgeLightsView.getAssistLights();
        if (((mode instanceof FullListening) || (mode instanceof FulfillBottom)) && assistLights.length == 4) {
            this.mBlueLight = assistLights[0];
            this.mRedLight = assistLights[1];
            this.mYellowLight = assistLights[2];
            this.mGreenLight = assistLights[3];
        } else {
            Resources resources = this.mResources;
            this.mBlueLight = new EdgeLight(resources.getColor(2131099879, (Resources.Theme) null));
            this.mRedLight = new EdgeLight(resources.getColor(2131099881, (Resources.Theme) null));
            this.mYellowLight = new EdgeLight(resources.getColor(2131099882, (Resources.Theme) null));
            this.mGreenLight = new EdgeLight(resources.getColor(2131099880, (Resources.Theme) null));
        }
        this.mLightsArray = new EdgeLight[]{this.mBlueLight, this.mRedLight, this.mYellowLight, this.mGreenLight};
        if (mode instanceof FulfillBottom) {
            z = ((FulfillBottom) mode).mSwingLeft;
        } else {
            z = this.mRandom.nextBoolean();
        }
        this.mSwingLeft = z;
        float f = this.mGuide.mRegions[0].normalizedLength;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        EdgeLight edgeLight = this.mBlueLight;
        EdgeLight edgeLight2 = this.mRedLight;
        EdgeLight edgeLight3 = this.mYellowLight;
        ofFloat.addUpdateListener(new FulfillBottom$$ExternalSyntheticLambda1(this, (edgeLight.mStart + edgeLight.mLength) / f, (edgeLight2.mStart + edgeLight2.mLength) / f, (edgeLight3.mStart + edgeLight3.mLength) / f));
        ofFloat.setDuration(1000);
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat2.addUpdateListener(new FulfillBottom$$ExternalSyntheticLambda0(this, 1));
        ofFloat2.setDuration(1300);
        ofFloat2.setInterpolator(CRADLE_INTERPOLATOR);
        ofFloat2.setRepeatMode(2);
        ofFloat2.setRepeatCount(-1);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mCradleAnimations = animatorSet;
        animatorSet.playSequentially(new Animator[]{ofFloat, ofFloat2});
        this.mCradleAnimations.start();
    }
}
