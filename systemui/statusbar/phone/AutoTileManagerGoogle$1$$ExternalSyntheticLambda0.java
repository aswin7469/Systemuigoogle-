package com.google.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.google.android.systemui.statusbar.phone.AutoTileManagerGoogle;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class AutoTileManagerGoogle$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AutoTileManagerGoogle.AnonymousClass1 f$0;

    public /* synthetic */ AutoTileManagerGoogle$1$$ExternalSyntheticLambda0(AutoTileManagerGoogle.AnonymousClass1 r1) {
        this.f$0 = r1;
    }

    public final void run() {
        AutoTileManagerGoogle autoTileManagerGoogle = AutoTileManagerGoogle.this;
        ((BatteryControllerImpl) autoTileManagerGoogle.mBatteryController).removeCallback(autoTileManagerGoogle.mBatteryControllerCallback);
    }
}
