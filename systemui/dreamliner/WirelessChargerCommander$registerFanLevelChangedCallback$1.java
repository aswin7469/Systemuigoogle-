package com.google.android.systemui.dreamliner;

import android.util.Log;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$registerFanLevelChangedCallback$1 extends Lambda implements Function1 {
    final /* synthetic */ WirelessChargerCommander this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$registerFanLevelChangedCallback$1(WirelessChargerCommander wirelessChargerCommander) {
        super(1);
        this.this$0 = wirelessChargerCommander;
    }

    public final Object invoke(Object obj) {
        WirelessCharger wirelessCharger = (WirelessCharger) obj;
        if (this.this$0.isFanLevelCallbackRegistered.compareAndSet(false, true)) {
            WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) wirelessCharger;
            wirelessChargerImpl.mFanLevelEventListeners.add(this.this$0.fanLevelEventListener);
            if (wirelessChargerImpl.mIsFanLevelCallbackRegistered.compareAndSet(false, true) && wirelessChargerImpl.initHALInterface()) {
                try {
                    ((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).registerFanLevelChangedCallback(wirelessChargerImpl.mIWirelessChargerFanLevelChangedCallback);
                } catch (Exception e) {
                    Log.i("Dreamliner-WLC_HAL", "register fan level changed callback fail: " + e.getMessage());
                }
            }
        }
        return Unit.INSTANCE;
    }
}
