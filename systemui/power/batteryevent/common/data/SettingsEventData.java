package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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

    public SettingsEventData(int i) {
        this(new EventData(Integer.valueOf(i)));
    }
}
