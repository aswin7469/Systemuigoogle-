package com.google.android.systemui.reversecharging;

import com.google.android.systemui.reversecharging.ReverseChargingController;
import com.google.android.systemui.statusbar.policy.BatteryControllerImplGoogle;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ReverseChargingController$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ ReverseChargingController f$0;

    public /* synthetic */ ReverseChargingController$$ExternalSyntheticLambda6(ReverseChargingController reverseChargingController) {
        this.f$0 = reverseChargingController;
    }

    public final void accept(Object obj) {
        ReverseChargingController reverseChargingController = this.f$0;
        BatteryControllerImplGoogle batteryControllerImplGoogle = (BatteryControllerImplGoogle) ((ReverseChargingController.ReverseChargingChangeCallback) obj);
        batteryControllerImplGoogle.onReverseChargingChanged(reverseChargingController.mRtxLevel, reverseChargingController.mName, reverseChargingController.mReverse);
    }
}
