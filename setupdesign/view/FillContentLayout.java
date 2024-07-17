package com.google.android.setupdesign.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class FillContentLayout extends FrameLayout {
    public final int maxHeight;
    public final int maxWidth;

    public FillContentLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public static int getMaxSizeMeasureSpec(int i, int i2, int i3) {
        int max = Math.max(0, i - i2);
        if (i3 >= 0) {
            return View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        if (i3 == -1) {
            return View.MeasureSpec.makeMeasureSpec(max, 1073741824);
        }
        if (i3 == -2) {
            return View.MeasureSpec.makeMeasureSpec(max, Integer.MIN_VALUE);
        }
        return 0;
    }

    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(FrameLayout.getDefaultSize(getSuggestedMinimumWidth(), i), FrameLayout.getDefaultSize(getSuggestedMinimumHeight(), i2));
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            childAt.measure(getMaxSizeMeasureSpec(Math.min(this.maxWidth, measuredWidth), getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.width), getMaxSizeMeasureSpec(Math.min(this.maxHeight, measuredHeight), getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height));
        }
    }

    public FillContentLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130970062);
    }

    public FillContentLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudFillContentLayout, i, 0);
            PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context);
            PartnerConfig partnerConfig = PartnerConfig.CONFIG_ILLUSTRATION_MAX_HEIGHT;
            if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig)) {
                this.maxHeight = (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f);
            } else {
                this.maxHeight = obtainStyledAttributes.getDimensionPixelSize(1, -1);
            }
            PartnerConfigHelper partnerConfigHelper2 = PartnerConfigHelper.get(context);
            PartnerConfig partnerConfig2 = PartnerConfig.CONFIG_ILLUSTRATION_MAX_WIDTH;
            if (partnerConfigHelper2.isPartnerConfigAvailable(partnerConfig2)) {
                this.maxWidth = (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig2, 0.0f);
            } else {
                this.maxWidth = obtainStyledAttributes.getDimensionPixelSize(0, -1);
            }
            obtainStyledAttributes.recycle();
        }
    }
}
