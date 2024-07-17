package com.google.android.systemui.columbus.legacy.sensors.config;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
