package com.google.android.systemui.dagger;

import android.content.Context;
import android.view.Display;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.keyguard.ClockEventController;
import com.android.keyguard.KeyguardClockSwitch;
import com.android.keyguard.KeyguardClockSwitchController;
import com.android.keyguard.KeyguardSliceViewController;
import com.android.keyguard.KeyguardStatusView;
import com.android.keyguard.KeyguardStatusViewController;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.ui.view.InWindowLauncherUnlockAnimationManager;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shared.clocks.ClockRegistry;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.AlwaysOnDisplayNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBindingFailureTracker;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.NotificationIconAreaController;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Optional;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl {
    public final Object display;
    public final Object keyguardSliceViewControllerProvider;
    public final Object keyguardStatusViewComponentImpl;
    public final Object presentation;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    public DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, KeyguardStatusView keyguardStatusView, Display display2) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.presentation = keyguardStatusView;
        this.display = display2;
        this.keyguardSliceViewControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 0, 7));
    }

    public final KeyguardClockSwitchController getKeyguardClockSwitchController() {
        SecureSettings secureSettings;
        DelayableExecutor delayableExecutor;
        KeyguardClockSwitch keyguardClockSwitch = (KeyguardClockSwitch) ((KeyguardStatusView) this.presentation).findViewById(2131362779);
        Preconditions.checkNotNullFromProvides(keyguardClockSwitch);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        StatusBarStateController statusBarStateController = (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get();
        ClockRegistry clockRegistry = (ClockRegistry) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClockRegistryProvider.get();
        KeyguardSliceViewController keyguardSliceViewController = (KeyguardSliceViewController) ((Provider) this.keyguardSliceViewControllerProvider).get();
        NotificationIconAreaController notificationIconAreaController = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconAreaController();
        LockscreenSmartspaceController lockscreenSmartspaceController = (LockscreenSmartspaceController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenSmartspaceControllerProvider.get();
        SystemBarUtilsState systemBarUtilsState = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemBarUtilsState();
        ScreenOffAnimationController screenOffAnimationController = (ScreenOffAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenOffAnimationControllerProvider.get();
        StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker = (StatusBarIconViewBindingFailureTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarIconViewBindingFailureTrackerProvider.get();
        KeyguardUnlockAnimationController keyguardUnlockAnimationController = (KeyguardUnlockAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUnlockAnimationControllerProvider.get();
        SecureSettings secureSettings2 = (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DelayableExecutor delayableExecutor2 = (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get();
        Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get();
        DumpManager dumpManager = (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get();
        KeyguardInteractor keyguardInteractor = (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get();
        KeyguardTransitionInteractor keyguardTransitionInteractor = (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get();
        BroadcastDispatcher broadcastDispatcher = (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get();
        BatteryController batteryController = (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get();
        KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get();
        ConfigurationController configurationController = (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get();
        Display display2 = (Display) Optional.of((Display) this.display).orElse((Object) null);
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        if (display2 == null) {
            secureSettings = secureSettings2;
            delayableExecutor = delayableExecutor2;
        } else {
            delayableExecutor = delayableExecutor2;
            secureSettings = secureSettings2;
            if (context.getDisplayId() != display2.getDisplayId()) {
                context = context.createDisplayContext(display2);
                Intrinsics.checkNotNull(context);
            }
        }
        ClockEventController clockEventController = new ClockEventController(keyguardInteractor, keyguardTransitionInteractor, broadcastDispatcher, batteryController, keyguardUpdateMonitor, configurationController, context.getResources(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardSmallClockLogProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardLargeClockLogProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (ZenModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.zenModeControllerImplProvider.get());
        ConfigurationState configurationState = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationState();
        DozeParameters dozeParameters = (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get();
        AlwaysOnDisplayNotificationIconViewStore alwaysOnDisplayNotificationIconViewStore = new AlwaysOnDisplayNotificationIconViewStore((NotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifCollectionProvider.get());
        StatusBarStateController statusBarStateController2 = statusBarStateController;
        ClockRegistry clockRegistry2 = clockRegistry;
        KeyguardSliceViewController keyguardSliceViewController2 = keyguardSliceViewController;
        NotificationIconAreaController notificationIconAreaController2 = notificationIconAreaController;
        LockscreenSmartspaceController lockscreenSmartspaceController2 = lockscreenSmartspaceController;
        SystemBarUtilsState systemBarUtilsState2 = systemBarUtilsState;
        ScreenOffAnimationController screenOffAnimationController2 = screenOffAnimationController;
        StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker2 = statusBarIconViewBindingFailureTracker;
        KeyguardUnlockAnimationController keyguardUnlockAnimationController2 = keyguardUnlockAnimationController;
        SecureSettings secureSettings3 = secureSettings;
        AlwaysOnDisplayNotificationIconViewStore alwaysOnDisplayNotificationIconViewStore2 = alwaysOnDisplayNotificationIconViewStore;
        return new KeyguardClockSwitchController(keyguardClockSwitch, statusBarStateController2, clockRegistry2, keyguardSliceViewController2, notificationIconAreaController2, lockscreenSmartspaceController2, systemBarUtilsState2, screenOffAnimationController2, statusBarIconViewBindingFailureTracker2, keyguardUnlockAnimationController2, secureSettings3, delayableExecutor, executor, dumpManager, clockEventController, (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardClockLogProvider.get(), (NotificationIconContainerAlwaysOnDisplayViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconContainerAlwaysOnDisplayViewModelProvider.get(), (KeyguardRootViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRootViewModelProvider.get(), configurationState, alwaysOnDisplayNotificationIconViewStore2, (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get(), (KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockInteractorProvider.get(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (InWindowLauncherUnlockAnimationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.inWindowLauncherUnlockAnimationManagerProvider.get());
    }

    public final KeyguardStatusViewController getKeyguardStatusViewController() {
        KeyguardClockSwitchController keyguardClockSwitchController = getKeyguardClockSwitchController();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        KeyguardLogger r10 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1476$$Nest$mkeyguardLogger(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new KeyguardStatusViewController((KeyguardStatusView) this.presentation, (KeyguardSliceViewController) ((Provider) this.keyguardSliceViewControllerProvider).get(), keyguardClockSwitchController, (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get(), (ScreenOffAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenOffAnimationControllerProvider.get(), r10, (InteractionJankMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInteractionJankMonitorProvider.get(), (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get(), (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (PowerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerInteractorProvider.get());
    }
}
