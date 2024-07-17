package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.statusbar.policy.KeyguardStateController;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UserSelectedAction$keyguardMonitorCallback$1 implements KeyguardStateController.Callback {
    public final /* synthetic */ UserSelectedAction this$0;

    public UserSelectedAction$keyguardMonitorCallback$1(UserSelectedAction userSelectedAction) {
        this.this$0 = userSelectedAction;
    }

    public final void onKeyguardShowingChanged() {
        this.this$0.updateAvailable$7();
    }
}
