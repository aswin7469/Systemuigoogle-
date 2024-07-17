package com.google.android.systemui.dagger;

import android.os.IThermalService;
import android.os.ServiceManager;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class SystemUIGoogleModule_ProvideIThermalServiceFactory implements Provider {
    public static IThermalService provideIThermalService() {
        IThermalService asInterface = IThermalService.Stub.asInterface(ServiceManager.getService("thermalservice"));
        Preconditions.checkNotNullFromProvides(asInterface);
        return asInterface;
    }
}
