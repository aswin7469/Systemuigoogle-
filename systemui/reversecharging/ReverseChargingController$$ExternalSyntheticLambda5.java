package com.google.android.systemui.reversecharging;

import java.util.ArrayList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ReverseChargingController$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ ReverseChargingController f$0;

    public /* synthetic */ ReverseChargingController$$ExternalSyntheticLambda5(ReverseChargingController reverseChargingController) {
        this.f$0 = reverseChargingController;
    }

    public final void run() {
        ReverseChargingController reverseChargingController = this.f$0;
        reverseChargingController.getClass();
        new ArrayList(reverseChargingController.mChangeCallbacks).forEach(new ReverseChargingController$$ExternalSyntheticLambda6(reverseChargingController));
    }
}
