package com.google.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Handler;
import android.os.PowerManager;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.settings.UserContentResolverProvider;
import com.android.systemui.statusbar.policy.BatteryControllerLogger;
import com.google.android.systemui.reversecharging.ReverseChargingController;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class GooglePolicyModule_ProvideBatteryControllerFactory implements Provider {
    public static BatteryControllerImplGoogle provideBatteryController(Context context, EnhancedEstimates enhancedEstimates, PowerManager powerManager, BroadcastDispatcher broadcastDispatcher, DemoModeController demoModeController, DumpManager dumpManager, BatteryControllerLogger batteryControllerLogger, Handler handler, Handler handler2, UserContentResolverProvider userContentResolverProvider, ReverseChargingController reverseChargingController) {
        BatteryControllerImplGoogle batteryControllerImplGoogle = new BatteryControllerImplGoogle(context, enhancedEstimates, powerManager, broadcastDispatcher, demoModeController, dumpManager, batteryControllerLogger, handler, handler2, userContentResolverProvider, reverseChargingController);
        batteryControllerImplGoogle.init$10();
        return batteryControllerImplGoogle;
    }
}
