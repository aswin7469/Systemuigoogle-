package com.google.android.systemui.elmyra.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.systemui.util.concurrency.DelayableExecutor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ChargingState extends TransientGate {
    public final Context mContext;
    public final AnonymousClass1 mPowerReceiver = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            ChargingState.this.block();
        }
    };

    public ChargingState(Context context, DelayableExecutor delayableExecutor, int i) {
        super(delayableExecutor, (long) i);
        this.mContext = context;
    }

    public final void onActivate() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        this.mContext.registerReceiver(this.mPowerReceiver, intentFilter);
    }

    public final void onDeactivate() {
        this.mContext.unregisterReceiver(this.mPowerReceiver);
    }
}
