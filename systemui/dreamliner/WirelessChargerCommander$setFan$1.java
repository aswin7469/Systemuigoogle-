package com.google.android.systemui.dreamliner;

import android.os.ResultReceiver;
import android.util.Log;
import com.google.android.systemui.dreamliner.WirelessCharger;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$setFan$1 extends Lambda implements Function1 {
    final /* synthetic */ byte $fanId;
    final /* synthetic */ byte $fanMode;
    final /* synthetic */ int $fanRpm;
    final /* synthetic */ ResultReceiver $resultReceiver;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$setFan$1(byte b, byte b2, int i, ResultReceiver resultReceiver) {
        super(1);
        this.$fanId = b;
        this.$fanMode = b2;
        this.$fanRpm = i;
        this.$resultReceiver = resultReceiver;
    }

    public final Object invoke(Object obj) {
        byte b = this.$fanId;
        byte b2 = this.$fanMode;
        int i = this.$fanRpm;
        final ResultReceiver resultReceiver = this.$resultReceiver;
        ((WirelessCharger) obj).setFan(b, b2, i, new Object() {
            public void onCallback(boolean z, byte b, byte b2, boolean z2, int i) {
                Log.i("WirelessChargerCommander", "IDP() response: d=" + z + ", i=" + i + ", m=0, t=" + b + ", o=" + b2 + ", sgi=" + z2);
                ((WirelessCharger.IsDockPresentCallback) resultReceiver).onCallback(z, b, b2, z2, i);
            }
        });
        return Unit.INSTANCE;
    }
}
