package com.google.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Handler;
import android.os.PowerManager;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.policy.BatteryControllerLogger;
import com.google.android.systemui.reversecharging.ReverseChargingController;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class GooglePolicyModule_ProvideBatteryControllerFactory implements Provider {
    public static BatteryControllerImplGoogle provideBatteryController(Context context, EnhancedEstimates enhancedEstimates, PowerManager powerManager, BroadcastDispatcher broadcastDispatcher, DemoModeController demoModeController, DumpManager dumpManager, BatteryControllerLogger batteryControllerLogger, Handler handler, Handler handler2, UserTracker userTracker, ReverseChargingController reverseChargingController) {
        BatteryControllerImplGoogle batteryControllerImplGoogle = new BatteryControllerImplGoogle(context, enhancedEstimates, powerManager, broadcastDispatcher, demoModeController, dumpManager, batteryControllerLogger, handler, handler2, userTracker, reverseChargingController);
        batteryControllerImplGoogle.init$10();
        return batteryControllerImplGoogle;
    }
}
