package com.google.android.systemui.dagger;

import android.os.Handler;
import com.android.systemui.keyguard.LifecycleScreenStatusProvider;
import com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig;
import com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider;
import com.android.systemui.unfold.updates.DeviceFoldStateProvider;
import com.android.systemui.unfold.updates.FoldProvider;
import com.android.systemui.unfold.updates.RotationChangeProvider;
import com.android.systemui.unfold.updates.hinge.HingeAngleProvider;
import com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5(DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final DeviceFoldStateProvider create(HingeAngleProvider hingeAngleProvider, RotationChangeProvider rotationChangeProvider, Handler handler) {
        DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) switchingProvider.keyguardStatusBarViewComponentImpl;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionModule.getClass();
        return new DeviceFoldStateProvider((ResourceUnfoldTransitionConfig) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) switchingProvider.keyguardStatusBarViewComponentImpl).resourceUnfoldTransitionConfigProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (LifecycleScreenStatusProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.lifecycleScreenStatusProvider.get(), (ActivityManagerActivityTypeProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.activityManagerActivityTypeProvider.get(), (UnfoldKeyguardVisibilityManagerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldKeyguardVisibilityProvider.get(), (FoldProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.deviceStateManagerFoldProvider.get(), hingeAngleProvider, rotationChangeProvider, handler);
    }
}
