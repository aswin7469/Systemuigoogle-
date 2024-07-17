package com.google.android.systemui.dagger;

import android.view.Display;
import com.android.keyguard.KeyguardStatusView;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorComponent$Factory;
import com.android.systemui.qs.dagger.QSSceneComponent$Factory;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory implements MediaProjectionAppSelectorComponent$Factory, QSSceneComponent$Factory {
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    public /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
    }

    public DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl build(KeyguardStatusView keyguardStatusView, Display display) {
        keyguardStatusView.getClass();
        display.getClass();
        return new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl(this.sysUIGoogleGlobalRootComponentImpl, this.sysUIGoogleSysUIComponentImpl, keyguardStatusView, display);
    }
}
