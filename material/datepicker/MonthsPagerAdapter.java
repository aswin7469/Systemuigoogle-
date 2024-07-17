package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.MaterialCalendar;
import java.util.Calendar;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class MonthsPagerAdapter extends RecyclerView.Adapter {
    public final CalendarConstraints calendarConstraints;
    public final int itemHeight;
    public final MaterialCalendar.AnonymousClass3 onDayClickListener;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        public final MaterialCalendarGridView monthGrid;
        public final TextView monthTitle;

        public ViewHolder(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(2131363050);
            this.monthTitle = textView;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            new ViewCompat.AnonymousClass1(2131363774, 3).set(textView, Boolean.TRUE);
            this.monthGrid = (MaterialCalendarGridView) linearLayout.findViewById(2131363045);
            if (!z) {
                textView.setVisibility(8);
            }
        }
    }

    public MonthsPagerAdapter(Context context, CalendarConstraints calendarConstraints2, MaterialCalendar.AnonymousClass3 r6) {
        int i;
        Month month = calendarConstraints2.start;
        Month month2 = calendarConstraints2.end;
        Month month3 = calendarConstraints2.openAt;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        } else if (month3.compareTo(month2) <= 0) {
            int i2 = MonthAdapter.MAXIMUM_WEEKS;
            Object obj = MaterialCalendar.MONTHS_VIEW_GROUP_TAG;
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(2131166769) * i2;
            if (MaterialDatePicker.readMaterialCalendarStyleBoolean(16843277, context)) {
                i = context.getResources().getDimensionPixelSize(2131166769);
            } else {
                i = 0;
            }
            this.itemHeight = dimensionPixelSize + i;
            this.calendarConstraints = calendarConstraints2;
            this.onDayClickListener = r6;
            setHasStableIds(true);
        } else {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
    }

    public final int getItemCount() {
        return this.calendarConstraints.monthSpan;
    }

    public final long getItemId(int i) {
        Calendar dayCopy = UtcDates.getDayCopy(this.calendarConstraints.start.firstOfMonth);
        dayCopy.add(2, i);
        return new Month(dayCopy).firstOfMonth.getTimeInMillis();
    }

    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        CalendarConstraints calendarConstraints2 = this.calendarConstraints;
        Calendar dayCopy = UtcDates.getDayCopy(calendarConstraints2.start.firstOfMonth);
        dayCopy.add(2, i);
        Month month = new Month(dayCopy);
        viewHolder2.monthTitle.setText(month.getLongName());
        final MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) viewHolder2.monthGrid.findViewById(2131363045);
        if (materialCalendarGridView.getAdapter() == null || !month.equals(materialCalendarGridView.getAdapter().month)) {
            new MonthAdapter(month, calendarConstraints2);
            throw null;
        }
        materialCalendarGridView.invalidate();
        MonthAdapter adapter = materialCalendarGridView.getAdapter();
        for (Long longValue : adapter.previouslySelectedDates) {
            long longValue2 = longValue.longValue();
            if (Month.create(longValue2).equals(adapter.month)) {
                Calendar dayCopy2 = UtcDates.getDayCopy(adapter.month.firstOfMonth);
                dayCopy2.setTimeInMillis(longValue2);
                int i2 = dayCopy2.get(5);
                adapter.updateSelectedState((TextView) materialCalendarGridView.getChildAt((materialCalendarGridView.getAdapter().firstPositionInMonth() + (i2 - 1)) - materialCalendarGridView.getFirstVisiblePosition()), longValue2);
            }
        }
        materialCalendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                MonthAdapter adapter = materialCalendarGridView.getAdapter();
                if (i >= adapter.firstPositionInMonth() && i <= adapter.lastPositionInMonth()) {
                    if (materialCalendarGridView.getAdapter().getItem(i).longValue() >= ((DateValidatorPointForward) MaterialCalendar.this.calendarConstraints.validator).point) {
                        throw null;
                    }
                }
            }
        });
    }

    public final RecyclerView.ViewHolder onCreateViewHolder(int i, RecyclerView recyclerView) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(recyclerView.getContext()).inflate(2131558805, recyclerView, false);
        if (!MaterialDatePicker.readMaterialCalendarStyleBoolean(16843277, recyclerView.getContext())) {
            return new ViewHolder(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, this.itemHeight));
        return new ViewHolder(linearLayout, true);
    }
}
