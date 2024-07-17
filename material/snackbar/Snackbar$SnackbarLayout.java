package com.google.android.material.snackbar;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class Snackbar$SnackbarLayout extends FrameLayout {
    public static final BaseTransientBottomBar$SnackbarBaseLayout$1 consumeAllTouchListener = new Object();
    public ColorStateList backgroundTint;
    public PorterDuff.Mode backgroundTintMode;
    public final int maxWidth;
    public final ShapeAppearanceModel shapeAppearanceModel;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: com.google.android.material.shape.MaterialShapeDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Snackbar$SnackbarLayout(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            android.content.Context r7 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r7, r0, r1, r1)
            r6.<init>(r7, r0)
            android.content.Context r7 = r6.getContext()
            int[] r2 = com.google.android.material.R$styleable.SnackbarLayout
            android.content.res.TypedArray r2 = r7.obtainStyledAttributes(r0, r2)
            r3 = 6
            boolean r4 = r2.hasValue(r3)
            if (r4 == 0) goto L_0x0024
            int r3 = r2.getDimensionPixelSize(r3, r1)
            float r3 = (float) r3
            java.util.WeakHashMap r4 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api21Impl.setElevation(r6, r3)
        L_0x0024:
            r3 = 2
            r2.getInt(r3, r1)
            r3 = 8
            boolean r3 = r2.hasValue(r3)
            if (r3 != 0) goto L_0x0038
            r3 = 9
            boolean r3 = r2.hasValue(r3)
            if (r3 == 0) goto L_0x0042
        L_0x0038:
            com.google.android.material.shape.ShapeAppearanceModel$Builder r0 = com.google.android.material.shape.ShapeAppearanceModel.builder((android.content.Context) r7, (android.util.AttributeSet) r0, (int) r1, (int) r1)
            com.google.android.material.shape.ShapeAppearanceModel r0 = r0.build()
            r6.shapeAppearanceModel = r0
        L_0x0042:
            r0 = 3
            r3 = 1065353216(0x3f800000, float:1.0)
            float r0 = r2.getFloat(r0, r3)
            r4 = 4
            android.content.res.ColorStateList r7 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r7, (android.content.res.TypedArray) r2, (int) r4)
            r6.setBackgroundTintList(r7)
            r7 = 5
            r4 = -1
            int r7 = r2.getInt(r7, r4)
            android.graphics.PorterDuff$Mode r5 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r7 = com.google.android.material.internal.ViewUtils.parseTintMode(r7, r5)
            r6.setBackgroundTintMode(r7)
            r7 = 1
            r2.getFloat(r7, r3)
            int r3 = r2.getDimensionPixelSize(r1, r4)
            r6.maxWidth = r3
            r3 = 7
            r2.getDimensionPixelSize(r3, r4)
            r2.recycle()
            com.google.android.material.snackbar.BaseTransientBottomBar$SnackbarBaseLayout$1 r2 = consumeAllTouchListener
            r6.setOnTouchListener(r2)
            r6.setFocusable(r7)
            android.graphics.drawable.Drawable r7 = r6.getBackground()
            if (r7 != 0) goto L_0x00cc
            r7 = 2130968887(0x7f040137, float:1.754644E38)
            int r7 = com.google.android.material.color.MaterialColors.getColor(r6, r7)
            r2 = 2130968872(0x7f040128, float:1.754641E38)
            int r2 = com.google.android.material.color.MaterialColors.getColor(r6, r2)
            int r7 = com.google.android.material.color.MaterialColors.layer(r7, r0, r2)
            com.google.android.material.shape.ShapeAppearanceModel r0 = r6.shapeAppearanceModel
            if (r0 == 0) goto L_0x00a4
            int r1 = com.google.android.material.snackbar.BaseTransientBottomBar.$r8$clinit
            com.google.android.material.shape.MaterialShapeDrawable r1 = new com.google.android.material.shape.MaterialShapeDrawable
            r1.<init>((com.google.android.material.shape.ShapeAppearanceModel) r0)
            android.content.res.ColorStateList r7 = android.content.res.ColorStateList.valueOf(r7)
            r1.setFillColor(r7)
            goto L_0x00c0
        L_0x00a4:
            android.content.res.Resources r0 = r6.getResources()
            int r2 = com.google.android.material.snackbar.BaseTransientBottomBar.$r8$clinit
            r2 = 2131166889(0x7f0706a9, float:1.7948036E38)
            float r0 = r0.getDimension(r2)
            android.graphics.drawable.GradientDrawable r2 = new android.graphics.drawable.GradientDrawable
            r2.<init>()
            r2.setShape(r1)
            r2.setCornerRadius(r0)
            r2.setColor(r7)
            r1 = r2
        L_0x00c0:
            android.content.res.ColorStateList r7 = r6.backgroundTint
            if (r7 == 0) goto L_0x00c7
            r1.setTintList(r7)
        L_0x00c7:
            java.util.WeakHashMap r7 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api16Impl.setBackground(r6, r1)
        L_0x00cc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.snackbar.Snackbar$SnackbarLayout.<init>(android.content.Context):void");
    }

    /* renamed from: onAttachedToWindow$com$google$android$material$snackbar$BaseTransientBottomBar$SnackbarBaseLayout */
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

    /* renamed from: setBackgroundDrawable$com$google$android$material$snackbar$BaseTransientBottomBar$SnackbarBaseLayout */
    public final void setBackgroundDrawable(Drawable drawable) {
        if (!(drawable == null || this.backgroundTint == null)) {
            drawable = drawable.mutate();
            drawable.setTintList(this.backgroundTint);
            drawable.setTintMode(this.backgroundTintMode);
        }
        super.setBackgroundDrawable(drawable);
    }

    /* renamed from: setBackgroundTintList$com$google$android$material$snackbar$BaseTransientBottomBar$SnackbarBaseLayout */
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

    /* renamed from: setBackgroundTintMode$com$google$android$material$snackbar$BaseTransientBottomBar$SnackbarBaseLayout */
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

    /* renamed from: setLayoutParams$com$google$android$material$snackbar$BaseTransientBottomBar$SnackbarBaseLayout */
    public final void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }
    }

    /* renamed from: setOnClickListener$com$google$android$material$snackbar$BaseTransientBottomBar$SnackbarBaseLayout */
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.google.android.material.shape.MaterialShapeDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Snackbar$SnackbarLayout(android.content.Context r6, android.util.AttributeSet r7) {
        /*
            r5 = this;
            r0 = 0
            android.content.Context r6 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r6, r7, r0, r0)
            r5.<init>(r6, r7)
            android.content.Context r6 = r5.getContext()
            int[] r1 = com.google.android.material.R$styleable.SnackbarLayout
            android.content.res.TypedArray r1 = r6.obtainStyledAttributes(r7, r1)
            r2 = 6
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x0023
            int r2 = r1.getDimensionPixelSize(r2, r0)
            float r2 = (float) r2
            java.util.WeakHashMap r3 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api21Impl.setElevation(r5, r2)
        L_0x0023:
            r2 = 2
            r1.getInt(r2, r0)
            r2 = 8
            boolean r2 = r1.hasValue(r2)
            if (r2 != 0) goto L_0x0037
            r2 = 9
            boolean r2 = r1.hasValue(r2)
            if (r2 == 0) goto L_0x0041
        L_0x0037:
            com.google.android.material.shape.ShapeAppearanceModel$Builder r7 = com.google.android.material.shape.ShapeAppearanceModel.builder((android.content.Context) r6, (android.util.AttributeSet) r7, (int) r0, (int) r0)
            com.google.android.material.shape.ShapeAppearanceModel r7 = r7.build()
            r5.shapeAppearanceModel = r7
        L_0x0041:
            r7 = 3
            r2 = 1065353216(0x3f800000, float:1.0)
            float r7 = r1.getFloat(r7, r2)
            r3 = 4
            android.content.res.ColorStateList r6 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r6, (android.content.res.TypedArray) r1, (int) r3)
            r5.setBackgroundTintList(r6)
            r6 = 5
            r3 = -1
            int r6 = r1.getInt(r6, r3)
            android.graphics.PorterDuff$Mode r4 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r6 = com.google.android.material.internal.ViewUtils.parseTintMode(r6, r4)
            r5.setBackgroundTintMode(r6)
            r6 = 1
            r1.getFloat(r6, r2)
            int r2 = r1.getDimensionPixelSize(r0, r3)
            r5.maxWidth = r2
            r2 = 7
            r1.getDimensionPixelSize(r2, r3)
            r1.recycle()
            com.google.android.material.snackbar.BaseTransientBottomBar$SnackbarBaseLayout$1 r1 = consumeAllTouchListener
            r5.setOnTouchListener(r1)
            r5.setFocusable(r6)
            android.graphics.drawable.Drawable r6 = r5.getBackground()
            if (r6 != 0) goto L_0x00cb
            r6 = 2130968887(0x7f040137, float:1.754644E38)
            int r6 = com.google.android.material.color.MaterialColors.getColor(r5, r6)
            r1 = 2130968872(0x7f040128, float:1.754641E38)
            int r1 = com.google.android.material.color.MaterialColors.getColor(r5, r1)
            int r6 = com.google.android.material.color.MaterialColors.layer(r6, r7, r1)
            com.google.android.material.shape.ShapeAppearanceModel r7 = r5.shapeAppearanceModel
            if (r7 == 0) goto L_0x00a3
            int r0 = com.google.android.material.snackbar.BaseTransientBottomBar.$r8$clinit
            com.google.android.material.shape.MaterialShapeDrawable r0 = new com.google.android.material.shape.MaterialShapeDrawable
            r0.<init>((com.google.android.material.shape.ShapeAppearanceModel) r7)
            android.content.res.ColorStateList r6 = android.content.res.ColorStateList.valueOf(r6)
            r0.setFillColor(r6)
            goto L_0x00bf
        L_0x00a3:
            android.content.res.Resources r7 = r5.getResources()
            int r1 = com.google.android.material.snackbar.BaseTransientBottomBar.$r8$clinit
            r1 = 2131166889(0x7f0706a9, float:1.7948036E38)
            float r7 = r7.getDimension(r1)
            android.graphics.drawable.GradientDrawable r1 = new android.graphics.drawable.GradientDrawable
            r1.<init>()
            r1.setShape(r0)
            r1.setCornerRadius(r7)
            r1.setColor(r6)
            r0 = r1
        L_0x00bf:
            android.content.res.ColorStateList r6 = r5.backgroundTint
            if (r6 == 0) goto L_0x00c6
            r0.setTintList(r6)
        L_0x00c6:
            java.util.WeakHashMap r6 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api16Impl.setBackground(r5, r0)
        L_0x00cb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.snackbar.Snackbar$SnackbarLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }
}
