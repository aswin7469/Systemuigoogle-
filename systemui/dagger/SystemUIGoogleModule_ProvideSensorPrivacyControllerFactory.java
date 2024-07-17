package com.google.android.systemui.dagger;

import android.hardware.SensorPrivacyManager;
import com.android.systemui.statusbar.policy.SensorPrivacyControllerImpl;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class SystemUIGoogleModule_ProvideSensorPrivacyControllerFactory implements Provider {
    public static SensorPrivacyControllerImpl provideSensorPrivacyController(SensorPrivacyManager sensorPrivacyManager) {
        SensorPrivacyControllerImpl sensorPrivacyControllerImpl = new SensorPrivacyControllerImpl(sensorPrivacyManager);
        sensorPrivacyControllerImpl.mSensorPrivacyEnabled = sensorPrivacyControllerImpl.mSensorPrivacyManager.isAllSensorPrivacyEnabled();
        sensorPrivacyControllerImpl.mSensorPrivacyManager.addAllSensorPrivacyListener(sensorPrivacyControllerImpl);
        return sensorPrivacyControllerImpl;
    }
}
