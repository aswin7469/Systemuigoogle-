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
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class HealthManager$getHealthStatus$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ HealthManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthManager$getHealthStatus$2(HealthManager healthManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthManager;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthManager$getHealthStatus$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((HealthManager$getHealthStatus$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Integer num;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StandaloneCoroutine standaloneCoroutine = this.this$0.initializer;
            this.label = 1;
            if (standaloneCoroutine.join(this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            IGoogleBattery iGoogleBattery = this.this$0.googleBattery;
            if (iGoogleBattery != null) {
                num = new Integer(((IGoogleBattery.Stub.Proxy) iGoogleBattery).getHealthStatus());
            } else {
                num = null;
            }
            Log.i("HealthManager", "getHealthStatus: " + num);
            return num;
        } catch (Exception e) {
            Log.w("HealthManager", "getHealthStatus: " + e);
            return null;
        }
    }
}
