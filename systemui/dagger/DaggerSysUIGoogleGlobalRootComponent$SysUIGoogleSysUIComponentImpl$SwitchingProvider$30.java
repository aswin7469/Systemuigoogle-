package com.google.android.systemui.dagger;

import android.content.Context;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import com.google.android.systemui.elmyra.gates.KeyguardDeferredSetup;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final KeyguardDeferredSetup create(Set set) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        return new KeyguardDeferredSetup((Context) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (Executor) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardVisibility(), (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get(), set);
    }
}
