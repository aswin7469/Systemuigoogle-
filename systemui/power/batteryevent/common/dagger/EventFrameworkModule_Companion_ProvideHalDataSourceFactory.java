package com.google.android.systemui.power.batteryevent.common.dagger;

import com.google.android.systemui.power.batteryevent.repository.HalDataSource;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class EventFrameworkModule_Companion_ProvideHalDataSourceFactory implements Provider {
    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.systemui.power.batteryevent.repository.GoogleBatteryManagerWrapperImpl, java.lang.Object] */
    public static HalDataSource provideHalDataSource() {
        return new HalDataSource(new Object());
    }
}
