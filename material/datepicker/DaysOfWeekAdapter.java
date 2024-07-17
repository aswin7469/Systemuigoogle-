package com.google.android.material.datepicker;

import android.widget.BaseAdapter;
import java.util.Calendar;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DaysOfWeekAdapter extends BaseAdapter {
    public final Calendar calendar;
    public final int daysInWeek;
    public final int firstDayOfWeek;

    public DaysOfWeekAdapter() {
        Calendar utcCalendarOf = UtcDates.getUtcCalendarOf((Calendar) null);
        this.calendar = utcCalendarOf;
        this.daysInWeek = utcCalendarOf.getMaximum(7);
        this.firstDayOfWeek = utcCalendarOf.getFirstDayOfWeek();
    }

    public final int getCount() {
        return this.daysInWeek;
    }

    public final Object getItem(int i) {
        int i2 = this.daysInWeek;
        if (i >= i2) {
            return null;
        }
        int i3 = i + this.firstDayOfWeek;
        if (i3 > i2) {
            i3 -= i2;
        }
        return Integer.valueOf(i3);
    }

    public final long getItemId(int i) {
        return 0;
    }

    /* JADX WARNING: type inference failed for: r5v7, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View getView(int r4, android.view.View r5, android.view.ViewGroup r6) {
        /*
            r3 = this;
            r0 = r5
            android.widget.TextView r0 = (android.widget.TextView) r0
            if (r5 != 0) goto L_0x0018
            android.content.Context r5 = r6.getContext()
            android.view.LayoutInflater r5 = android.view.LayoutInflater.from(r5)
            r0 = 2131558801(0x7f0d0191, float:1.8742928E38)
            r1 = 0
            android.view.View r5 = r5.inflate(r0, r6, r1)
            r0 = r5
            android.widget.TextView r0 = (android.widget.TextView) r0
        L_0x0018:
            java.util.Calendar r5 = r3.calendar
            int r1 = r3.firstDayOfWeek
            int r4 = r4 + r1
            int r1 = r3.daysInWeek
            if (r4 <= r1) goto L_0x0022
            int r4 = r4 - r1
        L_0x0022:
            r1 = 7
            r5.set(r1, r4)
            android.content.res.Resources r4 = r0.getResources()
            android.content.res.Configuration r4 = r4.getConfiguration()
            java.util.Locale r4 = r4.locale
            java.util.Calendar r5 = r3.calendar
            r2 = 4
            java.lang.String r4 = r5.getDisplayName(r1, r2, r4)
            r0.setText(r4)
            android.content.Context r4 = r6.getContext()
            r5 = 2131953261(0x7f13066d, float:1.9542988E38)
            java.lang.String r4 = r4.getString(r5)
            java.util.Calendar r3 = r3.calendar
            r5 = 2
            java.util.Locale r6 = java.util.Locale.getDefault()
            java.lang.String r3 = r3.getDisplayName(r1, r5, r6)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r0.setContentDescription(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.DaysOfWeekAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public DaysOfWeekAdapter(int i) {
        Calendar utcCalendarOf = UtcDates.getUtcCalendarOf((Calendar) null);
        this.calendar = utcCalendarOf;
        this.daysInWeek = utcCalendarOf.getMaximum(7);
        this.firstDayOfWeek = i;
    }
}
