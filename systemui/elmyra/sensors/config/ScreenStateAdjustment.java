package com.google.android.systemui.elmyra.sensors.config;

import android.content.Context;
import android.os.PowerManager;
import android.util.TypedValue;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
        context.getResources().getValue(2131165908, typedValue, true);
        this.mScreenOffAdjustment = typedValue.getFloat();
        keyguardUpdateMonitor.registerCallback(r0);
    }
}
