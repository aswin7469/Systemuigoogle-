package com.google.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.domain.autoaddable.CallbackControllerAutoAddable;
import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.statusbar.policy.BatteryController;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ReverseChargingAutoAddable extends CallbackControllerAutoAddable {
    public final String description;
    public final TileSpec spec = TileSpec.Companion.create("reverse");

    public ReverseChargingAutoAddable(BatteryController batteryController) {
        super(batteryController);
        AutoAddTracking autoAddTracking = getAutoAddTracking();
        this.description = "ReverseChargingAutoAddable (" + autoAddTracking + ")";
    }

    public final Object getCallback(ProducerScope producerScope) {
        return new ReverseChargingAutoAddable$getCallback$1(this, producerScope);
    }

    public final String getDescription() {
        return this.description;
    }

    public final TileSpec getSpec() {
        return this.spec;
    }
}
