package com.google.android.systemui.keyguard.ui.composable.section;

import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.keyguard.ui.viewmodel.AodAlphaViewModel;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.ShadeViewController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11;
import com.google.android.systemui.keyguard.ui.viewmodel.KeyguardAmbientIndicationViewModel;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class GoogleAmbientIndicationSection {
    public final ActivityStarter activityStarter;
    public final AodAlphaViewModel aodAlphaViewModel;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11 delayedWakeLockFactory;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final PowerInteractor powerInteractor;
    public final ShadeViewController shadeViewController;
    public final KeyguardAmbientIndicationViewModel viewModel;

    public GoogleAmbientIndicationSection(KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, AodAlphaViewModel aodAlphaViewModel2, ShadeViewController shadeViewController2, PowerInteractor powerInteractor2, KeyguardUpdateMonitor keyguardUpdateMonitor2, ActivityStarter activityStarter2, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11) {
        this.activityStarter = activityStarter2;
    }
}
