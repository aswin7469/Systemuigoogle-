package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.Arrays;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DateValidatorPointForward implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator CREATOR = new Object();
    public final long point;

    /* renamed from: com.google.android.material.datepicker.DateValidatorPointForward$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass1 implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            return new DateValidatorPointForward(parcel.readLong());
        }

        public final Object[] newArray(int i) {
            return new DateValidatorPointForward[i];
        }
    }

    public DateValidatorPointForward(long j) {
        this.point = j;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DateValidatorPointForward)) {
            return false;
        }
        if (this.point == ((DateValidatorPointForward) obj).point) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.point)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.point);
    }
}
