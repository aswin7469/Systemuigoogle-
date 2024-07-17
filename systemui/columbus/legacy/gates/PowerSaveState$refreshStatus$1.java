package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.DeferredCoroutine;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class PowerSaveState$refreshStatus$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ PowerSaveState this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PowerSaveState$refreshStatus$1(PowerSaveState powerSaveState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = powerSaveState;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        PowerSaveState$refreshStatus$1 powerSaveState$refreshStatus$1 = new PowerSaveState$refreshStatus$1(this.this$0, continuation);
        powerSaveState$refreshStatus$1.L$0 = obj;
        return powerSaveState$refreshStatus$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PowerSaveState$refreshStatus$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        PowerSaveState powerSaveState;
        Deferred deferred;
        PowerSaveState powerSaveState2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        boolean z = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            PowerSaveState powerSaveState3 = this.this$0;
            DeferredCoroutine async$default = BuildersKt.async$default(coroutineScope, powerSaveState3.bgDispatcher, new PowerSaveState$refreshStatus$1$newBatterySaverEnabled$1(powerSaveState3, (Continuation) null), 2);
            PowerSaveState powerSaveState4 = this.this$0;
            deferred = BuildersKt.async$default(coroutineScope, powerSaveState4.bgDispatcher, new PowerSaveState$refreshStatus$1$newIsDeviceInteractive$1(powerSaveState4, (Continuation) null), 2);
            PowerSaveState powerSaveState5 = this.this$0;
            this.L$0 = deferred;
            this.L$1 = powerSaveState5;
            this.label = 1;
            Object awaitInternal = async$default.awaitInternal(this);
            if (awaitInternal == coroutineSingletons) {
                return coroutineSingletons;
            }
            Object obj2 = awaitInternal;
            powerSaveState2 = powerSaveState5;
            obj = obj2;
        } else if (i == 1) {
            powerSaveState2 = (PowerSaveState) this.L$1;
            deferred = (Deferred) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            powerSaveState = (PowerSaveState) this.L$0;
            ResultKt.throwOnFailure(obj);
            powerSaveState.isDeviceInteractive = ((Boolean) obj).booleanValue();
            PowerSaveState powerSaveState6 = this.this$0;
            if (!powerSaveState6.batterySaverEnabled || powerSaveState6.isDeviceInteractive) {
                z = false;
            }
            powerSaveState6.setBlocking(z);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        powerSaveState2.batterySaverEnabled = ((Boolean) obj).booleanValue();
        PowerSaveState powerSaveState7 = this.this$0;
        this.L$0 = powerSaveState7;
        this.L$1 = null;
        this.label = 2;
        Object await = deferred.await(this);
        if (await == coroutineSingletons) {
            return coroutineSingletons;
        }
        powerSaveState = powerSaveState7;
        obj = await;
        powerSaveState.isDeviceInteractive = ((Boolean) obj).booleanValue();
        PowerSaveState powerSaveState62 = this.this$0;
        z = false;
        powerSaveState62.setBlocking(z);
        return Unit.INSTANCE;
    }
}
