package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class KeyguardProximity$updateBlocking$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyguardProximity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KeyguardProximity$updateBlocking$1(KeyguardProximity keyguardProximity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardProximity;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardProximity$updateBlocking$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((KeyguardProximity$updateBlocking$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            KeyguardProximity keyguardProximity = this.this$0;
            if (!keyguardProximity.isListening || !keyguardProximity.proximity.isBlocking()) {
                z = false;
            } else {
                z = true;
            }
            keyguardProximity.setBlocking(z);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
