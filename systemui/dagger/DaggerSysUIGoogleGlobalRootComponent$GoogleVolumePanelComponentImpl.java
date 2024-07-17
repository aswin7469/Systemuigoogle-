package com.google.android.systemui.dagger;

import com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import dagger.internal.SingleCheck;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl {
    public final Provider alwaysAvailableCriteriaProvider;
    public final Provider ancAvailabilityCriteriaProvider;
    public final Provider ancSliceInteractorProvider;
    public final Provider ancViewModelProvider;
    public final Provider audioVolumeComponentViewModelProvider;
    public final Provider bottomBarComponentProvider;
    public final Provider bottomBarViewModelProvider;
    public final Provider captioningAvailabilityCriteriaProvider;
    public final Provider captioningViewModelProvider;
    public final Provider castVolumeInteractorProvider;
    public final Provider clearCallingInteractorProvider;
    public final Provider clearCallingRepositoryProvider;
    public final Provider clearCallingViewModelProvider;
    public final Provider componentsFactoryProvider;
    public final Provider componentsInteractorImplProvider;
    public final Provider defaultComponentsLayoutManagerProvider;
    public final Provider factoryProvider;
    public final Provider factoryProvider2;
    public final Provider googleComponentAvailabilityCriteriaProvider;
    public final Provider mediaOutputActionsInteractorProvider;
    public final Provider mediaOutputAvailabilityCriteriaProvider;
    public final Provider mediaOutputComponentProvider;
    public final Provider mediaOutputInteractorProvider;
    public final Provider mediaOutputViewModelProvider;
    public final Provider provideBottomBarKeyProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider provideClearCallingComponentProvider;
    public final Provider provideCoroutineScopeProvider;
    public final Provider provideEnabledComponentsProvider;
    public final Provider provideFooterComponentsProvider;
    public final Provider provideHeaderComponentsProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider provideVolumePanelUiComponentProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider provideVolumePanelUiComponentProvider2;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;
    public final VolumePanelViewModel viewModel;
    public final Provider volumeSliderInteractorProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider volumeSlidersComponentProvider;

    public DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, VolumePanelViewModel volumePanelViewModel) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.viewModel = volumePanelViewModel;
        this.provideCoroutineScopeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 0);
        this.provideEnabledComponentsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 2);
        this.alwaysAvailableCriteriaProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 3);
        this.ancSliceInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 5);
        this.ancAvailabilityCriteriaProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 4);
        this.captioningAvailabilityCriteriaProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 6);
        this.mediaOutputAvailabilityCriteriaProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 7);
        this.googleComponentAvailabilityCriteriaProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 8);
        this.componentsInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 1);
        this.ancViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 11);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.provideVolumePanelUiComponentProvider = new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 10, 3);
        this.captioningViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 13);
        this.provideVolumePanelUiComponentProvider2 = new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 12, 3);
        this.clearCallingRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 17);
        this.clearCallingInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 16);
        this.clearCallingViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 15);
        this.provideClearCallingComponentProvider = new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 14, 3);
        this.bottomBarViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 19);
        this.bottomBarComponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 18);
        this.castVolumeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 22);
        this.volumeSliderInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 24);
        this.factoryProvider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 23, 3));
        this.mediaOutputInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 26);
        this.factoryProvider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 25, 3));
        this.audioVolumeComponentViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 21);
        this.volumeSlidersComponentProvider = new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 20, 3);
        this.mediaOutputActionsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 29);
        this.mediaOutputViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 28);
        this.mediaOutputComponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 27);
        this.componentsFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 9);
        this.provideBottomBarKeyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 31);
        this.provideHeaderComponentsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 32);
        this.provideFooterComponentsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 33);
        this.defaultComponentsLayoutManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 30);
    }
}
