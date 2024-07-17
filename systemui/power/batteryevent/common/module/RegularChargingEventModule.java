package com.google.android.systemui.power.batteryevent.common.module;

import android.content.Context;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import java.util.Collections;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class RegularChargingEventModule extends BaseBatteryEventModule {
    public final Context context;

    public RegularChargingEventModule(Context context2) {
        this.context = context2;
    }

    public final List getEventDataTypes() {
        return EmptyList.INSTANCE;
    }

    public final List getIntentActions() {
        return Collections.singletonList("android.intent.action.BATTERY_CHANGED");
    }

    public final BatteryEventType getModuleType() {
        return BatteryEventType.REGULAR_CHARGING;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0037, code lost:
        if (com.android.settingslib.fuelgauge.BatteryStatus.calculateChargingSpeed(r3.context, ((java.lang.Number) r2.value).intValue(), ((java.lang.Number) r4.value).intValue()) == 1) goto L_0x003b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean validate(com.google.android.systemui.power.batteryevent.common.data.SystemEventData r4) {
        /*
            r3 = this;
            com.google.android.systemui.power.batteryevent.common.data.EventData r0 = r4.plugged
            boolean r1 = r0.isChanged
            com.google.android.systemui.power.batteryevent.common.data.EventData r2 = r4.maxChargingCurrent
            com.google.android.systemui.power.batteryevent.common.data.EventData r4 = r4.maxChargingVoltage
            if (r1 != 0) goto L_0x0012
            boolean r1 = r2.isChanged
            if (r1 != 0) goto L_0x0012
            boolean r1 = r4.isChanged
            if (r1 == 0) goto L_0x003d
        L_0x0012:
            java.lang.Object r0 = r0.value
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            boolean r0 = com.android.settingslib.fuelgauge.BatteryStatus.isPluggedIn(r0)
            if (r0 == 0) goto L_0x003a
            java.lang.Object r0 = r2.value
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            java.lang.Object r4 = r4.value
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            android.content.Context r1 = r3.context
            int r4 = com.android.settingslib.fuelgauge.BatteryStatus.calculateChargingSpeed(r1, r0, r4)
            r0 = 1
            if (r4 != r0) goto L_0x003a
            goto L_0x003b
        L_0x003a:
            r0 = 0
        L_0x003b:
            r3.lastValidation = r0
        L_0x003d:
            boolean r3 = r3.lastValidation
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.common.module.RegularChargingEventModule.validate(com.google.android.systemui.power.batteryevent.common.data.SystemEventData):boolean");
    }
}
