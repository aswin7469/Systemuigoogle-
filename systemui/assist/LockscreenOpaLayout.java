package com.google.android.systemui.assist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import com.android.app.animation.Interpolators;
import com.google.android.systemui.elmyra.feedback.FeedbackEffect;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class LockscreenOpaLayout extends FrameLayout implements FeedbackEffect {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Interpolator INTERPOLATOR_5_100 = new PathInterpolator(1.0f, 0.0f, 0.95f, 1.0f);
    public final ArrayList mAnimatedViews = new ArrayList();
    public View mBlue;
    public AnimatorSet mCannedAnimatorSet;
    public final ArraySet mCurrentAnimators = new ArraySet();
    public AnimatorSet mGestureAnimatorSet;
    public int mGestureState = 0;
    public View mGreen;
    public AnimatorSet mLineAnimatorSet;
    public View mRed;
    public Resources mResources;
    public View mYellow;

    /* renamed from: com.google.android.systemui.assist.LockscreenOpaLayout$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass1 extends AnimatorListenerAdapter {
        public final void onAnimationEnd(Animator animator) {
            switch (1) {
                case 0:
                    LockscreenOpaLayout lockscreenOpaLayout = (LockscreenOpaLayout) this;
                    lockscreenOpaLayout.mGestureState = 1;
                    lockscreenOpaLayout.mGestureAnimatorSet = LockscreenOpaLayout.m917$$Nest$mgetLineAnimatorSet(lockscreenOpaLayout);
                    ((LockscreenOpaLayout) this).mGestureAnimatorSet.setCurrentPlayTime(0);
                    return;
                case 1:
                    ((LockscreenOpaLayout) this).mCurrentAnimators.clear();
                    ((LockscreenOpaLayout) this).skipToStartingValue();
                    LockscreenOpaLayout lockscreenOpaLayout2 = (LockscreenOpaLayout) this;
                    lockscreenOpaLayout2.mGestureState = 0;
                    lockscreenOpaLayout2.mGestureAnimatorSet = null;
                    return;
                case 2:
                    ((LockscreenOpaLayout) this).mCurrentAnimators.clear();
                    LockscreenOpaLayout lockscreenOpaLayout3 = (LockscreenOpaLayout) this;
                    lockscreenOpaLayout3.mGestureAnimatorSet = null;
                    lockscreenOpaLayout3.mGestureState = 0;
                    lockscreenOpaLayout3.skipToStartingValue();
                    return;
                case 3:
                    int i = LockscreenOpaLayout.$r8$clinit;
                    ((LockscreenOpaLayout) this).startRetractAnimation();
                    return;
                case 4:
                    LockscreenOpaLayout lockscreenOpaLayout4 = (LockscreenOpaLayout) this;
                    lockscreenOpaLayout4.mGestureAnimatorSet = LockscreenOpaLayout.m917$$Nest$mgetLineAnimatorSet(lockscreenOpaLayout4);
                    ((LockscreenOpaLayout) this).mGestureAnimatorSet.removeAllListeners();
                    ((LockscreenOpaLayout) this).mGestureAnimatorSet.addListener(new AnonymousClass1(6, this));
                    ((LockscreenOpaLayout) this).mGestureAnimatorSet.end();
                    return;
                case 5:
                    LockscreenOpaLayout.m918$$Nest$mstartCollapseAnimation((LockscreenOpaLayout) this);
                    return;
                default:
                    LockscreenOpaLayout.m918$$Nest$mstartCollapseAnimation((LockscreenOpaLayout) this);
                    return;
            }
        }
    }

    /* renamed from: -$$Nest$mgetLineAnimatorSet  reason: not valid java name */
    public static AnimatorSet m917$$Nest$mgetLineAnimatorSet(LockscreenOpaLayout lockscreenOpaLayout) {
        AnimatorSet animatorSet = lockscreenOpaLayout.mLineAnimatorSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            lockscreenOpaLayout.mLineAnimatorSet.cancel();
            return lockscreenOpaLayout.mLineAnimatorSet;
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        lockscreenOpaLayout.mLineAnimatorSet = animatorSet2;
        View view = lockscreenOpaLayout.mRed;
        Interpolator interpolator = lockscreenOpaLayout.INTERPOLATOR_5_100;
        Resources resources = lockscreenOpaLayout.mResources;
        Interpolator interpolator2 = OpaUtils.INTERPOLATOR_40_40;
        animatorSet2.play(OpaUtils.getTranslationObjectAnimatorX(view, interpolator, -((float) resources.getDimensionPixelOffset(2131167160)), lockscreenOpaLayout.mRed.getX(), 366)).with(OpaUtils.getTranslationObjectAnimatorX(lockscreenOpaLayout.mYellow, lockscreenOpaLayout.INTERPOLATOR_5_100, (float) lockscreenOpaLayout.mResources.getDimensionPixelOffset(2131167160), lockscreenOpaLayout.mYellow.getX(), 366)).with(OpaUtils.getTranslationObjectAnimatorX(lockscreenOpaLayout.mGreen, lockscreenOpaLayout.INTERPOLATOR_5_100, (float) lockscreenOpaLayout.mResources.getDimensionPixelOffset(2131167159), lockscreenOpaLayout.mGreen.getX(), 366)).with(OpaUtils.getTranslationObjectAnimatorX(lockscreenOpaLayout.mBlue, lockscreenOpaLayout.INTERPOLATOR_5_100, -((float) lockscreenOpaLayout.mResources.getDimensionPixelOffset(2131167159)), lockscreenOpaLayout.mBlue.getX(), 366));
        return lockscreenOpaLayout.mLineAnimatorSet;
    }

    /* renamed from: -$$Nest$mstartCollapseAnimation  reason: not valid java name */
    public static void m918$$Nest$mstartCollapseAnimation(final LockscreenOpaLayout lockscreenOpaLayout) {
        if (lockscreenOpaLayout.isAttachedToWindow()) {
            lockscreenOpaLayout.mCurrentAnimators.clear();
            ArraySet arraySet = lockscreenOpaLayout.mCurrentAnimators;
            ArraySet arraySet2 = new ArraySet();
            View view = lockscreenOpaLayout.mRed;
            Interpolator interpolator = OpaUtils.INTERPOLATOR_40_OUT;
            arraySet2.add(OpaUtils.getTranslationAnimatorX(133, view, interpolator));
            arraySet2.add(OpaUtils.getTranslationAnimatorX(150, lockscreenOpaLayout.mBlue, interpolator));
            arraySet2.add(OpaUtils.getTranslationAnimatorX(133, lockscreenOpaLayout.mYellow, interpolator));
            arraySet2.add(OpaUtils.getTranslationAnimatorX(150, lockscreenOpaLayout.mGreen, interpolator));
            OpaUtils.getLongestAnim(arraySet2).addListener(new AnonymousClass1(2));
            arraySet.addAll(arraySet2);
            ArraySet arraySet3 = lockscreenOpaLayout.mCurrentAnimators;
            for (int size = arraySet3.size() - 1; size >= 0; size--) {
                ((Animator) arraySet3.valueAt(size)).start();
            }
            lockscreenOpaLayout.mGestureState = 2;
            return;
        }
        lockscreenOpaLayout.skipToStartingValue();
    }

    public LockscreenOpaLayout(Context context) {
        super(context);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mResources = getResources();
        this.mBlue = findViewById(2131362083);
        this.mRed = findViewById(2131363412);
        this.mYellow = findViewById(2131364153);
        this.mGreen = findViewById(2131362639);
        this.mAnimatedViews.add(this.mBlue);
        this.mAnimatedViews.add(this.mRed);
        this.mAnimatedViews.add(this.mYellow);
        this.mAnimatedViews.add(this.mGreen);
    }

    public final void onProgress(int i, float f) {
        AnimatorSet animatorSet;
        int i2 = this.mGestureState;
        if (i2 != 2) {
            if (i2 == 4) {
                if (!this.mCurrentAnimators.isEmpty()) {
                    for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
                        Animator animator = (Animator) this.mCurrentAnimators.valueAt(size);
                        animator.removeAllListeners();
                        animator.end();
                    }
                    this.mCurrentAnimators.clear();
                }
                this.mGestureState = 0;
            }
            if (f == 0.0f) {
                this.mGestureState = 0;
                return;
            }
            long max = Math.max(0, ((long) (f * 533.0f)) - 167);
            int i3 = this.mGestureState;
            if (i3 != 0) {
                if (i3 == 1) {
                    this.mGestureAnimatorSet.setCurrentPlayTime(max);
                } else if (i3 == 3 && max >= 167) {
                    this.mGestureAnimatorSet.end();
                    if (this.mGestureState == 1) {
                        this.mGestureAnimatorSet.setCurrentPlayTime(max);
                    }
                }
            } else if (isAttachedToWindow()) {
                skipToStartingValue();
                this.mGestureState = 3;
                AnimatorSet animatorSet2 = this.mCannedAnimatorSet;
                if (animatorSet2 != null) {
                    animatorSet2.removeAllListeners();
                    this.mCannedAnimatorSet.cancel();
                    animatorSet = this.mCannedAnimatorSet;
                } else {
                    this.mCannedAnimatorSet = new AnimatorSet();
                    View view = this.mRed;
                    Interpolator interpolator = OpaUtils.INTERPOLATOR_40_40;
                    ObjectAnimator translationObjectAnimatorX = OpaUtils.getTranslationObjectAnimatorX(view, interpolator, -((float) this.mResources.getDimensionPixelOffset(2131167157)), this.mRed.getX(), 83);
                    translationObjectAnimatorX.setStartDelay(17);
                    ObjectAnimator translationObjectAnimatorX2 = OpaUtils.getTranslationObjectAnimatorX(this.mYellow, interpolator, (float) this.mResources.getDimensionPixelOffset(2131167157), this.mYellow.getX(), 83);
                    translationObjectAnimatorX2.setStartDelay(17);
                    AnimatorSet.Builder with = this.mCannedAnimatorSet.play(translationObjectAnimatorX).with(translationObjectAnimatorX2).with(OpaUtils.getTranslationObjectAnimatorX(this.mBlue, interpolator, -((float) this.mResources.getDimensionPixelOffset(2131167156)), this.mBlue.getX(), 167)).with(OpaUtils.getTranslationObjectAnimatorX(this.mGreen, interpolator, (float) this.mResources.getDimensionPixelOffset(2131167156), this.mGreen.getX(), 167));
                    View view2 = this.mRed;
                    Interpolator interpolator2 = Interpolators.LINEAR;
                    with.with(OpaUtils.getAlphaObjectAnimator(130, view2, interpolator2)).with(OpaUtils.getAlphaObjectAnimator(130, this.mYellow, interpolator2)).with(OpaUtils.getAlphaObjectAnimator(113, this.mBlue, interpolator2)).with(OpaUtils.getAlphaObjectAnimator(113, this.mGreen, interpolator2));
                    animatorSet = this.mCannedAnimatorSet;
                }
                this.mGestureAnimatorSet = animatorSet;
                animatorSet.addListener(new AnonymousClass1(0));
                this.mGestureAnimatorSet.start();
            } else {
                skipToStartingValue();
            }
        }
    }

    public final void onRelease() {
        int i = this.mGestureState;
        if (i != 2 && i != 4) {
            if (i == 3) {
                if (this.mGestureAnimatorSet.isRunning()) {
                    this.mGestureAnimatorSet.removeAllListeners();
                    this.mGestureAnimatorSet.addListener(new AnonymousClass1(3));
                    return;
                }
                this.mGestureState = 4;
                startRetractAnimation();
            } else if (i == 1) {
                startRetractAnimation();
            }
        }
    }

    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        int i = this.mGestureState;
        if (i != 4 && i != 2) {
            if (i == 3) {
                this.mGestureState = 2;
                this.mGestureAnimatorSet.removeAllListeners();
                this.mGestureAnimatorSet.addListener(new AnonymousClass1(4));
                return;
            }
            AnimatorSet animatorSet = this.mGestureAnimatorSet;
            if (animatorSet != null) {
                this.mGestureState = 2;
                animatorSet.removeAllListeners();
                this.mGestureAnimatorSet.addListener(new AnonymousClass1(5));
                if (!this.mGestureAnimatorSet.isStarted()) {
                    this.mGestureAnimatorSet.start();
                }
            }
        }
    }

    public final void skipToStartingValue() {
        int size = this.mAnimatedViews.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.mAnimatedViews.get(i);
            view.setAlpha(0.0f);
            view.setTranslationX(0.0f);
        }
    }

    public final void startRetractAnimation() {
        if (isAttachedToWindow()) {
            AnimatorSet animatorSet = this.mGestureAnimatorSet;
            if (animatorSet != null) {
                animatorSet.removeAllListeners();
                this.mGestureAnimatorSet.cancel();
            }
            this.mCurrentAnimators.clear();
            ArraySet arraySet = this.mCurrentAnimators;
            ArraySet arraySet2 = new ArraySet();
            View view = this.mRed;
            Interpolator interpolator = OpaUtils.INTERPOLATOR_40_OUT;
            arraySet2.add(OpaUtils.getTranslationAnimatorX(190, view, interpolator));
            arraySet2.add(OpaUtils.getTranslationAnimatorX(190, this.mBlue, interpolator));
            arraySet2.add(OpaUtils.getTranslationAnimatorX(190, this.mGreen, interpolator));
            arraySet2.add(OpaUtils.getTranslationAnimatorX(190, this.mYellow, interpolator));
            OpaUtils.getLongestAnim(arraySet2).addListener(new AnonymousClass1(1));
            arraySet.addAll(arraySet2);
            ArraySet arraySet3 = this.mCurrentAnimators;
            for (int size = arraySet3.size() - 1; size >= 0; size--) {
                ((Animator) arraySet3.valueAt(size)).start();
            }
            this.mGestureState = 4;
            return;
        }
        skipToStartingValue();
    }

    public LockscreenOpaLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LockscreenOpaLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public LockscreenOpaLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
