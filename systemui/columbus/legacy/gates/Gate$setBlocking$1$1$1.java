package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.gates.Gate;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class Gate$setBlocking$1$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Gate.Listener $it;
    int label;
    final /* synthetic */ Gate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Gate$setBlocking$1$1$1(Gate.Listener listener, Gate gate, Continuation continuation) {
        super(2, continuation);
        this.$it = listener;
        this.this$0 = gate;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new Gate$setBlocking$1$1$1(this.$it, this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((Gate$setBlocking$1$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$it.onGateChanged(this.this$0);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
