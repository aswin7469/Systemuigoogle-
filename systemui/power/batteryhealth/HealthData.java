package com.google.android.systemui.power.batteryhealth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.animation.graphics.vector.ObjectAnimator$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMapping$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class HealthData implements Parcelable {
    public static final CREATOR CREATOR = new Object();
    public final int capacityIndex;
    public final int healthIndex;
    public final int healthStatus;
    public final int performanceIndex;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class CREATOR implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            return new HealthData(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public final Object[] newArray(int i) {
            return new HealthData[i];
        }
    }

    public HealthData(int i, int i2, int i3, int i4) {
        this.healthIndex = i;
        this.performanceIndex = i2;
        this.capacityIndex = i3;
        this.healthStatus = i4;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HealthData)) {
            return false;
        }
        HealthData healthData = (HealthData) obj;
        if (this.healthIndex == healthData.healthIndex && this.performanceIndex == healthData.performanceIndex && this.capacityIndex == healthData.capacityIndex && this.healthStatus == healthData.healthStatus) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.healthStatus) + ObjectAnimator$$ExternalSyntheticOutline0.m(this.capacityIndex, ObjectAnimator$$ExternalSyntheticOutline0.m(this.performanceIndex, Integer.hashCode(this.healthIndex) * 31, 31), 31);
    }

    public final String toString() {
        int i = this.healthIndex;
        int i2 = this.performanceIndex;
        int i3 = this.capacityIndex;
        int i4 = this.healthStatus;
        StringBuilder m = ValidatingOffsetMapping$$ExternalSyntheticOutline0.m("hi: ", i, ", pi: ", i2, ", ci: ");
        m.append(i3);
        m.append(", hs: ");
        m.append(i4);
        return m.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.healthIndex);
        parcel.writeInt(this.performanceIndex);
        parcel.writeInt(this.capacityIndex);
        parcel.writeInt(this.healthStatus);
    }
}
