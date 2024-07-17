package com.google.android.systemui.columbus.legacy;

import android.content.Context;
import android.os.PowerManager;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class PowerManagerWrapper {
    public final PowerManager powerManager;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
