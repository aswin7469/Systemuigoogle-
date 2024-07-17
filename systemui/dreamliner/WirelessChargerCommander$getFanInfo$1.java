package com.google.android.systemui.dreamliner;

import android.os.ResultReceiver;
import com.google.android.systemui.dreamliner.WirelessCharger;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$getFanInfo$1 extends Lambda implements Function1 {
    final /* synthetic */ byte $fanId;
    final /* synthetic */ ResultReceiver $resultReceiver;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$getFanInfo$1(byte b, ResultReceiver resultReceiver) {
        super(1);
        this.$fanId = b;
        this.$resultReceiver = resultReceiver;
    }

    public final Object invoke(Object obj) {
        final byte b = this.$fanId;
        final ResultReceiver resultReceiver = this.$resultReceiver;
        ((WirelessCharger) obj).getFanInformation(b, new WirelessCharger.GetFanInformationCallback() {
        });
        return Unit.INSTANCE;
    }
}
