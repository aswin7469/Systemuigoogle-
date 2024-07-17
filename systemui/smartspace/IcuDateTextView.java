package com.google.android.systemui.smartspace;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import java.util.Locale;
import java.util.Objects;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class IcuDateTextView extends DoubleShadowTextView {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final AnonymousClass1 mAodSettingsObserver;
    public DateFormat mFormatter;
    public Handler mHandler;
    public final AnonymousClass2 mIntentReceiver;
    public boolean mIsAodEnabled;
    public boolean mIsInteractive;
    public String mText;
    public final IcuDateTextView$$ExternalSyntheticLambda0 mTicker;
    public boolean mUpdatesOnAod;

    public IcuDateTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mUpdatesOnAod) {
            boolean z = false;
            try {
                getContext().getContentResolver().registerContentObserver(Settings.Secure.getUriFor("doze_always_on"), false, this.mAodSettingsObserver, -1);
            } catch (Exception e) {
                Log.w("IcuDateTextView", "Unable to register DOZE_ALWAYS_ON content observer: ", e);
            }
            Context context = getContext();
            if (Settings.Secure.getIntForUser(context.getContentResolver(), "doze_always_on", 0, context.getUserId()) == 1) {
                z = true;
            }
            this.mIsAodEnabled = z;
        }
        this.mHandler = new Handler();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        getContext().registerReceiver(this.mIntentReceiver, intentFilter);
        this.mIsInteractive = ((PowerManager) this.mContext.getSystemService(PowerManager.class)).isInteractive();
        onTimeChanged(true);
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mHandler != null) {
            getContext().unregisterReceiver(this.mIntentReceiver);
            this.mHandler = null;
        }
        if (this.mUpdatesOnAod) {
            getContext().getContentResolver().unregisterContentObserver(this.mAodSettingsObserver);
        }
    }

    public final void onTimeChanged(boolean z) {
        if (this.mFormatter == null || z) {
            DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(getContext().getString(2131953861), Locale.getDefault());
            this.mFormatter = instanceForSkeleton;
            instanceForSkeleton.setContext(DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE);
        }
        if (isShown()) {
            String format = this.mFormatter.format(Long.valueOf(System.currentTimeMillis()));
            if (!Objects.equals(this.mText, format)) {
                this.mText = format;
                setText(format);
                setContentDescription(format);
            }
        }
    }

    public final void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        rescheduleTicker();
    }

    public final void rescheduleTicker() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mTicker);
            if ((this.mIsInteractive || (this.mUpdatesOnAod && this.mIsAodEnabled)) && isAggregatedVisible()) {
                this.mTicker.run();
            }
        }
    }

    public IcuDateTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mAodSettingsObserver = new ContentObserver(new Handler()) {
            public final void onChange(boolean z) {
                IcuDateTextView icuDateTextView = IcuDateTextView.this;
                int i = IcuDateTextView.$r8$clinit;
                Context context = icuDateTextView.getContext();
                boolean z2 = false;
                if (Settings.Secure.getIntForUser(context.getContentResolver(), "doze_always_on", 0, context.getUserId()) == 1) {
                    z2 = true;
                }
                IcuDateTextView icuDateTextView2 = IcuDateTextView.this;
                if (icuDateTextView2.mIsAodEnabled != z2) {
                    icuDateTextView2.mIsAodEnabled = z2;
                    icuDateTextView2.rescheduleTicker();
                }
            }
        };
        this.mTicker = new IcuDateTextView$$ExternalSyntheticLambda0(this);
        this.mIntentReceiver = new BroadcastReceiver() {
            public final void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                    IcuDateTextView icuDateTextView = IcuDateTextView.this;
                    icuDateTextView.mIsInteractive = true;
                    icuDateTextView.rescheduleTicker();
                } else if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                    IcuDateTextView icuDateTextView2 = IcuDateTextView.this;
                    icuDateTextView2.mIsInteractive = false;
                    icuDateTextView2.rescheduleTicker();
                } else {
                    IcuDateTextView icuDateTextView3 = IcuDateTextView.this;
                    int i = IcuDateTextView.$r8$clinit;
                    icuDateTextView3.onTimeChanged(!"android.intent.action.TIME_TICK".equals(intent.getAction()));
                }
            }
        };
    }
}
