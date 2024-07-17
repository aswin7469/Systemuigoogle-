package com.google.android.systemui.dagger;

import android.content.Context;
import android.view.Display;
import androidx.core.view.DifferentialMotionFlingController$$ExternalSyntheticLambda0;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.keyguard.ClockEventController;
import com.android.keyguard.KeyguardClockSwitch;
import com.android.keyguard.KeyguardClockSwitchController;
import com.android.keyguard.KeyguardSliceViewController;
import com.android.keyguard.KeyguardStatusView;
import com.android.keyguard.KeyguardStatusViewController;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
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
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerAlwaysOnDisplayViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBindingFailureTracker;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.NotificationIconAreaController;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.touch.TouchInsetManager;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Optional;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl {
    public Object display;
    public Object keyguardSliceViewControllerProvider;
    public Object presentation;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final Object sysUIGoogleSysUIComponentImpl;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class SwitchingProvider implements Provider {
        public final /* synthetic */ int $r8$classId;
        public final Object keyguardStatusViewComponentImpl;
        public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
        public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

        public /* synthetic */ SwitchingProvider(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, Object obj, int i) {
            this.$r8$classId = i;
            this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
            this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
            this.keyguardStatusViewComponentImpl = obj;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object, com.android.systemui.statusbar.notification.row.ExpandableViewController] */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0225  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0228  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object get() {
            /*
                r65 = this;
                r0 = r65
                int r1 = r0.$r8$classId
                switch(r1) {
                    case 0: goto L_0x02df;
                    case 1: goto L_0x0111;
                    case 2: goto L_0x0098;
                    case 3: goto L_0x0031;
                    default: goto L_0x0007;
                }
            L_0x0007:
                com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl r1 = new com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl
                java.lang.Object r2 = r0.keyguardStatusViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl r2 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) r2
                java.lang.String r3 = r2.nodeLabel
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r4 = r0.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r4 = r4.providerLayoutInflaterProvider
                java.lang.Object r4 = r4.get()
                android.view.LayoutInflater r4 = (android.view.LayoutInflater) r4
                java.lang.Integer r5 = r2.headerText
                int r5 = r5.intValue()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r0 = r0.sysUIGoogleSysUIComponentImpl
                dagger.internal.DelegateFactory r0 = r0.activityStarterImplProvider
                java.lang.Object r0 = r0.get()
                r6 = r0
                com.android.systemui.plugins.ActivityStarter r6 = (com.android.systemui.plugins.ActivityStarter) r6
                java.lang.String r7 = r2.clickIntentAction
                r2 = r1
                r2.<init>(r3, r4, r5, r6, r7)
                return r1
            L_0x0031:
                com.android.systemui.statusbar.policy.KeyguardUserSwitcherController r1 = new com.android.systemui.statusbar.policy.KeyguardUserSwitcherController
                java.lang.Object r2 = r0.keyguardStatusViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder r2 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder) r2
                java.lang.Object r2 = r2.sysUIGoogleGlobalRootComponentImpl
                r9 = r2
                com.android.systemui.statusbar.policy.KeyguardUserSwitcherView r9 = (com.android.systemui.statusbar.policy.KeyguardUserSwitcherView) r9
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r0.sysUIGoogleGlobalRootComponentImpl
                android.content.Context r10 = r2.context
                android.content.res.Resources r11 = r10.getResources()
                dagger.internal.Preconditions.checkNotNullFromProvides(r11)
                javax.inject.Provider r3 = r2.providerLayoutInflaterProvider
                java.lang.Object r3 = r3.get()
                r12 = r3
                android.view.LayoutInflater r12 = (android.view.LayoutInflater) r12
                javax.inject.Provider r2 = r2.screenLifecycleProvider
                java.lang.Object r2 = r2.get()
                r13 = r2
                com.android.systemui.keyguard.ScreenLifecycle r13 = (com.android.systemui.keyguard.ScreenLifecycle) r13
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r0 = r0.sysUIGoogleSysUIComponentImpl
                javax.inject.Provider r2 = r0.userSwitcherControllerProvider
                java.lang.Object r2 = r2.get()
                r14 = r2
                com.android.systemui.statusbar.policy.UserSwitcherController r14 = (com.android.systemui.statusbar.policy.UserSwitcherController) r14
                dagger.internal.DelegateFactory r2 = r0.keyguardStateControllerImplProvider
                java.lang.Object r2 = r2.get()
                r15 = r2
                com.android.systemui.statusbar.policy.KeyguardStateController r15 = (com.android.systemui.statusbar.policy.KeyguardStateController) r15
                dagger.internal.DelegateFactory r2 = r0.statusBarStateControllerImplProvider
                java.lang.Object r2 = r2.get()
                r16 = r2
                com.android.systemui.statusbar.SysuiStatusBarStateController r16 = (com.android.systemui.statusbar.SysuiStatusBarStateController) r16
                dagger.internal.DelegateFactory r2 = r0.keyguardUpdateMonitorProvider
                java.lang.Object r2 = r2.get()
                r17 = r2
                com.android.keyguard.KeyguardUpdateMonitor r17 = (com.android.keyguard.KeyguardUpdateMonitor) r17
                dagger.internal.DelegateFactory r2 = r0.dozeParametersProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.statusbar.phone.DozeParameters r2 = (com.android.systemui.statusbar.phone.DozeParameters) r2
                javax.inject.Provider r0 = r0.screenOffAnimationControllerProvider
                java.lang.Object r0 = r0.get()
                r18 = r0
                com.android.systemui.statusbar.phone.ScreenOffAnimationController r18 = (com.android.systemui.statusbar.phone.ScreenOffAnimationController) r18
                r8 = r1
                r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
                return r1
            L_0x0098:
                com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController r1 = new com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController
                java.lang.Object r2 = r0.keyguardStatusViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder r2 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder) r2
                java.lang.Object r2 = r2.sysUIGoogleGlobalRootComponentImpl
                r20 = r2
                android.widget.FrameLayout r20 = (android.widget.FrameLayout) r20
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r0.sysUIGoogleGlobalRootComponentImpl
                android.content.Context r3 = r2.context
                android.content.res.Resources r22 = r3.getResources()
                dagger.internal.Preconditions.checkNotNullFromProvides(r22)
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r0 = r0.sysUIGoogleSysUIComponentImpl
                javax.inject.Provider r4 = r0.userSwitcherControllerProvider
                java.lang.Object r4 = r4.get()
                r23 = r4
                com.android.systemui.statusbar.policy.UserSwitcherController r23 = (com.android.systemui.statusbar.policy.UserSwitcherController) r23
                dagger.internal.DelegateFactory r4 = r0.keyguardStateControllerImplProvider
                java.lang.Object r4 = r4.get()
                r24 = r4
                com.android.systemui.statusbar.policy.KeyguardStateController r24 = (com.android.systemui.statusbar.policy.KeyguardStateController) r24
                dagger.internal.DelegateFactory r4 = r0.falsingManagerProxyProvider
                java.lang.Object r4 = r4.get()
                r25 = r4
                com.android.systemui.plugins.FalsingManager r25 = (com.android.systemui.plugins.FalsingManager) r25
                javax.inject.Provider r4 = r0.configurationControllerImplProvider
                java.lang.Object r4 = r4.get()
                r26 = r4
                com.android.systemui.statusbar.policy.ConfigurationController r26 = (com.android.systemui.statusbar.policy.ConfigurationController) r26
                dagger.internal.DelegateFactory r4 = r0.statusBarStateControllerImplProvider
                java.lang.Object r4 = r4.get()
                r27 = r4
                com.android.systemui.statusbar.SysuiStatusBarStateController r27 = (com.android.systemui.statusbar.SysuiStatusBarStateController) r27
                dagger.internal.DelegateFactory r4 = r0.dozeParametersProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.statusbar.phone.DozeParameters r4 = (com.android.systemui.statusbar.phone.DozeParameters) r4
                javax.inject.Provider r4 = r0.screenOffAnimationControllerProvider
                java.lang.Object r4 = r4.get()
                r28 = r4
                com.android.systemui.statusbar.phone.ScreenOffAnimationController r28 = (com.android.systemui.statusbar.phone.ScreenOffAnimationController) r28
                javax.inject.Provider r0 = r0.userSwitchDialogControllerProvider
                java.lang.Object r0 = r0.get()
                r29 = r0
                com.android.systemui.qs.user.UserSwitchDialogController r29 = (com.android.systemui.qs.user.UserSwitchDialogController) r29
                javax.inject.Provider r0 = r2.provideUiEventLoggerProvider
                java.lang.Object r0 = r0.get()
                r30 = r0
                com.android.internal.logging.UiEventLogger r30 = (com.android.internal.logging.UiEventLogger) r30
                r19 = r1
                r21 = r3
                r19.<init>(r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)
                return r1
            L_0x0111:
                com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController r1 = new com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController
                java.lang.Object r2 = r0.keyguardStatusViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl r2 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl) r2
                com.android.systemui.statusbar.notification.row.ExpandableNotificationRow r3 = r2.expandableNotificationRow
                com.android.systemui.statusbar.notification.row.ActivatableNotificationViewController r4 = new com.android.systemui.statusbar.notification.row.ActivatableNotificationViewController
                com.android.systemui.statusbar.notification.row.ExpandableOutlineViewController r5 = new com.android.systemui.statusbar.notification.row.ExpandableOutlineViewController
                com.android.systemui.statusbar.notification.row.ExpandableViewController r6 = new com.android.systemui.statusbar.notification.row.ExpandableViewController
                r6.<init>()
                r5.<init>(r6)
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r6 = r2.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r7 = r6.provideAccessibilityManagerProvider
                java.lang.Object r7 = r7.get()
                android.view.accessibility.AccessibilityManager r7 = (android.view.accessibility.AccessibilityManager) r7
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r8 = r2.sysUIGoogleSysUIComponentImpl
                dagger.internal.DelegateFactory r9 = r8.falsingManagerProxyProvider
                java.lang.Object r9 = r9.get()
                com.android.systemui.plugins.FalsingManager r9 = (com.android.systemui.plugins.FalsingManager) r9
                r4.<init>(r3, r5, r7, r9)
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$RemoteInputViewSubcomponentFactory r5 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$RemoteInputViewSubcomponentFactory
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r7 = r0.sysUIGoogleGlobalRootComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r0 = r0.sysUIGoogleSysUIComponentImpl
                r5.<init>(r7, r0, r2)
                javax.inject.Provider r9 = r7.provideMetricsLoggerProvider
                java.lang.Object r9 = r9.get()
                r35 = r9
                com.android.internal.logging.MetricsLogger r35 = (com.android.internal.logging.MetricsLogger) r35
                javax.inject.Provider r9 = r0.colorUpdateLoggerProvider
                java.lang.Object r9 = r9.get()
                r36 = r9
                com.android.systemui.statusbar.notification.ColorUpdateLogger r36 = (com.android.systemui.statusbar.notification.ColorUpdateLogger) r36
                com.android.systemui.statusbar.notification.row.NotificationRowLogger r9 = new com.android.systemui.statusbar.notification.row.NotificationRowLogger
                javax.inject.Provider r10 = r8.provideNotificationsLogBufferProvider
                java.lang.Object r10 = r10.get()
                com.android.systemui.log.LogBuffer r10 = (com.android.systemui.log.LogBuffer) r10
                javax.inject.Provider r11 = r8.provideNotificationRenderLogBufferProvider
                java.lang.Object r11 = r11.get()
                com.android.systemui.log.LogBuffer r11 = (com.android.systemui.log.LogBuffer) r11
                r9.<init>(r10, r11)
                com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerLogger r10 = new com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerLogger
                javax.inject.Provider r11 = r8.provideNotificationRenderLogBufferProvider
                java.lang.Object r11 = r11.get()
                com.android.systemui.log.LogBuffer r11 = (com.android.systemui.log.LogBuffer) r11
                r10.<init>(r11)
                javax.inject.Provider r11 = r0.provideListContainerProvider
                java.lang.Object r11 = r11.get()
                r39 = r11
                com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$NotificationListContainerImpl r39 = (com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.NotificationListContainerImpl) r39
                javax.inject.Provider r11 = r0.smartReplyConstantsProvider
                java.lang.Object r11 = r11.get()
                r40 = r11
                com.android.systemui.statusbar.policy.SmartReplyConstants r40 = (com.android.systemui.statusbar.policy.SmartReplyConstants) r40
                javax.inject.Provider r11 = r0.provideSmartReplyControllerProvider
                java.lang.Object r11 = r11.get()
                r41 = r11
                com.android.systemui.statusbar.SmartReplyController r41 = (com.android.systemui.statusbar.SmartReplyController) r41
                javax.inject.Provider r11 = r7.providesPluginManagerProvider
                java.lang.Object r11 = r11.get()
                r42 = r11
                com.android.systemui.plugins.PluginManager r42 = (com.android.systemui.plugins.PluginManager) r42
                javax.inject.Provider r11 = r0.bindSystemClockProvider
                java.lang.Object r11 = r11.get()
                r43 = r11
                com.android.systemui.util.time.SystemClock r43 = (com.android.systemui.util.time.SystemClock) r43
                android.content.Context r11 = r6.context
                com.android.systemui.statusbar.notification.collection.NotificationEntry r12 = r2.notificationEntry
                android.service.notification.StatusBarNotification r13 = r12.mSbn
                dagger.internal.Preconditions.checkNotNullFromProvides(r13)
                android.os.UserHandle r14 = r13.getUser()
                int r14 = r14.getIdentifier()
                android.content.pm.PackageManager r11 = com.android.systemui.statusbar.phone.CentralSurfaces.getPackageManagerForUser(r14, r11)
                java.lang.String r13 = r13.getPackageName()
                r14 = 8704(0x2200, float:1.2197E-41)
                android.content.pm.ApplicationInfo r14 = r11.getApplicationInfo(r13, r14)     // Catch:{ NameNotFoundException -> 0x01d9 }
                if (r14 == 0) goto L_0x01d9
                java.lang.CharSequence r11 = r11.getApplicationLabel(r14)     // Catch:{ NameNotFoundException -> 0x01d9 }
                java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ NameNotFoundException -> 0x01d9 }
                r44 = r11
                goto L_0x01db
            L_0x01d9:
                r44 = r13
            L_0x01db:
                dagger.internal.Preconditions.checkNotNullFromProvides(r44)
                android.service.notification.StatusBarNotification r11 = r12.mSbn
                dagger.internal.Preconditions.checkNotNullFromProvides(r11)
                java.lang.String r45 = r11.getKey()
                dagger.internal.Preconditions.checkNotNullFromProvides(r45)
                dagger.internal.DelegateFactory r11 = r0.keyguardBypassControllerProvider
                java.lang.Object r11 = r11.get()
                r46 = r11
                com.android.systemui.statusbar.phone.KeyguardBypassController r46 = (com.android.systemui.statusbar.phone.KeyguardBypassController) r46
                javax.inject.Provider r11 = r0.groupMembershipManagerImplProvider
                java.lang.Object r11 = r11.get()
                r47 = r11
                com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl r47 = (com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl) r47
                javax.inject.Provider r11 = r0.groupExpansionManagerImplProvider
                java.lang.Object r11 = r11.get()
                r48 = r11
                com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl r48 = (com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl) r48
                javax.inject.Provider r11 = r0.rowContentBindStageProvider
                java.lang.Object r11 = r11.get()
                r49 = r11
                com.android.systemui.statusbar.notification.row.RowContentBindStage r49 = (com.android.systemui.statusbar.notification.row.RowContentBindStage) r49
                javax.inject.Provider r11 = r0.notificationStatsLoggerImplProvider
                javax.inject.Provider r12 = r0.provideLegacyLoggerOptionalProvider
                java.lang.Object r12 = r12.get()
                java.util.Optional r12 = (java.util.Optional) r12
                r13 = 0
                java.lang.Object r12 = r12.orElse(r13)
                com.android.systemui.statusbar.notification.logging.NotificationLogger r12 = (com.android.systemui.statusbar.notification.logging.NotificationLogger) r12
                if (r12 == 0) goto L_0x0228
                r50 = r12
                goto L_0x0230
            L_0x0228:
                java.lang.Object r11 = r11.get()
                com.android.systemui.statusbar.notification.stack.ui.view.NotificationRowStatsLogger r11 = (com.android.systemui.statusbar.notification.stack.ui.view.NotificationRowStatsLogger) r11
                r50 = r11
            L_0x0230:
                javax.inject.Provider r11 = r0.headsUpManagerPhoneProvider
                java.lang.Object r11 = r11.get()
                r51 = r11
                com.android.systemui.statusbar.policy.HeadsUpManager r51 = (com.android.systemui.statusbar.policy.HeadsUpManager) r51
                dagger.internal.DelegateFactory r11 = r0.statusBarStateControllerImplProvider
                java.lang.Object r11 = r11.get()
                r53 = r11
                com.android.systemui.plugins.statusbar.StatusBarStateController r53 = (com.android.systemui.plugins.statusbar.StatusBarStateController) r53
                javax.inject.Provider r11 = r0.notificationGutsManagerProvider
                java.lang.Object r11 = r11.get()
                r54 = r11
                com.android.systemui.statusbar.notification.row.NotificationGutsManager r54 = (com.android.systemui.statusbar.notification.row.NotificationGutsManager) r54
                javax.inject.Provider r11 = r0.provideAllowNotificationLongPressProvider
                java.lang.Object r11 = r11.get()
                java.lang.Boolean r11 = (java.lang.Boolean) r11
                boolean r55 = r11.booleanValue()
                javax.inject.Provider r11 = r0.onUserInteractionCallbackImplProvider
                java.lang.Object r11 = r11.get()
                r56 = r11
                com.android.systemui.statusbar.notification.collection.inflation.OnUserInteractionCallbackImpl r56 = (com.android.systemui.statusbar.notification.collection.inflation.OnUserInteractionCallbackImpl) r56
                dagger.internal.DelegateFactory r11 = r0.falsingManagerProxyProvider
                java.lang.Object r11 = r11.get()
                r57 = r11
                com.android.systemui.plugins.FalsingManager r57 = (com.android.systemui.plugins.FalsingManager) r57
                dagger.internal.DelegateFactory r11 = r0.featureFlagsClassicReleaseProvider
                java.lang.Object r11 = r11.get()
                r58 = r11
                com.android.systemui.flags.FeatureFlags r58 = (com.android.systemui.flags.FeatureFlags) r58
                javax.inject.Provider r11 = r0.peopleNotificationIdentifierImplProvider
                java.lang.Object r11 = r11.get()
                r59 = r11
                com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifierImpl r59 = (com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifierImpl) r59
                javax.inject.Provider r11 = r0.provideBubblesManagerProvider
                java.lang.Object r11 = r11.get()
                r60 = r11
                java.util.Optional r60 = (java.util.Optional) r60
                javax.inject.Provider r11 = r0.notificationSettingsControllerProvider
                java.lang.Object r11 = r11.get()
                r61 = r11
                com.android.systemui.statusbar.notification.row.NotificationSettingsController r61 = (com.android.systemui.statusbar.notification.row.NotificationSettingsController) r61
                com.android.systemui.statusbar.notification.row.ExpandableNotificationRowDragController r11 = new com.android.systemui.statusbar.notification.row.ExpandableNotificationRowDragController
                android.content.Context r6 = r6.context
                javax.inject.Provider r12 = r8.headsUpManagerPhoneProvider
                java.lang.Object r12 = r12.get()
                com.android.systemui.statusbar.policy.HeadsUpManager r12 = (com.android.systemui.statusbar.policy.HeadsUpManager) r12
                dagger.internal.DelegateFactory r13 = r8.provideShadeControllerProvider
                java.lang.Object r13 = r13.get()
                com.android.systemui.shade.ShadeController r13 = (com.android.systemui.shade.ShadeController) r13
                javax.inject.Provider r8 = r8.provideNotificationPanelLoggerProvider
                java.lang.Object r8 = r8.get()
                com.android.systemui.statusbar.notification.logging.NotificationPanelLoggerImpl r8 = (com.android.systemui.statusbar.notification.logging.NotificationPanelLoggerImpl) r8
                r11.<init>(r6, r12, r13, r8)
                javax.inject.Provider r0 = r0.notificationDismissibilityProviderImplProvider
                java.lang.Object r0 = r0.get()
                r63 = r0
                com.android.systemui.statusbar.notification.collection.provider.NotificationDismissibilityProviderImpl r63 = (com.android.systemui.statusbar.notification.collection.provider.NotificationDismissibilityProviderImpl) r63
                javax.inject.Provider r0 = r7.provideIStatusBarServiceProvider
                java.lang.Object r0 = r0.get()
                r64 = r0
                com.android.internal.statusbar.IStatusBarService r64 = (com.android.internal.statusbar.IStatusBarService) r64
                com.android.systemui.statusbar.phone.StatusBarNotificationPresenter r0 = r2.onExpandClickListener
                r52 = r0
                r31 = r1
                r32 = r3
                r33 = r4
                r34 = r5
                r37 = r9
                r38 = r10
                r62 = r11
                r31.<init>(r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64)
                return r1
            L_0x02df:
                com.android.keyguard.KeyguardSliceViewController r1 = new com.android.keyguard.KeyguardSliceViewController
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r0.sysUIGoogleGlobalRootComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r3 = r2.provideMainHandlerProvider
                java.lang.Object r3 = r3.get()
                r13 = r3
                android.os.Handler r13 = (android.os.Handler) r13
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r3 = r0.sysUIGoogleSysUIComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$SwitchingProvider r4 = r3.provideBgHandlerProvider
                java.lang.Object r4 = r4.get()
                r14 = r4
                android.os.Handler r14 = (android.os.Handler) r14
                r4 = 2131362800(0x7f0a03f0, float:1.834539E38)
                java.lang.Object r0 = r0.keyguardStatusViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl) r0
                java.lang.Object r0 = r0.presentation
                com.android.keyguard.KeyguardStatusView r0 = (com.android.keyguard.KeyguardStatusView) r0
                android.view.View r0 = r0.findViewById(r4)
                com.android.keyguard.KeyguardClockSwitch r0 = (com.android.keyguard.KeyguardClockSwitch) r0
                dagger.internal.Preconditions.checkNotNullFromProvides(r0)
                r4 = 2131362825(0x7f0a0409, float:1.8345442E38)
                android.view.View r0 = r0.findViewById(r4)
                r15 = r0
                com.android.keyguard.KeyguardSliceView r15 = (com.android.keyguard.KeyguardSliceView) r15
                dagger.internal.Preconditions.checkNotNullFromProvides(r15)
                dagger.internal.DelegateFactory r0 = r3.activityStarterImplProvider
                java.lang.Object r0 = r0.get()
                r16 = r0
                com.android.systemui.plugins.ActivityStarter r16 = (com.android.systemui.plugins.ActivityStarter) r16
                javax.inject.Provider r0 = r3.configurationControllerImplProvider
                java.lang.Object r0 = r0.get()
                r17 = r0
                com.android.systemui.statusbar.policy.ConfigurationController r17 = (com.android.systemui.statusbar.policy.ConfigurationController) r17
                dagger.internal.DelegateFactory r0 = r3.tunerServiceImplProvider
                java.lang.Object r0 = r0.get()
                r18 = r0
                com.android.systemui.tuner.TunerService r18 = (com.android.systemui.tuner.TunerService) r18
                javax.inject.Provider r0 = r2.dumpManagerProvider
                java.lang.Object r0 = r0.get()
                r19 = r0
                com.android.systemui.dump.DumpManager r19 = (com.android.systemui.dump.DumpManager) r19
                javax.inject.Provider r0 = r3.provideDisplayTrackerProvider
                java.lang.Object r0 = r0.get()
                r20 = r0
                com.android.systemui.settings.DisplayTracker r20 = (com.android.systemui.settings.DisplayTracker) r20
                r12 = r1
                r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl.SwitchingProvider.get():java.lang.Object");
        }
    }

    public DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
    }

    public KeyguardStatusViewController getKeyguardStatusViewController() {
        KeyguardSliceViewController keyguardSliceViewController = (KeyguardSliceViewController) ((Provider) this.keyguardSliceViewControllerProvider).get();
        KeyguardClockSwitch keyguardClockSwitch = (KeyguardClockSwitch) ((KeyguardStatusView) this.presentation).findViewById(2131362800);
        Preconditions.checkNotNullFromProvides(keyguardClockSwitch);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) this.sysUIGoogleSysUIComponentImpl;
        StatusBarStateController statusBarStateController = (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get();
        ClockRegistry clockRegistry = (ClockRegistry) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClockRegistryProvider.get();
        KeyguardSliceViewController keyguardSliceViewController2 = (KeyguardSliceViewController) ((Provider) this.keyguardSliceViewControllerProvider).get();
        NotificationIconAreaController notificationIconAreaController = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconAreaController();
        LockscreenSmartspaceController lockscreenSmartspaceController = (LockscreenSmartspaceController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenSmartspaceControllerProvider.get();
        NotificationIconContainerAlwaysOnDisplayViewBinder notificationIconContainerAlwaysOnDisplayViewBinder = new NotificationIconContainerAlwaysOnDisplayViewBinder((NotificationIconContainerAlwaysOnDisplayViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconContainerAlwaysOnDisplayViewModelProvider.get(), (KeyguardRootViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRootViewModelProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationState(), (StatusBarIconViewBindingFailureTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarIconViewBindingFailureTrackerProvider.get(), (ScreenOffAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenOffAnimationControllerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemBarUtilsState(), new AlwaysOnDisplayNotificationIconViewStore((NotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifCollectionProvider.get()));
        KeyguardUnlockAnimationController keyguardUnlockAnimationController = (KeyguardUnlockAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUnlockAnimationControllerProvider.get();
        SecureSettings secureSettings = (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DelayableExecutor delayableExecutor = (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get();
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
        if (!(display2 == null || context.getDisplayId() == display2.getDisplayId())) {
            context = context.createDisplayContext(display2);
            Intrinsics.checkNotNull(context);
        }
        return new KeyguardStatusViewController((KeyguardStatusView) this.presentation, keyguardSliceViewController, new KeyguardClockSwitchController(keyguardClockSwitch, statusBarStateController, clockRegistry, keyguardSliceViewController2, notificationIconAreaController, lockscreenSmartspaceController, notificationIconContainerAlwaysOnDisplayViewBinder, keyguardUnlockAnimationController, secureSettings, delayableExecutor, executor, dumpManager, new ClockEventController(keyguardInteractor, keyguardTransitionInteractor, broadcastDispatcher, batteryController, keyguardUpdateMonitor, configurationController, context.getResources(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.clockMessageBuffers(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (ZenModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.zenModeControllerImplProvider.get()), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardClockLogProvider.get(), (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get(), (KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockInteractorProvider.get(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (InWindowLauncherUnlockAnimationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.inWindowLauncherUnlockAnimationManagerProvider.get()), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get(), (ScreenOffAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenOffAnimationControllerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1539$$Nest$mkeyguardLogger(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (InteractionJankMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInteractionJankMonitorProvider.get(), (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (PowerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerInteractorProvider.get());
    }

    public DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, KeyguardStatusView keyguardStatusView, Display display2) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.presentation = keyguardStatusView;
        this.display = display2;
        this.keyguardSliceViewControllerProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 0));
    }

    public DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, LifecycleOwner lifecycleOwner, DifferentialMotionFlingController$$ExternalSyntheticLambda0 differentialMotionFlingController$$ExternalSyntheticLambda0, ViewModelStore viewModelStore, TouchInsetManager touchInsetManager) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.presentation = touchInsetManager;
        this.display = lifecycleOwner;
        this.keyguardSliceViewControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 0, 2));
        this.sysUIGoogleSysUIComponentImpl = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 1, 2));
    }
}
