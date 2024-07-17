package com.google.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.google.android.systemui.statusbar.phone.AutoTileManagerGoogle;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
