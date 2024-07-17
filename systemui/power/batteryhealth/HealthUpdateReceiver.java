package com.google.android.systemui.power.batteryhealth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HealthUpdateReceiver extends BroadcastReceiver {
    public final HealthManager healthManager;

    public HealthUpdateReceiver(HealthManager healthManager2) {
        this.healthManager = healthManager2;
    }

    public final void onReceive(Context context, Intent intent) {
        Log.i("HealthUpdateReceiver", "Start new BHI update");
        BuildersKt.launch$default(CoroutineScopeKt.MainScope(), (CoroutineContext) null, (CoroutineStart) null, new HealthUpdateReceiver$onReceive$1(this, (Continuation) null), 3);
    }
}
