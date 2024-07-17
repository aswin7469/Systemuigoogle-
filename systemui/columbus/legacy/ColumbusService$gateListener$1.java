package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.legacy.gates.Gate;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusService$gateListener$1 implements Gate.Listener {
    public final /* synthetic */ ColumbusService this$0;

    public ColumbusService$gateListener$1(ColumbusService columbusService) {
        this.this$0 = columbusService;
    }

    public final void onGateChanged(Gate gate) {
        this.this$0.updateSensorListener();
    }
}
