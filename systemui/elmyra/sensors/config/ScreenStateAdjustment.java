package com.google.android.systemui.elmyra.sensors.config;

import android.content.Context;
import android.os.PowerManager;
import android.util.TypedValue;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ScreenStateAdjustment {
    public Consumer mCallback;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback;
    public final PowerManager mPowerManager;
    public final float mScreenOffAdjustment;

    public ScreenStateAdjustment(Context context, PowerManager powerManager, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        AnonymousClass1 r0 = new KeyguardUpdateMonitorCallback() {
            public final void onFinishedGoingToSleep(int i) {
                ScreenStateAdjustment screenStateAdjustment = ScreenStateAdjustment.this;
                Consumer consumer = screenStateAdjustment.mCallback;
                if (consumer != null) {
                    consumer.accept(screenStateAdjustment);
                }
            }

            public final void onStartedWakingUp() {
                ScreenStateAdjustment screenStateAdjustment = ScreenStateAdjustment.this;
                Consumer consumer = screenStateAdjustment.mCallback;
                if (consumer != null) {
                    consumer.accept(screenStateAdjustment);
                }
            }
        };
        this.mKeyguardUpdateMonitorCallback = r0;
        this.mPowerManager = powerManager;
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(2131165876, typedValue, true);
        this.mScreenOffAdjustment = typedValue.getFloat();
        keyguardUpdateMonitor.registerCallback(r0);
    }
}
