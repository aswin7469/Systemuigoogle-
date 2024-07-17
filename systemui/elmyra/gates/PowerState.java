package com.google.android.systemui.elmyra.gates;

import android.os.PowerManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
