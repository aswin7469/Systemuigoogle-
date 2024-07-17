package com.google.android.systemui.columbus.legacy.sensors.config;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LowSensitivitySettingAdjustment$settingsChangeListener$1 implements ColumbusSettings.ColumbusSettingsChangeListener {
    public final /* synthetic */ LowSensitivitySettingAdjustment this$0;

    public LowSensitivitySettingAdjustment$settingsChangeListener$1(LowSensitivitySettingAdjustment lowSensitivitySettingAdjustment) {
        this.this$0 = lowSensitivitySettingAdjustment;
    }

    public final void onLowSensitivityChange(boolean z) {
        LowSensitivitySettingAdjustment lowSensitivitySettingAdjustment = this.this$0;
        if (lowSensitivitySettingAdjustment.useLowSensitivity != z) {
            lowSensitivitySettingAdjustment.useLowSensitivity = z;
            Function1 function1 = lowSensitivitySettingAdjustment.callback;
            if (function1 != null) {
                function1.invoke(lowSensitivitySettingAdjustment);
            }
        }
    }
}
