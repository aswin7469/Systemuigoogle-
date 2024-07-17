package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class Snackbar$SnackbarLayout extends FrameLayout {
    public static final BaseTransientBottomBar$SnackbarBaseLayout$1 consumeAllTouchListener = new Object();
    public ColorStateList backgroundTint;
    public PorterDuff.Mode backgroundTintMode;
    public final int maxWidth;
    public final ShapeAppearanceModel shapeAppearanceModel;

    public Snackbar$SnackbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api20Impl.requestApplyInsets(this);
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public final void onMeasure(int i, int i2) {
        onMeasure$com$google$android$material$snackbar$BaseTransientBottomBar$SnackbarBaseLayout(i, i2);
        int childCount = getChildCount();
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getLayoutParams().width == -1) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(childAt.getMeasuredHeight(), 1073741824));
            }
        }
    }

    public final void onMeasure$com$google$android$material$snackbar$BaseTransientBottomBar$SnackbarBaseLayout(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        if (this.maxWidth > 0 && getMeasuredWidth() > (i3 = this.maxWidth)) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
        }
    }

    public final void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        if (!(drawable == null || this.backgroundTint == null)) {
            drawable = drawable.mutate();
            drawable.setTintList(this.backgroundTint);
            drawable.setTintMode(this.backgroundTintMode);
        }
        super.setBackgroundDrawable(drawable);
    }

    public final void setBackgroundTintList(ColorStateList colorStateList) {
        this.backgroundTint = colorStateList;
        if (getBackground() != null) {
            Drawable mutate = getBackground().mutate();
            mutate.setTintList(colorStateList);
            mutate.setTintMode(this.backgroundTintMode);
            if (mutate != getBackground()) {
                super.setBackgroundDrawable(mutate);
            }
        }
    }

    public final void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.backgroundTintMode = mode;
        if (getBackground() != null) {
            Drawable mutate = getBackground().mutate();
            mutate.setTintMode(mode);
            if (mutate != getBackground()) {
                super.setBackgroundDrawable(mutate);
            }
        }
    }

    public final void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        BaseTransientBottomBar$SnackbarBaseLayout$1 baseTransientBottomBar$SnackbarBaseLayout$1;
        if (onClickListener != null) {
            baseTransientBottomBar$SnackbarBaseLayout$1 = null;
        } else {
            baseTransientBottomBar$SnackbarBaseLayout$1 = consumeAllTouchListener;
        }
        setOnTouchListener(baseTransientBottomBar$SnackbarBaseLayout$1);
        super.setOnClickListener(onClickListener);
    }

    public Snackbar$SnackbarLayout(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: com.google.android.material.shape.MaterialShapeDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Snackbar$SnackbarLayout(android.content.Context r5, android.util.AttributeSet r6, int r7) {
        /*
            r4 = this;
            r7 = 0
            android.content.Context r5 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r5, r6, r7, r7)
            r4.<init>(r5, r6)
            android.content.Context r5 = r4.getContext()
            int[] r0 = com.google.android.material.R$styleable.SnackbarLayout
            android.content.res.TypedArray r0 = r5.obtainStyledAttributes(r6, r0)
            r1 = 6
            boolean r2 = r0.hasValue(r1)
            if (r2 == 0) goto L_0x0023
            int r1 = r0.getDimensionPixelSize(r1, r7)
            float r1 = (float) r1
            java.util.WeakHashMap r2 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api21Impl.setElevation(r4, r1)
        L_0x0023:
            r1 = 2
            r0.getInt(r1, r7)
            r1 = 8
            boolean r1 = r0.hasValue(r1)
            if (r1 != 0) goto L_0x0037
            r1 = 9
            boolean r1 = r0.hasValue(r1)
            if (r1 == 0) goto L_0x0041
        L_0x0037:
            com.google.android.material.shape.ShapeAppearanceModel$Builder r6 = com.google.android.material.shape.ShapeAppearanceModel.builder((android.content.Context) r5, (android.util.AttributeSet) r6, (int) r7, (int) r7)
            com.google.android.material.shape.ShapeAppearanceModel r6 = r6.build()
            r4.shapeAppearanceModel = r6
        L_0x0041:
            r6 = 3
            r1 = 1065353216(0x3f800000, float:1.0)
            float r6 = r0.getFloat(r6, r1)
            r2 = 4
            android.content.res.ColorStateList r5 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r5, (android.content.res.TypedArray) r0, (int) r2)
            r4.setBackgroundTintList(r5)
            r5 = 5
            r2 = -1
            int r5 = r0.getInt(r5, r2)
            android.graphics.PorterDuff$Mode r3 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r5 = com.google.android.material.internal.ViewUtils.parseTintMode(r5, r3)
            r4.setBackgroundTintMode(r5)
            r5 = 1
            r0.getFloat(r5, r1)
            int r1 = r0.getDimensionPixelSize(r7, r2)
            r4.maxWidth = r1
            r1 = 7
            r0.getDimensionPixelSize(r1, r2)
            r0.recycle()
            com.google.android.material.snackbar.BaseTransientBottomBar$SnackbarBaseLayout$1 r0 = consumeAllTouchListener
            r4.setOnTouchListener(r0)
            r4.setFocusable(r5)
            android.graphics.drawable.Drawable r5 = r4.getBackground()
            if (r5 != 0) goto L_0x00cb
            r5 = 2130968887(0x7f040137, float:1.754644E38)
            int r5 = com.google.android.material.color.MaterialColors.getColor(r4, r5)
            r0 = 2130968872(0x7f040128, float:1.754641E38)
            int r0 = com.google.android.material.color.MaterialColors.getColor(r4, r0)
            int r5 = com.google.android.material.color.MaterialColors.layer(r5, r6, r0)
            com.google.android.material.shape.ShapeAppearanceModel r6 = r4.shapeAppearanceModel
            if (r6 == 0) goto L_0x00a3
            int r7 = com.google.android.material.snackbar.BaseTransientBottomBar.$r8$clinit
            com.google.android.material.shape.MaterialShapeDrawable r7 = new com.google.android.material.shape.MaterialShapeDrawable
            r7.<init>((com.google.android.material.shape.ShapeAppearanceModel) r6)
            android.content.res.ColorStateList r5 = android.content.res.ColorStateList.valueOf(r5)
            r7.setFillColor(r5)
            goto L_0x00bf
        L_0x00a3:
            android.content.res.Resources r6 = r4.getResources()
            int r0 = com.google.android.material.snackbar.BaseTransientBottomBar.$r8$clinit
            r0 = 2131166931(0x7f0706d3, float:1.7948121E38)
            float r6 = r6.getDimension(r0)
            android.graphics.drawable.GradientDrawable r0 = new android.graphics.drawable.GradientDrawable
            r0.<init>()
            r0.setShape(r7)
            r0.setCornerRadius(r6)
            r0.setColor(r5)
            r7 = r0
        L_0x00bf:
            android.content.res.ColorStateList r5 = r4.backgroundTint
            if (r5 == 0) goto L_0x00c6
            r7.setTintList(r5)
        L_0x00c6:
            java.util.WeakHashMap r5 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            r4.setBackgroundDrawable(r7)
        L_0x00cb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.snackbar.Snackbar$SnackbarLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }
}
