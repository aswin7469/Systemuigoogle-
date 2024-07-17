package com.google.android.systemui.dagger;

import com.android.systemui.dreams.touch.DreamOverlayTouchMonitor;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.phone.KeyguardStatusBarView;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import dagger.internal.DoubleCheck;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$InputSessionComponentImpl {
    public final Object dreamOverlayComponentImpl;
    public final Object gestureListener;
    public final Object inputEventListener;
    public final Object inputSessionComponentImpl;
    public final Object name;
    public final Object pilferOnGestureConsume;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    public DaggerSysUIGoogleGlobalRootComponent$InputSessionComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, KeyguardStatusBarView keyguardStatusBarView, NotificationPanelViewController.AnonymousClass10 r10) {
        this.name = keyguardStatusBarView;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.pilferOnGestureConsume = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 0, 6));
        this.dreamOverlayComponentImpl = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 1, 6));
        this.inputSessionComponentImpl = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 2, 6));
    }

    public DaggerSysUIGoogleGlobalRootComponent$InputSessionComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl, DreamOverlayTouchMonitor.AnonymousClass2 r4, DreamOverlayTouchMonitor.AnonymousClass3 r5, Boolean bool) {
        this.inputSessionComponentImpl = this;
        this.dreamOverlayComponentImpl = daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl;
        this.name = "dreamOverlay";
        this.pilferOnGestureConsume = bool;
    }
}
