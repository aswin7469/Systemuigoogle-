package com.google.android.setupcompat;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.google.android.setupcompat.internal.ClockProvider;
import com.google.android.setupcompat.internal.LifecycleFragment;
import com.google.android.setupcompat.internal.PersistableBundles;
import com.google.android.setupcompat.internal.Preconditions;
import com.google.android.setupcompat.internal.SetupCompatServiceInvoker;
import com.google.android.setupcompat.internal.SetupCompatServiceInvoker$$ExternalSyntheticLambda0;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.logging.CustomEvent;
import com.google.android.setupcompat.logging.MetricKey;
import com.google.android.setupcompat.logging.SetupMetricsLogger;
import com.google.android.setupcompat.logging.internal.FooterBarMixinMetrics;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupcompat.template.StatusBarMixin;
import com.google.android.setupcompat.template.SystemNavBarMixin;
import com.google.android.setupcompat.util.Logger;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.google.android.setupdesign.GlifLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class PartnerCustomizationLayout extends TemplateLayout {
    public static final Logger LOG = new Logger("PartnerCustomizationLayout");
    public Activity activity;
    public boolean useDynamicColor;
    public boolean useFullDynamicColorAttr;
    public boolean usePartnerResourceAttr;
    final ViewTreeObserver.OnWindowFocusChangeListener windowFocusChangeListener = new PartnerCustomizationLayout$$ExternalSyntheticLambda0((GlifLayout) this);

    public static void $r8$lambda$LkXYrnw5DYvjSfWXKSuSlNqcyss(PartnerCustomizationLayout partnerCustomizationLayout, boolean z) {
        SetupCompatServiceInvoker setupCompatServiceInvoker = SetupCompatServiceInvoker.get(partnerCustomizationLayout.getContext());
        String shortString = partnerCustomizationLayout.activity.getComponentName().toShortString();
        Activity activity2 = partnerCustomizationLayout.activity;
        Bundle bundle = new Bundle();
        bundle.putString("packageName", activity2.getComponentName().getPackageName());
        bundle.putString("screenName", activity2.getComponentName().getShortClassName());
        bundle.putInt("hash", partnerCustomizationLayout.hashCode());
        bundle.putBoolean("focus", z);
        bundle.putLong("timeInMillis", System.currentTimeMillis());
        setupCompatServiceInvoker.getClass();
        try {
            setupCompatServiceInvoker.loggingExecutor.execute(new SetupCompatServiceInvoker$$ExternalSyntheticLambda0(setupCompatServiceInvoker, shortString, bundle, 1));
        } catch (RejectedExecutionException e) {
            SetupCompatServiceInvoker.LOG.e(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Screen ", shortString, " report focus changed failed."), e);
        }
    }

    public PartnerCustomizationLayout(Context context, int i, int i2) {
        super(context, i, i2);
        init$1((AttributeSet) null, 2130970022);
    }

    public PersistableBundle getLayoutTypeMetrics() {
        return null;
    }

    public final void init$1(AttributeSet attributeSet, int i) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SucPartnerCustomizationLayout, i, 0);
            boolean z = obtainStyledAttributes.getBoolean(1, true);
            obtainStyledAttributes.recycle();
            if (z) {
                setSystemUiVisibility(1024);
            }
            registerMixin(StatusBarMixin.class, new StatusBarMixin(this, this.activity.getWindow(), attributeSet, i));
            Class<SystemNavBarMixin> cls = SystemNavBarMixin.class;
            registerMixin(cls, new SystemNavBarMixin(this, this.activity.getWindow()));
            registerMixin(FooterBarMixin.class, new FooterBarMixin(this, attributeSet, i));
            ((SystemNavBarMixin) getMixin(cls)).applyPartnerCustomizations(attributeSet, i);
            this.activity.getWindow().addFlags(Integer.MIN_VALUE);
            this.activity.getWindow().clearFlags(67108864);
            this.activity.getWindow().clearFlags(134217728);
        }
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [android.app.Fragment, com.google.android.setupcompat.internal.LifecycleFragment] */
    public final void onAttachedToWindow() {
        String str;
        super.onAttachedToWindow();
        Activity activity2 = this.activity;
        int i = LifecycleFragment.$r8$clinit;
        if (WizardManagerHelper.isAnySetupWizard(activity2.getIntent())) {
            SetupCompatServiceInvoker setupCompatServiceInvoker = SetupCompatServiceInvoker.get(activity2.getApplicationContext());
            String obj = activity2.getComponentName().toString();
            Bundle bundle = new Bundle();
            bundle.putString("screenName", activity2.getComponentName().toString());
            bundle.putString("intentAction", activity2.getIntent().getAction());
            setupCompatServiceInvoker.getClass();
            try {
                setupCompatServiceInvoker.loggingExecutor.execute(new SetupCompatServiceInvoker$$ExternalSyntheticLambda0(setupCompatServiceInvoker, obj, bundle, 0));
            } catch (RejectedExecutionException e) {
                SetupCompatServiceInvoker.LOG.e(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Screen ", obj, " bind back fail."), e);
            }
            FragmentManager fragmentManager = activity2.getFragmentManager();
            if (fragmentManager != null && !fragmentManager.isDestroyed()) {
                Fragment findFragmentByTag = fragmentManager.findFragmentByTag("lifecycle_monitor");
                if (findFragmentByTag == null) {
                    ? fragment = new Fragment();
                    fragment.durationInNanos = 0;
                    fragment.setRetainInstance(true);
                    try {
                        fragmentManager.beginTransaction().add(fragment, "lifecycle_monitor").commitNow();
                        findFragmentByTag = fragment;
                    } catch (IllegalStateException e2) {
                        Log.e("LifecycleFragment", "Error occurred when attach to Activity:" + activity2.getComponentName(), e2);
                    }
                } else if (!(findFragmentByTag instanceof LifecycleFragment)) {
                    Log.wtf("LifecycleFragment", activity2.getClass().getSimpleName().concat(" Incorrect instance on lifecycle fragment."));
                }
                LifecycleFragment lifecycleFragment = (LifecycleFragment) findFragmentByTag;
            }
        }
        if (WizardManagerHelper.isAnySetupWizard(this.activity.getIntent())) {
            getViewTreeObserver().addOnWindowFocusChangeListener(this.windowFocusChangeListener);
        }
        FooterBarMixin footerBarMixin = (FooterBarMixin) getMixin(FooterBarMixin.class);
        FooterBarMixinMetrics footerBarMixinMetrics = footerBarMixin.metrics;
        boolean isPrimaryButtonVisible = footerBarMixin.isPrimaryButtonVisible();
        String str2 = "Invisible";
        if (!footerBarMixinMetrics.primaryButtonVisibility.equals("Unknown")) {
            str = footerBarMixinMetrics.primaryButtonVisibility;
        } else if (isPrimaryButtonVisible) {
            str = "Visible";
        } else {
            str = str2;
        }
        footerBarMixinMetrics.primaryButtonVisibility = str;
        FooterBarMixinMetrics footerBarMixinMetrics2 = footerBarMixin.metrics;
        boolean isSecondaryButtonVisible = footerBarMixin.isSecondaryButtonVisible();
        if (!footerBarMixinMetrics2.secondaryButtonVisibility.equals("Unknown")) {
            str2 = footerBarMixinMetrics2.secondaryButtonVisibility;
        } else if (isSecondaryButtonVisible) {
            str2 = "Visible";
        }
        footerBarMixinMetrics2.secondaryButtonVisibility = str2;
    }

    public final void onBeforeTemplateInflated(AttributeSet attributeSet, int i) {
        boolean z = true;
        this.usePartnerResourceAttr = true;
        Activity lookupActivityFromContext = PartnerConfigHelper.lookupActivityFromContext(getContext());
        this.activity = lookupActivityFromContext;
        boolean isAnySetupWizard = WizardManagerHelper.isAnySetupWizard(lookupActivityFromContext.getIntent());
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SucPartnerCustomizationLayout, i, 0);
        if (!obtainStyledAttributes.hasValue(2)) {
            LOG.e("Attribute sucUsePartnerResource not found in " + this.activity.getComponentName());
        }
        if (!isAnySetupWizard && !obtainStyledAttributes.getBoolean(2, true)) {
            z = false;
        }
        this.usePartnerResourceAttr = z;
        this.useDynamicColor = obtainStyledAttributes.hasValue(0);
        this.useFullDynamicColorAttr = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        Logger logger = LOG;
        String str = "activity=" + this.activity.getClass().getSimpleName() + " isSetupFlow=" + isAnySetupWizard + " enablePartnerResourceLoading=true usePartnerResourceAttr=" + this.usePartnerResourceAttr + " useDynamicColor=" + this.useDynamicColor + " useFullDynamicColorAttr=" + this.useFullDynamicColorAttr;
        if (Log.isLoggable("SetupLibrary", 3)) {
            Log.d("SetupLibrary", logger.prefix.concat(str));
        } else {
            logger.getClass();
        }
    }

    public final void onDetachedFromWindow() {
        PersistableBundle persistableBundle;
        PersistableBundle persistableBundle2;
        super.onDetachedFromWindow();
        if (WizardManagerHelper.isAnySetupWizard(this.activity.getIntent())) {
            FooterBarMixin footerBarMixin = (FooterBarMixin) getMixin(FooterBarMixin.class);
            FooterBarMixinMetrics footerBarMixinMetrics = footerBarMixin.metrics;
            boolean isPrimaryButtonVisible = footerBarMixin.isPrimaryButtonVisible();
            boolean isSecondaryButtonVisible = footerBarMixin.isSecondaryButtonVisible();
            footerBarMixinMetrics.primaryButtonVisibility = FooterBarMixinMetrics.updateButtonVisibilityState(footerBarMixinMetrics.primaryButtonVisibility, isPrimaryButtonVisible);
            footerBarMixinMetrics.secondaryButtonVisibility = FooterBarMixinMetrics.updateButtonVisibilityState(footerBarMixinMetrics.secondaryButtonVisibility, isSecondaryButtonVisible);
            FooterButton footerButton = footerBarMixin.primaryButton;
            FooterButton footerButton2 = footerBarMixin.secondaryButton;
            if (footerButton != null) {
                persistableBundle = footerButton.getMetrics("PrimaryFooterButton");
            } else {
                persistableBundle = PersistableBundle.EMPTY;
            }
            if (footerButton2 != null) {
                persistableBundle2 = footerButton2.getMetrics("SecondaryFooterButton");
            } else {
                persistableBundle2 = PersistableBundle.EMPTY;
            }
            PersistableBundle persistableBundle3 = PersistableBundle.EMPTY;
            FooterBarMixinMetrics footerBarMixinMetrics2 = footerBarMixin.metrics;
            footerBarMixinMetrics2.getClass();
            PersistableBundle persistableBundle4 = new PersistableBundle();
            persistableBundle4.putString(FooterBarMixinMetrics.EXTRA_PRIMARY_BUTTON_VISIBILITY, footerBarMixinMetrics2.primaryButtonVisibility);
            persistableBundle4.putString(FooterBarMixinMetrics.EXTRA_SECONDARY_BUTTON_VISIBILITY, footerBarMixinMetrics2.secondaryButtonVisibility);
            PersistableBundle[] persistableBundleArr = {persistableBundle2, persistableBundle3};
            Logger logger = PersistableBundles.LOG;
            ArrayList<PersistableBundle> arrayList = new ArrayList<>();
            arrayList.addAll(Arrays.asList(new PersistableBundle[]{persistableBundle4, persistableBundle}));
            Collections.addAll(arrayList, persistableBundleArr);
            PersistableBundle persistableBundle5 = new PersistableBundle();
            for (PersistableBundle persistableBundle6 : arrayList) {
                for (String str : persistableBundle6.keySet()) {
                    Preconditions.checkArgument("Found duplicate key [" + str + "] while attempting to merge bundles.", !persistableBundle5.containsKey(str));
                }
                persistableBundle5.putAll(persistableBundle6);
            }
            Context context = getContext();
            MetricKey metricKey = MetricKey.get("SetupCompatMetrics", this.activity);
            Parcelable.Creator creator = CustomEvent.CREATOR;
            PersistableBundle persistableBundle7 = PersistableBundle.EMPTY;
            long millis = TimeUnit.NANOSECONDS.toMillis(ClockProvider.ticker.read());
            PersistableBundles.assertIsValid(persistableBundle5);
            PersistableBundles.assertIsValid(persistableBundle7);
            SetupMetricsLogger.logCustomEvent(context, new CustomEvent(millis, metricKey, persistableBundle5, persistableBundle7));
        }
        getViewTreeObserver().removeOnWindowFocusChangeListener(this.windowFocusChangeListener);
    }

    public final boolean shouldApplyDynamicColor() {
        if (this.useDynamicColor && PartnerConfigHelper.get(getContext()).isAvailable()) {
            return true;
        }
        return false;
    }

    public final boolean shouldApplyPartnerResource() {
        if (this.usePartnerResourceAttr && PartnerConfigHelper.get(getContext()).isAvailable()) {
            return true;
        }
        return false;
    }

    public final boolean useFullDynamicColor() {
        if (!shouldApplyDynamicColor() || !this.useFullDynamicColorAttr) {
            return false;
        }
        return true;
    }

    public PartnerCustomizationLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init$1(attributeSet, 2130970022);
    }

    public PartnerCustomizationLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init$1(attributeSet, i);
    }
}
