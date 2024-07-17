package com.google.android.systemui.power.batteryhealth;

import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.CancellableFlow;
import kotlinx.coroutines.flow.CancellableFlowImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class HealthService$registeredListenerNum$2$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ HealthService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthService$registeredListenerNum$2$1(HealthService healthService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthService;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthService$registeredListenerNum$2$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((HealthService$registeredListenerNum$2$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            HealthService healthService = this.this$0;
            this.label = 1;
            KProperty[] kPropertyArr = HealthService.$$delegatedProperties;
            healthService.getClass();
            Log.d("HealthService", "Subscribe listeners");
            HealthManager healthManager = healthService.healthManager;
            healthManager.getClass();
            Flow flowOn = FlowKt.flowOn(FlowKt.callbackFlow(new HealthManager$getHealthDataFlow$1(healthManager, (Continuation) null)), healthManager.bgDispatcher);
            if (!(flowOn instanceof CancellableFlow)) {
                flowOn = new CancellableFlowImpl(flowOn);
            }
            Object collect = flowOn.collect(new HealthService$subscribeListeners$2(healthService), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return unit;
    }
}
