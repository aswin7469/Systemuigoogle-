package com.google.android.systemui.reversecharging;

import java.util.ArrayList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ReverseChargingController$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ ReverseChargingController f$0;

    public /* synthetic */ ReverseChargingController$$ExternalSyntheticLambda10(ReverseChargingController reverseChargingController) {
        this.f$0 = reverseChargingController;
    }

    public final void run() {
        ReverseChargingController reverseChargingController = this.f$0;
        reverseChargingController.getClass();
        new ArrayList(reverseChargingController.mChangeCallbacks).forEach(new ReverseChargingController$$ExternalSyntheticLambda11(reverseChargingController));
    }
}
