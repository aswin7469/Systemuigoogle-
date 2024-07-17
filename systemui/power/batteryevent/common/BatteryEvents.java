package com.google.android.systemui.power.batteryevent.common;

import androidx.compose.animation.graphics.vector.ObjectAnimator$$ExternalSyntheticOutline0;
import androidx.compose.runtime.Anchor$$ExternalSyntheticOutline0;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatteryEvents {
    public final int batteryLevel;
    public final Set eventTypes;
    public final int pluggedType;

    public BatteryEvents(Set set, int i, int i2) {
        this.eventTypes = set;
        this.batteryLevel = i;
        this.pluggedType = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BatteryEvents)) {
            return false;
        }
        BatteryEvents batteryEvents = (BatteryEvents) obj;
        if (Intrinsics.areEqual(this.eventTypes, batteryEvents.eventTypes) && this.batteryLevel == batteryEvents.batteryLevel && this.pluggedType == batteryEvents.pluggedType) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.pluggedType) + ObjectAnimator$$ExternalSyntheticOutline0.m(this.batteryLevel, this.eventTypes.hashCode() * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BatteryEvents(eventTypes=");
        sb.append(this.eventTypes);
        sb.append(", batteryLevel=");
        sb.append(this.batteryLevel);
        sb.append(", pluggedType=");
        return Anchor$$ExternalSyntheticOutline0.m(sb, this.pluggedType, ")");
    }
}
