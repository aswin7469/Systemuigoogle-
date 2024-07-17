package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SettingsEventData {
    public final EventData dockDefenderBypass;

    public SettingsEventData(EventData eventData) {
        this.dockDefenderBypass = eventData;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof SettingsEventData) && Intrinsics.areEqual(this.dockDefenderBypass, ((SettingsEventData) obj).dockDefenderBypass)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.dockDefenderBypass.hashCode();
    }

    public final String toString() {
        return "SettingsEventData(dockDefenderBypass=" + this.dockDefenderBypass + ")";
    }
}
