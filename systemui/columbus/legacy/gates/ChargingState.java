package com.google.android.systemui.columbus.legacy.gates;

import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.broadcast.BroadcastDispatcher;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ChargingState extends TransientGate {
    public final BroadcastDispatcher broadcastDispatcher;
    public final long gateDuration = 500;
    public final ChargingState$powerReceiver$1 powerReceiver = new ChargingState$powerReceiver$1(this);

    public ChargingState(BroadcastDispatcher broadcastDispatcher2) {
        this.broadcastDispatcher = broadcastDispatcher2;
    }

    public final void onActivate() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.powerReceiver, intentFilter, (Executor) null, (UserHandle) null, 0, (String) null, 60);
    }

    public final void onDeactivate() {
        this.broadcastDispatcher.unregisterReceiver(this.powerReceiver);
    }
}
