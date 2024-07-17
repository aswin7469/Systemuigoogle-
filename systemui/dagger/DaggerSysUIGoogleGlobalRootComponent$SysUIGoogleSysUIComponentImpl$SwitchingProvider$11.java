package com.google.android.systemui.dagger;

import com.android.systemui.util.wakelock.DelayedWakeLock;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.internal.DoubleCheck;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final DelayedWakeLock create(String str) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        DoubleCheck.lazy(((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).provideBgHandlerProvider);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        return new DelayedWakeLock(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).wakeLockLogger(), str);
    }
}
