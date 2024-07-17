package com.google.android.systemui.columbus.legacy.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ChargingState$powerReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ ChargingState this$0;

    public ChargingState$powerReceiver$1(ChargingState chargingState) {
        this.this$0 = chargingState;
    }

    public final void onReceive(Context context, Intent intent) {
        ChargingState chargingState = this.this$0;
        chargingState.blockForMillis(chargingState.gateDuration);
    }
}
