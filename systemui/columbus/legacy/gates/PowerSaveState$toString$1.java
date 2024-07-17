package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class PowerSaveState$toString$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ PowerSaveState this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PowerSaveState$toString$1(PowerSaveState powerSaveState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = powerSaveState;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new PowerSaveState$toString$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PowerSaveState$toString$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PowerSaveState powerSaveState = this.this$0;
            boolean z = powerSaveState.batterySaverEnabled;
            boolean z2 = powerSaveState.isDeviceInteractive;
            return "[batterySaverEnabled -> " + z + "; isDeviceInteractive -> " + z2 + "]";
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
