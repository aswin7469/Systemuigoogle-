package com.google.android.systemui.power.batteryhealth;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class HealthUpdateReceiver$onReceive$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ HealthUpdateReceiver this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthUpdateReceiver$onReceive$1(HealthUpdateReceiver healthUpdateReceiver, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthUpdateReceiver;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthUpdateReceiver$onReceive$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((HealthUpdateReceiver$onReceive$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            HealthManager healthManager = this.this$0.healthManager;
            this.label = 1;
            healthManager.getClass();
            if (BuildersKt.withContext(healthManager.bgDispatcher, new HealthManager$getOnBackground$2((Continuation) null, new HealthManager$getAndUpdateHealthData$2(healthManager, (Continuation) null)), this) == coroutineSingletons) {
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
