package com.google.android.systemui.columbus.legacy.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
