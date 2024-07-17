package com.google.android.systemui.assist.uihints;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.MathUtils;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.navigationbar.NavigationModeController;
import com.google.android.systemui.assist.uihints.BlurProvider;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsListener;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;
import com.google.android.systemui.assist.uihints.edgelights.mode.FulfillBottom;
import com.google.android.systemui.assist.uihints.edgelights.mode.FullListening;
import com.google.android.systemui.assist.uihints.edgelights.mode.Gone;
import com.google.android.systemui.assist.uihints.input.TouchInsideRegion;
import java.util.Iterator;
import java.util.Optional;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class GlowController implements NgaMessageHandler.AudioInfoListener, NgaMessageHandler.CardInfoListener, EdgeLightsListener, TouchInsideRegion {
    public ValueAnimator mAnimator = null;
    public final Context mContext;
    public EdgeLight[] mEdgeLights = null;
    public EdgeLightsView.Mode mEdgeLightsMode = null;
    public final GlowView mGlowView;
    public int mGlowsY = 0;
    public int mGlowsYDestination = 0;
    public boolean mInvocationCompleting = false;
    public float mMedianLightness;
    public RollingAverage mSpeechRolling = new RollingAverage();
    public NgaUiController$$ExternalSyntheticLambda2 mVisibilityListener;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    enum GlowState {
    }

    public GlowController(Context context, ViewGroup viewGroup, TouchInsideHandler touchInsideHandler, NavigationModeController navigationModeController) {
        this.mContext = context;
        navigationModeController.addListener(new GlowController$$ExternalSyntheticLambda2(this));
        GlowView glowView = (GlowView) viewGroup.findViewById(2131362608);
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

    public final void animateGlowTranslationY(int i) {
        EdgeLight[] edgeLightArr;
        long min = (long) Math.min((float) 400, Math.abs((float) (i - this.mGlowsY)) / ((float) (((long) Math.abs(getMaxTranslationY() - getMinTranslationY())) / 400)));
        int i2 = this.mGlowsYDestination;
        GlowView glowView = this.mGlowView;
        if (i == i2) {
            int i3 = this.mGlowsY;
            int minTranslationY = getMinTranslationY();
            if (this.mEdgeLightsMode instanceof FullListening) {
                edgeLightArr = this.mEdgeLights;
            } else {
                edgeLightArr = null;
            }
            glowView.setGlowsY(i3, minTranslationY, edgeLightArr);
            return;
        }
        this.mGlowsYDestination = i;
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mGlowsY, i});
        this.mAnimator = ofInt;
        ofInt.addUpdateListener(new GlowController$$ExternalSyntheticLambda0(this));
        this.mAnimator.addListener(new AnimatorListenerAdapter() {
            public final void onAnimationEnd(Animator animator) {
                GlowController glowController = GlowController.this;
                glowController.mAnimator = null;
                if (GlowState.GONE.equals(glowController.getState())) {
                    GlowController.this.setVisibility$1(8);
                } else {
                    GlowController.this.maybeAnimateForSpeechConfidence();
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
            setVisibility$1(0);
        }
        this.mAnimator.start();
    }

    public final int getBlurRadius() {
        if (getState() == GlowState.GONE) {
            return this.mGlowView.mBlurRadius;
        }
        GlowState state = getState();
        GlowState glowState = GlowState.SHORT_DARK_BACKGROUND;
        Context context = this.mContext;
        if (state == glowState || getState() == GlowState.SHORT_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165944);
        }
        if (getState() == GlowState.TALL_DARK_BACKGROUND || getState() == GlowState.TALL_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165947);
        }
        return 0;
    }

    public final int getMaxTranslationY() {
        GlowState state = getState();
        GlowState glowState = GlowState.SHORT_DARK_BACKGROUND;
        Context context = this.mContext;
        if (state == glowState || getState() == GlowState.SHORT_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165945);
        }
        if (getState() == GlowState.TALL_DARK_BACKGROUND || getState() == GlowState.TALL_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165948);
        }
        return context.getResources().getDimensionPixelSize(2131165941);
    }

    public final int getMinTranslationY() {
        GlowState state = getState();
        GlowState glowState = GlowState.SHORT_DARK_BACKGROUND;
        Context context = this.mContext;
        if (state == glowState || getState() == GlowState.SHORT_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165946);
        }
        if (getState() == GlowState.TALL_DARK_BACKGROUND || getState() == GlowState.TALL_LIGHT_BACKGROUND) {
            return context.getResources().getDimensionPixelSize(2131165949);
        }
        return context.getResources().getDimensionPixelSize(2131165942);
    }

    public final GlowState getState() {
        boolean z;
        EdgeLightsView.Mode mode = this.mEdgeLightsMode;
        if (!(mode instanceof FulfillBottom) || ((FulfillBottom) mode).mIsListening) {
            z = false;
        } else {
            z = true;
        }
        if ((mode instanceof Gone) || mode == null || z) {
            return GlowState.GONE;
        }
        if (this.mMedianLightness < 0.4f) {
            return GlowState.TALL_DARK_BACKGROUND;
        }
        return GlowState.TALL_LIGHT_BACKGROUND;
    }

    public final Optional getTouchInsideRegion() {
        GlowView glowView = this.mGlowView;
        if (glowView.getVisibility() != 0) {
            return Optional.empty();
        }
        Rect rect = new Rect();
        glowView.getBoundsOnScreen(rect);
        rect.top = rect.bottom - getMaxTranslationY();
        return Optional.of(new Region(rect));
    }

    public final void maybeAnimateForSpeechConfidence() {
        EdgeLightsView.Mode mode = this.mEdgeLightsMode;
        if ((mode instanceof FullListening) || (mode instanceof FulfillBottom)) {
            float f = (float) 3;
            if (((double) (this.mSpeechRolling.mTotal / f)) >= 0.30000001192092896d || this.mGlowsYDestination > getMinTranslationY()) {
                animateGlowTranslationY((int) MathUtils.lerp(getMinTranslationY(), getMaxTranslationY(), (float) ((double) (this.mSpeechRolling.mTotal / f))));
            }
        }
    }

    public final void onAssistLightsUpdated(EdgeLightsView.Mode mode, EdgeLight[] edgeLightArr) {
        int i;
        if (!(this.mEdgeLightsMode instanceof FullListening)) {
            this.mEdgeLights = null;
            this.mGlowView.distributeEvenly();
            return;
        }
        this.mEdgeLights = edgeLightArr;
        if ((this.mInvocationCompleting && (mode instanceof Gone)) || !(mode instanceof FullListening)) {
            return;
        }
        if (edgeLightArr == null || edgeLightArr.length != 4) {
            StringBuilder sb = new StringBuilder("Expected 4 lights, have ");
            if (edgeLightArr == null) {
                i = 0;
            } else {
                i = edgeLightArr.length;
            }
            sb.append(i);
            Log.e("GlowController", sb.toString());
            return;
        }
        maybeAnimateForSpeechConfidence();
    }

    public final void onAudioInfo(float f, float f2) {
        this.mSpeechRolling.add(f2);
        maybeAnimateForSpeechConfidence();
    }

    public final void onModeStarted(EdgeLightsView.Mode mode) {
        boolean z = mode instanceof Gone;
        if (!z || this.mEdgeLightsMode != null) {
            this.mInvocationCompleting = !z;
            this.mEdgeLightsMode = mode;
            if (z) {
                this.mSpeechRolling = new RollingAverage();
            }
            animateGlowTranslationY(getMinTranslationY());
            return;
        }
        this.mEdgeLightsMode = mode;
    }

    public final void setVisibility$1(int i) {
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
            NgaUiController$$ExternalSyntheticLambda2 ngaUiController$$ExternalSyntheticLambda2 = this.mVisibilityListener;
            if (ngaUiController$$ExternalSyntheticLambda2 != null) {
                ((NgaUiController) ngaUiController$$ExternalSyntheticLambda2.f$0).refresh$2();
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

    public final void onCardInfo(int i, boolean z, boolean z2, boolean z3) {
    }
}
