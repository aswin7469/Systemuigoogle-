package com.google.android.systemui;

import android.content.Context;
import com.android.systemui.util.InitializationChecker;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SystemUIGoogleInitializer {
    public final Context mContext;
    public InitializationChecker mInitializationChecker;
    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl mRootComponent;
    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl mSysUIComponent;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl mWMComponent;

    public SystemUIGoogleInitializer(Context context) {
        this.mContext = context;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: com.android.wm.shell.keyguard.KeyguardTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: com.android.wm.shell.transition.ShellTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: com.android.wm.shell.sysui.ShellInterface} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v88, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: com.android.wm.shell.transition.ShellTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: com.android.wm.shell.keyguard.KeyguardTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: com.android.wm.shell.sysui.ShellInterface} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: com.android.wm.shell.transition.ShellTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: com.android.wm.shell.keyguard.KeyguardTransitions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: com.android.wm.shell.sysui.ShellInterface} */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Object, com.android.systemui.dagger.GlobalModule] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.android.systemui.dagger.AndroidInternalsModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v0, types: [com.android.systemui.dagger.FrameworkServicesModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v0, types: [com.android.systemui.util.kotlin.GlobalCoroutinesModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object, com.android.systemui.unfold.UnfoldTransitionModule] */
    /* JADX WARNING: type inference failed for: r7v0, types: [java.lang.Object, com.android.systemui.unfold.UnfoldSharedModule] */
    /* JADX WARNING: type inference failed for: r8v0, types: [com.android.systemui.unfold.UnfoldSharedInternalModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object, com.android.systemui.unfold.UnfoldRotationProviderInternalModule] */
    /* JADX WARNING: type inference failed for: r10v0, types: [java.lang.Object, com.android.systemui.unfold.HingeAngleProviderInternalModule] */
    /* JADX WARNING: type inference failed for: r11v0, types: [com.android.systemui.unfold.FoldStateProviderModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v1, types: [java.lang.Object, com.android.systemui.util.leak.LeakModule] */
    /* JADX WARNING: type inference failed for: r6v1, types: [com.android.systemui.dagger.SharedLibraryModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v1, types: [com.android.systemui.mediaprojection.devicepolicy.MediaProjectionDevicePolicyModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r8v1, types: [com.android.systemui.util.kotlin.SysUICoroutinesModule, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r9v1, types: [java.lang.Object, com.android.systemui.unfold.SysUIUnfoldModule] */
    /* JADX WARNING: type inference failed for: r10v1, types: [java.lang.Object, com.google.android.systemui.globalactions.ShutdownUiModuleGoogle] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void init() {
        /*
            r24 = this;
            r0 = r24
            android.content.Context r14 = r0.mContext
            r14.getClass()
            java.lang.Boolean r15 = java.lang.Boolean.FALSE
            java.lang.Class<android.content.Context> r1 = android.content.Context.class
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r13 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl
            com.android.systemui.dagger.GlobalModule r2 = new com.android.systemui.dagger.GlobalModule
            r2.<init>()
            com.android.systemui.dagger.AndroidInternalsModule r3 = new com.android.systemui.dagger.AndroidInternalsModule
            r3.<init>()
            com.android.systemui.dagger.FrameworkServicesModule r4 = new com.android.systemui.dagger.FrameworkServicesModule
            r4.<init>()
            com.android.systemui.util.kotlin.GlobalCoroutinesModule r5 = new com.android.systemui.util.kotlin.GlobalCoroutinesModule
            r5.<init>()
            com.android.systemui.unfold.UnfoldTransitionModule r6 = new com.android.systemui.unfold.UnfoldTransitionModule
            r6.<init>()
            com.android.systemui.unfold.UnfoldSharedModule r7 = new com.android.systemui.unfold.UnfoldSharedModule
            r7.<init>()
            com.android.systemui.unfold.UnfoldSharedInternalModule r8 = new com.android.systemui.unfold.UnfoldSharedInternalModule
            r8.<init>()
            com.android.systemui.unfold.UnfoldRotationProviderInternalModule r9 = new com.android.systemui.unfold.UnfoldRotationProviderInternalModule
            r9.<init>()
            com.android.systemui.unfold.HingeAngleProviderInternalModule r10 = new com.android.systemui.unfold.HingeAngleProviderInternalModule
            r10.<init>()
            com.android.systemui.unfold.FoldStateProviderModule r11 = new com.android.systemui.unfold.FoldStateProviderModule
            r11.<init>()
            r1 = r13
            r12 = r14
            r16 = r14
            r14 = r13
            r13 = r15
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r0.mRootComponent = r14
            com.android.systemui.util.InitializationChecker r1 = new com.android.systemui.util.InitializationChecker
            boolean r2 = r15.booleanValue()
            r1.<init>(r2)
            r0.mInitializationChecker = r1
            boolean r1 = r1.initializeComponents()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r0.mRootComponent
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder r3 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r2.sysUIGoogleGlobalRootComponentImpl
            r3.<init>(r2)
            com.android.systemui.util.InitializationChecker r4 = r0.mInitializationChecker
            boolean r4 = r4.initializeComponents()
            if (r4 == 0) goto L_0x00a6
            android.content.res.Resources r4 = r16.getResources()
            r5 = 2131034144(0x7f050020, float:1.7678797E38)
            boolean r4 = r4.getBoolean(r5)
            if (r4 != 0) goto L_0x0078
            goto L_0x00a6
        L_0x0078:
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
            if (r2 == 0) goto L_0x0099
            goto L_0x00b1
        L_0x0099:
            java.lang.String r0 = "SystemUIFactory"
            java.lang.String r1 = "Failed to initialize WMComponent"
            android.util.Log.w(r0, r1)
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>()
            throw r0
        L_0x00a6:
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r4 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl
            java.lang.Object r3 = r3.setShellMainThread
            android.os.HandlerThread r3 = (android.os.HandlerThread) r3
            r4.<init>(r2, r3)
            r0.mWMComponent = r4
        L_0x00b1:
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r0.mRootComponent
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r4 = r2.sysUIGoogleGlobalRootComponentImpl
            if (r1 == 0) goto L_0x0184
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r1 = r0.mWMComponent
            javax.inject.Provider r1 = r1.provideShellSysuiCallbacksProvider
            java.lang.Object r1 = r1.get()
            com.android.wm.shell.sysui.ShellInterface r1 = (com.android.wm.shell.sysui.ShellInterface) r1
            r1.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r2 = r0.mWMComponent
            javax.inject.Provider r2 = r2.providePip1Provider
            java.lang.Object r2 = r2.get()
            java.util.Optional r2 = (java.util.Optional) r2
            r2.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r3 = r0.mWMComponent
            javax.inject.Provider r3 = r3.provideSplitScreenProvider
            java.lang.Object r3 = r3.get()
            java.util.Optional r3 = (java.util.Optional) r3
            r3.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r5 = r0.mWMComponent
            javax.inject.Provider r5 = r5.provideOneHandedProvider
            java.lang.Object r5 = r5.get()
            java.util.Optional r5 = (java.util.Optional) r5
            r5.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r6 = r0.mWMComponent
            javax.inject.Provider r6 = r6.provideBubblesProvider
            java.lang.Object r6 = r6.get()
            java.util.Optional r6 = (java.util.Optional) r6
            r6.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r7 = r0.mWMComponent
            javax.inject.Provider r7 = r7.provideTaskViewFactoryProvider
            java.lang.Object r7 = r7.get()
            java.util.Optional r7 = (java.util.Optional) r7
            r7.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r8 = r0.mWMComponent
            javax.inject.Provider r8 = r8.provideRemoteTransitionsProvider
            java.lang.Object r8 = r8.get()
            com.android.wm.shell.transition.ShellTransitions r8 = (com.android.wm.shell.transition.ShellTransitions) r8
            r8.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r9 = r0.mWMComponent
            javax.inject.Provider r9 = r9.provideKeyguardTransitionsProvider
            java.lang.Object r9 = r9.get()
            com.android.wm.shell.keyguard.KeyguardTransitions r9 = (com.android.wm.shell.keyguard.KeyguardTransitions) r9
            r9.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r10 = r0.mWMComponent
            javax.inject.Provider r10 = r10.provideStartingSurfaceProvider
            java.lang.Object r10 = r10.get()
            java.util.Optional r10 = (java.util.Optional) r10
            r10.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r11 = r0.mWMComponent
            javax.inject.Provider r11 = r11.provideDisplayAreaHelperProvider
            java.lang.Object r11 = r11.get()
            java.util.Optional r11 = (java.util.Optional) r11
            r11.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r12 = r0.mWMComponent
            javax.inject.Provider r12 = r12.provideRecentTasksProvider
            java.lang.Object r12 = r12.get()
            java.util.Optional r12 = (java.util.Optional) r12
            r12.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r13 = r0.mWMComponent
            javax.inject.Provider r13 = r13.provideBackAnimationProvider
            java.lang.Object r13 = r13.get()
            java.util.Optional r13 = (java.util.Optional) r13
            r13.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r14 = r0.mWMComponent
            javax.inject.Provider r14 = r14.provideDesktopModeProvider
            java.lang.Object r14 = r14.get()
            java.util.Optional r14 = (java.util.Optional) r14
            r14.getClass()
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl r15 = r0.mWMComponent
            javax.inject.Provider r15 = r15.provideShellSysuiCallbacksProvider
            java.lang.Object r15 = r15.get()
            com.android.wm.shell.sysui.ShellInterface r15 = (com.android.wm.shell.sysui.ShellInterface) r15
            r15.onInit()
            r15 = r6
            r16 = r7
            r17 = r8
            r18 = r9
            r19 = r10
            r20 = r11
            r21 = r12
            r22 = r13
            r23 = r14
            r11 = r1
            r12 = r2
            r13 = r3
            r14 = r5
            goto L_0x01ef
        L_0x0184:
            com.android.systemui.SystemUIInitializer$3 r1 = new com.android.systemui.SystemUIInitializer$3
            r1.<init>()
            r2 = 0
            java.util.Optional r3 = java.util.Optional.ofNullable(r2)
            r3.getClass()
            java.util.Optional r5 = java.util.Optional.ofNullable(r2)
            r5.getClass()
            java.util.Optional r6 = java.util.Optional.ofNullable(r2)
            r6.getClass()
            java.util.Optional r7 = java.util.Optional.ofNullable(r2)
            r7.getClass()
            java.util.Optional r8 = java.util.Optional.ofNullable(r2)
            r8.getClass()
            com.android.systemui.SystemUIInitializer$2 r9 = new com.android.systemui.SystemUIInitializer$2
            r9.<init>()
            com.android.systemui.SystemUIInitializer$1 r10 = new com.android.systemui.SystemUIInitializer$1
            r10.<init>()
            java.util.Optional r11 = java.util.Optional.ofNullable(r2)
            r11.getClass()
            java.util.Optional r12 = java.util.Optional.ofNullable(r2)
            r12.getClass()
            java.util.Optional r13 = java.util.Optional.ofNullable(r2)
            r13.getClass()
            java.util.Optional r14 = java.util.Optional.ofNullable(r2)
            r14.getClass()
            java.util.Optional r2 = java.util.Optional.ofNullable(r2)
            r2.getClass()
            r23 = r2
            r15 = r7
            r16 = r8
            r17 = r9
            r18 = r10
            r20 = r11
            r19 = r12
            r21 = r13
            r22 = r14
            r11 = r1
            r12 = r3
            r13 = r5
            r14 = r6
        L_0x01ef:
            java.lang.Class<java.util.Optional> r1 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r1 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r1 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r1 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r1 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r1 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r1 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r1 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r1 = java.util.Optional.class
            java.lang.Class<java.util.Optional> r1 = java.util.Optional.class
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r1 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl
            com.android.systemui.util.leak.LeakModule r5 = new com.android.systemui.util.leak.LeakModule
            r5.<init>()
            com.android.systemui.dagger.SharedLibraryModule r6 = new com.android.systemui.dagger.SharedLibraryModule
            r6.<init>()
            com.android.systemui.mediaprojection.devicepolicy.MediaProjectionDevicePolicyModule r7 = new com.android.systemui.mediaprojection.devicepolicy.MediaProjectionDevicePolicyModule
            r7.<init>()
            com.android.systemui.util.kotlin.SysUICoroutinesModule r8 = new com.android.systemui.util.kotlin.SysUICoroutinesModule
            r8.<init>()
            com.android.systemui.unfold.SysUIUnfoldModule r9 = new com.android.systemui.unfold.SysUIUnfoldModule
            r9.<init>()
            com.google.android.systemui.globalactions.ShutdownUiModuleGoogle r10 = new com.google.android.systemui.globalactions.ShutdownUiModuleGoogle
            r10.<init>()
            r3 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            r0.mSysUIComponent = r1
            javax.inject.Provider r0 = r1.dependencyProvider
            java.lang.Object r0 = r0.get()
            com.android.systemui.Dependency r0 = (com.android.systemui.Dependency) r0
            com.android.systemui.Dependency$DependencyKey r1 = com.android.systemui.Dependency.TIME_TICK_HANDLER
            dagger.Lazy r2 = r0.mTimeTickHandler
            java.util.Objects.requireNonNull(r2)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r2)
            android.util.ArrayMap r2 = r0.mProviders
            r2.put(r1, r3)
            com.android.systemui.Dependency$DependencyKey r1 = com.android.systemui.Dependency.BG_LOOPER
            dagger.Lazy r3 = r0.mBgLooper
            java.util.Objects.requireNonNull(r3)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r4 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r4.<init>(r3)
            r2.put(r1, r4)
            com.android.systemui.Dependency$DependencyKey r1 = com.android.systemui.Dependency.MAIN_HANDLER
            dagger.Lazy r3 = r0.mMainHandler
            java.util.Objects.requireNonNull(r3)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r4 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r4.<init>(r3)
            r2.put(r1, r4)
            dagger.Lazy r1 = r0.mBroadcastDispatcher
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.broadcast.BroadcastDispatcher> r1 = com.android.systemui.broadcast.BroadcastDispatcher.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mBluetoothController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.policy.BluetoothControllerImpl> r1 = com.android.systemui.statusbar.policy.BluetoothControllerImpl.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mFlashlightController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.policy.FlashlightController> r1 = com.android.systemui.statusbar.policy.FlashlightController.class
            r2.put(r1, r3)
            java.lang.Class<com.android.keyguard.KeyguardUpdateMonitor> r1 = com.android.keyguard.KeyguardUpdateMonitor.class
            dagger.Lazy r3 = r0.mKeyguardUpdateMonitor
            java.util.Objects.requireNonNull(r3)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r4 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r4.<init>(r3)
            r2.put(r1, r4)
            dagger.Lazy r1 = r0.mDeviceProvisionedController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.policy.DeviceProvisionedController> r1 = com.android.systemui.statusbar.policy.DeviceProvisionedController.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mPluginManager
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.plugins.PluginManager> r1 = com.android.systemui.plugins.PluginManager.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mAssistManager
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.google.android.systemui.assist.AssistManagerGoogle> r1 = com.google.android.systemui.assist.AssistManagerGoogle.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mTunerService
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.tuner.TunerService> r1 = com.android.systemui.tuner.TunerService.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mDarkIconDispatcher
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.plugins.DarkIconDispatcher> r1 = com.android.systemui.plugins.DarkIconDispatcher.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mFragmentService
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.fragments.FragmentService> r1 = com.android.systemui.fragments.FragmentService.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mVolumeDialogController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.plugins.VolumeDialogController> r1 = com.android.systemui.plugins.VolumeDialogController.class
            r2.put(r1, r3)
            java.lang.Class<com.android.internal.logging.MetricsLogger> r1 = com.android.internal.logging.MetricsLogger.class
            dagger.Lazy r3 = r0.mMetricsLogger
            java.util.Objects.requireNonNull(r3)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r4 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r4.<init>(r3)
            r2.put(r1, r4)
            dagger.Lazy r1 = r0.mTunablePaddingService
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.tuner.TunablePadding$TunablePaddingService> r1 = com.android.systemui.tuner.TunablePadding$TunablePaddingService.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mUiOffloadThread
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.UiOffloadThread> r1 = com.android.systemui.UiOffloadThread.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mLightBarController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.phone.LightBarController> r1 = com.android.systemui.statusbar.phone.LightBarController.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mOverviewProxyService
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.recents.OverviewProxyService> r1 = com.android.systemui.recents.OverviewProxyService.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mNavBarModeController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.navigationbar.NavigationModeController> r1 = com.android.systemui.navigationbar.NavigationModeController.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mAccessibilityButtonModeObserver
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.accessibility.AccessibilityButtonModeObserver> r1 = com.android.systemui.accessibility.AccessibilityButtonModeObserver.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mAccessibilityButtonListController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.accessibility.AccessibilityButtonTargetsObserver> r1 = com.android.systemui.accessibility.AccessibilityButtonTargetsObserver.class
            r2.put(r1, r3)
            java.lang.Class<com.android.internal.statusbar.IStatusBarService> r1 = com.android.internal.statusbar.IStatusBarService.class
            dagger.Lazy r3 = r0.mIStatusBarService
            java.util.Objects.requireNonNull(r3)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r4 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r4.<init>(r3)
            r2.put(r1, r4)
            dagger.Lazy r1 = r0.mNotificationRemoteInputManagerCallback
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback> r1 = com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mNavigationBarController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.navigationbar.NavigationBarControllerImpl> r1 = com.android.systemui.navigationbar.NavigationBarControllerImpl.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mStatusBarStateController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.plugins.statusbar.StatusBarStateController> r1 = com.android.systemui.plugins.statusbar.StatusBarStateController.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mNotificationMediaManager
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.NotificationMediaManager> r1 = com.android.systemui.statusbar.NotificationMediaManager.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mSysUiStateFlagsContainer
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.model.SysUiState> r1 = com.android.systemui.model.SysUiState.class
            r2.put(r1, r3)
            java.lang.Class<com.android.systemui.statusbar.CommandQueue> r1 = com.android.systemui.statusbar.CommandQueue.class
            dagger.Lazy r3 = r0.mCommandQueue
            java.util.Objects.requireNonNull(r3)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r4 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r4.<init>(r3)
            r2.put(r1, r4)
            java.lang.Class<com.android.internal.logging.UiEventLogger> r1 = com.android.internal.logging.UiEventLogger.class
            dagger.Lazy r3 = r0.mUiEventLogger
            java.util.Objects.requireNonNull(r3)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r4 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r4.<init>(r3)
            r2.put(r1, r4)
            java.lang.Class<com.android.systemui.flags.FeatureFlags> r1 = com.android.systemui.flags.FeatureFlags.class
            dagger.Lazy r3 = r0.mFeatureFlagsLazy
            java.util.Objects.requireNonNull(r3)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r4 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r4.<init>(r3)
            r2.put(r1, r4)
            dagger.Lazy r1 = r0.mContentInsetsProviderLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider> r1 = com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mNotificationSectionsManagerLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.notification.stack.NotificationSectionsManager> r1 = com.android.systemui.statusbar.notification.stack.NotificationSectionsManager.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mScreenOffAnimationController
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.phone.ScreenOffAnimationController> r1 = com.android.systemui.statusbar.phone.ScreenOffAnimationController.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mAmbientStateLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.notification.stack.AmbientState> r1 = com.android.systemui.statusbar.notification.stack.AmbientState.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mGroupMembershipManagerLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl> r1 = com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mGroupExpansionManagerLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl> r1 = com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mSystemUIDialogManagerLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.phone.SystemUIDialogManager> r1 = com.android.systemui.statusbar.phone.SystemUIDialogManager.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mDialogTransitionAnimatorLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.animation.DialogTransitionAnimator> r1 = com.android.systemui.animation.DialogTransitionAnimator.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mUserTrackerLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.settings.UserTracker> r1 = com.android.systemui.settings.UserTracker.class
            r2.put(r1, r3)
            dagger.Lazy r1 = r0.mStatusBarWindowControllerLazy
            java.util.Objects.requireNonNull(r1)
            com.android.systemui.Dependency$$ExternalSyntheticLambda0 r3 = new com.android.systemui.Dependency$$ExternalSyntheticLambda0
            r3.<init>(r1)
            java.lang.Class<com.android.systemui.statusbar.window.StatusBarWindowController> r1 = com.android.systemui.statusbar.window.StatusBarWindowController.class
            r2.put(r1, r3)
            com.android.systemui.Dependency.setInstance(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.SystemUIGoogleInitializer.init():void");
    }
}
