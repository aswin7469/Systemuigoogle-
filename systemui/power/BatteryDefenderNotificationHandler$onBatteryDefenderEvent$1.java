package com.google.android.systemui.power;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class BatteryDefenderNotificationHandler$onBatteryDefenderEvent$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$onBatteryDefenderEvent$1(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryDefenderNotificationHandler;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryDefenderNotificationHandler$onBatteryDefenderEvent$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryDefenderNotificationHandler$onBatteryDefenderEvent$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = this.this$0;
            this.label = 1;
            int i2 = BatteryDefenderNotificationHandler.$r8$clinit;
            batteryDefenderNotificationHandler.getClass();
            Object withContext = BuildersKt.withContext(batteryDefenderNotificationHandler.backgroundDispatcher, new BatteryDefenderNotificationHandler$putDefenderStartTimestamp$2(batteryDefenderNotificationHandler, (Continuation) null), this);
            if (withContext != coroutineSingletons) {
                withContext = unit;
            }
            if (withContext == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return unit;
    }
}
