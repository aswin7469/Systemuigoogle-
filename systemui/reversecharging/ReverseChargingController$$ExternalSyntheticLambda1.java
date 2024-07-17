package com.google.android.systemui.reversecharging;

import android.util.Log;
import com.android.systemui.BootCompleteCache;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ReverseChargingController$$ExternalSyntheticLambda1 implements BootCompleteCache.BootCompleteListener {
    public final /* synthetic */ ReverseChargingController f$0;

    public /* synthetic */ ReverseChargingController$$ExternalSyntheticLambda1(ReverseChargingController reverseChargingController) {
        this.f$0 = reverseChargingController;
    }

    public final void onBootComplete() {
        boolean z = ReverseChargingController.DEBUG;
        ReverseChargingController reverseChargingController = this.f$0;
        if (z) {
            reverseChargingController.getClass();
            Log.d("ReverseChargingControl", "onBootComplete(): ACTION_BOOT_COMPLETED");
        }
        reverseChargingController.mBootCompleted = true;
        reverseChargingController.setRtxTimer(ReverseChargingController.DURATION_WAIT_NFC_SERVICE, 2);
    }
}
