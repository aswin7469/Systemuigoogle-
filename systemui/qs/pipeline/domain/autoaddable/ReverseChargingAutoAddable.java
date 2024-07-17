package com.google.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.domain.autoaddable.CallbackControllerAutoAddable;
import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.statusbar.policy.BatteryController;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ReverseChargingAutoAddable extends CallbackControllerAutoAddable {
    public final String description;
    public final TileSpec spec = TileSpec.Companion.create("reverse");

    public ReverseChargingAutoAddable(BatteryController batteryController) {
        super(batteryController);
        AutoAddTracking autoAddTracking = getAutoAddTracking();
        this.description = "ReverseChargingAutoAddable (" + autoAddTracking + ")";
    }

    public final String getDescription() {
        return this.description;
    }

    public final TileSpec getSpec() {
        return this.spec;
    }
}
