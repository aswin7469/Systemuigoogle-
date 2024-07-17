package com.google.android.systemui.columbus.legacy.sensors.config;

import android.content.Context;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SensorConfiguration {
    public final float defaultSensitivityValue;
    public final float lowSensitivityValue;

    public SensorConfiguration(Context context) {
        this.defaultSensitivityValue = ((float) context.getResources().getInteger(2131427342)) * 0.01f;
        this.lowSensitivityValue = ((float) context.getResources().getInteger(2131427343)) * 0.01f;
    }
}
