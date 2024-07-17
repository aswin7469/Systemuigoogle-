package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HalEventData {
    public final EventData dockDefendStatus;
    public final EventData dwellDefendEventData;
    public final EventData tempDefendEventData;

    public HalEventData(EventData eventData, EventData eventData2, EventData eventData3) {
        this.dockDefendStatus = eventData;
        this.tempDefendEventData = eventData2;
        this.dwellDefendEventData = eventData3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HalEventData)) {
            return false;
        }
        HalEventData halEventData = (HalEventData) obj;
        if (Intrinsics.areEqual(this.dockDefendStatus, halEventData.dockDefendStatus) && Intrinsics.areEqual(this.tempDefendEventData, halEventData.tempDefendEventData) && Intrinsics.areEqual(this.dwellDefendEventData, halEventData.dwellDefendEventData)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.tempDefendEventData.hashCode();
        return this.dwellDefendEventData.hashCode() + ((hashCode + (this.dockDefendStatus.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "HalEventData(dockDefendStatus=" + this.dockDefendStatus + ", tempDefendEventData=" + this.tempDefendEventData + ", dwellDefendEventData=" + this.dwellDefendEventData + ")";
    }
}
