package com.google.android.systemui.dagger;

import android.os.IThermalService;
import android.os.ServiceManager;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SystemUIGoogleModule_ProvideIThermalServiceFactory implements Provider {
    public static IThermalService provideIThermalService() {
        IThermalService asInterface = IThermalService.Stub.asInterface(ServiceManager.getService("thermalservice"));
        Preconditions.checkNotNullFromProvides(asInterface);
        return asInterface;
    }
}
