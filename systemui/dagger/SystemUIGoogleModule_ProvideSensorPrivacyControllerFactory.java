package com.google.android.systemui.dagger;

import android.hardware.SensorPrivacyManager;
import com.android.systemui.statusbar.policy.SensorPrivacyControllerImpl;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SystemUIGoogleModule_ProvideSensorPrivacyControllerFactory implements Provider {
    public static SensorPrivacyControllerImpl provideSensorPrivacyController(SensorPrivacyManager sensorPrivacyManager) {
        SensorPrivacyControllerImpl sensorPrivacyControllerImpl = new SensorPrivacyControllerImpl(sensorPrivacyManager);
        sensorPrivacyControllerImpl.mSensorPrivacyEnabled = sensorPrivacyControllerImpl.mSensorPrivacyManager.isAllSensorPrivacyEnabled();
        sensorPrivacyControllerImpl.mSensorPrivacyManager.addAllSensorPrivacyListener(sensorPrivacyControllerImpl);
        return sensorPrivacyControllerImpl;
    }
}
