package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class Month implements Comparable, Parcelable {
    public static final Parcelable.Creator CREATOR = new Object();
    public final int daysInMonth;
    public final int daysInWeek;
    public final Calendar firstOfMonth;
    public String longName;
    public final int month;
    public final long timeInMillis;
    public final int year;

    /* renamed from: com.google.android.material.datepicker.Month$1  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass1 implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            return Month.create(parcel.readInt(), parcel.readInt());
        }

        public final Object[] newArray(int i) {
            return new Month[i];
        }
    }

    public Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar dayCopy = UtcDates.getDayCopy(calendar);
        this.firstOfMonth = dayCopy;
        this.month = dayCopy.get(2);
        this.year = dayCopy.get(1);
        this.daysInWeek = dayCopy.getMaximum(7);
        this.daysInMonth = dayCopy.getActualMaximum(5);
        this.timeInMillis = dayCopy.getTimeInMillis();
    }

    public static Month create(int i, int i2) {
        Calendar utcCalendarOf = UtcDates.getUtcCalendarOf((Calendar) null);
        utcCalendarOf.set(1, i);
        utcCalendarOf.set(2, i2);
        return new Month(utcCalendarOf);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month2 = (Month) obj;
        if (this.month == month2.month && this.year == month2.year) {
            return true;
        }
        return false;
    }

    public final String getLongName() {
        if (this.longName == null) {
            this.longName = DateUtils.formatDateTime((Context) null, this.firstOfMonth.getTimeInMillis(), 8228);
        }
        return this.longName;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.month), Integer.valueOf(this.year)});
    }

    public final int monthsUntil(Month month2) {
        if (this.firstOfMonth instanceof GregorianCalendar) {
            return (month2.month - this.month) + ((month2.year - this.year) * 12);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.year);
        parcel.writeInt(this.month);
    }

    public final int compareTo(Month month2) {
        return this.firstOfMonth.compareTo(month2.firstOfMonth);
    }

    public static Month create(long j) {
        Calendar utcCalendarOf = UtcDates.getUtcCalendarOf((Calendar) null);
        utcCalendarOf.setTimeInMillis(j);
        return new Month(utcCalendarOf);
    }
}
