package com.google.android.systemui.power.batteryhealth;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class HealthManager$getOnBackground$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function2 $block;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthManager$getOnBackground$2(Continuation continuation, Function2 function2) {
        super(2, continuation);
        this.$block = function2;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        HealthManager$getOnBackground$2 healthManager$getOnBackground$2 = new HealthManager$getOnBackground$2(continuation, this.$block);
        healthManager$getOnBackground$2.L$0 = obj;
        return healthManager$getOnBackground$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((HealthManager$getOnBackground$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function2 function2 = this.$block;
            this.label = 1;
            obj = function2.invoke((CoroutineScope) this.L$0, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
