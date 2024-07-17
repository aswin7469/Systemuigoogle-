package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Object();
    public final Month end;
    public final int firstDayOfWeek;
    public final int monthSpan;
    public final Month openAt;
    public final Month start;
    public final DateValidator validator;
    public final int yearSpan;

    /* renamed from: com.google.android.material.datepicker.CalendarConstraints$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass1 implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), parcel.readInt());
        }

        public final Object[] newArray(int i) {
            return new CalendarConstraints[i];
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class Builder {
        public static final /* synthetic */ int $r8$clinit = 0;
        public static final long DEFAULT_END = 0;
        public static final long DEFAULT_START = 0;
        public Long openAt;

        static {
            long j = Month.create(1900, 0).timeInMillis;
            Calendar utcCalendarOf = UtcDates.getUtcCalendarOf((Calendar) null);
            utcCalendarOf.setTimeInMillis(j);
            UtcDates.getDayCopy(utcCalendarOf).getTimeInMillis();
            long j2 = Month.create(2100, 11).timeInMillis;
            Calendar utcCalendarOf2 = UtcDates.getUtcCalendarOf((Calendar) null);
            utcCalendarOf2.setTimeInMillis(j2);
            UtcDates.getDayCopy(utcCalendarOf2).getTimeInMillis();
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public interface DateValidator extends Parcelable {
    }

    public CalendarConstraints(Month month, Month month2, DateValidator dateValidator, Month month3, int i) {
        this.start = month;
        this.end = month2;
        this.openAt = month3;
        this.firstDayOfWeek = i;
        this.validator = dateValidator;
        if (month3 != null && month.firstOfMonth.compareTo(month3.firstOfMonth) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        } else if (month3 != null && month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        } else if (i < 0 || i > UtcDates.getUtcCalendarOf((Calendar) null).getMaximum(7)) {
            throw new IllegalArgumentException("firstDayOfWeek is not valid");
        } else {
            this.monthSpan = month.monthsUntil(month2) + 1;
            this.yearSpan = (month2.year - month.year) + 1;
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarConstraints)) {
            return false;
        }
        CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
        if (!this.start.equals(calendarConstraints.start) || !this.end.equals(calendarConstraints.end) || !Objects.equals(this.openAt, calendarConstraints.openAt) || this.firstDayOfWeek != calendarConstraints.firstDayOfWeek || !this.validator.equals(calendarConstraints.validator)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.start, this.end, this.openAt, Integer.valueOf(this.firstDayOfWeek), this.validator});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.start, 0);
        parcel.writeParcelable(this.end, 0);
        parcel.writeParcelable(this.openAt, 0);
        parcel.writeParcelable(this.validator, 0);
        parcel.writeInt(this.firstDayOfWeek);
    }
}
