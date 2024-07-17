package com.google.android.systemui.dagger;

import android.view.View;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl {
    public final /* synthetic */ int $r8$classId;
    public final Provider factoryProvider;
    public final Provider providesQSCutomizerProvider;
    public final Provider providesQSFooterProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider providesQSUsingCollapsedLandscapeMediaProvider;
    public final Provider qSAnimatorProvider;
    public final Provider qSContainerImplControllerProvider;
    public final Provider qSCustomizerControllerProvider;
    public final Provider qSFooterViewControllerProvider;
    public final Provider qSPanelControllerProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl qSSceneComponentImpl;
    public final Provider qSSquishinessControllerProvider;
    public final Provider quickQSPanelControllerProvider;
    public final Provider quickStatusBarHeaderControllerProvider;
    public final View rootView;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final Provider tileAdapterProvider;
    public final Provider tileQueryHelperProvider;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, View view) {
        this(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, view, 1);
        this.$r8$classId = 1;
    }

    public DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, View view, int i) {
        this.$r8$classId = i;
        if (i != 1) {
            this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
            this.rootView = view;
            this.providesQSCutomizerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 2, 12);
            this.tileQueryHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 3, 12);
            this.tileAdapterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 4, 12);
            this.qSCustomizerControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 1, 12);
            this.factoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 5, 12);
            this.qSPanelControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 0, 12);
            this.providesQSUsingCollapsedLandscapeMediaProvider = new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 7, 12);
            this.quickQSPanelControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 6, 12);
            this.qSAnimatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 8, 12);
            this.quickStatusBarHeaderControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 10, 12);
            this.qSContainerImplControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 9, 12);
            this.qSFooterViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 12, 12);
            this.providesQSFooterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 11, 12);
            this.qSSquishinessControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 13, 12);
            return;
        }
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.rootView = view;
        this.providesQSCutomizerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 2, 11);
        this.tileQueryHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 3, 11);
        this.tileAdapterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 4, 11);
        this.qSCustomizerControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 1, 11);
        this.factoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 5, 11);
        this.qSPanelControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 0, 11);
        this.providesQSUsingCollapsedLandscapeMediaProvider = new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 7, 11);
        this.quickQSPanelControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 6, 11);
        this.qSAnimatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 8, 11);
        this.quickStatusBarHeaderControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 10, 11);
        this.qSContainerImplControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 9, 11);
        this.qSFooterViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 12, 11);
        this.providesQSFooterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 11, 11);
        this.qSSquishinessControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 13, 11);
    }
}
