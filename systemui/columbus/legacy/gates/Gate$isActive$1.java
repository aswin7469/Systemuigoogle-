package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class Gate$isActive$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ Gate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Gate$isActive$1(Gate gate, Continuation continuation) {
        super(2, continuation);
        this.this$0 = gate;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new Gate$isActive$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((Gate$isActive$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boolean.valueOf(this.this$0.active);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
