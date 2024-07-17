package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.util.sensors.ProximitySensor;
import com.android.systemui.util.sensors.ProximitySensorImpl;
import com.android.systemui.util.sensors.ThresholdSensor;
import com.android.systemui.util.sensors.ThresholdSensorEvent;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
