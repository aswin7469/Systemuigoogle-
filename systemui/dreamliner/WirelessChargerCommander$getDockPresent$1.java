package com.google.android.systemui.dreamliner;

import android.util.Log;
import com.google.android.systemui.dreamliner.WirelessCharger;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class WirelessChargerCommander$getDockPresent$1 extends Lambda implements Function1 {
    final /* synthetic */ WirelessCharger.IsDockPresentCallback $callback;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WirelessChargerCommander$getDockPresent$1(WirelessCharger.IsDockPresentCallback isDockPresentCallback) {
        super(1);
        this.$callback = isDockPresentCallback;
    }

    public final Object invoke(Object obj) {
        ((WirelessCharger) obj).asyncIsDockPresent(new Object(this.$callback) {
            public void onCallback(boolean z, byte b, byte b2, boolean z2, int i) {
                Log.i("WirelessChargerCommander", "IDP() response: d=" + z + ", i=" + i + ", m=0, t=" + b + ", o=" + b2 + ", sgi=" + z2);
                ((WirelessCharger.IsDockPresentCallback) r4).onCallback(z, b, b2, z2, i);
            }
        });
        return Unit.INSTANCE;
    }
}
