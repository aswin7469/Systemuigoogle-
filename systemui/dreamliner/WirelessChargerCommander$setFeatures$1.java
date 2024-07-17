package com.google.android.systemui.dreamliner;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$setFeatures$1 extends Lambda implements Function1 {
    final /* synthetic */ long $chargerId;
    final /* synthetic */ long $feature;
    final /* synthetic */ ResultReceiver $resultReceiver;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$setFeatures$1(long j, long j2, ResultReceiver resultReceiver) {
        super(1);
        this.$chargerId = j;
        this.$feature = j2;
        this.$resultReceiver = resultReceiver;
    }

    public final Object invoke(Object obj) {
        int i;
        long j = this.$chargerId;
        long j2 = this.$feature;
        ResultReceiver resultReceiver = this.$resultReceiver;
        WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) obj);
        if (wirelessChargerImpl.initHALInterface()) {
            try {
                ((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).setFeatures(j, j2);
                i = 0;
            } catch (Exception e) {
                int mapError = WirelessChargerImpl.mapError(e);
                Log.i("Dreamliner-WLC_HAL", "set features fail: " + e.getMessage());
                i = mapError;
            }
            resultReceiver.send(i, (Bundle) null);
        }
        return Unit.INSTANCE;
    }
}
