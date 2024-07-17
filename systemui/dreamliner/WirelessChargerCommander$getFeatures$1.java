package com.google.android.systemui.dreamliner;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$getFeatures$1 extends Lambda implements Function1 {
    final /* synthetic */ long $chargerId;
    final /* synthetic */ ResultReceiver $resultReceiver;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$getFeatures$1(long j, ResultReceiver resultReceiver) {
        super(1);
        this.$chargerId = j;
        this.$resultReceiver = resultReceiver;
    }

    public final Object invoke(Object obj) {
        long j;
        int i;
        long j2 = this.$chargerId;
        ResultReceiver resultReceiver = this.$resultReceiver;
        WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) obj);
        if (wirelessChargerImpl.initHALInterface()) {
            try {
                j = ((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).getFeatures(j2);
                i = 0;
            } catch (Exception e) {
                i = WirelessChargerImpl.mapError(e);
                Log.i("Dreamliner-WLC_HAL", "get features fail: " + e.getMessage());
                j = 0;
            }
            if (i != 0) {
                resultReceiver.send(i, (Bundle) null);
            } else {
                Log.d("WirelessChargerCommander", "GF() response: f=" + j);
                Bundle bundle = new Bundle();
                bundle.putLong("charger_feature", j);
                resultReceiver.send(0, bundle);
            }
        }
        return Unit.INSTANCE;
    }
}
