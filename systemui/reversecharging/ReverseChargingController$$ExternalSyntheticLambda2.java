package com.google.android.systemui.reversecharging;

import android.util.Log;
import com.android.systemui.BootCompleteCache$BootCompleteListener;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ReverseChargingController$$ExternalSyntheticLambda2 implements BootCompleteCache$BootCompleteListener {
    public final /* synthetic */ ReverseChargingController f$0;

    public /* synthetic */ ReverseChargingController$$ExternalSyntheticLambda2(ReverseChargingController reverseChargingController) {
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
