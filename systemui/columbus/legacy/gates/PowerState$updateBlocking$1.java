package com.google.android.systemui.columbus.legacy.gates;

import android.os.PowerManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class PowerState$updateBlocking$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ PowerState this$0;

    /* renamed from: com.google.android.systemui.columbus.legacy.gates.PowerState$updateBlocking$1$1  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boolean.valueOf(!((PowerManager) PowerState.this.powerManager.get()).isInteractive());
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PowerState$updateBlocking$1(PowerState powerState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = powerState;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new PowerState$updateBlocking$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PowerState$updateBlocking$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        PowerState powerState;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final PowerState powerState2 = this.this$0;
            CoroutineDispatcher coroutineDispatcher = powerState2.bgDispatcher;
            AnonymousClass1 r3 = new AnonymousClass1((Continuation) null);
            this.L$0 = powerState2;
            this.label = 1;
            Object withContext = BuildersKt.withContext(coroutineDispatcher, r3, this);
            if (withContext == coroutineSingletons) {
                return coroutineSingletons;
            }
            PowerState powerState3 = powerState2;
            obj = withContext;
            powerState = powerState3;
        } else if (i == 1) {
            powerState = (PowerState) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        powerState.setBlocking(((Boolean) obj).booleanValue());
        return Unit.INSTANCE;
    }
}
