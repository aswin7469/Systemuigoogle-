package com.google.android.systemui.autorotate;

import android.provider.DeviceConfig;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
