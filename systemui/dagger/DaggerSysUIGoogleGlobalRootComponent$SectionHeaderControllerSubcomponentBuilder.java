package com.google.android.systemui.dagger;

import com.android.systemui.statusbar.notification.dagger.SectionHeaderControllerSubcomponent$Builder;
import dagger.internal.Preconditions;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentBuilder implements SectionHeaderControllerSubcomponent$Builder {
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
