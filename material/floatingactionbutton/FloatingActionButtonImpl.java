package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.core.util.Preconditions;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ImageMatrixProperty;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.StateListAnimator;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class FloatingActionButtonImpl {
    public static final FastOutLinearInInterpolator ELEVATION_ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    public static final int[] EMPTY_STATE_SET = new int[0];
    public static final int[] ENABLED_STATE_SET = {16842910};
    public static final int[] FOCUSED_ENABLED_STATE_SET = {16842908, 16842910};
    public static final int HIDE_ANIM_DURATION_ATTR = 2130969613;
    public static final int HIDE_ANIM_EASING_ATTR = 2130969624;
    public static final int[] HOVERED_ENABLED_STATE_SET = {16843623, 16842910};
    public static final int[] HOVERED_FOCUSED_ENABLED_STATE_SET = {16843623, 16842908, 16842910};
    public static final int[] PRESSED_ENABLED_STATE_SET = {16842919, 16842910};
    public static final int SHOW_ANIM_DURATION_ATTR = 2130969610;
    public static final int SHOW_ANIM_EASING_ATTR = 2130969626;
    public int animState = 0;
    public BorderDrawable borderDrawable;
    public Drawable contentBackground;
    public Animator currentAnimator;
    public float elevation;
    public boolean ensureMinTouchTargetSize;
    public ArrayList hideListeners;
    public MotionSpec hideMotionSpec;
    public float hoveredFocusedTranslationZ;
    public float imageMatrixScale = 1.0f;
    public int maxImageSize;
    public int minTouchTargetSize;
    public AnonymousClass6 preDrawListener;
    public float pressedTranslationZ;
    public Drawable rippleDrawable;
    public final float rotation;
    public boolean shadowPaddingEnabled = true;
    public final FloatingActionButton.ShadowDelegateImpl shadowViewDelegate;
    public ShapeAppearanceModel shapeAppearance;
    public MaterialShapeDrawable shapeDrawable;
    public ArrayList showListeners;
    public MotionSpec showMotionSpec;
    public final StateListAnimator stateListAnimator;
    public final Matrix tmpMatrix = new Matrix();
    public final Rect tmpRect = new Rect();
    public final RectF tmpRectF1 = new RectF();
    public final RectF tmpRectF2 = new RectF();
    public ArrayList transformationCallbacks;
    public final FloatingActionButton view;

    /* renamed from: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$6  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class AnonymousClass6 implements ViewTreeObserver.OnPreDrawListener {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ResetElevationAnimation extends ShadowAnimatorImpl {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ FloatingActionButtonImpl this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ResetElevationAnimation(FloatingActionButtonImplLollipop floatingActionButtonImplLollipop, int i) {
            super(floatingActionButtonImplLollipop);
            this.$r8$classId = i;
            this.this$0 = floatingActionButtonImplLollipop;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class ShadowAnimatorImpl extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        public float shadowSizeEnd;
        public float shadowSizeStart;
        public final /* synthetic */ FloatingActionButtonImpl this$0;
        public boolean validValues;

        public ShadowAnimatorImpl(FloatingActionButtonImplLollipop floatingActionButtonImplLollipop) {
            this.this$0 = floatingActionButtonImplLollipop;
        }

        public final void onAnimationEnd(Animator animator) {
            FloatingActionButtonImpl floatingActionButtonImpl = this.this$0;
            float f = (float) ((int) this.shadowSizeEnd);
            MaterialShapeDrawable materialShapeDrawable = floatingActionButtonImpl.shapeDrawable;
            if (materialShapeDrawable != null) {
                materialShapeDrawable.setElevation(f);
            }
            this.validValues = false;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
            r3.shadowSizeEnd = r1;
            r3.validValues = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
            r1 = r1 + r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onAnimationUpdate(android.animation.ValueAnimator r4) {
            /*
                r3 = this;
                boolean r0 = r3.validValues
                if (r0 != 0) goto L_0x0033
                com.google.android.material.floatingactionbutton.FloatingActionButtonImpl r0 = r3.this$0
                com.google.android.material.shape.MaterialShapeDrawable r0 = r0.shapeDrawable
                r1 = 0
                if (r0 != 0) goto L_0x000d
                r0 = r1
                goto L_0x0011
            L_0x000d:
                com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r0 = r0.drawableState
                float r0 = r0.elevation
            L_0x0011:
                r3.shadowSizeStart = r0
                r0 = r3
                com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$ResetElevationAnimation r0 = (com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ResetElevationAnimation) r0
                int r2 = r0.$r8$classId
                switch(r2) {
                    case 0: goto L_0x002a;
                    case 1: goto L_0x002e;
                    case 2: goto L_0x0023;
                    default: goto L_0x001b;
                }
            L_0x001b:
                com.google.android.material.floatingactionbutton.FloatingActionButtonImpl r0 = r0.this$0
                float r1 = r0.elevation
                float r0 = r0.pressedTranslationZ
            L_0x0021:
                float r1 = r1 + r0
                goto L_0x002e
            L_0x0023:
                com.google.android.material.floatingactionbutton.FloatingActionButtonImpl r0 = r0.this$0
                float r1 = r0.elevation
                float r0 = r0.hoveredFocusedTranslationZ
                goto L_0x0021
            L_0x002a:
                com.google.android.material.floatingactionbutton.FloatingActionButtonImpl r0 = r0.this$0
                float r1 = r0.elevation
            L_0x002e:
                r3.shadowSizeEnd = r1
                r0 = 1
                r3.validValues = r0
            L_0x0033:
                com.google.android.material.floatingactionbutton.FloatingActionButtonImpl r0 = r3.this$0
                float r1 = r3.shadowSizeStart
                float r3 = r3.shadowSizeEnd
                float r3 = r3 - r1
                float r4 = r4.getAnimatedFraction()
                float r4 = r4 * r3
                float r4 = r4 + r1
                int r3 = (int) r4
                float r3 = (float) r3
                com.google.android.material.shape.MaterialShapeDrawable r4 = r0.shapeDrawable
                if (r4 == 0) goto L_0x0049
                r4.setElevation(r3)
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl.onAnimationUpdate(android.animation.ValueAnimator):void");
        }
    }

    public FloatingActionButtonImpl(FloatingActionButton floatingActionButton, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.view = floatingActionButton;
        this.shadowViewDelegate = shadowDelegateImpl;
        StateListAnimator stateListAnimator2 = new StateListAnimator();
        FloatingActionButtonImplLollipop floatingActionButtonImplLollipop = (FloatingActionButtonImplLollipop) this;
        stateListAnimator2.addState(PRESSED_ENABLED_STATE_SET, createElevationAnimator(new ResetElevationAnimation(floatingActionButtonImplLollipop, 3)));
        stateListAnimator2.addState(HOVERED_FOCUSED_ENABLED_STATE_SET, createElevationAnimator(new ResetElevationAnimation(floatingActionButtonImplLollipop, 2)));
        stateListAnimator2.addState(FOCUSED_ENABLED_STATE_SET, createElevationAnimator(new ResetElevationAnimation(floatingActionButtonImplLollipop, 2)));
        stateListAnimator2.addState(HOVERED_ENABLED_STATE_SET, createElevationAnimator(new ResetElevationAnimation(floatingActionButtonImplLollipop, 2)));
        stateListAnimator2.addState(ENABLED_STATE_SET, createElevationAnimator(new ResetElevationAnimation(floatingActionButtonImplLollipop, 0)));
        stateListAnimator2.addState(EMPTY_STATE_SET, createElevationAnimator(new ResetElevationAnimation(floatingActionButtonImplLollipop, 1)));
        floatingActionButton.getRotation();
    }

    public static ValueAnimator createElevationAnimator(ResetElevationAnimation resetElevationAnimation) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
        valueAnimator.setDuration(100);
        valueAnimator.addListener(resetElevationAnimation);
        valueAnimator.addUpdateListener(resetElevationAnimation);
        valueAnimator.setFloatValues(new float[]{0.0f, 1.0f});
        return valueAnimator;
    }

    public final void calculateImageMatrixFromScale(float f, Matrix matrix) {
        matrix.reset();
        Drawable drawable = this.view.getDrawable();
        if (drawable != null && this.maxImageSize != 0) {
            RectF rectF = this.tmpRectF1;
            RectF rectF2 = this.tmpRectF2;
            rectF.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
            int i = this.maxImageSize;
            rectF2.set(0.0f, 0.0f, (float) i, (float) i);
            matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
            int i2 = this.maxImageSize;
            matrix.postScale(f, f, ((float) i2) / 2.0f, ((float) i2) / 2.0f);
        }
    }

    public final AnimatorSet createAnimator(MotionSpec motionSpec, float f, float f2, float f3) {
        ArrayList arrayList = new ArrayList();
        Property property = View.ALPHA;
        float[] fArr = {f};
        FloatingActionButton floatingActionButton = this.view;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(floatingActionButton, property, fArr);
        motionSpec.getTiming("opacity").apply(ofFloat);
        arrayList.add(ofFloat);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(floatingActionButton, View.SCALE_X, new float[]{f2});
        motionSpec.getTiming("scale").apply(ofFloat2);
        arrayList.add(ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(floatingActionButton, View.SCALE_Y, new float[]{f2});
        motionSpec.getTiming("scale").apply(ofFloat3);
        arrayList.add(ofFloat3);
        Matrix matrix = this.tmpMatrix;
        calculateImageMatrixFromScale(f3, matrix);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(floatingActionButton, new ImageMatrixProperty(), new TypeEvaluator() {
            public final float[] tempEndValues = new float[9];
            public final Matrix tempMatrix = new Matrix();
            public final float[] tempStartValues = new float[9];

            public final Object evaluate(float f, Object obj, Object obj2) {
                FloatingActionButtonImpl.this.imageMatrixScale = f;
                ((Matrix) obj).getValues(this.tempStartValues);
                ((Matrix) obj2).getValues(this.tempEndValues);
                for (int i = 0; i < 9; i++) {
                    float[] fArr = this.tempEndValues;
                    float f2 = fArr[i];
                    float f3 = this.tempStartValues[i];
                    fArr[i] = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f2, f3, f, f3);
                }
                this.tempMatrix.setValues(this.tempEndValues);
                return this.tempMatrix;
            }
        }, new Matrix[]{new Matrix(matrix)});
        motionSpec.getTiming("iconScale").apply(ofObject);
        arrayList.add(ofObject);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    public final AnimatorSet createDefaultAnimator(float f, float f2, float f3, int i, int i2) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        FloatingActionButton floatingActionButton = this.view;
        final float alpha = floatingActionButton.getAlpha();
        final float scaleX = floatingActionButton.getScaleX();
        final float scaleY = floatingActionButton.getScaleY();
        final float f4 = this.imageMatrixScale;
        final Matrix matrix = new Matrix(this.tmpMatrix);
        final float f5 = f;
        final float f6 = f2;
        final float f7 = f3;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                FloatingActionButtonImpl.this.view.setAlpha(AnimationUtils.lerp(alpha, f5, 0.0f, 0.2f, floatValue));
                FloatingActionButtonImpl.this.view.setScaleX(AnimationUtils.lerp(scaleX, f6, floatValue));
                FloatingActionButtonImpl.this.view.setScaleY(AnimationUtils.lerp(scaleY, f6, floatValue));
                FloatingActionButtonImpl.this.imageMatrixScale = AnimationUtils.lerp(f4, f7, floatValue);
                FloatingActionButtonImpl.this.calculateImageMatrixFromScale(AnimationUtils.lerp(f4, f7, floatValue), matrix);
                FloatingActionButtonImpl.this.view.setImageMatrix(matrix);
            }
        });
        arrayList.add(ofFloat);
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        animatorSet.setDuration((long) MotionUtils.resolveThemeDuration(floatingActionButton.getContext(), i, floatingActionButton.getContext().getResources().getInteger(2131427511)));
        animatorSet.setInterpolator(MotionUtils.resolveThemeInterpolator(floatingActionButton.getContext(), i2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    public abstract float getElevation();

    public void getPadding(Rect rect) {
        int i;
        float f;
        if (this.ensureMinTouchTargetSize) {
            int i2 = this.minTouchTargetSize;
            FloatingActionButton floatingActionButton = this.view;
            i = (i2 - floatingActionButton.getSizeDimension(floatingActionButton.size)) / 2;
        } else {
            i = 0;
        }
        if (this.shadowPaddingEnabled) {
            f = getElevation() + this.pressedTranslationZ;
        } else {
            f = 0.0f;
        }
        int max = Math.max(i, (int) Math.ceil((double) f));
        int max2 = Math.max(i, (int) Math.ceil((double) (f * 1.5f)));
        rect.set(max, max2, max, max2);
    }

    public abstract void initializeBackgroundDrawable(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i);

    public abstract void jumpDrawableToCurrentState();

    public abstract void onDrawableStateChanged(int[] iArr);

    public abstract void onElevationsChanged(float f, float f2, float f3);

    public final void onScaleChanged() {
        float f;
        ArrayList arrayList = this.transformationCallbacks;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                FloatingActionButton.TransformationCallbackWrapper transformationCallbackWrapper = (FloatingActionButton.TransformationCallbackWrapper) it.next();
                BottomAppBar.AnonymousClass2 r1 = transformationCallbackWrapper.listener;
                r1.getClass();
                BottomAppBar bottomAppBar = BottomAppBar.this;
                MaterialShapeDrawable materialShapeDrawable = bottomAppBar.materialShapeDrawable;
                FloatingActionButton floatingActionButton = FloatingActionButton.this;
                if (floatingActionButton.getVisibility() == 0 && bottomAppBar.fabAnchorMode == 1) {
                    f = floatingActionButton.getScaleY();
                } else {
                    f = 0.0f;
                }
                materialShapeDrawable.setInterpolation(f);
            }
        }
    }

    public final void onTranslationChanged() {
        ArrayList arrayList = this.transformationCallbacks;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                FloatingActionButton.TransformationCallbackWrapper transformationCallbackWrapper = (FloatingActionButton.TransformationCallbackWrapper) it.next();
                BottomAppBar.AnonymousClass2 r1 = transformationCallbackWrapper.listener;
                r1.getClass();
                BottomAppBar bottomAppBar = BottomAppBar.this;
                if (bottomAppBar.fabAnchorMode == 1) {
                    FloatingActionButton floatingActionButton = FloatingActionButton.this;
                    float translationX = floatingActionButton.getTranslationX();
                    if (bottomAppBar.getTopEdgeTreatment().horizontalOffset != translationX) {
                        bottomAppBar.getTopEdgeTreatment().horizontalOffset = translationX;
                        bottomAppBar.materialShapeDrawable.invalidateSelf();
                    }
                    float f = 0.0f;
                    float max = Math.max(0.0f, -floatingActionButton.getTranslationY());
                    if (bottomAppBar.getTopEdgeTreatment().cradleVerticalOffset != max) {
                        BottomAppBarTopEdgeTreatment topEdgeTreatment = bottomAppBar.getTopEdgeTreatment();
                        if (max >= 0.0f) {
                            topEdgeTreatment.cradleVerticalOffset = max;
                            bottomAppBar.materialShapeDrawable.invalidateSelf();
                        } else {
                            topEdgeTreatment.getClass();
                            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
                        }
                    }
                    MaterialShapeDrawable materialShapeDrawable = bottomAppBar.materialShapeDrawable;
                    if (floatingActionButton.getVisibility() == 0) {
                        f = floatingActionButton.getScaleY();
                    }
                    materialShapeDrawable.setInterpolation(f);
                }
            }
        }
    }

    public final void setShapeAppearance(ShapeAppearanceModel shapeAppearanceModel) {
        this.shapeAppearance = shapeAppearanceModel;
        MaterialShapeDrawable materialShapeDrawable = this.shapeDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
        Drawable drawable = this.rippleDrawable;
        if (drawable instanceof Shapeable) {
            ((Shapeable) drawable).setShapeAppearanceModel(shapeAppearanceModel);
        }
        BorderDrawable borderDrawable2 = this.borderDrawable;
        if (borderDrawable2 != null) {
            borderDrawable2.shapeAppearanceModel = shapeAppearanceModel;
            borderDrawable2.invalidateSelf();
        }
    }

    public abstract boolean shouldAddPadding();

    public final void updatePadding() {
        Rect rect = this.tmpRect;
        getPadding(rect);
        Preconditions.checkNotNull(this.contentBackground, "Didn't initialize content background");
        boolean shouldAddPadding = shouldAddPadding();
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.shadowViewDelegate;
        if (shouldAddPadding) {
            FloatingActionButtonImpl.super.setBackgroundDrawable(new InsetDrawable(this.contentBackground, rect.left, rect.top, rect.right, rect.bottom));
        } else {
            Drawable drawable = this.contentBackground;
            if (drawable != null) {
                FloatingActionButtonImpl.super.setBackgroundDrawable(drawable);
            } else {
                shadowDelegateImpl.getClass();
            }
        }
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        FloatingActionButton floatingActionButton = FloatingActionButton.this;
        floatingActionButton.shadowPadding.set(i, i2, i3, i4);
        int i5 = floatingActionButton.imagePadding;
        floatingActionButton.setPadding(i + i5, i2 + i5, i3 + i5, i4 + i5);
    }
}
