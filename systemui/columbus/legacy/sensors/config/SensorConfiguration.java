package com.google.android.systemui.columbus.legacy.sensors.config;

import android.content.Context;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SensorConfiguration {
    public final float defaultSensitivityValue;
    public final float lowSensitivityValue;

    public SensorConfiguration(Context context) {
        this.defaultSensitivityValue = ((float) context.getResources().getInteger(2131427341)) * 0.01f;
        this.lowSensitivityValue = ((float) context.getResources().getInteger(2131427342)) * 0.01f;
    }
}
