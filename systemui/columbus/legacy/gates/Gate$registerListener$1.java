package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.gates.Gate;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class Gate$registerListener$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Gate.Listener $listener;
    int label;
    final /* synthetic */ Gate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Gate$registerListener$1(Gate.Listener listener, Gate gate, Continuation continuation) {
        super(2, continuation);
        this.this$0 = gate;
        this.$listener = listener;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new Gate$registerListener$1(this.$listener, this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((Gate$registerListener$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.listeners.add(this.$listener);
            Gate gate = this.this$0;
            if (!gate.active && (!gate.listeners.isEmpty())) {
                Gate gate2 = this.this$0;
                gate2.active = true;
                gate2.onActivate();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
