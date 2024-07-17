package com.google.android.systemui.power.batteryevent.common.dagger;

import android.content.Context;
import com.google.android.systemui.power.batteryevent.repository.SettingsDataSource;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class EventFrameworkModule_Companion_ProvideSettingsDataSourceFactory implements Provider {
    public static SettingsDataSource provideSettingsDataSource(Context context) {
        return new SettingsDataSource(context);
    }
}
