package com.google.android.systemui.dagger;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.LightBarTransitionsController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final LightBarTransitionsController create(LightBarTransitionsController.DarkIntensityApplier darkIntensityApplier) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        Context context = switchingProvider.sysUIGoogleGlobalRootComponentImpl.context;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        return new LightBarTransitionsController(context, darkIntensityApplier, (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get());
    }
}
