package com.google.android.systemui.power.batteryhealth;

import android.os.IBinder;
import android.util.Log;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HealthManager$deathRecipient$1 implements IBinder.DeathRecipient {
    public static final HealthManager$deathRecipient$1 INSTANCE = new Object();

    public final void binderDied() {
        Log.w("HealthManager", "HW binder died");
    }
}
