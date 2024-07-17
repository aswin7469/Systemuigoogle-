package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
