package com.google.android.systemui.columbus.sensors;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class GestureController$gestureEvents$2 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARNING: type inference failed for: r0v1, types: [kotlin.coroutines.jvm.internal.SuspendLambda, com.google.android.systemui.columbus.sensors.GestureController$gestureEvents$2] */
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj2);
        ? suspendLambda = new SuspendLambda(3, (Continuation) obj3);
        suspendLambda.L$0 = null;
        suspendLambda.L$1 = null;
        return suspendLambda.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.L$1);
            return new Pair((GestureSensor$GestureDetectionEvent) this.L$0, (Object) null);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
