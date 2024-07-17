package com.google.android.systemui.columbus.legacy.sensors.config;

import android.provider.Settings;
import com.android.systemui.settings.UserTrackerImpl;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
