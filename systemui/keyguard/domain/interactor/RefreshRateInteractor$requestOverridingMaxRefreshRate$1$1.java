package com.google.android.systemui.keyguard.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class RefreshRateInteractor$requestOverridingMaxRefreshRate$1$1 extends SuspendLambda implements Function3 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    /* JADX WARNING: type inference failed for: r3v2, types: [com.google.android.systemui.keyguard.domain.interactor.RefreshRateInteractor$requestOverridingMaxRefreshRate$1$1, kotlin.coroutines.jvm.internal.SuspendLambda] */
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        ? suspendLambda = new SuspendLambda(3, (Continuation) obj3);
        suspendLambda.Z$0 = booleanValue;
        suspendLambda.Z$1 = booleanValue2;
        return suspendLambda.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z2 = this.Z$0;
            boolean z3 = this.Z$1;
            if (z2 || z3) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
