package com.google.android.systemui.reversecharging;

import android.nfc.NfcAdapter;
import android.util.Log;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ReverseChargingController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ReverseChargingController f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ ReverseChargingController$$ExternalSyntheticLambda0(ReverseChargingController reverseChargingController, boolean z, int i) {
        this.$r8$classId = i;
        this.f$0 = reverseChargingController;
        this.f$1 = z;
    }

    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ReverseChargingController reverseChargingController = this.f$0;
                boolean z = this.f$1;
                reverseChargingController.getClass();
                Log.i("ReverseChargingControl", "setRtxMode(): rtx=" + (z ? 1 : 0));
                ReverseWirelessCharger reverseWirelessCharger = (ReverseWirelessCharger) reverseChargingController.mRtxChargerManagerOptional.get();
                if (reverseWirelessCharger.initHALInterface()) {
                    try {
                        ((IWirelessCharger.Stub.Proxy) reverseWirelessCharger.mWirelessCharger).setRtxMode(z);
                        return;
                    } catch (Exception e) {
                        Log.w("ReverseWirelessCharger", "setRtxMode fail: ", e);
                        return;
                    }
                } else {
                    return;
                }
            default:
                ReverseChargingController reverseChargingController2 = this.f$0;
                boolean z2 = this.f$1;
                reverseChargingController2.getClass();
                try {
                    NfcAdapter.getDefaultAdapter(reverseChargingController2.mContext).setReaderModePollingEnabled(z2);
                    return;
                } catch (Exception e2) {
                    Log.e("ReverseChargingControl", "Could not change NFC reader mode, exception: " + e2);
                    return;
                }
        }
    }
}
