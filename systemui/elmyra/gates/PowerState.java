package com.google.android.systemui.elmyra.gates;

import android.os.PowerManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class PowerState extends Gate {
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() {
        public final void onFinishedGoingToSleep(int i) {
            PowerState.this.notifyListener();
        }

        public final void onStartedWakingUp() {
            PowerState.this.notifyListener();
        }
    };
    public final PowerManager mPowerManager;

    public PowerState(Executor executor, PowerManager powerManager, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        super(executor);
        this.mPowerManager = powerManager;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
    }

    public boolean isBlocked() {
        return !this.mPowerManager.isInteractive();
    }

    public void onActivate() {
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateMonitorCallback);
    }

    public void onDeactivate() {
        this.mKeyguardUpdateMonitor.removeCallback(this.mKeyguardUpdateMonitorCallback);
    }
}
