package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.statusbar.policy.KeyguardStateController;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class UserSelectedAction$keyguardMonitorCallback$1 implements KeyguardStateController.Callback {
    public final /* synthetic */ UserSelectedAction this$0;

    public UserSelectedAction$keyguardMonitorCallback$1(UserSelectedAction userSelectedAction) {
        this.this$0 = userSelectedAction;
    }

    public final void onKeyguardShowingChanged() {
        this.this$0.updateAvailable$7();
    }
}
