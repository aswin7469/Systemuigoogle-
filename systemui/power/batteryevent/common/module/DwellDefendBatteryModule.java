package com.google.android.systemui.power.batteryevent.common.module;

import android.util.Log;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.HalDataType;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DwellDefendBatteryModule extends BaseBatteryEventModule {
    public final List getEventDataTypes() {
        return Collections.singletonList(HalDataType.GOOGLE_BATTERY_DWELL_DEFEND_STATUS);
    }

    public final List getIntentActions() {
        return Collections.singletonList("android.intent.action.BATTERY_CHANGED");
    }

    public final BatteryEventType getModuleType() {
        return BatteryEventType.DWELL_DEFEND_BATTERY;
    }

    public final boolean validate(SystemEventData systemEventData) {
        boolean booleanValue = ((Boolean) systemEventData.halEventData.dwellDefendEventData.value).booleanValue();
        boolean z = this.lastValidation;
        Log.d("DwellDefendBatteryModule", "validate: " + z + " -> " + booleanValue);
        this.lastValidation = booleanValue;
        return booleanValue;
    }
}
