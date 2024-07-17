package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.os.UserHandle;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.Utils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class WirelessChargingNotification {
    static final long DISABLED_TIMESTAMP = Long.MIN_VALUE;
    public final Context mContext;
    public boolean mIsNotificationActive = false;
    public boolean mIsWirelessCharging;
    NotificationManager mNotificationManager;
    public final UiEventLogger mUiEventLogger;

    public WirelessChargingNotification(Context context, UiEventLogger uiEventLogger) {
        this.mContext = context;
        this.mUiEventLogger = uiEventLogger;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    public final void cancelNotification() {
        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("[cancelNotification] isNotificationActive: "), this.mIsNotificationActive, "WirelessChargingNotification");
        if (this.mIsNotificationActive) {
            this.mNotificationManager.cancelAsUser("wireless_charging", 2131954371, UserHandle.ALL);
            this.mIsNotificationActive = false;
            Utils.updateWirelessChargingWarningEnabled(this.mContext, false);
        }
    }
}
