package com.google.android.setupdesign.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class LayoutStyler {
    public static void applyPartnerCustomizationExtraPaddingStyle(View view) {
        int i;
        int i2;
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view != null) {
            Context context = view.getContext();
            PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context);
            PartnerConfig partnerConfig = PartnerConfig.CONFIG_LAYOUT_MARGIN_START;
            boolean isPartnerConfigAvailable = partnerConfigHelper.isPartnerConfigAvailable(partnerConfig);
            PartnerConfigHelper partnerConfigHelper2 = PartnerConfigHelper.get(context);
            PartnerConfig partnerConfig2 = PartnerConfig.CONFIG_LAYOUT_MARGIN_END;
            boolean isPartnerConfigAvailable2 = partnerConfigHelper2.isPartnerConfigAvailable(partnerConfig2);
            if (!PartnerStyleHelper.shouldApplyPartnerResource(view)) {
                return;
            }
            if (isPartnerConfigAvailable || isPartnerConfigAvailable2) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{2130970100, 2130970099});
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, 0);
                int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(1, 0);
                obtainStyledAttributes.recycle();
                if (isPartnerConfigAvailable) {
                    i = ((int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f)) - dimensionPixelSize;
                } else {
                    i = view.getPaddingStart();
                }
                if (isPartnerConfigAvailable2) {
                    i2 = ((int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig2, 0.0f)) - dimensionPixelSize2;
                    if (view.getId() == 2131363769) {
                        i2 = ((int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f)) - dimensionPixelSize2;
                    }
                } else {
                    i2 = view.getPaddingEnd();
                    if (view.getId() == 2131363769) {
                        i2 = view.getPaddingStart();
                    }
                }
                if (i != view.getPaddingStart() || i2 != view.getPaddingEnd()) {
                    if (view.getId() == 2131363769) {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                            marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                        } else {
                            marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
                        }
                        marginLayoutParams.setMargins(i, view.getPaddingTop(), i2, view.getPaddingBottom());
                        return;
                    }
                    view.setPadding(i, view.getPaddingTop(), i2, view.getPaddingBottom());
                }
            }
        }
    }
}
