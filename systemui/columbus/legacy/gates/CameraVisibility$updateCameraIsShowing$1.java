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
final class CameraVisibility$updateCameraIsShowing$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ CameraVisibility this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CameraVisibility$updateCameraIsShowing$1(CameraVisibility cameraVisibility, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cameraVisibility;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new CameraVisibility$updateCameraIsShowing$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CameraVisibility$updateCameraIsShowing$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CameraVisibility cameraVisibility;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CameraVisibility cameraVisibility2 = this.this$0;
            this.L$0 = cameraVisibility2;
            this.label = 1;
            Object access$isCameraShowing = CameraVisibility.access$isCameraShowing(cameraVisibility2, this);
            if (access$isCameraShowing == coroutineSingletons) {
                return coroutineSingletons;
            }
            cameraVisibility = cameraVisibility2;
            obj = access$isCameraShowing;
        } else if (i == 1) {
            cameraVisibility = (CameraVisibility) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        cameraVisibility.cameraShowing = ((Boolean) obj).booleanValue();
        CameraVisibility cameraVisibility3 = this.this$0;
        BuildersKt.launch$default(cameraVisibility3.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraVisibility$updateBlocking$1(cameraVisibility3, (Continuation) null), 3);
        return Unit.INSTANCE;
    }
}
