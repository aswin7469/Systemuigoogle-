package com.google.android.systemui.power.batteryevent.repository;

import android.os.IBinder;
import android.util.Log;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HalDataSource$deathRecipient$1 implements IBinder.DeathRecipient {
    public static final HalDataSource$deathRecipient$1 INSTANCE = new Object();

    public final void binderDied() {
        Log.e("GoogleBatteryDataSource", "Service died!!");
    }
}
