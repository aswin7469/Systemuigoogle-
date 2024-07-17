package com.google.android.systemui.dreamliner;

import android.os.ResultReceiver;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$doKeyExchange$1 extends Lambda implements Function1 {
    final /* synthetic */ byte[] $publicKey;
    final /* synthetic */ ResultReceiver $resultReceiver;
    final /* synthetic */ WirelessChargerCommander this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$doKeyExchange$1(byte[] bArr, ResultReceiver resultReceiver, WirelessChargerCommander wirelessChargerCommander) {
        super(1);
        this.$publicKey = bArr;
        this.$resultReceiver = resultReceiver;
        this.this$0 = wirelessChargerCommander;
    }

    public final Object invoke(Object obj) {
        ((WirelessCharger) obj).keyExchange(this.$publicKey, new Object(this.$resultReceiver, this.this$0) {
        });
        return Unit.INSTANCE;
    }
}
