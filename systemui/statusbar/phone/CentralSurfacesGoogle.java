package com.google.android.systemui.statusbar.phone;

import android.app.AlarmManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.IntentFilter;
import android.hardware.devicestate.DeviceStateManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.service.dreams.IDreamManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dependency;
import com.android.systemui.InitController;
import com.android.systemui.accessibility.floatingmenu.AccessibilityFloatingMenuController;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.back.domain.interactor.BackActionInteractor;
import com.android.systemui.biometrics.AuthRippleController;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.charging.WiredChargingRippleController;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.PluginDependencyProvider;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.scene.domain.interactor.WindowRootViewVisibilityInteractor;
import com.android.systemui.scene.shared.flag.SceneContainerFlags;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.QuickSettingsController;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeExpansionStateManager;
import com.android.systemui.shade.ShadeLogger;
import com.android.systemui.shade.ShadeSurface;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.LightRevealScrim;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.PulseExpansionHandler;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.core.StatusBarInitializer;
import com.android.systemui.statusbar.data.repository.StatusBarModeRepositoryImpl;
import com.android.systemui.statusbar.notification.NotificationLaunchAnimatorControllerProvider;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.init.NotificationsController;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.AutoHideController;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.DozeScrimController;
import com.android.systemui.statusbar.phone.DozeServiceHost;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.NotificationIconAreaController;
import com.android.systemui.statusbar.phone.PhoneStatusBarPolicy;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.StatusBarHideIconsForBouncerManager;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarSignalPolicy;
import com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.window.StatusBarWindowController;
import com.android.systemui.statusbar.window.StatusBarWindowStateController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.WallpaperController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.MessageRouterImpl;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.volume.VolumeComponent;
import com.google.android.systemui.ambientmusic.AmbientIndicationContainer;
import com.google.android.systemui.ambientmusic.AmbientIndicationService;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11;
import com.google.android.systemui.dreamliner.DockAlignmentController;
import com.google.android.systemui.dreamliner.DockAlignmentController$$ExternalSyntheticLambda0;
import com.google.android.systemui.dreamliner.DockIndicationController;
import com.google.android.systemui.dreamliner.DockObserver;
import com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda3;
import com.google.android.systemui.dreamliner.WirelessCharger;
import com.google.android.systemui.reversecharging.ReverseChargingViewController;
import com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle;
import dagger.Lazy;
import java.util.Optional;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CentralSurfacesGoogle extends CentralSurfacesImpl {
    public static final boolean DEBUG = Log.isLoggable("CentralSurfacesGoogle", 3);
    public final ActivityStarter mActivityStarter;
    public final AlarmManager mAlarmManager;
    public long mAnimStartTime;
    public final AnonymousClass1 mBatteryStateChangeCallback = new BatteryController.BatteryStateChangeCallback() {
        public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
            CentralSurfacesGoogle centralSurfacesGoogle = CentralSurfacesGoogle.this;
            centralSurfacesGoogle.mReceivingBatteryLevel = i;
            BatteryController batteryController = centralSurfacesGoogle.mBatteryController;
            if (!((BatteryControllerImpl) batteryController).mWirelessCharging) {
                if (SystemClock.uptimeMillis() - centralSurfacesGoogle.mAnimStartTime > 1500) {
                    centralSurfacesGoogle.mChargingAnimShown = false;
                }
                centralSurfacesGoogle.mReverseChargingAnimShown = false;
            }
            if (CentralSurfacesGoogle.DEBUG) {
                StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("onBatteryLevelChanged(): level=", ",wlc=", i);
                m.append(((BatteryControllerImpl) batteryController).mWirelessCharging ? 1 : 0);
                m.append(",wlcs=");
                m.append(centralSurfacesGoogle.mChargingAnimShown);
                m.append(",rtxs=");
                m.append(centralSurfacesGoogle.mReverseChargingAnimShown);
                m.append(",this=");
                m.append(this);
                Log.d("CentralSurfacesGoogle", m.toString());
            }
        }

        public final void onReverseChanged(int i, String str, boolean z) {
            long j;
            CentralSurfacesGoogle centralSurfacesGoogle = CentralSurfacesGoogle.this;
            if (!z && i >= 0 && !TextUtils.isEmpty(str) && ((BatteryControllerImpl) centralSurfacesGoogle.mBatteryController).mWirelessCharging && centralSurfacesGoogle.mChargingAnimShown && !centralSurfacesGoogle.mReverseChargingAnimShown) {
                centralSurfacesGoogle.mReverseChargingAnimShown = true;
                long uptimeMillis = SystemClock.uptimeMillis() - centralSurfacesGoogle.mAnimStartTime;
                if (uptimeMillis > 1500) {
                    j = 0;
                } else {
                    j = 1500 - uptimeMillis;
                }
                centralSurfacesGoogle.showChargingAnimation(centralSurfacesGoogle.mReceivingBatteryLevel, i, j);
            }
            if (CentralSurfacesGoogle.DEBUG) {
                StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("onReverseChanged(): rtx=", ",rxlevel=", z ? 1 : 0);
                m.append(centralSurfacesGoogle.mReceivingBatteryLevel);
                m.append(",level=");
                m.append(i);
                m.append(",name=");
                m.append(str);
                m.append(",wlc=");
                m.append(((BatteryControllerImpl) centralSurfacesGoogle.mBatteryController).mWirelessCharging ? 1 : 0);
                m.append(",wlcs=");
                m.append(centralSurfacesGoogle.mChargingAnimShown);
                m.append(",rtxs=");
                m.append(centralSurfacesGoogle.mReverseChargingAnimShown);
                m.append(",this=");
                m.append(this);
                Log.d("CentralSurfacesGoogle", m.toString());
            }
        }
    };
    public final BroadcastSender mBroadcastSender;
    public boolean mChargingAnimShown;
    public final Context mContext;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11 mDelayedWakeLockFactory;
    public final DockObserver mDockObserver;
    public final Optional mHealthManagerOptional;
    public final KeyguardIndicationControllerGoogle mKeyguardIndicationController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public int mReceivingBatteryLevel;
    public boolean mReverseChargingAnimShown;
    public final Optional mReverseChargingViewControllerOptional;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public final WallpaperNotifier mWallpaperNotifier;

    public CentralSurfacesGoogle(WallpaperNotifier wallpaperNotifier, Optional optional, DockObserver dockObserver, Optional optional2, Context context, NotificationsController notificationsController, FragmentService fragmentService, LightBarController lightBarController, AutoHideController autoHideController, StatusBarInitializer statusBarInitializer, StatusBarWindowController statusBarWindowController, StatusBarWindowStateController statusBarWindowStateController, StatusBarModeRepositoryImpl statusBarModeRepositoryImpl, KeyguardUpdateMonitor keyguardUpdateMonitor, StatusBarSignalPolicy statusBarSignalPolicy, PulseExpansionHandler pulseExpansionHandler, NotificationWakeUpCoordinator notificationWakeUpCoordinator, KeyguardBypassController keyguardBypassController, KeyguardStateController keyguardStateController, HeadsUpManager headsUpManager, FalsingManager falsingManager, FalsingCollector falsingCollector, BroadcastDispatcher broadcastDispatcher, BroadcastSender broadcastSender, NotificationGutsManager notificationGutsManager, ShadeExpansionStateManager shadeExpansionStateManager, KeyguardViewMediator keyguardViewMediator, DisplayMetrics displayMetrics, MetricsLogger metricsLogger, ShadeLogger shadeLogger, JavaAdapter javaAdapter, Executor executor, ShadeSurface shadeSurface, NotificationMediaManager notificationMediaManager, NotificationLockscreenUserManager notificationLockscreenUserManager, NotificationRemoteInputManager notificationRemoteInputManager, QuickSettingsController quickSettingsController, BatteryController batteryController, SysuiColorExtractor sysuiColorExtractor, ScreenLifecycle screenLifecycle, WakefulnessLifecycle wakefulnessLifecycle, PowerInteractor powerInteractor, CommunalInteractor communalInteractor, SysuiStatusBarStateController sysuiStatusBarStateController, Optional optional3, Lazy lazy, DeviceProvisionedController deviceProvisionedController, NavigationBarControllerImpl navigationBarControllerImpl, AccessibilityFloatingMenuController accessibilityFloatingMenuController, Lazy lazy2, ConfigurationController configurationController, NotificationShadeWindowController notificationShadeWindowController, Lazy lazy3, NotificationStackScrollLayoutController notificationStackScrollLayoutController, Lazy lazy4, Lazy lazy5, NotificationLaunchAnimatorControllerProvider notificationLaunchAnimatorControllerProvider, DozeParameters dozeParameters, ScrimController scrimController, Lazy lazy6, AuthRippleController authRippleController, Lazy lazy7, DozeServiceHost dozeServiceHost, BackActionInteractor backActionInteractor, PowerManager powerManager, DozeScrimController dozeScrimController, VolumeComponent volumeComponent, CommandQueue commandQueue, Lazy lazy8, PluginManager pluginManager, ShadeController shadeController, WindowRootViewVisibilityInteractor windowRootViewVisibilityInteractor, StatusBarKeyguardViewManager statusBarKeyguardViewManager, KeyguardViewMediator.AnonymousClass4 r177, InitController initController, Handler handler, PluginDependencyProvider pluginDependencyProvider, ExtensionControllerImpl extensionControllerImpl, UserInfoControllerImpl userInfoControllerImpl, PhoneStatusBarPolicy phoneStatusBarPolicy, KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle, DemoModeController demoModeController, StatusBarTouchableRegionManager statusBarTouchableRegionManager, NotificationIconAreaController notificationIconAreaController, BrightnessSliderController.Factory factory, ScreenOffAnimationController screenOffAnimationController, WallpaperController wallpaperController, StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager, LockscreenShadeTransitionController lockscreenShadeTransitionController, FeatureFlagsClassic featureFlagsClassic, KeyguardUnlockAnimationController keyguardUnlockAnimationController, DelayableExecutor delayableExecutor, MessageRouterImpl messageRouterImpl, WallpaperManager wallpaperManager, Optional optional4, ActivityTransitionAnimator activityTransitionAnimator, AlarmManager alarmManager, DeviceStateManager deviceStateManager, WiredChargingRippleController wiredChargingRippleController, IDreamManager iDreamManager, Lazy lazy9, Lazy lazy10, LightRevealScrim lightRevealScrim, AlternateBouncerInteractor alternateBouncerInteractor, UserTracker userTracker, Provider provider, ActivityStarter activityStarter, SelectedUserInteractor selectedUserInteractor, SceneContainerFlags sceneContainerFlags, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11) {
        super(context, notificationsController, fragmentService, lightBarController, autoHideController, statusBarInitializer, statusBarWindowController, statusBarWindowStateController, statusBarModeRepositoryImpl, keyguardUpdateMonitor, statusBarSignalPolicy, pulseExpansionHandler, notificationWakeUpCoordinator, keyguardBypassController, keyguardStateController, headsUpManager, falsingManager, falsingCollector, broadcastDispatcher, notificationGutsManager, shadeExpansionStateManager, keyguardViewMediator, displayMetrics, metricsLogger, shadeLogger, javaAdapter, executor, shadeSurface, notificationMediaManager, notificationLockscreenUserManager, notificationRemoteInputManager, quickSettingsController, batteryController, sysuiColorExtractor, screenLifecycle, wakefulnessLifecycle, powerInteractor, communalInteractor, sysuiStatusBarStateController, optional3, lazy, deviceProvisionedController, navigationBarControllerImpl, accessibilityFloatingMenuController, lazy2, configurationController, notificationShadeWindowController, lazy3, notificationStackScrollLayoutController, lazy4, lazy5, notificationLaunchAnimatorControllerProvider, dozeParameters, scrimController, lazy6, authRippleController, dozeServiceHost, backActionInteractor, powerManager, dozeScrimController, volumeComponent, commandQueue, lazy8, pluginManager, shadeController, windowRootViewVisibilityInteractor, statusBarKeyguardViewManager, r177, initController, handler, pluginDependencyProvider, extensionControllerImpl, userInfoControllerImpl, phoneStatusBarPolicy, keyguardIndicationControllerGoogle, demoModeController, lazy7, statusBarTouchableRegionManager, notificationIconAreaController, factory, screenOffAnimationController, wallpaperController, statusBarHideIconsForBouncerManager, lockscreenShadeTransitionController, featureFlagsClassic, keyguardUnlockAnimationController, delayableExecutor, messageRouterImpl, wallpaperManager, optional4, activityTransitionAnimator, deviceStateManager, wiredChargingRippleController, iDreamManager, lazy9, lazy10, lightRevealScrim, alternateBouncerInteractor, userTracker, provider, activityStarter, sceneContainerFlags);
        this.mContext = context;
        this.mBroadcastSender = broadcastSender;
        this.mWallpaperNotifier = wallpaperNotifier;
        this.mReverseChargingViewControllerOptional = optional;
        this.mHealthManagerOptional = optional2;
        this.mKeyguardIndicationController = keyguardIndicationControllerGoogle;
        this.mAlarmManager = alarmManager;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mDockObserver = dockObserver;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mActivityStarter = activityStarter;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mDelayedWakeLockFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11;
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [java.util.function.Consumer, java.lang.Object] */
    public final void start() {
        super.start();
        this.mBatteryController.observe(this.mLifecycle, this.mBatteryStateChangeCallback);
        Context context = this.mContext;
        if (!context.getResources().getBoolean(2131034186)) {
            ImageView imageView = (ImageView) getNotificationShadeWindowViewController().mView.findViewById(2131362494);
            DockObserver dockObserver = this.mDockObserver;
            if (imageView == null) {
                dockObserver.getClass();
                Log.e("DLObserver", "set null for dreamlinerGear");
            } else {
                dockObserver.mDreamlinerGear = imageView;
            }
            dockObserver.mPhotoPreview = (FrameLayout) getNotificationShadeWindowViewController().mView.findViewById(2131363300);
            DockIndicationController dockIndicationController = new DockIndicationController(this.mContext, this.mBroadcastSender, this.mKeyguardIndicationController, this.mStatusBarStateController, this.mNotificationShadeWindowController);
            dockObserver.mIndicationController = dockIndicationController;
            ((ConfigurationControllerImpl) dockObserver.mConfigurationController).addCallback(dockIndicationController);
            if (dockObserver.mWirelessCharger.isEmpty()) {
                Log.w("DLObserver", "wirelessCharger is not present");
            } else {
                DockAlignmentController dockAlignmentController = dockObserver.mDockAlignmentController;
                Optional optional = dockAlignmentController.mWirelessCharger;
                if (!optional.isPresent()) {
                    Log.w("DockAlignmentController", "wirelessCharger is null");
                } else {
                    ((WirelessCharger) optional.get()).registerAlignInfo(new DockAlignmentController$$ExternalSyntheticLambda0(dockAlignmentController));
                }
                dockAlignmentController.mDockAlignmentStateChangeListeners.add(new DockObserver$$ExternalSyntheticLambda3(dockObserver));
            }
        }
        this.mHealthManagerOptional.ifPresent(new Object());
        Optional optional2 = this.mReverseChargingViewControllerOptional;
        if (optional2.isPresent()) {
            ReverseChargingViewController reverseChargingViewController = (ReverseChargingViewController) optional2.get();
            reverseChargingViewController.mBatteryController.observe(reverseChargingViewController.mLifecycle, reverseChargingViewController);
            LifecycleRegistry lifecycleRegistry = reverseChargingViewController.mLifecycle;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            lifecycleRegistry.enforceMainThreadIfNeeded("markState");
            lifecycleRegistry.setCurrentState(state);
            reverseChargingViewController.mAmbientIndicationContainer = (AmbientIndicationContainer) ((NotificationShadeWindowControllerImpl) reverseChargingViewController.mNotificationShadeWindowController).mWindowRootView.findViewById(2131361967);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
            reverseChargingViewController.mBroadcastDispatcher.registerReceiver(reverseChargingViewController, intentFilter);
        }
        WallpaperNotifier wallpaperNotifier = this.mWallpaperNotifier;
        ((NotifPipeline) wallpaperNotifier.mNotifCollection).addCollectionListener(wallpaperNotifier.mNotifListener);
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.WALLPAPER_CHANGED");
        wallpaperNotifier.mContext.registerReceiver(wallpaperNotifier.mWallpaperChangedReceiver, intentFilter2);
        wallpaperNotifier.checkNotificationBroadcastSupport();
        AmbientIndicationContainer ambientIndicationContainer = (AmbientIndicationContainer) getNotificationShadeWindowViewController().mView.findViewById(2131361967);
        AmbientIndicationContainer ambientIndicationContainer2 = ambientIndicationContainer;
        ambientIndicationContainer2.initializeView(this.mShadeSurface, this.mPowerInteractor, this.mKeyguardUpdateMonitor, this.mActivityStarter, this.mDelayedWakeLockFactory);
        AmbientIndicationService ambientIndicationService = new AmbientIndicationService(context, ambientIndicationContainer, this.mAlarmManager, this.mSelectedUserInteractor);
        if (!ambientIndicationService.mStarted) {
            ambientIndicationService.mStarted = true;
            IntentFilter intentFilter3 = new IntentFilter();
            intentFilter3.addAction("com.google.android.ambientindication.action.AMBIENT_INDICATION_SHOW");
            intentFilter3.addAction("com.google.android.ambientindication.action.AMBIENT_INDICATION_HIDE");
            ambientIndicationService.mContext.registerReceiverAsUser(ambientIndicationService, UserHandle.ALL, intentFilter3, "com.google.android.ambientindication.permission.AMBIENT_INDICATION", (Handler) null, 2);
            ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).registerCallback(ambientIndicationService.mCallback);
        }
    }
}
