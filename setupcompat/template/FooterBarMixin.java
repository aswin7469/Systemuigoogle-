package com.google.android.setupcompat.template;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import com.android.app.viewcapture.data.ViewNode;
import com.google.android.setupcompat.PartnerCustomizationLayout;
import com.google.android.setupcompat.R$styleable;
import com.google.android.setupcompat.internal.FooterButtonPartnerConfig;
import com.google.android.setupcompat.internal.Preconditions;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.logging.internal.FooterBarMixinMetrics;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import java.util.HashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class FooterBarMixin implements Mixin {
    final boolean applyDynamicColor;
    final boolean applyPartnerResources;
    public LinearLayout buttonContainer;
    public final Context context;
    int defaultPadding;
    public int footerBarPaddingBottom;
    int footerBarPaddingEnd;
    int footerBarPaddingStart;
    public int footerBarPaddingTop;
    public final int footerBarPrimaryBackgroundColor;
    public final int footerBarSecondaryBackgroundColor;
    final boolean footerButtonAlignEnd;
    public final ViewStub footerStub;
    public boolean isSecondaryButtonInPrimaryStyle = false;
    public final FooterBarMixinMetrics metrics;
    public FooterButton primaryButton;
    public int primaryButtonId;
    public FooterButtonPartnerConfig primaryButtonPartnerConfigForTesting;
    public FooterButton secondaryButton;
    public int secondaryButtonId;
    public FooterButtonPartnerConfig secondaryButtonPartnerConfigForTesting;
    final boolean useFullDynamicColor;

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, com.google.android.setupcompat.logging.internal.FooterBarMixinMetrics] */
    public FooterBarMixin(TemplateLayout templateLayout, AttributeSet attributeSet, int i) {
        boolean z;
        boolean z2;
        String str;
        ? obj = new Object();
        obj.primaryButtonVisibility = "Unknown";
        obj.secondaryButtonVisibility = "Unknown";
        this.metrics = obj;
        Context context2 = templateLayout.getContext();
        this.context = context2;
        this.footerStub = (ViewStub) templateLayout.findViewById(2131363746);
        FooterButtonStyleUtils.defaultTextColor.clear();
        boolean z3 = templateLayout instanceof PartnerCustomizationLayout;
        boolean z4 = true;
        if (!z3 || !((PartnerCustomizationLayout) templateLayout).shouldApplyPartnerResource()) {
            z = false;
        } else {
            z = true;
        }
        this.applyPartnerResources = z;
        if (!z3 || !((PartnerCustomizationLayout) templateLayout).shouldApplyDynamicColor()) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.applyDynamicColor = z2;
        this.useFullDynamicColor = (!z3 || !((PartnerCustomizationLayout) templateLayout).useFullDynamicColor()) ? false : z4;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.SucFooterBarMixin, i, 0);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(12, 0);
        this.defaultPadding = dimensionPixelSize;
        this.footerBarPaddingTop = obtainStyledAttributes.getDimensionPixelSize(11, dimensionPixelSize);
        this.footerBarPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(8, this.defaultPadding);
        this.footerBarPaddingStart = obtainStyledAttributes.getDimensionPixelSize(10, 0);
        this.footerBarPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(9, 0);
        this.footerBarPrimaryBackgroundColor = obtainStyledAttributes.getColor(13, 0);
        this.footerBarSecondaryBackgroundColor = obtainStyledAttributes.getColor(15, 0);
        this.footerButtonAlignEnd = obtainStyledAttributes.getBoolean(0, false);
        int resourceId = obtainStyledAttributes.getResourceId(14, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(16, 0);
        obtainStyledAttributes.recycle();
        FooterButtonInflater footerButtonInflater = new FooterButtonInflater(context2);
        String str2 = "VisibleUsingXml";
        if (resourceId2 != 0) {
            XmlResourceParser xml = context2.getResources().getXml(resourceId2);
            try {
                FooterButton inflate = footerButtonInflater.inflate(xml);
                xml.close();
                setSecondaryButton(inflate);
                if (obj.primaryButtonVisibility.equals("Unknown")) {
                    str = str2;
                } else {
                    str = obj.primaryButtonVisibility;
                }
                obj.primaryButtonVisibility = str;
            } catch (Throwable th) {
                xml.close();
                throw th;
            }
        }
        if (resourceId != 0) {
            XmlResourceParser xml2 = context2.getResources().getXml(resourceId);
            try {
                FooterButton inflate2 = footerButtonInflater.inflate(xml2);
                xml2.close();
                setPrimaryButton(inflate2);
                obj.secondaryButtonVisibility = !obj.secondaryButtonVisibility.equals("Unknown") ? obj.secondaryButtonVisibility : str2;
            } catch (Throwable th2) {
                xml2.close();
                throw th2;
            }
        }
    }

    public final void addSpace() {
        LinearLayout ensureFooterInflated = ensureFooterInflated();
        View view = new View(this.context);
        view.setLayoutParams(new LinearLayout.LayoutParams(0, 0, 1.0f));
        view.setVisibility(4);
        ensureFooterInflated.addView(view);
    }

    public final LinearLayout ensureFooterInflated() {
        int dimension;
        if (this.buttonContainer == null) {
            ViewStub viewStub = this.footerStub;
            if (viewStub != null) {
                Context context2 = this.context;
                viewStub.setLayoutInflater(LayoutInflater.from(new ContextThemeWrapper(context2, 2132017761)));
                viewStub.setLayoutResource(2131559065);
                LinearLayout linearLayout = (LinearLayout) viewStub.inflate();
                this.buttonContainer = linearLayout;
                if (linearLayout != null) {
                    linearLayout.setId(View.generateViewId());
                    linearLayout.setPadding(this.footerBarPaddingStart, this.footerBarPaddingTop, this.footerBarPaddingEnd, this.footerBarPaddingBottom);
                    if (isFooterButtonAlignedEnd()) {
                        linearLayout.setGravity(8388629);
                    }
                }
                LinearLayout linearLayout2 = this.buttonContainer;
                if (linearLayout2 != null && this.applyPartnerResources) {
                    if (!this.useFullDynamicColor) {
                        linearLayout2.setBackgroundColor(PartnerConfigHelper.get(context2).getColor(context2, PartnerConfig.CONFIG_FOOTER_BAR_BG_COLOR));
                    }
                    PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context2);
                    PartnerConfig partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_PADDING_TOP;
                    if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig)) {
                        this.footerBarPaddingTop = (int) PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig, 0.0f);
                    }
                    PartnerConfigHelper partnerConfigHelper2 = PartnerConfigHelper.get(context2);
                    PartnerConfig partnerConfig2 = PartnerConfig.CONFIG_FOOTER_BUTTON_PADDING_BOTTOM;
                    if (partnerConfigHelper2.isPartnerConfigAvailable(partnerConfig2)) {
                        this.footerBarPaddingBottom = (int) PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig2, 0.0f);
                    }
                    PartnerConfigHelper partnerConfigHelper3 = PartnerConfigHelper.get(context2);
                    PartnerConfig partnerConfig3 = PartnerConfig.CONFIG_FOOTER_BAR_PADDING_START;
                    if (partnerConfigHelper3.isPartnerConfigAvailable(partnerConfig3)) {
                        this.footerBarPaddingStart = (int) PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig3, 0.0f);
                    }
                    PartnerConfigHelper partnerConfigHelper4 = PartnerConfigHelper.get(context2);
                    PartnerConfig partnerConfig4 = PartnerConfig.CONFIG_FOOTER_BAR_PADDING_END;
                    if (partnerConfigHelper4.isPartnerConfigAvailable(partnerConfig4)) {
                        this.footerBarPaddingEnd = (int) PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig4, 0.0f);
                    }
                    linearLayout2.setPadding(this.footerBarPaddingStart, this.footerBarPaddingTop, this.footerBarPaddingEnd, this.footerBarPaddingBottom);
                    PartnerConfigHelper partnerConfigHelper5 = PartnerConfigHelper.get(context2);
                    PartnerConfig partnerConfig5 = PartnerConfig.CONFIG_FOOTER_BAR_MIN_HEIGHT;
                    if (partnerConfigHelper5.isPartnerConfigAvailable(partnerConfig5) && (dimension = (int) PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig5, 0.0f)) > 0) {
                        linearLayout2.setMinimumHeight(dimension);
                    }
                }
            } else {
                throw new IllegalStateException("Footer stub is not found in this template");
            }
        }
        return this.buttonContainer;
    }

    public int getPaddingBottom() {
        LinearLayout linearLayout = this.buttonContainer;
        if (linearLayout != null) {
            return linearLayout.getPaddingBottom();
        }
        return this.footerStub.getPaddingBottom();
    }

    public int getPaddingTop() {
        LinearLayout linearLayout = this.buttonContainer;
        if (linearLayout != null) {
            return linearLayout.getPaddingTop();
        }
        return this.footerStub.getPaddingTop();
    }

    public final int getPartnerTheme(FooterButton footerButton, int i, PartnerConfig partnerConfig) {
        int i2 = footerButton.theme;
        if (i2 != 0 && !this.applyPartnerResources) {
            i = i2;
        }
        if (!this.applyPartnerResources) {
            return i;
        }
        Context context2 = this.context;
        if (PartnerConfigHelper.get(context2).getColor(context2, partnerConfig) == 0) {
            return 2132017759;
        }
        return 2132017758;
    }

    public final Button getPrimaryButtonView() {
        LinearLayout linearLayout = this.buttonContainer;
        if (linearLayout == null) {
            return null;
        }
        return (Button) linearLayout.findViewById(this.primaryButtonId);
    }

    public final Button getSecondaryButtonView() {
        LinearLayout linearLayout = this.buttonContainer;
        if (linearLayout == null) {
            return null;
        }
        return (Button) linearLayout.findViewById(this.secondaryButtonId);
    }

    public int getVisibility() {
        return this.buttonContainer.getVisibility();
    }

    public final FooterActionButton inflateButton(FooterButton footerButton, FooterButtonPartnerConfig footerButtonPartnerConfig) {
        FooterActionButton footerActionButton = (FooterActionButton) LayoutInflater.from(new ContextThemeWrapper(this.context, footerButtonPartnerConfig.partnerTheme)).inflate(2131559064, (ViewGroup) null, false);
        footerActionButton.setId(View.generateViewId());
        footerActionButton.setText(footerButton.text);
        footerActionButton.setOnClickListener(footerButton);
        footerActionButton.setVisibility(0);
        footerActionButton.setEnabled(footerButton.enabled);
        footerActionButton.footerButton = footerButton;
        final int id = footerActionButton.getId();
        footerButton.buttonListener = new Object() {
        };
        return footerActionButton;
    }

    public final boolean isFooterButtonAlignedEnd() {
        Context context2 = this.context;
        PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context2);
        PartnerConfig partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ALIGNED_END;
        if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig)) {
            return PartnerConfigHelper.get(context2).getBoolean(context2, partnerConfig, false);
        }
        return this.footerButtonAlignEnd;
    }

    public boolean isPrimaryButtonVisible() {
        if (getPrimaryButtonView() == null || getPrimaryButtonView().getVisibility() != 0) {
            return false;
        }
        return true;
    }

    public boolean isSecondaryButtonVisible() {
        if (getSecondaryButtonView() == null || getSecondaryButtonView().getVisibility() != 0) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01ee  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01fd  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0222  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0229  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0235  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0242  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x024d  */
    /* JADX WARNING: Removed duplicated region for block: B:90:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onFooterButtonApplyPartnerResource(com.google.android.setupcompat.template.FooterActionButton r18, com.google.android.setupcompat.internal.FooterButtonPartnerConfig r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            boolean r3 = r0.applyPartnerResources
            if (r3 != 0) goto L_0x000b
            return
        L_0x000b:
            boolean r3 = r0.applyDynamicColor
            int r4 = r18.getId()
            int r5 = r0.primaryButtonId
            r6 = 0
            if (r4 != r5) goto L_0x0018
            r4 = 1
            goto L_0x0019
        L_0x0018:
            r4 = r6
        L_0x0019:
            java.util.HashMap r5 = com.google.android.setupcompat.template.FooterButtonStyleUtils.defaultTextColor
            int r7 = r18.getId()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            android.content.res.ColorStateList r8 = r18.getTextColors()
            r5.put(r7, r8)
            r5 = 1132396544(0x437f0000, float:255.0)
            android.content.Context r7 = r0.context
            com.google.android.setupcompat.partnerconfig.PartnerConfig r8 = r2.buttonDisableTextColorConfig
            com.google.android.setupcompat.partnerconfig.PartnerConfig r9 = r2.buttonTextColorConfig
            r10 = 0
            if (r3 != 0) goto L_0x00c8
            boolean r11 = r18.isEnabled()
            if (r11 == 0) goto L_0x004d
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r11 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            int r11 = r11.getColor(r7, r9)
            if (r11 == 0) goto L_0x0050
            android.content.res.ColorStateList r11 = android.content.res.ColorStateList.valueOf(r11)
            r1.setTextColor(r11)
            goto L_0x0050
        L_0x004d:
            com.google.android.setupcompat.template.FooterButtonStyleUtils.updateButtonTextDisabledColorWithPartnerConfig(r7, r1, r8)
        L_0x0050:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r11 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r12 = r2.buttonBackgroundConfig
            int r11 = r11.getColor(r7, r12)
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r12 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r13 = r2.buttonDisableAlphaConfig
            float r12 = r12.getFraction(r7, r13)
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r13 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r14 = r2.buttonDisableBackgroundConfig
            int r13 = r13.getColor(r7, r14)
            r14 = -16842910(0xfffffffffefeff62, float:-1.6947497E38)
            int[] r14 = new int[]{r14}
            int[] r15 = new int[r6]
            if (r11 == 0) goto L_0x00c8
            int r16 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r16 > 0) goto L_0x0093
            r12 = 16842803(0x1010033, float:2.36937E-38)
            int[] r12 = new int[]{r12}
            android.content.res.TypedArray r12 = r7.obtainStyledAttributes(r12)
            r10 = 1048911544(0x3e851eb8, float:0.26)
            float r10 = r12.getFloat(r6, r10)
            r12.recycle()
            r12 = r10
        L_0x0093:
            if (r13 != 0) goto L_0x0096
            r13 = r11
        L_0x0096:
            android.content.res.ColorStateList r10 = new android.content.res.ColorStateList
            int[][] r14 = new int[][]{r14, r15}
            float r12 = r12 * r5
            int r12 = (int) r12
            int r15 = android.graphics.Color.red(r13)
            int r5 = android.graphics.Color.green(r13)
            int r13 = android.graphics.Color.blue(r13)
            int r5 = android.graphics.Color.argb(r12, r15, r5, r13)
            int[] r5 = new int[]{r5, r11}
            r10.<init>(r14, r5)
            android.graphics.drawable.Drawable r5 = r18.getBackground()
            android.graphics.drawable.Drawable r5 = r5.mutate()
            int[] r11 = new int[r6]
            r5.setState(r11)
            r18.refreshDrawableState()
            r1.setBackgroundTintList(r10)
        L_0x00c8:
            if (r3 == 0) goto L_0x00d3
            android.content.res.ColorStateList r3 = r18.getTextColors()
            int r3 = r3.getDefaultColor()
            goto L_0x00db
        L_0x00d3:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r3 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            int r3 = r3.getColor(r7, r9)
        L_0x00db:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r5 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r10 = r2.buttonRippleColorAlphaConfig
            float r5 = r5.getFraction(r7, r10)
            android.graphics.drawable.Drawable r10 = r18.getBackground()
            boolean r11 = r10 instanceof android.graphics.drawable.InsetDrawable
            r12 = 0
            if (r11 == 0) goto L_0x00f7
            android.graphics.drawable.InsetDrawable r10 = (android.graphics.drawable.InsetDrawable) r10
            android.graphics.drawable.Drawable r10 = r10.getDrawable()
            android.graphics.drawable.RippleDrawable r10 = (android.graphics.drawable.RippleDrawable) r10
            goto L_0x00ff
        L_0x00f7:
            boolean r11 = r10 instanceof android.graphics.drawable.RippleDrawable
            if (r11 == 0) goto L_0x00fe
            android.graphics.drawable.RippleDrawable r10 = (android.graphics.drawable.RippleDrawable) r10
            goto L_0x00ff
        L_0x00fe:
            r10 = r12
        L_0x00ff:
            if (r10 != 0) goto L_0x0102
            goto L_0x0136
        L_0x0102:
            r11 = 16842919(0x10100a7, float:2.3694026E-38)
            int[] r11 = new int[]{r11}
            r13 = 16842908(0x101009c, float:2.3693995E-38)
            int[] r13 = new int[]{r13}
            r14 = 1132396544(0x437f0000, float:255.0)
            float r5 = r5 * r14
            int r5 = (int) r5
            int r14 = android.graphics.Color.red(r3)
            int r15 = android.graphics.Color.green(r3)
            int r3 = android.graphics.Color.blue(r3)
            int r3 = android.graphics.Color.argb(r5, r14, r15, r3)
            android.content.res.ColorStateList r5 = new android.content.res.ColorStateList
            int[] r14 = android.util.StateSet.NOTHING
            int[][] r11 = new int[][]{r11, r13, r14}
            int[] r3 = new int[]{r3, r3, r6}
            r5.<init>(r11, r3)
            r10.setColor(r5)
        L_0x0136:
            android.view.ViewGroup$LayoutParams r3 = r18.getLayoutParams()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r5 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r10 = r2.buttonMarginStartConfig
            boolean r5 = r5.isPartnerConfigAvailable(r10)
            if (r5 == 0) goto L_0x015f
            boolean r5 = r3 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r5 == 0) goto L_0x015f
            android.view.ViewGroup$MarginLayoutParams r3 = (android.view.ViewGroup.MarginLayoutParams) r3
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r5 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            r11 = 0
            float r5 = r5.getDimension(r7, r10, r11)
            int r5 = (int) r5
            int r10 = r3.topMargin
            int r11 = r3.rightMargin
            int r13 = r3.bottomMargin
            r3.setMargins(r5, r10, r11, r13)
        L_0x015f:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r3 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r5 = r2.buttonTextSizeConfig
            r10 = 0
            float r3 = r3.getDimension(r7, r5, r10)
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 <= 0) goto L_0x0171
            r1.setTextSize(r6, r3)
        L_0x0171:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r3 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r5 = r2.buttonMinHeightConfig
            boolean r3 = r3.isPartnerConfigAvailable(r5)
            if (r3 == 0) goto L_0x018d
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r3 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            float r3 = r3.getDimension(r7, r5, r10)
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 <= 0) goto L_0x018d
            int r3 = (int) r3
            r1.setMinHeight(r3)
        L_0x018d:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r3 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r5 = r2.buttonTextTypeFaceConfig
            java.lang.String r3 = r3.getString(r7, r5)
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r5 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r10 = r2.buttonTextStyleConfig
            boolean r5 = r5.isPartnerConfigAvailable(r10)
            if (r5 == 0) goto L_0x01ac
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r5 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            int r5 = r5.getInteger(r7, r10, r6)
            goto L_0x01ad
        L_0x01ac:
            r5 = r6
        L_0x01ad:
            boolean r10 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.isFontWeightEnabled(r7)
            if (r10 == 0) goto L_0x01d2
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r10 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r11 = r2.buttonTextWeightConfig
            boolean r10 = r10.isPartnerConfigAvailable(r11)
            if (r10 == 0) goto L_0x01d2
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r10 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            r13 = 400(0x190, float:5.6E-43)
            int r10 = r10.getInteger(r7, r11, r13)
            android.graphics.Typeface r3 = android.graphics.Typeface.create(r3, r5)
            android.graphics.Typeface r3 = android.graphics.Typeface.create(r3, r10, r6)
            goto L_0x01d6
        L_0x01d2:
            android.graphics.Typeface r3 = android.graphics.Typeface.create(r3, r5)
        L_0x01d6:
            if (r3 == 0) goto L_0x01db
            r1.setTypeface(r3)
        L_0x01db:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r3 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r5 = r2.buttonRadiusConfig
            r10 = 0
            float r3 = r3.getDimension(r7, r5, r10)
            android.graphics.drawable.Drawable r5 = r18.getBackground()
            boolean r10 = r5 instanceof android.graphics.drawable.InsetDrawable
            if (r10 == 0) goto L_0x01fd
            android.graphics.drawable.InsetDrawable r5 = (android.graphics.drawable.InsetDrawable) r5
            android.graphics.drawable.Drawable r5 = r5.getDrawable()
            android.graphics.drawable.LayerDrawable r5 = (android.graphics.drawable.LayerDrawable) r5
            android.graphics.drawable.Drawable r5 = r5.getDrawable(r6)
            android.graphics.drawable.GradientDrawable r5 = (android.graphics.drawable.GradientDrawable) r5
            goto L_0x0220
        L_0x01fd:
            boolean r10 = r5 instanceof android.graphics.drawable.RippleDrawable
            if (r10 == 0) goto L_0x021f
            android.graphics.drawable.RippleDrawable r5 = (android.graphics.drawable.RippleDrawable) r5
            android.graphics.drawable.Drawable r10 = r5.getDrawable(r6)
            boolean r10 = r10 instanceof android.graphics.drawable.GradientDrawable
            if (r10 == 0) goto L_0x0212
            android.graphics.drawable.Drawable r5 = r5.getDrawable(r6)
            android.graphics.drawable.GradientDrawable r5 = (android.graphics.drawable.GradientDrawable) r5
            goto L_0x0220
        L_0x0212:
            android.graphics.drawable.Drawable r5 = r5.getDrawable(r6)
            android.graphics.drawable.InsetDrawable r5 = (android.graphics.drawable.InsetDrawable) r5
            android.graphics.drawable.Drawable r5 = r5.getDrawable()
            android.graphics.drawable.GradientDrawable r5 = (android.graphics.drawable.GradientDrawable) r5
            goto L_0x0220
        L_0x021f:
            r5 = r12
        L_0x0220:
            if (r5 == 0) goto L_0x0225
            r5.setCornerRadius(r3)
        L_0x0225:
            com.google.android.setupcompat.partnerconfig.PartnerConfig r2 = r2.buttonIconConfig
            if (r2 == 0) goto L_0x0232
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r3 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            android.graphics.drawable.Drawable r2 = r3.getDrawable(r7, r2)
            goto L_0x0233
        L_0x0232:
            r2 = r12
        L_0x0233:
            if (r2 == 0) goto L_0x0240
            int r3 = r2.getIntrinsicHeight()
            int r5 = r2.getIntrinsicWidth()
            r2.setBounds(r6, r6, r5, r3)
        L_0x0240:
            if (r4 == 0) goto L_0x0245
            r3 = r2
            r2 = r12
            goto L_0x0246
        L_0x0245:
            r3 = r12
        L_0x0246:
            r1.setCompoundDrawablesRelative(r2, r12, r3, r12)
            boolean r0 = r0.applyDynamicColor
            if (r0 != 0) goto L_0x0268
            boolean r0 = r18.isEnabled()
            if (r0 == 0) goto L_0x0265
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r0 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            int r0 = r0.getColor(r7, r9)
            if (r0 == 0) goto L_0x0268
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r0)
            r1.setTextColor(r0)
            goto L_0x0268
        L_0x0265:
            com.google.android.setupcompat.template.FooterButtonStyleUtils.updateButtonTextDisabledColorWithPartnerConfig(r7, r1, r8)
        L_0x0268:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupcompat.template.FooterBarMixin.onFooterButtonApplyPartnerResource(com.google.android.setupcompat.template.FooterActionButton, com.google.android.setupcompat.internal.FooterButtonPartnerConfig):void");
    }

    public final void onFooterButtonInflated(FooterActionButton footerActionButton, int i) {
        boolean z;
        if (!this.applyDynamicColor && i != 0) {
            HashMap hashMap = FooterButtonStyleUtils.defaultTextColor;
            footerActionButton.getBackground().mutate().setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
        }
        this.buttonContainer.addView(footerActionButton);
        Button primaryButtonView = getPrimaryButtonView();
        Button secondaryButtonView = getSecondaryButtonView();
        boolean z2 = true;
        int i2 = 0;
        if (primaryButtonView == null || primaryButtonView.getVisibility() != 0) {
            z = false;
        } else {
            z = true;
        }
        if (secondaryButtonView == null || secondaryButtonView.getVisibility() != 0) {
            z2 = false;
        }
        LinearLayout linearLayout = this.buttonContainer;
        if (linearLayout != null) {
            if (!z && !z2) {
                i2 = 8;
            }
            linearLayout.setVisibility(i2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void repopulateButtons() {
        /*
            r9 = this;
            android.widget.LinearLayout r0 = r9.ensureFooterInflated()
            android.widget.Button r1 = r9.getPrimaryButtonView()
            android.widget.Button r2 = r9.getSecondaryButtonView()
            r0.removeAllViews()
            boolean r3 = r9.isSecondaryButtonInPrimaryStyle
            android.content.Context r4 = r9.context
            r5 = 0
            if (r3 != 0) goto L_0x0018
        L_0x0016:
            r3 = r5
            goto L_0x0046
        L_0x0018:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r4)
            android.os.Bundle r3 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.applyNeutralButtonStyleBundle
            java.lang.String r6 = "isNeutralButtonStyleEnabled"
            if (r3 != 0) goto L_0x003b
            r3 = 0
            android.content.ContentResolver r7 = r4.getContentResolver()     // Catch:{ IllegalArgumentException | SecurityException -> 0x0031 }
            android.net.Uri r8 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.getContentUri()     // Catch:{ IllegalArgumentException | SecurityException -> 0x0031 }
            android.os.Bundle r7 = r7.call(r8, r6, r3, r3)     // Catch:{ IllegalArgumentException | SecurityException -> 0x0031 }
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.applyNeutralButtonStyleBundle = r7     // Catch:{ IllegalArgumentException | SecurityException -> 0x0031 }
            goto L_0x003b
        L_0x0031:
            java.lang.String r6 = "PartnerConfigHelper"
            java.lang.String r7 = "Neutral button style supporting status unknown; return as false."
            android.util.Log.w(r6, r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.applyNeutralButtonStyleBundle = r3
            goto L_0x0016
        L_0x003b:
            android.os.Bundle r3 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.applyNeutralButtonStyleBundle
            if (r3 == 0) goto L_0x0016
            boolean r3 = r3.getBoolean(r6, r5)
            if (r3 == 0) goto L_0x0016
            r3 = 1
        L_0x0046:
            android.content.res.Resources r4 = r4.getResources()
            android.content.res.Configuration r4 = r4.getConfiguration()
            int r4 = r4.orientation
            r6 = 2
            if (r4 != r6) goto L_0x005e
            if (r3 == 0) goto L_0x005e
            boolean r4 = r9.isFooterButtonAlignedEnd()
            if (r4 == 0) goto L_0x005e
            r9.addSpace()
        L_0x005e:
            if (r2 == 0) goto L_0x007a
            boolean r4 = r9.isSecondaryButtonInPrimaryStyle
            if (r4 == 0) goto L_0x0077
            int r4 = r0.getPaddingRight()
            int r6 = r0.getPaddingTop()
            int r7 = r0.getPaddingRight()
            int r8 = r0.getPaddingBottom()
            r0.setPadding(r4, r6, r7, r8)
        L_0x0077:
            r0.addView(r2)
        L_0x007a:
            boolean r4 = r9.isFooterButtonAlignedEnd()
            if (r4 != 0) goto L_0x0083
            r9.addSpace()
        L_0x0083:
            if (r1 == 0) goto L_0x0088
            r0.addView(r1)
        L_0x0088:
            if (r1 == 0) goto L_0x00ad
            if (r2 == 0) goto L_0x00ad
            if (r3 == 0) goto L_0x00ad
            r1.measure(r5, r5)
            int r9 = r1.getMeasuredWidth()
            r2.measure(r5, r5)
            int r0 = r2.getMeasuredWidth()
            int r9 = java.lang.Math.max(r9, r0)
            android.view.ViewGroup$LayoutParams r0 = r1.getLayoutParams()
            r0.width = r9
            android.view.ViewGroup$LayoutParams r0 = r2.getLayoutParams()
            r0.width = r9
            goto L_0x00d1
        L_0x00ad:
            r9 = 0
            r0 = -2
            if (r1 == 0) goto L_0x00c0
            android.view.ViewGroup$LayoutParams r3 = r1.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r3 = (android.widget.LinearLayout.LayoutParams) r3
            if (r3 == 0) goto L_0x00c0
            r3.width = r0
            r3.weight = r9
            r1.setLayoutParams(r3)
        L_0x00c0:
            if (r2 == 0) goto L_0x00d1
            android.view.ViewGroup$LayoutParams r1 = r2.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r1 = (android.widget.LinearLayout.LayoutParams) r1
            if (r1 == 0) goto L_0x00d1
            r1.width = r0
            r1.weight = r9
            r2.setLayoutParams(r1)
        L_0x00d1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupcompat.template.FooterBarMixin.repopulateButtons():void");
    }

    public final void setPrimaryButton(FooterButton footerButton) {
        PartnerConfig partnerConfig;
        FooterButton footerButton2 = footerButton;
        Preconditions.ensureOnMainThread("setPrimaryButton");
        ensureFooterInflated();
        PartnerConfig partnerConfig2 = PartnerConfig.CONFIG_FOOTER_PRIMARY_BUTTON_BG_COLOR;
        int partnerTheme = getPartnerTheme(footerButton2, 2132017758, partnerConfig2);
        PartnerConfig partnerConfig3 = PartnerConfig.CONFIG_FOOTER_BUTTON_DISABLED_ALPHA;
        PartnerConfig partnerConfig4 = PartnerConfig.CONFIG_FOOTER_BUTTON_DISABLED_BG_COLOR;
        PartnerConfig partnerConfig5 = PartnerConfig.CONFIG_FOOTER_PRIMARY_BUTTON_DISABLED_TEXT_COLOR;
        switch (footerButton2.buttonType) {
            case 1:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_ADD_ANOTHER;
                break;
            case 2:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_CANCEL;
                break;
            case 3:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_CLEAR;
                break;
            case 4:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_DONE;
                break;
            case 5:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_NEXT;
                break;
            case 6:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_OPT_IN;
                break;
            case ViewNode.WIDTH_FIELD_NUMBER:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_SKIP;
                break;
            case 8:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_STOP;
                break;
            default:
                partnerConfig = null;
                break;
        }
        PartnerConfig partnerConfig6 = partnerConfig;
        FooterButtonPartnerConfig footerButtonPartnerConfig = new FooterButtonPartnerConfig(partnerTheme, partnerConfig2, partnerConfig3, partnerConfig4, partnerConfig5, partnerConfig6, PartnerConfig.CONFIG_FOOTER_PRIMARY_BUTTON_TEXT_COLOR, PartnerConfig.CONFIG_FOOTER_PRIMARY_BUTTON_MARGIN_START, PartnerConfig.CONFIG_FOOTER_BUTTON_TEXT_SIZE, PartnerConfig.CONFIG_FOOTER_BUTTON_MIN_HEIGHT, PartnerConfig.CONFIG_FOOTER_BUTTON_FONT_FAMILY, PartnerConfig.CONFIG_FOOTER_BUTTON_FONT_WEIGHT, PartnerConfig.CONFIG_FOOTER_BUTTON_TEXT_STYLE, PartnerConfig.CONFIG_FOOTER_BUTTON_RADIUS, PartnerConfig.CONFIG_FOOTER_BUTTON_RIPPLE_COLOR_ALPHA);
        FooterActionButton inflateButton = inflateButton(footerButton2, footerButtonPartnerConfig);
        this.primaryButtonId = inflateButton.getId();
        inflateButton.isPrimaryButtonStyle = true;
        this.primaryButton = footerButton2;
        this.primaryButtonPartnerConfigForTesting = footerButtonPartnerConfig;
        onFooterButtonInflated(inflateButton, this.footerBarPrimaryBackgroundColor);
        onFooterButtonApplyPartnerResource(inflateButton, footerButtonPartnerConfig);
        repopulateButtons();
    }

    public final void setSecondaryButton(FooterButton footerButton) {
        PartnerConfig partnerConfig;
        FooterButton footerButton2 = footerButton;
        Preconditions.ensureOnMainThread("setSecondaryButton");
        this.isSecondaryButtonInPrimaryStyle = false;
        ensureFooterInflated();
        PartnerConfig partnerConfig2 = PartnerConfig.CONFIG_FOOTER_SECONDARY_BUTTON_BG_COLOR;
        int partnerTheme = getPartnerTheme(footerButton2, 2132017759, partnerConfig2);
        PartnerConfig partnerConfig3 = PartnerConfig.CONFIG_FOOTER_BUTTON_DISABLED_ALPHA;
        PartnerConfig partnerConfig4 = PartnerConfig.CONFIG_FOOTER_BUTTON_DISABLED_BG_COLOR;
        PartnerConfig partnerConfig5 = PartnerConfig.CONFIG_FOOTER_SECONDARY_BUTTON_DISABLED_TEXT_COLOR;
        switch (footerButton2.buttonType) {
            case 1:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_ADD_ANOTHER;
                break;
            case 2:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_CANCEL;
                break;
            case 3:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_CLEAR;
                break;
            case 4:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_DONE;
                break;
            case 5:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_NEXT;
                break;
            case 6:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_OPT_IN;
                break;
            case ViewNode.WIDTH_FIELD_NUMBER:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_SKIP;
                break;
            case 8:
                partnerConfig = PartnerConfig.CONFIG_FOOTER_BUTTON_ICON_STOP;
                break;
            default:
                partnerConfig = null;
                break;
        }
        PartnerConfig partnerConfig6 = partnerConfig;
        FooterButtonPartnerConfig footerButtonPartnerConfig = r3;
        FooterButtonPartnerConfig footerButtonPartnerConfig2 = new FooterButtonPartnerConfig(partnerTheme, partnerConfig2, partnerConfig3, partnerConfig4, partnerConfig5, partnerConfig6, PartnerConfig.CONFIG_FOOTER_SECONDARY_BUTTON_TEXT_COLOR, PartnerConfig.CONFIG_FOOTER_SECONDARY_BUTTON_MARGIN_START, PartnerConfig.CONFIG_FOOTER_BUTTON_TEXT_SIZE, PartnerConfig.CONFIG_FOOTER_BUTTON_MIN_HEIGHT, PartnerConfig.CONFIG_FOOTER_BUTTON_FONT_FAMILY, PartnerConfig.CONFIG_FOOTER_BUTTON_FONT_WEIGHT, PartnerConfig.CONFIG_FOOTER_BUTTON_TEXT_STYLE, PartnerConfig.CONFIG_FOOTER_BUTTON_RADIUS, PartnerConfig.CONFIG_FOOTER_BUTTON_RIPPLE_COLOR_ALPHA);
        FooterActionButton inflateButton = inflateButton(footerButton2, footerButtonPartnerConfig2);
        this.secondaryButtonId = inflateButton.getId();
        inflateButton.isPrimaryButtonStyle = false;
        this.secondaryButton = footerButton2;
        this.secondaryButtonPartnerConfigForTesting = footerButtonPartnerConfig2;
        onFooterButtonInflated(inflateButton, this.footerBarSecondaryBackgroundColor);
        onFooterButtonApplyPartnerResource(inflateButton, footerButtonPartnerConfig2);
        repopulateButtons();
    }
}
