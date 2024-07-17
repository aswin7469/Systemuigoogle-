package com.google.android.systemui.assist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Trace;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.app.animation.Interpolators;
import com.android.settingslib.Utils;
import com.android.systemui.Dependency;
import com.android.systemui.navigationbar.buttons.ButtonInterface;
import com.android.systemui.navigationbar.buttons.KeyButtonDrawable;
import com.android.systemui.navigationbar.buttons.KeyButtonView;
import com.android.systemui.recents.OverviewProxyService;
import com.google.android.systemui.elmyra.feedback.FeedbackEffect;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class OpaLayout extends FrameLayout implements ButtonInterface, FeedbackEffect {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Interpolator HOME_DISAPPEAR_INTERPOLATOR;
    public final ArrayList mAnimatedViews;
    public int mAnimationState;
    public View mBlue;
    public View mBottom;
    public final ArraySet mCurrentAnimators;
    public final OpaLayout$$ExternalSyntheticLambda0 mDiamondAnimation;
    public boolean mDiamondAnimationDelayed;
    public final Interpolator mDiamondInterpolator;
    public long mGestureAnimationSetDuration;
    public AnimatorSet mGestureAnimatorSet;
    public AnimatorSet mGestureLineSet;
    public int mGestureState;
    public View mGreen;
    public ImageView mHalo;
    public KeyButtonView mHome;
    public int mHomeDiameter;
    public boolean mIsPressed;
    public boolean mIsVertical;
    public View mLeft;
    public boolean mOpaEnabled;
    public boolean mOpaEnabledNeedsUpdate;
    public final AnonymousClass2 mOverviewProxyListener;
    public OverviewProxyService mOverviewProxyService;
    public View mRed;
    public Resources mResources;
    public final AnonymousClass1 mRetract;
    public View mRight;
    public long mStartTime;
    public View mTop;
    public int mTouchDownX;
    public int mTouchDownY;
    public ImageView mWhite;
    public ImageView mWhiteCutout;
    public boolean mWindowVisible;
    public View mYellow;

    /* renamed from: com.google.android.systemui.assist.OpaLayout$3  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass3 extends AnimatorListenerAdapter {
        public final /* synthetic */ OpaLayout this$0;

        {
            this.this$0 = r1;
        }

        public void onAnimationCancel(Animator animator) {
            switch (1) {
                case 0:
                    Trace.instant(4096, "OpaLayout.cancel.diamond");
                    this.this$0.mCurrentAnimators.clear();
                    return;
                case 1:
                    Trace.instant(4096, "OpaLayout.cancel.retract");
                    return;
                case 2:
                    Trace.instant(4096, "OpaLayout.cancel.collapse");
                    return;
                case 3:
                    Trace.instant(4096, "OpaLayout.cancel.line");
                    this.this$0.mCurrentAnimators.clear();
                    return;
                default:
                    super.onAnimationCancel(animator);
                    return;
            }
        }

        public final void onAnimationEnd(Animator animator) {
            switch (1) {
                case 0:
                    Trace.instant(4096, "OpaLayout.end.diamond");
                    OpaLayout opaLayout = this.this$0;
                    int i = OpaLayout.$r8$clinit;
                    if (opaLayout.allowAnimations()) {
                        opaLayout.mCurrentAnimators.clear();
                        ArraySet arraySet = opaLayout.mCurrentAnimators;
                        ArraySet arraySet2 = new ArraySet();
                        if (opaLayout.mIsVertical) {
                            View view = opaLayout.mRed;
                            Property property = View.Y;
                            float y = view.getY();
                            Resources resources = opaLayout.mResources;
                            Interpolator interpolator = OpaUtils.INTERPOLATOR_40_40;
                            float dimensionPixelOffset = y + ((float) resources.getDimensionPixelOffset(2131167154));
                            Interpolator interpolator2 = Interpolators.FAST_OUT_SLOW_IN;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view, property, dimensionPixelOffset, 225, interpolator2));
                            View view2 = opaLayout.mRed;
                            Property property2 = View.X;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view2, property2, view2.getX() + ((float) opaLayout.mResources.getDimensionPixelOffset(2131167155)), 133, interpolator2));
                            View view3 = opaLayout.mBlue;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view3, property, view3.getY() + ((float) opaLayout.mResources.getDimensionPixelOffset(2131167153)), 225, interpolator2));
                            View view4 = opaLayout.mYellow;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view4, property, view4.getY() + (-((float) opaLayout.mResources.getDimensionPixelOffset(2131167154))), 225, interpolator2));
                            View view5 = opaLayout.mYellow;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view5, property2, view5.getX() + (-((float) opaLayout.mResources.getDimensionPixelOffset(2131167155))), 133, interpolator2));
                            View view6 = opaLayout.mGreen;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view6, property, view6.getY() + (-((float) opaLayout.mResources.getDimensionPixelOffset(2131167153))), 225, interpolator2));
                        } else {
                            View view7 = opaLayout.mRed;
                            Property property3 = View.X;
                            float x = view7.getX();
                            Resources resources2 = opaLayout.mResources;
                            Interpolator interpolator3 = OpaUtils.INTERPOLATOR_40_40;
                            float f = x + (-((float) resources2.getDimensionPixelOffset(2131167154)));
                            Interpolator interpolator4 = Interpolators.FAST_OUT_SLOW_IN;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view7, property3, f, 225, interpolator4));
                            View view8 = opaLayout.mRed;
                            Property property4 = View.Y;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view8, property4, view8.getY() + ((float) opaLayout.mResources.getDimensionPixelOffset(2131167155)), 133, interpolator4));
                            View view9 = opaLayout.mBlue;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view9, property3, view9.getX() + (-((float) opaLayout.mResources.getDimensionPixelOffset(2131167153))), 225, interpolator4));
                            View view10 = opaLayout.mYellow;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view10, property3, view10.getX() + ((float) opaLayout.mResources.getDimensionPixelOffset(2131167154)), 225, interpolator4));
                            View view11 = opaLayout.mYellow;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view11, property4, view11.getY() + (-((float) opaLayout.mResources.getDimensionPixelOffset(2131167155))), 133, interpolator4));
                            View view12 = opaLayout.mGreen;
                            arraySet2.add(OpaLayout.getPropertyAnimator(view12, property3, view12.getX() + ((float) opaLayout.mResources.getDimensionPixelOffset(2131167153)), 225, interpolator4));
                        }
                        ImageView imageView = opaLayout.mWhite;
                        Property property5 = FrameLayout.SCALE_X;
                        arraySet2.add(OpaLayout.getPropertyAnimator(imageView, property5, 0.0f, 83, opaLayout.HOME_DISAPPEAR_INTERPOLATOR));
                        ImageView imageView2 = opaLayout.mWhite;
                        Property property6 = FrameLayout.SCALE_Y;
                        arraySet2.add(OpaLayout.getPropertyAnimator(imageView2, property6, 0.0f, 83, opaLayout.HOME_DISAPPEAR_INTERPOLATOR));
                        arraySet2.add(OpaLayout.getPropertyAnimator(opaLayout.mWhiteCutout, property5, 0.0f, 83, opaLayout.HOME_DISAPPEAR_INTERPOLATOR));
                        arraySet2.add(OpaLayout.getPropertyAnimator(opaLayout.mWhiteCutout, property6, 0.0f, 83, opaLayout.HOME_DISAPPEAR_INTERPOLATOR));
                        arraySet2.add(OpaLayout.getPropertyAnimator(opaLayout.mHalo, property5, 0.0f, 83, opaLayout.HOME_DISAPPEAR_INTERPOLATOR));
                        arraySet2.add(OpaLayout.getPropertyAnimator(opaLayout.mHalo, property6, 0.0f, 83, opaLayout.HOME_DISAPPEAR_INTERPOLATOR));
                        OpaLayout.getLongestAnim(arraySet2).addListener(new AnonymousClass3(opaLayout, 3));
                        arraySet.addAll(arraySet2);
                        opaLayout.mAnimationState = 3;
                        opaLayout.startAll$1(opaLayout.mCurrentAnimators);
                        return;
                    }
                    opaLayout.skipToStartingValue$1();
                    return;
                case 1:
                    Trace.instant(4096, "OpaLayout.end.retract");
                    this.this$0.mCurrentAnimators.clear();
                    this.this$0.skipToStartingValue$1();
                    return;
                case 2:
                    Trace.instant(4096, "OpaLayout.end.collapse");
                    this.this$0.mCurrentAnimators.clear();
                    this.this$0.skipToStartingValue$1();
                    return;
                case 3:
                    Trace.instant(4096, "OpaLayout.end.line");
                    OpaLayout.m919$$Nest$mstartCollapseAnimation(this.this$0);
                    return;
                case 4:
                    OpaLayout.m919$$Nest$mstartCollapseAnimation(this.this$0);
                    return;
                default:
                    OpaLayout.m919$$Nest$mstartCollapseAnimation(this.this$0);
                    return;
            }
        }

        public void onAnimationStart(Animator animator) {
            switch (1) {
                case 0:
                    Trace.instant(4096, "OpaLayout.start.diamond");
                    return;
                case 1:
                    Trace.instant(4096, "OpaLayout.start.retract");
                    return;
                case 2:
                    Trace.instant(4096, "OpaLayout.start.collapse");
                    return;
                case 3:
                    Trace.instant(4096, "OpaLayout.start.line");
                    return;
                default:
                    super.onAnimationStart(animator);
                    return;
            }
        }
    }

    /* renamed from: -$$Nest$mstartCollapseAnimation  reason: not valid java name */
    public static void m919$$Nest$mstartCollapseAnimation(OpaLayout opaLayout) {
        Animator animator;
        Animator animator2;
        Animator animator3;
        Animator animator4;
        if (opaLayout.allowAnimations()) {
            opaLayout.mCurrentAnimators.clear();
            ArraySet arraySet = opaLayout.mCurrentAnimators;
            ArraySet arraySet2 = new ArraySet();
            if (opaLayout.mIsVertical) {
                animator = getPropertyAnimator(opaLayout.mRed, FrameLayout.TRANSLATION_Y, 0.0f, 133, OpaUtils.INTERPOLATOR_40_OUT);
            } else {
                animator = getPropertyAnimator(opaLayout.mRed, FrameLayout.TRANSLATION_X, 0.0f, 133, OpaUtils.INTERPOLATOR_40_OUT);
            }
            arraySet2.add(animator);
            View view = opaLayout.mRed;
            Property property = FrameLayout.SCALE_X;
            Interpolator interpolator = OpaUtils.INTERPOLATOR_40_OUT;
            arraySet2.add(getPropertyAnimator(view, property, 1.0f, 200, interpolator));
            View view2 = opaLayout.mRed;
            Property property2 = FrameLayout.SCALE_Y;
            arraySet2.add(getPropertyAnimator(view2, property2, 1.0f, 200, interpolator));
            if (opaLayout.mIsVertical) {
                animator2 = getPropertyAnimator(opaLayout.mBlue, FrameLayout.TRANSLATION_Y, 0.0f, 150, interpolator);
            } else {
                animator2 = getPropertyAnimator(opaLayout.mBlue, FrameLayout.TRANSLATION_X, 0.0f, 150, interpolator);
            }
            arraySet2.add(animator2);
            arraySet2.add(getPropertyAnimator(opaLayout.mBlue, property, 1.0f, 200, interpolator));
            arraySet2.add(getPropertyAnimator(opaLayout.mBlue, property2, 1.0f, 200, interpolator));
            if (opaLayout.mIsVertical) {
                animator3 = getPropertyAnimator(opaLayout.mYellow, FrameLayout.TRANSLATION_Y, 0.0f, 133, interpolator);
            } else {
                animator3 = getPropertyAnimator(opaLayout.mYellow, FrameLayout.TRANSLATION_X, 0.0f, 133, interpolator);
            }
            arraySet2.add(animator3);
            arraySet2.add(getPropertyAnimator(opaLayout.mYellow, property, 1.0f, 200, interpolator));
            arraySet2.add(getPropertyAnimator(opaLayout.mYellow, property2, 1.0f, 200, interpolator));
            if (opaLayout.mIsVertical) {
                animator4 = getPropertyAnimator(opaLayout.mGreen, FrameLayout.TRANSLATION_Y, 0.0f, 150, interpolator);
            } else {
                animator4 = getPropertyAnimator(opaLayout.mGreen, FrameLayout.TRANSLATION_X, 0.0f, 150, interpolator);
            }
            arraySet2.add(animator4);
            arraySet2.add(getPropertyAnimator(opaLayout.mGreen, property, 1.0f, 200, interpolator));
            arraySet2.add(getPropertyAnimator(opaLayout.mGreen, property2, 1.0f, 200, interpolator));
            ImageView imageView = opaLayout.mWhite;
            Interpolator interpolator2 = Interpolators.FAST_OUT_SLOW_IN;
            Animator propertyAnimator = getPropertyAnimator(imageView, property, 1.0f, 150, interpolator2);
            Animator propertyAnimator2 = getPropertyAnimator(opaLayout.mWhite, property2, 1.0f, 150, interpolator2);
            Animator propertyAnimator3 = getPropertyAnimator(opaLayout.mWhiteCutout, property, 1.0f, 150, interpolator2);
            Animator propertyAnimator4 = getPropertyAnimator(opaLayout.mWhiteCutout, property2, 1.0f, 150, interpolator2);
            Animator propertyAnimator5 = getPropertyAnimator(opaLayout.mHalo, property, 1.0f, 150, interpolator2);
            Animator propertyAnimator6 = getPropertyAnimator(opaLayout.mHalo, property2, 1.0f, 150, interpolator2);
            Animator propertyAnimator7 = getPropertyAnimator(opaLayout.mHalo, FrameLayout.ALPHA, 1.0f, 150, interpolator2);
            propertyAnimator.setStartDelay(33);
            propertyAnimator2.setStartDelay(33);
            propertyAnimator3.setStartDelay(33);
            propertyAnimator4.setStartDelay(33);
            propertyAnimator5.setStartDelay(33);
            propertyAnimator6.setStartDelay(33);
            propertyAnimator7.setStartDelay(33);
            arraySet2.add(propertyAnimator);
            arraySet2.add(propertyAnimator2);
            arraySet2.add(propertyAnimator3);
            arraySet2.add(propertyAnimator4);
            arraySet2.add(propertyAnimator5);
            arraySet2.add(propertyAnimator6);
            arraySet2.add(propertyAnimator7);
            getLongestAnim(arraySet2).addListener(new AnonymousClass3(opaLayout, 2));
            arraySet.addAll(arraySet2);
            opaLayout.mAnimationState = 3;
            opaLayout.startAll$1(opaLayout.mCurrentAnimators);
            return;
        }
        opaLayout.skipToStartingValue$1();
    }

    public OpaLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public static Animator getLongestAnim(ArraySet arraySet) {
        long j = Long.MIN_VALUE;
        Animator animator = null;
        for (int size = arraySet.size() - 1; size >= 0; size--) {
            Animator animator2 = (Animator) arraySet.valueAt(size);
            if (animator2.getTotalDuration() > j) {
                j = animator2.getTotalDuration();
                animator = animator2;
            }
        }
        return animator;
    }

    public static Animator getPropertyAnimator(View view, Property property, float f, int i, Interpolator interpolator) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, property, new float[]{f});
        ofFloat.setDuration((long) i);
        ofFloat.setInterpolator(interpolator);
        return ofFloat;
    }

    public final void abortCurrentGesture() {
        if (Trace.isEnabled()) {
            Trace.instant(4096, "OpaLayout.abortCurrentGesture: animState=" + this.mAnimationState);
        }
        this.mHome.abortCurrentGesture();
        this.mIsPressed = false;
        this.mDiamondAnimationDelayed = false;
        removeCallbacks(this.mDiamondAnimation);
        cancelLongPress();
        int i = this.mAnimationState;
        if (i == 3 || i == 1) {
            this.mRetract.run();
        }
    }

    public final boolean allowAnimations() {
        if (!isAttachedToWindow() || !this.mWindowVisible) {
            return false;
        }
        return true;
    }

    public final void cancelCurrentAnimation(String str) {
        if (Trace.isEnabled()) {
            Trace.instant(4096, "OpaLayout.cancelCurrentAnimation: reason=" + str);
        }
        if (!this.mCurrentAnimators.isEmpty()) {
            for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
                Animator animator = (Animator) this.mCurrentAnimators.valueAt(size);
                animator.removeAllListeners();
                animator.cancel();
            }
            this.mCurrentAnimators.clear();
            this.mAnimationState = 0;
        }
        AnimatorSet animatorSet = this.mGestureAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.mGestureState = 0;
        }
    }

    public final void endCurrentAnimation(String str) {
        if (Trace.isEnabled()) {
            Trace.traceBegin(4096, "OpaLayout.endCurrentAnimation: reason=" + str);
        }
        if (!this.mCurrentAnimators.isEmpty()) {
            for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
                Animator animator = (Animator) this.mCurrentAnimators.valueAt(size);
                animator.removeAllListeners();
                animator.end();
            }
            this.mCurrentAnimators.clear();
        }
        this.mAnimationState = 0;
    }

    public final boolean getOpaEnabled() {
        if (this.mOpaEnabledNeedsUpdate) {
            OpaEnabledReceiver opaEnabledReceiver = ((AssistManagerGoogle) Dependency.sDependency.getDependencyInner(AssistManagerGoogle.class)).mOpaEnabledReceiver;
            opaEnabledReceiver.dispatchOpaEnabledState(opaEnabledReceiver.mContext);
            if (this.mOpaEnabledNeedsUpdate) {
                Log.w("OpaLayout", "mOpaEnabledNeedsUpdate not cleared by AssistManagerGoogle!");
            }
        }
        return this.mOpaEnabled;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mOverviewProxyService.addCallback((OverviewProxyService.OverviewProxyListener) this.mOverviewProxyListener);
        this.mOpaEnabledNeedsUpdate = true;
        post(new OpaLayout$$ExternalSyntheticLambda0(this, 1));
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateOpaLayout();
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mOverviewProxyService.removeCallback((OverviewProxyService.OverviewProxyListener) this.mOverviewProxyListener);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mResources = getResources();
        this.mBlue = findViewById(2131362083);
        this.mRed = findViewById(2131363412);
        this.mYellow = findViewById(2131364153);
        this.mGreen = findViewById(2131362639);
        this.mWhite = (ImageView) findViewById(2131364104);
        this.mWhiteCutout = (ImageView) findViewById(2131364105);
        this.mHalo = (ImageView) findViewById(2131362679);
        this.mHome = (KeyButtonView) findViewById(2131362702);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), 2132017515);
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(getContext(), 2132017514);
        ImageView imageView = this.mHalo;
        KeyButtonDrawable.AnonymousClass1 r3 = KeyButtonDrawable.KEY_DRAWABLE_ROTATE;
        imageView.setImageDrawable(KeyButtonDrawable.create(contextThemeWrapper, Utils.getColorAttrDefaultColor(contextThemeWrapper, 2130969921, 0), Utils.getColorAttrDefaultColor(contextThemeWrapper2, 2130969921, 0), 2131232423, true));
        this.mHomeDiameter = this.mResources.getDimensionPixelSize(2131167149);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.mWhiteCutout.setLayerType(2, paint);
        this.mAnimatedViews.add(this.mBlue);
        this.mAnimatedViews.add(this.mRed);
        this.mAnimatedViews.add(this.mYellow);
        this.mAnimatedViews.add(this.mGreen);
        this.mAnimatedViews.add(this.mWhite);
        this.mAnimatedViews.add(this.mWhiteCutout);
        this.mAnimatedViews.add(this.mHalo);
        this.mOverviewProxyService = (OverviewProxyService) Dependency.sDependency.getDependencyInner(OverviewProxyService.class);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        if (r0 != 3) goto L_0x00df;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            com.android.systemui.Dependency r0 = com.android.systemui.Dependency.sDependency
            java.lang.Class<com.google.android.systemui.assist.AssistManagerGoogle> r1 = com.google.android.systemui.assist.AssistManagerGoogle.class
            java.lang.Object r0 = r0.getDependencyInner(r1)
            com.google.android.systemui.assist.AssistManagerGoogle r0 = (com.google.android.systemui.assist.AssistManagerGoogle) r0
            boolean r1 = r5.getOpaEnabled()
            r2 = 0
            if (r1 == 0) goto L_0x00df
            boolean r1 = android.animation.ValueAnimator.areAnimatorsEnabled()
            if (r1 == 0) goto L_0x00df
            r1 = 5
            boolean r0 = r0.shouldOverrideAssist(r1)
            if (r0 != 0) goto L_0x00df
            int r0 = r5.mGestureState
            if (r0 == 0) goto L_0x0024
            goto L_0x00df
        L_0x0024:
            int r0 = r6.getAction()
            r1 = 2
            r3 = 1
            if (r0 == 0) goto L_0x00a7
            if (r0 == r3) goto L_0x006a
            if (r0 == r1) goto L_0x0035
            r6 = 3
            if (r0 == r6) goto L_0x006a
            goto L_0x00df
        L_0x0035:
            android.content.Context r0 = r5.getContext()
            android.view.ViewConfiguration r0 = android.view.ViewConfiguration.get(r0)
            int r0 = r0.getScaledTouchSlop()
            float r0 = (float) r0
            r1 = 1077936128(0x40400000, float:3.0)
            float r0 = r0 * r1
            float r1 = r6.getRawX()
            int r3 = r5.mTouchDownX
            float r3 = (float) r3
            float r1 = r1 - r3
            float r1 = java.lang.Math.abs(r1)
            int r1 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r1 > 0) goto L_0x0065
            float r6 = r6.getRawY()
            int r1 = r5.mTouchDownY
            float r1 = (float) r1
            float r6 = r6 - r1
            float r6 = java.lang.Math.abs(r6)
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 <= 0) goto L_0x00df
        L_0x0065:
            r5.abortCurrentGesture()
            goto L_0x00df
        L_0x006a:
            boolean r6 = r5.mDiamondAnimationDelayed
            if (r6 == 0) goto L_0x007a
            boolean r6 = r5.mIsPressed
            if (r6 == 0) goto L_0x00a4
            com.google.android.systemui.assist.OpaLayout$1 r6 = r5.mRetract
            r0 = 200(0xc8, double:9.9E-322)
            r5.postDelayed(r6, r0)
            goto L_0x00a4
        L_0x007a:
            int r6 = r5.mAnimationState
            if (r6 != r3) goto L_0x009b
            long r0 = android.os.SystemClock.elapsedRealtime()
            long r3 = r5.mStartTime
            long r0 = r0 - r3
            r3 = 100
            long r3 = r3 - r0
            com.google.android.systemui.assist.OpaLayout$1 r6 = r5.mRetract
            r5.removeCallbacks(r6)
            com.google.android.systemui.assist.OpaLayout$1 r6 = r5.mRetract
            r5.postDelayed(r6, r3)
            com.google.android.systemui.assist.OpaLayout$$ExternalSyntheticLambda0 r6 = r5.mDiamondAnimation
            r5.removeCallbacks(r6)
            r5.cancelLongPress()
            return r2
        L_0x009b:
            boolean r6 = r5.mIsPressed
            if (r6 == 0) goto L_0x00a4
            com.google.android.systemui.assist.OpaLayout$1 r6 = r5.mRetract
            r6.run()
        L_0x00a4:
            r5.mIsPressed = r2
            goto L_0x00df
        L_0x00a7:
            float r0 = r6.getRawX()
            int r0 = (int) r0
            r5.mTouchDownX = r0
            float r6 = r6.getRawY()
            int r6 = (int) r6
            r5.mTouchDownY = r6
            android.util.ArraySet r6 = r5.mCurrentAnimators
            boolean r6 = r6.isEmpty()
            if (r6 != 0) goto L_0x00c8
            int r6 = r5.mAnimationState
            if (r6 != r1) goto L_0x00c7
            java.lang.String r6 = "touchDown"
            r5.endCurrentAnimation(r6)
            goto L_0x00c8
        L_0x00c7:
            return r2
        L_0x00c8:
            long r0 = android.os.SystemClock.elapsedRealtime()
            r5.mStartTime = r0
            r5.mIsPressed = r3
            com.google.android.systemui.assist.OpaLayout$$ExternalSyntheticLambda0 r6 = r5.mDiamondAnimation
            r5.removeCallbacks(r6)
            com.google.android.systemui.assist.OpaLayout$1 r6 = r5.mRetract
            r5.removeCallbacks(r6)
            r5.mDiamondAnimationDelayed = r2
            r5.startDiamondAnimation()
        L_0x00df:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.assist.OpaLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void onProgress(int i, float f) {
        AnimatorSet animatorSet;
        if (this.mGestureState != 2 && allowAnimations()) {
            if (this.mAnimationState == 2) {
                endCurrentAnimation("progress=" + f);
            }
            if (this.mAnimationState == 0) {
                if (this.mGestureAnimatorSet == null) {
                    AnimatorSet animatorSet2 = this.mGestureLineSet;
                    if (animatorSet2 != null) {
                        animatorSet2.removeAllListeners();
                        this.mGestureLineSet.cancel();
                        animatorSet = this.mGestureLineSet;
                    } else {
                        this.mGestureLineSet = new AnimatorSet();
                        ImageView imageView = this.mWhite;
                        Interpolator interpolator = OpaUtils.INTERPOLATOR_40_OUT;
                        ObjectAnimator scaleObjectAnimator = OpaUtils.getScaleObjectAnimator(imageView, 0.0f, 100, interpolator);
                        ObjectAnimator scaleObjectAnimator2 = OpaUtils.getScaleObjectAnimator(this.mWhiteCutout, 0.0f, 100, interpolator);
                        ObjectAnimator scaleObjectAnimator3 = OpaUtils.getScaleObjectAnimator(this.mHalo, 0.0f, 100, interpolator);
                        scaleObjectAnimator.setStartDelay(50);
                        scaleObjectAnimator2.setStartDelay(50);
                        this.mGestureLineSet.play(scaleObjectAnimator).with(scaleObjectAnimator2).with(scaleObjectAnimator3);
                        View view = this.mTop;
                        Interpolator interpolator2 = Interpolators.FAST_OUT_SLOW_IN;
                        AnimatorSet.Builder with = this.mGestureLineSet.play(OpaUtils.getScaleObjectAnimator(view, 0.8f, 200, interpolator2)).with(scaleObjectAnimator);
                        View view2 = this.mRed;
                        Interpolator interpolator3 = Interpolators.LINEAR;
                        with.with(OpaUtils.getAlphaObjectAnimator(130, view2, interpolator3)).with(OpaUtils.getAlphaObjectAnimator(130, this.mYellow, interpolator3)).with(OpaUtils.getAlphaObjectAnimator(113, this.mBlue, interpolator3)).with(OpaUtils.getAlphaObjectAnimator(113, this.mGreen, interpolator3)).with(OpaUtils.getScaleObjectAnimator(this.mBottom, 0.8f, 200, interpolator2)).with(OpaUtils.getScaleObjectAnimator(this.mLeft, 0.8f, 200, interpolator2)).with(OpaUtils.getScaleObjectAnimator(this.mRight, 0.8f, 200, interpolator2));
                        if (this.mIsVertical) {
                            View view3 = this.mRed;
                            Interpolator interpolator4 = OpaUtils.INTERPOLATOR_40_40;
                            ObjectAnimator translationObjectAnimatorY = OpaUtils.getTranslationObjectAnimatorY(view3, interpolator4, (float) this.mResources.getDimensionPixelOffset(2131167154), this.mRed.getY() + 0.0f);
                            translationObjectAnimatorY.addListener(new AnonymousClass3(this, 4));
                            this.mGestureLineSet.play(translationObjectAnimatorY).with(scaleObjectAnimator3).with(OpaUtils.getTranslationObjectAnimatorY(this.mBlue, interpolator4, (float) this.mResources.getDimensionPixelOffset(2131167153), this.mBlue.getY() + ((float) this.mResources.getDimensionPixelOffset(2131167148)))).with(OpaUtils.getTranslationObjectAnimatorY(this.mYellow, interpolator4, -((float) this.mResources.getDimensionPixelOffset(2131167154)), this.mYellow.getY() + 0.0f)).with(OpaUtils.getTranslationObjectAnimatorY(this.mGreen, interpolator4, -((float) this.mResources.getDimensionPixelOffset(2131167153)), this.mGreen.getY() + (-((float) this.mResources.getDimensionPixelOffset(2131167148)))));
                        } else {
                            View view4 = this.mRed;
                            Interpolator interpolator5 = OpaUtils.INTERPOLATOR_40_40;
                            ObjectAnimator translationObjectAnimatorX = OpaUtils.getTranslationObjectAnimatorX(view4, interpolator5, -((float) this.mResources.getDimensionPixelOffset(2131167154)), this.mRed.getX() + 0.0f, 350);
                            translationObjectAnimatorX.addListener(new AnonymousClass3(this, 5));
                            this.mGestureLineSet.play(translationObjectAnimatorX).with(scaleObjectAnimator).with(OpaUtils.getTranslationObjectAnimatorX(this.mBlue, interpolator5, -((float) this.mResources.getDimensionPixelOffset(2131167153)), this.mBlue.getX() + (-((float) this.mResources.getDimensionPixelOffset(2131167148))), 350)).with(OpaUtils.getTranslationObjectAnimatorX(this.mYellow, interpolator5, (float) this.mResources.getDimensionPixelOffset(2131167154), this.mYellow.getX() + 0.0f, 350)).with(OpaUtils.getTranslationObjectAnimatorX(this.mGreen, interpolator5, (float) this.mResources.getDimensionPixelOffset(2131167153), this.mGreen.getX() + ((float) this.mResources.getDimensionPixelOffset(2131167148)), 350));
                        }
                        animatorSet = this.mGestureLineSet;
                    }
                    this.mGestureAnimatorSet = animatorSet;
                    this.mGestureAnimationSetDuration = animatorSet.getTotalDuration();
                }
                this.mGestureAnimatorSet.setCurrentPlayTime((long) (((float) (this.mGestureAnimationSetDuration - 1)) * f));
                if (f == 0.0f) {
                    this.mGestureState = 0;
                } else {
                    this.mGestureState = 1;
                }
            }
        }
    }

    public final void onRelease() {
        if (this.mAnimationState == 0 && this.mGestureState == 1) {
            AnimatorSet animatorSet = this.mGestureAnimatorSet;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            this.mGestureState = 0;
            startRetractAnimation$1();
        }
    }

    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        AnimatorSet animatorSet;
        if (this.mAnimationState == 0) {
            if (this.mGestureState != 1 || (animatorSet = this.mGestureAnimatorSet) == null || animatorSet.isStarted()) {
                skipToStartingValue$1();
                return;
            }
            this.mGestureAnimatorSet.start();
            this.mGestureState = 2;
        }
    }

    public final void onWindowVisibilityChanged(int i) {
        boolean z;
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        this.mWindowVisible = z;
        if (i == 0) {
            updateOpaLayout();
            return;
        }
        cancelCurrentAnimation("winVis=" + i);
        skipToStartingValue$1();
    }

    public final void setAccessibilityDelegate(View.AccessibilityDelegate accessibilityDelegate) {
        super.setAccessibilityDelegate(accessibilityDelegate);
        this.mHome.setAccessibilityDelegate(accessibilityDelegate);
    }

    public final void setDarkIntensity(float f) {
        if (this.mWhite.getDrawable() instanceof KeyButtonDrawable) {
            ((KeyButtonDrawable) this.mWhite.getDrawable()).setDarkIntensity(f);
        }
        ((KeyButtonDrawable) this.mHalo.getDrawable()).setDarkIntensity(f);
        this.mWhite.invalidate();
        this.mHalo.invalidate();
        this.mHome.setDarkIntensity(f);
    }

    public final void setImageDrawable(Drawable drawable) {
        this.mWhite.setImageDrawable(drawable);
        this.mWhiteCutout.setImageDrawable(drawable);
    }

    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        if (onLongClickListener == null) {
            this.mHome.setLongClickable(false);
            return;
        }
        this.mHome.setLongClickable(true);
        this.mHome.setOnLongClickListener(new OpaLayout$$ExternalSyntheticLambda1(this, onLongClickListener));
    }

    public final void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.mHome.setOnTouchListener(onTouchListener);
    }

    public final void setVertical(boolean z) {
        AnimatorSet animatorSet;
        if (!(this.mIsVertical == z || (animatorSet = this.mGestureAnimatorSet) == null)) {
            animatorSet.cancel();
            this.mGestureAnimatorSet = null;
            skipToStartingValue$1();
        }
        this.mIsVertical = z;
        this.mHome.getClass();
        if (this.mIsVertical) {
            this.mTop = this.mGreen;
            this.mBottom = this.mBlue;
            this.mRight = this.mYellow;
            this.mLeft = this.mRed;
            return;
        }
        this.mTop = this.mRed;
        this.mBottom = this.mYellow;
        this.mLeft = this.mBlue;
        this.mRight = this.mGreen;
    }

    public final void skipToStartingValue$1() {
        int size = this.mAnimatedViews.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.mAnimatedViews.get(i);
            view.setScaleY(1.0f);
            view.setScaleX(1.0f);
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            view.setAlpha(0.0f);
        }
        this.mHalo.setAlpha(1.0f);
        this.mWhite.setAlpha(1.0f);
        this.mWhiteCutout.setAlpha(1.0f);
        this.mAnimationState = 0;
        this.mGestureState = 0;
    }

    public final void startAll$1(ArraySet arraySet) {
        for (int size = arraySet.size() - 1; size >= 0; size--) {
            ((Animator) arraySet.valueAt(size)).start();
        }
        for (int size2 = this.mAnimatedViews.size() - 1; size2 >= 0; size2--) {
            ((View) this.mAnimatedViews.get(size2)).invalidate();
        }
    }

    public final void startDiamondAnimation() {
        if (allowAnimations()) {
            this.mCurrentAnimators.clear();
            int size = this.mAnimatedViews.size();
            for (int i = 0; i < size; i++) {
                ((View) this.mAnimatedViews.get(i)).setAlpha(1.0f);
            }
            ArraySet arraySet = this.mCurrentAnimators;
            ArraySet arraySet2 = new ArraySet();
            View view = this.mTop;
            Property property = View.Y;
            float y = view.getY();
            Resources resources = this.mResources;
            Interpolator interpolator = OpaUtils.INTERPOLATOR_40_40;
            arraySet2.add(getPropertyAnimator(view, property, y + (-((float) resources.getDimensionPixelOffset(2131167148))), 200, this.mDiamondInterpolator));
            View view2 = this.mTop;
            Property property2 = FrameLayout.SCALE_X;
            Interpolator interpolator2 = Interpolators.FAST_OUT_SLOW_IN;
            arraySet2.add(getPropertyAnimator(view2, property2, 0.8f, 200, interpolator2));
            View view3 = this.mTop;
            Property property3 = FrameLayout.SCALE_Y;
            arraySet2.add(getPropertyAnimator(view3, property3, 0.8f, 200, interpolator2));
            View view4 = this.mBottom;
            arraySet2.add(getPropertyAnimator(view4, property, view4.getY() + ((float) this.mResources.getDimensionPixelOffset(2131167148)), 200, this.mDiamondInterpolator));
            arraySet2.add(getPropertyAnimator(this.mBottom, property2, 0.8f, 200, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mBottom, property3, 0.8f, 200, interpolator2));
            View view5 = this.mLeft;
            Property property4 = View.X;
            arraySet2.add(getPropertyAnimator(view5, property4, view5.getX() + (-((float) this.mResources.getDimensionPixelOffset(2131167148))), 200, this.mDiamondInterpolator));
            arraySet2.add(getPropertyAnimator(this.mLeft, property2, 0.8f, 200, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mLeft, property3, 0.8f, 200, interpolator2));
            View view6 = this.mRight;
            arraySet2.add(getPropertyAnimator(view6, property4, view6.getX() + ((float) this.mResources.getDimensionPixelOffset(2131167148)), 200, this.mDiamondInterpolator));
            arraySet2.add(getPropertyAnimator(this.mRight, property2, 0.8f, 200, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mRight, property3, 0.8f, 200, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mWhite, property2, 0.625f, 200, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mWhite, property3, 0.625f, 200, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mWhiteCutout, property2, 0.625f, 200, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mWhiteCutout, property3, 0.625f, 200, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mHalo, property2, 0.47619048f, 100, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mHalo, property3, 0.47619048f, 100, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mHalo, View.ALPHA, 0.0f, 100, interpolator2));
            getLongestAnim(arraySet2).addListener(new AnonymousClass3(this, 0));
            arraySet.addAll(arraySet2);
            this.mAnimationState = 1;
            startAll$1(this.mCurrentAnimators);
            return;
        }
        skipToStartingValue$1();
    }

    public final void startRetractAnimation$1() {
        if (allowAnimations()) {
            this.mCurrentAnimators.clear();
            ArraySet arraySet = this.mCurrentAnimators;
            ArraySet arraySet2 = new ArraySet();
            View view = this.mRed;
            Property property = FrameLayout.TRANSLATION_X;
            Interpolator interpolator = OpaUtils.INTERPOLATOR_40_OUT;
            arraySet2.add(getPropertyAnimator(view, property, 0.0f, 190, interpolator));
            View view2 = this.mRed;
            Property property2 = FrameLayout.TRANSLATION_Y;
            arraySet2.add(getPropertyAnimator(view2, property2, 0.0f, 190, interpolator));
            View view3 = this.mRed;
            Property property3 = FrameLayout.SCALE_X;
            arraySet2.add(getPropertyAnimator(view3, property3, 1.0f, 190, interpolator));
            View view4 = this.mRed;
            Property property4 = FrameLayout.SCALE_Y;
            arraySet2.add(getPropertyAnimator(view4, property4, 1.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mBlue, property, 0.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mBlue, property2, 0.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mBlue, property3, 1.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mBlue, property4, 1.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mGreen, property, 0.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mGreen, property2, 0.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mGreen, property3, 1.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mGreen, property4, 1.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mYellow, property, 0.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mYellow, property2, 0.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mYellow, property3, 1.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mYellow, property4, 1.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mWhite, property3, 1.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mWhite, property4, 1.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mWhiteCutout, property3, 1.0f, 190, interpolator));
            arraySet2.add(getPropertyAnimator(this.mWhiteCutout, property4, 1.0f, 190, interpolator));
            ImageView imageView = this.mHalo;
            Interpolator interpolator2 = Interpolators.FAST_OUT_SLOW_IN;
            arraySet2.add(getPropertyAnimator(imageView, property3, 1.0f, 190, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mHalo, property4, 1.0f, 190, interpolator2));
            arraySet2.add(getPropertyAnimator(this.mHalo, FrameLayout.ALPHA, 1.0f, 190, interpolator2));
            getLongestAnim(arraySet2).addListener(new AnonymousClass3(this, 1));
            arraySet.addAll(arraySet2);
            this.mAnimationState = 2;
            startAll$1(this.mCurrentAnimators);
            return;
        }
        skipToStartingValue$1();
    }

    public final void updateOpaLayout() {
        boolean z;
        int i;
        int i2;
        ImageView.ScaleType scaleType;
        boolean shouldShowSwipeUpUI = this.mOverviewProxyService.shouldShowSwipeUpUI();
        boolean z2 = true;
        if (!this.mOpaEnabled || shouldShowSwipeUpUI) {
            z = false;
        } else {
            z = true;
        }
        ImageView imageView = this.mHalo;
        if (z) {
            i = 0;
        } else {
            i = 4;
        }
        imageView.setVisibility(i);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mHalo.getLayoutParams();
        if (z || shouldShowSwipeUpUI) {
            z2 = false;
        }
        if (z2) {
            i2 = this.mHomeDiameter;
        } else {
            i2 = -1;
        }
        layoutParams.width = i2;
        layoutParams.height = i2;
        this.mWhite.setLayoutParams(layoutParams);
        this.mWhiteCutout.setLayoutParams(layoutParams);
        if (z2) {
            scaleType = ImageView.ScaleType.FIT_CENTER;
        } else {
            scaleType = ImageView.ScaleType.CENTER;
        }
        this.mWhite.setScaleType(scaleType);
        this.mWhiteCutout.setScaleType(scaleType);
    }

    public OpaLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OpaLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.HOME_DISAPPEAR_INTERPOLATOR = new PathInterpolator(0.65f, 0.0f, 1.0f, 1.0f);
        this.mDiamondInterpolator = new PathInterpolator(0.2f, 0.0f, 0.2f, 1.0f);
        this.mCurrentAnimators = new ArraySet();
        this.mAnimatedViews = new ArrayList();
        this.mAnimationState = 0;
        this.mGestureState = 0;
        this.mRetract = new Runnable() {
            public final void run() {
                OpaLayout opaLayout = OpaLayout.this;
                int i = OpaLayout.$r8$clinit;
                opaLayout.cancelCurrentAnimation("retract");
                OpaLayout.this.startRetractAnimation$1();
            }
        };
        this.mOverviewProxyListener = new OverviewProxyService.OverviewProxyListener() {
            public final void onConnectionChanged(boolean z) {
                OpaLayout.this.updateOpaLayout();
            }
        };
        this.mDiamondAnimation = new OpaLayout$$ExternalSyntheticLambda0(this, 0);
    }

    public OpaLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }
}
