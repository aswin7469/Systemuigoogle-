package com.google.android.systemui.power.batteryevent.common.dagger;

import com.google.android.systemui.power.batteryevent.repository.HalDataSource;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class EventFrameworkModule_Companion_ProvideHalDataSourceFactory implements Provider {
    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.systemui.power.batteryevent.repository.GoogleBatteryManagerWrapperImpl, java.lang.Object] */
    public static HalDataSource provideHalDataSource() {
        return new HalDataSource(new Object());
    }
}
