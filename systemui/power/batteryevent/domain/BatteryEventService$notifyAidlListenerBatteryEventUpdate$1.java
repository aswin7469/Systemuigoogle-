package com.google.android.systemui.power.batteryevent.domain;

import com.google.android.systemui.power.batteryevent.common.BatteryEvents;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class BatteryEventService$notifyAidlListenerBatteryEventUpdate$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BatteryEventService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryEventService$notifyAidlListenerBatteryEventUpdate$1(BatteryEventService batteryEventService, Continuation continuation) {
        super(continuation);
        this.this$0 = batteryEventService;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return BatteryEventService.access$notifyAidlListenerBatteryEventUpdate(this.this$0, 0, (BatteryEvents) null, this);
    }
}
