package com.google.android.systemui.statusbar.phone;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.dagger.NightDisplayListenerModule$Builder;
import com.android.systemui.qs.AutoAddTracker;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.ReduceBrightColorsController;
import com.android.systemui.statusbar.phone.AutoTileManager;
import com.android.systemui.statusbar.phone.ManagedProfileControllerImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.CastController;
import com.android.systemui.statusbar.policy.DataSaverController;
import com.android.systemui.statusbar.policy.DeviceControlsController;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.SafetyController;
import com.android.systemui.statusbar.policy.WalletController;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AutoTileManagerGoogle extends AutoTileManager {
    public final BatteryController mBatteryController;
    public final AnonymousClass1 mBatteryControllerCallback = new BatteryController.BatteryStateChangeCallback() {
        public final void onReverseChanged(int i, String str, boolean z) {
            AutoTileManagerGoogle autoTileManagerGoogle = AutoTileManagerGoogle.this;
            if (!autoTileManagerGoogle.mAutoTracker.isAdded("reverse") && z) {
                autoTileManagerGoogle.mHost.addTile("reverse");
                autoTileManagerGoogle.mAutoTracker.setTileAdded("reverse");
                autoTileManagerGoogle.mHandler.post(new AutoTileManagerGoogle$1$$ExternalSyntheticLambda0(this));
            }
        }
    };

    public AutoTileManagerGoogle(Context context, AutoAddTracker.Builder builder, QSHost qSHost, Handler handler, SecureSettings secureSettings, HotspotController hotspotController, DataSaverController dataSaverController, ManagedProfileControllerImpl managedProfileControllerImpl, NightDisplayListenerModule$Builder nightDisplayListenerModule$Builder, CastController castController, BatteryController batteryController, ReduceBrightColorsController reduceBrightColorsController, DeviceControlsController deviceControlsController, WalletController walletController, SafetyController safetyController, boolean z) {
        super(context, builder, qSHost, handler, secureSettings, hotspotController, dataSaverController, managedProfileControllerImpl, nightDisplayListenerModule$Builder, castController, reduceBrightColorsController, deviceControlsController, walletController, safetyController, z);
        this.mBatteryController = batteryController;
    }

    public final void startControllersAndSettingsListeners() {
        super.startControllersAndSettingsListeners();
        if (!this.mAutoTracker.isAdded("reverse")) {
            this.mBatteryController.addCallback(this.mBatteryControllerCallback);
        }
    }

    public final void stopListening() {
        super.stopListening();
        ((BatteryControllerImpl) this.mBatteryController).removeCallback(this.mBatteryControllerCallback);
    }
}
