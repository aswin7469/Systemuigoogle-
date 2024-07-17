package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.util.sensors.ProximitySensor;
import com.android.systemui.util.sensors.ProximitySensorImpl;
import dagger.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
