package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.content.ComponentName;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import com.android.internal.graphics.ColorUtils;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.plugins.FalsingManager;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLogger;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class DateSmartspaceView extends LinearLayout implements BcSmartspaceDataPlugin.SmartspaceView {
    public static final boolean DEBUG = Log.isLoggable("DateSmartspaceView", 3);
    public final AnonymousClass1 mAodSettingsObserver;
    public int mCurrentTextColor;
    public BcSmartspaceDataPlugin mDataProvider;
    public final SmartspaceAction mDateAction;
    public final SmartspaceTarget mDateTarget;
    public IcuDateTextView mDateView;
    public final DoubleShadowIconDrawable mDndIconDrawable;
    public ImageView mDndImageView;
    public float mDozeAmount;
    public boolean mIsAodEnabled;
    public BcSmartspaceCardLoggingInfo mLoggingInfo;
    public final BcNextAlarmData mNextAlarmData;
    public final DoubleShadowIconDrawable mNextAlarmIconDrawable;
    public DoubleShadowTextView mNextAlarmTextView;
    public int mPrimaryTextColor;
    public String mUiSurface;

    public DateSmartspaceView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onAttachedToWindow() {
        DateSmartspaceView$$ExternalSyntheticLambda0 dateSmartspaceView$$ExternalSyntheticLambda0;
        super.onAttachedToWindow();
        if (TextUtils.equals(this.mUiSurface, BcSmartspaceDataPlugin.UI_SURFACE_LOCK_SCREEN_AOD)) {
            boolean z = false;
            try {
                getContext().getContentResolver().registerContentObserver(Settings.Secure.getUriFor("doze_always_on"), false, this.mAodSettingsObserver, -1);
            } catch (Exception e) {
                Log.w("DateSmartspaceView", "Unable to register DOZE_ALWAYS_ON content observer: ", e);
            }
            Context context = getContext();
            if (Settings.Secure.getIntForUser(context.getContentResolver(), "doze_always_on", 0, context.getUserId()) == 1) {
                z = true;
            }
            this.mIsAodEnabled = z;
        }
        BcSmartspaceCardLoggingInfo.Builder builder = new BcSmartspaceCardLoggingInfo.Builder();
        builder.mInstanceId = InstanceId.create(this.mDateTarget);
        builder.mFeatureType = this.mDateTarget.getFeatureType();
        builder.mDisplaySurface = BcSmartSpaceUtil.getLoggingDisplaySurface(this.mDozeAmount, this.mUiSurface);
        getContext().getPackageManager();
        builder.mUid = -1;
        BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo = new BcSmartspaceCardLoggingInfo(builder);
        this.mLoggingInfo = bcSmartspaceCardLoggingInfo;
        IcuDateTextView icuDateTextView = this.mDateView;
        SmartspaceTarget smartspaceTarget = this.mDateTarget;
        SmartspaceAction smartspaceAction = this.mDateAction;
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin = this.mDataProvider;
        if (bcSmartspaceDataPlugin == null) {
            dateSmartspaceView$$ExternalSyntheticLambda0 = null;
        } else {
            dateSmartspaceView$$ExternalSyntheticLambda0 = new DateSmartspaceView$$ExternalSyntheticLambda0(bcSmartspaceDataPlugin);
        }
        BcSmartSpaceUtil.setOnClickListener((View) icuDateTextView, smartspaceTarget, smartspaceAction, (BcSmartspaceDataPlugin.SmartspaceEventNotifier) dateSmartspaceView$$ExternalSyntheticLambda0, "DateSmartspaceView", bcSmartspaceCardLoggingInfo, 0);
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().getContentResolver().unregisterContentObserver(this.mAodSettingsObserver);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mDateView = (IcuDateTextView) findViewById(2131362381);
        this.mNextAlarmTextView = (DoubleShadowTextView) findViewById(2131361941);
        this.mDndImageView = (ImageView) findViewById(2131362459);
    }

    public final void registerDataProvider(BcSmartspaceDataPlugin bcSmartspaceDataPlugin) {
        this.mDataProvider = bcSmartspaceDataPlugin;
    }

    public final void setDnd(Drawable drawable, String str) {
        if (drawable == null) {
            BcSmartspaceTemplateDataUtils.updateVisibility(this.mDndImageView, 8);
        } else {
            this.mDndIconDrawable.setIcon(drawable.mutate());
            this.mDndImageView.setImageDrawable(this.mDndIconDrawable);
            this.mDndImageView.setContentDescription(str);
            BcSmartspaceTemplateDataUtils.updateVisibility(this.mDndImageView, 0);
        }
        updateColorForExtras();
    }

    public final void setDozeAmount(float f) {
        int loggingDisplaySurface;
        this.mDozeAmount = f;
        int blendARGB = ColorUtils.blendARGB(this.mPrimaryTextColor, -1, f);
        this.mCurrentTextColor = blendARGB;
        this.mDateView.setTextColor(blendARGB);
        updateColorForExtras();
        if (this.mLoggingInfo != null && (loggingDisplaySurface = BcSmartSpaceUtil.getLoggingDisplaySurface(this.mDozeAmount, this.mUiSurface)) != -1) {
            if (loggingDisplaySurface != 3 || this.mIsAodEnabled) {
                if (DEBUG) {
                    Log.d("DateSmartspaceView", "@" + Integer.toHexString(hashCode()) + ", setDozeAmount: Logging SMARTSPACE_CARD_SEEN, loggingSurface = " + loggingDisplaySurface);
                }
                BcSmartspaceCardLoggingInfo.Builder builder = new BcSmartspaceCardLoggingInfo.Builder();
                BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo = this.mLoggingInfo;
                builder.mInstanceId = bcSmartspaceCardLoggingInfo.mInstanceId;
                builder.mFeatureType = bcSmartspaceCardLoggingInfo.mFeatureType;
                builder.mDisplaySurface = loggingDisplaySurface;
                builder.mUid = bcSmartspaceCardLoggingInfo.mUid;
                BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo2 = new BcSmartspaceCardLoggingInfo(builder);
                BcSmartspaceEvent bcSmartspaceEvent = BcSmartspaceEvent.SMARTSPACE_CARD_SEEN;
                BcSmartspaceCardLogger.log(bcSmartspaceEvent, bcSmartspaceCardLoggingInfo2);
                if (this.mNextAlarmData.mImage != null) {
                    BcSmartspaceCardLoggingInfo.Builder builder2 = new BcSmartspaceCardLoggingInfo.Builder();
                    builder2.mInstanceId = InstanceId.create("upcoming_alarm_card_94510_12684");
                    builder2.mFeatureType = 23;
                    builder2.mDisplaySurface = loggingDisplaySurface;
                    builder2.mUid = this.mLoggingInfo.mUid;
                    BcSmartspaceCardLogger.log(bcSmartspaceEvent, new BcSmartspaceCardLoggingInfo(builder2));
                }
            }
        }
    }

    public final void setFalsingManager(FalsingManager falsingManager) {
        BcSmartSpaceUtil.sFalsingManager = falsingManager;
    }

    public final void setIntentStarter(BcSmartspaceDataPlugin.IntentStarter intentStarter) {
        BcSmartSpaceUtil.sIntentStarter = intentStarter;
    }

    public final void setNextAlarm(Drawable drawable, String str) {
        String str2;
        DateSmartspaceView$$ExternalSyntheticLambda0 dateSmartspaceView$$ExternalSyntheticLambda0;
        BcNextAlarmData bcNextAlarmData = this.mNextAlarmData;
        bcNextAlarmData.mImage = drawable;
        if (drawable != null) {
            drawable.mutate();
        }
        bcNextAlarmData.mDescription = str;
        if (this.mNextAlarmData.mImage == null) {
            BcSmartspaceTemplateDataUtils.updateVisibility(this.mNextAlarmTextView, 8);
        } else {
            this.mNextAlarmTextView.setContentDescription(getContext().getString(2131951785, new Object[]{str}));
            DoubleShadowTextView doubleShadowTextView = this.mNextAlarmTextView;
            BcNextAlarmData bcNextAlarmData2 = this.mNextAlarmData;
            bcNextAlarmData2.getClass();
            DateSmartspaceView$$ExternalSyntheticLambda0 dateSmartspaceView$$ExternalSyntheticLambda02 = null;
            if (!TextUtils.isEmpty((CharSequence) null)) {
                str2 = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), bcNextAlarmData2.mDescription, " · null");
            } else {
                str2 = bcNextAlarmData2.mDescription;
            }
            doubleShadowTextView.setText(str2);
            DoubleShadowIconDrawable doubleShadowIconDrawable = this.mNextAlarmIconDrawable;
            Drawable drawable2 = this.mNextAlarmData.mImage;
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131165919);
            drawable2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            doubleShadowIconDrawable.setIcon(drawable2);
            this.mNextAlarmTextView.setCompoundDrawablesRelative(this.mNextAlarmIconDrawable, (Drawable) null, (Drawable) null, (Drawable) null);
            BcSmartspaceTemplateDataUtils.updateVisibility(this.mNextAlarmTextView, 0);
            BcNextAlarmData bcNextAlarmData3 = this.mNextAlarmData;
            DoubleShadowTextView doubleShadowTextView2 = this.mNextAlarmTextView;
            BcSmartspaceDataPlugin bcSmartspaceDataPlugin = this.mDataProvider;
            if (bcSmartspaceDataPlugin == null) {
                dateSmartspaceView$$ExternalSyntheticLambda0 = null;
            } else {
                dateSmartspaceView$$ExternalSyntheticLambda0 = new DateSmartspaceView$$ExternalSyntheticLambda0(bcSmartspaceDataPlugin);
            }
            int loggingDisplaySurface = BcSmartSpaceUtil.getLoggingDisplaySurface(this.mDozeAmount, this.mUiSurface);
            bcNextAlarmData3.getClass();
            BcNextAlarmData.setOnClickListener(doubleShadowTextView2, dateSmartspaceView$$ExternalSyntheticLambda0, loggingDisplaySurface);
            BcNextAlarmData bcNextAlarmData4 = this.mNextAlarmData;
            DoubleShadowTextView doubleShadowTextView3 = this.mNextAlarmTextView;
            BcSmartspaceDataPlugin bcSmartspaceDataPlugin2 = this.mDataProvider;
            if (bcSmartspaceDataPlugin2 != null) {
                dateSmartspaceView$$ExternalSyntheticLambda02 = new DateSmartspaceView$$ExternalSyntheticLambda0(bcSmartspaceDataPlugin2);
            }
            int loggingDisplaySurface2 = BcSmartSpaceUtil.getLoggingDisplaySurface(this.mDozeAmount, this.mUiSurface);
            bcNextAlarmData4.getClass();
            BcNextAlarmData.setOnClickListener(doubleShadowTextView3, dateSmartspaceView$$ExternalSyntheticLambda02, loggingDisplaySurface2);
        }
        updateColorForExtras();
    }

    public final void setPrimaryTextColor(int i) {
        this.mPrimaryTextColor = i;
        int blendARGB = ColorUtils.blendARGB(i, -1, this.mDozeAmount);
        this.mCurrentTextColor = blendARGB;
        this.mDateView.setTextColor(blendARGB);
        updateColorForExtras();
    }

    public final void setUiSurface(String str) {
        if (!isAttachedToWindow()) {
            this.mUiSurface = str;
            if (TextUtils.equals(str, BcSmartspaceDataPlugin.UI_SURFACE_LOCK_SCREEN_AOD)) {
                IcuDateTextView icuDateTextView = this.mDateView;
                if (!icuDateTextView.isAttachedToWindow()) {
                    icuDateTextView.mUpdatesOnAod = true;
                    return;
                }
                throw new IllegalStateException("Must call before attaching view to window.");
            }
            return;
        }
        throw new IllegalStateException("Must call before attaching view to window.");
    }

    public final void updateColorForExtras() {
        DoubleShadowTextView doubleShadowTextView = this.mNextAlarmTextView;
        if (doubleShadowTextView != null) {
            doubleShadowTextView.setTextColor(this.mCurrentTextColor);
            this.mNextAlarmIconDrawable.setTint(this.mCurrentTextColor);
        }
        ImageView imageView = this.mDndImageView;
        if (imageView != null && imageView.getDrawable() != null) {
            imageView.getDrawable().setTint(this.mCurrentTextColor);
            imageView.invalidate();
        }
    }

    public DateSmartspaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARNING: type inference failed for: r4v9, types: [java.lang.Object, com.google.android.systemui.smartspace.BcNextAlarmData] */
    public DateSmartspaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mUiSurface = null;
        this.mDozeAmount = 0.0f;
        this.mDateTarget = new SmartspaceTarget.Builder("date_card_794317_92634", new ComponentName(getContext(), getClass()), getContext().getUser()).setFeatureType(1).build();
        this.mDateAction = new SmartspaceAction.Builder("dateId", "Date").setIntent(BcSmartSpaceUtil.getOpenCalendarIntent()).build();
        this.mNextAlarmData = new Object();
        this.mAodSettingsObserver = new ContentObserver(new Handler()) {
            public final void onChange(boolean z) {
                DateSmartspaceView dateSmartspaceView = DateSmartspaceView.this;
                boolean z2 = DateSmartspaceView.DEBUG;
                Context context = dateSmartspaceView.getContext();
                boolean z3 = false;
                if (Settings.Secure.getIntForUser(context.getContentResolver(), "doze_always_on", 0, context.getUserId()) == 1) {
                    z3 = true;
                }
                DateSmartspaceView dateSmartspaceView2 = DateSmartspaceView.this;
                if (dateSmartspaceView2.mIsAodEnabled != z3) {
                    dateSmartspaceView2.mIsAodEnabled = z3;
                }
            }
        };
        context.getTheme().applyStyle(2132017753, false);
        this.mNextAlarmIconDrawable = new DoubleShadowIconDrawable(context);
        this.mDndIconDrawable = new DoubleShadowIconDrawable(context);
    }
}
