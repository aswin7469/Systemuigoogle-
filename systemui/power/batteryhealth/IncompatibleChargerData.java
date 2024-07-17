package com.google.android.systemui.power.batteryhealth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import com.google.android.systemui.power.DumpUtils;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class IncompatibleChargerData implements Parcelable {
    public static final CREATOR CREATOR = new Object();
    public final boolean isIncompatibleCharger;
    public final long lastCompatibleChargerTime;
    public final long lastIncompatibleChargerTime;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class CREATOR implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            return new IncompatibleChargerData(parcel.readLong(), parcel.readLong(), parcel.readBoolean());
        }

        public final Object[] newArray(int i) {
            return new IncompatibleChargerData[i];
        }
    }

    public IncompatibleChargerData(long j, long j2, boolean z) {
        this.isIncompatibleCharger = z;
        this.lastCompatibleChargerTime = j;
        this.lastIncompatibleChargerTime = j2;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IncompatibleChargerData)) {
            return false;
        }
        IncompatibleChargerData incompatibleChargerData = (IncompatibleChargerData) obj;
        if (this.isIncompatibleCharger == incompatibleChargerData.isIncompatibleCharger && this.lastCompatibleChargerTime == incompatibleChargerData.lastCompatibleChargerTime && this.lastIncompatibleChargerTime == incompatibleChargerData.lastIncompatibleChargerTime) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.lastIncompatibleChargerTime) + Scale$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.isIncompatibleCharger) * 31, 31, this.lastCompatibleChargerTime);
    }

    public final String toString() {
        boolean z = this.isIncompatibleCharger;
        String readableDateTime = DumpUtils.toReadableDateTime(this.lastCompatibleChargerTime);
        String readableDateTime2 = DumpUtils.toReadableDateTime(this.lastIncompatibleChargerTime);
        return "isIncompatibleCharger: " + z + ", lastCompatibleChargerTime: " + readableDateTime + ", lastIncompatibleChargerTime: " + readableDateTime2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this.isIncompatibleCharger);
        parcel.writeLong(this.lastCompatibleChargerTime);
        parcel.writeLong(this.lastIncompatibleChargerTime);
    }
}
