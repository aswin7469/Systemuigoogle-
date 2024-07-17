package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class VrMode$vrStateCallbacks$1$onVrStateChanged$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $enabled;
    int label;
    final /* synthetic */ VrMode this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VrMode$vrStateCallbacks$1$onVrStateChanged$1(VrMode vrMode, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = vrMode;
        this.$enabled = z;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new VrMode$vrStateCallbacks$1$onVrStateChanged$1(this.this$0, this.$enabled, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((VrMode$vrStateCallbacks$1$onVrStateChanged$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            VrMode vrMode = this.this$0;
            vrMode.inVrMode = this.$enabled;
            BuildersKt.launch$default(vrMode.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new VrMode$updateBlocking$1(vrMode, (Continuation) null), 3);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
