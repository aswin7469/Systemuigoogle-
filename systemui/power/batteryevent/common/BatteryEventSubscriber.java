package com.google.android.systemui.power.batteryevent.common;

import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatteryEventSubscriber {
    public final List actions;
    public final BatteryEventType batteryEventType;
    public final List eventDataType;

    public BatteryEventSubscriber(BatteryEventType batteryEventType2, List list, List list2) {
        this.batteryEventType = batteryEventType2;
        this.actions = list;
        this.eventDataType = list2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000a, code lost:
        r5 = (com.google.android.systemui.power.batteryevent.common.BatteryEventSubscriber) r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.systemui.power.batteryevent.common.BatteryEventSubscriber
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.google.android.systemui.power.batteryevent.common.BatteryEventSubscriber r5 = (com.google.android.systemui.power.batteryevent.common.BatteryEventSubscriber) r5
            com.google.android.systemui.power.batteryevent.aidl.BatteryEventType r1 = r5.batteryEventType
            com.google.android.systemui.power.batteryevent.aidl.BatteryEventType r3 = r4.batteryEventType
            if (r3 == r1) goto L_0x0013
            return r2
        L_0x0013:
            java.util.List r1 = r4.actions
            java.util.List r3 = r5.actions
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r3)
            if (r1 != 0) goto L_0x001e
            return r2
        L_0x001e:
            java.util.List r4 = r4.eventDataType
            java.util.List r5 = r5.eventDataType
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 != 0) goto L_0x0029
            return r2
        L_0x0029:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.common.BatteryEventSubscriber.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int hashCode = this.actions.hashCode();
        return this.eventDataType.hashCode() + ((hashCode + (this.batteryEventType.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "BatteryEventSubscriber(batteryEventType=" + this.batteryEventType + ", actions=" + this.actions + ", eventDataType=" + this.eventDataType + ")";
    }
}
