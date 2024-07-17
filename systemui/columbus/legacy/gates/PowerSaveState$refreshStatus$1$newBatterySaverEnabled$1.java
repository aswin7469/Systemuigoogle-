package com.google.android.systemui.columbus.legacy.gates;

import android.os.PowerManager;
import android.os.PowerSaveState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class PowerSaveState$refreshStatus$1$newBatterySaverEnabled$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ PowerSaveState this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PowerSaveState$refreshStatus$1$newBatterySaverEnabled$1(PowerSaveState powerSaveState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = powerSaveState;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new PowerSaveState$refreshStatus$1$newBatterySaverEnabled$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PowerSaveState$refreshStatus$1$newBatterySaverEnabled$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PowerSaveState powerSaveState = ((PowerManager) this.this$0.powerManager.get()).getPowerSaveState(13);
            boolean z = false;
            if (powerSaveState != null && powerSaveState.batterySaverEnabled) {
                z = true;
            }
            return Boolean.valueOf(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
