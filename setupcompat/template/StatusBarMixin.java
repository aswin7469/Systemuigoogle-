package com.google.android.setupcompat.template;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import com.google.android.setupcompat.PartnerCustomizationLayout;
import com.google.android.setupcompat.R$styleable;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.view.StatusBarBackgroundLayout;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class StatusBarMixin implements Mixin {
    public final LinearLayout linearLayout;
    public final PartnerCustomizationLayout partnerCustomizationLayout;
    public final StatusBarBackgroundLayout statusBarLayout;

    public StatusBarMixin(PartnerCustomizationLayout partnerCustomizationLayout2, Window window, AttributeSet attributeSet, int i) {
        boolean z;
        this.partnerCustomizationLayout = partnerCustomizationLayout2;
        View findManagedViewById = partnerCustomizationLayout2.findManagedViewById(2131363713);
        if (findManagedViewById != null) {
            if (findManagedViewById instanceof StatusBarBackgroundLayout) {
                this.statusBarLayout = (StatusBarBackgroundLayout) findManagedViewById;
            } else {
                this.linearLayout = (LinearLayout) findManagedViewById;
            }
            View decorView = window.getDecorView();
            window.setStatusBarColor(0);
            TypedArray obtainStyledAttributes = partnerCustomizationLayout2.getContext().obtainStyledAttributes(attributeSet, R$styleable.SucStatusBarMixin, i, 0);
            if ((decorView.getSystemUiVisibility() & 8192) == 8192) {
                z = true;
            } else {
                z = false;
            }
            boolean z2 = obtainStyledAttributes.getBoolean(0, z);
            if (partnerCustomizationLayout2.shouldApplyPartnerResource()) {
                Context context = partnerCustomizationLayout2.getContext();
                z2 = PartnerConfigHelper.get(context).getBoolean(context, PartnerConfig.CONFIG_LIGHT_STATUS_BAR, false);
            }
            if (z2) {
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
            } else {
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & -8193);
            }
            setStatusBarBackground(obtainStyledAttributes.getDrawable(1));
            obtainStyledAttributes.recycle();
            return;
        }
        throw new NullPointerException("sucLayoutStatus cannot be null in StatusBarMixin");
    }

    public final void setStatusBarBackground(Drawable drawable) {
        boolean z;
        PartnerCustomizationLayout partnerCustomizationLayout2 = this.partnerCustomizationLayout;
        if (partnerCustomizationLayout2.shouldApplyPartnerResource() && !partnerCustomizationLayout2.useFullDynamicColor()) {
            Context context = partnerCustomizationLayout2.getContext();
            drawable = PartnerConfigHelper.get(context).getDrawable(context, PartnerConfig.CONFIG_STATUS_BAR_BACKGROUND);
        }
        StatusBarBackgroundLayout statusBarBackgroundLayout = this.statusBarLayout;
        if (statusBarBackgroundLayout == null) {
            this.linearLayout.setBackgroundDrawable(drawable);
            return;
        }
        statusBarBackgroundLayout.statusBarBackground = drawable;
        boolean z2 = false;
        if (drawable == null) {
            z = true;
        } else {
            z = false;
        }
        statusBarBackgroundLayout.setWillNotDraw(z);
        if (drawable != null) {
            z2 = true;
        }
        statusBarBackgroundLayout.setFitsSystemWindows(z2);
        statusBarBackgroundLayout.invalidate();
    }
}
