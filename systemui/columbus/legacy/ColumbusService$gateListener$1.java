package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.legacy.gates.Gate;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColumbusService$gateListener$1 implements Gate.Listener {
    public final /* synthetic */ ColumbusService this$0;

    public ColumbusService$gateListener$1(ColumbusService columbusService) {
        this.this$0 = columbusService;
    }

    public final void onGateChanged(Gate gate) {
        this.this$0.updateSensorListener();
    }
}
