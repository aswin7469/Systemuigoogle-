package com.google.android.systemui.dreamliner;

import android.util.Log;
import com.google.android.systemui.dreamliner.WirelessCharger;
import vendor.google.wireless_charger.DockPresent;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class WirelessChargerImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ WirelessChargerImpl f$0;

    public /* synthetic */ WirelessChargerImpl$$ExternalSyntheticLambda0(WirelessChargerImpl wirelessChargerImpl) {
        this.f$0 = wirelessChargerImpl;
    }

    public final void run() {
        WirelessChargerImpl wirelessChargerImpl = this.f$0;
        int i = WirelessChargerImpl.$r8$clinit;
        if (wirelessChargerImpl.initHALInterface()) {
            try {
                DockPresent isDockPresent = ((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).isDockPresent();
                if (System.nanoTime() >= wirelessChargerImpl.mPollingStartedTimeNs + WirelessChargerImpl.MAX_POLLING_TIMEOUT_NS || isDockPresent.id != 0) {
                    WirelessCharger.IsDockPresentCallback isDockPresentCallback = wirelessChargerImpl.mCallback;
                    if (isDockPresentCallback != null) {
                        isDockPresentCallback.onCallback(isDockPresent.docked, isDockPresent.type, isDockPresent.orientation, isDockPresent.isGetInfoSupported, isDockPresent.id);
                        wirelessChargerImpl.mCallback = null;
                        return;
                    }
                    return;
                }
                wirelessChargerImpl.mHandler.postDelayed(wirelessChargerImpl.mRunnable, 100);
            } catch (Exception e) {
                Log.i("Dreamliner-WLC_HAL", "isDockPresent fail: " + e.getMessage());
            }
        }
    }
}
