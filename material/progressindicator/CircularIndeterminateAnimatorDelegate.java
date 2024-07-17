package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Property;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.progressindicator.BaseProgressIndicator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CircularIndeterminateAnimatorDelegate extends IndeterminateAnimatorDelegate {
    public static final AnonymousClass3 ANIMATION_FRACTION = new Property("animationFraction", 0) {
        public final Object get(Object obj) {
            int i = 1;
            switch (i) {
                case 0:
                    CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate = (CircularIndeterminateAnimatorDelegate) obj;
                    switch (i) {
                        case 0:
                            return Float.valueOf(circularIndeterminateAnimatorDelegate.animationFraction);
                        default:
                            return Float.valueOf(circularIndeterminateAnimatorDelegate.completeEndFraction);
                    }
                default:
                    CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate2 = (CircularIndeterminateAnimatorDelegate) obj;
                    switch (i) {
                        case 0:
                            return Float.valueOf(circularIndeterminateAnimatorDelegate2.animationFraction);
                        default:
                            return Float.valueOf(circularIndeterminateAnimatorDelegate2.completeEndFraction);
                    }
            }
        }

        public final void set(Object obj, Object obj2) {
            int i = 1;
            switch (i) {
                case 0:
                    CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate = (CircularIndeterminateAnimatorDelegate) obj;
                    Float f = (Float) obj2;
                    switch (i) {
                        case 0:
                            circularIndeterminateAnimatorDelegate.setAnimationFraction(f.floatValue());
                            return;
                        default:
                            circularIndeterminateAnimatorDelegate.completeEndFraction = f.floatValue();
                            return;
                    }
                default:
                    CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate2 = (CircularIndeterminateAnimatorDelegate) obj;
                    Float f2 = (Float) obj2;
                    switch (i) {
                        case 0:
                            circularIndeterminateAnimatorDelegate2.setAnimationFraction(f2.floatValue());
                            return;
                        default:
                            circularIndeterminateAnimatorDelegate2.completeEndFraction = f2.floatValue();
                            return;
                    }
            }
        }
    };
    public static final AnonymousClass3 COMPLETE_END_FRACTION = new Property("completeEndFraction", 1) {
        public final Object get(Object obj) {
            int i = 1;
            switch (i) {
                case 0:
                    CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate = (CircularIndeterminateAnimatorDelegate) obj;
                    switch (i) {
                        case 0:
                            return Float.valueOf(circularIndeterminateAnimatorDelegate.animationFraction);
                        default:
                            return Float.valueOf(circularIndeterminateAnimatorDelegate.completeEndFraction);
                    }
                default:
                    CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate2 = (CircularIndeterminateAnimatorDelegate) obj;
                    switch (i) {
                        case 0:
                            return Float.valueOf(circularIndeterminateAnimatorDelegate2.animationFraction);
                        default:
                            return Float.valueOf(circularIndeterminateAnimatorDelegate2.completeEndFraction);
                    }
            }
        }

        public final void set(Object obj, Object obj2) {
            int i = 1;
            switch (i) {
                case 0:
                    CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate = (CircularIndeterminateAnimatorDelegate) obj;
                    Float f = (Float) obj2;
                    switch (i) {
                        case 0:
                            circularIndeterminateAnimatorDelegate.setAnimationFraction(f.floatValue());
                            return;
                        default:
                            circularIndeterminateAnimatorDelegate.completeEndFraction = f.floatValue();
                            return;
                    }
                default:
                    CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate2 = (CircularIndeterminateAnimatorDelegate) obj;
                    Float f2 = (Float) obj2;
                    switch (i) {
                        case 0:
                            circularIndeterminateAnimatorDelegate2.setAnimationFraction(f2.floatValue());
                            return;
                        default:
                            circularIndeterminateAnimatorDelegate2.completeEndFraction = f2.floatValue();
                            return;
                    }
            }
        }
    };
    public static final int[] DELAY_TO_COLLAPSE_IN_MS = {667, 2017, 3367, 4717};
    public static final int[] DELAY_TO_EXPAND_IN_MS = {0, 1350, 2700, 4050};
    public static final int[] DELAY_TO_FADE_IN_MS = {1000, 2350, 3700, 5050};
    public float animationFraction;
    public ObjectAnimator animator;
    public Animatable2Compat.AnimationCallback animatorCompleteCallback = null;
    public final CircularProgressIndicatorSpec baseSpec;
    public ObjectAnimator completeEndAnimator;
    public float completeEndFraction;
    public int indicatorColorIndexOffset = 0;
    public final FastOutSlowInInterpolator interpolator;

    /* JADX WARNING: type inference failed for: r2v1, types: [androidx.interpolator.view.animation.FastOutSlowInInterpolator, java.lang.Object] */
    public CircularIndeterminateAnimatorDelegate(CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(1);
        this.baseSpec = circularProgressIndicatorSpec;
        this.interpolator = new Object();
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
            if (this.drawable.isVisible()) {
                this.completeEndAnimator.start();
            } else {
                cancelAnimatorImmediately();
            }
        }
    }

    public void resetPropertiesForNewStart() {
        this.indicatorColorIndexOffset = 0;
        this.segmentColors[0] = MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[0], this.drawable.totalAlpha);
        this.completeEndFraction = 0.0f;
    }

    public void setAnimationFraction(float f) {
        FastOutSlowInInterpolator fastOutSlowInInterpolator;
        this.animationFraction = f;
        int i = (int) (5400.0f * f);
        float f2 = f * 1520.0f;
        float[] fArr = this.segmentPositions;
        fArr[0] = -20.0f + f2;
        fArr[1] = f2;
        int i2 = 0;
        while (true) {
            fastOutSlowInInterpolator = this.interpolator;
            if (i2 >= 4) {
                break;
            }
            float f3 = (float) 667;
            fArr[1] = (fastOutSlowInInterpolator.getInterpolation(((float) (i - DELAY_TO_EXPAND_IN_MS[i2])) / f3) * 250.0f) + fArr[1];
            fArr[0] = (fastOutSlowInInterpolator.getInterpolation(((float) (i - DELAY_TO_COLLAPSE_IN_MS[i2])) / f3) * 250.0f) + fArr[0];
            i2++;
        }
        float f4 = fArr[0];
        float f5 = fArr[1];
        float f6 = ((f5 - f4) * this.completeEndFraction) + f4;
        fArr[0] = f6;
        fArr[0] = f6 / 360.0f;
        fArr[1] = f5 / 360.0f;
        int i3 = 0;
        while (true) {
            if (i3 >= 4) {
                break;
            }
            float f7 = ((float) (i - DELAY_TO_FADE_IN_MS[i3])) / ((float) 333);
            if (f7 >= 0.0f && f7 <= 1.0f) {
                int i4 = i3 + this.indicatorColorIndexOffset;
                CircularProgressIndicatorSpec circularProgressIndicatorSpec = this.baseSpec;
                int[] iArr = circularProgressIndicatorSpec.indicatorColors;
                int length = i4 % iArr.length;
                int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(iArr[length], this.drawable.totalAlpha);
                int compositeARGBWithAlpha2 = MaterialColors.compositeARGBWithAlpha(circularProgressIndicatorSpec.indicatorColors[(length + 1) % iArr.length], this.drawable.totalAlpha);
                float interpolation = fastOutSlowInInterpolator.getInterpolation(f7);
                ArgbEvaluatorCompat argbEvaluatorCompat = ArgbEvaluatorCompat.instance;
                this.segmentColors[0] = ArgbEvaluatorCompat.evaluate(interpolation, Integer.valueOf(compositeARGBWithAlpha), Integer.valueOf(compositeARGBWithAlpha2)).intValue();
                break;
            }
            i3++;
        }
        this.drawable.invalidateSelf();
    }

    public final void startAnimator() {
        if (this.animator == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, new float[]{0.0f, 1.0f});
            this.animator = ofFloat;
            ofFloat.setDuration(5400);
            this.animator.setInterpolator((TimeInterpolator) null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter(this, 0) {
                public final /* synthetic */ CircularIndeterminateAnimatorDelegate this$0;

                {
                    this.this$0 = r1;
                }

                public final void onAnimationEnd(Animator animator) {
                    switch (1) {
                        case 1:
                            super.onAnimationEnd(animator);
                            this.this$0.cancelAnimatorImmediately();
                            CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate = this.this$0;
                            Animatable2Compat.AnimationCallback animationCallback = circularIndeterminateAnimatorDelegate.animatorCompleteCallback;
                            if (animationCallback != null) {
                                animationCallback.onAnimationEnd(circularIndeterminateAnimatorDelegate.drawable);
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
                            CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate = this.this$0;
                            circularIndeterminateAnimatorDelegate.indicatorColorIndexOffset = (circularIndeterminateAnimatorDelegate.indicatorColorIndexOffset + 4) % circularIndeterminateAnimatorDelegate.baseSpec.indicatorColors.length;
                            return;
                        default:
                            super.onAnimationRepeat(animator);
                            return;
                    }
                }
            });
        }
        if (this.completeEndAnimator == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, COMPLETE_END_FRACTION, new float[]{0.0f, 1.0f});
            this.completeEndAnimator = ofFloat2;
            ofFloat2.setDuration(333);
            this.completeEndAnimator.setInterpolator(this.interpolator);
            this.completeEndAnimator.addListener(new AnimatorListenerAdapter(this, 1) {
                public final /* synthetic */ CircularIndeterminateAnimatorDelegate this$0;

                {
                    this.this$0 = r1;
                }

                public final void onAnimationEnd(Animator animator) {
                    switch (1) {
                        case 1:
                            super.onAnimationEnd(animator);
                            this.this$0.cancelAnimatorImmediately();
                            CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate = this.this$0;
                            Animatable2Compat.AnimationCallback animationCallback = circularIndeterminateAnimatorDelegate.animatorCompleteCallback;
                            if (animationCallback != null) {
                                animationCallback.onAnimationEnd(circularIndeterminateAnimatorDelegate.drawable);
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
                            CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate = this.this$0;
                            circularIndeterminateAnimatorDelegate.indicatorColorIndexOffset = (circularIndeterminateAnimatorDelegate.indicatorColorIndexOffset + 4) % circularIndeterminateAnimatorDelegate.baseSpec.indicatorColors.length;
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
