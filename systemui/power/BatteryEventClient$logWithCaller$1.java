package com.google.android.systemui.power;

import android.util.Log;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
