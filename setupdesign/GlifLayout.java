package com.google.android.setupdesign;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.setupcompat.PartnerCustomizationLayout;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.template.StatusBarMixin;
import com.google.android.setupdesign.template.DescriptionMixin;
import com.google.android.setupdesign.template.HeaderMixin;
import com.google.android.setupdesign.template.IconMixin;
import com.google.android.setupdesign.template.ProfileMixin;
import com.google.android.setupdesign.template.ProgressBarMixin;
import com.google.android.setupdesign.util.HeaderAreaStyler$1;
import com.google.android.setupdesign.util.LayoutStyler;
import com.google.android.setupdesign.util.PartnerStyleHelper;
import com.google.android.setupdesign.util.TextViewPartnerStyler;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class GlifLayout extends PartnerCustomizationLayout {
    public boolean applyPartnerHeavyThemeResource;
    public ColorStateList backgroundBaseColor;
    public boolean backgroundPatterned;
    public ColorStateList primaryColor;

    public GlifLayout(Context context) {
        this(context, 0, 0);
    }

    public final ViewGroup findContainer(int i) {
        if (i == 0) {
            i = 2131363769;
        }
        if (i == 0) {
            i = 2131363745;
        }
        return (ViewGroup) findViewById(i);
    }

    /* JADX WARNING: type inference failed for: r11v1, types: [com.google.android.setupcompat.template.Mixin, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x020a  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x020d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void init(android.util.AttributeSet r10, int r11) {
        /*
            r9 = this;
            boolean r0 = r9.isInEditMode()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            android.content.Context r0 = r9.getContext()
            int[] r1 = com.google.android.setupdesign.R$styleable.SudGlifLayout
            r2 = 0
            android.content.res.TypedArray r0 = r0.obtainStyledAttributes(r10, r1, r11, r2)
            r1 = 4
            boolean r1 = r0.getBoolean(r1, r2)
            boolean r3 = r9.shouldApplyPartnerResource()
            r4 = 1
            if (r3 == 0) goto L_0x0022
            if (r1 == 0) goto L_0x0022
            r1 = r4
            goto L_0x0023
        L_0x0022:
            r1 = r2
        L_0x0023:
            r9.applyPartnerHeavyThemeResource = r1
            com.google.android.setupdesign.template.HeaderMixin r1 = new com.google.android.setupdesign.template.HeaderMixin
            r1.<init>(r9, r10, r11)
            java.lang.Class<com.google.android.setupdesign.template.HeaderMixin> r3 = com.google.android.setupdesign.template.HeaderMixin.class
            r9.registerMixin(r3, r1)
            com.google.android.setupdesign.template.DescriptionMixin r1 = new com.google.android.setupdesign.template.DescriptionMixin
            r1.<init>(r9, r10, r11)
            java.lang.Class<com.google.android.setupdesign.template.DescriptionMixin> r3 = com.google.android.setupdesign.template.DescriptionMixin.class
            r9.registerMixin(r3, r1)
            com.google.android.setupdesign.template.IconMixin r1 = new com.google.android.setupdesign.template.IconMixin
            r1.<init>(r9, r10, r11)
            java.lang.Class<com.google.android.setupdesign.template.IconMixin> r3 = com.google.android.setupdesign.template.IconMixin.class
            r9.registerMixin(r3, r1)
            com.google.android.setupdesign.template.ProfileMixin r1 = new com.google.android.setupdesign.template.ProfileMixin
            r1.<init>(r9)
            java.lang.Class<com.google.android.setupdesign.template.ProfileMixin> r3 = com.google.android.setupdesign.template.ProfileMixin.class
            r9.registerMixin(r3, r1)
            com.google.android.setupdesign.template.ProgressBarMixin r1 = new com.google.android.setupdesign.template.ProgressBarMixin
            r1.<init>(r9, r10, r11)
            java.lang.Class<com.google.android.setupdesign.template.ProgressBarMixin> r10 = com.google.android.setupdesign.template.ProgressBarMixin.class
            r9.registerMixin(r10, r1)
            com.google.android.setupdesign.template.IllustrationProgressMixin r11 = new com.google.android.setupdesign.template.IllustrationProgressMixin
            r11.<init>()
            com.google.android.setupdesign.template.IllustrationProgressMixin$ProgressConfig[] r1 = com.google.android.setupdesign.template.IllustrationProgressMixin.ProgressConfig.$VALUES
            r9.getContext()
            java.lang.Class<com.google.android.setupdesign.template.IllustrationProgressMixin> r1 = com.google.android.setupdesign.template.IllustrationProgressMixin.class
            r9.registerMixin(r1, r11)
            com.google.android.setupdesign.template.RequireScrollMixin r11 = new com.google.android.setupdesign.template.RequireScrollMixin
            r11.<init>()
            java.lang.Class<com.google.android.setupdesign.template.RequireScrollMixin> r1 = com.google.android.setupdesign.template.RequireScrollMixin.class
            r9.registerMixin(r1, r11)
            r11 = 2131363790(0x7f0a07ce, float:1.8347399E38)
            android.view.View r11 = r9.findViewById(r11)
            boolean r1 = r11 instanceof android.widget.ScrollView
            if (r1 == 0) goto L_0x007e
            android.widget.ScrollView r11 = (android.widget.ScrollView) r11
            goto L_0x007f
        L_0x007e:
            r11 = 0
        L_0x007f:
            if (r11 == 0) goto L_0x009b
            boolean r1 = r11 instanceof com.google.android.setupdesign.view.BottomScrollView
            if (r1 == 0) goto L_0x0088
            com.google.android.setupdesign.view.BottomScrollView r11 = (com.google.android.setupdesign.view.BottomScrollView) r11
            goto L_0x009b
        L_0x0088:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Cannot set non-BottomScrollView. Found="
            r1.<init>(r3)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            java.lang.String r1 = "ScrollViewDelegate"
            android.util.Log.w(r1, r11)
        L_0x009b:
            r11 = 2
            android.content.res.ColorStateList r1 = r0.getColorStateList(r11)
            if (r1 == 0) goto L_0x00b9
            r9.primaryColor = r1
            r9.updateBackground()
            com.google.android.setupcompat.template.Mixin r10 = r9.getMixin(r10)
            com.google.android.setupdesign.template.ProgressBarMixin r10 = (com.google.android.setupdesign.template.ProgressBarMixin) r10
            android.widget.ProgressBar r10 = r10.peekProgressBar()
            if (r10 == 0) goto L_0x00b9
            r10.setIndeterminateTintList(r1)
            r10.setProgressBackgroundTintList(r1)
        L_0x00b9:
            boolean r10 = r9.applyPartnerHeavyThemeResource
            if (r10 != 0) goto L_0x00cd
            boolean r10 = r9.shouldApplyPartnerResource()
            if (r10 == 0) goto L_0x00ed
            android.content.Context r10 = r9.getContext()
            boolean r10 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.shouldApplyExtendedPartnerConfig(r10)
            if (r10 == 0) goto L_0x00ed
        L_0x00cd:
            boolean r10 = r9.useFullDynamicColor()
            if (r10 == 0) goto L_0x00d4
            goto L_0x00ed
        L_0x00d4:
            android.content.Context r10 = r9.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r10 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r10)
            android.content.Context r1 = r9.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfig r3 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_LAYOUT_BACKGROUND_COLOR
            int r10 = r10.getColor(r1, r3)
            android.view.View r1 = r9.getRootView()
            r1.setBackgroundColor(r10)
        L_0x00ed:
            r10 = 2131363769(0x7f0a07b9, float:1.8347356E38)
            android.view.View r10 = r9.findViewById(r10)
            r1 = 0
            if (r10 == 0) goto L_0x0134
            boolean r3 = r9.shouldApplyPartnerResource()
            if (r3 == 0) goto L_0x0100
            com.google.android.setupdesign.util.LayoutStyler.applyPartnerCustomizationExtraPaddingStyle(r10)
        L_0x0100:
            android.content.Context r3 = r10.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r5 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r3)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r6 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_CONTENT_PADDING_TOP
            boolean r5 = r5.isPartnerConfigAvailable(r6)
            boolean r7 = r9.shouldApplyPartnerResource()
            if (r7 == 0) goto L_0x0134
            if (r5 == 0) goto L_0x0134
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r5 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r3)
            float r3 = r5.getDimension(r3, r6, r1)
            int r3 = (int) r3
            int r5 = r10.getPaddingTop()
            if (r3 == r5) goto L_0x0134
            int r5 = r10.getPaddingStart()
            int r6 = r10.getPaddingEnd()
            int r7 = r10.getPaddingBottom()
            r10.setPadding(r5, r3, r6, r7)
        L_0x0134:
            android.content.res.Resources r10 = r9.getResources()
            r3 = 2131167694(0x7f0709ce, float:1.7949669E38)
            int r10 = r10.getDimensionPixelSize(r3)
            boolean r3 = r9.shouldApplyPartnerResource()
            if (r3 == 0) goto L_0x0166
            android.content.Context r3 = r9.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r3 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r3)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r5 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_LAND_MIDDLE_HORIZONTAL_SPACING
            boolean r3 = r3.isPartnerConfigAvailable(r5)
            if (r3 == 0) goto L_0x0166
            android.content.Context r10 = r9.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r10 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r10)
            android.content.Context r3 = r9.getContext()
            float r10 = r10.getDimension(r3, r5, r1)
            int r10 = (int) r10
        L_0x0166:
            r3 = 2131363767(0x7f0a07b7, float:1.8347352E38)
            android.view.View r3 = r9.findViewById(r3)
            if (r3 == 0) goto L_0x01c0
            boolean r5 = r9.shouldApplyPartnerResource()
            if (r5 == 0) goto L_0x0197
            android.content.Context r5 = r9.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r5 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r5)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r6 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_LAYOUT_MARGIN_END
            boolean r5 = r5.isPartnerConfigAvailable(r6)
            if (r5 == 0) goto L_0x0197
            android.content.Context r5 = r9.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r5 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r5)
            android.content.Context r7 = r9.getContext()
            float r5 = r5.getDimension(r7, r6, r1)
            int r5 = (int) r5
            goto L_0x01ae
        L_0x0197:
            android.content.Context r5 = r9.getContext()
            r6 = 2130970099(0x7f0405f3, float:1.7548899E38)
            int[] r6 = new int[]{r6}
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6)
            int r6 = r5.getDimensionPixelSize(r2, r2)
            r5.recycle()
            r5 = r6
        L_0x01ae:
            int r6 = r10 / 2
            int r6 = r6 - r5
            int r5 = r3.getPaddingStart()
            int r7 = r3.getPaddingTop()
            int r8 = r3.getPaddingBottom()
            r3.setPadding(r5, r7, r6, r8)
        L_0x01c0:
            r5 = 2131363766(0x7f0a07b6, float:1.834735E38)
            android.view.View r5 = r9.findViewById(r5)
            if (r5 == 0) goto L_0x021d
            boolean r6 = r9.shouldApplyPartnerResource()
            if (r6 == 0) goto L_0x01f1
            android.content.Context r6 = r9.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r6 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r6)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r7 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_LAYOUT_MARGIN_START
            boolean r6 = r6.isPartnerConfigAvailable(r7)
            if (r6 == 0) goto L_0x01f1
            android.content.Context r6 = r9.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r6 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r6)
            android.content.Context r8 = r9.getContext()
            float r1 = r6.getDimension(r8, r7, r1)
            int r1 = (int) r1
            goto L_0x0208
        L_0x01f1:
            android.content.Context r1 = r9.getContext()
            r6 = 2130970100(0x7f0405f4, float:1.75489E38)
            int[] r6 = new int[]{r6}
            android.content.res.TypedArray r1 = r1.obtainStyledAttributes(r6)
            int r6 = r1.getDimensionPixelSize(r2, r2)
            r1.recycle()
            r1 = r6
        L_0x0208:
            if (r3 == 0) goto L_0x020d
            int r10 = r10 / r11
            int r10 = r10 - r1
            goto L_0x020e
        L_0x020d:
            r10 = r2
        L_0x020e:
            int r11 = r5.getPaddingTop()
            int r1 = r5.getPaddingEnd()
            int r3 = r5.getPaddingBottom()
            r5.setPadding(r10, r11, r1, r3)
        L_0x021d:
            android.content.res.ColorStateList r10 = r0.getColorStateList(r2)
            r9.backgroundBaseColor = r10
            r9.updateBackground()
            boolean r10 = r0.getBoolean(r4, r4)
            r9.backgroundPatterned = r10
            r9.updateBackground()
            r10 = 3
            int r10 = r0.getResourceId(r10, r2)
            if (r10 == 0) goto L_0x0245
            r11 = 2131363781(0x7f0a07c5, float:1.834738E38)
            android.view.View r9 = r9.findViewById(r11)
            android.view.ViewStub r9 = (android.view.ViewStub) r9
            r9.setLayoutResource(r10)
            r9.inflate()
        L_0x0245:
            r0.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.GlifLayout.init(android.util.AttributeSet, int):void");
    }

    public final void onFinishInflate() {
        int i;
        ViewGroup.LayoutParams layoutParams;
        PartnerConfig partnerConfig;
        int dimension;
        int i2;
        super.onFinishInflate();
        IconMixin iconMixin = (IconMixin) getMixin(IconMixin.class);
        if (PartnerStyleHelper.shouldApplyPartnerResource(iconMixin.templateLayout)) {
            TemplateLayout templateLayout = iconMixin.templateLayout;
            ImageView imageView = (ImageView) templateLayout.findViewById(2131363773);
            FrameLayout frameLayout = (FrameLayout) templateLayout.findViewById(2131363774);
            if (!(imageView == null || frameLayout == null)) {
                Context context = imageView.getContext();
                int layoutGravity = PartnerStyleHelper.getLayoutGravity(context);
                if (layoutGravity != 0 && (imageView.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                    layoutParams2.gravity = layoutGravity;
                    imageView.setLayoutParams(layoutParams2);
                }
                PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context);
                PartnerConfig partnerConfig2 = PartnerConfig.CONFIG_ICON_SIZE;
                if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig2)) {
                    imageView.getViewTreeObserver().addOnPreDrawListener(new HeaderAreaStyler$1(imageView));
                    ViewGroup.LayoutParams layoutParams3 = imageView.getLayoutParams();
                    layoutParams3.height = (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig2, 0.0f);
                    layoutParams3.width = -2;
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    Drawable drawable = imageView.getDrawable();
                    if (drawable != null && drawable.getIntrinsicWidth() > drawable.getIntrinsicHeight() * 2 && (i2 = layoutParams3.height) > (dimension = (int) context.getResources().getDimension(2131167721))) {
                        i = i2 - dimension;
                        layoutParams3.height = dimension;
                        layoutParams = frameLayout.getLayoutParams();
                        PartnerConfigHelper partnerConfigHelper2 = PartnerConfigHelper.get(context);
                        partnerConfig = PartnerConfig.CONFIG_ICON_MARGIN_TOP;
                        if (partnerConfigHelper2.isPartnerConfigAvailable(partnerConfig) && (layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, ((int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f)) + i, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                        }
                    }
                }
                i = 0;
                layoutParams = frameLayout.getLayoutParams();
                PartnerConfigHelper partnerConfigHelper22 = PartnerConfigHelper.get(context);
                partnerConfig = PartnerConfig.CONFIG_ICON_MARGIN_TOP;
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams2.setMargins(marginLayoutParams2.leftMargin, ((int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f)) + i, marginLayoutParams2.rightMargin, marginLayoutParams2.bottomMargin);
            }
        }
        ((HeaderMixin) getMixin(HeaderMixin.class)).tryApplyPartnerCustomizationStyle();
        TemplateLayout templateLayout2 = ((DescriptionMixin) getMixin(DescriptionMixin.class)).templateLayout;
        TextView textView = (TextView) templateLayout2.findViewById(2131363782);
        PartnerConfig partnerConfig3 = PartnerConfig.CONFIG_DESCRIPTION_LINK_FONT_FAMILY;
        PartnerConfig partnerConfig4 = PartnerConfig.CONFIG_DESCRIPTION_FONT_WEIGHT;
        PartnerConfig partnerConfig5 = PartnerConfig.CONFIG_DESCRIPTION_FONT_FAMILY;
        PartnerConfig partnerConfig6 = PartnerConfig.CONFIG_DESCRIPTION_TEXT_SIZE;
        PartnerConfig partnerConfig7 = PartnerConfig.CONFIG_DESCRIPTION_LINK_TEXT_COLOR;
        PartnerConfig partnerConfig8 = PartnerConfig.CONFIG_DESCRIPTION_TEXT_COLOR;
        if (textView != null && PartnerStyleHelper.shouldApplyPartnerResource(templateLayout2)) {
            TextViewPartnerStyler.applyPartnerCustomizationStyle(textView, new TextViewPartnerStyler.TextPartnerConfigs(partnerConfig8, partnerConfig7, partnerConfig6, partnerConfig5, partnerConfig4, partnerConfig3, PartnerConfig.CONFIG_DESCRIPTION_TEXT_MARGIN_TOP, PartnerConfig.CONFIG_DESCRIPTION_TEXT_MARGIN_BOTTOM, PartnerStyleHelper.getLayoutGravity(textView.getContext())));
        }
        ProgressBarMixin progressBarMixin = (ProgressBarMixin) getMixin(ProgressBarMixin.class);
        ProgressBar peekProgressBar = progressBarMixin.peekProgressBar();
        if (progressBarMixin.useBottomProgressBar && peekProgressBar != null) {
            TemplateLayout templateLayout3 = progressBarMixin.templateLayout;
            if (templateLayout3 instanceof GlifLayout) {
                GlifLayout glifLayout = (GlifLayout) templateLayout3;
                if (glifLayout.applyPartnerHeavyThemeResource || (glifLayout.shouldApplyPartnerResource() && PartnerConfigHelper.shouldApplyExtendedPartnerConfig(glifLayout.getContext()))) {
                    Context context2 = peekProgressBar.getContext();
                    ViewGroup.LayoutParams layoutParams4 = peekProgressBar.getLayoutParams();
                    if (layoutParams4 instanceof ViewGroup.MarginLayoutParams) {
                        ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) layoutParams4;
                        int i3 = marginLayoutParams3.topMargin;
                        PartnerConfigHelper partnerConfigHelper3 = PartnerConfigHelper.get(context2);
                        PartnerConfig partnerConfig9 = PartnerConfig.CONFIG_PROGRESS_BAR_MARGIN_TOP;
                        if (partnerConfigHelper3.isPartnerConfigAvailable(partnerConfig9)) {
                            i3 = (int) PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig9, context2.getResources().getDimension(2131167755));
                        }
                        int i4 = marginLayoutParams3.bottomMargin;
                        PartnerConfigHelper partnerConfigHelper4 = PartnerConfigHelper.get(context2);
                        PartnerConfig partnerConfig10 = PartnerConfig.CONFIG_PROGRESS_BAR_MARGIN_BOTTOM;
                        if (partnerConfigHelper4.isPartnerConfigAvailable(partnerConfig10)) {
                            i4 = (int) PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig10, context2.getResources().getDimension(2131167753));
                        }
                        if (!(i3 == marginLayoutParams3.topMargin && i4 == marginLayoutParams3.bottomMargin)) {
                            marginLayoutParams3.setMargins(marginLayoutParams3.leftMargin, i3, marginLayoutParams3.rightMargin, i4);
                        }
                    }
                }
            }
            Context context3 = peekProgressBar.getContext();
            ViewGroup.LayoutParams layoutParams5 = peekProgressBar.getLayoutParams();
            if (layoutParams5 instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams4 = (ViewGroup.MarginLayoutParams) layoutParams5;
                marginLayoutParams4.setMargins(marginLayoutParams4.leftMargin, (int) context3.getResources().getDimension(2131167755), marginLayoutParams4.rightMargin, (int) context3.getResources().getDimension(2131167753));
            }
        }
        TemplateLayout templateLayout4 = ((ProfileMixin) getMixin(ProfileMixin.class)).templateLayout;
        if (PartnerStyleHelper.shouldApplyPartnerResource(templateLayout4)) {
            ImageView imageView2 = (ImageView) templateLayout4.findViewById(2131363749);
            TextView textView2 = (TextView) templateLayout4.findViewById(2131363750);
            LinearLayout linearLayout = (LinearLayout) templateLayout4.findViewById(2131363777);
            LayoutStyler.applyPartnerCustomizationExtraPaddingStyle(templateLayout4.findViewById(2131363772));
            if (!(imageView2 == null || textView2 == null)) {
                Context context4 = imageView2.getContext();
                ViewGroup.LayoutParams layoutParams6 = imageView2.getLayoutParams();
                if (layoutParams6 instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams5 = (ViewGroup.MarginLayoutParams) layoutParams6;
                    marginLayoutParams5.setMargins(marginLayoutParams5.leftMargin, marginLayoutParams5.topMargin, (int) PartnerConfigHelper.get(context4).getDimension(context4, PartnerConfig.CONFIG_ACCOUNT_AVATAR_MARGIN_END, 0.0f), marginLayoutParams5.bottomMargin);
                }
                imageView2.setMaxHeight((int) PartnerConfigHelper.get(context4).getDimension(context4, PartnerConfig.CONFIG_ACCOUNT_AVATAR_SIZE, context4.getResources().getDimension(2131167590)));
                textView2.setTextSize(0, (float) ((int) PartnerConfigHelper.get(context4).getDimension(context4, PartnerConfig.CONFIG_ACCOUNT_NAME_TEXT_SIZE, context4.getResources().getDimension(2131167591))));
                Typeface create = Typeface.create(PartnerConfigHelper.get(context4).getString(context4, PartnerConfig.CONFIG_ACCOUNT_NAME_FONT_FAMILY), 0);
                if (create != null) {
                    textView2.setTypeface(create);
                }
                linearLayout.setGravity(PartnerStyleHelper.getLayoutGravity(linearLayout.getContext()));
            }
        }
        TextView textView3 = (TextView) findViewById(2131363771);
        if (textView3 == null) {
            return;
        }
        if (this.applyPartnerHeavyThemeResource) {
            TextViewPartnerStyler.applyPartnerCustomizationStyle(textView3, new TextViewPartnerStyler.TextPartnerConfigs(partnerConfig8, partnerConfig7, partnerConfig6, partnerConfig5, partnerConfig4, partnerConfig3, (PartnerConfig) null, (PartnerConfig) null, PartnerStyleHelper.getLayoutGravity(textView3.getContext())));
        } else if (shouldApplyPartnerResource()) {
            int layoutGravity2 = PartnerStyleHelper.getLayoutGravity(textView3.getContext());
            TextViewPartnerStyler.applyPartnerCustomizationVerticalMargins(textView3, new TextViewPartnerStyler.TextPartnerConfigs((PartnerConfig) null, (PartnerConfig) null, (PartnerConfig) null, (PartnerConfig) null, (PartnerConfig) null, (PartnerConfig) null, (PartnerConfig) null, (PartnerConfig) null, layoutGravity2));
            textView3.setGravity(layoutGravity2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0069, code lost:
        if (r6 != 0) goto L_0x0089;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View onInflateTemplate(android.view.LayoutInflater r5, int r6) {
        /*
            r4 = this;
            if (r6 != 0) goto L_0x0089
            android.content.Context r6 = r4.getContext()
            boolean r0 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.isEmbeddedActivityOnePaneEnabled(r6)
            if (r0 == 0) goto L_0x0028
            androidx.window.embedding.ActivityEmbeddingController r0 = androidx.window.embedding.ActivityEmbeddingController.getInstance(r6)
            android.app.Activity r6 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.lookupActivityFromContext(r6)
            androidx.window.embedding.EmbeddingBackend r0 = r0.backend
            androidx.window.embedding.ExtensionEmbeddingBackend r0 = (androidx.window.embedding.ExtensionEmbeddingBackend) r0
            androidx.window.embedding.EmbeddingCompat r0 = r0.embeddingExtension
            if (r0 == 0) goto L_0x0028
            androidx.window.extensions.embedding.ActivityEmbeddingComponent r0 = r0.embeddingExtension
            boolean r6 = r0.isActivityEmbedded(r6)
            if (r6 == 0) goto L_0x0028
            r6 = 2131559075(0x7f0d02a3, float:1.8743484E38)
            goto L_0x0089
        L_0x0028:
            android.content.Context r6 = r4.getContext()
            com.google.android.setupcompat.util.Logger r0 = com.google.android.setupcompat.util.ForceTwoPaneHelper.LOG
            boolean r6 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.isForceTwoPaneEnabled(r6)
            r0 = 2131559114(0x7f0d02ca, float:1.8743563E38)
            if (r6 == 0) goto L_0x0088
            android.content.Context r6 = r4.getContext()
            boolean r1 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.isForceTwoPaneEnabled(r6)
            if (r1 != 0) goto L_0x0042
            goto L_0x0088
        L_0x0042:
            android.content.res.Resources r1 = r6.getResources()     // Catch:{ NotFoundException -> 0x006c }
            java.lang.String r1 = r1.getResourceEntryName(r0)     // Catch:{ NotFoundException -> 0x006c }
            android.content.res.Resources r2 = r6.getResources()     // Catch:{ NotFoundException -> 0x006c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NotFoundException -> 0x006c }
            r3.<init>()     // Catch:{ NotFoundException -> 0x006c }
            r3.append(r1)     // Catch:{ NotFoundException -> 0x006c }
            java.lang.String r1 = "_two_pane"
            r3.append(r1)     // Catch:{ NotFoundException -> 0x006c }
            java.lang.String r1 = r3.toString()     // Catch:{ NotFoundException -> 0x006c }
            java.lang.String r3 = "layout"
            java.lang.String r6 = r6.getPackageName()     // Catch:{ NotFoundException -> 0x006c }
            int r6 = r2.getIdentifier(r1, r3, r6)     // Catch:{ NotFoundException -> 0x006c }
            if (r6 == 0) goto L_0x0088
            goto L_0x0089
        L_0x006c:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r1 = "Resource id 0x"
            r6.<init>(r1)
            java.lang.String r1 = java.lang.Integer.toHexString(r0)
            r6.append(r1)
            java.lang.String r1 = " is not found"
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            com.google.android.setupcompat.util.Logger r1 = com.google.android.setupcompat.util.ForceTwoPaneHelper.LOG
            r1.w(r6)
        L_0x0088:
            r6 = r0
        L_0x0089:
            r0 = 2132017878(0x7f1402d6, float:1.9674047E38)
            android.view.View r4 = r4.inflateTemplate(r5, r0, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.GlifLayout.onInflateTemplate(android.view.LayoutInflater, int):android.view.View");
    }

    public final void updateBackground() {
        int i;
        Drawable drawable;
        if (findViewById(2131363747) != null) {
            ColorStateList colorStateList = this.backgroundBaseColor;
            if (colorStateList != null) {
                i = colorStateList.getDefaultColor();
            } else {
                ColorStateList colorStateList2 = this.primaryColor;
                if (colorStateList2 != null) {
                    i = colorStateList2.getDefaultColor();
                } else {
                    i = 0;
                }
            }
            if (this.backgroundPatterned) {
                drawable = new GlifPatternDrawable(i);
            } else {
                drawable = new ColorDrawable(i);
            }
            ((StatusBarMixin) getMixin(StatusBarMixin.class)).setStatusBarBackground(drawable);
        }
    }

    public GlifLayout(Context context, int i) {
        this(context, i, 0);
    }

    public GlifLayout(Context context, int i, int i2) {
        super(context, i, i2);
        this.backgroundPatterned = true;
        this.applyPartnerHeavyThemeResource = false;
        init((AttributeSet) null, 2130970092);
    }

    public GlifLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.backgroundPatterned = true;
        this.applyPartnerHeavyThemeResource = false;
        init(attributeSet, 2130970092);
    }

    public GlifLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.backgroundPatterned = true;
        this.applyPartnerHeavyThemeResource = false;
        init(attributeSet, i);
    }
}
