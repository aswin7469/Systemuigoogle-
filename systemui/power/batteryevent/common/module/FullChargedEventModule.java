package com.google.android.systemui.power.batteryevent.common.module;

import com.android.settingslib.fuelgauge.BatteryStatus;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.data.EventData;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.Collections;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class FullChargedEventModule extends BaseBatteryEventModule {
    public final List getEventDataTypes() {
        return EmptyList.INSTANCE;
    }

    public final List getIntentActions() {
        return Collections.singletonList("android.intent.action.BATTERY_CHANGED");
    }

    public final BatteryEventType getModuleType() {
        return BatteryEventType.FULL_CHARGED;
    }

    public final boolean validate(SystemEventData systemEventData) {
        boolean z;
        EventData eventData = systemEventData.plugged;
        boolean z2 = eventData.isChanged;
        EventData eventData2 = systemEventData.batteryStatus;
        EventData eventData3 = systemEventData.batteryScale;
        EventData eventData4 = systemEventData.batteryLevel;
        if (z2 || eventData2.isChanged || eventData3.isChanged || eventData4.isChanged) {
            int intValue = ((Number) eventData.value).intValue();
            int intValue2 = ((Number) eventData2.value).intValue();
            int intValue3 = ((Number) eventData3.value).intValue();
            int intValue4 = ((Number) eventData4.value).intValue();
            if (!BatteryStatus.isPluggedIn(intValue) || !BatteryStatus.isCharged(intValue2, BatteryStatus.getBatteryLevel(intValue4, intValue3))) {
                z = false;
            } else {
                z = true;
            }
            this.lastValidation = z;
        }
        return this.lastValidation;
    }
}
