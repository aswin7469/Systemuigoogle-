package com.google.android.systemui.power;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class BatteryDefenderNotificationHandler$onActionResumeChargingSettings$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $bypassMode;
    int label;
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$onActionResumeChargingSettings$1(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryDefenderNotificationHandler;
        this.$bypassMode = i;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryDefenderNotificationHandler$onActionResumeChargingSettings$1(this.this$0, this.$bypassMode, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryDefenderNotificationHandler$onActionResumeChargingSettings$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = this.this$0;
            int i2 = this.$bypassMode;
            this.label = 1;
            if (BatteryDefenderNotificationHandler.access$bypassDefenderMode(batteryDefenderNotificationHandler, i2, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
