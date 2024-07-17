package com.google.android.systemui.columbus.legacy.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
