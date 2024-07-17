package com.google.android.systemui.assist.uihints;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.navigationbar.NavigationModeController;
import com.google.android.systemui.assist.uihints.BlurProvider;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsListener;
import com.google.android.systemui.assist.uihints.edgelights.mode.Gone;
import java.util.Iterator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class GlowController implements EdgeLightsListener {
    public ValueAnimator mAnimator = null;
    public final Context mContext;
    public Gone mEdgeLightsMode = null;
    public final GlowView mGlowView;
    public int mGlowsY = 0;
    public int mGlowsYDestination = 0;
    public float mMedianLightness;
    public NgaUiController$$ExternalSyntheticLambda6 mVisibilityListener;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    enum GlowState {
    }

    public GlowController(Context context, ViewGroup viewGroup, TouchInsideHandler touchInsideHandler, NavigationModeController navigationModeController) {
        new RollingAverage();
        this.mContext = context;
        navigationModeController.addListener(new GlowController$$ExternalSyntheticLambda2(this));
        GlowView glowView = (GlowView) viewGroup.findViewById(2131362628);
        this.mGlowView = glowView;
        int i = this.mGlowsY;
        glowView.setGlowsY(i, i, (EdgeLight[]) null);
        glowView.setOnClickListener(touchInsideHandler);
        glowView.setOnTouchListener(touchInsideHandler);
        glowView.setGlowsY(getMinTranslationY(), getMinTranslationY(), (EdgeLight[]) null);
        if (glowView.mGlowWidthRatio != 0.55f) {
            glowView.mGlowWidthRatio = 0.55f;
            glowView.updateGlowSizes();
            glowView.distributeEvenly();
        }
    }

    public final int getBlurRadius() {
        if (getState() == GlowState.GONE) {
            return this.mGlowView.mBlurRadius;
        }
        GlowState state = getState();
        GlowState glowState = GlowState.SHORT_DARK_BACKGROUND;
        Context context = this.mContext;
        if (state == glowState || getState() == GlowState.SHORT_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165977);
        }
        if (getState() == GlowState.TALL_DARK_BACKGROUND || getState() == GlowState.TALL_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165980);
        }
        return 0;
    }

    public final int getMaxTranslationY() {
        GlowState state = getState();
        GlowState glowState = GlowState.SHORT_DARK_BACKGROUND;
        Context context = this.mContext;
        if (state == glowState || getState() == GlowState.SHORT_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165978);
        }
        if (getState() == GlowState.TALL_DARK_BACKGROUND || getState() == GlowState.TALL_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165981);
        }
        return context.getResources().getDimensionPixelSize(2131165974);
    }

    public final int getMinTranslationY() {
        GlowState state = getState();
        GlowState glowState = GlowState.SHORT_DARK_BACKGROUND;
        Context context = this.mContext;
        if (state == glowState || getState() == GlowState.SHORT_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165979);
        }
        if (getState() == GlowState.TALL_DARK_BACKGROUND || getState() == GlowState.TALL_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165982);
        }
        return context.getResources().getDimensionPixelSize(2131165975);
    }

    public final GlowState getState() {
        Gone gone = this.mEdgeLightsMode;
        if ((gone instanceof Gone) || gone == null) {
            return GlowState.GONE;
        }
        if (this.mMedianLightness < 0.4f) {
            return GlowState.TALL_DARK_BACKGROUND;
        }
        return GlowState.TALL_LIGHT_BACKGROUND;
    }

    public final void onAssistLightsUpdated() {
        this.mGlowView.distributeEvenly();
    }

    public final void onModeStarted(Gone gone) {
        boolean z = gone instanceof Gone;
        if (!z || this.mEdgeLightsMode != null) {
            this.mEdgeLightsMode = gone;
            if (z) {
                new RollingAverage();
            }
            int minTranslationY = getMinTranslationY();
            long min = (long) Math.min((float) 400, Math.abs((float) (minTranslationY - this.mGlowsY)) / ((float) (((long) Math.abs(getMaxTranslationY() - getMinTranslationY())) / 400)));
            int i = this.mGlowsYDestination;
            GlowView glowView = this.mGlowView;
            if (minTranslationY == i) {
                glowView.setGlowsY(this.mGlowsY, getMinTranslationY(), (EdgeLight[]) null);
                return;
            }
            this.mGlowsYDestination = minTranslationY;
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mGlowsY, minTranslationY});
            this.mAnimator = ofInt;
            ofInt.addUpdateListener(new GlowController$$ExternalSyntheticLambda0(this));
            this.mAnimator.addListener(new AnimatorListenerAdapter() {
                public final void onAnimationEnd(Animator animator) {
                    GlowController glowController = GlowController.this;
                    glowController.mAnimator = null;
                    if (GlowState.GONE.equals(glowController.getState())) {
                        GlowController.this.setVisibility(8);
                    } else {
                        GlowController.this.getClass();
                    }
                }
            });
            this.mAnimator.setInterpolator(new LinearInterpolator());
            this.mAnimator.setDuration(min);
            this.mAnimator.addUpdateListener(new GlowController$$ExternalSyntheticLambda1(this, glowView.mBlurRadius, getBlurRadius()));
            float f = glowView.mGlowWidthRatio;
            float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(0.55f, f, 1.0f, f);
            if (f != m) {
                glowView.mGlowWidthRatio = m;
                glowView.updateGlowSizes();
                glowView.distributeEvenly();
            }
            if (glowView.getVisibility() != 0) {
                setVisibility(0);
            }
            this.mAnimator.start();
            return;
        }
        this.mEdgeLightsMode = gone;
    }

    public final void setVisibility(int i) {
        boolean z;
        GlowView glowView = this.mGlowView;
        glowView.setVisibility(i);
        boolean z2 = false;
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (glowView.getVisibility() == 0) {
            z2 = true;
        }
        if (z != z2) {
            NgaUiController$$ExternalSyntheticLambda6 ngaUiController$$ExternalSyntheticLambda6 = this.mVisibilityListener;
            if (ngaUiController$$ExternalSyntheticLambda6 != null) {
                ngaUiController$$ExternalSyntheticLambda6.f$0.refresh$1();
            }
            if (glowView.getVisibility() != 0) {
                Iterator it = glowView.mGlowImageViews.iterator();
                while (it.hasNext()) {
                    ((ImageView) it.next()).setImageDrawable((Drawable) null);
                }
                BlurProvider blurProvider = glowView.mBlurProvider;
                blurProvider.mBuffer = null;
                BlurProvider.BlurKernel blurKernel = blurProvider.mBlurKernel;
                blurKernel.prepareInputAllocation((Bitmap) null);
                blurKernel.prepareOutputAllocation((Bitmap) null);
            }
        }
    }
}
