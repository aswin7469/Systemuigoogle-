package com.google.android.systemui.dagger;

import android.app.AlarmManager;
import android.app.IWallpaperManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.display.AmbientDisplayConfiguration;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.service.notification.StatusBarNotification;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.lifecycle.Lifecycle;
import com.android.app.viewcapture.data.ViewNode;
import com.android.dream.lowlight.LowLightTransitionCoordinator;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.statusbar.IStatusBarService;
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
import com.android.keyguard.KeyguardViewController;
import com.android.keyguard.LiftToActivateListener;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.FaceAuthAccessibilityDelegate;
import com.android.systemui.biometrics.SideFpsController;
import com.android.systemui.biometrics.SideFpsControllerKt;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.classifier.FalsingA11yDelegate;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.complication.ComplicationHostViewController;
import com.android.systemui.dock.DockManager;
import com.android.systemui.doze.AlwaysOnDisplayPolicy;
import com.android.systemui.doze.DozeAuthRemover;
import com.android.systemui.doze.DozeBrightnessHostForwarder;
import com.android.systemui.doze.DozeDockHandler;
import com.android.systemui.doze.DozeFalsingManagerAdapter;
import com.android.systemui.doze.DozeHost;
import com.android.systemui.doze.DozeLog;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.doze.DozePauser;
import com.android.systemui.doze.DozeScreenBrightness;
import com.android.systemui.doze.DozeScreenState;
import com.android.systemui.doze.DozeScreenStatePreventingAdapter;
import com.android.systemui.doze.DozeSensors;
import com.android.systemui.doze.DozeSuppressor;
import com.android.systemui.doze.DozeSuspendScreenStatePreventingAdapter;
import com.android.systemui.doze.DozeTransitionListener;
import com.android.systemui.doze.DozeTriggers;
import com.android.systemui.doze.DozeUi;
import com.android.systemui.doze.DozeWallpaperState;
import com.android.systemui.dreams.DreamOverlayAnimationsController;
import com.android.systemui.dreams.DreamOverlayContainerView;
import com.android.systemui.dreams.DreamOverlayContainerViewController;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.dreams.DreamOverlayStatusBarItemsProvider;
import com.android.systemui.dreams.DreamOverlayStatusBarView;
import com.android.systemui.dreams.DreamOverlayStatusBarViewController;
import com.android.systemui.dreams.touch.scrim.BouncerlessScrimController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.data.repository.KeyguardRepository;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepository;
import com.android.systemui.keyguard.domain.interactor.KeyguardFaceAuthInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.media.controls.util.MediaFeatureFlag;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.user.UserSwitchDialogController;
import com.android.systemui.scene.shared.flag.SceneContainerFlags;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.BlurUtils;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationPresenter;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.RemoteInputNotificationRebuilder;
import com.android.systemui.statusbar.SmartReplyController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.systemui.statusbar.notification.AssistantFeedbackController;
import com.android.systemui.statusbar.notification.DynamicPrivacyController;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifLiveDataStoreImpl;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.ColorizedFgsCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.Coordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.DataStoreCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.DebugModeCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.DeviceProvisionedCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.DismissibilityCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.GroupCountCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.coordinator.HideLocallyDismissedNotifsCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.HideNotifsForOtherUsersCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.coordinator.MediaCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.NotifCoordinatorsImpl;
import com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.RowAppearanceCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinatorImpl;
import com.android.systemui.statusbar.notification.collection.coordinator.ShadeEventCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.SmartspaceDedupingCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.StackCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.ViewConfigCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator;
import com.android.systemui.statusbar.notification.collection.inflation.BindEventManagerImpl;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflater;
import com.android.systemui.statusbar.notification.collection.inflation.NotifUiAdjustmentProvider;
import com.android.systemui.statusbar.notification.collection.inflation.OnUserInteractionCallbackImpl;
import com.android.systemui.statusbar.notification.collection.provider.DebugModeFilterProvider;
import com.android.systemui.statusbar.notification.collection.provider.HighPriorityProvider;
import com.android.systemui.statusbar.notification.collection.provider.LaunchFullScreenIntentProvider;
import com.android.systemui.statusbar.notification.collection.provider.NotificationDismissibilityProvider;
import com.android.systemui.statusbar.notification.collection.provider.NotificationDismissibilityProviderImpl;
import com.android.systemui.statusbar.notification.collection.provider.SectionHeaderVisibilityProvider;
import com.android.systemui.statusbar.notification.collection.provider.SectionStyleProvider;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl;
import com.android.systemui.statusbar.notification.collection.render.NodeController;
import com.android.systemui.statusbar.notification.collection.render.NotifGutsViewManager;
import com.android.systemui.statusbar.notification.collection.render.NotifViewBarn;
import com.android.systemui.statusbar.notification.collection.render.SectionHeaderController;
import com.android.systemui.statusbar.notification.data.repository.ActiveNotificationListRepository;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.SeenNotificationsInteractor;
import com.android.systemui.statusbar.notification.icon.ConversationIconManager;
import com.android.systemui.statusbar.notification.icon.IconManager;
import com.android.systemui.statusbar.notification.interruption.HeadsUpViewBinder;
import com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;
import com.android.systemui.statusbar.notification.logging.NotificationPanelLoggerImpl;
import com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifier;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationViewController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRowDragController;
import com.android.systemui.statusbar.notification.row.ExpandableOutlineViewController;
import com.android.systemui.statusbar.notification.row.NotifInflationErrorManager;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.row.NotificationRowLogger;
import com.android.systemui.statusbar.notification.row.NotificationSettingsController;
import com.android.systemui.statusbar.notification.row.RowContentBindStage;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerLogger;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.NotificationIconAreaController;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.statusbar.policy.SmartReplyConstants;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.window.StatusBarWindowStateController;
import com.android.systemui.touch.TouchInsetManager;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.sensors.AsyncSensorManager;
import com.android.systemui.util.sensors.ProximityCheck;
import com.android.systemui.util.sensors.ProximitySensor;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SystemSettingsImpl;
import com.android.systemui.util.time.DateFormatUtil;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.wakelock.DelayedWakeLock;
import com.android.systemui.util.wakelock.WakeLock;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl {
    public final Provider dozeAuthRemoverProvider;
    public final Provider dozeDockHandlerProvider;
    public final Provider dozeFalsingManagerAdapterProvider;
    public final Provider dozeMachineProvider;
    public final DozeMachine.Service dozeMachineService;
    public final Provider dozePauserProvider;
    public final Provider dozeScreenBrightnessProvider;
    public final Provider dozeScreenStateProvider;
    public final Provider dozeSuppressorProvider;
    public final Provider dozeTriggersProvider;
    public final Provider dozeUiProvider;
    public final Provider dozeWallpaperStateProvider;
    public final Provider providesDozeWakeLockProvider;
    public final Provider providesWrappedServiceProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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

        /* JADX WARNING: type inference failed for: r5v7, types: [com.android.systemui.statusbar.notification.domain.interactor.RenderNotificationListInteractor, java.lang.Object] */
        private Coordinator get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            Object obj = this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    FeatureFlags featureFlags = (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get();
                    DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl = (DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl) obj;
                    DebugModeCoordinator debugModeCoordinator = (DebugModeCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.debugModeCoordinatorProvider.get();
                    DreamCoordinator dreamCoordinator = (DreamCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.dreamCoordinatorProvider.get();
                    return new NotifCoordinatorsImpl((SectionStyleProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sectionStyleProvider.get(), (DataStoreCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.dataStoreCoordinatorProvider.get(), (HideLocallyDismissedNotifsCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.hideLocallyDismissedNotifsCoordinatorProvider.get(), (HideNotifsForOtherUsersCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.hideNotifsForOtherUsersCoordinatorProvider.get(), (KeyguardCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.keyguardCoordinatorProvider.get(), (RankingCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.rankingCoordinatorProvider.get(), (ColorizedFgsCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.colorizedFgsCoordinatorProvider.get(), (DeviceProvisionedCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.deviceProvisionedCoordinatorProvider.get(), (BubbleCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.bubbleCoordinatorProvider.get(), (HeadsUpCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.headsUpCoordinatorProvider.get(), (GutsCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.gutsCoordinatorProvider.get(), (ConversationCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.conversationCoordinatorProvider.get(), debugModeCoordinator, (GroupCountCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.groupCountCoordinatorProvider.get(), (GroupWhenCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.groupWhenCoordinatorProvider.get(), (MediaCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.mediaCoordinatorProvider.get(), (PreparationCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.preparationCoordinatorProvider.get(), (RemoteInputCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.remoteInputCoordinatorProvider.get(), (RowAppearanceCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.rowAppearanceCoordinatorProvider.get(), (StackCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.stackCoordinatorProvider.get(), (ShadeEventCoordinator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeEventCoordinatorProvider.get(), (SmartspaceDedupingCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.smartspaceDedupingCoordinatorProvider.get(), (ViewConfigCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.viewConfigCoordinatorProvider.get(), (VisualStabilityCoordinator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.visualStabilityCoordinatorProvider.get(), (SensitiveContentCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.sensitiveContentCoordinatorImplProvider.get(), (DismissibilityCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.dismissibilityCoordinatorProvider.get());
                case 1:
                    return new DataStoreCoordinator((NotifLiveDataStoreImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifLiveDataStoreImplProvider.get());
                case 2:
                    return new HideLocallyDismissedNotifsCoordinator();
                case 3:
                    return new HideNotifsForOtherUsersCoordinator((NotificationLockscreenUserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationLockscreenUserManagerImplProvider.get());
                case 4:
                    return new KeyguardCoordinator((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (HeadsUpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpManagerPhoneProvider.get(), (KeyguardNotificationVisibilityProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardNotificationVisibilityProviderImplProvider.get(), (KeyguardRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRepositoryImplProvider.get(), (KeyguardTransitionRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionRepositoryImplProvider.get(), new KeyguardCoordinatorLogger((LogBuffer) ((DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl) obj).sysUIGoogleSysUIComponentImpl.provideUnseenNotificationLogBufferProvider.get()), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.applicationScopeProvider.get(), (SectionHeaderVisibilityProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sectionHeaderVisibilityProvider.get(), (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get(), (SeenNotificationsInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.seenNotificationsInteractorProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get());
                case 5:
                    Preconditions.checkNotNullFromProvides((NodeController) ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesAlertingHeaderSubcomponentProvider.get()).sectionHeaderNodeControllerImplProvider.get());
                    SectionHeaderController sectionHeaderController = (SectionHeaderController) ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesSilentHeaderSubcomponentProvider.get()).sectionHeaderNodeControllerImplProvider.get();
                    Preconditions.checkNotNullFromProvides(sectionHeaderController);
                    NodeController nodeController = (NodeController) ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesSilentHeaderSubcomponentProvider.get()).sectionHeaderNodeControllerImplProvider.get();
                    Preconditions.checkNotNullFromProvides(nodeController);
                    return new RankingCoordinator((StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (HighPriorityProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.highPriorityProvider.get(), sectionHeaderController, nodeController);
                case 6:
                    return new ColorizedFgsCoordinator();
                case ViewNode.WIDTH_FIELD_NUMBER:
                    IPackageManager iPackageManager = (IPackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIPackageManagerProvider.get();
                    return new DeviceProvisionedCoordinator((DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get());
                case 8:
                    return new BubbleCoordinator((Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBubblesManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setBubbles, (NotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifCollectionProvider.get());
                case ViewNode.SCROLLX_FIELD_NUMBER:
                    daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifPipelineFlags();
                    Preconditions.checkNotNullFromProvides((NodeController) ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesIncomingHeaderSubcomponentProvider.get()).sectionHeaderNodeControllerImplProvider.get());
                    return new HeadsUpCoordinator(new HeadsUpCoordinatorLogger((LogBuffer) ((DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl) obj).sysUIGoogleSysUIComponentImpl.provideNotificationHeadsUpLogBufferProvider.get()), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (HeadsUpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpManagerPhoneProvider.get(), (HeadsUpViewBinder) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpViewBinderProvider.get(), (VisualInterruptionDecisionProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideVisualInterruptionDecisionProvider.get(), (NotificationRemoteInputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationRemoteInputManagerProvider.get(), (LaunchFullScreenIntentProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.launchFullScreenIntentProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get());
                case ViewNode.SCROLLY_FIELD_NUMBER:
                    return new GutsCoordinator((NotifGutsViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationGutsManagerProvider.get(), new GutsCoordinatorLogger((LogBuffer) ((DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl) obj).sysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get()), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get());
                case ViewNode.TRANSLATIONX_FIELD_NUMBER:
                    Preconditions.checkNotNullFromProvides((NodeController) ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesPeopleHeaderSubcomponentProvider.get()).sectionHeaderNodeControllerImplProvider.get());
                    return new ConversationCoordinator((PeopleNotificationIdentifier) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.peopleNotificationIdentifierImplProvider.get(), (ConversationIconManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.iconManagerProvider.get(), (HighPriorityProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.highPriorityProvider.get());
                case ViewNode.TRANSLATIONY_FIELD_NUMBER:
                    return new DebugModeCoordinator((DebugModeFilterProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.debugModeFilterProvider.get());
                case ViewNode.SCALEX_FIELD_NUMBER:
                    return new GroupCountCoordinator();
                case ViewNode.SCALEY_FIELD_NUMBER:
                    return new GroupWhenCoordinator((DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
                case 15:
                    return new MediaCoordinator(new MediaFeatureFlag(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context), (IStatusBarService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIStatusBarServiceProvider.get(), (IconManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.iconManagerProvider.get());
                case ViewNode.WILLNOTDRAW_FIELD_NUMBER:
                    return new PreparationCoordinator(new PreparationCoordinatorLogger((LogBuffer) ((DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl) obj).sysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get()), (NotifInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifInflaterImplProvider.get(), (NotifInflationErrorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifInflationErrorManagerProvider.get(), (NotifViewBarn) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifViewBarnProvider.get(), (NotifUiAdjustmentProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifUiAdjustmentProvider.get(), (IStatusBarService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIStatusBarServiceProvider.get(), (BindEventManagerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindEventManagerImplProvider.get(), 9, 500);
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER:
                    return new RemoteInputCoordinator((DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (RemoteInputNotificationRebuilder) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.remoteInputNotificationRebuilderProvider.get(), (NotificationRemoteInputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationRemoteInputManagerProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (SmartReplyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSmartReplyControllerProvider.get());
                case ViewNode.VISIBILITY_FIELD_NUMBER:
                    return new RowAppearanceCoordinator(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (AssistantFeedbackController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistantFeedbackControllerProvider.get(), (SectionStyleProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sectionStyleProvider.get());
                case ViewNode.ELEVATION_FIELD_NUMBER:
                    NotificationIconAreaController notificationIconAreaController = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconAreaController();
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = ((DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl) obj).sysUIGoogleSysUIComponentImpl;
                    ActiveNotificationListRepository activeNotificationListRepository = (ActiveNotificationListRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.activeNotificationListRepositoryProvider.get();
                    SectionStyleProvider sectionStyleProvider = (SectionStyleProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.sectionStyleProvider.get();
                    return new StackCoordinator((GroupExpansionManagerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.groupExpansionManagerImplProvider.get(), notificationIconAreaController, new Object(), (ActiveNotificationsInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activeNotificationsInteractorProvider.get());
                case 20:
                    return new SmartspaceDedupingCoordinator((SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (LockscreenSmartspaceController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenSmartspaceControllerProvider.get(), (NotifPipeline) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifPipelineProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
                case 21:
                    return new ViewConfigCoordinator((ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (NotificationLockscreenUserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationLockscreenUserManagerImplProvider.get(), (NotificationGutsManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationGutsManagerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get());
                case 22:
                    return new SensitiveContentCoordinatorImpl((DynamicPrivacyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dynamicPrivacyControllerProvider.get(), (NotificationLockscreenUserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationLockscreenUserManagerImplProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get());
                case 23:
                    return new DismissibilityCoordinator((KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (NotificationDismissibilityProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationDismissibilityProviderImplProvider.get());
                case 24:
                    return new DreamCoordinator((SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.applicationScopeProvider.get(), (KeyguardRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRepositoryImplProvider.get());
                default:
                    throw new AssertionError(i);
            }
        }

        private Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl$SwitchingProvider() {
            Optional[] optionalArr;
            Object obj = this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl) obj;
                    AmbientDisplayConfiguration r8 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m882$$Nest$mambientDisplayConfiguration(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl);
                    DozeHost dozeHost = (DozeHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get();
                    DockManager dockManager = (DockManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dockObserverProvider.get();
                    DozeLog dozeLog = (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get();
                    WakefulnessLifecycle wakefulnessLifecycle = (WakefulnessLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wakefulnessLifecycleProvider.get();
                    WakeLock wakeLock = (WakeLock) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesDozeWakeLockProvider.get();
                    return new DozeMachine((DozeMachine.Service) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesWrappedServiceProvider.get(), r8, wakeLock, wakefulnessLifecycle, dozeLog, dockManager, dozeHost, new DozeMachine.Part[]{(DozePauser) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozePauserProvider.get(), (DozeFalsingManagerAdapter) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeFalsingManagerAdapterProvider.get(), (DozeTriggers) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeTriggersProvider.get(), (DozeUi) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeUiProvider.get(), (DozeScreenState) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenStateProvider.get(), (DozeScreenBrightness) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenBrightnessProvider.get(), (DozeWallpaperState) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeWallpaperStateProvider.get(), (DozeDockHandler) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeDockHandlerProvider.get(), (DozeAuthRemover) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeAuthRemoverProvider.get(), (DozeSuppressor) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeSuppressorProvider.get(), (DozeTransitionListener) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.sysUIGoogleSysUIComponentImpl.dozeTransitionListenerProvider.get()}, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
                case 1:
                    DozeParameters dozeParameters = (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get();
                    Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiBackgroundExecutorProvider.get();
                    DozeMachine.Service dozeBrightnessHostForwarder = new DozeBrightnessHostForwarder(((DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl) obj).dozeMachineService, (DozeHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), executor);
                    if (!SystemProperties.getBoolean("doze.display.supported", dozeParameters.mResources.getBoolean(2131034205))) {
                        dozeBrightnessHostForwarder = new DozeScreenStatePreventingAdapter(dozeBrightnessHostForwarder, executor);
                    }
                    if (!dozeParameters.mResources.getBoolean(2131034212)) {
                        return new DozeSuspendScreenStatePreventingAdapter(dozeBrightnessHostForwarder, executor);
                    }
                    return dozeBrightnessHostForwarder;
                case 2:
                    return new DelayedWakeLock((Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), WakeLock.createPartial(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wakeLockLogger(), "Doze", 20000));
                case 3:
                    return new DozePauser((Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (AlarmManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAlarmManagerProvider.get(), (AlwaysOnDisplayPolicy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alwaysOnDisplayPolicyProvider.get());
                case 4:
                    return new DozeFalsingManagerAdapter((FalsingCollector) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesFalsingCollectorLegacyProvider.get());
                case 5:
                    return new DozeTriggers(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DozeHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m882$$Nest$mambientDisplayConfiguration(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get(), (AsyncSensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.asyncSensorManagerProvider.get(), (WakeLock) ((DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl) obj).providesDozeWakeLockProvider.get(), (DockManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dockObserverProvider.get(), (ProximitySensor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideProximitySensorProvider.get(), new ProximityCheck((ProximitySensor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideProximitySensorProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get()), (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get(), (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get(), (AuthController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.authControllerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (SessionTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sessionTrackerProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (DevicePostureController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.devicePostureControllerImplProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get());
                case 6:
                    return new DozeUi(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (AlarmManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAlarmManagerProvider.get(), (WakeLock) ((DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl) obj).providesDozeWakeLockProvider.get(), (DozeHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get(), (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get());
                case ViewNode.WIDTH_FIELD_NUMBER:
                    DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl2 = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl) obj;
                    return new DozeScreenState((DozeMachine.Service) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl2.providesWrappedServiceProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (DozeHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get(), (WakeLock) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl2.providesDozeWakeLockProvider.get(), (AuthController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.authControllerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.udfpsControllerProvider, (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get(), (DozeScreenBrightness) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl2.dozeScreenBrightnessProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get());
                case 8:
                    Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
                    DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl3 = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl) obj;
                    DozeMachine.Service service = (DozeMachine.Service) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl3.providesWrappedServiceProvider.get();
                    AsyncSensorManager asyncSensorManager = (AsyncSensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.asyncSensorManagerProvider.get();
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl3.sysUIGoogleSysUIComponentImpl;
                    AsyncSensorManager asyncSensorManager2 = (AsyncSensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.asyncSensorManagerProvider.get();
                    Context context2 = daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl3.sysUIGoogleGlobalRootComponentImpl.context;
                    String[] stringArray = ((DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.dozeParametersProvider.get()).mResources.getStringArray(2130903121);
                    if (stringArray.length != 0) {
                        Optional[] optionalArr2 = new Optional[5];
                        Arrays.fill(optionalArr2, Optional.empty());
                        HashMap hashMap = new HashMap();
                        for (int i2 = 0; i2 < stringArray.length; i2++) {
                            String str = stringArray[i2];
                            if (!hashMap.containsKey(str)) {
                                hashMap.put(str, Optional.ofNullable(DozeSensors.findSensor(asyncSensorManager2, context2.getString(2131952457), stringArray[i2])));
                            }
                            optionalArr2[i2] = (Optional) hashMap.get(str);
                        }
                        optionalArr = optionalArr2;
                    } else {
                        optionalArr = new Optional[]{Optional.ofNullable(DozeSensors.findSensor(asyncSensorManager2, context2.getString(2131952457), (String) null))};
                    }
                    SystemSettingsImpl r16 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1633$$Nest$msystemSettingsImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl);
                    return new DozeScreenBrightness(context, service, asyncSensorManager, optionalArr, (DozeHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), new Handler(), (AlwaysOnDisplayPolicy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alwaysOnDisplayPolicyProvider.get(), (WakefulnessLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wakefulnessLifecycleProvider.get(), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get(), (DevicePostureController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.devicePostureControllerImplProvider.get(), (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get(), r16);
                case ViewNode.SCROLLX_FIELD_NUMBER:
                    return new DozeWallpaperState(IWallpaperManager.Stub.asInterface(ServiceManager.getService("wallpaper")), (BiometricUnlockController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.biometricUnlockControllerProvider.get(), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get());
                case ViewNode.SCROLLY_FIELD_NUMBER:
                    return new DozeDockHandler(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m882$$Nest$mambientDisplayConfiguration(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (DockManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dockObserverProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
                case ViewNode.TRANSLATIONX_FIELD_NUMBER:
                    return new DozeAuthRemover((KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get());
                case ViewNode.TRANSLATIONY_FIELD_NUMBER:
                    return new DozeSuppressor((DozeHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m882$$Nest$mambientDisplayConfiguration(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.biometricUnlockControllerProvider), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
                default:
                    throw new AssertionError(i);
            }
        }

        private Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            Object obj = this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl) obj;
                    ComplicationHostViewController complicationHostViewController = daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.complicationHostViewController;
                    Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
                    Preconditions.checkNotNullFromProvides(resources);
                    int intValue = ((Integer) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesMaxBurnInOffsetProvider.get()).intValue();
                    long integer = (long) DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.sysUIGoogleGlobalRootComponentImpl).getInteger(2131427363);
                    long integer2 = (long) DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.sysUIGoogleGlobalRootComponentImpl).getInteger(2131427367);
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.sysUIGoogleSysUIComponentImpl;
                    long j = integer2;
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.sysUIGoogleGlobalRootComponentImpl;
                    Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.context;
                    Resources resources2 = context.getResources();
                    Preconditions.checkNotNullFromProvides(resources2);
                    Resources resources3 = context.getResources();
                    Preconditions.checkNotNullFromProvides(resources3);
                    DreamOverlayAnimationsController dreamOverlayAnimationsController = new DreamOverlayAnimationsController((BlurUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.blurUtilsProvider.get(), daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.complicationHostViewController, (DreamOverlayStatusBarViewController) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.dreamOverlayStatusBarViewControllerProvider.get(), (DreamOverlayStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.dreamOverlayStateControllerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2).getDimensionPixelSize(2131165812), (DreamingToLockscreenTransitionViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.dreamingToLockscreenTransitionViewModelProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.configurationControllerImplProvider.get(), (long) resources2.getInteger(2131427364), (long) resources3.getInteger(2131427365), ((Integer) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesDreamInComplicationsTranslationYProvider.get()).intValue(), ((Long) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesDreamInComplicationsTranslationYDurationProvider.get()).longValue(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideDreamLogBufferProvider.get());
                    return new DreamOverlayContainerViewController((DreamOverlayContainerView) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesDreamOverlayContainerViewProvider.get(), complicationHostViewController, (ViewGroup) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesDreamOverlayContentViewProvider.get(), (DreamOverlayStatusBarViewController) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.dreamOverlayStatusBarViewControllerProvider.get(), (LowLightTransitionCoordinator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.lowLightTransitionCoordinatorProvider.get(), (BlurUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.blurUtilsProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), resources, intValue, integer, j, (PrimaryBouncerCallbackInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerCallbackInteractorProvider.get(), dreamOverlayAnimationsController, (DreamOverlayStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayStateControllerProvider.get(), (BouncerlessScrimController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bouncerlessScrimControllerProvider.get());
                case 1:
                    DreamOverlayContainerView dreamOverlayContainerView = (DreamOverlayContainerView) com.android.internal.util.Preconditions.checkNotNull((DreamOverlayContainerView) ((LayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providerLayoutInflaterProvider.get()).inflate(2131558583, (ViewGroup) null), "R.layout.dream_layout_container could not be properly inflated");
                    Preconditions.checkNotNullFromProvides(dreamOverlayContainerView);
                    return dreamOverlayContainerView;
                case 2:
                    ViewGroup viewGroup = (ViewGroup) com.android.internal.util.Preconditions.checkNotNull((ViewGroup) ((DreamOverlayContainerView) ((DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl) obj).providesDreamOverlayContainerViewProvider.get()).findViewById(2131362463), "R.id.dream_overlay_content must not be null");
                    Preconditions.checkNotNullFromProvides(viewGroup);
                    return viewGroup;
                case 3:
                    DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl2 = (DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl) obj;
                    Resources r5 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl);
                    TouchInsetManager touchInsetManager = daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl2.touchInsetManager;
                    TouchInsetManager.TouchInsetSession touchInsetSession = new TouchInsetManager.TouchInsetSession(touchInsetManager, touchInsetManager.mExecutor);
                    DateFormatUtil dateFormatUtil = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dateFormatUtil();
                    return new DreamOverlayStatusBarViewController((DreamOverlayStatusBarView) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl2.providesDreamOverlayStatusBarViewProvider.get(), r5, (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (ConnectivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideConnectivityManagagerProvider.get(), touchInsetSession, (AlarmManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAlarmManagerProvider.get(), (NextAlarmController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nextAlarmControllerImplProvider.get(), dateFormatUtil, (IndividualSensorPrivacyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideIndividualSensorPrivacyControllerProvider.get(), (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesDreamOverlayNotificationCountProvider.get(), (ZenModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.zenModeControllerImplProvider.get(), (StatusBarWindowStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarWindowStateControllerProvider.get(), (DreamOverlayStatusBarItemsProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayStatusBarItemsProvider.get(), (DreamOverlayStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayStateControllerProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDreamLogBufferProvider.get());
                case 4:
                    DreamOverlayStatusBarView dreamOverlayStatusBarView = (DreamOverlayStatusBarView) com.android.internal.util.Preconditions.checkNotNull((DreamOverlayStatusBarView) ((DreamOverlayContainerView) ((DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl) obj).providesDreamOverlayContainerViewProvider.get()).findViewById(2131362468), "R.id.status_bar must not be null");
                    Preconditions.checkNotNullFromProvides(dreamOverlayStatusBarView);
                    return dreamOverlayStatusBarView;
                case 5:
                    return Integer.valueOf(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getDimensionPixelSize(2131165704));
                case 6:
                    return Integer.valueOf(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getDimensionPixelSize(2131165852));
                case ViewNode.WIDTH_FIELD_NUMBER:
                    return Long.valueOf((long) DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getInteger(2131427366));
                case 8:
                    Lifecycle lifecycle = ((DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl) obj).lifecycleOwner.getLifecycle();
                    Preconditions.checkNotNullFromProvides(lifecycle);
                    return lifecycle;
                default:
                    throw new AssertionError(i);
            }
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object, com.android.systemui.statusbar.notification.row.ExpandableViewController] */
        private ExpandableNotificationRowController get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl$SwitchingProvider() {
            int i = this.id;
            if (i == 0) {
                DaggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl) this.dozeComponentImpl;
                ExpandableNotificationRow expandableNotificationRow = daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl.expandableNotificationRow;
                ActivatableNotificationViewController activatableNotificationViewController = new ActivatableNotificationViewController(expandableNotificationRow, new ExpandableOutlineViewController(new Object()), (AccessibilityManager) daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideAccessibilityManagerProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl.sysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get());
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
                DaggerSysUIGoogleGlobalRootComponent$WindowRootViewComponentImpl daggerSysUIGoogleGlobalRootComponent$WindowRootViewComponentImpl = new DaggerSysUIGoogleGlobalRootComponent$WindowRootViewComponentImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl);
                MetricsLogger metricsLogger = (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get();
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl.sysUIGoogleSysUIComponentImpl;
                NotificationRowLogger notificationRowLogger = new NotificationRowLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideNotificationsLogBufferProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideNotificationRenderLogBufferProvider.get());
                NotificationChildrenContainerLogger notificationChildrenContainerLogger = new NotificationChildrenContainerLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl.sysUIGoogleSysUIComponentImpl.provideNotificationRenderLogBufferProvider.get());
                NotificationListContainer notificationListContainer = (NotificationListContainer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideListContainerProvider.get();
                SmartReplyConstants smartReplyConstants = (SmartReplyConstants) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartReplyConstantsProvider.get();
                SmartReplyController smartReplyController = (SmartReplyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSmartReplyControllerProvider.get();
                Context context = daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
                SystemClock systemClock = (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get();
                StatusBarNotification statusBarNotification = daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl.notificationEntry.mSbn;
                Preconditions.checkNotNullFromProvides(statusBarNotification);
                PluginManager pluginManager = (PluginManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesPluginManagerProvider.get();
                PackageManager packageManagerForUser = CentralSurfaces.getPackageManagerForUser(statusBarNotification.getUser().getIdentifier(), context);
                String packageName = statusBarNotification.getPackageName();
                try {
                    ApplicationInfo applicationInfo = packageManagerForUser.getApplicationInfo(packageName, 8704);
                    if (applicationInfo != null) {
                        packageName = String.valueOf(packageManagerForUser.getApplicationLabel(applicationInfo));
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
                Preconditions.checkNotNullFromProvides(packageName);
                StatusBarNotification statusBarNotification2 = daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl.notificationEntry.mSbn;
                Preconditions.checkNotNullFromProvides(statusBarNotification2);
                String key = statusBarNotification2.getKey();
                Preconditions.checkNotNullFromProvides(key);
                NotificationPresenter notificationPresenter = daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl.onExpandClickListener;
                boolean booleanValue = ((Boolean) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAllowNotificationLongPressProvider.get()).booleanValue();
                String str = key;
                Context context2 = daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl.sysUIGoogleSysUIComponentImpl;
                MetricsLogger metricsLogger2 = metricsLogger;
                NotificationRowLogger notificationRowLogger2 = notificationRowLogger;
                NotificationChildrenContainerLogger notificationChildrenContainerLogger2 = notificationChildrenContainerLogger;
                NotificationListContainer notificationListContainer2 = notificationListContainer;
                SmartReplyConstants smartReplyConstants2 = smartReplyConstants;
                SmartReplyController smartReplyController2 = smartReplyController;
                PluginManager pluginManager2 = pluginManager;
                ExpandableNotificationRowDragController expandableNotificationRowDragController = new ExpandableNotificationRowDragController(context2, (HeadsUpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3.headsUpManagerPhoneProvider.get(), (ShadeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3.shadeControllerImplProvider.get(), (NotificationPanelLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3.provideNotificationPanelLoggerProvider.get());
                return new ExpandableNotificationRowController(expandableNotificationRow, activatableNotificationViewController, daggerSysUIGoogleGlobalRootComponent$WindowRootViewComponentImpl, metricsLogger2, notificationRowLogger2, notificationChildrenContainerLogger2, notificationListContainer2, smartReplyConstants2, smartReplyController2, pluginManager2, systemClock, packageName, str, (KeyguardBypassController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBypassControllerProvider.get(), (GroupMembershipManagerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.groupMembershipManagerImplProvider.get(), (GroupExpansionManagerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.groupExpansionManagerImplProvider.get(), (RowContentBindStage) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.rowContentBindStageProvider.get(), (NotificationLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationLoggerProvider.get(), (HeadsUpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpManagerPhoneProvider.get(), notificationPresenter, (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (NotificationGutsManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationGutsManagerProvider.get(), booleanValue, (OnUserInteractionCallbackImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.onUserInteractionCallbackImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (PeopleNotificationIdentifier) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.peopleNotificationIdentifierImplProvider.get(), (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBubblesManagerProvider.get(), (NotificationSettingsController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationSettingsControllerProvider.get(), expandableNotificationRowDragController, (NotificationDismissibilityProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationDismissibilityProviderImplProvider.get(), (IStatusBarService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIStatusBarServiceProvider.get());
            }
            throw new AssertionError(i);
        }

        private Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl$SwitchingProvider() {
            FingerprintSensorPropertiesInternal fingerprintSensorPropertiesInternal;
            Optional optional;
            Object obj = this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            if (i == 0) {
                DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl) obj;
                FalsingManager falsingManager = (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get();
                UserSwitcherController userSwitcherController = (UserSwitcherController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userSwitcherControllerProvider.get();
                GlobalSettingsImpl globalSettingsImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.globalSettingsImpl();
                daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.getClass();
                FalsingA11yDelegate falsingA11yDelegate = new FalsingA11yDelegate((FalsingCollector) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.sysUIGoogleSysUIComponentImpl.providesFalsingCollectorLegacyProvider.get());
                Provider provider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.javaAdapterProvider;
                Lazy lazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerInteractorProvider);
                Provider provider2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryInteractorProvider;
                FalsingA11yDelegate falsingA11yDelegate2 = falsingA11yDelegate;
                return new KeyguardSecurityContainerController((KeyguardSecurityContainer) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.providesKeyguardSecurityContainerProvider.get(), (AdminSecondaryLockScreenController.Factory) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.factoryProvider.get(), (LockPatternUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideLockPatternUtilsProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (KeyguardSecurityModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardSecurityModelProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (KeyguardSecurityViewFlipperController) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.keyguardSecurityViewFlipperControllerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (FalsingCollector) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesFalsingCollectorLegacyProvider.get(), falsingManager, userSwitcherController, (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get(), globalSettingsImpl, (SessionTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sessionTrackerProvider.get(), (Optional) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.providesOptionalSidefpsControllerProvider.get(), falsingA11yDelegate2, (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideTelephonyManagerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1643$$Nest$mviewMediatorCallback(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (AudioManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAudioManagerProvider.get(), (KeyguardFaceAuthInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUIKeyguardFaceAuthInteractorProvider.get(), (BouncerMessageInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bouncerMessageInteractorProvider.get(), provider, (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get(), (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get(), (FaceAuthAccessibilityDelegate) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.faceAuthAccessibilityDelegateProvider.get(), (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get(), lazy, provider2);
            } else if (i == 1) {
                ViewGroup viewGroup = ((DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl) obj).bouncerContainer;
                KeyguardSecurityContainer keyguardSecurityContainer = (KeyguardSecurityContainer) ((LayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providerLayoutInflaterProvider.get()).inflate(2131558663, viewGroup, false);
                viewGroup.addView(keyguardSecurityContainer);
                Preconditions.checkNotNullFromProvides(keyguardSecurityContainer);
                return keyguardSecurityContainer;
            } else if (i == 2) {
                return new AdminSecondaryLockScreenController.Factory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (KeyguardSecurityContainer) ((DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl) obj).providesKeyguardSecurityContainerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get());
            } else {
                if (i == 3) {
                    DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl2 = (DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl) obj;
                    LayoutInflater layoutInflater = (LayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providerLayoutInflaterProvider.get();
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl2.sysUIGoogleSysUIComponentImpl;
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl2.sysUIGoogleGlobalRootComponentImpl;
                    Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.context.getResources();
                    Preconditions.checkNotNullFromProvides(resources);
                    KeyguardInputViewController.Factory factory = new KeyguardInputViewController.Factory((KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardUpdateMonitorProvider.get(), (LockPatternUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideLockPatternUtilsProvider.get(), (LatencyTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideLatencyTrackerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1478$$Nest$mkeyguardMessageAreaControllerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2), (InputMethodManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideInputMethodManagerProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideMainDelayableExecutorProvider.get(), resources, new LiftToActivateListener((AccessibilityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideAccessibilityManagerProvider.get()), (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideTelephonyManagerProvider.get(), (FalsingCollector) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.providesFalsingCollectorLegacyProvider.get(), daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl2.emergencyButtonControllerFactory(), (DevicePostureController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.devicePostureControllerImplProvider.get(), (KeyguardViewController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.statusBarKeyguardViewManagerProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.featureFlagsClassicReleaseProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.selectedUserInteractorProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideUiEventLoggerProvider.get());
                    daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl2.emergencyButtonControllerFactory();
                    return new KeyguardSecurityViewFlipperController((KeyguardSecurityViewFlipper) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl2.providesKeyguardSecurityViewFlipperProvider.get(), (AsyncLayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAsyncLayoutInflaterProvider.get(), factory, (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get());
                } else if (i == 4) {
                    KeyguardSecurityViewFlipper keyguardSecurityViewFlipper = (KeyguardSecurityViewFlipper) ((KeyguardSecurityContainer) ((DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl) obj).providesKeyguardSecurityContainerProvider.get()).findViewById(2131363995);
                    Preconditions.checkNotNullFromProvides(keyguardSecurityViewFlipper);
                    return keyguardSecurityViewFlipper;
                } else if (i == 5) {
                    FingerprintManager fingerprintManager = (FingerprintManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesFingerprintManagerProvider.get();
                    Provider provider3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sideFpsControllerProvider;
                    if (fingerprintManager != null) {
                        fingerprintSensorPropertiesInternal = SideFpsControllerKt.getSideFpsSensorProperties(fingerprintManager);
                    } else {
                        fingerprintSensorPropertiesInternal = null;
                    }
                    if (fingerprintSensorPropertiesInternal != null) {
                        optional = Optional.of((SideFpsController) provider3.get());
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

        private KeyguardQsUserSwitchController get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$KeyguardQsUserSwitchComponentImpl$SwitchingProvider() {
            int i = this.id;
            if (i == 0) {
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
                Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
                Resources resources = context.getResources();
                Preconditions.checkNotNullFromProvides(resources);
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
                DozeParameters dozeParameters = (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get();
                return new KeyguardQsUserSwitchController((FrameLayout) ((DaggerSysUIGoogleGlobalRootComponent$casdcd_ComplicationComponentImpl) this.dozeComponentImpl).visibilityController, context, resources, (UserSwitcherController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userSwitcherControllerProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (ScreenOffAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenOffAnimationControllerProvider.get(), (UserSwitchDialogController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userSwitchDialogControllerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
            }
            throw new AssertionError(i);
        }

        /* JADX WARNING: type inference failed for: r1v116 */
        /* JADX WARNING: type inference failed for: r1v168 */
        /* JADX WARNING: type inference failed for: r25v10, types: [java.lang.Object, com.android.systemui.util.concurrency.ThreadFactoryImpl] */
        /* JADX WARNING: type inference failed for: r14v14, types: [com.android.systemui.qs.QSPanelController] */
        /* JADX WARNING: type inference failed for: r14v15, types: [com.android.systemui.qs.QuickQSPanelController] */
        /* JADX WARNING: type inference failed for: r14v16, types: [com.android.systemui.qs.QSPanelController] */
        /* JADX WARNING: type inference failed for: r14v17, types: [com.android.systemui.qs.QuickQSPanelController] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object get() {
            /*
                r57 = this;
                r0 = r57
                r1 = 2131363369(0x7f0a0629, float:1.8346545E38)
                r2 = 2131363354(0x7f0a061a, float:1.8346514E38)
                r3 = 2131363368(0x7f0a0628, float:1.8346543E38)
                r4 = 2131363356(0x7f0a061c, float:1.8346519E38)
                r5 = 2131363367(0x7f0a0627, float:1.834654E38)
                int r6 = r0.$r8$classId
                r7 = 2
                r8 = 2131362063(0x7f0a010f, float:1.8343896E38)
                r9 = 0
                r10 = 1
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r11 = r0.sysUIGoogleSysUIComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r12 = r0.sysUIGoogleGlobalRootComponentImpl
                java.lang.Object r13 = r0.dozeComponentImpl
                int r14 = r0.id
                switch(r6) {
                    case 0: goto L_0x0f8b;
                    case 1: goto L_0x0f86;
                    case 2: goto L_0x0f81;
                    case 3: goto L_0x0f7c;
                    case 4: goto L_0x0f77;
                    case 5: goto L_0x0f72;
                    case 6: goto L_0x0f40;
                    case 7: goto L_0x0ee5;
                    case 8: goto L_0x0e82;
                    case 9: goto L_0x0d1a;
                    case 10: goto L_0x0aac;
                    case 11: goto L_0x0798;
                    case 12: goto L_0x0495;
                    case 13: goto L_0x0468;
                    case 14: goto L_0x01eb;
                    case 15: goto L_0x00b8;
                    default: goto L_0x0024;
                }
            L_0x0024:
                if (r14 == 0) goto L_0x009b
                if (r14 != r10) goto L_0x0095
                com.android.systemui.complication.ComplicationLayoutEngine r0 = new com.android.systemui.complication.ComplicationLayoutEngine
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$cascd_ComplicationComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$cascd_ComplicationComponentImpl) r13
                javax.inject.Provider r1 = r13.providesComplicationHostViewProvider
                java.lang.Object r1 = r1.get()
                r16 = r1
                androidx.constraintlayout.widget.ConstraintLayout r16 = (androidx.constraintlayout.widget.ConstraintLayout) r16
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r1 = r13.sysUIGoogleGlobalRootComponentImpl
                android.content.res.Resources r2 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(r1)
                r3 = 2131165842(0x7f070292, float:1.7945913E38)
                int r17 = r2.getDimensionPixelSize(r3)
                android.content.res.Resources r2 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(r1)
                r3 = 2131165850(0x7f07029a, float:1.7945929E38)
                int r18 = r2.getDimensionPixelSize(r3)
                android.content.res.Resources r2 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(r1)
                r3 = 2131165851(0x7f07029b, float:1.794593E38)
                int r19 = r2.getDimensionPixelSize(r3)
                android.content.res.Resources r2 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(r1)
                r3 = 2131165849(0x7f070299, float:1.7945927E38)
                int r20 = r2.getDimensionPixelSize(r3)
                android.content.res.Resources r2 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(r1)
                r3 = 2131165848(0x7f070298, float:1.7945925E38)
                int r21 = r2.getDimensionPixelSize(r3)
                com.android.systemui.touch.TouchInsetManager$TouchInsetSession r2 = new com.android.systemui.touch.TouchInsetManager$TouchInsetSession
                com.android.systemui.touch.TouchInsetManager r3 = r13.touchInsetManager
                java.util.concurrent.Executor r4 = r3.mExecutor
                r2.<init>(r3, r4)
                android.content.res.Resources r3 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(r1)
                r4 = 2131427344(0x7f0b0010, float:1.8476302E38)
                int r23 = r3.getInteger(r4)
                android.content.res.Resources r1 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(r1)
                r3 = 2131427346(0x7f0b0012, float:1.8476306E38)
                int r24 = r1.getInteger(r3)
                r15 = r0
                r22 = r2
                r15.<init>(r16, r17, r18, r19, r20, r21, r22, r23, r24)
                goto L_0x00b7
            L_0x0095:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x009b:
                javax.inject.Provider r0 = r12.providerLayoutInflaterProvider
                java.lang.Object r0 = r0.get()
                android.view.LayoutInflater r0 = (android.view.LayoutInflater) r0
                r1 = 2131558582(0x7f0d00b6, float:1.8742484E38)
                android.view.View r0 = r0.inflate(r1, r9)
                androidx.constraintlayout.widget.ConstraintLayout r0 = (androidx.constraintlayout.widget.ConstraintLayout) r0
                java.lang.String r1 = "R.layout.dream_overlay_complications_layer did not properly inflated"
                java.lang.Object r0 = com.android.internal.util.Preconditions.checkNotNull(r0, r1)
                androidx.constraintlayout.widget.ConstraintLayout r0 = (androidx.constraintlayout.widget.ConstraintLayout) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
            L_0x00b7:
                return r0
            L_0x00b8:
                switch(r14) {
                    case 0: goto L_0x01d7;
                    case 1: goto L_0x01bd;
                    case 2: goto L_0x01a9;
                    case 3: goto L_0x0172;
                    case 4: goto L_0x015b;
                    case 5: goto L_0x0134;
                    case 6: goto L_0x00c1;
                    default: goto L_0x00bb;
                }
            L_0x00bb:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x00c1:
                com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation r0 = new com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation
                android.content.Context r1 = r12.context
                dagger.internal.DelegateFactory r2 = r11.featureFlagsClassicReleaseProvider
                java.lang.Object r2 = r2.get()
                r17 = r2
                com.android.systemui.flags.FeatureFlags r17 = (com.android.systemui.flags.FeatureFlags) r17
                javax.inject.Provider r2 = r12.provideDeviceStateManagerProvider
                java.lang.Object r2 = r2.get()
                r18 = r2
                android.hardware.devicestate.DeviceStateManager r18 = (android.hardware.devicestate.DeviceStateManager) r18
                javax.inject.Provider r2 = r12.provideContentResolverProvider
                java.lang.Object r2 = r2.get()
                r19 = r2
                android.content.ContentResolver r19 = (android.content.ContentResolver) r19
                javax.inject.Provider r2 = r12.provideDisplayManagerProvider
                java.lang.Object r2 = r2.get()
                r20 = r2
                android.hardware.display.DisplayManager r20 = (android.hardware.display.DisplayManager) r20
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) r13
                dagger.internal.InstanceFactory r2 = r13.p4Provider
                dagger.internal.InstanceFactory r3 = r13.p1Provider
                javax.inject.Provider r4 = r12.provideMainExecutorProvider
                java.lang.Object r4 = r4.get()
                r24 = r4
                java.util.concurrent.Executor r24 = (java.util.concurrent.Executor) r24
                com.android.systemui.util.concurrency.ThreadFactoryImpl r25 = new com.android.systemui.util.concurrency.ThreadFactoryImpl
                r25.<init>()
                javax.inject.Provider r4 = r12.provideBgRotationChangeProvider
                java.lang.Object r4 = r4.get()
                r26 = r4
                com.android.systemui.unfold.updates.RotationChangeProvider r26 = (com.android.systemui.unfold.updates.RotationChangeProvider) r26
                javax.inject.Provider r4 = r12.unfoldBgProgressHandlerProvider
                java.lang.Object r4 = r4.get()
                r27 = r4
                android.os.Handler r27 = (android.os.Handler) r27
                javax.inject.Provider r4 = r11.provideDisplayTrackerProvider
                java.lang.Object r4 = r4.get()
                r28 = r4
                com.android.systemui.settings.DisplayTracker r28 = (com.android.systemui.settings.DisplayTracker) r28
                com.android.keyguard.logging.ScrimLogger r29 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1572$$Nest$mscrimLogger(r11)
                java.util.Optional r4 = r11.setDisplayAreaHelper
                r15 = r0
                r16 = r1
                r21 = r2
                r22 = r3
                r23 = r4
                r15.<init>(r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
                goto L_0x01ea
            L_0x0134:
                com.android.systemui.unfold.UnfoldHapticsPlayer r0 = new com.android.systemui.unfold.UnfoldHapticsPlayer
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) r13
                dagger.internal.InstanceFactory r1 = r13.p1Provider
                java.lang.Object r1 = r1.instance
                com.android.systemui.unfold.UnfoldTransitionProgressProvider r1 = (com.android.systemui.unfold.UnfoldTransitionProgressProvider) r1
                javax.inject.Provider r2 = r12.deviceStateManagerFoldProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.updates.FoldProvider r2 = (com.android.systemui.unfold.updates.FoldProvider) r2
                javax.inject.Provider r3 = r12.provideMainExecutorProvider
                java.lang.Object r3 = r3.get()
                java.util.concurrent.Executor r3 = (java.util.concurrent.Executor) r3
                javax.inject.Provider r4 = r12.provideVibratorProvider
                java.lang.Object r4 = r4.get()
                android.os.Vibrator r4 = (android.os.Vibrator) r4
                r0.<init>(r1, r2, r3, r4)
                goto L_0x01ea
            L_0x015b:
                com.android.systemui.unfold.UnfoldTransitionWallpaperController r0 = new com.android.systemui.unfold.UnfoldTransitionWallpaperController
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) r13
                dagger.internal.InstanceFactory r1 = r13.p1Provider
                java.lang.Object r1 = r1.instance
                com.android.systemui.unfold.UnfoldTransitionProgressProvider r1 = (com.android.systemui.unfold.UnfoldTransitionProgressProvider) r1
                javax.inject.Provider r2 = r11.wallpaperControllerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.util.WallpaperController r2 = (com.android.systemui.util.WallpaperController) r2
                r0.<init>(r1, r2)
                goto L_0x01ea
            L_0x0172:
                com.android.systemui.unfold.FoldAodAnimationController r0 = new com.android.systemui.unfold.FoldAodAnimationController
                javax.inject.Provider r1 = r12.provideMainDelayableExecutorProvider
                java.lang.Object r1 = r1.get()
                r4 = r1
                com.android.systemui.util.concurrency.DelayableExecutor r4 = (com.android.systemui.util.concurrency.DelayableExecutor) r4
                javax.inject.Provider r1 = r12.provideDeviceStateManagerProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                android.hardware.devicestate.DeviceStateManager r6 = (android.hardware.devicestate.DeviceStateManager) r6
                javax.inject.Provider r1 = r11.wakefulnessLifecycleProvider
                java.lang.Object r1 = r1.get()
                r7 = r1
                com.android.systemui.keyguard.WakefulnessLifecycle r7 = (com.android.systemui.keyguard.WakefulnessLifecycle) r7
                com.android.systemui.util.settings.GlobalSettingsImpl r8 = r11.globalSettingsImpl()
                javax.inject.Provider r1 = r12.provideLatencyTrackerProvider
                java.lang.Object r1 = r1.get()
                r9 = r1
                com.android.internal.util.LatencyTracker r9 = (com.android.internal.util.LatencyTracker) r9
                javax.inject.Provider r1 = r11.keyguardInteractorProvider
                dagger.Lazy r10 = dagger.internal.DoubleCheck.lazy(r1)
                android.content.Context r5 = r12.context
                r3 = r0
                r3.<init>(r4, r5, r6, r7, r8, r9, r10)
                goto L_0x01ea
            L_0x01a9:
                com.android.systemui.shade.NotificationPanelUnfoldAnimationController r0 = new com.android.systemui.shade.NotificationPanelUnfoldAnimationController
                android.content.Context r1 = r12.context
                dagger.internal.DelegateFactory r2 = r11.statusBarStateControllerImplProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.plugins.statusbar.StatusBarStateController r2 = (com.android.systemui.plugins.statusbar.StatusBarStateController) r2
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) r13
                com.android.systemui.unfold.util.NaturalRotationUnfoldProgressProvider r3 = r13.p2
                r0.<init>(r1, r2, r3)
                goto L_0x01ea
            L_0x01bd:
                com.android.systemui.statusbar.phone.StatusBarMoveFromCenterAnimationController r0 = new com.android.systemui.statusbar.phone.StatusBarMoveFromCenterAnimationController
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) r13
                com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider r1 = r13.p3
                javax.inject.Provider r2 = r12.activityManagerActivityTypeProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider r2 = (com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider) r2
                javax.inject.Provider r3 = r12.provideWindowManagerProvider
                java.lang.Object r3 = r3.get()
                android.view.WindowManager r3 = (android.view.WindowManager) r3
                r0.<init>(r1, r2, r3)
                goto L_0x01ea
            L_0x01d7:
                com.android.keyguard.KeyguardUnfoldTransition r0 = new com.android.keyguard.KeyguardUnfoldTransition
                android.content.Context r1 = r12.context
                dagger.internal.DelegateFactory r2 = r11.statusBarStateControllerImplProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.plugins.statusbar.StatusBarStateController r2 = (com.android.systemui.plugins.statusbar.StatusBarStateController) r2
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) r13
                com.android.systemui.unfold.util.NaturalRotationUnfoldProgressProvider r3 = r13.p2
                r0.<init>(r1, r2, r3)
            L_0x01ea:
                return r0
            L_0x01eb:
                switch(r14) {
                    case 0: goto L_0x045a;
                    case 1: goto L_0x0457;
                    case 2: goto L_0x03b7;
                    case 3: goto L_0x030f;
                    case 4: goto L_0x02f2;
                    case 5: goto L_0x02e0;
                    case 6: goto L_0x02ce;
                    case 7: goto L_0x02ba;
                    case 8: goto L_0x0290;
                    case 9: goto L_0x0280;
                    case 10: goto L_0x0243;
                    case 11: goto L_0x022d;
                    case 12: goto L_0x0214;
                    case 13: goto L_0x0204;
                    case 14: goto L_0x01f4;
                    default: goto L_0x01ee;
                }
            L_0x01ee:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x01f4:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                com.android.systemui.statusbar.phone.PhoneStatusBarView r0 = r13.phoneStatusBarView
                r1 = 2131363682(0x7f0a0762, float:1.834718E38)
                android.view.View r0 = r0.findViewById(r1)
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0467
            L_0x0204:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                com.android.systemui.statusbar.phone.PhoneStatusBarView r0 = r13.phoneStatusBarView
                r1 = 2131363686(0x7f0a0766, float:1.8347188E38)
                android.view.View r0 = r0.findViewById(r1)
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0467
            L_0x0214:
                com.android.systemui.statusbar.phone.StatusBarBoundsProvider r0 = new com.android.systemui.statusbar.phone.StatusBarBoundsProvider
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                javax.inject.Provider r1 = r13.startSideContentProvider
                java.lang.Object r1 = r1.get()
                android.view.View r1 = (android.view.View) r1
                javax.inject.Provider r2 = r13.endSideContentProvider
                java.lang.Object r2 = r2.get()
                android.view.View r2 = (android.view.View) r2
                r0.<init>(r1, r2)
                goto L_0x0467
            L_0x022d:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                com.android.systemui.statusbar.phone.PhoneStatusBarView r0 = r13.phoneStatusBarView
                r1 = 2131363226(0x7f0a059a, float:1.8346255E38)
                android.view.View r0 = r0.findViewById(r1)
                android.view.ViewStub r0 = (android.view.ViewStub) r0
                android.view.View r0 = r0.inflate()
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0467
            L_0x0243:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                javax.inject.Provider r0 = r13.provideClockProvider
                java.lang.Object r0 = r0.get()
                r2 = r0
                com.android.systemui.statusbar.policy.Clock r2 = (com.android.systemui.statusbar.policy.Clock) r2
                javax.inject.Provider r0 = r13.provideOperatorNameViewProvider
                java.lang.Object r0 = r0.get()
                r3 = r0
                android.view.View r3 = (android.view.View) r3
                javax.inject.Provider r0 = r11.provideDemoModeControllerProvider
                java.lang.Object r0 = r0.get()
                r4 = r0
                com.android.systemui.demomode.DemoModeController r4 = (com.android.systemui.demomode.DemoModeController) r4
                javax.inject.Provider r0 = r13.providePhoneStatusBarTransitionsProvider
                java.lang.Object r0 = r0.get()
                r5 = r0
                com.android.systemui.statusbar.phone.PhoneStatusBarTransitions r5 = (com.android.systemui.statusbar.phone.PhoneStatusBarTransitions) r5
                dagger.internal.DelegateFactory r0 = r11.navigationBarControllerImplProvider
                java.lang.Object r0 = r0.get()
                r6 = r0
                com.android.systemui.navigationbar.NavigationBarController r6 = (com.android.systemui.navigationbar.NavigationBarController) r6
                android.content.Context r0 = r12.context
                int r7 = r0.getDisplayId()
                com.android.systemui.statusbar.phone.StatusBarDemoMode r0 = new com.android.systemui.statusbar.phone.StatusBarDemoMode
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7)
                goto L_0x0467
            L_0x0280:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                com.android.systemui.statusbar.phone.PhoneStatusBarView r0 = r13.phoneStatusBarView
                r1 = 2131363193(0x7f0a0579, float:1.8346188E38)
                android.view.View r0 = r0.findViewById(r1)
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0467
            L_0x0290:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                javax.inject.Provider r0 = r13.provideLightsOutNotifViewProvider
                java.lang.Object r0 = r0.get()
                android.view.View r0 = (android.view.View) r0
                javax.inject.Provider r1 = r12.provideWindowManagerProvider
                java.lang.Object r1 = r1.get()
                android.view.WindowManager r1 = (android.view.WindowManager) r1
                javax.inject.Provider r2 = r11.notifLiveDataStoreImplProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.notification.collection.NotifLiveDataStore r2 = (com.android.systemui.statusbar.notification.collection.NotifLiveDataStore) r2
                javax.inject.Provider r3 = r11.provideCommandQueueProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.CommandQueue r3 = (com.android.systemui.statusbar.CommandQueue) r3
                com.android.systemui.statusbar.phone.LegacyLightsOutNotifController r4 = new com.android.systemui.statusbar.phone.LegacyLightsOutNotifController
                r4.<init>(r0, r1, r2, r3)
                r0 = r4
                goto L_0x0467
            L_0x02ba:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                com.android.systemui.statusbar.phone.PhoneStatusBarView r0 = r13.phoneStatusBarView
                r1 = 2131363225(0x7f0a0599, float:1.8346253E38)
                android.view.View r0 = r0.findViewById(r1)
                java.util.Optional r0 = java.util.Optional.ofNullable(r0)
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0467
            L_0x02ce:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                com.android.systemui.statusbar.phone.PhoneStatusBarView r0 = r13.phoneStatusBarView
                r1 = 2131362262(0x7f0a01d6, float:1.83443E38)
                android.view.View r0 = r0.findViewById(r1)
                com.android.systemui.statusbar.policy.Clock r0 = (com.android.systemui.statusbar.policy.Clock) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0467
            L_0x02e0:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                com.android.systemui.statusbar.phone.PhoneStatusBarView r0 = r13.phoneStatusBarView
                r1 = 2131362671(0x7f0a036f, float:1.834513E38)
                android.view.View r0 = r0.findViewById(r1)
                com.android.systemui.statusbar.HeadsUpStatusBarView r0 = (com.android.systemui.statusbar.HeadsUpStatusBarView) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0467
            L_0x02f2:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                com.android.systemui.statusbar.phone.PhoneStatusBarView r0 = r13.phoneStatusBarView
                javax.inject.Provider r1 = r11.statusBarWindowControllerProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.window.StatusBarWindowController r1 = (com.android.systemui.statusbar.window.StatusBarWindowController) r1
                com.android.systemui.statusbar.phone.PhoneStatusBarTransitions r2 = new com.android.systemui.statusbar.phone.PhoneStatusBarTransitions
                com.android.systemui.statusbar.window.StatusBarWindowView r1 = r1.mStatusBarWindowView
                r3 = 2131363678(0x7f0a075e, float:1.8347172E38)
                android.view.View r1 = r1.findViewById(r3)
                r2.<init>(r0, r1)
                r0 = r2
                goto L_0x0467
            L_0x030f:
                com.android.systemui.statusbar.phone.HeadsUpAppearanceController r0 = new com.android.systemui.statusbar.phone.HeadsUpAppearanceController
                com.android.systemui.statusbar.phone.NotificationIconAreaController r4 = r11.notificationIconAreaController()
                javax.inject.Provider r1 = r11.headsUpManagerPhoneProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                com.android.systemui.statusbar.policy.HeadsUpManager r5 = (com.android.systemui.statusbar.policy.HeadsUpManager) r5
                dagger.internal.DelegateFactory r1 = r11.statusBarStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.systemui.plugins.statusbar.StatusBarStateController r6 = (com.android.systemui.plugins.statusbar.StatusBarStateController) r6
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                javax.inject.Provider r1 = r13.providePhoneStatusBarTransitionsProvider
                java.lang.Object r1 = r1.get()
                r7 = r1
                com.android.systemui.statusbar.phone.PhoneStatusBarTransitions r7 = (com.android.systemui.statusbar.phone.PhoneStatusBarTransitions) r7
                dagger.internal.DelegateFactory r1 = r11.keyguardBypassControllerProvider
                java.lang.Object r1 = r1.get()
                r8 = r1
                com.android.systemui.statusbar.phone.KeyguardBypassController r8 = (com.android.systemui.statusbar.phone.KeyguardBypassController) r8
                javax.inject.Provider r1 = r11.notificationWakeUpCoordinatorProvider
                java.lang.Object r1 = r1.get()
                r9 = r1
                com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator r9 = (com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator) r9
                javax.inject.Provider r1 = r11.darkIconDispatcherImplProvider
                java.lang.Object r1 = r1.get()
                r10 = r1
                com.android.systemui.plugins.DarkIconDispatcher r10 = (com.android.systemui.plugins.DarkIconDispatcher) r10
                dagger.internal.DelegateFactory r1 = r11.keyguardStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.policy.KeyguardStateController r1 = (com.android.systemui.statusbar.policy.KeyguardStateController) r1
                javax.inject.Provider r2 = r11.provideCommandQueueProvider
                java.lang.Object r2 = r2.get()
                r12 = r2
                com.android.systemui.statusbar.CommandQueue r12 = (com.android.systemui.statusbar.CommandQueue) r12
                javax.inject.Provider r2 = r11.notificationStackScrollLayoutControllerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController r2 = (com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController) r2
                dagger.internal.DelegateFactory r3 = r11.notificationPanelViewControllerProvider
                java.lang.Object r3 = r3.get()
                r14 = r3
                com.android.systemui.shade.ShadeViewController r14 = (com.android.systemui.shade.ShadeViewController) r14
                javax.inject.Provider r3 = r11.notificationRoundnessManagerProvider
                java.lang.Object r3 = r3.get()
                r15 = r3
                com.android.systemui.statusbar.notification.stack.NotificationRoundnessManager r15 = (com.android.systemui.statusbar.notification.stack.NotificationRoundnessManager) r15
                javax.inject.Provider r3 = r13.providesHeasdUpStatusBarViewProvider
                java.lang.Object r3 = r3.get()
                r16 = r3
                com.android.systemui.statusbar.HeadsUpStatusBarView r16 = (com.android.systemui.statusbar.HeadsUpStatusBarView) r16
                javax.inject.Provider r3 = r13.provideClockProvider
                java.lang.Object r3 = r3.get()
                r17 = r3
                com.android.systemui.statusbar.policy.Clock r17 = (com.android.systemui.statusbar.policy.Clock) r17
                dagger.internal.DelegateFactory r3 = r11.featureFlagsClassicReleaseProvider
                java.lang.Object r3 = r3.get()
                r18 = r3
                com.android.systemui.flags.FeatureFlagsClassic r18 = (com.android.systemui.flags.FeatureFlagsClassic) r18
                com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationIconInteractor r3 = new com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationIconInteractor
                javax.inject.Provider r11 = r11.headsUpNotificationIconViewStateRepositoryProvider
                java.lang.Object r11 = r11.get()
                com.android.systemui.statusbar.notification.data.repository.HeadsUpNotificationIconViewStateRepository r11 = (com.android.systemui.statusbar.notification.data.repository.HeadsUpNotificationIconViewStateRepository) r11
                r3.<init>(r11)
                javax.inject.Provider r11 = r13.provideOperatorFrameNameViewProvider
                java.lang.Object r11 = r11.get()
                r20 = r11
                java.util.Optional r20 = (java.util.Optional) r20
                r19 = r3
                r3 = r0
                r11 = r1
                r13 = r2
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
                goto L_0x0467
            L_0x03b7:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r0 = r13.sysUIGoogleSysUIComponentImpl
                javax.inject.Provider r1 = r0.provideSysUIUnfoldComponentProvider
                java.lang.Object r1 = r1.get()
                java.util.Optional r1 = (java.util.Optional) r1
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r1 = r13.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r1 = r1.provideStatusBarScopedTransitionProvider
                java.lang.Object r1 = r1.get()
                java.util.Optional r1 = (java.util.Optional) r1
                dagger.internal.DelegateFactory r2 = r0.featureFlagsClassicReleaseProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.flags.FeatureFlags r2 = (com.android.systemui.flags.FeatureFlags) r2
                javax.inject.Provider r3 = r0.implProvider
                java.lang.Object r3 = r3.get()
                r25 = r3
                com.android.systemui.scene.shared.flag.SceneContainerFlags r25 = (com.android.systemui.scene.shared.flag.SceneContainerFlags) r25
                com.android.systemui.user.ui.viewmodel.StatusBarUserChipViewModel r3 = new com.android.systemui.user.ui.viewmodel.StatusBarUserChipViewModel
                dagger.internal.DelegateFactory r4 = r0.userSwitcherInteractorProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.user.domain.interactor.UserSwitcherInteractor r4 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor) r4
                r3.<init>(r4)
                dagger.internal.DelegateFactory r4 = r0.centralSurfacesGoogleProvider
                java.lang.Object r4 = r4.get()
                r17 = r4
                com.android.systemui.statusbar.phone.CentralSurfaces r17 = (com.android.systemui.statusbar.phone.CentralSurfaces) r17
                javax.inject.Provider r4 = r0.statusBarWindowStateControllerProvider
                java.lang.Object r4 = r4.get()
                r18 = r4
                com.android.systemui.statusbar.window.StatusBarWindowStateController r18 = (com.android.systemui.statusbar.window.StatusBarWindowStateController) r18
                javax.inject.Provider r4 = r0.shadeControllerImplProvider
                java.lang.Object r4 = r4.get()
                r19 = r4
                com.android.systemui.shade.ShadeController r19 = (com.android.systemui.shade.ShadeController) r19
                dagger.internal.DelegateFactory r4 = r0.notificationPanelViewControllerProvider
                java.lang.Object r4 = r4.get()
                r20 = r4
                com.android.systemui.shade.ShadeViewController r20 = (com.android.systemui.shade.ShadeViewController) r20
                javax.inject.Provider r4 = r0.providesWindowRootViewProvider
                com.android.systemui.shade.ShadeLogger r5 = new com.android.systemui.shade.ShadeLogger
                javax.inject.Provider r6 = r0.provideShadeLogBufferProvider
                java.lang.Object r6 = r6.get()
                com.android.systemui.log.LogBuffer r6 = (com.android.systemui.log.LogBuffer) r6
                r5.<init>(r6)
                javax.inject.Provider r6 = r0.viewUtilProvider
                java.lang.Object r6 = r6.get()
                r24 = r6
                com.android.systemui.util.view.ViewUtil r24 = (com.android.systemui.util.view.ViewUtil) r24
                javax.inject.Provider r6 = r0.configurationControllerImplProvider
                java.lang.Object r6 = r6.get()
                r26 = r6
                com.android.systemui.statusbar.policy.ConfigurationController r26 = (com.android.systemui.statusbar.policy.ConfigurationController) r26
                com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory r27 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1629$$Nest$mstatusOverlayHoverListenerFactory(r0)
                com.android.systemui.flags.UnreleasedFlag r0 = com.android.systemui.flags.Flags.NULL_FLAG
                r2.getClass()
                com.android.systemui.statusbar.phone.PhoneStatusBarViewController r0 = new com.android.systemui.statusbar.phone.PhoneStatusBarViewController
                java.lang.Object r1 = r1.orElse(r9)
                r16 = r1
                com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider r16 = (com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider) r16
                com.android.systemui.statusbar.phone.PhoneStatusBarView r15 = r13.phoneStatusBarView
                r14 = r0
                r21 = r4
                r22 = r5
                r23 = r3
                r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
                goto L_0x0467
            L_0x0457:
                com.android.systemui.statusbar.phone.StatusBarLocation r0 = com.android.systemui.statusbar.phone.StatusBarLocation.HOME
                goto L_0x0467
            L_0x045a:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) r13
                com.android.systemui.statusbar.phone.PhoneStatusBarView r0 = r13.phoneStatusBarView
                android.view.View r0 = r0.findViewById(r8)
                com.android.systemui.battery.BatteryMeterView r0 = (com.android.systemui.battery.BatteryMeterView) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
            L_0x0467:
                return r0
            L_0x0468:
                if (r14 != 0) goto L_0x048f
                com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl r0 = new com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) r13
                java.lang.String r2 = r13.nodeLabel
                javax.inject.Provider r1 = r12.providerLayoutInflaterProvider
                java.lang.Object r1 = r1.get()
                r3 = r1
                android.view.LayoutInflater r3 = (android.view.LayoutInflater) r3
                java.lang.Integer r1 = r13.headerText
                int r4 = r1.intValue()
                dagger.internal.DelegateFactory r1 = r11.activityStarterImplProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                com.android.systemui.plugins.ActivityStarter r5 = (com.android.systemui.plugins.ActivityStarter) r5
                java.lang.String r6 = r13.clickIntentAction
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6)
                return r0
            L_0x048f:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x0495:
                switch(r14) {
                    case 0: goto L_0x06f4;
                    case 1: goto L_0x0699;
                    case 2: goto L_0x068d;
                    case 3: goto L_0x066c;
                    case 4: goto L_0x0642;
                    case 5: goto L_0x062e;
                    case 6: goto L_0x05c2;
                    case 7: goto L_0x05bc;
                    case 8: goto L_0x056f;
                    case 9: goto L_0x052e;
                    case 10: goto L_0x050e;
                    case 11: goto L_0x04ff;
                    case 12: goto L_0x04bf;
                    case 13: goto L_0x049e;
                    default: goto L_0x0498;
                }
            L_0x0498:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x049e:
                com.android.systemui.qs.QSSquishinessController r0 = new com.android.systemui.qs.QSSquishinessController
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                javax.inject.Provider r1 = r13.qSAnimatorProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.qs.QSAnimator r1 = (com.android.systemui.qs.QSAnimator) r1
                javax.inject.Provider r2 = r13.qSPanelControllerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.qs.QSPanelController r2 = (com.android.systemui.qs.QSPanelController) r2
                javax.inject.Provider r3 = r13.quickQSPanelControllerProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.qs.QuickQSPanelController r3 = (com.android.systemui.qs.QuickQSPanelController) r3
                r0.<init>(r1, r2, r3)
                goto L_0x0797
            L_0x04bf:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                android.view.View r0 = r0.requireViewById(r4)
                r2 = r0
                com.android.systemui.qs.QSFooterView r2 = (com.android.systemui.qs.QSFooterView) r2
                dagger.internal.DelegateFactory r0 = r11.provideUserTrackerProvider
                java.lang.Object r0 = r0.get()
                r3 = r0
                com.android.systemui.settings.UserTracker r3 = (com.android.systemui.settings.UserTracker) r3
                javax.inject.Provider r0 = r11.falsingManagerProxyProvider
                java.lang.Object r0 = r0.get()
                r4 = r0
                com.android.systemui.plugins.FalsingManager r4 = (com.android.systemui.plugins.FalsingManager) r4
                dagger.internal.DelegateFactory r0 = r11.activityStarterImplProvider
                java.lang.Object r0 = r0.get()
                r5 = r0
                com.android.systemui.plugins.ActivityStarter r5 = (com.android.systemui.plugins.ActivityStarter) r5
                javax.inject.Provider r0 = r13.qSPanelControllerProvider
                java.lang.Object r0 = r0.get()
                r6 = r0
                com.android.systemui.qs.QSPanelController r6 = (com.android.systemui.qs.QSPanelController) r6
                javax.inject.Provider r0 = r11.retailModeInteractorImplProvider
                java.lang.Object r0 = r0.get()
                r7 = r0
                com.android.systemui.retail.domain.interactor.RetailModeInteractor r7 = (com.android.systemui.retail.domain.interactor.RetailModeInteractor) r7
                com.android.systemui.qs.QSFooterViewController r0 = new com.android.systemui.qs.QSFooterViewController
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7)
                goto L_0x0797
            L_0x04ff:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                javax.inject.Provider r0 = r13.qSFooterViewControllerProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.qs.QSFooterViewController r0 = (com.android.systemui.qs.QSFooterViewController) r0
                r0.init$10()
                goto L_0x0797
            L_0x050e:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                com.android.systemui.qs.QuickStatusBarHeader r0 = com.android.systemui.qs.dagger.QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(r0)
                javax.inject.Provider r1 = r13.quickQSPanelControllerProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.qs.QuickQSPanelController r1 = (com.android.systemui.qs.QuickQSPanelController) r1
                javax.inject.Provider r2 = r11.implProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.scene.shared.flag.SceneContainerFlags r2 = (com.android.systemui.scene.shared.flag.SceneContainerFlags) r2
                com.android.systemui.qs.QuickStatusBarHeaderController r3 = new com.android.systemui.qs.QuickStatusBarHeaderController
                r3.<init>(r0, r1, r2)
                r0 = r3
                goto L_0x0797
            L_0x052e:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                android.view.View r0 = r0.requireViewById(r3)
                r2 = r0
                com.android.systemui.qs.QSContainerImpl r2 = (com.android.systemui.qs.QSContainerImpl) r2
                javax.inject.Provider r0 = r13.qSPanelControllerProvider
                java.lang.Object r0 = r0.get()
                r3 = r0
                com.android.systemui.qs.QSPanelController r3 = (com.android.systemui.qs.QSPanelController) r3
                javax.inject.Provider r0 = r13.quickStatusBarHeaderControllerProvider
                java.lang.Object r0 = r0.get()
                javax.inject.Provider r1 = r11.configurationControllerImplProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                com.android.systemui.statusbar.policy.ConfigurationController r5 = (com.android.systemui.statusbar.policy.ConfigurationController) r5
                javax.inject.Provider r1 = r11.falsingManagerProxyProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.systemui.plugins.FalsingManager r6 = (com.android.systemui.plugins.FalsingManager) r6
                javax.inject.Provider r1 = r11.implProvider
                java.lang.Object r1 = r1.get()
                r7 = r1
                com.android.systemui.scene.shared.flag.SceneContainerFlags r7 = (com.android.systemui.scene.shared.flag.SceneContainerFlags) r7
                com.android.systemui.qs.QSContainerImplController r8 = new com.android.systemui.qs.QSContainerImplController
                r4 = r0
                com.android.systemui.qs.QuickStatusBarHeaderController r4 = (com.android.systemui.qs.QuickStatusBarHeaderController) r4
                r1 = r8
                r1.<init>(r2, r3, r4, r5, r6, r7)
                r0 = r8
                goto L_0x0797
            L_0x056f:
                com.android.systemui.qs.QSAnimator r0 = new com.android.systemui.qs.QSAnimator
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r10 = r13.rootView
                com.android.systemui.qs.QuickStatusBarHeader r1 = com.android.systemui.qs.dagger.QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(r10)
                android.view.View r1 = r1.requireViewById(r5)
                com.android.systemui.qs.QuickQSPanel r1 = (com.android.systemui.qs.QuickQSPanel) r1
                javax.inject.Provider r2 = r13.qSPanelControllerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.qs.QSPanelController r2 = (com.android.systemui.qs.QSPanelController) r2
                javax.inject.Provider r3 = r13.quickQSPanelControllerProvider
                java.lang.Object r3 = r3.get()
                r13 = r3
                com.android.systemui.qs.QuickQSPanelController r13 = (com.android.systemui.qs.QuickQSPanelController) r13
                dagger.internal.DelegateFactory r3 = r11.qSHostAdapterProvider
                java.lang.Object r3 = r3.get()
                r14 = r3
                com.android.systemui.qs.QSHost r14 = (com.android.systemui.qs.QSHost) r14
                javax.inject.Provider r3 = r12.provideMainDelayableExecutorProvider
                java.lang.Object r3 = r3.get()
                r15 = r3
                com.android.systemui.util.concurrency.DelayableExecutor r15 = (com.android.systemui.util.concurrency.DelayableExecutor) r15
                javax.inject.Provider r3 = r11.tunerServiceImplProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.tuner.TunerService r3 = (com.android.systemui.tuner.TunerService) r3
                javax.inject.Provider r3 = r12.qSExpansionPathInterpolatorProvider
                java.lang.Object r3 = r3.get()
                r16 = r3
                com.android.systemui.qs.QSExpansionPathInterpolator r16 = (com.android.systemui.qs.QSExpansionPathInterpolator) r16
                r9 = r0
                r11 = r1
                r12 = r2
                r9.<init>(r10, r11, r12, r13, r14, r15, r16)
                goto L_0x0797
            L_0x05bc:
                android.content.Context r0 = r12.context
                java.lang.Boolean r0 = java.lang.Boolean.FALSE
                goto L_0x0797
            L_0x05c2:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                com.android.systemui.qs.QuickStatusBarHeader r0 = com.android.systemui.qs.dagger.QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(r0)
                android.view.View r0 = r0.requireViewById(r5)
                r15 = r0
                com.android.systemui.qs.QuickQSPanel r15 = (com.android.systemui.qs.QuickQSPanel) r15
                dagger.internal.DelegateFactory r0 = r11.qSHostAdapterProvider
                java.lang.Object r0 = r0.get()
                r16 = r0
                com.android.systemui.qs.QSHost r16 = (com.android.systemui.qs.QSHost) r16
                javax.inject.Provider r0 = r13.qSCustomizerControllerProvider
                java.lang.Object r0 = r0.get()
                r17 = r0
                com.android.systemui.qs.customize.QSCustomizerController r17 = (com.android.systemui.qs.customize.QSCustomizerController) r17
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r0 = r13.sysUIGoogleGlobalRootComponentImpl
                android.content.Context r0 = r0.context
                javax.inject.Provider r0 = r11.providesQuickQSMediaHostProvider
                java.lang.Object r0 = r0.get()
                r19 = r0
                com.android.systemui.media.controls.ui.MediaHost r19 = (com.android.systemui.media.controls.ui.MediaHost) r19
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl$SwitchingProvider r0 = r13.providesQSUsingCollapsedLandscapeMediaProvider
                javax.inject.Provider r1 = r12.provideMetricsLoggerProvider
                java.lang.Object r1 = r1.get()
                r21 = r1
                com.android.internal.logging.MetricsLogger r21 = (com.android.internal.logging.MetricsLogger) r21
                javax.inject.Provider r1 = r12.provideUiEventLoggerProvider
                java.lang.Object r1 = r1.get()
                r22 = r1
                com.android.internal.logging.UiEventLogger r22 = (com.android.internal.logging.UiEventLogger) r22
                com.android.systemui.qs.logging.QSLogger r23 = r11.qSLogger()
                javax.inject.Provider r1 = r12.dumpManagerProvider
                java.lang.Object r1 = r1.get()
                r24 = r1
                com.android.systemui.dump.DumpManager r24 = (com.android.systemui.dump.DumpManager) r24
                javax.inject.Provider r1 = r11.splitShadeStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                r25 = r1
                com.android.systemui.statusbar.policy.SplitShadeStateController r25 = (com.android.systemui.statusbar.policy.SplitShadeStateController) r25
                com.android.systemui.qs.QuickQSPanelController r1 = new com.android.systemui.qs.QuickQSPanelController
                r18 = 0
                r14 = r1
                r20 = r0
                r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
            L_0x062b:
                r0 = r1
                goto L_0x0797
            L_0x062e:
                android.content.Context r0 = r12.context
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                javax.inject.Provider r1 = r13.qSCustomizerControllerProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.qs.customize.QSCustomizerController r1 = (com.android.systemui.qs.customize.QSCustomizerController) r1
                com.android.systemui.qs.QSTileRevealController$Factory r2 = new com.android.systemui.qs.QSTileRevealController$Factory
                r2.<init>(r0, r1)
                r0 = r2
                goto L_0x0797
            L_0x0642:
                com.android.systemui.qs.customize.TileAdapter r0 = new com.android.systemui.qs.customize.TileAdapter
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                r13.getClass()
                android.view.View r1 = r13.rootView
                android.content.Context r1 = r1.getContext()
                dagger.internal.DelegateFactory r2 = r11.qSHostAdapterProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.qs.QSHost r2 = (com.android.systemui.qs.QSHost) r2
                javax.inject.Provider r3 = r12.provideUiEventLoggerProvider
                java.lang.Object r3 = r3.get()
                com.android.internal.logging.UiEventLogger r3 = (com.android.internal.logging.UiEventLogger) r3
                dagger.internal.DelegateFactory r4 = r11.featureFlagsClassicReleaseProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.flags.FeatureFlags r4 = (com.android.systemui.flags.FeatureFlags) r4
                r0.<init>(r1, r2, r3, r4)
                goto L_0x0797
            L_0x066c:
                com.android.systemui.qs.customize.TileQueryHelper r0 = new com.android.systemui.qs.customize.TileQueryHelper
                android.content.Context r1 = r12.context
                dagger.internal.DelegateFactory r2 = r11.provideUserTrackerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.settings.UserTracker r2 = (com.android.systemui.settings.UserTracker) r2
                javax.inject.Provider r3 = r12.provideMainExecutorProvider
                java.lang.Object r3 = r3.get()
                java.util.concurrent.Executor r3 = (java.util.concurrent.Executor) r3
                javax.inject.Provider r4 = r11.provideBackgroundExecutorProvider
                java.lang.Object r4 = r4.get()
                java.util.concurrent.Executor r4 = (java.util.concurrent.Executor) r4
                r0.<init>(r1, r2, r3, r4)
                goto L_0x0797
            L_0x068d:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                android.view.View r0 = r0.requireViewById(r2)
                com.android.systemui.qs.customize.QSCustomizer r0 = (com.android.systemui.qs.customize.QSCustomizer) r0
                goto L_0x0797
            L_0x0699:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                javax.inject.Provider r0 = r13.providesQSCutomizerProvider
                java.lang.Object r0 = r0.get()
                r2 = r0
                com.android.systemui.qs.customize.QSCustomizer r2 = (com.android.systemui.qs.customize.QSCustomizer) r2
                javax.inject.Provider r0 = r13.tileQueryHelperProvider
                java.lang.Object r0 = r0.get()
                r3 = r0
                com.android.systemui.qs.customize.TileQueryHelper r3 = (com.android.systemui.qs.customize.TileQueryHelper) r3
                dagger.internal.DelegateFactory r0 = r11.qSHostAdapterProvider
                java.lang.Object r0 = r0.get()
                r4 = r0
                com.android.systemui.qs.QSHost r4 = (com.android.systemui.qs.QSHost) r4
                javax.inject.Provider r0 = r13.tileAdapterProvider
                java.lang.Object r0 = r0.get()
                r5 = r0
                com.android.systemui.qs.customize.TileAdapter r5 = (com.android.systemui.qs.customize.TileAdapter) r5
                javax.inject.Provider r0 = r12.screenLifecycleProvider
                java.lang.Object r0 = r0.get()
                r6 = r0
                com.android.systemui.keyguard.ScreenLifecycle r6 = (com.android.systemui.keyguard.ScreenLifecycle) r6
                dagger.internal.DelegateFactory r0 = r11.keyguardStateControllerImplProvider
                java.lang.Object r0 = r0.get()
                r7 = r0
                com.android.systemui.statusbar.policy.KeyguardStateController r7 = (com.android.systemui.statusbar.policy.KeyguardStateController) r7
                javax.inject.Provider r0 = r11.lightBarControllerProvider
                java.lang.Object r0 = r0.get()
                r8 = r0
                com.android.systemui.statusbar.phone.LightBarController r8 = (com.android.systemui.statusbar.phone.LightBarController) r8
                javax.inject.Provider r0 = r11.configurationControllerImplProvider
                java.lang.Object r0 = r0.get()
                r9 = r0
                com.android.systemui.statusbar.policy.ConfigurationController r9 = (com.android.systemui.statusbar.policy.ConfigurationController) r9
                javax.inject.Provider r0 = r12.provideUiEventLoggerProvider
                java.lang.Object r0 = r0.get()
                r10 = r0
                com.android.internal.logging.UiEventLogger r10 = (com.android.internal.logging.UiEventLogger) r10
                com.android.systemui.qs.customize.QSCustomizerController r0 = new com.android.systemui.qs.customize.QSCustomizerController
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
                goto L_0x0797
            L_0x06f4:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                android.view.View r0 = r0.requireViewById(r1)
                r15 = r0
                com.android.systemui.qs.QSPanel r15 = (com.android.systemui.qs.QSPanel) r15
                javax.inject.Provider r0 = r11.tunerServiceImplProvider
                java.lang.Object r0 = r0.get()
                r16 = r0
                com.android.systemui.tuner.TunerService r16 = (com.android.systemui.tuner.TunerService) r16
                dagger.internal.DelegateFactory r0 = r11.qSHostAdapterProvider
                java.lang.Object r0 = r0.get()
                r17 = r0
                com.android.systemui.qs.QSHost r17 = (com.android.systemui.qs.QSHost) r17
                javax.inject.Provider r0 = r13.qSCustomizerControllerProvider
                java.lang.Object r0 = r0.get()
                r18 = r0
                com.android.systemui.qs.customize.QSCustomizerController r18 = (com.android.systemui.qs.customize.QSCustomizerController) r18
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r0 = r13.sysUIGoogleGlobalRootComponentImpl
                android.content.Context r0 = r0.context
                javax.inject.Provider r0 = r11.providesQSMediaHostProvider
                java.lang.Object r0 = r0.get()
                r20 = r0
                com.android.systemui.media.controls.ui.MediaHost r20 = (com.android.systemui.media.controls.ui.MediaHost) r20
                javax.inject.Provider r0 = r13.factoryProvider
                java.lang.Object r0 = r0.get()
                javax.inject.Provider r1 = r12.dumpManagerProvider
                java.lang.Object r1 = r1.get()
                r22 = r1
                com.android.systemui.dump.DumpManager r22 = (com.android.systemui.dump.DumpManager) r22
                javax.inject.Provider r1 = r12.provideMetricsLoggerProvider
                java.lang.Object r1 = r1.get()
                r23 = r1
                com.android.internal.logging.MetricsLogger r23 = (com.android.internal.logging.MetricsLogger) r23
                javax.inject.Provider r1 = r12.provideUiEventLoggerProvider
                java.lang.Object r1 = r1.get()
                r24 = r1
                com.android.internal.logging.UiEventLogger r24 = (com.android.internal.logging.UiEventLogger) r24
                com.android.systemui.qs.logging.QSLogger r25 = r11.qSLogger()
                javax.inject.Provider r1 = r11.factoryProvider25
                java.lang.Object r1 = r1.get()
                r26 = r1
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$21 r26 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$21) r26
                com.android.systemui.settings.brightness.BrightnessSliderController$Factory r27 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1403$$Nest$mbrightnessSliderControllerFactory(r11)
                javax.inject.Provider r1 = r11.falsingManagerProxyProvider
                java.lang.Object r1 = r1.get()
                r28 = r1
                com.android.systemui.plugins.FalsingManager r28 = (com.android.systemui.plugins.FalsingManager) r28
                dagger.internal.DelegateFactory r1 = r11.statusBarKeyguardViewManagerProvider
                java.lang.Object r1 = r1.get()
                r29 = r1
                com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r29 = (com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager) r29
                javax.inject.Provider r1 = r11.splitShadeStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                r30 = r1
                com.android.systemui.statusbar.policy.SplitShadeStateController r30 = (com.android.systemui.statusbar.policy.SplitShadeStateController) r30
                javax.inject.Provider r1 = r11.implProvider
                java.lang.Object r1 = r1.get()
                r31 = r1
                com.android.systemui.scene.shared.flag.SceneContainerFlags r31 = (com.android.systemui.scene.shared.flag.SceneContainerFlags) r31
                com.android.systemui.qs.QSPanelController r1 = new com.android.systemui.qs.QSPanelController
                r14 = r1
                r21 = r0
                com.android.systemui.qs.QSTileRevealController$Factory r21 = (com.android.systemui.qs.QSTileRevealController.Factory) r21
                r19 = 0
                r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31)
                goto L_0x062b
            L_0x0797:
                return r0
            L_0x0798:
                switch(r14) {
                    case 0: goto L_0x0a06;
                    case 1: goto L_0x09ab;
                    case 2: goto L_0x099f;
                    case 3: goto L_0x097e;
                    case 4: goto L_0x0954;
                    case 5: goto L_0x0940;
                    case 6: goto L_0x08d2;
                    case 7: goto L_0x08bf;
                    case 8: goto L_0x0872;
                    case 9: goto L_0x0831;
                    case 10: goto L_0x0811;
                    case 11: goto L_0x0802;
                    case 12: goto L_0x07c2;
                    case 13: goto L_0x07a1;
                    default: goto L_0x079b;
                }
            L_0x079b:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x07a1:
                com.android.systemui.qs.QSSquishinessController r0 = new com.android.systemui.qs.QSSquishinessController
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                javax.inject.Provider r1 = r13.qSAnimatorProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.qs.QSAnimator r1 = (com.android.systemui.qs.QSAnimator) r1
                javax.inject.Provider r2 = r13.qSPanelControllerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.qs.QSPanelController r2 = (com.android.systemui.qs.QSPanelController) r2
                javax.inject.Provider r3 = r13.quickQSPanelControllerProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.qs.QuickQSPanelController r3 = (com.android.systemui.qs.QuickQSPanelController) r3
                r0.<init>(r1, r2, r3)
                goto L_0x0aab
            L_0x07c2:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                android.view.View r0 = r0.requireViewById(r4)
                r2 = r0
                com.android.systemui.qs.QSFooterView r2 = (com.android.systemui.qs.QSFooterView) r2
                dagger.internal.DelegateFactory r0 = r11.provideUserTrackerProvider
                java.lang.Object r0 = r0.get()
                r3 = r0
                com.android.systemui.settings.UserTracker r3 = (com.android.systemui.settings.UserTracker) r3
                javax.inject.Provider r0 = r11.falsingManagerProxyProvider
                java.lang.Object r0 = r0.get()
                r4 = r0
                com.android.systemui.plugins.FalsingManager r4 = (com.android.systemui.plugins.FalsingManager) r4
                dagger.internal.DelegateFactory r0 = r11.activityStarterImplProvider
                java.lang.Object r0 = r0.get()
                r5 = r0
                com.android.systemui.plugins.ActivityStarter r5 = (com.android.systemui.plugins.ActivityStarter) r5
                javax.inject.Provider r0 = r13.qSPanelControllerProvider
                java.lang.Object r0 = r0.get()
                r6 = r0
                com.android.systemui.qs.QSPanelController r6 = (com.android.systemui.qs.QSPanelController) r6
                javax.inject.Provider r0 = r11.retailModeInteractorImplProvider
                java.lang.Object r0 = r0.get()
                r7 = r0
                com.android.systemui.retail.domain.interactor.RetailModeInteractor r7 = (com.android.systemui.retail.domain.interactor.RetailModeInteractor) r7
                com.android.systemui.qs.QSFooterViewController r0 = new com.android.systemui.qs.QSFooterViewController
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7)
                goto L_0x0aab
            L_0x0802:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                javax.inject.Provider r0 = r13.qSFooterViewControllerProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.qs.QSFooterViewController r0 = (com.android.systemui.qs.QSFooterViewController) r0
                r0.init$10()
                goto L_0x0aab
            L_0x0811:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                com.android.systemui.qs.QuickStatusBarHeader r0 = com.android.systemui.qs.dagger.QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(r0)
                javax.inject.Provider r1 = r13.quickQSPanelControllerProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.qs.QuickQSPanelController r1 = (com.android.systemui.qs.QuickQSPanelController) r1
                javax.inject.Provider r2 = r11.implProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.scene.shared.flag.SceneContainerFlags r2 = (com.android.systemui.scene.shared.flag.SceneContainerFlags) r2
                com.android.systemui.qs.QuickStatusBarHeaderController r3 = new com.android.systemui.qs.QuickStatusBarHeaderController
                r3.<init>(r0, r1, r2)
                r0 = r3
                goto L_0x0aab
            L_0x0831:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                android.view.View r0 = r0.requireViewById(r3)
                r2 = r0
                com.android.systemui.qs.QSContainerImpl r2 = (com.android.systemui.qs.QSContainerImpl) r2
                javax.inject.Provider r0 = r13.qSPanelControllerProvider
                java.lang.Object r0 = r0.get()
                r3 = r0
                com.android.systemui.qs.QSPanelController r3 = (com.android.systemui.qs.QSPanelController) r3
                javax.inject.Provider r0 = r13.quickStatusBarHeaderControllerProvider
                java.lang.Object r0 = r0.get()
                javax.inject.Provider r1 = r11.configurationControllerImplProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                com.android.systemui.statusbar.policy.ConfigurationController r5 = (com.android.systemui.statusbar.policy.ConfigurationController) r5
                javax.inject.Provider r1 = r11.falsingManagerProxyProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.systemui.plugins.FalsingManager r6 = (com.android.systemui.plugins.FalsingManager) r6
                javax.inject.Provider r1 = r11.implProvider
                java.lang.Object r1 = r1.get()
                r7 = r1
                com.android.systemui.scene.shared.flag.SceneContainerFlags r7 = (com.android.systemui.scene.shared.flag.SceneContainerFlags) r7
                com.android.systemui.qs.QSContainerImplController r8 = new com.android.systemui.qs.QSContainerImplController
                r4 = r0
                com.android.systemui.qs.QuickStatusBarHeaderController r4 = (com.android.systemui.qs.QuickStatusBarHeaderController) r4
                r1 = r8
                r1.<init>(r2, r3, r4, r5, r6, r7)
                r0 = r8
                goto L_0x0aab
            L_0x0872:
                com.android.systemui.qs.QSAnimator r0 = new com.android.systemui.qs.QSAnimator
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r10 = r13.rootView
                com.android.systemui.qs.QuickStatusBarHeader r1 = com.android.systemui.qs.dagger.QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(r10)
                android.view.View r1 = r1.requireViewById(r5)
                com.android.systemui.qs.QuickQSPanel r1 = (com.android.systemui.qs.QuickQSPanel) r1
                javax.inject.Provider r2 = r13.qSPanelControllerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.qs.QSPanelController r2 = (com.android.systemui.qs.QSPanelController) r2
                javax.inject.Provider r3 = r13.quickQSPanelControllerProvider
                java.lang.Object r3 = r3.get()
                r13 = r3
                com.android.systemui.qs.QuickQSPanelController r13 = (com.android.systemui.qs.QuickQSPanelController) r13
                dagger.internal.DelegateFactory r3 = r11.qSHostAdapterProvider
                java.lang.Object r3 = r3.get()
                r14 = r3
                com.android.systemui.qs.QSHost r14 = (com.android.systemui.qs.QSHost) r14
                javax.inject.Provider r3 = r12.provideMainDelayableExecutorProvider
                java.lang.Object r3 = r3.get()
                r15 = r3
                com.android.systemui.util.concurrency.DelayableExecutor r15 = (com.android.systemui.util.concurrency.DelayableExecutor) r15
                javax.inject.Provider r3 = r11.tunerServiceImplProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.tuner.TunerService r3 = (com.android.systemui.tuner.TunerService) r3
                javax.inject.Provider r3 = r12.qSExpansionPathInterpolatorProvider
                java.lang.Object r3 = r3.get()
                r16 = r3
                com.android.systemui.qs.QSExpansionPathInterpolator r16 = (com.android.systemui.qs.QSExpansionPathInterpolator) r16
                r9 = r0
                r11 = r1
                r12 = r2
                r9.<init>(r10, r11, r12, r13, r14, r15, r16)
                goto L_0x0aab
            L_0x08bf:
                android.content.Context r0 = r12.context
                android.content.res.Resources r0 = r0.getResources()
                r1 = 2131034168(0x7f050038, float:1.7678846E38)
                boolean r0 = r0.getBoolean(r1)
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                goto L_0x0aab
            L_0x08d2:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                com.android.systemui.qs.QuickStatusBarHeader r0 = com.android.systemui.qs.dagger.QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(r0)
                android.view.View r0 = r0.requireViewById(r5)
                r15 = r0
                com.android.systemui.qs.QuickQSPanel r15 = (com.android.systemui.qs.QuickQSPanel) r15
                dagger.internal.DelegateFactory r0 = r11.qSHostAdapterProvider
                java.lang.Object r0 = r0.get()
                r16 = r0
                com.android.systemui.qs.QSHost r16 = (com.android.systemui.qs.QSHost) r16
                javax.inject.Provider r0 = r13.qSCustomizerControllerProvider
                java.lang.Object r0 = r0.get()
                r17 = r0
                com.android.systemui.qs.customize.QSCustomizerController r17 = (com.android.systemui.qs.customize.QSCustomizerController) r17
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r0 = r13.sysUIGoogleGlobalRootComponentImpl
                android.content.Context r0 = r0.context
                boolean r18 = com.android.systemui.util.Utils.useQsMediaPlayer(r0)
                javax.inject.Provider r0 = r11.providesQuickQSMediaHostProvider
                java.lang.Object r0 = r0.get()
                r19 = r0
                com.android.systemui.media.controls.ui.MediaHost r19 = (com.android.systemui.media.controls.ui.MediaHost) r19
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl$SwitchingProvider r0 = r13.providesQSUsingCollapsedLandscapeMediaProvider
                javax.inject.Provider r1 = r12.provideMetricsLoggerProvider
                java.lang.Object r1 = r1.get()
                r21 = r1
                com.android.internal.logging.MetricsLogger r21 = (com.android.internal.logging.MetricsLogger) r21
                javax.inject.Provider r1 = r12.provideUiEventLoggerProvider
                java.lang.Object r1 = r1.get()
                r22 = r1
                com.android.internal.logging.UiEventLogger r22 = (com.android.internal.logging.UiEventLogger) r22
                com.android.systemui.qs.logging.QSLogger r23 = r11.qSLogger()
                javax.inject.Provider r1 = r12.dumpManagerProvider
                java.lang.Object r1 = r1.get()
                r24 = r1
                com.android.systemui.dump.DumpManager r24 = (com.android.systemui.dump.DumpManager) r24
                javax.inject.Provider r1 = r11.splitShadeStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                r25 = r1
                com.android.systemui.statusbar.policy.SplitShadeStateController r25 = (com.android.systemui.statusbar.policy.SplitShadeStateController) r25
                com.android.systemui.qs.QuickQSPanelController r1 = new com.android.systemui.qs.QuickQSPanelController
                r14 = r1
                r20 = r0
                r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
            L_0x093d:
                r0 = r1
                goto L_0x0aab
            L_0x0940:
                android.content.Context r0 = r12.context
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                javax.inject.Provider r1 = r13.qSCustomizerControllerProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.qs.customize.QSCustomizerController r1 = (com.android.systemui.qs.customize.QSCustomizerController) r1
                com.android.systemui.qs.QSTileRevealController$Factory r2 = new com.android.systemui.qs.QSTileRevealController$Factory
                r2.<init>(r0, r1)
                r0 = r2
                goto L_0x0aab
            L_0x0954:
                com.android.systemui.qs.customize.TileAdapter r0 = new com.android.systemui.qs.customize.TileAdapter
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                r13.getClass()
                android.view.View r1 = r13.rootView
                android.content.Context r1 = r1.getContext()
                dagger.internal.DelegateFactory r2 = r11.qSHostAdapterProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.qs.QSHost r2 = (com.android.systemui.qs.QSHost) r2
                javax.inject.Provider r3 = r12.provideUiEventLoggerProvider
                java.lang.Object r3 = r3.get()
                com.android.internal.logging.UiEventLogger r3 = (com.android.internal.logging.UiEventLogger) r3
                dagger.internal.DelegateFactory r4 = r11.featureFlagsClassicReleaseProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.flags.FeatureFlags r4 = (com.android.systemui.flags.FeatureFlags) r4
                r0.<init>(r1, r2, r3, r4)
                goto L_0x0aab
            L_0x097e:
                com.android.systemui.qs.customize.TileQueryHelper r0 = new com.android.systemui.qs.customize.TileQueryHelper
                android.content.Context r1 = r12.context
                dagger.internal.DelegateFactory r2 = r11.provideUserTrackerProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.settings.UserTracker r2 = (com.android.systemui.settings.UserTracker) r2
                javax.inject.Provider r3 = r12.provideMainExecutorProvider
                java.lang.Object r3 = r3.get()
                java.util.concurrent.Executor r3 = (java.util.concurrent.Executor) r3
                javax.inject.Provider r4 = r11.provideBackgroundExecutorProvider
                java.lang.Object r4 = r4.get()
                java.util.concurrent.Executor r4 = (java.util.concurrent.Executor) r4
                r0.<init>(r1, r2, r3, r4)
                goto L_0x0aab
            L_0x099f:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                android.view.View r0 = r0.requireViewById(r2)
                com.android.systemui.qs.customize.QSCustomizer r0 = (com.android.systemui.qs.customize.QSCustomizer) r0
                goto L_0x0aab
            L_0x09ab:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                javax.inject.Provider r0 = r13.providesQSCutomizerProvider
                java.lang.Object r0 = r0.get()
                r2 = r0
                com.android.systemui.qs.customize.QSCustomizer r2 = (com.android.systemui.qs.customize.QSCustomizer) r2
                javax.inject.Provider r0 = r13.tileQueryHelperProvider
                java.lang.Object r0 = r0.get()
                r3 = r0
                com.android.systemui.qs.customize.TileQueryHelper r3 = (com.android.systemui.qs.customize.TileQueryHelper) r3
                dagger.internal.DelegateFactory r0 = r11.qSHostAdapterProvider
                java.lang.Object r0 = r0.get()
                r4 = r0
                com.android.systemui.qs.QSHost r4 = (com.android.systemui.qs.QSHost) r4
                javax.inject.Provider r0 = r13.tileAdapterProvider
                java.lang.Object r0 = r0.get()
                r5 = r0
                com.android.systemui.qs.customize.TileAdapter r5 = (com.android.systemui.qs.customize.TileAdapter) r5
                javax.inject.Provider r0 = r12.screenLifecycleProvider
                java.lang.Object r0 = r0.get()
                r6 = r0
                com.android.systemui.keyguard.ScreenLifecycle r6 = (com.android.systemui.keyguard.ScreenLifecycle) r6
                dagger.internal.DelegateFactory r0 = r11.keyguardStateControllerImplProvider
                java.lang.Object r0 = r0.get()
                r7 = r0
                com.android.systemui.statusbar.policy.KeyguardStateController r7 = (com.android.systemui.statusbar.policy.KeyguardStateController) r7
                javax.inject.Provider r0 = r11.lightBarControllerProvider
                java.lang.Object r0 = r0.get()
                r8 = r0
                com.android.systemui.statusbar.phone.LightBarController r8 = (com.android.systemui.statusbar.phone.LightBarController) r8
                javax.inject.Provider r0 = r11.configurationControllerImplProvider
                java.lang.Object r0 = r0.get()
                r9 = r0
                com.android.systemui.statusbar.policy.ConfigurationController r9 = (com.android.systemui.statusbar.policy.ConfigurationController) r9
                javax.inject.Provider r0 = r12.provideUiEventLoggerProvider
                java.lang.Object r0 = r0.get()
                r10 = r0
                com.android.internal.logging.UiEventLogger r10 = (com.android.internal.logging.UiEventLogger) r10
                com.android.systemui.qs.customize.QSCustomizerController r0 = new com.android.systemui.qs.customize.QSCustomizerController
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
                goto L_0x0aab
            L_0x0a06:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) r13
                android.view.View r0 = r13.rootView
                android.view.View r0 = r0.requireViewById(r1)
                r15 = r0
                com.android.systemui.qs.QSPanel r15 = (com.android.systemui.qs.QSPanel) r15
                javax.inject.Provider r0 = r11.tunerServiceImplProvider
                java.lang.Object r0 = r0.get()
                r16 = r0
                com.android.systemui.tuner.TunerService r16 = (com.android.systemui.tuner.TunerService) r16
                dagger.internal.DelegateFactory r0 = r11.qSHostAdapterProvider
                java.lang.Object r0 = r0.get()
                r17 = r0
                com.android.systemui.qs.QSHost r17 = (com.android.systemui.qs.QSHost) r17
                javax.inject.Provider r0 = r13.qSCustomizerControllerProvider
                java.lang.Object r0 = r0.get()
                r18 = r0
                com.android.systemui.qs.customize.QSCustomizerController r18 = (com.android.systemui.qs.customize.QSCustomizerController) r18
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r0 = r13.sysUIGoogleGlobalRootComponentImpl
                android.content.Context r0 = r0.context
                boolean r19 = com.android.systemui.util.Utils.useQsMediaPlayer(r0)
                javax.inject.Provider r0 = r11.providesQSMediaHostProvider
                java.lang.Object r0 = r0.get()
                r20 = r0
                com.android.systemui.media.controls.ui.MediaHost r20 = (com.android.systemui.media.controls.ui.MediaHost) r20
                javax.inject.Provider r0 = r13.factoryProvider
                java.lang.Object r0 = r0.get()
                javax.inject.Provider r1 = r12.dumpManagerProvider
                java.lang.Object r1 = r1.get()
                r22 = r1
                com.android.systemui.dump.DumpManager r22 = (com.android.systemui.dump.DumpManager) r22
                javax.inject.Provider r1 = r12.provideMetricsLoggerProvider
                java.lang.Object r1 = r1.get()
                r23 = r1
                com.android.internal.logging.MetricsLogger r23 = (com.android.internal.logging.MetricsLogger) r23
                javax.inject.Provider r1 = r12.provideUiEventLoggerProvider
                java.lang.Object r1 = r1.get()
                r24 = r1
                com.android.internal.logging.UiEventLogger r24 = (com.android.internal.logging.UiEventLogger) r24
                com.android.systemui.qs.logging.QSLogger r25 = r11.qSLogger()
                javax.inject.Provider r1 = r11.factoryProvider25
                java.lang.Object r1 = r1.get()
                r26 = r1
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$21 r26 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$21) r26
                com.android.systemui.settings.brightness.BrightnessSliderController$Factory r27 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1403$$Nest$mbrightnessSliderControllerFactory(r11)
                javax.inject.Provider r1 = r11.falsingManagerProxyProvider
                java.lang.Object r1 = r1.get()
                r28 = r1
                com.android.systemui.plugins.FalsingManager r28 = (com.android.systemui.plugins.FalsingManager) r28
                dagger.internal.DelegateFactory r1 = r11.statusBarKeyguardViewManagerProvider
                java.lang.Object r1 = r1.get()
                r29 = r1
                com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r29 = (com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager) r29
                javax.inject.Provider r1 = r11.splitShadeStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                r30 = r1
                com.android.systemui.statusbar.policy.SplitShadeStateController r30 = (com.android.systemui.statusbar.policy.SplitShadeStateController) r30
                javax.inject.Provider r1 = r11.implProvider
                java.lang.Object r1 = r1.get()
                r31 = r1
                com.android.systemui.scene.shared.flag.SceneContainerFlags r31 = (com.android.systemui.scene.shared.flag.SceneContainerFlags) r31
                com.android.systemui.qs.QSPanelController r1 = new com.android.systemui.qs.QSPanelController
                r14 = r1
                r21 = r0
                com.android.systemui.qs.QSTileRevealController$Factory r21 = (com.android.systemui.qs.QSTileRevealController.Factory) r21
                r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31)
                goto L_0x093d
            L_0x0aab:
                return r0
            L_0x0aac:
                if (r14 == 0) goto L_0x0b3f
                if (r14 == r10) goto L_0x0b18
                if (r14 == r7) goto L_0x0b0b
                r0 = 3
                if (r14 == r0) goto L_0x0af3
                r0 = 4
                if (r14 == r0) goto L_0x0ae2
                r0 = 5
                if (r14 != r0) goto L_0x0adc
                com.android.systemui.navigationbar.NavigationBarTransitions r0 = new com.android.systemui.navigationbar.NavigationBarTransitions
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl) r13
                javax.inject.Provider r1 = r13.provideNavigationBarviewProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.navigationbar.NavigationBarView r1 = (com.android.systemui.navigationbar.NavigationBarView) r1
                javax.inject.Provider r2 = r11.factoryProvider6
                java.lang.Object r2 = r2.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$5 r2 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$5) r2
                javax.inject.Provider r3 = r11.provideDisplayTrackerProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.settings.DisplayTracker r3 = (com.android.systemui.settings.DisplayTracker) r3
                r0.<init>(r1, r2, r3)
                goto L_0x0d19
            L_0x0adc:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x0ae2:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl) r13
                android.content.Context r0 = r13.context
                java.lang.Class<android.view.WindowManager> r1 = android.view.WindowManager.class
                java.lang.Object r0 = r0.getSystemService(r1)
                android.view.WindowManager r0 = (android.view.WindowManager) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0d19
            L_0x0af3:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl) r13
                javax.inject.Provider r0 = r13.provideLayoutInflaterProvider
                java.lang.Object r0 = r0.get()
                android.view.LayoutInflater r0 = (android.view.LayoutInflater) r0
                r1 = 2131558826(0x7f0d01aa, float:1.8742979E38)
                android.view.View r0 = r0.inflate(r1, r9)
                com.android.systemui.navigationbar.NavigationBarFrame r0 = (com.android.systemui.navigationbar.NavigationBarFrame) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0d19
            L_0x0b0b:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl) r13
                android.content.Context r0 = r13.context
                android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r0)
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0d19
            L_0x0b18:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl) r13
                javax.inject.Provider r0 = r13.provideLayoutInflaterProvider
                java.lang.Object r0 = r0.get()
                android.view.LayoutInflater r0 = (android.view.LayoutInflater) r0
                javax.inject.Provider r1 = r13.provideNavigationBarFrameProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.navigationbar.NavigationBarFrame r1 = (com.android.systemui.navigationbar.NavigationBarFrame) r1
                r2 = 2131558825(0x7f0d01a9, float:1.8742977E38)
                android.view.View r0 = r0.inflate(r2, r1)
                r1 = 2131363156(0x7f0a0554, float:1.8346113E38)
                android.view.View r0 = r0.findViewById(r1)
                com.android.systemui.navigationbar.NavigationBarView r0 = (com.android.systemui.navigationbar.NavigationBarView) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0d19
            L_0x0b3f:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl) r13
                javax.inject.Provider r0 = r13.provideNavigationBarviewProvider
                java.lang.Object r0 = r0.get()
                r15 = r0
                com.android.systemui.navigationbar.NavigationBarView r15 = (com.android.systemui.navigationbar.NavigationBarView) r15
                javax.inject.Provider r0 = r13.provideNavigationBarFrameProvider
                java.lang.Object r0 = r0.get()
                r16 = r0
                com.android.systemui.navigationbar.NavigationBarFrame r16 = (com.android.systemui.navigationbar.NavigationBarFrame) r16
                javax.inject.Provider r0 = r13.provideWindowManagerProvider
                java.lang.Object r0 = r0.get()
                r19 = r0
                android.view.WindowManager r19 = (android.view.WindowManager) r19
                dagger.internal.DelegateFactory r0 = r11.assistManagerGoogleProvider
                dagger.Lazy r20 = dagger.internal.DoubleCheck.lazy(r0)
                javax.inject.Provider r0 = r12.provideAccessibilityManagerProvider
                java.lang.Object r0 = r0.get()
                r21 = r0
                android.view.accessibility.AccessibilityManager r21 = (android.view.accessibility.AccessibilityManager) r21
                javax.inject.Provider r0 = r11.provideDeviceProvisionedControllerProvider
                java.lang.Object r0 = r0.get()
                r22 = r0
                com.android.systemui.statusbar.policy.DeviceProvisionedController r22 = (com.android.systemui.statusbar.policy.DeviceProvisionedController) r22
                javax.inject.Provider r0 = r12.provideMetricsLoggerProvider
                java.lang.Object r0 = r0.get()
                r23 = r0
                com.android.internal.logging.MetricsLogger r23 = (com.android.internal.logging.MetricsLogger) r23
                dagger.internal.DelegateFactory r0 = r11.overviewProxyServiceProvider
                java.lang.Object r0 = r0.get()
                r24 = r0
                com.android.systemui.recents.OverviewProxyService r24 = (com.android.systemui.recents.OverviewProxyService) r24
                javax.inject.Provider r0 = r11.navigationModeControllerProvider
                java.lang.Object r0 = r0.get()
                r25 = r0
                com.android.systemui.navigationbar.NavigationModeController r25 = (com.android.systemui.navigationbar.NavigationModeController) r25
                dagger.internal.DelegateFactory r0 = r11.statusBarStateControllerImplProvider
                java.lang.Object r0 = r0.get()
                r26 = r0
                com.android.systemui.plugins.statusbar.StatusBarStateController r26 = (com.android.systemui.plugins.statusbar.StatusBarStateController) r26
                dagger.internal.DelegateFactory r0 = r11.statusBarKeyguardViewManagerProvider
                java.lang.Object r0 = r0.get()
                r27 = r0
                com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r27 = (com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager) r27
                javax.inject.Provider r0 = r11.provideSysUiStateProvider
                java.lang.Object r0 = r0.get()
                r28 = r0
                com.android.systemui.model.SysUiState r28 = (com.android.systemui.model.SysUiState) r28
                dagger.internal.DelegateFactory r0 = r11.provideUserTrackerProvider
                java.lang.Object r0 = r0.get()
                r29 = r0
                com.android.systemui.settings.UserTracker r29 = (com.android.systemui.settings.UserTracker) r29
                javax.inject.Provider r0 = r11.provideCommandQueueProvider
                java.lang.Object r0 = r0.get()
                r30 = r0
                com.android.systemui.statusbar.CommandQueue r30 = (com.android.systemui.statusbar.CommandQueue) r30
                javax.inject.Provider r0 = r11.provideRecentsProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.recents.Recents r0 = (com.android.systemui.recents.Recents) r0
                java.util.Optional r32 = java.util.Optional.of(r0)
                dagger.internal.DelegateFactory r0 = r11.optionalOfCentralSurfacesProvider
                dagger.Lazy r33 = dagger.internal.DoubleCheck.lazy(r0)
                dagger.internal.DelegateFactory r0 = r11.keyguardStateControllerImplProvider
                java.lang.Object r0 = r0.get()
                r34 = r0
                com.android.systemui.statusbar.policy.KeyguardStateController r34 = (com.android.systemui.statusbar.policy.KeyguardStateController) r34
                dagger.internal.DelegateFactory r0 = r11.notificationPanelViewControllerProvider
                java.lang.Object r0 = r0.get()
                r35 = r0
                com.android.systemui.shade.ShadeViewController r35 = (com.android.systemui.shade.ShadeViewController) r35
                javax.inject.Provider r0 = r11.notificationRemoteInputManagerProvider
                java.lang.Object r0 = r0.get()
                r36 = r0
                com.android.systemui.statusbar.NotificationRemoteInputManager r36 = (com.android.systemui.statusbar.NotificationRemoteInputManager) r36
                javax.inject.Provider r0 = r11.notificationShadeDepthControllerProvider
                java.lang.Object r0 = r0.get()
                r37 = r0
                com.android.systemui.statusbar.NotificationShadeDepthController r37 = (com.android.systemui.statusbar.NotificationShadeDepthController) r37
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r0 = r12.provideMainHandlerProvider
                java.lang.Object r0 = r0.get()
                r38 = r0
                android.os.Handler r38 = (android.os.Handler) r38
                javax.inject.Provider r0 = r12.provideMainExecutorProvider
                java.lang.Object r0 = r0.get()
                r39 = r0
                java.util.concurrent.Executor r39 = (java.util.concurrent.Executor) r39
                javax.inject.Provider r0 = r11.provideBackgroundExecutorProvider
                java.lang.Object r0 = r0.get()
                r40 = r0
                java.util.concurrent.Executor r40 = (java.util.concurrent.Executor) r40
                javax.inject.Provider r0 = r12.provideUiEventLoggerProvider
                java.lang.Object r0 = r0.get()
                r41 = r0
                com.android.internal.logging.UiEventLogger r41 = (com.android.internal.logging.UiEventLogger) r41
                javax.inject.Provider r0 = r11.navBarHelperProvider
                java.lang.Object r0 = r0.get()
                r42 = r0
                com.android.systemui.navigationbar.NavBarHelper r42 = (com.android.systemui.navigationbar.NavBarHelper) r42
                javax.inject.Provider r0 = r11.lightBarControllerProvider
                java.lang.Object r0 = r0.get()
                r43 = r0
                com.android.systemui.statusbar.phone.LightBarController r43 = (com.android.systemui.statusbar.phone.LightBarController) r43
                com.android.systemui.statusbar.phone.LightBarController$Factory r44 = new com.android.systemui.statusbar.phone.LightBarController$Factory
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r0 = r13.sysUIGoogleSysUIComponentImpl
                javax.inject.Provider r1 = r0.javaAdapterProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.util.kotlin.JavaAdapter r1 = (com.android.systemui.util.kotlin.JavaAdapter) r1
                javax.inject.Provider r2 = r0.darkIconDispatcherImplProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.plugins.DarkIconDispatcher r2 = (com.android.systemui.plugins.DarkIconDispatcher) r2
                javax.inject.Provider r3 = r0.provideBatteryControllerProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.policy.BatteryController r3 = (com.android.systemui.statusbar.policy.BatteryController) r3
                javax.inject.Provider r4 = r0.navigationModeControllerProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.navigationbar.NavigationModeController r4 = (com.android.systemui.navigationbar.NavigationModeController) r4
                javax.inject.Provider r5 = r0.statusBarModeRepositoryImplProvider
                java.lang.Object r5 = r5.get()
                com.android.systemui.statusbar.data.repository.StatusBarModeRepositoryStore r5 = (com.android.systemui.statusbar.data.repository.StatusBarModeRepositoryStore) r5
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r8 = r13.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r6 = r8.dumpManagerProvider
                java.lang.Object r6 = r6.get()
                com.android.systemui.dump.DumpManager r6 = (com.android.systemui.dump.DumpManager) r6
                javax.inject.Provider r0 = r0.provideDisplayTrackerProvider
                java.lang.Object r0 = r0.get()
                r7 = r0
                com.android.systemui.settings.DisplayTracker r7 = (com.android.systemui.settings.DisplayTracker) r7
                r0 = r44
                r0.<init>(r1, r2, r3, r4, r5, r6, r7)
                javax.inject.Provider r0 = r11.autoHideControllerProvider
                java.lang.Object r0 = r0.get()
                r45 = r0
                com.android.systemui.statusbar.phone.AutoHideController r45 = (com.android.systemui.statusbar.phone.AutoHideController) r45
                com.android.systemui.statusbar.phone.AutoHideController$Factory r0 = new com.android.systemui.statusbar.phone.AutoHideController$Factory
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r1 = r8.provideMainHandlerProvider
                java.lang.Object r1 = r1.get()
                android.os.Handler r1 = (android.os.Handler) r1
                javax.inject.Provider r2 = r8.provideIWindowManagerProvider
                java.lang.Object r2 = r2.get()
                android.view.IWindowManager r2 = (android.view.IWindowManager) r2
                r0.<init>(r1, r2)
                javax.inject.Provider r1 = r12.provideOptionalTelecomManagerProvider
                java.lang.Object r1 = r1.get()
                r47 = r1
                java.util.Optional r47 = (java.util.Optional) r47
                javax.inject.Provider r1 = r12.provideInputMethodManagerProvider
                java.lang.Object r1 = r1.get()
                r48 = r1
                android.view.inputmethod.InputMethodManager r48 = (android.view.inputmethod.InputMethodManager) r48
                com.android.systemui.navigationbar.buttons.DeadZone r1 = new com.android.systemui.navigationbar.buttons.DeadZone
                javax.inject.Provider r2 = r13.provideNavigationBarviewProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.navigationbar.NavigationBarView r2 = (com.android.systemui.navigationbar.NavigationBarView) r2
                r1.<init>(r2)
                javax.inject.Provider r2 = r11.deviceConfigProxyProvider
                java.lang.Object r2 = r2.get()
                r50 = r2
                com.android.systemui.util.DeviceConfigProxy r50 = (com.android.systemui.util.DeviceConfigProxy) r50
                javax.inject.Provider r2 = r13.navigationBarTransitionsProvider
                java.lang.Object r2 = r2.get()
                r51 = r2
                com.android.systemui.navigationbar.NavigationBarTransitions r51 = (com.android.systemui.navigationbar.NavigationBarTransitions) r51
                dagger.internal.DelegateFactory r2 = r11.provideUserTrackerProvider
                java.lang.Object r2 = r2.get()
                r53 = r2
                com.android.systemui.settings.UserContextProvider r53 = (com.android.systemui.settings.UserContextProvider) r53
                javax.inject.Provider r2 = r11.wakefulnessLifecycleProvider
                java.lang.Object r2 = r2.get()
                r54 = r2
                com.android.systemui.keyguard.WakefulnessLifecycle r54 = (com.android.systemui.keyguard.WakefulnessLifecycle) r54
                javax.inject.Provider r2 = r11.provideTaskStackChangeListenersProvider
                java.lang.Object r2 = r2.get()
                r55 = r2
                com.android.systemui.shared.system.TaskStackChangeListeners r55 = (com.android.systemui.shared.system.TaskStackChangeListeners) r55
                javax.inject.Provider r2 = r11.provideDisplayTrackerProvider
                java.lang.Object r2 = r2.get()
                r56 = r2
                com.android.systemui.settings.DisplayTracker r56 = (com.android.systemui.settings.DisplayTracker) r56
                com.android.systemui.navigationbar.NavigationBar r2 = new com.android.systemui.navigationbar.NavigationBar
                r14 = r2
                java.util.Optional r3 = r11.setPip
                r31 = r3
                java.util.Optional r3 = r11.setBackAnimation
                r52 = r3
                android.os.Bundle r3 = r13.savedState
                r17 = r3
                android.content.Context r3 = r13.context
                r18 = r3
                r46 = r0
                r49 = r1
                r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56)
                r0 = r2
            L_0x0d19:
                return r0
            L_0x0d1a:
                switch(r14) {
                    case 0: goto L_0x0e31;
                    case 1: goto L_0x0e11;
                    case 2: goto L_0x0df8;
                    case 3: goto L_0x0dee;
                    case 4: goto L_0x0dd7;
                    case 5: goto L_0x0db4;
                    case 6: goto L_0x0dae;
                    case 7: goto L_0x0da6;
                    case 8: goto L_0x0d7d;
                    case 9: goto L_0x0d75;
                    case 10: goto L_0x0d5e;
                    case 11: goto L_0x0d3c;
                    case 12: goto L_0x0d23;
                    default: goto L_0x0d1d;
                }
            L_0x0d1d:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x0d23:
                com.android.systemui.mediaprojection.appselector.MediaProjectionBlockerEmptyStateProvider r0 = new com.android.systemui.mediaprojection.appselector.MediaProjectionBlockerEmptyStateProvider
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl) r13
                android.os.UserHandle r1 = r13.hostUserHandle
                android.os.UserHandle r2 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1555$$Nest$mpersonalProfileUserHandle(r11)
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$SwitchingProvider r3 = r11.screenCaptureDevicePolicyResolverProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver r3 = (com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver) r3
                android.content.Context r4 = r12.context
                r0.<init>(r1, r2, r3, r4)
                goto L_0x0e81
            L_0x0d3c:
                com.android.systemui.mediaprojection.appselector.view.TaskPreviewSizeProvider r0 = new com.android.systemui.mediaprojection.appselector.view.TaskPreviewSizeProvider
                android.content.Context r1 = r12.context
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl) r13
                com.android.systemui.mediaprojection.appselector.view.WindowMetricsProviderImpl r2 = new com.android.systemui.mediaprojection.appselector.view.WindowMetricsProviderImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r3 = r13.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r3 = r3.provideWindowManagerProvider
                java.lang.Object r3 = r3.get()
                android.view.WindowManager r3 = (android.view.WindowManager) r3
                r2.<init>(r3)
                javax.inject.Provider r3 = r11.configurationControllerImplProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.policy.ConfigurationController r3 = (com.android.systemui.statusbar.policy.ConfigurationController) r3
                r0.<init>(r1, r2, r3)
                goto L_0x0e81
            L_0x0d5e:
                com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerLabelLoader r0 = new com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerLabelLoader
                javax.inject.Provider r1 = r11.bgDispatcherProvider
                java.lang.Object r1 = r1.get()
                kotlinx.coroutines.CoroutineDispatcher r1 = (kotlinx.coroutines.CoroutineDispatcher) r1
                javax.inject.Provider r2 = r12.providePackageManagerProvider
                java.lang.Object r2 = r2.get()
                android.content.pm.PackageManager r2 = (android.content.pm.PackageManager) r2
                r0.<init>(r1, r2)
                goto L_0x0e81
            L_0x0d75:
                android.content.Context r0 = r12.context
                com.android.launcher3.icons.IconFactory r0 = com.android.launcher3.icons.IconFactory.obtain(r0)
                goto L_0x0e81
            L_0x0d7d:
                com.android.systemui.mediaprojection.appselector.data.IconLoaderLibAppIconLoader r0 = new com.android.systemui.mediaprojection.appselector.data.IconLoaderLibAppIconLoader
                javax.inject.Provider r1 = r11.bgDispatcherProvider
                java.lang.Object r1 = r1.get()
                r2 = r1
                kotlinx.coroutines.CoroutineDispatcher r2 = (kotlinx.coroutines.CoroutineDispatcher) r2
                android.content.Context r3 = r12.context
                javax.inject.Provider r1 = r12.providePackageManagerWrapperProvider
                java.lang.Object r1 = r1.get()
                r4 = r1
                com.android.systemui.shared.system.PackageManagerWrapper r4 = (com.android.systemui.shared.system.PackageManagerWrapper) r4
                javax.inject.Provider r1 = r12.providePackageManagerProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                android.content.pm.PackageManager r5 = (android.content.pm.PackageManager) r5
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl) r13
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl$SwitchingProvider r6 = r13.bindIconFactoryProvider
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6)
                goto L_0x0e81
            L_0x0da6:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2 r1 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2
                r1.<init>(r0)
            L_0x0dab:
                r0 = r1
                goto L_0x0e81
            L_0x0dae:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1 r1 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1
                r1.<init>(r0)
                goto L_0x0dab
            L_0x0db4:
                com.android.systemui.mediaprojection.appselector.view.MediaProjectionRecentsViewController r0 = new com.android.systemui.mediaprojection.appselector.view.MediaProjectionRecentsViewController
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl) r13
                javax.inject.Provider r1 = r13.factoryProvider2
                java.lang.Object r1 = r1.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1 r1 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1) r1
                javax.inject.Provider r2 = r13.taskPreviewSizeProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.mediaprojection.appselector.view.TaskPreviewSizeProvider r2 = (com.android.systemui.mediaprojection.appselector.view.TaskPreviewSizeProvider) r2
                javax.inject.Provider r3 = r12.provideIActivityTaskManagerProvider
                java.lang.Object r3 = r3.get()
                android.app.IActivityTaskManager r3 = (android.app.IActivityTaskManager) r3
                com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorResultHandler r4 = r13.resultHandler
                r0.<init>(r1, r2, r3, r4)
                goto L_0x0e81
            L_0x0dd7:
                com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerThumbnailLoader r0 = new com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerThumbnailLoader
                javax.inject.Provider r1 = r11.bgDispatcherProvider
                java.lang.Object r1 = r1.get()
                kotlinx.coroutines.CoroutineDispatcher r1 = (kotlinx.coroutines.CoroutineDispatcher) r1
                javax.inject.Provider r2 = r11.provideActivityManagerWrapperProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.shared.system.ActivityManagerWrapper r2 = (com.android.systemui.shared.system.ActivityManagerWrapper) r2
                r0.<init>(r1, r2)
                goto L_0x0e81
            L_0x0dee:
                android.content.Context r0 = r12.context
                android.content.ComponentName r1 = new android.content.ComponentName
                java.lang.Class<com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorActivity> r2 = com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorActivity.class
                r1.<init>(r0, r2)
                goto L_0x0dab
            L_0x0df8:
                javax.inject.Provider r0 = r11.applicationScopeProvider
                java.lang.Object r0 = r0.get()
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                kotlin.coroutines.CoroutineContext r0 = r0.getCoroutineContext()
                kotlinx.coroutines.SupervisorJobImpl r1 = kotlinx.coroutines.SupervisorKt.SupervisorJob$default()
                kotlin.coroutines.CoroutineContext r0 = r0.plus(r1)
                kotlinx.coroutines.internal.ContextScope r0 = kotlinx.coroutines.CoroutineScopeKt.CoroutineScope(r0)
                goto L_0x0e81
            L_0x0e11:
                com.android.systemui.mediaprojection.appselector.data.ShellRecentTaskListProvider r0 = new com.android.systemui.mediaprojection.appselector.data.ShellRecentTaskListProvider
                javax.inject.Provider r1 = r11.bgDispatcherProvider
                java.lang.Object r1 = r1.get()
                kotlinx.coroutines.CoroutineDispatcher r1 = (kotlinx.coroutines.CoroutineDispatcher) r1
                javax.inject.Provider r2 = r11.provideBackgroundExecutorProvider
                java.lang.Object r2 = r2.get()
                java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
                dagger.internal.DelegateFactory r3 = r11.provideUserTrackerProvider
                java.lang.Object r3 = r3.get()
                com.android.systemui.settings.UserTracker r3 = (com.android.systemui.settings.UserTracker) r3
                java.util.Optional r4 = r11.setRecentTasks
                r0.<init>(r1, r2, r4, r3)
                goto L_0x0e81
            L_0x0e31:
                com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorController r0 = new com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorController
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl) r13
                javax.inject.Provider r1 = r13.bindRecentTaskListProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.systemui.mediaprojection.appselector.data.RecentTaskListProvider r6 = (com.android.systemui.mediaprojection.appselector.data.RecentTaskListProvider) r6
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$SwitchingProvider r1 = r11.screenCaptureDevicePolicyResolverProvider
                java.lang.Object r1 = r1.get()
                r8 = r1
                com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver r8 = (com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver) r8
                javax.inject.Provider r1 = r13.provideCoroutineScopeProvider
                java.lang.Object r1 = r1.get()
                r10 = r1
                kotlinx.coroutines.CoroutineScope r10 = (kotlinx.coroutines.CoroutineScope) r10
                javax.inject.Provider r1 = r13.provideAppSelectorComponentNameProvider
                java.lang.Object r1 = r1.get()
                android.content.ComponentName r1 = (android.content.ComponentName) r1
                javax.inject.Provider r2 = r13.bindRecentTaskThumbnailLoaderProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.mediaprojection.appselector.data.RecentTaskThumbnailLoader r2 = (com.android.systemui.mediaprojection.appselector.data.RecentTaskThumbnailLoader) r2
                java.lang.Boolean r3 = r13.isFirstStart
                boolean r14 = r3.booleanValue()
                javax.inject.Provider r3 = r11.mediaProjectionMetricsLoggerProvider
                java.lang.Object r3 = r3.get()
                r15 = r3
                com.android.systemui.mediaprojection.MediaProjectionMetricsLogger r15 = (com.android.systemui.mediaprojection.MediaProjectionMetricsLogger) r15
                java.lang.Integer r3 = r13.hostUid
                int r16 = r3.intValue()
                com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorView r7 = r13.view
                android.os.UserHandle r9 = r13.hostUserHandle
                java.lang.String r12 = r13.callingPackage
                r5 = r0
                r11 = r1
                r13 = r2
                r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            L_0x0e81:
                return r0
            L_0x0e82:
                if (r14 != 0) goto L_0x0edf
                com.android.systemui.statusbar.policy.KeyguardUserSwitcherController r0 = new com.android.systemui.statusbar.policy.KeyguardUserSwitcherController
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$casdcd_ComplicationComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$casdcd_ComplicationComponentImpl) r13
                java.lang.Object r1 = r13.visibilityController
                r2 = r1
                com.android.systemui.statusbar.policy.KeyguardUserSwitcherView r2 = (com.android.systemui.statusbar.policy.KeyguardUserSwitcherView) r2
                android.content.Context r3 = r12.context
                android.content.res.Resources r4 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m886$$Nest$mmainResources(r12)
                javax.inject.Provider r1 = r12.providerLayoutInflaterProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                android.view.LayoutInflater r5 = (android.view.LayoutInflater) r5
                javax.inject.Provider r1 = r12.screenLifecycleProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.systemui.keyguard.ScreenLifecycle r6 = (com.android.systemui.keyguard.ScreenLifecycle) r6
                javax.inject.Provider r1 = r11.userSwitcherControllerProvider
                java.lang.Object r1 = r1.get()
                r7 = r1
                com.android.systemui.statusbar.policy.UserSwitcherController r7 = (com.android.systemui.statusbar.policy.UserSwitcherController) r7
                dagger.internal.DelegateFactory r1 = r11.keyguardStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                r8 = r1
                com.android.systemui.statusbar.policy.KeyguardStateController r8 = (com.android.systemui.statusbar.policy.KeyguardStateController) r8
                dagger.internal.DelegateFactory r1 = r11.statusBarStateControllerImplProvider
                java.lang.Object r1 = r1.get()
                r9 = r1
                com.android.systemui.statusbar.SysuiStatusBarStateController r9 = (com.android.systemui.statusbar.SysuiStatusBarStateController) r9
                javax.inject.Provider r1 = r11.keyguardUpdateMonitorProvider
                java.lang.Object r1 = r1.get()
                r10 = r1
                com.android.keyguard.KeyguardUpdateMonitor r10 = (com.android.keyguard.KeyguardUpdateMonitor) r10
                dagger.internal.DelegateFactory r1 = r11.dozeParametersProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.phone.DozeParameters r1 = (com.android.systemui.statusbar.phone.DozeParameters) r1
                dagger.internal.DelegateFactory r1 = r11.screenOffAnimationControllerProvider
                java.lang.Object r1 = r1.get()
                r11 = r1
                com.android.systemui.statusbar.phone.ScreenOffAnimationController r11 = (com.android.systemui.statusbar.phone.ScreenOffAnimationController) r11
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
                return r0
            L_0x0edf:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x0ee5:
                if (r14 != 0) goto L_0x0f3a
                com.android.keyguard.KeyguardSliceViewController r0 = new com.android.keyguard.KeyguardSliceViewController
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl) r13
                java.lang.Object r1 = r13.presentation
                com.android.keyguard.KeyguardStatusView r1 = (com.android.keyguard.KeyguardStatusView) r1
                r2 = 2131362779(0x7f0a03db, float:1.8345348E38)
                android.view.View r1 = r1.findViewById(r2)
                com.android.keyguard.KeyguardClockSwitch r1 = (com.android.keyguard.KeyguardClockSwitch) r1
                dagger.internal.Preconditions.checkNotNullFromProvides(r1)
                r2 = 2131362804(0x7f0a03f4, float:1.8345399E38)
                android.view.View r1 = r1.findViewById(r2)
                r2 = r1
                com.android.keyguard.KeyguardSliceView r2 = (com.android.keyguard.KeyguardSliceView) r2
                dagger.internal.Preconditions.checkNotNullFromProvides(r2)
                dagger.internal.DelegateFactory r1 = r11.activityStarterImplProvider
                java.lang.Object r1 = r1.get()
                r3 = r1
                com.android.systemui.plugins.ActivityStarter r3 = (com.android.systemui.plugins.ActivityStarter) r3
                javax.inject.Provider r1 = r11.configurationControllerImplProvider
                java.lang.Object r1 = r1.get()
                r4 = r1
                com.android.systemui.statusbar.policy.ConfigurationController r4 = (com.android.systemui.statusbar.policy.ConfigurationController) r4
                javax.inject.Provider r1 = r11.tunerServiceImplProvider
                java.lang.Object r1 = r1.get()
                r5 = r1
                com.android.systemui.tuner.TunerService r5 = (com.android.systemui.tuner.TunerService) r5
                javax.inject.Provider r1 = r12.dumpManagerProvider
                java.lang.Object r1 = r1.get()
                r6 = r1
                com.android.systemui.dump.DumpManager r6 = (com.android.systemui.dump.DumpManager) r6
                javax.inject.Provider r1 = r11.provideDisplayTrackerProvider
                java.lang.Object r1 = r1.get()
                r7 = r1
                com.android.systemui.settings.DisplayTracker r7 = (com.android.systemui.settings.DisplayTracker) r7
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7)
                return r0
            L_0x0f3a:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x0f40:
                if (r14 == 0) goto L_0x0f5f
                if (r14 == r10) goto L_0x0f4f
                if (r14 != r7) goto L_0x0f49
                com.android.systemui.statusbar.phone.StatusBarLocation r0 = com.android.systemui.statusbar.phone.StatusBarLocation.KEYGUARD
                goto L_0x0f71
            L_0x0f49:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x0f4f:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$InputSessionComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$InputSessionComponentImpl) r13
                java.lang.Object r0 = r13.name
                com.android.systemui.statusbar.phone.KeyguardStatusBarView r0 = (com.android.systemui.statusbar.phone.KeyguardStatusBarView) r0
                android.view.View r0 = r0.findViewById(r8)
                com.android.systemui.battery.BatteryMeterView r0 = (com.android.systemui.battery.BatteryMeterView) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                goto L_0x0f71
            L_0x0f5f:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$InputSessionComponentImpl r13 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$InputSessionComponentImpl) r13
                java.lang.Object r0 = r13.name
                com.android.systemui.statusbar.phone.KeyguardStatusBarView r0 = (com.android.systemui.statusbar.phone.KeyguardStatusBarView) r0
                r1 = 2131362778(0x7f0a03da, float:1.8345346E38)
                android.view.View r0 = r0.findViewById(r1)
                com.android.keyguard.CarrierText r0 = (com.android.keyguard.CarrierText) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
            L_0x0f71:
                return r0
            L_0x0f72:
                com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController r0 = r57.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$KeyguardQsUserSwitchComponentImpl$SwitchingProvider()
                return r0
            L_0x0f77:
                java.lang.Object r0 = r57.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl$SwitchingProvider()
                return r0
            L_0x0f7c:
                com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController r0 = r57.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl$SwitchingProvider()
                return r0
            L_0x0f81:
                java.lang.Object r0 = r57.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl$SwitchingProvider()
                return r0
            L_0x0f86:
                com.android.systemui.statusbar.notification.collection.coordinator.Coordinator r0 = r57.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl$SwitchingProvider()
                return r0
            L_0x0f8b:
                java.lang.Object r0 = r57.get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl$SwitchingProvider()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider.get():java.lang.Object");
        }
    }

    public DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, DozeMachine.Service service) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.dozeMachineService = service;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.providesWrappedServiceProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 1, 0));
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
}
