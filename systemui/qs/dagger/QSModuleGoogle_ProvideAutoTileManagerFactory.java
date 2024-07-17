package com.google.android.systemui.qs.dagger;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.dagger.NightDisplayListenerModule$Builder;
import com.android.systemui.qs.AutoAddTracker;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.ReduceBrightColorsController;
import com.android.systemui.statusbar.phone.ManagedProfileControllerImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.CastController;
import com.android.systemui.statusbar.policy.DataSaverControllerImpl;
import com.android.systemui.statusbar.policy.DeviceControlsControllerImpl;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.SafetyController;
import com.android.systemui.statusbar.policy.WalletControllerImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.statusbar.phone.AutoTileManagerGoogle;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class QSModuleGoogle_ProvideAutoTileManagerFactory implements Provider {
    public static void provideAutoTileManager(Context context, AutoAddTracker.Builder builder, QSHost qSHost, Handler handler, SecureSettings secureSettings, HotspotController hotspotController, DataSaverControllerImpl dataSaverControllerImpl, ManagedProfileControllerImpl managedProfileControllerImpl, NightDisplayListenerModule$Builder nightDisplayListenerModule$Builder, CastController castController, BatteryController batteryController, ReduceBrightColorsController reduceBrightColorsController, DeviceControlsControllerImpl deviceControlsControllerImpl, WalletControllerImpl walletControllerImpl, SafetyController safetyController, boolean z) {
        new AutoTileManagerGoogle(context, builder, qSHost, handler, secureSettings, hotspotController, dataSaverControllerImpl, managedProfileControllerImpl, nightDisplayListenerModule$Builder, castController, batteryController, reduceBrightColorsController, deviceControlsControllerImpl, walletControllerImpl, safetyController, z);
        throw new IllegalStateException("Legacy code path not supported when com.android.systemui.qs_new_pipeline is enabled.".toString());
    }
}
