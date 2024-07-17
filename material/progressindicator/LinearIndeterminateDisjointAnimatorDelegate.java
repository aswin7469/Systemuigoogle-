package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Property;
import android.view.animation.Interpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.progressindicator.BaseProgressIndicator;
import java.util.Arrays;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LinearIndeterminateDisjointAnimatorDelegate extends IndeterminateAnimatorDelegate {
    public static final AnonymousClass3 ANIMATION_FRACTION = new Property(Float.class, "animationFraction");
    public static final int[] DELAY_TO_MOVE_SEGMENT_ENDS = {1267, 1000, 333, 0};
    public static final int[] DURATION_TO_MOVE_SEGMENT_ENDS = {533, 567, 850, 750};
    public float animationFraction;
    public ObjectAnimator animator;
    public Animatable2Compat.AnimationCallback animatorCompleteCallback = null;
    public final LinearProgressIndicatorSpec baseSpec;
    public ObjectAnimator completeEndAnimator;
    public boolean dirtyColors;
    public int indicatorColorIndex = 0;
    public final Interpolator[] interpolatorArray;

    /* renamed from: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate$3  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass3 extends Property {
        public final Object get(Object obj) {
            return Float.valueOf(((LinearIndeterminateDisjointAnimatorDelegate) obj).animationFraction);
        }

        public final void set(Object obj, Object obj2) {
            ((LinearIndeterminateDisjointAnimatorDelegate) obj).setAnimationFraction(((Float) obj2).floatValue());
        }
    }

    public LinearIndeterminateDisjointAnimatorDelegate(Context context, LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(2);
        this.baseSpec = linearProgressIndicatorSpec;
        this.interpolatorArray = new Interpolator[]{AnimationUtilsCompat.loadInterpolator(2130772513, context), AnimationUtilsCompat.loadInterpolator(2130772514, context), AnimationUtilsCompat.loadInterpolator(2130772515, context), AnimationUtilsCompat.loadInterpolator(2130772516, context)};
    }

    public final void cancelAnimatorImmediately() {
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    public final void registerAnimatorsCompleteCallback(BaseProgressIndicator.AnonymousClass3 r1) {
        this.animatorCompleteCallback = r1;
    }

    public final void requestCancelAnimatorAfterCurrentCycle() {
        ObjectAnimator objectAnimator = this.completeEndAnimator;
        if (objectAnimator != null && !objectAnimator.isRunning()) {
            cancelAnimatorImmediately();
            if (this.drawable.isVisible()) {
                this.completeEndAnimator.setFloatValues(new float[]{this.animationFraction, 1.0f});
                this.completeEndAnimator.setDuration((long) ((1.0f - this.animationFraction) * 1800.0f));
                this.completeEndAnimator.start();
            }
        }
    }

    public void resetPropertiesForNewStart() {
        this.indicatorColorIndex = 0;
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[0], this.drawable.totalAlpha);
        int[] iArr = this.segmentColors;
        iArr[0] = compositeARGBWithAlpha;
        iArr[1] = compositeARGBWithAlpha;
    }

    public void setAnimationFraction(float f) {
        this.animationFraction = f;
        int i = (int) (f * 1800.0f);
        for (int i2 = 0; i2 < 4; i2++) {
            this.segmentPositions[i2] = Math.max(0.0f, Math.min(1.0f, this.interpolatorArray[i2].getInterpolation(((float) (i - DELAY_TO_MOVE_SEGMENT_ENDS[i2])) / ((float) DURATION_TO_MOVE_SEGMENT_ENDS[i2]))));
        }
        if (this.dirtyColors) {
            Arrays.fill(this.segmentColors, MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[this.indicatorColorIndex], this.drawable.totalAlpha));
            this.dirtyColors = false;
        }
        this.drawable.invalidateSelf();
    }

    public final void startAnimator() {
        ObjectAnimator objectAnimator = this.animator;
        AnonymousClass3 r3 = ANIMATION_FRACTION;
        if (objectAnimator == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, r3, new float[]{0.0f, 1.0f});
            this.animator = ofFloat;
            ofFloat.setDuration(1800);
            this.animator.setInterpolator((TimeInterpolator) null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter(this, 0) {
                public final /* synthetic */ LinearIndeterminateDisjointAnimatorDelegate this$0;

                {
                    this.this$0 = r1;
                }

                public final void onAnimationEnd(Animator animator) {
                    switch (1) {
                        case 1:
                            super.onAnimationEnd(animator);
                            this.this$0.cancelAnimatorImmediately();
                            LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate = this.this$0;
                            Animatable2Compat.AnimationCallback animationCallback = linearIndeterminateDisjointAnimatorDelegate.animatorCompleteCallback;
                            if (animationCallback != null) {
                                animationCallback.onAnimationEnd(linearIndeterminateDisjointAnimatorDelegate.drawable);
                                return;
                            }
                            return;
                        default:
                            super.onAnimationEnd(animator);
                            return;
                    }
                }

                public final void onAnimationRepeat(Animator animator) {
                    switch (1) {
                        case 0:
                            super.onAnimationRepeat(animator);
                            LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate = this.this$0;
                            linearIndeterminateDisjointAnimatorDelegate.indicatorColorIndex = (linearIndeterminateDisjointAnimatorDelegate.indicatorColorIndex + 1) % linearIndeterminateDisjointAnimatorDelegate.baseSpec.indicatorColors.length;
                            linearIndeterminateDisjointAnimatorDelegate.dirtyColors = true;
                            return;
                        default:
                            super.onAnimationRepeat(animator);
                            return;
                    }
                }
            });
        }
        if (this.completeEndAnimator == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, r3, new float[]{1.0f});
            this.completeEndAnimator = ofFloat2;
            ofFloat2.setDuration(1800);
            this.completeEndAnimator.setInterpolator((TimeInterpolator) null);
            this.completeEndAnimator.addListener(new AnimatorListenerAdapter(this, 1) {
                public final /* synthetic */ LinearIndeterminateDisjointAnimatorDelegate this$0;

                {
                    this.this$0 = r1;
                }

                public final void onAnimationEnd(Animator animator) {
                    switch (1) {
                        case 1:
                            super.onAnimationEnd(animator);
                            this.this$0.cancelAnimatorImmediately();
                            LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate = this.this$0;
                            Animatable2Compat.AnimationCallback animationCallback = linearIndeterminateDisjointAnimatorDelegate.animatorCompleteCallback;
                            if (animationCallback != null) {
                                animationCallback.onAnimationEnd(linearIndeterminateDisjointAnimatorDelegate.drawable);
                                return;
                            }
                            return;
                        default:
                            super.onAnimationEnd(animator);
                            return;
                    }
                }

                public final void onAnimationRepeat(Animator animator) {
                    switch (1) {
                        case 0:
                            super.onAnimationRepeat(animator);
                            LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate = this.this$0;
                            linearIndeterminateDisjointAnimatorDelegate.indicatorColorIndex = (linearIndeterminateDisjointAnimatorDelegate.indicatorColorIndex + 1) % linearIndeterminateDisjointAnimatorDelegate.baseSpec.indicatorColors.length;
                            linearIndeterminateDisjointAnimatorDelegate.dirtyColors = true;
                            return;
                        default:
                            super.onAnimationRepeat(animator);
                            return;
                    }
                }
            });
        }
        resetPropertiesForNewStart();
        this.animator.start();
    }

    public final void unregisterAnimatorsCompleteCallback() {
        this.animatorCompleteCallback = null;
    }
}
