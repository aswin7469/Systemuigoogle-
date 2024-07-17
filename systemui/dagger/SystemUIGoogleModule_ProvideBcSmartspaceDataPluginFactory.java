package com.google.android.systemui.dagger;

import com.google.android.systemui.smartspace.BcSmartspaceDataProvider;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class SystemUIGoogleModule_ProvideBcSmartspaceDataPluginFactory implements Provider {
    public static BcSmartspaceDataProvider provideBcSmartspaceDataPlugin() {
        return new BcSmartspaceDataProvider();
    }
}
