package com.google.android.systemui.dreamliner;

import android.os.ResultReceiver;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$doChallenge$1 extends Lambda implements Function1 {
    final /* synthetic */ byte[] $challengeData;
    final /* synthetic */ byte $dockId;
    final /* synthetic */ ResultReceiver $resultReceiver;
    final /* synthetic */ WirelessChargerCommander this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$doChallenge$1(byte b, byte[] bArr, ResultReceiver resultReceiver, WirelessChargerCommander wirelessChargerCommander) {
        super(1);
        this.$dockId = b;
        this.$challengeData = bArr;
        this.$resultReceiver = resultReceiver;
        this.this$0 = wirelessChargerCommander;
    }

    public final Object invoke(Object obj) {
        byte b = this.$dockId;
        byte[] bArr = this.$challengeData;
        final ResultReceiver resultReceiver = this.$resultReceiver;
        final WirelessChargerCommander wirelessChargerCommander = this.this$0;
        ((WirelessCharger) obj).challenge(b, bArr, new Object() {
        });
        return Unit.INSTANCE;
    }
}
