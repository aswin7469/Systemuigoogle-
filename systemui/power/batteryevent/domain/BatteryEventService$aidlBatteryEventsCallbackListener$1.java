package com.google.android.systemui.power.batteryevent.domain;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BatteryEventService$aidlBatteryEventsCallbackListener$1 extends RemoteCallbackList {
    public final /* synthetic */ BatteryEventService this$0;

    public BatteryEventService$aidlBatteryEventsCallbackListener$1(BatteryEventService batteryEventService) {
        this.this$0 = batteryEventService;
    }

    public final void onCallbackDied(IInterface iInterface, Object obj) {
        IBatteryEventsListener iBatteryEventsListener = (IBatteryEventsListener) iInterface;
        super.onCallbackDied(iBatteryEventsListener, obj);
        this.this$0.batteryEventsCallbackCache.remove(iBatteryEventsListener);
    }

    public final boolean unregister(IBatteryEventsListener iBatteryEventsListener) {
        boolean unregister = super.unregister(iBatteryEventsListener);
        this.this$0.batteryEventsCallbackCache.remove(iBatteryEventsListener);
        return unregister;
    }
}
