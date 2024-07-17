package com.google.android.systemui.columbus.legacy;

import android.content.Context;
import android.os.PowerManager;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class PowerManagerWrapper {
    public final PowerManager powerManager;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class WakeLockWrapper {
        public final PowerManager.WakeLock wakeLock;

        public WakeLockWrapper(PowerManager.WakeLock wakeLock2) {
            this.wakeLock = wakeLock2;
        }
    }

    public PowerManagerWrapper(Context context) {
        this.powerManager = (PowerManager) context.getSystemService("power");
    }
}
