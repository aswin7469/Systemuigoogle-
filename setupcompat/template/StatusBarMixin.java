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

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class StatusBarMixin implements Mixin {
    public final LinearLayout linearLayout;
    public final PartnerCustomizationLayout partnerCustomizationLayout;
    public final StatusBarBackgroundLayout statusBarLayout;

    public StatusBarMixin(PartnerCustomizationLayout partnerCustomizationLayout2, Window window, AttributeSet attributeSet, int i) {
        boolean z;
        this.partnerCustomizationLayout = partnerCustomizationLayout2;
        View findViewById = partnerCustomizationLayout2.findViewById(2131363747);
        if (findViewById != null) {
            if (findViewById instanceof StatusBarBackgroundLayout) {
                this.statusBarLayout = (StatusBarBackgroundLayout) findViewById;
            } else {
                this.linearLayout = (LinearLayout) findViewById;
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
