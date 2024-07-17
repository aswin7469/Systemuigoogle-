package com.google.android.systemui.autorotate;

import android.provider.DeviceConfig;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class AutorotateDataService$$ExternalSyntheticLambda0 implements DeviceConfig.OnPropertiesChangedListener {
    public final /* synthetic */ AutorotateDataService f$0;

    public /* synthetic */ AutorotateDataService$$ExternalSyntheticLambda0(AutorotateDataService autorotateDataService) {
        this.f$0 = autorotateDataService;
    }

    public final void onPropertiesChanged(DeviceConfig.Properties properties) {
        AutorotateDataService autorotateDataService = this.f$0;
        autorotateDataService.getClass();
        Set keyset = properties.getKeyset();
        if (keyset.contains("log_raw_sensor_data") || keyset.contains("log_rotation_preindication")) {
            autorotateDataService.readFlagsToControlSensorLogging();
        }
    }
}
