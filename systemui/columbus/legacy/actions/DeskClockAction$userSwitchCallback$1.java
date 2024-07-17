package com.google.android.systemui.columbus.legacy.actions;

import android.app.SynchronousUserSwitchObserver;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DeskClockAction$userSwitchCallback$1 extends SynchronousUserSwitchObserver {
    public final /* synthetic */ DeskClockAction this$0;

    public DeskClockAction$userSwitchCallback$1(DeskClockAction deskClockAction) {
        this.this$0 = deskClockAction;
    }

    public final void onUserSwitching(int i) {
        this.this$0.updateBroadcastReceiver();
    }
}
