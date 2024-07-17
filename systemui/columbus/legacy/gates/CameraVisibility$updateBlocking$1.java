package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class CameraVisibility$updateBlocking$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ CameraVisibility this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CameraVisibility$updateBlocking$1(CameraVisibility cameraVisibility, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cameraVisibility;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new CameraVisibility$updateBlocking$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((CameraVisibility$updateBlocking$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CameraVisibility cameraVisibility = this.this$0;
            if (cameraVisibility.exceptionActive || !cameraVisibility.cameraShowing) {
                z = false;
            } else {
                z = true;
            }
            cameraVisibility.setBlocking(z);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
