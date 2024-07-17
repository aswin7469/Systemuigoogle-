package com.google.android.systemui.elmyra.gates;

import com.google.android.systemui.elmyra.gates.Gate;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class Gate$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Gate f$0;

    public /* synthetic */ Gate$$ExternalSyntheticLambda0(Gate gate) {
        this.f$0 = gate;
    }

    public final void run() {
        Gate gate = this.f$0;
        Gate.Listener listener = gate.mListener;
        if (listener != null) {
            listener.onGateChanged(gate);
        }
    }
}
