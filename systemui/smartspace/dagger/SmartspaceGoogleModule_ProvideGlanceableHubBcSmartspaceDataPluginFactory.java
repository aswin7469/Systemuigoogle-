package com.google.android.systemui.smartspace.dagger;

import com.google.android.systemui.smartspace.BcSmartspaceDataProvider;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SmartspaceGoogleModule_ProvideGlanceableHubBcSmartspaceDataPluginFactory implements Provider {
    public static BcSmartspaceDataProvider provideGlanceableHubBcSmartspaceDataPlugin() {
        return new BcSmartspaceDataProvider();
    }
}
