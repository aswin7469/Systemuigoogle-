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

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
