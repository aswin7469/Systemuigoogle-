package com.google.android.material.divider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class MaterialDivider extends View {
    public final int color;
    public final MaterialShapeDrawable dividerDrawable;
    public final int insetEnd;
    public final int insetStart;
    public final int thickness;

    public MaterialDivider(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 2130969548, 2132018840), attributeSet, 2130969548);
        Context context2 = getContext();
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.dividerDrawable = materialShapeDrawable;
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialDivider, 2130969548, 2132018840, new int[0]);
        this.thickness = obtainStyledAttributes.getDimensionPixelSize(3, getResources().getDimensionPixelSize(2131166629));
        this.insetStart = obtainStyledAttributes.getDimensionPixelOffset(2, 0);
        this.insetEnd = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        int defaultColor = MaterialResources.getColorStateList(context2, obtainStyledAttributes, 0).getDefaultColor();
        if (this.color != defaultColor) {
            this.color = defaultColor;
            materialShapeDrawable.setFillColor(ColorStateList.valueOf(defaultColor));
            invalidate();
        }
        obtainStyledAttributes.recycle();
    }

    public final void onDraw(Canvas canvas) {
        int i;
        int width;
        int i2;
        super.onDraw(canvas);
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        boolean z = true;
        if (ViewCompat.Api17Impl.getLayoutDirection(this) != 1) {
            z = false;
        }
        if (z) {
            i = this.insetEnd;
        } else {
            i = this.insetStart;
        }
        if (z) {
            width = getWidth();
            i2 = this.insetStart;
        } else {
            width = getWidth();
            i2 = this.insetEnd;
        }
        this.dividerDrawable.setBounds(i, 0, width - i2, getBottom() - getTop());
        this.dividerDrawable.draw(canvas);
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        int measuredHeight = getMeasuredHeight();
        if (mode == Integer.MIN_VALUE || mode == 0) {
            int i3 = this.thickness;
            if (i3 > 0 && measuredHeight != i3) {
                measuredHeight = i3;
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
    }
}
