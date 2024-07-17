package com.google.android.systemui.columbus.legacy.actions;

import com.google.android.systemui.columbus.legacy.gates.Gate;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SilenceCall$gateListener$1 implements Gate.Listener {
    public final /* synthetic */ SilenceCall this$0;

    public SilenceCall$gateListener$1(SilenceCall silenceCall) {
        this.this$0 = silenceCall;
    }

    public final void onGateChanged(Gate gate) {
        this.this$0.updatePhoneStateListener();
    }
}
