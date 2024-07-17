package com.google.android.systemui.power;

import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatteryEventClient$listener$1 extends IBatteryEventsListener.Stub {
    public final /* synthetic */ BatteryEventClient this$0;

    public BatteryEventClient$listener$1(BatteryEventClient batteryEventClient) {
        this.this$0 = batteryEventClient;
        attachInterface(this, "com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener");
    }

    public final void onBatteryEventChanged(int i, int i2, List list) {
        BatteryEventClient$logWithCaller$1 batteryEventClient$logWithCaller$1 = this.this$0.logWithCaller;
        batteryEventClient$logWithCaller$1.d("onBatteryEventChanged: " + list + ", batteryLevel: " + i);
        Function3 function3 = this.this$0.onBatteryEventUpdate;
        if (list == null) {
            list = EmptyList.INSTANCE;
        }
        function3.invoke(list, Integer.valueOf(i), Integer.valueOf(i2));
    }
}
