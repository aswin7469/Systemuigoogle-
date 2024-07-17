package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class EventData {
    public boolean isChanged = true;
    public final Object value;

    public EventData(Object obj) {
        this.value = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof EventData) && Intrinsics.areEqual(this.value, ((EventData) obj).value)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.value;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        return "EventData(value=" + this.value + ")";
    }
}
