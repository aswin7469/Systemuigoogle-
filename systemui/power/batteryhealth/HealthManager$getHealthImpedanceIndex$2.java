package com.google.android.systemui.power.batteryhealth;

import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobSupport;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class HealthManager$getHealthImpedanceIndex$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ HealthManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthManager$getHealthImpedanceIndex$2(HealthManager healthManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthManager;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthManager$getHealthImpedanceIndex$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((HealthManager$getHealthImpedanceIndex$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Integer num;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Job job = this.this$0.initializer;
            this.label = 1;
            if (((JobSupport) job).join(this) == coroutineSingletons) {
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
                num = new Integer(((IGoogleBattery.Stub.Proxy) iGoogleBattery).getHealthImpedanceIndex());
            } else {
                num = null;
            }
            Log.i("HealthManager", "getImpedanceIdx: " + num);
            return num;
        } catch (Exception e) {
            Log.w("HealthManager", "getImpedanceIdx: " + e);
            return null;
        }
    }
}
