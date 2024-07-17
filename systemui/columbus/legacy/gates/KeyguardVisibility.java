package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import dagger.Lazy;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class KeyguardVisibility extends Gate {
    public final KeyguardVisibility$keyguardMonitorCallback$1 keyguardMonitorCallback = new KeyguardVisibility$keyguardMonitorCallback$1(this);
    public final Lazy keyguardStateController;

    public KeyguardVisibility(Lazy lazy) {
        this.keyguardStateController = lazy;
    }

    public final void onActivate() {
        Lazy lazy = this.keyguardStateController;
        ((KeyguardStateControllerImpl) ((KeyguardStateController) lazy.get())).addCallback(this.keyguardMonitorCallback);
        setBlocking(((KeyguardStateControllerImpl) ((KeyguardStateController) lazy.get())).mShowing);
    }

    public final void onDeactivate() {
        ((KeyguardStateControllerImpl) ((KeyguardStateController) this.keyguardStateController.get())).removeCallback(this.keyguardMonitorCallback);
    }
}
