package com.google.android.systemui.dagger;

import com.google.android.systemui.smartspace.BcSmartspaceDataProvider;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SystemUIGoogleModule_ProvideBcSmartspaceDataPluginFactory implements Provider {
    public static BcSmartspaceDataProvider provideBcSmartspaceDataPlugin() {
        return new BcSmartspaceDataProvider();
    }
}
