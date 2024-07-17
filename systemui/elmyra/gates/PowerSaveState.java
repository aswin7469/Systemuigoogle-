package com.google.android.systemui.elmyra.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import com.android.systemui.broadcast.BroadcastDispatcher;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class PowerSaveState extends Gate {
    public boolean mBatterySaverEnabled;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public boolean mIsDeviceInteractive;
    public final Object mLock = new Object();
    public final PowerManager mPowerManager;
    public final AnonymousClass1 mReceiver = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            PowerSaveState.this.refreshStatus();
            PowerSaveState.this.notifyListener();
        }
    };

    public PowerSaveState(Executor executor, PowerManager powerManager, BroadcastDispatcher broadcastDispatcher) {
        super(executor);
        this.mPowerManager = powerManager;
        this.mBroadcastDispatcher = broadcastDispatcher;
    }

    public final boolean isBlocked() {
        boolean z;
        synchronized (this.mLock) {
            try {
                if (!this.mBatterySaverEnabled || this.mIsDeviceInteractive) {
                    z = false;
                } else {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public final void onActivate() {
        IntentFilter intentFilter = new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.mBroadcastDispatcher.registerReceiver(this.mReceiver, intentFilter);
        refreshStatus();
    }

    public final void onDeactivate() {
        this.mBroadcastDispatcher.unregisterReceiver(this.mReceiver);
    }

    public final void refreshStatus() {
        synchronized (this.mLock) {
            this.mBatterySaverEnabled = this.mPowerManager.getPowerSaveState(13).batterySaverEnabled;
            this.mIsDeviceInteractive = this.mPowerManager.isInteractive();
        }
    }
}
