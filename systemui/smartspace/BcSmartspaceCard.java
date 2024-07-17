package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.PathInterpolator;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.app.animation.Interpolators;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardMetadataLoggingInfo;
import com.google.android.systemui.smartspace.logging.BcSmartspaceSubcardLoggingInfo;
import com.google.android.systemui.smartspace.utils.ContentDescriptionUtil;
import java.util.List;
import java.util.Locale;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class BcSmartspaceCard extends ConstraintLayout {
    public final DoubleShadowIconDrawable mBaseActionIconDrawable;
    public DoubleShadowTextView mBaseActionIconSubtitleView;
    public IcuDateTextView mDateView;
    public float mDozeAmount;
    public BcSmartspaceDataPlugin.SmartspaceEventNotifier mEventNotifier;
    public final DoubleShadowIconDrawable mIconDrawable;
    public int mIconTintColor;
    public String mPrevSmartspaceTargetId;
    public BcSmartspaceCardSecondary mSecondaryCard;
    public ViewGroup mSecondaryCardGroup;
    public TextView mSubtitleTextView;
    public SmartspaceTarget mTarget;
    public ViewGroup mTextGroup;
    public TextView mTitleTextView;
    public boolean mUsePageIndicatorUi;
    public boolean mValidSecondaryCard;

    public BcSmartspaceCard(Context context) {
        this(context, (AttributeSet) null);
    }

    public static int getClickedIndex(BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo, int i) {
        List list;
        BcSmartspaceSubcardLoggingInfo bcSmartspaceSubcardLoggingInfo = bcSmartspaceCardLoggingInfo.mSubcardInfo;
        if (bcSmartspaceSubcardLoggingInfo == null || (list = bcSmartspaceSubcardLoggingInfo.mSubcards) == null) {
            return 0;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            BcSmartspaceCardMetadataLoggingInfo bcSmartspaceCardMetadataLoggingInfo = (BcSmartspaceCardMetadataLoggingInfo) list.get(i2);
            if (bcSmartspaceCardMetadataLoggingInfo != null && bcSmartspaceCardMetadataLoggingInfo.mCardTypeId == i) {
                return i2 + 1;
            }
        }
        return 0;
    }

    public final AccessibilityNodeInfo createAccessibilityNodeInfo() {
        AccessibilityNodeInfo createAccessibilityNodeInfo = super.createAccessibilityNodeInfo();
        createAccessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", " ");
        return createAccessibilityNodeInfo;
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mTextGroup = (ViewGroup) findViewById(2131363809);
        this.mSecondaryCardGroup = (ViewGroup) findViewById(2131363538);
        this.mDateView = (IcuDateTextView) findViewById(2131362361);
        this.mTitleTextView = (TextView) findViewById(2131363840);
        this.mSubtitleTextView = (TextView) findViewById(2131363708);
        this.mBaseActionIconSubtitleView = (DoubleShadowTextView) findViewById(2131362060);
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
            ViewGroup viewGroup = this.mSecondaryCardGroup;
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
            SmartspaceTarget smartspaceTarget2 = this.mTarget;
            if (smartspaceTarget2 == null || smartspaceTarget2.getFeatureType() != 30) {
                ViewGroup viewGroup2 = this.mSecondaryCardGroup;
                if (viewGroup2 == null || viewGroup2.getVisibility() == 8) {
                    this.mTextGroup.setTranslationX(0.0f);
                    return;
                }
                ViewGroup viewGroup3 = this.mTextGroup;
                if (!isRtl$2()) {
                    i = -1;
                }
                viewGroup3.setTranslationX(((PathInterpolator) Interpolators.EMPHASIZED).getInterpolation(this.mDozeAmount) * ((float) (this.mSecondaryCardGroup.getWidth() * i)));
                this.mSecondaryCardGroup.setAlpha(Math.max(0.0f, Math.min(1.0f, ((1.0f - this.mDozeAmount) * 9.0f) - 6.0f)));
            }
        }
    }

    public final void setPrimaryOnClickListener(SmartspaceTarget smartspaceTarget, SmartspaceAction smartspaceAction, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        if (smartspaceAction != null) {
            BcSmartSpaceUtil.setOnClickListener((View) this, smartspaceTarget, smartspaceAction, this.mEventNotifier, "BcSmartspaceCard", bcSmartspaceCardLoggingInfo, 0);
            TextView textView = this.mTitleTextView;
            if (textView != null) {
                BcSmartSpaceUtil.setOnClickListener((View) textView, smartspaceTarget, smartspaceAction, this.mEventNotifier, "BcSmartspaceCard", bcSmartspaceCardLoggingInfo, 0);
            }
            TextView textView2 = this.mSubtitleTextView;
            if (textView2 != null) {
                BcSmartSpaceUtil.setOnClickListener((View) textView2, smartspaceTarget, smartspaceAction, this.mEventNotifier, "BcSmartspaceCard", bcSmartspaceCardLoggingInfo, 0);
            }
        }
    }

    public final void setPrimaryTextColor(int i) {
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextColor(i);
        }
        IcuDateTextView icuDateTextView = this.mDateView;
        if (icuDateTextView != null) {
            icuDateTextView.setTextColor(i);
        }
        TextView textView2 = this.mSubtitleTextView;
        if (textView2 != null) {
            textView2.setTextColor(i);
        }
        DoubleShadowTextView doubleShadowTextView = this.mBaseActionIconSubtitleView;
        if (doubleShadowTextView != null) {
            doubleShadowTextView.setTextColor(i);
        }
        BcSmartspaceCardSecondary bcSmartspaceCardSecondary = this.mSecondaryCard;
        if (bcSmartspaceCardSecondary != null) {
            bcSmartspaceCardSecondary.setTextColor(i);
        }
        this.mIconTintColor = i;
        updateIconTint();
    }

    public final void setSubtitle(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        DoubleShadowIconDrawable doubleShadowIconDrawable;
        int i;
        TextView textView = this.mSubtitleTextView;
        if (textView == null) {
            Log.w("BcSmartspaceCard", "No subtitle view to update");
            return;
        }
        textView.setText(charSequence);
        TextView textView2 = this.mSubtitleTextView;
        DoubleShadowIconDrawable doubleShadowIconDrawable2 = null;
        if (TextUtils.isEmpty(charSequence) || !z) {
            doubleShadowIconDrawable = null;
        } else {
            doubleShadowIconDrawable = this.mIconDrawable;
        }
        textView2.setCompoundDrawablesRelative(doubleShadowIconDrawable, (Drawable) null, (Drawable) null, (Drawable) null);
        TextView textView3 = this.mSubtitleTextView;
        SmartspaceTarget smartspaceTarget = this.mTarget;
        if (smartspaceTarget == null || smartspaceTarget.getFeatureType() != 5 || this.mUsePageIndicatorUi) {
            i = 1;
        } else {
            i = 2;
        }
        textView3.setMaxLines(i);
        ContentDescriptionUtil.setFormattedContentDescription("BcSmartspaceCard", this.mSubtitleTextView, charSequence, charSequence2);
        TextView textView4 = this.mSubtitleTextView;
        if (z) {
            doubleShadowIconDrawable2 = this.mIconDrawable;
        }
        BcSmartspaceTemplateDataUtils.offsetTextViewForIcon(textView4, doubleShadowIconDrawable2, isRtl$2());
    }

    public final void setTitle(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        SmartspaceAction smartspaceAction;
        Bundle bundle;
        boolean z2;
        DoubleShadowIconDrawable doubleShadowIconDrawable;
        TextView textView = this.mTitleTextView;
        if (textView == null) {
            Log.w("BcSmartspaceCard", "No title view to update");
            return;
        }
        textView.setText(charSequence);
        SmartspaceTarget smartspaceTarget = this.mTarget;
        DoubleShadowIconDrawable doubleShadowIconDrawable2 = null;
        if (smartspaceTarget == null) {
            smartspaceAction = null;
        } else {
            smartspaceAction = smartspaceTarget.getHeaderAction();
        }
        if (smartspaceAction == null) {
            bundle = null;
        } else {
            bundle = smartspaceAction.getExtras();
        }
        if (bundle == null || !bundle.containsKey("titleEllipsize")) {
            SmartspaceTarget smartspaceTarget2 = this.mTarget;
            if (smartspaceTarget2 == null || smartspaceTarget2.getFeatureType() != 2 || !Locale.ENGLISH.getLanguage().equals(this.mContext.getResources().getConfiguration().locale.getLanguage())) {
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
            } else {
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            }
        } else {
            String string = bundle.getString("titleEllipsize");
            try {
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.valueOf(string));
            } catch (IllegalArgumentException unused) {
                Log.w("BcSmartspaceCard", "Invalid TruncateAt value: " + string);
            }
        }
        boolean z3 = false;
        if (bundle != null) {
            int i = bundle.getInt("titleMaxLines");
            if (i != 0) {
                this.mTitleTextView.setMaxLines(i);
            }
            z2 = bundle.getBoolean("disableTitleIcon");
        } else {
            z2 = false;
        }
        if (z && !z2) {
            z3 = true;
        }
        if (z3) {
            ContentDescriptionUtil.setFormattedContentDescription("BcSmartspaceCard", this.mTitleTextView, charSequence, charSequence2);
        }
        TextView textView2 = this.mTitleTextView;
        if (z3) {
            doubleShadowIconDrawable = this.mIconDrawable;
        } else {
            doubleShadowIconDrawable = null;
        }
        textView2.setCompoundDrawablesRelative(doubleShadowIconDrawable, (Drawable) null, (Drawable) null, (Drawable) null);
        TextView textView3 = this.mTitleTextView;
        if (z3) {
            doubleShadowIconDrawable2 = this.mIconDrawable;
        }
        BcSmartspaceTemplateDataUtils.offsetTextViewForIcon(textView3, doubleShadowIconDrawable2, isRtl$2());
    }

    public final void updateIconTint() {
        DoubleShadowIconDrawable doubleShadowIconDrawable;
        SmartspaceTarget smartspaceTarget = this.mTarget;
        if (smartspaceTarget != null && (doubleShadowIconDrawable = this.mIconDrawable) != null) {
            if (smartspaceTarget.getFeatureType() != 1) {
                doubleShadowIconDrawable.setTint(this.mIconTintColor);
            } else {
                doubleShadowIconDrawable.setTintList((ColorStateList) null);
            }
            DoubleShadowIconDrawable doubleShadowIconDrawable2 = this.mBaseActionIconDrawable;
            if (doubleShadowIconDrawable2 != null) {
                SmartspaceAction baseAction = this.mTarget.getBaseAction();
                if (baseAction == null || baseAction.getExtras() == null || baseAction.getExtras().isEmpty() || baseAction.getExtras().getInt("subcardType", -1) != 1) {
                    doubleShadowIconDrawable2.setTint(this.mIconTintColor);
                } else {
                    doubleShadowIconDrawable2.setTintList((ColorStateList) null);
                }
            }
        }
    }

    public BcSmartspaceCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSecondaryCard = null;
        this.mPrevSmartspaceTargetId = "";
        this.mIconTintColor = GraphicsUtils.getAttrColor(16842806, getContext());
        this.mTextGroup = null;
        this.mSecondaryCardGroup = null;
        this.mDateView = null;
        this.mTitleTextView = null;
        this.mSubtitleTextView = null;
        this.mBaseActionIconSubtitleView = null;
        context.getTheme().applyStyle(2132017752, false);
        this.mIconDrawable = new DoubleShadowIconDrawable(context);
        this.mBaseActionIconDrawable = new DoubleShadowIconDrawable(context);
    }
}
