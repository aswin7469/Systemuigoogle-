package com.google.android.systemui;

import android.content.Context;
import com.android.systemui.util.InitializationChecker;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SystemUIGoogleInitializer {
    public final Context mContext;
    public InitializationChecker mInitializationChecker;
    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl mRootComponent;
    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl mSysUIComponent;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl mWMComponent;

    public SystemUIGoogleInitializer(Context context) {
        this.mContext = context;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v0, resolved type: com.android.wm.shell.keyguard.KeyguardTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: com.android.wm.shell.transition.ShellTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: com.android.wm.shell.sysui.ShellInterface} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: com.google.android.systemui.globalactions.ShutdownUiModuleGoogle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v1, resolved type: com.android.wm.shell.transition.ShellTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v1, resolved type: com.android.wm.shell.keyguard.KeyguardTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: com.android.wm.shell.sysui.ShellInterface} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v2, resolved type: com.android.wm.shell.transition.ShellTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v2, resolved type: com.android.wm.shell.keyguard.KeyguardTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: com.android.wm.shell.sysui.ShellInterface} */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Object, com.android.systemui.dagger.GlobalModule] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.android.systemui.dagger.AndroidInternalsModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v0, types: [com.android.systemui.dagger.FrameworkServicesModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object, com.android.systemui.unfold.UnfoldTransitionModule] */
    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object, com.android.systemui.unfold.UnfoldSharedModule] */
    /* JADX WARNING: type inference failed for: r7v0, types: [com.android.systemui.unfold.UnfoldSharedInternalModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r8v0, types: [java.lang.Object, com.android.systemui.unfold.UnfoldRotationProviderInternalModule] */
    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object, com.android.systemui.unfold.HingeAngleProviderInternalModule] */
    /* JADX WARNING: type inference failed for: r10v0, types: [com.android.systemui.unfold.FoldStateProviderModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Object, com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$Builder] */
    /* JADX WARNING: type inference failed for: r5v1, types: [java.lang.Object, com.android.systemui.util.leak.LeakModule] */
    /* JADX WARNING: type inference failed for: r6v1, types: [com.android.systemui.dagger.SharedLibraryModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v1, types: [com.android.systemui.communal.dagger.CommunalModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r8v1, types: [java.lang.Object, com.android.systemui.util.kotlin.CoroutinesModule] */
    /* JADX WARNING: type inference failed for: r9v1, types: [com.android.systemui.mediaprojection.devicepolicy.MediaProjectionDevicePolicyModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r10v1, types: [java.lang.Object, com.android.systemui.unfold.SysUIUnfoldModule] */
    /* JADX WARNING: type inference failed for: r11v1, types: [java.lang.Object, com.android.systemui.keyguard.dagger.KeyguardModule] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void init() {
        /*
            r28 = this;
            r0 = r28
            android.content.Context r13 = r0.mContext
            r13.getClass()
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            java.lang.Class<android.content.Context> r1 = android.content.Context.class
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r15 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl
            com.android.systemui.dagger.GlobalModule r2 = new com.android.systemui.dagger.GlobalModule
            r2.<init>()
            com.android.systemui.dagger.AndroidInternalsModule r3 = new com.android.systemui.dagger.AndroidInternalsModule
            r3.<init>()
            com.android.systemui.dagger.FrameworkServicesModule r4 = new com.android.systemui.dagger.FrameworkServicesModule
            r4.<init>()
            com.android.systemui.unfold.UnfoldTransitionModule r5 = new com.android.systemui.unfold.UnfoldTransitionModule
            r5.<init>()
            com.android.systemui.unfold.UnfoldSharedModule r6 = new com.android.systemui.unfold.UnfoldSharedModule
            r6.<init>()
            com.android.systemui.unfold.UnfoldSharedInternalModule r7 = new com.android.systemui.unfold.UnfoldSharedInternalModule
            r7.<init>()
            com.android.systemui.unfold.UnfoldRotationProviderInternalModule r8 = new com.android.systemui.unfold.UnfoldRotationProviderInternalModule
            r8.<init>()
            com.android.systemui.unfold.HingeAngleProviderInternalModule r9 = new com.android.systemui.unfold.HingeAngleProviderInternalModule
            r9.<init>()
            com.android.systemui.unfold.FoldStateProviderModule r10 = new com.android.systemui.unfold.FoldStateProviderModule
            r10.<init>()
            r1 = r15
            r11 = r13
            r12 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0.mRootComponent = r15
            com.android.systemui.util.InitializationChecker r1 = new com.android.systemui.util.InitializationChecker
            boolean r2 = r14.booleanValue()
            r1.<init>(r2)
            r0.mInitializationChecker = r1
            boolean r1 = r1.initializeComponents()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r0.mRootComponent
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$Builder r3 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$Builder
            r3.<init>()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r2.sysUIGoogleGlobalRootComponentImpl
            r3.context = r2
            com.android.systemui.util.InitializationChecker r2 = r0.mInitializationChecker
            boolean r2 = r2.initializeComponents()
            if (r2 == 0) goto L_0x00a0
            android.content.res.Resources r2 = r13.getResources()
            r4 = 2131034144(0x7f050020, float:1.7678797E38)
            boolean r2 = r2.getBoolean(r4)
            if (r2 != 0) goto L_0x0072
            goto L_0x00a0
        L_0x0072:
            android.os.HandlerThread r2 = new android.os.HandlerThread
            java.lang.String r4 = "wmshell.main"
            r5 = -4
            r2.<init>(r4, r5)
            r2.start()
            android.os.Looper r4 = r2.getLooper()
            android.os.Handler r4 = android.os.Handler.createAsync(r4)
            com.android.systemui.SystemUIInitializer$$ExternalSyntheticLambda0 r5 = new com.android.systemui.SystemUIInitializer$$ExternalSyntheticLambda0
            r5.<init>(r0, r3, r2)
            r2 = 5000(0x1388, double:2.4703E-320)
            boolean r2 = r4.runWithScissors(r5, r2)
            if (r2 == 0) goto L_0x0093
            goto L_0x00af
        L_0x0093:
            java.lang.String r0 = "SystemUIFactory"
            java.lang.String r1 = "Failed to initialize WMComponent"
            android.util.Log.w(r0, r1)
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>()
            throw r0
        L_0x00a0:
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r2 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl
            java.lang.Object r4 = r3.context
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r4 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) r4
            java.lang.Object r3 = r3.instrumentationTest
            android.os.HandlerThread r3 = (android.os.HandlerThread) r3
            r2.<init>(r4, r3)
            r0.mWMComponent = r2
        L_0x00af:
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r0.mRootComponent
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r2.sysUIGoogleGlobalRootComponentImpl
            if (r1 == 0) goto L_0x0187
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r3 = r0.mWMComponent
            javax.inject.Provider r3 = r3.provideShellSysuiCallbacksProvider
            java.lang.Object r3 = r3.get()
            com.android.wm.shell.sysui.ShellInterface r3 = (com.android.wm.shell.sysui.ShellInterface) r3
            r3.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r4 = r0.mWMComponent
            javax.inject.Provider r4 = r4.providePip1Provider
            java.lang.Object r4 = r4.get()
            java.util.Optional r4 = (java.util.Optional) r4
            r4.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r5 = r0.mWMComponent
            javax.inject.Provider r5 = r5.provideSplitScreenProvider
            java.lang.Object r5 = r5.get()
            java.util.Optional r5 = (java.util.Optional) r5
            r5.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r6 = r0.mWMComponent
            javax.inject.Provider r6 = r6.provideOneHandedProvider
            java.lang.Object r6 = r6.get()
            java.util.Optional r6 = (java.util.Optional) r6
            r6.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r7 = r0.mWMComponent
            javax.inject.Provider r7 = r7.provideBubblesProvider
            java.lang.Object r7 = r7.get()
            java.util.Optional r7 = (java.util.Optional) r7
            r7.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r8 = r0.mWMComponent
            javax.inject.Provider r8 = r8.provideTaskViewFactoryProvider
            java.lang.Object r8 = r8.get()
            java.util.Optional r8 = (java.util.Optional) r8
            r8.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r9 = r0.mWMComponent
            javax.inject.Provider r9 = r9.provideRemoteTransitionsProvider
            java.lang.Object r9 = r9.get()
            com.android.wm.shell.transition.ShellTransitions r9 = (com.android.wm.shell.transition.ShellTransitions) r9
            r9.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r10 = r0.mWMComponent
            javax.inject.Provider r10 = r10.provideKeyguardTransitionsProvider
            java.lang.Object r10 = r10.get()
            com.android.wm.shell.keyguard.KeyguardTransitions r10 = (com.android.wm.shell.keyguard.KeyguardTransitions) r10
            r10.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r11 = r0.mWMComponent
            javax.inject.Provider r11 = r11.provideStartingSurfaceProvider
            java.lang.Object r11 = r11.get()
            java.util.Optional r11 = (java.util.Optional) r11
            r11.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r12 = r0.mWMComponent
            javax.inject.Provider r12 = r12.provideDisplayAreaHelperProvider
            java.lang.Object r12 = r12.get()
            java.util.Optional r12 = (java.util.Optional) r12
            r12.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r13 = r0.mWMComponent
            javax.inject.Provider r13 = r13.provideRecentTasksProvider
            java.lang.Object r13 = r13.get()
            java.util.Optional r13 = (java.util.Optional) r13
            r13.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r14 = r0.mWMComponent
            javax.inject.Provider r14 = r14.provideBackAnimationProvider
            java.lang.Object r14 = r14.get()
            java.util.Optional r14 = (java.util.Optional) r14
            r14.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r15 = r0.mWMComponent
            javax.inject.Provider r15 = r15.provideDesktopModeProvider
            java.lang.Object r15 = r15.get()
            java.util.Optional r15 = (java.util.Optional) r15
            r15.getClass()
            r16 = r3
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r3 = r0.mWMComponent
            javax.inject.Provider r3 = r3.provideShellSysuiCallbacksProvider
            java.lang.Object r3 = r3.get()
            com.android.wm.shell.sysui.ShellInterface r3 = (com.android.wm.shell.sysui.ShellInterface) r3
            r3.onInit()
            r17 = r7
            r18 = r8
            r19 = r9
            r20 = r10
            r21 = r11
            r22 = r12
            r23 = r13
            r24 = r14
            r25 = r15
            r13 = r16
            r14 = r4
            r15 = r5
            r16 = r6
            goto L_0x01f4
        L_0x0187:
            com.android.systemui.SystemUIInitializer$3 r3 = new com.android.systemui.SystemUIInitializer$3
            r3.<init>()
            r4 = 0
            java.util.Optional r5 = java.util.Optional.ofNullable(r4)
            r5.getClass()
            java.util.Optional r6 = java.util.Optional.ofNullable(r4)
            r6.getClass()
            java.util.Optional r7 = java.util.Optional.ofNullable(r4)
            r7.getClass()
            java.util.Optional r8 = java.util.Optional.ofNullable(r4)
            r8.getClass()
            java.util.Optional r9 = java.util.Optional.ofNullable(r4)
            r9.getClass()
            com.android.systemui.SystemUIInitializer$2 r10 = new com.android.systemui.SystemUIInitializer$2
            r10.<init>()
            com.android.systemui.SystemUIInitializer$1 r11 = new com.android.systemui.SystemUIInitializer$1
            r11.<init>()
            java.util.Optional r12 = java.util.Optional.ofNullable(r4)
            r12.getClass()
            java.util.Optional r13 = java.util.Optional.ofNullable(r4)
            r13.getClass()
            java.util.Optional r14 = java.util.Optional.ofNullable(r4)
            r14.getClass()
            java.util.Optional r15 = java.util.Optional.ofNullable(r4)
            r15.getClass()
            java.util.Optional r4 = java.util.Optional.ofNullable(r4)
            r4.getClass()
            r25 = r4
            r16 = r7
            r17 = r8
            r18 = r9
            r19 = r10
            r20 = r11
            r22 = r12
            r21 = r13
            r23 = r14
            r24 = r15
            r13 = r3
            r14 = r5
            r15 = r6
        L_0x01f4:
            java.lang.Class<java.util.Optional> r3 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r3 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r3 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r3 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r3 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r3 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r3 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r3 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r3 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r3 = java.util.Optional.class
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r12 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl
            com.android.systemui.util.leak.LeakModule r5 = new com.android.systemui.util.leak.LeakModule
            r5.<init>()
            com.android.systemui.dagger.SharedLibraryModule r6 = new com.android.systemui.dagger.SharedLibraryModule
            r6.<init>()
            com.android.systemui.communal.dagger.CommunalModule r7 = new com.android.systemui.communal.dagger.CommunalModule
            r7.<init>()
            com.android.systemui.util.kotlin.CoroutinesModule r8 = new com.android.systemui.util.kotlin.CoroutinesModule
            r8.<init>()
            com.android.systemui.mediaprojection.devicepolicy.MediaProjectionDevicePolicyModule r9 = new com.android.systemui.mediaprojection.devicepolicy.MediaProjectionDevicePolicyModule
            r9.<init>()
            com.android.systemui.unfold.SysUIUnfoldModule r10 = new com.android.systemui.unfold.SysUIUnfoldModule
            r10.<init>()
            com.android.systemui.keyguard.dagger.KeyguardModule r11 = new com.android.systemui.keyguard.dagger.KeyguardModule
            r11.<init>()
            com.google.android.systemui.globalactions.ShutdownUiModuleGoogle r26 = new com.google.android.systemui.globalactions.ShutdownUiModuleGoogle
            r26.<init>()
            r3 = r12
            r4 = r2
            r27 = r2
            r2 = r12
            r12 = r26
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
            r0.mSysUIComponent = r2
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x02a0
            javax.inject.Provider r1 = r2.provideSysUIUnfoldComponentProvider
            java.lang.Object r1 = r1.get()
            java.util.Optional r1 = (java.util.Optional) r1
            com.android.systemui.dagger.SysUIComponent$$ExternalSyntheticLambda0 r6 = new com.android.systemui.dagger.SysUIComponent$$ExternalSyntheticLambda0
            r6.<init>(r5)
            r1.ifPresent(r6)
            javax.inject.Provider r1 = r2.mediaMuteAwaitConnectionCliProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.media.muteawait.MediaMuteAwaitConnectionCli r1 = (com.android.systemui.media.muteawait.MediaMuteAwaitConnectionCli) r1
            javax.inject.Provider r1 = r2.nearbyMediaDevicesManagerProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.media.nearby.NearbyMediaDevicesManager r1 = (com.android.systemui.media.nearby.NearbyMediaDevicesManager) r1
            javax.inject.Provider r1 = r2.connectingDisplayViewModelProvider
            java.lang.Object r1 = r1.get()
            com.android.systemui.display.ui.viewmodel.ConnectingDisplayViewModel r1 = (com.android.systemui.display.ui.viewmodel.ConnectingDisplayViewModel) r1
            r1.init()
            r1 = r27
            javax.inject.Provider r6 = r1.providesFoldStateLoggingProvider
            java.lang.Object r6 = r6.get()
            java.util.Optional r6 = (java.util.Optional) r6
            com.android.systemui.dagger.SysUIComponent$$ExternalSyntheticLambda0 r7 = new com.android.systemui.dagger.SysUIComponent$$ExternalSyntheticLambda0
            r7.<init>(r4)
            r6.ifPresent(r7)
            javax.inject.Provider r6 = r1.providesFoldStateLoggerProvider
            java.lang.Object r6 = r6.get()
            java.util.Optional r6 = (java.util.Optional) r6
            com.android.systemui.dagger.SysUIComponent$$ExternalSyntheticLambda0 r7 = new com.android.systemui.dagger.SysUIComponent$$ExternalSyntheticLambda0
            r7.<init>(r3)
            r6.ifPresent(r7)
            javax.inject.Provider r1 = r1.unfoldTransitionProgressProvider
            java.lang.Object r1 = r1.get()
            java.util.Optional r1 = (java.util.Optional) r1
            com.android.systemui.dagger.SysUIComponent$$ExternalSyntheticLambda1 r6 = new com.android.systemui.dagger.SysUIComponent$$ExternalSyntheticLambda1
            r6.<init>(r5, r2)
            r1.ifPresent(r6)
        L_0x02a0:
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r0 = r0.mSysUIComponent
            javax.inject.Provider r0 = r0.dependencyProvider
            java.lang.Object r0 = r0.get()
            com.android.systemui.Dependency r0 = (com.android.systemui.Dependency) r0
            com.android.systemui.Dependency$DependencyKey r1 = com.android.systemui.Dependency.TIME_TICK_HANDLER
            dagger.Lazy r2 = r0.mTimeTickHandler
            java.util.Objects.requireNonNull(r2)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r6 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r6.<init>(r2, r5)
            android.util.ArrayMap r2 = r0.mProviders
            r2.put(r1, r6)
            com.android.systemui.Dependency$DependencyKey r1 = com.android.systemui.Dependency.BG_LOOPER
            dagger.Lazy r6 = r0.mBgLooper
            java.util.Objects.requireNonNull(r6)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r7 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r8 = 11
            r7.<init>(r6, r8)
            r2.put(r1, r7)
            com.android.systemui.Dependency$DependencyKey r1 = com.android.systemui.Dependency.MAIN_HANDLER
            dagger.Lazy r6 = r0.mMainHandler
            java.util.Objects.requireNonNull(r6)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r7 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r8 = 22
            r7.<init>(r6, r8)
            r2.put(r1, r7)
            dagger.Lazy r1 = r0.mBroadcastDispatcher
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda1 r6 = new com.android.systemui.Dependency$$ExternalSyntheticLambda1
            r6.<init>(r1, r5)
            java.lang.Class<com.android.systemui.broadcast.BroadcastDispatcher> r1 = com.android.systemui.broadcast.BroadcastDispatcher.class
            r2.put(r1, r6)
            dagger.Lazy r1 = r0.mBluetoothController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda1 r5 = new com.android.systemui.Dependency$$ExternalSyntheticLambda1
            r6 = 4
            r5.<init>(r1, r6)
            java.lang.Class<com.android.systemui.statusbar.policy.BluetoothControllerImpl> r1 = com.android.systemui.statusbar.policy.BluetoothControllerImpl.class
            r2.put(r1, r5)
            dagger.Lazy r1 = r0.mFlashlightController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda1 r5 = new com.android.systemui.Dependency$$ExternalSyntheticLambda1
            r7 = 5
            r5.<init>(r1, r7)
            java.lang.Class<com.android.systemui.statusbar.policy.FlashlightController> r1 = com.android.systemui.statusbar.policy.FlashlightController.class
            r2.put(r1, r5)
            java.lang.Class<com.android.keyguard.KeyguardUpdateMonitor> r1 = com.android.keyguard.KeyguardUpdateMonitor.class
            dagger.Lazy r5 = r0.mKeyguardUpdateMonitor
            java.util.Objects.requireNonNull(r5)
            com.android.systemui.Dependency$$ExternalSyntheticLambda1 r8 = new com.android.systemui.Dependency$$ExternalSyntheticLambda1
            r9 = 6
            r8.<init>(r5, r9)
            r2.put(r1, r8)
            dagger.Lazy r1 = r0.mDeviceProvisionedController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda1 r5 = new com.android.systemui.Dependency$$ExternalSyntheticLambda1
            r8 = 7
            r5.<init>(r1, r8)
            java.lang.Class<com.android.systemui.statusbar.policy.DeviceProvisionedController> r1 = com.android.systemui.statusbar.policy.DeviceProvisionedController.class
            r2.put(r1, r5)
            dagger.Lazy r1 = r0.mPluginManager
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda1 r5 = new com.android.systemui.Dependency$$ExternalSyntheticLambda1
            r10 = 8
            r5.<init>(r1, r10)
            java.lang.Class<com.android.systemui.plugins.PluginManager> r1 = com.android.systemui.plugins.PluginManager.class
            r2.put(r1, r5)
            dagger.Lazy r1 = r0.mAssistManager
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda1 r5 = new com.android.systemui.Dependency$$ExternalSyntheticLambda1
            r11 = 9
            r5.<init>(r1, r11)
            java.lang.Class<com.android.systemui.assist.AssistManager> r1 = com.android.systemui.assist.AssistManager.class
            r2.put(r1, r5)
            dagger.Lazy r1 = r0.mTunerService
            java.lang.Class<com.android.systemui.tuner.TunerService> r5 = com.android.systemui.tuner.TunerService.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r4, r2, r5)
            dagger.Lazy r1 = r0.mDarkIconDispatcher
            java.lang.Class<com.android.systemui.plugins.DarkIconDispatcher> r5 = com.android.systemui.plugins.DarkIconDispatcher.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r3, r2, r5)
            dagger.Lazy r1 = r0.mFragmentService
            r5 = 3
            java.lang.Class<com.android.systemui.fragments.FragmentService> r12 = com.android.systemui.fragments.FragmentService.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r5, r2, r12)
            dagger.Lazy r1 = r0.mVolumeDialogController
            java.lang.Class<com.android.systemui.plugins.VolumeDialogController> r12 = com.android.systemui.plugins.VolumeDialogController.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r12)
            java.lang.Class<com.android.internal.logging.MetricsLogger> r1 = com.android.internal.logging.MetricsLogger.class
            dagger.Lazy r6 = r0.mMetricsLogger
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r6, r6, r7, r2, r1)
            dagger.Lazy r1 = r0.mTunablePaddingService
            java.lang.Class<com.android.systemui.tuner.TunablePadding$TunablePaddingService> r6 = com.android.systemui.tuner.TunablePadding$TunablePaddingService.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r9, r2, r6)
            dagger.Lazy r1 = r0.mUiOffloadThread
            java.lang.Class<com.android.systemui.UiOffloadThread> r6 = com.android.systemui.UiOffloadThread.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r8, r2, r6)
            dagger.Lazy r1 = r0.mLightBarController
            java.lang.Class<com.android.systemui.statusbar.phone.LightBarController> r6 = com.android.systemui.statusbar.phone.LightBarController.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r10, r2, r6)
            dagger.Lazy r1 = r0.mOverviewProxyService
            java.lang.Class<com.android.systemui.recents.OverviewProxyService> r6 = com.android.systemui.recents.OverviewProxyService.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r11, r2, r6)
            dagger.Lazy r1 = r0.mNavBarModeController
            r6 = 10
            java.lang.Class<com.android.systemui.navigationbar.NavigationModeController> r7 = com.android.systemui.navigationbar.NavigationModeController.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mAccessibilityButtonModeObserver
            r6 = 12
            java.lang.Class<com.android.systemui.accessibility.AccessibilityButtonModeObserver> r7 = com.android.systemui.accessibility.AccessibilityButtonModeObserver.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mAccessibilityButtonListController
            r6 = 13
            java.lang.Class<com.android.systemui.accessibility.AccessibilityButtonTargetsObserver> r7 = com.android.systemui.accessibility.AccessibilityButtonTargetsObserver.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            java.lang.Class<com.android.internal.statusbar.IStatusBarService> r1 = com.android.internal.statusbar.IStatusBarService.class
            dagger.Lazy r6 = r0.mIStatusBarService
            r7 = 14
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r6, r6, r7, r2, r1)
            dagger.Lazy r1 = r0.mNotificationRemoteInputManagerCallback
            r6 = 15
            java.lang.Class<com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback> r7 = com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mNavigationBarController
            r6 = 16
            java.lang.Class<com.android.systemui.navigationbar.NavigationBarController> r7 = com.android.systemui.navigationbar.NavigationBarController.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mStatusBarStateController
            r6 = 17
            java.lang.Class<com.android.systemui.plugins.statusbar.StatusBarStateController> r7 = com.android.systemui.plugins.statusbar.StatusBarStateController.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mNotificationMediaManager
            r6 = 18
            java.lang.Class<com.android.systemui.statusbar.NotificationMediaManager> r7 = com.android.systemui.statusbar.NotificationMediaManager.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mSysUiStateFlagsContainer
            r6 = 19
            java.lang.Class<com.android.systemui.model.SysUiState> r7 = com.android.systemui.model.SysUiState.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            java.lang.Class<com.android.systemui.statusbar.CommandQueue> r1 = com.android.systemui.statusbar.CommandQueue.class
            dagger.Lazy r6 = r0.mCommandQueue
            r7 = 20
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r6, r6, r7, r2, r1)
            java.lang.Class<com.android.internal.logging.UiEventLogger> r1 = com.android.internal.logging.UiEventLogger.class
            dagger.Lazy r6 = r0.mUiEventLogger
            r7 = 21
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r6, r6, r7, r2, r1)
            java.lang.Class<com.android.systemui.flags.FeatureFlags> r1 = com.android.systemui.flags.FeatureFlags.class
            dagger.Lazy r6 = r0.mFeatureFlagsLazy
            r7 = 23
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r6, r6, r7, r2, r1)
            dagger.Lazy r1 = r0.mContentInsetsProviderLazy
            r6 = 24
            java.lang.Class<com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider> r7 = com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mNotificationSectionsManagerLazy
            r6 = 25
            java.lang.Class<com.android.systemui.statusbar.notification.stack.NotificationSectionsManager> r7 = com.android.systemui.statusbar.notification.stack.NotificationSectionsManager.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mScreenOffAnimationController
            r6 = 26
            java.lang.Class<com.android.systemui.statusbar.phone.ScreenOffAnimationController> r7 = com.android.systemui.statusbar.phone.ScreenOffAnimationController.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mAmbientStateLazy
            r6 = 27
            java.lang.Class<com.android.systemui.statusbar.notification.stack.AmbientState> r7 = com.android.systemui.statusbar.notification.stack.AmbientState.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mGroupMembershipManagerLazy
            r6 = 28
            java.lang.Class<com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl> r7 = com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mGroupExpansionManagerLazy
            r6 = 29
            java.lang.Class<com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl> r7 = com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl.class
            com.google.android.systemui.SystemUIGoogleInitializer$$ExternalSyntheticOutline0.m(r1, r1, r6, r2, r7)
            dagger.Lazy r1 = r0.mSystemUIDialogManagerLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda1 r6 = new com.android.systemui.Dependency$$ExternalSyntheticLambda1
            r6.<init>(r1, r4)
            java.lang.Class<com.android.systemui.statusbar.phone.SystemUIDialogManager> r1 = com.android.systemui.statusbar.phone.SystemUIDialogManager.class
            r2.put(r1, r6)
            dagger.Lazy r1 = r0.mDialogLaunchAnimatorLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda1 r4 = new com.android.systemui.Dependency$$ExternalSyntheticLambda1
            r4.<init>(r1, r3)
            java.lang.Class<com.android.systemui.animation.DialogLaunchAnimator> r1 = com.android.systemui.animation.DialogLaunchAnimator.class
            r2.put(r1, r4)
            dagger.Lazy r1 = r0.mUserTrackerLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda1 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda1
            r3.<init>(r1, r5)
            java.lang.Class<com.android.systemui.settings.UserTracker> r1 = com.android.systemui.settings.UserTracker.class
            r2.put(r1, r3)
            com.android.systemui.Dependency.setInstance(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.SystemUIGoogleInitializer.init():void");
    }
}
