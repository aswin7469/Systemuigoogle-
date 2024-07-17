package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class TelephonyActivity$updateBlocking$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ TelephonyActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TelephonyActivity$updateBlocking$1(TelephonyActivity telephonyActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = telephonyActivity;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new TelephonyActivity$updateBlocking$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((TelephonyActivity$updateBlocking$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            TelephonyActivity telephonyActivity = this.this$0;
            telephonyActivity.setBlocking(telephonyActivity.isCallBlocked);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
