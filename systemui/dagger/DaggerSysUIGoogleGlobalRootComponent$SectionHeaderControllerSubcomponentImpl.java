package com.google.android.systemui.dagger;

import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl {
    public final String clickIntentAction;
    public final Integer headerText;
    public final String nodeLabel;
    public final Provider sectionHeaderNodeControllerImplProvider;

    public DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, String str, Integer num, String str2) {
        this.nodeLabel = str;
        this.headerText = num;
        this.clickIntentAction = str2;
        this.sectionHeaderNodeControllerImplProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 0, 13));
    }
}
