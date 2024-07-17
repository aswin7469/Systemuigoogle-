package com.google.android.systemui.dagger;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.DreamManager;
import android.app.IActivityTaskManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.StatsManager;
import android.app.UiModeManager;
import android.app.admin.DevicePolicyManager;
import android.app.role.RoleManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.devicestate.DeviceStateManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.media.AudioManager;
import android.media.MediaRouter2Manager;
import android.net.ConnectivityManager;
import android.net.NetworkScoreManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.PowerExemptionManager;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.permission.PermissionManager;
import android.provider.DeviceConfig;
import android.safetycenter.SafetyCenterManager;
import android.service.vr.IVrManager;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.IWindowManager;
import android.view.LayoutInflater;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.accessibility.Flags;
import android.view.textclassifier.TextClassificationManager;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import com.android.app.motiontool.DdmHandleMotionTool;
import com.android.app.motiontool.MotionToolManager;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.internal.app.IBatteryStats;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.util.LatencyTracker;
import com.android.keyguard.CarrierTextManager;
import com.android.keyguard.ClockEventController;
import com.android.keyguard.KeyguardBiometricLockoutLogger;
import com.android.keyguard.KeyguardDisplayManager;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.logging.CarrierTextManagerLogger;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.keyguard.logging.KeyguardTransitionAnimationLogger;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.view.accessibility.data.repository.CaptioningRepositoryImpl;
import com.android.settingslib.view.accessibility.domain.interactor.CaptioningInteractor;
import com.android.settingslib.volume.data.repository.AudioRepositoryImpl;
import com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl;
import com.android.systemui.Dependency;
import com.android.systemui.ForegroundServicesDialog;
import com.android.systemui.GuestResetOrExitSessionReceiver;
import com.android.systemui.GuestSessionNotification;
import com.android.systemui.LatencyTester;
import com.android.systemui.Prefs;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.SliceBroadcastRelayHandler;
import com.android.systemui.SystemUIAppComponentFactoryBase;
import com.android.systemui.SystemUISecondaryUserService;
import com.android.systemui.SystemUIService;
import com.android.systemui.accessibility.AccessibilityLogger;
import com.android.systemui.accessibility.Magnification;
import com.android.systemui.accessibility.SystemActions;
import com.android.systemui.accessibility.data.repository.AccessibilityRepositoryImpl;
import com.android.systemui.accessibility.data.repository.ColorCorrectionRepositoryImpl;
import com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl;
import com.android.systemui.accessibility.domain.interactor.AccessibilityInteractor;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.appops.AppOpsController;
import com.android.systemui.authentication.AuthenticationModule$getSecurityMode$1;
import com.android.systemui.back.domain.interactor.BackActionInteractor;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.AuthDialogPanelInteractionDetector;
import com.android.systemui.biometrics.AuthRippleController;
import com.android.systemui.biometrics.BiometricNotificationService;
import com.android.systemui.biometrics.domain.interactor.FingerprintPropertyInteractor;
import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.BouncerInteractorModule$emergencyDialerIntentFactory$1;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageAuditLogger;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.bouncer.ui.BouncerViewImpl;
import com.android.systemui.bouncer.ui.viewmodel.KeyguardBouncerViewModel;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastDispatcherStartable;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.broadcast.PendingRemovalStore;
import com.android.systemui.broadcast.logging.BroadcastDispatcherLogger;
import com.android.systemui.camera.data.repository.CameraAutoRotateRepositoryImpl;
import com.android.systemui.camera.data.repository.CameraSensorPrivacyRepositoryImpl;
import com.android.systemui.classifier.DiagonalClassifier;
import com.android.systemui.classifier.DistanceClassifier;
import com.android.systemui.classifier.DoubleTapClassifier;
import com.android.systemui.classifier.FalsingClassifier;
import com.android.systemui.classifier.FalsingCoreStartable;
import com.android.systemui.classifier.FalsingDataProvider;
import com.android.systemui.classifier.LongTapClassifier;
import com.android.systemui.classifier.ProximityClassifier;
import com.android.systemui.classifier.SingleTapClassifier;
import com.android.systemui.classifier.TapClassifier;
import com.android.systemui.classifier.ZigZagClassifier;
import com.android.systemui.clipboardoverlay.ClipboardImageLoader;
import com.android.systemui.clipboardoverlay.ClipboardListener;
import com.android.systemui.clipboardoverlay.ClipboardOverlayUtils;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView;
import com.android.systemui.clipboardoverlay.ClipboardOverlayWindow;
import com.android.systemui.clipboardoverlay.ClipboardToast;
import com.android.systemui.clipboardoverlay.ClipboardTransitionExecutor;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.communal.CommunalSceneStartable;
import com.android.systemui.communal.data.db.DefaultWidgetPopulation;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalTutorialInteractor;
import com.android.systemui.communal.log.CommunalLoggerStartable;
import com.android.systemui.communal.ui.view.layout.sections.CommunalTutorialIndicatorSection;
import com.android.systemui.communal.ui.viewmodel.CommunalTutorialIndicatorViewModel;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.communal.widgets.CommunalAppWidgetHostStartable;
import com.android.systemui.communal.widgets.CommunalWidgetHost;
import com.android.systemui.communal.widgets.EditWidgetsActivity;
import com.android.systemui.communal.widgets.EditWidgetsActivityStarterImpl;
import com.android.systemui.communal.widgets.WidgetInteractionHandler;
import com.android.systemui.contrast.ContrastDialogActivity;
import com.android.systemui.controls.management.ControlsEditingActivity;
import com.android.systemui.controls.management.ControlsFavoritingActivity;
import com.android.systemui.controls.management.ControlsProviderSelectorActivity;
import com.android.systemui.controls.management.ControlsRequestDialog;
import com.android.systemui.controls.management.PanelConfirmationDialogFactory;
import com.android.systemui.controls.panels.AuthorizedPanelsRepositoryImpl;
import com.android.systemui.controls.start.ControlsStartable;
import com.android.systemui.controls.ui.ControlsActivity;
import com.android.systemui.controls.ui.ControlsDialogsFactory;
import com.android.systemui.dagger.ContextComponentResolver;
import com.android.systemui.dagger.NightDisplayListenerModule$Builder;
import com.android.systemui.dagger.SharedLibraryModule;
import com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryUdfpsInteractor;
import com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor;
import com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder;
import com.android.systemui.deviceentry.ui.viewmodel.DeviceEntryUdfpsAccessibilityOverlayViewModel;
import com.android.systemui.display.data.repository.DeviceStateRepositoryImpl;
import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl;
import com.android.systemui.display.ui.viewmodel.ConnectingDisplayViewModel;
import com.android.systemui.doze.DozeService;
import com.android.systemui.dreams.AssistantAttentionMonitor;
import com.android.systemui.dreams.DreamMonitor;
import com.android.systemui.dreams.DreamOverlayService;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.dreams.callbacks.AssistantAttentionCallback;
import com.android.systemui.dreams.callbacks.DreamStatusBarStateCallback;
import com.android.systemui.dreams.conditions.AssistantAttentionCondition;
import com.android.systemui.dreams.conditions.DreamCondition;
import com.android.systemui.dreams.homecontrols.HomeControlsDreamService;
import com.android.systemui.dreams.homecontrols.HomeControlsDreamStartable;
import com.android.systemui.dump.DumpHandler;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.dump.LogBufferEulogizer;
import com.android.systemui.dump.LogBufferFreezer;
import com.android.systemui.dump.SystemUIAuxiliaryDumpService;
import com.android.systemui.dump.SystemUIConfigDumpable;
import com.android.systemui.flags.ConditionalRestarter;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.FeatureFlagsReleaseStartable;
import com.android.systemui.flags.FlagDependencies;
import com.android.systemui.flags.NotOccludedCondition;
import com.android.systemui.flags.PluggedInCondition;
import com.android.systemui.flags.ScreenIdleCondition;
import com.android.systemui.globalactions.GlobalActionsComponent;
import com.android.systemui.keyboard.BluetoothDialogDelegate;
import com.android.systemui.keyboard.KeyboardUI;
import com.android.systemui.keyboard.PhysicalKeyboardCoreStartable;
import com.android.systemui.keyboard.data.repository.KeyboardRepositoryImpl;
import com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepository;
import com.android.systemui.keyboard.stickykeys.ui.viewmodel.StickyKeysIndicatorViewModel;
import com.android.systemui.keyguard.CustomizationProvider;
import com.android.systemui.keyguard.KeyguardService;
import com.android.systemui.keyguard.KeyguardSliceProvider;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.KeyguardViewConfigurator;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.ResourceTrimmer;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.WorkLockActivity;
import com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.FlashlightQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.HomeControlsKeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceProviderClientFactoryImpl;
import com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable;
import com.android.systemui.keyguard.data.quickaffordance.QrCodeScannerKeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.QuickAccessWalletKeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.VideoCameraQuickAffordanceConfig;
import com.android.systemui.keyguard.data.repository.KeyguardBlueprintRepository;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.BurnInInteractor;
import com.android.systemui.keyguard.domain.interactor.GlanceableHubTransitions;
import com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardBottomAreaInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardLongPressInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardSmartspaceInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionCoreStartable;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.TransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardBlueprint;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow;
import com.android.systemui.keyguard.ui.binder.KeyguardDismissActionBinder;
import com.android.systemui.keyguard.ui.binder.KeyguardDismissBinder;
import com.android.systemui.keyguard.ui.binder.SideFpsProgressBarViewBinder;
import com.android.systemui.keyguard.ui.composable.blueprint.CommunalBlueprint;
import com.android.systemui.keyguard.ui.composable.blueprint.DefaultBlueprint;
import com.android.systemui.keyguard.ui.composable.blueprint.ShortcutsBesideUdfpsBlueprint;
import com.android.systemui.keyguard.ui.composable.blueprint.SplitShadeBlueprint;
import com.android.systemui.keyguard.ui.composable.blueprint.SplitShadeWeatherClockBlueprint;
import com.android.systemui.keyguard.ui.composable.blueprint.WeatherClockBlueprint;
import com.android.systemui.keyguard.ui.composable.section.BottomAreaSection;
import com.android.systemui.keyguard.ui.composable.section.DefaultClockSection;
import com.android.systemui.keyguard.ui.composable.section.LockSection;
import com.android.systemui.keyguard.ui.composable.section.MediaCarouselSection;
import com.android.systemui.keyguard.ui.composable.section.SettingsMenuSection;
import com.android.systemui.keyguard.ui.composable.section.SmartSpaceSection;
import com.android.systemui.keyguard.ui.composable.section.StatusBarSection;
import com.android.systemui.keyguard.ui.preview.KeyguardRemotePreviewManager;
import com.android.systemui.keyguard.ui.transitions.DeviceEntryIconTransition;
import com.android.systemui.keyguard.ui.view.KeyguardRootView;
import com.android.systemui.keyguard.ui.view.layout.KeyguardBlueprintCommandListener;
import com.android.systemui.keyguard.ui.view.layout.blueprints.KeyguardBlueprintModule$Companion$bindWeatherClockBlueprintPlaceHolder$1;
import com.android.systemui.keyguard.ui.view.layout.sections.AlignShortcutsToUdfpsSection;
import com.android.systemui.keyguard.ui.view.layout.sections.AodBurnInSection;
import com.android.systemui.keyguard.ui.view.layout.sections.AodNotificationIconsSection;
import com.android.systemui.keyguard.ui.view.layout.sections.ClockSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultDeviceEntrySection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultIndicationAreaSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultNotificationStackScrollLayoutSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultSettingsPopupMenuSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultShortcutsSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultStatusBarSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultStatusViewSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultUdfpsAccessibilityOverlaySection;
import com.android.systemui.keyguard.ui.view.layout.sections.NotificationStackScrollLayoutSection;
import com.android.systemui.keyguard.ui.view.layout.sections.SmartspaceSection;
import com.android.systemui.keyguard.ui.view.layout.sections.SplitShadeMediaSection;
import com.android.systemui.keyguard.ui.view.layout.sections.SplitShadeNotificationStackScrollLayoutSection;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerMessageAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerUdfpsIconViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AodAlphaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AodBurnInViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.BouncerToGoneFlows;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryBackgroundViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryForegroundViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DozingToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DreamingHostedToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GoneToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBottomAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardIndicationAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardLongPressViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSettingsMenuViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSmartspaceViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenContentViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToAodTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToDozingTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToDreamingHostedTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToDreamingTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToGoneTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToOccludedTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToPrimaryBouncerTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.MediaCarouselViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OccludedToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OffToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.ShadeDependentFlows;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.log.core.Logger;
import com.android.systemui.log.table.TableLogBufferFactory;
import com.android.systemui.media.RingtonePlayer;
import com.android.systemui.media.controls.domain.pipeline.MediaDataFilter;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.domain.pipeline.MediaDeviceManager;
import com.android.systemui.media.controls.domain.pipeline.MediaSessionBasedFilter;
import com.android.systemui.media.controls.domain.resume.MediaBrowserFactory;
import com.android.systemui.media.controls.domain.resume.ResumeMediaBrowserFactory;
import com.android.systemui.media.controls.domain.resume.ResumeMediaBrowserLogger;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaController;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaControllerLogger;
import com.android.systemui.media.controls.ui.controller.MediaCarouselController;
import com.android.systemui.media.controls.ui.controller.MediaHostStatesManager;
import com.android.systemui.media.controls.ui.controller.MediaViewController;
import com.android.systemui.media.controls.ui.controller.MediaViewLogger;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.media.controls.util.LocalMediaManagerFactory;
import com.android.systemui.media.controls.util.MediaControllerFactory;
import com.android.systemui.media.controls.util.MediaFlags;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import com.android.systemui.media.dialog.MediaOutputBroadcastDialogFactory;
import com.android.systemui.media.dialog.MediaOutputDialogFactory;
import com.android.systemui.media.dialog.MediaOutputDialogReceiver;
import com.android.systemui.media.dialog.MediaOutputSwitcherDialogUI;
import com.android.systemui.media.muteawait.MediaMuteAwaitConnectionCli;
import com.android.systemui.media.muteawait.MediaMuteAwaitConnectionManagerFactory;
import com.android.systemui.media.nearby.NearbyMediaDevicesManager;
import com.android.systemui.media.taptotransfer.MediaTttCommandLineHelper;
import com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver;
import com.android.systemui.media.taptotransfer.receiver.MediaTttReceiverRippleController;
import com.android.systemui.media.taptotransfer.sender.MediaTttSenderCoordinator;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorActivity;
import com.android.systemui.mediaprojection.devicepolicy.MediaProjectionDevicePolicyModule;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity;
import com.android.systemui.mediaprojection.taskswitcher.MediaProjectionTaskSwitcherCoreStartable;
import com.android.systemui.mediaprojection.taskswitcher.domain.interactor.TaskSwitchInteractor;
import com.android.systemui.mediaprojection.taskswitcher.ui.viewmodel.TaskSwitcherNotificationViewModel;
import com.android.systemui.model.SysUiState;
import com.android.systemui.motiontool.MotionToolStartable;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.gestural.BackPanelController;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.notetask.LaunchNotesRoleSettingsTrampolineActivity;
import com.android.systemui.notetask.NoteTaskBubblesController;
import com.android.systemui.notetask.NoteTaskController;
import com.android.systemui.notetask.NoteTaskControllerUpdateService;
import com.android.systemui.notetask.NoteTaskEventLogger;
import com.android.systemui.notetask.NoteTaskInfoResolver;
import com.android.systemui.notetask.NoteTaskInitializer;
import com.android.systemui.notetask.quickaffordance.NoteTaskQuickAffordanceConfig;
import com.android.systemui.notetask.shortcut.CreateNoteTaskShortcutActivity;
import com.android.systemui.notetask.shortcut.LaunchNoteTaskActivity;
import com.android.systemui.people.PeopleProvider;
import com.android.systemui.people.PeopleSpaceActivity;
import com.android.systemui.people.widget.LaunchConversationActivity;
import com.android.systemui.people.widget.PeopleSpaceWidgetManager;
import com.android.systemui.people.widget.PeopleSpaceWidgetPinnedReceiver;
import com.android.systemui.people.widget.PeopleSpaceWidgetProvider;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.clocks.ClockMessageBuffers;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.PowerUI;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.privacy.OngoingPrivacyChip;
import com.android.systemui.privacy.PrivacyDialogController;
import com.android.systemui.privacy.PrivacyDialogControllerV2;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.privacy.PrivacyItemMonitor;
import com.android.systemui.privacy.logging.PrivacyLogger;
import com.android.systemui.process.condition.SystemProcessCondition;
import com.android.systemui.qs.HeaderPrivacyIconsController;
import com.android.systemui.qs.QSDisableFlagsLogger;
import com.android.systemui.qs.QSFragmentStartable;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSTileHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.android.systemui.qs.pipeline.domain.startable.QSPipelineCoreStartable;
import com.android.systemui.qs.pipeline.shared.QSPipelineFlagsRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger;
import com.android.systemui.qs.tiles.InternetTile;
import com.android.systemui.qs.tiles.InternetTileNewImpl;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.analytics.QSTileAnalytics;
import com.android.systemui.qs.tiles.base.interactor.DisabledByPolicyInteractorImpl;
import com.android.systemui.qs.tiles.base.interactor.RestrictedLockProxy;
import com.android.systemui.qs.tiles.base.logging.QSTileLogger;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileCoroutineScopeFactory;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.dialog.InternetDialogManager;
import com.android.systemui.qs.tiles.impl.airplane.domain.AirplaneModeMapper;
import com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileDataInteractor;
import com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.alarm.domain.AlarmTileMapper;
import com.android.systemui.qs.tiles.impl.alarm.domain.interactor.AlarmTileDataInteractor;
import com.android.systemui.qs.tiles.impl.alarm.domain.interactor.AlarmTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.colorcorrection.domain.ColorCorrectionTileMapper;
import com.android.systemui.qs.tiles.impl.colorcorrection.domain.interactor.ColorCorrectionTileDataInteractor;
import com.android.systemui.qs.tiles.impl.colorcorrection.domain.interactor.ColorCorrectionUserActionInteractor;
import com.android.systemui.qs.tiles.impl.flashlight.domain.FlashlightMapper;
import com.android.systemui.qs.tiles.impl.flashlight.domain.interactor.FlashlightTileDataInteractor;
import com.android.systemui.qs.tiles.impl.flashlight.domain.interactor.FlashlightTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.fontscaling.domain.FontScalingTileMapper;
import com.android.systemui.qs.tiles.impl.fontscaling.domain.interactor.FontScalingTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.inversion.domain.ColorInversionTileMapper;
import com.android.systemui.qs.tiles.impl.inversion.domain.interactor.ColorInversionTileDataInteractor;
import com.android.systemui.qs.tiles.impl.inversion.domain.interactor.ColorInversionUserActionInteractor;
import com.android.systemui.qs.tiles.impl.location.domain.LocationTileMapper;
import com.android.systemui.qs.tiles.impl.location.domain.interactor.LocationTileDataInteractor;
import com.android.systemui.qs.tiles.impl.location.domain.interactor.LocationTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.rotation.domain.interactor.RotationLockTileDataInteractor;
import com.android.systemui.qs.tiles.impl.rotation.domain.interactor.RotationLockTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.rotation.ui.mapper.RotationLockTileMapper;
import com.android.systemui.qs.tiles.impl.saver.domain.DataSaverTileMapper;
import com.android.systemui.qs.tiles.impl.saver.domain.interactor.DataSaverTileDataInteractor;
import com.android.systemui.qs.tiles.impl.saver.domain.interactor.DataSaverTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.uimodenight.domain.UiModeNightTileMapper;
import com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileDataInteractor;
import com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileUserActionInteractor;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfigProviderImpl;
import com.android.systemui.qs.tiles.viewmodel.QSTilePolicy;
import com.android.systemui.qs.tiles.viewmodel.QSTileUIConfig;
import com.android.systemui.reardisplay.RearDisplayDialogController;
import com.android.systemui.recents.OverviewProxyRecentsImpl;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.recents.Recents;
import com.android.systemui.recents.ScreenPinningRequest;
import com.android.systemui.recordissue.IssueRecordingService;
import com.android.systemui.scene.data.repository.SceneContainerRepository;
import com.android.systemui.scene.domain.interactor.WindowRootViewVisibilityInteractor;
import com.android.systemui.scene.domain.startable.SceneContainerStartable;
import com.android.systemui.scene.shared.flag.SceneContainerFlags;
import com.android.systemui.scene.shared.model.SceneContainerConfig;
import com.android.systemui.scene.shared.model.SceneDataSource;
import com.android.systemui.screenrecord.RecordingController;
import com.android.systemui.screenrecord.RecordingService;
import com.android.systemui.screenshot.ActionProxyReceiver;
import com.android.systemui.screenshot.DeleteScreenshotReceiver;
import com.android.systemui.screenshot.ImageExporter;
import com.android.systemui.screenshot.LongScreenshotActivity;
import com.android.systemui.screenshot.ScreenshotProxyService;
import com.android.systemui.screenshot.SmartActionsReceiver;
import com.android.systemui.screenshot.TakeScreenshotService;
import com.android.systemui.screenshot.appclips.AppClipsActivity;
import com.android.systemui.screenshot.appclips.AppClipsScreenshotHelperService;
import com.android.systemui.screenshot.appclips.AppClipsService;
import com.android.systemui.screenshot.appclips.AppClipsTrampolineActivity;
import com.android.systemui.sensorprivacy.SensorUseStartedActivity;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.settings.brightness.BrightnessDialog;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.systemui.shade.GlanceableHubContainerController;
import com.android.systemui.shade.LargeScreenHeaderHelper;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.shade.QsBatteryModeController;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeLogger;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shade.ShadeWindowLogger;
import com.android.systemui.shade.carrier.ShadeCarrierGroupController;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.DevicePolicyManagerWrapper;
import com.android.systemui.shared.system.PackageManagerWrapper;
import com.android.systemui.shortcut.ShortcutKeyDispatcher;
import com.android.systemui.smartspace.filters.LockscreenTargetFilter;
import com.android.systemui.smartspace.preconditions.LockscreenPrecondition;
import com.android.systemui.statusbar.BlurUtils;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.ImmersiveModeConfirmation;
import com.android.systemui.statusbar.KeyboardShortcutsReceiver;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.LockscreenShadeScrimTransitionController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.OperatorNameViewController;
import com.android.systemui.statusbar.SmartReplyController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.android.systemui.statusbar.connectivity.AccessPointController;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.WifiStatusTrackerFactory;
import com.android.systemui.statusbar.connectivity.ui.MobileContextProvider;
import com.android.systemui.statusbar.data.repository.NotificationListenerSettingsRepository;
import com.android.systemui.statusbar.data.repository.StatusBarModeRepositoryImpl;
import com.android.systemui.statusbar.disableflags.DisableFlagsLogger;
import com.android.systemui.statusbar.domain.interactor.RemoteInputInteractor;
import com.android.systemui.statusbar.domain.interactor.SilentNotificationStatusIconsVisibilityInteractor;
import com.android.systemui.statusbar.events.SystemEventChipAnimationController;
import com.android.systemui.statusbar.gesture.GesturePointerEventListener;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.systemui.statusbar.notification.ConversationNotificationManager;
import com.android.systemui.statusbar.notification.ConversationNotificationProcessor;
import com.android.systemui.statusbar.notification.InstantAppNotifier;
import com.android.systemui.statusbar.notification.NotifPipelineFlags;
import com.android.systemui.statusbar.notification.NotificationClicker;
import com.android.systemui.statusbar.notification.NotificationClickerLogger;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifInflaterLogger;
import com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescer;
import com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescerLogger;
import com.android.systemui.statusbar.notification.collection.coordinator.ShadeEventCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderLogger;
import com.android.systemui.statusbar.notification.collection.listbuilder.ShadeListBuilderLogger;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger;
import com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl;
import com.android.systemui.statusbar.notification.data.repository.HeadsUpNotificationIconViewStateRepository;
import com.android.systemui.statusbar.notification.data.repository.NotificationsKeyguardViewStateRepository;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationIconInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.SeenNotificationsInteractor;
import com.android.systemui.statusbar.notification.icon.domain.interactor.AlwaysOnDisplayNotificationIconsInteractor;
import com.android.systemui.statusbar.notification.icon.domain.interactor.NotificationIconsInteractor;
import com.android.systemui.statusbar.notification.icon.domain.interactor.StatusBarNotificationIconsInteractor;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.AlwaysOnDisplayNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerShelfViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerStatusBarViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.ShelfNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBindingFailureTracker;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerShelfViewModel;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel;
import com.android.systemui.statusbar.notification.interruption.HeadsUpViewBinderLogger;
import com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.notification.interruption.NotificationInterruptLogger;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;
import com.android.systemui.statusbar.notification.logging.NotificationMemoryMonitor;
import com.android.systemui.statusbar.notification.row.NotifBindPipeline;
import com.android.systemui.statusbar.notification.row.NotifBindPipelineInitializer;
import com.android.systemui.statusbar.notification.row.NotifBindPipelineLogger;
import com.android.systemui.statusbar.notification.row.NotificationContentInflaterLogger;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.row.RowContentBindStage;
import com.android.systemui.statusbar.notification.row.RowContentBindStageLogger;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.ActivatableNotificationViewModelImpl;
import com.android.systemui.statusbar.notification.shelf.ui.viewmodel.NotificationShelfViewModel;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.DisplaySwitchNotificationsHiderTracker;
import com.android.systemui.statusbar.notification.stack.NotificationRoundnessManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLogger;
import com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.statusbar.notification.stack.StackStateLogger;
import com.android.systemui.statusbar.notification.stack.domain.interactor.NotificationStackInteractor;
import com.android.systemui.statusbar.notification.stack.ui.view.SharedNotificationContainer;
import com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.HideListViewModel;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationStackAppearanceViewModel;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.ConfigurationControllerStartable;
import com.android.systemui.statusbar.phone.DarkIconDispatcherImpl;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.KeyguardBottomAreaView;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.KeyguardClockPositionAlgorithm;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.android.systemui.statusbar.phone.LSShadeTransitionLogger;
import com.android.systemui.statusbar.phone.LetterboxBackgroundProvider;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.LockscreenGestureLogger;
import com.android.systemui.statusbar.phone.NotificationIconAreaController;
import com.android.systemui.statusbar.phone.NotificationListenerWithPlugins;
import com.android.systemui.statusbar.phone.PhoneStatusBarPolicy;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.phone.StatusBarHeadsUpChangeListener;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarterLogger;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.phone.data.repository.DarkIconRepositoryImpl;
import com.android.systemui.statusbar.phone.domain.interactor.DarkIconInteractor;
import com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragmentLogger;
import com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragmentStartable;
import com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController;
import com.android.systemui.statusbar.pipeline.StatusBarPipelineFlags;
import com.android.systemui.statusbar.pipeline.airplane.data.repository.AirplaneModeRepository;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigCoreStartable;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoModeMobileConnectionDataSource;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CarrierMergedConnectionRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.FullMobileConnectionRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileUiAdapter;
import com.android.systemui.statusbar.pipeline.mobile.util.SubscriptionManagerProxyImpl;
import com.android.systemui.statusbar.pipeline.satellite.domain.interactor.DeviceBasedSatelliteInteractor;
import com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel.DeviceBasedSatelliteViewModel;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.InternetTileViewModel;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepositoryDagger;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.DemoModeWifiDataSource;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.DemoWifiRepository;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryStateNotifier;
import com.android.systemui.statusbar.policy.BluetoothControllerImpl;
import com.android.systemui.statusbar.policy.CastController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DataSaverControllerImpl;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceStateRotationLockSettingControllerLogger;
import com.android.systemui.statusbar.policy.FlashlightController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManagerLogger;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.statusbar.policy.SafetyController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionControllerLogger;
import com.android.systemui.statusbar.policy.SensorPrivacyControllerImpl;
import com.android.systemui.statusbar.policy.SmartActionInflaterImpl;
import com.android.systemui.statusbar.policy.SmartReplyConstants;
import com.android.systemui.statusbar.policy.SmartReplyInflaterImpl;
import com.android.systemui.statusbar.policy.SmartReplyStateInflaterImpl;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.policy.VariableDateViewController;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.data.repository.DeviceProvisioningRepositoryImpl;
import com.android.systemui.statusbar.policy.data.repository.UserSetupRepositoryImpl;
import com.android.systemui.statusbar.policy.data.repository.ZenModeRepositoryImpl;
import com.android.systemui.statusbar.policy.domain.interactor.UserSetupInteractor;
import com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor;
import com.android.systemui.statusbar.ui.SystemBarUtilsProxyImpl;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import com.android.systemui.statusbar.window.StatusBarWindowController;
import com.android.systemui.stylus.StylusManager;
import com.android.systemui.stylus.StylusUsiPowerStartable;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.android.systemui.telephony.ui.activity.SwitchToManagedProfileForCallActivity;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator;
import com.android.systemui.theme.ThemeOverlayController;
import com.android.systemui.toast.ToastLogger;
import com.android.systemui.toast.ToastUI;
import com.android.systemui.touch.TouchInsetManager;
import com.android.systemui.tuner.TunerActivity;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.unfold.DisplaySwitchLatencyTracker;
import com.android.systemui.unfold.SysUIUnfoldModule;
import com.android.systemui.unfold.UnfoldInitializationStartable;
import com.android.systemui.unfold.UnfoldTraceLogger;
import com.android.systemui.usb.StorageNotification;
import com.android.systemui.usb.UsbAccessoryUriActivity;
import com.android.systemui.usb.UsbConfirmActivity;
import com.android.systemui.usb.UsbDebuggingActivity;
import com.android.systemui.usb.UsbDebuggingSecondaryUserActivity;
import com.android.systemui.usb.UsbPermissionActivity;
import com.android.systemui.user.CreateUserActivity;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.user.ui.dialog.UserSwitcherDialogCoordinator;
import com.android.systemui.util.AsyncActivityLauncher;
import com.android.systemui.util.CarrierConfigTracker;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.RingerModeTrackerImpl;
import com.android.systemui.util.animation.data.repository.AnimationStatusRepositoryImpl;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutionImpl;
import com.android.systemui.util.concurrency.GlobalConcurrencyModule_ProvideMainLooperFactory;
import com.android.systemui.util.concurrency.MessageRouterImpl;
import com.android.systemui.util.concurrency.RepeatableExecutorImpl;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.kotlin.SysUICoroutinesModule;
import com.android.systemui.util.leak.LeakModule;
import com.android.systemui.util.sensors.AsyncSensorManager;
import com.android.systemui.util.sensors.SensorModule;
import com.android.systemui.util.sensors.ThresholdSensor;
import com.android.systemui.util.sensors.ThresholdSensorImpl;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SystemSettingsImpl;
import com.android.systemui.util.time.DateFormatUtil;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.systemui.util.wakelock.WakeLockLogger;
import com.android.systemui.volume.VolumeDialogImpl;
import com.android.systemui.volume.VolumePanelDialogReceiver;
import com.android.systemui.volume.VolumePanelFactory;
import com.android.systemui.volume.VolumeUI;
import com.android.systemui.volume.domain.interactor.VolumePanelNavigationInteractor;
import com.android.systemui.volume.panel.component.mediaoutput.data.repository.LocalMediaRepositoryFactoryImpl;
import com.android.systemui.volume.panel.ui.activity.VolumePanelActivity;
import com.android.systemui.volume.ui.navigation.VolumeNavigator;
import com.android.systemui.wallet.controller.WalletContextualLocationsService;
import com.android.systemui.wallet.ui.WalletActivity;
import com.android.systemui.wallpapers.ImageWallpaper;
import com.android.systemui.wmshell.WMShell;
import com.android.wm.shell.keyguard.KeyguardTransitions;
import com.android.wm.shell.sysui.ShellInterface;
import com.android.wm.shell.transition.ShellTransitions;
import com.google.android.systemui.GoogleServices;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.assist.OpaEnabledDispatcher;
import com.google.android.systemui.assist.uihints.AssistantPresenceHandler;
import com.google.android.systemui.assist.uihints.AssistantWarmer;
import com.google.android.systemui.assist.uihints.ConfigurationHandler;
import com.google.android.systemui.assist.uihints.GoBackHandler;
import com.google.android.systemui.assist.uihints.NavBarFadeController;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.SwipeHandler;
import com.google.android.systemui.autorotate.AutorotateDataService;
import com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService;
import com.google.android.systemui.columbus.legacy.actions.Action;
import com.google.android.systemui.columbus.legacy.feedback.FeedbackEffect;
import com.google.android.systemui.columbus.legacy.feedback.HapticClick;
import com.google.android.systemui.columbus.legacy.feedback.UserActivity;
import com.google.android.systemui.columbus.legacy.gates.CameraVisibility;
import com.google.android.systemui.columbus.legacy.gates.ChargingState;
import com.google.android.systemui.columbus.legacy.gates.FlagEnabled;
import com.google.android.systemui.columbus.legacy.gates.KeyguardProximity;
import com.google.android.systemui.columbus.legacy.gates.PowerSaveState;
import com.google.android.systemui.columbus.legacy.gates.PowerState;
import com.google.android.systemui.columbus.legacy.gates.ScreenTouch;
import com.google.android.systemui.columbus.legacy.gates.SetupWizard;
import com.google.android.systemui.columbus.legacy.gates.SystemKeyPress;
import com.google.android.systemui.columbus.legacy.gates.TelephonyActivity;
import com.google.android.systemui.columbus.legacy.gates.UsbState;
import com.google.android.systemui.columbus.legacy.gates.VrMode;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import com.google.android.systemui.dreams.LockscreenWallpaperDreamService;
import com.google.android.systemui.elmyra.actions.DeskClockAction;
import com.google.android.systemui.elmyra.actions.SilenceCall;
import com.google.android.systemui.elmyra.actions.UnpinNotifications;
import com.google.android.systemui.elmyra.feedback.NavUndimEffect;
import com.google.android.systemui.elmyra.feedback.OpaHomeButton;
import com.google.android.systemui.elmyra.feedback.OpaLockscreen;
import com.google.android.systemui.elmyra.feedback.SquishyNavigationButtons;
import com.google.android.systemui.elmyra.gates.Gate;
import com.google.android.systemui.elmyra.gates.KeyguardVisibility;
import com.google.android.systemui.elmyra.gates.LockTask;
import com.google.android.systemui.elmyra.gates.NavigationBarVisibility;
import com.google.android.systemui.elmyra.gates.NonGesturalNavigation;
import com.google.android.systemui.elmyra.sensors.config.ScreenStateAdjustment;
import com.google.android.systemui.globalactions.ShutdownUIGoogle;
import com.google.android.systemui.globalactions.ShutdownUiModuleGoogle;
import com.google.android.systemui.keyguard.ActiveUnlockChipbarManager;
import com.google.android.systemui.keyguard.AmbientIndicationCoreStartable;
import com.google.android.systemui.keyguard.RefreshRateRequesterBinder;
import com.google.android.systemui.keyguard.domain.interactor.AmbientIndicationInteractor;
import com.google.android.systemui.keyguard.ui.composable.section.GoogleAmbientIndicationSection;
import com.google.android.systemui.keyguard.ui.sections.DefaultAmbientIndicationAreaSection;
import com.google.android.systemui.keyguard.ui.viewmodel.KeyguardAmbientIndicationViewModel;
import com.google.android.systemui.lowlightclock.ChargingStatusProvider;
import com.google.android.systemui.lowlightclock.LowLightClockAnimationProvider;
import com.google.android.systemui.lowlightclock.LowLightClockDreamService;
import com.google.android.systemui.power.BatteryEventClient;
import com.google.android.systemui.power.batteryevent.domain.BatteryEventService;
import com.google.android.systemui.power.batteryevent.repository.FrameworkDataSource;
import com.google.android.systemui.power.batteryhealth.HealthService;
import com.google.android.systemui.power.batteryhealth.HealthUpdateReceiver;
import com.google.android.systemui.qs.launcher.LauncherTileService;
import com.google.android.systemui.smartspace.KeyguardSmartspaceStartable;
import com.google.android.systemui.statusbar.phone.WallpaperNotifier;
import com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor;
import com.google.android.systemui.vpn.AdaptivePPNService;
import com.google.hardware.pixel.display.IDisplay;
import dagger.Lazy;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.SetBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyIterator;
import kotlin.collections.EmptySet;
import kotlin.collections.SetsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl implements SysUIGoogleSysUIComponent {
    public final Provider accessibilityButtonModeObserverProvider;
    public final Provider accessibilityButtonTargetsObserverProvider;
    public Provider accessibilityFloatingMenuControllerProvider;
    public Provider accessibilityInteractorProvider;
    public final Provider accessibilityManagerWrapperProvider;
    public Provider accessibilityQsShortcutsRepositoryImplProvider;
    public Provider accessibilityTilesInteractorProvider;
    public Provider actionFetcherProvider;
    public Provider actionIntentExecutorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider actionProxyReceiverProvider;
    public final Provider activeNotificationListRepositoryProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider activeNotificationsInteractorProvider;
    public Provider activeUnlockChipbarManagerProvider;
    public final Provider activeUnlockConfigProvider;
    public Provider activityIntentHelperProvider;
    public final DelegateFactory activityStarterImplProvider;
    public Provider activityTaskManagerProxyProvider;
    public Provider activityTaskManagerTasksRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider adapterProvider;
    public Provider adaptivePPNServiceProvider;
    public Provider aiAiCHREGestureSensorProvider;
    public Provider airplaneModeInteractorProvider;
    public final Provider airplaneModeRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider airplaneModeTileProvider;
    public Provider airplaneModeViewModelImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider alarmTileProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider alternateBouncerDependenciesProvider;
    public final Provider alternateBouncerInteractorProvider;
    public Provider alternateBouncerToAodTransitionViewModelProvider;
    public Provider alternateBouncerToDozingTransitionViewModelProvider;
    public Provider alternateBouncerToGoneTransitionViewModelProvider;
    public Provider alternateBouncerToPrimaryBouncerTransitionViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider alternateBouncerUdfpsAccessibilityOverlayViewModelProvider;
    public final Provider alwaysOnDisplayPolicyProvider;
    public Provider ambientIndicationCoreStartableProvider;
    public final Provider ambientIndicationInteractorProvider;
    public final Provider ambientIndicationRepositoryProvider;
    public Provider ambientStateProvider;
    public final Provider animatedImageNotificationManagerProvider;
    public Provider aodAlphaViewModelProvider;
    public Provider aodBurnInViewModelProvider;
    public Provider aodToGoneTransitionViewModelProvider;
    public Provider aodToLockscreenTransitionViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider appClipsActivityProvider;
    public Provider appClipsCrossProcessHelperProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider appClipsScreenshotHelperServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider appClipsServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider appClipsTrampolineActivityProvider;
    public final Provider appOpsControllerImplProvider;
    public final Provider appOpsPrivacyItemMonitorProvider;
    public Provider assistContentRequesterProvider;
    public Provider assistInteractorProvider;
    public Provider assistInvocationEffectProvider;
    public Provider assistInvocationEffectProvider2;
    public DelegateFactory assistManagerGoogleProvider;
    public Provider assistRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider assistantAttentionMonitorProvider;
    public Provider assistantFeedbackControllerProvider;
    public Provider assistantPresenceHandlerProvider;
    public Provider assistantWarmerProvider;
    public Provider asyncSensorManagerProvider;
    public final DelegateFactory authControllerProvider;
    public Provider authRippleControllerProvider;
    public Provider authRippleInteractorProvider;
    public final Provider authenticationInteractorProvider;
    public final Provider authenticationRepositoryImplProvider;
    public Provider autoAddInteractorProvider;
    public Provider autoAddSettingRepositoryProvider;
    public final Provider autoHideControllerProvider;
    public Provider avalancheProvider;
    public Provider backActionInteractorProvider;
    public Provider backlightDialogViewModelProvider;
    public Provider backupManagerProxyProvider;
    public final Provider batteryControllerLoggerProvider;
    public Provider batteryEventModuleProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider batteryEventServiceProvider;
    public Provider batteryEventStateControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider batterySaverConfirmationDialogProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider batterySaverTileGoogleProvider;
    public final Provider bgApplicationScopeProvider;
    public final Provider bgCoroutineContextProvider;
    public final Provider bgDispatcherProvider;
    public Provider bindEventLogProvider;
    public final Provider bindEventManagerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider bindInternetTileProvider;
    public Provider bindRotationPolicyWrapperProvider;
    public final Provider bindSystemClockProvider;
    public Provider bindSystemStatusAnimationSchedulerProvider;
    public final Provider bindableIconsRegistryImplProvider;
    public final Provider bindsReaderProvider;
    public Provider biometricMessageInteractorProvider;
    public Provider biometricNotificationBroadcastReceiverProvider;
    public Provider biometricNotificationDialogFactoryProvider;
    public Provider biometricNotificationServiceProvider;
    public final Provider biometricSettingsRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider biometricStatusInteractorImplProvider;
    public Provider biometricStatusRepositoryImplProvider;
    public DelegateFactory biometricUnlockControllerProvider;
    public Provider biometricUnlockInteractorProvider;
    public Provider biometricUnlockLoggerProvider;
    public Provider bluetoothAutoOnInteractorProvider;
    public Provider bluetoothAutoOnRepositoryProvider;
    public Provider bluetoothControllerImplProvider;
    public Provider bluetoothLoggerProvider;
    public Provider bluetoothRepositoryImplProvider;
    public Provider bluetoothStateInteractorProvider;
    public Provider bluetoothTileDialogRepositoryProvider;
    public Provider bluetoothTileDialogViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider bluetoothTileProvider;
    public Provider blurUtilsProvider;
    public final Provider bootCompleteCacheImplProvider;
    public Provider bouncerActionButtonInteractorProvider;
    public Provider bouncerDialogFactoryProvider;
    public Provider bouncerInteractorProvider;
    public Provider bouncerLoggerProvider;
    public Provider bouncerMessageAuditLoggerProvider;
    public final Provider bouncerMessageInteractorProvider;
    public final Provider bouncerMessageRepositoryImplProvider;
    public Provider bouncerRepositoryProvider;
    public Provider bouncerSceneProvider;
    public Provider bouncerViewBinderProvider;
    public final Provider bouncerViewImplProvider;
    public Provider bouncerlessScrimControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider brightLineFalsingManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider brightnessDialogProvider;
    public Provider broadcastDialogControllerProvider;
    public final Provider broadcastDispatcherProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider broadcastDispatcherStartableProvider;
    public Provider broadcastFetcherProvider;
    public final Provider broadcastSenderProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider builderProvider;
    public Provider builderProvider2;
    public Provider builderProvider3;
    public Provider burnInInteractorProvider;
    public Provider cHREGestureSensorDelegatorProvider;
    public Provider cHREGestureSensorProvider;
    public Provider cHREGestureSensorProvider2;
    public Provider callbackHandlerProvider;
    public Provider cameraActionProvider;
    public Provider cameraAutoRotateRepositoryImplProvider;
    public Provider cameraGestureHelperProvider;
    public Provider cameraLauncherProvider;
    public Provider cameraQuickAffordanceConfigProvider;
    public Provider cameraSensorPrivacyRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider cameraToggleTileProvider;
    public Provider cameraVisibilityProvider;
    public Provider cameraVisibilityProvider2;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider carrierConfigCoreStartableProvider;
    public final Provider carrierConfigRepositoryProvider;
    public final Provider carrierConfigTrackerProvider;
    public Provider castAutoAddableProvider;
    public Provider castControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider castTileProvider;
    public Provider centralSurfacesCommandQueueCallbacksProvider;
    public DelegateFactory centralSurfacesGoogleProvider;
    public Provider channelEditorDialogControllerProvider;
    public Provider chargingStateProvider;
    public Provider chargingStateProvider2;
    public Provider chipbarAnimatorProvider;
    public Provider chipbarCoordinatorProvider;
    public Provider chipbarLoggerProvider;
    public Provider clipboardListenerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider clipboardOverlayControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider collapsedStatusBarFragmentProvider;
    public Provider collapsedStatusBarFragmentStartableProvider;
    public Provider collapsedStatusBarViewBinderImplProvider;
    public Provider collapsedStatusBarViewModelImplProvider;
    public Provider colorCorrectionRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider colorCorrectionTileProvider;
    public Provider colorInversionRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider colorInversionTileProvider;
    public Provider colorUpdateLoggerProvider;
    public Provider columbusManagerProvider;
    public Provider columbusServiceProvider;
    public Provider columbusServiceWrapperProvider;
    public Provider columbusSettingsFetcherProvider;
    public Provider columbusSettingsProvider;
    public Provider columbusStarterImplProvider;
    public Provider columbusStructuredDataManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider columbusTargetRequestServiceProvider;
    public final Provider commandRegistryProvider;
    public Provider communalAppWidgetHostStartableProvider;
    public Provider communalEditModeViewModelProvider;
    public DelegateFactory communalInteractorProvider;
    public Provider communalLoggerStartableProvider;
    public Provider communalMediaRepositoryImplProvider;
    public Provider communalPrefsRepositoryImplProvider;
    public final Provider communalRepositoryImplProvider;
    public Provider communalSceneProvider;
    public Provider communalSceneStartableProvider;
    public Provider communalSettingsInteractorProvider;
    public Provider communalSettingsRepositoryImplProvider;
    public Provider communalSmartspaceControllerProvider;
    public Provider communalTutorialDisabledRepositoryImplProvider;
    public Provider communalTutorialInteractorProvider;
    public Provider communalViewModelProvider;
    public Provider communalWidgetRepositoryImplProvider;
    public Provider composeBouncerDependenciesProvider;
    public final Provider configurationControllerImplProvider;
    public Provider configurationControllerStartableProvider;
    public Provider configurationHandlerProvider;
    public final Provider configurationInteractorProvider;
    public final Provider configurationRepositoryImplProvider;
    public final Provider connectedDisplayInteractorImplProvider;
    public Provider connectingDisplayViewModelProvider;
    public Provider connectivityConstantsProvider;
    public final Provider connectivityInputLoggerProvider;
    public final Provider connectivityRepositoryImplProvider;
    public final Provider connectivitySlotsProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider containerConfigProvider;
    public Provider contentFetcherProvider;
    public Provider contentResolverWrapperProvider;
    public DelegateFactory contextComponentResolverProvider;
    public Provider contextualTipsInteractorProvider;
    public Provider contextualTipsRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider contrastDialogActivityProvider;
    public Provider controlActionCoordinatorImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider controlsActivityProvider;
    public Provider controlsBindingControllerImplProvider;
    public DelegateFactory controlsComponentProvider;
    public DelegateFactory controlsControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider controlsEditingActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider controlsFavoritingActivityProvider;
    public DelegateFactory controlsListingControllerImplProvider;
    public Provider controlsMetricsLoggerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider controlsProviderSelectorActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider controlsRequestDialogProvider;
    public Provider controlsSettingsDialogManagerImplProvider;
    public Provider controlsSettingsRepositoryImplProvider;
    public Provider controlsStartableProvider;
    public Provider controlsUiControllerImplProvider;
    public final Provider conversationNotificationManagerProvider;
    public Provider coroutineScopeCoreStartableProvider;
    public final Provider countDownTimerUtilProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider createNoteTaskShortcutActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider createUserActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider credentialInteractorImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider credentialViewModelProvider;
    public Provider currentTilesInteractorImplProvider;
    public Provider customIconCacheProvider;
    public Provider customTileAddedSharedPrefsRepositoryProvider;
    public Provider customTileMapperProvider;
    public final Provider darkIconDispatcherImplProvider;
    public Provider darkIconRepositoryImplProvider;
    public Provider dataSaverAutoAddableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dataSaverTileProvider;
    public Provider debugModeFilterProvider;
    public Provider defaultKeyguardBlueprintProvider;
    public Provider defaultTilesQSHostRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider defaultUdfpsTouchOverlayViewModelProvider;
    public Provider defaultUiControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider deleteScreenshotReceiverProvider;
    public final Provider demoModeMobileConnectionDataSourceProvider;
    public final Provider demoModeWifiDataSourceProvider;
    public Provider dependencyProvider;
    public final Provider deviceBasedSatelliteBindableIconProvider;
    public final Provider deviceBasedSatelliteInteractorProvider;
    public final Provider deviceBasedSatelliteRepositoryImplProvider;
    public final Provider deviceConfigProxyProvider;
    public Provider deviceControlsAutoAddableProvider;
    public Provider deviceControlsControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider deviceControlsTileProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider deviceEntryBackgroundViewModelProvider;
    public Provider deviceEntryBiometricAuthInteractorProvider;
    public Provider deviceEntryBiometricSettingsInteractorProvider;
    public final DelegateFactory deviceEntryFaceAuthRepositoryImplProvider;
    public final Provider deviceEntryFingerprintAuthInteractorProvider;
    public final Provider deviceEntryFingerprintAuthRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider deviceEntryForegroundViewModelProvider;
    public Provider deviceEntryHapticsInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider deviceEntryIconViewModelProvider;
    public DelegateFactory deviceEntryInteractorProvider;
    public final Provider deviceEntryRepositoryImplProvider;
    public Provider deviceEntrySideFpsOverlayInteractorProvider;
    public Provider deviceEntrySourceInteractorProvider;
    public Provider deviceEntryUdfpsInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider deviceEntryUdfpsTouchOverlayViewModelProvider;
    public Provider deviceItemInteractorProvider;
    public final Provider devicePostureControllerImplProvider;
    public final Provider devicePostureRepositoryImplProvider;
    public final Provider deviceProvisionedControllerImplProvider;
    public final Provider deviceProvisioningInteractorProvider;
    public Provider deviceStateRotationLockSettingControllerProvider;
    public final Provider deviceUnlockedInteractorProvider;
    public final Provider disableFlagsLoggerProvider;
    public final Provider disableFlagsRepositoryImplProvider;
    public Provider disabledByPolicyInteractorImplProvider;
    public final Provider disabledWifiRepositoryProvider;
    public final Provider dismissCallbackRegistryProvider;
    public Provider dismissTimerProvider;
    public Provider displayMetricsRepositoryProvider;
    public final Provider displayRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider displayStateInteractorImplProvider;
    public final Provider displayStateRepositoryImplProvider;
    public Provider displaySwitchLatencyTrackerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dndTileProvider;
    public Provider doNotDisturbQuickAffordanceConfigProvider;
    public Provider dockAlignmentControllerProvider;
    public final DelegateFactory dockObserverProvider;
    public Provider dozeInteractorProvider;
    public final Provider dozeLogProvider;
    public final DelegateFactory dozeParametersProvider;
    public final Provider dozeScrimControllerProvider;
    public Provider dozeServiceHostProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dozeServiceProvider;
    public final Provider dozeTransitionListenerProvider;
    public Provider dozingToGoneTransitionViewModelProvider;
    public Provider dozingToLockscreenTransitionViewModelProvider;
    public Provider dozingToPrimaryBouncerTransitionViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dreamMonitorProvider;
    public final Provider dreamOverlayCallbackControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dreamOverlayServiceProvider;
    public Provider dreamOverlayStateControllerProvider;
    public Provider dreamOverlayStatusBarItemsProvider;
    public Provider dreamOverlayViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dreamTileProvider;
    public Provider dreamingHostedToLockscreenTransitionViewModelProvider;
    public Provider dreamingToGlanceableHubTransitionViewModelProvider;
    public Provider dreamingToLockscreenTransitionViewModelProvider;
    public Provider dynamicPrivacyControllerProvider;
    public Provider edgeLightsControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider editWidgetsActivityProvider;
    public Provider emergencyAffordanceManagerProvider;
    public Provider emergencyServicesRepositoryProvider;
    public final Provider enhancedEstimatesGoogleImplProvider;
    public Provider ethernetInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider eventLogImplProvider;
    public Provider extensionControllerImplProvider;
    public Provider faceAuthAccessibilityDelegateProvider;
    public final Provider faceAuthenticationLoggerProvider;
    public final Provider faceHelpMessageDeferralFactoryProvider;
    public Provider faceHelpMessageDeferralInteractorProvider;
    public final Provider facePropertyRepositoryImplProvider;
    public Provider faceScanningProviderFactoryProvider;
    public Provider faceSettingsRepositoryImplProvider;
    public final Provider faceWakeUpTriggersConfigImplProvider;
    public final Provider factoryProvider;
    public Provider factoryProvider10;
    public Provider factoryProvider11;
    public Provider factoryProvider12;
    public Provider factoryProvider13;
    public Provider factoryProvider14;
    public Provider factoryProvider15;
    public Provider factoryProvider16;
    public Provider factoryProvider17;
    public Provider factoryProvider18;
    public Provider factoryProvider19;
    public final Provider factoryProvider2;
    public Provider factoryProvider20;
    public Provider factoryProvider21;
    public Provider factoryProvider22;
    public Provider factoryProvider23;
    public Provider factoryProvider24;
    public Provider factoryProvider25;
    public Provider factoryProvider26;
    public Provider factoryProvider27;
    public Provider factoryProvider28;
    public Provider factoryProvider29;
    public final Provider factoryProvider3;
    public Provider factoryProvider30;
    public Provider factoryProvider31;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider factoryProvider32;
    public Provider factoryProvider33;
    public Provider factoryProvider34;
    public Provider factoryProvider35;
    public Provider factoryProvider36;
    public Provider factoryProvider37;
    public Provider factoryProvider38;
    public Provider factoryProvider39;
    public final Provider factoryProvider4;
    public Provider factoryProvider40;
    public Provider factoryProvider41;
    public Provider factoryProvider42;
    public final Provider factoryProvider5;
    public DelegateFactory factoryProvider6;
    public Provider factoryProvider7;
    public Provider factoryProvider8;
    public Provider factoryProvider9;
    public final DelegateFactory falsingCollectorImplProvider;
    public final Provider falsingCollectorNoOpProvider;
    public Provider falsingCoreStartableProvider;
    public final DelegateFactory falsingDataProvider;
    public Provider falsingInteractorProvider;
    public final DelegateFactory falsingManagerProxyProvider;
    public final DelegateFactory featureFlagsClassicReleaseProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider featureFlagsReleaseStartableProvider;
    public Provider fgsManagerControllerImplProvider;
    public final Provider filesProvider;
    public Provider fingerprintPropertyInteractorProvider;
    public final Provider fingerprintPropertyRepositoryImplProvider;
    public Provider flagDependenciesNotifierProvider;
    public Provider flagDependenciesProvider;
    public Provider flagEnabledProvider;
    public Provider flashlightControllerImplProvider;
    public Provider flashlightQuickAffordanceConfigProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider flashlightTileProvider;
    public Provider flingVelocityWrapperProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider fontScalingDialogDelegateProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider fontScalingTileProvider;
    public Provider footerActionsControllerProvider;
    public Provider footerActionsInteractorImplProvider;
    public Provider footerActionsViewBinderProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider foregroundServicesDialogProvider;
    public Provider foregroundServicesRepositoryImplProvider;
    public Provider fpsUnlockTrackerProvider;
    public final DelegateFactory fragmentServiceProvider;
    public Provider fromAlternateBouncerTransitionInteractorProvider;
    public Provider fromAodTransitionInteractorProvider;
    public Provider fromDozingTransitionInteractorProvider;
    public Provider fromDreamingLockscreenHostedTransitionInteractorProvider;
    public Provider fromDreamingTransitionInteractorProvider;
    public Provider fromGlanceableHubTransitionInteractorProvider;
    public Provider fromGoneTransitionInteractorProvider;
    public Provider fromLockscreenTransitionInteractorProvider;
    public Provider fromOccludedTransitionInteractorProvider;
    public Provider fromPrimaryBouncerTransitionInteractorProvider;
    public Provider gateFetcherProvider;
    public Provider gestureConfigurationProvider;
    public Provider gestureConfigurationProvider2;
    public Provider gestureControllerProvider;
    public Provider gestureControllerProvider2;
    public Provider gesturePointerEventDetectorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider gesturePointerEventListenerProvider;
    public Provider gestureSensorImplProvider;
    public Provider getClockRegistryProvider;
    public Provider glanceableHubToDreamingTransitionViewModelProvider;
    public Provider glanceableHubToLockscreenTransitionViewModelProvider;
    public Provider globalActionsComponentProvider;
    public DelegateFactory globalActionsDialogLiteProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider globalActionsImplProvider;
    public Provider globalActionsInteractorProvider;
    public Provider globalActionsRepositoryProvider;
    public Provider glowControllerProvider;
    public Provider goBackHandlerProvider;
    public Provider goneSceneProvider;
    public Provider goneToAodTransitionViewModelProvider;
    public Provider goneToDozingTransitionViewModelProvider;
    public Provider goneToDreamingLockscreenHostedTransitionViewModelProvider;
    public Provider goneToDreamingTransitionViewModelProvider;
    public Provider goneToLockscreenTransitionViewModelProvider;
    public Provider googleAssistLoggerProvider;
    public Provider googleControlsTileResourceConfigurationImplProvider;
    public Provider googleDefaultUiControllerProvider;
    public Provider googleServicesProvider;
    public Provider groupExpansionManagerImplProvider;
    public final Provider groupMembershipManagerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider guestResetOrExitSessionReceiverProvider;
    public Provider guestResumeSessionReceiverProvider;
    public DelegateFactory guestUserInteractorProvider;
    public Provider hapticClickProvider;
    public Provider headlessSystemUserModeImplProvider;
    public final Provider headsUpManagerPhoneProvider;
    public Provider headsUpNotificationIconViewStateRepositoryProvider;
    public final Provider headsUpViewBinderProvider;
    public Provider healthManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider healthServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider healthUpdateReceiverProvider;
    public Provider hideListViewModelProvider;
    public Provider hideNotificationsInteractorProvider;
    public Provider highPriorityProvider;
    public final Provider historyTrackerProvider;
    public Provider homeControlsComponentInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider homeControlsDreamServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider homeControlsDreamStartableProvider;
    public Provider homeControlsKeyguardQuickAffordanceConfigProvider;
    public Provider hotspotAutoAddableProvider;
    public Provider hotspotControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider hotspotTileProvider;
    public Provider iconControllerProvider;
    public final Provider iconManagerProvider;
    public Provider imageCaptureImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider imageWallpaperProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider immersiveModeConfirmationProvider;
    public final Provider implProvider;
    public Provider implProvider2;
    public Provider inWindowLauncherAnimationViewModelProvider;
    public Provider inWindowLauncherUnlockAnimationInteractorProvider;
    public Provider inWindowLauncherUnlockAnimationManagerProvider;
    public Provider inWindowLauncherUnlockAnimationRepositoryProvider;
    public final Provider indicationHelperProvider;
    public Provider initControllerProvider;
    public Provider inputMethodInteractorProvider;
    public Provider inputMethodRepositoryImplProvider;
    public Provider installedTilesComponentRepositoryImplProvider;
    public Provider instantAppNotifierProvider;
    public DelegateFactory internetDialogManagerProvider;
    public Provider internetTileViewModelProvider;
    public Provider isPMLiteEnabledProvider;
    public Provider isReduceBrightColorsAvailableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider issueRecordingServiceProvider;
    public Provider jNIGestureSensorProvider;
    public final Provider javaAdapterProvider;
    public Provider keyEventInteractorProvider;
    public Provider keyEventRepositoryImplProvider;
    public Provider keyboardBacklightDialogCoordinatorProvider;
    public Provider keyboardBacklightInteractorProvider;
    public Provider keyboardRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider keyboardShortcutsReceiverProvider;
    public Provider keyboardUIProvider;
    public Provider keyguardBiometricLockoutLoggerProvider;
    public DelegateFactory keyguardBlueprintInteractorProvider;
    public DelegateFactory keyguardBlueprintRepositoryProvider;
    public Provider keyguardBlueprintViewBinderProvider;
    public Provider keyguardBottomAreaInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider keyguardBottomAreaViewControllerProvider;
    public final Provider keyguardBouncerRepositoryImplProvider;
    public final DelegateFactory keyguardBypassControllerProvider;
    public Provider keyguardClockInteractorProvider;
    public Provider keyguardClockRepositoryImplProvider;
    public Provider keyguardClockViewModelProvider;
    public Provider keyguardDismissActionBinderProvider;
    public Provider keyguardDismissActionInteractorProvider;
    public Provider keyguardDismissBinderProvider;
    public Provider keyguardDismissInteractorProvider;
    public final Provider keyguardDismissUtilProvider;
    public Provider keyguardDisplayManagerProvider;
    public final Provider keyguardIndicationControllerGoogleProvider;
    public final DelegateFactory keyguardInteractorProvider;
    public Provider keyguardKeyEventInteractorProvider;
    public Provider keyguardKeyboardInteractorProvider;
    public Provider keyguardLifecyclesDispatcherProvider;
    public Provider keyguardLongPressInteractorProvider;
    public Provider keyguardLongPressViewModelProvider;
    public Provider keyguardMediaControllerProvider;
    public Provider keyguardNotificationVisibilityProviderImplProvider;
    public Provider keyguardPreviewRendererFactoryProvider;
    public Provider keyguardProximityProvider;
    public Provider keyguardQuickAffordanceInteractorProvider;
    public Provider keyguardQuickAffordanceLegacySettingSyncerProvider;
    public Provider keyguardQuickAffordanceLocalUserSelectionManagerProvider;
    public Provider keyguardQuickAffordanceRemoteUserSelectionManagerProvider;
    public DelegateFactory keyguardQuickAffordanceRepositoryProvider;
    public Provider keyguardRemotePreviewManagerProvider;
    public final Provider keyguardRepositoryImplProvider;
    public Provider keyguardRootViewModelProvider;
    public final Provider keyguardSecurityModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider keyguardServiceProvider;
    public Provider keyguardSmartspaceInteractorProvider;
    public Provider keyguardSmartspaceRepositoryProvider;
    public Provider keyguardSmartspaceStartableProvider;
    public Provider keyguardSmartspaceViewModelProvider;
    public final DelegateFactory keyguardStateControllerImplProvider;
    public Provider keyguardStatusBarInteractorProvider;
    public Provider keyguardStatusBarRepositoryImplProvider;
    public Provider keyguardStatusBarViewModelProvider;
    public Provider keyguardSurfaceBehindInteractorProvider;
    public Provider keyguardSurfaceBehindParamsApplierProvider;
    public Provider keyguardSurfaceBehindRepositoryImplProvider;
    public Provider keyguardSurfaceBehindViewModelProvider;
    public Provider keyguardTransitionAnimationFlowProvider;
    public Provider keyguardTransitionAuditLoggerProvider;
    public Provider keyguardTransitionCoreStartableProvider;
    public final DelegateFactory keyguardTransitionInteractorProvider;
    public final Provider keyguardTransitionRepositoryImplProvider;
    public DelegateFactory keyguardUnlockAnimationControllerProvider;
    public final DelegateFactory keyguardUpdateMonitorProvider;
    public Provider keyguardViewConfiguratorProvider;
    public Provider keyguardVisibilityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider largeScreenHeaderHelperProvider;
    public Provider largeScreenShadeInterpolatorImplProvider;
    public Provider latencyTesterProvider;
    public Provider launchAppProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider launchConversationActivityProvider;
    public Provider launchFullScreenIntentProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider launchNoteTaskActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider launchNotesRoleSettingsTrampolineActivityProvider;
    public Provider launchOpaProvider;
    public Provider launchOpaProvider2;
    public Provider launchOverviewProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider launcherTileServiceProvider;
    public final LeakModule leakModule;
    public Provider legacyBouncerDependenciesProvider;
    public Provider legacyNotificationIconAreaControllerImplProvider;
    public final Provider letterboxAppearanceCalculatorProvider;
    public final Provider letterboxBackgroundProvider;
    public Provider liftToRunFaceAuthBinderProvider;
    public final Provider lightBarControllerProvider;
    public Provider lightRevealScrimInteractorProvider;
    public Provider lightRevealScrimRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider lightRevealScrimViewModelProvider;
    public Provider lightnessProvider;
    public Provider lightsOutInteractorProvider;
    public Provider locationControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider locationTileProvider;
    public Provider lockIconViewControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider lockscreenContentProvider;
    public DelegateFactory lockscreenContentViewModelProvider;
    public Provider lockscreenGestureLoggerProvider;
    public Provider lockscreenHostedDreamGestureListenerProvider;
    public Provider lockscreenSceneProvider;
    public Provider lockscreenSceneViewModelProvider;
    public Provider lockscreenShadeTransitionControllerProvider;
    public Provider lockscreenSmartspaceControllerProvider;
    public Provider lockscreenToAodTransitionViewModelProvider;
    public Provider lockscreenToDozingTransitionViewModelProvider;
    public Provider lockscreenToDreamingHostedTransitionViewModelProvider;
    public Provider lockscreenToDreamingTransitionViewModelProvider;
    public Provider lockscreenToGlanceableHubTransitionViewModelProvider;
    public Provider lockscreenToGoneTransitionViewModelProvider;
    public Provider lockscreenToOccludedTransitionViewModelProvider;
    public Provider lockscreenToPrimaryBouncerTransitionViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider lockscreenWallpaperDreamServiceProvider;
    public final Provider logBufferEulogizerProvider;
    public final Provider logBufferFactoryProvider;
    public Provider logContextInteractorImplProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider logcatEchoTrackerDebugProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider longScreenshotActivityProvider;
    public Provider longScreenshotDataProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider lowLightClockDreamServiceProvider;
    public Provider lowSensitivitySettingAdjustmentProvider;
    public Provider magnificationProvider;
    public Provider manageMediaProvider;
    public Provider managedProfileControllerImplProvider;
    public Provider mediaCarouselControllerLoggerProvider;
    public DelegateFactory mediaCarouselControllerProvider;
    public final Provider mediaContainerControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mediaControlPanelProvider;
    public final Provider mediaDataManagerProvider;
    public final Provider mediaFlagsProvider;
    public Provider mediaHierarchyManagerProvider;
    public Provider mediaHostStatesManagerProvider;
    public Provider mediaMuteAwaitConnectionCliProvider;
    public final Provider mediaMuteAwaitConnectionManagerFactoryProvider;
    public final Provider mediaMuteAwaitLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mediaOutputDialogReceiverProvider;
    public Provider mediaOutputSwitcherDialogUIProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mediaProjectionAppSelectorActivityProvider;
    public final MediaProjectionDevicePolicyModule mediaProjectionDevicePolicyModule;
    public Provider mediaProjectionManagerRepositoryProvider;
    public Provider mediaProjectionMetricsLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mediaProjectionPermissionActivityProvider;
    public Provider mediaProjectionTaskSwitcherCoreStartableProvider;
    public final Provider mediaResumeListenerProvider;
    public Provider mediaSessionLegacyHelperWrapperProvider;
    public final Provider mediaTimeoutListenerProvider;
    public final Provider mediaTimeoutLoggerProvider;
    public Provider mediaTttChipControllerReceiverProvider;
    public Provider mediaTttCommandLineHelperProvider;
    public Provider mediaTttFlagsProvider;
    public Provider mediaTttReceiverLoggerProvider;
    public Provider mediaTttReceiverUiEventLoggerProvider;
    public Provider mediaTttSenderCoordinatorProvider;
    public Provider mediaTttSenderLoggerProvider;
    public Provider mediaTttSenderUiEventLoggerProvider;
    public final Provider mediaUiEventLoggerProvider;
    public Provider mediaViewLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider microphoneToggleTileProvider;
    public Provider minimumTilesResourceRepositoryProvider;
    public final Provider mobileConnectionsRepositoryImplProvider;
    public Provider mobileContextProvider;
    public final Provider mobileIconsInteractorImplProvider;
    public Provider mobileIconsViewModelProvider;
    public final Provider mobileInputLoggerProvider;
    public final Provider mobileRepositorySwitcherProvider;
    public Provider mobileSignalControllerFactoryProvider;
    public Provider mobileUiAdapterProvider;
    public Provider mobileViewLoggerProvider;
    public Provider modeSwitchesControllerProvider;
    public Provider motionToolStartableProvider;
    public Provider muteQuickAffordanceConfigProvider;
    public Provider muteQuickAffordanceCoreStartableProvider;
    public Provider naturalScrollingSettingObserverProvider;
    public Provider navBarFadeControllerProvider;
    public Provider navBarFaderProvider;
    public Provider navBarHelperProvider;
    public DelegateFactory navigationBarControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider navigationBarEdgePanelProvider;
    public final Provider navigationModeControllerProvider;
    public Provider nearbyMediaDevicesLoggerProvider;
    public Provider nearbyMediaDevicesManagerProvider;
    public Provider networkControllerImplProvider;
    public final DelegateFactory newKeyguardViewMediatorProvider;
    public Provider newQSTileFactoryProvider;
    public Provider nextAlarmControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider nfcTileProvider;
    public Provider ngaMessageHandlerProvider;
    public Provider ngaUiControllerProvider;
    public Provider nightDisplayAutoAddableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider nightDisplayTileProvider;
    public Provider noOpGestureSensorProvider;
    public Provider noteTaskBubblesControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider noteTaskBubblesServiceProvider;
    public Provider noteTaskControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider noteTaskControllerUpdateServiceProvider;
    public final Provider notifBindPipelineProvider;
    public final Provider notifCollectionProvider;
    public final Provider notifCoordinatorsProvider;
    public final Provider notifInflaterImplProvider;
    public final Provider notifInflationErrorManagerProvider;
    public final Provider notifLiveDataStoreImplProvider;
    public final Provider notifPipelineChoreographerImplProvider;
    public final Provider notifPipelineInitializerProvider;
    public final Provider notifPipelineProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notifRemoteViewCacheImplProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notifRemoteViewsFactoryContainerImplProvider;
    public Provider notifUiAdjustmentProvider;
    public final Provider notifViewBarnProvider;
    public Provider notificationAlertsInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notificationChannelsProvider;
    public final Provider notificationClickNotifierProvider;
    public final Provider notificationContentInflaterProvider;
    public final Provider notificationDismissibilityProviderImplProvider;
    public Provider notificationGutsManagerProvider;
    public Provider notificationIconContainerAlwaysOnDisplayViewModelProvider;
    public Provider notificationInsetsImplProvider;
    public final Provider notificationInteractionTrackerProvider;
    public Provider notificationInterruptStateProviderImplProvider;
    public Provider notificationLaunchAnimationInteractorProvider;
    public Provider notificationLaunchAnimationRepositoryProvider;
    public final Provider notificationListenerProvider;
    public final Provider notificationListenerSettingsRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notificationListenerWithPluginsProvider;
    public final DelegateFactory notificationLockscreenUserManagerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notificationLoggerViewModelProvider;
    public Provider notificationMemoryDumperProvider;
    public Provider notificationMemoryLoggerProvider;
    public Provider notificationMemoryMonitorProvider;
    public DelegateFactory notificationPanelViewControllerProvider;
    public Provider notificationPersonExtractorPluginBoundaryProvider;
    public final Provider notificationRemoteInputManagerProvider;
    public Provider notificationRoundnessManagerProvider;
    public final Provider notificationRowBinderImplProvider;
    public Provider notificationSectionProvider;
    public final Provider notificationSectionsFeatureManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notificationSectionsManagerProvider;
    public Provider notificationSettingsControllerProvider;
    public Provider notificationShadeDepthControllerProvider;
    public final DelegateFactory notificationShadeWindowControllerImplProvider;
    public Provider notificationShadeWindowViewControllerProvider;
    public Provider notificationShelfInteractorProvider;
    public Provider notificationShelfViewModelProvider;
    public Provider notificationStackAppearanceInteractorProvider;
    public Provider notificationStackAppearanceRepositoryProvider;
    public Provider notificationStackAppearanceViewModelProvider;
    public Provider notificationStackInteractorProvider;
    public DelegateFactory notificationStackScrollLayoutControllerProvider;
    public Provider notificationStackSizeCalculatorProvider;
    public Provider notificationStatsLoggerImplProvider;
    public Provider notificationTargetsHelperProvider;
    public final Provider notificationVisibilityProviderImplProvider;
    public Provider notificationWakeUpCoordinatorProvider;
    public final Provider notificationsControllerImplProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notificationsControllerStubProvider;
    public Provider notificationsKeyguardViewStateRepositoryProvider;
    public Provider notificationsPlaceholderViewModelProvider;
    public Provider notificationsQSContainerControllerProvider;
    public Provider occludedToAodTransitionViewModelProvider;
    public Provider occludedToLockscreenTransitionViewModelProvider;
    public Provider occludingAppDeviceEntryInteractorProvider;
    public Provider occludingAppDeviceEntryMessageViewModelProvider;
    public Provider offToLockscreenTransitionViewModelProvider;
    public Provider onUserInteractionCallbackImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider oneHandedModeTileProvider;
    public final Provider ongoingCallControllerProvider;
    public final Provider ongoingCallLoggerProvider;
    public final Provider ongoingCallRepositoryProvider;
    public Provider opaEnabledReceiverProvider;
    public Provider openNotificationShadeProvider;
    public DelegateFactory optionalOfCentralSurfacesProvider;
    public Provider overlappedElementControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider overlayToggleTileProvider;
    public Provider overlayUiHostProvider;
    public Provider overviewProxyRecentsImplProvider;
    public final DelegateFactory overviewProxyServiceProvider;
    public Provider packageChangeInteractorProvider;
    public Provider packageChangeRepositoryImplProvider;
    public Provider packageUpdateLoggerProvider;
    public Provider panelExpansionInteractorProvider;
    public Provider panelInteractorImplProvider;
    public Provider peopleNotificationIdentifierImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider peopleSpaceActivityProvider;
    public final Provider peopleSpaceWidgetManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider peopleSpaceWidgetPinnedReceiverProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider peopleSpaceWidgetProvider;
    public Provider peopleTileRepositoryImplProvider;
    public Provider peopleWidgetRepositoryImplProvider;
    public Provider phoneStateMonitorProvider;
    public Provider physicalKeyboardCoreStartableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider postureDependentProximitySensorProvider;
    public final DelegateFactory powerInteractorProvider;
    public Provider powerManagerWrapperProvider;
    public Provider powerNotificationWarningsGoogleImplProvider;
    public final Provider powerRepositoryImplProvider;
    public Provider powerSaveStateProvider;
    public Provider powerStateProvider;
    public Provider powerUIProvider;
    public final Provider primaryBouncerCallbackInteractorProvider;
    public final DelegateFactory primaryBouncerInteractorProvider;
    public Provider primaryBouncerToAodTransitionViewModelProvider;
    public Provider primaryBouncerToDozingTransitionViewModelProvider;
    public Provider primaryBouncerToGoneTransitionViewModelProvider;
    public Provider primaryBouncerToLockscreenTransitionViewModelProvider;
    public Provider privacyChipInteractorProvider;
    public Provider privacyChipRepositoryImplProvider;
    public final Provider privacyConfigProvider;
    public Provider privacyDialogControllerProvider;
    public Provider privacyDialogControllerV2Provider;
    public Provider privacyDotDecorProviderFactoryProvider;
    public Provider privacyDotViewControllerProvider;
    public final Provider privacyItemControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider promptCredentialInteractorProvider;
    public Provider promptRepositoryImplProvider;
    public Provider promptSelectorInteractorImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider promptViewModelProvider;
    public Provider provideAccessPointControllerImplProvider;
    public final Provider provideActivityManagerWrapperProvider;
    public Provider provideActivityTransitionAnimatorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideAirplaneModeTileViewModelProvider;
    public final Provider provideAirplaneTableLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideAlarmTileViewModelProvider;
    public Provider provideAllowNotificationLongPressProvider;
    public Provider provideAncSliceRepositoryProvider;
    public Provider provideAnimationFeatureFlagsProvider;
    public final Provider provideAppWidgetManagerProvider;
    public Provider provideAssistUtilsProvider;
    public Provider provideAutoRotateSettingsManagerProvider;
    public Provider provideAutoTileManagerProvider;
    public final Provider provideBackgroundDelayableExecutorProvider;
    public final Provider provideBackgroundExecutorProvider;
    public Provider provideBackgroundRepeatableExecutorProvider;
    public Provider provideBaseShadeInteractorProvider;
    public final Provider provideBatteryControllerLogProvider;
    public final Provider provideBatteryControllerProvider;
    public Provider provideBcSmartspaceConfigPluginProvider;
    public Provider provideBcSmartspaceDataPluginProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideBgHandlerProvider;
    public final Provider provideBgLooperProvider;
    public final Provider provideBiometricLogBufferProvider;
    public final Provider provideBouncerLogBufferProvider;
    public Provider provideBouncerLogProvider;
    public final Provider provideBroadcastDispatcherLogBufferProvider;
    public final Provider provideBroadcastRunningExecutorProvider;
    public final Provider provideBroadcastRunningLooperProvider;
    public Provider provideBubblesManagerProvider;
    public Provider provideCarrierTextManagerLogProvider;
    public Provider provideChipbarLogBufferProvider;
    public Provider provideCollapsedSbFragmentLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideColorCorrectionTileViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideColorInversionTileViewModelProvider;
    public Provider provideColumbusActionsProvider;
    public Provider provideColumbusStarterProvider;
    public final Provider provideCommandQueueProvider;
    public Provider provideCommunalAppWidgetHostProvider;
    public Provider provideCommunalDatabaseProvider;
    public Provider provideCommunalLogBufferProvider;
    public Provider provideCommunalTableLogBufferProvider;
    public DelegateFactory provideCommunalWidgetDaoProvider;
    public Provider provideCommunalWidgetHostProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideCoreStartableProvider;
    public Provider provideDataSaverControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideDataSaverTileViewModelProvider;
    public Provider provideDateSmartspaceDataPluginProvider;
    public final Provider provideDemoModeControllerProvider;
    public final Provider provideDevicePolicyManagerWrapperProvider;
    public final Provider provideDeviceProvisionedControllerProvider;
    public Provider provideDeviceStateAutoRotationLogBufferProvider;
    public Provider provideDialogTransitionAnimatorProvider;
    public final Provider provideDisableFlagsRepositoryLogBufferProvider;
    public Provider provideDisplayMetricsRepoLogBufferProvider;
    public final Provider provideDisplayTrackerProvider;
    public final Provider provideDozeLogBufferProvider;
    public Provider provideDreamLogBufferProvider;
    public final Provider provideFaceAuthLogProvider;
    public Provider provideFaceAuthTableLogProvider;
    public Provider provideFaceDetectTableLogProvider;
    public final Provider provideFingerprintInteractiveToAuthProvider;
    public Provider provideFingerprintReEnrollNotificationProvider;
    public Provider provideFirstMobileSubShowingNetworkTypeIconProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideFlashlightTileViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideFontScalingTileViewModelProvider;
    public Provider provideFullscreenActionsProvider;
    public Provider provideGestureAdjustmentsProvider;
    public Provider provideGestureSensorProvider;
    public Provider provideGestureSensorProvider2;
    public Provider provideGlanceableHubBcSmartspaceDataPluginProvider;
    public Provider provideHalDataSourceProvider;
    public final Provider provideHealthManagerProvider;
    public final Provider provideIThermalServiceProvider;
    public final Provider provideIndividualSensorPrivacyControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideInputMonitorProvider;
    public Provider provideKeyboardLogBufferProvider;
    public Provider provideKeyguardClockLogProvider;
    public Provider provideKeyguardLargeClockLogProvider;
    public final Provider provideKeyguardLogBufferProvider;
    public Provider provideKeyguardMediaControllerLogBufferProvider;
    public Provider provideKeyguardSmallClockLogProvider;
    public Provider provideKeyguardTransitionAnimationLogBufferProvider;
    public final Provider provideKeyguardUpdateMonitorLogBufferProvider;
    public Provider provideLSShadeTransitionControllerBufferProvider;
    public final Provider provideLegacyLoggerOptionalProvider;
    public Provider provideListContainerProvider;
    public final Provider provideLocalBluetoothControllerProvider;
    public Provider provideLocalMediaInteractorProvider;
    public Provider provideLocalMediaRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideLocationTileViewModelProvider;
    public final Provider provideLogcatEchoTrackerProvider;
    public Provider provideLongRunningDelayableExecutorProvider;
    public Provider provideLongRunningExecutorProvider;
    public Provider provideLongRunningLooperProvider;
    public final Provider provideMediaBrowserBufferProvider;
    public Provider provideMediaCarouselControllerBufferProvider;
    public Provider provideMediaDeviceSessionRepositoryProvider;
    public final Provider provideMediaMuteAwaitLogBufferProvider;
    public Provider provideMediaTttReceiverLogBufferProvider;
    public Provider provideMediaTttSenderLogBufferProvider;
    public Provider provideMediaViewLogBufferProvider;
    public final Provider provideMobileInputLogBufferProvider;
    public final Provider provideMobileSummaryLogBufferProvider;
    public Provider provideMobileViewLogBufferProvider;
    public Provider provideMonitorTableLogBufferProvider;
    public Provider provideNavBarButtonClickLogBufferProvider;
    public Provider provideNavbarOrientationTrackingLogBufferProvider;
    public Provider provideNearbyMediaDevicesLogBufferProvider;
    public final Provider provideNotifInflationExecutorProvider;
    public final Provider provideNotifInflationLogBufferProvider;
    public final Provider provideNotifInflationLooperProvider;
    public final Provider provideNotifInteractionLogBufferProvider;
    public final Provider provideNotifRemoteViewCacheProvider;
    public final Provider provideNotifRemoteViewsFactoryContainerProvider;
    public final Provider provideNotificationHeadsUpLogBufferProvider;
    public Provider provideNotificationInterruptLogBufferProvider;
    public Provider provideNotificationLockScreenLogBufferProvider;
    public final Provider provideNotificationMediaManagerProvider;
    public final Provider provideNotificationPanelLoggerProvider;
    public final Provider provideNotificationRemoteInputLogBufferProvider;
    public Provider provideNotificationRenderLogBufferProvider;
    public Provider provideNotificationSettingsInteractorProvider;
    public Provider provideNotificationSettingsRepositoryProvider;
    public Provider provideNotificationTransitionAnimatorControllerProvider;
    public final Provider provideNotificationsControllerProvider;
    public final Provider provideNotificationsLogBufferProvider;
    public Provider provideNotificationsSoundPolicyInteractrorProvider;
    public Provider provideNotificationsSoundPolicyRepositoryProvider;
    public final Provider provideOemSatelliteInputLogProvider;
    public Provider provideOptionalProvider;
    public Provider providePackageChangeRepoLogBufferProvider;
    public Provider provideParentViewGroupProvider;
    public final Provider providePrivacyLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideProximitySensorProvider;
    public Provider provideQBluetoothTileDialogLogBufferProvider;
    public Provider provideQSAutoAddLogBufferProvider;
    public Provider provideQSConfigLogBufferProvider;
    public Provider provideQSFragmentDisableLogBufferProvider;
    public Provider provideQSTileListLogBufferProvider;
    public Provider provideQuickAccessWalletClientProvider;
    public Provider provideQuickSettingsLogBufferProvider;
    public Provider provideQuickTapActionsProvider;
    public Provider provideQuickTapWakeLockProvider;
    public final Provider provideRealWifiRepositoryProvider;
    public Provider provideRecentsProvider;
    public final Provider provideReverseChargingViewControllerOptionalProvider;
    public final Provider provideReverseWirelessChargerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideRotationTileViewModelProvider;
    public final Provider provideSceneFrameworkLogBufferProvider;
    public Provider provideScreenDecorationsLogProvider;
    public Provider provideScrimLogBufferProvider;
    public Provider provideSecureSettingsRepositoryProvider;
    public Provider provideSensitiveNotificationProtectionLogBufferProvider;
    public Provider provideSensorPrivacyControllerProvider;
    public Provider provideSettingsDataSourceProvider;
    public Provider provideShadeAnimationInteractorProvider;
    public DelegateFactory provideShadeControllerProvider;
    public Provider provideShadeLockscreenInteractorProvider;
    public Provider provideShadeLogBufferProvider;
    public Provider provideShadeTouchLogBufferProvider;
    public Provider provideShadeWindowLogBufferProvider;
    public final Provider provideSharedConnectivityTableLogBufferProvider;
    public final Provider provideSmartReplyControllerProvider;
    public final Provider provideStatusBarIconListProvider;
    public Provider provideStatusBarNetworkControllerBufferProvider;
    public final Provider provideSwipeUpLogBufferProvider;
    public Provider provideSysUIUnfoldComponentProvider;
    public final Provider provideSysUiStateProvider;
    public Provider provideSystemEventDataSourceProvider;
    public Provider provideSystemStatusAnimationSchedulerLogBufferProvider;
    public Provider provideSystemUserMonitorProvider;
    public final Provider provideTaskStackChangeListenersProvider;
    public Provider provideTimeTickHandlerProvider;
    public Provider provideToastLogBufferProvider;
    public Provider provideUdfpsLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideUiModeNightTileViewModelProvider;
    public Provider provideUnseenNotificationLogBufferProvider;
    public final Provider provideUsbManagerProvider;
    public Provider provideUserSelectedActionsProvider;
    public final DelegateFactory provideUserTrackerProvider;
    public Provider provideVerboseMobileViewLogBufferProvider;
    public Provider provideVisualInterruptionDecisionProvider;
    public final Provider provideWakeLockLogProvider;
    public Provider provideWeatherSmartspaceDataPluginProvider;
    public final Provider provideWifiInputLogBufferProvider;
    public final Provider provideWifiRepositoryDaggerProvider;
    public final Provider provideWifiRepositoryViaTrackerLibDaggerProvider;
    public final Provider provideWifiTableLogBufferProvider;
    public final Provider provideWifiTrackerLibInputLogBufferProvider;
    public final Provider provideWifiTrackerLibTableLogBufferProvider;
    public final Provider provideWirelessChargerProvider;
    public Provider providerBluetoothLogBufferProvider;
    public final Provider providerProvider;
    public Provider providesAlertingHeaderSubcomponentProvider;
    public Provider providesAuthRippleViewProvider;
    public Provider providesBatteryMeterViewControllerProvider;
    public Provider providesBatteryMeterViewProvider;
    public Provider providesBiometricStatusInteractorProvider;
    public Provider providesCombinedShadeHeadersConstraintManagerProvider;
    public Provider providesCommunalMediaHostProvider;
    public Provider providesControlsFeatureEnabledProvider;
    public Provider providesCredentialInteractorProvider;
    public Provider providesDisplayStateInteractorProvider;
    public Provider providesDreamOverlayNotificationCountProvider;
    public final Provider providesFalsingCollectorLegacyProvider;
    public Provider providesGestureSensorProvider;
    public Provider providesIncomingHeaderSubcomponentProvider;
    public Provider providesKeyguardMediaHostProvider;
    public Provider providesKeyguardRootViewProvider;
    public final Provider providesLeakDetectorProvider;
    public Provider providesLightRevealScrimProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider providesLockscreenBlueprintsProvider;
    public final Provider providesMediaTimeoutListenerLogBufferProvider;
    public DelegateFactory providesNotificationPanelViewProvider;
    public DelegateFactory providesNotificationShadeWindowViewProvider;
    public Provider providesNotificationStackScrollLayoutProvider;
    public Provider providesNotificationsQuickSettingsContainerProvider;
    public Provider providesOngoingPrivacyChipProvider;
    public Provider providesOverlapDetectorProvider;
    public Provider providesPeopleHeaderSubcomponentProvider;
    public Provider providesPluginExecutorProvider;
    public Provider providesQSMediaHostProvider;
    public Provider providesQSRestoreLogBufferProvider;
    public Provider providesQuickQSMediaHostProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider providesScrnshtNotifSmartActionsProvider;
    public Provider providesShadeHeaderViewProvider;
    public Provider providesSharedNotificationContainerProvider;
    public Provider providesSilentHeaderSubcomponentProvider;
    public final Provider providesStatusBarWindowViewProvider;
    public Provider providesStatusIconContainerProvider;
    public Provider providesTapAgainViewProvider;
    public Provider providesThreadAssertProvider;
    public DelegateFactory providesWindowRootViewProvider;
    public Provider providsBackGestureTfClassifierProvider;
    public Provider proximityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider proximitySensorImplProvider;
    public Provider pulseExpansionHandlerProvider;
    public Provider pulsingGestureListenerProvider;
    public Provider qRCodeScannerControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider qRCodeScannerTileProvider;
    public Provider qSFactoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider qSFragmentLegacyProvider;
    public Provider qSFragmentStartableProvider;
    public DelegateFactory qSHostAdapterProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider qSImplProvider;
    public Provider qSPipelineCoreStartableProvider;
    public Provider qSPipelineFlagsRepositoryProvider;
    public Provider qSSceneAdapterImplProvider;
    public Provider qSSecurityFooterUtilsProvider;
    public Provider qSSettingsRestoredBroadcastRepositoryProvider;
    public Provider qSTileAnalyticsProvider;
    public Provider qSTileConfigProviderImplProvider;
    public DelegateFactory qSTileHostProvider;
    public Provider qSTileIntentUserInputHandlerImplProvider;
    public Provider qSTileLoggerProvider;
    public Provider qrCodeScannerKeyguardQuickAffordanceConfigProvider;
    public Provider qsEventLoggerImplProvider;
    public Provider qsFrameTranslateImplProvider;
    public Provider quickAccessWalletControllerProvider;
    public Provider quickAccessWalletKeyguardQuickAffordanceConfigProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider quickAccessWalletTileProvider;
    public Provider quickSettingsControllerProvider;
    public Provider quickSettingsSceneProvider;
    public Provider quickSettingsSceneViewModelProvider;
    public Provider rearDisplayDialogControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider recordIssueTileProvider;
    public Provider recordingControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider recordingServiceProvider;
    public Provider reduceBrightColorsAutoAddableProvider;
    public Provider reduceBrightColorsControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider reduceBrightColorsTileProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider refreshRateInteractorProvider;
    public Provider refreshRateRequesterBinderProvider;
    public Provider refreshUsersSchedulerProvider;
    public final Provider remoteInputControllerLoggerProvider;
    public Provider remoteInputInteractorProvider;
    public Provider remoteInputNotificationRebuilderProvider;
    public final Provider remoteInputQuickSettingsDisablerProvider;
    public Provider remoteInputRepositoryImplProvider;
    public final Provider remoteInputUriControllerProvider;
    public final Provider renderStageManagerProvider;
    public Provider requestProcessorProvider;
    public Provider resourceTrimmerProvider;
    public Provider restartDozeListenerProvider;
    public Provider restoreReconciliationInteractorProvider;
    public final Provider resumeMediaBrowserLoggerProvider;
    public Provider retailModeInteractorImplProvider;
    public Provider retailModeSettingsRepositoryProvider;
    public Provider reverseChargingAutoAddableProvider;
    public final Provider reverseChargingControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider reverseChargingTileProvider;
    public final Provider reverseChargingViewControllerProvider;
    public Provider ringerModeTrackerImplProvider;
    public Provider ringtonePlayerProvider;
    public Provider rotationLockControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider rotationLockTileGoogleProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider rotationPolicyWrapperImplProvider;
    public final Provider rowContentBindStageProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider rowInflaterTaskProvider;
    public final Provider sceneContainerPluginProvider;
    public Provider sceneContainerStartableProvider;
    public Provider sceneContainerViewModelProvider;
    public final Provider sceneDataSourceDelegatorProvider;
    public final Provider sceneInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider screenCaptureDevicePolicyResolverProvider;
    public Provider screenDecorationsLoggerProvider;
    public Provider screenDecorationsProvider;
    public Provider screenOffAnimationControllerProvider;
    public Provider screenOnCoordinatorProvider;
    public Provider screenPinningRequestProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider screenRecordTileProvider;
    public Provider screenTouchProvider;
    public Provider screenshotPolicyImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider screenshotProxyServiceProvider;
    public Provider screenshotSmartActionsProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider screenshotSoundControllerImplProvider;
    public Provider screenshotSoundProviderImplProvider;
    public Provider scrimControllerProvider;
    public Provider scrimControllerProvider2;
    public Provider scrimShadeTransitionControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider sectionHeaderControllerSubcomponentBuilderProvider;
    public final Provider sectionHeaderVisibilityProvider;
    public Provider sectionStyleProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider secureSettingsImplProvider;
    public Provider securityControllerImplProvider;
    public Provider securityRepositoryImplProvider;
    public Provider seenNotificationsInteractorProvider;
    public Provider selectedComponentRepositoryImplProvider;
    public final Provider selectedUserInteractorProvider;
    public Provider sensitiveNotificationProtectionControllerImplProvider;
    public Provider sensorConfigurationProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider sensorUseStartedActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider serviceConfigurationGoogleProvider;
    public final DelegateFactory sessionTrackerProvider;
    public final Optional setBackAnimation;
    public final Optional setBubbles;
    public final Optional setDesktopMode;
    public final Optional setDisplayAreaHelper;
    public final KeyguardTransitions setKeyguardTransitions;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider setOfSceneProvider;
    public final Optional setOneHanded;
    public final Optional setPip;
    public final Optional setRecentTasks;
    public final ShellInterface setShell;
    public final Optional setSplitScreen;
    public final Optional setStartingSurface;
    public final Optional setTaskViewFactory;
    public final ShellTransitions setTransitions;
    public Provider settingsActionProvider;
    public Provider settingsActionProvider2;
    public Provider setupWizardActionProvider;
    public Provider setupWizardProvider;
    public Provider setupWizardProvider2;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider severeLowBatteryNotificationProvider;
    public Provider shadeAnimationInteractorLegacyImplProvider;
    public Provider shadeAnimationInteractorSceneContainerImplProvider;
    public Provider shadeAnimationRepositoryProvider;
    public Provider shadeControllerImplProvider;
    public Provider shadeControllerSceneImplProvider;
    public Provider shadeEventCoordinatorProvider;
    public Provider shadeExpansionStateManagerProvider;
    public Provider shadeHeaderClockInteractorProvider;
    public Provider shadeHeaderClockRepositoryProvider;
    public Provider shadeHeaderControllerProvider;
    public Provider shadeHeaderViewModelProvider;
    public final DelegateFactory shadeInteractorImplProvider;
    public Provider shadeInteractorLegacyImplProvider;
    public Provider shadeInteractorSceneContainerImplProvider;
    public final Provider shadeListBuilderProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider shadeLockscreenInteractorImplProvider;
    public final Provider shadeRepositoryImplProvider;
    public Provider shadeSceneProvider;
    public Provider shadeSceneViewModelProvider;
    public Provider shadeTransitionControllerProvider;
    public final Provider shadeViewManagerFactoryProvider;
    public final SharedLibraryModule sharedLibraryModule;
    public Provider sharedNotificationContainerInteractorProvider;
    public Provider sharedNotificationContainerViewModelProvider;
    public Provider shortcutKeyDispatcherProvider;
    public Provider shortcutsBesideUdfpsKeyguardBlueprintProvider;
    public final ShutdownUiModuleGoogle shutdownUiModuleGoogle;
    public Provider sideFpsControllerProvider;
    public Provider sideFpsLoggerProvider;
    public Provider sideFpsOverlayViewBinderProvider;
    public Provider sideFpsProgressBarProvider;
    public Provider sideFpsProgressBarViewBinderProvider;
    public Provider sideFpsProgressBarViewModelProvider;
    public Provider sideFpsSensorInteractorProvider;
    public Provider silenceAlertsDisabledProvider;
    public Provider silenceCallProvider;
    public Provider simBouncerInteractorProvider;
    public Provider simBouncerRepositoryImplProvider;
    public Provider singlePointerTouchProcessorProvider;
    public Provider sliceBroadcastRelayHandlerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider smartActionsReceiverProvider;
    public final Provider smartReplyConstantsProvider;
    public Provider smartspaceRepositoryImplProvider;
    public Provider snapshotConfigurationProvider;
    public Provider snoozeAlarmProvider;
    public Provider splitShadeKeyguardBlueprintProvider;
    public final Provider splitShadeStateControllerImplProvider;
    public final Provider statusBarContentInsetsProvider;
    public Provider statusBarHeadsUpChangeListenerProvider;
    public Provider statusBarHideIconsForBouncerManagerProvider;
    public final Provider statusBarIconControllerImplProvider;
    public Provider statusBarIconViewBindingFailureTrackerProvider;
    public Provider statusBarInitializerProvider;
    public final DelegateFactory statusBarKeyguardViewManagerProvider;
    public Provider statusBarLocationPublisherProvider;
    public final Provider statusBarModePerDisplayRepositoryFactoryProvider;
    public final Provider statusBarModeRepositoryImplProvider;
    public Provider statusBarNotificationActivityStarterProvider;
    public Provider statusBarNotificationPresenterProvider;
    public final Provider statusBarPipelineFlagsProvider;
    public Provider statusBarRemoteInputCallbackProvider;
    public Provider statusBarSignalPolicyProvider;
    public final DelegateFactory statusBarStateControllerImplProvider;
    public Provider statusBarTouchableRegionManagerProvider;
    public final Provider statusBarWindowControllerProvider;
    public Provider statusBarWindowStateControllerProvider;
    public Provider stickyKeyDialogFactoryProvider;
    public Provider stickyKeysIndicatorCoordinatorProvider;
    public Provider stickyKeysRepositoryImplProvider;
    public Provider storageNotificationProvider;
    public Provider stylusManagerProvider;
    public Provider stylusUsiPowerStartableProvider;
    public Provider stylusUsiPowerUIProvider;
    public Provider subscriptionManagerSlotIndexResolverProvider;
    public Provider swipeChipbarAwayGestureHandlerProvider;
    public Provider swipeHandlerProvider;
    public final Provider swipeStatusBarAwayGestureHandlerProvider;
    public Provider swipeToDismissInteractorProvider;
    public Provider swipeUpAnywhereGestureHandlerProvider;
    public final Provider swipeUpGestureLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchToManagedProfileForCallActivityProvider;
    public final SysUICoroutinesModule sysUICoroutinesModule;
    public final Provider sysUICutoutProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl = this;
    public Provider sysUIKeyEventHandlerProvider;
    public final SysUIUnfoldModule sysUIUnfoldModule;
    public Provider systemActionsProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider systemClockImplProvider;
    public final Provider systemEventCoordinatorProvider;
    public Provider systemKeyPressProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider systemStatusAnimationSchedulerImplProvider;
    public Provider systemStatusAnimationSchedulerLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider systemUIAuxiliaryDumpServiceProvider;
    public final Provider systemUIConfigDumpableProvider;
    public final Provider systemUIDeviceEntryFaceAuthInteractorProvider;
    public DelegateFactory systemUIDialogManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider systemUISecondaryUserServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider systemUIServiceProvider;
    public Provider sysuiColorExtractorProvider;
    public final Provider tableLogBufferFactoryProvider;
    public Provider takeScreenshotExecutorProvider;
    public Provider takeScreenshotProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider takeScreenshotServiceProvider;
    public Provider tapAgainViewControllerProvider;
    public Provider tapGestureDetectorProvider;
    public final Provider targetSdkResolverProvider;
    public Provider taskSwitchInteractorProvider;
    public Provider taskSwitcherNotificationCoordinatorProvider;
    public Provider taskbarDelegateProvider;
    public Provider telephonyActivityProvider;
    public Provider telephonyInteractorProvider;
    public final Provider telephonyListenerManagerProvider;
    public Provider telephonyRepositoryImplProvider;
    public Provider temporaryViewUiEventLoggerProvider;
    public Provider themeOverlayApplierProvider;
    public Provider themeOverlayControllerGoogleProvider;
    public Provider tileJavaAdapterProvider;
    public DelegateFactory tileServicesProvider;
    public Provider tileSpecSettingsRepositoryProvider;
    public Provider timeoutManagerProvider;
    public Provider toastFactoryProvider;
    public Provider toastUIProvider;
    public Provider toggleFlashlightProvider;
    public Provider touchInsideHandlerProvider;
    public Provider transcriptionControllerProvider;
    public final Provider trustRepositoryImplProvider;
    public final Provider trustRepositoryLoggerProvider;
    public Provider tunablePaddingServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider tunerActivityProvider;
    public final DelegateFactory tunerServiceImplProvider;
    public DelegateFactory udfpsControllerProvider;
    public Provider udfpsHapticsSimulatorProvider;
    public Provider udfpsKeyguardAccessibilityDelegateProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider udfpsLoggerProvider;
    public Provider udfpsOverlayInteractorProvider;
    public Provider udfpsShellProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider uiModeNightTileProvider;
    public Provider uiOffloadThreadProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider unfoldInitializationStartableProvider;
    public Provider unfoldTraceLoggerProvider;
    public Provider unlockedScreenOffAnimationControllerProvider;
    public Provider unpinNotificationsProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider usbAccessoryUriActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider usbConfirmActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider usbDebuggingActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider usbDebuggingSecondaryUserActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider usbPermissionActivityProvider;
    public Provider usbStateProvider;
    public Provider usbStateProvider2;
    public Provider userActivityProvider;
    public Provider userAwareSecureSettingsRepositoryImplProvider;
    public Provider userFetcherProvider;
    public Provider userFileManagerImplProvider;
    public Provider userInfoControllerImplProvider;
    public final Provider userRepositoryImplProvider;
    public Provider userSelectedActionProvider;
    public final Provider userSetupRepositoryImplProvider;
    public Provider userSwitchDialogControllerProvider;
    public Provider userSwitcherControllerProvider;
    public Provider userSwitcherDialogCoordinatorProvider;
    public DelegateFactory userSwitcherInteractorProvider;
    public Provider userSwitcherRepositoryImplProvider;
    public Provider userSwitcherViewModelProvider;
    public Provider verboseMobileViewLoggerProvider;
    public Provider vibratorHelperProvider;
    public Provider videoCameraQuickAffordanceConfigProvider;
    public Provider viewModelProvider;
    public Provider viewUtilProvider;
    public Provider visibilityLocationProviderDelegatorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider visualInterruptionDecisionProviderImplProvider;
    public Provider visualStabilityCoordinatorProvider;
    public final Provider visualStabilityProvider;
    public Provider volumeDialogComponentProvider;
    public Provider volumeDialogControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider volumePanelActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider volumePanelDialogReceiverProvider;
    public Provider volumePanelFactoryProvider;
    public Provider volumeUIProvider;
    public Provider vpnNetworkMonitorProvider;
    public Provider vpnPackageMonitorProvider;
    public Provider vrModeProvider;
    public Provider wMShellProvider;
    public Provider wakeModeProvider;
    public Provider wakefulnessLifecycleProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider walletActivityProvider;
    public Provider walletAutoAddableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider walletContextualLocationsServiceProvider;
    public Provider walletContextualSuggestionsControllerProvider;
    public Provider walletControllerImplProvider;
    public Provider wallpaperControllerProvider;
    public Provider wallpaperRepositoryImplProvider;
    public Provider wifiConstantsProvider;
    public final Provider wifiInputLoggerProvider;
    public Provider wifiInteractorImplProvider;
    public final Provider wifiPickerTrackerFactoryProvider;
    public final Provider wifiRepositorySwitcherProvider;
    public Provider wifiStateWorkerProvider;
    public Provider wifiUiAdapterProvider;
    public Provider wifiViewModelProvider;
    public Provider windowManagerLockscreenVisibilityInteractorProvider;
    public Provider windowManagerLockscreenVisibilityManagerProvider;
    public Provider windowManagerLockscreenVisibilityViewModelProvider;
    public final Provider windowRootViewVisibilityInteractorProvider;
    public final Provider windowRootViewVisibilityRepositoryProvider;
    public Provider wiredChargingRippleControllerProvider;
    public final Provider wirelessChargerCommanderProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider workLockActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider workModeTileProvider;
    public Provider workTileAutoAddableProvider;
    public Provider workTileRestoreProcessorProvider;
    public Provider zenModeControllerImplProvider;

    /* renamed from: -$$Nest$maccessibilityLogger  reason: not valid java name */
    public static AccessibilityLogger m1429$$Nest$maccessibilityLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AccessibilityLogger((UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
    }

    /* renamed from: -$$Nest$maccessibilityRepository  reason: not valid java name */
    public static AccessibilityRepositoryImpl m1430$$Nest$maccessibilityRepository(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AccessibilityRepositoryImpl((AccessibilityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideAccessibilityManagerProvider.get());
    }

    /* renamed from: -$$Nest$mactivatableNotificationViewModel  reason: not valid java name */
    public static ActivatableNotificationViewModelImpl m1431$$Nest$mactivatableNotificationViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ActivatableNotificationViewModelImpl((AccessibilityInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.accessibilityInteractorProvider.get());
    }

    /* renamed from: -$$Nest$mairplaneModeMapper  reason: not valid java name */
    public static AirplaneModeMapper m1432$$Nest$mairplaneModeMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AirplaneModeMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mairplaneModeTileDataInteractor  reason: not valid java name */
    public static AirplaneModeTileDataInteractor m1433$$Nest$mairplaneModeTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AirplaneModeTileDataInteractor((AirplaneModeRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.airplaneModeRepositoryImplProvider.get());
    }

    /* renamed from: -$$Nest$mairplaneModeTileUserActionInteractor  reason: not valid java name */
    public static AirplaneModeTileUserActionInteractor m1434$$Nest$mairplaneModeTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AirplaneModeTileUserActionInteractor((AirplaneModeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.airplaneModeInteractorProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$malarmTileDataInteractor  reason: not valid java name */
    public static AlarmTileDataInteractor m1435$$Nest$malarmTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AlarmTileDataInteractor((NextAlarmController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nextAlarmControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dateFormatUtil());
    }

    /* renamed from: -$$Nest$malarmTileMapper  reason: not valid java name */
    public static AlarmTileMapper m1436$$Nest$malarmTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        return new AlarmTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
    }

    /* renamed from: -$$Nest$malarmTileUserActionInteractor  reason: not valid java name */
    public static AlarmTileUserActionInteractor m1437$$Nest$malarmTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AlarmTileUserActionInteractor((QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$malertingHeaderSectionHeaderController  reason: not valid java name */
    public static SectionHeaderNodeControllerImpl m1438$$Nest$malertingHeaderSectionHeaderController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl = (SectionHeaderNodeControllerImpl) ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesAlertingHeaderSubcomponentProvider.get()).sectionHeaderNodeControllerImplProvider.get();
        Preconditions.checkNotNullFromProvides(sectionHeaderNodeControllerImpl);
        return sectionHeaderNodeControllerImpl;
    }

    /* renamed from: -$$Nest$malignShortcutsToUdfpsSection  reason: not valid java name */
    public static AlignShortcutsToUdfpsSection m1439$$Nest$malignShortcutsToUdfpsSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Resources r1 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl);
        KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardQuickAffordancesCombinedViewModel();
        KeyguardRootViewModel keyguardRootViewModel = (KeyguardRootViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRootViewModelProvider.get();
        return new AlignShortcutsToUdfpsSection(r1, keyguardQuickAffordancesCombinedViewModel, (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (KeyguardIndicationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardIndicationControllerGoogleProvider.get(), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get());
    }

    /* renamed from: -$$Nest$malternateBouncerMessageAreaViewModel  reason: not valid java name */
    public static AlternateBouncerMessageAreaViewModel m1440$$Nest$malternateBouncerMessageAreaViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AlternateBouncerMessageAreaViewModel((BiometricMessageInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.biometricMessageInteractorProvider.get(), (AlternateBouncerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerInteractorProvider.get());
    }

    /* renamed from: -$$Nest$malternateBouncerUdfpsIconViewModel  reason: not valid java name */
    public static AlternateBouncerUdfpsIconViewModel m1441$$Nest$malternateBouncerUdfpsIconViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AlternateBouncerUdfpsIconViewModel(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (ConfigurationInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationInteractorProvider.get(), (DeviceEntryUdfpsInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryUdfpsInteractorProvider.get(), (DeviceEntryBackgroundViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryBackgroundViewModelProvider.get(), (FingerprintPropertyInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fingerprintPropertyInteractorProvider.get(), (UdfpsOverlayInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.udfpsOverlayInteractorProvider.get());
    }

    /* renamed from: -$$Nest$malternateBouncerViewModel  reason: not valid java name */
    public static AlternateBouncerViewModel m1442$$Nest$malternateBouncerViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AlternateBouncerViewModel((StatusBarKeyguardViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarKeyguardViewManagerProvider.get(), (KeyguardTransitionAnimationFlow) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionAnimationFlowProvider.get());
    }

    /* renamed from: -$$Nest$malwaysOnDisplayNotificationIconsInteractor  reason: not valid java name */
    public static AlwaysOnDisplayNotificationIconsInteractor m1443$$Nest$malwaysOnDisplayNotificationIconsInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AlwaysOnDisplayNotificationIconsInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (DeviceEntryInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconsInteractor());
    }

    /* renamed from: -$$Nest$manimationStatusRepositoryImpl  reason: not valid java name */
    public static AnimationStatusRepositoryImpl m1444$$Nest$manimationStatusRepositoryImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AnimationStatusRepositoryImpl((ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgHandlerProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get());
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.android.systemui.keyguard.ui.view.layout.sections.AodBurnInSection, java.lang.Object] */
    /* renamed from: -$$Nest$maodBurnInSection  reason: not valid java name */
    public static AodBurnInSection m1445$$Nest$maodBurnInSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
        KeyguardRootView keyguardRootView = (KeyguardRootView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesKeyguardRootViewProvider.get();
        KeyguardClockViewModel keyguardClockViewModel = (KeyguardClockViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockViewModelProvider.get();
        return new Object();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.android.systemui.keyguard.ui.view.layout.sections.AodNotificationIconsSection] */
    /* renamed from: -$$Nest$maodNotificationIconsSection  reason: not valid java name */
    public static AodNotificationIconsSection m1446$$Nest$maodNotificationIconsSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationState();
        StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker = (StatusBarIconViewBindingFailureTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarIconViewBindingFailureTrackerProvider.get();
        NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel = (NotificationIconContainerAlwaysOnDisplayViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconContainerAlwaysOnDisplayViewModelProvider.get();
        new AlwaysOnDisplayNotificationIconViewStore((NotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifCollectionProvider.get());
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconAreaController();
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemBarUtilsState();
        return new Object();
    }

    /* renamed from: -$$Nest$massistantAttentionCallback  reason: not valid java name */
    public static AssistantAttentionCallback m1447$$Nest$massistantAttentionCallback(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AssistantAttentionCallback((DreamOverlayStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayStateControllerProvider.get());
    }

    /* renamed from: -$$Nest$massistantAttentionCondition  reason: not valid java name */
    public static AssistantAttentionCondition m1448$$Nest$massistantAttentionCondition(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        CoroutineScope coroutineScope = (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get();
        return new AssistantAttentionCondition((AssistManagerGoogle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistManagerGoogleProvider.get());
    }

    /* renamed from: -$$Nest$masyncActivityLauncher  reason: not valid java name */
    public static AsyncActivityLauncher m1449$$Nest$masyncActivityLauncher(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new AsyncActivityLauncher(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (IActivityTaskManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIActivityTaskManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiBackgroundExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mauthDialogPanelInteractionDetector  reason: not valid java name */
    public static AuthDialogPanelInteractionDetector m1450$$Nest$mauthDialogPanelInteractionDetector(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AuthDialogPanelInteractionDetector((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider));
    }

    /* renamed from: -$$Nest$mauthorizedPanelsRepositoryImpl  reason: not valid java name */
    public static AuthorizedPanelsRepositoryImpl m1451$$Nest$mauthorizedPanelsRepositoryImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AuthorizedPanelsRepositoryImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (UserFileManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userFileManagerImplProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.google.android.systemui.autorotate.DataLogger, java.lang.Object] */
    /* renamed from: -$$Nest$mautorotateDataService  reason: not valid java name */
    public static AutorotateDataService m1452$$Nest$mautorotateDataService(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        ? obj = new Object();
        obj.mDataQueue = new LinkedList();
        obj.mLastPullTimeNanos = 0;
        obj.mStatsManager = (StatsManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideStatsManagerProvider.get();
        return new AutorotateDataService(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (SensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesSensorManagerProvider.get(), obj, (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mbatteryEventClient  reason: not valid java name */
    public static BatteryEventClient m1453$$Nest$mbatteryEventClient(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new BatteryEventClient(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
    }

    /* renamed from: -$$Nest$mbatteryStateNotifier  reason: not valid java name */
    public static BatteryStateNotifier m1454$$Nest$mbatteryStateNotifier(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new BatteryStateNotifier((BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get(), (NotificationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideNotificationManagerProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundDelayableExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
    }

    /* renamed from: -$$Nest$mbluetoothDialogDelegate  reason: not valid java name */
    public static BluetoothDialogDelegate m1455$$Nest$mbluetoothDialogDelegate(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new BluetoothDialogDelegate((SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get());
    }

    /* renamed from: -$$Nest$mbouncerToGoneFlows  reason: not valid java name */
    public static BouncerToGoneFlows m1456$$Nest$mbouncerToGoneFlows(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new BouncerToGoneFlows((SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (PrimaryBouncerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerInteractorProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardDismissActionInteractorProvider), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get(), (KeyguardTransitionAnimationFlow) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionAnimationFlowProvider.get());
    }

    /* renamed from: -$$Nest$mbrightnessSliderControllerFactory  reason: not valid java name */
    public static BrightnessSliderController.Factory m1457$$Nest$mbrightnessSliderControllerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new BrightnessSliderController.Factory((FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
    }

    /* renamed from: -$$Nest$mbroadcastDispatcherLogger  reason: not valid java name */
    public static BroadcastDispatcherLogger m1458$$Nest$mbroadcastDispatcherLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new BroadcastDispatcherLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBroadcastDispatcherLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mcaptioningInteractor  reason: not valid java name */
    public static CaptioningInteractor m1459$$Nest$mcaptioningInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new CaptioningInteractor(new CaptioningRepositoryImpl((CaptioningManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideCaptioningManagerProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get()));
    }

    /* renamed from: -$$Nest$mchargingStatusProvider  reason: not valid java name */
    public static ChargingStatusProvider m1460$$Nest$mchargingStatusProvider(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        Resources resources = context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        return new ChargingStatusProvider(context, resources, (IBatteryStats) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIBatteryStatsProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get());
    }

    /* renamed from: -$$Nest$mclipboardImageLoader  reason: not valid java name */
    public static ClipboardImageLoader m1461$$Nest$mclipboardImageLoader(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new ClipboardImageLoader(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
    }

    /* renamed from: -$$Nest$mclipboardOverlayUtils  reason: not valid java name */
    public static ClipboardOverlayUtils m1462$$Nest$mclipboardOverlayUtils(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ClipboardOverlayUtils((TextClassificationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideTextClassificationManagerProvider.get());
    }

    /* renamed from: -$$Nest$mclipboardOverlayView  reason: not valid java name */
    public static ClipboardOverlayView m1463$$Nest$mclipboardOverlayView(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        ClipboardOverlayView clipboardOverlayView = (ClipboardOverlayView) LayoutInflater.from(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overlayWindowContextContext()).inflate(2131558521, (ViewGroup) null);
        Preconditions.checkNotNullFromProvides(clipboardOverlayView);
        return clipboardOverlayView;
    }

    /* renamed from: -$$Nest$mclipboardOverlayWindow  reason: not valid java name */
    public static ClipboardOverlayWindow m1464$$Nest$mclipboardOverlayWindow(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ClipboardOverlayWindow(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overlayWindowContextContext());
    }

    /* renamed from: -$$Nest$mclipboardToast  reason: not valid java name */
    public static ClipboardToast m1465$$Nest$mclipboardToast(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ClipboardToast(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context);
    }

    /* renamed from: -$$Nest$mclipboardTransitionExecutor  reason: not valid java name */
    public static ClipboardTransitionExecutor m1466$$Nest$mclipboardTransitionExecutor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ClipboardTransitionExecutor(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (DisplayTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDisplayTrackerProvider.get());
    }

    /* renamed from: -$$Nest$mclockEventController  reason: not valid java name */
    public static ClockEventController m1467$$Nest$mclockEventController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        KeyguardInteractor keyguardInteractor = (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get();
        KeyguardTransitionInteractor keyguardTransitionInteractor = (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get();
        BroadcastDispatcher broadcastDispatcher = (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get();
        BatteryController batteryController = (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get();
        KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get();
        ConfigurationController configurationController = (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        Display display = (Display) Optional.empty().orElse((Object) null);
        if (!(display == null || context.getDisplayId() == display.getDisplayId())) {
            context = context.createDisplayContext(display);
            Intrinsics.checkNotNull(context);
        }
        return new ClockEventController(keyguardInteractor, keyguardTransitionInteractor, broadcastDispatcher, batteryController, keyguardUpdateMonitor, configurationController, context.getResources(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.clockMessageBuffers(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (ZenModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.zenModeControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mclockSection  reason: not valid java name */
    public static ClockSection m1468$$Nest$mclockSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ClockSection((KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockInteractorProvider.get(), (KeyguardClockViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockViewModelProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get(), (KeyguardSmartspaceViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardSmartspaceViewModelProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBlueprintInteractorProvider));
    }

    /* renamed from: -$$Nest$mcollapsedStatusBarFragmentLogger  reason: not valid java name */
    public static CollapsedStatusBarFragmentLogger m1469$$Nest$mcollapsedStatusBarFragmentLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new CollapsedStatusBarFragmentLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCollapsedSbFragmentLogBufferProvider.get(), (DisableFlagsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disableFlagsLoggerProvider.get());
    }

    /* renamed from: -$$Nest$mcolorCorrectionTileDataInteractor  reason: not valid java name */
    public static ColorCorrectionTileDataInteractor m1470$$Nest$mcolorCorrectionTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorCorrectionTileDataInteractor((ColorCorrectionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorCorrectionRepositoryImplProvider.get());
    }

    /* renamed from: -$$Nest$mcolorCorrectionTileMapper  reason: not valid java name */
    public static ColorCorrectionTileMapper m1471$$Nest$mcolorCorrectionTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorCorrectionTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mcolorCorrectionUserActionInteractor  reason: not valid java name */
    public static ColorCorrectionUserActionInteractor m1472$$Nest$mcolorCorrectionUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorCorrectionUserActionInteractor((ColorCorrectionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorCorrectionRepositoryImplProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$mcolorInversionTileDataInteractor  reason: not valid java name */
    public static ColorInversionTileDataInteractor m1473$$Nest$mcolorInversionTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorInversionTileDataInteractor((ColorInversionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorInversionRepositoryImplProvider.get());
    }

    /* renamed from: -$$Nest$mcolorInversionTileMapper  reason: not valid java name */
    public static ColorInversionTileMapper m1474$$Nest$mcolorInversionTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorInversionTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mcolorInversionUserActionInteractor  reason: not valid java name */
    public static ColorInversionUserActionInteractor m1475$$Nest$mcolorInversionUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorInversionUserActionInteractor((ColorInversionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorInversionRepositoryImplProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$mcommunalTutorialIndicatorSection  reason: not valid java name */
    public static CommunalTutorialIndicatorSection m1476$$Nest$mcommunalTutorialIndicatorSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new CommunalTutorialIndicatorSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), new CommunalTutorialIndicatorViewModel((CommunalTutorialInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalTutorialInteractorProvider.get(), (KeyguardBottomAreaInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBottomAreaInteractorProvider.get()), (CommunalInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalInteractorProvider.get());
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.android.systemui.flags.SystemExitRestarter, java.lang.Object] */
    /* renamed from: -$$Nest$mconditionalRestarter  reason: not valid java name */
    public static ConditionalRestarter m1477$$Nest$mconditionalRestarter(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Set set;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        IStatusBarService iStatusBarService = (IStatusBarService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIStatusBarServiceProvider.get();
        ? obj = new Object();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new ScreenIdleCondition(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerInteractorProvider)));
        arrayList.add(new PluggedInCondition(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider)));
        arrayList.add(new NotOccludedCondition(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider)));
        if (arrayList.isEmpty()) {
            set = Collections.emptySet();
        } else if (arrayList.size() == 1) {
            set = Collections.singleton(arrayList.get(0));
        } else {
            set = Collections.unmodifiableSet(new HashSet(arrayList));
        }
        return new ConditionalRestarter(obj, set, (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get());
    }

    /* renamed from: -$$Nest$mcontrolsDialogsFactory  reason: not valid java name */
    public static ControlsDialogsFactory m1478$$Nest$mcontrolsDialogsFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ControlsDialogsFactory((SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get());
    }

    /* renamed from: -$$Nest$mconversationNotificationProcessor  reason: not valid java name */
    public static ConversationNotificationProcessor m1479$$Nest$mconversationNotificationProcessor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ConversationNotificationProcessor((LauncherApps) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideLauncherAppsProvider.get(), (ConversationNotificationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.conversationNotificationManagerProvider.get());
    }

    /* renamed from: -$$Nest$mdataSaverTileDataInteractor  reason: not valid java name */
    public static DataSaverTileDataInteractor m1480$$Nest$mdataSaverTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DataSaverTileDataInteractor((DataSaverControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDataSaverControllerProvider.get());
    }

    /* renamed from: -$$Nest$mdataSaverTileMapper  reason: not valid java name */
    public static DataSaverTileMapper m1481$$Nest$mdataSaverTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DataSaverTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mdataSaverTileUserActionInteractor  reason: not valid java name */
    public static DataSaverTileUserActionInteractor m1482$$Nest$mdataSaverTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new DataSaverTileUserActionInteractor((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.mainCoroutineContextProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (DataSaverControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDataSaverControllerProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get(), (DialogTransitionAnimator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDialogTransitionAnimatorProvider.get(), (SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get(), (UserFileManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userFileManagerImplProvider.get());
    }

    /* renamed from: -$$Nest$mddmHandleMotionTool  reason: not valid java name */
    public static DdmHandleMotionTool m1483$$Nest$mddmHandleMotionTool(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        MotionToolManager motionToolManager;
        DdmHandleMotionTool ddmHandleMotionTool;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        WindowManagerGlobal instance = WindowManagerGlobal.getInstance();
        synchronized (MotionToolManager.Companion) {
            motionToolManager = MotionToolManager.INSTANCE;
            if (motionToolManager == null) {
                motionToolManager = new MotionToolManager(instance);
                MotionToolManager.INSTANCE = motionToolManager;
            }
        }
        synchronized (DdmHandleMotionTool.Companion) {
            ddmHandleMotionTool = DdmHandleMotionTool.INSTANCE;
            if (ddmHandleMotionTool == null) {
                ddmHandleMotionTool = new DdmHandleMotionTool(motionToolManager);
                DdmHandleMotionTool.INSTANCE = ddmHandleMotionTool;
            }
        }
        return ddmHandleMotionTool;
    }

    /* renamed from: -$$Nest$mdefaultAmbientIndicationAreaSection  reason: not valid java name */
    public static DefaultAmbientIndicationAreaSection m1484$$Nest$mdefaultAmbientIndicationAreaSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DefaultAmbientIndicationAreaSection((KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), new KeyguardAmbientIndicationViewModel((AmbientIndicationInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.ambientIndicationInteractorProvider.get(), (BurnInInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.burnInInteractorProvider.get()), (AodAlphaViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodAlphaViewModelProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationPanelViewControllerProvider), (PowerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerInteractorProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider13.get());
    }

    /* renamed from: -$$Nest$mdefaultDeviceEntrySection  reason: not valid java name */
    public static DefaultDeviceEntrySection m1485$$Nest$mdefaultDeviceEntrySection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Lazy lazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockIconViewControllerProvider);
        Lazy lazy2 = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryIconViewModelProvider);
        Lazy lazy3 = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryForegroundViewModelProvider);
        Lazy lazy4 = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryBackgroundViewModelProvider);
        Lazy lazy5 = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider);
        Lazy lazy6 = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider);
        return new DefaultDeviceEntrySection((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (AuthController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.authControllerProvider.get(), (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (NotificationPanelView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNotificationPanelViewProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), lazy, lazy2, lazy3, lazy4, lazy5, lazy6);
    }

    /* renamed from: -$$Nest$mdefaultIndicationAreaSection  reason: not valid java name */
    public static DefaultIndicationAreaSection m1486$$Nest$mdefaultIndicationAreaSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DefaultIndicationAreaSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardIndicationAreaViewModel(), (KeyguardIndicationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardIndicationControllerGoogleProvider.get());
    }

    /* renamed from: -$$Nest$mdefaultNotificationStackScrollLayoutSection  reason: not valid java name */
    public static DefaultNotificationStackScrollLayoutSection m1487$$Nest$mdefaultNotificationStackScrollLayoutSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.largeScreenHeaderHelperProvider);
        return new NotificationStackScrollLayoutSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get(), (NotificationPanelView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNotificationPanelViewProvider.get(), (SharedNotificationContainer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesSharedNotificationContainerProvider.get(), (SharedNotificationContainerViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sharedNotificationContainerViewModelProvider.get(), (NotificationStackAppearanceViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationStackAppearanceViewModelProvider.get(), (AmbientState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.ambientStateProvider.get(), (NotificationStackScrollLayoutController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationStackScrollLayoutControllerProvider.get(), (NotificationStackSizeCalculator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationStackSizeCalculatorProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.mainDispatcherProvider.get());
    }

    /* renamed from: -$$Nest$mdefaultSettingsPopupMenuSection  reason: not valid java name */
    public static DefaultSettingsPopupMenuSection m1488$$Nest$mdefaultSettingsPopupMenuSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DefaultSettingsPopupMenuSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), new KeyguardSettingsMenuViewModel((KeyguardLongPressInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardLongPressInteractorProvider.get()), (KeyguardLongPressViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardLongPressViewModelProvider.get(), (KeyguardRootViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRootViewModelProvider.get(), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get());
    }

    /* renamed from: -$$Nest$mdefaultShortcutsSection  reason: not valid java name */
    public static DefaultShortcutsSection m1489$$Nest$mdefaultShortcutsSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Resources r1 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl);
        KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardQuickAffordancesCombinedViewModel();
        KeyguardRootViewModel keyguardRootViewModel = (KeyguardRootViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRootViewModelProvider.get();
        return new DefaultShortcutsSection(r1, keyguardQuickAffordancesCombinedViewModel, (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (KeyguardIndicationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardIndicationControllerGoogleProvider.get(), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get());
    }

    /* renamed from: -$$Nest$mdefaultStatusBarSection  reason: not valid java name */
    public static DefaultStatusBarSection m1490$$Nest$mdefaultStatusBarSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new DefaultStatusBarSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (NotificationPanelView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNotificationPanelViewProvider.get(), new DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleSysUIComponentImpl));
    }

    /* renamed from: -$$Nest$mdefaultStatusViewSection  reason: not valid java name */
    public static DefaultStatusViewSection m1491$$Nest$mdefaultStatusViewSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new DefaultStatusViewSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (NotificationPanelView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNotificationPanelViewProvider.get(), new DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleSysUIComponentImpl), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardViewConfiguratorProvider), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationPanelViewControllerProvider), (KeyguardMediaController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardMediaControllerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mdefaultUdfpsAccessibilityOverlaySection  reason: not valid java name */
    public static DefaultUdfpsAccessibilityOverlaySection m1492$$Nest$mdefaultUdfpsAccessibilityOverlaySection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DefaultUdfpsAccessibilityOverlaySection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, new DeviceEntryUdfpsAccessibilityOverlayViewModel((UdfpsOverlayInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.udfpsOverlayInteractorProvider.get(), (AccessibilityInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.accessibilityInteractorProvider.get(), (DeviceEntryIconViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryIconViewModelProvider.get(), (DeviceEntryForegroundViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryForegroundViewModelProvider.get()));
    }

    /* renamed from: -$$Nest$mdefaultWidgetPopulation  reason: not valid java name */
    public static DefaultWidgetPopulation m1493$$Nest$mdefaultWidgetPopulation(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        DelegateFactory delegateFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommunalWidgetDaoProvider;
        Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        return new DefaultWidgetPopulation((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CommunalWidgetHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommunalWidgetHostProvider.get(), delegateFactory, resources.getStringArray(2130903092), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommunalLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mdemoMobileConnectionsRepository  reason: not valid java name */
    public static DemoMobileConnectionsRepository m1494$$Nest$mdemoMobileConnectionsRepository(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new DemoMobileConnectionsRepository((DemoModeMobileConnectionDataSource) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.demoModeMobileConnectionDataSourceProvider.get(), (DemoModeWifiDataSource) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.demoModeWifiDataSourceProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (TableLogBufferFactory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tableLogBufferFactoryProvider.get());
    }

    /* renamed from: -$$Nest$mdemoWifiRepository  reason: not valid java name */
    public static DemoWifiRepository m1495$$Nest$mdemoWifiRepository(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DemoWifiRepository((DemoModeWifiDataSource) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.demoModeWifiDataSourceProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
    }

    /* renamed from: -$$Nest$mdeviceBasedSatelliteViewModel  reason: not valid java name */
    public static DeviceBasedSatelliteViewModel m1496$$Nest$mdeviceBasedSatelliteViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        return new DeviceBasedSatelliteViewModel((DeviceBasedSatelliteInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceBasedSatelliteInteractorProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (AirplaneModeRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.airplaneModeRepositoryImplProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideOemSatelliteInputLogProvider.get());
    }

    /* renamed from: -$$Nest$mdeviceProvisioningRepositoryImpl  reason: not valid java name */
    public static DeviceProvisioningRepositoryImpl m1497$$Nest$mdeviceProvisioningRepositoryImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DeviceProvisioningRepositoryImpl((DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get());
    }

    /* renamed from: -$$Nest$mdeviceStateHelper  reason: not valid java name */
    public static KeyguardDisplayManager.DeviceStateHelper m1498$$Nest$mdeviceStateHelper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new KeyguardDisplayManager.DeviceStateHelper(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DeviceStateManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDeviceStateManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mdeviceStateRepositoryImpl  reason: not valid java name */
    public static DeviceStateRepositoryImpl m1499$$Nest$mdeviceStateRepositoryImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new DeviceStateRepositoryImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DeviceStateManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDeviceStateManagerProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgApplicationScopeProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mdeviceStateRotationLockSettingControllerLogger  reason: not valid java name */
    public static DeviceStateRotationLockSettingControllerLogger m1500$$Nest$mdeviceStateRotationLockSettingControllerLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DeviceStateRotationLockSettingControllerLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceStateAutoRotationLogBufferProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.android.systemui.classifier.SingleTapClassifier, com.android.systemui.classifier.TapClassifier] */
    /* renamed from: -$$Nest$mdoubleTapClassifier  reason: not valid java name */
    public static DoubleTapClassifier m1501$$Nest$mdoubleTapClassifier(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new DoubleTapClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), new TapClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (float) ((ViewConfiguration) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideViewConfigurationProvider.get()).getScaledTouchSlop()), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getDimension(2131165836));
    }

    /* renamed from: -$$Nest$mdreamCondition  reason: not valid java name */
    public static DreamCondition m1502$$Nest$mdreamCondition(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        CoroutineScope coroutineScope = (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get();
        Object systemService = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context.getSystemService(DreamManager.class);
        if (systemService != null) {
            return new DreamCondition((DreamManager) systemService, (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get());
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    /* renamed from: -$$Nest$mdreamManager  reason: not valid java name */
    public static DreamManager m1503$$Nest$mdreamManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Object systemService = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context.getSystemService(DreamManager.class);
        if (systemService != null) {
            return (DreamManager) systemService;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    /* renamed from: -$$Nest$mdreamStatusBarStateCallback  reason: not valid java name */
    public static DreamStatusBarStateCallback m1504$$Nest$mdreamStatusBarStateCallback(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DreamStatusBarStateCallback((SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mdumpHandler  reason: not valid java name */
    public static DumpHandler m1505$$Nest$mdumpHandler(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DumpHandler((DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (LogBufferEulogizer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.logBufferEulogizerProvider.get(), (SystemUIConfigDumpable) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUIConfigDumpableProvider.get());
    }

    /* renamed from: -$$Nest$medgeBackGestureHandlerFactory  reason: not valid java name */
    public static EdgeBackGestureHandler.Factory m1506$$Nest$medgeBackGestureHandlerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.sysUIGoogleGlobalRootComponentImpl;
        BackPanelController.Factory factory = new BackPanelController.Factory((WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), (ViewConfiguration) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideViewConfigurationProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.vibratorHelperProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.configurationControllerImplProvider.get(), (LatencyTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideLatencyTrackerProvider.get(), (InteractionJankMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInteractionJankMonitorProvider.get());
        return new EdgeBackGestureHandler.Factory((OverviewProxyService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.overviewProxyServiceProvider.get(), (SysUiState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideSysUiStateProvider.get(), (PluginManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesPluginManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideBackgroundExecutorProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideUserTrackerProvider.get(), (NavigationModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.navigationModeControllerProvider.get(), factory, (ViewConfiguration) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideViewConfigurationProvider.get(), (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), (IWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIWindowManagerProvider.get(), (InputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInputManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.setPip, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.setDesktopMode, (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.falsingManagerProxyProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.navigationBarEdgePanelProvider, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.providsBackGestureTfClassifierProvider, (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.featureFlagsClassicReleaseProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.lightBarControllerProvider);
    }

    /* renamed from: -$$Nest$meditWidgetsActivityStarterImpl  reason: not valid java name */
    public static EditWidgetsActivityStarterImpl m1507$$Nest$meditWidgetsActivityStarterImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new EditWidgetsActivityStarterImpl((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get());
    }

    /* renamed from: -$$Nest$memergencyDialerIntentFactory  reason: not valid java name */
    public static BouncerInteractorModule$emergencyDialerIntentFactory$1 m1508$$Nest$memergencyDialerIntentFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new BouncerInteractorModule$emergencyDialerIntentFactory$1((TelecomManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideTelecomManagerProvider.get());
    }

    /* renamed from: -$$Nest$mexitSessionDialogFactory  reason: not valid java name */
    public static GuestResetOrExitSessionReceiver.ExitSessionDialogFactory m1509$$Nest$mexitSessionDialogFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new GuestResetOrExitSessionReceiver.ExitSessionDialogFactory((SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$8) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider10.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl));
    }

    /* renamed from: -$$Nest$mexpansionStateLogger  reason: not valid java name */
    public static NotificationLogger.ExpansionStateLogger m1510$$Nest$mexpansionStateLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationLogger.ExpansionStateLogger((Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiBackgroundExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mflashlightMapper  reason: not valid java name */
    public static FlashlightMapper m1511$$Nest$mflashlightMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new FlashlightMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mflashlightTileDataInteractor  reason: not valid java name */
    public static FlashlightTileDataInteractor m1512$$Nest$mflashlightTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new FlashlightTileDataInteractor((FlashlightController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.flashlightControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mflashlightTileUserActionInteractor  reason: not valid java name */
    public static FlashlightTileUserActionInteractor m1513$$Nest$mflashlightTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new FlashlightTileUserActionInteractor((FlashlightController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.flashlightControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mfontScalingTileMapper  reason: not valid java name */
    public static FontScalingTileMapper m1514$$Nest$mfontScalingTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new FontScalingTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mfontScalingTileUserActionInteractor  reason: not valid java name */
    public static FontScalingTileUserActionInteractor m1515$$Nest$mfontScalingTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new FontScalingTileUserActionInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.mainCoroutineContextProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fontScalingDialogDelegateProvider, (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (DialogTransitionAnimator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDialogTransitionAnimatorProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get());
    }

    /* renamed from: -$$Nest$mframeworkDataSource  reason: not valid java name */
    public static FrameworkDataSource m1516$$Nest$mframeworkDataSource(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new FrameworkDataSource(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (PowerManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePowerManagerProvider.get());
    }

    /* JADX WARNING: type inference failed for: r12v0, types: [com.android.systemui.statusbar.pipeline.mobile.util.MobileMappingsProxyImpl, java.lang.Object] */
    /* renamed from: -$$Nest$mfullMobileConnectionRepositoryFactory  reason: not valid java name */
    public static FullMobileConnectionRepository.Factory m1517$$Nest$mfullMobileConnectionRepositoryFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        MobileConnectionRepositoryImpl.Factory factory = r5;
        MobileConnectionRepositoryImpl.Factory factory2 = new MobileConnectionRepositoryImpl.Factory(context, (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.broadcastDispatcherProvider.get(), (ConnectivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideConnectivityManagagerProvider.get(), (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideTelephonyManagerProvider.get(), (MobileInputLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.mobileInputLoggerProvider.get(), (CarrierConfigRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.carrierConfigRepositoryProvider.get(), new Object(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.featureFlagsClassicReleaseProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.bgDispatcherProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
        return new FullMobileConnectionRepository.Factory((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (TableLogBufferFactory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.tableLogBufferFactoryProvider.get(), factory, (CarrierMergedConnectionRepository.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.factoryProvider3.get());
    }

    /* renamed from: -$$Nest$mfunctionOfIntegerAndSecurityMode  reason: not valid java name */
    public static AuthenticationModule$getSecurityMode$1 m1518$$Nest$mfunctionOfIntegerAndSecurityMode(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AuthenticationModule$getSecurityMode$1((KeyguardSecurityModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardSecurityModelProvider.get());
    }

    /* renamed from: -$$Nest$mglanceableHubContainerController  reason: not valid java name */
    public static GlanceableHubContainerController m1519$$Nest$mglanceableHubContainerController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new GlanceableHubContainerController((CommunalInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalInteractorProvider.get(), (CommunalViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalViewModelProvider.get(), (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get(), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get(), (PowerManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.providePowerManagerProvider.get());
    }

    /* renamed from: -$$Nest$mglanceableHubTransitions  reason: not valid java name */
    public static GlanceableHubTransitions m1520$$Nest$mglanceableHubTransitions(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new GlanceableHubTransitions((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get(), (KeyguardTransitionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionRepositoryImplProvider.get(), (CommunalInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalInteractorProvider.get());
    }

    /* renamed from: -$$Nest$mgroupCoalescer  reason: not valid java name */
    public static GroupCoalescer m1522$$Nest$mgroupCoalescer(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new GroupCoalescer((DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), new GroupCoalescerLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get()));
    }

    /* renamed from: -$$Nest$mguestResetOrExitSessionReceiver  reason: not valid java name */
    public static GuestResetOrExitSessionReceiver m1523$$Nest$mguestResetOrExitSessionReceiver(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new GuestResetOrExitSessionReceiver((UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), new GuestResetOrExitSessionReceiver.ExitSessionDialogFactory((SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$7) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider9.get()), new GuestResetOrExitSessionReceiver.ExitSessionDialogFactory((SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$8) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider10.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl)));
    }

    /* renamed from: -$$Nest$mguestSessionNotification  reason: not valid java name */
    public static GuestSessionNotification m1524$$Nest$mguestSessionNotification(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new GuestSessionNotification(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (NotificationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideNotificationManagerProvider.get());
    }

    /* renamed from: -$$Nest$mheaderPrivacyIconsController  reason: not valid java name */
    public static HeaderPrivacyIconsController m1525$$Nest$mheaderPrivacyIconsController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.sysUIGoogleGlobalRootComponentImpl;
        return new HeaderPrivacyIconsController((PrivacyItemController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.privacyItemControllerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (OngoingPrivacyChip) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.providesOngoingPrivacyChipProvider.get(), (PrivacyDialogController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.privacyDialogControllerProvider.get(), (PrivacyDialogControllerV2) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.privacyDialogControllerV2Provider.get(), new PrivacyLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.providePrivacyLogBufferProvider.get()), (StatusIconContainer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.providesStatusIconContainerProvider.get(), (PermissionManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePermissionManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideBackgroundExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.activityStarterImplProvider.get(), (AppOpsController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.appOpsControllerImplProvider.get(), (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.broadcastDispatcherProvider.get(), (SafetyCenterManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideSafetyCenterManagerProvider.get(), (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideDeviceProvisionedControllerProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.featureFlagsClassicReleaseProvider.get());
    }

    /* renamed from: -$$Nest$mheadsUpManagerLogger  reason: not valid java name */
    public static HeadsUpManagerLogger m1526$$Nest$mheadsUpManagerLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new HeadsUpManagerLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationHeadsUpLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mheadsUpViewBinderLogger  reason: not valid java name */
    public static HeadsUpViewBinderLogger m1527$$Nest$mheadsUpViewBinderLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new HeadsUpViewBinderLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationHeadsUpLogBufferProvider.get());
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.google.hardware.pixel.display.IDisplay$Stub$Proxy, java.lang.Object] */
    /* renamed from: -$$Nest$miDisplay  reason: not valid java name */
    public static IDisplay m1528$$Nest$miDisplay(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        IDisplay iDisplay;
        IBinder waitForDeclaredService = ServiceManager.waitForDeclaredService(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getString(2131952260));
        if (waitForDeclaredService == null) {
            return null;
        }
        int i = IDisplay.Stub.$r8$clinit;
        IInterface queryLocalInterface = waitForDeclaredService.queryLocalInterface(IDisplay.DESCRIPTOR);
        if (queryLocalInterface == null || !(queryLocalInterface instanceof IDisplay)) {
            ? obj = new Object();
            obj.mRemote = waitForDeclaredService;
            iDisplay = obj;
        } else {
            iDisplay = (IDisplay) queryLocalInterface;
        }
        return iDisplay;
    }

    /* renamed from: -$$Nest$mincomingHeaderSectionHeaderController  reason: not valid java name */
    public static SectionHeaderNodeControllerImpl m1529$$Nest$mincomingHeaderSectionHeaderController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl = (SectionHeaderNodeControllerImpl) ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesIncomingHeaderSubcomponentProvider.get()).sectionHeaderNodeControllerImplProvider.get();
        Preconditions.checkNotNullFromProvides(sectionHeaderNodeControllerImpl);
        return sectionHeaderNodeControllerImpl;
    }

    /* renamed from: -$$Nest$minjectDependency  reason: not valid java name */
    public static void m1530$$Nest$minjectDependency(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, Dependency dependency) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        dependency.mDumpManager = (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get();
        dependency.mBroadcastDispatcher = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider);
        dependency.mBluetoothController = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bluetoothControllerImplProvider);
        dependency.mFlashlightController = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.flashlightControllerImplProvider);
        dependency.mKeyguardUpdateMonitor = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider);
        dependency.mDeviceProvisionedController = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider);
        dependency.mPluginManager = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesPluginManagerProvider);
        dependency.mAssistManager = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistManagerGoogleProvider);
        dependency.mTunerService = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider);
        dependency.mDarkIconDispatcher = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.darkIconDispatcherImplProvider);
        dependency.mFragmentService = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fragmentServiceProvider);
        dependency.mVolumeDialogController = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumeDialogControllerImplProvider);
        dependency.mMetricsLogger = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider);
        dependency.mTunablePaddingService = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunablePaddingServiceProvider);
        dependency.mUiOffloadThread = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiOffloadThreadProvider);
        dependency.mLightBarController = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lightBarControllerProvider);
        dependency.mOverviewProxyService = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overviewProxyServiceProvider);
        dependency.mNavBarModeController = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationModeControllerProvider);
        dependency.mAccessibilityButtonModeObserver = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.accessibilityButtonModeObserverProvider);
        dependency.mAccessibilityButtonListController = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.accessibilityButtonTargetsObserverProvider);
        dependency.mIStatusBarService = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIStatusBarServiceProvider);
        dependency.mNotificationRemoteInputManagerCallback = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarRemoteInputCallbackProvider);
        dependency.mNavigationBarController = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationBarControllerImplProvider);
        dependency.mStatusBarStateController = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider);
        dependency.mNotificationMediaManager = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationMediaManagerProvider);
        dependency.mBgLooper = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgLooperProvider);
        dependency.mMainHandler = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider);
        dependency.mTimeTickHandler = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideTimeTickHandlerProvider);
        dependency.mSysUiStateFlagsContainer = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSysUiStateProvider);
        dependency.mCommandQueue = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider);
        dependency.mUiEventLogger = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider);
        dependency.mContentInsetsProviderLazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarContentInsetsProvider);
        dependency.mFeatureFlagsLazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider);
        dependency.mNotificationSectionsManagerLazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationSectionsManagerProvider);
        dependency.mScreenOffAnimationController = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenOffAnimationControllerProvider);
        dependency.mAmbientStateLazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.ambientStateProvider);
        dependency.mGroupMembershipManagerLazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.groupMembershipManagerImplProvider);
        dependency.mGroupExpansionManagerLazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.groupExpansionManagerImplProvider);
        dependency.mSystemUIDialogManagerLazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUIDialogManagerProvider);
        dependency.mDialogTransitionAnimatorLazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDialogTransitionAnimatorProvider);
        dependency.mUserTrackerLazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider);
        dependency.mStatusBarWindowControllerLazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarWindowControllerProvider);
    }

    /* renamed from: -$$Nest$minternetTile  reason: not valid java name */
    public static InternetTile m1531$$Nest$minternetTile(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new InternetTile((QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get(), (Looper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgLooperProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSLogger(), (NetworkController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.networkControllerImplProvider.get(), (AccessPointController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAccessPointControllerImplProvider.get(), (InternetDialogManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.internetDialogManagerProvider.get());
    }

    /* renamed from: -$$Nest$minternetTileNewImpl  reason: not valid java name */
    public static InternetTileNewImpl m1532$$Nest$minternetTileNewImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new InternetTileNewImpl((QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get(), (Looper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgLooperProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSLogger(), (InternetTileViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.internetTileViewModelProvider.get(), (InternetDialogManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.internetDialogManagerProvider.get(), (AccessPointController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAccessPointControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardBlueprintCommandListener  reason: not valid java name */
    public static KeyguardBlueprintCommandListener m1533$$Nest$mkeyguardBlueprintCommandListener(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardBlueprintCommandListener((CommandRegistry) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.commandRegistryProvider.get(), (KeyguardBlueprintRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBlueprintRepositoryProvider.get(), (KeyguardBlueprintInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBlueprintInteractorProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardBlueprintViewModel  reason: not valid java name */
    public static KeyguardBlueprintViewModel m1534$$Nest$mkeyguardBlueprintViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardBlueprintViewModel((KeyguardBlueprintInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBlueprintInteractorProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardBottomAreaView  reason: not valid java name */
    public static KeyguardBottomAreaView m1535$$Nest$mkeyguardBottomAreaView(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return (KeyguardBottomAreaView) ((LayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.providerLayoutInflaterProvider.get()).inflate(2131558647, (NotificationPanelView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNotificationPanelViewProvider.get(), false);
    }

    /* renamed from: -$$Nest$mkeyguardBouncerViewModel  reason: not valid java name */
    public static KeyguardBouncerViewModel m1536$$Nest$mkeyguardBouncerViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardBouncerViewModel((BouncerViewImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bouncerViewImplProvider.get(), (PrimaryBouncerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerInteractorProvider.get());
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.android.systemui.statusbar.phone.KeyguardClockPositionAlgorithm] */
    /* renamed from: -$$Nest$mkeyguardClockPositionAlgorithm  reason: not valid java name */
    public static KeyguardClockPositionAlgorithm m1537$$Nest$mkeyguardClockPositionAlgorithm(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        ? obj = new Object();
        obj.mCutoutTopInset = 0;
        new Logger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardClockLogProvider.get(), "KeyguardClockPositionAlgorithm");
        return obj;
    }

    /* renamed from: -$$Nest$mkeyguardLogger  reason: not valid java name */
    public static KeyguardLogger m1539$$Nest$mkeyguardLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardMediaControllerLogger  reason: not valid java name */
    public static KeyguardMediaControllerLogger m1540$$Nest$mkeyguardMediaControllerLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardMediaControllerLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardMediaControllerLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardMessageAreaControllerFactory  reason: not valid java name */
    public static KeyguardMessageAreaController.Factory m1541$$Nest$mkeyguardMessageAreaControllerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardMessageAreaController.Factory((KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardQuickAffordanceProviderClientFactoryImpl  reason: not valid java name */
    public static KeyguardQuickAffordanceProviderClientFactoryImpl m1542$$Nest$mkeyguardQuickAffordanceProviderClientFactoryImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardQuickAffordanceProviderClientFactoryImpl((UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardTransitionAnimationLogger  reason: not valid java name */
    public static KeyguardTransitionAnimationLogger m1543$$Nest$mkeyguardTransitionAnimationLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardTransitionAnimationLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardTransitionAnimationLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mlSShadeTransitionLogger  reason: not valid java name */
    public static LSShadeTransitionLogger m1544$$Nest$mlSShadeTransitionLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new LSShadeTransitionLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLSShadeTransitionControllerBufferProvider.get(), (LockscreenGestureLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenGestureLoggerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m975$$Nest$mdisplayMetrics(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl));
    }

    /* renamed from: -$$Nest$mlocalMediaRepositoryFactoryImpl  reason: not valid java name */
    public static LocalMediaRepositoryFactoryImpl m1545$$Nest$mlocalMediaRepositoryFactoryImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        AudioManagerEventsReceiverImpl audioManagerEventsReceiver = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.audioManagerEventsReceiver();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new LocalMediaRepositoryFactoryImpl(audioManagerEventsReceiver, (MediaRouter2Manager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMediaRouter2ManagerProvider.get(), new LocalMediaManagerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (LocalBluetoothManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocalBluetoothControllerProvider.get()), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get());
    }

    /* renamed from: -$$Nest$mlocationTileDataInteractor  reason: not valid java name */
    public static LocationTileDataInteractor m1546$$Nest$mlocationTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new LocationTileDataInteractor((LocationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.locationControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mlocationTileMapper  reason: not valid java name */
    public static LocationTileMapper m1547$$Nest$mlocationTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new LocationTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mlocationTileUserActionInteractor  reason: not valid java name */
    public static LocationTileUserActionInteractor m1548$$Nest$mlocationTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new LocationTileUserActionInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (LocationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.locationControllerImplProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mlockscreenPrecondition  reason: not valid java name */
    public static LockscreenPrecondition m1549$$Nest$mlockscreenPrecondition(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new LockscreenPrecondition((DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get(), (ExecutionImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideExecutionProvider.get());
    }

    /* renamed from: -$$Nest$mlockscreenShadeScrimTransitionController  reason: not valid java name */
    public static LockscreenShadeScrimTransitionController m1550$$Nest$mlockscreenShadeScrimTransitionController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new LockscreenShadeScrimTransitionController((ScrimController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.scrimControllerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mlockscreenTargetFilter  reason: not valid java name */
    public static LockscreenTargetFilter m1551$$Nest$mlockscreenTargetFilter(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new LockscreenTargetFilter((SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (ExecutionImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideExecutionProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mlogBufferFreezer  reason: not valid java name */
    public static LogBufferFreezer m1552$$Nest$mlogBufferFreezer(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new LogBufferFreezer((DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get());
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [com.android.systemui.classifier.LongTapClassifier, com.android.systemui.classifier.TapClassifier] */
    /* renamed from: -$$Nest$mlongTapClassifier  reason: not valid java name */
    public static LongTapClassifier m1553$$Nest$mlongTapClassifier(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new TapClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), ((float) ((ViewConfiguration) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideViewConfigurationProvider.get()).getScaledTouchSlop()) * 1.25f);
    }

    /* renamed from: -$$Nest$mlowLightClockAnimationProvider  reason: not valid java name */
    public static LowLightClockAnimationProvider m1554$$Nest$mlowLightClockAnimationProvider(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new LowLightClockAnimationProvider(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getDimensionPixelOffset(2131166397), (long) DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getInteger(2131427475), (long) DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getInteger(2131427473), (long) DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getInteger(2131427472));
    }

    /* renamed from: -$$Nest$mmainMessageRouter  reason: not valid java name */
    public static MessageRouterImpl m1555$$Nest$mmainMessageRouter(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new MessageRouterImpl((DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mmapOfClassOfAndProviderOfActivity  reason: not valid java name */
    public static Map m1556$$Nest$mmapOfClassOfAndProviderOfActivity(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(31);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.controlsProviderSelectorActivityProvider;
        Map map = mapBuilder.contributions;
        map.put(ControlsProviderSelectorActivity.class, switchingProvider);
        map.put(ControlsFavoritingActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.controlsFavoritingActivityProvider);
        map.put(ControlsEditingActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.controlsEditingActivityProvider);
        map.put(ControlsRequestDialog.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.controlsRequestDialogProvider);
        map.put(ControlsActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.controlsActivityProvider);
        map.put(MediaProjectionAppSelectorActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaProjectionAppSelectorActivityProvider);
        map.put(MediaProjectionPermissionActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaProjectionPermissionActivityProvider);
        map.put(LaunchNoteTaskActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.launchNoteTaskActivityProvider);
        map.put(LaunchNotesRoleSettingsTrampolineActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.launchNotesRoleSettingsTrampolineActivityProvider);
        map.put(CreateNoteTaskShortcutActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.createNoteTaskShortcutActivityProvider);
        map.put(WalletActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.walletActivityProvider);
        map.put(TunerActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerActivityProvider);
        map.put(ForegroundServicesDialog.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.foregroundServicesDialogProvider);
        map.put(WorkLockActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.workLockActivityProvider);
        map.put(BrightnessDialog.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.brightnessDialogProvider);
        map.put(ContrastDialogActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.contrastDialogActivityProvider);
        map.put(UsbDebuggingActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbDebuggingActivityProvider);
        map.put(UsbDebuggingSecondaryUserActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbDebuggingSecondaryUserActivityProvider);
        map.put(UsbPermissionActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbPermissionActivityProvider);
        map.put(UsbConfirmActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbConfirmActivityProvider);
        map.put(UsbAccessoryUriActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbAccessoryUriActivityProvider);
        map.put(CreateUserActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.createUserActivityProvider);
        map.put(PeopleSpaceActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.peopleSpaceActivityProvider);
        map.put(LongScreenshotActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.longScreenshotActivityProvider);
        map.put(AppClipsTrampolineActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.appClipsTrampolineActivityProvider);
        map.put(AppClipsActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.appClipsActivityProvider);
        map.put(LaunchConversationActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.launchConversationActivityProvider);
        map.put(SensorUseStartedActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sensorUseStartedActivityProvider);
        map.put(EditWidgetsActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.editWidgetsActivityProvider);
        map.put(SwitchToManagedProfileForCallActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.switchToManagedProfileForCallActivityProvider);
        map.put(VolumePanelActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumePanelActivityProvider);
        return mapBuilder.build();
    }

    /* renamed from: -$$Nest$mmapOfClassOfAndProviderOfBroadcastReceiver  reason: not valid java name */
    public static Map m1557$$Nest$mmapOfClassOfAndProviderOfBroadcastReceiver(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(10);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.actionProxyReceiverProvider;
        Map map = mapBuilder.contributions;
        map.put(ActionProxyReceiver.class, switchingProvider);
        map.put(DeleteScreenshotReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deleteScreenshotReceiverProvider);
        map.put(SmartActionsReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartActionsReceiverProvider);
        map.put(MediaOutputDialogReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaOutputDialogReceiverProvider);
        map.put(VolumePanelDialogReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumePanelDialogReceiverProvider);
        map.put(PeopleSpaceWidgetPinnedReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.peopleSpaceWidgetPinnedReceiverProvider);
        map.put(PeopleSpaceWidgetProvider.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.peopleSpaceWidgetProvider);
        map.put(GuestResetOrExitSessionReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.guestResetOrExitSessionReceiverProvider);
        map.put(KeyboardShortcutsReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyboardShortcutsReceiverProvider);
        map.put(HealthUpdateReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.healthUpdateReceiverProvider);
        return mapBuilder.build();
    }

    /* renamed from: -$$Nest$mmapOfClassOfAndProviderOfRecentsImplementation  reason: not valid java name */
    public static Map m1558$$Nest$mmapOfClassOfAndProviderOfRecentsImplementation(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return Collections.singletonMap(OverviewProxyRecentsImpl.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overviewProxyRecentsImplProvider);
    }

    /* renamed from: -$$Nest$mmapOfClassOfAndProviderOfService  reason: not valid java name */
    public static Map m1559$$Nest$mmapOfClassOfAndProviderOfService(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(24);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUISecondaryUserServiceProvider;
        Map map = mapBuilder.contributions;
        map.put(SystemUISecondaryUserService.class, switchingProvider);
        map.put(HomeControlsDreamService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.homeControlsDreamServiceProvider);
        map.put(TakeScreenshotService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.takeScreenshotServiceProvider);
        map.put(ScreenshotProxyService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenshotProxyServiceProvider);
        map.put(AppClipsScreenshotHelperService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.appClipsScreenshotHelperServiceProvider);
        map.put(AppClipsService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.appClipsServiceProvider);
        map.put(NoteTaskControllerUpdateService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.noteTaskControllerUpdateServiceProvider);
        map.put(NoteTaskBubblesController.NoteTaskBubblesService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.noteTaskBubblesServiceProvider);
        map.put(WalletContextualLocationsService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.walletContextualLocationsServiceProvider);
        map.put(DozeService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceProvider);
        map.put(ImageWallpaper.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.imageWallpaperProvider);
        map.put(KeyguardService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardServiceProvider);
        map.put(DreamOverlayService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayServiceProvider);
        map.put(NotificationListenerWithPlugins.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationListenerWithPluginsProvider);
        map.put(SystemUIService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUIServiceProvider);
        map.put(SystemUIAuxiliaryDumpService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUIAuxiliaryDumpServiceProvider);
        map.put(RecordingService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.recordingServiceProvider);
        map.put(IssueRecordingService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.issueRecordingServiceProvider);
        map.put(ColumbusTargetRequestService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.columbusTargetRequestServiceProvider);
        map.put(LowLightClockDreamService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lowLightClockDreamServiceProvider);
        map.put(LockscreenWallpaperDreamService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenWallpaperDreamServiceProvider);
        map.put(LauncherTileService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.launcherTileServiceProvider);
        map.put(HealthService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.healthServiceProvider);
        map.put(BatteryEventService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.batteryEventServiceProvider);
        return mapBuilder.build();
    }

    /* renamed from: -$$Nest$mmapOfStringAndProviderOfQSTileImplOf  reason: not valid java name */
    public static Map m1560$$Nest$mmapOfStringAndProviderOfQSTileImplOf(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(31);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindInternetTileProvider;
        Map map = mapBuilder.contributions;
        map.put("internet", switchingProvider);
        map.put("inversion", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorInversionTileProvider);
        map.put("night", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nightDisplayTileProvider);
        map.put("reduce_brightness", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.reduceBrightColorsTileProvider);
        map.put("onehanded", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.oneHandedModeTileProvider);
        map.put("color_correction", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorCorrectionTileProvider);
        map.put(BcSmartspaceDataPlugin.UI_SURFACE_DREAM, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamTileProvider);
        map.put("font_scaling", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fontScalingTileProvider);
        map.put("bt", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bluetoothTileProvider);
        map.put("cast", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.castTileProvider);
        map.put("hotspot", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.hotspotTileProvider);
        map.put("airplane", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.airplaneModeTileProvider);
        map.put("saver", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dataSaverTileProvider);
        map.put("nfc", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nfcTileProvider);
        map.put("controls", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceControlsTileProvider);
        map.put("dnd", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dndTileProvider);
        map.put("work", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.workModeTileProvider);
        map.put("flashlight", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.flashlightTileProvider);
        map.put("location", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.locationTileProvider);
        map.put("cameratoggle", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.cameraToggleTileProvider);
        map.put("mictoggle", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.microphoneToggleTileProvider);
        map.put("alarm", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alarmTileProvider);
        map.put("dark", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiModeNightTileProvider);
        map.put("qr_code_scanner", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qRCodeScannerTileProvider);
        map.put("record_issue", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.recordIssueTileProvider);
        map.put("screenrecord", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenRecordTileProvider);
        map.put("wallet", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.quickAccessWalletTileProvider);
        map.put("battery", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.batterySaverTileGoogleProvider);
        map.put("ott", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overlayToggleTileProvider);
        map.put("reverse", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.reverseChargingTileProvider);
        map.put("rotation", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.rotationLockTileGoogleProvider);
        return mapBuilder.build();
    }

    /* renamed from: -$$Nest$mmapOfStringAndProviderOfQSTileViewModel  reason: not valid java name */
    public static void m1561$$Nest$mmapOfStringAndProviderOfQSTileViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(10);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideColorCorrectionTileViewModelProvider;
        Map map = mapBuilder.contributions;
        map.put("color_correction", switchingProvider);
        map.put("inversion", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideColorInversionTileViewModelProvider);
        map.put("font_scaling", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideFontScalingTileViewModelProvider);
        map.put("airplane", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAirplaneModeTileViewModelProvider);
        map.put("saver", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDataSaverTileViewModelProvider);
        map.put("flashlight", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideFlashlightTileViewModelProvider);
        map.put("location", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocationTileViewModelProvider);
        map.put("alarm", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAlarmTileViewModelProvider);
        map.put("dark", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUiModeNightTileViewModelProvider);
        map.put("rotation", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideRotationTileViewModelProvider);
        mapBuilder.build();
    }

    /* JADX WARNING: type inference failed for: r7v3, types: [com.android.systemui.qs.tiles.viewmodel.QSTilePolicy$Restricted, java.lang.Object] */
    /* renamed from: -$$Nest$mmapOfStringAndQSTileConfig  reason: not valid java name */
    public static Map m1562$$Nest$mmapOfStringAndQSTileConfig(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(10);
        QSTileConfig qSTileConfig = new QSTileConfig(TileSpec.Companion.create("color_correction"), new QSTileUIConfig.Resource(2131233020, 2131953597), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), (QSTilePolicy.Restricted) null, 24);
        Map map = mapBuilder.contributions;
        map.put("color_correction", qSTileConfig);
        map.put("inversion", new QSTileConfig(TileSpec.Companion.create("inversion"), new QSTileUIConfig.Resource(2131233498, 2131953640), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), (QSTilePolicy.Restricted) null, 24));
        map.put("font_scaling", new QSTileConfig(TileSpec.Companion.create("font_scaling"), new QSTileUIConfig.Resource(2131233023, 2131953634), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), (QSTilePolicy.Restricted) null, 24));
        map.put("airplane", new QSTileConfig(TileSpec.Companion.create("airplane"), new QSTileUIConfig.Resource(2131233458, 2131951914), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), new Object(), 8));
        map.put("saver", new QSTileConfig(TileSpec.Companion.create("saver"), new QSTileUIConfig.Resource(2131233478, 2131952400), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), (QSTilePolicy.Restricted) null, 24));
        map.put("flashlight", new QSTileConfig(TileSpec.Companion.create("flashlight"), new QSTileUIConfig.Resource(2131233488, 2131953633), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), (QSTilePolicy.Restricted) null, 24));
        map.put("location", new QSTileConfig(TileSpec.Companion.create("location"), new QSTileUIConfig.Resource(2131233502, 2131953641), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), (QSTilePolicy.Restricted) null, 24));
        map.put("alarm", new QSTileConfig(TileSpec.Companion.create("alarm"), new QSTileUIConfig.Resource(2131232451, 2131953928), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), (QSTilePolicy.Restricted) null, 24));
        map.put("dark", new QSTileConfig(TileSpec.Companion.create("dark"), new QSTileUIConfig.Resource(2131233500, 2131953667), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), (QSTilePolicy.Restricted) null, 24));
        map.put("rotation", new QSTileConfig(TileSpec.Companion.create("rotation"), new QSTileUIConfig.Resource(2131233460, 2131953657), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), (QSTilePolicy.Restricted) null, 24));
        return mapBuilder.build();
    }

    /* renamed from: -$$Nest$mmediaDataFilter  reason: not valid java name */
    public static MediaDataFilter m1563$$Nest$mmediaDataFilter(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new MediaDataFilter(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (BroadcastSender) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastSenderProvider.get(), (NotificationLockscreenUserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationLockscreenUserManagerImplProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (MediaUiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaUiEventLoggerProvider.get(), (MediaFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaFlagsProvider.get());
    }

    /* renamed from: -$$Nest$mmediaDeviceManager  reason: not valid java name */
    public static MediaDeviceManager m1564$$Nest$mmediaDeviceManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        return new MediaDeviceManager(context, new MediaControllerFactory(context), new LocalMediaManagerFactory(context, (LocalBluetoothManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocalBluetoothControllerProvider.get()), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMediaRouter2ManagerProvider), (MediaMuteAwaitConnectionManagerFactory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaMuteAwaitConnectionManagerFactoryProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocalBluetoothControllerProvider), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get());
    }

    /* renamed from: -$$Nest$mmediaOutputBroadcastDialogFactory  reason: not valid java name */
    public static MediaOutputBroadcastDialogFactory m1565$$Nest$mmediaOutputBroadcastDialogFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        UiEventLogger uiEventLogger = (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get();
        return new MediaOutputBroadcastDialogFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmediaSessionManager(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (LocalBluetoothManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocalBluetoothControllerProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (BroadcastSender) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastSenderProvider.get(), (CommonNotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifPipelineProvider.get(), (DialogTransitionAnimator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDialogTransitionAnimatorProvider.get(), (NearbyMediaDevicesManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nearbyMediaDevicesManagerProvider.get(), (AudioManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAudioManagerProvider.get(), (PowerExemptionManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePowerExemptionManagerProvider.get(), (KeyguardManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideKeyguardManagerProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
    }

    /* renamed from: -$$Nest$mmediaSessionBasedFilter  reason: not valid java name */
    public static MediaSessionBasedFilter m1566$$Nest$mmediaSessionBasedFilter(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new MediaSessionBasedFilter(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmediaSessionManager(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mmediaTttReceiverRippleController  reason: not valid java name */
    public static MediaTttReceiverRippleController m1567$$Nest$mmediaTttReceiverRippleController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new MediaTttReceiverRippleController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get());
    }

    /* renamed from: -$$Nest$mmediaViewController  reason: not valid java name */
    public static MediaViewController m1568$$Nest$mmediaViewController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new MediaViewController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (MediaHostStatesManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaHostStatesManagerProvider.get(), (MediaViewLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaViewLoggerProvider.get(), (MediaFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaFlagsProvider.get());
    }

    /* renamed from: -$$Nest$mnamedBoolean  reason: not valid java name */
    public static Boolean m1569$$Nest$mnamedBoolean(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        try {
            return Boolean.valueOf(((PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get()).getServiceInfo(new ComponentName(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, DreamOverlayService.class), 128).enabled);
        } catch (PackageManager.NameNotFoundException unused) {
            return Boolean.FALSE;
        }
    }

    /* renamed from: -$$Nest$mnamedBoolean2  reason: not valid java name */
    public static boolean m1570$$Nest$mnamedBoolean2(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getBoolean(17891668);
    }

    /* renamed from: -$$Nest$mnamedBoolean3  reason: not valid java name */
    public static boolean m1571$$Nest$mnamedBoolean3(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getBoolean(17891667);
    }

    /* renamed from: -$$Nest$mnamedBoolean4  reason: not valid java name */
    public static boolean m1572$$Nest$mnamedBoolean4(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        try {
            if (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getIntArray(17236083).length != 0) {
                return true;
            }
            return false;
        } catch (Resources.NotFoundException unused) {
            return false;
        }
    }

    /* renamed from: -$$Nest$mnamedComponentName2  reason: not valid java name */
    public static ComponentName m1573$$Nest$mnamedComponentName2(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        String string = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context.getResources().getString(2131952261);
        if (string.length() == 0) {
            return null;
        }
        return ComponentName.unflattenFromString(string);
    }

    /* renamed from: -$$Nest$mnamedComponentName3  reason: not valid java name */
    public static ComponentName m1574$$Nest$mnamedComponentName3(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        String string = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context.getResources().getString(2131952258);
        if (string.isEmpty()) {
            return null;
        }
        return ComponentName.unflattenFromString(string);
    }

    /* renamed from: -$$Nest$mnamedInteger2  reason: not valid java name */
    public static int m1575$$Nest$mnamedInteger2(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getInteger(2131427415);
    }

    /* renamed from: -$$Nest$mnamedInteger3  reason: not valid java name */
    public static int m1576$$Nest$mnamedInteger3(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getInteger(2131427425);
    }

    /* renamed from: -$$Nest$mnamedSetOfAction2  reason: not valid java name */
    public static Set m1577$$Nest$mnamedSetOfAction2(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return Collections.singleton((Action) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.settingsActionProvider2.get());
    }

    /* renamed from: -$$Nest$mnamedSetOfFalsingClassifier  reason: not valid java name */
    public static Set m1578$$Nest$mnamedSetOfFalsingClassifier(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        DistanceClassifier distanceClassifier = new DistanceClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get());
        ProximityClassifier proximityClassifier = new ProximityClassifier(new DistanceClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get()), (FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get());
        HashSet hashSet = new HashSet(Arrays.asList(new FalsingClassifier[]{new FalsingClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get()), new FalsingClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get()), new DiagonalClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get()), distanceClassifier, proximityClassifier, new ZigZagClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get())}));
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "Set contributions cannot be null");
        }
        arrayList.addAll(hashSet);
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$mnamedSetOfFeedbackEffect  reason: not valid java name */
    public static Set m1579$$Nest$mnamedSetOfFeedbackEffect(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return Collections.singleton((FeedbackEffect) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistInvocationEffectProvider2.get());
    }

    /* renamed from: -$$Nest$mnamedSetOfFeedbackEffect2  reason: not valid java name */
    public static Set m1580$$Nest$mnamedSetOfFeedbackEffect2(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        Set of = SetsKt.setOf((HapticClick) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.hapticClickProvider.get(), (UserActivity) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userActivityProvider.get());
        Preconditions.checkNotNullFromProvides(of);
        Set<Object> set = of;
        for (Object checkNotNull : set) {
            Preconditions.checkNotNull(checkNotNull, "Set contributions cannot be null");
        }
        arrayList.addAll(set);
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$mnamedSetOfGate  reason: not valid java name */
    public static Set m1581$$Nest$mnamedSetOfGate(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        Set of = SetsKt.setOf((FlagEnabled) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.flagEnabledProvider.get(), (KeyguardProximity) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardProximityProvider.get(), (SetupWizard) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setupWizardProvider2.get(), (TelephonyActivity) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.telephonyActivityProvider.get(), (VrMode) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vrModeProvider.get(), (CameraVisibility) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.cameraVisibilityProvider2.get(), (PowerSaveState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerSaveStateProvider.get(), (PowerState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerStateProvider.get());
        Preconditions.checkNotNullFromProvides(of);
        Set<Object> set = of;
        for (Object checkNotNull : set) {
            Preconditions.checkNotNull(checkNotNull, "Set contributions cannot be null");
        }
        arrayList.addAll(set);
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$mnamedSetOfGate2  reason: not valid java name */
    public static Set m1582$$Nest$mnamedSetOfGate2(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        Set of = SetsKt.setOf((ChargingState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.chargingStateProvider2.get(), (UsbState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbStateProvider2.get(), (SystemKeyPress) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemKeyPressProvider.get(), (ScreenTouch) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenTouchProvider.get());
        Preconditions.checkNotNullFromProvides(of);
        Set<Object> set = of;
        for (Object checkNotNull : set) {
            Preconditions.checkNotNull(checkNotNull, "Set contributions cannot be null");
        }
        arrayList.addAll(set);
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$mnamedSetOfInteger  reason: not valid java name */
    public static Set m1583$$Nest$mnamedSetOfInteger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        Set of = SetsKt.setOf(24, 25, 26);
        Preconditions.checkNotNullFromProvides(of);
        Set<Object> set = of;
        for (Object checkNotNull : set) {
            Preconditions.checkNotNull(checkNotNull, "Set contributions cannot be null");
        }
        arrayList.addAll(set);
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$mnamedString  reason: not valid java name */
    public static String m1584$$Nest$mnamedString(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        ((DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get()).getClass();
        String string = DeviceConfig.getString("systemui", "back_gesture_ml_model_name", "backgesture");
        Preconditions.checkNotNullFromProvides(string);
        return string;
    }

    /* renamed from: -$$Nest$mnamedString2  reason: not valid java name */
    public static String m1585$$Nest$mnamedString2(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        String string = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getString(2131951936);
        Preconditions.checkNotNullFromProvides(string);
        return string;
    }

    /* renamed from: -$$Nest$mnamedString3  reason: not valid java name */
    public static String m1586$$Nest$mnamedString3(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        String string = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getString(2131952947);
        Preconditions.checkNotNullFromProvides(string);
        return string;
    }

    /* renamed from: -$$Nest$mnamedString4  reason: not valid java name */
    public static String m1587$$Nest$mnamedString4(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        String string = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getString(2131953998);
        Preconditions.checkNotNullFromProvides(string);
        return string;
    }

    /* renamed from: -$$Nest$mnamedTouchInsetManager  reason: not valid java name */
    public static TouchInsetManager m1588$$Nest$mnamedTouchInsetManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new TouchInsetManager((Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mnightDisplayListenerModuleBuilder  reason: not valid java name */
    public static NightDisplayListenerModule$Builder m1589$$Nest$mnightDisplayListenerModuleBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NightDisplayListenerModule$Builder(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgHandlerProvider.get());
    }

    /* renamed from: -$$Nest$mnoteTaskEventLogger  reason: not valid java name */
    public static NoteTaskEventLogger m1591$$Nest$mnoteTaskEventLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NoteTaskEventLogger((UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
    }

    /* renamed from: -$$Nest$mnoteTaskInfoResolver  reason: not valid java name */
    public static NoteTaskInfoResolver m1592$$Nest$mnoteTaskInfoResolver(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new NoteTaskInfoResolver((RoleManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideRoleManagerProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get());
    }

    /* renamed from: -$$Nest$mnoteTaskInitializer  reason: not valid java name */
    public static NoteTaskInitializer m1593$$Nest$mnoteTaskInitializer(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        boolean noteTaskEnabledKeyBoolean = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.noteTaskEnabledKeyBoolean();
        return new NoteTaskInitializer((NoteTaskController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.noteTaskControllerProvider.get(), (RoleManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideRoleManagerProvider.get(), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setBubbles, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), noteTaskEnabledKeyBoolean);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.android.systemui.statusbar.notification.row.NotifBindPipelineInitializer, java.lang.Object] */
    /* renamed from: -$$Nest$mnotifBindPipelineInitializer  reason: not valid java name */
    public static NotifBindPipelineInitializer m1594$$Nest$mnotifBindPipelineInitializer(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        ? obj = new Object();
        obj.mNotifBindPipeline = (NotifBindPipeline) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifBindPipelineProvider.get();
        obj.mRowContentBindStage = (RowContentBindStage) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.rowContentBindStageProvider.get();
        return obj;
    }

    /* renamed from: -$$Nest$mnotifBindPipelineLogger  reason: not valid java name */
    public static NotifBindPipelineLogger m1595$$Nest$mnotifBindPipelineLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotifBindPipelineLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mnotifCollectionLogger  reason: not valid java name */
    public static NotifCollectionLogger m1596$$Nest$mnotifCollectionLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotifCollectionLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mnotifInflaterLogger  reason: not valid java name */
    public static NotifInflaterLogger m1597$$Nest$mnotifInflaterLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotifInflaterLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotifInflationLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mnotificationClickerBuilder  reason: not valid java name */
    public static NotificationClicker.Builder m1599$$Nest$mnotificationClickerBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationClicker.Builder(new NotificationClickerLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotifInteractionLogBufferProvider.get()), (PowerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerInteractorProvider.get());
    }

    /* renamed from: -$$Nest$mnotificationContentInflaterLogger  reason: not valid java name */
    public static NotificationContentInflaterLogger m1600$$Nest$mnotificationContentInflaterLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationContentInflaterLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotifInflationLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mnotificationIconContainerStatusBarViewBinder  reason: not valid java name */
    public static NotificationIconContainerStatusBarViewBinder m1601$$Nest$mnotificationIconContainerStatusBarViewBinder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationIconContainerStatusBarViewBinder(new NotificationIconContainerStatusBarViewModel((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), new DarkIconInteractor((DarkIconRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.darkIconRepositoryImplProvider.get()), new StatusBarNotificationIconsInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconsInteractor(), (NotificationListenerSettingsRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationListenerSettingsRepositoryProvider.get()), new HeadsUpNotificationIconInteractor((HeadsUpNotificationIconViewStateRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpNotificationIconViewStateRepositoryProvider.get()), (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get()), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationState(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemBarUtilsState(), (StatusBarIconViewBindingFailureTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarIconViewBindingFailureTrackerProvider.get(), new StatusBarNotificationIconViewStore((NotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifCollectionProvider.get()));
    }

    /* renamed from: -$$Nest$mnotificationInterruptLogger  reason: not valid java name */
    public static NotificationInterruptLogger m1602$$Nest$mnotificationInterruptLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationInterruptLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationInterruptLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mnotificationListViewBinder  reason: not valid java name */
    public static NotificationListViewBinder m1603$$Nest$mnotificationListViewBinder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.sysUIGoogleGlobalRootComponentImpl;
        DisplaySwitchNotificationsHiderTracker displaySwitchNotificationsHiderTracker = new DisplaySwitchNotificationsHiderTracker((ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.shadeInteractorImplProvider.get(), (LatencyTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideLatencyTrackerProvider.get());
        ConfigurationState configurationState = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationState();
        NotificationIconAreaController notificationIconAreaController = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconAreaController();
        Optional empty = Optional.empty();
        Intrinsics.checkNotNull(empty);
        NotificationIconContainerShelfViewBinder notificationIconContainerShelfViewBinder = new NotificationIconContainerShelfViewBinder(new NotificationIconContainerShelfViewModel((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.bgCoroutineContextProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconsInteractor()), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationState(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemBarUtilsState(), (StatusBarIconViewBindingFailureTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.statusBarIconViewBindingFailureTrackerProvider.get(), new ShelfNotificationIconViewStore((NotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.notifCollectionProvider.get()));
        Provider provider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.statusBarNotificationActivityStarterProvider;
        SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl = (SectionHeaderNodeControllerImpl) ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.providesSilentHeaderSubcomponentProvider.get()).sectionHeaderNodeControllerImplProvider.get();
        Preconditions.checkNotNullFromProvides(sectionHeaderNodeControllerImpl);
        Optional empty2 = Optional.empty();
        Intrinsics.checkNotNull(empty2);
        return new NotificationListViewBinder((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.bgDispatcherProvider.get(), displaySwitchNotificationsHiderTracker, configurationState, (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.falsingManagerProxyProvider.get(), notificationIconAreaController, empty, (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), notificationIconContainerShelfViewBinder, provider, sectionHeaderNodeControllerImpl, new NotificationListViewModel((NotificationShelfViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.notificationShelfViewModelProvider.get(), (HideListViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.hideListViewModelProvider.get(), (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideOptionalProvider.get(), empty2, (ActiveNotificationsInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.activeNotificationsInteractorProvider.get(), (NotificationStackInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.notificationStackInteractorProvider.get(), (RemoteInputInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.remoteInputInteractorProvider.get(), (SeenNotificationsInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.seenNotificationsInteractorProvider.get(), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.shadeInteractorImplProvider.get(), new UserSetupInteractor((UserSetupRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.userSetupRepositoryImplProvider.get()), new ZenModeInteractor(new ZenModeRepositoryImpl((ZenModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.zenModeControllerImplProvider.get()))));
    }

    /* renamed from: -$$Nest$mnotificationRowBinderLogger  reason: not valid java name */
    public static NotificationRowBinderLogger m1604$$Nest$mnotificationRowBinderLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationRowBinderLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotifInflationLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mnotificationStackScrollLogger  reason: not valid java name */
    public static NotificationStackScrollLogger m1605$$Nest$mnotificationStackScrollLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationStackScrollLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationHeadsUpLogBufferProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationRenderLogBufferProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mnotificationSwipeHelperBuilder  reason: not valid java name */
    public static NotificationSwipeHelper.Builder m1606$$Nest$mnotificationSwipeHelperBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new NotificationSwipeHelper.Builder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (ViewConfiguration) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideViewConfigurationProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (NotificationRoundnessManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationRoundnessManagerProvider.get());
    }

    /* renamed from: -$$Nest$mnotificationWakeUpCoordinatorLogger  reason: not valid java name */
    public static NotificationWakeUpCoordinatorLogger m1607$$Nest$mnotificationWakeUpCoordinatorLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationWakeUpCoordinatorLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationLockScreenLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mopaEnabledDispatcher  reason: not valid java name */
    public static OpaEnabledDispatcher m1608$$Nest$mopaEnabledDispatcher(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new OpaEnabledDispatcher(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.centralSurfacesGoogleProvider));
    }

    /* renamed from: -$$Nest$mopaHomeButton  reason: not valid java name */
    public static OpaHomeButton m1609$$Nest$mopaHomeButton(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new OpaHomeButton((KeyguardViewMediator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.newKeyguardViewMediatorProvider.get(), (CentralSurfaces) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.centralSurfacesGoogleProvider.get(), (NavigationModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationModeControllerProvider.get());
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.google.android.systemui.elmyra.feedback.OpaLockscreen] */
    /* renamed from: -$$Nest$mopaLockscreen  reason: not valid java name */
    public static OpaLockscreen m1610$$Nest$mopaLockscreen(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        CentralSurfaces centralSurfaces = (CentralSurfaces) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.centralSurfacesGoogleProvider.get();
        KeyguardStateController keyguardStateController = (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get();
        return new Object();
    }

    /* renamed from: -$$Nest$moperatorNameViewControllerFactory  reason: not valid java name */
    public static OperatorNameViewController.Factory m1611$$Nest$moperatorNameViewControllerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new OperatorNameViewController.Factory((DarkIconDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.darkIconDispatcherImplProvider.get(), (NetworkController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.networkControllerImplProvider.get(), (TunerService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider.get(), (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideTelephonyManagerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (CarrierConfigTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.carrierConfigTrackerProvider.get());
    }

    /* renamed from: -$$Nest$mpanelConfirmationDialogFactory  reason: not valid java name */
    public static PanelConfirmationDialogFactory m1613$$Nest$mpanelConfirmationDialogFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new PanelConfirmationDialogFactory((SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get());
    }

    /* renamed from: -$$Nest$mpanelInteractor  reason: not valid java name */
    public static PanelInteractorImpl m1614$$Nest$mpanelInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        QSPipelineFlagsRepository qSPipelineFlagsRepository = (QSPipelineFlagsRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSPipelineFlagsRepositoryProvider.get();
        QSTileHost qSTileHost = (QSTileHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileHostProvider.get();
        return (PanelInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.panelInteractorImplProvider.get();
    }

    /* renamed from: -$$Nest$mpendingRemovalStore  reason: not valid java name */
    public static PendingRemovalStore m1615$$Nest$mpendingRemovalStore(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new PendingRemovalStore(new BroadcastDispatcherLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBroadcastDispatcherLogBufferProvider.get()));
    }

    /* renamed from: -$$Nest$mpeopleHeaderSectionHeaderController  reason: not valid java name */
    public static SectionHeaderNodeControllerImpl m1616$$Nest$mpeopleHeaderSectionHeaderController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl = (SectionHeaderNodeControllerImpl) ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesPeopleHeaderSubcomponentProvider.get()).sectionHeaderNodeControllerImplProvider.get();
        Preconditions.checkNotNullFromProvides(sectionHeaderNodeControllerImpl);
        return sectionHeaderNodeControllerImpl;
    }

    /* renamed from: -$$Nest$mpersonalProfileUserHandle  reason: not valid java name */
    public static UserHandle m1617$$Nest$mpersonalProfileUserHandle(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        int i;
        ActivityManagerWrapper activityManagerWrapper = (ActivityManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideActivityManagerWrapperProvider.get();
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaProjectionDevicePolicyModule.getClass();
        try {
            UserInfo currentUser = ActivityManager.getService().getCurrentUser();
            if (currentUser != null) {
                i = currentUser.id;
            } else {
                i = 0;
            }
            return UserHandle.of(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* renamed from: -$$Nest$mphoneStatusBarPolicy  reason: not valid java name */
    public static PhoneStatusBarPolicy m1618$$Nest$mphoneStatusBarPolicy(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.sysUIGoogleGlobalRootComponentImpl;
        Looper provideMainLooper = GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper();
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        Resources resources = context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        RotationLockController rotationLockController = (RotationLockController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.rotationLockControllerImplProvider.get();
        DataSaverControllerImpl dataSaverControllerImpl = (DataSaverControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideDataSaverControllerProvider.get();
        int displayId = context.getDisplayId();
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.frameworkServicesModule.getClass();
        SharedPreferences sharedPreferences = Prefs.get(context);
        Preconditions.checkNotNullFromProvides(sharedPreferences);
        DateFormatUtil dateFormatUtil = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dateFormatUtil();
        PrivacyLogger privacyLogger = new PrivacyLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.providePrivacyLogBufferProvider.get());
        Resources resources2 = resources;
        CastController castController = (CastController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.castControllerImplProvider.get();
        HotspotController hotspotController = (HotspotController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.hotspotControllerImplProvider.get();
        BluetoothControllerImpl bluetoothControllerImpl = (BluetoothControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.bluetoothControllerImplProvider.get();
        NextAlarmController nextAlarmController = (NextAlarmController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.nextAlarmControllerImplProvider.get();
        UserInfoControllerImpl userInfoControllerImpl = (UserInfoControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.userInfoControllerImplProvider.get();
        PrivacyLogger privacyLogger2 = privacyLogger;
        return new PhoneStatusBarPolicy((StatusBarIconController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.statusBarIconControllerImplProvider.get(), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideCommandQueueProvider.get(), (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.broadcastDispatcherProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiBackgroundExecutorProvider.get(), provideMainLooper, resources2, castController, hotspotController, bluetoothControllerImpl, nextAlarmController, userInfoControllerImpl, rotationLockController, dataSaverControllerImpl, (ZenModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.zenModeControllerImplProvider.get(), (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideDeviceProvisionedControllerProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardStateControllerImplProvider.get(), (LocationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.locationControllerImplProvider.get(), (SensorPrivacyControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideSensorPrivacyControllerProvider.get(), (AlarmManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAlarmManagerProvider.get(), (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUserManagerProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideUserTrackerProvider.get(), (DevicePolicyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDevicePolicyManagerProvider.get(), (RecordingController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.recordingControllerProvider.get(), (TelecomManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideTelecomManagerProvider.get(), displayId, sharedPreferences, dateFormatUtil, (RingerModeTrackerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.ringerModeTrackerImplProvider.get(), (PrivacyItemController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.privacyItemControllerProvider.get(), privacyLogger2, (ConnectedDisplayInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.connectedDisplayInteractorImplProvider.get(), (JavaAdapter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.javaAdapterProvider.get());
    }

    /* renamed from: -$$Nest$mpowerState  reason: not valid java name */
    public static com.google.android.systemui.elmyra.gates.PowerState m1619$$Nest$mpowerState(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new com.google.android.systemui.elmyra.gates.PowerState((Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (PowerManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePowerManagerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get());
    }

    /* renamed from: -$$Nest$mprimaryProxSensorThresholdSensor  reason: not valid java name */
    public static ThresholdSensorImpl m1620$$Nest$mprimaryProxSensorThresholdSensor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        float f;
        SensorManager sensorManager = (SensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.providesSensorManagerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Resources r2 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl);
        ThresholdSensorImpl.Builder builder = new ThresholdSensorImpl.Builder(r2, (AsyncSensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.asyncSensorManagerProvider.get(), (ExecutionImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideExecutionProvider.get());
        try {
            builder.mSensorDelay = 3;
            Sensor findSensorByType = builder.findSensorByType(r2.getString(2131953545), true);
            if (findSensorByType != null) {
                builder.mSensor = findSensorByType;
                builder.mSensorSet = true;
            }
            try {
                builder.setThresholdValue(r2.getFloat(2131167268));
            } catch (Resources.NotFoundException unused) {
            }
            builder.setThresholdLatchResourceId(2131167269);
            return builder.build();
        } catch (IllegalStateException unused2) {
            Sensor defaultSensor = sensorManager.getDefaultSensor(8, true);
            builder.mSensor = defaultSensor;
            builder.mSensorSet = true;
            if (defaultSensor != null) {
                f = defaultSensor.getMaximumRange();
            } else {
                f = 0.0f;
            }
            builder.setThresholdValue(f);
            return builder.build();
        }
    }

    /* renamed from: -$$Nest$mprimaryProxSensorThresholdSensorArray  reason: not valid java name */
    public static ThresholdSensor[] m1621$$Nest$mprimaryProxSensorThresholdSensorArray(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return SensorModule.createPostureToSensorMapping(new ThresholdSensorImpl.BuilderFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (AsyncSensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.asyncSensorManagerProvider.get(), (ExecutionImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideExecutionProvider.get()), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getStringArray(2130903141), 2131167268, 2131167269);
    }

    /* renamed from: -$$Nest$mqSDisableFlagsLogger  reason: not valid java name */
    public static QSDisableFlagsLogger m1622$$Nest$mqSDisableFlagsLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSDisableFlagsLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideQSFragmentDisableLogBufferProvider.get(), (DisableFlagsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disableFlagsLoggerProvider.get());
    }

    /* renamed from: -$$Nest$mqSPipelineLogger  reason: not valid java name */
    public static QSPipelineLogger m1624$$Nest$mqSPipelineLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSPipelineLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideQSTileListLogBufferProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideQSAutoAddLogBufferProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesQSRestoreLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mqSTileViewModelFactoryComponentOfCustomTileDataModel  reason: not valid java name */
    public static void m1625$$Nest$mqSTileViewModelFactoryComponentOfCustomTileDataModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DisabledByPolicyInteractorImpl disabledByPolicyInteractorImpl = (DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get();
        UserRepositoryImpl userRepositoryImpl = (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get();
        FalsingManager falsingManager = (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get();
        QSTileAnalytics qSTileAnalytics = (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get();
        QSTileLogger qSTileLogger = (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get();
        QSTileConfigProviderImpl qSTileConfigProviderImpl = (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get();
        SystemClock systemClock = (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get();
        CoroutineDispatcher coroutineDispatcher = (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get();
    }

    /* renamed from: -$$Nest$mqsBatteryModeController  reason: not valid java name */
    public static QsBatteryModeController m1626$$Nest$mqsBatteryModeController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QsBatteryModeController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (StatusBarContentInsetsProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarContentInsetsProvider.get());
    }

    /* renamed from: -$$Nest$mquickTapSetOfFeedbackEffect  reason: not valid java name */
    public static Set m1627$$Nest$mquickTapSetOfFeedbackEffect(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        EmptySet emptySet = EmptySet.INSTANCE;
        Preconditions.checkNotNullFromProvides(emptySet);
        EmptyIterator emptyIterator = EmptyIterator.INSTANCE;
        arrayList.addAll(emptySet);
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$mquickTapSetOfGate  reason: not valid java name */
    public static Set m1628$$Nest$mquickTapSetOfGate(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        EmptySet emptySet = EmptySet.INSTANCE;
        Preconditions.checkNotNullFromProvides(emptySet);
        EmptyIterator emptyIterator = EmptyIterator.INSTANCE;
        arrayList.addAll(emptySet);
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$mrecentsImplementation  reason: not valid java name */
    public static OverviewProxyRecentsImpl m1629$$Nest$mrecentsImplementation(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
        ContextComponentResolver contextComponentResolver = (ContextComponentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.contextComponentResolverProvider.get();
        String string = context.getString(2131952276);
        if (string == null || string.length() == 0) {
            throw new RuntimeException("No recents component configured", (Throwable) null);
        }
        OverviewProxyRecentsImpl overviewProxyRecentsImpl = (OverviewProxyRecentsImpl) ContextComponentResolver.resolve(string, contextComponentResolver.mRecentsCreators);
        if (overviewProxyRecentsImpl == null) {
            try {
                try {
                    overviewProxyRecentsImpl = (OverviewProxyRecentsImpl) context.getClassLoader().loadClass(string).newInstance();
                } catch (Throwable th) {
                    throw new RuntimeException("Error creating recents component: ".concat(string), th);
                }
            } catch (Throwable th2) {
                throw new RuntimeException("Error loading recents component: ".concat(string), th2);
            }
        }
        Preconditions.checkNotNullFromProvides(overviewProxyRecentsImpl);
        return overviewProxyRecentsImpl;
    }

    /* renamed from: -$$Nest$mrestrictedLockProxy  reason: not valid java name */
    public static RestrictedLockProxy m1630$$Nest$mrestrictedLockProxy(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new RestrictedLockProxy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context);
    }

    /* renamed from: -$$Nest$mresumeMediaBrowserFactory  reason: not valid java name */
    public static ResumeMediaBrowserFactory m1631$$Nest$mresumeMediaBrowserFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
        return new ResumeMediaBrowserFactory(context, new MediaBrowserFactory(context), (ResumeMediaBrowserLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.resumeMediaBrowserLoggerProvider.get());
    }

    /* renamed from: -$$Nest$mrotationLockTileDataInteractor  reason: not valid java name */
    public static RotationLockTileDataInteractor m1632$$Nest$mrotationLockTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new RotationLockTileDataInteractor((RotationLockController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.rotationLockControllerImplProvider.get(), (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get(), (CameraAutoRotateRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.cameraAutoRotateRepositoryImplProvider.get(), (CameraSensorPrivacyRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.cameraSensorPrivacyRepositoryImplProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl));
    }

    /* renamed from: -$$Nest$mrotationLockTileMapper  reason: not valid java name */
    public static RotationLockTileMapper m1633$$Nest$mrotationLockTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new RotationLockTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme(), (DevicePostureController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.devicePostureControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mrotationLockTileUserActionInteractor  reason: not valid java name */
    public static RotationLockTileUserActionInteractor m1634$$Nest$mrotationLockTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new RotationLockTileUserActionInteractor((RotationLockController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.rotationLockControllerImplProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$mrowContentBindStageLogger  reason: not valid java name */
    public static RowContentBindStageLogger m1635$$Nest$mrowContentBindStageLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new RowContentBindStageLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotifInflationLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$msafetyController  reason: not valid java name */
    public static SafetyController m1636$$Nest$msafetyController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new SafetyController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get(), (SafetyCenterManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideSafetyCenterManagerProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgHandlerProvider.get());
    }

    /* renamed from: -$$Nest$msceneContainerRepository  reason: not valid java name */
    public static SceneContainerRepository m1637$$Nest$msceneContainerRepository(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new SceneContainerRepository((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (SceneContainerConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.containerConfigProvider.get(), (SceneDataSource) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sceneDataSourceDelegatorProvider.get());
    }

    /* renamed from: -$$Nest$msecondaryProxSensorThresholdSensor  reason: not valid java name */
    public static ThresholdSensorImpl m1638$$Nest$msecondaryProxSensorThresholdSensor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Resources r1 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl);
        ThresholdSensorImpl.Builder builder = new ThresholdSensorImpl.Builder(r1, (AsyncSensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.asyncSensorManagerProvider.get(), (ExecutionImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideExecutionProvider.get());
        try {
            Sensor findSensorByType = builder.findSensorByType(r1.getString(2131953544), true);
            if (findSensorByType != null) {
                builder.mSensor = findSensorByType;
                builder.mSensorSet = true;
            }
            try {
                builder.setThresholdValue(r1.getFloat(2131167266));
            } catch (Resources.NotFoundException unused) {
            }
            builder.setThresholdLatchResourceId(2131167267);
            return builder.build();
        } catch (IllegalStateException unused2) {
            builder.mSensor = null;
            builder.mSensorSet = true;
            builder.setThresholdValue(0.0f);
            return builder.build();
        }
    }

    /* renamed from: -$$Nest$msecondaryProxSensorThresholdSensorArray  reason: not valid java name */
    public static ThresholdSensor[] m1639$$Nest$msecondaryProxSensorThresholdSensorArray(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return SensorModule.createPostureToSensorMapping(new ThresholdSensorImpl.BuilderFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (AsyncSensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.asyncSensorManagerProvider.get(), (ExecutionImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideExecutionProvider.get()), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getStringArray(2130903142), 2131167266, 2131167267);
    }

    /* renamed from: -$$Nest$mseekBarViewModel  reason: not valid java name */
    public static SeekBarViewModel m1640$$Nest$mseekBarViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new SeekBarViewModel((RepeatableExecutorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundRepeatableExecutorProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get());
    }

    /* renamed from: -$$Nest$msensitiveNotificationProtectionControllerLogger  reason: not valid java name */
    public static SensitiveNotificationProtectionControllerLogger m1641$$Nest$msensitiveNotificationProtectionControllerLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new SensitiveNotificationProtectionControllerLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSensitiveNotificationProtectionLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$msetOfAction  reason: not valid java name */
    public static Set m1642$$Nest$msetOfAction(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        SetBuilder setBuilder = new SetBuilder(5);
        Set namedSetOfAction = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.namedSetOfAction();
        Preconditions.checkNotNullFromProvides(namedSetOfAction);
        setBuilder.addAll(namedSetOfAction);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        setBuilder.add(new UnpinNotifications(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), Optional.of((HeadsUpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpManagerPhoneProvider.get())));
        setBuilder.add((com.google.android.systemui.elmyra.actions.Action) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.cameraActionProvider.get());
        setBuilder.add((com.google.android.systemui.elmyra.actions.Action) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setupWizardActionProvider.get());
        setBuilder.add((com.google.android.systemui.elmyra.actions.Action) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.launchOpaProvider.get());
        return setBuilder.build();
    }

    /* renamed from: -$$Nest$msetOfAdjustment  reason: not valid java name */
    public static Set m1643$$Nest$msetOfAdjustment(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return Collections.singleton(new ScreenStateAdjustment(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (PowerManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePowerManagerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get()));
    }

    /* renamed from: -$$Nest$msetOfAutoAddable  reason: not valid java name */
    public static Set m1644$$Nest$msetOfAutoAddable(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Set set;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        SetBuilder setBuilder = new SetBuilder(11);
        Resources r1 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$32 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$32 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$32) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider39.get();
        String[] stringArray = r1.getStringArray(2130903114);
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            Intrinsics.checkNotNull(str);
            List split$default = StringsKt.split$default(str, new String[]{":"}, 2, 2);
            AutoAddableSetting autoAddableSetting = null;
            if (split$default.size() == 2) {
                String str2 = (String) split$default.get(0);
                String str3 = (String) split$default.get(1);
                if (Intrinsics.areEqual(TileSpec.Companion.create(str3), TileSpec.Invalid.INSTANCE)) {
                    Log.w("AutoAddableSettingList", "Malformed item in array: ".concat(str));
                } else {
                    TileSpec create = TileSpec.Companion.create(str3);
                    DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$32.this$0;
                    autoAddableSetting = new AutoAddableSetting((SecureSettings) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).secureSettingsImplProvider.get(), (CoroutineDispatcher) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).bgDispatcherProvider.get(), str2, create);
                }
            } else {
                Log.w("AutoAddableSettingList", "Malformed item in array: ".concat(str));
            }
            if (autoAddableSetting != null) {
                arrayList.add(autoAddableSetting);
            }
        }
        Set set2 = CollectionsKt.toSet(arrayList);
        Preconditions.checkNotNullFromProvides(set2);
        setBuilder.addAll(set2);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider40.get();
        if (Flags.a11yQsShortcut()) {
            set = SetsKt.setOf(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33.create(TileSpec.Companion.create("color_correction"), AccessibilityShortcutController.DALTONIZER_COMPONENT_NAME), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33.create(TileSpec.Companion.create("inversion"), AccessibilityShortcutController.COLOR_INVERSION_COMPONENT_NAME), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33.create(TileSpec.Companion.create("onehanded"), AccessibilityShortcutController.ONE_HANDED_COMPONENT_NAME), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33.create(TileSpec.Companion.create("reduce_brightness"), AccessibilityShortcutController.REDUCE_BRIGHT_COLORS_COMPONENT_NAME));
        } else {
            set = EmptySet.INSTANCE;
        }
        Preconditions.checkNotNullFromProvides(set);
        setBuilder.addAll(set);
        setBuilder.add((AutoAddable) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.castAutoAddableProvider.get());
        setBuilder.add((AutoAddable) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dataSaverAutoAddableProvider.get());
        setBuilder.add((AutoAddable) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceControlsAutoAddableProvider.get());
        setBuilder.add((AutoAddable) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.hotspotAutoAddableProvider.get());
        setBuilder.add((AutoAddable) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nightDisplayAutoAddableProvider.get());
        setBuilder.add((AutoAddable) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.reduceBrightColorsAutoAddableProvider.get());
        setBuilder.add((AutoAddable) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.walletAutoAddableProvider.get());
        setBuilder.add((AutoAddable) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.workTileAutoAddableProvider.get());
        setBuilder.add((AutoAddable) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.reverseChargingAutoAddableProvider.get());
        return setBuilder.build();
    }

    /* renamed from: -$$Nest$msetOfComposableLockscreenSceneBlueprint  reason: not valid java name */
    public static Set m1645$$Nest$msetOfComposableLockscreenSceneBlueprint(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        SetBuilder setBuilder = new SetBuilder(6);
        setBuilder.add(new CommunalBlueprint((LockscreenContentViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.lockscreenContentViewModelProvider.get()));
        StatusBarSection statusBarSection = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarSection();
        DefaultClockSection defaultClockSection = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.defaultClockSection();
        SmartSpaceSection smartSpaceSection = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartSpaceSection();
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.notificationSectionProvider.get());
        setBuilder.add(new DefaultBlueprint((LockscreenContentViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.lockscreenContentViewModelProvider.get(), statusBarSection, defaultClockSection, smartSpaceSection, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockSection(), Optional.of(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.googleAmbientIndicationSection()), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bottomAreaSection(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.settingsMenuSection(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaCarouselSection(), (KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardClockInteractorProvider.get()));
        StatusBarSection statusBarSection2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarSection();
        DefaultClockSection defaultClockSection2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.defaultClockSection();
        SmartSpaceSection smartSpaceSection2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartSpaceSection();
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.notificationSectionProvider.get());
        setBuilder.add(new ShortcutsBesideUdfpsBlueprint((LockscreenContentViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.lockscreenContentViewModelProvider.get(), statusBarSection2, defaultClockSection2, smartSpaceSection2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockSection(), Optional.of(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.googleAmbientIndicationSection()), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bottomAreaSection(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.settingsMenuSection(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaCarouselSection(), (KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardClockInteractorProvider.get()));
        StatusBarSection statusBarSection3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarSection();
        DefaultClockSection defaultClockSection3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.defaultClockSection();
        SmartSpaceSection smartSpaceSection3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartSpaceSection();
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.notificationSectionProvider.get());
        setBuilder.add(new SplitShadeBlueprint((LockscreenContentViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.lockscreenContentViewModelProvider.get(), statusBarSection3, defaultClockSection3, smartSpaceSection3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockSection(), Optional.of(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.googleAmbientIndicationSection()), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bottomAreaSection(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.settingsMenuSection(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaCarouselSection(), (KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardClockInteractorProvider.get(), (LargeScreenHeaderHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.largeScreenHeaderHelperProvider.get()));
        StatusBarSection statusBarSection4 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarSection();
        SmartSpaceSection smartSpaceSection4 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartSpaceSection();
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.notificationSectionProvider.get());
        setBuilder.add(new SplitShadeWeatherClockBlueprint((LockscreenContentViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.lockscreenContentViewModelProvider.get(), statusBarSection4, smartSpaceSection4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockSection(), Optional.of(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.googleAmbientIndicationSection()), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bottomAreaSection(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.settingsMenuSection(), (KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardClockInteractorProvider.get(), (LargeScreenHeaderHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.largeScreenHeaderHelperProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaCarouselSection()));
        StatusBarSection statusBarSection5 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarSection();
        SmartSpaceSection smartSpaceSection5 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartSpaceSection();
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.notificationSectionProvider.get());
        setBuilder.add(new WeatherClockBlueprint((LockscreenContentViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.lockscreenContentViewModelProvider.get(), statusBarSection5, smartSpaceSection5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockSection(), Optional.of(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.googleAmbientIndicationSection()), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bottomAreaSection(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.settingsMenuSection(), (KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardClockInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaCarouselSection()));
        return setBuilder.build();
    }

    /* renamed from: -$$Nest$msetOfConfigInfoListener  reason: not valid java name */
    public static Set m1646$$Nest$msetOfConfigInfoListener(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        HashSet hashSet = new HashSet(Arrays.asList(new NgaMessageHandler.ConfigInfoListener[]{(AssistantPresenceHandler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistantPresenceHandlerProvider.get(), (ConfigurationHandler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationHandlerProvider.get()}));
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "Set contributions cannot be null");
        }
        arrayList.addAll(hashSet);
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$msetOfConfigurationListener  reason: not valid java name */
    public static Set m1647$$Nest$msetOfConfigurationListener(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        SetBuilder setBuilder = new SetBuilder(9);
        setBuilder.add((ConfigurationController.ConfigurationListener) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.authControllerProvider.get());
        setBuilder.add((ConfigurationController.ConfigurationListener) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideRecentsProvider.get());
        setBuilder.add((ConfigurationController.ConfigurationListener) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenPinningRequestProvider.get());
        setBuilder.add((ConfigurationController.ConfigurationListener) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumeUIProvider.get());
        setBuilder.add((ConfigurationController.ConfigurationListener) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerUIProvider.get());
        setBuilder.add((ConfigurationController.ConfigurationListener) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.rearDisplayDialogControllerProvider.get());
        setBuilder.add((ConfigurationController.ConfigurationListener) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenDecorationsProvider.get());
        setBuilder.add((ConfigurationController.ConfigurationListener) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemActionsProvider.get());
        setBuilder.add((ConfigurationController.ConfigurationListener) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.toastUIProvider.get());
        return setBuilder.build();
    }

    /* renamed from: -$$Nest$msetOfDeviceEntryIconTransition  reason: not valid java name */
    public static Set m1648$$Nest$msetOfDeviceEntryIconTransition(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        SetBuilder setBuilder = new SetBuilder(24);
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerToAodTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerToDozingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerToGoneTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerToPrimaryBouncerTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodToGoneTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozingToGoneTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozingToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozingToPrimaryBouncerTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamingToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToAodTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToDozingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToDreamingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToOccludedTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToPrimaryBouncerTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToGoneTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.goneToAodTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.goneToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.goneToDozingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.occludedToAodTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.occludedToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerToAodTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerToDozingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerToLockscreenTransitionViewModelProvider.get());
        return setBuilder.build();
    }

    /* renamed from: -$$Nest$msetOfFeedbackEffect  reason: not valid java name */
    public static Set m1649$$Nest$msetOfFeedbackEffect(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(4);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        arrayList.add(new com.google.android.systemui.elmyra.feedback.HapticClick(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideOptionalVibratorProvider.get()));
        arrayList.add(new SquishyNavigationButtons(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (KeyguardViewMediator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.newKeyguardViewMediatorProvider.get(), (CentralSurfaces) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.centralSurfacesGoogleProvider.get(), (NavigationModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationModeControllerProvider.get()));
        arrayList.add(new NavUndimEffect((NavigationBarControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationBarControllerImplProvider.get()));
        arrayList.add(new com.google.android.systemui.elmyra.feedback.UserActivity((KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (PowerManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePowerManagerProvider.get()));
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$msetOfGate  reason: not valid java name */
    public static Set m1650$$Nest$msetOfGate(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        SetBuilder setBuilder = new SetBuilder(12);
        setBuilder.add(((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider37.get()).create(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.namedSetOfAction()));
        setBuilder.add((Gate) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wakeModeProvider.get());
        setBuilder.add((Gate) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.cameraVisibilityProvider.get());
        setBuilder.add((Gate) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.chargingStateProvider.get());
        setBuilder.add((Gate) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbStateProvider.get());
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        setBuilder.add(new NavigationBarVisibility((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get(), (AssistManagerGoogle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistManagerGoogleProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardVisibility(), new NonGesturalNavigation((Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (NavigationModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationModeControllerProvider.get()), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.namedSetOfAction()));
        setBuilder.add(new com.google.android.systemui.elmyra.gates.TelephonyActivity((Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideTelephonyManagerProvider.get(), (TelephonyListenerManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.telephonyListenerManagerProvider.get()));
        setBuilder.add((Gate) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setupWizardProvider.get());
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        Resources resources = context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        Resources resources2 = context.getResources();
        Preconditions.checkNotNullFromProvides(resources2);
        setBuilder.add(new com.google.android.systemui.elmyra.gates.SystemKeyPress(resources, (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), resources2.getInteger(2131427424), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get()));
        setBuilder.add(new com.google.android.systemui.elmyra.gates.VrMode((Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (IVrManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIVrManagerProvider.get()));
        setBuilder.add(new com.google.android.systemui.elmyra.gates.PowerSaveState((Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (PowerManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePowerManagerProvider.get(), (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get()));
        setBuilder.add(new LockTask((Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get()));
        return setBuilder.build();
    }

    /* renamed from: -$$Nest$msetOfGoBackListener  reason: not valid java name */
    public static Set m1651$$Nest$msetOfGoBackListener(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return Collections.singleton((GoBackHandler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.goBackHandlerProvider.get());
    }

    /* renamed from: -$$Nest$msetOfKeyguardBlueprint  reason: not valid java name */
    public static Set m1652$$Nest$msetOfKeyguardBlueprint(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        SetBuilder setBuilder = new SetBuilder(5);
        setBuilder.add(new KeyguardBlueprintModule$Companion$bindWeatherClockBlueprintPlaceHolder$1(0));
        setBuilder.add(new KeyguardBlueprintModule$Companion$bindWeatherClockBlueprintPlaceHolder$1(1));
        setBuilder.add((KeyguardBlueprint) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.defaultKeyguardBlueprintProvider.get());
        setBuilder.add((KeyguardBlueprint) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeKeyguardBlueprintProvider.get());
        setBuilder.add((KeyguardBlueprint) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shortcutsBesideUdfpsKeyguardBlueprintProvider.get());
        return setBuilder.build();
    }

    /* renamed from: -$$Nest$msetOfKeyguardQuickAffordanceConfig  reason: not valid java name */
    public static Set m1653$$Nest$msetOfKeyguardQuickAffordanceConfig(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(2);
        Set of = SetsKt.setOf((CameraQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.cameraQuickAffordanceConfigProvider.get(), (DoNotDisturbQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.doNotDisturbQuickAffordanceConfigProvider.get(), (FlashlightQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.flashlightQuickAffordanceConfigProvider.get(), (HomeControlsKeyguardQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.homeControlsKeyguardQuickAffordanceConfigProvider.get(), (MuteQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.muteQuickAffordanceConfigProvider.get(), (QuickAccessWalletKeyguardQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.quickAccessWalletKeyguardQuickAffordanceConfigProvider.get(), (QrCodeScannerKeyguardQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.qrCodeScannerKeyguardQuickAffordanceConfigProvider.get(), (VideoCameraQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.videoCameraQuickAffordanceConfigProvider.get());
        Preconditions.checkNotNullFromProvides(of);
        Set<Object> set = of;
        for (Object checkNotNull : set) {
            Preconditions.checkNotNull(checkNotNull, "Set contributions cannot be null");
        }
        arrayList.addAll(set);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.sysUIGoogleGlobalRootComponentImpl;
        arrayList.add(new NoteTaskQuickAffordanceConfig(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (NoteTaskController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.noteTaskControllerProvider.get(), new NoteTaskInfoResolver((RoleManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideRoleManagerProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get()), (StylusManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.stylusManagerProvider.get(), (RoleManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideRoleManagerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardUpdateMonitorProvider.get(), (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUserManagerProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.keyguardQuickAffordanceRepositoryProvider), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.noteTaskEnabledKeyBoolean(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideBackgroundExecutorProvider.get()));
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$msetOfNavBarVisibilityListener  reason: not valid java name */
    public static Set m1654$$Nest$msetOfNavBarVisibilityListener(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return Collections.singleton((NavBarFadeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navBarFadeControllerProvider.get());
    }

    /* renamed from: -$$Nest$msetOfPrivacyItemMonitor  reason: not valid java name */
    public static Set m1655$$Nest$msetOfPrivacyItemMonitor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return Collections.singleton((PrivacyItemMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.appOpsPrivacyItemMonitorProvider.get());
    }

    /* renamed from: -$$Nest$msetOfSwipeListener  reason: not valid java name */
    public static Set m1656$$Nest$msetOfSwipeListener(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return Collections.singleton((SwipeHandler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.swipeHandlerProvider.get());
    }

    /* renamed from: -$$Nest$msetOfTransitionInteractor  reason: not valid java name */
    public static Set m1657$$Nest$msetOfTransitionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        SetBuilder setBuilder = new SetBuilder(10);
        setBuilder.add((TransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fromPrimaryBouncerTransitionInteractorProvider.get());
        setBuilder.add((TransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fromLockscreenTransitionInteractorProvider.get());
        setBuilder.add((TransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fromAodTransitionInteractorProvider.get());
        setBuilder.add((TransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fromGoneTransitionInteractorProvider.get());
        setBuilder.add((TransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fromDreamingTransitionInteractorProvider.get());
        setBuilder.add((TransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fromDreamingLockscreenHostedTransitionInteractorProvider.get());
        setBuilder.add((TransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fromOccludedTransitionInteractorProvider.get());
        setBuilder.add((TransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fromDozingTransitionInteractorProvider.get());
        setBuilder.add((TransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fromAlternateBouncerTransitionInteractorProvider.get());
        setBuilder.add((TransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fromGlanceableHubTransitionInteractorProvider.get());
        return setBuilder.build();
    }

    /* renamed from: -$$Nest$msetOfWarmingListener  reason: not valid java name */
    public static Set m1658$$Nest$msetOfWarmingListener(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return Collections.singleton((AssistantWarmer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistantWarmerProvider.get());
    }

    /* renamed from: -$$Nest$mshadeCarrierGroupControllerBuilder  reason: not valid java name */
    public static ShadeCarrierGroupController.Builder m1659$$Nest$mshadeCarrierGroupControllerBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ShadeCarrierGroupController.Builder((ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgHandlerProvider.get(), GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper(), (NetworkController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.networkControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.carrierTextManagerBuilder(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (CarrierConfigTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.carrierConfigTrackerProvider.get(), (ShadeCarrierGroupController.SubscriptionManagerSlotIndexResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.subscriptionManagerSlotIndexResolverProvider.get(), (MobileUiAdapter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mobileUiAdapterProvider.get(), (MobileContextProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mobileContextProvider.get(), (StatusBarPipelineFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarPipelineFlagsProvider.get());
    }

    /* renamed from: -$$Nest$mshadeDependentFlows  reason: not valid java name */
    public static ShadeDependentFlows m1660$$Nest$mshadeDependentFlows(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ShadeDependentFlows((KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get(), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get());
    }

    /* renamed from: -$$Nest$mshadeEventCoordinatorLogger  reason: not valid java name */
    public static ShadeEventCoordinatorLogger m1661$$Nest$mshadeEventCoordinatorLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ShadeEventCoordinatorLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mshadeListBuilderLogger  reason: not valid java name */
    public static ShadeListBuilderLogger m1662$$Nest$mshadeListBuilderLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifPipelineFlags();
        return new ShadeListBuilderLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mshadeLogger  reason: not valid java name */
    public static ShadeLogger m1663$$Nest$mshadeLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ShadeLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mshadeWindowLogger  reason: not valid java name */
    public static ShadeWindowLogger m1664$$Nest$mshadeWindowLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ShadeWindowLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeWindowLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mshutdownUi  reason: not valid java name */
    public static ShutdownUIGoogle m1665$$Nest$mshutdownUi(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shutdownUiModuleGoogle.getClass();
        return new ShutdownUIGoogle(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (BlurUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.blurUtilsProvider.get(), (ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get());
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.android.systemui.statusbar.domain.interactor.SilentNotificationStatusIconsVisibilityInteractor, java.lang.Object] */
    /* renamed from: -$$Nest$msilentNotificationStatusIconsVisibilityInteractor  reason: not valid java name */
    public static SilentNotificationStatusIconsVisibilityInteractor m1666$$Nest$msilentNotificationStatusIconsVisibilityInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        NotificationListenerSettingsRepository notificationListenerSettingsRepository = (NotificationListenerSettingsRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationListenerSettingsRepositoryProvider.get();
        return new Object();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.android.systemui.classifier.SingleTapClassifier, com.android.systemui.classifier.TapClassifier] */
    /* renamed from: -$$Nest$msingleTapClassifier  reason: not valid java name */
    public static SingleTapClassifier m1667$$Nest$msingleTapClassifier(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new TapClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (float) ((ViewConfiguration) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideViewConfigurationProvider.get()).getScaledTouchSlop());
    }

    /* renamed from: -$$Nest$msmartReplyStateInflaterImpl  reason: not valid java name */
    public static SmartReplyStateInflaterImpl m1668$$Nest$msmartReplyStateInflaterImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        SmartReplyInflaterImpl smartReplyInflaterImpl = new SmartReplyInflaterImpl((SmartReplyConstants) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartReplyConstantsProvider.get(), (KeyguardDismissUtil) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardDismissUtilProvider.get(), (NotificationRemoteInputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationRemoteInputManagerProvider.get(), (SmartReplyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSmartReplyControllerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
        HeadsUpManager headsUpManager = (HeadsUpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpManagerPhoneProvider.get();
        return new SmartReplyStateInflaterImpl((SmartReplyConstants) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartReplyConstantsProvider.get(), (ActivityManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideActivityManagerWrapperProvider.get(), (PackageManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerWrapperProvider.get(), (DevicePolicyManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDevicePolicyManagerWrapperProvider.get(), smartReplyInflaterImpl, new SmartActionInflaterImpl((SmartReplyConstants) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartReplyConstantsProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (SmartReplyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSmartReplyControllerProvider.get()));
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.android.systemui.keyguard.ui.view.layout.sections.SmartspaceSection, java.lang.Object] */
    /* renamed from: -$$Nest$msmartspaceSection  reason: not valid java name */
    public static SmartspaceSection m1669$$Nest$msmartspaceSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
        KeyguardClockViewModel keyguardClockViewModel = (KeyguardClockViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockViewModelProvider.get();
        KeyguardSmartspaceViewModel keyguardSmartspaceViewModel = (KeyguardSmartspaceViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardSmartspaceViewModelProvider.get();
        KeyguardSmartspaceInteractor keyguardSmartspaceInteractor = (KeyguardSmartspaceInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardSmartspaceInteractorProvider.get();
        LockscreenSmartspaceController lockscreenSmartspaceController = (LockscreenSmartspaceController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenSmartspaceControllerProvider.get();
        KeyguardUnlockAnimationController keyguardUnlockAnimationController = (KeyguardUnlockAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUnlockAnimationControllerProvider.get();
        DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBlueprintInteractorProvider);
        return new Object();
    }

    /* renamed from: -$$Nest$msoftSetOfGate  reason: not valid java name */
    public static Set m1670$$Nest$msoftSetOfGate(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        EmptySet emptySet = EmptySet.INSTANCE;
        Preconditions.checkNotNullFromProvides(emptySet);
        EmptyIterator emptyIterator = EmptyIterator.INSTANCE;
        arrayList.addAll(emptySet);
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.android.systemui.keyguard.ui.view.layout.sections.SplitShadeMediaSection, java.lang.Object] */
    /* renamed from: -$$Nest$msplitShadeMediaSection  reason: not valid java name */
    public static SplitShadeMediaSection m1671$$Nest$msplitShadeMediaSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
        NotificationPanelView notificationPanelView = (NotificationPanelView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNotificationPanelViewProvider.get();
        KeyguardMediaController keyguardMediaController = (KeyguardMediaController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardMediaControllerProvider.get();
        return new Object();
    }

    /* renamed from: -$$Nest$msplitShadeNotificationStackScrollLayoutSection  reason: not valid java name */
    public static SplitShadeNotificationStackScrollLayoutSection m1672$$Nest$msplitShadeNotificationStackScrollLayoutSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        KeyguardSmartspaceViewModel keyguardSmartspaceViewModel = (KeyguardSmartspaceViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardSmartspaceViewModelProvider.get();
        return new NotificationStackScrollLayoutSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (SceneContainerFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.implProvider.get(), (NotificationPanelView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNotificationPanelViewProvider.get(), (SharedNotificationContainer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesSharedNotificationContainerProvider.get(), (SharedNotificationContainerViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sharedNotificationContainerViewModelProvider.get(), (NotificationStackAppearanceViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationStackAppearanceViewModelProvider.get(), (AmbientState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.ambientStateProvider.get(), (NotificationStackScrollLayoutController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationStackScrollLayoutControllerProvider.get(), (NotificationStackSizeCalculator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationStackSizeCalculatorProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.mainDispatcherProvider.get());
    }

    /* renamed from: -$$Nest$mstackStateLogger  reason: not valid java name */
    public static StackStateLogger m1673$$Nest$mstackStateLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new StackStateLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationHeadsUpLogBufferProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationRenderLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mstaticOfAirplaneModeTileModel  reason: not valid java name */
    public static QSTileViewModelFactory$Static m1674$$Nest$mstaticOfAirplaneModeTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfAlarmTileModel  reason: not valid java name */
    public static QSTileViewModelFactory$Static m1675$$Nest$mstaticOfAlarmTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfColorCorrectionTileModel  reason: not valid java name */
    public static QSTileViewModelFactory$Static m1676$$Nest$mstaticOfColorCorrectionTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfColorInversionTileModel  reason: not valid java name */
    public static QSTileViewModelFactory$Static m1677$$Nest$mstaticOfColorInversionTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfDataSaverTileModel  reason: not valid java name */
    public static QSTileViewModelFactory$Static m1678$$Nest$mstaticOfDataSaverTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfFlashlightTileModel  reason: not valid java name */
    public static QSTileViewModelFactory$Static m1679$$Nest$mstaticOfFlashlightTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfFontScalingTileModel  reason: not valid java name */
    public static QSTileViewModelFactory$Static m1680$$Nest$mstaticOfFontScalingTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfLocationTileModel  reason: not valid java name */
    public static QSTileViewModelFactory$Static m1681$$Nest$mstaticOfLocationTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfRotationLockTileModel  reason: not valid java name */
    public static QSTileViewModelFactory$Static m1682$$Nest$mstaticOfRotationLockTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfUiModeNightTileModel  reason: not valid java name */
    public static QSTileViewModelFactory$Static m1683$$Nest$mstaticOfUiModeNightTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstatusBarNotificationActivityStarterLogger  reason: not valid java name */
    public static StatusBarNotificationActivityStarterLogger m1684$$Nest$mstatusBarNotificationActivityStarterLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new StatusBarNotificationActivityStarterLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotifInteractionLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mstatusOverlayHoverListenerFactory  reason: not valid java name */
    public static StatusOverlayHoverListenerFactory m1685$$Nest$mstatusOverlayHoverListenerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new StatusOverlayHoverListenerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (DarkIconDispatcherImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.darkIconDispatcherImplProvider.get());
    }

    /* renamed from: -$$Nest$mstickyKeysIndicatorViewModel  reason: not valid java name */
    public static StickyKeysIndicatorViewModel m1686$$Nest$mstickyKeysIndicatorViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new StickyKeysIndicatorViewModel((StickyKeysRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.stickyKeysRepositoryImplProvider.get(), (KeyboardRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyboardRepositoryImplProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
    }

    /* renamed from: -$$Nest$msubscriptionManagerProxyImpl  reason: not valid java name */
    public static SubscriptionManagerProxyImpl m1687$$Nest$msubscriptionManagerProxyImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Context context = (Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get();
        return new SubscriptionManagerProxyImpl((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (SubscriptionManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideSubscriptionManagerProvider.get());
    }

    /* renamed from: -$$Nest$msystemEventChipAnimationController  reason: not valid java name */
    public static SystemEventChipAnimationController m1688$$Nest$msystemEventChipAnimationController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new SystemEventChipAnimationController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (StatusBarWindowController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarWindowControllerProvider.get(), (StatusBarContentInsetsProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarContentInsetsProvider.get());
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [com.android.systemui.process.ProcessWrapper, java.lang.Object] */
    /* renamed from: -$$Nest$msystemProcessCondition  reason: not valid java name */
    public static SystemProcessCondition m1689$$Nest$msystemProcessCondition(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        CoroutineScope coroutineScope = (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get();
        return new SystemProcessCondition(new Object());
    }

    /* renamed from: -$$Nest$msystemSettingsImpl  reason: not valid java name */
    public static SystemSettingsImpl m1690$$Nest$msystemSettingsImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new SystemSettingsImpl((ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
    }

    /* renamed from: -$$Nest$mtaskSwitcherNotificationViewModel  reason: not valid java name */
    public static TaskSwitcherNotificationViewModel m1691$$Nest$mtaskSwitcherNotificationViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new TaskSwitcherNotificationViewModel((TaskSwitchInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.taskSwitchInteractorProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get());
    }

    /* renamed from: -$$Nest$mtoastLogger  reason: not valid java name */
    public static ToastLogger m1692$$Nest$mtoastLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ToastLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideToastLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$muiModeNightTileDataInteractor  reason: not valid java name */
    public static UiModeNightTileDataInteractor m1693$$Nest$muiModeNightTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new UiModeNightTileDataInteractor((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (UiModeManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiModeManagerProvider.get(), (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get(), (LocationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.locationControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dateFormatUtil());
    }

    /* renamed from: -$$Nest$muiModeNightTileMapper  reason: not valid java name */
    public static UiModeNightTileMapper m1694$$Nest$muiModeNightTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        return new UiModeNightTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$muiModeNightTileUserActionInteractor  reason: not valid java name */
    public static UiModeNightTileUserActionInteractor m1695$$Nest$muiModeNightTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new UiModeNightTileUserActionInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (UiModeManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiModeManagerProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$mvariableDateViewControllerFactory  reason: not valid java name */
    public static VariableDateViewController.Factory m1696$$Nest$mvariableDateViewControllerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new VariableDateViewController.Factory((SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get(), new ShadeLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeLogBufferProvider.get()), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideTimeTickHandlerProvider.get());
    }

    /* renamed from: -$$Nest$mviewMediatorCallback  reason: not valid java name */
    public static KeyguardViewMediator.AnonymousClass4 m1697$$Nest$mviewMediatorCallback(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        KeyguardViewMediator.AnonymousClass4 r0 = ((KeyguardViewMediator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.newKeyguardViewMediatorProvider.get()).mViewMediatorCallback;
        Preconditions.checkNotNullFromProvides(r0);
        return r0;
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [com.android.systemui.volume.panel.shared.flag.VolumePanelFlag, java.lang.Object] */
    /* renamed from: -$$Nest$mvolumeDialog  reason: not valid java name */
    public static VolumeDialogImpl m1698$$Nest$mvolumeDialog(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.sysUIGoogleGlobalRootComponentImpl;
        VolumeDialogImpl volumeDialogImpl = new VolumeDialogImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (VolumeDialogController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.volumeDialogControllerImplProvider.get(), (AccessibilityManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.accessibilityManagerWrapperProvider.get(), (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideDeviceProvisionedControllerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.configurationControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaOutputDialogFactory(), (InteractionJankMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInteractionJankMonitorProvider.get(), new VolumePanelNavigationInteractor((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), new Object()), new VolumeNavigator((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (VolumePanelFactory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.volumePanelFactoryProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.activityStarterImplProvider.get()), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$16) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.factoryProvider21.get(), (DevicePostureController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.devicePostureControllerImplProvider.get(), Looper.getMainLooper(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.secureSettingsImplProvider), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.vibratorHelperProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.bindSystemClockProvider.get());
        volumeDialogImpl.mHandler.obtainMessage(5, 1, 0).sendToTarget();
        if (!volumeDialogImpl.mAutomute) {
            volumeDialogImpl.mAutomute = true;
            volumeDialogImpl.mHandler.sendEmptyMessage(4);
        }
        if (volumeDialogImpl.mSilentMode) {
            volumeDialogImpl.mSilentMode = false;
            volumeDialogImpl.mHandler.sendEmptyMessage(4);
        }
        return volumeDialogImpl;
    }

    /* renamed from: -$$Nest$mwakeLockBuilder  reason: not valid java name */
    public static WakeLock.Builder m1699$$Nest$mwakeLockBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new WakeLock.Builder(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wakeLockLogger());
    }

    /* renamed from: -$$Nest$mwallpaperNotifier  reason: not valid java name */
    public static WallpaperNotifier m1700$$Nest$mwallpaperNotifier(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new WallpaperNotifier(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (CommonNotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifPipelineProvider.get(), (BroadcastSender) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastSenderProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiBackgroundExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mwidgetInteractionHandler  reason: not valid java name */
    public static WidgetInteractionHandler m1701$$Nest$mwidgetInteractionHandler(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        return new WidgetInteractionHandler((ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get());
    }

    /* renamed from: -$$Nest$mwifiStatusTrackerFactory  reason: not valid java name */
    public static WifiStatusTrackerFactory m1702$$Nest$mwifiStatusTrackerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new WifiStatusTrackerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (WifiManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWifiManagerProvider.get(), (NetworkScoreManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideNetworkScoreManagerProvider.get(), (ConnectivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideConnectivityManagagerProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get());
    }

    /* renamed from: -$$Nest$mworkProfileUserHandle  reason: not valid java name */
    public static UserHandle m1703$$Nest$mworkProfileUserHandle(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Object obj;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaProjectionDevicePolicyModule.getClass();
        Iterator it = ((UserTrackerImpl) ((UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get())).getUserProfiles().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((UserInfo) obj).isManagedProfile()) {
                break;
            }
        }
        UserInfo userInfo = (UserInfo) obj;
        if (userInfo != null) {
            return userInfo.getUserHandle();
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r2v48, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v93, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v96, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v111, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v112, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v113, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v120, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v125, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v135, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v138, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v139, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v140, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v147, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r2v173, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v54, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v85, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v86, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v113, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v114, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v145, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v148, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v165, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v172, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v173, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v225, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v252, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r1v325, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, LeakModule leakModule2, SharedLibraryModule sharedLibraryModule2, MediaProjectionDevicePolicyModule mediaProjectionDevicePolicyModule2, SysUICoroutinesModule sysUICoroutinesModule2, SysUIUnfoldModule sysUIUnfoldModule2, ShutdownUiModuleGoogle shutdownUiModuleGoogle2, ShellInterface shellInterface, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, ShellTransitions shellTransitions, KeyguardTransitions keyguardTransitions, Optional optional6, Optional optional7, Optional optional8, Optional optional9, Optional optional10) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUICoroutinesModule = sysUICoroutinesModule2;
        this.leakModule = leakModule2;
        this.sharedLibraryModule = sharedLibraryModule2;
        this.setShell = shellInterface;
        this.setBubbles = optional4;
        this.sysUIUnfoldModule = sysUIUnfoldModule2;
        this.setTaskViewFactory = optional5;
        this.shutdownUiModuleGoogle = shutdownUiModuleGoogle2;
        this.mediaProjectionDevicePolicyModule = mediaProjectionDevicePolicyModule2;
        this.setStartingSurface = optional6;
        this.setPip = optional;
        this.setDesktopMode = optional10;
        this.setBackAnimation = optional9;
        this.setKeyguardTransitions = keyguardTransitions;
        this.setTransitions = shellTransitions;
        this.setSplitScreen = optional2;
        this.setOneHanded = optional3;
        this.setRecentTasks = optional8;
        this.setDisplayAreaHelper = optional7;
        this.bootCompleteCacheImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 0);
        this.configurationControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 1);
        this.provideBgLooperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 5);
        this.provideBackgroundExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 4);
        this.deviceConfigProxyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 10);
        this.bindsReaderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 9);
        this.bindSystemClockProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 13, 1));
        this.provideBroadcastRunningLooperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 15);
        this.provideBroadcastRunningExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 16);
        this.bgDispatcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 21);
        this.commandRegistryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 22);
        this.logcatEchoTrackerDebugProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 20, 1);
        this.provideLogcatEchoTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 19);
        this.logBufferFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 18);
        this.provideBroadcastDispatcherLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 17);
        this.provideUserTrackerProvider = new Object();
        this.broadcastDispatcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 14);
        this.powerRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 12);
        this.enhancedEstimatesGoogleImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 26);
        this.provideDemoModeControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 27);
        this.provideBatteryControllerLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 29);
        this.batteryControllerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 28);
        this.provideBgHandlerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 30, 1);
        this.provideReverseWirelessChargerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 32);
        this.provideUsbManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 33);
        this.provideIThermalServiceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 34);
        this.reverseChargingControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 31);
        this.provideBatteryControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 25);
        this.provideWirelessChargerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 36);
        this.wirelessChargerCommanderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 37);
        this.javaAdapterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 39);
        this.secureSettingsImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 44, 1);
        this.deviceProvisionedControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 43);
        this.provideDeviceProvisionedControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 42);
        this.deviceProvisioningInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 41);
        this.provideDisplayTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 47);
        this.filesProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 49);
        this.logBufferEulogizerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 48);
        this.systemUIConfigDumpableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 50);
        this.powerInteractorProvider = new Object();
        this.provideCommandQueueProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 46);
        this.featureFlagsClassicReleaseProvider = new Object();
        this.splitShadeStateControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 52);
        this.remoteInputQuickSettingsDisablerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 51);
        this.provideDisableFlagsRepositoryLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 53);
        this.disableFlagsLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 54);
        this.disableFlagsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 45);
        this.alwaysOnDisplayPolicyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 56);
        this.providesLeakDetectorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 58);
        this.statusBarStateControllerImplProvider = new Object();
        this.statusBarKeyguardViewManagerProvider = new Object();
        this.authControllerProvider = new Object();
        this.telephonyListenerManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 67);
        this.userRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 70);
        this.selectedUserInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 69);
        this.keyguardUpdateMonitorProvider = new Object();
        this.activeUnlockConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 68);
        this.provideKeyguardUpdateMonitorLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 71);
        this.sessionTrackerProvider = new Object();
        this.faceWakeUpTriggersConfigImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 72);
        this.devicePostureControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 73);
        this.provideFingerprintInteractiveToAuthProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 74);
        this.provideTaskStackChangeListenersProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 75);
        DelegateFactory.setDelegate(this.keyguardUpdateMonitorProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 66, 1)));
        this.falsingDataProvider = new Object();
        this.historyTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 78);
        this.keyguardStateControllerImplProvider = new Object();
        this.newKeyguardViewMediatorProvider = new Object();
        this.dozeParametersProvider = new Object();
        this.provideDozeLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 84);
        this.dozeLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 83);
        this.dozeScrimControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 82);
        this.tunerServiceImplProvider = new Object();
        this.activeNotificationListRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 90);
        this.activeNotificationsInteractorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 89, 1);
        this.notifLiveDataStoreImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 91);
        this.provideNotificationsLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 94);
        this.notificationDismissibilityProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 95);
        this.notifCollectionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 93);
        this.notifPipelineChoreographerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 97);
        this.notificationClickNotifierProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 99);
        this.notificationInteractionTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 98);
        this.shadeListBuilderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 96);
        this.renderStageManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 100);
        this.notifPipelineProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 92);
        this.notificationVisibilityProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 88);
        this.overviewProxyServiceProvider = new Object();
        this.navigationModeControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 103);
        this.containerConfigProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 107, 1);
        this.sceneDataSourceDelegatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 108);
        this.provideSceneFrameworkLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 109);
        this.implProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 113);
        this.keyguardSecurityModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 114);
        this.connectivitySlotsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 118);
        this.provideSharedConnectivityTableLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 120);
        this.connectivityInputLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 119);
        this.connectivityRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 117);
        this.provideMobileInputLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 122);
        this.mobileInputLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 121);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.tableLogBufferFactoryProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 124, 1));
        this.provideMobileSummaryLogBufferProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 123, 1));
        this.bgCoroutineContextProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 126, 1));
        this.provideAirplaneTableLogBufferProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 127, 1));
        this.airplaneModeRepositoryImplProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 125, 1));
        this.disabledWifiRepositoryProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 131, 1));
        this.provideWifiInputLogBufferProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 134, 1));
        this.wifiInputLoggerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 133, 1));
        this.provideWifiTableLogBufferProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 135, 1));
        this.factoryProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 132, 1));
        this.provideWifiRepositoryDaggerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 130, 1));
        this.wifiPickerTrackerFactoryProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 138, 1));
        this.provideWifiTrackerLibInputLogBufferProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 139, 1));
        this.provideWifiTrackerLibTableLogBufferProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 140, 1));
        this.factoryProvider2 = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 137, 1));
        this.provideWifiRepositoryViaTrackerLibDaggerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 136, 1));
        this.provideRealWifiRepositoryProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 129, 1));
        this.demoModeWifiDataSourceProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 141, 1));
        this.wifiRepositorySwitcherProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 128, 1));
        this.carrierConfigRepositoryProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 142, 1));
        this.factoryProvider3 = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 143, 1));
        this.mobileConnectionsRepositoryImplProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 116, 1));
        this.demoModeMobileConnectionDataSourceProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 144, 1));
        this.mobileRepositorySwitcherProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 115, 1));
        this.authenticationRepositoryImplProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 112, 1));
        this.authenticationInteractorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 111, 1));
        this.keyguardBypassControllerProvider = new Object();
        this.dozeTransitionListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 147);
        this.dreamOverlayCallbackControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 148);
        this.bgApplicationScopeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 151);
        this.displayRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 152);
        this.displayStateRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 150);
        this.configurationRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 153);
        this.facePropertyRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 149);
        this.keyguardRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 146);
        this.deviceEntryRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 145);
        this.deviceUnlockedInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 110);
        this.sceneInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 106);
        this.sceneContainerPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 105);
        this.provideSysUiStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 104);
        this.accessibilityButtonModeObserverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 155);
        this.accessibilityButtonTargetsObserverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 156);
        this.notificationShadeWindowControllerImplProvider = new Object();
        this.shadeInteractorImplProvider = new Object();
        this.provideFaceAuthLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 163);
        this.faceAuthenticationLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 162);
        this.devicePostureRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 165);
        this.fingerprintPropertyRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 166);
        this.biometricSettingsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 164);
        this.deviceEntryFingerprintAuthRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 167);
        this.trustRepositoryLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 169);
        this.trustRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 168);
        this.provideBouncerLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 172);
        this.keyguardBouncerRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 171);
        this.configurationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 173);
        this.shadeRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 174);
        this.keyguardTransitionRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 176);
        this.keyguardTransitionInteractorProvider = new Object();
        this.keyguardInteractorProvider = new Object();
        this.communalRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 179);
        this.provideAppWidgetManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 181);
        this.provideWakeLockLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 187);
        this.broadcastSenderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 186);
        this.provideStatusBarIconListProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 191);
        this.statusBarPipelineFlagsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 192);
        this.provideOemSatelliteInputLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 197);
        this.deviceBasedSatelliteRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 196);
        this.carrierConfigTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 199);
        this.userSetupRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 200);
        this.mobileIconsInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 198);
        this.deviceBasedSatelliteInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 195);
        this.deviceBasedSatelliteBindableIconProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 194);
        this.bindableIconsRegistryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 193);
        this.statusBarIconControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 190);
        this.dockObserverProvider = new Object();
        this.provideBackgroundDelayableExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 202);
        this.falsingManagerProxyProvider = new Object();
        this.provideBiometricLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 204);
        this.faceHelpMessageDeferralFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 203);
        this.provideKeyguardLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 205);
        this.alternateBouncerInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 206);
        this.bouncerMessageRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 208);
        this.countDownTimerUtilProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 209);
        this.bouncerViewImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 211);
        this.primaryBouncerCallbackInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 212);
        this.falsingCollectorImplProvider = new Object();
        this.falsingCollectorNoOpProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 214);
        this.providesFalsingCollectorLegacyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 213);
        this.dismissCallbackRegistryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 215);
        this.deviceEntryFaceAuthRepositoryImplProvider = new Object();
        this.primaryBouncerInteractorProvider = new Object();
        this.deviceEntryFingerprintAuthInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 217);
        this.systemUIDeviceEntryFaceAuthInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 216);
        DelegateFactory.setDelegate(this.primaryBouncerInteractorProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 210, 1)));
        this.bouncerMessageInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 207);
        this.indicationHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 218);
        this.keyguardIndicationControllerGoogleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 201);
        this.ambientIndicationRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 220);
        this.ambientIndicationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 219);
        this.reverseChargingViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 189);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3 = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = this.sysUIGoogleSysUIComponentImpl;
        this.provideReverseChargingViewControllerOptionalProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 188, 1));
        this.provideHealthManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 221);
        this.notificationListenerSettingsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 225);
        this.notificationListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 224);
        this.targetSdkResolverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 226);
        this.notifCoordinatorsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 228);
        this.notifInflationErrorManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 230);
        this.provideNotifInflationLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 231);
        this.notifInflaterImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 229);
        this.mediaContainerControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 233);
        this.notificationSectionsFeatureManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 234);
        this.sectionHeaderVisibilityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 235);
        this.notifViewBarnProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 236);
        this.shadeViewManagerFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 232);
        this.notifPipelineInitializerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 227);
        this.notifBindPipelineProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 237);
        this.provideNotifRemoteViewCacheProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 240, 1));
        this.notificationLockscreenUserManagerImplProvider = new Object();
        this.provideSmartReplyControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 242);
        this.remoteInputUriControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 243);
        this.provideNotificationRemoteInputLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 245);
        this.remoteInputControllerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 244);
        this.provideNotifInteractionLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 246);
        this.notificationRemoteInputManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 241);
        this.bindEventManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 248);
        this.conversationNotificationManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 247);
        this.provideNotifInflationLooperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 250);
        this.provideNotifInflationExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 249);
        this.smartReplyConstantsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 251);
        this.provideActivityManagerWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 252);
        this.provideDevicePolicyManagerWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 253);
        this.activityStarterImplProvider = new Object();
        this.keyguardDismissUtilProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 254);
        this.provideNotificationHeadsUpLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 256);
        this.groupMembershipManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 257);
        this.visualStabilityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 258);
        this.accessibilityManagerWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 259);
        this.headsUpManagerPhoneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 255);
        this.provideNotifRemoteViewsFactoryContainerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 261, 1));
        this.providerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 260);
        this.notificationContentInflaterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 239);
        this.rowContentBindStageProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 238);
        this.windowRootViewVisibilityRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 264);
        this.windowRootViewVisibilityInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 263);
        this.provideNotificationPanelLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 265);
        this.provideLegacyLoggerOptionalProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 262);
        this.rowInflaterTaskProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 267, 1);
        this.iconManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 268);
        this.notificationRowBinderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 266);
        this.providesMediaTimeoutListenerLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 273);
        this.mediaTimeoutLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 272);
        this.mediaFlagsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 274);
        this.mediaTimeoutListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 271);
        this.provideMediaBrowserBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 277);
        this.resumeMediaBrowserLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 276);
        this.mediaResumeListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 275);
        this.provideLocalBluetoothControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 278);
        this.provideMediaMuteAwaitLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 281);
        this.mediaMuteAwaitLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 280);
        this.mediaMuteAwaitConnectionManagerFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 279);
        this.mediaUiEventLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 282);
        this.mediaDataManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 270);
        this.provideNotificationMediaManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 269);
        this.headsUpViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 283);
        this.animatedImageNotificationManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 284);
        this.peopleSpaceWidgetManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 285);
        this.notificationsControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 223);
        this.notificationsControllerStubProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 286, 1);
        this.provideNotificationsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 222);
        this.fragmentServiceProvider = new Object();
        this.factoryProvider4 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 288);
        DelegateFactory.setDelegate(this.fragmentServiceProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 287, 1)));
        this.factoryProvider5 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 291);
        this.darkIconDispatcherImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 290);
        this.letterboxBackgroundProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 295);
        this.letterboxAppearanceCalculatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 294);
        this.ongoingCallRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 296);
        this.statusBarModePerDisplayRepositoryFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 293);
        this.statusBarModeRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 292);
        this.lightBarControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 289);
        this.autoHideControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 297);
        this.providesStatusBarWindowViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 300);
        this.sysUICutoutProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 302);
        this.statusBarContentInsetsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 301);
        this.statusBarWindowControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 299);
        this.ongoingCallLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 305);
        this.provideSwipeUpLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 308);
        this.swipeUpGestureLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 307);
        this.swipeStatusBarAwayGestureHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 306);
        this.ongoingCallControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 304);
        this.privacyConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 312);
        this.provideIndividualSensorPrivacyControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 315);
        this.appOpsControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 314);
        this.providePrivacyLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 316);
        this.appOpsPrivacyItemMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 313);
        this.privacyItemControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 311);
        this.connectedDisplayInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 317);
        this.systemEventCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 310);
        initialize4();
        initialize5();
        initialize6();
        initialize7();
        initialize8();
        initialize9();
        initialize10();
        initialize11();
        initialize12();
        initialize13();
        initialize14();
        initialize15();
    }

    public final AudioManagerEventsReceiverImpl audioManagerEventsReceiver() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new AudioManagerEventsReceiverImpl((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
    }

    public final AudioRepositoryImpl audioRepository() {
        AudioManagerEventsReceiverImpl audioManagerEventsReceiver = audioManagerEventsReceiver();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new AudioRepositoryImpl(audioManagerEventsReceiver, (AudioManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAudioManagerProvider.get(), (CoroutineContext) this.bgCoroutineContextProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
    }

    public final BottomAreaSection bottomAreaSection() {
        return new BottomAreaSection(keyguardQuickAffordancesCombinedViewModel(), (FalsingManager) this.falsingManagerProxyProvider.get(), (VibratorHelper) this.vibratorHelperProvider.get(), (KeyguardIndicationController) this.keyguardIndicationControllerGoogleProvider.get(), keyguardIndicationAreaViewModel());
    }

    public final CarrierTextManager.Builder carrierTextManagerBuilder() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        Resources resources = context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        return new CarrierTextManager.Builder(context, resources, (WifiRepository) this.wifiRepositorySwitcherProvider.get(), (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideTelephonyManagerProvider.get(), (TelephonyListenerManager) this.telephonyListenerManagerProvider.get(), (WakefulnessLifecycle) this.wakefulnessLifecycleProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) this.provideBackgroundExecutorProvider.get(), (KeyguardUpdateMonitor) this.keyguardUpdateMonitorProvider.get(), new CarrierTextManagerLogger((LogBuffer) this.provideCarrierTextManagerLogProvider.get()));
    }

    public final ClockMessageBuffers clockMessageBuffers() {
        return new ClockMessageBuffers((LogBuffer) this.provideKeyguardClockLogProvider.get(), (LogBuffer) this.provideKeyguardSmallClockLogProvider.get(), (LogBuffer) this.provideKeyguardLargeClockLogProvider.get());
    }

    public final ConfigurationState configurationState() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new ConfigurationState((ConfigurationController) this.configurationControllerImplProvider.get(), (Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (LayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providerLayoutInflaterProvider.get());
    }

    public final DateFormatUtil dateFormatUtil() {
        return new DateFormatUtil(this.sysUIGoogleGlobalRootComponentImpl.context, (UserTracker) this.provideUserTrackerProvider.get());
    }

    public final DefaultClockSection defaultClockSection() {
        return new DefaultClockSection((KeyguardClockViewModel) this.keyguardClockViewModelProvider.get(), (KeyguardClockInteractor) this.keyguardClockInteractorProvider.get(), (AodBurnInViewModel) this.aodBurnInViewModelProvider.get());
    }

    public final Map getStartables() {
        MapBuilder mapBuilder = new MapBuilder(102);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.provideCoreStartableProvider;
        Map map = mapBuilder.contributions;
        map.put(NotificationLogger.class, switchingProvider);
        map.put(CoroutineInitializer.class, this.coroutineScopeCoreStartableProvider);
        map.put(BroadcastDispatcherStartable.class, this.broadcastDispatcherStartableProvider);
        map.put(AuthController.class, this.authControllerProvider);
        map.put(SideFpsOverlayViewBinder.class, this.sideFpsOverlayViewBinderProvider);
        map.put(ConfigurationControllerStartable.class, this.configurationControllerStartableProvider);
        map.put(FalsingCoreStartable.class, this.falsingCoreStartableProvider);
        map.put(FeatureFlagsReleaseStartable.class, this.featureFlagsReleaseStartableProvider);
        map.put(FlagDependencies.class, this.flagDependenciesProvider);
        map.put(LetterboxBackgroundProvider.class, this.letterboxBackgroundProvider);
        map.put(MotionToolStartable.class, this.motionToolStartableProvider);
        map.put(QSFragmentStartable.class, this.qSFragmentStartableProvider);
        map.put(OngoingCallController.class, this.ongoingCallControllerProvider);
        map.put(LightBarController.class, this.lightBarControllerProvider);
        map.put(StatusBarModeRepositoryImpl.class, this.statusBarModeRepositoryImplProvider);
        map.put(MobileUiAdapter.class, this.mobileUiAdapterProvider);
        map.put(CarrierConfigCoreStartable.class, this.carrierConfigCoreStartableProvider);
        map.put(WifiRepositoryDagger.class, this.provideWifiRepositoryDaggerProvider);
        map.put(StatusBarIconViewBindingFailureTracker.class, this.statusBarIconViewBindingFailureTrackerProvider);
        map.put(UserSwitcherDialogCoordinator.class, this.userSwitcherDialogCoordinatorProvider);
        map.put(BiometricNotificationService.class, this.biometricNotificationServiceProvider);
        map.put(ClipboardListener.class, this.clipboardListenerProvider);
        map.put(GlobalActionsComponent.class, this.globalActionsComponentProvider);
        map.put(InstantAppNotifier.class, this.instantAppNotifierProvider);
        map.put(KeyboardUI.class, this.keyboardUIProvider);
        map.put(MediaProjectionTaskSwitcherCoreStartable.class, this.mediaProjectionTaskSwitcherCoreStartableProvider);
        map.put(KeyguardBiometricLockoutLogger.class, this.keyguardBiometricLockoutLoggerProvider);
        map.put(KeyguardViewMediator.class, this.newKeyguardViewMediatorProvider);
        map.put(LatencyTester.class, this.latencyTesterProvider);
        map.put(DisplaySwitchLatencyTracker.class, this.displaySwitchLatencyTrackerProvider);
        map.put(ImmersiveModeConfirmation.class, this.immersiveModeConfirmationProvider);
        map.put(RingtonePlayer.class, this.ringtonePlayerProvider);
        map.put(GesturePointerEventListener.class, this.gesturePointerEventListenerProvider);
        map.put(SessionTracker.class, this.sessionTrackerProvider);
        map.put(ShortcutKeyDispatcher.class, this.shortcutKeyDispatcherProvider);
        map.put(SliceBroadcastRelayHandler.class, this.sliceBroadcastRelayHandlerProvider);
        map.put(StorageNotification.class, this.storageNotificationProvider);
        map.put(ThemeOverlayController.class, this.themeOverlayControllerGoogleProvider);
        map.put(MediaOutputSwitcherDialogUI.class, this.mediaOutputSwitcherDialogUIProvider);
        map.put(Magnification.class, this.magnificationProvider);
        map.put(WMShell.class, this.wMShellProvider);
        map.put(MediaTttSenderCoordinator.class, this.mediaTttSenderCoordinatorProvider);
        map.put(MediaTttChipControllerReceiver.class, this.mediaTttChipControllerReceiverProvider);
        map.put(MediaTttCommandLineHelper.class, this.mediaTttCommandLineHelperProvider);
        map.put(ChipbarCoordinator.class, this.chipbarCoordinatorProvider);
        map.put(StylusUsiPowerStartable.class, this.stylusUsiPowerStartableProvider);
        map.put(PhysicalKeyboardCoreStartable.class, this.physicalKeyboardCoreStartableProvider);
        map.put(MuteQuickAffordanceCoreStartable.class, this.muteQuickAffordanceCoreStartableProvider);
        map.put(DreamMonitor.class, this.dreamMonitorProvider);
        map.put(AssistantAttentionMonitor.class, this.assistantAttentionMonitorProvider);
        map.put(KeyguardViewConfigurator.class, this.keyguardViewConfiguratorProvider);
        map.put(ScrimController.class, this.scrimControllerProvider);
        map.put(StatusBarHeadsUpChangeListener.class, this.statusBarHeadsUpChangeListenerProvider);
        map.put(BackActionInteractor.class, this.backActionInteractorProvider);
        map.put(KeyguardDismissActionBinder.class, this.keyguardDismissActionBinderProvider);
        map.put(KeyguardDismissBinder.class, this.keyguardDismissBinderProvider);
        map.put(CommunalLoggerStartable.class, this.communalLoggerStartableProvider);
        map.put(CommunalSceneStartable.class, this.communalSceneStartableProvider);
        map.put(CommunalAppWidgetHostStartable.class, this.communalAppWidgetHostStartableProvider);
        map.put(HomeControlsDreamStartable.class, this.homeControlsDreamStartableProvider);
        map.put(UserFileManagerImpl.class, this.userFileManagerImplProvider);
        map.put(ControlsStartable.class, this.controlsStartableProvider);
        map.put(GoogleServices.class, this.googleServicesProvider);
        map.put(AdaptivePPNService.class, this.adaptivePPNServiceProvider);
        map.put(ActiveUnlockChipbarManager.class, this.activeUnlockChipbarManagerProvider);
        map.put(RefreshRateRequesterBinder.class, this.refreshRateRequesterBinderProvider);
        map.put(AmbientIndicationCoreStartable.class, this.ambientIndicationCoreStartableProvider);
        map.put(ContextualTipsInteractor.class, this.contextualTipsInteractorProvider);
        map.put(Recents.class, this.provideRecentsProvider);
        map.put(ScreenPinningRequest.class, this.screenPinningRequestProvider);
        map.put(CentralSurfaces.class, this.centralSurfacesGoogleProvider);
        map.put(NotificationRemoteInputManager.class, this.notificationRemoteInputManagerProvider);
        map.put(SysuiStatusBarStateController.class, this.statusBarStateControllerImplProvider);
        map.put(NotificationGutsManager.class, this.notificationGutsManagerProvider);
        map.put(VisualInterruptionDecisionProvider.class, this.provideVisualInterruptionDecisionProvider);
        map.put(KeyguardNotificationVisibilityProviderImpl.class, this.keyguardNotificationVisibilityProviderImplProvider);
        map.put(NotificationMemoryMonitor.class, this.notificationMemoryMonitorProvider);
        map.put(KeyguardUpdateMonitor.class, this.keyguardUpdateMonitorProvider);
        map.put(SideFpsProgressBarViewBinder.class, this.sideFpsProgressBarViewBinderProvider);
        map.put(BouncerMessageAuditLogger.class, this.bouncerMessageAuditLoggerProvider);
        map.put(SystemUIDeviceEntryFaceAuthInteractor.class, this.systemUIDeviceEntryFaceAuthInteractorProvider);
        map.put(LiftToRunFaceAuthBinder.class, this.liftToRunFaceAuthBinderProvider);
        map.put(KeyguardTransitionCoreStartable.class, this.keyguardTransitionCoreStartableProvider);
        map.put(ResourceTrimmer.class, this.resourceTrimmerProvider);
        map.put(CollapsedStatusBarFragmentStartable.class, this.collapsedStatusBarFragmentStartableProvider);
        map.put(ConnectingDisplayViewModel.class, this.connectingDisplayViewModelProvider);
        map.put(VolumeUI.class, this.volumeUIProvider);
        map.put(MediaMuteAwaitConnectionCli.class, this.mediaMuteAwaitConnectionCliProvider);
        map.put(NearbyMediaDevicesManager.class, this.nearbyMediaDevicesManagerProvider);
        map.put(PowerUI.class, this.powerUIProvider);
        map.put(QSPipelineCoreStartable.class, this.qSPipelineCoreStartableProvider);
        map.put(RearDisplayDialogController.class, this.rearDisplayDialogControllerProvider);
        map.put(SceneContainerStartable.class, this.sceneContainerStartableProvider);
        map.put(WindowRootViewVisibilityInteractor.class, this.windowRootViewVisibilityInteractorProvider);
        map.put(ScreenDecorations.class, this.screenDecorationsProvider);
        map.put(KeyguardSmartspaceStartable.class, this.keyguardSmartspaceStartableProvider);
        map.put(SystemActions.class, this.systemActionsProvider);
        map.put(UnfoldInitializationStartable.class, this.unfoldInitializationStartableProvider);
        map.put(ShadeController.class, this.provideShadeControllerProvider);
        map.put(AuthRippleController.class, this.authRippleControllerProvider);
        map.put(ToastUI.class, this.toastUIProvider);
        map.put(UnfoldTraceLogger.class, this.unfoldTraceLoggerProvider);
        return mapBuilder.build();
    }

    public final GlobalSettingsImpl globalSettingsImpl() {
        return new GlobalSettingsImpl((ContentResolver) this.sysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get());
    }

    public final GoogleAmbientIndicationSection googleAmbientIndicationSection() {
        return new GoogleAmbientIndicationSection(new KeyguardAmbientIndicationViewModel((AmbientIndicationInteractor) this.ambientIndicationInteractorProvider.get(), (BurnInInteractor) this.burnInInteractorProvider.get()), (AodAlphaViewModel) this.aodAlphaViewModelProvider.get(), (ShadeViewController) this.notificationPanelViewControllerProvider.get(), (PowerInteractor) this.powerInteractorProvider.get(), (KeyguardUpdateMonitor) this.keyguardUpdateMonitorProvider.get(), (ActivityStarter) this.activityStarterImplProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11) this.factoryProvider13.get());
    }

    public final ImageExporter imageExporter() {
        return new ImageExporter((ContentResolver) this.sysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (FeatureFlags) this.featureFlagsClassicReleaseProvider.get());
    }

    /* JADX WARNING: type inference failed for: r0v56, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v96, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v136, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    public final void initialize10() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.deviceControlsControllerImplProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 884, 1));
        this.walletControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 885);
        this.provideAutoTileManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 882);
        DelegateFactory.setDelegate(this.qSTileHostProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 795, 1)));
        this.provideQSTileListLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 888);
        this.provideQSAutoAddLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 889);
        this.providesQSRestoreLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 890);
        this.retailModeSettingsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 891);
        this.defaultTilesQSHostRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 893);
        this.factoryProvider27 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 892);
        this.tileSpecSettingsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 887);
        this.providePackageChangeRepoLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 898);
        this.packageUpdateLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 897);
        this.factoryProvider28 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 896);
        this.packageChangeRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 895);
        this.installedTilesComponentRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 894);
        this.minimumTilesResourceRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 899);
        this.currentTilesInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 886);
        this.builderProvider3 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 900);
        DelegateFactory.setDelegate(this.qSHostAdapterProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 794, 1)));
        this.centralSurfacesCommandQueueCallbacksProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 791);
        this.provideSensorPrivacyControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 901);
        this.wiredChargingRippleControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 902);
        this.lightRevealScrimRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 905);
        this.lightRevealScrimInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 904);
        this.lightRevealScrimViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 903, 1);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 185, this.centralSurfacesGoogleProvider);
        DelegateFactory.setDelegate(this.optionalOfCentralSurfacesProvider, new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 184, 1));
        DelegateFactory.setDelegate(this.activityStarterImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 183, 1)));
        this.provideCommunalAppWidgetHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 182);
        this.provideCommunalWidgetHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 906);
        this.provideCommunalWidgetDaoProvider = new Object();
        this.provideCommunalDatabaseProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 908);
        DelegateFactory.setDelegate(this.provideCommunalWidgetDaoProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 907, 1)));
        this.communalWidgetRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 180);
        this.communalPrefsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 909);
        this.communalMediaRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 910);
        this.provideGlanceableHubBcSmartspaceDataPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 913);
        this.communalSmartspaceControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 912);
        this.smartspaceRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 911);
        DelegateFactory.setDelegate(this.communalInteractorProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 178, 1)));
        this.swipeToDismissInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 914);
        this.fromLockscreenTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 177);
        this.fromPrimaryBouncerTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 915);
        this.fromAodTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 916);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 175, this.keyguardTransitionInteractorProvider);
        DelegateFactory.setDelegate(this.keyguardInteractorProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 170, 1)));
        this.provideFaceDetectTableLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 917);
        this.provideFaceAuthTableLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 918);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 161, this.deviceEntryFaceAuthRepositoryImplProvider);
        DelegateFactory.setDelegate(this.deviceEntryInteractorProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 160, 1)));
        this.provideShadeTouchLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 919);
        this.shadeControllerSceneImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 159);
        this.shadeControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 920);
        DelegateFactory.setDelegate(this.provideShadeControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 158, 1)));
        this.contextComponentResolverProvider = new Object();
        this.provideRecentsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 921);
        this.systemActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 157);
        this.navigationBarEdgePanelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 922, 1);
        this.providsBackGestureTfClassifierProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 923);
        this.navBarHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 154);
        this.taskbarDelegateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 924);
        DelegateFactory.setDelegate(this.navigationBarControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 102, 1)));
        this.inWindowLauncherUnlockAnimationRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 927);
        this.keyguardSurfaceBehindRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 928);
        this.inWindowLauncherUnlockAnimationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 926);
        this.inWindowLauncherAnimationViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 929);
        this.inWindowLauncherUnlockAnimationManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 925);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 101, this.overviewProxyServiceProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 87, this.notificationLockscreenUserManagerImplProvider);
        DelegateFactory.setDelegate(this.keyguardBypassControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 86, 1)));
        this.provideShadeWindowLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 930);
        DelegateFactory.setDelegate(this.notificationShadeWindowControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 85, 1)));
        this.biometricUnlockInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 931);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 81, this.biometricUnlockControllerProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 80, this.keyguardUnlockAnimationControllerProvider);
        DelegateFactory.setDelegate(this.keyguardStateControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 79, 1)));
        this.brightLineFalsingManagerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 77, 1);
        DelegateFactory.setDelegate(this.falsingManagerProxyProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 76, 1)));
        this.udfpsHapticsSimulatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 932);
        this.udfpsShellProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 933);
        this.systemUIDialogManagerProvider = new Object();
        this.providesPluginExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 934);
        this.providesOverlapDetectorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 936);
        this.singlePointerTouchProcessorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 935);
        this.udfpsKeyguardAccessibilityDelegateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 937);
        this.fpsUnlockTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 938);
        this.deviceEntryUdfpsTouchOverlayViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 939, 1);
        this.defaultUdfpsTouchOverlayViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 940, 1);
        DelegateFactory.setDelegate(this.udfpsControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 65, 1)));
        this.sideFpsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 941);
        this.provideUdfpsLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 943);
        this.udfpsLoggerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 942, 1);
        this.logContextInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 944);
        this.faceSettingsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 947);
        this.promptRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 946);
        this.providesCredentialInteractorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 948, 1));
        this.promptCredentialInteractorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 945, 1);
        this.promptSelectorInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 949);
    }

    public final void initialize11() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.credentialViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 950, 1);
        this.promptViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 951, 1);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 64, this.authControllerProvider);
        DelegateFactory.setDelegate(this.sessionTrackerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 63, 1)));
        this.factoryProvider29 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 953);
        this.keyguardDisplayManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 952);
        this.screenOnCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 954);
        this.keyguardSurfaceBehindInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 957);
        this.keyguardSurfaceBehindParamsApplierProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 956);
        this.windowManagerLockscreenVisibilityManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 955);
        DelegateFactory.setDelegate(this.newKeyguardViewMediatorProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 62, 1)));
        this.windowManagerLockscreenVisibilityInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 958);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 61, this.statusBarKeyguardViewManagerProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 60, this.systemUIDialogManagerProvider);
        DelegateFactory.setDelegate(this.factoryProvider6, new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 59, 1));
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 57, this.tunerServiceImplProvider);
        DelegateFactory.setDelegate(this.dozeParametersProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 55, 1)));
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 960);
        this.shadeInteractorLegacyImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 961);
        this.provideBaseShadeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 959);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 40, this.shadeInteractorImplProvider);
        DelegateFactory.setDelegate(this.statusBarStateControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 38, 1)));
        this.dockAlignmentControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 962);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 35, this.dockObserverProvider);
        DelegateFactory.setDelegate(this.falsingDataProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 24, 1)));
        this.asyncSensorManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 965);
        this.postureDependentProximitySensorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 964, 1);
        this.proximitySensorImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 966, 1);
        this.provideProximitySensorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 963, 1);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 23, this.falsingCollectorImplProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 11, this.powerInteractorProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 8, this.featureFlagsClassicReleaseProvider);
        DelegateFactory.setDelegate(this.provideUserTrackerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 7, 1)));
        this.activityTaskManagerProxyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 967);
        DelegateFactory.setDelegate(this.controlsListingControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 6, 1)));
        this.controlsProviderSelectorActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 3, 1);
        this.controlsFavoritingActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 968, 1);
        this.controlsEditingActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 969, 1);
        this.controlsRequestDialogProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 970, 1);
        this.controlsSettingsDialogManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 972);
        this.controlsActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 971, 1);
        this.mediaProjectionAppSelectorActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 973, 1);
        this.mediaProjectionPermissionActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 974, 1);
        this.launchNoteTaskActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 975, 1);
        this.launchNotesRoleSettingsTrampolineActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 976, 1);
        this.createNoteTaskShortcutActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 977, 1);
        this.walletActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 978, 1);
        this.tunerActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 979, 1);
        this.foregroundServicesDialogProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 980, 1);
        this.workLockActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 981, 1);
        this.factoryProvider30 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 983);
        this.brightnessDialogProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 982, 1);
        this.contrastDialogActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 984, 1);
        this.usbDebuggingActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 985, 1);
        this.usbDebuggingSecondaryUserActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 986, 1);
        this.usbPermissionActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 987, 1);
        this.usbConfirmActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 988, 1);
        this.usbAccessoryUriActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 989, 1);
        this.createUserActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 990, 1);
        this.peopleTileRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 992);
        this.peopleWidgetRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 993);
        this.peopleSpaceActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 991, 1);
        this.longScreenshotDataProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 995);
        this.actionIntentExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 996);
        this.longScreenshotActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 994, 1);
        this.appClipsTrampolineActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 997, 1);
        this.appClipsCrossProcessHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 999);
        this.appClipsActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 998, 1);
        this.launchConversationActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1000, 1);
        this.sensorUseStartedActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1001, 1);
        this.communalEditModeViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1003);
        this.factoryProvider31 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1004);
        this.editWidgetsActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1002, 1);
        this.switchToManagedProfileForCallActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1005, 1);
        this.factoryProvider32 = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1007, 1);
        this.volumePanelActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1006, 1);
        this.systemUISecondaryUserServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1008, 1);
        this.factoryProvider33 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1010);
        this.packageChangeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1012);
        this.homeControlsComponentInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1011);
        this.homeControlsDreamServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1009, 1);
        this.providesScrnshtNotifSmartActionsProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1016, 1);
        this.screenshotSmartActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1015);
        this.factoryProvider34 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1017);
        this.imageCaptureImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1018);
        this.assistContentRequesterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1019);
        this.screenshotSoundProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1021);
        this.screenshotSoundControllerImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1020, 1);
        this.factoryProvider35 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1014);
        this.screenshotPolicyImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1023);
        this.requestProcessorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1022);
        this.takeScreenshotExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1024);
        this.takeScreenshotServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1013, 1);
        this.screenshotProxyServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1025, 1);
        this.appClipsScreenshotHelperServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1026, 1);
        this.appClipsServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1027, 1);
        this.noteTaskControllerUpdateServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1028, 1);
        this.noteTaskBubblesServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1029, 1);
        this.walletContextualSuggestionsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1031);
        this.walletContextualLocationsServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1030, 1);
    }

    public final void initialize12() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.dozeServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1032, 1);
        this.provideLongRunningLooperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1035);
        this.provideLongRunningDelayableExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1034);
        this.imageWallpaperProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1033, 1);
        this.keyguardLifecyclesDispatcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1037);
        this.windowManagerLockscreenVisibilityViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1038);
        this.keyguardSurfaceBehindViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1039);
        this.keyguardServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1036, 1);
        this.dreamOverlayServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1040, 1);
        this.notificationListenerWithPluginsProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1041, 1);
        this.systemUIServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1042, 1);
        this.systemUIAuxiliaryDumpServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1043, 1);
        this.provideLongRunningExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1045);
        this.recordingServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1044, 1);
        this.issueRecordingServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1046, 1);
        this.contentResolverWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1050);
        this.factoryProvider36 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1049);
        this.columbusSettingsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1048);
        this.columbusStructuredDataManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1051);
        this.columbusTargetRequestServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1047, 1);
        this.lowLightClockDreamServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1052, 1);
        this.lockscreenWallpaperDreamServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1053, 1);
        this.launcherTileServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1054, 1);
        this.healthManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1056);
        this.healthServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1055, 1);
        this.batteryEventModuleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1059);
        this.provideHalDataSourceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1061);
        this.provideSettingsDataSourceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1062);
        this.provideSystemEventDataSourceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1060);
        this.batteryEventStateControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1058);
        this.batteryEventServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1057, 1);
        this.overviewProxyRecentsImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1063);
        this.actionProxyReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1064, 1);
        this.deleteScreenshotReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1065, 1);
        this.smartActionsReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1066, 1);
        this.mediaOutputDialogReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1067, 1);
        this.volumePanelDialogReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1068, 1);
        this.peopleSpaceWidgetPinnedReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1069, 1);
        this.peopleSpaceWidgetProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1070, 1);
        this.guestResetOrExitSessionReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1071, 1);
        this.keyboardShortcutsReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1072, 1);
        this.healthUpdateReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1073, 1);
        DelegateFactory.setDelegate(this.contextComponentResolverProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 2, 1)));
        this.tunablePaddingServiceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1075);
        this.uiOffloadThreadProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1076);
        this.dependencyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1074);
        this.provideCoreStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1077, 1);
        this.coroutineScopeCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1078);
        this.broadcastDispatcherStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1079, 1);
        this.biometricStatusRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1082);
        this.providesBiometricStatusInteractorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1081, 1));
        this.deviceEntrySideFpsOverlayInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1083);
        this.sideFpsLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1086);
        this.sideFpsSensorInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1085);
        this.sideFpsProgressBarViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1084);
        this.sideFpsOverlayViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1080);
        this.volumeUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1088);
        this.batterySaverConfirmationDialogProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1091, 1);
        this.severeLowBatteryNotificationProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1092, 1);
        this.powerNotificationWarningsGoogleImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1090);
        this.powerUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1089);
        this.rearDisplayDialogControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1093);
        this.privacyDotViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1095);
        this.privacyDotDecorProviderFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1096);
        this.provideScreenDecorationsLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1099);
        this.screenDecorationsLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1098);
        this.faceScanningProviderFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1097);
        this.screenDecorationsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1094);
        this.provideToastLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1101);
        this.toastUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1100);
        this.configurationControllerStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1087);
        this.falsingCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1102);
        this.featureFlagsReleaseStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1103, 1);
        this.flagDependenciesNotifierProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1105);
        this.flagDependenciesProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1104);
        this.motionToolStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1106);
        this.qSFragmentLegacyProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1108, 1);
        this.qSFragmentStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1107);
        this.carrierConfigCoreStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1109, 1);
        this.adapterProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1111, 1);
        this.userSwitcherDialogCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1110);
        this.biometricNotificationDialogFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1114);
        this.biometricNotificationBroadcastReceiverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1113);
        this.provideFingerprintReEnrollNotificationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1115);
        this.biometricNotificationServiceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1112);
        this.clipboardOverlayControllerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1117, 1);
        this.clipboardListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1116);
        this.instantAppNotifierProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1118);
        this.keyboardUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1119);
        this.activityTaskManagerTasksRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1124);
        this.mediaProjectionManagerRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1123);
        this.taskSwitchInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1122);
        this.taskSwitcherNotificationCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1121);
        this.mediaProjectionTaskSwitcherCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1120);
        this.keyguardBiometricLockoutLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1125);
        this.latencyTesterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1126);
        this.displaySwitchLatencyTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1127);
        this.immersiveModeConfirmationProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1128, 1);
        this.ringtonePlayerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1129);
    }

    public final void initialize13() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.gesturePointerEventDetectorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1131, 1));
        this.gesturePointerEventListenerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1130, 1);
        this.shortcutKeyDispatcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1132);
        this.sliceBroadcastRelayHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1133);
        this.storageNotificationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1134);
        this.themeOverlayApplierProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1136);
        this.themeOverlayControllerGoogleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1135);
        this.mediaOutputSwitcherDialogUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1137);
        this.modeSwitchesControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1139);
        this.magnificationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1138);
        this.wMShellProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1140);
        this.provideMediaTttSenderLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1143);
        this.mediaTttSenderLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1142);
        this.mediaTttFlagsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1144);
        this.mediaTttSenderUiEventLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1145);
        this.mediaTttSenderCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1141);
        this.provideMediaTttReceiverLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1148);
        this.mediaTttReceiverLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1147);
        this.mediaTttReceiverUiEventLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1149);
        this.mediaTttChipControllerReceiverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1146);
        this.mediaTttCommandLineHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1150);
        this.stylusUsiPowerUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1152);
        this.stylusUsiPowerStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1151);
        this.keyboardRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1157);
        this.keyboardBacklightInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1156);
        this.backlightDialogViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1155);
        this.keyboardBacklightDialogCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1154);
        this.stickyKeyDialogFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1159);
        this.userAwareSecureSettingsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1161);
        this.provideKeyboardLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1162);
        this.stickyKeysRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1160);
        this.stickyKeysIndicatorCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1158);
        this.physicalKeyboardCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1153);
        this.muteQuickAffordanceCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1163);
        this.provideMonitorTableLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1166);
        this.provideSystemUserMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1165);
        this.restartDozeListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1167);
        this.dreamMonitorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1164, 1);
        this.assistantAttentionMonitorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1168, 1);
        this.statusBarHeadsUpChangeListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1169);
        this.keyguardDismissActionBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1170);
        this.keyguardDismissBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1171);
        this.communalLoggerStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1172);
        this.communalSceneStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1173);
        this.communalAppWidgetHostStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1174);
        this.homeControlsDreamStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1175, 1);
        this.controlsStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1176);
        this.factoryProvider37 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1179);
        this.wakeModeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1180);
        this.assistInvocationEffectProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1183);
        this.cameraActionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1182);
        this.cameraVisibilityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1181);
        this.chargingStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1184);
        this.usbStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1185);
        this.launchOpaProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1188);
        this.settingsActionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1187);
        this.setupWizardProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1186);
        this.setupWizardActionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1189);
        this.gestureConfigurationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1192);
        this.jNIGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1191);
        this.snapshotConfigurationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1194);
        this.cHREGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1193);
        this.providesGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1190);
        this.serviceConfigurationGoogleProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1178, 1);
        this.provideQuickTapActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1198);
        this.provideQuickTapWakeLockProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1199);
        this.noOpGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1202);
        this.provideGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1201);
        this.gateFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1203);
        this.gestureControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1200);
        this.actionFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1204);
        this.columbusManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1197);
        this.broadcastFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1207);
        this.userFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1206);
        this.contentFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1208);
        this.backupManagerProxyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1209);
        this.columbusSettingsFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1205);
        this.columbusStarterImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1196);
        this.silenceAlertsDisabledProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1215);
        this.dismissTimerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1214);
        this.snoozeAlarmProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1216);
        this.silenceCallProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1217);
        this.settingsActionProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1218);
        this.provideFullscreenActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1213);
        this.unpinNotificationsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1219);
        this.assistInvocationEffectProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1223);
        this.launchOpaProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1222);
        this.manageMediaProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1224);
        this.takeScreenshotProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1225);
        this.launchOverviewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1226);
        this.openNotificationShadeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1227);
        this.keyguardVisibilityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1229);
        this.launchAppProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1228);
        this.toggleFlashlightProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1230);
        this.provideUserSelectedActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1221);
        this.powerManagerWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1231);
        this.userSelectedActionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1220);
        this.provideColumbusActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1212);
        this.hapticClickProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1232);
        this.userActivityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1233);
    }

    public final void initialize14() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.flagEnabledProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1234, 1));
        this.proximityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1236);
        this.keyguardProximityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1235);
        this.setupWizardProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1237);
        this.telephonyActivityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1238);
        this.vrModeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1239);
        this.powerStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1241);
        this.cameraVisibilityProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1240);
        this.powerSaveStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1242);
        this.sensorConfigurationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1249);
        this.lowSensitivitySettingAdjustmentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1248);
        this.provideGestureAdjustmentsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1247);
        this.gestureConfigurationProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1246);
        this.cHREGestureSensorProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1245);
        this.aiAiCHREGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1251);
        this.cHREGestureSensorDelegatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1250);
        this.gestureSensorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1252);
        this.provideGestureSensorProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1244);
        this.chargingStateProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1253);
        this.usbStateProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1254);
        this.systemKeyPressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1255);
        this.provideInputMonitorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1257, 1);
        this.screenTouchProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1256);
        this.gestureControllerProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1243);
        this.columbusServiceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1211);
        this.columbusServiceWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1210);
        this.provideColumbusStarterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1195);
        this.googleServicesProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1177);
        this.vpnNetworkMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1259);
        this.vpnPackageMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1260);
        this.adaptivePPNServiceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1258);
        this.activeUnlockChipbarManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1261);
        this.refreshRateInteractorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1263, 1);
        this.refreshRateRequesterBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1262);
        this.ambientIndicationCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1264);
        this.contextualTipsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1266);
        this.contextualTipsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1265);
        this.notificationMemoryDumperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1268);
        this.notificationMemoryLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1269);
        this.notificationMemoryMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1267);
        this.sideFpsProgressBarProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1271);
        this.sideFpsProgressBarViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1270);
        this.bouncerMessageAuditLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1272);
        this.liftToRunFaceAuthBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1273);
        this.fromGoneTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1275);
        this.fromDreamingLockscreenHostedTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1276);
        this.fromOccludedTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1277);
        this.fromDozingTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1278);
        this.fromAlternateBouncerTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1279);
        this.fromGlanceableHubTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1280);
        this.keyguardTransitionAuditLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1281);
        this.keyguardTransitionCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1274);
        this.resourceTrimmerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1282);
        this.collapsedStatusBarFragmentStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1283);
        this.connectingDisplayViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1284);
        this.mediaMuteAwaitConnectionCliProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1285);
        this.factoryProvider38 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1289);
        this.accessibilityQsShortcutsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1288);
        this.accessibilityTilesInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1287);
        this.factoryProvider39 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1291);
        this.factoryProvider40 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1292);
        this.castAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1293);
        this.dataSaverAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1294);
        this.deviceControlsAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1295);
        this.hotspotAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1296);
        this.nightDisplayAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1297);
        this.reduceBrightColorsAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1298);
        this.walletAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1299);
        this.workTileRestoreProcessorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1301);
        this.workTileAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1300);
        this.reverseChargingAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1302);
        this.factoryProvider41 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1304);
        this.autoAddSettingRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1303);
        this.autoAddInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1290);
        this.qSSettingsRestoredBroadcastRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1306);
        this.restoreReconciliationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1305);
        this.qSPipelineCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1286);
        this.sceneContainerStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1307);
        this.keyguardSmartspaceStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1308);
        this.unfoldInitializationStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1309, 1);
        this.unfoldTraceLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1310);
        this.notificationChannelsProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1311, 1);
        this.keyguardPreviewRendererFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1313);
        this.keyguardRemotePreviewManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1312);
        this.retailModeInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1314);
        this.provideUnseenNotificationLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1315);
        this.debugModeFilterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1316);
        this.notifUiAdjustmentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1317);
        this.remoteInputNotificationRebuilderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1318);
        this.notificationSettingsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1319);
        DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1320, 1));
        DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1321, 1));
        this.userSwitchDialogControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1322);
        this.keyguardStatusBarRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1325);
        this.keyguardStatusBarInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1324);
        this.keyguardStatusBarViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1323);
        this.provideNavBarButtonClickLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1326);
        this.provideNavbarOrientationTrackingLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1327);
        this.keyguardKeyboardInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1328);
        this.faceAuthAccessibilityDelegateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1329);
    }

    public final void initialize15() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1330, 1));
        this.factoryProvider42 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1332);
        this.provideAncSliceRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1331);
        this.provideLocalMediaRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1334);
        this.provideLocalMediaInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1333);
        this.provideNotificationsSoundPolicyRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1336);
        this.provideNotificationsSoundPolicyInteractrorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1335);
        this.provideMediaDeviceSessionRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1337);
        this.providesDreamOverlayNotificationCountProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1338);
        this.dreamOverlayStatusBarItemsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1339);
        this.dreamingToGlanceableHubTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1341);
        this.glanceableHubToDreamingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1342);
        this.dreamOverlayViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1340);
        this.bouncerlessScrimControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1343);
    }

    /* JADX WARNING: type inference failed for: r0v65, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v72, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v128, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v129, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v148, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v173, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    public final void initialize4() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.provideSystemStatusAnimationSchedulerLogBufferProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 319, 1));
        this.systemStatusAnimationSchedulerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 318);
        this.bindSystemStatusAnimationSchedulerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 309, 1));
        this.statusBarLocationPublisherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 320);
        this.provideSysUIUnfoldComponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 324);
        this.wakefulnessLifecycleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 326);
        this.unlockedScreenOffAnimationControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 325);
        this.screenOffAnimationControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 323);
        this.provideNotificationLockScreenLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 327);
        this.notificationsKeyguardViewStateRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 328);
        this.notificationWakeUpCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 322);
        this.sectionStyleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 329);
        this.legacyNotificationIconAreaControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 321);
        DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 330, 1));
        this.shadeExpansionStateManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 331);
        this.airplaneModeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 336);
        this.airplaneModeViewModelImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 335);
        this.provideMobileViewLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 340);
        this.mobileViewLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 339);
        this.provideVerboseMobileViewLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 342);
        this.verboseMobileViewLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 341);
        this.connectivityConstantsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 343);
        this.mobileIconsViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 338);
        this.provideFirstMobileSubShowingNetworkTypeIconProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 337);
        this.wifiInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 344);
        this.wifiConstantsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 345);
        this.wifiViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 334);
        this.wifiUiAdapterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 333);
        this.callbackHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 348);
        this.provideAccessPointControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 349);
        this.mobileSignalControllerFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 350);
        this.provideAnimationFeatureFlagsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 353);
        this.provideDialogTransitionAnimatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 352);
        this.internetDialogManagerProvider = new Object();
        this.toastFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 355);
        this.locationControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 356);
        this.wifiStateWorkerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 357);
        this.factoryProvider6 = new Object();
        this.factoryProvider7 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 354);
        DelegateFactory.setDelegate(this.internetDialogManagerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 351, 1)));
        this.provideStatusBarNetworkControllerBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 358);
        this.networkControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 347);
        this.mobileContextProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 346);
        this.mobileUiAdapterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 359);
        this.factoryProvider8 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 332);
        this.lightsOutInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 361);
        this.collapsedStatusBarViewModelImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 360);
        this.collapsedStatusBarViewBinderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 362);
        this.statusBarWindowStateControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 364);
        this.statusBarHideIconsForBouncerManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 363);
        this.falsingInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 370);
        this.sceneContainerViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 369);
        this.bouncerRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 375);
        this.simBouncerRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 377);
        this.simBouncerInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 376);
        this.bouncerInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 374);
        this.inputMethodRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 379);
        this.inputMethodInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 378);
        this.emergencyServicesRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 381);
        this.telephonyRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 383);
        this.telephonyInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 382);
        this.emergencyAffordanceManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 384);
        this.bouncerActionButtonInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 380);
        this.implProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 385);
        this.headlessSystemUserModeImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 388);
        this.refreshUsersSchedulerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 389);
        this.userSwitcherInteractorProvider = new Object();
        this.guestUserInteractorProvider = new Object();
        this.userSwitcherControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 393);
        this.factoryProvider9 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 392);
        this.guestResumeSessionReceiverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 391);
        this.factoryProvider10 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 394);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 390, this.guestUserInteractorProvider);
        DelegateFactory.setDelegate(this.userSwitcherInteractorProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 387, 1)));
        this.userSwitcherViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 386);
        this.viewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 373);
        this.bouncerDialogFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 395);
        this.bouncerSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 372);
        this.communalInteractorProvider = new Object();
        this.communalTutorialDisabledRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 399);
        this.communalSettingsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 401);
        this.provideCommunalTableLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 402);
        this.communalSettingsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 400);
        this.communalTutorialInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 398);
        this.mediaHostStatesManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 407);
        this.provideMediaViewLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 409);
        this.mediaViewLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 408);
        this.provideBackgroundRepeatableExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 410);
        this.provideNearbyMediaDevicesLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 413);
        this.nearbyMediaDevicesLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 412);
        this.nearbyMediaDevicesManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 411);
        this.mediaCarouselControllerProvider = new Object();
        this.activityIntentHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 414);
        this.factoryProvider11 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 416);
        this.broadcastDialogControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 415);
        this.mediaControlPanelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 406, 1);
        this.provideMediaCarouselControllerBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 418);
        this.mediaCarouselControllerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 417);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 405, this.mediaCarouselControllerProvider);
    }

    /* JADX WARNING: type inference failed for: r0v22, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v53, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v66, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v67, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v87, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v115, types: [javax.inject.Provider, java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v158, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v159, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v160, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v161, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    public final void initialize5() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.provideDreamLogBufferProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 420, 1));
        this.dreamOverlayStateControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 419);
        this.mediaHierarchyManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 404);
        this.providesCommunalMediaHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 403);
        this.provideCommunalLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 421);
        this.communalViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 397);
        this.communalSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 396);
        this.notificationStackAppearanceRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 425);
        this.notificationStackAppearanceInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 424);
        this.notificationsPlaceholderViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 423);
        this.goneSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 422);
        this.deviceEntryInteractorProvider = new Object();
        this.keyguardLongPressInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 429);
        this.keyguardLongPressViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 428);
        this.lockscreenSceneViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 427);
        this.provideKeyguardClockLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 435);
        this.provideKeyguardSmallClockLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 436);
        this.provideKeyguardLargeClockLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 437);
        this.getClockRegistryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 434);
        this.zenModeControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 438);
        this.keyguardClockRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 433);
        this.keyguardClockInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 432);
        this.keyguardBottomAreaInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 442);
        this.userFileManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 446);
        this.keyguardQuickAffordanceLocalUserSelectionManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 445);
        this.keyguardQuickAffordanceRemoteUserSelectionManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 447);
        this.keyguardQuickAffordanceLegacySettingSyncerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 448);
        this.centralSurfacesGoogleProvider = new Object();
        this.cameraGestureHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 450);
        this.cameraQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 449);
        this.doNotDisturbQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 451);
        this.flashlightControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 453);
        this.flashlightQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 452);
        this.providesControlsFeatureEnabledProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 456);
        this.controlsControllerImplProvider = new Object();
        this.controlsListingControllerImplProvider = new Object();
        this.controlsMetricsLoggerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 460);
        this.vibratorHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 461);
        this.controlsSettingsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 462);
        this.controlActionCoordinatorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 459);
        this.customIconCacheProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 463);
        this.selectedComponentRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 464);
        this.controlsUiControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 458);
        this.factoryProvider12 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 466);
        this.controlsBindingControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 465);
        DelegateFactory.setDelegate(this.controlsControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 457, 1)));
        this.controlsComponentProvider = new Object();
        this.googleControlsTileResourceConfigurationImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 467);
        DelegateFactory.setDelegate(this.controlsComponentProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 455, 1)));
        this.homeControlsKeyguardQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 454);
        this.ringerModeTrackerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 469);
        this.muteQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 468);
        this.provideQuickAccessWalletClientProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 472);
        this.quickAccessWalletControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 471);
        this.quickAccessWalletKeyguardQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 470);
        this.qRCodeScannerControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 474);
        this.qrCodeScannerKeyguardQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 473);
        this.videoCameraQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 475);
        this.noteTaskBubblesControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 477);
        this.noteTaskControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 476);
        this.stylusManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 478);
        ? obj = new Object();
        this.keyguardQuickAffordanceRepositoryProvider = obj;
        DelegateFactory.setDelegate(obj, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 444, 1)));
        this.keyguardQuickAffordanceInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 443);
        this.burnInInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 479);
        this.deviceEntryUdfpsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 481);
        this.provideKeyguardTransitionAnimationLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 483);
        this.keyguardTransitionAnimationFlowProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 482);
        this.aodToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 480);
        this.dozingToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 484);
        this.dreamingHostedToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 485);
        this.fromDreamingTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 487);
        this.dreamingToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 486);
        this.goneToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 488);
        this.occludedToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 489);
        this.offToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 490);
        this.primaryBouncerToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 491);
        this.lockscreenToAodTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 492);
        this.lockscreenToDozingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 493);
        this.lockscreenToDreamingHostedTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 494);
        this.lockscreenToDreamingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 495);
        this.lockscreenToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 496);
        this.lockscreenToOccludedTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 497);
        this.lockscreenToPrimaryBouncerTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 498);
        this.providesNotificationPanelViewProvider = new Object();
        this.udfpsControllerProvider = new Object();
        this.biometricUnlockControllerProvider = new Object();
        this.providesNotificationShadeWindowViewProvider = new Object();
        this.provideScrimLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 502);
        this.providesLightRevealScrimProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 501);
        this.deviceEntrySourceInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 504);
        this.authRippleInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 503);
        this.providesAuthRippleViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 505);
        this.authRippleControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 500);
        this.lockIconViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 499);
        this.alternateBouncerToAodTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 507);
        this.alternateBouncerToDozingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 508);
        this.keyguardDismissInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 511);
        this.keyguardDismissActionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 510);
        this.alternateBouncerToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 509);
    }

    /* JADX WARNING: type inference failed for: r0v37, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v70, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v71, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v98, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v99, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v116, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    public final void initialize6() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.alternateBouncerToPrimaryBouncerTransitionViewModelProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 512, 1));
        this.aodToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 513);
        this.dozingToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 514);
        this.dozingToPrimaryBouncerTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 515);
        this.goneToAodTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 516);
        this.goneToDozingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 517);
        this.occludedToAodTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 518);
        this.primaryBouncerToAodTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 519);
        this.primaryBouncerToDozingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 520);
        this.deviceEntryIconViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 506, 1);
        this.udfpsOverlayInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 522);
        this.deviceEntryForegroundViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 521, 1);
        this.deviceEntryBackgroundViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 523, 1);
        this.glanceableHubToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 525);
        this.lockscreenToGlanceableHubTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 526);
        this.primaryBouncerToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 527);
        this.keyguardClockViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 529);
        this.aodBurnInViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 528);
        this.aodAlphaViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 530);
        this.keyguardRootViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 524);
        this.notificationPanelViewControllerProvider = new Object();
        this.factoryProvider13 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 531);
        this.providesKeyguardRootViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 533);
        this.providesDisplayStateInteractorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 538, 1));
        this.fingerprintPropertyInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 537);
        this.deviceEntryBiometricSettingsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 539);
        this.faceHelpMessageDeferralInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 540);
        this.biometricMessageInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 536);
        this.occludingAppDeviceEntryInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 535);
        this.occludingAppDeviceEntryMessageViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 534);
        this.provideChipbarLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 543);
        this.chipbarLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 542);
        this.chipbarAnimatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 544);
        this.swipeChipbarAwayGestureHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 545);
        this.viewUtilProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 546);
        this.temporaryViewUiEventLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 547);
        this.chipbarCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 541);
        this.keyguardBlueprintRepositoryProvider = new Object();
        this.keyguardBlueprintInteractorProvider = new Object();
        this.deviceEntryBiometricAuthInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 549);
        this.keyEventRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 551);
        this.keyEventInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 550);
        this.biometricUnlockLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 552);
        this.deviceEntryHapticsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 548);
        this.provideDateSmartspaceDataPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 555);
        this.provideWeatherSmartspaceDataPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 556);
        this.provideBcSmartspaceDataPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 557);
        this.provideBcSmartspaceConfigPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 558);
        this.lockscreenSmartspaceControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 554);
        this.keyguardSmartspaceRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 560);
        this.keyguardSmartspaceInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 559);
        this.keyguardSmartspaceViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 553);
        this.lockscreenContentViewModelProvider = new Object();
        this.keyguardUnlockAnimationControllerProvider = new Object();
        this.providesNotificationStackScrollLayoutProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 564);
        this.provideAllowNotificationLongPressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 565);
        this.extensionControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 570);
        this.notificationPersonExtractorPluginBoundaryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 569);
        this.peopleNotificationIdentifierImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 568);
        this.highPriorityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 567);
        this.channelEditorDialogControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 571);
        this.assistantFeedbackControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 572);
        this.provideShadeControllerProvider = new Object();
        this.provideNotificationInterruptLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 576);
        this.keyguardNotificationVisibilityProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 577);
        this.bindEventLogProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 578, 1));
        this.notificationInterruptStateProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 575);
        this.avalancheProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 580);
        this.visualInterruptionDecisionProviderImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 579, 1);
        this.provideVisualInterruptionDecisionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 574);
        this.provideSensitiveNotificationProtectionLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 582);
        this.sensitiveNotificationProtectionControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 581);
        this.provideBubblesManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 573);
        this.shadeAnimationRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 587);
        this.shadeAnimationInteractorSceneContainerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 586);
        this.shadeAnimationInteractorLegacyImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 588);
        this.provideShadeAnimationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 585);
        this.visibilityLocationProviderDelegatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 589);
        this.visualStabilityCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 584);
        this.onUserInteractionCallbackImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 583);
        this.notificationGutsManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 566);
        this.notificationRoundnessManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 590);
        this.dynamicPrivacyControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 591);
        this.providesKeyguardMediaHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 593);
        this.provideKeyguardMediaControllerLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 594);
        this.keyguardMediaControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 592);
        this.colorUpdateLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 595);
        this.wallpaperRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 597);
        this.largeScreenShadeInterpolatorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 598);
        this.scrimControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 596);
        this.groupExpansionManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 599);
        this.sectionHeaderControllerSubcomponentBuilderProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 601, 1);
        this.providesSilentHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 600);
        this.provideLSShadeTransitionControllerBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 603);
        this.lockscreenGestureLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 604);
        this.providesIncomingHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 607);
        this.providesPeopleHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 608);
        this.providesAlertingHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 609);
    }

    /* JADX WARNING: type inference failed for: r0v52, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v84, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v89, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v118, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v160, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    public final void initialize7() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.notificationSectionsManagerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 606, 1);
        this.ambientStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 605);
        this.factoryProvider14 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 610);
        this.blurUtilsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 612);
        this.wallpaperControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 613);
        this.notificationShadeDepthControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 611);
        this.factoryProvider15 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 614);
        this.factoryProvider16 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 615);
        this.factoryProvider17 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 616);
        this.shadeLockscreenInteractorImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 618, 1);
        this.provideShadeLockscreenInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 617);
        this.naturalScrollingSettingObserverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 619);
        this.providesQSMediaHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 622);
        this.providesQuickQSMediaHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 623);
        this.provideQSFragmentDisableLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 624);
        this.qSPipelineFlagsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 626);
        this.provideQuickSettingsLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 625);
        this.provideQSConfigLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 627);
        this.fgsManagerControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 629);
        this.footerActionsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 628);
        this.securityControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 633);
        this.qSSecurityFooterUtilsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 632);
        this.securityRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 634);
        this.foregroundServicesRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 635);
        this.userInfoControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 637);
        this.userSwitcherRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 636);
        this.footerActionsInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 631);
        this.globalActionsDialogLiteProvider = new Object();
        this.globalActionsImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 640, 1);
        this.globalActionsComponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 639);
        this.sysuiColorExtractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 641);
        this.globalActionsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 643);
        this.globalActionsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 642);
        DelegateFactory.setDelegate(this.globalActionsDialogLiteProvider, new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 638, 1));
        this.isPMLiteEnabledProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 644);
        this.factoryProvider18 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 630);
        this.footerActionsViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 645);
        this.qSImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 621, 1);
        this.qSSceneAdapterImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 620);
        this.lockscreenShadeTransitionControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 602);
        this.seenNotificationsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 646);
        this.notificationStatsLoggerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 647);
        this.headsUpNotificationIconViewStateRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 648);
        this.statusBarIconViewBindingFailureTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 649);
        this.provideAssistUtilsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 652);
        this.assistManagerGoogleProvider = new Object();
        this.timeoutManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 654);
        this.assistantPresenceHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 655);
        this.optionalOfCentralSurfacesProvider = new Object();
        this.phoneStateMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 658);
        this.googleAssistLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 657);
        this.touchInsideHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 656);
        this.overlayUiHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 659);
        this.provideParentViewGroupProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 661);
        this.edgeLightsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 660);
        this.glowControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 662);
        this.overlappedElementControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 664);
        this.lightnessProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 665);
        this.scrimControllerProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 663);
        this.flingVelocityWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 667);
        this.transcriptionControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 666);
        this.iconControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 668);
        this.assistantWarmerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 669);
        this.navigationBarControllerImplProvider = new Object();
        this.navBarFaderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 671);
        this.navBarFadeControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 670);
        this.ngaUiControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 653);
        this.opaEnabledReceiverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 672);
        this.configurationHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 674);
        this.goBackHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 675);
        this.swipeHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 676);
        this.ngaMessageHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 673);
        this.defaultUiControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 677);
        this.googleDefaultUiControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 678);
        this.assistRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 680);
        this.assistInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 679);
        DelegateFactory.setDelegate(this.assistManagerGoogleProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 651, 1)));
        this.statusBarRemoteInputCallbackProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 681);
        this.provideDisplayMetricsRepoLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 686);
        this.displayMetricsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 685);
        this.qsFrameTranslateImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 684);
        this.scrimShadeTransitionControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 688);
        this.panelExpansionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 689);
        this.shadeTransitionControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 687);
        this.pulseExpansionHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 690);
        this.notificationStackScrollLayoutControllerProvider = new Object();
        this.providesShadeHeaderViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 692);
        this.factoryProvider19 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 693);
        this.providesOngoingPrivacyChipProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 694);
        this.privacyDialogControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 695);
        this.privacyDialogControllerV2Provider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 696);
        this.providesStatusIconContainerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 697);
        this.provideShadeLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 698);
        this.provideTimeTickHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 699);
        this.providesBatteryMeterViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 701);
        this.providesBatteryMeterViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 700);
        this.provideCarrierTextManagerLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 702);
        this.subscriptionManagerSlotIndexResolverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 703);
        this.providesCombinedShadeHeadersConstraintManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 704);
        this.nextAlarmControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 705);
    }

    /* JADX WARNING: type inference failed for: r0v61, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    public final void initialize8() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.shadeHeaderControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 691, 1));
        this.statusBarTouchableRegionManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 706);
        this.screenCaptureDevicePolicyResolverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 708, 1);
        this.mediaProjectionMetricsLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 709);
        this.recordingControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 707);
        this.castControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 710);
        this.largeScreenHeaderHelperProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 711, 1);
        this.quickSettingsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 683);
        this.notificationAlertsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 712);
        this.shadeEventCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 713);
        this.initControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 714);
        this.provideListContainerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 715);
        this.statusBarNotificationPresenterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 682);
        this.provideActivityTransitionAnimatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 716);
        this.notificationLaunchAnimationRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 719);
        this.notificationLaunchAnimationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 718);
        this.provideNotificationTransitionAnimatorControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 717);
        this.launchFullScreenIntentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 720);
        this.statusBarNotificationActivityStarterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 650);
        this.notificationShelfInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 722);
        this.accessibilityInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 723);
        this.notificationShelfViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 721);
        this.hideNotificationsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 725);
        this.hideListViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 724);
        this.provideSecureSettingsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 729);
        this.provideNotificationSettingsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 728);
        this.provideNotificationSettingsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 727);
        this.provideOptionalProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 726);
        new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 730, 1);
        this.notificationStackInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 731);
        this.remoteInputRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 733);
        this.remoteInputInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 732);
        this.providesWindowRootViewProvider = new Object();
        this.provideNotificationRenderLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 734);
        this.notificationStackSizeCalculatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 735);
        this.notificationTargetsHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 736);
        DelegateFactory.setDelegate(this.notificationStackScrollLayoutControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 563, 1)));
        this.providesSharedNotificationContainerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 737);
        this.sharedNotificationContainerInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 739);
        this.goneToDreamingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 740);
        this.sharedNotificationContainerViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 738);
        this.notificationStackAppearanceViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 741);
        this.notificationSectionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 562);
        this.providesLockscreenBlueprintsProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 561, 1);
        this.keyguardBlueprintViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 742);
        this.keyguardViewConfiguratorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 532);
        this.notificationIconContainerAlwaysOnDisplayViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 743);
        this.defaultKeyguardBlueprintProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 441);
        this.splitShadeKeyguardBlueprintProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 744);
        this.shortcutsBesideUdfpsKeyguardBlueprintProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 745);
        this.providesThreadAssertProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 746);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 440, this.keyguardBlueprintRepositoryProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 439, this.keyguardBlueprintInteractorProvider);
        DelegateFactory.setDelegate(this.lockscreenContentViewModelProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 431, 1)));
        this.lockscreenContentProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 430, 1);
        this.lockscreenSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 426);
        this.privacyChipRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 751);
        this.privacyChipInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 750);
        this.shadeHeaderClockRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 753);
        this.shadeHeaderClockInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 752);
        this.shadeHeaderViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 749);
        this.quickSettingsSceneViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 748);
        this.factoryProvider20 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 754);
        this.quickSettingsSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 747);
        this.shadeSceneViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 756);
        this.shadeSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 755);
        this.setOfSceneProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 371, 1);
        this.notificationInsetsImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 757);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 368, this.providesWindowRootViewProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 367, this.providesNotificationShadeWindowViewProvider);
        DelegateFactory.setDelegate(this.providesNotificationPanelViewProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 366, 1)));
        this.builderProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 758, 1);
        this.providesNotificationsQuickSettingsContainerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 760);
        this.notificationsQSContainerControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 759);
        this.providesTapAgainViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 762);
        this.tapAgainViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 761);
        this.keyguardBottomAreaViewControllerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 763, 1);
        this.goneToDreamingLockscreenHostedTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 764);
        DelegateFactory.setDelegate(this.notificationPanelViewControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 365, 1)));
        this.darkIconRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 765);
        this.provideCollapsedSbFragmentLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 766);
        this.collapsedStatusBarFragmentProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 303, 1);
        this.statusBarInitializerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 298);
        this.statusBarSignalPolicyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 767);
        this.accessibilityFloatingMenuControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 768);
        this.dozeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 771);
        this.dozeServiceHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 770);
        this.pulsingGestureListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 772);
        this.lockscreenHostedDreamGestureListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 773);
        this.backActionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 775);
        this.mediaSessionLegacyHelperWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 777);
        this.keyguardKeyEventInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 776);
        this.sysUIKeyEventHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 774);
        this.swipeUpAnywhereGestureHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 779);
        this.tapGestureDetectorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 780);
        this.alternateBouncerUdfpsAccessibilityOverlayViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 781, 1);
        this.alternateBouncerDependenciesProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 778, 1);
        this.provideBouncerLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 785);
        this.bouncerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 784);
        this.legacyBouncerDependenciesProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 783);
    }

    /* JADX WARNING: type inference failed for: r0v22, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v66, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    /* JADX WARNING: type inference failed for: r0v69, types: [java.lang.Object, dagger.internal.DelegateFactory] */
    public final void initialize9() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.composeBouncerDependenciesProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 786, 1));
        this.bouncerViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 782);
        this.notificationShadeWindowViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 769);
        this.volumeDialogControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 788);
        this.volumePanelFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 789);
        this.factoryProvider21 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 790);
        this.volumeDialogComponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 787);
        this.screenPinningRequestProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 792);
        this.cameraLauncherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 793);
        this.qsEventLoggerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 798);
        this.qSTileConfigProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 797);
        this.qSHostAdapterProvider = new Object();
        this.factoryProvider22 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 799);
        this.disabledByPolicyInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 801);
        this.qSTileAnalyticsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 802);
        this.qSTileLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 803);
        this.colorCorrectionRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 804);
        this.qSTileIntentUserInputHandlerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 805);
        this.provideColorCorrectionTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 800, 1);
        this.colorInversionRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 807);
        this.provideColorInversionTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 806, 1);
        this.fontScalingDialogDelegateProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 809, 1);
        this.provideFontScalingTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 808, 1);
        this.provideAirplaneModeTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 810, 1);
        this.provideDataSaverControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 812);
        this.provideDataSaverTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 811, 1);
        this.provideFlashlightTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 813, 1);
        this.provideLocationTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 814, 1);
        this.provideAlarmTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 815, 1);
        this.provideUiModeNightTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 816, 1);
        this.bindRotationPolicyWrapperProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 819, 1));
        this.provideAutoRotateSettingsManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 821);
        this.provideDeviceStateAutoRotationLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 822);
        this.deviceStateRotationLockSettingControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 820);
        this.rotationLockControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 818);
        this.cameraAutoRotateRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 823);
        this.cameraSensorPrivacyRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 824);
        this.provideRotationTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 817, 1);
        this.newQSTileFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 796);
        this.qSTileHostProvider = new Object();
        this.panelInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 828);
        this.tileServicesProvider = new Object();
        this.factoryProvider23 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 829);
        this.customTileAddedSharedPrefsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 830);
        DelegateFactory.setDelegate(this.tileServicesProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 827, 1)));
        this.factoryProvider24 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 826);
        this.ethernetInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 833);
        this.internetTileViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 832);
        this.bindInternetTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 831, 1);
        this.colorInversionTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 834, 1);
        this.nightDisplayTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 835, 1);
        this.isReduceBrightColorsAvailableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 837);
        this.reduceBrightColorsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 838);
        this.reduceBrightColorsTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 836, 1);
        this.oneHandedModeTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 839, 1);
        this.colorCorrectionTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 840, 1);
        this.dreamTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 841, 1);
        this.fontScalingTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 842, 1);
        this.providerBluetoothLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 846);
        this.bluetoothLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 845);
        this.bluetoothRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 847);
        this.bluetoothControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 844);
        this.bluetoothTileDialogRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 850);
        this.provideQBluetoothTileDialogLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 851);
        this.deviceItemInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 849);
        this.bluetoothStateInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 852);
        this.bluetoothAutoOnRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 854);
        this.bluetoothAutoOnInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 853);
        this.factoryProvider25 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 855);
        this.bluetoothTileDialogViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 848);
        this.bluetoothTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 843, 1);
        this.hotspotControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 857);
        this.tileJavaAdapterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 858);
        this.castTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 856, 1);
        this.hotspotTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 859, 1);
        this.airplaneModeTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 860, 1);
        this.dataSaverTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 861, 1);
        this.nfcTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 862, 1);
        this.deviceControlsTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 863, 1);
        this.dndTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 864, 1);
        this.managedProfileControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 866);
        this.workModeTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 865, 1);
        this.flashlightTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 867, 1);
        this.locationTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 868, 1);
        this.cameraToggleTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 869, 1);
        this.microphoneToggleTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 870, 1);
        this.alarmTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 871, 1);
        this.uiModeNightTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 872, 1);
        this.qRCodeScannerTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 873, 1);
        this.factoryProvider26 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m$1(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 875);
        this.recordIssueTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 874, 1);
        this.screenRecordTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 876, 1);
        this.quickAccessWalletTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 877, 1);
        this.batterySaverTileGoogleProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 878, 1);
        this.overlayToggleTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 879, 1);
        this.reverseChargingTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 880, 1);
        this.rotationLockTileGoogleProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 881, 1);
        this.qSFactoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 825);
        this.builderProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 883);
    }

    public void inject(SystemUIAppComponentFactoryBase systemUIAppComponentFactoryBase) {
        systemUIAppComponentFactoryBase.componentHelper = (ContextComponentResolver) this.contextComponentResolverProvider.get();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Object, com.android.systemui.doze.util.BurnInHelperWrapper] */
    public final KeyguardBottomAreaViewModel keyguardBottomAreaViewModel() {
        return new KeyguardBottomAreaViewModel((KeyguardInteractor) this.keyguardInteractorProvider.get(), (KeyguardQuickAffordanceInteractor) this.keyguardQuickAffordanceInteractorProvider.get(), (KeyguardBottomAreaInteractor) this.keyguardBottomAreaInteractorProvider.get(), new Object(), (KeyguardLongPressViewModel) this.keyguardLongPressViewModelProvider.get(), new KeyguardSettingsMenuViewModel((KeyguardLongPressInteractor) this.keyguardLongPressInteractorProvider.get()));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Object, com.android.systemui.doze.util.BurnInHelperWrapper] */
    public final KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel() {
        return new KeyguardIndicationAreaViewModel((KeyguardInteractor) this.keyguardInteractorProvider.get(), (KeyguardBottomAreaInteractor) this.keyguardBottomAreaInteractorProvider.get(), keyguardBottomAreaViewModel(), new Object(), (BurnInInteractor) this.burnInInteractorProvider.get(), keyguardQuickAffordancesCombinedViewModel(), (ConfigurationInteractor) this.configurationInteractorProvider.get());
    }

    public final KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel() {
        return new KeyguardQuickAffordancesCombinedViewModel((KeyguardQuickAffordanceInteractor) this.keyguardQuickAffordanceInteractorProvider.get(), (KeyguardInteractor) this.keyguardInteractorProvider.get(), (ShadeInteractor) this.shadeInteractorImplProvider.get(), (AodToLockscreenTransitionViewModel) this.aodToLockscreenTransitionViewModelProvider.get(), (DozingToLockscreenTransitionViewModel) this.dozingToLockscreenTransitionViewModelProvider.get(), (DreamingHostedToLockscreenTransitionViewModel) this.dreamingHostedToLockscreenTransitionViewModelProvider.get(), (DreamingToLockscreenTransitionViewModel) this.dreamingToLockscreenTransitionViewModelProvider.get(), (GoneToLockscreenTransitionViewModel) this.goneToLockscreenTransitionViewModelProvider.get(), (OccludedToLockscreenTransitionViewModel) this.occludedToLockscreenTransitionViewModelProvider.get(), (OffToLockscreenTransitionViewModel) this.offToLockscreenTransitionViewModelProvider.get(), (PrimaryBouncerToLockscreenTransitionViewModel) this.primaryBouncerToLockscreenTransitionViewModelProvider.get(), (LockscreenToAodTransitionViewModel) this.lockscreenToAodTransitionViewModelProvider.get(), (LockscreenToDozingTransitionViewModel) this.lockscreenToDozingTransitionViewModelProvider.get(), (LockscreenToDreamingHostedTransitionViewModel) this.lockscreenToDreamingHostedTransitionViewModelProvider.get(), (LockscreenToDreamingTransitionViewModel) this.lockscreenToDreamingTransitionViewModelProvider.get(), (LockscreenToGoneTransitionViewModel) this.lockscreenToGoneTransitionViewModelProvider.get(), (LockscreenToOccludedTransitionViewModel) this.lockscreenToOccludedTransitionViewModelProvider.get(), (LockscreenToPrimaryBouncerTransitionViewModel) this.lockscreenToPrimaryBouncerTransitionViewModelProvider.get(), (KeyguardTransitionInteractor) this.keyguardTransitionInteractorProvider.get());
    }

    public final KeyguardVisibility keyguardVisibility() {
        return new KeyguardVisibility((Executor) this.sysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (KeyguardStateController) this.keyguardStateControllerImplProvider.get());
    }

    public final LockSection lockSection() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new LockSection((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), (AuthController) this.authControllerProvider.get(), (FeatureFlagsClassic) this.featureFlagsClassicReleaseProvider.get(), DoubleCheck.lazy(this.lockIconViewControllerProvider), DoubleCheck.lazy(this.deviceEntryIconViewModelProvider), DoubleCheck.lazy(this.deviceEntryForegroundViewModelProvider), DoubleCheck.lazy(this.deviceEntryBackgroundViewModelProvider), DoubleCheck.lazy(this.falsingManagerProxyProvider), DoubleCheck.lazy(this.vibratorHelperProvider), (NotificationPanelView) this.providesNotificationPanelViewProvider.get());
    }

    public final MediaCarouselSection mediaCarouselSection() {
        return new MediaCarouselSection((MediaCarouselController) this.mediaCarouselControllerProvider.get(), (MediaHost) this.providesKeyguardMediaHostProvider.get(), new MediaCarouselViewModel((MediaDataManager) this.mediaDataManagerProvider.get()));
    }

    public final MediaOutputDialogFactory mediaOutputDialogFactory() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new MediaOutputDialogFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmediaSessionManager(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (LocalBluetoothManager) this.provideLocalBluetoothControllerProvider.get(), (ActivityStarter) this.activityStarterImplProvider.get(), (BroadcastSender) this.broadcastSenderProvider.get(), (CommonNotifCollection) this.notifPipelineProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (DialogTransitionAnimator) this.provideDialogTransitionAnimatorProvider.get(), (NearbyMediaDevicesManager) this.nearbyMediaDevicesManagerProvider.get(), (AudioManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAudioManagerProvider.get(), (PowerExemptionManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePowerExemptionManagerProvider.get(), (KeyguardManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideKeyguardManagerProvider.get(), (FeatureFlags) this.featureFlagsClassicReleaseProvider.get(), (UserTracker) this.provideUserTrackerProvider.get());
    }

    public final Set namedSetOfAction() {
        ArrayList arrayList = new ArrayList(3);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        arrayList.add(new DeskClockAction(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get()));
        arrayList.add(new DeskClockAction(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get()));
        arrayList.add(new SilenceCall(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (TelephonyListenerManager) this.telephonyListenerManagerProvider.get()));
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }

    public final boolean noteTaskEnabledKeyBoolean() {
        boolean isRoleAvailable = ((RoleManager) this.sysUIGoogleGlobalRootComponentImpl.provideRoleManagerProvider.get()).isRoleAvailable("android.app.role.NOTES");
        boolean isEnabled = ((FeatureFlagsClassicRelease) ((FeatureFlags) this.featureFlagsClassicReleaseProvider.get())).isEnabled(com.android.systemui.flags.Flags.NOTE_TASKS);
        if (!isRoleAvailable || !isEnabled) {
            return false;
        }
        return true;
    }

    public final NotifPipelineFlags notifPipelineFlags() {
        return new NotifPipelineFlags((FeatureFlags) this.featureFlagsClassicReleaseProvider.get());
    }

    public final NotificationIconAreaController notificationIconAreaController() {
        Object obj = this.legacyNotificationIconAreaControllerImplProvider.get();
        Intrinsics.checkNotNull(obj);
        return (NotificationIconAreaController) obj;
    }

    public final NotificationIconsInteractor notificationIconsInteractor() {
        return new NotificationIconsInteractor((ActiveNotificationsInteractor) this.activeNotificationsInteractorProvider.get(), this.setBubbles, new HeadsUpNotificationIconInteractor((HeadsUpNotificationIconViewStateRepository) this.headsUpNotificationIconViewStateRepositoryProvider.get()), (NotificationsKeyguardViewStateRepository) this.notificationsKeyguardViewStateRepositoryProvider.get());
    }

    public final Context overlayWindowContextContext() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        ((DisplayTracker) this.provideDisplayTrackerProvider.get()).getClass();
        Context createWindowContext = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.createWindowContext(((DisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDisplayManagerProvider.get()).getDisplay(0), 2036, (Bundle) null);
        Preconditions.checkNotNullFromProvides(createWindowContext);
        return createWindowContext;
    }

    public final QSLogger qSLogger() {
        return new QSLogger((LogBuffer) this.provideQuickSettingsLogBufferProvider.get(), (LogBuffer) this.provideQSConfigLogBufferProvider.get());
    }

    public final QSTileCoroutineScopeFactory qSTileCoroutineScopeFactory() {
        return new QSTileCoroutineScopeFactory((CoroutineScope) this.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
    }

    public final SettingsMenuSection settingsMenuSection() {
        return new SettingsMenuSection(new KeyguardSettingsMenuViewModel((KeyguardLongPressInteractor) this.keyguardLongPressInteractorProvider.get()), (KeyguardLongPressViewModel) this.keyguardLongPressViewModelProvider.get(), (VibratorHelper) this.vibratorHelperProvider.get(), (ActivityStarter) this.activityStarterImplProvider.get());
    }

    public final SmartSpaceSection smartSpaceSection() {
        return new SmartSpaceSection((LockscreenSmartspaceController) this.lockscreenSmartspaceControllerProvider.get(), (KeyguardUnlockAnimationController) this.keyguardUnlockAnimationControllerProvider.get(), (KeyguardSmartspaceViewModel) this.keyguardSmartspaceViewModelProvider.get(), (AodBurnInViewModel) this.aodBurnInViewModelProvider.get());
    }

    public final StatusBarSection statusBarSection() {
        return new StatusBarSection(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(this.sysUIGoogleGlobalRootComponentImpl, this.sysUIGoogleSysUIComponentImpl), DoubleCheck.lazy(this.providesNotificationPanelViewProvider));
    }

    public final SystemBarUtilsState systemBarUtilsState() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new SystemBarUtilsState((CoroutineContext) this.bgCoroutineContextProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.mainCoroutineContextProvider.get(), (ConfigurationController) this.configurationControllerImplProvider.get(), new SystemBarUtilsProxyImpl((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get()));
    }

    public final Resources.Theme theme() {
        return this.sysUIGoogleGlobalRootComponentImpl.context.getTheme();
    }

    public final WakeLockLogger wakeLockLogger() {
        return new WakeLockLogger((LogBuffer) this.provideWakeLockLogProvider.get());
    }

    public void inject(KeyguardSliceProvider keyguardSliceProvider) {
        keyguardSliceProvider.mDozeParameters = (DozeParameters) this.dozeParametersProvider.get();
        keyguardSliceProvider.mZenModeController = (ZenModeController) this.zenModeControllerImplProvider.get();
        keyguardSliceProvider.mNextAlarmController = (NextAlarmController) this.nextAlarmControllerImplProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        keyguardSliceProvider.mAlarmManager = (AlarmManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAlarmManagerProvider.get();
        keyguardSliceProvider.mContentResolver = (ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get();
        keyguardSliceProvider.mMediaManager = (NotificationMediaManager) this.provideNotificationMediaManagerProvider.get();
        keyguardSliceProvider.mStatusBarStateController = (StatusBarStateController) this.statusBarStateControllerImplProvider.get();
        keyguardSliceProvider.mKeyguardBypassController = (KeyguardBypassController) this.keyguardBypassControllerProvider.get();
        keyguardSliceProvider.mKeyguardUpdateMonitor = (KeyguardUpdateMonitor) this.keyguardUpdateMonitorProvider.get();
        keyguardSliceProvider.mUserTracker = (UserTracker) this.provideUserTrackerProvider.get();
        keyguardSliceProvider.mWakeLockLogger = wakeLockLogger();
        keyguardSliceProvider.mBgHandler = (Handler) this.provideBgHandlerProvider.get();
    }

    public void inject(PeopleProvider peopleProvider) {
        peopleProvider.mPeopleSpaceWidgetManager = (PeopleSpaceWidgetManager) this.peopleSpaceWidgetManagerProvider.get();
    }

    public void inject(CustomizationProvider customizationProvider) {
        customizationProvider.interactor = (KeyguardQuickAffordanceInteractor) this.keyguardQuickAffordanceInteractorProvider.get();
        customizationProvider.previewManager = (KeyguardRemotePreviewManager) this.keyguardRemotePreviewManagerProvider.get();
        customizationProvider.mainDispatcher = (CoroutineDispatcher) this.sysUIGoogleGlobalRootComponentImpl.mainDispatcherProvider.get();
    }
}
