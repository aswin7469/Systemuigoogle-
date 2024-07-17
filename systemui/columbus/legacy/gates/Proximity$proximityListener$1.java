package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.util.sensors.ProximitySensor;
import com.android.systemui.util.sensors.ProximitySensorImpl;
import com.android.systemui.util.sensors.ThresholdSensor;
import com.android.systemui.util.sensors.ThresholdSensorEvent;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class Proximity$proximityListener$1 implements ThresholdSensor.Listener {
    public final /* synthetic */ Proximity this$0;

    public Proximity$proximityListener$1(Proximity proximity) {
        this.this$0 = proximity;
    }

    public final void onThresholdCrossed(ThresholdSensorEvent thresholdSensorEvent) {
        Proximity proximity = this.this$0;
        proximity.setBlocking(Intrinsics.areEqual(((ProximitySensorImpl) ((ProximitySensor) proximity.proximitySensor.get())).isNear(), Boolean.TRUE));
    }
}
