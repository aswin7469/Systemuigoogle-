package com.google.android.setupdesign;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.window.embedding.ActivityEmbeddingController;
import androidx.window.embedding.EmbeddingCompat;
import androidx.window.embedding.EmbeddingInterfaceCompat;
import androidx.window.embedding.ExtensionEmbeddingBackend;
import com.google.android.setupcompat.PartnerCustomizationLayout;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.template.StatusBarMixin;
import com.google.android.setupdesign.template.DescriptionMixin;
import com.google.android.setupdesign.template.HeaderMixin;
import com.google.android.setupdesign.template.IconMixin;
import com.google.android.setupdesign.template.IllustrationProgressMixin;
import com.google.android.setupdesign.template.ProfileMixin;
import com.google.android.setupdesign.template.ProgressBarMixin;
import com.google.android.setupdesign.template.RequireScrollMixin;
import com.google.android.setupdesign.util.HeaderAreaStyler$1;
import com.google.android.setupdesign.util.LayoutStyler;
import com.google.android.setupdesign.util.PartnerStyleHelper;
import com.google.android.setupdesign.util.TextViewPartnerStyler;
import com.google.android.setupdesign.view.BottomScrollView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class GlifLayout extends PartnerCustomizationLayout {
    public boolean applyPartnerHeavyThemeResource;
    public ColorStateList backgroundBaseColor;
    public boolean backgroundPatterned;
    public ColorStateList primaryColor;

    public GlifLayout(Context context) {
        this(context, 0, 0);
    }

    public static boolean isEmbeddedActivityOnePaneEnabled(Context context) {
        if (PartnerConfigHelper.isEmbeddedActivityOnePaneEnabled(context)) {
            ActivityEmbeddingController instance = ActivityEmbeddingController.getInstance(context);
            Activity lookupActivityFromContext = PartnerConfigHelper.lookupActivityFromContext(context);
            EmbeddingInterfaceCompat embeddingInterfaceCompat = ((ExtensionEmbeddingBackend) instance.backend).embeddingExtension;
            if (embeddingInterfaceCompat == null || !((EmbeddingCompat) embeddingInterfaceCompat).embeddingExtension.isActivityEmbedded(lookupActivityFromContext)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public ViewGroup findContainer(int i) {
        if (i == 0) {
            i = 2131363735;
        }
        return super.findContainer(i);
    }

    /* JADX WARNING: type inference failed for: r7v1, types: [com.google.android.setupcompat.template.Mixin, java.lang.Object] */
    public final void init$2(AttributeSet attributeSet, int i) {
        boolean z;
        ScrollView scrollView;
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SudGlifLayout, i, 0);
            boolean z2 = obtainStyledAttributes.getBoolean(4, false);
            if (!shouldApplyPartnerResource() || !z2) {
                z = false;
            } else {
                z = true;
            }
            this.applyPartnerHeavyThemeResource = z;
            registerMixin(HeaderMixin.class, new HeaderMixin(this, attributeSet, i));
            registerMixin(DescriptionMixin.class, new DescriptionMixin(this, attributeSet, i));
            registerMixin(IconMixin.class, new IconMixin(this, attributeSet, i));
            registerMixin(ProfileMixin.class, new ProfileMixin(this));
            ProgressBarMixin progressBarMixin = new ProgressBarMixin(this, attributeSet, i);
            Class<ProgressBarMixin> cls = ProgressBarMixin.class;
            registerMixin(cls, progressBarMixin);
            ? obj = new Object();
            IllustrationProgressMixin.ProgressConfig[] progressConfigArr = IllustrationProgressMixin.ProgressConfig.$VALUES;
            getContext();
            registerMixin(IllustrationProgressMixin.class, obj);
            registerMixin(RequireScrollMixin.class, new RequireScrollMixin());
            View findManagedViewById = findManagedViewById(2131363756);
            if (findManagedViewById instanceof ScrollView) {
                scrollView = (ScrollView) findManagedViewById;
            } else {
                scrollView = null;
            }
            if (scrollView != null) {
                if (scrollView instanceof BottomScrollView) {
                    BottomScrollView bottomScrollView = (BottomScrollView) scrollView;
                } else {
                    Log.w("ScrollViewDelegate", "Cannot set non-BottomScrollView. Found=" + scrollView);
                }
            }
            ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(2);
            if (colorStateList != null) {
                this.primaryColor = colorStateList;
                updateBackground();
                ProgressBarMixin progressBarMixin2 = (ProgressBarMixin) getMixin(cls);
                progressBarMixin2.color = colorStateList;
                ProgressBar peekProgressBar = progressBarMixin2.peekProgressBar();
                if (peekProgressBar != null) {
                    peekProgressBar.setIndeterminateTintList(colorStateList);
                    peekProgressBar.setProgressBackgroundTintList(colorStateList);
                }
            }
            if (shouldApplyPartnerHeavyThemeResource() && !useFullDynamicColor()) {
                getRootView().setBackgroundColor(PartnerConfigHelper.get(getContext()).getColor(getContext(), PartnerConfig.CONFIG_LAYOUT_BACKGROUND_COLOR));
            }
            View findManagedViewById2 = findManagedViewById(2131363735);
            if (findManagedViewById2 != null) {
                if (shouldApplyPartnerResource()) {
                    LayoutStyler.applyPartnerCustomizationExtraPaddingStyle(findManagedViewById2);
                }
                if (!(this instanceof GlifPreferenceLayout)) {
                    tryApplyPartnerCustomizationContentPaddingTopStyle(findManagedViewById2);
                }
            }
            updateLandscapeMiddleHorizontalSpacing();
            this.backgroundBaseColor = obtainStyledAttributes.getColorStateList(0);
            updateBackground();
            this.backgroundPatterned = obtainStyledAttributes.getBoolean(1, true);
            updateBackground();
            int resourceId = obtainStyledAttributes.getResourceId(3, 0);
            if (resourceId != 0) {
                ViewStub viewStub = (ViewStub) findManagedViewById(2131363747);
                viewStub.setLayoutResource(resourceId);
                viewStub.inflate();
            }
            obtainStyledAttributes.recycle();
        }
    }

    public final void onFinishInflate() {
        boolean z;
        int i;
        ViewGroup.LayoutParams layoutParams;
        PartnerConfig partnerConfig;
        int dimension;
        int i2;
        super.onFinishInflate();
        IconMixin iconMixin = (IconMixin) getMixin(IconMixin.class);
        if (PartnerStyleHelper.shouldApplyPartnerResource((View) iconMixin.templateLayout)) {
            TemplateLayout templateLayout = iconMixin.templateLayout;
            ImageView imageView = (ImageView) templateLayout.findManagedViewById(2131363739);
            FrameLayout frameLayout = (FrameLayout) templateLayout.findManagedViewById(2131363740);
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
                    if (drawable != null && drawable.getIntrinsicWidth() > drawable.getIntrinsicHeight() * 2 && (i2 = layoutParams3.height) > (dimension = (int) context.getResources().getDimension(2131167676))) {
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
        TextView textView = (TextView) templateLayout2.findManagedViewById(2131363748);
        PartnerConfig partnerConfig3 = PartnerConfig.CONFIG_DESCRIPTION_LINK_FONT_FAMILY;
        PartnerConfig partnerConfig4 = PartnerConfig.CONFIG_DESCRIPTION_FONT_WEIGHT;
        PartnerConfig partnerConfig5 = PartnerConfig.CONFIG_DESCRIPTION_FONT_FAMILY;
        PartnerConfig partnerConfig6 = PartnerConfig.CONFIG_DESCRIPTION_TEXT_SIZE;
        PartnerConfig partnerConfig7 = PartnerConfig.CONFIG_DESCRIPTION_LINK_TEXT_COLOR;
        PartnerConfig partnerConfig8 = PartnerConfig.CONFIG_DESCRIPTION_TEXT_COLOR;
        if (textView != null && PartnerStyleHelper.shouldApplyPartnerResource((View) templateLayout2)) {
            TextViewPartnerStyler.applyPartnerCustomizationStyle(textView, new TextViewPartnerStyler.TextPartnerConfigs(partnerConfig8, partnerConfig7, partnerConfig6, partnerConfig5, partnerConfig4, partnerConfig3, PartnerConfig.CONFIG_DESCRIPTION_TEXT_MARGIN_TOP, PartnerConfig.CONFIG_DESCRIPTION_TEXT_MARGIN_BOTTOM, PartnerStyleHelper.getLayoutGravity(textView.getContext())));
        }
        ProgressBarMixin progressBarMixin = (ProgressBarMixin) getMixin(ProgressBarMixin.class);
        ProgressBar peekProgressBar = progressBarMixin.peekProgressBar();
        if (progressBarMixin.useBottomProgressBar && peekProgressBar != null) {
            TemplateLayout templateLayout3 = progressBarMixin.templateLayout;
            if (!(templateLayout3 instanceof GlifLayout)) {
                z = false;
            } else {
                z = ((GlifLayout) templateLayout3).shouldApplyPartnerHeavyThemeResource();
            }
            if (z) {
                Context context2 = peekProgressBar.getContext();
                ViewGroup.LayoutParams layoutParams4 = peekProgressBar.getLayoutParams();
                if (layoutParams4 instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) layoutParams4;
                    int i3 = marginLayoutParams3.topMargin;
                    PartnerConfigHelper partnerConfigHelper3 = PartnerConfigHelper.get(context2);
                    PartnerConfig partnerConfig9 = PartnerConfig.CONFIG_PROGRESS_BAR_MARGIN_TOP;
                    if (partnerConfigHelper3.isPartnerConfigAvailable(partnerConfig9)) {
                        i3 = (int) PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig9, context2.getResources().getDimension(2131167710));
                    }
                    int i4 = marginLayoutParams3.bottomMargin;
                    PartnerConfigHelper partnerConfigHelper4 = PartnerConfigHelper.get(context2);
                    PartnerConfig partnerConfig10 = PartnerConfig.CONFIG_PROGRESS_BAR_MARGIN_BOTTOM;
                    if (partnerConfigHelper4.isPartnerConfigAvailable(partnerConfig10)) {
                        i4 = (int) PartnerConfigHelper.get(context2).getDimension(context2, partnerConfig10, context2.getResources().getDimension(2131167708));
                    }
                    if (!(i3 == marginLayoutParams3.topMargin && i4 == marginLayoutParams3.bottomMargin)) {
                        marginLayoutParams3.setMargins(marginLayoutParams3.leftMargin, i3, marginLayoutParams3.rightMargin, i4);
                    }
                }
            } else {
                Context context3 = peekProgressBar.getContext();
                ViewGroup.LayoutParams layoutParams5 = peekProgressBar.getLayoutParams();
                if (layoutParams5 instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams4 = (ViewGroup.MarginLayoutParams) layoutParams5;
                    marginLayoutParams4.setMargins(marginLayoutParams4.leftMargin, (int) context3.getResources().getDimension(2131167710), marginLayoutParams4.rightMargin, (int) context3.getResources().getDimension(2131167708));
                }
            }
        }
        TemplateLayout templateLayout4 = ((ProfileMixin) getMixin(ProfileMixin.class)).templateLayout;
        if (PartnerStyleHelper.shouldApplyPartnerResource((View) templateLayout4)) {
            ImageView imageView2 = (ImageView) templateLayout4.findManagedViewById(2131363715);
            TextView textView2 = (TextView) templateLayout4.findManagedViewById(2131363716);
            LinearLayout linearLayout = (LinearLayout) templateLayout4.findManagedViewById(2131363743);
            LayoutStyler.applyPartnerCustomizationExtraPaddingStyle(templateLayout4.findManagedViewById(2131363738));
            if (!(imageView2 == null || textView2 == null)) {
                Context context4 = imageView2.getContext();
                ViewGroup.LayoutParams layoutParams6 = imageView2.getLayoutParams();
                if (layoutParams6 instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams5 = (ViewGroup.MarginLayoutParams) layoutParams6;
                    marginLayoutParams5.setMargins(marginLayoutParams5.leftMargin, marginLayoutParams5.topMargin, (int) PartnerConfigHelper.get(context4).getDimension(context4, PartnerConfig.CONFIG_ACCOUNT_AVATAR_MARGIN_END, 0.0f), marginLayoutParams5.bottomMargin);
                }
                imageView2.setMaxHeight((int) PartnerConfigHelper.get(context4).getDimension(context4, PartnerConfig.CONFIG_ACCOUNT_AVATAR_SIZE, context4.getResources().getDimension(2131167545)));
                textView2.setTextSize(0, (float) ((int) PartnerConfigHelper.get(context4).getDimension(context4, PartnerConfig.CONFIG_ACCOUNT_NAME_TEXT_SIZE, context4.getResources().getDimension(2131167546))));
                Typeface create = Typeface.create(PartnerConfigHelper.get(context4).getString(context4, PartnerConfig.CONFIG_ACCOUNT_NAME_FONT_FAMILY), 0);
                if (create != null) {
                    textView2.setTypeface(create);
                }
                linearLayout.setGravity(PartnerStyleHelper.getLayoutGravity(linearLayout.getContext()));
            }
        }
        TextView textView3 = (TextView) findManagedViewById(2131363737);
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

    public View onInflateTemplate(LayoutInflater layoutInflater, int i) {
        if (i == 0) {
            if (isEmbeddedActivityOnePaneEnabled(getContext())) {
                i = 2131559069;
            } else {
                i = 2131559099;
            }
        }
        return inflateTemplate(layoutInflater, 2132017877, i);
    }

    public final boolean shouldApplyPartnerHeavyThemeResource() {
        if (this.applyPartnerHeavyThemeResource || (shouldApplyPartnerResource() && PartnerConfigHelper.shouldApplyExtendedPartnerConfig(getContext()))) {
            return true;
        }
        return false;
    }

    public final void tryApplyPartnerCustomizationContentPaddingTopStyle(View view) {
        int dimension;
        Context context = view.getContext();
        PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context);
        PartnerConfig partnerConfig = PartnerConfig.CONFIG_CONTENT_PADDING_TOP;
        boolean isPartnerConfigAvailable = partnerConfigHelper.isPartnerConfigAvailable(partnerConfig);
        if (shouldApplyPartnerResource() && isPartnerConfigAvailable && (dimension = (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f)) != view.getPaddingTop()) {
            view.setPadding(view.getPaddingStart(), dimension, view.getPaddingEnd(), view.getPaddingBottom());
        }
    }

    public final void updateBackground() {
        int i;
        Drawable drawable;
        if (findManagedViewById(2131363713) != null) {
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

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00d8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateLandscapeMiddleHorizontalSpacing() {
        /*
            r8 = this;
            android.content.res.Resources r0 = r8.getResources()
            r1 = 2131167649(0x7f0709a1, float:1.7949578E38)
            int r0 = r0.getDimensionPixelSize(r1)
            boolean r1 = r8.shouldApplyPartnerResource()
            r2 = 0
            if (r1 == 0) goto L_0x0033
            android.content.Context r1 = r8.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r1 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r1)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r3 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_LAND_MIDDLE_HORIZONTAL_SPACING
            boolean r1 = r1.isPartnerConfigAvailable(r3)
            if (r1 == 0) goto L_0x0033
            android.content.Context r0 = r8.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r0 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r0)
            android.content.Context r1 = r8.getContext()
            float r0 = r0.getDimension(r1, r3, r2)
            int r0 = (int) r0
        L_0x0033:
            r1 = 2131363733(0x7f0a0795, float:1.8347283E38)
            android.view.View r1 = r8.findManagedViewById(r1)
            r3 = 0
            if (r1 == 0) goto L_0x008e
            boolean r4 = r8.shouldApplyPartnerResource()
            if (r4 == 0) goto L_0x0065
            android.content.Context r4 = r8.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r4 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r4)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r5 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_LAYOUT_MARGIN_END
            boolean r4 = r4.isPartnerConfigAvailable(r5)
            if (r4 == 0) goto L_0x0065
            android.content.Context r4 = r8.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r4 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r4)
            android.content.Context r6 = r8.getContext()
            float r4 = r4.getDimension(r6, r5, r2)
            int r4 = (int) r4
            goto L_0x007c
        L_0x0065:
            android.content.Context r4 = r8.getContext()
            r5 = 2130970099(0x7f0405f3, float:1.7548899E38)
            int[] r5 = new int[]{r5}
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5)
            int r5 = r4.getDimensionPixelSize(r3, r3)
            r4.recycle()
            r4 = r5
        L_0x007c:
            int r5 = r0 / 2
            int r5 = r5 - r4
            int r4 = r1.getPaddingStart()
            int r6 = r1.getPaddingTop()
            int r7 = r1.getPaddingBottom()
            r1.setPadding(r4, r6, r5, r7)
        L_0x008e:
            r4 = 2131363732(0x7f0a0794, float:1.8347281E38)
            android.view.View r4 = r8.findManagedViewById(r4)
            if (r4 == 0) goto L_0x00eb
            boolean r5 = r8.shouldApplyPartnerResource()
            if (r5 == 0) goto L_0x00bf
            android.content.Context r5 = r8.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r5 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r5)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r6 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_LAYOUT_MARGIN_START
            boolean r5 = r5.isPartnerConfigAvailable(r6)
            if (r5 == 0) goto L_0x00bf
            android.content.Context r5 = r8.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r5 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r5)
            android.content.Context r8 = r8.getContext()
            float r8 = r5.getDimension(r8, r6, r2)
            int r8 = (int) r8
            goto L_0x00d6
        L_0x00bf:
            android.content.Context r8 = r8.getContext()
            r2 = 2130970100(0x7f0405f4, float:1.75489E38)
            int[] r2 = new int[]{r2}
            android.content.res.TypedArray r8 = r8.obtainStyledAttributes(r2)
            int r2 = r8.getDimensionPixelSize(r3, r3)
            r8.recycle()
            r8 = r2
        L_0x00d6:
            if (r1 == 0) goto L_0x00dc
            int r0 = r0 / 2
            int r3 = r0 - r8
        L_0x00dc:
            int r8 = r4.getPaddingTop()
            int r0 = r4.getPaddingEnd()
            int r1 = r4.getPaddingBottom()
            r4.setPadding(r3, r8, r0, r1)
        L_0x00eb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.GlifLayout.updateLandscapeMiddleHorizontalSpacing():void");
    }

    public GlifLayout(Context context, int i) {
        this(context, i, 0);
    }

    public GlifLayout(Context context, int i, int i2) {
        super(context, i, i2);
        this.backgroundPatterned = true;
        this.applyPartnerHeavyThemeResource = false;
        init$2((AttributeSet) null, 2130970092);
    }

    public GlifLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.backgroundPatterned = true;
        this.applyPartnerHeavyThemeResource = false;
        init$2(attributeSet, 2130970092);
    }

    public GlifLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.backgroundPatterned = true;
        this.applyPartnerHeavyThemeResource = false;
        init$2(attributeSet, i);
    }
}
