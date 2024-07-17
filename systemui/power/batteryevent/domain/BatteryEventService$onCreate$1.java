package com.google.android.systemui.power.batteryevent.domain;

import android.util.Log;
import com.google.android.systemui.power.batteryevent.common.BatteryEvents;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class BatteryEventService$onCreate$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BatteryEventService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryEventService$onCreate$1(BatteryEventService batteryEventService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryEventService;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryEventService$onCreate$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        ((BatteryEventService$onCreate$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final BatteryEventService batteryEventService = this.this$0;
            StateFlowImpl stateFlowImpl = batteryEventService.eventStateController.mutableBatteryEventsFlow;
            AnonymousClass1 r3 = new FlowCollector() {
                public final Object emit(Object obj, Continuation continuation) {
                    BatteryEvents batteryEvents = (BatteryEvents) obj;
                    Log.d("BatteryEventService", "collect BatteryEvents: " + batteryEvents);
                    Unit unit = Unit.INSTANCE;
                    if (batteryEvents == null) {
                        return unit;
                    }
                    BatteryEventService batteryEventService = BatteryEventService.this;
                    Object withContext = BuildersKt.withContext(batteryEventService.backgroundDispatcher, new BatteryEventService$notifyForBatteryEventsUpdate$2(batteryEvents, batteryEventService, (Continuation) null), continuation);
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (withContext != coroutineSingletons) {
                        withContext = unit;
                    }
                    if (withContext == coroutineSingletons) {
                        return withContext;
                    }
                    return unit;
                }
            };
            this.label = 1;
            stateFlowImpl.collect(r3, this);
            return coroutineSingletons;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
            throw new KotlinNothingValueException();
        }
    }
}
