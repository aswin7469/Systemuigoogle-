package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.shade.ShadeController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$24;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SetupWizardAction_Factory implements Provider {
    public static SetupWizardAction newInstance(Context context, Executor executor, SettingsAction settingsAction, LaunchOpa launchOpa, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$24 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$24, KeyguardUpdateMonitor keyguardUpdateMonitor, ShadeController shadeController) {
        return new SetupWizardAction(context, executor, settingsAction, launchOpa, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$24, keyguardUpdateMonitor, shadeController);
    }
}
