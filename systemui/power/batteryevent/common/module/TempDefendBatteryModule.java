package com.google.android.systemui.power.batteryevent.common.module;

import android.util.Log;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.HalDataType;
import com.google.android.systemui.power.batteryevent.common.data.EventData;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TempDefendBatteryModule extends BaseBatteryEventModule {
    public final List getEventDataTypes() {
        return Collections.singletonList(HalDataType.GOOGLE_BATTERY_DOCK_DEFEND_STATUS);
    }

    public final List getIntentActions() {
        return Collections.singletonList("android.intent.action.BATTERY_CHANGED");
    }

    public final BatteryEventType getModuleType() {
        return BatteryEventType.TEMP_DEFEND_BATTERY;
    }

    public final boolean validate(SystemEventData systemEventData) {
        boolean z;
        EventData eventData = systemEventData.halEventData.dockDefendStatus;
        EventData eventData2 = systemEventData.chargingStatus;
        boolean z2 = eventData2.isChanged;
        Object obj = eventData2.value;
        if (z2 || eventData.isChanged) {
            boolean z3 = false;
            if (((Number) obj).intValue() == 4) {
                z = true;
            } else {
                z = false;
            }
            if (((Number) eventData.value).intValue() != 1 && z) {
                z3 = true;
            }
            this.lastValidation = z3;
        }
        Log.d("TempDefendBatteryModule", "validated: " + this.lastValidation + " dockDefendStatus:" + eventData + ", chargingStatus: " + obj);
        return this.lastValidation;
    }
}
