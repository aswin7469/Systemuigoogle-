package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class HalEventData {
    public final EventData dockDefendStatus;

    public HalEventData(EventData eventData) {
        this.dockDefendStatus = eventData;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof HalEventData) && Intrinsics.areEqual(this.dockDefendStatus, ((HalEventData) obj).dockDefendStatus)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.dockDefendStatus.hashCode();
    }

    public final String toString() {
        return "HalEventData(dockDefendStatus=" + this.dockDefendStatus + ")";
    }

    public HalEventData(int i) {
        this(new EventData(Integer.valueOf(i)));
    }
}
