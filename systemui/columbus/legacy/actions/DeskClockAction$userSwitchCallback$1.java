package com.google.android.systemui.columbus.legacy.actions;

import android.app.SynchronousUserSwitchObserver;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DeskClockAction$userSwitchCallback$1 extends SynchronousUserSwitchObserver {
    public final /* synthetic */ DeskClockAction this$0;

    public DeskClockAction$userSwitchCallback$1(DeskClockAction deskClockAction) {
        this.this$0 = deskClockAction;
    }

    public final void onUserSwitching(int i) {
        this.this$0.updateBroadcastReceiver();
    }
}
