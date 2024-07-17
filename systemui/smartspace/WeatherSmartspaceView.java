package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.SmartspaceUtils;
import android.app.smartspace.uitemplatedata.BaseTemplateData;
import android.app.smartspace.uitemplatedata.Icon;
import android.app.smartspace.uitemplatedata.TapAction;
import android.app.smartspace.uitemplatedata.Text;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.android.internal.graphics.ColorUtils;
import com.android.systemui.bcsmartspace.R$styleable;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.plugins.FalsingManager;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLogger;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggerUtil;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import com.google.android.systemui.smartspace.utils.ContentDescriptionUtil;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class WeatherSmartspaceView extends FrameLayout implements BcSmartspaceDataPlugin.SmartspaceTargetListener, BcSmartspaceDataPlugin.SmartspaceView {
    public static final boolean DEBUG = Log.isLoggable("WeatherSmartspaceView", 3);
    public final AnonymousClass1 mAodSettingsObserver;
    public BcSmartspaceDataPlugin mDataProvider;
    public float mDozeAmount;
    public final DoubleShadowIconDrawable mIconDrawable;
    public final int mIconSize;
    public boolean mIsAodEnabled;
    public BcSmartspaceCardLoggingInfo mLoggingInfo;
    public int mPrimaryTextColor;
    public final boolean mRemoveTextDescent;
    public final int mTextDescentExtraPadding;
    public String mUiSurface;
    public DoubleShadowTextView mView;

    public WeatherSmartspaceView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (TextUtils.equals(this.mUiSurface, BcSmartspaceDataPlugin.UI_SURFACE_LOCK_SCREEN_AOD)) {
            boolean z = false;
            try {
                getContext().getContentResolver().registerContentObserver(Settings.Secure.getUriFor("doze_always_on"), false, this.mAodSettingsObserver, -1);
            } catch (Exception e) {
                Log.w("WeatherSmartspaceView", "Unable to register DOZE_ALWAYS_ON content observer: ", e);
            }
            Context context = getContext();
            if (Settings.Secure.getIntForUser(context.getContentResolver(), "doze_always_on", 0, context.getUserId()) == 1) {
                z = true;
            }
            this.mIsAodEnabled = z;
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().getContentResolver().unregisterContentObserver(this.mAodSettingsObserver);
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin = this.mDataProvider;
        if (bcSmartspaceDataPlugin != null) {
            bcSmartspaceDataPlugin.unregisterListener(this);
        }
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mView = (DoubleShadowTextView) findViewById(2131364063);
    }

    public final void onSmartspaceTargetsUpdated(List list) {
        CharSequence charSequence;
        if (list.size() <= 1) {
            if (list.isEmpty() && TextUtils.equals(this.mUiSurface, BcSmartspaceDataPlugin.UI_SURFACE_DREAM)) {
                return;
            }
            if (list.isEmpty()) {
                BcSmartspaceTemplateDataUtils.updateVisibility(this.mView, 8);
                return;
            }
            BcSmartspaceTemplateDataUtils.updateVisibility(this.mView, 0);
            SmartspaceTarget smartspaceTarget = (SmartspaceTarget) list.get(0);
            if (smartspaceTarget.getFeatureType() == 1) {
                boolean containsValidTemplateType = BcSmartspaceCardLoggerUtil.containsValidTemplateType(smartspaceTarget.getTemplateData());
                SmartspaceAction headerAction = smartspaceTarget.getHeaderAction();
                if (containsValidTemplateType || headerAction != null) {
                    BcSmartspaceCardLoggingInfo.Builder builder = new BcSmartspaceCardLoggingInfo.Builder();
                    builder.mInstanceId = InstanceId.create(smartspaceTarget);
                    builder.mFeatureType = smartspaceTarget.getFeatureType();
                    builder.mDisplaySurface = BcSmartSpaceUtil.getLoggingDisplaySurface(this.mDozeAmount, this.mUiSurface);
                    getContext().getPackageManager();
                    builder.mUid = -1;
                    builder.mDimensionalInfo = BcSmartspaceCardLoggerUtil.createDimensionalLoggingInfo(smartspaceTarget.getTemplateData());
                    this.mLoggingInfo = builder.build();
                    WeatherSmartspaceView$$ExternalSyntheticLambda0 weatherSmartspaceView$$ExternalSyntheticLambda0 = null;
                    if (!containsValidTemplateType) {
                        SmartspaceAction headerAction2 = smartspaceTarget.getHeaderAction();
                        if (headerAction2 == null) {
                            Log.d("WeatherSmartspaceView", "Passed-in header action is null");
                        } else {
                            CharSequence title = headerAction2.getTitle();
                            this.mView.setText(title.toString());
                            ContentDescriptionUtil.setFormattedContentDescription("WeatherSmartspaceView", this.mView, title, headerAction2.getContentDescription());
                            this.mIconDrawable.setIcon(BcSmartSpaceUtil.getIconDrawableWithCustomSize(headerAction2.getIcon(), getContext(), this.mIconSize));
                            this.mView.setCompoundDrawablesRelative(this.mIconDrawable, (Drawable) null, (Drawable) null, (Drawable) null);
                            DoubleShadowTextView doubleShadowTextView = this.mView;
                            BcSmartspaceDataPlugin bcSmartspaceDataPlugin = this.mDataProvider;
                            if (bcSmartspaceDataPlugin != null) {
                                weatherSmartspaceView$$ExternalSyntheticLambda0 = new WeatherSmartspaceView$$ExternalSyntheticLambda0(bcSmartspaceDataPlugin, 0);
                            }
                            BcSmartSpaceUtil.setOnClickListener((View) doubleShadowTextView, smartspaceTarget, headerAction2, (BcSmartspaceDataPlugin.SmartspaceEventNotifier) weatherSmartspaceView$$ExternalSyntheticLambda0, "WeatherSmartspaceView", this.mLoggingInfo);
                        }
                    } else if (smartspaceTarget.getTemplateData() != null) {
                        BaseTemplateData.SubItemInfo subtitleItem = smartspaceTarget.getTemplateData().getSubtitleItem();
                        if (subtitleItem == null) {
                            Log.d("WeatherSmartspaceView", "Passed-in item info is null");
                        } else {
                            Text text = subtitleItem.getText();
                            BcSmartspaceTemplateDataUtils.setText(this.mView, text);
                            Icon icon = subtitleItem.getIcon();
                            if (icon != null) {
                                Drawable iconDrawableWithCustomSize = BcSmartSpaceUtil.getIconDrawableWithCustomSize(icon.getIcon(), getContext(), this.mIconSize);
                                this.mIconDrawable.setIcon(iconDrawableWithCustomSize);
                                this.mView.setCompoundDrawablesRelative(iconDrawableWithCustomSize, (Drawable) null, (Drawable) null, (Drawable) null);
                            }
                            DoubleShadowTextView doubleShadowTextView2 = this.mView;
                            CharSequence charSequence2 = "";
                            if (SmartspaceUtils.isEmpty(text)) {
                                charSequence = charSequence2;
                            } else {
                                charSequence = text.getText();
                            }
                            if (icon != null) {
                                charSequence2 = icon.getContentDescription();
                            }
                            ContentDescriptionUtil.setFormattedContentDescription("WeatherSmartspaceView", doubleShadowTextView2, charSequence, charSequence2);
                            TapAction tapAction = subtitleItem.getTapAction();
                            if (tapAction != null) {
                                DoubleShadowTextView doubleShadowTextView3 = this.mView;
                                BcSmartspaceDataPlugin bcSmartspaceDataPlugin2 = this.mDataProvider;
                                if (bcSmartspaceDataPlugin2 != null) {
                                    weatherSmartspaceView$$ExternalSyntheticLambda0 = new WeatherSmartspaceView$$ExternalSyntheticLambda0(bcSmartspaceDataPlugin2, 1);
                                }
                                BcSmartSpaceUtil.setOnClickListener((View) doubleShadowTextView3, smartspaceTarget, tapAction, (BcSmartspaceDataPlugin.SmartspaceEventNotifier) weatherSmartspaceView$$ExternalSyntheticLambda0, "WeatherSmartspaceView", this.mLoggingInfo);
                            }
                        }
                    }
                    if (this.mRemoveTextDescent) {
                        DoubleShadowTextView doubleShadowTextView4 = this.mView;
                        doubleShadowTextView4.setPaddingRelative(0, 0, 0, this.mTextDescentExtraPadding - ((int) Math.floor((double) doubleShadowTextView4.getPaint().getFontMetrics().descent)));
                    }
                }
            }
        }
    }

    public final void registerDataProvider(BcSmartspaceDataPlugin bcSmartspaceDataPlugin) {
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin2 = this.mDataProvider;
        if (bcSmartspaceDataPlugin2 != null) {
            bcSmartspaceDataPlugin2.unregisterListener(this);
        }
        this.mDataProvider = bcSmartspaceDataPlugin;
        bcSmartspaceDataPlugin.registerListener(this);
    }

    public final void setDozeAmount(float f) {
        int loggingDisplaySurface;
        this.mDozeAmount = f;
        this.mView.setTextColor(ColorUtils.blendARGB(this.mPrimaryTextColor, -1, f));
        if (this.mLoggingInfo != null && (loggingDisplaySurface = BcSmartSpaceUtil.getLoggingDisplaySurface(this.mDozeAmount, this.mUiSurface)) != -1) {
            if (loggingDisplaySurface != 3 || this.mIsAodEnabled) {
                BcSmartspaceCardLoggingInfo.Builder builder = new BcSmartspaceCardLoggingInfo.Builder();
                BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo = this.mLoggingInfo;
                builder.mInstanceId = bcSmartspaceCardLoggingInfo.mInstanceId;
                builder.mFeatureType = bcSmartspaceCardLoggingInfo.mFeatureType;
                builder.mDisplaySurface = loggingDisplaySurface;
                builder.mUid = bcSmartspaceCardLoggingInfo.mUid;
                BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo2 = new BcSmartspaceCardLoggingInfo(builder);
                if (DEBUG) {
                    Log.d("WeatherSmartspaceView", "@" + Integer.toHexString(hashCode()) + ", setDozeAmount: Logging SMARTSPACE_CARD_SEEN, loggingSurface = " + loggingDisplaySurface);
                }
                BcSmartspaceCardLogger.log(BcSmartspaceEvent.SMARTSPACE_CARD_SEEN, bcSmartspaceCardLoggingInfo2);
            }
        }
    }

    public final void setFalsingManager(FalsingManager falsingManager) {
        BcSmartSpaceUtil.sFalsingManager = falsingManager;
    }

    public final void setIntentStarter(BcSmartspaceDataPlugin.IntentStarter intentStarter) {
        BcSmartSpaceUtil.sIntentStarter = intentStarter;
    }

    public final void setPrimaryTextColor(int i) {
        this.mPrimaryTextColor = i;
        this.mView.setTextColor(ColorUtils.blendARGB(i, -1, this.mDozeAmount));
    }

    public final void setUiSurface(String str) {
        if (!isAttachedToWindow()) {
            this.mUiSurface = str;
            return;
        }
        throw new IllegalStateException("Must call before attaching view to window.");
    }

    public WeatherSmartspaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: finally extract failed */
    public WeatherSmartspaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mUiSurface = null;
        this.mDozeAmount = 0.0f;
        this.mLoggingInfo = null;
        this.mAodSettingsObserver = new ContentObserver(new Handler()) {
            public final void onChange(boolean z) {
                WeatherSmartspaceView weatherSmartspaceView = WeatherSmartspaceView.this;
                boolean z2 = WeatherSmartspaceView.DEBUG;
                Context context = weatherSmartspaceView.getContext();
                boolean z3 = false;
                if (Settings.Secure.getIntForUser(context.getContentResolver(), "doze_always_on", 0, context.getUserId()) == 1) {
                    z3 = true;
                }
                WeatherSmartspaceView weatherSmartspaceView2 = WeatherSmartspaceView.this;
                if (weatherSmartspaceView2.mIsAodEnabled != z3) {
                    weatherSmartspaceView2.mIsAodEnabled = z3;
                }
            }
        };
        context.getTheme().applyStyle(2132017752, false);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.WeatherSmartspaceView, 0, 0);
        try {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, context.getResources().getDimensionPixelSize(2131165887));
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(0, context.getResources().getDimensionPixelSize(2131165885));
            this.mRemoveTextDescent = obtainStyledAttributes.getBoolean(2, false);
            this.mTextDescentExtraPadding = obtainStyledAttributes.getDimensionPixelSize(3, 0);
            obtainStyledAttributes.recycle();
            this.mIconSize = dimensionPixelSize;
            this.mIconDrawable = new DoubleShadowIconDrawable(context, dimensionPixelSize, dimensionPixelSize2);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
