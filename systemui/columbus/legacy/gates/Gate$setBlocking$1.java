package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.gates.Gate;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class Gate$setBlocking$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $blocking;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Gate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Gate$setBlocking$1(Gate gate, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = gate;
        this.$blocking = z;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        Gate$setBlocking$1 gate$setBlocking$1 = new Gate$setBlocking$1(this.this$0, this.$blocking, continuation);
        gate$setBlocking$1.L$0 = obj;
        return gate$setBlocking$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((Gate$setBlocking$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Gate gate = this.this$0;
            boolean z = gate.isBlocked;
            boolean z2 = this.$blocking;
            if (z != z2) {
                gate.isBlocked = z2;
                if (gate.active) {
                    for (Gate.Listener gate$setBlocking$1$1$1 : gate.listeners) {
                        BuildersKt.launch$default(coroutineScope, gate.mainPostDispatcher, (CoroutineStart) null, new Gate$setBlocking$1$1$1(gate$setBlocking$1$1$1, gate, (Continuation) null), 2);
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
