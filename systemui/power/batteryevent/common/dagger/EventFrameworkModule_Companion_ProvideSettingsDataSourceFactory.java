package com.google.android.systemui.power.batteryevent.common.dagger;

import android.content.Context;
import com.google.android.systemui.power.batteryevent.repository.SettingsDataSource;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class EventFrameworkModule_Companion_ProvideSettingsDataSourceFactory implements Provider {
    public static SettingsDataSource provideSettingsDataSource(Context context) {
        return new SettingsDataSource(context);
    }
}
