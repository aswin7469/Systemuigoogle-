package com.google.android.systemui.dreamliner;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$getWpcCertificate$1 extends Lambda implements Function1 {
    final /* synthetic */ short $length;
    final /* synthetic */ short $offset;
    final /* synthetic */ ResultReceiver $resultReceiver;
    final /* synthetic */ byte $slot;
    final /* synthetic */ WirelessChargerCommander this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$getWpcCertificate$1(byte b, short s, short s2, ResultReceiver resultReceiver, WirelessChargerCommander wirelessChargerCommander) {
        super(1);
        this.$slot = b;
        this.$offset = s;
        this.$length = s2;
        this.$resultReceiver = resultReceiver;
        this.this$0 = wirelessChargerCommander;
    }

    public final Object invoke(Object obj) {
        ArrayList arrayList;
        int i;
        byte b = this.$slot;
        short s = this.$offset;
        short s2 = this.$length;
        ResultReceiver resultReceiver = this.$resultReceiver;
        WirelessChargerCommander wirelessChargerCommander = this.this$0;
        WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) obj);
        if (wirelessChargerImpl.initHALInterface()) {
            Bundle bundle = null;
            try {
                arrayList = WirelessChargerImpl.convertPrimitiveArrayToArrayList(((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).getWpcAuthCertificate(b, (char) s, (char) s2));
                i = 0;
            } catch (Exception e) {
                i = WirelessChargerImpl.mapError(e);
                Log.i("Dreamliner-WLC_HAL", "get wpc cert fail: " + e.getMessage());
                arrayList = null;
            }
            ExifInterface$$ExternalSyntheticOutline0.m("GWAC() result: ", "WirelessChargerCommander", i);
            if (i != 0 || arrayList == null) {
                resultReceiver.send(i, (Bundle) null);
            } else {
                Log.d("WirelessChargerCommander", "GWAC() response: c=" + arrayList);
                wirelessChargerCommander.getClass();
                if (!arrayList.isEmpty()) {
                    bundle = new Bundle();
                    bundle.putByteArray("wpc_cert", CollectionsKt.toByteArray(arrayList));
                }
                resultReceiver.send(0, bundle);
            }
        }
        return Unit.INSTANCE;
    }
}
