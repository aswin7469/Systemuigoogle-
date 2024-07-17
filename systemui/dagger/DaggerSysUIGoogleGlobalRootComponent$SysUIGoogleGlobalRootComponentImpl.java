package com.google.android.systemui.dagger;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.hardware.display.AmbientDisplayConfiguration;
import android.media.projection.MediaProjectionManager;
import android.util.DisplayMetrics;
import com.android.systemui.dagger.AndroidInternalsModule;
import com.android.systemui.dagger.FrameworkServicesModule;
import com.android.systemui.dagger.GlobalModule;
import com.android.systemui.unfold.FoldStateProviderModule;
import com.android.systemui.unfold.HingeAngleProviderInternalModule;
import com.android.systemui.unfold.UnfoldRotationProviderInternalModule;
import com.android.systemui.unfold.UnfoldSharedInternalModule;
import com.android.systemui.unfold.UnfoldSharedModule;
import com.android.systemui.unfold.UnfoldTransitionModule;
import com.android.systemui.unfold.data.repository.UnfoldTransitionRepositoryImpl;
import com.android.systemui.unfold.domain.interactor.UnfoldTransitionInteractorImpl;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.SingleCheck;
import java.util.Optional;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl {
    public final Provider activityManagerActivityTypeProvider;
    public final AndroidInternalsModule androidInternalsModule;
    public final Provider buildInfoProvider;
    public final Context context;
    public final Provider deviceStateManagerFoldProvider;
    public final Provider deviceStateRepositoryImplProvider;
    public final Provider dumpManagerProvider;
    public final SwitchingProvider executionImplProvider;
    public final Provider factoryProvider;
    public final Provider factoryProvider2;
    public final Provider factoryProvider3;
    public final Provider factoryProvider4;
    public final Provider factoryProvider5;
    public final Provider factoryProvider6;
    public final Provider factoryProvider7;
    public final Provider factoryProvider8;
    public final SwitchingProvider fixedTimingTransitionProgressProvider;
    public final FoldStateProviderModule foldStateProviderModule;
    public final Provider foldStateRepositoryProvider;
    public final FrameworkServicesModule frameworkServicesModule;
    public final GlobalModule globalModule;
    public final HingeAngleProviderInternalModule hingeAngleProviderInternalModule;
    public final Boolean instrumentationTest;
    public final Provider lifecycleScreenStatusProvider;
    public final Provider lowLightTransitionCoordinatorProvider;
    public final Provider opaEnabledSettingsProvider;
    public final Provider pluginDependencyProvider;
    public final Provider pluginEnablerImplProvider;
    public final Provider provideAccessibilityManagerProvider;
    public final Provider provideActivityManagerProvider;
    public final Provider provideActivityTaskManagerProvider;
    public final Provider provideAlarmManagerProvider;
    public final Provider provideAmbientContextManagerProvider;
    public final SwitchingProvider provideApplicationContextProvider;
    public final Provider provideAsyncLayoutInflaterProvider;
    public final Provider provideAudioManagerProvider;
    public final Provider provideBgFoldStateProvider;
    public final Provider provideBgLooperProvider;
    public final Provider provideBgRotationChangeProvider;
    public final Provider provideBluetoothAdapterProvider;
    public final Provider provideBluetoothManagerProvider;
    public final Provider provideCameraManagerProvider;
    public final Provider provideCarrierConfigManagerProvider;
    public final Provider provideClipboardManagerProvider;
    public final Provider provideColorDisplayManagerProvider;
    public final Provider provideConnectivityManagagerProvider;
    public final Provider provideContentResolverProvider;
    public final Provider provideCrossWindowBlurListenersProvider;
    public final Provider provideDevicePolicyManagerProvider;
    public final Provider provideDeviceStateManagerProvider;
    public final Provider provideDisplayManagerProvider;
    public final Provider provideExecutionProvider;
    public final Provider provideFaceManagerProvider;
    public final Provider provideFoldStateProvider;
    public final Provider provideIActivityManagerProvider;
    public final Provider provideIActivityTaskManagerProvider;
    public final Provider provideIAudioServiceProvider;
    public final Provider provideIBatteryStatsProvider;
    public final Provider provideIDreamManagerProvider;
    public final Provider provideIMediaProjectionManagerProvider;
    public final Provider provideINotificationManagerProvider;
    public final Provider provideIPackageManagerProvider;
    public final Provider provideIStatusBarServiceProvider;
    public final Provider provideIUriGrantsManagerProvider;
    public final Provider provideIVrManagerProvider;
    public final Provider provideIWindowManagerProvider;
    public final Provider provideInputManagerProvider;
    public final Provider provideInputMethodManagerProvider;
    public final Provider provideInteractionJankMonitorProvider;
    public final Provider provideIsTestHarnessProvider;
    public final Provider provideJobSchedulerProvider;
    public final Provider provideKeyguardManagerProvider;
    public final Provider provideLatencyTrackerProvider;
    public final Provider provideLauncherAppsProvider;
    public final Provider provideLockPatternUtilsProvider;
    public final Provider provideMainDelayableExecutorProvider;
    public final Provider provideMainExecutorProvider;
    public final SwitchingProvider provideMainHandlerProvider;
    public final SwitchingProvider provideMediaRouter2ManagerProvider;
    public final Provider provideMetricsLoggerProvider;
    public final Provider provideNaturalRotationProgressProvider;
    public final Provider provideNetworkScoreManagerProvider;
    public final Provider provideNotificationManagerCompatProvider;
    public final Provider provideNotificationManagerProvider;
    public final Provider provideOptionalTelecomManagerProvider;
    public final Provider provideOptionalVibratorProvider;
    public final Provider provideOverlayManagerProvider;
    public final Provider providePackageManagerProvider;
    public final Provider providePackageManagerWrapperProvider;
    public final Provider providePermissionManagerProvider;
    public final Provider providePluginInstanceManagerFactoryProvider;
    public final Provider providePowerExemptionManagerProvider;
    public final Provider providePowerManagerProvider;
    public final Provider provideProgressForwarderProvider;
    public final Provider provideRoleManagerProvider;
    public final Provider provideRotationChangeProvider;
    public final Provider provideSafetyCenterManagerProvider;
    public final Provider provideSensorPrivacyManagerProvider;
    public final Provider provideShellProgressProvider;
    public final Provider provideShortcutManagerProvider;
    public final Provider provideSmartspaceManagerProvider;
    public final Provider provideStatsManagerProvider;
    public final Provider provideStatusBarManagerProvider;
    public final Provider provideStatusBarScopedTransitionProvider;
    public final Provider provideStorageManagerProvider;
    public final Provider provideSubscriptionManagerProvider;
    public final Provider provideTelecomManagerProvider;
    public final Provider provideTelephonyManagerProvider;
    public final Provider provideTextClassificationManagerProvider;
    public final Provider provideTrustManagerProvider;
    public final Provider provideUiBackgroundExecutorProvider;
    public final Provider provideUiEventLoggerProvider;
    public final Provider provideUiModeManagerProvider;
    public final Provider provideUnfoldOnlyProvider;
    public final Provider provideUserManagerProvider;
    public final Provider provideVibratorProvider;
    public final Provider provideViewConfigurationProvider;
    public final Provider provideVirtualDeviceManagerProvider;
    public final Provider provideWifiManagerProvider;
    public final Provider provideWindowManagerProvider;
    public final Provider providerLayoutInflaterProvider;
    public final Provider providesBiometricManagerProvider;
    public final Provider providesChoreographerProvider;
    public final Provider providesFingerprintManagerProvider;
    public final Provider providesFoldStateListenerProvider;
    public final Provider providesFoldStateLoggerProvider;
    public final Provider providesFoldStateLoggingProvider;
    public final Provider providesPluginExecutorProvider;
    public final Provider providesPluginInstanceFactoryProvider;
    public final Provider providesPluginManagerProvider;
    public final Provider providesSensorManagerProvider;
    public final Provider qSExpansionPathInterpolatorProvider;
    public final Provider resourceUnfoldTransitionConfigProvider;
    public final Provider screenLifecycleProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl = this;
    public final Provider uncaughtExceptionPreHandlerManagerProvider;
    public final Provider unfoldBgProgressFlagProvider;
    public final Provider unfoldBgProgressHandlerProvider;
    public final Provider unfoldBgTransitionProgressProvider;
    public final Provider unfoldKeyguardVisibilityManagerImplProvider;
    public final Provider unfoldKeyguardVisibilityProvider;
    public final UnfoldRotationProviderInternalModule unfoldRotationProviderInternalModule;
    public final UnfoldSharedInternalModule unfoldSharedInternalModule;
    public final UnfoldSharedModule unfoldSharedModule;
    public final UnfoldTransitionModule unfoldTransitionModule;
    public final SwitchingProvider unfoldTransitionProgressForwarderProvider;
    public final Provider unfoldTransitionProgressProvider;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SwitchingProvider implements Provider {
        public final int id;
        public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;

        /* renamed from: com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4  reason: invalid class name */
        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class AnonymousClass4 {
        }

        public SwitchingProvider(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, int i) {
            this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
            this.id = i;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v147, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v24, resolved type: android.hardware.face.FaceManager} */
        /* JADX WARNING: type inference failed for: r14v40, types: [com.android.systemui.Dumpable, com.android.systemui.keyguard.ScreenLifecycle, java.lang.Object, com.android.systemui.keyguard.Lifecycle] */
        /* JADX WARNING: type inference failed for: r14v177, types: [com.android.systemui.util.concurrency.ThreadFactory, java.lang.Object] */
        /* JADX WARNING: type inference failed for: r14v290, types: [android.os.Binder, android.os.IInterface, java.lang.Object] */
        /* JADX WARNING: type inference failed for: r1v59, types: [com.android.systemui.util.time.SystemClockImpl, java.lang.Object] */
        /* JADX WARNING: type inference failed for: r14v337, types: [java.lang.Object, com.android.systemui.qs.QSExpansionPathInterpolator] */
        /* JADX WARNING: type inference failed for: r0v166, types: [com.android.systemui.qs.PathInterpolatorBuilder, java.lang.Object] */
        /* JADX WARNING: Code restructure failed: missing block: B:293:?, code lost:
            return r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:301:?, code lost:
            return r0;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object get() {
            /*
                r14 = this;
                int r0 = r14.id
                int r1 = r0 / 100
                r2 = 1
                r3 = 0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r4 = r14.sysUIGoogleGlobalRootComponentImpl
                if (r1 == 0) goto L_0x032a
                if (r1 != r2) goto L_0x0324
                switch(r0) {
                    case 100: goto L_0x0311;
                    case 101: goto L_0x0309;
                    case 102: goto L_0x0301;
                    case 103: goto L_0x02f3;
                    case 104: goto L_0x02e0;
                    case 105: goto L_0x02d2;
                    case 106: goto L_0x02c4;
                    case 107: goto L_0x02b6;
                    case 108: goto L_0x02aa;
                    case 109: goto L_0x0297;
                    case 110: goto L_0x0288;
                    case 111: goto L_0x0279;
                    case 112: goto L_0x026a;
                    case 113: goto L_0x025b;
                    case 114: goto L_0x022e;
                    case 115: goto L_0x0222;
                    case 116: goto L_0x0213;
                    case 117: goto L_0x0207;
                    case 118: goto L_0x0200;
                    case 119: goto L_0x01bd;
                    case 120: goto L_0x01a8;
                    case 121: goto L_0x0195;
                    case 122: goto L_0x017e;
                    case 123: goto L_0x016f;
                    case 124: goto L_0x0160;
                    case 125: goto L_0x0151;
                    case 126: goto L_0x0149;
                    case 127: goto L_0x013d;
                    case 128: goto L_0x012e;
                    case 129: goto L_0x011e;
                    case 130: goto L_0x010f;
                    case 131: goto L_0x0101;
                    case 132: goto L_0x00f2;
                    case 133: goto L_0x0028;
                    case 134: goto L_0x0015;
                    default: goto L_0x000f;
                }
            L_0x000f:
                java.lang.AssertionError r14 = new java.lang.AssertionError
                r14.<init>(r0)
                throw r14
            L_0x0015:
                android.content.Context r14 = r4.context
                java.lang.Class<android.telecom.TelecomManager> r0 = android.telecom.TelecomManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.telecom.TelecomManager r14 = (android.telecom.TelecomManager) r14
                java.util.Optional r14 = java.util.Optional.ofNullable(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x0028:
                com.android.systemui.qs.QSExpansionPathInterpolator r14 = new com.android.systemui.qs.QSExpansionPathInterpolator
                r14.<init>()
                com.android.systemui.qs.PathInterpolatorBuilder r0 = new com.android.systemui.qs.PathInterpolatorBuilder
                r0.<init>()
                android.graphics.Path r1 = new android.graphics.Path
                r1.<init>()
                r11 = 0
                r1.moveTo(r11, r11)
                r7 = 0
                r8 = 1065353216(0x3f800000, float:1.0)
                r5 = 0
                r6 = 0
                r9 = 1065353216(0x3f800000, float:1.0)
                r10 = 1065353216(0x3f800000, float:1.0)
                r4 = r1
                r4.cubicTo(r5, r6, r7, r8, r9, r10)
                r4 = 990057071(0x3b03126f, float:0.002)
                float[] r1 = r1.approximate(r4)
                int r4 = r1.length
                int r4 = r4 / 3
                r5 = r1[r2]
                int r5 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
                if (r5 != 0) goto L_0x00ea
                r5 = 2
                r6 = r1[r5]
                int r6 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                if (r6 != 0) goto L_0x00ea
                int r6 = r1.length
                int r6 = r6 - r5
                r5 = r1[r6]
                r6 = 1065353216(0x3f800000, float:1.0)
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 != 0) goto L_0x00ea
                int r5 = r1.length
                int r5 = r5 - r2
                r5 = r1[r5]
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 != 0) goto L_0x00ea
                float[] r5 = new float[r4]
                r0.mX = r5
                float[] r5 = new float[r4]
                r0.mY = r5
                float[] r5 = new float[r4]
                r0.mDist = r5
                r6 = r3
                r7 = r6
                r5 = r11
            L_0x0080:
                if (r6 >= r4) goto L_0x00d4
                int r8 = r7 + 1
                r9 = r1[r7]
                int r10 = r7 + 2
                r8 = r1[r8]
                int r7 = r7 + 3
                r10 = r1[r10]
                int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                if (r11 != 0) goto L_0x009f
                int r11 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
                if (r11 != 0) goto L_0x0097
                goto L_0x009f
            L_0x0097:
                java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "The Path cannot have discontinuity in the X axis."
                r14.<init>(r0)
                throw r14
            L_0x009f:
                int r5 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
                if (r5 < 0) goto L_0x00cc
                float[] r5 = r0.mX
                r5[r6] = r8
                float[] r11 = r0.mY
                r11[r6] = r10
                if (r6 <= 0) goto L_0x00c7
                r12 = r5[r6]
                int r13 = r6 + -1
                r5 = r5[r13]
                float r12 = r12 - r5
                r5 = r11[r13]
                float r10 = r10 - r5
                float r12 = r12 * r12
                float r10 = r10 * r10
                float r10 = r10 + r12
                double r10 = (double) r10
                double r10 = java.lang.Math.sqrt(r10)
                float r5 = (float) r10
                float[] r10 = r0.mDist
                r11 = r10[r13]
                float r11 = r11 + r5
                r10[r6] = r11
            L_0x00c7:
                int r6 = r6 + 1
                r5 = r8
                r11 = r9
                goto L_0x0080
            L_0x00cc:
                java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "The Path cannot loop back on itself."
                r14.<init>(r0)
                throw r14
            L_0x00d4:
                float[] r1 = r0.mDist
                int r5 = r1.length
                int r5 = r5 - r2
                r1 = r1[r5]
            L_0x00da:
                if (r3 >= r4) goto L_0x00e6
                float[] r2 = r0.mDist
                r5 = r2[r3]
                float r5 = r5 / r1
                r2[r3] = r5
                int r3 = r3 + 1
                goto L_0x00da
            L_0x00e6:
                r14.pathInterpolatorBuilder = r0
                goto L_0x0323
            L_0x00ea:
                java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "The Path must start at (0,0) and end at (1,1)"
                r14.<init>(r0)
                throw r14
            L_0x00f2:
                android.content.Context r14 = r4.context
                java.lang.Class<android.view.inputmethod.InputMethodManager> r0 = android.view.inputmethod.InputMethodManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.view.inputmethod.InputMethodManager r14 = (android.view.inputmethod.InputMethodManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x0101:
                com.android.systemui.dagger.FrameworkServicesModule r14 = r4.frameworkServicesModule
                r14.getClass()
                androidx.asynclayoutinflater.view.AsyncLayoutInflater r14 = new androidx.asynclayoutinflater.view.AsyncLayoutInflater
                android.content.Context r0 = r4.context
                r14.<init>(r0)
                goto L_0x0323
            L_0x010f:
                java.lang.String r14 = "package"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.content.pm.IPackageManager r14 = android.content.pm.IPackageManager.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x011e:
                com.android.systemui.unfold.UnfoldSharedModule r14 = r4.unfoldSharedModule
                javax.inject.Provider r0 = r4.unfoldKeyguardVisibilityManagerImplProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl r0 = (com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl) r0
                r14.getClass()
            L_0x012b:
                r14 = r0
                goto L_0x0323
            L_0x012e:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.StatsManager> r0 = android.app.StatsManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.StatsManager r14 = (android.app.StatsManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x013d:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.ambientcontext.AmbientContextManager> r0 = android.app.ambientcontext.AmbientContextManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.ambientcontext.AmbientContextManager r14 = (android.app.ambientcontext.AmbientContextManager) r14
                goto L_0x0323
            L_0x0149:
                android.content.Context r14 = r4.context
                androidx.core.app.NotificationManagerCompat r0 = new androidx.core.app.NotificationManagerCompat
                r0.<init>(r14)
                goto L_0x012b
            L_0x0151:
                android.content.Context r14 = r4.context
                java.lang.Class<android.os.storage.StorageManager> r0 = android.os.storage.StorageManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.storage.StorageManager r14 = (android.os.storage.StorageManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x0160:
                android.content.Context r14 = r4.context
                java.lang.Class<android.content.ClipboardManager> r0 = android.content.ClipboardManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.content.ClipboardManager r14 = (android.content.ClipboardManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x016f:
                android.content.Context r14 = r4.context
                java.lang.Class<android.view.textclassifier.TextClassificationManager> r0 = android.view.textclassifier.TextClassificationManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.view.textclassifier.TextClassificationManager r14 = (android.view.textclassifier.TextClassificationManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x017e:
                com.android.systemui.unfold.system.DeviceStateRepositoryImpl r14 = new com.android.systemui.unfold.system.DeviceStateRepositoryImpl
                javax.inject.Provider r0 = r4.deviceStateManagerFoldProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.updates.FoldProvider r0 = (com.android.systemui.unfold.updates.FoldProvider) r0
                javax.inject.Provider r1 = r4.provideMainExecutorProvider
                java.lang.Object r1 = r1.get()
                java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
                r14.<init>(r0, r1)
                goto L_0x0323
            L_0x0195:
                com.android.systemui.unfold.UnfoldSharedModule r14 = r4.unfoldSharedModule
                com.android.systemui.unfold.updates.FoldStateRepositoryImpl r0 = new com.android.systemui.unfold.updates.FoldStateRepositoryImpl
                javax.inject.Provider r1 = r4.provideFoldStateProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.unfold.updates.FoldStateProvider r1 = (com.android.systemui.unfold.updates.FoldStateProvider) r1
                r0.<init>(r1)
                r14.getClass()
                goto L_0x012b
            L_0x01a8:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r4.unfoldTransitionModule
                javax.inject.Provider r0 = r4.providesFoldStateLoggingProvider
                java.lang.Object r0 = r0.get()
                java.util.Optional r0 = (java.util.Optional) r0
                r14.getClass()
                com.android.systemui.unfold.UnfoldTransitionModule$providesFoldStateLogger$1 r14 = com.android.systemui.unfold.UnfoldTransitionModule$providesFoldStateLogger$1.INSTANCE
                java.util.Optional r14 = r0.map(r14)
                goto L_0x0323
            L_0x01bd:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r4.unfoldTransitionModule
                javax.inject.Provider r0 = r4.resourceUnfoldTransitionConfigProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r0 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r0
                javax.inject.Provider r1 = r4.provideFoldStateProvider
                dagger.Lazy r1 = dagger.internal.DoubleCheck.lazy(r1)
                r14.getClass()
                kotlin.Lazy r14 = r0.isHingeAngleEnabled$delegate
                java.lang.Object r14 = r14.getValue()
                java.lang.Boolean r14 = (java.lang.Boolean) r14
                boolean r14 = r14.booleanValue()
                if (r14 == 0) goto L_0x01f7
                com.android.systemui.unfold.FoldStateLoggingProviderImpl r14 = new com.android.systemui.unfold.FoldStateLoggingProviderImpl
                java.lang.Object r0 = r1.get()
                com.android.systemui.unfold.updates.FoldStateProvider r0 = (com.android.systemui.unfold.updates.FoldStateProvider) r0
                com.android.systemui.util.time.SystemClockImpl r1 = new com.android.systemui.util.time.SystemClockImpl
                r1.<init>()
                r14.<init>(r0, r1)
                java.util.Optional r14 = java.util.Optional.of(r14)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
                goto L_0x0323
            L_0x01f7:
                java.util.Optional r14 = java.util.Optional.empty()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
                goto L_0x0323
            L_0x0200:
                com.android.dream.lowlight.LowLightTransitionCoordinator r14 = new com.android.dream.lowlight.LowLightTransitionCoordinator
                r14.<init>()
                goto L_0x0323
            L_0x0207:
                java.lang.String r14 = "vrmanager"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.service.vr.IVrManager r14 = android.service.vr.IVrManager.Stub.asInterface(r14)
                goto L_0x0323
            L_0x0213:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.StatusBarManager> r0 = android.app.StatusBarManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.StatusBarManager r14 = (android.app.StatusBarManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x0222:
                com.android.systemui.unfold.progress.UnfoldTransitionProgressForwarder r14 = new com.android.systemui.unfold.progress.UnfoldTransitionProgressForwarder
                r14.<init>()
                java.lang.String r0 = "com.android.systemui.unfold.progress.IUnfoldAnimation"
                r14.attachInterface(r14, r0)
                goto L_0x0323
            L_0x022e:
                com.android.systemui.unfold.UnfoldSharedInternalModule r14 = r4.unfoldSharedInternalModule
                javax.inject.Provider r0 = r4.resourceUnfoldTransitionConfigProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r0 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r1 = r4.unfoldTransitionProgressForwarderProvider
                r14.getClass()
                kotlin.Lazy r14 = r0.isEnabled$delegate
                java.lang.Object r14 = r14.getValue()
                java.lang.Boolean r14 = (java.lang.Boolean) r14
                boolean r14 = r14.booleanValue()
                if (r14 != 0) goto L_0x0251
                java.util.Optional r14 = java.util.Optional.empty()
                goto L_0x0323
            L_0x0251:
                java.lang.Object r14 = r1.get()
                java.util.Optional r14 = java.util.Optional.of(r14)
                goto L_0x0323
            L_0x025b:
                android.content.Context r14 = r4.context
                java.lang.Class<android.content.om.OverlayManager> r0 = android.content.om.OverlayManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.content.om.OverlayManager r14 = (android.content.om.OverlayManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x026a:
                android.content.Context r14 = r4.context
                java.lang.Class<android.hardware.display.ColorDisplayManager> r0 = android.hardware.display.ColorDisplayManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.display.ColorDisplayManager r14 = (android.hardware.display.ColorDisplayManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x0279:
                java.lang.String r14 = "uri_grants"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.app.IUriGrantsManager r14 = android.app.IUriGrantsManager.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x0288:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.UiModeManager> r0 = android.app.UiModeManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.UiModeManager r14 = (android.app.UiModeManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x0297:
                android.content.Context r14 = r4.context
                java.lang.Class<android.os.Vibrator> r0 = android.os.Vibrator.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.Vibrator r14 = (android.os.Vibrator) r14
                java.util.Optional r14 = java.util.Optional.ofNullable(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x02aa:
                com.android.systemui.plugins.PluginDependencyProvider r14 = new com.android.systemui.plugins.PluginDependencyProvider
                javax.inject.Provider r0 = r4.providesPluginManagerProvider
                dagger.Lazy r0 = dagger.internal.DoubleCheck.lazy(r0)
                r14.<init>(r0)
                goto L_0x0323
            L_0x02b6:
                java.lang.String r14 = "audio"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.media.IAudioService r14 = android.media.IAudioService.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x02c4:
                java.lang.String r14 = "media_projection"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.media.projection.IMediaProjectionManager r14 = android.media.projection.IMediaProjectionManager.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x02d2:
                android.content.Context r14 = r4.context
                java.lang.Class<android.safetycenter.SafetyCenterManager> r0 = android.safetycenter.SafetyCenterManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.safetycenter.SafetyCenterManager r14 = (android.safetycenter.SafetyCenterManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x02e0:
                android.content.Context r14 = r4.context
                java.lang.Class<android.permission.PermissionManager> r0 = android.permission.PermissionManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.permission.PermissionManager r14 = (android.permission.PermissionManager) r14
                if (r14 == 0) goto L_0x02ef
                r14.initializeUsageHelper()
            L_0x02ef:
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x02f3:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.job.JobScheduler> r0 = android.app.job.JobScheduler.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.job.JobScheduler r14 = (android.app.job.JobScheduler) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x0301:
                android.view.CrossWindowBlurListeners r14 = android.view.CrossWindowBlurListeners.getInstance()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0323
            L_0x0309:
                com.google.android.systemui.assist.OpaEnabledSettings r14 = new com.google.android.systemui.assist.OpaEnabledSettings
                android.content.Context r0 = r4.context
                r14.<init>(r0)
                goto L_0x0323
            L_0x0311:
                com.android.systemui.dagger.FrameworkServicesModule r14 = r4.frameworkServicesModule
                r14.getClass()
                java.lang.String r14 = "notification"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.app.INotificationManager r14 = android.app.INotificationManager.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
            L_0x0323:
                return r14
            L_0x0324:
                java.lang.AssertionError r14 = new java.lang.AssertionError
                r14.<init>(r0)
                throw r14
            L_0x032a:
                com.android.systemui.unfold.updates.hinge.EmptyHingeAngleProvider r1 = com.android.systemui.unfold.updates.hinge.EmptyHingeAngleProvider.INSTANCE
                r5 = 0
                switch(r0) {
                    case 0: goto L_0x0add;
                    case 1: goto L_0x0ad7;
                    case 2: goto L_0x0ac9;
                    case 3: goto L_0x0a7e;
                    case 4: goto L_0x0a78;
                    case 5: goto L_0x0a67;
                    case 6: goto L_0x0a58;
                    case 7: goto L_0x09cd;
                    case 8: goto L_0x09c6;
                    case 9: goto L_0x09bb;
                    case 10: goto L_0x09b4;
                    case 11: goto L_0x09ad;
                    case 12: goto L_0x09a6;
                    case 13: goto L_0x0997;
                    case 14: goto L_0x092e;
                    case 15: goto L_0x0927;
                    case 16: goto L_0x0918;
                    case 17: goto L_0x08ff;
                    case 18: goto L_0x08f8;
                    case 19: goto L_0x08e9;
                    case 20: goto L_0x08da;
                    case 21: goto L_0x08cb;
                    case 22: goto L_0x08c4;
                    case 23: goto L_0x08b9;
                    case 24: goto L_0x08b2;
                    case 25: goto L_0x08a3;
                    case 26: goto L_0x089a;
                    case 27: goto L_0x086e;
                    case 28: goto L_0x0867;
                    case 29: goto L_0x0858;
                    case 30: goto L_0x0851;
                    case 31: goto L_0x0809;
                    case 32: goto L_0x07a0;
                    case 33: goto L_0x0791;
                    case 34: goto L_0x0780;
                    case 35: goto L_0x0754;
                    case 36: goto L_0x074b;
                    case 37: goto L_0x0723;
                    case 38: goto L_0x0718;
                    case 39: goto L_0x070f;
                    case 40: goto L_0x0700;
                    case 41: goto L_0x06f1;
                    case 42: goto L_0x06e2;
                    case 43: goto L_0x06d3;
                    case 44: goto L_0x06c6;
                    case 45: goto L_0x06bb;
                    case 46: goto L_0x06b1;
                    case 47: goto L_0x06a2;
                    case 48: goto L_0x0692;
                    case 49: goto L_0x0683;
                    case 50: goto L_0x065c;
                    case 51: goto L_0x0655;
                    case 52: goto L_0x0633;
                    case 53: goto L_0x061e;
                    case 54: goto L_0x0613;
                    case 55: goto L_0x060c;
                    case 56: goto L_0x05f3;
                    case 57: goto L_0x05da;
                    case 58: goto L_0x05ca;
                    case 59: goto L_0x05be;
                    case 60: goto L_0x05b0;
                    case 61: goto L_0x05a1;
                    case 62: goto L_0x0592;
                    case 63: goto L_0x0583;
                    case 64: goto L_0x0574;
                    case 65: goto L_0x0565;
                    case 66: goto L_0x0556;
                    case 67: goto L_0x0533;
                    case 68: goto L_0x052a;
                    case 69: goto L_0x0521;
                    case 70: goto L_0x0513;
                    case 71: goto L_0x0507;
                    case 72: goto L_0x04fc;
                    case 73: goto L_0x04ed;
                    case 74: goto L_0x04bc;
                    case 75: goto L_0x0479;
                    case 76: goto L_0x046e;
                    case 77: goto L_0x045f;
                    case 78: goto L_0x044e;
                    case 79: goto L_0x043e;
                    case 80: goto L_0x0437;
                    case 81: goto L_0x042c;
                    case 82: goto L_0x041d;
                    case 83: goto L_0x0411;
                    case 84: goto L_0x0402;
                    case 85: goto L_0x03fe;
                    case 86: goto L_0x03ef;
                    case 87: goto L_0x03e0;
                    case 88: goto L_0x03d1;
                    case 89: goto L_0x03c5;
                    case 90: goto L_0x03b9;
                    case 91: goto L_0x03aa;
                    case 92: goto L_0x039b;
                    case 93: goto L_0x038c;
                    case 94: goto L_0x0380;
                    case 95: goto L_0x0371;
                    case 96: goto L_0x0362;
                    case 97: goto L_0x0353;
                    case 98: goto L_0x0345;
                    case 99: goto L_0x0336;
                    default: goto L_0x0330;
                }
            L_0x0330:
                java.lang.AssertionError r14 = new java.lang.AssertionError
                r14.<init>(r0)
                throw r14
            L_0x0336:
                android.content.Context r14 = r4.context
                java.lang.Class<android.bluetooth.BluetoothManager> r0 = android.bluetooth.BluetoothManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.bluetooth.BluetoothManager r14 = (android.bluetooth.BluetoothManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0345:
                javax.inject.Provider r14 = r4.provideBluetoothManagerProvider
                java.lang.Object r14 = r14.get()
                android.bluetooth.BluetoothManager r14 = (android.bluetooth.BluetoothManager) r14
                android.bluetooth.BluetoothAdapter r14 = r14.getAdapter()
                goto L_0x0ae4
            L_0x0353:
                android.content.Context r14 = r4.context
                java.lang.Class<android.hardware.input.InputManager> r0 = android.hardware.input.InputManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.input.InputManager r14 = (android.hardware.input.InputManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0362:
                android.content.Context r14 = r4.context
                java.lang.Class<android.content.pm.ShortcutManager> r0 = android.content.pm.ShortcutManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.content.pm.ShortcutManager r14 = (android.content.pm.ShortcutManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0371:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.role.RoleManager> r0 = android.app.role.RoleManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.role.RoleManager r14 = (android.app.role.RoleManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0380:
                android.content.Context r14 = r4.context
                java.lang.Class<android.os.Vibrator> r0 = android.os.Vibrator.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.Vibrator r14 = (android.os.Vibrator) r14
                goto L_0x0ae4
            L_0x038c:
                android.content.Context r14 = r4.context
                java.lang.Class<android.hardware.camera2.CameraManager> r0 = android.hardware.camera2.CameraManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.camera2.CameraManager r14 = (android.hardware.camera2.CameraManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x039b:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.KeyguardManager> r0 = android.app.KeyguardManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.KeyguardManager r14 = (android.app.KeyguardManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x03aa:
                android.content.Context r14 = r4.context
                java.lang.Class<android.os.PowerExemptionManager> r0 = android.os.PowerExemptionManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.PowerExemptionManager r14 = (android.os.PowerExemptionManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x03b9:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.smartspace.SmartspaceManager> r0 = android.app.smartspace.SmartspaceManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.smartspace.SmartspaceManager r14 = (android.app.smartspace.SmartspaceManager) r14
                goto L_0x0ae4
            L_0x03c5:
                android.content.Context r14 = r4.context
                java.lang.Class<android.telecom.TelecomManager> r0 = android.telecom.TelecomManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.telecom.TelecomManager r14 = (android.telecom.TelecomManager) r14
                goto L_0x0ae4
            L_0x03d1:
                android.content.Context r14 = r4.context
                java.lang.Class<android.net.NetworkScoreManager> r0 = android.net.NetworkScoreManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.net.NetworkScoreManager r14 = (android.net.NetworkScoreManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x03e0:
                android.content.Context r14 = r4.context
                java.lang.Class<android.companion.virtual.VirtualDeviceManager> r0 = android.companion.virtual.VirtualDeviceManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.companion.virtual.VirtualDeviceManager r14 = (android.companion.virtual.VirtualDeviceManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x03ef:
                android.content.Context r14 = r4.context
                java.lang.Class<android.media.AudioManager> r0 = android.media.AudioManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.media.AudioManager r14 = (android.media.AudioManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x03fe:
                com.android.systemui.shared.system.PackageManagerWrapper r14 = com.android.systemui.shared.system.PackageManagerWrapper.sInstance
                goto L_0x0ae4
            L_0x0402:
                android.content.Context r14 = r4.context
                java.lang.Class<android.telephony.CarrierConfigManager> r0 = android.telephony.CarrierConfigManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.telephony.CarrierConfigManager r14 = (android.telephony.CarrierConfigManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0411:
                android.content.Context r14 = r4.context
                java.lang.Class<android.net.wifi.WifiManager> r0 = android.net.wifi.WifiManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.net.wifi.WifiManager r14 = (android.net.wifi.WifiManager) r14
                goto L_0x0ae4
            L_0x041d:
                android.content.Context r14 = r4.context
                java.lang.Class<android.net.ConnectivityManager> r0 = android.net.ConnectivityManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.net.ConnectivityManager r14 = (android.net.ConnectivityManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x042c:
                android.content.Context r14 = r4.context
                android.view.ViewConfiguration r14 = android.view.ViewConfiguration.get(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0437:
                com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager r14 = new com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager
                r14.<init>()
                goto L_0x0ae4
            L_0x043e:
                android.content.Context r14 = r4.context
                java.util.List r14 = com.android.systemui.plugins.PluginsModule_ProvidesPrivilegedPluginsFactory.providesPrivilegedPlugins(r14)
                boolean r0 = com.android.systemui.plugins.PluginsModule_ProvidesPluginDebugFactory.providesPluginDebug()
                com.android.systemui.shared.plugins.PluginInstance$Factory r14 = com.android.systemui.plugins.PluginsModule_ProvidesPluginInstanceFactoryFactory.providesPluginInstanceFactory(r14, r0)
                goto L_0x0ae4
            L_0x044e:
                com.android.systemui.plugins.PluginEnablerImpl r14 = new com.android.systemui.plugins.PluginEnablerImpl
                android.content.Context r0 = r4.context
                javax.inject.Provider r1 = r4.providePackageManagerProvider
                java.lang.Object r1 = r1.get()
                android.content.pm.PackageManager r1 = (android.content.pm.PackageManager) r1
                r14.<init>(r0, r1)
                goto L_0x0ae4
            L_0x045f:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.NotificationManager> r0 = android.app.NotificationManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.NotificationManager r14 = (android.app.NotificationManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x046e:
                com.android.systemui.util.concurrency.ThreadFactoryImpl r14 = new com.android.systemui.util.concurrency.ThreadFactoryImpl
                r14.<init>()
                java.util.concurrent.Executor r14 = com.android.systemui.plugins.PluginsModule_ProvidesPluginExecutorFactory.providesPluginExecutor(r14)
                goto L_0x0ae4
            L_0x0479:
                android.content.Context r0 = r4.context
                javax.inject.Provider r14 = r4.providePackageManagerProvider
                java.lang.Object r14 = r14.get()
                r1 = r14
                android.content.pm.PackageManager r1 = (android.content.pm.PackageManager) r1
                javax.inject.Provider r14 = r4.provideMainExecutorProvider
                java.lang.Object r14 = r14.get()
                r2 = r14
                java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
                javax.inject.Provider r14 = r4.providesPluginExecutorProvider
                java.lang.Object r14 = r14.get()
                r3 = r14
                java.util.concurrent.Executor r3 = (java.util.concurrent.Executor) r3
                javax.inject.Provider r14 = r4.provideNotificationManagerProvider
                java.lang.Object r14 = r14.get()
                android.app.NotificationManager r14 = (android.app.NotificationManager) r14
                javax.inject.Provider r5 = r4.pluginEnablerImplProvider
                java.lang.Object r5 = r5.get()
                com.android.systemui.shared.plugins.PluginEnabler r5 = (com.android.systemui.shared.plugins.PluginEnabler) r5
                android.content.Context r6 = r4.context
                java.util.List r6 = com.android.systemui.plugins.PluginsModule_ProvidesPrivilegedPluginsFactory.providesPrivilegedPlugins(r6)
                javax.inject.Provider r4 = r4.providesPluginInstanceFactoryProvider
                java.lang.Object r4 = r4.get()
                r7 = r4
                com.android.systemui.shared.plugins.PluginInstance$Factory r7 = (com.android.systemui.shared.plugins.PluginInstance.Factory) r7
                r4 = r14
                com.android.systemui.shared.plugins.PluginActionManager$Factory r14 = com.android.systemui.plugins.PluginsModule_ProvidePluginInstanceManagerFactoryFactory.providePluginInstanceManagerFactory(r0, r1, r2, r3, r4, r5, r6, r7)
                goto L_0x0ae4
            L_0x04bc:
                android.content.Context r0 = r4.context
                javax.inject.Provider r14 = r4.providePluginInstanceManagerFactoryProvider
                java.lang.Object r14 = r14.get()
                r1 = r14
                com.android.systemui.shared.plugins.PluginActionManager$Factory r1 = (com.android.systemui.shared.plugins.PluginActionManager.Factory) r1
                boolean r2 = com.android.systemui.plugins.PluginsModule_ProvidesPluginDebugFactory.providesPluginDebug()
                javax.inject.Provider r14 = r4.uncaughtExceptionPreHandlerManagerProvider
                java.lang.Object r14 = r14.get()
                r3 = r14
                com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager r3 = (com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager) r3
                javax.inject.Provider r14 = r4.pluginEnablerImplProvider
                java.lang.Object r14 = r14.get()
                com.android.systemui.shared.plugins.PluginEnabler r14 = (com.android.systemui.shared.plugins.PluginEnabler) r14
                android.content.Context r4 = r4.context
                com.android.systemui.shared.plugins.PluginPrefs r5 = com.android.systemui.plugins.PluginsModule_ProvidesPluginPrefsFactory.providesPluginPrefs(r4)
                java.util.List r6 = com.android.systemui.plugins.PluginsModule_ProvidesPrivilegedPluginsFactory.providesPrivilegedPlugins(r4)
                r4 = r14
                com.android.systemui.plugins.PluginManager r14 = com.android.systemui.plugins.PluginsModule_ProvidesPluginManagerFactory.providesPluginManager(r0, r1, r2, r3, r4, r5, r6)
                goto L_0x0ae4
            L_0x04ed:
                java.lang.String r14 = "batterystats"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                com.android.internal.app.IBatteryStats r14 = com.android.internal.app.IBatteryStats.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x04fc:
                android.content.Context r14 = r4.context
                android.media.MediaRouter2Manager r14 = android.media.MediaRouter2Manager.getInstance(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0507:
                com.android.systemui.dagger.AndroidInternalsModule r14 = r4.androidInternalsModule
                r14.getClass()
                com.android.internal.logging.MetricsLogger r14 = new com.android.internal.logging.MetricsLogger
                r14.<init>()
                goto L_0x0ae4
            L_0x0513:
                com.android.systemui.dagger.FrameworkServicesModule r14 = r4.frameworkServicesModule
                r14.getClass()
                android.view.Choreographer r14 = android.view.Choreographer.getInstance()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0521:
                android.app.IActivityManager r14 = android.app.ActivityManager.getService()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x052a:
                android.app.IActivityTaskManager r14 = android.app.ActivityTaskManager.getService()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0533:
                android.content.Context r14 = r4.context
                javax.inject.Provider r0 = r4.provideFaceManagerProvider
                java.lang.Object r0 = r0.get()
                android.hardware.face.FaceManager r0 = (android.hardware.face.FaceManager) r0
                javax.inject.Provider r1 = r4.providesFingerprintManagerProvider
                java.lang.Object r1 = r1.get()
                android.hardware.fingerprint.FingerprintManager r1 = (android.hardware.fingerprint.FingerprintManager) r1
                if (r0 != 0) goto L_0x054a
                if (r1 != 0) goto L_0x054a
                goto L_0x0553
            L_0x054a:
                java.lang.Class<android.hardware.biometrics.BiometricManager> r0 = android.hardware.biometrics.BiometricManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                r5 = r14
                android.hardware.biometrics.BiometricManager r5 = (android.hardware.biometrics.BiometricManager) r5
            L_0x0553:
                r14 = r5
                goto L_0x0ae4
            L_0x0556:
                android.content.Context r14 = r4.context
                java.lang.Class<android.hardware.SensorPrivacyManager> r0 = android.hardware.SensorPrivacyManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.SensorPrivacyManager r14 = (android.hardware.SensorPrivacyManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0565:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.admin.DevicePolicyManager> r0 = android.app.admin.DevicePolicyManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.admin.DevicePolicyManager r14 = (android.app.admin.DevicePolicyManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0574:
                java.lang.String r14 = "dreams"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.service.dreams.IDreamManager r14 = android.service.dreams.IDreamManager.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0583:
                android.content.Context r14 = r4.context
                java.lang.Class<android.telephony.SubscriptionManager> r0 = android.telephony.SubscriptionManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.telephony.SubscriptionManager r14 = (android.telephony.SubscriptionManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0592:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.trust.TrustManager> r0 = android.app.trust.TrustManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.trust.TrustManager r14 = (android.app.trust.TrustManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x05a1:
                android.content.Context r14 = r4.context
                java.lang.Class<android.telephony.TelephonyManager> r0 = android.telephony.TelephonyManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.telephony.TelephonyManager r14 = (android.telephony.TelephonyManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x05b0:
                com.android.systemui.dagger.AndroidInternalsModule r14 = r4.androidInternalsModule
                r14.getClass()
                com.android.internal.widget.LockPatternUtils r14 = new com.android.internal.widget.LockPatternUtils
                android.content.Context r0 = r4.context
                r14.<init>(r0)
                goto L_0x0ae4
            L_0x05be:
                android.os.Looper r14 = com.android.systemui.util.concurrency.GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper()
                com.android.systemui.util.concurrency.ExecutorImpl r0 = new com.android.systemui.util.concurrency.ExecutorImpl
                r0.<init>(r14)
            L_0x05c7:
                r14 = r0
                goto L_0x0ae4
            L_0x05ca:
                com.android.systemui.dagger.FrameworkServicesModule r14 = r4.frameworkServicesModule
                r14.getClass()
                android.content.Context r14 = r4.context
                android.view.LayoutInflater r14 = android.view.LayoutInflater.from(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x05da:
                android.content.Context r14 = r4.context
                android.content.pm.PackageManager r0 = r14.getPackageManager()
                java.lang.String r1 = "android.hardware.biometrics.face"
                boolean r0 = r0.hasSystemFeature(r1)
                if (r0 == 0) goto L_0x0553
                java.lang.Class<android.hardware.face.FaceManager> r0 = android.hardware.face.FaceManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                r5 = r14
                android.hardware.face.FaceManager r5 = (android.hardware.face.FaceManager) r5
                goto L_0x0553
            L_0x05f3:
                android.content.Context r14 = r4.context
                android.content.pm.PackageManager r0 = r14.getPackageManager()
                java.lang.String r1 = "android.hardware.fingerprint"
                boolean r0 = r0.hasSystemFeature(r1)
                if (r0 == 0) goto L_0x0553
                java.lang.Class<android.hardware.fingerprint.FingerprintManager> r0 = android.hardware.fingerprint.FingerprintManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                r5 = r14
                android.hardware.fingerprint.FingerprintManager r5 = (android.hardware.fingerprint.FingerprintManager) r5
                goto L_0x0553
            L_0x060c:
                com.android.systemui.util.concurrency.ExecutionImpl r14 = new com.android.systemui.util.concurrency.ExecutionImpl
                r14.<init>()
                goto L_0x0ae4
            L_0x0613:
                android.content.Context r14 = r4.context
                com.android.internal.util.LatencyTracker r14 = com.android.internal.util.LatencyTracker.getInstance(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x061e:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r4.unfoldTransitionModule
                javax.inject.Provider r0 = r4.provideNaturalRotationProgressProvider
                java.lang.Object r0 = r0.get()
                java.util.Optional r0 = (java.util.Optional) r0
                r14.getClass()
                com.android.systemui.unfold.UnfoldTransitionModule$providesFoldStateLogger$1 r14 = com.android.systemui.unfold.UnfoldTransitionModule$providesFoldStateLogger$1.INSTANCE$1
                java.util.Optional r14 = r0.map(r14)
                goto L_0x0ae4
            L_0x0633:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r4.unfoldTransitionModule
                javax.inject.Provider r0 = r4.provideRotationChangeProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.updates.RotationChangeProvider r0 = (com.android.systemui.unfold.updates.RotationChangeProvider) r0
                javax.inject.Provider r1 = r4.unfoldTransitionProgressProvider
                java.lang.Object r1 = r1.get()
                java.util.Optional r1 = (java.util.Optional) r1
                r14.getClass()
                com.android.systemui.unfold.UnfoldTransitionModule$provideUnfoldOnlyProvider$1 r14 = new com.android.systemui.unfold.UnfoldTransitionModule$provideUnfoldOnlyProvider$1
                android.content.Context r3 = r4.context
                r14.<init>(r2, r3, r0)
                java.util.Optional r14 = r1.map(r14)
                goto L_0x0ae4
            L_0x0655:
                com.android.systemui.util.wrapper.BuildInfo r14 = new com.android.systemui.util.wrapper.BuildInfo
                r14.<init>()
                goto L_0x0ae4
            L_0x065c:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r4.unfoldTransitionModule
                javax.inject.Provider r0 = r4.provideDeviceStateManagerProvider
                java.lang.Object r0 = r0.get()
                android.hardware.devicestate.DeviceStateManager r0 = (android.hardware.devicestate.DeviceStateManager) r0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r1 = r4.provideApplicationContextProvider
                java.lang.Object r1 = r1.get()
                android.content.Context r1 = (android.content.Context) r1
                javax.inject.Provider r2 = r4.provideMainExecutorProvider
                java.lang.Object r2 = r2.get()
                java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
                r14.getClass()
                android.hardware.devicestate.DeviceStateManager$FoldStateListener r14 = new android.hardware.devicestate.DeviceStateManager$FoldStateListener
                r14.<init>(r1)
                r0.registerCallback(r2, r14)
                goto L_0x0ae4
            L_0x0683:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.AlarmManager> r0 = android.app.AlarmManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.AlarmManager r14 = (android.app.AlarmManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0692:
                com.android.systemui.dagger.GlobalModule r14 = r4.globalModule
                r14.getClass()
                android.content.Context r14 = r4.context
                android.content.Context r14 = r14.getApplicationContext()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x06a2:
                android.content.Context r14 = r4.context
                java.lang.Class<android.os.PowerManager> r0 = android.os.PowerManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.PowerManager r14 = (android.os.PowerManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x06b1:
                boolean r14 = android.app.ActivityManager.isRunningInUserTestHarness()
                java.lang.Boolean r14 = java.lang.Boolean.valueOf(r14)
                goto L_0x0ae4
            L_0x06bb:
                android.content.Context r14 = r4.context
                android.content.pm.PackageManager r14 = r14.getPackageManager()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x06c6:
                com.android.internal.jank.InteractionJankMonitor r14 = com.android.internal.jank.InteractionJankMonitor.getInstance()
                r0 = -256(0xffffffffffffff00, float:NaN)
                r1 = 4604930618986332160(0x3fe8000000000000, double:0.75)
                r14.configDebugOverlay(r0, r1)
                goto L_0x0ae4
            L_0x06d3:
                android.content.Context r14 = r4.context
                java.lang.Class<android.content.pm.LauncherApps> r0 = android.content.pm.LauncherApps.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.content.pm.LauncherApps r14 = (android.content.pm.LauncherApps) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x06e2:
                android.content.Context r14 = r4.context
                java.lang.Class<android.os.UserManager> r0 = android.os.UserManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.UserManager r14 = (android.os.UserManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x06f1:
                java.lang.String r14 = "statusbar"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                com.android.internal.statusbar.IStatusBarService r14 = com.android.internal.statusbar.IStatusBarService.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0700:
                android.content.Context r14 = r4.context
                java.lang.Class<android.view.WindowManager> r0 = android.view.WindowManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.view.WindowManager r14 = (android.view.WindowManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x070f:
                android.app.ActivityTaskManager r14 = android.app.ActivityTaskManager.getInstance()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0718:
                android.content.Context r14 = r4.context
                java.util.concurrent.Executor r14 = r14.getMainExecutor()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0723:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r4.unfoldTransitionModule
                javax.inject.Provider r0 = r4.deviceStateManagerFoldProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.updates.FoldProvider r0 = (com.android.systemui.unfold.updates.FoldProvider) r0
                javax.inject.Provider r1 = r4.provideMainExecutorProvider
                java.lang.Object r1 = r1.get()
                java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
                javax.inject.Provider r2 = r4.unfoldTransitionProgressProvider
                java.lang.Object r2 = r2.get()
                java.util.Optional r2 = (java.util.Optional) r2
                r14.getClass()
                com.android.systemui.unfold.UnfoldTransitionModule$provideUnfoldOnlyProvider$1 r14 = new com.android.systemui.unfold.UnfoldTransitionModule$provideUnfoldOnlyProvider$1
                r14.<init>(r3, r0, r1)
                java.util.Optional r14 = r2.map(r14)
                goto L_0x0ae4
            L_0x074b:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r4.unfoldTransitionModule
                r14.getClass()
                java.lang.Boolean r14 = java.lang.Boolean.FALSE
                goto L_0x0ae4
            L_0x0754:
                com.android.systemui.unfold.UnfoldRotationProviderInternalModule r14 = r4.unfoldRotationProviderInternalModule
                javax.inject.Provider r0 = r4.factoryProvider7
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass7) r0
                javax.inject.Provider r1 = r4.unfoldBgProgressHandlerProvider
                java.lang.Object r1 = r1.get()
                android.os.Handler r1 = (android.os.Handler) r1
                r14.getClass()
                com.android.systemui.unfold.updates.RotationChangeProvider r14 = new com.android.systemui.unfold.updates.RotationChangeProvider
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r0 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.this
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r0.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r2 = r2.provideDisplayManagerProvider
                java.lang.Object r2 = r2.get()
                android.hardware.display.DisplayManager r2 = (android.hardware.display.DisplayManager) r2
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r0 = r0.sysUIGoogleGlobalRootComponentImpl
                android.content.Context r0 = r0.context
                r14.<init>(r2, r0, r1)
                goto L_0x0ae4
            L_0x0780:
                android.os.HandlerThread r14 = new android.os.HandlerThread
                java.lang.String r0 = "UnfoldBg"
                r1 = -2
                r14.<init>(r0, r1)
                r14.start()
                android.os.Looper r14 = r14.getLooper()
                goto L_0x0ae4
            L_0x0791:
                javax.inject.Provider r14 = r4.provideBgLooperProvider
                java.lang.Object r14 = r14.get()
                android.os.Looper r14 = (android.os.Looper) r14
                android.os.Handler r0 = new android.os.Handler
                r0.<init>(r14)
                goto L_0x05c7
            L_0x07a0:
                com.android.systemui.unfold.FoldStateProviderModule r14 = r4.foldStateProviderModule
                javax.inject.Provider r0 = r4.factoryProvider5
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass5) r0
                javax.inject.Provider r2 = r4.resourceUnfoldTransitionConfigProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r2 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r2
                javax.inject.Provider r3 = r4.unfoldBgProgressHandlerProvider
                java.lang.Object r3 = r3.get()
                android.os.Handler r3 = (android.os.Handler) r3
                javax.inject.Provider r5 = r4.factoryProvider6
                java.lang.Object r5 = r5.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6 r5 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass6) r5
                com.android.systemui.unfold.HingeAngleProviderInternalModule r6 = r4.hingeAngleProviderInternalModule
                r6.getClass()
                kotlin.Lazy r2 = r2.isHingeAngleEnabled$delegate
                java.lang.Object r2 = r2.getValue()
                java.lang.Boolean r2 = (java.lang.Boolean) r2
                boolean r2 = r2.booleanValue()
                if (r2 == 0) goto L_0x07f0
                com.android.systemui.unfold.updates.hinge.HingeSensorAngleProvider r1 = new com.android.systemui.unfold.updates.hinge.HingeSensorAngleProvider
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r2 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.this
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r5 = r2.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r5 = r5.providesSensorManagerProvider
                java.lang.Object r5 = r5.get()
                android.hardware.SensorManager r5 = (android.hardware.SensorManager) r5
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r2.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r2 = r2.provideUiBackgroundExecutorProvider
                java.lang.Object r2 = r2.get()
                java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
                r1.<init>(r5, r2, r3)
            L_0x07f0:
                javax.inject.Provider r2 = r4.provideBgRotationChangeProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.updates.RotationChangeProvider r2 = (com.android.systemui.unfold.updates.RotationChangeProvider) r2
                javax.inject.Provider r3 = r4.unfoldBgProgressHandlerProvider
                java.lang.Object r3 = r3.get()
                android.os.Handler r3 = (android.os.Handler) r3
                r14.getClass()
                com.android.systemui.unfold.updates.DeviceFoldStateProvider r14 = r0.create(r1, r2, r3)
                goto L_0x0ae4
            L_0x0809:
                com.android.systemui.unfold.UnfoldSharedInternalModule r14 = r4.unfoldSharedInternalModule
                javax.inject.Provider r0 = r4.resourceUnfoldTransitionConfigProvider
                java.lang.Object r0 = r0.get()
                r5 = r0
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r5 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r5
                javax.inject.Provider r0 = r4.factoryProvider
                java.lang.Object r0 = r0.get()
                r6 = r0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1 r6 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass1) r6
                javax.inject.Provider r0 = r4.factoryProvider2
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass2) r0
                javax.inject.Provider r1 = r4.factoryProvider4
                java.lang.Object r1 = r1.get()
                r8 = r1
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3 r8 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass3) r8
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r9 = r4.fixedTimingTransitionProgressProvider
                javax.inject.Provider r1 = r4.provideBgFoldStateProvider
                java.lang.Object r1 = r1.get()
                r10 = r1
                com.android.systemui.unfold.updates.FoldStateProvider r10 = (com.android.systemui.unfold.updates.FoldStateProvider) r10
                javax.inject.Provider r1 = r4.unfoldBgProgressHandlerProvider
                java.lang.Object r1 = r1.get()
                r11 = r1
                android.os.Handler r11 = (android.os.Handler) r11
                r14.getClass()
                java.lang.String r14 = "BgThread"
                com.android.systemui.unfold.util.ATraceLoggerTransitionProgressListener r7 = r0.create(r14)
                java.util.Optional r14 = com.android.systemui.unfold.UnfoldSharedInternalModule.createOptionalUnfoldTransitionProgressProvider(r5, r6, r7, r8, r9, r10, r11)
                goto L_0x0ae4
            L_0x0851:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8
                r0.<init>()
                goto L_0x05c7
            L_0x0858:
                android.content.Context r14 = r4.context
                java.lang.Class<android.hardware.display.DisplayManager> r0 = android.hardware.display.DisplayManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.display.DisplayManager r14 = (android.hardware.display.DisplayManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0867:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7
                r0.<init>()
                goto L_0x05c7
            L_0x086e:
                com.android.systemui.unfold.UnfoldRotationProviderInternalModule r14 = r4.unfoldRotationProviderInternalModule
                javax.inject.Provider r0 = r4.factoryProvider7
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass7) r0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r1 = r4.provideMainHandlerProvider
                java.lang.Object r1 = r1.get()
                android.os.Handler r1 = (android.os.Handler) r1
                r14.getClass()
                com.android.systemui.unfold.updates.RotationChangeProvider r14 = new com.android.systemui.unfold.updates.RotationChangeProvider
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r0 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.this
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r0.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r2 = r2.provideDisplayManagerProvider
                java.lang.Object r2 = r2.get()
                android.hardware.display.DisplayManager r2 = (android.hardware.display.DisplayManager) r2
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r0 = r0.sysUIGoogleGlobalRootComponentImpl
                android.content.Context r0 = r0.context
                r14.<init>(r2, r0, r1)
                goto L_0x0ae4
            L_0x089a:
                java.util.concurrent.ExecutorService r14 = java.util.concurrent.Executors.newSingleThreadExecutor()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x08a3:
                android.content.Context r14 = r4.context
                java.lang.Class<android.hardware.SensorManager> r0 = android.hardware.SensorManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.SensorManager r14 = (android.hardware.SensorManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x08b2:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6
                r0.<init>()
                goto L_0x05c7
            L_0x08b9:
                android.os.Looper r14 = com.android.systemui.util.concurrency.GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper()
                android.os.Handler r0 = new android.os.Handler
                r0.<init>(r14)
                goto L_0x05c7
            L_0x08c4:
                com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl r14 = new com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl
                r14.<init>()
                goto L_0x0ae4
            L_0x08cb:
                com.android.systemui.unfold.UnfoldSharedModule r14 = r4.unfoldSharedModule
                javax.inject.Provider r0 = r4.unfoldKeyguardVisibilityManagerImplProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl r0 = (com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl) r0
                r14.getClass()
                goto L_0x05c7
            L_0x08da:
                android.content.Context r14 = r4.context
                java.lang.Class<android.app.ActivityManager> r0 = android.app.ActivityManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.ActivityManager r14 = (android.app.ActivityManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x08e9:
                com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider r14 = new com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider
                javax.inject.Provider r0 = r4.provideActivityManagerProvider
                java.lang.Object r0 = r0.get()
                android.app.ActivityManager r0 = (android.app.ActivityManager) r0
                r14.<init>(r0)
                goto L_0x0ae4
            L_0x08f8:
                com.android.systemui.dump.DumpManager r14 = new com.android.systemui.dump.DumpManager
                r14.<init>()
                goto L_0x0ae4
            L_0x08ff:
                com.android.systemui.keyguard.ScreenLifecycle r14 = new com.android.systemui.keyguard.ScreenLifecycle
                javax.inject.Provider r0 = r4.dumpManagerProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.dump.DumpManager r0 = (com.android.systemui.dump.DumpManager) r0
                r14.<init>()
                r14.mScreenState = r3
                r0.getClass()
                java.lang.String r1 = "ScreenLifecycle"
                r0.registerDumpable(r14, com.android.systemui.dump.DumpPriority.CRITICAL, r1)
                goto L_0x0ae4
            L_0x0918:
                com.android.systemui.keyguard.LifecycleScreenStatusProvider r14 = new com.android.systemui.keyguard.LifecycleScreenStatusProvider
                javax.inject.Provider r0 = r4.screenLifecycleProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.keyguard.ScreenLifecycle r0 = (com.android.systemui.keyguard.ScreenLifecycle) r0
                r14.<init>(r0)
                goto L_0x0ae4
            L_0x0927:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5
                r0.<init>()
                goto L_0x05c7
            L_0x092e:
                com.android.systemui.unfold.FoldStateProviderModule r14 = r4.foldStateProviderModule
                javax.inject.Provider r0 = r4.factoryProvider5
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass5) r0
                javax.inject.Provider r2 = r4.resourceUnfoldTransitionConfigProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r2 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r2
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r3 = r4.provideMainHandlerProvider
                java.lang.Object r3 = r3.get()
                android.os.Handler r3 = (android.os.Handler) r3
                javax.inject.Provider r5 = r4.factoryProvider6
                java.lang.Object r5 = r5.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6 r5 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass6) r5
                com.android.systemui.unfold.HingeAngleProviderInternalModule r6 = r4.hingeAngleProviderInternalModule
                r6.getClass()
                kotlin.Lazy r2 = r2.isHingeAngleEnabled$delegate
                java.lang.Object r2 = r2.getValue()
                java.lang.Boolean r2 = (java.lang.Boolean) r2
                boolean r2 = r2.booleanValue()
                if (r2 == 0) goto L_0x097e
                com.android.systemui.unfold.updates.hinge.HingeSensorAngleProvider r1 = new com.android.systemui.unfold.updates.hinge.HingeSensorAngleProvider
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r2 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.this
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r5 = r2.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r5 = r5.providesSensorManagerProvider
                java.lang.Object r5 = r5.get()
                android.hardware.SensorManager r5 = (android.hardware.SensorManager) r5
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r2.sysUIGoogleGlobalRootComponentImpl
                javax.inject.Provider r2 = r2.provideUiBackgroundExecutorProvider
                java.lang.Object r2 = r2.get()
                java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
                r1.<init>(r5, r2, r3)
            L_0x097e:
                javax.inject.Provider r2 = r4.provideRotationChangeProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.updates.RotationChangeProvider r2 = (com.android.systemui.unfold.updates.RotationChangeProvider) r2
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r3 = r4.provideMainHandlerProvider
                java.lang.Object r3 = r3.get()
                android.os.Handler r3 = (android.os.Handler) r3
                r14.getClass()
                com.android.systemui.unfold.updates.DeviceFoldStateProvider r14 = r0.create(r1, r2, r3)
                goto L_0x0ae4
            L_0x0997:
                com.android.systemui.unfold.progress.FixedTimingTransitionProgressProvider r14 = new com.android.systemui.unfold.progress.FixedTimingTransitionProgressProvider
                javax.inject.Provider r0 = r4.provideFoldStateProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.updates.FoldStateProvider r0 = (com.android.systemui.unfold.updates.FoldStateProvider) r0
                r14.<init>(r0)
                goto L_0x0ae4
            L_0x09a6:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4 r14 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4
                r14.<init>()
                goto L_0x0ae4
            L_0x09ad:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3
                r0.<init>()
                goto L_0x05c7
            L_0x09b4:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2
                r0.<init>()
                goto L_0x05c7
            L_0x09bb:
                android.content.Context r14 = r4.context
                android.content.ContentResolver r14 = r14.getContentResolver()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x09c6:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1
                r0.<init>()
                goto L_0x05c7
            L_0x09cd:
                com.android.systemui.unfold.UnfoldSharedInternalModule r14 = r4.unfoldSharedInternalModule
                javax.inject.Provider r0 = r4.resourceUnfoldTransitionConfigProvider
                java.lang.Object r0 = r0.get()
                r5 = r0
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r5 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r5
                javax.inject.Provider r0 = r4.factoryProvider
                java.lang.Object r0 = r0.get()
                r6 = r0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1 r6 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass1) r6
                javax.inject.Provider r0 = r4.factoryProvider2
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass2) r0
                javax.inject.Provider r1 = r4.factoryProvider4
                java.lang.Object r1 = r1.get()
                r8 = r1
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3 r8 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass3) r8
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r9 = r4.fixedTimingTransitionProgressProvider
                javax.inject.Provider r1 = r4.provideFoldStateProvider
                java.lang.Object r1 = r1.get()
                r10 = r1
                com.android.systemui.unfold.updates.FoldStateProvider r10 = (com.android.systemui.unfold.updates.FoldStateProvider) r10
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider r1 = r4.provideMainHandlerProvider
                java.lang.Object r1 = r1.get()
                r11 = r1
                android.os.Handler r11 = (android.os.Handler) r11
                javax.inject.Provider r1 = r4.factoryProvider8
                java.lang.Object r1 = r1.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8 r1 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.AnonymousClass8) r1
                javax.inject.Provider r2 = r4.unfoldBgTransitionProgressProvider
                javax.inject.Provider r3 = r4.unfoldBgProgressFlagProvider
                java.lang.Object r3 = r3.get()
                java.lang.Boolean r3 = (java.lang.Boolean) r3
                java.util.Optional r3 = java.util.Optional.of(r3)
                r14.getClass()
                java.lang.Boolean r14 = java.lang.Boolean.FALSE
                boolean r4 = r3.isPresent()
                if (r4 == 0) goto L_0x0a2b
                java.lang.Object r14 = r3.get()
            L_0x0a2b:
                java.lang.Boolean r14 = (java.lang.Boolean) r14
                boolean r14 = r14.booleanValue()
                if (r14 == 0) goto L_0x0a4c
                java.lang.Object r14 = r2.get()
                java.util.Optional r14 = (java.util.Optional) r14
                com.android.systemui.unfold.UnfoldSharedInternalModule$unfoldTransitionProgressProvider$mainThreadProvider$1 r2 = new com.android.systemui.unfold.UnfoldSharedInternalModule$unfoldTransitionProgressProvider$mainThreadProvider$1
                r2.<init>(r1)
                java.util.Optional r14 = r14.map(r2)
                com.android.systemui.unfold.UnfoldSharedInternalModule$unfoldTransitionProgressProvider$1 r1 = new com.android.systemui.unfold.UnfoldSharedInternalModule$unfoldTransitionProgressProvider$1
                r1.<init>(r0)
                r14.ifPresent(r1)
                goto L_0x0ae4
            L_0x0a4c:
                java.lang.String r14 = "MainThread"
                com.android.systemui.unfold.util.ATraceLoggerTransitionProgressListener r7 = r0.create(r14)
                java.util.Optional r14 = com.android.systemui.unfold.UnfoldSharedInternalModule.createOptionalUnfoldTransitionProgressProvider(r5, r6, r7, r8, r9, r10, r11)
                goto L_0x0ae4
            L_0x0a58:
                android.content.Context r14 = r4.context
                java.lang.Class<android.hardware.devicestate.DeviceStateManager> r0 = android.hardware.devicestate.DeviceStateManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.devicestate.DeviceStateManager r14 = (android.hardware.devicestate.DeviceStateManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0a67:
                com.android.systemui.unfold.system.DeviceStateManagerFoldProvider r14 = new com.android.systemui.unfold.system.DeviceStateManagerFoldProvider
                javax.inject.Provider r0 = r4.provideDeviceStateManagerProvider
                java.lang.Object r0 = r0.get()
                android.hardware.devicestate.DeviceStateManager r0 = (android.hardware.devicestate.DeviceStateManager) r0
                android.content.Context r1 = r4.context
                r14.<init>(r0, r1)
                goto L_0x0ae4
            L_0x0a78:
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r14 = new com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig
                r14.<init>()
                goto L_0x0ae4
            L_0x0a7e:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r4.unfoldTransitionModule
                javax.inject.Provider r0 = r4.resourceUnfoldTransitionConfigProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r0 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r0
                javax.inject.Provider r1 = r4.deviceStateManagerFoldProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.unfold.updates.FoldProvider r1 = (com.android.systemui.unfold.updates.FoldProvider) r1
                javax.inject.Provider r2 = r4.unfoldTransitionProgressProvider
                javax.inject.Provider r3 = r4.provideUnfoldOnlyProvider
                r14.getClass()
                kotlin.Lazy r14 = r0.isEnabled$delegate
                java.lang.Object r14 = r14.getValue()
                java.lang.Boolean r14 = (java.lang.Boolean) r14
                boolean r14 = r14.booleanValue()
                if (r14 == 0) goto L_0x0aac
                boolean r14 = com.android.systemui.unfold.UnfoldTransitionModuleKt.ENABLE_FOLD_TASK_ANIMATIONS
                if (r14 == 0) goto L_0x0aaa
                goto L_0x0aad
            L_0x0aaa:
                r2 = r3
                goto L_0x0aad
            L_0x0aac:
                r2 = r5
            L_0x0aad:
                if (r2 == 0) goto L_0x0ac6
                java.lang.Object r14 = r2.get()
                java.util.Optional r14 = (java.util.Optional) r14
                if (r14 == 0) goto L_0x0ac6
                java.lang.Object r14 = r14.orElse(r5)
                com.android.systemui.unfold.UnfoldTransitionProgressProvider r14 = (com.android.systemui.unfold.UnfoldTransitionProgressProvider) r14
                if (r14 == 0) goto L_0x0ac6
                com.android.systemui.unfold.UnfoldProgressProvider r0 = new com.android.systemui.unfold.UnfoldProgressProvider
                r0.<init>(r14, r1)
                goto L_0x05c7
            L_0x0ac6:
                com.android.wm.shell.unfold.ShellUnfoldProgressProvider$1 r14 = com.android.wm.shell.unfold.ShellUnfoldProgressProvider.NO_PROVIDER
                goto L_0x0ae4
            L_0x0ac9:
                android.content.Context r14 = r4.context
                java.lang.Class<android.view.accessibility.AccessibilityManager> r0 = android.view.accessibility.AccessibilityManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.view.accessibility.AccessibilityManager r14 = (android.view.accessibility.AccessibilityManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0ae4
            L_0x0ad7:
                com.android.internal.logging.UiEventLoggerImpl r14 = new com.android.internal.logging.UiEventLoggerImpl
                r14.<init>()
                goto L_0x0ae4
            L_0x0add:
                android.view.IWindowManager r14 = android.view.WindowManagerGlobal.getWindowManagerService()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
            L_0x0ae4:
                return r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.SwitchingProvider.get():java.lang.Object");
        }
    }

    /* renamed from: -$$Nest$mambientDisplayConfiguration  reason: not valid java name */
    public static AmbientDisplayConfiguration m882$$Nest$mambientDisplayConfiguration(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.frameworkServicesModule.getClass();
        return new AmbientDisplayConfiguration(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
    }

    /* renamed from: -$$Nest$mapplicationAssetManager  reason: not valid java name */
    public static AssetManager m883$$Nest$mapplicationAssetManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        AssetManager assets = ((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get()).getAssets();
        Preconditions.checkNotNullFromProvides(assets);
        return assets;
    }

    /* renamed from: -$$Nest$mdisplayIdInteger  reason: not valid java name */
    public static int m884$$Nest$mdisplayIdInteger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        return daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getDisplayId();
    }

    /* renamed from: -$$Nest$mdisplayMetrics  reason: not valid java name */
    public static DisplayMetrics m885$$Nest$mdisplayMetrics(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.globalModule.getClass();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /* renamed from: -$$Nest$mmainResources  reason: not valid java name */
    public static Resources m886$$Nest$mmainResources(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        return resources;
    }

    /* renamed from: -$$Nest$mmediaProjectionManager  reason: not valid java name */
    public static MediaProjectionManager m887$$Nest$mmediaProjectionManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.getClass();
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(MediaProjectionManager.class);
        Preconditions.checkNotNullFromProvides(mediaProjectionManager);
        return mediaProjectionManager;
    }

    /* renamed from: -$$Nest$munfoldTransitionInteractorImpl  reason: not valid java name */
    public static UnfoldTransitionInteractorImpl m888$$Nest$munfoldTransitionInteractorImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        return new UnfoldTransitionInteractorImpl(new UnfoldTransitionRepositoryImpl((Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionProgressProvider.get()));
    }

    /* renamed from: -$$Nest$mwallpaperManager  reason: not valid java name */
    public static WallpaperManager m889$$Nest$mwallpaperManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.getClass();
        WallpaperManager wallpaperManager = (WallpaperManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(WallpaperManager.class);
        Preconditions.checkNotNullFromProvides(wallpaperManager);
        return wallpaperManager;
    }

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl(GlobalModule globalModule2, AndroidInternalsModule androidInternalsModule2, FrameworkServicesModule frameworkServicesModule2, UnfoldTransitionModule unfoldTransitionModule2, UnfoldSharedModule unfoldSharedModule2, UnfoldSharedInternalModule unfoldSharedInternalModule2, UnfoldRotationProviderInternalModule unfoldRotationProviderInternalModule2, HingeAngleProviderInternalModule hingeAngleProviderInternalModule2, FoldStateProviderModule foldStateProviderModule2, Context context2, Boolean bool) {
        this.instrumentationTest = bool;
        this.context = context2;
        this.unfoldTransitionModule = unfoldTransitionModule2;
        this.unfoldSharedInternalModule = unfoldSharedInternalModule2;
        this.foldStateProviderModule = foldStateProviderModule2;
        this.unfoldSharedModule = unfoldSharedModule2;
        this.hingeAngleProviderInternalModule = hingeAngleProviderInternalModule2;
        this.unfoldRotationProviderInternalModule = unfoldRotationProviderInternalModule2;
        this.globalModule = globalModule2;
        this.frameworkServicesModule = frameworkServicesModule2;
        this.androidInternalsModule = androidInternalsModule2;
        this.provideIWindowManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 0);
        this.provideUiEventLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 1);
        this.provideAccessibilityManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 2);
        this.resourceUnfoldTransitionConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 4);
        this.provideDeviceStateManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 6);
        this.deviceStateManagerFoldProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 5);
        this.provideContentResolverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 9);
        this.factoryProvider = SingleCheck.provider(new SwitchingProvider(this, 8));
        this.factoryProvider2 = SingleCheck.provider(new SwitchingProvider(this, 10));
        this.factoryProvider3 = SingleCheck.provider(new SwitchingProvider(this, 12));
        this.factoryProvider4 = SingleCheck.provider(new SwitchingProvider(this, 11));
        this.dumpManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 18);
        this.screenLifecycleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 17);
        this.lifecycleScreenStatusProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 16);
        this.provideActivityManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 20);
        this.activityManagerActivityTypeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 19);
        this.unfoldKeyguardVisibilityManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 22);
        this.unfoldKeyguardVisibilityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 21);
        this.factoryProvider5 = SingleCheck.provider(new SwitchingProvider(this, 15));
        this.provideMainHandlerProvider = new SwitchingProvider(this, 23);
        this.providesSensorManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 25);
        this.provideUiBackgroundExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 26);
        this.factoryProvider6 = SingleCheck.provider(new SwitchingProvider(this, 24));
        this.provideDisplayManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 29);
        this.factoryProvider7 = SingleCheck.provider(new SwitchingProvider(this, 28));
        this.provideRotationChangeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 27);
        this.provideFoldStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 14);
        this.fixedTimingTransitionProgressProvider = new SwitchingProvider(this, 13);
        this.factoryProvider8 = SingleCheck.provider(new SwitchingProvider(this, 30));
        this.provideBgLooperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 34);
        this.unfoldBgProgressHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 33);
        this.provideBgRotationChangeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 35);
        this.provideBgFoldStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 32);
        this.unfoldBgTransitionProgressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 31);
        this.unfoldBgProgressFlagProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 36);
        this.unfoldTransitionProgressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 7);
        this.provideMainExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 38);
        this.provideUnfoldOnlyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 37);
        this.provideShellProgressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 3);
        this.provideActivityTaskManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 39);
        this.provideWindowManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 40);
        this.provideIStatusBarServiceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 41);
        this.provideUserManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 42);
        this.provideLauncherAppsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 43);
        this.provideInteractionJankMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 44);
        this.providePackageManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 45);
        this.provideIsTestHarnessProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 46);
        this.providePowerManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 47);
        this.provideApplicationContextProvider = new SwitchingProvider(this, 48);
        this.provideAlarmManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 49);
        this.providesFoldStateListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 50);
        this.buildInfoProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 51);
        this.provideNaturalRotationProgressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 52);
        this.provideStatusBarScopedTransitionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 53);
        this.provideLatencyTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 54);
        this.provideExecutionProvider = DoubleCheck.provider(new SwitchingProvider(this, 55));
        this.providesFingerprintManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 56);
        this.provideFaceManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 57);
        this.providerLayoutInflaterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 58);
        this.provideMainDelayableExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 59);
        this.provideLockPatternUtilsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 60);
        this.provideTelephonyManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 61);
        this.provideTrustManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 62);
        this.provideSubscriptionManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 63);
        this.provideIDreamManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 64);
        this.provideDevicePolicyManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 65);
        this.provideSensorPrivacyManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 66);
        this.providesBiometricManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 67);
        this.provideIActivityTaskManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 68);
        this.provideIActivityManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 69);
        this.providesChoreographerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 70);
        this.provideMetricsLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 71);
        this.provideMediaRouter2ManagerProvider = new SwitchingProvider(this, 72);
        this.provideIBatteryStatsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 73);
        this.providesPluginExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 76);
        this.provideNotificationManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 77);
        this.pluginEnablerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 78);
        this.providesPluginInstanceFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 79);
        this.providePluginInstanceManagerFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 75);
        this.uncaughtExceptionPreHandlerManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 80);
        this.providesPluginManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 74);
        this.provideViewConfigurationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 81);
        this.provideConnectivityManagagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 82);
        this.provideWifiManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 83);
        this.provideCarrierConfigManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 84);
        this.providePackageManagerWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 85);
        this.provideAudioManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 86);
        this.provideVirtualDeviceManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 87);
        this.provideNetworkScoreManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 88);
        this.provideTelecomManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 89);
        this.provideSmartspaceManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 90);
        this.providePowerExemptionManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 91);
        this.provideKeyguardManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 92);
        this.provideCameraManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 93);
        this.provideVibratorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 94);
        this.provideRoleManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 95);
        this.provideShortcutManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 96);
        this.provideInputManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 97);
        this.provideBluetoothManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 99);
        this.provideBluetoothAdapterProvider = DoubleCheck.provider(new SwitchingProvider(this, 98));
        this.provideINotificationManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 100));
        this.opaEnabledSettingsProvider = DoubleCheck.provider(new SwitchingProvider(this, 101));
        this.provideCrossWindowBlurListenersProvider = DoubleCheck.provider(new SwitchingProvider(this, 102));
        this.provideJobSchedulerProvider = DoubleCheck.provider(new SwitchingProvider(this, 103));
        this.providePermissionManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 104));
        this.provideSafetyCenterManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 105));
        this.provideIMediaProjectionManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 106));
        this.provideIAudioServiceProvider = DoubleCheck.provider(new SwitchingProvider(this, 107));
        this.pluginDependencyProvider = DoubleCheck.provider(new SwitchingProvider(this, 108));
        this.provideOptionalVibratorProvider = DoubleCheck.provider(new SwitchingProvider(this, 109));
        this.provideUiModeManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 110));
        this.provideIUriGrantsManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 111));
        this.provideColorDisplayManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 112));
        this.provideOverlayManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 113));
        this.unfoldTransitionProgressForwarderProvider = new SwitchingProvider(this, 115);
        this.provideProgressForwarderProvider = DoubleCheck.provider(new SwitchingProvider(this, 114));
        this.provideStatusBarManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 116));
        this.provideIVrManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 117));
        this.lowLightTransitionCoordinatorProvider = DoubleCheck.provider(new SwitchingProvider(this, 118));
        this.providesFoldStateLoggingProvider = DoubleCheck.provider(new SwitchingProvider(this, 119));
        this.providesFoldStateLoggerProvider = DoubleCheck.provider(new SwitchingProvider(this, 120));
        this.foldStateRepositoryProvider = DoubleCheck.provider(new SwitchingProvider(this, 121));
        this.deviceStateRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider(this, 122));
        this.provideTextClassificationManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 123));
        this.provideClipboardManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 124));
        this.provideStorageManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 125));
        this.provideNotificationManagerCompatProvider = DoubleCheck.provider(new SwitchingProvider(this, 126));
        this.provideAmbientContextManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 127));
        this.provideStatsManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 128));
        DoubleCheck.provider(new SwitchingProvider(this, 129));
        this.provideIPackageManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 130));
        this.provideAsyncLayoutInflaterProvider = DoubleCheck.provider(new SwitchingProvider(this, 131));
        this.provideInputMethodManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 132));
        this.qSExpansionPathInterpolatorProvider = DoubleCheck.provider(new SwitchingProvider(this, 133));
        this.provideOptionalTelecomManagerProvider = DoubleCheck.provider(new SwitchingProvider(this, 134));
    }
}
