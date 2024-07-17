package com.google.android.systemui.dagger;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import com.android.keyguard.ClockEventController;
import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.communal.domain.interactor.CommunalTutorialInteractor;
import com.android.systemui.communal.ui.viewmodel.CommunalTutorialIndicatorViewModel;
import com.android.systemui.keyguard.domain.interactor.KeyguardBottomAreaInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBottomAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardPreviewClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardPreviewSmartspaceViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OccludingAppDeviceEntryMessageViewModel;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shared.clocks.ClockRegistry;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final KeyguardPreviewRenderer create(Bundle bundle) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Context context = (Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideApplicationContextProvider.get();
        KeyguardPreviewClockViewModel keyguardPreviewClockViewModel = new KeyguardPreviewClockViewModel((KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockInteractorProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.applicationScopeProvider.get());
        KeyguardPreviewSmartspaceViewModel keyguardPreviewSmartspaceViewModel = new KeyguardPreviewSmartspaceViewModel((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockInteractorProvider.get());
        KeyguardBottomAreaViewModel keyguardBottomAreaViewModel = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBottomAreaViewModel();
        KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardQuickAffordancesCombinedViewModel();
        ConfigurationState configurationState = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationState();
        ClockEventController r14 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1467$$Nest$mclockEventController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl);
        ClockRegistry clockRegistry = (ClockRegistry) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClockRegistryProvider.get();
        CommunalTutorialIndicatorViewModel communalTutorialIndicatorViewModel = new CommunalTutorialIndicatorViewModel((CommunalTutorialInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalTutorialInteractorProvider.get(), (KeyguardBottomAreaInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBottomAreaInteractorProvider.get());
        return new KeyguardPreviewRenderer((Context) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.mainDispatcherProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), keyguardPreviewClockViewModel, keyguardPreviewSmartspaceViewModel, keyguardBottomAreaViewModel, keyguardQuickAffordancesCombinedViewModel, (DisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDisplayManagerProvider.get(), (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), configurationState, r14, clockRegistry, (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), (LockscreenSmartspaceController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenSmartspaceControllerProvider.get(), (UdfpsOverlayInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.udfpsOverlayInteractorProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get(), (KeyguardIndicationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardIndicationControllerGoogleProvider.get(), (KeyguardRootViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRootViewModelProvider.get(), bundle, (OccludingAppDeviceEntryMessageViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.occludingAppDeviceEntryMessageViewModelProvider.get(), (ChipbarCoordinator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.chipbarCoordinatorProvider.get(), (ScreenOffAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenOffAnimationControllerProvider.get(), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get(), (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get(), communalTutorialIndicatorViewModel, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1489$$Nest$mdefaultShortcutsSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl));
    }
}
