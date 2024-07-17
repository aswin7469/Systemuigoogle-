package com.google.android.systemui.power.batteryhealth;

import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import vendor.google.google_battery.BatteryHealthStats;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class HealthManager$getHealthData$4 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $healthAlgo;
    int label;
    final /* synthetic */ HealthManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthManager$getHealthData$4(HealthManager healthManager, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthManager;
        this.$healthAlgo = i;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthManager$getHealthData$4(this.this$0, this.$healthAlgo, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((HealthManager$getHealthData$4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        BatteryHealthStats batteryHealthStats;
        int i;
        int i2;
        int i3;
        int i4;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i5 = this.label;
        if (i5 == 0) {
            ResultKt.throwOnFailure(obj);
            StandaloneCoroutine standaloneCoroutine = this.this$0.initializer;
            this.label = 1;
            if (standaloneCoroutine.join(this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i5 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            IGoogleBattery iGoogleBattery = this.this$0.googleBattery;
            if (iGoogleBattery != null) {
                batteryHealthStats = ((IGoogleBattery.Stub.Proxy) iGoogleBattery).getHealthStats(this.$healthAlgo);
            } else {
                batteryHealthStats = null;
            }
            if (batteryHealthStats != null) {
                i = batteryHealthStats.healthIndex;
            } else {
                i = -1;
            }
            if (batteryHealthStats != null) {
                i2 = batteryHealthStats.healthImpedanceIndex;
            } else {
                i2 = -1;
            }
            if (batteryHealthStats != null) {
                i3 = batteryHealthStats.healthCapacityIndex;
            } else {
                i3 = -1;
            }
            if (batteryHealthStats != null) {
                i4 = batteryHealthStats.healthStatus;
            } else {
                i4 = -1;
            }
            HealthData healthData = new HealthData(i, i2, i3, i4);
            Log.i("HealthManager", "getHealthData: " + healthData + ", algo: " + this.$healthAlgo);
            return healthData;
        } catch (Exception e) {
            Log.w("HealthManager", "getHealthData: " + e + ", algo: " + this.$healthAlgo);
            return new HealthData(-1, -1, -1, -1);
        }
    }
}
