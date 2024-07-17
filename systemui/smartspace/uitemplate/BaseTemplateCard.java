package com.google.android.systemui.smartspace.uitemplate;

import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.SmartspaceUtils;
import android.app.smartspace.uitemplatedata.BaseTemplateData;
import android.app.smartspace.uitemplatedata.Icon;
import android.app.smartspace.uitemplatedata.Text;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.PathInterpolator;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.app.animation.Interpolators;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.BcSmartSpaceUtil;
import com.google.android.systemui.smartspace.BcSmartspaceCardSecondary;
import com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils;
import com.google.android.systemui.smartspace.CardPagerAdapter$onBindViewHolder$1;
import com.google.android.systemui.smartspace.DoubleShadowIconDrawable;
import com.google.android.systemui.smartspace.DoubleShadowTextView;
import com.google.android.systemui.smartspace.IcuDateTextView;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggerUtil;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardMetadataLoggingInfo;
import com.google.android.systemui.smartspace.logging.BcSmartspaceSubcardLoggingInfo;
import com.google.android.systemui.smartspace.utils.ContentDescriptionUtil;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class BaseTemplateCard extends ConstraintLayout {
    public IcuDateTextView mDateView;
    public float mDozeAmount;
    public ViewGroup mExtrasGroup;
    public int mFeatureType;
    public int mIconTintColor;
    public BcSmartspaceCardLoggingInfo mLoggingInfo;
    public String mPrevSmartspaceTargetId;
    public BcSmartspaceCardSecondary mSecondaryCard;
    public ViewGroup mSecondaryCardPane;
    public boolean mShouldShowPageIndicator;
    public ViewGroup mSubtitleGroup;
    public DoubleShadowTextView mSubtitleSupplementalView;
    public DoubleShadowTextView mSubtitleTextView;
    public DoubleShadowTextView mSupplementalLineTextView;
    public SmartspaceTarget mTarget;
    public BaseTemplateData mTemplateData;
    public ViewGroup mTextGroup;
    public DoubleShadowTextView mTitleTextView;
    public String mUiSurface;
    public boolean mValidSecondaryCard;

    public BaseTemplateCard(Context context) {
        this(context, (AttributeSet) null);
    }

    public static boolean shouldTint(BaseTemplateData.SubItemInfo subItemInfo) {
        if (subItemInfo == null || subItemInfo.getIcon() == null) {
            return false;
        }
        return subItemInfo.getIcon().shouldTint();
    }

    public final AccessibilityNodeInfo createAccessibilityNodeInfo() {
        AccessibilityNodeInfo createAccessibilityNodeInfo = super.createAccessibilityNodeInfo();
        createAccessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", " ");
        return createAccessibilityNodeInfo;
    }

    public final int getSubcardIndex(BaseTemplateData.SubItemInfo subItemInfo) {
        BcSmartspaceSubcardLoggingInfo bcSmartspaceSubcardLoggingInfo;
        List list;
        BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo = this.mLoggingInfo;
        if (!(bcSmartspaceCardLoggingInfo == null || (bcSmartspaceSubcardLoggingInfo = bcSmartspaceCardLoggingInfo.mSubcardInfo) == null || (list = bcSmartspaceSubcardLoggingInfo.mSubcards) == null || list.isEmpty() || subItemInfo == null || subItemInfo.getLoggingInfo() == null)) {
            int featureType = subItemInfo.getLoggingInfo().getFeatureType();
            BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo2 = this.mLoggingInfo;
            if (featureType != bcSmartspaceCardLoggingInfo2.mFeatureType) {
                List list2 = bcSmartspaceCardLoggingInfo2.mSubcardInfo.mSubcards;
                BaseTemplateData.SubItemLoggingInfo loggingInfo = subItemInfo.getLoggingInfo();
                for (int i = 0; i < list2.size(); i++) {
                    BcSmartspaceCardMetadataLoggingInfo bcSmartspaceCardMetadataLoggingInfo = (BcSmartspaceCardMetadataLoggingInfo) list2.get(i);
                    if (bcSmartspaceCardMetadataLoggingInfo.mInstanceId == loggingInfo.getInstanceId() && bcSmartspaceCardMetadataLoggingInfo.mCardTypeId == loggingInfo.getFeatureType()) {
                        return i + 1;
                    }
                }
            }
        }
        return 0;
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mTextGroup = (ViewGroup) findViewById(2131363809);
        this.mSecondaryCardPane = (ViewGroup) findViewById(2131363538);
        this.mDateView = (IcuDateTextView) findViewById(2131362361);
        this.mTitleTextView = (DoubleShadowTextView) findViewById(2131363840);
        this.mSubtitleGroup = (ViewGroup) findViewById(2131363631);
        this.mSubtitleTextView = (DoubleShadowTextView) findViewById(2131363708);
        this.mSubtitleSupplementalView = (DoubleShadowTextView) findViewById(2131362060);
        ViewGroup viewGroup = (ViewGroup) findViewById(2131363629);
        this.mExtrasGroup = viewGroup;
        if (viewGroup != null) {
            this.mSupplementalLineTextView = (DoubleShadowTextView) viewGroup.findViewById(2131363758);
        }
    }

    public final void resetTextView(DoubleShadowTextView doubleShadowTextView) {
        if (doubleShadowTextView != null) {
            doubleShadowTextView.setCompoundDrawablesRelative((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            doubleShadowTextView.setOnClickListener((View.OnClickListener) null);
            doubleShadowTextView.setContentDescription((CharSequence) null);
            doubleShadowTextView.setText((CharSequence) null);
            BcSmartspaceTemplateDataUtils.offsetTextViewForIcon(doubleShadowTextView, (DoubleShadowIconDrawable) null, isRtl$2());
        }
    }

    public final void setDozeAmount(float f) {
        boolean z;
        this.mDozeAmount = f;
        SmartspaceTarget smartspaceTarget = this.mTarget;
        if (!(smartspaceTarget == null || smartspaceTarget.getBaseAction() == null || this.mTarget.getBaseAction().getExtras() == null)) {
            Bundle extras = this.mTarget.getBaseAction().getExtras();
            if (this.mTitleTextView != null && extras.getBoolean("hide_title_on_aod")) {
                this.mTitleTextView.setAlpha(1.0f - f);
            }
            if (this.mSubtitleTextView != null && extras.getBoolean("hide_subtitle_on_aod")) {
                this.mSubtitleTextView.setAlpha(1.0f - f);
            }
        }
        if (this.mTextGroup != null) {
            ViewGroup viewGroup = this.mSecondaryCardPane;
            int i = 1;
            int i2 = 0;
            if (this.mDozeAmount == 1.0f || !this.mValidSecondaryCard) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                i2 = 8;
            }
            BcSmartspaceTemplateDataUtils.updateVisibility(viewGroup, i2);
            ViewGroup viewGroup2 = this.mSecondaryCardPane;
            if (viewGroup2 == null || viewGroup2.getVisibility() == 8) {
                this.mTextGroup.setTranslationX(0.0f);
                return;
            }
            ViewGroup viewGroup3 = this.mTextGroup;
            if (!isRtl$2()) {
                i = -1;
            }
            viewGroup3.setTranslationX(((PathInterpolator) Interpolators.EMPHASIZED).getInterpolation(this.mDozeAmount) * ((float) (this.mSecondaryCardPane.getWidth() * i)));
            this.mSecondaryCardPane.setAlpha(Math.max(0.0f, Math.min(1.0f, ((1.0f - this.mDozeAmount) * 9.0f) - 6.0f)));
        }
    }

    public final void setPrimaryTextColor(int i) {
        this.mIconTintColor = i;
        DoubleShadowTextView doubleShadowTextView = this.mTitleTextView;
        if (doubleShadowTextView != null) {
            doubleShadowTextView.setTextColor(i);
            BaseTemplateData baseTemplateData = this.mTemplateData;
            if (baseTemplateData != null) {
                updateTextViewIconTint(this.mTitleTextView, shouldTint(baseTemplateData.getPrimaryItem()));
            }
        }
        IcuDateTextView icuDateTextView = this.mDateView;
        if (icuDateTextView != null) {
            icuDateTextView.setTextColor(i);
        }
        DoubleShadowTextView doubleShadowTextView2 = this.mSubtitleTextView;
        if (doubleShadowTextView2 != null) {
            doubleShadowTextView2.setTextColor(i);
            BaseTemplateData baseTemplateData2 = this.mTemplateData;
            if (baseTemplateData2 != null) {
                updateTextViewIconTint(this.mSubtitleTextView, shouldTint(baseTemplateData2.getSubtitleItem()));
            }
        }
        DoubleShadowTextView doubleShadowTextView3 = this.mSubtitleSupplementalView;
        if (doubleShadowTextView3 != null) {
            doubleShadowTextView3.setTextColor(i);
            BaseTemplateData baseTemplateData3 = this.mTemplateData;
            if (baseTemplateData3 != null) {
                updateTextViewIconTint(this.mSubtitleSupplementalView, shouldTint(baseTemplateData3.getSubtitleSupplementalItem()));
            }
        }
        updateZenColors();
    }

    public final void setUpTextView(DoubleShadowTextView doubleShadowTextView, BaseTemplateData.SubItemInfo subItemInfo, CardPagerAdapter$onBindViewHolder$1 cardPagerAdapter$onBindViewHolder$1) {
        CharSequence charSequence;
        if (doubleShadowTextView == null) {
            Log.d("SsBaseTemplateCard", "No text view can be set up");
            return;
        }
        resetTextView(doubleShadowTextView);
        if (subItemInfo == null) {
            Log.d("SsBaseTemplateCard", "Passed-in item info is null");
            BcSmartspaceTemplateDataUtils.updateVisibility(doubleShadowTextView, 8);
            return;
        }
        Text text = subItemInfo.getText();
        BcSmartspaceTemplateDataUtils.setText(doubleShadowTextView, subItemInfo.getText());
        if (!SmartspaceUtils.isEmpty(text)) {
            doubleShadowTextView.setTextColor(this.mIconTintColor);
        }
        Icon icon = subItemInfo.getIcon();
        if (icon != null) {
            DoubleShadowIconDrawable doubleShadowIconDrawable = new DoubleShadowIconDrawable(getContext());
            android.graphics.drawable.Icon icon2 = icon.getIcon();
            Context context = getContext();
            doubleShadowIconDrawable.setIcon(BcSmartSpaceUtil.getIconDrawableWithCustomSize(icon2, context, context.getResources().getDimensionPixelSize(2131165887)));
            doubleShadowTextView.setCompoundDrawablesRelative(doubleShadowIconDrawable, (Drawable) null, (Drawable) null, (Drawable) null);
            if (SmartspaceUtils.isEmpty(text)) {
                charSequence = "";
            } else {
                charSequence = text.getText();
            }
            ContentDescriptionUtil.setFormattedContentDescription("SsBaseTemplateCard", doubleShadowTextView, charSequence, icon.getContentDescription());
            updateTextViewIconTint(doubleShadowTextView, icon.shouldTint());
            BcSmartspaceTemplateDataUtils.offsetTextViewForIcon(doubleShadowTextView, doubleShadowIconDrawable, isRtl$2());
        }
        BcSmartspaceTemplateDataUtils.updateVisibility(doubleShadowTextView, 0);
        BcSmartSpaceUtil.setOnClickListener((View) doubleShadowTextView, this.mTarget, subItemInfo.getTapAction(), (BcSmartspaceDataPlugin.SmartspaceEventNotifier) cardPagerAdapter$onBindViewHolder$1, "SsBaseTemplateCard", this.mLoggingInfo, getSubcardIndex(subItemInfo));
    }

    public final void updateTextViewIconTint(DoubleShadowTextView doubleShadowTextView, boolean z) {
        for (Drawable drawable : doubleShadowTextView.getCompoundDrawablesRelative()) {
            if (drawable != null) {
                if (z) {
                    drawable.setTint(this.mIconTintColor);
                } else {
                    drawable.setTintList((ColorStateList) null);
                }
            }
        }
    }

    public final void updateZenColors() {
        DoubleShadowTextView doubleShadowTextView = this.mSupplementalLineTextView;
        if (doubleShadowTextView != null) {
            doubleShadowTextView.setTextColor(this.mIconTintColor);
            if (BcSmartspaceCardLoggerUtil.containsValidTemplateType(this.mTemplateData)) {
                updateTextViewIconTint(this.mSupplementalLineTextView, shouldTint(this.mTemplateData.getSupplementalLineItem()));
            }
        }
    }

    public BaseTemplateCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSecondaryCard = null;
        this.mFeatureType = 0;
        this.mLoggingInfo = null;
        this.mIconTintColor = GraphicsUtils.getAttrColor(16842806, getContext());
        this.mPrevSmartspaceTargetId = "";
        this.mTextGroup = null;
        this.mSecondaryCardPane = null;
        this.mDateView = null;
        this.mTitleTextView = null;
        this.mSubtitleGroup = null;
        this.mSubtitleTextView = null;
        this.mSubtitleSupplementalView = null;
        this.mExtrasGroup = null;
        this.mSupplementalLineTextView = null;
        context.getTheme().applyStyle(2132017752, false);
    }
}
