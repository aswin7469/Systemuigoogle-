package com.google.android.systemui.smartspace;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.SmartspaceTargetEvent;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import com.android.systemui.plugins.BcSmartspaceConfigPlugin;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.plugins.FalsingManager;
import com.google.android.systemui.smartspace.CardPagerAdapter;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLogger;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggerUtil;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import com.google.android.systemui.smartspace.logging.BcSmartspaceSubcardLoggingInfo;
import com.google.android.systemui.smartspace.uitemplate.BaseTemplateCard;
import java.time.DateTimeException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class BcSmartspaceView extends FrameLayout implements BcSmartspaceDataPlugin.SmartspaceTargetListener, BcSmartspaceDataPlugin.SmartspaceView {
    public static final boolean DEBUG = Log.isLoggable("BcSmartspaceView", 3);
    public final CardPagerAdapter mAdapter = new CardPagerAdapter(this, this.mConfigProvider);
    public boolean mAnimateSmartspaceUpdate = false;
    public final AnonymousClass1 mAodObserver = new ContentObserver(new Handler()) {
        public final void onChange(boolean z) {
            BcSmartspaceView bcSmartspaceView = BcSmartspaceView.this;
            boolean z2 = BcSmartspaceView.DEBUG;
            Context context = bcSmartspaceView.getContext();
            boolean z3 = false;
            if (Settings.Secure.getIntForUser(context.getContentResolver(), "doze_always_on", 0, context.getUserId()) == 1) {
                z3 = true;
            }
            bcSmartspaceView.mIsAodEnabled = z3;
        }
    };
    public int mCardPosition = 0;
    public BcSmartspaceConfigPlugin mConfigProvider = new Object();
    public BcSmartspaceDataPlugin mDataProvider;
    public boolean mIsAodEnabled = false;
    public final ArraySet mLastReceivedTargets = new ArraySet();
    public final AnonymousClass2 mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        public final void onPageScrollStateChanged(int i) {
            List list;
            BcSmartspaceView bcSmartspaceView = BcSmartspaceView.this;
            bcSmartspaceView.mScrollState = i;
            if (i == 0 && (list = bcSmartspaceView.mPendingTargets) != null) {
                bcSmartspaceView.onSmartspaceTargetsUpdated(list);
            }
        }

        public final void onPageScrolled(int i, float f, int i2) {
            PageIndicator pageIndicator = BcSmartspaceView.this.mPageIndicator;
            if (pageIndicator != null) {
                pageIndicator.setPageOffset(i, f);
            }
        }

        public final void onPageSelected(int i) {
            BcSmartspaceView bcSmartspaceView = BcSmartspaceView.this;
            SmartspaceTarget targetAtPosition = bcSmartspaceView.mAdapter.getTargetAtPosition(bcSmartspaceView.mCardPosition);
            bcSmartspaceView.mCardPosition = i;
            SmartspaceTarget targetAtPosition2 = bcSmartspaceView.mAdapter.getTargetAtPosition(i);
            bcSmartspaceView.logSmartspaceEvent(targetAtPosition2, bcSmartspaceView.mCardPosition, BcSmartspaceEvent.SMARTSPACE_CARD_SEEN);
            if (bcSmartspaceView.mDataProvider == null) {
                Log.w("BcSmartspaceView", "Cannot notify target hidden/shown smartspace events: data provider null");
                return;
            }
            if (targetAtPosition == null) {
                Log.w("BcSmartspaceView", "Cannot notify target hidden smartspace event: previous target is null.");
            } else {
                SmartspaceTargetEvent.Builder builder = new SmartspaceTargetEvent.Builder(3);
                builder.setSmartspaceTarget(targetAtPosition);
                SmartspaceAction baseAction = targetAtPosition.getBaseAction();
                if (baseAction != null) {
                    builder.setSmartspaceActionId(baseAction.getId());
                }
                bcSmartspaceView.mDataProvider.notifySmartspaceEvent(builder.build());
            }
            SmartspaceTargetEvent.Builder builder2 = new SmartspaceTargetEvent.Builder(2);
            builder2.setSmartspaceTarget(targetAtPosition2);
            SmartspaceAction baseAction2 = targetAtPosition2.getBaseAction();
            if (baseAction2 != null) {
                builder2.setSmartspaceActionId(baseAction2.getId());
            }
            bcSmartspaceView.mDataProvider.notifySmartspaceEvent(builder2.build());
        }
    };
    public PageIndicator mPageIndicator;
    public List mPendingTargets;
    public float mPreviousDozeAmount = 0.0f;
    public Animator mRunningAnimation;
    public int mScrollState = 0;
    public boolean mSplitShadeEnabled = false;
    public ViewPager mViewPager;

    /* JADX WARNING: type inference failed for: r1v1, types: [com.android.systemui.plugins.BcSmartspaceConfigPlugin, java.lang.Object] */
    public BcSmartspaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void animateSmartspaceUpdate(final ConstraintLayout constraintLayout) {
        if (this.mRunningAnimation == null && constraintLayout.getParent() == null) {
            final ViewGroup viewGroup = (ViewGroup) this.mViewPager.getParent();
            constraintLayout.measure(View.MeasureSpec.makeMeasureSpec(this.mViewPager.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.mViewPager.getHeight(), 1073741824));
            constraintLayout.layout(this.mViewPager.getLeft(), this.mViewPager.getTop(), this.mViewPager.getRight(), this.mViewPager.getBottom());
            AnimatorSet animatorSet = new AnimatorSet();
            float dimension = getContext().getResources().getDimension(2131165915);
            Property property = View.TRANSLATION_Y;
            animatorSet.play(ObjectAnimator.ofFloat(constraintLayout, property, new float[]{0.0f, ((float) (-getHeight())) - dimension}));
            animatorSet.play(ObjectAnimator.ofFloat(constraintLayout, View.ALPHA, new float[]{1.0f, 0.0f}));
            animatorSet.play(ObjectAnimator.ofFloat(this.mViewPager, property, new float[]{((float) getHeight()) + dimension, 0.0f}));
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public final void onAnimationEnd(Animator animator) {
                    constraintLayout.setTranslationY(0.0f);
                    constraintLayout.setAlpha(1.0f);
                    viewGroup.getOverlay().remove(constraintLayout);
                    BcSmartspaceView bcSmartspaceView = BcSmartspaceView.this;
                    bcSmartspaceView.mRunningAnimation = null;
                    bcSmartspaceView.mAnimateSmartspaceUpdate = false;
                }

                public final void onAnimationStart(Animator animator) {
                    viewGroup.getOverlay().add(constraintLayout);
                }
            });
            this.mRunningAnimation = animatorSet;
            animatorSet.start();
        }
    }

    public final int getCurrentCardTopPadding() {
        BcSmartspaceCard bcSmartspaceCard;
        BaseTemplateCard baseTemplateCard;
        CardPagerAdapter.ViewHolder viewHolder = (CardPagerAdapter.ViewHolder) this.mAdapter.viewHolders.get(this.mViewPager.mCurItem);
        ViewGroup viewGroup = null;
        if (viewHolder != null) {
            bcSmartspaceCard = viewHolder.legacyCard;
        } else {
            bcSmartspaceCard = null;
        }
        if (bcSmartspaceCard != null) {
            CardPagerAdapter.ViewHolder viewHolder2 = (CardPagerAdapter.ViewHolder) this.mAdapter.viewHolders.get(this.mViewPager.mCurItem);
            if (viewHolder2 != null) {
                viewGroup = viewHolder2.legacyCard;
            }
            return viewGroup.getPaddingTop();
        }
        CardPagerAdapter.ViewHolder viewHolder3 = (CardPagerAdapter.ViewHolder) this.mAdapter.viewHolders.get(this.mViewPager.mCurItem);
        if (viewHolder3 != null) {
            baseTemplateCard = viewHolder3.card;
        } else {
            baseTemplateCard = null;
        }
        if (baseTemplateCard == null) {
            return 0;
        }
        CardPagerAdapter.ViewHolder viewHolder4 = (CardPagerAdapter.ViewHolder) this.mAdapter.viewHolders.get(this.mViewPager.mCurItem);
        if (viewHolder4 != null) {
            viewGroup = viewHolder4.card;
        }
        return viewGroup.getPaddingTop();
    }

    public final int getSelectedPage() {
        return this.mViewPager.mCurItem;
    }

    public final void logSmartspaceEvent(SmartspaceTarget smartspaceTarget, int i, BcSmartspaceEvent bcSmartspaceEvent) {
        int i2;
        BcSmartspaceSubcardLoggingInfo bcSmartspaceSubcardLoggingInfo;
        if (bcSmartspaceEvent == BcSmartspaceEvent.SMARTSPACE_CARD_RECEIVED) {
            try {
                i2 = (int) Instant.now().minusMillis(smartspaceTarget.getCreationTimeMillis()).toEpochMilli();
            } catch (ArithmeticException | DateTimeException e) {
                Log.e("BcSmartspaceView", "received_latency_millis will be -1 due to exception ", e);
                i2 = -1;
            }
        } else {
            i2 = 0;
        }
        boolean containsValidTemplateType = BcSmartspaceCardLoggerUtil.containsValidTemplateType(smartspaceTarget.getTemplateData());
        BcSmartspaceCardLoggingInfo.Builder builder = new BcSmartspaceCardLoggingInfo.Builder();
        builder.mInstanceId = InstanceId.create(smartspaceTarget);
        builder.mFeatureType = smartspaceTarget.getFeatureType();
        CardPagerAdapter cardPagerAdapter = this.mAdapter;
        builder.mDisplaySurface = BcSmartSpaceUtil.getLoggingDisplaySurface(cardPagerAdapter.dozeAmount, cardPagerAdapter.uiSurface);
        builder.mRank = i;
        builder.mCardinality = this.mAdapter.smartspaceTargets.size();
        builder.mReceivedLatency = i2;
        getContext().getPackageManager();
        builder.mUid = -1;
        if (containsValidTemplateType) {
            bcSmartspaceSubcardLoggingInfo = BcSmartspaceCardLoggerUtil.createSubcardLoggingInfo(smartspaceTarget.getTemplateData());
        } else {
            bcSmartspaceSubcardLoggingInfo = BcSmartspaceCardLoggerUtil.createSubcardLoggingInfo(smartspaceTarget);
        }
        builder.mSubcardInfo = bcSmartspaceSubcardLoggingInfo;
        builder.mDimensionalInfo = BcSmartspaceCardLoggerUtil.createDimensionalLoggingInfo(smartspaceTarget.getTemplateData());
        BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo = new BcSmartspaceCardLoggingInfo(builder);
        if (containsValidTemplateType) {
            BcSmartspaceCardLoggerUtil.tryForcePrimaryFeatureTypeOrUpdateLogInfoFromTemplateData(bcSmartspaceCardLoggingInfo, smartspaceTarget.getTemplateData());
        } else {
            BcSmartspaceCardLoggerUtil.tryForcePrimaryFeatureTypeAndInjectWeatherSubcard(bcSmartspaceCardLoggingInfo, smartspaceTarget);
        }
        BcSmartspaceCardLogger.log(bcSmartspaceEvent, bcSmartspaceCardLoggingInfo);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mViewPager.setAdapter(this.mAdapter);
        ViewPager viewPager = this.mViewPager;
        AnonymousClass2 r2 = this.mOnPageChangeListener;
        if (viewPager.mOnPageChangeListeners == null) {
            viewPager.mOnPageChangeListeners = new ArrayList();
        }
        viewPager.mOnPageChangeListeners.add(r2);
        this.mPageIndicator.setNumPages(this.mAdapter.smartspaceTargets.size(), isLayoutRtl());
        if (TextUtils.equals(this.mAdapter.uiSurface, BcSmartspaceDataPlugin.UI_SURFACE_LOCK_SCREEN_AOD)) {
            try {
                boolean z = false;
                getContext().getContentResolver().registerContentObserver(Settings.Secure.getUriFor("doze_always_on"), false, this.mAodObserver, -1);
                Context context = getContext();
                if (Settings.Secure.getIntForUser(context.getContentResolver(), "doze_always_on", 0, context.getUserId()) == 1) {
                    z = true;
                }
                this.mIsAodEnabled = z;
            } catch (Exception e) {
                Log.w("BcSmartspaceView", "Unable to register Doze Always on content observer.", e);
            }
        }
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin = this.mDataProvider;
        if (bcSmartspaceDataPlugin != null) {
            registerDataProvider(bcSmartspaceDataPlugin);
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().getContentResolver().unregisterContentObserver(this.mAodObserver);
        ViewPager viewPager = this.mViewPager;
        AnonymousClass2 r1 = this.mOnPageChangeListener;
        List list = viewPager.mOnPageChangeListeners;
        if (list != null) {
            list.remove(r1);
        }
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin = this.mDataProvider;
        if (bcSmartspaceDataPlugin != null) {
            bcSmartspaceDataPlugin.unregisterListener(this);
        }
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mViewPager = (ViewPager) findViewById(2131363659);
        this.mPageIndicator = (PageIndicator) findViewById(2131363662);
    }

    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131165916);
        if (size <= 0 || size >= dimensionPixelSize) {
            super.onMeasure(i, i2);
            setScaleX(1.0f);
            setScaleY(1.0f);
            resetPivot();
            return;
        }
        float f = (float) size;
        float f2 = (float) dimensionPixelSize;
        float f3 = f / f2;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.round(((float) View.MeasureSpec.getSize(i)) / f3), 1073741824), View.MeasureSpec.makeMeasureSpec(dimensionPixelSize, 1073741824));
        setScaleX(f3);
        setScaleY(f3);
        setPivotX(0.0f);
        setPivotY(f2 / 2.0f);
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Object, java.util.function.Function] */
    public final void onSmartspaceTargetsUpdated(List<SmartspaceTarget> list) {
        int i;
        BaseTemplateCard baseTemplateCard;
        int i2;
        Bundle extras;
        if (DEBUG) {
            Log.d("BcSmartspaceView", "@" + Integer.toHexString(hashCode()) + ", onTargetsAvailable called. Callers = " + Debug.getCallers(5));
            StringBuilder sb = new StringBuilder("    targets.size() = ");
            sb.append(list.size());
            Log.d("BcSmartspaceView", sb.toString());
            Log.d("BcSmartspaceView", "    targets = " + list.toString());
        }
        if (this.mScrollState == 0 || this.mAdapter.smartspaceTargets.size() <= 1) {
            BcSmartspaceCard bcSmartspaceCard = null;
            this.mPendingTargets = null;
            boolean isLayoutRtl = isLayoutRtl();
            int i3 = this.mViewPager.mCurItem;
            if (isLayoutRtl) {
                i = this.mAdapter.smartspaceTargets.size() - i3;
                List arrayList = new ArrayList(list);
                Collections.reverse(arrayList);
                list = arrayList;
            } else {
                i = i3;
            }
            CardPagerAdapter.ViewHolder viewHolder = (CardPagerAdapter.ViewHolder) this.mAdapter.viewHolders.get(i3);
            if (viewHolder != null) {
                baseTemplateCard = viewHolder.card;
            } else {
                baseTemplateCard = null;
            }
            CardPagerAdapter.ViewHolder viewHolder2 = (CardPagerAdapter.ViewHolder) this.mAdapter.viewHolders.get(i3);
            if (viewHolder2 != null) {
                bcSmartspaceCard = viewHolder2.legacyCard;
            }
            CardPagerAdapter cardPagerAdapter = this.mAdapter;
            List list2 = cardPagerAdapter._aodTargets;
            list2.clear();
            List list3 = cardPagerAdapter._lockscreenTargets;
            list3.clear();
            cardPagerAdapter.hasDifferentTargets = false;
            for (SmartspaceTarget smartspaceTarget : list) {
                Intrinsics.checkNotNull(smartspaceTarget);
                if (smartspaceTarget.getFeatureType() != 34) {
                    SmartspaceAction baseAction = smartspaceTarget.getBaseAction();
                    if (baseAction == null || (extras = baseAction.getExtras()) == null) {
                        i2 = 3;
                    } else {
                        i2 = extras.getInt("SCREEN_EXTRA", 3);
                    }
                    if ((i2 & 2) != 0) {
                        list2.add(smartspaceTarget);
                    }
                    if ((i2 & 1) != 0) {
                        list3.add(smartspaceTarget);
                    }
                    if (i2 != 3) {
                        cardPagerAdapter.hasDifferentTargets = true;
                    }
                }
            }
            if (!cardPagerAdapter.configProvider.isDefaultDateWeatherDisabled()) {
                cardPagerAdapter.addDefaultDateCardIfEmpty(list2);
                cardPagerAdapter.addDefaultDateCardIfEmpty(list3);
            }
            cardPagerAdapter.updateTargetVisibility();
            cardPagerAdapter.notifyDataSetChanged();
            int size = this.mAdapter.smartspaceTargets.size();
            PageIndicator pageIndicator = this.mPageIndicator;
            if (pageIndicator != null) {
                pageIndicator.setNumPages(size, isLayoutRtl);
            }
            if (isLayoutRtl) {
                int max = Math.max(0, Math.min(size - 1, size - i));
                this.mViewPager.setCurrentItem(max, false);
                this.mPageIndicator.setPageOffset(max, 0.0f);
            }
            if (this.mAnimateSmartspaceUpdate) {
                if (baseTemplateCard != null) {
                    animateSmartspaceUpdate(baseTemplateCard);
                } else if (bcSmartspaceCard != null) {
                    animateSmartspaceUpdate(bcSmartspaceCard);
                }
            }
            for (int i4 = 0; i4 < size; i4++) {
                SmartspaceTarget targetAtPosition = this.mAdapter.getTargetAtPosition(i4);
                if (!this.mLastReceivedTargets.contains(targetAtPosition.getSmartspaceTargetId())) {
                    logSmartspaceEvent(targetAtPosition, i4, BcSmartspaceEvent.SMARTSPACE_CARD_RECEIVED);
                    SmartspaceTargetEvent.Builder builder = new SmartspaceTargetEvent.Builder(8);
                    builder.setSmartspaceTarget(targetAtPosition);
                    SmartspaceAction baseAction2 = targetAtPosition.getBaseAction();
                    if (baseAction2 != null) {
                        builder.setSmartspaceActionId(baseAction2.getId());
                    }
                    this.mDataProvider.notifySmartspaceEvent(builder.build());
                }
            }
            this.mLastReceivedTargets.clear();
            this.mLastReceivedTargets.addAll((Collection) this.mAdapter.smartspaceTargets.stream().map(new Object()).collect(Collectors.toList()));
            return;
        }
        this.mPendingTargets = list;
    }

    public final void onVisibilityAggregated(boolean z) {
        int i;
        super.onVisibilityAggregated(z);
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin = this.mDataProvider;
        if (bcSmartspaceDataPlugin != null) {
            if (z) {
                i = 6;
            } else {
                i = 7;
            }
            bcSmartspaceDataPlugin.notifySmartspaceEvent(new SmartspaceTargetEvent.Builder(i).build());
        }
    }

    public final void registerConfigProvider(BcSmartspaceConfigPlugin bcSmartspaceConfigPlugin) {
        this.mConfigProvider = bcSmartspaceConfigPlugin;
        this.mAdapter.configProvider = bcSmartspaceConfigPlugin;
    }

    public final void registerDataProvider(BcSmartspaceDataPlugin bcSmartspaceDataPlugin) {
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin2 = this.mDataProvider;
        if (bcSmartspaceDataPlugin2 != null) {
            bcSmartspaceDataPlugin2.unregisterListener(this);
        }
        this.mDataProvider = bcSmartspaceDataPlugin;
        bcSmartspaceDataPlugin.registerListener(this);
        this.mAdapter.dataProvider = this.mDataProvider;
    }

    public final void setDozeAmount(float f) {
        CardPagerAdapter.TransitionType transitionType;
        List list;
        int i;
        float f2;
        float f3;
        CardPagerAdapter cardPagerAdapter = this.mAdapter;
        List list2 = cardPagerAdapter.smartspaceTargets;
        cardPagerAdapter.dozeAmount = f;
        float f4 = cardPagerAdapter.previousDozeAmount;
        if (f4 > f) {
            transitionType = CardPagerAdapter.TransitionType.TO_LOCKSCREEN;
        } else if (f4 < f) {
            transitionType = CardPagerAdapter.TransitionType.TO_AOD;
        } else {
            transitionType = CardPagerAdapter.TransitionType.NOT_IN_TRANSITION;
        }
        cardPagerAdapter.transitioningTo = transitionType;
        cardPagerAdapter.previousDozeAmount = f;
        cardPagerAdapter.updateTargetVisibility();
        cardPagerAdapter.updateCurrentTextColor();
        if (!this.mAdapter.smartspaceTargets.isEmpty()) {
            BcSmartspaceTemplateDataUtils.updateVisibility(this, 0);
        }
        float f5 = 1.0f;
        if (this.mAdapter.hasAodLockscreenTransition) {
            float f6 = this.mPreviousDozeAmount;
            if (f == f6) {
                f5 = getAlpha();
            } else {
                if (f6 > f) {
                    f2 = 1.0f - f;
                } else {
                    f2 = f;
                }
                float f7 = 0.36f;
                if (f2 < 0.36f) {
                    f3 = 0.36f - f2;
                } else {
                    f3 = f2 - 0.36f;
                    f7 = 0.64f;
                }
                f5 = f3 / f7;
            }
        }
        setAlpha(f5);
        PageIndicator pageIndicator = this.mPageIndicator;
        if (pageIndicator != null) {
            pageIndicator.setNumPages(this.mAdapter.smartspaceTargets.size(), isLayoutRtl());
            this.mPageIndicator.setAlpha(f5);
        }
        this.mPreviousDozeAmount = f;
        CardPagerAdapter cardPagerAdapter2 = this.mAdapter;
        if (cardPagerAdapter2.hasDifferentTargets && (list = cardPagerAdapter2.smartspaceTargets) != list2 && list.size() > 0) {
            if (isLayoutRtl()) {
                i = this.mAdapter.smartspaceTargets.size() - 1;
            } else {
                i = 0;
            }
            this.mViewPager.setCurrentItem(i, false);
            this.mPageIndicator.setPageOffset(i, 0.0f);
        }
        CardPagerAdapter cardPagerAdapter3 = this.mAdapter;
        int loggingDisplaySurface = BcSmartSpaceUtil.getLoggingDisplaySurface(cardPagerAdapter3.dozeAmount, cardPagerAdapter3.uiSurface);
        if (loggingDisplaySurface != -1) {
            if (loggingDisplaySurface != 3 || this.mIsAodEnabled) {
                if (DEBUG) {
                    Log.d("BcSmartspaceView", "@" + Integer.toHexString(hashCode()) + ", setDozeAmount: Logging SMARTSPACE_CARD_SEEN, currentSurface = " + loggingDisplaySurface);
                }
                SmartspaceTarget targetAtPosition = this.mAdapter.getTargetAtPosition(this.mCardPosition);
                if (targetAtPosition == null) {
                    Log.w("BcSmartspaceView", "Current card is not present in the Adapter; cannot log.");
                } else {
                    logSmartspaceEvent(targetAtPosition, this.mCardPosition, BcSmartspaceEvent.SMARTSPACE_CARD_SEEN);
                }
            }
        }
    }

    public final void setDozing(boolean z) {
        if (!z && this.mSplitShadeEnabled) {
            CardPagerAdapter cardPagerAdapter = this.mAdapter;
            if (cardPagerAdapter.hasAodLockscreenTransition && cardPagerAdapter.getLockscreenTargets().isEmpty()) {
                BcSmartspaceTemplateDataUtils.updateVisibility(this, 8);
            }
        }
    }

    public final void setFalsingManager(FalsingManager falsingManager) {
        BcSmartSpaceUtil.sFalsingManager = falsingManager;
    }

    public final void setIntentStarter(BcSmartspaceDataPlugin.IntentStarter intentStarter) {
        BcSmartSpaceUtil.sIntentStarter = intentStarter;
    }

    public final void setKeyguardBypassEnabled(boolean z) {
        CardPagerAdapter cardPagerAdapter = this.mAdapter;
        cardPagerAdapter.keyguardBypassEnabled = z;
        cardPagerAdapter.updateTargetVisibility();
    }

    public final void setMediaTarget(SmartspaceTarget smartspaceTarget) {
        this.mAdapter.setMediaTarget(smartspaceTarget);
    }

    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mViewPager.setOnLongClickListener(onLongClickListener);
    }

    public final void setPrimaryTextColor(int i) {
        CardPagerAdapter cardPagerAdapter = this.mAdapter;
        cardPagerAdapter.primaryTextColor = i;
        cardPagerAdapter.updateCurrentTextColor();
        PageIndicator pageIndicator = this.mPageIndicator;
        pageIndicator.mPrimaryColor = i;
        for (int i2 = 0; i2 < pageIndicator.getChildCount(); i2++) {
            ((ImageView) pageIndicator.getChildAt(i2)).getDrawable().setTint(pageIndicator.mPrimaryColor);
        }
    }

    public final void setSplitShadeEnabled(boolean z) {
        this.mSplitShadeEnabled = z;
    }

    public final void setUiSurface(String str) {
        if (!isAttachedToWindow()) {
            this.mAdapter.uiSurface = str;
            return;
        }
        throw new IllegalStateException("Must call before attaching view to window.");
    }
}
