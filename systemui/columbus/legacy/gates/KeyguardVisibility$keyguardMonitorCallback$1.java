package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
