package com.google.android.systemui.power;

import android.content.ComponentName;
import android.content.Intent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class BatteryEventClient$registerBatteryEventCallback$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BatteryEventClient this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryEventClient$registerBatteryEventCallback$1(BatteryEventClient batteryEventClient, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryEventClient;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryEventClient$registerBatteryEventCallback$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((BatteryEventClient$registerBatteryEventCallback$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.android.systemui", "com.google.android.systemui.power.batteryevent.domain.BatteryEventService"));
            BatteryEventClient batteryEventClient = this.this$0;
            batteryEventClient.context.bindService(intent, batteryEventClient.connection, 1);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
