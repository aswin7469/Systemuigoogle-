package com.google.android.systemui.dagger;

import android.app.IActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import android.media.AudioManager;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.IWindowManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.lifecycle.LifecycleOwner;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.AdminSecondaryLockScreenController;
import com.android.keyguard.KeyguardInputViewController;
import com.android.keyguard.KeyguardSecurityContainer;
import com.android.keyguard.KeyguardSecurityContainerController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardSecurityViewFlipper;
import com.android.keyguard.KeyguardSecurityViewFlipperController;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.LiftToActivateListener;
import com.android.keyguard.domain.interactor.KeyguardKeyboardInteractor;
import com.android.launcher3.icons.IconFactory;
import com.android.systemui.battery.BatteryMeterView;
import com.android.systemui.biometrics.FaceAuthAccessibilityDelegate;
import com.android.systemui.biometrics.SideFpsController;
import com.android.systemui.biometrics.SideFpsControllerKt;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor;
import com.android.systemui.classifier.FalsingA11yDelegate;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.complication.ComplicationHostViewController;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.mediaprojection.MediaProjectionMetricsLogger;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorActivity;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorController;
import com.android.systemui.mediaprojection.appselector.MediaProjectionBlockerEmptyStateProvider;
import com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerLabelLoader;
import com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerThumbnailLoader;
import com.android.systemui.mediaprojection.appselector.data.IconLoaderLibAppIconLoader;
import com.android.systemui.mediaprojection.appselector.data.ShellRecentTaskListProvider;
import com.android.systemui.mediaprojection.appselector.view.MediaProjectionRecentsViewController;
import com.android.systemui.mediaprojection.appselector.view.TaskPreviewSizeProvider;
import com.android.systemui.mediaprojection.appselector.view.WindowMetricsProviderImpl;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavBarButtonClickLogger;
import com.android.systemui.navigationbar.NavBarHelper;
import com.android.systemui.navigationbar.NavbarOrientationTrackingLogger;
import com.android.systemui.navigationbar.NavigationBar;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationBarFrame;
import com.android.systemui.navigationbar.NavigationBarTransitions;
import com.android.systemui.navigationbar.NavigationBarView;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.buttons.DeadZone;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSAnimator;
import com.android.systemui.qs.QSContainerImpl;
import com.android.systemui.qs.QSContainerImplController;
import com.android.systemui.qs.QSExpansionPathInterpolator;
import com.android.systemui.qs.QSFooterView;
import com.android.systemui.qs.QSFooterViewController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSPanel;
import com.android.systemui.qs.QSPanelController;
import com.android.systemui.qs.QSPanelController_Factory;
import com.android.systemui.qs.QSSquishinessController;
import com.android.systemui.qs.QSTileRevealController;
import com.android.systemui.qs.QuickQSPanel;
import com.android.systemui.qs.QuickQSPanelController;
import com.android.systemui.qs.QuickStatusBarHeaderController;
import com.android.systemui.qs.customize.QSCustomizer;
import com.android.systemui.qs.customize.QSCustomizerController;
import com.android.systemui.qs.customize.TileAdapter;
import com.android.systemui.qs.customize.TileQueryHelper;
import com.android.systemui.qs.dagger.QSScopeModule_ProvidesQuickStatusBarHeaderFactory;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.recents.Recents;
import com.android.systemui.retail.domain.interactor.RetailModeInteractorImpl;
import com.android.systemui.scene.shared.flag.SceneContainerFlags;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeLogger;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.PackageManagerWrapper;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.HeadsUpStatusBarView;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.data.repository.StatusBarModeRepositoryImpl;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.collection.NotifLiveDataStoreImpl;
import com.android.systemui.statusbar.notification.data.repository.HeadsUpNotificationIconViewStateRepository;
import com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationIconInteractor;
import com.android.systemui.statusbar.notification.stack.NotificationRoundnessManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.AutoHideController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceController;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.LegacyLightsOutNotifController;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.PhoneStatusBarTransitions;
import com.android.systemui.statusbar.phone.PhoneStatusBarViewController;
import com.android.systemui.statusbar.phone.StatusBarBoundsProvider;
import com.android.systemui.statusbar.phone.StatusBarDemoMode;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.statusbar.window.StatusBarWindowController;
import com.android.systemui.statusbar.window.StatusBarWindowStateController;
import com.android.systemui.touch.TouchInsetManager;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.user.ui.viewmodel.StatusBarUserChipViewModel;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.Utils;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.systemui.util.view.ViewUtil;
import dagger.Lazy;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl {
    public final /* synthetic */ int $r8$classId;
    public final Object dozeAuthRemoverProvider;
    public final Object dozeDockHandlerProvider;
    public final Provider dozeFalsingManagerAdapterProvider;
    public final Object dozeMachineProvider;
    public final Object dozeMachineService;
    public final Provider dozePauserProvider;
    public final Provider dozeScreenBrightnessProvider;
    public final Provider dozeScreenStateProvider;
    public final Object dozeSuppressorProvider;
    public final Provider dozeTriggersProvider;
    public final Provider dozeUiProvider;
    public final Provider dozeWallpaperStateProvider;
    public final Provider providesDozeWakeLockProvider;
    public final Provider providesWrappedServiceProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final Object sysUIGoogleSysUIComponentImpl;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class SwitchingProvider implements Provider {
        public final /* synthetic */ int $r8$classId;
        public final Object dozeComponentImpl;
        public final int id;
        public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
        public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

        public /* synthetic */ SwitchingProvider(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, Object obj, int i, int i2) {
            this.$r8$classId = i2;
            this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
            this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
            this.dozeComponentImpl = obj;
            this.id = i;
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl$SwitchingProvider() {
            FingerprintSensorPropertiesInternal fingerprintSensorPropertiesInternal;
            Optional optional;
            DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl) this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            if (i == 0) {
                FalsingManager falsingManager = (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get();
                UserSwitcherController userSwitcherController = (UserSwitcherController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userSwitcherControllerProvider.get();
                GlobalSettingsImpl globalSettingsImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.globalSettingsImpl();
                FalsingA11yDelegate falsingA11yDelegate = new FalsingA11yDelegate((FalsingCollector) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.sysUIGoogleSysUIComponentImpl.providesFalsingCollectorLegacyProvider.get());
                Provider provider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.javaAdapterProvider;
                Lazy lazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerInteractorProvider);
                DelegateFactory delegateFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryInteractorProvider;
                FalsingA11yDelegate falsingA11yDelegate2 = falsingA11yDelegate;
                return new KeyguardSecurityContainerController((KeyguardSecurityContainer) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.providesKeyguardSecurityContainerProvider.get(), (AdminSecondaryLockScreenController.Factory) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.factoryProvider.get(), (LockPatternUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideLockPatternUtilsProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (KeyguardSecurityModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardSecurityModelProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (KeyguardSecurityViewFlipperController) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.keyguardSecurityViewFlipperControllerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (FalsingCollector) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesFalsingCollectorLegacyProvider.get(), falsingManager, userSwitcherController, (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get(), globalSettingsImpl, (SessionTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sessionTrackerProvider.get(), (Optional) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.providesOptionalSidefpsControllerProvider.get(), falsingA11yDelegate2, (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideTelephonyManagerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1697$$Nest$mviewMediatorCallback(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (AudioManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAudioManagerProvider.get(), (DeviceEntryFaceAuthInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUIDeviceEntryFaceAuthInteractorProvider.get(), (BouncerMessageInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bouncerMessageInteractorProvider.get(), provider, (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get(), (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get(), (FaceAuthAccessibilityDelegate) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.faceAuthAccessibilityDelegateProvider.get(), (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get(), lazy, delegateFactory);
            } else if (i == 1) {
                ViewGroup viewGroup = daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.bouncerContainer;
                KeyguardSecurityContainer keyguardSecurityContainer = (KeyguardSecurityContainer) ((LayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providerLayoutInflaterProvider.get()).inflate(2131558669, viewGroup, false);
                viewGroup.addView(keyguardSecurityContainer);
                Preconditions.checkNotNullFromProvides(keyguardSecurityContainer);
                return keyguardSecurityContainer;
            } else if (i == 2) {
                return new AdminSecondaryLockScreenController.Factory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (KeyguardSecurityContainer) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.providesKeyguardSecurityContainerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get());
            } else {
                if (i == 3) {
                    LayoutInflater layoutInflater = (LayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providerLayoutInflaterProvider.get();
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.sysUIGoogleSysUIComponentImpl;
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.sysUIGoogleGlobalRootComponentImpl;
                    Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.context.getResources();
                    Preconditions.checkNotNullFromProvides(resources);
                    KeyguardInputViewController.Factory factory = new KeyguardInputViewController.Factory((KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardUpdateMonitorProvider.get(), (LockPatternUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideLockPatternUtilsProvider.get(), (LatencyTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideLatencyTrackerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1541$$Nest$mkeyguardMessageAreaControllerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2), (InputMethodManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideInputMethodManagerProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideMainDelayableExecutorProvider.get(), resources, new LiftToActivateListener((AccessibilityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideAccessibilityManagerProvider.get()), (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideTelephonyManagerProvider.get(), (FalsingCollector) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.providesFalsingCollectorLegacyProvider.get(), daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.emergencyButtonControllerFactory(), (DevicePostureController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.devicePostureControllerImplProvider.get(), (StatusBarKeyguardViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.statusBarKeyguardViewManagerProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.featureFlagsClassicReleaseProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.selectedUserInteractorProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideUiEventLoggerProvider.get(), (KeyguardKeyboardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardKeyboardInteractorProvider.get());
                    daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.emergencyButtonControllerFactory();
                    return new KeyguardSecurityViewFlipperController((KeyguardSecurityViewFlipper) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.providesKeyguardSecurityViewFlipperProvider.get(), (AsyncLayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAsyncLayoutInflaterProvider.get(), factory, (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get());
                } else if (i == 4) {
                    KeyguardSecurityViewFlipper keyguardSecurityViewFlipper = (KeyguardSecurityViewFlipper) ((KeyguardSecurityContainer) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.providesKeyguardSecurityContainerProvider.get()).findViewById(2131364033);
                    Preconditions.checkNotNullFromProvides(keyguardSecurityViewFlipper);
                    return keyguardSecurityViewFlipper;
                } else if (i == 5) {
                    FingerprintManager fingerprintManager = (FingerprintManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesFingerprintManagerProvider.get();
                    Provider provider2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sideFpsControllerProvider;
                    if (fingerprintManager != null) {
                        fingerprintSensorPropertiesInternal = SideFpsControllerKt.getSideFpsSensorProperties(fingerprintManager);
                    } else {
                        fingerprintSensorPropertiesInternal = null;
                    }
                    if (fingerprintSensorPropertiesInternal != null) {
                        optional = Optional.of((SideFpsController) provider2.get());
                    } else {
                        optional = Optional.empty();
                    }
                    Preconditions.checkNotNullFromProvides(optional);
                    return optional;
                } else {
                    throw new AssertionError(i);
                }
            }
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl) this.dozeComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    int intValue = daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.hostUid.intValue();
                    return new MediaProjectionAppSelectorController((ShellRecentTaskListProvider) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.bindRecentTaskListProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.view, (ScreenCaptureDevicePolicyResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenCaptureDevicePolicyResolverProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.hostUserHandle, (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.provideCoroutineScopeProvider.get(), (ComponentName) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.provideAppSelectorComponentNameProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.callingPackage, (ActivityTaskManagerThumbnailLoader) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.bindRecentTaskThumbnailLoaderProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.isFirstStart.booleanValue(), (MediaProjectionMetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaProjectionMetricsLoggerProvider.get(), intValue);
                case 1:
                    return new ShellRecentTaskListProvider((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setRecentTasks, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
                case 2:
                    return CoroutineScopeKt.CoroutineScope(((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get()).getCoroutineContext().plus(SupervisorKt.SupervisorJob$default()));
                case 3:
                    return new ComponentName(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, MediaProjectionAppSelectorActivity.class);
                case 4:
                    return new ActivityTaskManagerThumbnailLoader((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (ActivityManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideActivityManagerWrapperProvider.get());
                case 5:
                    return new MediaProjectionRecentsViewController((DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.factoryProvider2.get(), (TaskPreviewSizeProvider) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.taskPreviewSizeProvider.get(), (IActivityTaskManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIActivityTaskManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.resultHandler);
                case 6:
                    return new DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1(this);
                case ViewNode.WIDTH_FIELD_NUMBER:
                    return new DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2(this);
                case 8:
                    return new IconLoaderLibAppIconLoader((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (PackageManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerWrapperProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.bindIconFactoryProvider);
                case ViewNode.SCROLLX_FIELD_NUMBER:
                    return IconFactory.obtain(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                case ViewNode.SCROLLY_FIELD_NUMBER:
                    return new ActivityTaskManagerLabelLoader((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get());
                case ViewNode.TRANSLATIONX_FIELD_NUMBER:
                    return new TaskPreviewSizeProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, new WindowMetricsProviderImpl((WindowManager) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get()), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get());
                case ViewNode.TRANSLATIONY_FIELD_NUMBER:
                    return new MediaProjectionBlockerEmptyStateProvider(daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.hostUserHandle, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1617$$Nest$mpersonalProfileUserHandle(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (ScreenCaptureDevicePolicyResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenCaptureDevicePolicyResolverProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                default:
                    throw new AssertionError(i);
            }
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl) this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            if (i == 0) {
                Lazy lazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistManagerGoogleProvider);
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.sysUIGoogleSysUIComponentImpl;
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.sysUIGoogleGlobalRootComponentImpl;
                LightBarController.Factory factory = new LightBarController.Factory((JavaAdapter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.javaAdapterProvider.get(), (DarkIconDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.darkIconDispatcherImplProvider.get(), (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideBatteryControllerProvider.get(), (NavigationModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.navigationModeControllerProvider.get(), (StatusBarModeRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.statusBarModeRepositoryImplProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.dumpManagerProvider.get(), (DisplayTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideDisplayTrackerProvider.get());
                AutoHideController.Factory factory2 = new AutoHideController.Factory((Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideMainHandlerProvider.get(), (IWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideIWindowManagerProvider.get());
                DeadZone deadZone = new DeadZone((NavigationBarView) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideNavigationBarviewProvider.get());
                NavBarButtonClickLogger navBarButtonClickLogger = new NavBarButtonClickLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideNavBarButtonClickLogBufferProvider.get());
                NavbarOrientationTrackingLogger navbarOrientationTrackingLogger = new NavbarOrientationTrackingLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideNavbarOrientationTrackingLogBufferProvider.get());
                NavbarOrientationTrackingLogger navbarOrientationTrackingLogger2 = navbarOrientationTrackingLogger;
                AutoHideController.Factory factory3 = factory2;
                return new NavigationBar((NavigationBarView) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideNavigationBarviewProvider.get(), (NavigationBarFrame) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideNavigationBarFrameProvider.get(), daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.savedState, daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.context, (WindowManager) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideWindowManagerProvider.get(), lazy, (AccessibilityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAccessibilityManagerProvider.get(), (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (OverviewProxyService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overviewProxyServiceProvider.get(), (NavigationModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationModeControllerProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (StatusBarKeyguardViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarKeyguardViewManagerProvider.get(), (SysUiState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSysUiStateProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setPip, Optional.of((Recents) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideRecentsProvider.get()), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.optionalOfCentralSurfacesProvider), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (ShadeViewController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationPanelViewControllerProvider.get(), (NotificationRemoteInputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationRemoteInputManagerProvider.get(), (NotificationShadeDepthController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationShadeDepthControllerProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (NavBarHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navBarHelperProvider.get(), (LightBarController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lightBarControllerProvider.get(), factory, (AutoHideController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.autoHideControllerProvider.get(), factory3, (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideOptionalTelecomManagerProvider.get(), (InputMethodManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInputMethodManagerProvider.get(), deadZone, (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get(), (NavigationBarTransitions) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.navigationBarTransitionsProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setBackAnimation, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (WakefulnessLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wakefulnessLifecycleProvider.get(), (TaskStackChangeListeners) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideTaskStackChangeListenersProvider.get(), (DisplayTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDisplayTrackerProvider.get(), navBarButtonClickLogger, navbarOrientationTrackingLogger2);
            } else if (i == 1) {
                NavigationBarView navigationBarView = (NavigationBarView) ((LayoutInflater) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideLayoutInflaterProvider.get()).inflate(2131558832, (NavigationBarFrame) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideNavigationBarFrameProvider.get()).findViewById(2131363183);
                Preconditions.checkNotNullFromProvides(navigationBarView);
                return navigationBarView;
            } else if (i == 2) {
                LayoutInflater from = LayoutInflater.from(daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.context);
                Preconditions.checkNotNullFromProvides(from);
                return from;
            } else if (i == 3) {
                NavigationBarFrame navigationBarFrame = (NavigationBarFrame) ((LayoutInflater) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideLayoutInflaterProvider.get()).inflate(2131558833, (ViewGroup) null);
                Preconditions.checkNotNullFromProvides(navigationBarFrame);
                return navigationBarFrame;
            } else if (i == 4) {
                WindowManager windowManager = (WindowManager) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.context.getSystemService(WindowManager.class);
                Preconditions.checkNotNullFromProvides(windowManager);
                return windowManager;
            } else if (i == 5) {
                return new NavigationBarTransitions((NavigationBarView) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideNavigationBarviewProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider5.get(), (DisplayTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDisplayTrackerProvider.get());
            } else {
                throw new AssertionError(i);
            }
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$QSFragmentComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl) this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    return QSPanelController_Factory.newInstance((QSPanel) ((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).requireViewById(2131363396), (TunerService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider.get(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeFalsingManagerAdapterProvider.get(), Utils.useQsMediaPlayer(daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.sysUIGoogleGlobalRootComponentImpl.context), (MediaHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesQSMediaHostProvider.get(), daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeTriggersProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSLogger(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$25) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider30.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1457$$Nest$mbrightnessSliderControllerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (StatusBarKeyguardViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarKeyguardViewManagerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get(), (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get());
                case 1:
                    return new QSCustomizerController((QSCustomizer) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesWrappedServiceProvider.get(), (TileQueryHelper) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesDozeWakeLockProvider.get(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (TileAdapter) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozePauserProvider.get(), (ScreenLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.screenLifecycleProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (LightBarController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lightBarControllerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get());
                case 2:
                    return (QSCustomizer) ((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).requireViewById(2131363381);
                case 3:
                    return new TileQueryHelper(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get());
                case 4:
                    daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.getClass();
                    return new TileAdapter(((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).getContext(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get());
                case 5:
                    return new QSTileRevealController.Factory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeFalsingManagerAdapterProvider.get());
                case 6:
                    return new QuickQSPanelController((QuickQSPanel) QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).requireViewById(2131363394), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeFalsingManagerAdapterProvider.get(), Utils.useQsMediaPlayer(daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.sysUIGoogleGlobalRootComponentImpl.context), (MediaHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesQuickQSMediaHostProvider.get(), (SwitchingProvider) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.sysUIGoogleSysUIComponentImpl, (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSLogger(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get());
                case ViewNode.WIDTH_FIELD_NUMBER:
                    return Boolean.valueOf(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources().getBoolean(2131034166));
                case 8:
                    View view = (View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService;
                    TunerService tunerService = (TunerService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider.get();
                    return new QSAnimator(view, (QuickQSPanel) QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(view).requireViewById(2131363394), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeUiProvider.get(), (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenBrightnessProvider.get(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (QSExpansionPathInterpolator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.qSExpansionPathInterpolatorProvider.get());
                case ViewNode.SCROLLX_FIELD_NUMBER:
                    return new QSContainerImplController((QSContainerImpl) ((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).requireViewById(2131363395), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeUiProvider.get(), (QuickStatusBarHeaderController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeWallpaperStateProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get());
                case ViewNode.SCROLLY_FIELD_NUMBER:
                    return new QuickStatusBarHeaderController(QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService), (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenBrightnessProvider.get(), (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get());
                case ViewNode.TRANSLATIONX_FIELD_NUMBER:
                    QSFooterViewController qSFooterViewController = (QSFooterViewController) ((Provider) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeAuthRemoverProvider).get();
                    qSFooterViewController.init$10();
                    return qSFooterViewController;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER:
                    return new QSFooterViewController((QSFooterView) ((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).requireViewById(2131363383), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeUiProvider.get(), (RetailModeInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.retailModeInteractorImplProvider.get());
                case ViewNode.SCALEX_FIELD_NUMBER:
                    return new QSSquishinessController((QSAnimator) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenStateProvider.get(), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeUiProvider.get(), (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenBrightnessProvider.get());
                default:
                    throw new AssertionError(i);
            }
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl) this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    Context context = daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
                    return QSPanelController_Factory.newInstance((QSPanel) ((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).requireViewById(2131363396), (TunerService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider.get(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeFalsingManagerAdapterProvider.get(), false, (MediaHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesQSMediaHostProvider.get(), daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeTriggersProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSLogger(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$25) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider30.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1457$$Nest$mbrightnessSliderControllerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (StatusBarKeyguardViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarKeyguardViewManagerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get(), (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get());
                case 1:
                    return new QSCustomizerController((QSCustomizer) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesWrappedServiceProvider.get(), (TileQueryHelper) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesDozeWakeLockProvider.get(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (TileAdapter) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozePauserProvider.get(), (ScreenLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.screenLifecycleProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (LightBarController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lightBarControllerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get());
                case 2:
                    return (QSCustomizer) ((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).requireViewById(2131363381);
                case 3:
                    return new TileQueryHelper(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get());
                case 4:
                    daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.getClass();
                    return new TileAdapter(((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).getContext(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get());
                case 5:
                    return new QSTileRevealController.Factory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeFalsingManagerAdapterProvider.get());
                case 6:
                    Context context2 = daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
                    return new QuickQSPanelController((QuickQSPanel) QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).requireViewById(2131363394), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeFalsingManagerAdapterProvider.get(), false, (MediaHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesQuickQSMediaHostProvider.get(), (SwitchingProvider) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.sysUIGoogleSysUIComponentImpl, (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSLogger(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get());
                case ViewNode.WIDTH_FIELD_NUMBER:
                    Context context3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
                    return Boolean.FALSE;
                case 8:
                    View view = (View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService;
                    TunerService tunerService = (TunerService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider.get();
                    return new QSAnimator(view, (QuickQSPanel) QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(view).requireViewById(2131363394), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeUiProvider.get(), (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenBrightnessProvider.get(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (QSExpansionPathInterpolator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.qSExpansionPathInterpolatorProvider.get());
                case ViewNode.SCROLLX_FIELD_NUMBER:
                    return new QSContainerImplController((QSContainerImpl) ((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).requireViewById(2131363395), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeUiProvider.get(), (QuickStatusBarHeaderController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeWallpaperStateProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get());
                case ViewNode.SCROLLY_FIELD_NUMBER:
                    return new QuickStatusBarHeaderController(QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService), (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenBrightnessProvider.get(), (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get());
                case ViewNode.TRANSLATIONX_FIELD_NUMBER:
                    QSFooterViewController qSFooterViewController = (QSFooterViewController) ((Provider) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeAuthRemoverProvider).get();
                    qSFooterViewController.init$10();
                    return qSFooterViewController;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER:
                    return new QSFooterViewController((QSFooterView) ((View) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService).requireViewById(2131363383), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeUiProvider.get(), (RetailModeInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.retailModeInteractorImplProvider.get());
                case ViewNode.SCALEX_FIELD_NUMBER:
                    return new QSSquishinessController((QSAnimator) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenStateProvider.get(), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeUiProvider.get(), (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenBrightnessProvider.get());
                default:
                    throw new AssertionError(i);
            }
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) this.dozeComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    BatteryMeterView batteryMeterView = (BatteryMeterView) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(2131362068);
                    Preconditions.checkNotNullFromProvides(batteryMeterView);
                    return batteryMeterView;
                case 1:
                    return StatusBarLocation.HOME;
                case 2:
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.sysUIGoogleSysUIComponentImpl;
                    Optional optional = (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideSysUIUnfoldComponentProvider.get();
                    StatusBarUserChipViewModel statusBarUserChipViewModel = new StatusBarUserChipViewModel((UserSwitcherInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.userSwitcherInteractorProvider.get());
                    CentralSurfaces centralSurfaces = (CentralSurfaces) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.centralSurfacesGoogleProvider.get();
                    StatusBarWindowStateController statusBarWindowStateController = (StatusBarWindowStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.statusBarWindowStateControllerProvider.get();
                    ShadeController shadeController = (ShadeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideShadeControllerProvider.get();
                    ShadeViewController shadeViewController = (ShadeViewController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.notificationPanelViewControllerProvider.get();
                    DelegateFactory delegateFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.providesWindowRootViewProvider;
                    ShadeLogger shadeLogger = new ShadeLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideShadeLogBufferProvider.get());
                    ViewUtil viewUtil = (ViewUtil) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.viewUtilProvider.get();
                    StatusOverlayHoverListenerFactory r18 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1685$$Nest$mstatusOverlayHoverListenerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2);
                    UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
                    ((FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.featureFlagsClassicReleaseProvider.get()).getClass();
                    ScopedUnfoldTransitionProgressProvider scopedUnfoldTransitionProgressProvider = (ScopedUnfoldTransitionProgressProvider) ((Optional) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideStatusBarScopedTransitionProvider.get()).orElse((Object) null);
                    return new PhoneStatusBarViewController(daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView, scopedUnfoldTransitionProgressProvider, centralSurfaces, statusBarWindowStateController, shadeController, shadeViewController, delegateFactory, shadeLogger, statusBarUserChipViewModel, viewUtil, (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.implProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.configurationControllerImplProvider.get(), r18);
                case 3:
                    return new HeadsUpAppearanceController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconAreaController(), (HeadsUpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpManagerPhoneProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (PhoneStatusBarTransitions) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.providePhoneStatusBarTransitionsProvider.get(), (KeyguardBypassController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBypassControllerProvider.get(), (NotificationWakeUpCoordinator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationWakeUpCoordinatorProvider.get(), (DarkIconDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.darkIconDispatcherImplProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get(), (NotificationStackScrollLayoutController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationStackScrollLayoutControllerProvider.get(), (ShadeViewController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationPanelViewControllerProvider.get(), (NotificationRoundnessManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationRoundnessManagerProvider.get(), (HeadsUpStatusBarView) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.providesHeasdUpStatusBarViewProvider.get(), (Clock) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.provideClockProvider.get(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), new HeadsUpNotificationIconInteractor((HeadsUpNotificationIconViewStateRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpNotificationIconViewStateRepositoryProvider.get()), (Optional) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.provideOperatorFrameNameViewProvider.get());
                case 4:
                    return new PhoneStatusBarTransitions(daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView, ((StatusBarWindowController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarWindowControllerProvider.get()).mStatusBarWindowView.findViewById(2131363711));
                case 5:
                    HeadsUpStatusBarView headsUpStatusBarView = (HeadsUpStatusBarView) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(2131362691);
                    Preconditions.checkNotNullFromProvides(headsUpStatusBarView);
                    return headsUpStatusBarView;
                case 6:
                    Clock clock = (Clock) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(2131362278);
                    Preconditions.checkNotNullFromProvides(clock);
                    return clock;
                case ViewNode.WIDTH_FIELD_NUMBER:
                    Optional ofNullable = Optional.ofNullable(daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(2131363252));
                    Preconditions.checkNotNullFromProvides(ofNullable);
                    return ofNullable;
                case 8:
                    return new LegacyLightsOutNotifController((View) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.provideLightsOutNotifViewProvider.get(), (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), (NotifLiveDataStoreImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifLiveDataStoreImplProvider.get(), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get());
                case ViewNode.SCROLLX_FIELD_NUMBER:
                    View findViewById = daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(2131363220);
                    Preconditions.checkNotNullFromProvides(findViewById);
                    return findViewById;
                case ViewNode.SCROLLY_FIELD_NUMBER:
                    return new StatusBarDemoMode((Clock) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.provideClockProvider.get(), (View) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.provideOperatorNameViewProvider.get(), (DemoModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDemoModeControllerProvider.get(), (PhoneStatusBarTransitions) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.providePhoneStatusBarTransitionsProvider.get(), (NavigationBarControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationBarControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getDisplayId());
                case ViewNode.TRANSLATIONX_FIELD_NUMBER:
                    View inflate = ((ViewStub) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(2131363253)).inflate();
                    inflate.setVisibility(8);
                    return inflate;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER:
                    return new StatusBarBoundsProvider((View) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.startSideContentProvider.get(), (View) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.endSideContentProvider.get());
                case ViewNode.SCALEX_FIELD_NUMBER:
                    View findViewById2 = daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(2131363719);
                    Preconditions.checkNotNullFromProvides(findViewById2);
                    return findViewById2;
                case ViewNode.SCALEY_FIELD_NUMBER:
                    View findViewById3 = daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(2131363715);
                    Preconditions.checkNotNullFromProvides(findViewById3);
                    return findViewById3;
                default:
                    throw new AssertionError(i);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v31, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v48, resolved type: com.android.systemui.util.concurrency.ThreadFactoryImpl} */
        /* JADX WARNING: type inference failed for: r3v81, types: [com.android.systemui.statusbar.notification.domain.interactor.RenderNotificationListInteractor, java.lang.Object] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object get() {
            /*
                r40 = this;
                r0 = r40
                r1 = 5
                r2 = 0
                r3 = 0
                r4 = 6
                r5 = 1
                int r6 = r0.id
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r7 = r0.sysUIGoogleGlobalRootComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r8 = r0.sysUIGoogleSysUIComponentImpl
                java.lang.Object r9 = r0.dozeComponentImpl
                int r10 = r0.$r8$classId
                switch(r10) {
                    case 0: goto L_0x0cbc;
                    case 1: goto L_0x0761;
                    case 2: goto L_0x04fc;
                    case 3: goto L_0x01b0;
                    case 4: goto L_0x01ab;
                    case 5: goto L_0x01a6;
                    case 6: goto L_0x01a1;
                    case 7: goto L_0x019c;
                    case 8: goto L_0x0197;
                    case 9: goto L_0x0192;
                    default: goto L_0x0014;
                }
            L_0x0014:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl r9 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) r9
                switch(r6) {
                    case 0: goto L_0x0180;
                    case 1: goto L_0x0168;
                    case 2: goto L_0x0156;
                    case 3: goto L_0x011c;
                    case 4: goto L_0x00d8;
                    case 5: goto L_0x00d0;
                    case 6: goto L_0x0099;
                    case 7: goto L_0x0084;
                    case 8: goto L_0x005f;
                    case 9: goto L_0x001f;
                    default: goto L_0x0019;
                }
            L_0x0019:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r6)
                throw r0
            L_0x001f:
                com.android.systemui.unfold.UnfoldLatencyTracker r0 = new com.android.systemui.unfold.UnfoldLatencyTracker
                javax.inject.Provider r1 = r7.provideLatencyTrackerProvider
                java.lang.Object r1 = r1.get()
                r8 = r1
                com.android.internal.util.LatencyTracker r8 = (com.android.internal.util.LatencyTracker) r8
                javax.inject.Provider r1 = r7.provideDeviceStateManagerProvider
                java.lang.Object r1 = r1.get()
                r9 = r1
                android.hardware.devicestate.DeviceStateManager r9 = (android.hardware.devicestate.DeviceStateManager) r9
                javax.inject.Provider r1 = r7.unfoldTransitionProgressProvider
                java.lang.Object r1 = r1.get()
                r10 = r1
                java.util.Optional r10 = (java.util.Optional) r10
                javax.inject.Provider r1 = r7.provideUiBackgroundExecutorProvider
                java.lang.Object r1 = r1.get()
                r11 = r1
                java.util.concurrent.Executor r11 = (java.util.concurrent.Executor) r11
                javax.inject.Provider r1 = r7.provideContentResolverProvider
                java.lang.Object r1 = r1.get()
                r13 = r1
                android.content.ContentResolver r13 = (android.content.ContentResolver) r13
                javax.inject.Provider r1 = r7.screenLifecycleProvider
                java.lang.Object r1 = r1.get()
                r14 = r1
                com.android.systemui.keyguard.ScreenLifecycle r14 = (com.android.systemui.keyguard.ScreenLifecycle) r14
                android.content.Context r12 = r7.context
                r7 = r0
                r7.<init>(r8, r9, r10, r11, r12, r13, r14)
                goto L_0x0191
            L_0x005f:
                com.android.systemui.unfold.UnfoldHapticsPlayer r0 = new com.android.systemui.unfold.UnfoldHapticsPlayer
                dagger.internal.InstanceFactory r1 = r9.p1Provider
                java.lang.Object r1 = r1.instance
                com.android.systemui.unfold.UnfoldTransitionProgressProvider r1 = (com.android.systemui.unfold.UnfoldTransitionProgressProvider) r1
                javax.inject.Provider r2 = r7.deviceStateManagerFoldProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.updates.FoldProvider r2 = (com.android.systemui.unfold.updates.FoldProvider) r2
                javax.inject.Provider r3 = r7.provideMainExecutorProvider
                java.lang.Object r3 = r3.get()
                java.util.concurrent.Executor r3 = (java.util.concurrent.Executor) r3
                javax.inject.Provider r4 = r7.provideVibratorProvider
                java.lang.Object r4 = r4.get()
                android.os.Vibrator r4 = (android.os.Vibrator) r4
                r0.<init>(r1, r2, r3, r4)
                goto L_0x0191
            L_0x0084:
                com.android.systemui.unfold.UnfoldTransitionWallpaperController r0 = new com.android.systemui.unfold.UnfoldTransitionWallpaperController
                dagger.internal.InstanceFactory r1 = r9.p1Provider
                java.lang.Object r1 = r1.instance
                com.android.systemui.unfold.UnfoldTransitionProgressProvider r1 = (com.android.systemui.unfold.UnfoldTransitionProgressProvider) r1
                javax.inject.Provider r2 = r8.wallpaperControllerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.util.WallpaperController r2 = (com.android.systemui.util.WallpaperController) r2
                r0.<init>(r1, r2)
                goto L_0x0191
            L_0x0099:
                com.android.systemui.unfold.FoldLightRevealOverlayAnimation r0 = new com.android.systemui.unfold.FoldLightRevealOverlayAnimation
                android.content.Context r4 = r7.context
                javax.inject.Provider r1 = r7.unfoldBgProgressHandlerProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                android.os.Handler r5 = (android.os.Handler) r5
                com.android.systemui.display.data.repository.DeviceStateRepositoryImpl r6 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1499$$Nest$mdeviceStateRepositoryImpl(r8)
                dagger.internal.DelegateFactory r1 = r8.powerInteractorProvider
                java.lang.Object r1 = r1.get()
                r7 = r1
                com.android.systemui.power.domain.interactor.PowerInteractor r7 = (com.android.systemui.power.domain.interactor.PowerInteractor) r7
                javax.inject.Provider r1 = r8.bgApplicationScopeProvider
                java.lang.Object r1 = r1.get()
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                com.android.systemui.util.animation.data.repository.AnimationStatusRepositoryImpl r2 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1444$$Nest$manimationStatusRepositoryImpl(r8)
                javax.inject.Provider r3 = r9.factoryProvider
                java.lang.Object r3 = r3.get()
                r10 = r3
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1 r10 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1) r10
                r3 = r0
                r8 = r1
                r9 = r2
                r3.<init>(r4, r5, r6, r7, r8, r9, r10)
                goto L_0x0191
            L_0x00d0:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1 r1 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1
                r1.<init>(r0)
                r0 = r1
                goto L_0x0191
            L_0x00d8:
                com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation r0 = new com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation
                android.content.Context r3 = r7.context
                dagger.internal.DelegateFactory r1 = r8.featureFlagsClassicReleaseProvider
                java.lang.Object r1 = r1.get()
                r4 = r1
                com.android.systemui.flags.FeatureFlagsClassic r4 = (com.android.systemui.flags.FeatureFlagsClassic) r4
                javax.inject.Provider r1 = r7.provideContentResolverProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                android.content.ContentResolver r5 = (android.content.ContentResolver) r5
                javax.inject.Provider r1 = r7.unfoldBgProgressHandlerProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                android.os.Handler r6 = (android.os.Handler) r6
                dagger.internal.InstanceFactory r1 = r9.p4Provider
                dagger.internal.InstanceFactory r8 = r9.p1Provider
                javax.inject.Provider r2 = r7.provideDeviceStateManagerProvider
                java.lang.Object r2 = r2.get()
                r10 = r2
                android.hardware.devicestate.DeviceStateManager r10 = (android.hardware.devicestate.DeviceStateManager) r10
                com.android.systemui.util.concurrency.ThreadFactoryImpl r11 = new com.android.systemui.util.concurrency.ThreadFactoryImpl
                r11.<init>()
                javax.inject.Provider r2 = r9.factoryProvider
                java.lang.Object r2 = r2.get()
                r12 = r2
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1 r12 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1) r12
                r2 = r0
                r7 = r1
                r9 = r10
                r10 = r11
                r11 = r12
                r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
                goto L_0x0191
            L_0x011c:
                com.android.systemui.unfold.FoldAodAnimationController r0 = new com.android.systemui.unfold.FoldAodAnimationController
                javax.inject.Provider r1 = r7.provideMainDelayableExecutorProvider
                java.lang.Object r1 = r1.get()
                r14 = r1
                com.android.systemui.util.concurrency.DelayableExecutor r14 = (com.android.systemui.util.concurrency.DelayableExecutor) r14
                javax.inject.Provider r1 = r7.provideDeviceStateManagerProvider
                java.lang.Object r1 = r1.get()
                r16 = r1
                android.hardware.devicestate.DeviceStateManager r16 = (android.hardware.devicestate.DeviceStateManager) r16
                javax.inject.Provider r1 = r8.wakefulnessLifecycleProvider
                java.lang.Object r1 = r1.get()
                r17 = r1
                com.android.systemui.keyguard.WakefulnessLifecycle r17 = (com.android.systemui.keyguard.WakefulnessLifecycle) r17
                com.android.systemui.util.settings.GlobalSettingsImpl r18 = r8.globalSettingsImpl()
                javax.inject.Provider r1 = r7.provideLatencyTrackerProvider
                java.lang.Object r1 = r1.get()
                r19 = r1
                com.android.internal.util.LatencyTracker r19 = (com.android.internal.util.LatencyTracker) r19
                dagger.internal.DelegateFactory r1 = r8.keyguardInteractorProvider
                dagger.Lazy r20 = dagger.internal.DoubleCheck.lazy(r1)
                android.content.Context r15 = r7.context
                r13 = r0
                r13.<init>(r14, r15, r16, r17, r18, r19, r20)
                goto L_0x0191
            L_0x0156:
                com.android.systemui.shade.NotificationPanelUnfoldAnimationController r0 = new com.android.systemui.shade.NotificationPanelUnfoldAnimationController
                android.content.Context r1 = r7.context
                dagger.internal.DelegateFactory r2 = r8.statusBarStateControllerImplProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.plugins.statusbar.StatusBarStateController r2 = (com.android.systemui.plugins.statusbar.StatusBarStateController) r2
                com.android.systemui.unfold.util.NaturalRotationUnfoldProgressProvider r3 = r9.p2
                r0.<init>(r1, r2, r3)
                goto L_0x0191
            L_0x0168:
                com.android.systemui.statusbar.phone.StatusBarMoveFromCenterAnimationController r0 = new com.android.systemui.statusbar.phone.StatusBarMoveFromCenterAnimationController
                com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider r1 = r9.p3
                javax.inject.Provider r2 = r7.activityManagerActivityTypeProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider r2 = (com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider) r2
                javax.inject.Provider r3 = r7.provideWindowManagerProvider
                java.lang.Object r3 = r3.get()
                android.view.WindowManager r3 = (android.view.WindowManager) r3
                r0.<init>(r1, r2, r3)
                goto L_0x0191
            L_0x0180:
                com.android.keyguard.KeyguardUnfoldTransition r0 = new com.android.keyguard.KeyguardUnfoldTransition
                android.content.Context r1 = r7.context
                dagger.internal.DelegateFactory r2 = r8.statusBarStateControllerImplProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.plugins.statusbar.StatusBarStateController r2 = (com.android.systemui.plugins.statusbar.StatusBarStateController) r2
                com.android.systemui.unfold.util.NaturalRotationUnfoldProgressProvider r3 = r9.p2
                r0.<init>(r1, r2, r3)
            L_0x0191:
                return r0
            L_0x0192:
                java.lang.Object r0 = r40.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl$SwitchingProvider()
                return r0
            L_0x0197:
                java.lang.Object r0 = r40.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl$SwitchingProvider()
                return r0
            L_0x019c:
                java.lang.Object r0 = r40.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$QSFragmentComponentImpl$SwitchingProvider()
                return r0
            L_0x01a1:
                java.lang.Object r0 = r40.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl$SwitchingProvider()
                return r0
            L_0x01a6:
                java.lang.Object r0 = r40.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider()
                return r0
            L_0x01ab:
                java.lang.Object r0 = r40.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl$SwitchingProvider()
                return r0
            L_0x01b0:
                java.lang.String r1 = "volume_sliders"
                java.lang.String r2 = "clear_calling"
                java.lang.String r3 = "bottom_bar"
                java.lang.String r5 = "media_output"
                java.lang.String r10 = "captioning"
                java.lang.String r11 = "anc"
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl r9 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl) r9
                switch(r6) {
                    case 0: goto L_0x04e3;
                    case 1: goto L_0x04a1;
                    case 2: goto L_0x0484;
                    case 3: goto L_0x047e;
                    case 4: goto L_0x046f;
                    case 5: goto L_0x0458;
                    case 6: goto L_0x044d;
                    case 7: goto L_0x043d;
                    case 8: goto L_0x0436;
                    case 9: goto L_0x0402;
                    case 10: goto L_0x03a3;
                    case 11: goto L_0x0384;
                    case 12: goto L_0x0376;
                    case 13: goto L_0x0361;
                    case 14: goto L_0x0353;
                    case 15: goto L_0x0332;
                    case 16: goto L_0x0323;
                    case 17: goto L_0x0302;
                    case 18: goto L_0x02f3;
                    case 19: goto L_0x02e2;
                    case 20: goto L_0x02d3;
                    case 21: goto L_0x02ac;
                    case 22: goto L_0x0295;
                    case 23: goto L_0x028e;
                    case 24: goto L_0x0287;
                    case 25: goto L_0x0280;
                    case 26: goto L_0x0247;
                    case 27: goto L_0x0238;
                    case 28: goto L_0x0211;
                    case 29: goto L_0x01fe;
                    case 30: goto L_0x01df;
                    case 31: goto L_0x04fb;
                    case 32: goto L_0x01d4;
                    case 33: goto L_0x01c7;
                    default: goto L_0x01c1;
                }
            L_0x01c1:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r6)
                throw r0
            L_0x01c7:
                java.lang.String[] r0 = new java.lang.String[]{r11, r10}
                java.util.List r3 = kotlin.collections.CollectionsKt__CollectionsKt.listOf(r0)
                dagger.internal.Preconditions.checkNotNullFromProvides(r3)
                goto L_0x04fb
            L_0x01d4:
                java.util.Set r0 = java.util.Collections.singleton(r5)
                java.util.Collection r0 = (java.util.Collection) r0
                r3 = r0
                java.util.Collection r3 = (java.util.Collection) r3
                goto L_0x04fb
            L_0x01df:
                com.android.systemui.volume.panel.ui.layout.DefaultComponentsLayoutManager r3 = new com.android.systemui.volume.panel.ui.layout.DefaultComponentsLayoutManager
                javax.inject.Provider r0 = r9.provideBottomBarKeyProvider
                java.lang.Object r0 = r0.get()
                java.lang.String r0 = (java.lang.String) r0
                javax.inject.Provider r1 = r9.provideHeaderComponentsProvider
                java.lang.Object r1 = r1.get()
                java.util.Collection r1 = (java.util.Collection) r1
                javax.inject.Provider r2 = r9.provideFooterComponentsProvider
                java.lang.Object r2 = r2.get()
                java.util.Collection r2 = (java.util.Collection) r2
                r3.<init>(r0, r1, r2)
                goto L_0x04fb
            L_0x01fe:
                com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputActionsInteractor r3 = new com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputActionsInteractor
                com.android.systemui.media.dialog.MediaOutputDialogFactory r0 = r8.mediaOutputDialogFactory()
                dagger.internal.DelegateFactory r1 = r8.activityStarterImplProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.plugins.ActivityStarter r1 = (com.android.systemui.plugins.ActivityStarter) r1
                r3.<init>(r0, r1)
                goto L_0x04fb
            L_0x0211:
                com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel r3 = new com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel
                android.content.Context r5 = r7.context
                javax.inject.Provider r0 = r9.provideCoroutineScopeProvider
                java.lang.Object r0 = r0.get()
                r6 = r0
                kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
                javax.inject.Provider r0 = r9.mediaOutputActionsInteractorProvider
                java.lang.Object r0 = r0.get()
                r8 = r0
                com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputActionsInteractor r8 = (com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputActionsInteractor) r8
                javax.inject.Provider r0 = r9.mediaOutputInteractorProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor r0 = (com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor) r0
                com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel r7 = r9.viewModel
                r4 = r3
                r9 = r0
                r4.<init>(r5, r6, r7, r8, r9)
                goto L_0x04fb
            L_0x0238:
                com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent r3 = new com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent
                javax.inject.Provider r0 = r9.mediaOutputViewModelProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel r0 = (com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel) r0
                r3.<init>(r0)
                goto L_0x04fb
            L_0x0247:
                com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor r3 = new com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor
                com.android.systemui.volume.panel.component.mediaoutput.data.repository.LocalMediaRepositoryFactoryImpl r5 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1545$$Nest$mlocalMediaRepositoryFactoryImpl(r8)
                javax.inject.Provider r0 = r7.providePackageManagerProvider
                java.lang.Object r0 = r0.get()
                r6 = r0
                android.content.pm.PackageManager r6 = (android.content.pm.PackageManager) r6
                javax.inject.Provider r0 = r9.provideCoroutineScopeProvider
                java.lang.Object r0 = r0.get()
                r7 = r0
                kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
                javax.inject.Provider r0 = r8.bgCoroutineContextProvider
                java.lang.Object r0 = r0.get()
                kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$SwitchingProvider r1 = r8.provideBgHandlerProvider
                java.lang.Object r1 = r1.get()
                r9 = r1
                android.os.Handler r9 = (android.os.Handler) r9
                javax.inject.Provider r1 = r8.provideMediaDeviceSessionRepositoryProvider
                java.lang.Object r1 = r1.get()
                r10 = r1
                com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl r10 = (com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl) r10
                r4 = r3
                r8 = r0
                r4.<init>(r5, r6, r7, r8, r9, r10)
                goto L_0x04fb
            L_0x0280:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$2 r3 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$2
                r3.<init>(r0)
                goto L_0x04fb
            L_0x0287:
                com.android.systemui.volume.panel.component.volume.domain.interactor.VolumeSliderInteractor r3 = new com.android.systemui.volume.panel.component.volume.domain.interactor.VolumeSliderInteractor
                r3.<init>()
                goto L_0x04fb
            L_0x028e:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$1 r3 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$1
                r3.<init>(r0)
                goto L_0x04fb
            L_0x0295:
                com.android.systemui.volume.panel.component.volume.domain.interactor.CastVolumeInteractor r3 = new com.android.systemui.volume.panel.component.volume.domain.interactor.CastVolumeInteractor
                javax.inject.Provider r0 = r9.provideCoroutineScopeProvider
                java.lang.Object r0 = r0.get()
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                javax.inject.Provider r1 = r8.provideLocalMediaInteractorProvider
                java.lang.Object r1 = r1.get()
                com.android.settingslib.volume.domain.interactor.LocalMediaInteractor r1 = (com.android.settingslib.volume.domain.interactor.LocalMediaInteractor) r1
                r3.<init>(r0, r1)
                goto L_0x04fb
            L_0x02ac:
                com.android.systemui.volume.panel.component.volume.ui.viewmodel.AudioVolumeComponentViewModel r3 = new com.android.systemui.volume.panel.component.volume.ui.viewmodel.AudioVolumeComponentViewModel
                javax.inject.Provider r0 = r9.provideCoroutineScopeProvider
                java.lang.Object r0 = r0.get()
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                javax.inject.Provider r1 = r9.castVolumeInteractorProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.volume.panel.component.volume.domain.interactor.CastVolumeInteractor r1 = (com.android.systemui.volume.panel.component.volume.domain.interactor.CastVolumeInteractor) r1
                javax.inject.Provider r2 = r9.factoryProvider
                java.lang.Object r2 = r2.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$1 r2 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$1) r2
                javax.inject.Provider r4 = r9.factoryProvider2
                java.lang.Object r4 = r4.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$2 r4 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$2) r4
                r3.<init>(r0, r1, r2, r4)
                goto L_0x04fb
            L_0x02d3:
                com.android.systemui.volume.panel.component.volume.ui.composable.VolumeSlidersComponent r3 = new com.android.systemui.volume.panel.component.volume.ui.composable.VolumeSlidersComponent
                javax.inject.Provider r0 = r9.audioVolumeComponentViewModelProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.volume.panel.component.volume.ui.viewmodel.AudioVolumeComponentViewModel r0 = (com.android.systemui.volume.panel.component.volume.ui.viewmodel.AudioVolumeComponentViewModel) r0
                r3.<init>(r0)
                goto L_0x04fb
            L_0x02e2:
                com.android.systemui.volume.panel.component.bottombar.ui.viewmodel.BottomBarViewModel r3 = new com.android.systemui.volume.panel.component.bottombar.ui.viewmodel.BottomBarViewModel
                dagger.internal.DelegateFactory r0 = r8.activityStarterImplProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.plugins.ActivityStarter r0 = (com.android.systemui.plugins.ActivityStarter) r0
                com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel r1 = r9.viewModel
                r3.<init>(r0, r1)
                goto L_0x04fb
            L_0x02f3:
                com.android.systemui.volume.panel.component.bottombar.ui.BottomBarComponent r3 = new com.android.systemui.volume.panel.component.bottombar.ui.BottomBarComponent
                javax.inject.Provider r0 = r9.bottomBarViewModelProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.volume.panel.component.bottombar.ui.viewmodel.BottomBarViewModel r0 = (com.android.systemui.volume.panel.component.bottombar.ui.viewmodel.BottomBarViewModel) r0
                r3.<init>(r0)
                goto L_0x04fb
            L_0x0302:
                com.google.android.systemui.volume.panel.component.clearcalling.data.repository.ClearCallingRepository r3 = new com.google.android.systemui.volume.panel.component.clearcalling.data.repository.ClearCallingRepository
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r0 = r8.sysUIGoogleGlobalRootComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r0 = r0.provideApplicationContextProvider
                java.lang.Object r0 = r0.get()
                android.content.Context r0 = (android.content.Context) r0
                javax.inject.Provider r1 = r8.provideLocalBluetoothControllerProvider
                java.lang.Object r1 = r1.get()
                com.android.settingslib.bluetooth.LocalBluetoothManager r1 = (com.android.settingslib.bluetooth.LocalBluetoothManager) r1
                com.google.android.settingslib.dcservice.DcServiceClientImpl r2 = new com.google.android.settingslib.dcservice.DcServiceClientImpl
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                r2.<init>(r0, r1)
                r3.<init>(r2)
                goto L_0x04fb
            L_0x0323:
                com.google.android.systemui.volume.panel.component.clearcalling.domain.interactor.ClearCallingInteractor r3 = new com.google.android.systemui.volume.panel.component.clearcalling.domain.interactor.ClearCallingInteractor
                javax.inject.Provider r0 = r9.clearCallingRepositoryProvider
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.volume.panel.component.clearcalling.data.repository.ClearCallingRepository r0 = (com.google.android.systemui.volume.panel.component.clearcalling.data.repository.ClearCallingRepository) r0
                r3.<init>(r0)
                goto L_0x04fb
            L_0x0332:
                com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel r3 = new com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel
                android.content.Context r0 = r7.context
                javax.inject.Provider r1 = r9.clearCallingInteractorProvider
                java.lang.Object r1 = r1.get()
                com.google.android.systemui.volume.panel.component.clearcalling.domain.interactor.ClearCallingInteractor r1 = (com.google.android.systemui.volume.panel.component.clearcalling.domain.interactor.ClearCallingInteractor) r1
                javax.inject.Provider r2 = r9.provideCoroutineScopeProvider
                java.lang.Object r2 = r2.get()
                kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
                javax.inject.Provider r4 = r8.bgDispatcherProvider
                java.lang.Object r4 = r4.get()
                kotlinx.coroutines.CoroutineDispatcher r4 = (kotlinx.coroutines.CoroutineDispatcher) r4
                r3.<init>(r0, r1, r2, r4)
                goto L_0x04fb
            L_0x0353:
                javax.inject.Provider r0 = r9.clearCallingViewModelProvider
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel r0 = (com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel) r0
                com.android.systemui.volume.panel.component.button.ui.composable.ToggleButtonComponent r3 = com.google.android.systemui.volume.panel.component.clearcalling.ClearCallingModule_Companion_ProvideClearCallingComponentFactory.provideClearCallingComponent(r0)
                goto L_0x04fb
            L_0x0361:
                com.android.systemui.volume.panel.component.captioning.ui.viewmodel.CaptioningViewModel r3 = new com.android.systemui.volume.panel.component.captioning.ui.viewmodel.CaptioningViewModel
                android.content.Context r0 = r7.context
                com.android.settingslib.view.accessibility.domain.interactor.CaptioningInteractor r1 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1459$$Nest$mcaptioningInteractor(r8)
                javax.inject.Provider r2 = r9.provideCoroutineScopeProvider
                java.lang.Object r2 = r2.get()
                kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
                r3.<init>(r0, r1, r2)
                goto L_0x04fb
            L_0x0376:
                javax.inject.Provider r0 = r9.captioningViewModelProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.volume.panel.component.captioning.ui.viewmodel.CaptioningViewModel r0 = (com.android.systemui.volume.panel.component.captioning.ui.viewmodel.CaptioningViewModel) r0
                com.android.systemui.volume.panel.component.button.ui.composable.ToggleButtonComponent r3 = com.android.systemui.volume.panel.component.captioning.CaptioningModule_Companion_ProvideVolumePanelUiComponentFactory.provideVolumePanelUiComponent(r0)
                goto L_0x04fb
            L_0x0384:
                com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel r3 = new com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r0 = r7.provideApplicationContextProvider
                java.lang.Object r0 = r0.get()
                android.content.Context r0 = (android.content.Context) r0
                javax.inject.Provider r1 = r9.provideCoroutineScopeProvider
                java.lang.Object r1 = r1.get()
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                javax.inject.Provider r2 = r9.ancSliceInteractorProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.volume.panel.component.anc.domain.interactor.AncSliceInteractor r2 = (com.android.systemui.volume.panel.component.anc.domain.interactor.AncSliceInteractor) r2
                r3.<init>(r0, r1, r2)
                goto L_0x04fb
            L_0x03a3:
                javax.inject.Provider r0 = r9.ancViewModelProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel r0 = (com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel) r0
                com.android.systemui.volume.panel.component.anc.ui.composable.AncPopup r1 = new com.android.systemui.volume.panel.component.anc.ui.composable.AncPopup
                com.android.systemui.volume.panel.component.popup.ui.composable.VolumePanelPopup r2 = new com.android.systemui.volume.panel.component.popup.ui.composable.VolumePanelPopup
                com.android.systemui.statusbar.phone.SystemUIDialogFactory r10 = new com.android.systemui.statusbar.phone.SystemUIDialogFactory
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r3 = r9.sysUIGoogleGlobalRootComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r3 = r3.provideApplicationContextProvider
                java.lang.Object r3 = r3.get()
                r4 = r3
                android.content.Context r4 = (android.content.Context) r4
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r11 = r9.sysUIGoogleSysUIComponentImpl
                dagger.internal.DelegateFactory r3 = r11.systemUIDialogManagerProvider
                java.lang.Object r3 = r3.get()
                r5 = r3
                com.android.systemui.statusbar.phone.SystemUIDialogManager r5 = (com.android.systemui.statusbar.phone.SystemUIDialogManager) r5
                javax.inject.Provider r3 = r11.provideSysUiStateProvider
                java.lang.Object r3 = r3.get()
                r6 = r3
                com.android.systemui.model.SysUiState r6 = (com.android.systemui.model.SysUiState) r6
                javax.inject.Provider r3 = r11.broadcastDispatcherProvider
                java.lang.Object r3 = r3.get()
                r7 = r3
                com.android.systemui.broadcast.BroadcastDispatcher r7 = (com.android.systemui.broadcast.BroadcastDispatcher) r7
                javax.inject.Provider r3 = r11.provideDialogTransitionAnimatorProvider
                java.lang.Object r3 = r3.get()
                r8 = r3
                com.android.systemui.animation.DialogTransitionAnimator r8 = (com.android.systemui.animation.DialogTransitionAnimator) r8
                r3 = r10
                r3.<init>(r4, r5, r6, r7, r8)
                javax.inject.Provider r3 = r11.provideDialogTransitionAnimatorProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.animation.DialogTransitionAnimator r3 = (com.android.systemui.animation.DialogTransitionAnimator) r3
                r2.<init>(r10, r3)
                javax.inject.Provider r3 = r9.ancViewModelProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel r3 = (com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel) r3
                r1.<init>(r2, r3)
                com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent r3 = com.android.systemui.volume.panel.component.anc.AncModule_Companion_ProvideVolumePanelUiComponentFactory.provideVolumePanelUiComponent(r0, r1)
                goto L_0x04fb
            L_0x0402:
                com.android.systemui.volume.panel.ui.composable.ComponentsFactory r0 = new com.android.systemui.volume.panel.ui.composable.ComponentsFactory
                r9.getClass()
                dagger.internal.MapBuilder r6 = new dagger.internal.MapBuilder
                r6.<init>(r4)
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl$SwitchingProvider r4 = r9.provideVolumePanelUiComponentProvider
                java.util.Map r7 = r6.contributions
                r7.put(r11, r4)
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl$SwitchingProvider r4 = r9.provideVolumePanelUiComponentProvider2
                r7.put(r10, r4)
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl$SwitchingProvider r4 = r9.provideClearCallingComponentProvider
                r7.put(r2, r4)
                javax.inject.Provider r2 = r9.bottomBarComponentProvider
                r7.put(r3, r2)
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl$SwitchingProvider r2 = r9.volumeSlidersComponentProvider
                r7.put(r1, r2)
                javax.inject.Provider r1 = r9.mediaOutputComponentProvider
                r7.put(r5, r1)
                java.util.Map r1 = r6.build()
                r0.<init>(r1)
            L_0x0433:
                r3 = r0
                goto L_0x04fb
            L_0x0436:
                com.google.android.systemui.volume.panel.component.shared.availabilitycriteria.GoogleComponentAvailabilityCriteria r3 = new com.google.android.systemui.volume.panel.component.shared.availabilitycriteria.GoogleComponentAvailabilityCriteria
                r3.<init>()
                goto L_0x04fb
            L_0x043d:
                com.android.systemui.volume.panel.component.mediaoutput.domain.MediaOutputAvailabilityCriteria r3 = new com.android.systemui.volume.panel.component.mediaoutput.domain.MediaOutputAvailabilityCriteria
                com.android.settingslib.volume.data.repository.AudioRepositoryImpl r0 = r8.audioRepository()
                com.android.settingslib.volume.domain.interactor.AudioModeInteractor r1 = new com.android.settingslib.volume.domain.interactor.AudioModeInteractor
                r1.<init>(r0)
                r3.<init>(r1)
                goto L_0x04fb
            L_0x044d:
                com.android.systemui.volume.panel.component.captioning.domain.CaptioningAvailabilityCriteria r3 = new com.android.systemui.volume.panel.component.captioning.domain.CaptioningAvailabilityCriteria
                com.android.settingslib.view.accessibility.domain.interactor.CaptioningInteractor r0 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1459$$Nest$mcaptioningInteractor(r8)
                r3.<init>(r0)
                goto L_0x04fb
            L_0x0458:
                com.android.systemui.volume.panel.component.anc.domain.interactor.AncSliceInteractor r3 = new com.android.systemui.volume.panel.component.anc.domain.interactor.AncSliceInteractor
                javax.inject.Provider r0 = r8.provideAncSliceRepositoryProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.volume.panel.component.anc.data.repository.AncSliceRepositoryImpl r0 = (com.android.systemui.volume.panel.component.anc.data.repository.AncSliceRepositoryImpl) r0
                javax.inject.Provider r1 = r9.provideCoroutineScopeProvider
                java.lang.Object r1 = r1.get()
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                r3.<init>(r0, r1)
                goto L_0x04fb
            L_0x046f:
                com.android.systemui.volume.panel.component.anc.domain.AncAvailabilityCriteria r3 = new com.android.systemui.volume.panel.component.anc.domain.AncAvailabilityCriteria
                javax.inject.Provider r0 = r9.ancSliceInteractorProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.volume.panel.component.anc.domain.interactor.AncSliceInteractor r0 = (com.android.systemui.volume.panel.component.anc.domain.interactor.AncSliceInteractor) r0
                r3.<init>(r0)
                goto L_0x04fb
            L_0x047e:
                com.android.systemui.volume.panel.domain.AlwaysAvailableCriteria r3 = new com.android.systemui.volume.panel.domain.AlwaysAvailableCriteria
                r3.<init>()
                goto L_0x04fb
            L_0x0484:
                java.lang.String r6 = "captioning"
                java.lang.String r7 = "volume_sliders"
                java.lang.String r4 = "bottom_bar"
                java.lang.String r5 = "anc"
                java.lang.String r8 = "media_output"
                java.lang.String r9 = "clear_calling"
                java.lang.String[] r0 = new java.lang.String[]{r4, r5, r6, r7, r8, r9}
                java.util.Set r0 = kotlin.collections.SetsKt.setOf(r0)
                java.util.Collection r0 = (java.util.Collection) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                r3 = r0
                java.util.Collection r3 = (java.util.Collection) r3
                goto L_0x04fb
            L_0x04a1:
                com.android.systemui.volume.panel.domain.interactor.ComponentsInteractorImpl r0 = new com.android.systemui.volume.panel.domain.interactor.ComponentsInteractorImpl
                javax.inject.Provider r6 = r9.provideEnabledComponentsProvider
                java.lang.Object r6 = r6.get()
                java.util.Collection r6 = (java.util.Collection) r6
                javax.inject.Provider r7 = r9.alwaysAvailableCriteriaProvider
                javax.inject.Provider r8 = r9.provideCoroutineScopeProvider
                java.lang.Object r8 = r8.get()
                kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
                dagger.internal.MapBuilder r12 = new dagger.internal.MapBuilder
                r12.<init>(r4)
                javax.inject.Provider r4 = r9.alwaysAvailableCriteriaProvider
                java.util.Map r13 = r12.contributions
                r13.put(r3, r4)
                javax.inject.Provider r3 = r9.ancAvailabilityCriteriaProvider
                r13.put(r11, r3)
                javax.inject.Provider r3 = r9.alwaysAvailableCriteriaProvider
                r13.put(r1, r3)
                javax.inject.Provider r1 = r9.captioningAvailabilityCriteriaProvider
                r13.put(r10, r1)
                javax.inject.Provider r1 = r9.mediaOutputAvailabilityCriteriaProvider
                r13.put(r5, r1)
                javax.inject.Provider r1 = r9.googleComponentAvailabilityCriteriaProvider
                r13.put(r2, r1)
                java.util.Map r1 = r12.build()
                r0.<init>(r6, r7, r8, r1)
                goto L_0x0433
            L_0x04e3:
                javax.inject.Provider r0 = r7.applicationScopeProvider
                java.lang.Object r0 = r0.get()
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                kotlin.coroutines.CoroutineContext r0 = r0.getCoroutineContext()
                kotlinx.coroutines.SupervisorJobImpl r1 = kotlinx.coroutines.SupervisorKt.SupervisorJob$default()
                kotlin.coroutines.CoroutineContext r0 = r0.plus(r1)
                kotlinx.coroutines.internal.ContextScope r3 = kotlinx.coroutines.CoroutineScopeKt.CoroutineScope(r0)
            L_0x04fb:
                return r3
            L_0x04fc:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl r9 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl) r9
                switch(r6) {
                    case 0: goto L_0x0640;
                    case 1: goto L_0x0622;
                    case 2: goto L_0x0604;
                    case 3: goto L_0x0566;
                    case 4: goto L_0x0548;
                    case 5: goto L_0x0537;
                    case 6: goto L_0x0526;
                    case 7: goto L_0x0514;
                    case 8: goto L_0x0507;
                    default: goto L_0x0501;
                }
            L_0x0501:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r6)
                throw r0
            L_0x0507:
                java.lang.Object r0 = r9.dozeAuthRemoverProvider
                androidx.lifecycle.LifecycleOwner r0 = (androidx.lifecycle.LifecycleOwner) r0
                androidx.lifecycle.Lifecycle r0 = r0.getLifecycle()
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0760
            L_0x0514:
                android.content.res.Resources r0 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(r7)
                r1 = 2131427367(0x7f0b0027, float:1.8476348E38)
                int r0 = r0.getInteger(r1)
                long r0 = (long) r0
                java.lang.Long r0 = java.lang.Long.valueOf(r0)
                goto L_0x0760
            L_0x0526:
                android.content.res.Resources r0 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(r7)
                r1 = 2131165883(0x7f0702bb, float:1.7945996E38)
                int r0 = r0.getDimensionPixelSize(r1)
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                goto L_0x0760
            L_0x0537:
                android.content.res.Resources r0 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(r7)
                r1 = 2131165726(0x7f07021e, float:1.7945677E38)
                int r0 = r0.getDimensionPixelSize(r1)
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                goto L_0x0760
            L_0x0548:
                javax.inject.Provider r0 = r9.providesWrappedServiceProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.dreams.DreamOverlayContainerView r0 = (com.android.systemui.dreams.DreamOverlayContainerView) r0
                r1 = 2131362488(0x7f0a02b8, float:1.8344758E38)
                android.view.View r0 = r0.findViewById(r1)
                com.android.systemui.dreams.DreamOverlayStatusBarView r0 = (com.android.systemui.dreams.DreamOverlayStatusBarView) r0
                java.lang.String r1 = "R.id.status_bar must not be null"
                java.lang.Object r0 = com.android.internal.util.Preconditions.checkNotNull(r0, r1)
                com.android.systemui.dreams.DreamOverlayStatusBarView r0 = (com.android.systemui.dreams.DreamOverlayStatusBarView) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0760
            L_0x0566:
                com.android.systemui.dreams.DreamOverlayStatusBarViewController r0 = new com.android.systemui.dreams.DreamOverlayStatusBarViewController
                javax.inject.Provider r1 = r9.dozePauserProvider
                java.lang.Object r1 = r1.get()
                r2 = r1
                com.android.systemui.dreams.DreamOverlayStatusBarView r2 = (com.android.systemui.dreams.DreamOverlayStatusBarView) r2
                android.content.res.Resources r3 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(r7)
                javax.inject.Provider r1 = r7.provideMainExecutorProvider
                java.lang.Object r1 = r1.get()
                r4 = r1
                java.util.concurrent.Executor r4 = (java.util.concurrent.Executor) r4
                javax.inject.Provider r1 = r7.provideConnectivityManagagerProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                android.net.ConnectivityManager r5 = (android.net.ConnectivityManager) r5
                com.android.systemui.touch.TouchInsetManager$TouchInsetSession r6 = new com.android.systemui.touch.TouchInsetManager$TouchInsetSession
                java.lang.Object r1 = r9.dozeDockHandlerProvider
                com.android.systemui.touch.TouchInsetManager r1 = (com.android.systemui.touch.TouchInsetManager) r1
                java.util.concurrent.Executor r9 = r1.mExecutor
                r6.<init>(r1, r9)
                javax.inject.Provider r1 = r7.provideAlarmManagerProvider
                java.lang.Object r1 = r1.get()
                r7 = r1
                android.app.AlarmManager r7 = (android.app.AlarmManager) r7
                javax.inject.Provider r1 = r8.nextAlarmControllerImplProvider
                java.lang.Object r1 = r1.get()
                r9 = r1
                com.android.systemui.statusbar.policy.NextAlarmController r9 = (com.android.systemui.statusbar.policy.NextAlarmController) r9
                com.android.systemui.util.time.DateFormatUtil r10 = r8.dateFormatUtil()
                javax.inject.Provider r1 = r8.provideIndividualSensorPrivacyControllerProvider
                java.lang.Object r1 = r1.get()
                r11 = r1
                com.android.systemui.statusbar.policy.IndividualSensorPrivacyControllerImpl r11 = (com.android.systemui.statusbar.policy.IndividualSensorPrivacyControllerImpl) r11
                javax.inject.Provider r1 = r8.providesDreamOverlayNotificationCountProvider
                java.lang.Object r1 = r1.get()
                r12 = r1
                java.util.Optional r12 = (java.util.Optional) r12
                javax.inject.Provider r1 = r8.zenModeControllerImplProvider
                java.lang.Object r1 = r1.get()
                r13 = r1
                com.android.systemui.statusbar.policy.ZenModeController r13 = (com.android.systemui.statusbar.policy.ZenModeController) r13
                javax.inject.Provider r1 = r8.statusBarWindowStateControllerProvider
                java.lang.Object r1 = r1.get()
                r14 = r1
                com.android.systemui.statusbar.window.StatusBarWindowStateController r14 = (com.android.systemui.statusbar.window.StatusBarWindowStateController) r14
                javax.inject.Provider r1 = r8.dreamOverlayStatusBarItemsProvider
                java.lang.Object r1 = r1.get()
                r15 = r1
                com.android.systemui.dreams.DreamOverlayStatusBarItemsProvider r15 = (com.android.systemui.dreams.DreamOverlayStatusBarItemsProvider) r15
                javax.inject.Provider r1 = r8.dreamOverlayStateControllerProvider
                java.lang.Object r1 = r1.get()
                r16 = r1
                com.android.systemui.dreams.DreamOverlayStateController r16 = (com.android.systemui.dreams.DreamOverlayStateController) r16
                dagger.internal.DelegateFactory r1 = r8.provideUserTrackerProvider
                java.lang.Object r1 = r1.get()
                r18 = r1
                com.android.systemui.settings.UserTracker r18 = (com.android.systemui.settings.UserTracker) r18
                javax.inject.Provider r1 = r8.provideDreamLogBufferProvider
                java.lang.Object r1 = r1.get()
                r17 = r1
                com.android.systemui.log.LogBuffer r17 = (com.android.systemui.log.LogBuffer) r17
                r1 = r0
                r8 = r9
                r9 = r10
                r10 = r11
                r11 = r12
                r12 = r13
                r13 = r14
                r14 = r15
                r15 = r16
                r16 = r18
                r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
                goto L_0x0760
            L_0x0604:
                javax.inject.Provider r0 = r9.providesWrappedServiceProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.dreams.DreamOverlayContainerView r0 = (com.android.systemui.dreams.DreamOverlayContainerView) r0
                r1 = 2131362483(0x7f0a02b3, float:1.8344748E38)
                android.view.View r0 = r0.findViewById(r1)
                android.view.ViewGroup r0 = (android.view.ViewGroup) r0
                java.lang.String r1 = "R.id.dream_overlay_content must not be null"
                java.lang.Object r0 = com.android.internal.util.Preconditions.checkNotNull(r0, r1)
                android.view.ViewGroup r0 = (android.view.ViewGroup) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0760
            L_0x0622:
                javax.inject.Provider r0 = r7.providerLayoutInflaterProvider
                java.lang.Object r0 = r0.get()
                android.view.LayoutInflater r0 = (android.view.LayoutInflater) r0
                r1 = 2131558589(0x7f0d00bd, float:1.8742498E38)
                android.view.View r0 = r0.inflate(r1, r3)
                com.android.systemui.dreams.DreamOverlayContainerView r0 = (com.android.systemui.dreams.DreamOverlayContainerView) r0
                java.lang.String r1 = "R.layout.dream_layout_container could not be properly inflated"
                java.lang.Object r0 = com.android.internal.util.Preconditions.checkNotNull(r0, r1)
                com.android.systemui.dreams.DreamOverlayContainerView r0 = (com.android.systemui.dreams.DreamOverlayContainerView) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0760
            L_0x0640:
                com.android.systemui.dreams.DreamOverlayContainerViewController r0 = new com.android.systemui.dreams.DreamOverlayContainerViewController
                javax.inject.Provider r1 = r9.providesWrappedServiceProvider
                java.lang.Object r1 = r1.get()
                r11 = r1
                com.android.systemui.dreams.DreamOverlayContainerView r11 = (com.android.systemui.dreams.DreamOverlayContainerView) r11
                javax.inject.Provider r1 = r9.providesDozeWakeLockProvider
                java.lang.Object r1 = r1.get()
                r13 = r1
                android.view.ViewGroup r13 = (android.view.ViewGroup) r13
                javax.inject.Provider r1 = r9.dozeFalsingManagerAdapterProvider
                java.lang.Object r1 = r1.get()
                r14 = r1
                com.android.systemui.dreams.DreamOverlayStatusBarViewController r14 = (com.android.systemui.dreams.DreamOverlayStatusBarViewController) r14
                javax.inject.Provider r1 = r7.lowLightTransitionCoordinatorProvider
                java.lang.Object r1 = r1.get()
                r15 = r1
                com.android.dream.lowlight.LowLightTransitionCoordinator r15 = (com.android.dream.lowlight.LowLightTransitionCoordinator) r15
                javax.inject.Provider r1 = r8.blurUtilsProvider
                java.lang.Object r1 = r1.get()
                r16 = r1
                com.android.systemui.statusbar.BlurUtils r16 = (com.android.systemui.statusbar.BlurUtils) r16
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r1 = r7.provideMainHandlerProvider
                java.lang.Object r1 = r1.get()
                r17 = r1
                android.os.Handler r17 = (android.os.Handler) r17
                android.content.Context r1 = r7.context
                android.content.res.Resources r18 = r1.getResources()
                dagger.internal.Preconditions.checkNotNullFromProvides(r18)
                javax.inject.Provider r1 = r9.dozeTriggersProvider
                java.lang.Object r1 = r1.get()
                java.lang.Integer r1 = (java.lang.Integer) r1
                int r19 = r1.intValue()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r1 = r9.sysUIGoogleGlobalRootComponentImpl
                android.content.res.Resources r2 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(r1)
                r3 = 2131427364(0x7f0b0024, float:1.8476342E38)
                int r2 = r2.getInteger(r3)
                long r2 = (long) r2
                android.content.res.Resources r4 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(r1)
                r5 = 2131427368(0x7f0b0028, float:1.847635E38)
                int r4 = r4.getInteger(r5)
                long r4 = (long) r4
                javax.inject.Provider r6 = r8.primaryBouncerCallbackInteractorProvider
                java.lang.Object r6 = r6.get()
                r24 = r6
                com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor r24 = (com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor) r24
                com.android.systemui.dreams.DreamOverlayAnimationsController r6 = new com.android.systemui.dreams.DreamOverlayAnimationsController
                java.lang.Object r7 = r9.sysUIGoogleSysUIComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r7 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) r7
                javax.inject.Provider r10 = r7.blurUtilsProvider
                java.lang.Object r10 = r10.get()
                r26 = r10
                com.android.systemui.statusbar.BlurUtils r26 = (com.android.systemui.statusbar.BlurUtils) r26
                javax.inject.Provider r10 = r9.dozeFalsingManagerAdapterProvider
                java.lang.Object r10 = r10.get()
                r28 = r10
                com.android.systemui.dreams.DreamOverlayStatusBarViewController r28 = (com.android.systemui.dreams.DreamOverlayStatusBarViewController) r28
                javax.inject.Provider r10 = r7.dreamOverlayStateControllerProvider
                java.lang.Object r10 = r10.get()
                r29 = r10
                com.android.systemui.dreams.DreamOverlayStateController r29 = (com.android.systemui.dreams.DreamOverlayStateController) r29
                android.content.res.Resources r10 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(r1)
                r12 = 2131165843(0x7f070293, float:1.7945915E38)
                int r30 = r10.getDimensionPixelSize(r12)
                javax.inject.Provider r10 = r7.dreamOverlayViewModelProvider
                java.lang.Object r10 = r10.get()
                r31 = r10
                com.android.systemui.dreams.ui.viewmodel.DreamOverlayViewModel r31 = (com.android.systemui.dreams.ui.viewmodel.DreamOverlayViewModel) r31
                android.content.Context r1 = r1.context
                android.content.res.Resources r10 = r1.getResources()
                dagger.internal.Preconditions.checkNotNullFromProvides(r10)
                r12 = 2131427365(0x7f0b0025, float:1.8476344E38)
                int r10 = r10.getInteger(r12)
                r22 = r4
                long r4 = (long) r10
                android.content.res.Resources r1 = r1.getResources()
                dagger.internal.Preconditions.checkNotNullFromProvides(r1)
                r10 = 2131427366(0x7f0b0026, float:1.8476346E38)
                int r1 = r1.getInteger(r10)
                r20 = r2
                long r1 = (long) r1
                javax.inject.Provider r3 = r9.dozeUiProvider
                java.lang.Object r3 = r3.get()
                java.lang.Integer r3 = (java.lang.Integer) r3
                int r36 = r3.intValue()
                javax.inject.Provider r3 = r9.dozeScreenBrightnessProvider
                java.lang.Object r3 = r3.get()
                java.lang.Long r3 = (java.lang.Long) r3
                long r37 = r3.longValue()
                javax.inject.Provider r3 = r7.provideDreamLogBufferProvider
                java.lang.Object r3 = r3.get()
                r39 = r3
                com.android.systemui.log.LogBuffer r39 = (com.android.systemui.log.LogBuffer) r39
                java.lang.Object r3 = r9.dozeMachineService
                r27 = r3
                com.android.systemui.complication.ComplicationHostViewController r27 = (com.android.systemui.complication.ComplicationHostViewController) r27
                r25 = r6
                r32 = r4
                r34 = r1
                r25.<init>(r26, r27, r28, r29, r30, r31, r32, r34, r36, r37, r39)
                javax.inject.Provider r1 = r8.dreamOverlayStateControllerProvider
                java.lang.Object r1 = r1.get()
                r26 = r1
                com.android.systemui.dreams.DreamOverlayStateController r26 = (com.android.systemui.dreams.DreamOverlayStateController) r26
                javax.inject.Provider r1 = r8.bouncerlessScrimControllerProvider
                java.lang.Object r1 = r1.get()
                r27 = r1
                com.android.systemui.dreams.touch.scrim.BouncerlessScrimController r27 = (com.android.systemui.dreams.touch.scrim.BouncerlessScrimController) r27
                java.lang.Object r1 = r9.dozeMachineService
                r12 = r1
                com.android.systemui.complication.ComplicationHostViewController r12 = (com.android.systemui.complication.ComplicationHostViewController) r12
                r10 = r0
                r25 = r6
                r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r22, r24, r25, r26, r27)
            L_0x0760:
                return r0
            L_0x0761:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl r9 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl) r9
                switch(r6) {
                    case 0: goto L_0x0b77;
                    case 1: goto L_0x0b68;
                    case 2: goto L_0x0b61;
                    case 3: goto L_0x0b52;
                    case 4: goto L_0x0ad6;
                    case 5: goto L_0x0a86;
                    case 6: goto L_0x0a7f;
                    case 7: goto L_0x0a68;
                    case 8: goto L_0x0a4f;
                    case 9: goto L_0x09e3;
                    case 10: goto L_0x09bd;
                    case 11: goto L_0x098b;
                    case 12: goto L_0x097c;
                    case 13: goto L_0x0975;
                    case 14: goto L_0x095e;
                    case 15: goto L_0x093e;
                    case 16: goto L_0x08ec;
                    case 17: goto L_0x08b7;
                    case 18: goto L_0x08b0;
                    case 19: goto L_0x0897;
                    case 20: goto L_0x0865;
                    case 21: goto L_0x0830;
                    case 22: goto L_0x07fa;
                    case 23: goto L_0x07b3;
                    case 24: goto L_0x079c;
                    case 25: goto L_0x077d;
                    case 26: goto L_0x076c;
                    default: goto L_0x0766;
                }
            L_0x0766:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r6)
                throw r0
            L_0x076c:
                com.android.systemui.statusbar.notification.collection.coordinator.NotificationStatsLoggerCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.NotificationStatsLoggerCoordinator
                r8.getClass()
                java.util.Optional r1 = java.util.Optional.empty()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                r0.<init>(r1)
                goto L_0x0cbb
            L_0x077d:
                com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator
                dagger.internal.DelegateFactory r1 = r8.statusBarStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.SysuiStatusBarStateController r1 = (com.android.systemui.statusbar.SysuiStatusBarStateController) r1
                javax.inject.Provider r2 = r7.applicationScopeProvider
                java.lang.Object r2 = r2.get()
                kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
                javax.inject.Provider r3 = r8.keyguardRepositoryImplProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl r3 = (com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl) r3
                r0.<init>(r1, r2, r3)
                goto L_0x0cbb
            L_0x079c:
                com.android.systemui.statusbar.notification.collection.coordinator.DismissibilityCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.DismissibilityCoordinator
                dagger.internal.DelegateFactory r1 = r8.keyguardStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.policy.KeyguardStateController r1 = (com.android.systemui.statusbar.policy.KeyguardStateController) r1
                javax.inject.Provider r2 = r8.notificationDismissibilityProviderImplProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.notification.collection.provider.NotificationDismissibilityProviderImpl r2 = (com.android.systemui.statusbar.notification.collection.provider.NotificationDismissibilityProviderImpl) r2
                r0.<init>(r1, r2)
                goto L_0x0cbb
            L_0x07b3:
                com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinatorImpl r0 = new com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinatorImpl
                javax.inject.Provider r1 = r8.dynamicPrivacyControllerProvider
                java.lang.Object r1 = r1.get()
                r4 = r1
                com.android.systemui.statusbar.notification.DynamicPrivacyController r4 = (com.android.systemui.statusbar.notification.DynamicPrivacyController) r4
                dagger.internal.DelegateFactory r1 = r8.notificationLockscreenUserManagerImplProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                com.android.systemui.statusbar.NotificationLockscreenUserManager r5 = (com.android.systemui.statusbar.NotificationLockscreenUserManager) r5
                dagger.internal.DelegateFactory r1 = r8.keyguardUpdateMonitorProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.keyguard.KeyguardUpdateMonitor r6 = (com.android.keyguard.KeyguardUpdateMonitor) r6
                dagger.internal.DelegateFactory r1 = r8.statusBarStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                r7 = r1
                com.android.systemui.plugins.statusbar.StatusBarStateController r7 = (com.android.systemui.plugins.statusbar.StatusBarStateController) r7
                dagger.internal.DelegateFactory r1 = r8.keyguardStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.policy.KeyguardStateController r1 = (com.android.systemui.statusbar.policy.KeyguardStateController) r1
                javax.inject.Provider r2 = r8.selectedUserInteractorProvider
                java.lang.Object r2 = r2.get()
                r9 = r2
                com.android.systemui.user.domain.interactor.SelectedUserInteractor r9 = (com.android.systemui.user.domain.interactor.SelectedUserInteractor) r9
                javax.inject.Provider r2 = r8.sensitiveNotificationProtectionControllerImplProvider
                java.lang.Object r2 = r2.get()
                r10 = r2
                com.android.systemui.statusbar.policy.SensitiveNotificationProtectionController r10 = (com.android.systemui.statusbar.policy.SensitiveNotificationProtectionController) r10
                r3 = r0
                r8 = r1
                r3.<init>(r4, r5, r6, r7, r8, r9, r10)
                goto L_0x0cbb
            L_0x07fa:
                com.android.systemui.statusbar.notification.collection.coordinator.ViewConfigCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.ViewConfigCoordinator
                javax.inject.Provider r1 = r8.configurationControllerImplProvider
                java.lang.Object r1 = r1.get()
                r12 = r1
                com.android.systemui.statusbar.policy.ConfigurationController r12 = (com.android.systemui.statusbar.policy.ConfigurationController) r12
                dagger.internal.DelegateFactory r1 = r8.notificationLockscreenUserManagerImplProvider
                java.lang.Object r1 = r1.get()
                r13 = r1
                com.android.systemui.statusbar.NotificationLockscreenUserManager r13 = (com.android.systemui.statusbar.NotificationLockscreenUserManager) r13
                javax.inject.Provider r1 = r8.notificationGutsManagerProvider
                java.lang.Object r1 = r1.get()
                r14 = r1
                com.android.systemui.statusbar.notification.row.NotificationGutsManager r14 = (com.android.systemui.statusbar.notification.row.NotificationGutsManager) r14
                dagger.internal.DelegateFactory r1 = r8.keyguardUpdateMonitorProvider
                java.lang.Object r1 = r1.get()
                r15 = r1
                com.android.keyguard.KeyguardUpdateMonitor r15 = (com.android.keyguard.KeyguardUpdateMonitor) r15
                javax.inject.Provider r1 = r8.colorUpdateLoggerProvider
                java.lang.Object r1 = r1.get()
                r16 = r1
                com.android.systemui.statusbar.notification.ColorUpdateLogger r16 = (com.android.systemui.statusbar.notification.ColorUpdateLogger) r16
                r11 = r0
                r11.<init>(r12, r13, r14, r15, r16)
                goto L_0x0cbb
            L_0x0830:
                com.android.systemui.statusbar.notification.collection.coordinator.SmartspaceDedupingCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.SmartspaceDedupingCoordinator
                dagger.internal.DelegateFactory r1 = r8.statusBarStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                r2 = r1
                com.android.systemui.statusbar.SysuiStatusBarStateController r2 = (com.android.systemui.statusbar.SysuiStatusBarStateController) r2
                javax.inject.Provider r1 = r8.lockscreenSmartspaceControllerProvider
                java.lang.Object r1 = r1.get()
                r3 = r1
                com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController r3 = (com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController) r3
                javax.inject.Provider r1 = r8.notifPipelineProvider
                java.lang.Object r1 = r1.get()
                r4 = r1
                com.android.systemui.statusbar.notification.collection.NotifPipeline r4 = (com.android.systemui.statusbar.notification.collection.NotifPipeline) r4
                javax.inject.Provider r1 = r7.provideMainDelayableExecutorProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                com.android.systemui.util.concurrency.DelayableExecutor r5 = (com.android.systemui.util.concurrency.DelayableExecutor) r5
                javax.inject.Provider r1 = r8.bindSystemClockProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.systemui.util.time.SystemClock r6 = (com.android.systemui.util.time.SystemClock) r6
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6)
                goto L_0x0cbb
            L_0x0865:
                com.android.systemui.statusbar.notification.collection.coordinator.StackCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.StackCoordinator
                javax.inject.Provider r1 = r8.groupExpansionManagerImplProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl r1 = (com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl) r1
                com.android.systemui.statusbar.phone.NotificationIconAreaController r2 = r8.notificationIconAreaController()
                com.android.systemui.statusbar.notification.domain.interactor.RenderNotificationListInteractor r3 = new com.android.systemui.statusbar.notification.domain.interactor.RenderNotificationListInteractor
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r4 = r9.sysUIGoogleSysUIComponentImpl
                javax.inject.Provider r5 = r4.activeNotificationListRepositoryProvider
                java.lang.Object r5 = r5.get()
                com.android.systemui.statusbar.notification.data.repository.ActiveNotificationListRepository r5 = (com.android.systemui.statusbar.notification.data.repository.ActiveNotificationListRepository) r5
                javax.inject.Provider r4 = r4.sectionStyleProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.statusbar.notification.collection.provider.SectionStyleProvider r4 = (com.android.systemui.statusbar.notification.collection.provider.SectionStyleProvider) r4
                r3.<init>()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$SwitchingProvider r4 = r8.activeNotificationsInteractorProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor r4 = (com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor) r4
                r0.<init>(r1, r2, r3, r4)
                goto L_0x0cbb
            L_0x0897:
                com.android.systemui.statusbar.notification.collection.coordinator.RowAppearanceCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.RowAppearanceCoordinator
                android.content.Context r1 = r7.context
                javax.inject.Provider r2 = r8.assistantFeedbackControllerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.notification.AssistantFeedbackController r2 = (com.android.systemui.statusbar.notification.AssistantFeedbackController) r2
                javax.inject.Provider r3 = r8.sectionStyleProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.notification.collection.provider.SectionStyleProvider r3 = (com.android.systemui.statusbar.notification.collection.provider.SectionStyleProvider) r3
                r0.<init>(r1, r2, r3)
                goto L_0x0cbb
            L_0x08b0:
                com.android.systemui.statusbar.notification.collection.coordinator.RowAlertTimeCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.RowAlertTimeCoordinator
                r0.<init>()
                goto L_0x0cbb
            L_0x08b7:
                com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinator
                javax.inject.Provider r1 = r7.dumpManagerProvider
                java.lang.Object r1 = r1.get()
                r2 = r1
                com.android.systemui.dump.DumpManager r2 = (com.android.systemui.dump.DumpManager) r2
                javax.inject.Provider r1 = r8.remoteInputNotificationRebuilderProvider
                java.lang.Object r1 = r1.get()
                r3 = r1
                com.android.systemui.statusbar.RemoteInputNotificationRebuilder r3 = (com.android.systemui.statusbar.RemoteInputNotificationRebuilder) r3
                javax.inject.Provider r1 = r8.notificationRemoteInputManagerProvider
                java.lang.Object r1 = r1.get()
                r4 = r1
                com.android.systemui.statusbar.NotificationRemoteInputManager r4 = (com.android.systemui.statusbar.NotificationRemoteInputManager) r4
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r1 = r7.provideMainHandlerProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                android.os.Handler r5 = (android.os.Handler) r5
                javax.inject.Provider r1 = r8.provideSmartReplyControllerProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.systemui.statusbar.SmartReplyController r6 = (com.android.systemui.statusbar.SmartReplyController) r6
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6)
                goto L_0x0cbb
            L_0x08ec:
                com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator
                com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinatorLogger r1 = new com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinatorLogger
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r2 = r9.sysUIGoogleSysUIComponentImpl
                javax.inject.Provider r2 = r2.provideNotificationsLogBufferProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.log.LogBuffer r2 = (com.android.systemui.log.LogBuffer) r2
                r1.<init>(r2)
                javax.inject.Provider r2 = r8.notifInflaterImplProvider
                java.lang.Object r2 = r2.get()
                r9 = r2
                com.android.systemui.statusbar.notification.collection.inflation.NotifInflater r9 = (com.android.systemui.statusbar.notification.collection.inflation.NotifInflater) r9
                javax.inject.Provider r2 = r8.notifInflationErrorManagerProvider
                java.lang.Object r2 = r2.get()
                r10 = r2
                com.android.systemui.statusbar.notification.row.NotifInflationErrorManager r10 = (com.android.systemui.statusbar.notification.row.NotifInflationErrorManager) r10
                javax.inject.Provider r2 = r8.notifViewBarnProvider
                java.lang.Object r2 = r2.get()
                r11 = r2
                com.android.systemui.statusbar.notification.collection.render.NotifViewBarn r11 = (com.android.systemui.statusbar.notification.collection.render.NotifViewBarn) r11
                javax.inject.Provider r2 = r8.notifUiAdjustmentProvider
                java.lang.Object r2 = r2.get()
                r12 = r2
                com.android.systemui.statusbar.notification.collection.inflation.NotifUiAdjustmentProvider r12 = (com.android.systemui.statusbar.notification.collection.inflation.NotifUiAdjustmentProvider) r12
                javax.inject.Provider r2 = r7.provideIStatusBarServiceProvider
                java.lang.Object r2 = r2.get()
                r13 = r2
                com.android.internal.statusbar.IStatusBarService r13 = (com.android.internal.statusbar.IStatusBarService) r13
                javax.inject.Provider r2 = r8.bindEventManagerImplProvider
                java.lang.Object r2 = r2.get()
                r14 = r2
                com.android.systemui.statusbar.notification.collection.inflation.BindEventManagerImpl r14 = (com.android.systemui.statusbar.notification.collection.inflation.BindEventManagerImpl) r14
                r15 = 9
                r16 = 500(0x1f4, double:2.47E-321)
                r7 = r0
                r8 = r1
                r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16)
                goto L_0x0cbb
            L_0x093e:
                com.android.systemui.statusbar.notification.collection.coordinator.MediaCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.MediaCoordinator
                com.android.systemui.media.controls.util.MediaFeatureFlag r1 = new com.android.systemui.media.controls.util.MediaFeatureFlag
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r8.sysUIGoogleGlobalRootComponentImpl
                android.content.Context r2 = r2.context
                r1.<init>(r2)
                javax.inject.Provider r2 = r7.provideIStatusBarServiceProvider
                java.lang.Object r2 = r2.get()
                com.android.internal.statusbar.IStatusBarService r2 = (com.android.internal.statusbar.IStatusBarService) r2
                javax.inject.Provider r3 = r8.iconManagerProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.notification.icon.IconManager r3 = (com.android.systemui.statusbar.notification.icon.IconManager) r3
                r0.<init>(r1, r2, r3)
                goto L_0x0cbb
            L_0x095e:
                com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator
                javax.inject.Provider r1 = r7.provideMainDelayableExecutorProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.util.concurrency.DelayableExecutor r1 = (com.android.systemui.util.concurrency.DelayableExecutor) r1
                javax.inject.Provider r2 = r8.bindSystemClockProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.util.time.SystemClock r2 = (com.android.systemui.util.time.SystemClock) r2
                r0.<init>(r1, r2)
                goto L_0x0cbb
            L_0x0975:
                com.android.systemui.statusbar.notification.collection.coordinator.GroupCountCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.GroupCountCoordinator
                r0.<init>()
                goto L_0x0cbb
            L_0x097c:
                com.android.systemui.statusbar.notification.collection.coordinator.DebugModeCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.DebugModeCoordinator
                javax.inject.Provider r1 = r8.debugModeFilterProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.notification.collection.provider.DebugModeFilterProvider r1 = (com.android.systemui.statusbar.notification.collection.provider.DebugModeFilterProvider) r1
                r0.<init>(r1)
                goto L_0x0cbb
            L_0x098b:
                com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator
                javax.inject.Provider r1 = r8.peopleNotificationIdentifierImplProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifierImpl r1 = (com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifierImpl) r1
                javax.inject.Provider r2 = r8.iconManagerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.notification.icon.IconManager r2 = (com.android.systemui.statusbar.notification.icon.IconManager) r2
                javax.inject.Provider r3 = r8.highPriorityProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.notification.collection.provider.HighPriorityProvider r3 = (com.android.systemui.statusbar.notification.collection.provider.HighPriorityProvider) r3
                javax.inject.Provider r4 = r8.providesPeopleHeaderSubcomponentProvider
                java.lang.Object r4 = r4.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl r4 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) r4
                javax.inject.Provider r4 = r4.sectionHeaderNodeControllerImplProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.statusbar.notification.collection.render.NodeController r4 = (com.android.systemui.statusbar.notification.collection.render.NodeController) r4
                dagger.internal.Preconditions.checkNotNullFromProvides(r4)
                r0.<init>(r1, r2, r3)
                goto L_0x0cbb
            L_0x09bd:
                com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinator
                javax.inject.Provider r1 = r8.notificationGutsManagerProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.notification.row.NotificationGutsManager r1 = (com.android.systemui.statusbar.notification.row.NotificationGutsManager) r1
                com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinatorLogger r2 = new com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinatorLogger
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r3 = r9.sysUIGoogleSysUIComponentImpl
                javax.inject.Provider r3 = r3.provideNotificationsLogBufferProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.log.LogBuffer r3 = (com.android.systemui.log.LogBuffer) r3
                r2.<init>(r3)
                javax.inject.Provider r3 = r7.dumpManagerProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.dump.DumpManager r3 = (com.android.systemui.dump.DumpManager) r3
                r0.<init>(r1, r2, r3)
                goto L_0x0cbb
            L_0x09e3:
                com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator
                com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinatorLogger r5 = new com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinatorLogger
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r1 = r9.sysUIGoogleSysUIComponentImpl
                javax.inject.Provider r1 = r1.provideNotificationHeadsUpLogBufferProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.log.LogBuffer r1 = (com.android.systemui.log.LogBuffer) r1
                r5.<init>(r1)
                javax.inject.Provider r1 = r8.bindSystemClockProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.systemui.util.time.SystemClock r6 = (com.android.systemui.util.time.SystemClock) r6
                javax.inject.Provider r1 = r8.headsUpManagerPhoneProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.policy.HeadsUpManager r1 = (com.android.systemui.statusbar.policy.HeadsUpManager) r1
                javax.inject.Provider r2 = r8.headsUpViewBinderProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.notification.interruption.HeadsUpViewBinder r2 = (com.android.systemui.statusbar.notification.interruption.HeadsUpViewBinder) r2
                javax.inject.Provider r3 = r8.provideVisualInterruptionDecisionProvider
                java.lang.Object r3 = r3.get()
                r9 = r3
                com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider r9 = (com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider) r9
                javax.inject.Provider r3 = r8.notificationRemoteInputManagerProvider
                java.lang.Object r3 = r3.get()
                r10 = r3
                com.android.systemui.statusbar.NotificationRemoteInputManager r10 = (com.android.systemui.statusbar.NotificationRemoteInputManager) r10
                javax.inject.Provider r3 = r8.launchFullScreenIntentProvider
                java.lang.Object r3 = r3.get()
                r11 = r3
                com.android.systemui.statusbar.notification.collection.provider.LaunchFullScreenIntentProvider r11 = (com.android.systemui.statusbar.notification.collection.provider.LaunchFullScreenIntentProvider) r11
                r8.notifPipelineFlags()
                javax.inject.Provider r3 = r8.providesIncomingHeaderSubcomponentProvider
                java.lang.Object r3 = r3.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl r3 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) r3
                javax.inject.Provider r3 = r3.sectionHeaderNodeControllerImplProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.notification.collection.render.NodeController r3 = (com.android.systemui.statusbar.notification.collection.render.NodeController) r3
                dagger.internal.Preconditions.checkNotNullFromProvides(r3)
                javax.inject.Provider r3 = r7.provideMainDelayableExecutorProvider
                java.lang.Object r3 = r3.get()
                r12 = r3
                com.android.systemui.util.concurrency.DelayableExecutor r12 = (com.android.systemui.util.concurrency.DelayableExecutor) r12
                r4 = r0
                r7 = r1
                r8 = r2
                r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)
                goto L_0x0cbb
            L_0x0a4f:
                com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator
                javax.inject.Provider r1 = r8.provideBubblesManagerProvider
                java.lang.Object r1 = r1.get()
                java.util.Optional r1 = (java.util.Optional) r1
                javax.inject.Provider r2 = r8.notifCollectionProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.notification.collection.NotifCollection r2 = (com.android.systemui.statusbar.notification.collection.NotifCollection) r2
                java.util.Optional r3 = r8.setBubbles
                r0.<init>(r1, r3, r2)
                goto L_0x0cbb
            L_0x0a68:
                com.android.systemui.statusbar.notification.collection.coordinator.DeviceProvisionedCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.DeviceProvisionedCoordinator
                javax.inject.Provider r1 = r8.provideDeviceProvisionedControllerProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.policy.DeviceProvisionedController r1 = (com.android.systemui.statusbar.policy.DeviceProvisionedController) r1
                javax.inject.Provider r2 = r7.provideIPackageManagerProvider
                java.lang.Object r2 = r2.get()
                android.content.pm.IPackageManager r2 = (android.content.pm.IPackageManager) r2
                r0.<init>(r1)
                goto L_0x0cbb
            L_0x0a7f:
                com.android.systemui.statusbar.notification.collection.coordinator.ColorizedFgsCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.ColorizedFgsCoordinator
                r0.<init>()
                goto L_0x0cbb
            L_0x0a86:
                com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator
                dagger.internal.DelegateFactory r1 = r8.statusBarStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.plugins.statusbar.StatusBarStateController r1 = (com.android.systemui.plugins.statusbar.StatusBarStateController) r1
                javax.inject.Provider r2 = r8.highPriorityProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.notification.collection.provider.HighPriorityProvider r2 = (com.android.systemui.statusbar.notification.collection.provider.HighPriorityProvider) r2
                javax.inject.Provider r3 = r8.providesAlertingHeaderSubcomponentProvider
                java.lang.Object r3 = r3.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl r3 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) r3
                javax.inject.Provider r3 = r3.sectionHeaderNodeControllerImplProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.notification.collection.render.NodeController r3 = (com.android.systemui.statusbar.notification.collection.render.NodeController) r3
                dagger.internal.Preconditions.checkNotNullFromProvides(r3)
                javax.inject.Provider r3 = r8.providesSilentHeaderSubcomponentProvider
                java.lang.Object r3 = r3.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl r3 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) r3
                javax.inject.Provider r3 = r3.sectionHeaderNodeControllerImplProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl r3 = (com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl) r3
                dagger.internal.Preconditions.checkNotNullFromProvides(r3)
                javax.inject.Provider r4 = r8.providesSilentHeaderSubcomponentProvider
                java.lang.Object r4 = r4.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl r4 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) r4
                javax.inject.Provider r4 = r4.sectionHeaderNodeControllerImplProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.statusbar.notification.collection.render.NodeController r4 = (com.android.systemui.statusbar.notification.collection.render.NodeController) r4
                dagger.internal.Preconditions.checkNotNullFromProvides(r4)
                r0.<init>(r1, r2, r3, r4)
                goto L_0x0cbb
            L_0x0ad6:
                com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinator
                javax.inject.Provider r1 = r8.bgDispatcherProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                kotlinx.coroutines.CoroutineDispatcher r6 = (kotlinx.coroutines.CoroutineDispatcher) r6
                javax.inject.Provider r1 = r7.dumpManagerProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.dump.DumpManager r1 = (com.android.systemui.dump.DumpManager) r1
                javax.inject.Provider r2 = r8.headsUpManagerPhoneProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.policy.HeadsUpManager r2 = (com.android.systemui.statusbar.policy.HeadsUpManager) r2
                javax.inject.Provider r3 = r8.keyguardNotificationVisibilityProviderImplProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl r3 = (com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl) r3
                javax.inject.Provider r4 = r8.keyguardRepositoryImplProvider
                java.lang.Object r4 = r4.get()
                r10 = r4
                com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl r10 = (com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl) r10
                javax.inject.Provider r4 = r8.keyguardTransitionRepositoryImplProvider
                java.lang.Object r4 = r4.get()
                r11 = r4
                com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r11 = (com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl) r11
                com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger r12 = new com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r4 = r9.sysUIGoogleSysUIComponentImpl
                javax.inject.Provider r4 = r4.provideUnseenNotificationLogBufferProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.log.LogBuffer r4 = (com.android.systemui.log.LogBuffer) r4
                r12.<init>(r4)
                javax.inject.Provider r4 = r7.applicationScopeProvider
                java.lang.Object r4 = r4.get()
                r13 = r4
                kotlinx.coroutines.CoroutineScope r13 = (kotlinx.coroutines.CoroutineScope) r13
                javax.inject.Provider r4 = r8.sectionHeaderVisibilityProvider
                java.lang.Object r4 = r4.get()
                r14 = r4
                com.android.systemui.statusbar.notification.collection.provider.SectionHeaderVisibilityProvider r14 = (com.android.systemui.statusbar.notification.collection.provider.SectionHeaderVisibilityProvider) r14
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$SwitchingProvider r4 = r8.secureSettingsImplProvider
                java.lang.Object r4 = r4.get()
                r15 = r4
                com.android.systemui.util.settings.SecureSettings r15 = (com.android.systemui.util.settings.SecureSettings) r15
                javax.inject.Provider r4 = r8.seenNotificationsInteractorProvider
                java.lang.Object r4 = r4.get()
                r16 = r4
                com.android.systemui.statusbar.notification.domain.interactor.SeenNotificationsInteractor r16 = (com.android.systemui.statusbar.notification.domain.interactor.SeenNotificationsInteractor) r16
                dagger.internal.DelegateFactory r4 = r8.statusBarStateControllerImplProvider
                java.lang.Object r4 = r4.get()
                r17 = r4
                com.android.systemui.plugins.statusbar.StatusBarStateController r17 = (com.android.systemui.plugins.statusbar.StatusBarStateController) r17
                r5 = r0
                r7 = r1
                r8 = r2
                r9 = r3
                r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
                goto L_0x0cbb
            L_0x0b52:
                com.android.systemui.statusbar.notification.collection.coordinator.HideNotifsForOtherUsersCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.HideNotifsForOtherUsersCoordinator
                dagger.internal.DelegateFactory r1 = r8.notificationLockscreenUserManagerImplProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.NotificationLockscreenUserManager r1 = (com.android.systemui.statusbar.NotificationLockscreenUserManager) r1
                r0.<init>(r1)
                goto L_0x0cbb
            L_0x0b61:
                com.android.systemui.statusbar.notification.collection.coordinator.HideLocallyDismissedNotifsCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.HideLocallyDismissedNotifsCoordinator
                r0.<init>()
                goto L_0x0cbb
            L_0x0b68:
                com.android.systemui.statusbar.notification.collection.coordinator.DataStoreCoordinator r0 = new com.android.systemui.statusbar.notification.collection.coordinator.DataStoreCoordinator
                javax.inject.Provider r1 = r8.notifLiveDataStoreImplProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.notification.collection.NotifLiveDataStoreImpl r1 = (com.android.systemui.statusbar.notification.collection.NotifLiveDataStoreImpl) r1
                r0.<init>(r1)
                goto L_0x0cbb
            L_0x0b77:
                com.android.systemui.statusbar.notification.collection.coordinator.NotifCoordinatorsImpl r0 = new com.android.systemui.statusbar.notification.collection.coordinator.NotifCoordinatorsImpl
                javax.inject.Provider r1 = r8.sectionStyleProvider
                java.lang.Object r1 = r1.get()
                r3 = r1
                com.android.systemui.statusbar.notification.collection.provider.SectionStyleProvider r3 = (com.android.systemui.statusbar.notification.collection.provider.SectionStyleProvider) r3
                dagger.internal.DelegateFactory r1 = r8.featureFlagsClassicReleaseProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.flags.FeatureFlags r1 = (com.android.systemui.flags.FeatureFlags) r1
                javax.inject.Provider r1 = r9.dataStoreCoordinatorProvider
                java.lang.Object r1 = r1.get()
                r4 = r1
                com.android.systemui.statusbar.notification.collection.coordinator.DataStoreCoordinator r4 = (com.android.systemui.statusbar.notification.collection.coordinator.DataStoreCoordinator) r4
                javax.inject.Provider r1 = r9.hideLocallyDismissedNotifsCoordinatorProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                com.android.systemui.statusbar.notification.collection.coordinator.HideLocallyDismissedNotifsCoordinator r5 = (com.android.systemui.statusbar.notification.collection.coordinator.HideLocallyDismissedNotifsCoordinator) r5
                javax.inject.Provider r1 = r9.hideNotifsForOtherUsersCoordinatorProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.systemui.statusbar.notification.collection.coordinator.HideNotifsForOtherUsersCoordinator r6 = (com.android.systemui.statusbar.notification.collection.coordinator.HideNotifsForOtherUsersCoordinator) r6
                javax.inject.Provider r1 = r9.keyguardCoordinatorProvider
                java.lang.Object r1 = r1.get()
                r7 = r1
                com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinator r7 = (com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinator) r7
                javax.inject.Provider r1 = r9.rankingCoordinatorProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator r1 = (com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator) r1
                javax.inject.Provider r2 = r9.colorizedFgsCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r10 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.ColorizedFgsCoordinator r10 = (com.android.systemui.statusbar.notification.collection.coordinator.ColorizedFgsCoordinator) r10
                javax.inject.Provider r2 = r9.deviceProvisionedCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r11 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.DeviceProvisionedCoordinator r11 = (com.android.systemui.statusbar.notification.collection.coordinator.DeviceProvisionedCoordinator) r11
                javax.inject.Provider r2 = r9.bubbleCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r12 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator r12 = (com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator) r12
                javax.inject.Provider r2 = r9.headsUpCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r13 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator r13 = (com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator) r13
                javax.inject.Provider r2 = r9.gutsCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r14 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinator r14 = (com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinator) r14
                javax.inject.Provider r2 = r9.conversationCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r15 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator r15 = (com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator) r15
                javax.inject.Provider r2 = r9.debugModeCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r16 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.DebugModeCoordinator r16 = (com.android.systemui.statusbar.notification.collection.coordinator.DebugModeCoordinator) r16
                javax.inject.Provider r2 = r9.groupCountCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r17 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.GroupCountCoordinator r17 = (com.android.systemui.statusbar.notification.collection.coordinator.GroupCountCoordinator) r17
                javax.inject.Provider r2 = r9.groupWhenCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r18 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator r18 = (com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator) r18
                javax.inject.Provider r2 = r9.mediaCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r19 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.MediaCoordinator r19 = (com.android.systemui.statusbar.notification.collection.coordinator.MediaCoordinator) r19
                javax.inject.Provider r2 = r9.preparationCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r20 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator r20 = (com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator) r20
                javax.inject.Provider r2 = r9.remoteInputCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r21 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinator r21 = (com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinator) r21
                javax.inject.Provider r2 = r9.rowAlertTimeCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r22 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.RowAlertTimeCoordinator r22 = (com.android.systemui.statusbar.notification.collection.coordinator.RowAlertTimeCoordinator) r22
                javax.inject.Provider r2 = r9.rowAppearanceCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r23 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.RowAppearanceCoordinator r23 = (com.android.systemui.statusbar.notification.collection.coordinator.RowAppearanceCoordinator) r23
                javax.inject.Provider r2 = r9.stackCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r24 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.StackCoordinator r24 = (com.android.systemui.statusbar.notification.collection.coordinator.StackCoordinator) r24
                javax.inject.Provider r2 = r8.shadeEventCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r25 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.ShadeEventCoordinator r25 = (com.android.systemui.statusbar.notification.collection.coordinator.ShadeEventCoordinator) r25
                javax.inject.Provider r2 = r9.smartspaceDedupingCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r26 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.SmartspaceDedupingCoordinator r26 = (com.android.systemui.statusbar.notification.collection.coordinator.SmartspaceDedupingCoordinator) r26
                javax.inject.Provider r2 = r9.viewConfigCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r27 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.ViewConfigCoordinator r27 = (com.android.systemui.statusbar.notification.collection.coordinator.ViewConfigCoordinator) r27
                javax.inject.Provider r2 = r8.visualStabilityCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r28 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator r28 = (com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator) r28
                javax.inject.Provider r2 = r9.sensitiveContentCoordinatorImplProvider
                java.lang.Object r2 = r2.get()
                r29 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinatorImpl r29 = (com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinatorImpl) r29
                javax.inject.Provider r2 = r9.dismissibilityCoordinatorProvider
                java.lang.Object r2 = r2.get()
                r30 = r2
                com.android.systemui.statusbar.notification.collection.coordinator.DismissibilityCoordinator r30 = (com.android.systemui.statusbar.notification.collection.coordinator.DismissibilityCoordinator) r30
                javax.inject.Provider r2 = r9.dreamCoordinatorProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator r2 = (com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator) r2
                javax.inject.Provider r2 = r9.notificationStatsLoggerCoordinatorProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.notification.collection.coordinator.NotificationStatsLoggerCoordinator r2 = (com.android.systemui.statusbar.notification.collection.coordinator.NotificationStatsLoggerCoordinator) r2
                r2 = r0
                r8 = r1
                r9 = r10
                r10 = r11
                r11 = r12
                r12 = r13
                r13 = r14
                r14 = r15
                r15 = r16
                r16 = r17
                r17 = r18
                r18 = r19
                r19 = r20
                r20 = r21
                r21 = r22
                r22 = r23
                r23 = r24
                r24 = r25
                r25 = r26
                r26 = r27
                r27 = r28
                r28 = r29
                r29 = r30
                r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
            L_0x0cbb:
                return r0
            L_0x0cbc:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl r9 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl) r9
                switch(r6) {
                    case 0: goto L_0x100b;
                    case 1: goto L_0x0fbc;
                    case 2: goto L_0x0fac;
                    case 3: goto L_0x0f8d;
                    case 4: goto L_0x0f7e;
                    case 5: goto L_0x0eba;
                    case 6: goto L_0x0e7a;
                    case 7: goto L_0x0e13;
                    case 8: goto L_0x0d48;
                    case 9: goto L_0x0d27;
                    case 10: goto L_0x0d0b;
                    case 11: goto L_0x0cf4;
                    case 12: goto L_0x0cc7;
                    default: goto L_0x0cc1;
                }
            L_0x0cc1:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r6)
                throw r0
            L_0x0cc7:
                com.android.systemui.doze.DozeSuppressor r0 = new com.android.systemui.doze.DozeSuppressor
                javax.inject.Provider r1 = r8.dozeServiceHostProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.phone.DozeServiceHost r1 = (com.android.systemui.statusbar.phone.DozeServiceHost) r1
                android.hardware.display.AmbientDisplayConfiguration r9 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m972$$Nest$mambientDisplayConfiguration(r7)
                javax.inject.Provider r2 = r8.dozeLogProvider
                java.lang.Object r2 = r2.get()
                r10 = r2
                com.android.systemui.doze.DozeLog r10 = (com.android.systemui.doze.DozeLog) r10
                dagger.internal.DelegateFactory r2 = r8.biometricUnlockControllerProvider
                dagger.Lazy r11 = dagger.internal.DoubleCheck.lazy(r2)
                dagger.internal.DelegateFactory r2 = r8.provideUserTrackerProvider
                java.lang.Object r2 = r2.get()
                r12 = r2
                com.android.systemui.settings.UserTracker r12 = (com.android.systemui.settings.UserTracker) r12
                r7 = r0
                r8 = r1
                r7.<init>(r8, r9, r10, r11, r12)
                goto L_0x10f2
            L_0x0cf4:
                com.android.systemui.doze.DozeAuthRemover r0 = new com.android.systemui.doze.DozeAuthRemover
                dagger.internal.DelegateFactory r1 = r8.keyguardUpdateMonitorProvider
                java.lang.Object r1 = r1.get()
                com.android.keyguard.KeyguardUpdateMonitor r1 = (com.android.keyguard.KeyguardUpdateMonitor) r1
                javax.inject.Provider r2 = r8.selectedUserInteractorProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.user.domain.interactor.SelectedUserInteractor r2 = (com.android.systemui.user.domain.interactor.SelectedUserInteractor) r2
                r0.<init>(r1, r2)
                goto L_0x10f2
            L_0x0d0b:
                android.hardware.display.AmbientDisplayConfiguration r0 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m972$$Nest$mambientDisplayConfiguration(r7)
                dagger.internal.DelegateFactory r1 = r8.dockObserverProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.dock.DockManager r1 = (com.android.systemui.dock.DockManager) r1
                dagger.internal.DelegateFactory r2 = r8.provideUserTrackerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.settings.UserTracker r2 = (com.android.systemui.settings.UserTracker) r2
                com.android.systemui.doze.DozeDockHandler r3 = new com.android.systemui.doze.DozeDockHandler
                r3.<init>(r0, r1, r2)
                r0 = r3
                goto L_0x10f2
            L_0x0d27:
                com.android.systemui.doze.DozeWallpaperState r0 = new com.android.systemui.doze.DozeWallpaperState
                java.lang.String r1 = "wallpaper"
                android.os.IBinder r1 = android.os.ServiceManager.getService(r1)
                android.app.IWallpaperManager r1 = android.app.IWallpaperManager.Stub.asInterface(r1)
                dagger.internal.DelegateFactory r2 = r8.biometricUnlockControllerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.phone.BiometricUnlockController r2 = (com.android.systemui.statusbar.phone.BiometricUnlockController) r2
                dagger.internal.DelegateFactory r3 = r8.dozeParametersProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.phone.DozeParameters r3 = (com.android.systemui.statusbar.phone.DozeParameters) r3
                r0.<init>(r1, r2, r3)
                goto L_0x10f2
            L_0x0d48:
                com.android.systemui.doze.DozeScreenBrightness r0 = new com.android.systemui.doze.DozeScreenBrightness
                android.content.Context r6 = r7.context
                javax.inject.Provider r4 = r9.providesWrappedServiceProvider
                java.lang.Object r4 = r4.get()
                r7 = r4
                com.android.systemui.doze.DozeMachine$Service r7 = (com.android.systemui.doze.DozeMachine.Service) r7
                javax.inject.Provider r4 = r8.asyncSensorManagerProvider
                java.lang.Object r4 = r4.get()
                r10 = r4
                com.android.systemui.util.sensors.AsyncSensorManager r10 = (com.android.systemui.util.sensors.AsyncSensorManager) r10
                java.lang.Object r4 = r9.sysUIGoogleSysUIComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r4 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) r4
                javax.inject.Provider r11 = r4.asyncSensorManagerProvider
                java.lang.Object r11 = r11.get()
                com.android.systemui.util.sensors.AsyncSensorManager r11 = (com.android.systemui.util.sensors.AsyncSensorManager) r11
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r9 = r9.sysUIGoogleGlobalRootComponentImpl
                android.content.Context r9 = r9.context
                dagger.internal.DelegateFactory r4 = r4.dozeParametersProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.statusbar.phone.DozeParameters r4 = (com.android.systemui.statusbar.phone.DozeParameters) r4
                android.content.res.Resources r4 = r4.mResources
                r12 = 2130903122(0x7f030052, float:1.7413053E38)
                java.lang.String[] r4 = r4.getStringArray(r12)
                int r12 = r4.length
                r13 = 2131952478(0x7f13035e, float:1.95414E38)
                if (r12 == 0) goto L_0x0db9
                java.util.Optional[] r1 = new java.util.Optional[r1]
                java.util.Optional r3 = java.util.Optional.empty()
                java.util.Arrays.fill(r1, r3)
                java.util.HashMap r3 = new java.util.HashMap
                r3.<init>()
            L_0x0d93:
                int r12 = r4.length
                if (r2 >= r12) goto L_0x0dc9
                r12 = r4[r2]
                boolean r14 = r3.containsKey(r12)
                if (r14 != 0) goto L_0x0daf
                java.lang.String r14 = r9.getString(r13)
                r15 = r4[r2]
                android.hardware.Sensor r14 = com.android.systemui.doze.DozeSensors.findSensor(r11, r14, r15)
                java.util.Optional r14 = java.util.Optional.ofNullable(r14)
                r3.put(r12, r14)
            L_0x0daf:
                java.lang.Object r12 = r3.get(r12)
                java.util.Optional r12 = (java.util.Optional) r12
                r1[r2] = r12
                int r2 = r2 + r5
                goto L_0x0d93
            L_0x0db9:
                java.util.Optional[] r1 = new java.util.Optional[r5]
                java.lang.String r4 = r9.getString(r13)
                android.hardware.Sensor r3 = com.android.systemui.doze.DozeSensors.findSensor(r11, r4, r3)
                java.util.Optional r3 = java.util.Optional.ofNullable(r3)
                r1[r2] = r3
            L_0x0dc9:
                javax.inject.Provider r2 = r8.dozeServiceHostProvider
                java.lang.Object r2 = r2.get()
                r9 = r2
                com.android.systemui.statusbar.phone.DozeServiceHost r9 = (com.android.systemui.statusbar.phone.DozeServiceHost) r9
                android.os.Handler r2 = new android.os.Handler
                r2.<init>()
                javax.inject.Provider r3 = r8.alwaysOnDisplayPolicyProvider
                java.lang.Object r3 = r3.get()
                r11 = r3
                com.android.systemui.doze.AlwaysOnDisplayPolicy r11 = (com.android.systemui.doze.AlwaysOnDisplayPolicy) r11
                javax.inject.Provider r3 = r8.wakefulnessLifecycleProvider
                java.lang.Object r3 = r3.get()
                r12 = r3
                com.android.systemui.keyguard.WakefulnessLifecycle r12 = (com.android.systemui.keyguard.WakefulnessLifecycle) r12
                dagger.internal.DelegateFactory r3 = r8.dozeParametersProvider
                java.lang.Object r3 = r3.get()
                r13 = r3
                com.android.systemui.statusbar.phone.DozeParameters r13 = (com.android.systemui.statusbar.phone.DozeParameters) r13
                javax.inject.Provider r3 = r8.devicePostureControllerImplProvider
                java.lang.Object r3 = r3.get()
                r14 = r3
                com.android.systemui.statusbar.policy.DevicePostureController r14 = (com.android.systemui.statusbar.policy.DevicePostureController) r14
                javax.inject.Provider r3 = r8.dozeLogProvider
                java.lang.Object r3 = r3.get()
                r15 = r3
                com.android.systemui.doze.DozeLog r15 = (com.android.systemui.doze.DozeLog) r15
                com.android.systemui.util.settings.SystemSettingsImpl r16 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1690$$Nest$msystemSettingsImpl(r8)
                r4 = r0
                r5 = r6
                r6 = r7
                r7 = r10
                r8 = r1
                r10 = r2
                r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
                goto L_0x10f2
            L_0x0e13:
                com.android.systemui.doze.DozeScreenState r0 = new com.android.systemui.doze.DozeScreenState
                javax.inject.Provider r1 = r9.providesWrappedServiceProvider
                java.lang.Object r1 = r1.get()
                r18 = r1
                com.android.systemui.doze.DozeMachine$Service r18 = (com.android.systemui.doze.DozeMachine.Service) r18
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r1 = r7.provideMainHandlerProvider
                java.lang.Object r1 = r1.get()
                r19 = r1
                android.os.Handler r19 = (android.os.Handler) r19
                javax.inject.Provider r1 = r8.dozeServiceHostProvider
                java.lang.Object r1 = r1.get()
                r20 = r1
                com.android.systemui.statusbar.phone.DozeServiceHost r20 = (com.android.systemui.statusbar.phone.DozeServiceHost) r20
                dagger.internal.DelegateFactory r1 = r8.dozeParametersProvider
                java.lang.Object r1 = r1.get()
                r21 = r1
                com.android.systemui.statusbar.phone.DozeParameters r21 = (com.android.systemui.statusbar.phone.DozeParameters) r21
                javax.inject.Provider r1 = r9.providesDozeWakeLockProvider
                java.lang.Object r1 = r1.get()
                r22 = r1
                com.android.systemui.util.wakelock.WakeLock r22 = (com.android.systemui.util.wakelock.WakeLock) r22
                dagger.internal.DelegateFactory r1 = r8.authControllerProvider
                java.lang.Object r1 = r1.get()
                r23 = r1
                com.android.systemui.biometrics.AuthController r23 = (com.android.systemui.biometrics.AuthController) r23
                dagger.internal.DelegateFactory r1 = r8.udfpsControllerProvider
                javax.inject.Provider r2 = r8.dozeLogProvider
                java.lang.Object r2 = r2.get()
                r25 = r2
                com.android.systemui.doze.DozeLog r25 = (com.android.systemui.doze.DozeLog) r25
                javax.inject.Provider r2 = r9.dozeScreenBrightnessProvider
                java.lang.Object r2 = r2.get()
                r26 = r2
                com.android.systemui.doze.DozeScreenBrightness r26 = (com.android.systemui.doze.DozeScreenBrightness) r26
                javax.inject.Provider r2 = r8.selectedUserInteractorProvider
                java.lang.Object r2 = r2.get()
                r27 = r2
                com.android.systemui.user.domain.interactor.SelectedUserInteractor r27 = (com.android.systemui.user.domain.interactor.SelectedUserInteractor) r27
                r17 = r0
                r24 = r1
                r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
                goto L_0x10f2
            L_0x0e7a:
                com.android.systemui.doze.DozeUi r0 = new com.android.systemui.doze.DozeUi
                android.content.Context r3 = r7.context
                javax.inject.Provider r1 = r7.provideAlarmManagerProvider
                java.lang.Object r1 = r1.get()
                r4 = r1
                android.app.AlarmManager r4 = (android.app.AlarmManager) r4
                javax.inject.Provider r1 = r9.providesDozeWakeLockProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                com.android.systemui.util.wakelock.WakeLock r5 = (com.android.systemui.util.wakelock.WakeLock) r5
                javax.inject.Provider r1 = r8.dozeServiceHostProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.systemui.statusbar.phone.DozeServiceHost r6 = (com.android.systemui.statusbar.phone.DozeServiceHost) r6
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r1 = r7.provideMainHandlerProvider
                java.lang.Object r1 = r1.get()
                r7 = r1
                android.os.Handler r7 = (android.os.Handler) r7
                dagger.internal.DelegateFactory r1 = r8.dozeParametersProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.phone.DozeParameters r1 = (com.android.systemui.statusbar.phone.DozeParameters) r1
                javax.inject.Provider r2 = r8.dozeLogProvider
                java.lang.Object r2 = r2.get()
                r9 = r2
                com.android.systemui.doze.DozeLog r9 = (com.android.systemui.doze.DozeLog) r9
                r2 = r0
                r8 = r1
                r2.<init>(r3, r4, r5, r6, r7, r8, r9)
                goto L_0x10f2
            L_0x0eba:
                com.android.systemui.doze.DozeTriggers r0 = new com.android.systemui.doze.DozeTriggers
                android.content.Context r11 = r7.context
                javax.inject.Provider r1 = r8.dozeServiceHostProvider
                java.lang.Object r1 = r1.get()
                r12 = r1
                com.android.systemui.statusbar.phone.DozeServiceHost r12 = (com.android.systemui.statusbar.phone.DozeServiceHost) r12
                android.hardware.display.AmbientDisplayConfiguration r13 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m972$$Nest$mambientDisplayConfiguration(r7)
                dagger.internal.DelegateFactory r1 = r8.dozeParametersProvider
                java.lang.Object r1 = r1.get()
                r14 = r1
                com.android.systemui.statusbar.phone.DozeParameters r14 = (com.android.systemui.statusbar.phone.DozeParameters) r14
                javax.inject.Provider r1 = r8.asyncSensorManagerProvider
                java.lang.Object r1 = r1.get()
                r15 = r1
                com.android.systemui.util.sensors.AsyncSensorManager r15 = (com.android.systemui.util.sensors.AsyncSensorManager) r15
                javax.inject.Provider r1 = r9.providesDozeWakeLockProvider
                java.lang.Object r1 = r1.get()
                r16 = r1
                com.android.systemui.util.wakelock.WakeLock r16 = (com.android.systemui.util.wakelock.WakeLock) r16
                dagger.internal.DelegateFactory r1 = r8.dockObserverProvider
                java.lang.Object r1 = r1.get()
                r17 = r1
                com.android.systemui.dock.DockManager r17 = (com.android.systemui.dock.DockManager) r17
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$SwitchingProvider r1 = r8.provideProximitySensorProvider
                java.lang.Object r1 = r1.get()
                r18 = r1
                com.android.systemui.util.sensors.ProximitySensor r18 = (com.android.systemui.util.sensors.ProximitySensor) r18
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$SwitchingProvider r1 = r8.provideProximitySensorProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.util.sensors.ProximitySensor r1 = (com.android.systemui.util.sensors.ProximitySensor) r1
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r8.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r2 = r2.provideMainDelayableExecutorProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.util.concurrency.DelayableExecutor r2 = (com.android.systemui.util.concurrency.DelayableExecutor) r2
                com.android.systemui.util.sensors.ProximityCheck r3 = new com.android.systemui.util.sensors.ProximityCheck
                r3.<init>(r1, r2)
                javax.inject.Provider r1 = r8.dozeLogProvider
                java.lang.Object r1 = r1.get()
                r20 = r1
                com.android.systemui.doze.DozeLog r20 = (com.android.systemui.doze.DozeLog) r20
                javax.inject.Provider r1 = r8.broadcastDispatcherProvider
                java.lang.Object r1 = r1.get()
                r21 = r1
                com.android.systemui.broadcast.BroadcastDispatcher r21 = (com.android.systemui.broadcast.BroadcastDispatcher) r21
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$SwitchingProvider r1 = r8.secureSettingsImplProvider
                java.lang.Object r1 = r1.get()
                r22 = r1
                com.android.systemui.util.settings.SecureSettings r22 = (com.android.systemui.util.settings.SecureSettings) r22
                dagger.internal.DelegateFactory r1 = r8.authControllerProvider
                java.lang.Object r1 = r1.get()
                r23 = r1
                com.android.systemui.biometrics.AuthController r23 = (com.android.systemui.biometrics.AuthController) r23
                javax.inject.Provider r1 = r7.provideUiEventLoggerProvider
                java.lang.Object r1 = r1.get()
                r24 = r1
                com.android.internal.logging.UiEventLogger r24 = (com.android.internal.logging.UiEventLogger) r24
                dagger.internal.DelegateFactory r1 = r8.sessionTrackerProvider
                java.lang.Object r1 = r1.get()
                r25 = r1
                com.android.systemui.log.SessionTracker r25 = (com.android.systemui.log.SessionTracker) r25
                dagger.internal.DelegateFactory r1 = r8.keyguardStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                r26 = r1
                com.android.systemui.statusbar.policy.KeyguardStateController r26 = (com.android.systemui.statusbar.policy.KeyguardStateController) r26
                javax.inject.Provider r1 = r8.devicePostureControllerImplProvider
                java.lang.Object r1 = r1.get()
                r27 = r1
                com.android.systemui.statusbar.policy.DevicePostureController r27 = (com.android.systemui.statusbar.policy.DevicePostureController) r27
                dagger.internal.DelegateFactory r1 = r8.provideUserTrackerProvider
                java.lang.Object r1 = r1.get()
                r28 = r1
                com.android.systemui.settings.UserTracker r28 = (com.android.systemui.settings.UserTracker) r28
                javax.inject.Provider r1 = r8.selectedUserInteractorProvider
                java.lang.Object r1 = r1.get()
                r29 = r1
                com.android.systemui.user.domain.interactor.SelectedUserInteractor r29 = (com.android.systemui.user.domain.interactor.SelectedUserInteractor) r29
                r10 = r0
                r19 = r3
                r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
                goto L_0x10f2
            L_0x0f7e:
                com.android.systemui.doze.DozeFalsingManagerAdapter r0 = new com.android.systemui.doze.DozeFalsingManagerAdapter
                javax.inject.Provider r1 = r8.providesFalsingCollectorLegacyProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.classifier.FalsingCollector r1 = (com.android.systemui.classifier.FalsingCollector) r1
                r0.<init>(r1)
                goto L_0x10f2
            L_0x0f8d:
                com.android.systemui.doze.DozePauser r0 = new com.android.systemui.doze.DozePauser
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r1 = r7.provideMainHandlerProvider
                java.lang.Object r1 = r1.get()
                android.os.Handler r1 = (android.os.Handler) r1
                javax.inject.Provider r2 = r7.provideAlarmManagerProvider
                java.lang.Object r2 = r2.get()
                android.app.AlarmManager r2 = (android.app.AlarmManager) r2
                javax.inject.Provider r3 = r8.alwaysOnDisplayPolicyProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.doze.AlwaysOnDisplayPolicy r3 = (com.android.systemui.doze.AlwaysOnDisplayPolicy) r3
                r0.<init>(r1, r2, r3)
                goto L_0x10f2
            L_0x0fac:
                javax.inject.Provider r0 = r8.factoryProvider13
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11) r0
                java.lang.String r1 = "Doze"
                com.android.systemui.util.wakelock.DelayedWakeLock r0 = r0.create(r1)
                goto L_0x10f2
            L_0x0fbc:
                java.lang.Object r0 = r9.dozeMachineService
                com.android.systemui.doze.DozeMachine$Service r0 = (com.android.systemui.doze.DozeMachine.Service) r0
                javax.inject.Provider r1 = r8.dozeServiceHostProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.phone.DozeServiceHost r1 = (com.android.systemui.statusbar.phone.DozeServiceHost) r1
                dagger.internal.DelegateFactory r2 = r8.dozeParametersProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.phone.DozeParameters r2 = (com.android.systemui.statusbar.phone.DozeParameters) r2
                javax.inject.Provider r3 = r7.provideUiBackgroundExecutorProvider
                java.lang.Object r3 = r3.get()
                java.util.concurrent.Executor r3 = (java.util.concurrent.Executor) r3
                com.android.systemui.doze.DozeBrightnessHostForwarder r4 = new com.android.systemui.doze.DozeBrightnessHostForwarder
                r4.<init>(r0, r1, r3)
                android.content.res.Resources r0 = r2.mResources
                r1 = 2131034205(0x7f05005d, float:1.767892E38)
                boolean r0 = r0.getBoolean(r1)
                java.lang.String r1 = "doze.display.supported"
                boolean r0 = android.os.SystemProperties.getBoolean(r1, r0)
                r0 = r0 ^ r5
                if (r0 == 0) goto L_0x0ff5
                com.android.systemui.doze.DozeScreenStatePreventingAdapter r0 = new com.android.systemui.doze.DozeScreenStatePreventingAdapter
                r0.<init>(r4, r3)
                r4 = r0
            L_0x0ff5:
                android.content.res.Resources r0 = r2.mResources
                r1 = 2131034212(0x7f050064, float:1.7678935E38)
                boolean r0 = r0.getBoolean(r1)
                r0 = r0 ^ r5
                if (r0 == 0) goto L_0x1008
                com.android.systemui.doze.DozeSuspendScreenStatePreventingAdapter r0 = new com.android.systemui.doze.DozeSuspendScreenStatePreventingAdapter
                r0.<init>(r4, r3)
                goto L_0x10f2
            L_0x1008:
                r0 = r4
                goto L_0x10f2
            L_0x100b:
                com.android.systemui.doze.DozeMachine r0 = new com.android.systemui.doze.DozeMachine
                javax.inject.Provider r3 = r9.providesWrappedServiceProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.doze.DozeMachine$Service r3 = (com.android.systemui.doze.DozeMachine.Service) r3
                android.hardware.display.AmbientDisplayConfiguration r6 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m972$$Nest$mambientDisplayConfiguration(r7)
                javax.inject.Provider r7 = r9.providesDozeWakeLockProvider
                java.lang.Object r7 = r7.get()
                com.android.systemui.util.wakelock.WakeLock r7 = (com.android.systemui.util.wakelock.WakeLock) r7
                javax.inject.Provider r10 = r8.wakefulnessLifecycleProvider
                java.lang.Object r10 = r10.get()
                com.android.systemui.keyguard.WakefulnessLifecycle r10 = (com.android.systemui.keyguard.WakefulnessLifecycle) r10
                javax.inject.Provider r11 = r8.dozeLogProvider
                java.lang.Object r11 = r11.get()
                com.android.systemui.doze.DozeLog r11 = (com.android.systemui.doze.DozeLog) r11
                dagger.internal.DelegateFactory r12 = r8.dockObserverProvider
                java.lang.Object r12 = r12.get()
                com.android.systemui.dock.DockManager r12 = (com.android.systemui.dock.DockManager) r12
                javax.inject.Provider r13 = r8.dozeServiceHostProvider
                java.lang.Object r13 = r13.get()
                com.android.systemui.statusbar.phone.DozeServiceHost r13 = (com.android.systemui.statusbar.phone.DozeServiceHost) r13
                javax.inject.Provider r14 = r9.dozePauserProvider
                java.lang.Object r14 = r14.get()
                com.android.systemui.doze.DozePauser r14 = (com.android.systemui.doze.DozePauser) r14
                javax.inject.Provider r15 = r9.dozeFalsingManagerAdapterProvider
                java.lang.Object r15 = r15.get()
                com.android.systemui.doze.DozeFalsingManagerAdapter r15 = (com.android.systemui.doze.DozeFalsingManagerAdapter) r15
                javax.inject.Provider r4 = r9.dozeTriggersProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.doze.DozeTriggers r4 = (com.android.systemui.doze.DozeTriggers) r4
                javax.inject.Provider r1 = r9.dozeUiProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.doze.DozeUi r1 = (com.android.systemui.doze.DozeUi) r1
                javax.inject.Provider r5 = r9.dozeScreenStateProvider
                java.lang.Object r5 = r5.get()
                com.android.systemui.doze.DozeScreenState r5 = (com.android.systemui.doze.DozeScreenState) r5
                javax.inject.Provider r2 = r9.dozeScreenBrightnessProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.doze.DozeScreenBrightness r2 = (com.android.systemui.doze.DozeScreenBrightness) r2
                r40 = r13
                javax.inject.Provider r13 = r9.dozeWallpaperStateProvider
                java.lang.Object r13 = r13.get()
                com.android.systemui.doze.DozeWallpaperState r13 = (com.android.systemui.doze.DozeWallpaperState) r13
                r20 = r12
                java.lang.Object r12 = r9.dozeDockHandlerProvider
                javax.inject.Provider r12 = (javax.inject.Provider) r12
                java.lang.Object r12 = r12.get()
                com.android.systemui.doze.DozeDockHandler r12 = (com.android.systemui.doze.DozeDockHandler) r12
                r21 = r11
                java.lang.Object r11 = r9.dozeAuthRemoverProvider
                javax.inject.Provider r11 = (javax.inject.Provider) r11
                java.lang.Object r11 = r11.get()
                com.android.systemui.doze.DozeAuthRemover r11 = (com.android.systemui.doze.DozeAuthRemover) r11
                r22 = r10
                java.lang.Object r10 = r9.dozeSuppressorProvider
                javax.inject.Provider r10 = (javax.inject.Provider) r10
                java.lang.Object r10 = r10.get()
                com.android.systemui.doze.DozeSuppressor r10 = (com.android.systemui.doze.DozeSuppressor) r10
                java.lang.Object r9 = r9.sysUIGoogleSysUIComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r9 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) r9
                javax.inject.Provider r9 = r9.dozeTransitionListenerProvider
                java.lang.Object r9 = r9.get()
                com.android.systemui.doze.DozeTransitionListener r9 = (com.android.systemui.doze.DozeTransitionListener) r9
                r23 = r7
                r7 = 11
                com.android.systemui.doze.DozeMachine$Part[] r7 = new com.android.systemui.doze.DozeMachine.Part[r7]
                r19 = 0
                r7[r19] = r14
                r14 = 1
                r7[r14] = r15
                r14 = 2
                r7[r14] = r4
                r4 = 3
                r7[r4] = r1
                r1 = 4
                r7[r1] = r5
                r1 = 5
                r7[r1] = r2
                r1 = 6
                r7[r1] = r13
                r1 = 7
                r7[r1] = r12
                r1 = 8
                r7[r1] = r11
                r1 = 9
                r7[r1] = r10
                r1 = 10
                r7[r1] = r9
                r9 = r7
                com.android.systemui.doze.DozeMachine$Part[] r9 = (com.android.systemui.doze.DozeMachine.Part[]) r9
                dagger.internal.DelegateFactory r1 = r8.provideUserTrackerProvider
                java.lang.Object r1 = r1.get()
                r10 = r1
                com.android.systemui.settings.UserTracker r10 = (com.android.systemui.settings.UserTracker) r10
                r1 = r0
                r2 = r3
                r3 = r6
                r4 = r23
                r5 = r22
                r6 = r21
                r7 = r20
                r8 = r40
                r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            L_0x10f2:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider.get():java.lang.Object");
        }
    }

    public DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, View view, int i) {
        this.$r8$classId = i;
        switch (i) {
            case 3:
                this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
                this.dozeMachineService = view;
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
                this.providesWrappedServiceProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 2, 8));
                this.providesDozeWakeLockProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 3, 8));
                this.dozePauserProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 4, 8));
                this.dozeFalsingManagerAdapterProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 1, 8));
                this.dozeTriggersProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 5, 8));
                this.dozeUiProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 0, 8));
                this.sysUIGoogleSysUIComponentImpl = new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 7, 8);
                this.dozeScreenBrightnessProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 6, 8));
                this.dozeScreenStateProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 8, 8));
                this.dozeWallpaperStateProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 10, 8));
                this.dozeDockHandlerProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 9, 8));
                this.dozeAuthRemoverProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 12, 8));
                this.dozeSuppressorProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 11, 8));
                this.dozeMachineProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 13, 8));
                return;
            default:
                this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
                this.dozeMachineService = view;
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
                this.providesWrappedServiceProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 2, 7));
                this.providesDozeWakeLockProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 3, 7));
                this.dozePauserProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 4, 7));
                this.dozeFalsingManagerAdapterProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 1, 7));
                this.dozeTriggersProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 5, 7));
                this.dozeUiProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 0, 7));
                this.sysUIGoogleSysUIComponentImpl = new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 7, 7);
                this.dozeScreenBrightnessProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 6, 7));
                this.dozeScreenStateProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 8, 7));
                this.dozeWallpaperStateProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 10, 7));
                this.dozeDockHandlerProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 9, 7));
                this.dozeAuthRemoverProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 12, 7));
                this.dozeSuppressorProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 11, 7));
                this.dozeMachineProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 13, 7));
                return;
        }
    }

    public QSAnimator getQSAnimator() {
        switch (this.$r8$classId) {
            case 2:
                return (QSAnimator) this.dozeScreenStateProvider.get();
            default:
                return (QSAnimator) this.dozeScreenStateProvider.get();
        }
    }

    public QSContainerImplController getQSContainerImplController() {
        switch (this.$r8$classId) {
            case 2:
                return (QSContainerImplController) ((Provider) this.dozeDockHandlerProvider).get();
            default:
                return (QSContainerImplController) ((Provider) this.dozeDockHandlerProvider).get();
        }
    }

    public QSCustomizerController getQSCustomizerController() {
        switch (this.$r8$classId) {
            case 2:
                return (QSCustomizerController) this.dozeFalsingManagerAdapterProvider.get();
            default:
                return (QSCustomizerController) this.dozeFalsingManagerAdapterProvider.get();
        }
    }

    public QSFooterViewController getQSFooter() {
        switch (this.$r8$classId) {
            case 2:
                return (QSFooterViewController) ((Provider) this.dozeSuppressorProvider).get();
            default:
                return (QSFooterViewController) ((Provider) this.dozeSuppressorProvider).get();
        }
    }

    public QSPanelController getQSPanelController() {
        switch (this.$r8$classId) {
            case 2:
                return (QSPanelController) this.dozeUiProvider.get();
            default:
                return (QSPanelController) this.dozeUiProvider.get();
        }
    }

    public QSSquishinessController getQSSquishinessController() {
        switch (this.$r8$classId) {
            case 2:
                return (QSSquishinessController) ((Provider) this.dozeMachineProvider).get();
            default:
                return (QSSquishinessController) ((Provider) this.dozeMachineProvider).get();
        }
    }

    public QuickQSPanelController getQuickQSPanelController() {
        switch (this.$r8$classId) {
            case 2:
                return (QuickQSPanelController) this.dozeScreenBrightnessProvider.get();
            default:
                return (QuickQSPanelController) this.dozeScreenBrightnessProvider.get();
        }
    }

    public View getRootView() {
        switch (this.$r8$classId) {
            case 2:
                return (View) this.dozeMachineService;
            default:
                return (View) this.dozeMachineService;
        }
    }

    public DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, DozeMachine.Service service) {
        this.$r8$classId = 0;
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.dozeMachineService = service;
        this.providesWrappedServiceProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 1, 0));
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.providesDozeWakeLockProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 2, 0));
        this.dozePauserProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 3, 0));
        this.dozeFalsingManagerAdapterProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 4, 0));
        this.dozeTriggersProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 5, 0));
        this.dozeUiProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 6, 0));
        this.dozeScreenBrightnessProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 8, 0));
        this.dozeScreenStateProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 7, 0));
        this.dozeWallpaperStateProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 9, 0));
        this.dozeDockHandlerProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 10, 0));
        this.dozeAuthRemoverProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 11, 0));
        this.dozeSuppressorProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 12, 0));
        this.dozeMachineProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 0, 0));
    }

    public DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, LifecycleOwner lifecycleOwner, ComplicationHostViewController complicationHostViewController, TouchInsetManager touchInsetManager, Set set) {
        this.$r8$classId = 1;
        this.dozeMachineProvider = this;
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.dozeMachineService = complicationHostViewController;
        this.dozeDockHandlerProvider = touchInsetManager;
        this.dozeAuthRemoverProvider = lifecycleOwner;
        this.dozeSuppressorProvider = set;
        this.providesWrappedServiceProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 1, 2));
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.providesDozeWakeLockProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 2, 2));
        this.dozePauserProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 4, 2));
        this.dozeFalsingManagerAdapterProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 3, 2));
        this.dozeTriggersProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 5, 2));
        this.dozeUiProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 6, 2));
        this.dozeScreenBrightnessProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 7, 2));
        this.dozeScreenStateProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 0, 2));
        this.dozeWallpaperStateProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 8, 2));
    }
}
