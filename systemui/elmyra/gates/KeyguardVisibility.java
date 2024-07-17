package com.google.android.systemui.elmyra.gates;

import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
