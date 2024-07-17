package com.google.android.systemui.power.batteryevent.domain;

import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener;
import com.google.android.systemui.power.batteryevent.common.BatteryEvents;
import com.google.android.systemui.power.batteryevent.domain.BatteryEventService;
import java.util.Set;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class BatteryEventService$updateBatteryEventsCallbackCache$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BatteryEventService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryEventService$updateBatteryEventsCallbackCache$1(BatteryEventService batteryEventService, Continuation continuation) {
        super(continuation);
        this.this$0 = batteryEventService;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        BatteryEventService batteryEventService = this.this$0;
        Set set = BatteryEventService.supportedCallers;
        return batteryEventService.updateBatteryEventsCallbackCache((BatteryEventService.BatteryEventsCallbackData) null, (BatteryEvents) null, (IBatteryEventsListener) null, this);
    }
}
