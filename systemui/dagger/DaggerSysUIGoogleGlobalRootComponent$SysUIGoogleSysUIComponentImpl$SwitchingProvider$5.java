package com.google.android.systemui.dagger;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.LightBarTransitionsController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$5 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$5(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final LightBarTransitionsController create(LightBarTransitionsController.DarkIntensityApplier darkIntensityApplier) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        Context context = switchingProvider.sysUIGoogleGlobalRootComponentImpl.context;
        Object obj = switchingProvider.wMComponentImpl;
        return new LightBarTransitionsController(context, darkIntensityApplier, (CommandQueue) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).provideCommandQueueProvider.get(), (KeyguardStateController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).keyguardStateControllerImplProvider.get(), (StatusBarStateController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).statusBarStateControllerImplProvider.get());
    }
}
