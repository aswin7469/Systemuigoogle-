package com.google.android.systemui.power.batteryevent.common.module;

import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.HalDataType;
import com.google.android.systemui.power.batteryevent.common.SettingsDataType;
import com.google.android.systemui.power.batteryevent.common.data.EventData;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.Collections;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DockDefendBatteryModule extends BaseBatteryEventModule {
    public final List getEventDataTypes() {
        return CollectionsKt__CollectionsKt.listOf(HalDataType.GOOGLE_BATTERY_DOCK_DEFEND_STATUS, SettingsDataType.DOCK_DEFENDER_BYPASS);
    }

    public final List getIntentActions() {
        return Collections.singletonList("android.intent.action.BATTERY_CHANGED");
    }

    public final BatteryEventType getModuleType() {
        return BatteryEventType.DOCK_DEFEND_BATTERY;
    }

    public final boolean validate(SystemEventData systemEventData) {
        boolean z;
        EventData eventData = systemEventData.settingsEventData.dockDefenderBypass;
        EventData eventData2 = systemEventData.halEventData.dockDefendStatus;
        EventData eventData3 = systemEventData.plugged;
        boolean z2 = eventData3.isChanged;
        EventData eventData4 = systemEventData.chargingStatus;
        if (z2 || eventData4.isChanged || eventData.isChanged || eventData2.isChanged) {
            int intValue = ((Number) eventData3.value).intValue();
            int intValue2 = ((Number) eventData4.value).intValue();
            int intValue3 = ((Number) eventData.value).intValue();
            int intValue4 = ((Number) eventData2.value).intValue();
            boolean z3 = false;
            if (intValue == 8) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                ExifInterface$$ExternalSyntheticOutline0.m("not DockDefend -> plugged: ", "DockDefendBatteryModule", intValue);
            } else if (intValue2 != 4) {
                ExifInterface$$ExternalSyntheticOutline0.m("not DockDefend -> chargingStatus: ", "DockDefendBatteryModule", intValue2);
            } else if (intValue3 == 1) {
                ExifInterface$$ExternalSyntheticOutline0.m("not DockDefend -> dockDefendBypass: ", "DockDefendBatteryModule", intValue3);
            } else if (intValue4 != 1) {
                ExifInterface$$ExternalSyntheticOutline0.m("not DockDefend -> dockDefendStatus: ", "DockDefendBatteryModule", intValue4);
            } else {
                z3 = true;
            }
            this.lastValidation = z3;
        }
        return this.lastValidation;
    }
}
