package com.google.android.systemui.power;

import android.os.IBinder;
import android.util.Log;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1 implements IBinder.DeathRecipient {
    public static final BatteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1 INSTANCE = new Object();

    public final void binderDied() {
        Log.e("GoogleBatteryDataSource", "Service died!!");
    }
}
