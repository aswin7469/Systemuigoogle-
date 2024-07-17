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
import com.android.systemui.statusbar.policy.CastController;
import com.android.systemui.statusbar.policy.DataSaverControllerImpl;
import com.android.systemui.statusbar.policy.DeviceControlsControllerImpl;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.SafetyController;
import com.android.systemui.statusbar.policy.WalletControllerImpl;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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

    public AutoTileManagerGoogle(Context context, AutoAddTracker.Builder builder, QSHost qSHost, Handler handler, SecureSettings secureSettings, HotspotController hotspotController, DataSaverControllerImpl dataSaverControllerImpl, ManagedProfileControllerImpl managedProfileControllerImpl, NightDisplayListenerModule$Builder nightDisplayListenerModule$Builder, CastController castController, BatteryController batteryController, ReduceBrightColorsController reduceBrightColorsController, DeviceControlsControllerImpl deviceControlsControllerImpl, WalletControllerImpl walletControllerImpl, SafetyController safetyController, boolean z) {
        super(context, builder, qSHost, handler, secureSettings, hotspotController, dataSaverControllerImpl, managedProfileControllerImpl, nightDisplayListenerModule$Builder, castController, reduceBrightColorsController, deviceControlsControllerImpl, walletControllerImpl, safetyController, z);
        this.mBatteryController = batteryController;
    }
}
