package com.google.android.systemui.assist.uihints.edgelights.mode;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.MathUtils;
import android.view.animation.PathInterpolator;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.assist.ui.PerimeterPathGuide;
import com.google.android.systemui.assist.uihints.RollingAverage;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightUpdateListener;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class FullListening implements EdgeLightsView.Mode {
    public static final PathInterpolator INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
    public Animator mAnimator;
    public EdgeLightsView mEdgeLightsView;
    public final boolean mFakeForHalfListening;
    public PerimeterPathGuide mGuide;
    public boolean mLastPerturbationWasEven = false;
    public long mLastSpeechTimestampMs = 0;
    public final EdgeLight[] mLights;
    public final RollingAverage mRollingConfidence = new RollingAverage();
    public State mState = State.NOT_STARTED;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    enum State {
    }

    public FullListening(Context context, boolean z) {
        this.mFakeForHalfListening = z;
        this.mLights = new EdgeLight[]{new EdgeLight(context.getResources().getColor(2131099879, (Resources.Theme) null)), new EdgeLight(context.getResources().getColor(2131099881, (Resources.Theme) null)), new EdgeLight(context.getResources().getColor(2131099882, (Resources.Theme) null)), new EdgeLight(context.getResources().getColor(2131099880, (Resources.Theme) null))};
    }

    public final EdgeLight[] createPerturbedLights() {
        float f;
        double d;
        double d2;
        float f2 = this.mGuide.mRegions[0].normalizedLength;
        State state = this.mState;
        State state2 = State.LISTENING_TO_SPEECH;
        if (state == state2) {
            if (this.mLastPerturbationWasEven) {
                f = 0.39999998f;
            } else {
                f = 0.6f;
            }
        } else if (this.mLastPerturbationWasEven) {
            f = 0.49f;
        } else {
            f = 0.51f;
        }
        float f3 = f * f2;
        float f4 = f2 / 2.0f;
        float lerp = MathUtils.lerp(Math.min(f4, f3), Math.max(f4, f3), (float) ((double) (this.mRollingConfidence.mTotal / ((float) 3))));
        float f5 = f2 - lerp;
        this.mLastPerturbationWasEven = !this.mLastPerturbationWasEven;
        State state3 = this.mState;
        if (state3 == state2) {
            d = 0.6d;
        } else {
            d = 0.52d;
        }
        if (state3 == state2) {
            d2 = 0.4d;
        } else {
            d2 = 0.48d;
        }
        double d3 = d - d2;
        float random = ((float) ((Math.random() * d3) + d2)) * lerp;
        float random2 = ((float) ((Math.random() * d3) + d2)) * f5;
        float f6 = f5 - random2;
        EdgeLight[] copy = EdgeLight.copy(this.mLights);
        EdgeLight edgeLight = copy[0];
        edgeLight.mLength = random;
        EdgeLight edgeLight2 = copy[1];
        edgeLight2.mLength = random2;
        EdgeLight edgeLight3 = copy[2];
        edgeLight3.mLength = f6;
        EdgeLight edgeLight4 = copy[3];
        edgeLight4.mLength = lerp - random;
        edgeLight.mStart = 0.0f;
        edgeLight2.mStart = random;
        float f7 = random + random2;
        edgeLight3.mStart = f7;
        edgeLight4.mStart = f7 + f6;
        return copy;
    }

    public final EdgeLight[] getFinalLights() {
        EdgeLight[] copy = EdgeLight.copy(this.mLights);
        float f = this.mGuide.mRegions[0].normalizedLength / 4.0f;
        for (int i = 0; i < copy.length; i++) {
            EdgeLight edgeLight = copy[i];
            edgeLight.mStart = ((float) i) * f;
            edgeLight.mLength = f;
        }
        return copy;
    }

    public final int getSubType() {
        return 1;
    }

    public final void onAudioLevelUpdate(float f) {
        long j;
        this.mRollingConfidence.add(f);
        if (f > 0.1f) {
            j = SystemClock.uptimeMillis();
        } else {
            j = this.mLastSpeechTimestampMs;
        }
        this.mLastSpeechTimestampMs = j;
        if (this.mState != State.EXPANDING_TO_WIDTH) {
            updateStateAndAnimation();
        }
    }

    public final void onConfigurationChanged() {
        setAnimator((Animator) null);
        EdgeLight[] edgeLightArr = this.mLights;
        float f = 0.0f;
        for (EdgeLight edgeLight : edgeLightArr) {
            f += edgeLight.mLength;
        }
        float f2 = this.mGuide.mRegions[0].normalizedLength;
        EdgeLight edgeLight2 = edgeLightArr[0];
        edgeLight2.mStart = 0.0f;
        float f3 = (edgeLight2.mLength / f) * f2;
        edgeLight2.mLength = f3;
        EdgeLight edgeLight3 = edgeLightArr[1];
        float f4 = 0.0f + f3;
        edgeLight3.mStart = f4;
        float f5 = (edgeLight3.mLength / f) * f2;
        edgeLight3.mLength = f5;
        EdgeLight edgeLight4 = edgeLightArr[2];
        float f6 = f4 + f5;
        edgeLight4.mStart = f6;
        float f7 = (edgeLight4.mLength / f) * f2;
        edgeLight4.mLength = f7;
        EdgeLight edgeLight5 = edgeLightArr[3];
        edgeLight5.mStart = f6 + f7;
        edgeLight5.mLength = (edgeLight5.mLength / f) * f2;
        updateStateAndAnimation();
    }

    public final void onNewModeRequest(EdgeLightsView edgeLightsView, EdgeLightsView.Mode mode) {
        if (!(mode instanceof FullListening) || ((FullListening) mode).mFakeForHalfListening != this.mFakeForHalfListening) {
            setAnimator((Animator) null);
            edgeLightsView.commitModeTransition(mode);
        }
    }

    public final boolean preventsInvocations() {
        return true;
    }

    public final void setAnimator(Animator animator) {
        Animator animator2 = this.mAnimator;
        if (animator2 != null) {
            animator2.cancel();
        }
        this.mAnimator = animator;
        if (animator != null) {
            animator.start();
        }
    }

    public final void start(EdgeLightsView edgeLightsView, PerimeterPathGuide perimeterPathGuide, EdgeLightsView.Mode mode) {
        EdgeLight[] edgeLightArr;
        boolean z;
        float f;
        float f2;
        this.mEdgeLightsView = edgeLightsView;
        this.mGuide = perimeterPathGuide;
        this.mState = State.EXPANDING_TO_WIDTH;
        edgeLightsView.setVisibility(0);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        long j = 0;
        if (!(mode instanceof FullListening)) {
            if (mode instanceof FulfillBottom) {
                j = 300;
            } else if (edgeLightsView.mAssistInvocationLights.isEmpty()) {
                j = 500;
            }
        }
        ofFloat.setDuration(j);
        ofFloat.setInterpolator(INTERPOLATOR);
        EdgeLight[] edgeLightArr2 = this.mLights;
        EdgeLight[] copy = EdgeLight.copy(edgeLightArr2);
        if (edgeLightsView.getAssistLights() != null) {
            edgeLightArr = EdgeLight.copy(edgeLightsView.getAssistLights());
        } else {
            edgeLightArr = null;
        }
        if (!(mode instanceof FulfillBottom) || edgeLightArr == null || copy.length != edgeLightArr.length) {
            z = false;
        } else {
            z = true;
        }
        for (int i = 0; i < copy.length; i++) {
            EdgeLight edgeLight = copy[i];
            if (z) {
                f = edgeLightArr[i].mStart;
            } else {
                f = perimeterPathGuide.getRegionCenter(PerimeterPathGuide.Region.BOTTOM);
            }
            edgeLight.mStart = f;
            if (z) {
                f2 = edgeLightArr[i].mLength;
            } else {
                f2 = 0.0f;
            }
            edgeLight.mLength = f2;
        }
        ofFloat.addUpdateListener(new EdgeLightUpdateListener(copy, getFinalLights(), edgeLightArr2, edgeLightsView));
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public boolean mCancelled = false;

            public final void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                this.mCancelled = true;
            }

            public final void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                FullListening fullListening = FullListening.this;
                if (fullListening.mAnimator == animator) {
                    fullListening.mAnimator = null;
                }
                if (!this.mCancelled) {
                    fullListening.updateStateAndAnimation();
                }
            }
        });
        setAnimator(ofFloat);
    }

    public final void updateStateAndAnimation() {
        boolean z;
        EdgeLight[] edgeLightArr;
        int i;
        RollingAverage rollingAverage = this.mRollingConfidence;
        float f = (float) 3;
        if (((double) (rollingAverage.mTotal / f)) > 0.10000000149011612d) {
            z = true;
        } else {
            z = false;
        }
        State state = State.LISTENING_TO_SPEECH;
        if (!z) {
            State state2 = this.mState;
            State state3 = State.WAITING_FOR_ENDPOINTER;
            if (state2 != state && state2 != state3) {
                State state4 = State.WAITING_FOR_SPEECH;
                if (state2 != state4 || this.mAnimator == null) {
                    this.mState = state4;
                    edgeLightArr = createPerturbedLights();
                    i = 1200;
                } else {
                    return;
                }
            } else if (this.mAnimator == null || !(state2 == state3 || state2 == state)) {
                this.mState = state3;
                edgeLightArr = getFinalLights();
                i = 2000;
            } else {
                return;
            }
        } else if (this.mState != state || this.mAnimator == null) {
            this.mState = state;
            edgeLightArr = createPerturbedLights();
            i = (int) MathUtils.lerp(400, 150, (float) ((double) (rollingAverage.mTotal / f)));
        } else {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public boolean mCancelled = false;

            public final void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                this.mCancelled = true;
            }

            public final void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                FullListening fullListening = FullListening.this;
                if (fullListening.mAnimator == animator) {
                    fullListening.mAnimator = null;
                }
                if (!this.mCancelled) {
                    fullListening.updateStateAndAnimation();
                }
            }
        });
        ofFloat.setDuration((long) i);
        ofFloat.setInterpolator(INTERPOLATOR);
        EdgeLight[] edgeLightArr2 = this.mLights;
        ofFloat.addUpdateListener(new EdgeLightUpdateListener(EdgeLight.copy(edgeLightArr2), edgeLightArr, edgeLightArr2, this.mEdgeLightsView));
        setAnimator(ofFloat);
    }
}
