package com.google.android.systemui.dagger;

import android.widget.FrameLayout;
import com.android.systemui.statusbar.policy.KeyguardUserSwitcherView;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl;
import dagger.internal.DoubleCheck;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder {
    public Object setShellMainThread;
    public final Object sysUIGoogleGlobalRootComponentImpl;

    public DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
    }

    public DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, FrameLayout frameLayout) {
        this.sysUIGoogleGlobalRootComponentImpl = frameLayout;
        this.setShellMainThread = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 2));
    }

    public DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, KeyguardUserSwitcherView keyguardUserSwitcherView) {
        this.sysUIGoogleGlobalRootComponentImpl = keyguardUserSwitcherView;
        this.setShellMainThread = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 3));
    }
}
