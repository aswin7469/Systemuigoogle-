package com.google.android.setupcompat.template;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Window;
import com.google.android.setupcompat.PartnerCustomizationLayout;
import com.google.android.setupcompat.R$styleable;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SystemNavBarMixin implements Mixin {
    final boolean applyPartnerResources;
    public final TemplateLayout templateLayout;
    final boolean useFullDynamicColor;
    public final Window windowOfActivity;

    public SystemNavBarMixin(TemplateLayout templateLayout2, Window window) {
        boolean z;
        this.templateLayout = templateLayout2;
        this.windowOfActivity = window;
        boolean z2 = templateLayout2 instanceof PartnerCustomizationLayout;
        boolean z3 = false;
        if (!z2 || !((PartnerCustomizationLayout) templateLayout2).shouldApplyPartnerResource()) {
            z = false;
        } else {
            z = true;
        }
        this.applyPartnerResources = z;
        if (z2 && ((PartnerCustomizationLayout) templateLayout2).useFullDynamicColor()) {
            z3 = true;
        }
        this.useFullDynamicColor = z3;
    }

    public final void applyPartnerCustomizations(AttributeSet attributeSet, int i) {
        TemplateLayout templateLayout2 = this.templateLayout;
        TypedArray obtainStyledAttributes = templateLayout2.getContext().obtainStyledAttributes(attributeSet, R$styleable.SucSystemNavBarMixin, i, 0);
        boolean z = true;
        int color = obtainStyledAttributes.getColor(1, 0);
        Window window = this.windowOfActivity;
        if (window != null) {
            if (this.applyPartnerResources && !this.useFullDynamicColor) {
                Context context = templateLayout2.getContext();
                color = PartnerConfigHelper.get(context).getColor(context, PartnerConfig.CONFIG_NAVIGATION_BAR_BG_COLOR);
            }
            window.setNavigationBarColor(color);
        }
        if (!(window == null || (window.getDecorView().getSystemUiVisibility() & 16) == 16)) {
            z = false;
        }
        boolean z2 = obtainStyledAttributes.getBoolean(0, z);
        if (window != null) {
            if (this.applyPartnerResources) {
                Context context2 = templateLayout2.getContext();
                z2 = PartnerConfigHelper.get(context2).getBoolean(context2, PartnerConfig.CONFIG_LIGHT_NAVIGATION_BAR, false);
            }
            if (z2) {
                window.getDecorView().setSystemUiVisibility(16 | window.getDecorView().getSystemUiVisibility());
            } else {
                window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() & -17);
            }
        }
        TypedArray obtainStyledAttributes2 = templateLayout2.getContext().obtainStyledAttributes(new int[]{16844141});
        int color2 = obtainStyledAttributes.getColor(2, obtainStyledAttributes2.getColor(0, 0));
        if (window != null) {
            if (this.applyPartnerResources) {
                Context context3 = templateLayout2.getContext();
                PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context3);
                PartnerConfig partnerConfig = PartnerConfig.CONFIG_NAVIGATION_BAR_DIVIDER_COLOR;
                if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig)) {
                    color2 = PartnerConfigHelper.get(context3).getColor(context3, partnerConfig);
                }
            }
            window.setNavigationBarDividerColor(color2);
        }
        obtainStyledAttributes2.recycle();
        obtainStyledAttributes.recycle();
    }
}
