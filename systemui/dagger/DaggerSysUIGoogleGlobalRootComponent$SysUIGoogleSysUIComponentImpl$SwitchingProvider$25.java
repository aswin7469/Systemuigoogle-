package com.google.android.systemui.dagger;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.service.vr.IVrManager;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.brightness.BrightnessController;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.systemui.util.concurrency.GlobalConcurrencyModule_ProvideMainLooperFactory;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$25 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$25(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final BrightnessController create(BrightnessSliderController brightnessSliderController) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        Context context = switchingProvider.sysUIGoogleGlobalRootComponentImpl.context;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        return new BrightnessController(context, brightnessSliderController, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (DisplayTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDisplayTrackerProvider.get(), (DisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDisplayManagerProvider.get(), (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get(), (IVrManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIVrManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgHandlerProvider.get());
    }
}
