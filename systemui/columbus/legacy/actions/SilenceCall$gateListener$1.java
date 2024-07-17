package com.google.android.systemui.columbus.legacy.actions;

import com.google.android.systemui.columbus.legacy.gates.Gate;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SilenceCall$gateListener$1 implements Gate.Listener {
    public final /* synthetic */ SilenceCall this$0;

    public SilenceCall$gateListener$1(SilenceCall silenceCall) {
        this.this$0 = silenceCall;
    }

    public final void onGateChanged(Gate gate) {
        this.this$0.updatePhoneStateListener();
    }
}
