package com.google.android.systemui.reversecharging;

import com.google.android.systemui.reversecharging.ReverseChargingController;
import com.google.android.systemui.statusbar.policy.BatteryControllerImplGoogle;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ReverseChargingController$$ExternalSyntheticLambda11 implements Consumer {
    public final /* synthetic */ ReverseChargingController f$0;

    public /* synthetic */ ReverseChargingController$$ExternalSyntheticLambda11(ReverseChargingController reverseChargingController) {
        this.f$0 = reverseChargingController;
    }

    public final void accept(Object obj) {
        ReverseChargingController reverseChargingController = this.f$0;
        BatteryControllerImplGoogle batteryControllerImplGoogle = (BatteryControllerImplGoogle) ((ReverseChargingController.ReverseChargingChangeCallback) obj);
        batteryControllerImplGoogle.onReverseChargingChanged(reverseChargingController.mRtxLevel, reverseChargingController.mName, reverseChargingController.mReverse);
    }
}
