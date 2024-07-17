package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class KeyguardVisibility$keyguardMonitorCallback$1 implements KeyguardStateController.Callback {
    public final /* synthetic */ KeyguardVisibility this$0;

    public KeyguardVisibility$keyguardMonitorCallback$1(KeyguardVisibility keyguardVisibility) {
        this.this$0 = keyguardVisibility;
    }

    public final void onKeyguardShowingChanged() {
        KeyguardVisibility keyguardVisibility = this.this$0;
        keyguardVisibility.setBlocking(((KeyguardStateControllerImpl) ((KeyguardStateController) keyguardVisibility.keyguardStateController.get())).mShowing);
    }
}
