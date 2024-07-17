package com.google.android.systemui.columbus.legacy.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class PowerSaveState$receiver$1 extends BroadcastReceiver {
    public final /* synthetic */ PowerSaveState this$0;

    public PowerSaveState$receiver$1(PowerSaveState powerSaveState) {
        this.this$0 = powerSaveState;
    }

    public final void onReceive(Context context, Intent intent) {
        PowerSaveState powerSaveState = this.this$0;
        powerSaveState.getClass();
        BuildersKt.launch$default(powerSaveState.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new PowerSaveState$refreshStatus$1(powerSaveState, (Continuation) null), 3);
    }
}
