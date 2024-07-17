package com.google.android.systemui.columbus.legacy.gates;

import android.service.vr.IVrManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class VrMode$onActivate$1$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ IVrManager $it;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VrMode$onActivate$1$1$1(IVrManager iVrManager, Continuation continuation) {
        super(2, continuation);
        this.$it = iVrManager;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new VrMode$onActivate$1$1$1(this.$it, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((VrMode$onActivate$1$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boolean.valueOf(this.$it.getVrModeState());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
