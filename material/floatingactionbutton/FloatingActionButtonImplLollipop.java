package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class FloatingActionButtonImplLollipop extends FloatingActionButtonImpl {
    public StateListAnimator stateListAnimator;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AlwaysStatefulMaterialShapeDrawable extends MaterialShapeDrawable {
        public final boolean isStateful() {
            return true;
        }
    }

    public final Animator createElevationAnimator(float f, float f2) {
        AnimatorSet animatorSet = new AnimatorSet();
        FloatingActionButton floatingActionButton = this.view;
        animatorSet.play(ObjectAnimator.ofFloat(floatingActionButton, "elevation", new float[]{f}).setDuration(0)).with(ObjectAnimator.ofFloat(floatingActionButton, View.TRANSLATION_Z, new float[]{f2}).setDuration(100));
        animatorSet.setInterpolator(FloatingActionButtonImpl.ELEVATION_ANIM_INTERPOLATOR);
        return animatorSet;
    }

    public final MaterialShapeDrawable createShapeDrawable() {
        ShapeAppearanceModel shapeAppearanceModel = this.shapeAppearance;
        shapeAppearanceModel.getClass();
        return new MaterialShapeDrawable(shapeAppearanceModel);
    }

    public final float getElevation() {
        return this.view.getElevation();
    }

    public final void getPadding(Rect rect) {
        if (FloatingActionButton.this.compatPadding) {
            super.getPadding(rect);
            return;
        }
        if (this.ensureMinTouchTargetSize) {
            FloatingActionButton floatingActionButton = this.view;
            int sizeDimension = floatingActionButton.getSizeDimension(floatingActionButton.size);
            int i = this.minTouchTargetSize;
            if (sizeDimension < i) {
                int sizeDimension2 = (i - floatingActionButton.getSizeDimension(floatingActionButton.size)) / 2;
                rect.set(sizeDimension2, sizeDimension2, sizeDimension2, sizeDimension2);
                return;
            }
        }
        rect.set(0, 0, 0, 0);
    }

    public final void initializeBackgroundDrawable(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        Drawable drawable;
        MaterialShapeDrawable createShapeDrawable = createShapeDrawable();
        this.shapeDrawable = createShapeDrawable;
        createShapeDrawable.setTintList(colorStateList);
        if (mode != null) {
            this.shapeDrawable.setTintMode(mode);
        }
        MaterialShapeDrawable materialShapeDrawable = this.shapeDrawable;
        FloatingActionButton floatingActionButton = this.view;
        materialShapeDrawable.initializeElevationOverlay(floatingActionButton.getContext());
        if (i > 0) {
            Context context = floatingActionButton.getContext();
            ShapeAppearanceModel shapeAppearanceModel = this.shapeAppearance;
            shapeAppearanceModel.getClass();
            BorderDrawable borderDrawable = new BorderDrawable(shapeAppearanceModel);
            Object obj = ContextCompat.sLock;
            int color = context.getColor(2131099851);
            int color2 = context.getColor(2131099850);
            int color3 = context.getColor(2131099848);
            int color4 = context.getColor(2131099849);
            borderDrawable.topOuterStrokeColor = color;
            borderDrawable.topInnerStrokeColor = color2;
            borderDrawable.bottomOuterStrokeColor = color3;
            borderDrawable.bottomInnerStrokeColor = color4;
            float f = (float) i;
            if (borderDrawable.borderWidth != f) {
                borderDrawable.borderWidth = f;
                borderDrawable.paint.setStrokeWidth(f * 1.3333f);
                borderDrawable.invalidateShader = true;
                borderDrawable.invalidateSelf();
            }
            if (colorStateList != null) {
                borderDrawable.currentBorderTintColor = colorStateList.getColorForState(borderDrawable.getState(), borderDrawable.currentBorderTintColor);
            }
            borderDrawable.borderTint = colorStateList;
            borderDrawable.invalidateShader = true;
            borderDrawable.invalidateSelf();
            this.borderDrawable = borderDrawable;
            BorderDrawable borderDrawable2 = this.borderDrawable;
            borderDrawable2.getClass();
            MaterialShapeDrawable materialShapeDrawable2 = this.shapeDrawable;
            materialShapeDrawable2.getClass();
            drawable = new LayerDrawable(new Drawable[]{borderDrawable2, materialShapeDrawable2});
        } else {
            this.borderDrawable = null;
            drawable = this.shapeDrawable;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(colorStateList2), drawable, (Drawable) null);
        this.rippleDrawable = rippleDrawable;
        this.contentBackground = rippleDrawable;
    }

    public final void onElevationsChanged(float f, float f2, float f3) {
        FloatingActionButton floatingActionButton = this.view;
        if (floatingActionButton.getStateListAnimator() == this.stateListAnimator) {
            StateListAnimator stateListAnimator2 = new StateListAnimator();
            stateListAnimator2.addState(FloatingActionButtonImpl.PRESSED_ENABLED_STATE_SET, createElevationAnimator(f, f3));
            stateListAnimator2.addState(FloatingActionButtonImpl.HOVERED_FOCUSED_ENABLED_STATE_SET, createElevationAnimator(f, f2));
            stateListAnimator2.addState(FloatingActionButtonImpl.FOCUSED_ENABLED_STATE_SET, createElevationAnimator(f, f2));
            stateListAnimator2.addState(FloatingActionButtonImpl.HOVERED_ENABLED_STATE_SET, createElevationAnimator(f, f2));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, "elevation", new float[]{f}).setDuration(0));
            arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, View.TRANSLATION_Z, new float[]{0.0f}).setDuration(100));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(FloatingActionButtonImpl.ELEVATION_ANIM_INTERPOLATOR);
            stateListAnimator2.addState(FloatingActionButtonImpl.ENABLED_STATE_SET, animatorSet);
            stateListAnimator2.addState(FloatingActionButtonImpl.EMPTY_STATE_SET, createElevationAnimator(0.0f, 0.0f));
            this.stateListAnimator = stateListAnimator2;
            floatingActionButton.setStateListAnimator(stateListAnimator2);
        }
        if (shouldAddPadding()) {
            updatePadding();
        }
    }

    public final boolean shouldAddPadding() {
        if (!FloatingActionButton.this.compatPadding) {
            if (this.ensureMinTouchTargetSize) {
                FloatingActionButton floatingActionButton = this.view;
                if (floatingActionButton.getSizeDimension(floatingActionButton.size) >= this.minTouchTargetSize) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public final void onDrawableStateChanged(int[] iArr) {
    }

    public final void jumpDrawableToCurrentState() {
    }
}
