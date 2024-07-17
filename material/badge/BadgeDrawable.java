package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BadgeDrawable extends Drawable implements TextDrawableHelper.TextDrawableDelegate {
    public WeakReference anchorViewRef;
    public final Rect badgeBounds = new Rect();
    public float badgeCenterX;
    public float badgeCenterY;
    public final WeakReference contextRef;
    public float cornerRadius;
    public WeakReference customBadgeParentRef;
    public float halfBadgeHeight;
    public float halfBadgeWidth;
    public final int maxBadgeNumber;
    public final MaterialShapeDrawable shapeDrawable;
    public final BadgeState state;
    public final TextDrawableHelper textDrawableHelper;

    public BadgeDrawable(Context context, BadgeState.State state2) {
        FrameLayout frameLayout;
        TextAppearance textAppearance;
        Context context2;
        WeakReference weakReference = new WeakReference(context);
        this.contextRef = weakReference;
        ThemeEnforcement.checkTheme(context, ThemeEnforcement.MATERIAL_CHECK_ATTRS, "Theme.MaterialComponents");
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.shapeDrawable = materialShapeDrawable;
        TextDrawableHelper textDrawableHelper2 = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper2;
        TextPaint textPaint = textDrawableHelper2.textPaint;
        textPaint.setTextAlign(Paint.Align.CENTER);
        Context context3 = (Context) weakReference.get();
        if (!(context3 == null || textDrawableHelper2.textAppearance == (textAppearance = new TextAppearance(context3, 2132018069)) || (context2 = (Context) weakReference.get()) == null)) {
            textDrawableHelper2.setTextAppearance(textAppearance, context2);
            updateCenterAndBounds();
        }
        BadgeState badgeState = new BadgeState(context, state2);
        this.state = badgeState;
        BadgeState.State state3 = badgeState.currentState;
        this.maxBadgeNumber = ((int) Math.pow(10.0d, ((double) state3.maxCharacterCount) - 1.0d)) - 1;
        textDrawableHelper2.textWidthDirty = true;
        updateCenterAndBounds();
        invalidateSelf();
        textDrawableHelper2.textWidthDirty = true;
        updateCenterAndBounds();
        invalidateSelf();
        textPaint.setAlpha(getAlpha());
        invalidateSelf();
        ColorStateList valueOf = ColorStateList.valueOf(state3.backgroundColor.intValue());
        if (materialShapeDrawable.drawableState.fillColor != valueOf) {
            materialShapeDrawable.setFillColor(valueOf);
            invalidateSelf();
        }
        textPaint.setColor(state3.badgeTextColor.intValue());
        invalidateSelf();
        WeakReference weakReference2 = this.anchorViewRef;
        if (!(weakReference2 == null || weakReference2.get() == null)) {
            View view = (View) this.anchorViewRef.get();
            WeakReference weakReference3 = this.customBadgeParentRef;
            if (weakReference3 != null) {
                frameLayout = (FrameLayout) weakReference3.get();
            } else {
                frameLayout = null;
            }
            updateBadgeCoordinates(view, frameLayout);
        }
        updateCenterAndBounds();
        setVisible(state3.isVisible.booleanValue(), false);
    }

    public final void draw(Canvas canvas) {
        if (!getBounds().isEmpty() && getAlpha() != 0 && isVisible()) {
            this.shapeDrawable.draw(canvas);
            if (hasNumber()) {
                Rect rect = new Rect();
                String badgeText = getBadgeText();
                this.textDrawableHelper.textPaint.getTextBounds(badgeText, 0, badgeText.length(), rect);
                canvas.drawText(badgeText, this.badgeCenterX, this.badgeCenterY + ((float) (rect.height() / 2)), this.textDrawableHelper.textPaint);
            }
        }
    }

    public final int getAlpha() {
        return this.state.currentState.alpha;
    }

    public final String getBadgeText() {
        if (getNumber() <= this.maxBadgeNumber) {
            return NumberFormat.getInstance(this.state.currentState.numberLocale).format((long) getNumber());
        }
        Context context = (Context) this.contextRef.get();
        if (context == null) {
            return "";
        }
        return String.format(this.state.currentState.numberLocale, context.getString(2131953252), new Object[]{Integer.valueOf(this.maxBadgeNumber), "+"});
    }

    public final int getIntrinsicHeight() {
        return this.badgeBounds.height();
    }

    public final int getIntrinsicWidth() {
        return this.badgeBounds.width();
    }

    public final int getNumber() {
        if (hasNumber()) {
            return this.state.currentState.number;
        }
        return 0;
    }

    public final int getOpacity() {
        return -3;
    }

    public final boolean hasNumber() {
        if (this.state.currentState.number != -1) {
            return true;
        }
        return false;
    }

    public final boolean isStateful() {
        return false;
    }

    public final boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public final void onTextSizeChange() {
        invalidateSelf();
    }

    public final void setAlpha(int i) {
        BadgeState badgeState = this.state;
        badgeState.overridingState.alpha = i;
        badgeState.currentState.alpha = i;
        this.textDrawableHelper.textPaint.setAlpha(getAlpha());
        invalidateSelf();
    }

    public final void updateBadgeCoordinates(View view, FrameLayout frameLayout) {
        this.anchorViewRef = new WeakReference(view);
        this.customBadgeParentRef = new WeakReference(frameLayout);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
        updateCenterAndBounds();
        invalidateSelf();
    }

    public final void updateCenterAndBounds() {
        View view;
        int i;
        int i2;
        int i3;
        float f;
        float f2;
        float f3;
        Context context = (Context) this.contextRef.get();
        WeakReference weakReference = this.anchorViewRef;
        ViewGroup viewGroup = null;
        if (weakReference != null) {
            view = (View) weakReference.get();
        } else {
            view = null;
        }
        if (context != null && view != null) {
            Rect rect = new Rect();
            rect.set(this.badgeBounds);
            Rect rect2 = new Rect();
            view.getDrawingRect(rect2);
            WeakReference weakReference2 = this.customBadgeParentRef;
            if (weakReference2 != null) {
                viewGroup = (ViewGroup) weakReference2.get();
            }
            if (viewGroup != null) {
                viewGroup.offsetDescendantRectToMyCoords(view, rect2);
            }
            if (hasNumber()) {
                i = this.state.currentState.verticalOffsetWithText.intValue();
            } else {
                i = this.state.currentState.verticalOffsetWithoutText.intValue();
            }
            int intValue = this.state.currentState.additionalVerticalOffset.intValue() + i;
            int intValue2 = this.state.currentState.badgeGravity.intValue();
            if (intValue2 == 8388691 || intValue2 == 8388693) {
                this.badgeCenterY = (float) (rect2.bottom - intValue);
            } else {
                this.badgeCenterY = (float) (rect2.top + intValue);
            }
            if (getNumber() <= 9) {
                if (!hasNumber()) {
                    f3 = this.state.badgeRadius;
                } else {
                    f3 = this.state.badgeWithTextRadius;
                }
                this.cornerRadius = f3;
                this.halfBadgeHeight = f3;
                this.halfBadgeWidth = f3;
            } else {
                float f4 = this.state.badgeWithTextRadius;
                this.cornerRadius = f4;
                this.halfBadgeHeight = f4;
                this.halfBadgeWidth = (this.textDrawableHelper.getTextWidth(getBadgeText()) / 2.0f) + this.state.badgeWidePadding;
            }
            Resources resources = context.getResources();
            if (hasNumber()) {
                i2 = 2131166728;
            } else {
                i2 = 2131166725;
            }
            int dimensionPixelSize = resources.getDimensionPixelSize(i2);
            if (hasNumber()) {
                i3 = this.state.currentState.horizontalOffsetWithText.intValue();
            } else {
                i3 = this.state.currentState.horizontalOffsetWithoutText.intValue();
            }
            int intValue3 = this.state.currentState.additionalHorizontalOffset.intValue() + i3;
            int intValue4 = this.state.currentState.badgeGravity.intValue();
            if (intValue4 == 8388659 || intValue4 == 8388691) {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                if (ViewCompat.Api17Impl.getLayoutDirection(view) == 0) {
                    f = (((float) rect2.left) - this.halfBadgeWidth) + ((float) dimensionPixelSize) + ((float) intValue3);
                } else {
                    f = ((((float) rect2.right) + this.halfBadgeWidth) - ((float) dimensionPixelSize)) - ((float) intValue3);
                }
                this.badgeCenterX = f;
            } else {
                WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                if (ViewCompat.Api17Impl.getLayoutDirection(view) == 0) {
                    f2 = ((((float) rect2.right) + this.halfBadgeWidth) - ((float) dimensionPixelSize)) - ((float) intValue3);
                } else {
                    f2 = (((float) rect2.left) - this.halfBadgeWidth) + ((float) dimensionPixelSize) + ((float) intValue3);
                }
                this.badgeCenterX = f2;
            }
            Rect rect3 = this.badgeBounds;
            float f5 = this.badgeCenterX;
            float f6 = this.badgeCenterY;
            float f7 = this.halfBadgeWidth;
            float f8 = this.halfBadgeHeight;
            rect3.set((int) (f5 - f7), (int) (f6 - f8), (int) (f5 + f7), (int) (f6 + f8));
            MaterialShapeDrawable materialShapeDrawable = this.shapeDrawable;
            float f9 = this.cornerRadius;
            ShapeAppearanceModel.Builder builder = materialShapeDrawable.drawableState.shapeAppearanceModel.toBuilder();
            builder.setAllCornerSizes(f9);
            materialShapeDrawable.setShapeAppearanceModel(builder.build());
            if (!rect.equals(this.badgeBounds)) {
                this.shapeDrawable.setBounds(this.badgeBounds);
            }
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
