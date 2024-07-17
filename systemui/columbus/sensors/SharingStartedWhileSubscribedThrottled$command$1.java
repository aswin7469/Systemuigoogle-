package com.google.android.systemui.columbus.sensors;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.SharingCommand;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class SharingStartedWhileSubscribedThrottled$command$1 extends SuspendLambda implements Function2 {
    /* synthetic */ int I$0;
    int label;

    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.systemui.columbus.sensors.SharingStartedWhileSubscribedThrottled$command$1, kotlin.coroutines.Continuation, kotlin.coroutines.jvm.internal.SuspendLambda] */
    public final Continuation create(Object obj, Continuation continuation) {
        ? suspendLambda = new SuspendLambda(2, continuation);
        suspendLambda.I$0 = ((Number) obj).intValue();
        return suspendLambda;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SharingStartedWhileSubscribedThrottled$command$1) create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.I$0 > 0) {
                return SharingCommand.START;
            }
            return SharingCommand.STOP_AND_RESET_REPLAY_CACHE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
