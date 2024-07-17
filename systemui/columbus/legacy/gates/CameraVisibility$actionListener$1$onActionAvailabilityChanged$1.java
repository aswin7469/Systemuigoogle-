package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.actions.Action;
import java.util.Iterator;
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

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class CameraVisibility$actionListener$1$onActionAvailabilityChanged$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ CameraVisibility this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CameraVisibility$actionListener$1$onActionAvailabilityChanged$1(CameraVisibility cameraVisibility, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cameraVisibility;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new CameraVisibility$actionListener$1$onActionAvailabilityChanged$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((CameraVisibility$actionListener$1$onActionAvailabilityChanged$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CameraVisibility cameraVisibility = this.this$0;
            Iterator it = cameraVisibility.exceptions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj2 = null;
                    break;
                }
                obj2 = it.next();
                if (((Action) obj2).isAvailable) {
                    break;
                }
            }
            if (obj2 != null) {
                z = true;
            } else {
                z = false;
            }
            cameraVisibility.exceptionActive = z;
            CameraVisibility cameraVisibility2 = this.this$0;
            BuildersKt.launch$default(cameraVisibility2.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraVisibility$updateBlocking$1(cameraVisibility2, (Continuation) null), 3);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
