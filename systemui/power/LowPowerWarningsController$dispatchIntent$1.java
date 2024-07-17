package com.google.android.systemui.power;

import android.content.Intent;
import android.os.PowerManager;
import com.android.internal.logging.UiEventLogger;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LowPowerWarningsController$dispatchIntent$1 implements Runnable {
    public final /* synthetic */ Intent $intent;
    public final /* synthetic */ LowPowerWarningsController this$0;

    public LowPowerWarningsController$dispatchIntent$1(LowPowerWarningsController lowPowerWarningsController, Intent intent) {
        this.this$0 = lowPowerWarningsController;
        this.$intent = intent;
    }

    public final void run() {
        BatteryMetricEvent batteryMetricEvent;
        BatteryMetricEvent batteryMetricEvent2;
        LowPowerWarningsController lowPowerWarningsController = this.this$0;
        Intent intent = this.$intent;
        lowPowerWarningsController.getClass();
        String action = intent.getAction();
        if (action != null) {
            int hashCode = action.hashCode();
            Boolean bool = null;
            UiEventLogger uiEventLogger = lowPowerWarningsController.uiEventLogger;
            if (hashCode != 1779291251) {
                if (hashCode == 1913925732 && action.equals("com.android.settingslib.fuelgauge.ACTION_SAVER_STATE_MANUAL_UPDATE")) {
                    int intExtra = intent.getIntExtra("extra_power_save_mode_manual_enabled_reason", 0);
                    if (intent.getBooleanExtra("extra_power_save_mode_manual_enabled", false)) {
                        batteryMetricEvent2 = BatteryMetricEvent.BATTERY_SAVER_ENABLED_REASON;
                    } else {
                        batteryMetricEvent2 = BatteryMetricEvent.BATTERY_SAVER_DISABLED_REASON;
                    }
                    uiEventLogger.logWithPosition(batteryMetricEvent2, 0, (String) null, intExtra);
                }
            } else if (action.equals("android.os.action.POWER_SAVE_MODE_CHANGED")) {
                PowerManager powerManager = lowPowerWarningsController.powerManager;
                if (powerManager != null) {
                    bool = Boolean.valueOf(powerManager.isPowerSaveMode());
                }
                if (!Intrinsics.areEqual(lowPowerWarningsController.prevPowerSaveEnabledAsync, bool)) {
                    if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
                        batteryMetricEvent = BatteryMetricEvent.BATTERY_SAVER_ENABLED;
                    } else {
                        batteryMetricEvent = BatteryMetricEvent.BATTERY_SAVER_DISABLED;
                    }
                    uiEventLogger.log(batteryMetricEvent);
                    lowPowerWarningsController.prevPowerSaveEnabledAsync = bool;
                }
            }
        }
    }
}
