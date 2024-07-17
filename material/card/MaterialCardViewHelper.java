package com.google.android.material.card;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import androidx.cardview.widget.CardView;
import androidx.cardview.widget.RoundRectDrawable;
import androidx.cardview.widget.RoundRectDrawableWithShadow;
import com.google.android.material.R$styleable;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class MaterialCardViewHelper {
    public static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    public final MaterialShapeDrawable bgDrawable;
    public boolean checkable;
    public Drawable checkedIcon;
    public int checkedIconGravity;
    public int checkedIconMargin;
    public int checkedIconSize;
    public ColorStateList checkedIconTint;
    public LayerDrawable clickableForegroundDrawable;
    public Drawable fgDrawable;
    public final MaterialShapeDrawable foregroundContentDrawable;
    public MaterialShapeDrawable foregroundShapeDrawable;
    public boolean isBackgroundOverwritten = false;
    public final MaterialCardView materialCardView;
    public ColorStateList rippleColor;
    public Drawable rippleDrawable;
    public ShapeAppearanceModel shapeAppearanceModel;
    public ColorStateList strokeColor;
    public int strokeWidth;
    public final Rect userContentPadding = new Rect();

    /* renamed from: com.google.android.material.card.MaterialCardViewHelper$1  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass1 extends InsetDrawable {
        public final int getMinimumHeight() {
            return -1;
        }

        public final int getMinimumWidth() {
            return -1;
        }

        public final boolean getPadding(Rect rect) {
            return false;
        }
    }

    public MaterialCardViewHelper(MaterialCardView materialCardView2, AttributeSet attributeSet) {
        this.materialCardView = materialCardView2;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(materialCardView2.getContext(), attributeSet, 2130969543, 2132018796);
        this.bgDrawable = materialShapeDrawable;
        materialShapeDrawable.initializeElevationOverlay(materialCardView2.getContext());
        materialShapeDrawable.setShadowColor();
        ShapeAppearanceModel.Builder builder = materialShapeDrawable.drawableState.shapeAppearanceModel.toBuilder();
        TypedArray obtainStyledAttributes = materialCardView2.getContext().obtainStyledAttributes(attributeSet, R$styleable.CardView, 2130969543, 2132017483);
        if (obtainStyledAttributes.hasValue(3)) {
            builder.setAllCornerSizes(obtainStyledAttributes.getDimension(3, 0.0f));
        }
        this.foregroundContentDrawable = new MaterialShapeDrawable();
        setShapeAppearanceModel(builder.build());
        obtainStyledAttributes.recycle();
    }

    public static float calculateCornerPaddingForCornerTreatment(CornerTreatment cornerTreatment, float f) {
        if (cornerTreatment instanceof RoundedCornerTreatment) {
            return (float) ((1.0d - COS_45) * ((double) f));
        }
        if (cornerTreatment instanceof CutCornerTreatment) {
            return f / 2.0f;
        }
        return 0.0f;
    }

    public final float calculateActualCornerPadding() {
        CornerTreatment cornerTreatment = this.shapeAppearanceModel.topLeftCorner;
        MaterialShapeDrawable materialShapeDrawable = this.bgDrawable;
        return Math.max(Math.max(calculateCornerPaddingForCornerTreatment(cornerTreatment, materialShapeDrawable.getTopLeftCornerResolvedSize()), calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.topRightCorner, materialShapeDrawable.drawableState.shapeAppearanceModel.topRightCornerSize.getCornerSize(materialShapeDrawable.getBoundsAsRectF$1()))), Math.max(calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.bottomRightCorner, materialShapeDrawable.drawableState.shapeAppearanceModel.bottomRightCornerSize.getCornerSize(materialShapeDrawable.getBoundsAsRectF$1())), calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.bottomLeftCorner, materialShapeDrawable.drawableState.shapeAppearanceModel.bottomLeftCornerSize.getCornerSize(materialShapeDrawable.getBoundsAsRectF$1()))));
    }

    public final Drawable getClickableForeground() {
        if (this.rippleDrawable == null) {
            this.foregroundShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.rippleDrawable = new RippleDrawable(this.rippleColor, (Drawable) null, this.foregroundShapeDrawable);
        }
        if (this.clickableForegroundDrawable == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.rippleDrawable, this.foregroundContentDrawable, this.checkedIcon});
            this.clickableForegroundDrawable = layerDrawable;
            layerDrawable.setId(2, 2131363126);
        }
        return this.clickableForegroundDrawable;
    }

    public final AnonymousClass1 insetDrawable(Drawable drawable) {
        int i;
        int i2;
        float f;
        MaterialCardView materialCardView2 = this.materialCardView;
        if (materialCardView2.mCompatPadding) {
            float f2 = ((RoundRectDrawable) materialCardView2.mCardViewDelegate.mCardBackground).mPadding * 1.5f;
            float f3 = 0.0f;
            if (shouldAddCornerPaddingOutsideCardBackground()) {
                f = calculateActualCornerPadding();
            } else {
                f = 0.0f;
            }
            int ceil = (int) Math.ceil((double) (f2 + f));
            float f4 = ((RoundRectDrawable) materialCardView2.mCardViewDelegate.mCardBackground).mPadding;
            if (shouldAddCornerPaddingOutsideCardBackground()) {
                f3 = calculateActualCornerPadding();
            }
            i2 = (int) Math.ceil((double) (f4 + f3));
            i = ceil;
        } else {
            i2 = 0;
            i = 0;
        }
        return new InsetDrawable(drawable, i2, i, i2, i);
    }

    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel2) {
        this.shapeAppearanceModel = shapeAppearanceModel2;
        MaterialShapeDrawable materialShapeDrawable = this.bgDrawable;
        materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel2);
        materialShapeDrawable.shadowBitmapDrawingEnable = !materialShapeDrawable.isRoundRect();
        MaterialShapeDrawable materialShapeDrawable2 = this.foregroundContentDrawable;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.setShapeAppearanceModel(shapeAppearanceModel2);
        }
        MaterialShapeDrawable materialShapeDrawable3 = this.foregroundShapeDrawable;
        if (materialShapeDrawable3 != null) {
            materialShapeDrawable3.setShapeAppearanceModel(shapeAppearanceModel2);
        }
    }

    public final boolean shouldAddCornerPaddingOutsideCardBackground() {
        MaterialCardView materialCardView2 = this.materialCardView;
        if (!materialCardView2.mPreventCornerOverlap || !this.bgDrawable.isRoundRect() || !materialCardView2.mCompatPadding) {
            return false;
        }
        return true;
    }

    public final void updateContentPadding() {
        boolean z;
        float f;
        MaterialCardView materialCardView2 = this.materialCardView;
        if (!materialCardView2.mPreventCornerOverlap || this.bgDrawable.isRoundRect()) {
            z = false;
        } else {
            z = true;
        }
        float f2 = 0.0f;
        if (z || shouldAddCornerPaddingOutsideCardBackground()) {
            f = calculateActualCornerPadding();
        } else {
            f = 0.0f;
        }
        if (materialCardView2.mPreventCornerOverlap && materialCardView2.mCompatPadding) {
            f2 = (float) ((1.0d - COS_45) * ((double) ((RoundRectDrawable) materialCardView2.mCardViewDelegate.mCardBackground).mRadius));
        }
        int i = (int) (f - f2);
        Rect rect = this.userContentPadding;
        materialCardView2.mContentPadding.set(rect.left + i, rect.top + i, rect.right + i, rect.bottom + i);
        CardView.AnonymousClass1 r7 = materialCardView2.mCardViewDelegate;
        CardView cardView = CardView.this;
        if (!cardView.mCompatPadding) {
            r7.setShadowPadding(0, 0, 0, 0);
            return;
        }
        RoundRectDrawable roundRectDrawable = (RoundRectDrawable) r7.mCardBackground;
        float f3 = roundRectDrawable.mPadding;
        float f4 = roundRectDrawable.mRadius;
        int ceil = (int) Math.ceil((double) RoundRectDrawableWithShadow.calculateHorizontalPadding(f3, f4, cardView.mPreventCornerOverlap));
        int ceil2 = (int) Math.ceil((double) RoundRectDrawableWithShadow.calculateVerticalPadding(f3, f4, CardView.this.mPreventCornerOverlap));
        r7.setShadowPadding(ceil, ceil2, ceil, ceil2);
    }
}
