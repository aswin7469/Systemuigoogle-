package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.DeviceConfig;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.google.android.systemui.googlebattery.AdaptiveChargingManager;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AdaptiveChargingNotification {
    public final AdaptiveChargingManager mAdaptiveChargingManager;
    boolean mAdaptiveChargingQueryInBackground = true;
    public final Context mContext;
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public final NotificationManager mNotificationManager;
    public final UiEventLogger mUiEventLogger;
    boolean mWasActive = false;

    public AdaptiveChargingNotification(Context context, AdaptiveChargingManager adaptiveChargingManager, UiEventLogger uiEventLogger) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        this.mUiEventLogger = uiEventLogger;
        this.mAdaptiveChargingManager = adaptiveChargingManager;
    }

    public final void cancelNotification() {
        if (this.mWasActive) {
            this.mNotificationManager.cancelAsUser("adaptive_charging", 2131951877, UserHandle.ALL);
            this.mWasActive = false;
        }
    }

    public final void checkAdaptiveChargingStatus(final boolean z) {
        this.mAdaptiveChargingManager.getClass();
        if (DeviceConfig.getBoolean("adaptive_charging", "adaptive_charging_notification", false)) {
            AnonymousClass1 r0 = new AdaptiveChargingManager.AdaptiveChargingStatusReceiver() {
                public final void onReceiveStatus(int i, String str) {
                    AdaptiveChargingNotification.this.mHandler.post(new AdaptiveChargingNotification$1$$ExternalSyntheticLambda0(this, str, i, z));
                }
            };
            if (!this.mAdaptiveChargingQueryInBackground) {
                AdaptiveChargingManager.queryStatus(r0);
            } else {
                AsyncTask.execute(new AdaptiveChargingNotification$$ExternalSyntheticLambda0(this, r0));
            }
        }
    }

    public void resolveBatteryChangedIntent(Intent intent) {
        boolean z;
        if (intent.getIntExtra("plugged", 0) != 0) {
            z = true;
        } else {
            z = false;
        }
        boolean isCharged = BatteryStatus.isCharged(intent.getIntExtra("status", 1), BatteryStatus.getBatteryLevel(intent));
        if (!z || isCharged) {
            cancelNotification();
        } else {
            checkAdaptiveChargingStatus(false);
        }
    }
}
