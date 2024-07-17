package com.google.android.systemui.dagger;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.service.vr.IVrManager;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.brightness.BrightnessController;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.systemui.util.concurrency.GlobalConcurrencyModule_ProvideMainLooperFactory;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$21 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$21(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final BrightnessController create(BrightnessSliderController brightnessSliderController) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        Context context = switchingProvider.sysUIGoogleGlobalRootComponentImpl.context;
        Object obj = switchingProvider.wMComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        return new BrightnessController(context, brightnessSliderController, (UserTracker) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).provideUserTrackerProvider.get(), (DisplayTracker) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).provideDisplayTrackerProvider.get(), (DisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDisplayManagerProvider.get(), (SecureSettings) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).secureSettingsImplProvider.get(), (IVrManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIVrManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper(), ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).backgroundHandler());
    }
}
