package com.google.android.systemui.power.batteryevent.common.module;

import android.content.Context;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.data.EventData;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.Collections;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SlowChargingEventModule extends BaseBatteryEventModule {
    public final Context context;

    public SlowChargingEventModule(Context context2) {
        this.context = context2;
    }

    public final List getEventDataTypes() {
        return EmptyList.INSTANCE;
    }

    public final List getIntentActions() {
        return Collections.singletonList("android.intent.action.BATTERY_CHANGED");
    }

    public final BatteryEventType getModuleType() {
        return BatteryEventType.SLOW_CHARGING;
    }

    public final boolean validate(SystemEventData systemEventData) {
        boolean z;
        EventData eventData = systemEventData.plugged;
        boolean z2 = eventData.isChanged;
        EventData eventData2 = systemEventData.maxChargingCurrent;
        EventData eventData3 = systemEventData.maxChargingVoltage;
        if (z2 || eventData2.isChanged || eventData3.isChanged) {
            if (BatteryStatus.isPluggedIn(((Number) eventData.value).intValue())) {
                if (BatteryStatus.calculateChargingSpeed(this.context, ((Number) eventData2.value).intValue(), ((Number) eventData3.value).intValue()) == 0) {
                    z = true;
                    this.lastValidation = z;
                }
            }
            z = false;
            this.lastValidation = z;
        }
        return this.lastValidation;
    }
}
