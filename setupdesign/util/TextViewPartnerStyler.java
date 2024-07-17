package com.google.android.setupdesign.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.setupcompat.PartnerCustomizationLayout;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.util.Logger;
import com.google.android.setupdesign.GlifLayout;
import com.google.android.setupdesign.view.RichTextView;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class TextViewPartnerStyler {

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class TextPartnerConfigs {
        public final PartnerConfig textColorConfig;
        public final PartnerConfig textFontFamilyConfig;
        public final PartnerConfig textFontWeightConfig;
        public final int textGravity;
        public final PartnerConfig textLinkFontFamilyConfig;
        public final PartnerConfig textLinkedColorConfig;
        public final PartnerConfig textMarginBottomConfig;
        public final PartnerConfig textMarginTopConfig;
        public final PartnerConfig textSizeConfig;

        public TextPartnerConfigs(PartnerConfig partnerConfig, PartnerConfig partnerConfig2, PartnerConfig partnerConfig3, PartnerConfig partnerConfig4, PartnerConfig partnerConfig5, PartnerConfig partnerConfig6, PartnerConfig partnerConfig7, PartnerConfig partnerConfig8, int i) {
            this.textColorConfig = partnerConfig;
            this.textLinkedColorConfig = partnerConfig2;
            this.textSizeConfig = partnerConfig3;
            this.textFontFamilyConfig = partnerConfig4;
            this.textFontWeightConfig = partnerConfig5;
            this.textLinkFontFamilyConfig = partnerConfig6;
            this.textMarginTopConfig = partnerConfig7;
            this.textMarginBottomConfig = partnerConfig8;
            this.textGravity = i;
        }
    }

    public static void applyPartnerCustomizationStyle(TextView textView, TextPartnerConfigs textPartnerConfigs) {
        PartnerConfig partnerConfig;
        Typeface create;
        PartnerConfig partnerConfig2;
        boolean z;
        int color;
        TemplateLayout templateLayout;
        View findViewById;
        int color2;
        Context context = textView.getContext();
        PartnerConfig partnerConfig3 = textPartnerConfigs.textColorConfig;
        if (!(partnerConfig3 == null || !PartnerConfigHelper.get(context).isPartnerConfigAvailable(partnerConfig3) || (color2 = PartnerConfigHelper.get(context).getColor(context, partnerConfig3)) == 0)) {
            textView.setTextColor(color2);
        }
        Typeface typeface = null;
        PartnerConfig partnerConfig4 = textPartnerConfigs.textLinkedColorConfig;
        if (partnerConfig4 != null && PartnerConfigHelper.get(context).isPartnerConfigAvailable(partnerConfig4)) {
            Context context2 = textView.getContext();
            try {
                Logger logger = PartnerCustomizationLayout.LOG;
                Activity lookupActivityFromContext = PartnerConfigHelper.lookupActivityFromContext(context2);
                if (lookupActivityFromContext == null || (findViewById = lookupActivityFromContext.findViewById(2131363747)) == null) {
                    templateLayout = null;
                } else {
                    templateLayout = (TemplateLayout) findViewById.getParent();
                }
                if (templateLayout instanceof GlifLayout) {
                    z = ((GlifLayout) templateLayout).shouldApplyDynamicColor();
                    if (!z && (color = PartnerConfigHelper.get(context).getColor(context, partnerConfig4)) != 0) {
                        textView.setLinkTextColor(color);
                    }
                }
            } catch (ClassCastException | IllegalArgumentException unused) {
            }
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(new int[]{2130970014});
            boolean hasValue = obtainStyledAttributes.hasValue(0);
            obtainStyledAttributes.recycle();
            z = hasValue;
            textView.setLinkTextColor(color);
        }
        PartnerConfig partnerConfig5 = textPartnerConfigs.textSizeConfig;
        if (partnerConfig5 != null && PartnerConfigHelper.get(context).isPartnerConfigAvailable(partnerConfig5)) {
            float dimension = PartnerConfigHelper.get(context).getDimension(context, partnerConfig5, 0.0f);
            if (dimension > 0.0f) {
                textView.setTextSize(0, dimension);
            }
        }
        PartnerConfig partnerConfig6 = textPartnerConfigs.textFontFamilyConfig;
        if (partnerConfig6 != null && PartnerConfigHelper.get(context).isPartnerConfigAvailable(partnerConfig6)) {
            typeface = Typeface.create(PartnerConfigHelper.get(context).getString(context, partnerConfig6), 0);
        }
        if (PartnerConfigHelper.isFontWeightEnabled(context) && (partnerConfig2 = textPartnerConfigs.textFontWeightConfig) != null && PartnerConfigHelper.get(context).isPartnerConfigAvailable(partnerConfig2)) {
            int integer = PartnerConfigHelper.get(context).getInteger(context, partnerConfig2, 400);
            if (typeface == null) {
                typeface = textView.getTypeface();
            }
            typeface = Typeface.create(typeface, integer, false);
        }
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
        if ((textView instanceof RichTextView) && (partnerConfig = textPartnerConfigs.textLinkFontFamilyConfig) != null && PartnerConfigHelper.get(context).isPartnerConfigAvailable(partnerConfig) && (create = Typeface.create(PartnerConfigHelper.get(context).getString(context, partnerConfig), 0)) != null) {
            RichTextView richTextView = (RichTextView) textView;
            RichTextView.setSpanTypeface(create);
        }
        applyPartnerCustomizationVerticalMargins(textView, textPartnerConfigs);
        textView.setGravity(textPartnerConfigs.textGravity);
    }

    public static void applyPartnerCustomizationVerticalMargins(TextView textView, TextPartnerConfigs textPartnerConfigs) {
        int i;
        int i2;
        PartnerConfig partnerConfig = textPartnerConfigs.textMarginTopConfig;
        PartnerConfig partnerConfig2 = textPartnerConfigs.textMarginBottomConfig;
        if (partnerConfig != null || partnerConfig2 != null) {
            Context context = textView.getContext();
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                if (partnerConfig == null || !PartnerConfigHelper.get(context).isPartnerConfigAvailable(partnerConfig)) {
                    i = layoutParams2.topMargin;
                } else {
                    i = (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f);
                }
                if (partnerConfig2 == null || !PartnerConfigHelper.get(context).isPartnerConfigAvailable(partnerConfig2)) {
                    i2 = layoutParams2.bottomMargin;
                } else {
                    i2 = (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig2, 0.0f);
                }
                layoutParams2.setMargins(layoutParams2.leftMargin, i, layoutParams2.rightMargin, i2);
                textView.setLayoutParams(layoutParams);
            }
        }
    }
}
