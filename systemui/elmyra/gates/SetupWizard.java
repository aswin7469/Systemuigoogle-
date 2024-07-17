package com.google.android.systemui.elmyra.gates;

import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.google.android.systemui.elmyra.actions.SettingsAction;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SetupWizard extends Gate {
    public final DeviceProvisionedController mProvisionedController;
    public final AnonymousClass1 mProvisionedListener = new DeviceProvisionedController.DeviceProvisionedListener() {
        public final void onDeviceProvisionedChanged() {
            boolean z;
            SetupWizard setupWizard = SetupWizard.this;
            DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) setupWizard.mProvisionedController;
            if (!deviceProvisionedControllerImpl.deviceProvisioned.get() || !deviceProvisionedControllerImpl.isCurrentUserSetup()) {
                z = false;
            } else {
                z = true;
            }
            if (z != setupWizard.mSetupComplete) {
                setupWizard.mSetupComplete = z;
                setupWizard.notifyListener();
            }
        }

        public final void onUserSetupChanged() {
            boolean z;
            SetupWizard setupWizard = SetupWizard.this;
            DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) setupWizard.mProvisionedController;
            if (!deviceProvisionedControllerImpl.deviceProvisioned.get() || !deviceProvisionedControllerImpl.isCurrentUserSetup()) {
                z = false;
            } else {
                z = true;
            }
            if (z != setupWizard.mSetupComplete) {
                setupWizard.mSetupComplete = z;
                setupWizard.notifyListener();
            }
        }
    };
    public final SettingsAction mSettingsAction;
    public boolean mSetupComplete;

    public SetupWizard(Executor executor, DeviceProvisionedController deviceProvisionedController, SettingsAction settingsAction) {
        super(executor);
        this.mSettingsAction = settingsAction;
        this.mProvisionedController = deviceProvisionedController;
    }

    public final boolean isBlocked() {
        if (this.mSettingsAction.isAvailable()) {
            return false;
        }
        return !this.mSetupComplete;
    }

    public final void onActivate() {
        boolean z;
        DeviceProvisionedController deviceProvisionedController = this.mProvisionedController;
        DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) deviceProvisionedController;
        if (!deviceProvisionedControllerImpl.deviceProvisioned.get() || !deviceProvisionedControllerImpl.isCurrentUserSetup()) {
            z = false;
        } else {
            z = true;
        }
        this.mSetupComplete = z;
        ((DeviceProvisionedControllerImpl) deviceProvisionedController).addCallback(this.mProvisionedListener);
    }

    public final void onDeactivate() {
        ((DeviceProvisionedControllerImpl) this.mProvisionedController).removeCallback(this.mProvisionedListener);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [isDeviceProvisioned -> ");
        DeviceProvisionedController deviceProvisionedController = this.mProvisionedController;
        sb.append(((DeviceProvisionedControllerImpl) deviceProvisionedController).deviceProvisioned.get());
        sb.append("; isCurrentUserSetup -> ");
        sb.append(((DeviceProvisionedControllerImpl) deviceProvisionedController).isCurrentUserSetup());
        sb.append("]");
        return sb.toString();
    }
}
