package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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

    public FrameworkApiEventData(boolean z, boolean z2) {
        this(new EventData(Boolean.valueOf(z)), new EventData(Boolean.valueOf(z2)));
    }
}
