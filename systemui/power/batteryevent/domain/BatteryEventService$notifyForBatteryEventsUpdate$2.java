package com.google.android.systemui.power.batteryevent.domain;

import android.util.Log;
import com.google.android.systemui.power.batteryevent.common.BatteryEvents;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class BatteryEventService$notifyForBatteryEventsUpdate$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ BatteryEvents $events;
    int label;
    final /* synthetic */ BatteryEventService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryEventService$notifyForBatteryEventsUpdate$2(BatteryEvents batteryEvents, BatteryEventService batteryEventService, Continuation continuation) {
        super(2, continuation);
        this.$events = batteryEvents;
        this.this$0 = batteryEventService;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryEventService$notifyForBatteryEventsUpdate$2(this.$events, this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryEventService$notifyForBatteryEventsUpdate$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BatteryEvents batteryEvents = this.$events;
            Log.d("BatteryEventService", "notifyForBatteryEventsUpdate: " + batteryEvents);
            BatteryEventService batteryEventService = this.this$0;
            BatteryEvents batteryEvents2 = this.$events;
            this.label = 1;
            if (BuildersKt.withContext(batteryEventService.backgroundDispatcher, new BatteryEventService$notifyAidlBatteryEventsCallbacks$2(batteryEvents2, batteryEventService, (Continuation) null), this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return unit;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        BatteryEventService batteryEventService2 = this.this$0;
        BatteryEvents batteryEvents3 = this.$events;
        this.label = 2;
        Object withContext = BuildersKt.withContext(batteryEventService2.backgroundDispatcher, new BatteryEventService$notifyBroadcastBatteryEventsUpdate$2(batteryEvents3, batteryEventService2, (Continuation) null), this);
        if (withContext != coroutineSingletons) {
            withContext = unit;
        }
        if (withContext == coroutineSingletons) {
            return coroutineSingletons;
        }
        return unit;
    }
}
