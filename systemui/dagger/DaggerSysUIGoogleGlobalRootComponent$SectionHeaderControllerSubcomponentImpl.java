package com.google.android.systemui.dagger;

import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl {
    public final String clickIntentAction;
    public final Integer headerText;
    public final String nodeLabel;
    public final Provider sectionHeaderNodeControllerImplProvider;

    public DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, String str, Integer num, String str2) {
        this.nodeLabel = str;
        this.headerText = num;
        this.clickIntentAction = str2;
        this.sectionHeaderNodeControllerImplProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 4));
    }
}
