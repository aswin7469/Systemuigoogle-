package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class FrameworkApiEventData {
    public final EventData batterySaverState;
    public final EventData extremeBatterySaverState;

    public FrameworkApiEventData(EventData eventData, EventData eventData2) {
        this.batterySaverState = eventData;
        this.extremeBatterySaverState = eventData2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FrameworkApiEventData)) {
            return false;
        }
        FrameworkApiEventData frameworkApiEventData = (FrameworkApiEventData) obj;
        if (Intrinsics.areEqual(this.batterySaverState, frameworkApiEventData.batterySaverState) && Intrinsics.areEqual(this.extremeBatterySaverState, frameworkApiEventData.extremeBatterySaverState)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.extremeBatterySaverState.hashCode() + (this.batterySaverState.hashCode() * 31);
    }

    public final String toString() {
        return "FrameworkApiEventData(batterySaverState=" + this.batterySaverState + ", extremeBatterySaverState=" + this.extremeBatterySaverState + ")";
    }
}
