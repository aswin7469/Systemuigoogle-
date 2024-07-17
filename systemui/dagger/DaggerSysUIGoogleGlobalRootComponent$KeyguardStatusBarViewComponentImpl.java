package com.google.android.systemui.dagger;

import android.content.ContentResolver;
import android.os.Handler;
import android.os.UserManager;
import com.android.keyguard.CarrierText;
import com.android.keyguard.CarrierTextController;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.battery.BatteryMeterView;
import com.android.systemui.battery.BatteryMeterViewController;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerImpl;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.KeyguardStatusBarView;
import com.android.systemui.statusbar.phone.KeyguardStatusBarViewController;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.ui.viewmodel.KeyguardStatusBarViewModel;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.user.ui.viewmodel.StatusBarUserChipViewModel;
import com.android.systemui.util.settings.SecureSettings;
import dagger.internal.DoubleCheck;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl {
    public final Provider getBatteryMeterViewProvider = DoubleCheck.provider(new SwitchingProvider(1, 0, this));
    public final Provider getCarrierTextProvider = DoubleCheck.provider(new SwitchingProvider(0, 0, this));
    public final Provider getStatusBarLocationProvider = DoubleCheck.provider(new SwitchingProvider(2, 0, this));
    public final NotificationPanelViewController.AnonymousClass10 shadeViewStateProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;
    public final KeyguardStatusBarView view;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class SwitchingProvider implements Provider {
        public final /* synthetic */ int $r8$classId;
        public final int id;
        public final Object keyguardStatusBarViewComponentImpl;

        public /* synthetic */ SwitchingProvider(int i, int i2, Object obj) {
            this.$r8$classId = i2;
            this.keyguardStatusBarViewComponentImpl = obj;
            this.id = i;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v126, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: android.hardware.face.FaceManager} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v128, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: android.hardware.face.FaceManager} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v162, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: android.hardware.face.FaceManager} */
        /* JADX WARNING: type inference failed for: r14v47, types: [com.android.systemui.Dumpable, com.android.systemui.keyguard.ScreenLifecycle, java.lang.Object, com.android.systemui.keyguard.Lifecycle] */
        /* JADX WARNING: type inference failed for: r14v178, types: [com.android.systemui.util.concurrency.ThreadFactory, java.lang.Object] */
        /* JADX WARNING: type inference failed for: r14v316, types: [android.os.Binder, android.os.IInterface, java.lang.Object] */
        /* JADX WARNING: type inference failed for: r1v60, types: [com.android.systemui.util.time.SystemClockImpl, java.lang.Object] */
        /* JADX WARNING: type inference failed for: r0v180, types: [java.lang.Object, com.android.systemui.qs.QSExpansionPathInterpolator] */
        /* JADX WARNING: type inference failed for: r1v64, types: [com.android.systemui.qs.PathInterpolatorBuilder, java.lang.Object] */
        /* JADX WARNING: Code restructure failed: missing block: B:317:?, code lost:
            return r4;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object get() {
            /*
                r14 = this;
                int r0 = r14.$r8$classId
                switch(r0) {
                    case 0: goto L_0x0b79;
                    default: goto L_0x0005;
                }
            L_0x0005:
                int r0 = r14.id
                int r1 = r0 / 100
                r2 = 0
                java.lang.Object r3 = r14.keyguardStatusBarViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r3 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) r3
                if (r1 == 0) goto L_0x0398
                r14 = 1
                if (r1 != r14) goto L_0x0392
                switch(r0) {
                    case 100: goto L_0x0383;
                    case 101: goto L_0x0377;
                    case 102: goto L_0x0368;
                    case 103: goto L_0x0359;
                    case 104: goto L_0x034a;
                    case 105: goto L_0x033c;
                    case 106: goto L_0x032d;
                    case 107: goto L_0x0319;
                    case 108: goto L_0x0310;
                    case 109: goto L_0x0301;
                    case 110: goto L_0x02f8;
                    case 111: goto L_0x02e4;
                    case 112: goto L_0x02d5;
                    case 113: goto L_0x02c6;
                    case 114: goto L_0x02b7;
                    case 115: goto L_0x02aa;
                    case 116: goto L_0x0297;
                    case 117: goto L_0x0288;
                    case 118: goto L_0x0279;
                    case 119: goto L_0x026a;
                    case 120: goto L_0x025b;
                    case 121: goto L_0x022e;
                    case 122: goto L_0x0222;
                    case 123: goto L_0x0213;
                    case 124: goto L_0x0207;
                    case 125: goto L_0x0200;
                    case 126: goto L_0x01f1;
                    case 127: goto L_0x01e2;
                    case 128: goto L_0x01d6;
                    case 129: goto L_0x01c7;
                    case 130: goto L_0x01be;
                    case 131: goto L_0x01b2;
                    case 132: goto L_0x01a3;
                    case 133: goto L_0x0160;
                    case 134: goto L_0x014b;
                    case 135: goto L_0x0134;
                    case 136: goto L_0x0126;
                    case 137: goto L_0x005b;
                    case 138: goto L_0x004c;
                    case 139: goto L_0x0039;
                    case 140: goto L_0x002b;
                    case 141: goto L_0x001c;
                    default: goto L_0x0016;
                }
            L_0x0016:
                java.lang.AssertionError r14 = new java.lang.AssertionError
                r14.<init>(r0)
                throw r14
            L_0x001c:
                android.content.Context r14 = r3.context
                java.lang.Class<android.view.accessibility.CaptioningManager> r0 = android.view.accessibility.CaptioningManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.view.accessibility.CaptioningManager r14 = (android.view.accessibility.CaptioningManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x002b:
                com.android.systemui.dagger.FrameworkServicesModule r14 = r3.frameworkServicesModule
                r14.getClass()
                androidx.asynclayoutinflater.view.AsyncLayoutInflater r14 = new androidx.asynclayoutinflater.view.AsyncLayoutInflater
                android.content.Context r0 = r3.context
                r14.<init>(r0)
                goto L_0x0b78
            L_0x0039:
                android.content.Context r14 = r3.context
                java.lang.Class<android.telecom.TelecomManager> r0 = android.telecom.TelecomManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.telecom.TelecomManager r14 = (android.telecom.TelecomManager) r14
                java.util.Optional r14 = java.util.Optional.ofNullable(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x004c:
                java.lang.String r14 = "package"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.content.pm.IPackageManager r14 = android.content.pm.IPackageManager.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x005b:
                com.android.systemui.qs.QSExpansionPathInterpolator r0 = new com.android.systemui.qs.QSExpansionPathInterpolator
                r0.<init>()
                com.android.systemui.qs.PathInterpolatorBuilder r1 = new com.android.systemui.qs.PathInterpolatorBuilder
                r1.<init>()
                android.graphics.Path r10 = new android.graphics.Path
                r10.<init>()
                r11 = 0
                r10.moveTo(r11, r11)
                r6 = 0
                r7 = 1065353216(0x3f800000, float:1.0)
                r4 = 0
                r5 = 0
                r8 = 1065353216(0x3f800000, float:1.0)
                r9 = 1065353216(0x3f800000, float:1.0)
                r3 = r10
                r3.cubicTo(r4, r5, r6, r7, r8, r9)
                r3 = 990057071(0x3b03126f, float:0.002)
                float[] r3 = r10.approximate(r3)
                int r4 = r3.length
                int r4 = r4 / 3
                r5 = r3[r14]
                int r5 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
                if (r5 != 0) goto L_0x011e
                r5 = 2
                r6 = r3[r5]
                int r6 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                if (r6 != 0) goto L_0x011e
                int r6 = r3.length
                int r6 = r6 - r5
                r5 = r3[r6]
                r6 = 1065353216(0x3f800000, float:1.0)
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 != 0) goto L_0x011e
                int r5 = r3.length
                int r5 = r5 - r14
                r5 = r3[r5]
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 != 0) goto L_0x011e
                float[] r5 = new float[r4]
                r1.mX = r5
                float[] r5 = new float[r4]
                r1.mY = r5
                float[] r5 = new float[r4]
                r1.mDist = r5
                r6 = r2
                r7 = r6
                r5 = r11
            L_0x00b3:
                if (r6 >= r4) goto L_0x0107
                int r8 = r7 + 1
                r9 = r3[r7]
                int r10 = r7 + 2
                r8 = r3[r8]
                int r7 = r7 + 3
                r10 = r3[r10]
                int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                if (r11 != 0) goto L_0x00d2
                int r11 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
                if (r11 != 0) goto L_0x00ca
                goto L_0x00d2
            L_0x00ca:
                java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "The Path cannot have discontinuity in the X axis."
                r14.<init>(r0)
                throw r14
            L_0x00d2:
                int r5 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
                if (r5 < 0) goto L_0x00ff
                float[] r5 = r1.mX
                r5[r6] = r8
                float[] r11 = r1.mY
                r11[r6] = r10
                if (r6 <= 0) goto L_0x00fa
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
                float[] r10 = r1.mDist
                r11 = r10[r13]
                float r11 = r11 + r5
                r10[r6] = r11
            L_0x00fa:
                int r6 = r6 + 1
                r5 = r8
                r11 = r9
                goto L_0x00b3
            L_0x00ff:
                java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "The Path cannot loop back on itself."
                r14.<init>(r0)
                throw r14
            L_0x0107:
                float[] r3 = r1.mDist
                int r5 = r3.length
                int r5 = r5 - r14
                r14 = r3[r5]
            L_0x010d:
                if (r2 >= r4) goto L_0x0119
                float[] r3 = r1.mDist
                r5 = r3[r2]
                float r5 = r5 / r14
                r3[r2] = r5
                int r2 = r2 + 1
                goto L_0x010d
            L_0x0119:
                r0.pathInterpolatorBuilder = r1
            L_0x011b:
                r14 = r0
                goto L_0x0b78
            L_0x011e:
                java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "The Path must start at (0,0) and end at (1,1)"
                r14.<init>(r0)
                throw r14
            L_0x0126:
                com.android.systemui.unfold.UnfoldSharedModule r14 = r3.unfoldSharedModule
                javax.inject.Provider r0 = r3.unfoldKeyguardVisibilityManagerImplProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl r0 = (com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl) r0
                r14.getClass()
                goto L_0x011b
            L_0x0134:
                com.android.systemui.unfold.system.DeviceStateRepositoryImpl r14 = new com.android.systemui.unfold.system.DeviceStateRepositoryImpl
                javax.inject.Provider r0 = r3.deviceStateManagerFoldProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.updates.FoldProvider r0 = (com.android.systemui.unfold.updates.FoldProvider) r0
                javax.inject.Provider r1 = r3.provideMainExecutorProvider
                java.lang.Object r1 = r1.get()
                java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
                r14.<init>(r0, r1)
                goto L_0x0b78
            L_0x014b:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r3.unfoldTransitionModule
                javax.inject.Provider r0 = r3.providesFoldStateLoggingProvider
                java.lang.Object r0 = r0.get()
                java.util.Optional r0 = (java.util.Optional) r0
                r14.getClass()
                com.android.systemui.unfold.UnfoldTransitionModule$providesFoldStateLogger$1 r14 = com.android.systemui.unfold.UnfoldTransitionModule$providesFoldStateLogger$1.INSTANCE
                java.util.Optional r14 = r0.map(r14)
                goto L_0x0b78
            L_0x0160:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r3.unfoldTransitionModule
                javax.inject.Provider r0 = r3.resourceUnfoldTransitionConfigProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r0 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r0
                javax.inject.Provider r1 = r3.provideFoldStateProvider
                dagger.Lazy r1 = dagger.internal.DoubleCheck.lazy(r1)
                r14.getClass()
                kotlin.Lazy r14 = r0.isHingeAngleEnabled$delegate
                java.lang.Object r14 = r14.getValue()
                java.lang.Boolean r14 = (java.lang.Boolean) r14
                boolean r14 = r14.booleanValue()
                if (r14 == 0) goto L_0x019a
                com.android.systemui.unfold.FoldStateLoggingProviderImpl r14 = new com.android.systemui.unfold.FoldStateLoggingProviderImpl
                java.lang.Object r0 = r1.get()
                com.android.systemui.unfold.updates.DeviceFoldStateProvider r0 = (com.android.systemui.unfold.updates.DeviceFoldStateProvider) r0
                com.android.systemui.util.time.SystemClockImpl r1 = new com.android.systemui.util.time.SystemClockImpl
                r1.<init>()
                r14.<init>(r0, r1)
                java.util.Optional r14 = java.util.Optional.of(r14)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
                goto L_0x0b78
            L_0x019a:
                java.util.Optional r14 = java.util.Optional.empty()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
                goto L_0x0b78
            L_0x01a3:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.StatsManager> r0 = android.app.StatsManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.StatsManager r14 = (android.app.StatsManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x01b2:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.ambientcontext.AmbientContextManager> r0 = android.app.ambientcontext.AmbientContextManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.ambientcontext.AmbientContextManager r14 = (android.app.ambientcontext.AmbientContextManager) r14
                goto L_0x0b78
            L_0x01be:
                android.content.Context r14 = r3.context
                androidx.core.app.NotificationManagerCompat r0 = new androidx.core.app.NotificationManagerCompat
                r0.<init>(r14)
                goto L_0x011b
            L_0x01c7:
                android.content.Context r14 = r3.context
                java.lang.Class<android.os.storage.StorageManager> r0 = android.os.storage.StorageManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.storage.StorageManager r14 = (android.os.storage.StorageManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x01d6:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r3.unfoldTransitionModule
                r14.getClass()
                com.android.systemui.unfold.DisplaySwitchLatencyLogger r14 = new com.android.systemui.unfold.DisplaySwitchLatencyLogger
                r14.<init>()
                goto L_0x0b78
            L_0x01e2:
                android.content.Context r14 = r3.context
                java.lang.Class<android.content.ClipboardManager> r0 = android.content.ClipboardManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.content.ClipboardManager r14 = (android.content.ClipboardManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x01f1:
                android.content.Context r14 = r3.context
                java.lang.Class<android.view.textclassifier.TextClassificationManager> r0 = android.view.textclassifier.TextClassificationManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.view.textclassifier.TextClassificationManager r14 = (android.view.textclassifier.TextClassificationManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0200:
                com.android.dream.lowlight.LowLightTransitionCoordinator r14 = new com.android.dream.lowlight.LowLightTransitionCoordinator
                r14.<init>()
                goto L_0x0b78
            L_0x0207:
                java.lang.String r14 = "vrmanager"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.service.vr.IVrManager r14 = android.service.vr.IVrManager.Stub.asInterface(r14)
                goto L_0x0b78
            L_0x0213:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.StatusBarManager> r0 = android.app.StatusBarManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.StatusBarManager r14 = (android.app.StatusBarManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0222:
                com.android.systemui.unfold.progress.UnfoldTransitionProgressForwarder r14 = new com.android.systemui.unfold.progress.UnfoldTransitionProgressForwarder
                r14.<init>()
                java.lang.String r0 = "com.android.systemui.unfold.progress.IUnfoldAnimation"
                r14.attachInterface(r14, r0)
                goto L_0x0b78
            L_0x022e:
                com.android.systemui.unfold.UnfoldSharedInternalModule r14 = r3.unfoldSharedInternalModule
                javax.inject.Provider r0 = r3.resourceUnfoldTransitionConfigProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r0 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r1 = r3.unfoldTransitionProgressForwarderProvider
                r14.getClass()
                kotlin.Lazy r14 = r0.isEnabled$delegate
                java.lang.Object r14 = r14.getValue()
                java.lang.Boolean r14 = (java.lang.Boolean) r14
                boolean r14 = r14.booleanValue()
                if (r14 != 0) goto L_0x0251
                java.util.Optional r14 = java.util.Optional.empty()
                goto L_0x0b78
            L_0x0251:
                java.lang.Object r14 = r1.get()
                java.util.Optional r14 = java.util.Optional.of(r14)
                goto L_0x0b78
            L_0x025b:
                android.content.Context r14 = r3.context
                java.lang.Class<android.content.om.OverlayManager> r0 = android.content.om.OverlayManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.content.om.OverlayManager r14 = (android.content.om.OverlayManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x026a:
                android.content.Context r14 = r3.context
                java.lang.Class<android.hardware.display.ColorDisplayManager> r0 = android.hardware.display.ColorDisplayManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.display.ColorDisplayManager r14 = (android.hardware.display.ColorDisplayManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0279:
                java.lang.String r14 = "uri_grants"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.app.IUriGrantsManager r14 = android.app.IUriGrantsManager.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0288:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.UiModeManager> r0 = android.app.UiModeManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.UiModeManager r14 = (android.app.UiModeManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0297:
                android.content.Context r14 = r3.context
                java.lang.Class<android.os.Vibrator> r0 = android.os.Vibrator.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.Vibrator r14 = (android.os.Vibrator) r14
                java.util.Optional r14 = java.util.Optional.ofNullable(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x02aa:
                com.android.systemui.plugins.PluginDependencyProvider r14 = new com.android.systemui.plugins.PluginDependencyProvider
                javax.inject.Provider r0 = r3.providesPluginManagerProvider
                dagger.Lazy r0 = dagger.internal.DoubleCheck.lazy(r0)
                r14.<init>(r0)
                goto L_0x0b78
            L_0x02b7:
                java.lang.String r14 = "audio"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.media.IAudioService r14 = android.media.IAudioService.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x02c6:
                java.lang.String r14 = "media_projection"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.media.projection.IMediaProjectionManager r14 = android.media.projection.IMediaProjectionManager.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x02d5:
                android.content.Context r14 = r3.context
                java.lang.Class<android.safetycenter.SafetyCenterManager> r0 = android.safetycenter.SafetyCenterManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.safetycenter.SafetyCenterManager r14 = (android.safetycenter.SafetyCenterManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x02e4:
                android.content.Context r14 = r3.context
                java.lang.Class<android.permission.PermissionManager> r0 = android.permission.PermissionManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.permission.PermissionManager r14 = (android.permission.PermissionManager) r14
                if (r14 == 0) goto L_0x02f3
                r14.initializeUsageHelper()
            L_0x02f3:
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x02f8:
                com.google.android.systemui.assist.OpaEnabledSettings r14 = new com.google.android.systemui.assist.OpaEnabledSettings
                android.content.Context r0 = r3.context
                r14.<init>(r0)
                goto L_0x0b78
            L_0x0301:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.job.JobScheduler> r0 = android.app.job.JobScheduler.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.job.JobScheduler r14 = (android.app.job.JobScheduler) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0310:
                android.view.CrossWindowBlurListeners r14 = android.view.CrossWindowBlurListeners.getInstance()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0319:
                com.android.systemui.dagger.FrameworkServicesModule r14 = r3.frameworkServicesModule
                r14.getClass()
                java.lang.String r14 = "notification"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.app.INotificationManager r14 = android.app.INotificationManager.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x032d:
                android.content.Context r14 = r3.context
                java.lang.Class<android.bluetooth.BluetoothManager> r0 = android.bluetooth.BluetoothManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.bluetooth.BluetoothManager r14 = (android.bluetooth.BluetoothManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x033c:
                javax.inject.Provider r14 = r3.provideBluetoothManagerProvider
                java.lang.Object r14 = r14.get()
                android.bluetooth.BluetoothManager r14 = (android.bluetooth.BluetoothManager) r14
                android.bluetooth.BluetoothAdapter r14 = r14.getAdapter()
                goto L_0x0b78
            L_0x034a:
                android.content.Context r14 = r3.context
                java.lang.Class<android.hardware.input.InputManager> r0 = android.hardware.input.InputManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.input.InputManager r14 = (android.hardware.input.InputManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0359:
                android.content.Context r14 = r3.context
                java.lang.Class<android.content.pm.ShortcutManager> r0 = android.content.pm.ShortcutManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.content.pm.ShortcutManager r14 = (android.content.pm.ShortcutManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0368:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.role.RoleManager> r0 = android.app.role.RoleManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.role.RoleManager r14 = (android.app.role.RoleManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0377:
                android.content.Context r14 = r3.context
                java.lang.Class<android.os.Vibrator> r0 = android.os.Vibrator.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.Vibrator r14 = (android.os.Vibrator) r14
                goto L_0x0b78
            L_0x0383:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.KeyguardManager> r0 = android.app.KeyguardManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.KeyguardManager r14 = (android.app.KeyguardManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0392:
                java.lang.AssertionError r14 = new java.lang.AssertionError
                r14.<init>(r0)
                throw r14
            L_0x0398:
                com.android.systemui.unfold.updates.hinge.EmptyHingeAngleProvider r1 = com.android.systemui.unfold.updates.hinge.EmptyHingeAngleProvider.INSTANCE
                r4 = 0
                switch(r0) {
                    case 0: goto L_0x0b73;
                    case 1: goto L_0x0b6b;
                    case 2: goto L_0x0b5d;
                    case 3: goto L_0x0b11;
                    case 4: goto L_0x0b0b;
                    case 5: goto L_0x0afa;
                    case 6: goto L_0x0aeb;
                    case 7: goto L_0x0a60;
                    case 8: goto L_0x0a59;
                    case 9: goto L_0x0a4e;
                    case 10: goto L_0x0a47;
                    case 11: goto L_0x0a40;
                    case 12: goto L_0x0a39;
                    case 13: goto L_0x0a2a;
                    case 14: goto L_0x09bd;
                    case 15: goto L_0x09b6;
                    case 16: goto L_0x09a7;
                    case 17: goto L_0x098e;
                    case 18: goto L_0x0987;
                    case 19: goto L_0x0978;
                    case 20: goto L_0x0969;
                    case 21: goto L_0x095a;
                    case 22: goto L_0x0953;
                    case 23: goto L_0x0948;
                    case 24: goto L_0x0941;
                    case 25: goto L_0x0932;
                    case 26: goto L_0x0929;
                    case 27: goto L_0x08f9;
                    case 28: goto L_0x08f2;
                    case 29: goto L_0x08e3;
                    case 30: goto L_0x08dc;
                    case 31: goto L_0x0894;
                    case 32: goto L_0x0827;
                    case 33: goto L_0x0818;
                    case 34: goto L_0x0807;
                    case 35: goto L_0x07d7;
                    case 36: goto L_0x07ce;
                    case 37: goto L_0x07a5;
                    case 38: goto L_0x079a;
                    case 39: goto L_0x0793;
                    case 40: goto L_0x078a;
                    case 41: goto L_0x077b;
                    case 42: goto L_0x076c;
                    case 43: goto L_0x075d;
                    case 44: goto L_0x074e;
                    case 45: goto L_0x0741;
                    case 46: goto L_0x0736;
                    case 47: goto L_0x0726;
                    case 48: goto L_0x071c;
                    case 49: goto L_0x070d;
                    case 50: goto L_0x06fa;
                    case 51: goto L_0x06db;
                    case 52: goto L_0x06cf;
                    case 53: goto L_0x06c0;
                    case 54: goto L_0x0699;
                    case 55: goto L_0x0692;
                    case 56: goto L_0x068b;
                    case 57: goto L_0x0672;
                    case 58: goto L_0x0659;
                    case 59: goto L_0x0649;
                    case 60: goto L_0x063e;
                    case 61: goto L_0x0630;
                    case 62: goto L_0x0621;
                    case 63: goto L_0x0616;
                    case 64: goto L_0x0606;
                    case 65: goto L_0x05f7;
                    case 66: goto L_0x05e4;
                    case 67: goto L_0x05d5;
                    case 68: goto L_0x05c6;
                    case 69: goto L_0x05b7;
                    case 70: goto L_0x0594;
                    case 71: goto L_0x058b;
                    case 72: goto L_0x055b;
                    case 73: goto L_0x0519;
                    case 74: goto L_0x050e;
                    case 75: goto L_0x04ff;
                    case 76: goto L_0x04ee;
                    case 77: goto L_0x04de;
                    case 78: goto L_0x04d7;
                    case 79: goto L_0x04cb;
                    case 80: goto L_0x04c0;
                    case 81: goto L_0x04b7;
                    case 82: goto L_0x04a9;
                    case 83: goto L_0x049a;
                    case 84: goto L_0x048e;
                    case 85: goto L_0x047f;
                    case 86: goto L_0x0470;
                    case 87: goto L_0x045d;
                    case 88: goto L_0x044e;
                    case 89: goto L_0x044a;
                    case 90: goto L_0x043f;
                    case 91: goto L_0x0433;
                    case 92: goto L_0x0424;
                    case 93: goto L_0x0415;
                    case 94: goto L_0x03f2;
                    case 95: goto L_0x03dd;
                    case 96: goto L_0x03ce;
                    case 97: goto L_0x03bf;
                    case 98: goto L_0x03b3;
                    case 99: goto L_0x03a4;
                    default: goto L_0x039e;
                }
            L_0x039e:
                java.lang.AssertionError r14 = new java.lang.AssertionError
                r14.<init>(r0)
                throw r14
            L_0x03a4:
                android.content.Context r14 = r3.context
                java.lang.Class<android.os.PowerExemptionManager> r0 = android.os.PowerExemptionManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.PowerExemptionManager r14 = (android.os.PowerExemptionManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x03b3:
                android.content.Context r14 = r3.context
                java.lang.Class<android.telecom.TelecomManager> r0 = android.telecom.TelecomManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.telecom.TelecomManager r14 = (android.telecom.TelecomManager) r14
                goto L_0x0b78
            L_0x03bf:
                android.content.Context r14 = r3.context
                java.lang.Class<android.view.inputmethod.InputMethodManager> r0 = android.view.inputmethod.InputMethodManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.view.inputmethod.InputMethodManager r14 = (android.view.inputmethod.InputMethodManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x03ce:
                android.content.Context r14 = r3.context
                java.lang.Class<android.net.NetworkScoreManager> r0 = android.net.NetworkScoreManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.net.NetworkScoreManager r14 = (android.net.NetworkScoreManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x03dd:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r3.unfoldTransitionModule
                javax.inject.Provider r0 = r3.provideNaturalRotationProgressProvider
                java.lang.Object r0 = r0.get()
                java.util.Optional r0 = (java.util.Optional) r0
                r14.getClass()
                com.android.systemui.unfold.UnfoldTransitionModule$providesFoldStateLogger$1 r14 = com.android.systemui.unfold.UnfoldTransitionModule$providesFoldStateLogger$1.INSTANCE$1
                java.util.Optional r14 = r0.map(r14)
                goto L_0x0b78
            L_0x03f2:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r3.unfoldTransitionModule
                javax.inject.Provider r0 = r3.provideRotationChangeProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.updates.RotationChangeProvider r0 = (com.android.systemui.unfold.updates.RotationChangeProvider) r0
                javax.inject.Provider r1 = r3.unfoldTransitionProgressProvider
                java.lang.Object r1 = r1.get()
                java.util.Optional r1 = (java.util.Optional) r1
                r14.getClass()
                com.android.systemui.unfold.UnfoldTransitionModule$provideUnfoldOnlyProvider$1 r14 = new com.android.systemui.unfold.UnfoldTransitionModule$provideUnfoldOnlyProvider$1
                android.content.Context r2 = r3.context
                r3 = 1
                r14.<init>(r3, r2, r0)
                java.util.Optional r14 = r1.map(r14)
                goto L_0x0b78
            L_0x0415:
                android.content.Context r14 = r3.context
                java.lang.Class<android.companion.virtual.VirtualDeviceManager> r0 = android.companion.virtual.VirtualDeviceManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.companion.virtual.VirtualDeviceManager r14 = (android.companion.virtual.VirtualDeviceManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0424:
                android.content.Context r14 = r3.context
                java.lang.Class<android.media.AudioManager> r0 = android.media.AudioManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.media.AudioManager r14 = (android.media.AudioManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0433:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.smartspace.SmartspaceManager> r0 = android.app.smartspace.SmartspaceManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.smartspace.SmartspaceManager r14 = (android.app.smartspace.SmartspaceManager) r14
                goto L_0x0b78
            L_0x043f:
                android.content.Context r14 = r3.context
                android.media.MediaRouter2Manager r14 = android.media.MediaRouter2Manager.getInstance(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x044a:
                com.android.systemui.shared.system.PackageManagerWrapper r14 = com.android.systemui.shared.system.PackageManagerWrapper.sInstance
                goto L_0x0b78
            L_0x044e:
                java.lang.String r14 = "batterystats"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                com.android.internal.app.IBatteryStats r14 = com.android.internal.app.IBatteryStats.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x045d:
                android.content.Context r14 = r3.context
                java.lang.Class<android.telephony.satellite.SatelliteManager> r0 = android.telephony.satellite.SatelliteManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.telephony.satellite.SatelliteManager r14 = (android.telephony.satellite.SatelliteManager) r14
                java.util.Optional r14 = java.util.Optional.ofNullable(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0470:
                android.content.Context r14 = r3.context
                java.lang.Class<android.hardware.camera2.CameraManager> r0 = android.hardware.camera2.CameraManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.camera2.CameraManager r14 = (android.hardware.camera2.CameraManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x047f:
                android.content.Context r14 = r3.context
                java.lang.Class<android.telephony.CarrierConfigManager> r0 = android.telephony.CarrierConfigManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.telephony.CarrierConfigManager r14 = (android.telephony.CarrierConfigManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x048e:
                android.content.Context r14 = r3.context
                java.lang.Class<android.net.wifi.WifiManager> r0 = android.net.wifi.WifiManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.net.wifi.WifiManager r14 = (android.net.wifi.WifiManager) r14
                goto L_0x0b78
            L_0x049a:
                android.content.Context r14 = r3.context
                java.lang.Class<android.net.ConnectivityManager> r0 = android.net.ConnectivityManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.net.ConnectivityManager r14 = (android.net.ConnectivityManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x04a9:
                com.android.systemui.dagger.FrameworkServicesModule r14 = r3.frameworkServicesModule
                r14.getClass()
                android.view.Choreographer r14 = android.view.Choreographer.getInstance()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x04b7:
                android.app.IActivityManager r14 = android.app.ActivityManager.getService()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x04c0:
                android.content.Context r14 = r3.context
                android.view.ViewConfiguration r14 = android.view.ViewConfiguration.get(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x04cb:
                com.android.systemui.dagger.AndroidInternalsModule r14 = r3.androidInternalsModule
                r14.getClass()
                com.android.internal.logging.MetricsLogger r14 = new com.android.internal.logging.MetricsLogger
                r14.<init>()
                goto L_0x0b78
            L_0x04d7:
                com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager r14 = new com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager
                r14.<init>()
                goto L_0x0b78
            L_0x04de:
                android.content.Context r14 = r3.context
                java.util.List r14 = com.android.systemui.plugins.PluginsModule_ProvidesPrivilegedPluginsFactory.providesPrivilegedPlugins(r14)
                boolean r0 = com.android.systemui.plugins.PluginsModule_ProvidesPluginDebugFactory.providesPluginDebug()
                com.android.systemui.shared.plugins.PluginInstance$Factory r14 = com.android.systemui.plugins.PluginsModule_ProvidesPluginInstanceFactoryFactory.providesPluginInstanceFactory(r14, r0)
                goto L_0x0b78
            L_0x04ee:
                com.android.systemui.plugins.PluginEnablerImpl r14 = new com.android.systemui.plugins.PluginEnablerImpl
                android.content.Context r0 = r3.context
                javax.inject.Provider r1 = r3.providePackageManagerProvider
                java.lang.Object r1 = r1.get()
                android.content.pm.PackageManager r1 = (android.content.pm.PackageManager) r1
                r14.<init>(r0, r1)
                goto L_0x0b78
            L_0x04ff:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.NotificationManager> r0 = android.app.NotificationManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.NotificationManager r14 = (android.app.NotificationManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x050e:
                com.android.systemui.util.concurrency.ThreadFactoryImpl r14 = new com.android.systemui.util.concurrency.ThreadFactoryImpl
                r14.<init>()
                java.util.concurrent.Executor r14 = com.android.systemui.plugins.PluginsModule_ProvidesPluginExecutorFactory.providesPluginExecutor(r14)
                goto L_0x0b78
            L_0x0519:
                android.content.Context r0 = r3.context
                javax.inject.Provider r14 = r3.providePackageManagerProvider
                java.lang.Object r14 = r14.get()
                r1 = r14
                android.content.pm.PackageManager r1 = (android.content.pm.PackageManager) r1
                javax.inject.Provider r14 = r3.provideMainExecutorProvider
                java.lang.Object r14 = r14.get()
                r2 = r14
                java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
                javax.inject.Provider r14 = r3.providesPluginExecutorProvider
                java.lang.Object r14 = r14.get()
                java.util.concurrent.Executor r14 = (java.util.concurrent.Executor) r14
                javax.inject.Provider r4 = r3.provideNotificationManagerProvider
                java.lang.Object r4 = r4.get()
                android.app.NotificationManager r4 = (android.app.NotificationManager) r4
                javax.inject.Provider r5 = r3.pluginEnablerImplProvider
                java.lang.Object r5 = r5.get()
                com.android.systemui.shared.plugins.PluginEnabler r5 = (com.android.systemui.shared.plugins.PluginEnabler) r5
                android.content.Context r6 = r3.context
                java.util.List r6 = com.android.systemui.plugins.PluginsModule_ProvidesPrivilegedPluginsFactory.providesPrivilegedPlugins(r6)
                javax.inject.Provider r3 = r3.providesPluginInstanceFactoryProvider
                java.lang.Object r3 = r3.get()
                r7 = r3
                com.android.systemui.shared.plugins.PluginInstance$Factory r7 = (com.android.systemui.shared.plugins.PluginInstance.Factory) r7
                r3 = r14
                com.android.systemui.shared.plugins.PluginActionManager$Factory r14 = com.android.systemui.plugins.PluginsModule_ProvidePluginInstanceManagerFactoryFactory.providePluginInstanceManagerFactory(r0, r1, r2, r3, r4, r5, r6, r7)
                goto L_0x0b78
            L_0x055b:
                android.content.Context r0 = r3.context
                javax.inject.Provider r14 = r3.providePluginInstanceManagerFactoryProvider
                java.lang.Object r14 = r14.get()
                r1 = r14
                com.android.systemui.shared.plugins.PluginActionManager$Factory r1 = (com.android.systemui.shared.plugins.PluginActionManager.Factory) r1
                boolean r2 = com.android.systemui.plugins.PluginsModule_ProvidesPluginDebugFactory.providesPluginDebug()
                javax.inject.Provider r14 = r3.uncaughtExceptionPreHandlerManagerProvider
                java.lang.Object r14 = r14.get()
                com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager r14 = (com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager) r14
                javax.inject.Provider r4 = r3.pluginEnablerImplProvider
                java.lang.Object r4 = r4.get()
                com.android.systemui.shared.plugins.PluginEnabler r4 = (com.android.systemui.shared.plugins.PluginEnabler) r4
                android.content.Context r3 = r3.context
                com.android.systemui.shared.plugins.PluginPrefs r5 = com.android.systemui.plugins.PluginsModule_ProvidesPluginPrefsFactory.providesPluginPrefs(r3)
                java.util.List r6 = com.android.systemui.plugins.PluginsModule_ProvidesPrivilegedPluginsFactory.providesPrivilegedPlugins(r3)
                r3 = r14
                com.android.systemui.plugins.PluginManager r14 = com.android.systemui.plugins.PluginsModule_ProvidesPluginManagerFactory.providesPluginManager(r0, r1, r2, r3, r4, r5, r6)
                goto L_0x0b78
            L_0x058b:
                android.app.IActivityTaskManager r14 = android.app.ActivityTaskManager.getService()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0594:
                android.content.Context r14 = r3.context
                javax.inject.Provider r0 = r3.provideFaceManagerProvider
                java.lang.Object r0 = r0.get()
                android.hardware.face.FaceManager r0 = (android.hardware.face.FaceManager) r0
                javax.inject.Provider r1 = r3.providesFingerprintManagerProvider
                java.lang.Object r1 = r1.get()
                android.hardware.fingerprint.FingerprintManager r1 = (android.hardware.fingerprint.FingerprintManager) r1
                if (r0 != 0) goto L_0x05ab
                if (r1 != 0) goto L_0x05ab
                goto L_0x05b4
            L_0x05ab:
                java.lang.Class<android.hardware.biometrics.BiometricManager> r0 = android.hardware.biometrics.BiometricManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                r4 = r14
                android.hardware.biometrics.BiometricManager r4 = (android.hardware.biometrics.BiometricManager) r4
            L_0x05b4:
                r14 = r4
                goto L_0x0b78
            L_0x05b7:
                android.content.Context r14 = r3.context
                java.lang.Class<android.hardware.SensorPrivacyManager> r0 = android.hardware.SensorPrivacyManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.SensorPrivacyManager r14 = (android.hardware.SensorPrivacyManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x05c6:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.admin.DevicePolicyManager> r0 = android.app.admin.DevicePolicyManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.admin.DevicePolicyManager r14 = (android.app.admin.DevicePolicyManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x05d5:
                java.lang.String r14 = "dreams"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                android.service.dreams.IDreamManager r14 = android.service.dreams.IDreamManager.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x05e4:
                android.content.Context r14 = r3.context
                java.lang.Class<android.telephony.SubscriptionManager> r0 = android.telephony.SubscriptionManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.telephony.SubscriptionManager r14 = (android.telephony.SubscriptionManager) r14
                android.telephony.SubscriptionManager r14 = r14.createForAllUserProfiles()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x05f7:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.trust.TrustManager> r0 = android.app.trust.TrustManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.trust.TrustManager r14 = (android.app.trust.TrustManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0606:
                com.android.systemui.util.kotlin.GlobalCoroutinesModule r14 = r3.globalCoroutinesModule
                r14.getClass()
                kotlinx.coroutines.scheduling.DefaultScheduler r14 = kotlinx.coroutines.Dispatchers.Default
                kotlinx.coroutines.android.HandlerContext r14 = kotlinx.coroutines.internal.MainDispatcherLoader.dispatcher
                kotlinx.coroutines.android.HandlerContext r14 = r14.immediate
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0616:
                android.content.Context r14 = r3.context
                com.android.internal.util.LatencyTracker r14 = com.android.internal.util.LatencyTracker.getInstance(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0621:
                android.content.Context r14 = r3.context
                java.lang.Class<android.telephony.TelephonyManager> r0 = android.telephony.TelephonyManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.telephony.TelephonyManager r14 = (android.telephony.TelephonyManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0630:
                com.android.systemui.dagger.AndroidInternalsModule r14 = r3.androidInternalsModule
                r14.getClass()
                com.android.internal.widget.LockPatternUtils r14 = new com.android.internal.widget.LockPatternUtils
                android.content.Context r0 = r3.context
                r14.<init>(r0)
                goto L_0x0b78
            L_0x063e:
                android.os.Looper r14 = com.android.systemui.util.concurrency.GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper()
                com.android.systemui.util.concurrency.ExecutorImpl r0 = new com.android.systemui.util.concurrency.ExecutorImpl
                r0.<init>(r14)
                goto L_0x011b
            L_0x0649:
                com.android.systemui.dagger.FrameworkServicesModule r14 = r3.frameworkServicesModule
                r14.getClass()
                android.content.Context r14 = r3.context
                android.view.LayoutInflater r14 = android.view.LayoutInflater.from(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0659:
                android.content.Context r14 = r3.context
                android.content.pm.PackageManager r0 = r14.getPackageManager()
                java.lang.String r1 = "android.hardware.biometrics.face"
                boolean r0 = r0.hasSystemFeature(r1)
                if (r0 == 0) goto L_0x05b4
                java.lang.Class<android.hardware.face.FaceManager> r0 = android.hardware.face.FaceManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                r4 = r14
                android.hardware.face.FaceManager r4 = (android.hardware.face.FaceManager) r4
                goto L_0x05b4
            L_0x0672:
                android.content.Context r14 = r3.context
                android.content.pm.PackageManager r0 = r14.getPackageManager()
                java.lang.String r1 = "android.hardware.fingerprint"
                boolean r0 = r0.hasSystemFeature(r1)
                if (r0 == 0) goto L_0x05b4
                java.lang.Class<android.hardware.fingerprint.FingerprintManager> r0 = android.hardware.fingerprint.FingerprintManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                r4 = r14
                android.hardware.fingerprint.FingerprintManager r4 = (android.hardware.fingerprint.FingerprintManager) r4
                goto L_0x05b4
            L_0x068b:
                com.android.systemui.util.concurrency.ExecutionImpl r14 = new com.android.systemui.util.concurrency.ExecutionImpl
                r14.<init>()
                goto L_0x0b78
            L_0x0692:
                com.android.systemui.util.wrapper.BuildInfo r14 = new com.android.systemui.util.wrapper.BuildInfo
                r14.<init>()
                goto L_0x0b78
            L_0x0699:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r3.unfoldTransitionModule
                javax.inject.Provider r0 = r3.provideDeviceStateManagerProvider
                java.lang.Object r0 = r0.get()
                android.hardware.devicestate.DeviceStateManager r0 = (android.hardware.devicestate.DeviceStateManager) r0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r1 = r3.provideApplicationContextProvider
                java.lang.Object r1 = r1.get()
                android.content.Context r1 = (android.content.Context) r1
                javax.inject.Provider r2 = r3.provideMainExecutorProvider
                java.lang.Object r2 = r2.get()
                java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
                r14.getClass()
                android.hardware.devicestate.DeviceStateManager$FoldStateListener r14 = new android.hardware.devicestate.DeviceStateManager$FoldStateListener
                r14.<init>(r1)
                r0.registerCallback(r2, r14)
                goto L_0x0b78
            L_0x06c0:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.AlarmManager> r0 = android.app.AlarmManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.AlarmManager r14 = (android.app.AlarmManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x06cf:
                com.android.systemui.util.kotlin.GlobalCoroutinesModule r14 = r3.globalCoroutinesModule
                r14.getClass()
                kotlin.coroutines.EmptyCoroutineContext r14 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x06db:
                com.android.systemui.util.kotlin.GlobalCoroutinesModule r14 = r3.globalCoroutinesModule
                javax.inject.Provider r0 = r3.tracingCoroutineContextProvider
                java.lang.Object r0 = r0.get()
                kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0
                r14.getClass()
                kotlinx.coroutines.scheduling.DefaultScheduler r14 = kotlinx.coroutines.Dispatchers.Default
                kotlinx.coroutines.android.HandlerContext r14 = kotlinx.coroutines.internal.MainDispatcherLoader.dispatcher
                kotlinx.coroutines.android.HandlerContext r14 = r14.immediate
                r14.getClass()
                kotlin.coroutines.CoroutineContext r14 = kotlin.coroutines.CoroutineContext.DefaultImpls.plus(r14, r0)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x06fa:
                com.android.systemui.util.kotlin.GlobalCoroutinesModule r14 = r3.globalCoroutinesModule
                javax.inject.Provider r0 = r3.mainCoroutineContextProvider
                java.lang.Object r0 = r0.get()
                kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0
                r14.getClass()
                kotlinx.coroutines.internal.ContextScope r14 = kotlinx.coroutines.CoroutineScopeKt.CoroutineScope(r0)
                goto L_0x0b78
            L_0x070d:
                android.content.Context r14 = r3.context
                java.lang.Class<android.os.PowerManager> r0 = android.os.PowerManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.PowerManager r14 = (android.os.PowerManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x071c:
                boolean r14 = android.app.ActivityManager.isRunningInUserTestHarness()
                java.lang.Boolean r14 = java.lang.Boolean.valueOf(r14)
                goto L_0x0b78
            L_0x0726:
                com.android.systemui.dagger.GlobalModule r14 = r3.globalModule
                r14.getClass()
                android.content.Context r14 = r3.context
                android.content.Context r14 = r14.getApplicationContext()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0736:
                android.content.Context r14 = r3.context
                android.content.pm.PackageManager r14 = r14.getPackageManager()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0741:
                com.android.internal.jank.InteractionJankMonitor r14 = com.android.internal.jank.InteractionJankMonitor.getInstance()
                r0 = -256(0xffffffffffffff00, float:NaN)
                r1 = 4604930618986332160(0x3fe8000000000000, double:0.75)
                r14.configDebugOverlay(r0, r1)
                goto L_0x0b78
            L_0x074e:
                android.content.Context r14 = r3.context
                java.lang.Class<android.content.pm.LauncherApps> r0 = android.content.pm.LauncherApps.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.content.pm.LauncherApps r14 = (android.content.pm.LauncherApps) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x075d:
                android.content.Context r14 = r3.context
                java.lang.Class<android.os.UserManager> r0 = android.os.UserManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.os.UserManager r14 = (android.os.UserManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x076c:
                java.lang.String r14 = "statusbar"
                android.os.IBinder r14 = android.os.ServiceManager.getService(r14)
                com.android.internal.statusbar.IStatusBarService r14 = com.android.internal.statusbar.IStatusBarService.Stub.asInterface(r14)
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x077b:
                android.content.Context r14 = r3.context
                java.lang.Class<android.view.WindowManager> r0 = android.view.WindowManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.view.WindowManager r14 = (android.view.WindowManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x078a:
                android.app.ActivityTaskManager r14 = android.app.ActivityTaskManager.getInstance()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0793:
                com.android.internal.logging.UiEventLoggerImpl r14 = new com.android.internal.logging.UiEventLoggerImpl
                r14.<init>()
                goto L_0x0b78
            L_0x079a:
                android.content.Context r14 = r3.context
                java.util.concurrent.Executor r14 = r14.getMainExecutor()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x07a5:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r3.unfoldTransitionModule
                javax.inject.Provider r0 = r3.deviceStateManagerFoldProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.updates.FoldProvider r0 = (com.android.systemui.unfold.updates.FoldProvider) r0
                javax.inject.Provider r1 = r3.provideMainExecutorProvider
                java.lang.Object r1 = r1.get()
                java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
                javax.inject.Provider r2 = r3.unfoldTransitionProgressProvider
                java.lang.Object r2 = r2.get()
                java.util.Optional r2 = (java.util.Optional) r2
                r14.getClass()
                com.android.systemui.unfold.UnfoldTransitionModule$provideUnfoldOnlyProvider$1 r14 = new com.android.systemui.unfold.UnfoldTransitionModule$provideUnfoldOnlyProvider$1
                r3 = 0
                r14.<init>(r3, r0, r1)
                java.util.Optional r14 = r2.map(r14)
                goto L_0x0b78
            L_0x07ce:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r3.unfoldTransitionModule
                r14.getClass()
                java.lang.Boolean r14 = java.lang.Boolean.TRUE
                goto L_0x0b78
            L_0x07d7:
                com.android.systemui.unfold.UnfoldRotationProviderInternalModule r14 = r3.unfoldRotationProviderInternalModule
                javax.inject.Provider r0 = r3.factoryProvider7
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7) r0
                javax.inject.Provider r1 = r3.unfoldBgProgressHandlerProvider
                java.lang.Object r1 = r1.get()
                android.os.Handler r1 = (android.os.Handler) r1
                r14.getClass()
                com.android.systemui.unfold.updates.RotationChangeProvider r14 = new com.android.systemui.unfold.updates.RotationChangeProvider
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r0 = r0.this$0
                java.lang.Object r2 = r0.keyguardStatusBarViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) r2
                javax.inject.Provider r2 = r2.provideDisplayManagerProvider
                java.lang.Object r2 = r2.get()
                android.hardware.display.DisplayManager r2 = (android.hardware.display.DisplayManager) r2
                java.lang.Object r0 = r0.keyguardStatusBarViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) r0
                android.content.Context r0 = r0.context
                r14.<init>(r2, r0, r1)
                goto L_0x0b78
            L_0x0807:
                android.os.HandlerThread r14 = new android.os.HandlerThread
                java.lang.String r0 = "UnfoldBg"
                r1 = -2
                r14.<init>(r0, r1)
                r14.start()
                android.os.Looper r14 = r14.getLooper()
                goto L_0x0b78
            L_0x0818:
                javax.inject.Provider r14 = r3.provideBgLooperProvider
                java.lang.Object r14 = r14.get()
                android.os.Looper r14 = (android.os.Looper) r14
                android.os.Handler r0 = new android.os.Handler
                r0.<init>(r14)
                goto L_0x011b
            L_0x0827:
                com.android.systemui.unfold.FoldStateProviderModule r14 = r3.foldStateProviderModule
                javax.inject.Provider r0 = r3.factoryProvider5
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5) r0
                javax.inject.Provider r2 = r3.resourceUnfoldTransitionConfigProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r2 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r2
                javax.inject.Provider r4 = r3.unfoldBgProgressHandlerProvider
                java.lang.Object r4 = r4.get()
                android.os.Handler r4 = (android.os.Handler) r4
                javax.inject.Provider r5 = r3.factoryProvider6
                java.lang.Object r5 = r5.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6 r5 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6) r5
                com.android.systemui.unfold.HingeAngleProviderInternalModule r6 = r3.hingeAngleProviderInternalModule
                r6.getClass()
                kotlin.Lazy r2 = r2.isHingeAngleEnabled$delegate
                java.lang.Object r2 = r2.getValue()
                java.lang.Boolean r2 = (java.lang.Boolean) r2
                boolean r2 = r2.booleanValue()
                if (r2 == 0) goto L_0x087b
                com.android.systemui.unfold.updates.hinge.HingeSensorAngleProvider r1 = new com.android.systemui.unfold.updates.hinge.HingeSensorAngleProvider
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r2 = r5.this$0
                java.lang.Object r5 = r2.keyguardStatusBarViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r5 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) r5
                javax.inject.Provider r5 = r5.providesSensorManagerProvider
                java.lang.Object r5 = r5.get()
                android.hardware.SensorManager r5 = (android.hardware.SensorManager) r5
                java.lang.Object r2 = r2.keyguardStatusBarViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) r2
                javax.inject.Provider r2 = r2.provideUiBackgroundExecutorProvider
                java.lang.Object r2 = r2.get()
                java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
                r1.<init>(r5, r2, r4)
            L_0x087b:
                javax.inject.Provider r2 = r3.provideBgRotationChangeProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.updates.RotationChangeProvider r2 = (com.android.systemui.unfold.updates.RotationChangeProvider) r2
                javax.inject.Provider r3 = r3.unfoldBgProgressHandlerProvider
                java.lang.Object r3 = r3.get()
                android.os.Handler r3 = (android.os.Handler) r3
                r14.getClass()
                com.android.systemui.unfold.updates.DeviceFoldStateProvider r14 = r0.create(r1, r2, r3)
                goto L_0x0b78
            L_0x0894:
                com.android.systemui.unfold.UnfoldSharedInternalModule r14 = r3.unfoldSharedInternalModule
                javax.inject.Provider r0 = r3.resourceUnfoldTransitionConfigProvider
                java.lang.Object r0 = r0.get()
                r4 = r0
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r4 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r4
                javax.inject.Provider r0 = r3.factoryProvider
                java.lang.Object r0 = r0.get()
                r5 = r0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1 r5 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1) r5
                javax.inject.Provider r0 = r3.factoryProvider2
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2) r0
                javax.inject.Provider r1 = r3.factoryProvider4
                java.lang.Object r1 = r1.get()
                r7 = r1
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3 r7 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3) r7
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r8 = r3.fixedTimingTransitionProgressProvider
                javax.inject.Provider r1 = r3.provideBgFoldStateProvider
                java.lang.Object r1 = r1.get()
                r9 = r1
                com.android.systemui.unfold.updates.DeviceFoldStateProvider r9 = (com.android.systemui.unfold.updates.DeviceFoldStateProvider) r9
                javax.inject.Provider r1 = r3.unfoldBgProgressHandlerProvider
                java.lang.Object r1 = r1.get()
                r10 = r1
                android.os.Handler r10 = (android.os.Handler) r10
                r14.getClass()
                java.lang.String r14 = "BgThread"
                com.android.systemui.unfold.util.ATraceLoggerTransitionProgressListener r6 = r0.create(r14)
                java.util.Optional r14 = com.android.systemui.unfold.UnfoldSharedInternalModule.createOptionalUnfoldTransitionProgressProvider(r4, r5, r6, r7, r8, r9, r10)
                goto L_0x0b78
            L_0x08dc:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8
                r0.<init>(r14)
                goto L_0x011b
            L_0x08e3:
                android.content.Context r14 = r3.context
                java.lang.Class<android.hardware.display.DisplayManager> r0 = android.hardware.display.DisplayManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.display.DisplayManager r14 = (android.hardware.display.DisplayManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x08f2:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7
                r0.<init>(r14)
                goto L_0x011b
            L_0x08f9:
                com.android.systemui.unfold.UnfoldRotationProviderInternalModule r14 = r3.unfoldRotationProviderInternalModule
                javax.inject.Provider r0 = r3.factoryProvider7
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7) r0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r1 = r3.provideMainHandlerProvider
                java.lang.Object r1 = r1.get()
                android.os.Handler r1 = (android.os.Handler) r1
                r14.getClass()
                com.android.systemui.unfold.updates.RotationChangeProvider r14 = new com.android.systemui.unfold.updates.RotationChangeProvider
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r0 = r0.this$0
                java.lang.Object r2 = r0.keyguardStatusBarViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) r2
                javax.inject.Provider r2 = r2.provideDisplayManagerProvider
                java.lang.Object r2 = r2.get()
                android.hardware.display.DisplayManager r2 = (android.hardware.display.DisplayManager) r2
                java.lang.Object r0 = r0.keyguardStatusBarViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) r0
                android.content.Context r0 = r0.context
                r14.<init>(r2, r0, r1)
                goto L_0x0b78
            L_0x0929:
                java.util.concurrent.ExecutorService r14 = java.util.concurrent.Executors.newSingleThreadExecutor()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0932:
                android.content.Context r14 = r3.context
                java.lang.Class<android.hardware.SensorManager> r0 = android.hardware.SensorManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.SensorManager r14 = (android.hardware.SensorManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0941:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6
                r0.<init>(r14)
                goto L_0x011b
            L_0x0948:
                android.os.Looper r14 = com.android.systemui.util.concurrency.GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper()
                android.os.Handler r0 = new android.os.Handler
                r0.<init>(r14)
                goto L_0x011b
            L_0x0953:
                com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl r14 = new com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl
                r14.<init>()
                goto L_0x0b78
            L_0x095a:
                com.android.systemui.unfold.UnfoldSharedModule r14 = r3.unfoldSharedModule
                javax.inject.Provider r0 = r3.unfoldKeyguardVisibilityManagerImplProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl r0 = (com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl) r0
                r14.getClass()
                goto L_0x011b
            L_0x0969:
                android.content.Context r14 = r3.context
                java.lang.Class<android.app.ActivityManager> r0 = android.app.ActivityManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.app.ActivityManager r14 = (android.app.ActivityManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0978:
                com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider r14 = new com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider
                javax.inject.Provider r0 = r3.provideActivityManagerProvider
                java.lang.Object r0 = r0.get()
                android.app.ActivityManager r0 = (android.app.ActivityManager) r0
                r14.<init>(r0)
                goto L_0x0b78
            L_0x0987:
                com.android.systemui.dump.DumpManager r14 = new com.android.systemui.dump.DumpManager
                r14.<init>()
                goto L_0x0b78
            L_0x098e:
                com.android.systemui.keyguard.ScreenLifecycle r14 = new com.android.systemui.keyguard.ScreenLifecycle
                javax.inject.Provider r0 = r3.dumpManagerProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.dump.DumpManager r0 = (com.android.systemui.dump.DumpManager) r0
                r14.<init>()
                r14.mScreenState = r2
                r0.getClass()
                java.lang.String r1 = "ScreenLifecycle"
                r0.registerDumpable(r14, com.android.systemui.dump.DumpPriority.CRITICAL, r1)
                goto L_0x0b78
            L_0x09a7:
                com.android.systemui.keyguard.LifecycleScreenStatusProvider r14 = new com.android.systemui.keyguard.LifecycleScreenStatusProvider
                javax.inject.Provider r0 = r3.screenLifecycleProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.keyguard.ScreenLifecycle r0 = (com.android.systemui.keyguard.ScreenLifecycle) r0
                r14.<init>(r0)
                goto L_0x0b78
            L_0x09b6:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5
                r0.<init>(r14)
                goto L_0x011b
            L_0x09bd:
                com.android.systemui.unfold.FoldStateProviderModule r14 = r3.foldStateProviderModule
                javax.inject.Provider r0 = r3.factoryProvider5
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5) r0
                javax.inject.Provider r2 = r3.resourceUnfoldTransitionConfigProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r2 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r2
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r4 = r3.provideMainHandlerProvider
                java.lang.Object r4 = r4.get()
                android.os.Handler r4 = (android.os.Handler) r4
                javax.inject.Provider r5 = r3.factoryProvider6
                java.lang.Object r5 = r5.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6 r5 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6) r5
                com.android.systemui.unfold.HingeAngleProviderInternalModule r6 = r3.hingeAngleProviderInternalModule
                r6.getClass()
                kotlin.Lazy r2 = r2.isHingeAngleEnabled$delegate
                java.lang.Object r2 = r2.getValue()
                java.lang.Boolean r2 = (java.lang.Boolean) r2
                boolean r2 = r2.booleanValue()
                if (r2 == 0) goto L_0x0a11
                com.android.systemui.unfold.updates.hinge.HingeSensorAngleProvider r1 = new com.android.systemui.unfold.updates.hinge.HingeSensorAngleProvider
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r2 = r5.this$0
                java.lang.Object r5 = r2.keyguardStatusBarViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r5 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) r5
                javax.inject.Provider r5 = r5.providesSensorManagerProvider
                java.lang.Object r5 = r5.get()
                android.hardware.SensorManager r5 = (android.hardware.SensorManager) r5
                java.lang.Object r2 = r2.keyguardStatusBarViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) r2
                javax.inject.Provider r2 = r2.provideUiBackgroundExecutorProvider
                java.lang.Object r2 = r2.get()
                java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
                r1.<init>(r5, r2, r4)
            L_0x0a11:
                javax.inject.Provider r2 = r3.provideRotationChangeProvider
                java.lang.Object r2 = r2.get()
                com.android.systemui.unfold.updates.RotationChangeProvider r2 = (com.android.systemui.unfold.updates.RotationChangeProvider) r2
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r3 = r3.provideMainHandlerProvider
                java.lang.Object r3 = r3.get()
                android.os.Handler r3 = (android.os.Handler) r3
                r14.getClass()
                com.android.systemui.unfold.updates.DeviceFoldStateProvider r14 = r0.create(r1, r2, r3)
                goto L_0x0b78
            L_0x0a2a:
                com.android.systemui.unfold.progress.FixedTimingTransitionProgressProvider r14 = new com.android.systemui.unfold.progress.FixedTimingTransitionProgressProvider
                javax.inject.Provider r0 = r3.provideFoldStateProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.updates.DeviceFoldStateProvider r0 = (com.android.systemui.unfold.updates.DeviceFoldStateProvider) r0
                r14.<init>(r0)
                goto L_0x0b78
            L_0x0a39:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4 r14 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4
                r14.<init>()
                goto L_0x0b78
            L_0x0a40:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3
                r0.<init>(r14)
                goto L_0x011b
            L_0x0a47:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2
                r0.<init>(r14)
                goto L_0x011b
            L_0x0a4e:
                android.content.Context r14 = r3.context
                android.content.ContentResolver r14 = r14.getContentResolver()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0a59:
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1 r0 = new com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1
                r0.<init>(r14)
                goto L_0x011b
            L_0x0a60:
                com.android.systemui.unfold.UnfoldSharedInternalModule r14 = r3.unfoldSharedInternalModule
                javax.inject.Provider r0 = r3.resourceUnfoldTransitionConfigProvider
                java.lang.Object r0 = r0.get()
                r4 = r0
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r4 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r4
                javax.inject.Provider r0 = r3.factoryProvider
                java.lang.Object r0 = r0.get()
                r5 = r0
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1 r5 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1) r5
                javax.inject.Provider r0 = r3.factoryProvider2
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2 r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2) r0
                javax.inject.Provider r1 = r3.factoryProvider4
                java.lang.Object r1 = r1.get()
                r7 = r1
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3 r7 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3) r7
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r8 = r3.fixedTimingTransitionProgressProvider
                javax.inject.Provider r1 = r3.provideFoldStateProvider
                java.lang.Object r1 = r1.get()
                r9 = r1
                com.android.systemui.unfold.updates.DeviceFoldStateProvider r9 = (com.android.systemui.unfold.updates.DeviceFoldStateProvider) r9
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl$SwitchingProvider r1 = r3.provideMainHandlerProvider
                java.lang.Object r1 = r1.get()
                r10 = r1
                android.os.Handler r10 = (android.os.Handler) r10
                javax.inject.Provider r1 = r3.factoryProvider8
                java.lang.Object r1 = r1.get()
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8 r1 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8) r1
                javax.inject.Provider r2 = r3.unfoldBgTransitionProgressProvider
                javax.inject.Provider r3 = r3.unfoldBgProgressFlagProvider
                java.lang.Object r3 = r3.get()
                java.lang.Boolean r3 = (java.lang.Boolean) r3
                java.util.Optional r3 = java.util.Optional.of(r3)
                r14.getClass()
                java.lang.Boolean r14 = java.lang.Boolean.FALSE
                boolean r6 = r3.isPresent()
                if (r6 == 0) goto L_0x0abe
                java.lang.Object r14 = r3.get()
            L_0x0abe:
                java.lang.Boolean r14 = (java.lang.Boolean) r14
                boolean r14 = r14.booleanValue()
                if (r14 == 0) goto L_0x0adf
                java.lang.Object r14 = r2.get()
                java.util.Optional r14 = (java.util.Optional) r14
                com.android.systemui.unfold.UnfoldSharedInternalModule$unfoldTransitionProgressProvider$mainThreadProvider$1 r2 = new com.android.systemui.unfold.UnfoldSharedInternalModule$unfoldTransitionProgressProvider$mainThreadProvider$1
                r2.<init>(r1)
                java.util.Optional r14 = r14.map(r2)
                com.android.systemui.unfold.UnfoldSharedInternalModule$unfoldTransitionProgressProvider$1 r1 = new com.android.systemui.unfold.UnfoldSharedInternalModule$unfoldTransitionProgressProvider$1
                r1.<init>(r0)
                r14.ifPresent(r1)
                goto L_0x0b78
            L_0x0adf:
                java.lang.String r14 = "MainThread"
                com.android.systemui.unfold.util.ATraceLoggerTransitionProgressListener r6 = r0.create(r14)
                java.util.Optional r14 = com.android.systemui.unfold.UnfoldSharedInternalModule.createOptionalUnfoldTransitionProgressProvider(r4, r5, r6, r7, r8, r9, r10)
                goto L_0x0b78
            L_0x0aeb:
                android.content.Context r14 = r3.context
                java.lang.Class<android.hardware.devicestate.DeviceStateManager> r0 = android.hardware.devicestate.DeviceStateManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.hardware.devicestate.DeviceStateManager r14 = (android.hardware.devicestate.DeviceStateManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0afa:
                com.android.systemui.unfold.system.DeviceStateManagerFoldProvider r14 = new com.android.systemui.unfold.system.DeviceStateManagerFoldProvider
                javax.inject.Provider r0 = r3.provideDeviceStateManagerProvider
                java.lang.Object r0 = r0.get()
                android.hardware.devicestate.DeviceStateManager r0 = (android.hardware.devicestate.DeviceStateManager) r0
                android.content.Context r1 = r3.context
                r14.<init>(r0, r1)
                goto L_0x0b78
            L_0x0b0b:
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r14 = new com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig
                r14.<init>()
                goto L_0x0b78
            L_0x0b11:
                com.android.systemui.unfold.UnfoldTransitionModule r14 = r3.unfoldTransitionModule
                javax.inject.Provider r0 = r3.resourceUnfoldTransitionConfigProvider
                java.lang.Object r0 = r0.get()
                com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig r0 = (com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig) r0
                javax.inject.Provider r1 = r3.deviceStateManagerFoldProvider
                java.lang.Object r1 = r1.get()
                com.android.systemui.unfold.updates.FoldProvider r1 = (com.android.systemui.unfold.updates.FoldProvider) r1
                javax.inject.Provider r2 = r3.unfoldTransitionProgressProvider
                javax.inject.Provider r3 = r3.provideUnfoldOnlyProvider
                r14.getClass()
                kotlin.Lazy r14 = r0.isEnabled$delegate
                java.lang.Object r14 = r14.getValue()
                java.lang.Boolean r14 = (java.lang.Boolean) r14
                boolean r14 = r14.booleanValue()
                if (r14 == 0) goto L_0x0b3f
                boolean r14 = com.android.systemui.unfold.UnfoldTransitionModuleKt.ENABLE_FOLD_TASK_ANIMATIONS
                if (r14 == 0) goto L_0x0b3d
                goto L_0x0b40
            L_0x0b3d:
                r2 = r3
                goto L_0x0b40
            L_0x0b3f:
                r2 = r4
            L_0x0b40:
                if (r2 == 0) goto L_0x0b59
                java.lang.Object r14 = r2.get()
                java.util.Optional r14 = (java.util.Optional) r14
                if (r14 == 0) goto L_0x0b59
                java.lang.Object r14 = r14.orElse(r4)
                com.android.systemui.unfold.UnfoldTransitionProgressProvider r14 = (com.android.systemui.unfold.UnfoldTransitionProgressProvider) r14
                if (r14 == 0) goto L_0x0b59
                com.android.systemui.unfold.UnfoldProgressProvider r0 = new com.android.systemui.unfold.UnfoldProgressProvider
                r0.<init>(r14, r1)
                goto L_0x011b
            L_0x0b59:
                com.android.wm.shell.unfold.ShellUnfoldProgressProvider$1 r0 = com.android.wm.shell.unfold.ShellUnfoldProgressProvider.NO_PROVIDER
                goto L_0x011b
            L_0x0b5d:
                android.content.Context r14 = r3.context
                java.lang.Class<android.view.accessibility.AccessibilityManager> r0 = android.view.accessibility.AccessibilityManager.class
                java.lang.Object r14 = r14.getSystemService(r0)
                android.view.accessibility.AccessibilityManager r14 = (android.view.accessibility.AccessibilityManager) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0b6b:
                android.view.IWindowManager r14 = android.view.WindowManagerGlobal.getWindowManagerService()
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0b78
            L_0x0b73:
                com.android.systemui.flags.SystemPropertiesHelper r14 = new com.android.systemui.flags.SystemPropertiesHelper
                r14.<init>()
            L_0x0b78:
                return r14
            L_0x0b79:
                java.lang.Object r0 = r14.keyguardStatusBarViewComponentImpl
                com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl r0 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl) r0
                int r14 = r14.id
                if (r14 == 0) goto L_0x0b9f
                r1 = 1
                if (r14 == r1) goto L_0x0b90
                r0 = 2
                if (r14 != r0) goto L_0x0b8a
                com.android.systemui.statusbar.phone.StatusBarLocation r14 = com.android.systemui.statusbar.phone.StatusBarLocation.KEYGUARD
                goto L_0x0bad
            L_0x0b8a:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r14)
                throw r0
            L_0x0b90:
                com.android.systemui.statusbar.phone.KeyguardStatusBarView r14 = r0.view
                r0 = 2131362068(0x7f0a0114, float:1.8343906E38)
                android.view.View r14 = r14.findViewById(r0)
                com.android.systemui.battery.BatteryMeterView r14 = (com.android.systemui.battery.BatteryMeterView) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
                goto L_0x0bad
            L_0x0b9f:
                com.android.systemui.statusbar.phone.KeyguardStatusBarView r14 = r0.view
                r0 = 2131362799(0x7f0a03ef, float:1.8345389E38)
                android.view.View r14 = r14.findViewById(r0)
                com.android.keyguard.CarrierText r14 = (com.android.keyguard.CarrierText) r14
                dagger.internal.Preconditions.checkNotNullFromProvides(r14)
            L_0x0bad:
                return r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider.get():java.lang.Object");
        }
    }

    public DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, KeyguardStatusBarView keyguardStatusBarView, NotificationPanelViewController.AnonymousClass10 r4) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.view = keyguardStatusBarView;
        this.shadeViewStateProvider = r4;
    }

    public final KeyguardStatusBarViewController getKeyguardStatusBarViewController() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        BatteryMeterViewController batteryMeterViewController = new BatteryMeterViewController((BatteryMeterView) this.getBatteryMeterViewProvider.get(), (StatusBarLocation) this.getStatusBarLocationProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (TunerService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get());
        StatusBarUserChipViewModel statusBarUserChipViewModel = new StatusBarUserChipViewModel((UserSwitcherInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userSwitcherInteractorProvider.get());
        KeyguardLogger r26 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1539$$Nest$mkeyguardLogger(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl);
        NotificationMediaManager notificationMediaManager = (NotificationMediaManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationMediaManagerProvider.get();
        StatusOverlayHoverListenerFactory r27 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1685$$Nest$mstatusOverlayHoverListenerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl);
        KeyguardStatusBarView keyguardStatusBarView = this.view;
        NotificationPanelViewController.AnonymousClass10 r11 = this.shadeViewStateProvider;
        StatusBarUserChipViewModel statusBarUserChipViewModel2 = statusBarUserChipViewModel;
        return new KeyguardStatusBarViewController(keyguardStatusBarView, new CarrierTextController((CarrierText) this.getCarrierTextProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.carrierTextManagerBuilder(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get()), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (SystemStatusAnimationSchedulerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemStatusAnimationSchedulerProvider.get(), (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get(), (UserInfoControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userInfoControllerImplProvider.get(), (StatusBarIconController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarIconControllerImplProvider.get(), (StatusBarIconController.TintedIconManager.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider19.get(), batteryMeterViewController, r11, (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (KeyguardBypassController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBypassControllerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (KeyguardStatusBarViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStatusBarViewModelProvider.get(), (BiometricUnlockController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.biometricUnlockControllerProvider.get(), (SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (StatusBarContentInsetsProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarContentInsetsProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUserManagerProvider.get(), statusBarUserChipViewModel2, (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get(), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), r26, r27);
    }
}
