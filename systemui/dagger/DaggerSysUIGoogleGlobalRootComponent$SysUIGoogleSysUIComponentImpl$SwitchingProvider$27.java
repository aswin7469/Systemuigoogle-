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
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel;
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

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$27 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$27(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final KeyguardPreviewRenderer create(Bundle bundle) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        Object obj = switchingProvider.wMComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj;
        Context context = (Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get();
        KeyguardPreviewClockViewModel keyguardPreviewClockViewModel = new KeyguardPreviewClockViewModel((KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockInteractorProvider.get());
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj;
        KeyguardPreviewSmartspaceViewModel keyguardPreviewSmartspaceViewModel = new KeyguardPreviewSmartspaceViewModel((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardClockInteractorProvider.get());
        KeyguardBottomAreaViewModel keyguardBottomAreaViewModel = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).keyguardBottomAreaViewModel();
        KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).keyguardQuickAffordancesCombinedViewModel();
        ConfigurationState configurationState = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).configurationState();
        ClockEventController r15 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1412$$Nest$mclockEventController((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj);
        KeyguardBlueprintViewModel keyguardBlueprintViewModel = new KeyguardBlueprintViewModel((KeyguardBlueprintInteractor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).keyguardBlueprintInteractorProvider.get());
        KeyguardBlueprintViewModel keyguardBlueprintViewModel2 = keyguardBlueprintViewModel;
        return new KeyguardPreviewRenderer((Context) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (CoroutineScope) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).applicationScopeProvider.get(), (CoroutineDispatcher) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).mainDispatcherProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (CoroutineDispatcher) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).bgDispatcherProvider.get(), keyguardPreviewClockViewModel, keyguardPreviewSmartspaceViewModel, keyguardBottomAreaViewModel, keyguardQuickAffordancesCombinedViewModel, (DisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDisplayManagerProvider.get(), (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), configurationState, r15, (ClockRegistry) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).getClockRegistryProvider.get(), (BroadcastDispatcher) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).broadcastDispatcherProvider.get(), (LockscreenSmartspaceController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).lockscreenSmartspaceControllerProvider.get(), (UdfpsOverlayInteractor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).udfpsOverlayInteractorProvider.get(), (FeatureFlagsClassic) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).featureFlagsClassicReleaseProvider.get(), (FalsingManager) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).falsingManagerProxyProvider.get(), (VibratorHelper) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).vibratorHelperProvider.get(), (KeyguardIndicationController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).keyguardIndicationControllerGoogleProvider.get(), (KeyguardRootViewModel) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).keyguardRootViewModelProvider.get(), bundle, keyguardBlueprintViewModel2, (OccludingAppDeviceEntryMessageViewModel) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).occludingAppDeviceEntryMessageViewModelProvider.get(), (ChipbarCoordinator) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).chipbarCoordinatorProvider.get(), (ScreenOffAnimationController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).screenOffAnimationControllerProvider.get(), (ShadeInteractor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).shadeInteractorImplProvider.get(), (SecureSettings) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) obj).secureSettingsImplProvider.get());
    }
}
