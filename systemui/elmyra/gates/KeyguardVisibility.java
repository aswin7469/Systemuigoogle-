package com.google.android.systemui.elmyra.gates;

import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardVisibility extends Gate {
    public final AnonymousClass1 mKeyguardMonitorCallback = new KeyguardStateController.Callback() {
        public final void onKeyguardShowingChanged() {
            KeyguardVisibility.this.notifyListener();
        }
    };
    public final KeyguardStateController mKeyguardStateController;

    public KeyguardVisibility(Executor executor, KeyguardStateController keyguardStateController) {
        super(executor);
        this.mKeyguardStateController = keyguardStateController;
    }

    public final boolean isBlocked() {
        return ((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing;
    }

    public final void onActivate() {
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(this.mKeyguardMonitorCallback);
    }

    public final void onDeactivate() {
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).removeCallback(this.mKeyguardMonitorCallback);
    }
}
