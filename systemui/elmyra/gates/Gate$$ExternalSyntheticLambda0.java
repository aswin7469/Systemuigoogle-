package com.google.android.systemui.elmyra.gates;

import com.google.android.systemui.elmyra.gates.Gate;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
