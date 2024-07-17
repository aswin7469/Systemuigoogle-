package com.google.android.systemui.power.batteryhealth;

import android.content.SharedPreferences;
import kotlin.Pair;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class HealthManager$getHealthDataFlow$1$listener$1 implements SharedPreferences.OnSharedPreferenceChangeListener {
    public final /* synthetic */ ProducerScope $$this$callbackFlow;

    public HealthManager$getHealthDataFlow$1$listener$1(ProducerScope producerScope) {
        this.$$this$callbackFlow = producerScope;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences != null) {
            ((ProducerCoroutine) this.$$this$callbackFlow).m1738trySendJP2dKIU(new Pair(sharedPreferences, str));
        }
    }
}
