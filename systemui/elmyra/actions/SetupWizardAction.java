package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.shade.ShadeController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30;
import com.google.android.systemui.elmyra.gates.Gate;
import com.google.android.systemui.elmyra.gates.KeyguardDeferredSetup;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SetupWizardAction extends Action {
    public final Context mContext;
    public boolean mDeviceInDemoMode;
    public final AnonymousClass2 mKeyguardDeferredSetupListener;
    public final LaunchOpa mLaunchOpa;
    public final SettingsAction mSettingsAction;
    public final String mSettingsPackageName;
    public final ShadeController mShadeController;
    public boolean mUserCompletedSuw;
    public final KeyguardUpdateMonitorCallback mUserSwitchCallback;

    public SetupWizardAction(Context context, Executor executor, SettingsAction settingsAction, LaunchOpa launchOpa, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30, KeyguardUpdateMonitor keyguardUpdateMonitor, ShadeController shadeController) {
        super(executor, (List) null);
        AnonymousClass1 r4 = new KeyguardUpdateMonitorCallback() {
            public final void onUserSwitching(int i) {
                SetupWizardAction setupWizardAction = SetupWizardAction.this;
                setupWizardAction.mDeviceInDemoMode = UserManager.isDeviceInDemoMode(setupWizardAction.mContext);
                setupWizardAction.notifyListener();
            }
        };
        this.mUserSwitchCallback = r4;
        AnonymousClass2 r0 = new Gate.Listener() {
            public final void onGateChanged(Gate gate) {
                boolean z = ((KeyguardDeferredSetup) gate).mDeferredSetupComplete;
                SetupWizardAction setupWizardAction = SetupWizardAction.this;
                setupWizardAction.mUserCompletedSuw = z;
                setupWizardAction.notifyListener();
            }
        };
        this.mContext = context;
        this.mSettingsPackageName = context.getResources().getString(2131953858);
        this.mSettingsAction = settingsAction;
        this.mLaunchOpa = launchOpa;
        this.mShadeController = shadeController;
        keyguardUpdateMonitor.registerCallback(r4);
        KeyguardDeferredSetup create = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30.create(Collections.emptySet());
        create.activate();
        create.mListener = r0;
        this.mUserCompletedSuw = create.mDeferredSetupComplete;
    }

    public final boolean isAvailable() {
        if (!this.mDeviceInDemoMode && this.mLaunchOpa.isAvailable() && !this.mUserCompletedSuw && !this.mSettingsAction.isAvailable()) {
            return true;
        }
        return false;
    }

    public final void onProgress(int i, float f) {
        updateFeedbackEffects(i, f);
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.mShadeController.cancelExpansionAndCollapseShade();
        triggerFeedbackEffects(detectionProperties);
        if (!this.mUserCompletedSuw && !this.mSettingsAction.isAvailable()) {
            Intent intent = new Intent();
            intent.setAction("com.google.android.settings.ASSIST_GESTURE_TRAINING");
            intent.setPackage(this.mSettingsPackageName);
            intent.setFlags(268468224);
            this.mContext.startActivityAsUser(intent, UserHandle.of(-2));
        }
    }

    public final void triggerFeedbackEffects(GestureSensor.DetectionProperties detectionProperties) {
        super.triggerFeedbackEffects(detectionProperties);
        this.mLaunchOpa.triggerFeedbackEffects(detectionProperties);
    }

    public final void updateFeedbackEffects(int i, float f) {
        super.updateFeedbackEffects(i, f);
        this.mLaunchOpa.updateFeedbackEffects(i, f);
    }
}
