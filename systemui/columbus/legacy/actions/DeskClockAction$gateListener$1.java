package com.google.android.systemui.columbus.legacy.actions;

import com.google.android.systemui.columbus.legacy.gates.Gate;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DeskClockAction$gateListener$1 implements Gate.Listener {
    public final /* synthetic */ DeskClockAction this$0;

    public DeskClockAction$gateListener$1(DeskClockAction deskClockAction) {
        this.this$0 = deskClockAction;
    }

    public final void onGateChanged(Gate gate) {
        this.this$0.updateBroadcastReceiver();
    }
}
