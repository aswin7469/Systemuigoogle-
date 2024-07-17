package com.google.android.systemui.power.batteryhealth;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class HealthManager$getHealthDataFlow$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HealthManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthManager$getHealthDataFlow$1(HealthManager healthManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthManager;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        HealthManager$getHealthDataFlow$1 healthManager$getHealthDataFlow$1 = new HealthManager$getHealthDataFlow$1(this.this$0, continuation);
        healthManager$getHealthDataFlow$1.L$0 = obj;
        return healthManager$getHealthDataFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((HealthManager$getHealthDataFlow$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final HealthManager$getHealthDataFlow$1$listener$1 healthManager$getHealthDataFlow$1$listener$1 = new HealthManager$getHealthDataFlow$1$listener$1(producerScope);
            this.this$0.context.getApplicationContext().getSharedPreferences("health_prefs", 0).registerOnSharedPreferenceChangeListener(healthManager$getHealthDataFlow$1$listener$1);
            final HealthManager healthManager = this.this$0;
            AnonymousClass1 r3 = new Function0() {
                public final Object invoke() {
                    HealthManager.this.context.getApplicationContext().getSharedPreferences("health_prefs", 0).unregisterOnSharedPreferenceChangeListener(healthManager$getHealthDataFlow$1$listener$1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, r3, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
