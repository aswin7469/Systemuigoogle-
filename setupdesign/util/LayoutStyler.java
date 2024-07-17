package com.google.android.setupdesign.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
                    if (view.getId() == 2131363735) {
                        i2 = ((int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f)) - dimensionPixelSize2;
                    }
                } else {
                    i2 = view.getPaddingEnd();
                    if (view.getId() == 2131363735) {
                        i2 = view.getPaddingStart();
                    }
                }
                if (i != view.getPaddingStart() || i2 != view.getPaddingEnd()) {
                    if (view.getId() == 2131363735) {
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

    public static void applyPartnerCustomizationLayoutPaddingStyle(View view) {
        int i;
        int i2;
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
                if (isPartnerConfigAvailable) {
                    i = (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f);
                } else {
                    i = view.getPaddingStart();
                }
                if (isPartnerConfigAvailable2) {
                    i2 = (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig2, 0.0f);
                } else {
                    i2 = view.getPaddingEnd();
                }
                if (i != view.getPaddingStart() || i2 != view.getPaddingEnd()) {
                    view.setPadding(i, view.getPaddingTop(), i2, view.getPaddingBottom());
                }
            }
        }
    }
}
