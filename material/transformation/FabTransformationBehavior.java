package com.google.android.material.transformation;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.MotionTiming;
import com.google.android.material.animation.Positioning;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

@Deprecated
/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    public float dependencyOriginalTranslationX;
    public float dependencyOriginalTranslationY;
    public final int[] tmpArray = new int[2];
    public final Rect tmpRect = new Rect();
    public final RectF tmpRectF1 = new RectF();
    public final RectF tmpRectF2 = new RectF();

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class FabTransformationSpec {
        public Positioning positioning;
        public MotionSpec timings;
    }

    public FabTransformationBehavior() {
    }

    public static Pair calculateMotionTiming(float f, float f2, boolean z, FabTransformationSpec fabTransformationSpec) {
        MotionTiming motionTiming;
        MotionTiming motionTiming2;
        int i;
        if (f == 0.0f || f2 == 0.0f) {
            motionTiming2 = fabTransformationSpec.timings.getTiming("translationXLinear");
            motionTiming = fabTransformationSpec.timings.getTiming("translationYLinear");
        } else if ((!z || f2 >= 0.0f) && (z || i <= 0)) {
            motionTiming2 = fabTransformationSpec.timings.getTiming("translationXCurveDownwards");
            motionTiming = fabTransformationSpec.timings.getTiming("translationYCurveDownwards");
        } else {
            motionTiming2 = fabTransformationSpec.timings.getTiming("translationXCurveUpwards");
            motionTiming = fabTransformationSpec.timings.getTiming("translationYCurveUpwards");
        }
        return new Pair(motionTiming2, motionTiming);
    }

    public static float calculateValueOfAnimationAtEndOfExpansion(FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, float f) {
        long j = motionTiming.delay;
        MotionTiming timing = fabTransformationSpec.timings.getTiming("expansion");
        return AnimationUtils.lerp(f, 0.0f, motionTiming.getInterpolator().getInterpolation(((float) (((timing.delay + timing.duration) + 17) - j)) / ((float) motionTiming.duration)));
    }

    public final float calculateTranslationX(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        rectF.offset(this.dependencyOriginalTranslationX, this.dependencyOriginalTranslationY);
        calculateWindowBounds(view2, rectF2);
        positioning.getClass();
        return (rectF2.centerX() - rectF.centerX()) + 0.0f;
    }

    public final float calculateTranslationY(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        rectF.offset(this.dependencyOriginalTranslationX, this.dependencyOriginalTranslationY);
        calculateWindowBounds(view2, rectF2);
        positioning.getClass();
        return (rectF2.centerY() - rectF.centerY()) + 0.0f;
    }

    public final void calculateWindowBounds(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        int[] iArr = this.tmpArray;
        view.getLocationInWindow(iArr);
        rectF.offsetTo((float) iArr[0], (float) iArr[1]);
        rectF.offset((float) ((int) (-view.getTranslationX())), (float) ((int) (-view.getTranslationY())));
    }

    public final boolean layoutDependsOn(View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        } else if (!(view2 instanceof FloatingActionButton)) {
            return false;
        } else {
            int i = ((FloatingActionButton) view2).expandableWidgetHelper.expandedComponentIdHint;
            if (i == 0 || i == view.getId()) {
                return true;
            }
            return false;
        }
    }

    public final void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        if (layoutParams.dodgeInsetEdges == 0) {
            layoutParams.dodgeInsetEdges = 80;
        }
    }

    /* JADX WARNING: type inference failed for: r0v11, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x03c0 A[LOOP:1: B:103:0x03be->B:104:0x03c0, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x02e6  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x02e9  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x033e  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.animation.AnimatorSet onCreateExpandedStateChangeAnimation(android.view.View r25, android.view.View r26, boolean r27, boolean r28) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            r3 = r27
            r4 = 0
            r5 = 1
            android.content.Context r6 = r26.getContext()
            com.google.android.material.transformation.FabTransformationBehavior$FabTransformationSpec r6 = r0.onCreateMotionSpec(r6, r3)
            if (r3 == 0) goto L_0x0020
            float r7 = r25.getTranslationX()
            r0.dependencyOriginalTranslationX = r7
            float r7 = r25.getTranslationY()
            r0.dependencyOriginalTranslationY = r7
        L_0x0020:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.WeakHashMap r9 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            float r9 = androidx.core.view.ViewCompat.Api21Impl.getElevation(r26)
            float r10 = androidx.core.view.ViewCompat.Api21Impl.getElevation(r25)
            float r9 = r9 - r10
            r10 = 0
            if (r3 == 0) goto L_0x0049
            if (r28 != 0) goto L_0x003e
            float r9 = -r9
            r2.setTranslationZ(r9)
        L_0x003e:
            android.util.Property r9 = android.view.View.TRANSLATION_Z
            float[] r11 = new float[r5]
            r11[r4] = r10
            android.animation.ObjectAnimator r9 = android.animation.ObjectAnimator.ofFloat(r2, r9, r11)
            goto L_0x0054
        L_0x0049:
            android.util.Property r11 = android.view.View.TRANSLATION_Z
            float r9 = -r9
            float[] r12 = new float[r5]
            r12[r4] = r9
            android.animation.ObjectAnimator r9 = android.animation.ObjectAnimator.ofFloat(r2, r11, r12)
        L_0x0054:
            com.google.android.material.animation.MotionSpec r11 = r6.timings
            java.lang.String r12 = "elevation"
            com.google.android.material.animation.MotionTiming r11 = r11.getTiming(r12)
            r11.apply(r9)
            r7.add(r9)
            android.graphics.RectF r9 = r0.tmpRectF1
            com.google.android.material.animation.Positioning r11 = r6.positioning
            float r11 = r0.calculateTranslationX(r1, r2, r11)
            com.google.android.material.animation.Positioning r12 = r6.positioning
            float r12 = r0.calculateTranslationY(r1, r2, r12)
            android.util.Pair r13 = calculateMotionTiming(r11, r12, r3, r6)
            java.lang.Object r14 = r13.first
            com.google.android.material.animation.MotionTiming r14 = (com.google.android.material.animation.MotionTiming) r14
            java.lang.Object r13 = r13.second
            com.google.android.material.animation.MotionTiming r13 = (com.google.android.material.animation.MotionTiming) r13
            android.graphics.RectF r15 = r0.tmpRectF2
            android.graphics.Rect r4 = r0.tmpRect
            if (r3 == 0) goto L_0x00c8
            if (r28 != 0) goto L_0x008c
            float r10 = -r11
            r2.setTranslationX(r10)
            float r10 = -r12
            r2.setTranslationY(r10)
        L_0x008c:
            android.util.Property r10 = android.view.View.TRANSLATION_X
            r18 = r8
            float[] r8 = new float[r5]
            r16 = 0
            r17 = 0
            r8[r17] = r16
            android.animation.ObjectAnimator r8 = android.animation.ObjectAnimator.ofFloat(r2, r10, r8)
            android.util.Property r10 = android.view.View.TRANSLATION_Y
            r19 = r8
            float[] r8 = new float[r5]
            r8[r17] = r16
            android.animation.ObjectAnimator r8 = android.animation.ObjectAnimator.ofFloat(r2, r10, r8)
            float r10 = -r11
            float r11 = -r12
            float r10 = calculateValueOfAnimationAtEndOfExpansion(r6, r14, r10)
            float r11 = calculateValueOfAnimationAtEndOfExpansion(r6, r13, r11)
            r2.getWindowVisibleDisplayFrame(r4)
            r9.set(r4)
            r0.calculateWindowBounds(r2, r15)
            r15.offset(r10, r11)
            r15.intersect(r9)
            r9.set(r15)
            r10 = r8
            r8 = r19
            goto L_0x00e2
        L_0x00c8:
            r18 = r8
            android.util.Property r8 = android.view.View.TRANSLATION_X
            float r10 = -r11
            float[] r11 = new float[r5]
            r16 = 0
            r11[r16] = r10
            android.animation.ObjectAnimator r8 = android.animation.ObjectAnimator.ofFloat(r2, r8, r11)
            android.util.Property r10 = android.view.View.TRANSLATION_Y
            float r11 = -r12
            float[] r12 = new float[r5]
            r12[r16] = r11
            android.animation.ObjectAnimator r10 = android.animation.ObjectAnimator.ofFloat(r2, r10, r12)
        L_0x00e2:
            r14.apply(r8)
            r13.apply(r10)
            r7.add(r8)
            r7.add(r10)
            float r8 = r9.width()
            float r10 = r9.height()
            com.google.android.material.animation.Positioning r11 = r6.positioning
            float r11 = r0.calculateTranslationX(r1, r2, r11)
            com.google.android.material.animation.Positioning r12 = r6.positioning
            float r12 = r0.calculateTranslationY(r1, r2, r12)
            android.util.Pair r13 = calculateMotionTiming(r11, r12, r3, r6)
            java.lang.Object r14 = r13.first
            com.google.android.material.animation.MotionTiming r14 = (com.google.android.material.animation.MotionTiming) r14
            java.lang.Object r13 = r13.second
            com.google.android.material.animation.MotionTiming r13 = (com.google.android.material.animation.MotionTiming) r13
            r19 = r11
            android.util.Property r11 = android.view.View.TRANSLATION_X
            r20 = r12
            float[] r12 = new float[r5]
            if (r3 == 0) goto L_0x011d
            r5 = r19
        L_0x011a:
            r16 = 0
            goto L_0x0120
        L_0x011d:
            float r5 = r0.dependencyOriginalTranslationX
            goto L_0x011a
        L_0x0120:
            r12[r16] = r5
            android.animation.ObjectAnimator r5 = android.animation.ObjectAnimator.ofFloat(r1, r11, r12)
            android.util.Property r11 = android.view.View.TRANSLATION_Y
            r21 = r8
            r12 = 1
            float[] r8 = new float[r12]
            if (r3 == 0) goto L_0x0132
            r12 = r20
            goto L_0x0134
        L_0x0132:
            float r12 = r0.dependencyOriginalTranslationY
        L_0x0134:
            r8[r16] = r12
            android.animation.ObjectAnimator r8 = android.animation.ObjectAnimator.ofFloat(r1, r11, r8)
            r14.apply(r5)
            r13.apply(r8)
            r7.add(r5)
            r7.add(r8)
            boolean r5 = r2 instanceof com.google.android.material.circularreveal.CircularRevealWidget
            if (r5 == 0) goto L_0x015b
            boolean r8 = r1 instanceof android.widget.ImageView
            if (r8 != 0) goto L_0x014f
            goto L_0x015b
        L_0x014f:
            r8 = r2
            com.google.android.material.circularreveal.CircularRevealWidget r8 = (com.google.android.material.circularreveal.CircularRevealWidget) r8
            r11 = r1
            android.widget.ImageView r11 = (android.widget.ImageView) r11
            android.graphics.drawable.Drawable r11 = r11.getDrawable()
            if (r11 != 0) goto L_0x015e
        L_0x015b:
            r8 = r18
            goto L_0x01a0
        L_0x015e:
            r11.mutate()
            r12 = 255(0xff, float:3.57E-43)
            if (r3 == 0) goto L_0x0176
            if (r28 != 0) goto L_0x016a
            r11.setAlpha(r12)
        L_0x016a:
            com.google.android.material.animation.DrawableAlphaProperty r12 = com.google.android.material.animation.DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT
            r13 = 0
            int[] r14 = new int[]{r13}
            android.animation.ObjectAnimator r12 = android.animation.ObjectAnimator.ofInt(r11, r12, r14)
            goto L_0x0180
        L_0x0176:
            com.google.android.material.animation.DrawableAlphaProperty r13 = com.google.android.material.animation.DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT
            int[] r12 = new int[]{r12}
            android.animation.ObjectAnimator r12 = android.animation.ObjectAnimator.ofInt(r11, r13, r12)
        L_0x0180:
            com.google.android.material.transformation.FabTransformationBehavior$2 r13 = new com.google.android.material.transformation.FabTransformationBehavior$2
            r13.<init>(r2)
            r12.addUpdateListener(r13)
            com.google.android.material.animation.MotionSpec r13 = r6.timings
            java.lang.String r14 = "iconFade"
            com.google.android.material.animation.MotionTiming r13 = r13.getTiming(r14)
            r13.apply(r12)
            r7.add(r12)
            com.google.android.material.transformation.FabTransformationBehavior$3 r12 = new com.google.android.material.transformation.FabTransformationBehavior$3
            r12.<init>(r11)
            r8 = r18
            r8.add(r12)
        L_0x01a0:
            if (r5 != 0) goto L_0x01aa
            r18 = r5
            r20 = r6
            r4 = r7
            r5 = r8
            goto L_0x02e4
        L_0x01aa:
            r11 = r2
            com.google.android.material.circularreveal.CircularRevealWidget r11 = (com.google.android.material.circularreveal.CircularRevealWidget) r11
            com.google.android.material.animation.Positioning r12 = r6.positioning
            r0.calculateWindowBounds(r1, r9)
            float r13 = r0.dependencyOriginalTranslationX
            float r14 = r0.dependencyOriginalTranslationY
            r9.offset(r13, r14)
            r0.calculateWindowBounds(r2, r15)
            float r12 = r0.calculateTranslationX(r1, r2, r12)
            float r12 = -r12
            r13 = 0
            r15.offset(r12, r13)
            float r12 = r9.centerX()
            float r13 = r15.left
            float r12 = r12 - r13
            com.google.android.material.animation.Positioning r13 = r6.positioning
            r0.calculateWindowBounds(r1, r9)
            float r14 = r0.dependencyOriginalTranslationX
            r18 = r5
            float r5 = r0.dependencyOriginalTranslationY
            r9.offset(r14, r5)
            r0.calculateWindowBounds(r2, r15)
            float r0 = r0.calculateTranslationY(r1, r2, r13)
            float r0 = -r0
            r5 = 0
            r15.offset(r5, r0)
            float r0 = r9.centerY()
            float r5 = r15.top
            float r0 = r0 - r5
            r5 = r1
            com.google.android.material.floatingactionbutton.FloatingActionButton r5 = (com.google.android.material.floatingactionbutton.FloatingActionButton) r5
            boolean r9 = androidx.core.view.ViewCompat.Api19Impl.isLaidOut(r5)
            if (r9 == 0) goto L_0x0205
            int r9 = r5.getWidth()
            int r13 = r5.getHeight()
            r14 = 0
            r4.set(r14, r14, r9, r13)
            r5.offsetRectWithShadow(r4)
        L_0x0205:
            int r4 = r4.width()
            float r4 = (float) r4
            r5 = 1073741824(0x40000000, float:2.0)
            float r4 = r4 / r5
            com.google.android.material.animation.MotionSpec r5 = r6.timings
            java.lang.String r9 = "expansion"
            com.google.android.material.animation.MotionTiming r5 = r5.getTiming(r9)
            if (r3 == 0) goto L_0x025e
            if (r28 != 0) goto L_0x0221
            com.google.android.material.circularreveal.CircularRevealWidget$RevealInfo r9 = new com.google.android.material.circularreveal.CircularRevealWidget$RevealInfo
            r9.<init>(r12, r0, r4)
            r11.setRevealInfo(r9)
        L_0x0221:
            if (r28 == 0) goto L_0x0229
            com.google.android.material.circularreveal.CircularRevealWidget$RevealInfo r4 = r11.getRevealInfo()
            float r4 = r4.radius
        L_0x0229:
            r9 = r21
            float r9 = com.google.android.material.math.MathUtils.distanceToFurthestCorner(r12, r0, r9, r10)
            android.animation.Animator r9 = com.google.android.material.circularreveal.CircularRevealCompat.createCircularReveal(r11, r12, r0, r9)
            com.google.android.material.transformation.FabTransformationBehavior$4 r10 = new com.google.android.material.transformation.FabTransformationBehavior$4
            r10.<init>()
            r9.addListener(r10)
            long r13 = r5.delay
            int r10 = (int) r12
            int r0 = (int) r0
            r15 = r8
            r24 = r9
            r8 = 0
            int r12 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1))
            if (r12 <= 0) goto L_0x0255
            android.animation.Animator r0 = android.view.ViewAnimationUtils.createCircularReveal(r2, r10, r0, r4, r4)
            r0.setStartDelay(r8)
            r0.setDuration(r13)
            r7.add(r0)
        L_0x0255:
            r9 = r24
            r20 = r6
            r4 = r7
            r22 = r11
            goto L_0x02d3
        L_0x025e:
            r15 = r8
            com.google.android.material.circularreveal.CircularRevealWidget$RevealInfo r8 = r11.getRevealInfo()
            float r8 = r8.radius
            android.animation.Animator r9 = com.google.android.material.circularreveal.CircularRevealCompat.createCircularReveal(r11, r12, r0, r4)
            long r13 = r5.delay
            int r10 = (int) r12
            int r0 = (int) r0
            r22 = r11
            r11 = 0
            int r20 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r20 <= 0) goto L_0x0282
            android.animation.Animator r8 = android.view.ViewAnimationUtils.createCircularReveal(r2, r10, r0, r8, r8)
            r8.setStartDelay(r11)
            r8.setDuration(r13)
            r7.add(r8)
        L_0x0282:
            com.google.android.material.animation.MotionSpec r8 = r6.timings
            androidx.collection.SimpleArrayMap r8 = r8.timings
            int r13 = r8.size
            r14 = 0
        L_0x0289:
            if (r14 >= r13) goto L_0x02af
            java.lang.Object r20 = r8.valueAt(r14)
            r21 = r8
            r8 = r20
            com.google.android.material.animation.MotionTiming r8 = (com.google.android.material.animation.MotionTiming) r8
            r20 = r6
            r23 = r7
            long r6 = r8.delay
            r24 = r9
            long r8 = r8.duration
            long r6 = r6 + r8
            long r11 = java.lang.Math.max(r11, r6)
            r6 = 1
            int r14 = r14 + r6
            r9 = r24
            r6 = r20
            r8 = r21
            r7 = r23
            goto L_0x0289
        L_0x02af:
            r20 = r6
            r23 = r7
            r24 = r9
            long r6 = r5.delay
            long r8 = r5.duration
            long r6 = r6 + r8
            int r8 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r8 >= 0) goto L_0x02cf
            android.animation.Animator r0 = android.view.ViewAnimationUtils.createCircularReveal(r2, r10, r0, r4, r4)
            r0.setStartDelay(r6)
            long r11 = r11 - r6
            r0.setDuration(r11)
            r4 = r23
            r4.add(r0)
            goto L_0x02d1
        L_0x02cf:
            r4 = r23
        L_0x02d1:
            r9 = r24
        L_0x02d3:
            r5.apply(r9)
            r4.add(r9)
            com.google.android.material.circularreveal.CircularRevealCompat$1 r0 = new com.google.android.material.circularreveal.CircularRevealCompat$1
            r5 = r22
            r0.<init>()
            r5 = r15
            r5.add(r0)
        L_0x02e4:
            if (r18 != 0) goto L_0x02e9
            r6 = r20
            goto L_0x0337
        L_0x02e9:
            r0 = r2
            com.google.android.material.circularreveal.CircularRevealWidget r0 = (com.google.android.material.circularreveal.CircularRevealWidget) r0
            java.util.WeakHashMap r6 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            android.content.res.ColorStateList r6 = androidx.core.view.ViewCompat.Api21Impl.getBackgroundTintList(r25)
            if (r6 == 0) goto L_0x0301
            int[] r7 = r25.getDrawableState()
            int r8 = r6.getDefaultColor()
            int r6 = r6.getColorForState(r7, r8)
            goto L_0x0302
        L_0x0301:
            r6 = 0
        L_0x0302:
            r7 = 16777215(0xffffff, float:2.3509886E-38)
            r7 = r7 & r6
            if (r3 == 0) goto L_0x0318
            if (r28 != 0) goto L_0x030d
            r0.setCircularRevealScrimColor(r6)
        L_0x030d:
            com.google.android.material.circularreveal.CircularRevealWidget$CircularRevealScrimColorProperty r6 = com.google.android.material.circularreveal.CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR
            int[] r7 = new int[]{r7}
            android.animation.ObjectAnimator r0 = android.animation.ObjectAnimator.ofInt(r0, r6, r7)
            goto L_0x0322
        L_0x0318:
            com.google.android.material.circularreveal.CircularRevealWidget$CircularRevealScrimColorProperty r7 = com.google.android.material.circularreveal.CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR
            int[] r6 = new int[]{r6}
            android.animation.ObjectAnimator r0 = android.animation.ObjectAnimator.ofInt(r0, r7, r6)
        L_0x0322:
            com.google.android.material.animation.ArgbEvaluatorCompat r6 = com.google.android.material.animation.ArgbEvaluatorCompat.instance
            r0.setEvaluator(r6)
            r6 = r20
            com.google.android.material.animation.MotionSpec r7 = r6.timings
            java.lang.String r8 = "color"
            com.google.android.material.animation.MotionTiming r7 = r7.getTiming(r8)
            r7.apply(r0)
            r4.add(r0)
        L_0x0337:
            boolean r0 = r2 instanceof android.view.ViewGroup
            if (r0 != 0) goto L_0x033e
        L_0x033b:
            r11 = 0
            goto L_0x03a9
        L_0x033e:
            r7 = 2131363127(0x7f0a0537, float:1.8346054E38)
            android.view.View r7 = r2.findViewById(r7)
            r8 = 0
            if (r7 == 0) goto L_0x0350
            boolean r0 = r7 instanceof android.view.ViewGroup
            if (r0 == 0) goto L_0x036e
            r8 = r7
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
            goto L_0x036e
        L_0x0350:
            boolean r7 = r2 instanceof com.google.android.material.transformation.TransformationChildLayout
            if (r7 != 0) goto L_0x035f
            boolean r7 = r2 instanceof com.google.android.material.transformation.TransformationChildCard
            if (r7 == 0) goto L_0x0359
            goto L_0x035f
        L_0x0359:
            if (r0 == 0) goto L_0x036e
            r8 = r2
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
            goto L_0x036e
        L_0x035f:
            r0 = r2
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            r7 = 0
            android.view.View r0 = r0.getChildAt(r7)
            boolean r7 = r0 instanceof android.view.ViewGroup
            if (r7 == 0) goto L_0x036e
            r8 = r0
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
        L_0x036e:
            if (r8 != 0) goto L_0x0371
            goto L_0x033b
        L_0x0371:
            if (r3 == 0) goto L_0x038e
            if (r28 != 0) goto L_0x037f
            com.google.android.material.animation.ChildrenAlphaProperty r0 = com.google.android.material.animation.ChildrenAlphaProperty.CHILDREN_ALPHA
            r7 = 0
            java.lang.Float r7 = java.lang.Float.valueOf(r7)
            r0.set(r8, r7)
        L_0x037f:
            com.google.android.material.animation.ChildrenAlphaProperty r0 = com.google.android.material.animation.ChildrenAlphaProperty.CHILDREN_ALPHA
            r7 = 1
            float[] r9 = new float[r7]
            r10 = 1065353216(0x3f800000, float:1.0)
            r11 = 0
            r9[r11] = r10
            android.animation.ObjectAnimator r0 = android.animation.ObjectAnimator.ofFloat(r8, r0, r9)
            goto L_0x039b
        L_0x038e:
            r7 = 1
            r11 = 0
            com.google.android.material.animation.ChildrenAlphaProperty r0 = com.google.android.material.animation.ChildrenAlphaProperty.CHILDREN_ALPHA
            float[] r9 = new float[r7]
            r7 = 0
            r9[r11] = r7
            android.animation.ObjectAnimator r0 = android.animation.ObjectAnimator.ofFloat(r8, r0, r9)
        L_0x039b:
            com.google.android.material.animation.MotionSpec r6 = r6.timings
            java.lang.String r7 = "contentFade"
            com.google.android.material.animation.MotionTiming r6 = r6.getTiming(r7)
            r6.apply(r0)
            r4.add(r0)
        L_0x03a9:
            android.animation.AnimatorSet r0 = new android.animation.AnimatorSet
            r0.<init>()
            com.google.android.material.animation.AnimatorSetCompat.playTogether(r0, r4)
            com.google.android.material.transformation.FabTransformationBehavior$1 r4 = new com.google.android.material.transformation.FabTransformationBehavior$1
            r4.<init>(r3, r2, r1)
            r0.addListener(r4)
            int r1 = r5.size()
            r4 = r11
        L_0x03be:
            if (r4 >= r1) goto L_0x03cc
            java.lang.Object r2 = r5.get(r4)
            android.animation.Animator$AnimatorListener r2 = (android.animation.Animator.AnimatorListener) r2
            r0.addListener(r2)
            r2 = 1
            int r4 = r4 + r2
            goto L_0x03be
        L_0x03cc:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.transformation.FabTransformationBehavior.onCreateExpandedStateChangeAnimation(android.view.View, android.view.View, boolean, boolean):android.animation.AnimatorSet");
    }

    public abstract FabTransformationSpec onCreateMotionSpec(Context context, boolean z);

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
