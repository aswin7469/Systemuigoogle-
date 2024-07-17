package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class TransientGate$blockForMillis$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ long $blockDuration;
    int label;
    final /* synthetic */ TransientGate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TransientGate$blockForMillis$1(TransientGate transientGate, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = transientGate;
        this.$blockDuration = j;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new TransientGate$blockForMillis$1(this.this$0, this.$blockDuration, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((TransientGate$blockForMillis$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.setBlocking(true);
            long j = this.$blockDuration;
            this.label = 1;
            if (DelayKt.delay(j, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.setBlocking(false);
        this.this$0.currentJob = null;
        return Unit.INSTANCE;
    }
}
