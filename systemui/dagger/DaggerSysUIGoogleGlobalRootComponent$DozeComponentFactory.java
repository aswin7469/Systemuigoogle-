package com.google.android.systemui.dagger;

import android.view.Display;
import com.android.keyguard.KeyguardStatusView;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorComponent$Factory;
import com.android.systemui.qs.dagger.QSSceneComponent$Factory;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory implements MediaProjectionAppSelectorComponent$Factory, QSSceneComponent$Factory {
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    public /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
    }

    public final DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl build(KeyguardStatusView keyguardStatusView, Display display) {
        keyguardStatusView.getClass();
        display.getClass();
        return new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl(this.sysUIGoogleGlobalRootComponentImpl, this.sysUIGoogleSysUIComponentImpl, keyguardStatusView, display);
    }

    public /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0 daggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0) {
        this(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl);
    }
}
