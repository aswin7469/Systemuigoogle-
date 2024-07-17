package com.google.android.systemui.dreamliner;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$getFanLevel$1 extends Lambda implements Function1 {
    final /* synthetic */ ResultReceiver $resultReceiver;
    final /* synthetic */ WirelessChargerCommander this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$getFanLevel$1(ResultReceiver resultReceiver, WirelessChargerCommander wirelessChargerCommander) {
        super(1);
        this.$resultReceiver = resultReceiver;
        this.this$0 = wirelessChargerCommander;
    }

    public final Object invoke(Object obj) {
        long currentTimeMillis = System.currentTimeMillis();
        WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) obj);
        int i = -1;
        if (wirelessChargerImpl.initHALInterface()) {
            Log.d("Dreamliner-WLC_HAL", "command=2");
            try {
                i = ((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).getFanLevel();
            } catch (Exception e) {
                Log.i("Dreamliner-WLC_HAL", "command=2 fail: " + e.getMessage());
            }
        }
        Log.d("WirelessChargerCommander", "GFL() response: l=" + i + ", spending time=" + (System.currentTimeMillis() - currentTimeMillis));
        ResultReceiver resultReceiver = this.$resultReceiver;
        if (resultReceiver != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("fan_level", i);
            resultReceiver.send(0, bundle);
        } else {
            Iterator it = this.this$0.wirelessChargerFanLevelChangedCallback.iterator();
            while (it.hasNext()) {
                ((DockObserver$$ExternalSyntheticLambda2) it.next()).onFanLevelChanged(i);
            }
        }
        return Unit.INSTANCE;
    }
}
