package com.google.android.systemui.power.batteryevent.common.module;

import com.android.settingslib.fuelgauge.BatteryStatus;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.data.EventData;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.Collections;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LowBatteryEventModule extends BaseBatteryEventModule {
    public final List getEventDataTypes() {
        return EmptyList.INSTANCE;
    }

    public final List getIntentActions() {
        return Collections.singletonList("android.intent.action.BATTERY_CHANGED");
    }

    public final BatteryEventType getModuleType() {
        return BatteryEventType.LOW_BATTERY;
    }

    public final boolean validate(SystemEventData systemEventData) {
        boolean z;
        EventData eventData = systemEventData.batteryLevel;
        boolean z2 = eventData.isChanged;
        EventData eventData2 = systemEventData.batteryScale;
        EventData eventData3 = systemEventData.plugged;
        if (z2 || eventData2.isChanged || eventData3.isChanged) {
            int batteryLevel = BatteryStatus.getBatteryLevel(((Number) eventData.value).intValue(), ((Number) eventData2.value).intValue());
            if (batteryLevel > 20 || BatteryStatus.isPluggedIn(((Number) eventData3.value).intValue()) || batteryLevel <= 10 || batteryLevel <= 3) {
                z = false;
            } else {
                z = true;
            }
            this.lastValidation = z;
        }
        return this.lastValidation;
    }
}
