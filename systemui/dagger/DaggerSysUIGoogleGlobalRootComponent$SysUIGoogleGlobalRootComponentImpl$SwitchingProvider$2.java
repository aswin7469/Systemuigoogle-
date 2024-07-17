package com.google.android.systemui.dagger;

import com.android.systemui.unfold.util.ATraceLoggerTransitionProgressListener;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2(DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final ATraceLoggerTransitionProgressListener create(String str) {
        ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) this.this$0.keyguardStatusBarViewComponentImpl).unfoldTransitionModule.getClass();
        return new ATraceLoggerTransitionProgressListener(str);
    }
}
