package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SystemEventData {
    public final EventData batteryLevel;
    public final EventData batteryScale;
    public final EventData batteryStatus;
    public final EventData chargingStatus;
    public final FrameworkApiEventData frameworkApiEventData;
    public final HalEventData halEventData;
    public final String intentAction;
    public final EventData maxChargingCurrent;
    public final EventData maxChargingVoltage;
    public final EventData plugged;
    public final SettingsEventData settingsEventData;

    public SystemEventData(String str, EventData eventData, EventData eventData2, EventData eventData3, EventData eventData4, EventData eventData5, EventData eventData6, EventData eventData7, HalEventData halEventData2, SettingsEventData settingsEventData2, FrameworkApiEventData frameworkApiEventData2) {
        this.intentAction = str;
        this.plugged = eventData;
        this.batteryScale = eventData2;
        this.batteryLevel = eventData3;
        this.chargingStatus = eventData4;
        this.maxChargingCurrent = eventData5;
        this.maxChargingVoltage = eventData6;
        this.batteryStatus = eventData7;
        this.halEventData = halEventData2;
        this.settingsEventData = settingsEventData2;
        this.frameworkApiEventData = frameworkApiEventData2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SystemEventData)) {
            return false;
        }
        SystemEventData systemEventData = (SystemEventData) obj;
        if (Intrinsics.areEqual(this.intentAction, systemEventData.intentAction) && Intrinsics.areEqual(this.plugged, systemEventData.plugged) && Intrinsics.areEqual(this.batteryScale, systemEventData.batteryScale) && Intrinsics.areEqual(this.batteryLevel, systemEventData.batteryLevel) && Intrinsics.areEqual(this.chargingStatus, systemEventData.chargingStatus) && Intrinsics.areEqual(this.maxChargingCurrent, systemEventData.maxChargingCurrent) && Intrinsics.areEqual(this.maxChargingVoltage, systemEventData.maxChargingVoltage) && Intrinsics.areEqual(this.batteryStatus, systemEventData.batteryStatus) && Intrinsics.areEqual(this.halEventData, systemEventData.halEventData) && Intrinsics.areEqual(this.settingsEventData, systemEventData.settingsEventData) && Intrinsics.areEqual(this.frameworkApiEventData, systemEventData.frameworkApiEventData)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.plugged.hashCode();
        int hashCode2 = this.batteryScale.hashCode();
        int hashCode3 = this.batteryLevel.hashCode();
        int hashCode4 = this.chargingStatus.hashCode();
        int hashCode5 = this.maxChargingCurrent.hashCode();
        int hashCode6 = this.maxChargingVoltage.hashCode();
        int hashCode7 = this.batteryStatus.hashCode();
        int hashCode8 = this.halEventData.hashCode();
        int hashCode9 = this.settingsEventData.dockDefenderBypass.hashCode();
        return this.frameworkApiEventData.hashCode() + ((hashCode9 + ((hashCode8 + ((hashCode7 + ((hashCode6 + ((hashCode5 + ((hashCode4 + ((hashCode3 + ((hashCode2 + ((hashCode + (this.intentAction.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "SystemEventData(intentAction=" + this.intentAction + ", plugged=" + this.plugged + ", batteryScale=" + this.batteryScale + ", batteryLevel=" + this.batteryLevel + ", chargingStatus=" + this.chargingStatus + ", maxChargingCurrent=" + this.maxChargingCurrent + ", maxChargingVoltage=" + this.maxChargingVoltage + ", batteryStatus=" + this.batteryStatus + ", halEventData=" + this.halEventData + ", settingsEventData=" + this.settingsEventData + ", frameworkApiEventData=" + this.frameworkApiEventData + ")";
    }
}
