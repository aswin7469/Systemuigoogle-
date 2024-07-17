package com.google.android.systemui.power.batteryhealth;

import android.content.SharedPreferences;
import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class HealthManager$getHealthDataFromPrefs$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ SharedPreferences $healthPrefs;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthManager$getHealthDataFromPrefs$2(SharedPreferences sharedPreferences, Continuation continuation) {
        super(2, continuation);
        this.$healthPrefs = sharedPreferences;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthManager$getHealthDataFromPrefs$2(this.$healthPrefs, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((HealthManager$getHealthDataFromPrefs$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SharedPreferences sharedPreferences = this.$healthPrefs;
            HealthData healthData = new HealthData(sharedPreferences.getInt("health_index", -1), sharedPreferences.getInt("perf_index", -1), sharedPreferences.getInt("capacity_index", -1), sharedPreferences.getInt("health_status", -1));
            Log.i("HealthManager", "Get BHI from prefs: " + healthData);
            return healthData;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
