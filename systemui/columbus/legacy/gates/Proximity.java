package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.util.sensors.ProximitySensor;
import com.android.systemui.util.sensors.ProximitySensorImpl;
import dagger.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class Proximity extends Gate {
    public final Proximity$proximityListener$1 proximityListener = new Proximity$proximityListener$1(this);
    public final Lazy proximitySensor;

    public Proximity(Lazy lazy) {
        this.proximitySensor = lazy;
    }

    public final void onActivate() {
        Lazy lazy = this.proximitySensor;
        ((ProximitySensor) lazy.get()).register(this.proximityListener);
        setBlocking(Intrinsics.areEqual(((ProximitySensorImpl) ((ProximitySensor) lazy.get())).isNear(), Boolean.TRUE));
    }

    public final void onDeactivate() {
        ((ProximitySensor) this.proximitySensor.get()).unregister(this.proximityListener);
    }
}
