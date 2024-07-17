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
final class HealthManager$bootCompletedReceiver$1$onReceive$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ HealthManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthManager$bootCompletedReceiver$1$onReceive$1(HealthManager healthManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthManager;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthManager$bootCompletedReceiver$1$onReceive$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((HealthManager$bootCompletedReceiver$1$onReceive$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Object obj3 = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            HealthManager healthManager = this.this$0;
            this.label = 1;
            healthManager.getClass();
            Object withContext = BuildersKt.withContext(healthManager.bgDispatcher, new HealthManager$runOnBackground$2((Continuation) null, new HealthManager$updateHealthDataPeriodically$2(healthManager, (Continuation) null)), this);
            if (withContext != obj2) {
                withContext = obj3;
            }
            if (withContext != obj2) {
                withContext = obj3;
            }
            if (withContext == obj2) {
                return obj2;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj3;
    }
}
