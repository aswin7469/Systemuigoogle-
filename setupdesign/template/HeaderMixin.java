package com.google.android.setupdesign.template;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import com.google.android.setupcompat.R$styleable;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.template.Mixin;
import com.google.android.setupdesign.util.LayoutStyler;
import com.google.android.setupdesign.util.PartnerStyleHelper;
import com.google.android.setupdesign.util.TextViewPartnerStyler;
import java.util.ArrayList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class HeaderMixin implements Mixin {
    boolean autoTextSizeEnabled = false;
    public float headerAutoSizeLineExtraSpacingInPx;
    public int headerAutoSizeMaxLineOfMaxSize;
    public float headerAutoSizeMaxTextSizeInPx;
    public float headerAutoSizeMinTextSizeInPx;
    public final TemplateLayout templateLayout;
    public final ArrayList titlePreDrawListeners = new ArrayList();

    public HeaderMixin(TemplateLayout templateLayout2, AttributeSet attributeSet, int i) {
        TextView textView;
        TextView textView2;
        this.templateLayout = templateLayout2;
        TypedArray obtainStyledAttributes = templateLayout2.getContext().obtainStyledAttributes(attributeSet, R$styleable.SucHeaderMixin, i, 0);
        CharSequence text = obtainStyledAttributes.getText(4);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(5);
        obtainStyledAttributes.recycle();
        if (((TextView) templateLayout2.findManagedViewById(2131363714)) != null) {
            ((TextView) templateLayout2.findManagedViewById(2131363714)).getTextSize();
            ((TextView) templateLayout2.findManagedViewById(2131363714)).getLineHeight();
        }
        tryUpdateAutoTextSizeFlagWithPartnerConfig();
        if (!(text == null || (textView2 = (TextView) templateLayout2.findManagedViewById(2131363714)) == null)) {
            if (this.autoTextSizeEnabled) {
                autoAdjustTextSize(textView2);
            }
            textView2.setText(text);
        }
        if (colorStateList != null && (textView = (TextView) templateLayout2.findManagedViewById(2131363714)) != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public final void autoAdjustTextSize(final TextView textView) {
        if (textView != null) {
            textView.setTextSize(0, this.headerAutoSizeMaxTextSizeInPx);
            textView.getTextSize();
            textView.setLineHeight(Math.round(this.headerAutoSizeLineExtraSpacingInPx + this.headerAutoSizeMaxTextSizeInPx));
            textView.getLineHeight();
            textView.setMaxLines(6);
            AnonymousClass1 r0 = new ViewTreeObserver.OnPreDrawListener() {
                public final boolean onPreDraw() {
                    textView.getViewTreeObserver().removeOnPreDrawListener(this);
                    int lineCount = textView.getLineCount();
                    HeaderMixin headerMixin = HeaderMixin.this;
                    if (lineCount <= headerMixin.headerAutoSizeMaxLineOfMaxSize) {
                        return true;
                    }
                    textView.setTextSize(0, headerMixin.headerAutoSizeMinTextSizeInPx);
                    TextView textView = textView;
                    HeaderMixin headerMixin2 = HeaderMixin.this;
                    textView.setLineHeight(Math.round(headerMixin2.headerAutoSizeLineExtraSpacingInPx + headerMixin2.headerAutoSizeMinTextSizeInPx));
                    textView.invalidate();
                    return false;
                }
            };
            textView.getViewTreeObserver().addOnPreDrawListener(r0);
            this.titlePreDrawListeners.add(r0);
        }
    }

    public final void tryApplyPartnerCustomizationStyle() {
        TemplateLayout templateLayout2 = this.templateLayout;
        TextView textView = (TextView) templateLayout2.findManagedViewById(2131363714);
        if (PartnerStyleHelper.shouldApplyPartnerResource((View) templateLayout2)) {
            View findManagedViewById = templateLayout2.findManagedViewById(2131363738);
            LayoutStyler.applyPartnerCustomizationExtraPaddingStyle(findManagedViewById);
            if (textView != null) {
                TextViewPartnerStyler.applyPartnerCustomizationStyle(textView, new TextViewPartnerStyler.TextPartnerConfigs(PartnerConfig.CONFIG_HEADER_TEXT_COLOR, (PartnerConfig) null, PartnerConfig.CONFIG_HEADER_TEXT_SIZE, PartnerConfig.CONFIG_HEADER_FONT_FAMILY, PartnerConfig.CONFIG_HEADER_FONT_WEIGHT, (PartnerConfig) null, PartnerConfig.CONFIG_HEADER_TEXT_MARGIN_TOP, PartnerConfig.CONFIG_HEADER_TEXT_MARGIN_BOTTOM, PartnerStyleHelper.getLayoutGravity(textView.getContext())));
            }
            ViewGroup viewGroup = (ViewGroup) findManagedViewById;
            if (viewGroup != null) {
                Context context = viewGroup.getContext();
                viewGroup.setBackgroundColor(PartnerConfigHelper.get(context).getColor(context, PartnerConfig.CONFIG_HEADER_AREA_BACKGROUND_COLOR));
                PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context);
                PartnerConfig partnerConfig = PartnerConfig.CONFIG_HEADER_CONTAINER_MARGIN_BOTTOM;
                if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig)) {
                    ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                        marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f));
                        viewGroup.setLayoutParams(layoutParams);
                    }
                }
            }
        }
        tryUpdateAutoTextSizeFlagWithPartnerConfig();
        if (this.autoTextSizeEnabled) {
            autoAdjustTextSize(textView);
        }
    }

    public final void tryUpdateAutoTextSizeFlagWithPartnerConfig() {
        TemplateLayout templateLayout2 = this.templateLayout;
        Context context = templateLayout2.getContext();
        if (!PartnerStyleHelper.shouldApplyPartnerResource((View) templateLayout2)) {
            this.autoTextSizeEnabled = false;
            return;
        }
        PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context);
        PartnerConfig partnerConfig = PartnerConfig.CONFIG_HEADER_AUTO_SIZE_ENABLED;
        if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig)) {
            this.autoTextSizeEnabled = PartnerConfigHelper.get(context).getBoolean(context, partnerConfig, this.autoTextSizeEnabled);
        }
        if (this.autoTextSizeEnabled) {
            Context context2 = templateLayout2.getContext();
            PartnerConfigHelper partnerConfigHelper2 = PartnerConfigHelper.get(context2);
            PartnerConfig partnerConfig2 = PartnerConfig.CONFIG_HEADER_AUTO_SIZE_MAX_TEXT_SIZE;
            if (partnerConfigHelper2.isPartnerConfigAvailable(partnerConfig2)) {
                this.headerAutoSizeMaxTextSizeInPx = PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig2, 0.0f);
            }
            PartnerConfigHelper partnerConfigHelper3 = PartnerConfigHelper.get(context2);
            PartnerConfig partnerConfig3 = PartnerConfig.CONFIG_HEADER_AUTO_SIZE_MIN_TEXT_SIZE;
            if (partnerConfigHelper3.isPartnerConfigAvailable(partnerConfig3)) {
                this.headerAutoSizeMinTextSizeInPx = PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig3, 0.0f);
            }
            PartnerConfigHelper partnerConfigHelper4 = PartnerConfigHelper.get(context2);
            PartnerConfig partnerConfig4 = PartnerConfig.CONFIG_HEADER_AUTO_SIZE_LINE_SPACING_EXTRA;
            if (partnerConfigHelper4.isPartnerConfigAvailable(partnerConfig4)) {
                this.headerAutoSizeLineExtraSpacingInPx = PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig4, 0.0f);
            }
            PartnerConfigHelper partnerConfigHelper5 = PartnerConfigHelper.get(context2);
            PartnerConfig partnerConfig5 = PartnerConfig.CONFIG_HEADER_AUTO_SIZE_MAX_LINE_OF_MAX_SIZE;
            if (partnerConfigHelper5.isPartnerConfigAvailable(partnerConfig5)) {
                this.headerAutoSizeMaxLineOfMaxSize = PartnerConfigHelper.get(context2).getInteger(context2, partnerConfig5, 0);
            }
            if (this.headerAutoSizeMaxLineOfMaxSize >= 1) {
                float f = this.headerAutoSizeMinTextSizeInPx;
                if (f > 0.0f && this.headerAutoSizeMaxTextSizeInPx >= f) {
                    return;
                }
            }
            Log.w("HeaderMixin", "Invalid configs, disable auto text size.");
            this.autoTextSizeEnabled = false;
        }
    }
}
