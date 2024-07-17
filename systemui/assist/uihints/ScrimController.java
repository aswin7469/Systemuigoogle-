package com.google.android.systemui.assist.uihints;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.TranscriptionController;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsListener;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;
import com.google.android.systemui.assist.uihints.edgelights.mode.FullListening;
import com.google.android.systemui.assist.uihints.input.TouchInsideRegion;
import java.util.Optional;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ScrimController implements TranscriptionController.TranscriptionSpaceListener, NgaMessageHandler.CardInfoListener, EdgeLightsListener, TouchInsideRegion {
    public static final LinearInterpolator ALPHA_INTERPOLATOR = new LinearInterpolator();
    public ValueAnimator mAlphaAnimator = new ValueAnimator();
    public boolean mCardForcesScrimGone = false;
    public boolean mCardTransitionAnimated = false;
    public boolean mCardVisible = false;
    public boolean mHaveAccurateLightness = false;
    public boolean mInFullListening = false;
    public float mInvocationProgress = 0.0f;
    public boolean mIsDozing = false;
    public final LightnessProvider mLightnessProvider;
    public float mMedianLightness;
    public final OverlappedElementController mOverlappedElement;
    public final View mScrimView;
    public boolean mTranscriptionVisible = false;
    public NgaUiController$$ExternalSyntheticLambda2 mVisibilityListener;

    public ScrimController(ViewGroup viewGroup, OverlappedElementController overlappedElementController, LightnessProvider lightnessProvider, TouchInsideHandler touchInsideHandler) {
        View findViewById = viewGroup.findViewById(2131363506);
        this.mScrimView = findViewById;
        findViewById.setBackgroundTintBlendMode(BlendMode.SRC_IN);
        this.mLightnessProvider = lightnessProvider;
        findViewById.setOnClickListener(touchInsideHandler);
        findViewById.setOnTouchListener(touchInsideHandler);
        this.mOverlappedElement = overlappedElementController;
    }

    public final ValueAnimator createRelativeAlphaAnimator(float f) {
        View view = this.mScrimView;
        ValueAnimator duration = ValueAnimator.ofFloat(new float[]{view.getAlpha(), f}).setDuration((long) (Math.abs(f - view.getAlpha()) * 300.0f));
        duration.setInterpolator(ALPHA_INTERPOLATOR);
        duration.addUpdateListener(new ScrimController$$ExternalSyntheticLambda0(this));
        return duration;
    }

    public final Optional getTouchInsideRegion() {
        View view = this.mScrimView;
        if (view.getVisibility() != 0) {
            return Optional.empty();
        }
        Rect rect = new Rect();
        view.getHitRect(rect);
        rect.top = rect.bottom - view.getResources().getDimensionPixelSize(2131167400);
        return Optional.of(new Region(rect));
    }

    public final void onCardInfo(int i, boolean z, boolean z2, boolean z3) {
        this.mCardVisible = z;
        this.mCardTransitionAnimated = z2;
        this.mCardForcesScrimGone = z3;
        refresh$1();
    }

    public final void onModeStarted(EdgeLightsView.Mode mode) {
        this.mInFullListening = mode instanceof FullListening;
        refresh$1();
    }

    public final void refresh$1() {
        if (!this.mHaveAccurateLightness || this.mIsDozing) {
            setRelativeAlpha(0.0f, false);
            return;
        }
        boolean z = this.mCardVisible;
        if (z && this.mCardForcesScrimGone) {
            setRelativeAlpha(0.0f, this.mCardTransitionAnimated);
        } else if (this.mInFullListening || this.mTranscriptionVisible) {
            if (!z || this.mScrimView.getVisibility() == 0) {
                setRelativeAlpha(1.0f, false);
            }
        } else if (z) {
            setRelativeAlpha(0.0f, this.mCardTransitionAnimated);
        } else {
            float f = this.mInvocationProgress;
            if (f > 0.0f) {
                setRelativeAlpha(Math.min(1.0f, f), false);
            } else {
                setRelativeAlpha(0.0f, true);
            }
        }
    }

    public final void setRelativeAlpha(float f, boolean z) {
        int i;
        if (!this.mHaveAccurateLightness && f > 0.0f) {
            return;
        }
        if (f < 0.0f || f > 1.0f) {
            Log.e("ScrimController", "Got unexpected alpha: " + f + ", ignoring");
            return;
        }
        if (this.mAlphaAnimator.isRunning()) {
            this.mAlphaAnimator.cancel();
        }
        int i2 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        OverlappedElementController overlappedElementController = this.mOverlappedElement;
        View view = this.mScrimView;
        if (i2 > 0) {
            if (view.getVisibility() != 0) {
                if (this.mMedianLightness <= 0.4f) {
                    i = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
                } else {
                    i = -1;
                }
                view.setBackgroundTintList(ColorStateList.valueOf(i));
                setVisibility$2(0);
            }
            if (z) {
                ValueAnimator createRelativeAlphaAnimator = createRelativeAlphaAnimator(f);
                this.mAlphaAnimator = createRelativeAlphaAnimator;
                createRelativeAlphaAnimator.start();
                return;
            }
            view.setAlpha(f);
            overlappedElementController.setAlpha(1.0f - f);
        } else if (z) {
            ValueAnimator createRelativeAlphaAnimator2 = createRelativeAlphaAnimator(f);
            this.mAlphaAnimator = createRelativeAlphaAnimator2;
            createRelativeAlphaAnimator2.addListener(new AnimatorListenerAdapter() {
                public boolean mCancelled = false;

                public final void onAnimationCancel(Animator animator) {
                    super.onAnimationCancel(animator);
                    this.mCancelled = true;
                }

                public final void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    if (!this.mCancelled) {
                        ScrimController.this.setVisibility$2(8);
                    }
                }
            });
            this.mAlphaAnimator.start();
        } else {
            view.setAlpha(0.0f);
            overlappedElementController.setAlpha(1.0f);
            setVisibility$2(8);
        }
    }

    public final void setVisibility$2(int i) {
        boolean z;
        Drawable drawable;
        View view = this.mScrimView;
        if (i != view.getVisibility()) {
            view.setVisibility(i);
            NgaUiController$$ExternalSyntheticLambda2 ngaUiController$$ExternalSyntheticLambda2 = this.mVisibilityListener;
            if (ngaUiController$$ExternalSyntheticLambda2 != null) {
                ((NgaUiController) ngaUiController$$ExternalSyntheticLambda2.f$0).refresh$2();
            }
            if (i == 0) {
                z = true;
            } else {
                z = false;
            }
            this.mLightnessProvider.mMuted = z;
            if (i == 0) {
                drawable = view.getContext().getDrawable(2131233508);
            } else {
                drawable = null;
            }
            view.setBackground(drawable);
            if (i != 0) {
                this.mOverlappedElement.setAlpha(1.0f);
                refresh$1();
            }
        }
    }
}
