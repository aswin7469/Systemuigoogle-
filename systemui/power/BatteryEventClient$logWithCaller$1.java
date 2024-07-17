package com.google.android.systemui.power;

import android.util.Log;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatteryEventClient$logWithCaller$1 {
    public final /* synthetic */ BatteryEventClient this$0;

    public BatteryEventClient$logWithCaller$1(BatteryEventClient batteryEventClient) {
        this.this$0 = batteryEventClient;
    }

    public final void d(String str) {
        String str2 = this.this$0.callerTag;
        Log.d("BatteryEventClient", "[" + str2 + "] " + str);
    }

    public final void w(String str) {
        String str2 = this.this$0.callerTag;
        Log.w("BatteryEventClient", "[" + str2 + "] " + str);
    }
}
