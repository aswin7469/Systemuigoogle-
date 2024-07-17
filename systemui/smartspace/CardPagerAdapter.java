package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.SmartspaceUtils;
import android.app.smartspace.uitemplatedata.BaseTemplateData;
import android.app.smartspace.uitemplatedata.TapAction;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.graphics.ColorUtils;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.systemui.plugins.BcSmartspaceConfigPlugin;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggerUtil;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import com.google.android.systemui.smartspace.logging.BcSmartspaceSubcardLoggingInfo;
import com.google.android.systemui.smartspace.uitemplate.BaseTemplateCard;
import com.google.android.systemui.smartspace.utils.ContentDescriptionUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CardPagerAdapter extends PagerAdapter {
    public static final Companion Companion = new Object();
    public final List _aodTargets = new ArrayList();
    public final List _lockscreenTargets = new ArrayList();
    public BcSmartspaceConfigPlugin configProvider;
    public int currentTextColor;
    public BcSmartspaceDataPlugin dataProvider;
    public float dozeAmount;
    public final int dozeColor = -1;
    public final LazyServerFlagLoader enableCardRecycling = new LazyServerFlagLoader("enable_card_recycling");
    public final LazyServerFlagLoader enableReducedCardRecycling = new LazyServerFlagLoader("enable_reduced_card_recycling");
    public boolean hasAodLockscreenTransition;
    public boolean hasDifferentTargets;
    public boolean keyguardBypassEnabled;
    public final List mediaTargets = new ArrayList();
    public float previousDozeAmount;
    public int primaryTextColor;
    public final SparseArray recycledCards = new SparseArray();
    public final SparseArray recycledLegacyCards = new SparseArray();
    public final View root;
    public List smartspaceTargets = new ArrayList();
    public TransitionType transitioningTo;
    public String uiSurface;
    public final SparseArray viewHolders = new SparseArray();

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public enum TransitionType {
        ;

        /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.systemui.smartspace.CardPagerAdapter$TransitionType, java.lang.Enum] */
        /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.systemui.smartspace.CardPagerAdapter$TransitionType, java.lang.Enum] */
        /* JADX WARNING: type inference failed for: r2v2, types: [com.google.android.systemui.smartspace.CardPagerAdapter$TransitionType, java.lang.Enum] */
        static {
            TransitionType[] transitionTypeArr;
            EnumEntriesKt.enumEntries(transitionTypeArr);
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class ViewHolder {
        public final BaseTemplateCard card;
        public final BcSmartspaceCard legacyCard;
        public final int position;
        public SmartspaceTarget target;

        public ViewHolder(int i, BcSmartspaceCard bcSmartspaceCard, SmartspaceTarget smartspaceTarget, BaseTemplateCard baseTemplateCard) {
            this.position = i;
            this.legacyCard = bcSmartspaceCard;
            this.target = smartspaceTarget;
            this.card = baseTemplateCard;
        }
    }

    public CardPagerAdapter(View view, BcSmartspaceConfigPlugin bcSmartspaceConfigPlugin) {
        this.root = view;
        int attrColor = GraphicsUtils.getAttrColor(16842806, view.getContext());
        this.primaryTextColor = attrColor;
        this.currentTextColor = attrColor;
        this.configProvider = bcSmartspaceConfigPlugin;
        this.transitioningTo = TransitionType.NOT_IN_TRANSITION;
    }

    public static final int getBaseLegacyCardRes(int i) {
        return Companion.getBaseLegacyCardRes(i);
    }

    public static final int getLegacySecondaryCardRes(int i) {
        return Companion.getLegacySecondaryCardRes(i);
    }

    public static final boolean useRecycledViewForNewTarget(SmartspaceTarget smartspaceTarget, SmartspaceTarget smartspaceTarget2) {
        return Companion.useRecycledViewForNewTarget(smartspaceTarget, smartspaceTarget2);
    }

    public final void addDefaultDateCardIfEmpty(List list) {
        if (list.isEmpty()) {
            View view = this.root;
            list.add(new SmartspaceTarget.Builder("date_card_794317_92634", new ComponentName(view.getContext(), CardPagerAdapter.class), view.getContext().getUser()).setFeatureType(1).setTemplateData(new BaseTemplateData.Builder(1).build()).build());
        }
    }

    public final void destroyItem(int i, ViewGroup viewGroup, Object obj) {
        ViewHolder viewHolder = (ViewHolder) obj;
        BcSmartspaceCard bcSmartspaceCard = viewHolder.legacyCard;
        LazyServerFlagLoader lazyServerFlagLoader = this.enableCardRecycling;
        if (bcSmartspaceCard != null) {
            SmartspaceTarget smartspaceTarget = bcSmartspaceCard.mTarget;
            if (smartspaceTarget != null && lazyServerFlagLoader.get()) {
                this.recycledLegacyCards.put(Companion.getFeatureType(smartspaceTarget), bcSmartspaceCard);
            }
            viewGroup.removeView(bcSmartspaceCard);
        }
        BaseTemplateCard baseTemplateCard = viewHolder.card;
        if (baseTemplateCard != null) {
            SmartspaceTarget smartspaceTarget2 = baseTemplateCard.mTarget;
            if (smartspaceTarget2 != null && lazyServerFlagLoader.get()) {
                this.recycledCards.put(smartspaceTarget2.getFeatureType(), baseTemplateCard);
            }
            viewGroup.removeView(baseTemplateCard);
        }
        SparseArray sparseArray = this.viewHolders;
        if (sparseArray.get(i) == viewHolder) {
            sparseArray.remove(i);
        }
    }

    public final int getCount() {
        return this.smartspaceTargets.size();
    }

    public final int getItemPosition(Object obj) {
        ViewHolder viewHolder = (ViewHolder) obj;
        SmartspaceTarget targetAtPosition = getTargetAtPosition(viewHolder.position);
        if (viewHolder.target == targetAtPosition) {
            return -1;
        }
        if (targetAtPosition == null || Companion.getFeatureType(targetAtPosition) != Companion.getFeatureType(viewHolder.target) || !Intrinsics.areEqual(targetAtPosition.getSmartspaceTargetId(), viewHolder.target.getSmartspaceTargetId())) {
            return -2;
        }
        viewHolder.target = targetAtPosition;
        onBindViewHolder(viewHolder);
        return -1;
    }

    public final List getLockscreenTargets() {
        List list = this._lockscreenTargets;
        if (!isMediaPreferred(list) || !this.keyguardBypassEnabled) {
            return list;
        }
        return this.mediaTargets;
    }

    public final SmartspaceTarget getTargetAtPosition(int i) {
        if (this.smartspaceTargets.isEmpty() || i < 0 || i >= this.smartspaceTargets.size()) {
            return null;
        }
        return (SmartspaceTarget) this.smartspaceTargets.get(i);
    }

    public final Object instantiateItem(int i, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        BcSmartspaceCard bcSmartspaceCard;
        BcSmartspaceCard bcSmartspaceCard2;
        BaseTemplateCard baseTemplateCard;
        BaseTemplateData.SubItemInfo subItemInfo;
        int i2;
        int i3;
        int i4 = i;
        ViewGroup viewGroup2 = viewGroup;
        SmartspaceTarget smartspaceTarget = (SmartspaceTarget) this.smartspaceTargets.get(i4);
        boolean containsValidTemplateType = BcSmartspaceCardLoggerUtil.containsValidTemplateType(smartspaceTarget.getTemplateData());
        Companion companion = Companion;
        LazyServerFlagLoader lazyServerFlagLoader = this.enableReducedCardRecycling;
        LazyServerFlagLoader lazyServerFlagLoader2 = this.enableCardRecycling;
        if (containsValidTemplateType) {
            Log.i("SsCardPagerAdapter", "Use UI template for the feature: " + smartspaceTarget.getFeatureType());
            if (lazyServerFlagLoader2.get()) {
                baseTemplateCard = (BaseTemplateCard) this.recycledCards.removeReturnOld(smartspaceTarget.getFeatureType());
            } else {
                baseTemplateCard = null;
            }
            if (baseTemplateCard == null || (lazyServerFlagLoader.get() && !companion.useRecycledViewForNewTarget(smartspaceTarget, baseTemplateCard.mTarget))) {
                BaseTemplateData templateData = smartspaceTarget.getTemplateData();
                if (templateData != null) {
                    subItemInfo = templateData.getPrimaryItem();
                } else {
                    subItemInfo = null;
                }
                if (subItemInfo == null || (SmartspaceUtils.isEmpty(subItemInfo.getText()) && subItemInfo.getIcon() == null)) {
                    i2 = 2131559021;
                } else {
                    i2 = 2131559020;
                }
                LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
                BaseTemplateCard baseTemplateCard2 = (BaseTemplateCard) from.inflate(i2, viewGroup2, false);
                String str = this.uiSurface;
                baseTemplateCard2.mUiSurface = str;
                if (baseTemplateCard2.mDateView != null && TextUtils.equals(str, BcSmartspaceDataPlugin.UI_SURFACE_LOCK_SCREEN_AOD)) {
                    IcuDateTextView icuDateTextView = baseTemplateCard2.mDateView;
                    if (!icuDateTextView.isAttachedToWindow()) {
                        icuDateTextView.mUpdatesOnAod = true;
                    } else {
                        throw new IllegalStateException("Must call before attaching view to window.");
                    }
                }
                if (templateData != null) {
                    switch (templateData.getTemplateType()) {
                        case 2:
                            i3 = 2131559043;
                            break;
                        case 3:
                            i3 = 2131559044;
                            break;
                        case 4:
                            i3 = 2131559036;
                            break;
                        case 5:
                            i3 = 2131559041;
                            break;
                        case 6:
                            i3 = 2131559038;
                            break;
                        case ViewNode.WIDTH_FIELD_NUMBER:
                            i3 = 2131559042;
                            break;
                        default:
                            i3 = 0;
                            break;
                    }
                    if (i3 != 0) {
                        BcSmartspaceCardSecondary bcSmartspaceCardSecondary = (BcSmartspaceCardSecondary) from.inflate(i3, baseTemplateCard2, false);
                        Log.i("SsCardPagerAdapter", "Secondary card is found");
                        ViewGroup viewGroup3 = baseTemplateCard2.mSecondaryCardPane;
                        if (viewGroup3 != null) {
                            baseTemplateCard2.mSecondaryCard = bcSmartspaceCardSecondary;
                            BcSmartspaceTemplateDataUtils.updateVisibility(viewGroup3, 8);
                            baseTemplateCard2.mSecondaryCardPane.removeAllViews();
                            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(baseTemplateCard2.getResources().getDimensionPixelSize(2131165912));
                            layoutParams.setMarginStart(baseTemplateCard2.getResources().getDimensionPixelSize(2131165923));
                            layoutParams.startToStart = 0;
                            layoutParams.topToTop = 0;
                            layoutParams.bottomToBottom = 0;
                            baseTemplateCard2.mSecondaryCardPane.addView(bcSmartspaceCardSecondary, layoutParams);
                        }
                    }
                }
                baseTemplateCard = baseTemplateCard2;
            }
            viewHolder = new ViewHolder(i4, (BcSmartspaceCard) null, smartspaceTarget, baseTemplateCard);
            viewGroup2.addView(baseTemplateCard);
        } else {
            if (lazyServerFlagLoader2.get()) {
                bcSmartspaceCard = (BcSmartspaceCard) this.recycledLegacyCards.removeReturnOld(Companion.getFeatureType(smartspaceTarget));
            } else {
                bcSmartspaceCard = null;
            }
            if (bcSmartspaceCard == null || (lazyServerFlagLoader.get() && !companion.useRecycledViewForNewTarget(smartspaceTarget, bcSmartspaceCard.mTarget))) {
                int featureType = Companion.getFeatureType(smartspaceTarget);
                LayoutInflater from2 = LayoutInflater.from(viewGroup.getContext());
                int baseLegacyCardRes = companion.getBaseLegacyCardRes(featureType);
                if (baseLegacyCardRes == 0) {
                    RecordingInputConnection$$ExternalSyntheticOutline0.m("No legacy card can be created for feature type: ", "SsCardPagerAdapter", featureType);
                    bcSmartspaceCard2 = null;
                } else {
                    BcSmartspaceCard bcSmartspaceCard3 = (BcSmartspaceCard) from2.inflate(baseLegacyCardRes, viewGroup2, false);
                    String str2 = this.uiSurface;
                    bcSmartspaceCard3.getClass();
                    if (bcSmartspaceCard3.mDateView != null && TextUtils.equals(str2, BcSmartspaceDataPlugin.UI_SURFACE_LOCK_SCREEN_AOD)) {
                        IcuDateTextView icuDateTextView2 = bcSmartspaceCard3.mDateView;
                        if (!icuDateTextView2.isAttachedToWindow()) {
                            icuDateTextView2.mUpdatesOnAod = true;
                        } else {
                            throw new IllegalStateException("Must call before attaching view to window.");
                        }
                    }
                    int legacySecondaryCardRes = companion.getLegacySecondaryCardRes(featureType);
                    if (legacySecondaryCardRes != 0) {
                        BcSmartspaceCardSecondary bcSmartspaceCardSecondary2 = (BcSmartspaceCardSecondary) from2.inflate(legacySecondaryCardRes, bcSmartspaceCard3, false);
                        ViewGroup viewGroup4 = bcSmartspaceCard3.mSecondaryCardGroup;
                        if (viewGroup4 != null) {
                            bcSmartspaceCard3.mSecondaryCard = bcSmartspaceCardSecondary2;
                            BcSmartspaceTemplateDataUtils.updateVisibility(viewGroup4, 8);
                            bcSmartspaceCard3.mSecondaryCardGroup.removeAllViews();
                            ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(bcSmartspaceCard3.getResources().getDimensionPixelSize(2131165912));
                            layoutParams2.setMarginStart(bcSmartspaceCard3.getResources().getDimensionPixelSize(2131165923));
                            layoutParams2.startToStart = 0;
                            layoutParams2.topToTop = 0;
                            layoutParams2.bottomToBottom = 0;
                            bcSmartspaceCard3.mSecondaryCardGroup.addView(bcSmartspaceCardSecondary2, layoutParams2);
                        }
                    }
                    bcSmartspaceCard2 = bcSmartspaceCard3;
                }
                bcSmartspaceCard = bcSmartspaceCard2;
            }
            viewHolder = new ViewHolder(i4, bcSmartspaceCard, smartspaceTarget, (BaseTemplateCard) null);
            if (bcSmartspaceCard != null) {
                viewGroup2.addView(bcSmartspaceCard);
            }
        }
        onBindViewHolder(viewHolder);
        this.viewHolders.put(i4, viewHolder);
        return viewHolder;
    }

    public final boolean isMediaPreferred(List list) {
        if (!list.isEmpty()) {
            if (list.size() != 1) {
                return false;
            }
            Object obj = list.get(0);
            Intrinsics.checkNotNull(obj);
            if (((SmartspaceTarget) obj).getFeatureType() != 1) {
                return false;
            }
        }
        if (!this.mediaTargets.isEmpty()) {
            return true;
        }
        return false;
    }

    public final boolean isViewFromObject(View view, Object obj) {
        ViewHolder viewHolder = (ViewHolder) obj;
        if (view == viewHolder.legacyCard || view == viewHolder.card) {
            return true;
        }
        return false;
    }

    public final void onBindViewHolder(ViewHolder viewHolder) {
        BcSmartspaceSubcardLoggingInfo bcSmartspaceSubcardLoggingInfo;
        CardPagerAdapter$onBindViewHolder$1 cardPagerAdapter$onBindViewHolder$1;
        boolean z;
        String str;
        Drawable drawable;
        int i;
        int i2;
        int i3;
        boolean z2;
        boolean z3;
        boolean z4;
        CardPagerAdapter$onBindViewHolder$1 cardPagerAdapter$onBindViewHolder$12;
        boolean z5;
        String str2;
        int i4;
        CardPagerAdapter$onBindViewHolder$1 cardPagerAdapter$onBindViewHolder$13;
        String str3;
        DoubleShadowTextView doubleShadowTextView;
        DoubleShadowTextView doubleShadowTextView2;
        String str4;
        int i5;
        ViewHolder viewHolder2 = viewHolder;
        SmartspaceTarget smartspaceTarget = (SmartspaceTarget) this.smartspaceTargets.get(viewHolder2.position);
        boolean containsValidTemplateType = BcSmartspaceCardLoggerUtil.containsValidTemplateType(smartspaceTarget.getTemplateData());
        BcSmartspaceCardLoggingInfo.Builder builder = new BcSmartspaceCardLoggingInfo.Builder();
        builder.mInstanceId = InstanceId.create(smartspaceTarget);
        builder.mFeatureType = smartspaceTarget.getFeatureType();
        builder.mDisplaySurface = BcSmartSpaceUtil.getLoggingDisplaySurface(this.dozeAmount, this.uiSurface);
        builder.mRank = viewHolder2.position;
        builder.mCardinality = this.smartspaceTargets.size();
        this.root.getContext().getPackageManager();
        builder.mUid = -1;
        if (containsValidTemplateType) {
            bcSmartspaceSubcardLoggingInfo = BcSmartspaceCardLoggerUtil.createSubcardLoggingInfo(smartspaceTarget.getTemplateData());
        } else {
            bcSmartspaceSubcardLoggingInfo = BcSmartspaceCardLoggerUtil.createSubcardLoggingInfo(smartspaceTarget);
        }
        builder.mSubcardInfo = bcSmartspaceSubcardLoggingInfo;
        builder.mDimensionalInfo = BcSmartspaceCardLoggerUtil.createDimensionalLoggingInfo(smartspaceTarget.getTemplateData());
        BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo = new BcSmartspaceCardLoggingInfo(builder);
        int i6 = 8;
        if (containsValidTemplateType) {
            BaseTemplateData templateData = smartspaceTarget.getTemplateData();
            if (templateData != null) {
                BcSmartspaceCardLoggerUtil.tryForcePrimaryFeatureTypeOrUpdateLogInfoFromTemplateData(bcSmartspaceCardLoggingInfo, templateData);
                BaseTemplateCard baseTemplateCard = viewHolder2.card;
                if (baseTemplateCard == null) {
                    Log.w("SsCardPagerAdapter", "No ui-template card view can be binded");
                    return;
                }
                if (this.dataProvider == null) {
                    cardPagerAdapter$onBindViewHolder$12 = null;
                } else {
                    cardPagerAdapter$onBindViewHolder$12 = new CardPagerAdapter$onBindViewHolder$1(this, 0);
                }
                if (this.smartspaceTargets.size() > 1) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (!smartspaceTarget.getSmartspaceTargetId().equals(baseTemplateCard.mPrevSmartspaceTargetId)) {
                    baseTemplateCard.mTarget = null;
                    baseTemplateCard.mTemplateData = null;
                    baseTemplateCard.mFeatureType = 0;
                    baseTemplateCard.mLoggingInfo = null;
                    baseTemplateCard.setOnClickListener((View.OnClickListener) null);
                    IcuDateTextView icuDateTextView = baseTemplateCard.mDateView;
                    if (icuDateTextView != null) {
                        icuDateTextView.setOnClickListener((View.OnClickListener) null);
                    }
                    baseTemplateCard.resetTextView(baseTemplateCard.mTitleTextView);
                    baseTemplateCard.resetTextView(baseTemplateCard.mSubtitleTextView);
                    baseTemplateCard.resetTextView(baseTemplateCard.mSubtitleSupplementalView);
                    baseTemplateCard.resetTextView(baseTemplateCard.mSupplementalLineTextView);
                    BcSmartspaceTemplateDataUtils.updateVisibility(baseTemplateCard.mTitleTextView, 8);
                    BcSmartspaceTemplateDataUtils.updateVisibility(baseTemplateCard.mSubtitleTextView, 8);
                    BcSmartspaceTemplateDataUtils.updateVisibility(baseTemplateCard.mSubtitleSupplementalView, 8);
                    BcSmartspaceTemplateDataUtils.updateVisibility(baseTemplateCard.mSecondaryCardPane, 8);
                    BcSmartspaceTemplateDataUtils.updateVisibility(baseTemplateCard.mExtrasGroup, 8);
                }
                baseTemplateCard.mPrevSmartspaceTargetId = smartspaceTarget.getSmartspaceTargetId();
                baseTemplateCard.mTarget = smartspaceTarget;
                baseTemplateCard.mTemplateData = smartspaceTarget.getTemplateData();
                baseTemplateCard.mFeatureType = smartspaceTarget.getFeatureType();
                baseTemplateCard.mLoggingInfo = bcSmartspaceCardLoggingInfo;
                baseTemplateCard.mShouldShowPageIndicator = z5;
                baseTemplateCard.mValidSecondaryCard = false;
                ViewGroup viewGroup = baseTemplateCard.mTextGroup;
                if (viewGroup != null) {
                    viewGroup.setTranslationX(0.0f);
                }
                if (baseTemplateCard.mTemplateData != null) {
                    BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo2 = baseTemplateCard.mLoggingInfo;
                    if (bcSmartspaceCardLoggingInfo2 == null) {
                        BcSmartspaceCardLoggingInfo.Builder builder2 = new BcSmartspaceCardLoggingInfo.Builder();
                        builder2.mDisplaySurface = BcSmartSpaceUtil.getLoggingDisplaySurface(baseTemplateCard.mDozeAmount, baseTemplateCard.mUiSurface);
                        builder2.mFeatureType = baseTemplateCard.mFeatureType;
                        baseTemplateCard.getContext().getPackageManager();
                        SmartspaceTarget smartspaceTarget2 = baseTemplateCard.mTarget;
                        builder2.mUid = -1;
                        builder2.mDimensionalInfo = BcSmartspaceCardLoggerUtil.createDimensionalLoggingInfo(smartspaceTarget2.getTemplateData());
                        bcSmartspaceCardLoggingInfo2 = new BcSmartspaceCardLoggingInfo(builder2);
                    }
                    baseTemplateCard.mLoggingInfo = bcSmartspaceCardLoggingInfo2;
                    if (baseTemplateCard.mSecondaryCard != null) {
                        Log.i("SsBaseTemplateCard", "Secondary card is not null");
                        baseTemplateCard.mSecondaryCard.reset$1(smartspaceTarget.getSmartspaceTargetId());
                        baseTemplateCard.mValidSecondaryCard = baseTemplateCard.mSecondaryCard.setSmartspaceActions(smartspaceTarget, cardPagerAdapter$onBindViewHolder$12, baseTemplateCard.mLoggingInfo);
                    }
                    ViewGroup viewGroup2 = baseTemplateCard.mSecondaryCardPane;
                    if (viewGroup2 != null) {
                        if (baseTemplateCard.mDozeAmount == 1.0f || !baseTemplateCard.mValidSecondaryCard) {
                            i5 = 8;
                        } else {
                            i5 = 0;
                        }
                        BcSmartspaceTemplateDataUtils.updateVisibility(viewGroup2, i5);
                    }
                    BaseTemplateData.SubItemInfo primaryItem = baseTemplateCard.mTemplateData.getPrimaryItem();
                    if (baseTemplateCard.mDateView == null) {
                        str2 = "SsBaseTemplateCard";
                        i4 = 0;
                    } else {
                        if (primaryItem == null || primaryItem.getTapAction() == null) {
                            str4 = UUID.randomUUID().toString();
                        } else {
                            str4 = primaryItem.getTapAction().getId().toString();
                        }
                        TapAction build = new TapAction.Builder(str4).setIntent(BcSmartSpaceUtil.getOpenCalendarIntent()).build();
                        str2 = "SsBaseTemplateCard";
                        i4 = 0;
                        BcSmartSpaceUtil.setOnClickListener((View) baseTemplateCard, baseTemplateCard.mTarget, build, (BcSmartspaceDataPlugin.SmartspaceEventNotifier) cardPagerAdapter$onBindViewHolder$12, "SsBaseTemplateCard", bcSmartspaceCardLoggingInfo, 0);
                        BcSmartSpaceUtil.setOnClickListener((View) baseTemplateCard.mDateView, baseTemplateCard.mTarget, build, (BcSmartspaceDataPlugin.SmartspaceEventNotifier) cardPagerAdapter$onBindViewHolder$12, "SsBaseTemplateCard", bcSmartspaceCardLoggingInfo, 0);
                    }
                    baseTemplateCard.setUpTextView(baseTemplateCard.mTitleTextView, baseTemplateCard.mTemplateData.getPrimaryItem(), cardPagerAdapter$onBindViewHolder$12);
                    baseTemplateCard.setUpTextView(baseTemplateCard.mSubtitleTextView, baseTemplateCard.mTemplateData.getSubtitleItem(), cardPagerAdapter$onBindViewHolder$12);
                    baseTemplateCard.setUpTextView(baseTemplateCard.mSubtitleSupplementalView, baseTemplateCard.mTemplateData.getSubtitleSupplementalItem(), cardPagerAdapter$onBindViewHolder$12);
                    baseTemplateCard.setUpTextView(baseTemplateCard.mSupplementalLineTextView, baseTemplateCard.mTemplateData.getSupplementalLineItem(), cardPagerAdapter$onBindViewHolder$12);
                    if (baseTemplateCard.mExtrasGroup != null) {
                        DoubleShadowTextView doubleShadowTextView3 = baseTemplateCard.mSupplementalLineTextView;
                        if (doubleShadowTextView3 == null || doubleShadowTextView3.getVisibility() != 0 || (baseTemplateCard.mShouldShowPageIndicator && baseTemplateCard.mDateView == null)) {
                            BcSmartspaceTemplateDataUtils.updateVisibility(baseTemplateCard.mExtrasGroup, 8);
                        } else {
                            BcSmartspaceTemplateDataUtils.updateVisibility(baseTemplateCard.mExtrasGroup, i4);
                            baseTemplateCard.updateZenColors();
                        }
                    }
                    if (smartspaceTarget.getFeatureType() != 1 || (doubleShadowTextView2 = baseTemplateCard.mSubtitleSupplementalView) == null || doubleShadowTextView2.getVisibility() != 8 || baseTemplateCard.mTemplateData.getSubtitleItem() == null || baseTemplateCard.mTemplateData.getSubtitleItem().getTapAction() == null) {
                        str3 = str2;
                        cardPagerAdapter$onBindViewHolder$13 = cardPagerAdapter$onBindViewHolder$12;
                    } else {
                        cardPagerAdapter$onBindViewHolder$13 = cardPagerAdapter$onBindViewHolder$12;
                        str3 = str2;
                        BcSmartSpaceUtil.setOnClickListener((View) baseTemplateCard.mSubtitleGroup, smartspaceTarget, baseTemplateCard.mTemplateData.getSubtitleItem().getTapAction(), (BcSmartspaceDataPlugin.SmartspaceEventNotifier) cardPagerAdapter$onBindViewHolder$13, "SsBaseTemplateCard", baseTemplateCard.mLoggingInfo, baseTemplateCard.getSubcardIndex(baseTemplateCard.mTemplateData.getSubtitleItem()));
                    }
                    if (smartspaceTarget.getFeatureType() == 1 && (doubleShadowTextView = baseTemplateCard.mSubtitleSupplementalView) != null && doubleShadowTextView.getVisibility() == 0) {
                        baseTemplateCard.mSubtitleTextView.setEllipsize((TextUtils.TruncateAt) null);
                    }
                    if (!(baseTemplateCard.mTemplateData.getPrimaryItem() == null || baseTemplateCard.mTemplateData.getPrimaryItem().getTapAction() == null)) {
                        BcSmartSpaceUtil.setOnClickListener((View) baseTemplateCard, smartspaceTarget, baseTemplateCard.mTemplateData.getPrimaryItem().getTapAction(), (BcSmartspaceDataPlugin.SmartspaceEventNotifier) cardPagerAdapter$onBindViewHolder$13, "SsBaseTemplateCard", baseTemplateCard.mLoggingInfo, 0);
                    }
                    ViewGroup viewGroup3 = baseTemplateCard.mSecondaryCardPane;
                    if (viewGroup3 == null) {
                        Log.i(str3, "Secondary card pane is null");
                    } else {
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) viewGroup3.getLayoutParams();
                        layoutParams.matchConstraintMaxWidth = baseTemplateCard.getWidth() / 2;
                        baseTemplateCard.mSecondaryCardPane.setLayoutParams(layoutParams);
                    }
                }
                baseTemplateCard.setPrimaryTextColor(this.currentTextColor);
                baseTemplateCard.setDozeAmount(this.dozeAmount);
                return;
            }
            throw new IllegalStateException("Required value was null.".toString());
        }
        BcSmartspaceCardLoggerUtil.tryForcePrimaryFeatureTypeAndInjectWeatherSubcard(bcSmartspaceCardLoggingInfo, smartspaceTarget);
        BcSmartspaceCard bcSmartspaceCard = viewHolder2.legacyCard;
        if (bcSmartspaceCard == null) {
            Log.w("SsCardPagerAdapter", "No legacy card view can be binded");
            return;
        }
        if (this.dataProvider == null) {
            cardPagerAdapter$onBindViewHolder$1 = null;
        } else {
            cardPagerAdapter$onBindViewHolder$1 = new CardPagerAdapter$onBindViewHolder$1(this, 1);
        }
        if (this.smartspaceTargets.size() > 1) {
            z = true;
        } else {
            z = false;
        }
        String smartspaceTargetId = smartspaceTarget.getSmartspaceTargetId();
        if (!bcSmartspaceCard.mPrevSmartspaceTargetId.equals(smartspaceTargetId)) {
            bcSmartspaceCard.mPrevSmartspaceTargetId = smartspaceTargetId;
            bcSmartspaceCard.mEventNotifier = null;
            BcSmartspaceTemplateDataUtils.updateVisibility(bcSmartspaceCard.mSecondaryCardGroup, 8);
            bcSmartspaceCard.mIconDrawable.setIcon((Drawable) null);
            bcSmartspaceCard.mBaseActionIconDrawable.setIcon((Drawable) null);
            bcSmartspaceCard.setTitle((CharSequence) null, (CharSequence) null, false);
            bcSmartspaceCard.setSubtitle((CharSequence) null, (CharSequence) null, false);
            bcSmartspaceCard.updateIconTint();
            bcSmartspaceCard.setOnClickListener((View.OnClickListener) null);
            TextView textView = bcSmartspaceCard.mTitleTextView;
            if (textView != null) {
                textView.setOnClickListener((View.OnClickListener) null);
            }
            TextView textView2 = bcSmartspaceCard.mSubtitleTextView;
            if (textView2 != null) {
                textView2.setOnClickListener((View.OnClickListener) null);
            }
            DoubleShadowTextView doubleShadowTextView4 = bcSmartspaceCard.mBaseActionIconSubtitleView;
            if (doubleShadowTextView4 != null) {
                doubleShadowTextView4.setOnClickListener((View.OnClickListener) null);
            }
        }
        bcSmartspaceCard.mTarget = smartspaceTarget;
        bcSmartspaceCard.mEventNotifier = cardPagerAdapter$onBindViewHolder$1;
        SmartspaceAction headerAction = smartspaceTarget.getHeaderAction();
        SmartspaceAction baseAction = smartspaceTarget.getBaseAction();
        bcSmartspaceCard.mUsePageIndicatorUi = z;
        bcSmartspaceCard.mValidSecondaryCard = false;
        ViewGroup viewGroup4 = bcSmartspaceCard.mTextGroup;
        if (viewGroup4 != null) {
            viewGroup4.setTranslationX(0.0f);
        }
        if (headerAction != null) {
            BcSmartspaceCardSecondary bcSmartspaceCardSecondary = bcSmartspaceCard.mSecondaryCard;
            if (bcSmartspaceCardSecondary != null) {
                bcSmartspaceCardSecondary.reset$1(smartspaceTarget.getSmartspaceTargetId());
                bcSmartspaceCard.mValidSecondaryCard = bcSmartspaceCard.mSecondaryCard.setSmartspaceActions(smartspaceTarget, bcSmartspaceCard.mEventNotifier, bcSmartspaceCardLoggingInfo);
            }
            ViewGroup viewGroup5 = bcSmartspaceCard.mSecondaryCardGroup;
            if (viewGroup5 != null) {
                viewGroup5.setAlpha(1.0f);
            }
            ViewGroup viewGroup6 = bcSmartspaceCard.mSecondaryCardGroup;
            if (bcSmartspaceCard.mDozeAmount != 1.0f && bcSmartspaceCard.mValidSecondaryCard) {
                i6 = 0;
            }
            BcSmartspaceTemplateDataUtils.updateVisibility(viewGroup6, i6);
            Icon icon = headerAction.getIcon();
            Context context = bcSmartspaceCard.getContext();
            Drawable iconDrawableWithCustomSize = BcSmartSpaceUtil.getIconDrawableWithCustomSize(icon, context, context.getResources().getDimensionPixelSize(2131165919));
            if (iconDrawableWithCustomSize != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            bcSmartspaceCard.mIconDrawable.setIcon(iconDrawableWithCustomSize);
            CharSequence title = headerAction.getTitle();
            CharSequence subtitle = headerAction.getSubtitle();
            if (smartspaceTarget.getFeatureType() == 1 || !TextUtils.isEmpty(title)) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z6 = !TextUtils.isEmpty(subtitle);
            if (!z3) {
                title = subtitle;
            }
            CharSequence contentDescription = headerAction.getContentDescription();
            if (z3 == z6 || !z2) {
                z4 = false;
            } else {
                z4 = true;
            }
            bcSmartspaceCard.setTitle(title, contentDescription, z4);
            if (!z3 || !z6) {
                subtitle = null;
            }
            bcSmartspaceCard.setSubtitle(subtitle, headerAction.getContentDescription(), z2);
        }
        if (bcSmartspaceCard.mBaseActionIconSubtitleView != null) {
            if (baseAction == null || baseAction.getIcon() == null) {
                drawable = null;
            } else {
                Icon icon2 = baseAction.getIcon();
                Context context2 = bcSmartspaceCard.getContext();
                drawable = BcSmartSpaceUtil.getIconDrawableWithCustomSize(icon2, context2, context2.getResources().getDimensionPixelSize(2131165919));
            }
            bcSmartspaceCard.mBaseActionIconDrawable.setIcon(drawable);
            if (drawable == null) {
                BcSmartspaceTemplateDataUtils.updateVisibility(bcSmartspaceCard.mBaseActionIconSubtitleView, 4);
                bcSmartspaceCard.mBaseActionIconSubtitleView.setOnClickListener((View.OnClickListener) null);
                bcSmartspaceCard.mBaseActionIconSubtitleView.setContentDescription((CharSequence) null);
            } else {
                bcSmartspaceCard.mBaseActionIconSubtitleView.setText(baseAction.getSubtitle());
                bcSmartspaceCard.mBaseActionIconSubtitleView.setCompoundDrawablesRelative(bcSmartspaceCard.mBaseActionIconDrawable, (Drawable) null, (Drawable) null, (Drawable) null);
                BcSmartspaceTemplateDataUtils.updateVisibility(bcSmartspaceCard.mBaseActionIconSubtitleView, 0);
                if (baseAction.getExtras() == null || baseAction.getExtras().isEmpty()) {
                    i = -1;
                    i2 = -1;
                } else {
                    i = -1;
                    i2 = baseAction.getExtras().getInt("subcardType", -1);
                }
                if (i2 != i) {
                    i3 = BcSmartspaceCard.getClickedIndex(bcSmartspaceCardLoggingInfo, i2);
                } else {
                    Log.d("BcSmartspaceCard", "Subcard expected but missing type. loggingInfo=" + bcSmartspaceCardLoggingInfo.toString() + ", baseAction=" + baseAction.toString());
                    i3 = 0;
                }
                BcSmartSpaceUtil.setOnClickListener((View) bcSmartspaceCard.mBaseActionIconSubtitleView, smartspaceTarget, baseAction, bcSmartspaceCard.mEventNotifier, "BcSmartspaceCard", bcSmartspaceCardLoggingInfo, i3);
                ContentDescriptionUtil.setFormattedContentDescription("BcSmartspaceCard", bcSmartspaceCard.mBaseActionIconSubtitleView, baseAction.getSubtitle(), baseAction.getContentDescription());
            }
        }
        bcSmartspaceCard.updateIconTint();
        if (bcSmartspaceCard.mDateView != null) {
            if (headerAction != null) {
                str = headerAction.getId();
            } else if (baseAction != null) {
                str = baseAction.getId();
            } else {
                str = UUID.randomUUID().toString();
            }
            BcSmartSpaceUtil.setOnClickListener((View) bcSmartspaceCard.mDateView, smartspaceTarget, new SmartspaceAction.Builder(str, "unusedTitle").setIntent(BcSmartSpaceUtil.getOpenCalendarIntent()).build(), bcSmartspaceCard.mEventNotifier, "BcSmartspaceCard", bcSmartspaceCardLoggingInfo, 0);
        }
        if (headerAction != null && (headerAction.getIntent() != null || headerAction.getPendingIntent() != null)) {
            if (smartspaceTarget.getFeatureType() == 1 && bcSmartspaceCardLoggingInfo.mFeatureType == 39) {
                BcSmartspaceCard.getClickedIndex(bcSmartspaceCardLoggingInfo, 1);
            }
            bcSmartspaceCard.setPrimaryOnClickListener(smartspaceTarget, headerAction, bcSmartspaceCardLoggingInfo);
        } else if (baseAction == null || (baseAction.getIntent() == null && baseAction.getPendingIntent() == null)) {
            bcSmartspaceCard.setPrimaryOnClickListener(smartspaceTarget, headerAction, bcSmartspaceCardLoggingInfo);
        } else {
            bcSmartspaceCard.setPrimaryOnClickListener(smartspaceTarget, baseAction, bcSmartspaceCardLoggingInfo);
        }
        ViewGroup viewGroup7 = bcSmartspaceCard.mSecondaryCardGroup;
        if (viewGroup7 != null) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) viewGroup7.getLayoutParams();
            List actionChips = smartspaceTarget.getActionChips();
            int featureType = smartspaceTarget.getFeatureType();
            if (!actionChips.isEmpty() ? featureType == 13 && actionChips.size() == 1 : featureType == -2) {
                layoutParams2.matchConstraintMaxWidth = (bcSmartspaceCard.getWidth() * 3) / 4;
            } else {
                layoutParams2.matchConstraintMaxWidth = bcSmartspaceCard.getWidth() / 2;
            }
            bcSmartspaceCard.mSecondaryCardGroup.setLayoutParams(layoutParams2);
        }
        bcSmartspaceCard.setPrimaryTextColor(this.currentTextColor);
        bcSmartspaceCard.setDozeAmount(this.dozeAmount);
    }

    public final void setMediaTarget(SmartspaceTarget smartspaceTarget) {
        List list = this.mediaTargets;
        list.clear();
        if (smartspaceTarget != null) {
            list.add(smartspaceTarget);
        }
        updateTargetVisibility();
        notifyDataSetChanged();
    }

    public final void updateCurrentTextColor() {
        this.currentTextColor = ColorUtils.blendARGB(this.primaryTextColor, this.dozeColor, this.dozeAmount);
        SparseArray sparseArray = this.viewHolders;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            ViewHolder viewHolder = (ViewHolder) sparseArray.get(sparseArray.keyAt(i));
            if (viewHolder != null) {
                BcSmartspaceCard bcSmartspaceCard = viewHolder.legacyCard;
                if (bcSmartspaceCard != null) {
                    bcSmartspaceCard.setPrimaryTextColor(this.currentTextColor);
                    bcSmartspaceCard.setDozeAmount(this.dozeAmount);
                }
                BaseTemplateCard baseTemplateCard = viewHolder.card;
                if (baseTemplateCard != null) {
                    baseTemplateCard.setPrimaryTextColor(this.currentTextColor);
                    baseTemplateCard.setDozeAmount(this.dozeAmount);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateTargetVisibility() {
        /*
            r9 = this;
            java.util.List r0 = r9._aodTargets
            boolean r1 = r9.isMediaPreferred(r0)
            if (r1 == 0) goto L_0x000b
            java.util.List r0 = r9.mediaTargets
            goto L_0x0014
        L_0x000b:
            boolean r1 = r9.hasDifferentTargets
            if (r1 == 0) goto L_0x0010
            goto L_0x0014
        L_0x0010:
            java.util.List r0 = r9.getLockscreenTargets()
        L_0x0014:
            java.util.List r1 = r9.getLockscreenTargets()
            java.util.List r2 = r9.smartspaceTargets
            r3 = 0
            r4 = 1052266988(0x3eb851ec, float:0.36)
            r5 = 1065353216(0x3f800000, float:1.0)
            r6 = 1
            if (r2 == r0) goto L_0x0036
            float r7 = r9.dozeAmount
            int r8 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r8 != 0) goto L_0x002a
            goto L_0x0034
        L_0x002a:
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r7 < 0) goto L_0x0036
            com.google.android.systemui.smartspace.CardPagerAdapter$TransitionType r7 = r9.transitioningTo
            com.google.android.systemui.smartspace.CardPagerAdapter$TransitionType r8 = com.google.android.systemui.smartspace.CardPagerAdapter.TransitionType.TO_AOD
            if (r7 != r8) goto L_0x0036
        L_0x0034:
            r7 = r6
            goto L_0x0037
        L_0x0036:
            r7 = r3
        L_0x0037:
            if (r2 == r1) goto L_0x004e
            float r2 = r9.dozeAmount
            r8 = 0
            int r8 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r8 != 0) goto L_0x0041
            goto L_0x004c
        L_0x0041:
            float r5 = r5 - r2
            int r2 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r2 < 0) goto L_0x004e
            com.google.android.systemui.smartspace.CardPagerAdapter$TransitionType r2 = r9.transitioningTo
            com.google.android.systemui.smartspace.CardPagerAdapter$TransitionType r4 = com.google.android.systemui.smartspace.CardPagerAdapter.TransitionType.TO_LOCKSCREEN
            if (r2 != r4) goto L_0x004e
        L_0x004c:
            r2 = r6
            goto L_0x004f
        L_0x004e:
            r2 = r3
        L_0x004f:
            if (r7 != 0) goto L_0x0053
            if (r2 == 0) goto L_0x005d
        L_0x0053:
            if (r7 == 0) goto L_0x0057
            r2 = r0
            goto L_0x0058
        L_0x0057:
            r2 = r1
        L_0x0058:
            r9.smartspaceTargets = r2
            r9.notifyDataSetChanged()
        L_0x005d:
            if (r0 == r1) goto L_0x0060
            goto L_0x0061
        L_0x0060:
            r6 = r3
        L_0x0061:
            r9.hasAodLockscreenTransition = r6
            com.android.systemui.plugins.BcSmartspaceConfigPlugin r0 = r9.configProvider
            boolean r0 = r0.isDefaultDateWeatherDisabled()
            if (r0 == 0) goto L_0x0084
            java.lang.String r0 = r9.uiSurface
            java.lang.String r1 = "home"
            boolean r0 = kotlin.text.StringsKt__StringsJVMKt.equals(r0, r1, r3)
            if (r0 != 0) goto L_0x0084
            java.util.List r0 = r9.smartspaceTargets
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x007f
            r3 = 8
        L_0x007f:
            android.view.View r9 = r9.root
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r9, r3)
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.CardPagerAdapter.updateTargetVisibility():void");
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class Companion {
        public static int getFeatureType(SmartspaceTarget smartspaceTarget) {
            List actionChips = smartspaceTarget.getActionChips();
            int featureType = smartspaceTarget.getFeatureType();
            if (actionChips.isEmpty()) {
                return featureType;
            }
            if (featureType == 13 && actionChips.size() == 1) {
                return -2;
            }
            return -1;
        }

        public static boolean useRecycledViewForAction(SmartspaceAction smartspaceAction, SmartspaceAction smartspaceAction2) {
            Set set;
            if (!(smartspaceAction == null && smartspaceAction2 == null)) {
                if (!(smartspaceAction == null || smartspaceAction2 == null)) {
                    Intrinsics.checkNotNull(smartspaceAction);
                    Bundle extras = smartspaceAction.getExtras();
                    Intrinsics.checkNotNull(smartspaceAction2);
                    Bundle extras2 = smartspaceAction2.getExtras();
                    if (!(extras == null && extras2 == null)) {
                        Bundle extras3 = smartspaceAction.getExtras();
                        Bundle extras4 = smartspaceAction2.getExtras();
                        if (!(extras3 == null || extras4 == null)) {
                            Bundle extras5 = smartspaceAction.getExtras();
                            Set set2 = null;
                            if (extras5 != null) {
                                set = extras5.keySet();
                            } else {
                                set = null;
                            }
                            Bundle extras6 = smartspaceAction2.getExtras();
                            if (extras6 != null) {
                                set2 = extras6.keySet();
                            }
                            if (Intrinsics.areEqual(set, set2)) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public static boolean useRecycledViewForActionsList(List list, List list2) {
            boolean z;
            if (list == null && list2 == null) {
                return true;
            }
            if (list == null || list2 == null) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                Intrinsics.checkNotNull(list);
                int size = list.size();
                Intrinsics.checkNotNull(list2);
                if (size == list2.size() && IntStream.range(0, list.size()).allMatch(new CardPagerAdapter$Companion$useRecycledViewForActionsList$1(list, list2))) {
                    return true;
                }
            }
            return false;
        }

        public final int getBaseLegacyCardRes(int i) {
            if (i == -2 || i == -1) {
                return 2131559022;
            }
            if (i == 1) {
                return 0;
            }
            if (i == 2 || i == 3 || i == 4 || i == 6 || i == 18 || i == 20 || i == 30 || i == 9 || i == 10) {
                return 2131559022;
            }
            switch (i) {
            }
            return 2131559022;
        }

        public final int getLegacySecondaryCardRes(int i) {
            if (i == -2) {
                return 2131559024;
            }
            if (i == -1) {
                return 2131559023;
            }
            if (i == 1 || i == 2) {
                return 0;
            }
            if (i != 3) {
                if (i == 4) {
                    return 2131559026;
                }
                if (i == 6) {
                    return 0;
                }
                if (i != 18) {
                    if (i == 20 || i == 30) {
                        return 2131559025;
                    }
                    if (i == 9) {
                        return 2131559032;
                    }
                    if (i == 10) {
                        return 2131559033;
                    }
                    switch (i) {
                        case ViewNode.SCALEX_FIELD_NUMBER:
                            return 2131559031;
                        case ViewNode.SCALEY_FIELD_NUMBER:
                            return 2131559030;
                        default:
                            return 0;
                    }
                }
            }
            return 2131559028;
        }

        public final boolean useRecycledViewForNewTarget(SmartspaceTarget smartspaceTarget, SmartspaceTarget smartspaceTarget2) {
            if (smartspaceTarget2 != null && Intrinsics.areEqual(smartspaceTarget.getSmartspaceTargetId(), smartspaceTarget2.getSmartspaceTargetId()) && useRecycledViewForAction(smartspaceTarget.getHeaderAction(), smartspaceTarget2.getHeaderAction()) && useRecycledViewForAction(smartspaceTarget.getBaseAction(), smartspaceTarget2.getBaseAction()) && useRecycledViewForActionsList(smartspaceTarget.getActionChips(), smartspaceTarget2.getActionChips()) && useRecycledViewForActionsList(smartspaceTarget.getIconGrid(), smartspaceTarget2.getIconGrid())) {
                BaseTemplateData templateData = smartspaceTarget.getTemplateData();
                BaseTemplateData templateData2 = smartspaceTarget2.getTemplateData();
                if ((templateData != null || templateData2 != null) && (templateData == null || templateData2 == null || !Intrinsics.areEqual(templateData, templateData2))) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public static /* synthetic */ void getMAX_FEATURE_TYPE$annotations() {
        }

        public static /* synthetic */ void getMIN_FEATURE_TYPE$annotations() {
        }
    }

    public static /* synthetic */ void getAodTargets$annotations() {
    }

    public static /* synthetic */ void getConfigProvider$annotations() {
    }

    public static /* synthetic */ void getDataProvider$annotations() {
    }

    public static /* synthetic */ void getDozeAmount$annotations() {
    }

    public static /* synthetic */ void getHasAodLockscreenTransition$annotations() {
    }

    public static /* synthetic */ void getHasDifferentTargets$annotations() {
    }

    public static /* synthetic */ void getKeyguardBypassEnabled$annotations() {
    }

    public static /* synthetic */ void getLockscreenTargets$annotations() {
    }

    public static /* synthetic */ void getPrimaryTextColor$annotations() {
    }

    public static /* synthetic */ void getUiSurface$annotations() {
    }
}
