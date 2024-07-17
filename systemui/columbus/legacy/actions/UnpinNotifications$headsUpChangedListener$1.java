package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UnpinNotifications$headsUpChangedListener$1 implements OnHeadsUpChangedListener {
    public final /* synthetic */ UnpinNotifications this$0;

    public UnpinNotifications$headsUpChangedListener$1(UnpinNotifications unpinNotifications) {
        this.this$0 = unpinNotifications;
    }

    public final void onHeadsUpPinnedModeChanged(boolean z) {
        boolean z2;
        UnpinNotifications unpinNotifications = this.this$0;
        unpinNotifications.hasPinnedHeadsUp = z;
        if (unpinNotifications.silenceAlertsDisabled.isBlocking() || !unpinNotifications.hasPinnedHeadsUp) {
            z2 = false;
        } else {
            z2 = true;
        }
        unpinNotifications.setAvailable(z2);
    }
}
