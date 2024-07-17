package com.google.android.systemui.dagger;

import dagger.internal.Preconditions;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder {
    public String clickIntentAction;
    public Integer headerText;
    public String nodeLabel;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    public DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
    }

    public final DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl build() {
        Class<String> cls = String.class;
        Preconditions.checkBuilderRequirement(cls, this.nodeLabel);
        Preconditions.checkBuilderRequirement(Integer.class, this.headerText);
        Preconditions.checkBuilderRequirement(cls, this.clickIntentAction);
        return new DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl(this.sysUIGoogleGlobalRootComponentImpl, this.sysUIGoogleSysUIComponentImpl, this.nodeLabel, this.headerText, this.clickIntentAction);
    }
}
