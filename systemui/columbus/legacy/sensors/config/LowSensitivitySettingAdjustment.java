package com.google.android.systemui.columbus.legacy.sensors.config;

import android.provider.Settings;
import com.android.systemui.settings.UserTrackerImpl;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LowSensitivitySettingAdjustment {
    public Function1 callback;
    public final SensorConfiguration sensorConfiguration;
    public boolean useLowSensitivity;

    public LowSensitivitySettingAdjustment(ColumbusSettings columbusSettings, SensorConfiguration sensorConfiguration2) {
        this.sensorConfiguration = sensorConfiguration2;
        columbusSettings.registerColumbusSettingsChangeListener(new LowSensitivitySettingAdjustment$settingsChangeListener$1(this));
        this.useLowSensitivity = Settings.Secure.getIntForUser(columbusSettings.contentResolver, "columbus_low_sensitivity", 0, ((UserTrackerImpl) columbusSettings.userTracker).getUserId()) != 0;
        Function1 function1 = this.callback;
        if (function1 != null) {
            function1.invoke(this);
        }
    }
}
