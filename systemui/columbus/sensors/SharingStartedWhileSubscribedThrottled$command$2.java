package com.google.android.systemui.columbus.sensors;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.SharingCommand;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class SharingStartedWhileSubscribedThrottled$command$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARNING: type inference failed for: r1v1, types: [kotlin.coroutines.Continuation, kotlin.coroutines.jvm.internal.SuspendLambda, com.google.android.systemui.columbus.sensors.SharingStartedWhileSubscribedThrottled$command$2] */
    public final Continuation create(Object obj, Continuation continuation) {
        ? suspendLambda = new SuspendLambda(2, continuation);
        suspendLambda.L$0 = obj;
        return suspendLambda;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SharingStartedWhileSubscribedThrottled$command$2) create((SharingCommand) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (((SharingCommand) this.L$0) != SharingCommand.START) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
