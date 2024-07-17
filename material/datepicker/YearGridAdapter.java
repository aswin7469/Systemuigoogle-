package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Locale;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class YearGridAdapter extends RecyclerView.Adapter {
    public final MaterialCalendar materialCalendar;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public ViewHolder(TextView textView2) {
            super(textView2);
            this.textView = textView2;
        }
    }

    public YearGridAdapter(MaterialCalendar materialCalendar2) {
        this.materialCalendar = materialCalendar2;
    }

    public final int getItemCount() {
        return this.materialCalendar.calendarConstraints.yearSpan;
    }

    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        MaterialCalendar materialCalendar2 = this.materialCalendar;
        int i2 = materialCalendar2.calendarConstraints.start.year + i;
        TextView textView = ((ViewHolder) viewHolder).textView;
        String string = textView.getContext().getString(2131953307);
        textView.setText(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i2)}));
        textView.setContentDescription(String.format(string, new Object[]{Integer.valueOf(i2)}));
        CalendarStyle calendarStyle = materialCalendar2.calendarStyle;
        if (UtcDates.getTodayCalendar().get(1) == i2) {
            CalendarItemStyle calendarItemStyle = calendarStyle.todayYear;
        } else {
            CalendarItemStyle calendarItemStyle2 = calendarStyle.year;
        }
        throw null;
    }

    public final RecyclerView.ViewHolder onCreateViewHolder(RecyclerView recyclerView, int i) {
        return new ViewHolder((TextView) LayoutInflater.from(recyclerView.getContext()).inflate(2131558816, recyclerView, false));
    }
}
