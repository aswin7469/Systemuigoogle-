package com.google.android.systemui.power.batteryhealth;

import android.content.SharedPreferences;
import kotlin.Pair;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HealthManager$getHealthDataFlow$1$listener$1 implements SharedPreferences.OnSharedPreferenceChangeListener {
    public final /* synthetic */ ProducerScope $$this$callbackFlow;

    public HealthManager$getHealthDataFlow$1$listener$1(ProducerScope producerScope) {
        this.$$this$callbackFlow = producerScope;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences != null) {
            ((ProducerCoroutine) this.$$this$callbackFlow).m1780trySendJP2dKIU(new Pair(sharedPreferences, str));
        }
    }
}
