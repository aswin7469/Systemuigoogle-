package com.google.android.material.card;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import androidx.cardview.widget.CardView;
import androidx.cardview.widget.RoundRectDrawable;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class MaterialCardView extends CardView implements Checkable, Shapeable {
    public static final int[] CHECKABLE_STATE_SET = {16842911};
    public static final int[] CHECKED_STATE_SET = {16842912};
    public final MaterialCardViewHelper cardViewHelper;
    public boolean checked;
    public final boolean isParentCardViewDoneInitializing;

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final boolean isChecked() {
        return this.checked;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.cardViewHelper.bgDrawable);
    }

    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 3);
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        if (materialCardViewHelper != null && materialCardViewHelper.checkable) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        if (this.checked) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent.setChecked(this.checked);
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean z;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.cardview.widget.CardView");
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        if (materialCardViewHelper == null || !materialCardViewHelper.checkable) {
            z = false;
        } else {
            z = true;
        }
        accessibilityNodeInfo.setCheckable(z);
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(this.checked);
    }

    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        float f;
        super.onMeasure(i, i2);
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (materialCardViewHelper.clickableForegroundDrawable != null) {
            MaterialCardView materialCardView = materialCardViewHelper.materialCardView;
            if (materialCardView.mCompatPadding) {
                float f2 = ((RoundRectDrawable) materialCardView.mCardViewDelegate.mCardBackground).mPadding * 1.5f;
                float f3 = 0.0f;
                if (materialCardViewHelper.shouldAddCornerPaddingOutsideCardBackground()) {
                    f = materialCardViewHelper.calculateActualCornerPadding();
                } else {
                    f = 0.0f;
                }
                i4 = (int) Math.ceil((double) ((f2 + f) * 2.0f));
                float f4 = ((RoundRectDrawable) materialCardView.mCardViewDelegate.mCardBackground).mPadding;
                if (materialCardViewHelper.shouldAddCornerPaddingOutsideCardBackground()) {
                    f3 = materialCardViewHelper.calculateActualCornerPadding();
                }
                i3 = (int) Math.ceil((double) ((f4 + f3) * 2.0f));
            } else {
                i4 = 0;
                i3 = 0;
            }
            int i11 = materialCardViewHelper.checkedIconGravity;
            if ((i11 & 8388613) == 8388613) {
                i5 = ((measuredWidth - materialCardViewHelper.checkedIconMargin) - materialCardViewHelper.checkedIconSize) - i3;
            } else {
                i5 = materialCardViewHelper.checkedIconMargin;
            }
            if ((i11 & 80) == 80) {
                i6 = materialCardViewHelper.checkedIconMargin;
            } else {
                i6 = ((measuredHeight - materialCardViewHelper.checkedIconMargin) - materialCardViewHelper.checkedIconSize) - i4;
            }
            int i12 = i6;
            if ((i11 & 8388613) == 8388613) {
                i7 = materialCardViewHelper.checkedIconMargin;
            } else {
                i7 = ((measuredWidth - materialCardViewHelper.checkedIconMargin) - materialCardViewHelper.checkedIconSize) - i3;
            }
            if ((i11 & 80) == 80) {
                i8 = ((measuredHeight - materialCardViewHelper.checkedIconMargin) - materialCardViewHelper.checkedIconSize) - i4;
            } else {
                i8 = materialCardViewHelper.checkedIconMargin;
            }
            int i13 = i8;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            if (ViewCompat.Api17Impl.getLayoutDirection(materialCardView) == 1) {
                i10 = i7;
                i9 = i5;
            } else {
                i9 = i7;
                i10 = i5;
            }
            materialCardViewHelper.clickableForegroundDrawable.setLayerInset(2, i10, i13, i9, i12);
        }
    }

    public final void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        if (this.isParentCardViewDoneInitializing) {
            if (!this.cardViewHelper.isBackgroundOverwritten) {
                Log.i("MaterialCardView", "Setting a custom background is not supported.");
                this.cardViewHelper.isBackgroundOverwritten = true;
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    public final void setChecked(boolean z) {
        if (this.checked != z) {
            toggle();
        }
    }

    public final void setClickable(boolean z) {
        Drawable drawable;
        super.setClickable(z);
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        if (materialCardViewHelper != null) {
            Drawable drawable2 = materialCardViewHelper.fgDrawable;
            MaterialCardView materialCardView = materialCardViewHelper.materialCardView;
            if (materialCardView.isClickable()) {
                drawable = materialCardViewHelper.getClickableForeground();
            } else {
                drawable = materialCardViewHelper.foregroundContentDrawable;
            }
            materialCardViewHelper.fgDrawable = drawable;
            if (drawable2 == drawable) {
                return;
            }
            if (materialCardView.getForeground() instanceof InsetDrawable) {
                ((InsetDrawable) materialCardView.getForeground()).setDrawable(drawable);
            } else {
                materialCardView.setForeground(materialCardViewHelper.insetDrawable(drawable));
            }
        }
    }

    public final void setRadius(float f) {
        super.setRadius(f);
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        ShapeAppearanceModel.Builder builder = materialCardViewHelper.shapeAppearanceModel.toBuilder();
        builder.setAllCornerSizes(f);
        materialCardViewHelper.setShapeAppearanceModel(builder.build());
        materialCardViewHelper.fgDrawable.invalidateSelf();
        boolean shouldAddCornerPaddingOutsideCardBackground = materialCardViewHelper.shouldAddCornerPaddingOutsideCardBackground();
        MaterialShapeDrawable materialShapeDrawable = materialCardViewHelper.bgDrawable;
        MaterialCardView materialCardView = materialCardViewHelper.materialCardView;
        if (shouldAddCornerPaddingOutsideCardBackground || (materialCardView.mPreventCornerOverlap && !materialShapeDrawable.isRoundRect())) {
            materialCardViewHelper.updateContentPadding();
        }
        if (materialCardViewHelper.shouldAddCornerPaddingOutsideCardBackground()) {
            if (!materialCardViewHelper.isBackgroundOverwritten) {
                super.setBackgroundDrawable(materialCardViewHelper.insetDrawable(materialShapeDrawable));
            }
            materialCardView.setForeground(materialCardViewHelper.insetDrawable(materialCardViewHelper.fgDrawable));
        }
    }

    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        RectF rectF = new RectF();
        rectF.set(this.cardViewHelper.bgDrawable.getBounds());
        setClipToOutline(shapeAppearanceModel.isRoundRect(rectF));
        this.cardViewHelper.setShapeAppearanceModel(shapeAppearanceModel);
    }

    public final void toggle() {
        int i;
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        if (materialCardViewHelper != null && materialCardViewHelper.checkable && isEnabled()) {
            this.checked = !this.checked;
            refreshDrawableState();
            MaterialCardViewHelper materialCardViewHelper2 = this.cardViewHelper;
            Drawable drawable = materialCardViewHelper2.rippleDrawable;
            if (drawable != null) {
                Rect bounds = drawable.getBounds();
                int i2 = bounds.bottom;
                materialCardViewHelper2.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, i2 - 1);
                materialCardViewHelper2.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, i2);
            }
            MaterialCardViewHelper materialCardViewHelper3 = this.cardViewHelper;
            boolean z = this.checked;
            Drawable drawable2 = materialCardViewHelper3.checkedIcon;
            if (drawable2 != null) {
                if (z) {
                    i = 255;
                } else {
                    i = 0;
                }
                drawable2.setAlpha(i);
            }
        }
    }

    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 2130969543, 2132018796), attributeSet, 2130969543);
        this.checked = false;
        this.isParentCardViewDoneInitializing = true;
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(getContext(), attributeSet, R$styleable.MaterialCardView, 2130969543, 2132018796, new int[0]);
        MaterialCardViewHelper materialCardViewHelper = new MaterialCardViewHelper(this, attributeSet);
        this.cardViewHelper = materialCardViewHelper;
        ColorStateList colorStateList = ((RoundRectDrawable) this.mCardViewDelegate.mCardBackground).mBackground;
        MaterialShapeDrawable materialShapeDrawable = materialCardViewHelper.bgDrawable;
        materialShapeDrawable.setFillColor(colorStateList);
        Rect rect = this.mContentPadding;
        materialCardViewHelper.userContentPadding.set(rect.left, rect.top, rect.right, rect.bottom);
        materialCardViewHelper.updateContentPadding();
        MaterialCardView materialCardView = materialCardViewHelper.materialCardView;
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(materialCardView.getContext(), obtainStyledAttributes, 11);
        materialCardViewHelper.strokeColor = colorStateList2;
        if (colorStateList2 == null) {
            materialCardViewHelper.strokeColor = ColorStateList.valueOf(-1);
        }
        materialCardViewHelper.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(12, 0);
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        materialCardViewHelper.checkable = z;
        materialCardView.setLongClickable(z);
        materialCardViewHelper.checkedIconTint = MaterialResources.getColorStateList(materialCardView.getContext(), obtainStyledAttributes, 6);
        Drawable drawable = MaterialResources.getDrawable(materialCardView.getContext(), obtainStyledAttributes, 2);
        if (drawable != null) {
            Drawable mutate = drawable.mutate();
            materialCardViewHelper.checkedIcon = mutate;
            mutate.setTintList(materialCardViewHelper.checkedIconTint);
            boolean z2 = materialCardView.checked;
            Drawable drawable2 = materialCardViewHelper.checkedIcon;
            if (drawable2 != null) {
                drawable2.setAlpha(z2 ? 255 : 0);
            }
        } else {
            materialCardViewHelper.checkedIcon = null;
        }
        LayerDrawable layerDrawable = materialCardViewHelper.clickableForegroundDrawable;
        if (layerDrawable != null) {
            layerDrawable.setDrawableByLayerId(2131363126, materialCardViewHelper.checkedIcon);
        }
        materialCardViewHelper.checkedIconSize = obtainStyledAttributes.getDimensionPixelSize(5, 0);
        materialCardViewHelper.checkedIconMargin = obtainStyledAttributes.getDimensionPixelSize(4, 0);
        materialCardViewHelper.checkedIconGravity = obtainStyledAttributes.getInteger(3, 8388661);
        ColorStateList colorStateList3 = MaterialResources.getColorStateList(materialCardView.getContext(), obtainStyledAttributes, 7);
        materialCardViewHelper.rippleColor = colorStateList3;
        if (colorStateList3 == null) {
            materialCardViewHelper.rippleColor = ColorStateList.valueOf(MaterialColors.getColor(materialCardView, 2130968859));
        }
        ColorStateList colorStateList4 = MaterialResources.getColorStateList(materialCardView.getContext(), obtainStyledAttributes, 1);
        colorStateList4 = colorStateList4 == null ? ColorStateList.valueOf(0) : colorStateList4;
        MaterialShapeDrawable materialShapeDrawable2 = materialCardViewHelper.foregroundContentDrawable;
        materialShapeDrawable2.setFillColor(colorStateList4);
        Drawable drawable3 = materialCardViewHelper.rippleDrawable;
        if (drawable3 != null) {
            ((RippleDrawable) drawable3).setColor(materialCardViewHelper.rippleColor);
        }
        materialShapeDrawable.setElevation(CardView.this.getElevation());
        ColorStateList colorStateList5 = materialCardViewHelper.strokeColor;
        materialShapeDrawable2.drawableState.strokeWidth = (float) materialCardViewHelper.strokeWidth;
        materialShapeDrawable2.invalidateSelf();
        materialShapeDrawable2.setStrokeColor(colorStateList5);
        super.setBackgroundDrawable(materialCardViewHelper.insetDrawable(materialShapeDrawable));
        Drawable clickableForeground = materialCardView.isClickable() ? materialCardViewHelper.getClickableForeground() : materialShapeDrawable2;
        materialCardViewHelper.fgDrawable = clickableForeground;
        materialCardView.setForeground(materialCardViewHelper.insetDrawable(clickableForeground));
        obtainStyledAttributes.recycle();
    }
}
