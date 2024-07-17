package com.google.android.systemui.dagger;

import android.app.ActivityTaskManager;
import android.os.PowerManager;
import android.telecom.TelecomManager;
import android.view.ViewGroup;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.EmergencyButtonController;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import dagger.internal.DoubleCheck;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl {
    public final ViewGroup bouncerContainer;
    public final Provider factoryProvider;
    public final Provider keyguardSecurityContainerControllerProvider;
    public final Provider keyguardSecurityViewFlipperControllerProvider;
    public final Provider providesKeyguardSecurityContainerProvider;
    public final Provider providesKeyguardSecurityViewFlipperProvider;
    public final Provider providesOptionalSidefpsControllerProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    public DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, ViewGroup viewGroup) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.bouncerContainer = viewGroup;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.providesKeyguardSecurityContainerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 1, 4));
        this.factoryProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 2, 4));
        this.providesKeyguardSecurityViewFlipperProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 4, 4));
        this.keyguardSecurityViewFlipperControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 3, 4));
        this.providesOptionalSidefpsControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 5, 4));
        this.keyguardSecurityContainerControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 0, 4));
    }

    public final EmergencyButtonController.Factory emergencyButtonControllerFactory() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new EmergencyButtonController.Factory((ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationControllerImplProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (PowerManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePowerManagerProvider.get(), (ActivityTaskManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideActivityTaskManagerProvider.get(), (ShadeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeControllerProvider.get(), (TelecomManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideTelecomManagerProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (LockPatternUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideLockPatternUtilsProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get());
    }
}
