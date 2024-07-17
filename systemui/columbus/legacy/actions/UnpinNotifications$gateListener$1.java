package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.google.android.systemui.columbus.legacy.gates.Gate;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UnpinNotifications$gateListener$1 implements Gate.Listener {
    public final /* synthetic */ UnpinNotifications this$0;

    public UnpinNotifications$gateListener$1(UnpinNotifications unpinNotifications) {
        this.this$0 = unpinNotifications;
    }

    public final void onGateChanged(Gate gate) {
        boolean z;
        UnpinNotifications unpinNotifications = this.this$0;
        boolean isBlocking = unpinNotifications.silenceAlertsDisabled.isBlocking();
        UnpinNotifications$headsUpChangedListener$1 unpinNotifications$headsUpChangedListener$1 = unpinNotifications.headsUpChangedListener;
        HeadsUpManager headsUpManager = unpinNotifications.headsUpManager;
        if (!isBlocking) {
            if (headsUpManager != null) {
                ((BaseHeadsUpManager) headsUpManager).addListener(unpinNotifications$headsUpChangedListener$1);
            }
            if (headsUpManager != null) {
                z = ((BaseHeadsUpManager) headsUpManager).mHasPinnedNotification;
            } else {
                z = false;
            }
            unpinNotifications.hasPinnedHeadsUp = z;
        } else if (headsUpManager != null) {
            ((BaseHeadsUpManager) headsUpManager).mListeners.remove(unpinNotifications$headsUpChangedListener$1);
        }
    }
}
