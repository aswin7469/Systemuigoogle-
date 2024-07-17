package com.google.android.systemui.smartspace;

import com.android.systemui.statusbar.policy.ZenModeController;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardZenAlarmViewController$zenModeCallback$1 implements ZenModeController.Callback {
    public final /* synthetic */ KeyguardZenAlarmViewController this$0;

    public KeyguardZenAlarmViewController$zenModeCallback$1(KeyguardZenAlarmViewController keyguardZenAlarmViewController) {
        this.this$0 = keyguardZenAlarmViewController;
    }

    public final void onZenChanged(int i) {
        this.this$0.updateDnd();
    }
}
