package com.google.android.systemui.smartspace;

import com.android.systemui.statusbar.policy.ZenModeController;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class KeyguardZenAlarmViewController$zenModeCallback$1 implements ZenModeController.Callback {
    public final /* synthetic */ KeyguardZenAlarmViewController this$0;

    public KeyguardZenAlarmViewController$zenModeCallback$1(KeyguardZenAlarmViewController keyguardZenAlarmViewController) {
        this.this$0 = keyguardZenAlarmViewController;
    }

    public final void onZenChanged(int i) {
        this.this$0.updateDnd();
    }
}
