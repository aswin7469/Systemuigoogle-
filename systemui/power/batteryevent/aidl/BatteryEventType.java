package com.google.android.systemui.power.batteryevent.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public enum BatteryEventType implements Parcelable {
    UNKNOWN(0),
    FULL_CHARGED(1),
    LOW_BATTERY(3),
    EXTREME_LOW_BATTERY(4),
    SEVERE_LOW_BATTERY(5),
    REGULAR_CHARGING(6),
    FAST_CHARGING(7),
    SLOW_CHARGING(8),
    NOT_CHARGING(10),
    TEMP_DEFEND_BATTERY(11),
    DWELL_DEFEND_BATTERY(12),
    DOCK_DEFEND_BATTERY(13),
    WIRED_INCOMPATIBLE_CHARGING(14);
    
    public static final CREATOR CREATOR = null;
    private final String typeName;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class CREATOR implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            String str;
            BatteryEventType batteryEventType = null;
            if (parcel != null) {
                str = parcel.readString();
            } else {
                str = null;
            }
            if (str == null) {
                Log.w("BatteryEventType", "null parameter for createFromParcel");
                return BatteryEventType.UNKNOWN;
            }
            BatteryEventType[] values = BatteryEventType.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                BatteryEventType batteryEventType2 = values[i];
                if (Intrinsics.areEqual(batteryEventType2.getTypeName(), str)) {
                    batteryEventType = batteryEventType2;
                    break;
                }
                i++;
            }
            if (batteryEventType == null) {
                return BatteryEventType.UNKNOWN;
            }
            return batteryEventType;
        }

        public final Object[] newArray(int i) {
            return new BatteryEventType[i];
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, com.google.android.systemui.power.batteryevent.aidl.BatteryEventType$CREATOR] */
    static {
        BatteryEventType[] batteryEventTypeArr;
        EnumEntriesKt.enumEntries(batteryEventTypeArr);
        CREATOR = new Object();
    }

    /* access modifiers changed from: public */
    BatteryEventType(int i) {
        this.typeName = r2;
    }

    public final int describeContents() {
        return 0;
    }

    public final String getTypeName() {
        return this.typeName;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.typeName);
    }
}
