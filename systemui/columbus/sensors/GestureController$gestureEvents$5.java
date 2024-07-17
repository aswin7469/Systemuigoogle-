package com.google.android.systemui.columbus.sensors;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import com.android.internal.logging.UiEventLogger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class GestureController$gestureEvents$5 extends SuspendLambda implements Function2 {
    final /* synthetic */ UiEventLogger $uiEventLogger;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GestureController$gestureEvents$5(UiEventLogger uiEventLogger, Continuation continuation) {
        super(2, continuation);
        this.$uiEventLogger = uiEventLogger;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        GestureController$gestureEvents$5 gestureController$gestureEvents$5 = new GestureController$gestureEvents$5(this.$uiEventLogger, continuation);
        gestureController$gestureEvents$5.L$0 = obj;
        return gestureController$gestureEvents$5;
    }

    public final Object invoke(Object obj, Object obj2) {
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
        Unit unit = Unit.INSTANCE;
        ((GestureController$gestureEvents$5) create((Object) null, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.L$0);
            throw null;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
