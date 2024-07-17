package com.google.android.systemui.dagger;

import android.content.Context;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import com.google.android.systemui.elmyra.gates.KeyguardDeferredSetup;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$24 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$24(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final KeyguardDeferredSetup create(Set set) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        Object obj = switchingProvider.wMComponentImpl;
        return new KeyguardDeferredSetup((Context) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (Executor) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).keyguardVisibility(), (SecureSettings) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).secureSettingsImplProvider.get(), set);
    }
}
