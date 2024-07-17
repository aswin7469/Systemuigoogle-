package com.google.android.systemui.dagger;

import android.database.ContentObserver;
import android.net.Uri;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl$observer$1;
import com.android.systemui.util.settings.GlobalSettings;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SystemUIGoogleModule_ProvideDeviceProvisionedControllerFactory implements Provider {
    public static void provideDeviceProvisionedController(DeviceProvisionedControllerImpl deviceProvisionedControllerImpl) {
        if (deviceProvisionedControllerImpl.initted.compareAndSet(false, true)) {
            deviceProvisionedControllerImpl.dumpManager.registerDumpable(deviceProvisionedControllerImpl);
            deviceProvisionedControllerImpl.updateValues(-1, true, true);
            ((UserTrackerImpl) deviceProvisionedControllerImpl.userTracker).addCallback(deviceProvisionedControllerImpl.userChangedCallback, deviceProvisionedControllerImpl.backgroundExecutor);
            GlobalSettings globalSettings = deviceProvisionedControllerImpl.globalSettings;
            Uri uri = deviceProvisionedControllerImpl.deviceProvisionedUri;
            DeviceProvisionedControllerImpl$observer$1 deviceProvisionedControllerImpl$observer$1 = deviceProvisionedControllerImpl.observer;
            globalSettings.registerContentObserver(uri, false, deviceProvisionedControllerImpl$observer$1);
            globalSettings.registerContentObserver(deviceProvisionedControllerImpl.frpActiveUri, false, deviceProvisionedControllerImpl$observer$1);
            deviceProvisionedControllerImpl.secureSettings.registerContentObserverForUser(deviceProvisionedControllerImpl.userSetupUri, false, (ContentObserver) deviceProvisionedControllerImpl$observer$1, -1);
        }
    }
}
