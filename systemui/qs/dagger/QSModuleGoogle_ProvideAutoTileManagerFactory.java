package com.google.android.systemui.qs.dagger;

import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dagger.NightDisplayListenerModule$Builder;
import com.android.systemui.dump.DumpPriority;
import com.android.systemui.qs.AutoAddTracker;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.ReduceBrightColorsController;
import com.android.systemui.statusbar.phone.AutoTileManager;
import com.android.systemui.statusbar.phone.ManagedProfileControllerImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.CastController;
import com.android.systemui.statusbar.policy.DataSaverController;
import com.android.systemui.statusbar.policy.DeviceControlsController;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.SafetyController;
import com.android.systemui.statusbar.policy.WalletController;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import com.google.android.systemui.statusbar.phone.AutoTileManagerGoogle;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class QSModuleGoogle_ProvideAutoTileManagerFactory implements Provider {
    public static AutoTileManagerGoogle provideAutoTileManager(Context context, AutoAddTracker.Builder builder, QSHost qSHost, Handler handler, SecureSettings secureSettings, HotspotController hotspotController, DataSaverController dataSaverController, ManagedProfileControllerImpl managedProfileControllerImpl, NightDisplayListenerModule$Builder nightDisplayListenerModule$Builder, CastController castController, BatteryController batteryController, ReduceBrightColorsController reduceBrightColorsController, DeviceControlsController deviceControlsController, WalletController walletController, SafetyController safetyController, boolean z) {
        AutoTileManagerGoogle autoTileManagerGoogle = r0;
        AutoTileManagerGoogle autoTileManagerGoogle2 = new AutoTileManagerGoogle(context, builder, qSHost, handler, secureSettings, hotspotController, dataSaverController, managedProfileControllerImpl, nightDisplayListenerModule$Builder, castController, batteryController, reduceBrightColorsController, deviceControlsController, walletController, safetyController, z);
        AutoTileManagerGoogle autoTileManagerGoogle3 = autoTileManagerGoogle;
        boolean z2 = autoTileManagerGoogle3.mInitialized;
        AutoAddTracker autoAddTracker = autoTileManagerGoogle3.mAutoTracker;
        if (z2) {
            Log.w("AutoTileManager", "Trying to re-initialize");
        } else {
            autoAddTracker.dumpManager.registerDumpable(autoAddTracker, DumpPriority.CRITICAL, "AutoAddTracker");
            autoAddTracker.loadTiles();
            SecureSettings secureSettings2 = autoAddTracker.secureSettings;
            ((SecureSettingsImpl) secureSettings2).getClass();
            secureSettings2.registerContentObserverForUser(Settings.Secure.getUriFor("qs_auto_tiles"), false, (ContentObserver) autoAddTracker.contentObserver, -1);
            BroadcastDispatcher.registerReceiver$default(autoAddTracker.broadcastDispatcher, autoAddTracker.restoreReceiver, AutoAddTracker.FILTER, autoAddTracker.backgroundExecutor, UserHandle.of(autoAddTracker.userId), 0, (String) null, 48);
            try {
                for (String str : autoTileManagerGoogle3.mContext.getResources().getStringArray(2130903113)) {
                    String[] split = str.split(":");
                    if (split.length == 2) {
                        autoTileManagerGoogle3.mAutoAddSettingList.add(new AutoTileManager.AutoAddSetting(autoTileManagerGoogle3, autoTileManagerGoogle3.mSecureSettings, autoTileManagerGoogle3.mHandler, split[0], autoTileManagerGoogle3.mCurrentUser.getIdentifier(), split[1]));
                    } else {
                        Log.w("AutoTileManager", "Malformed item in array: ".concat(str));
                    }
                }
            } catch (Resources.NotFoundException unused) {
                Log.w("AutoTileManager", "Missing config resource");
            }
            autoTileManagerGoogle3.startControllersAndSettingsListeners();
            autoTileManagerGoogle3.mInitialized = true;
        }
        if (!autoAddTracker.isAdded("ott") && Build.IS_DEBUGGABLE) {
            autoAddTracker.setTileAdded("ott");
            autoTileManagerGoogle3.mHost.addTile("ott");
        }
        return autoTileManagerGoogle3;
    }
}
