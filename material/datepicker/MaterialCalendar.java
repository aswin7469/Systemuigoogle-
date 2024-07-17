package com.google.android.material.datepicker;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.GridView;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class MaterialCalendar extends PickerFragment {
    static final Object MONTHS_VIEW_GROUP_TAG = "MONTHS_VIEW_GROUP_TAG";
    static final Object NAVIGATION_NEXT_TAG = "NAVIGATION_NEXT_TAG";
    static final Object NAVIGATION_PREV_TAG = "NAVIGATION_PREV_TAG";
    static final Object SELECTOR_TOGGLE_TAG = "SELECTOR_TOGGLE_TAG";
    public CalendarConstraints calendarConstraints;
    public CalendarSelector calendarSelector;
    public CalendarStyle calendarStyle;
    public Month current;
    public View dayFrame;
    public RecyclerView recyclerView;
    public int themeResId;
    public View yearFrame;
    public RecyclerView yearSelector;

    /* renamed from: com.google.android.material.datepicker.MaterialCalendar$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass1 extends AccessibilityDelegateCompat {
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
            accessibilityNodeInfoCompat.setCollectionInfo((AccessibilityNodeInfoCompat.RangeInfoCompat) null);
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    enum CalendarSelector {
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = this.mArguments;
        }
        this.themeResId = bundle.getInt("THEME_RES_ID_KEY");
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(bundle.getParcelable("GRID_SELECTOR_KEY"));
        this.calendarConstraints = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.current = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final int i;
        int i2;
        DaysOfWeekAdapter daysOfWeekAdapter;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.themeResId);
        this.calendarStyle = new CalendarStyle(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month month = this.calendarConstraints.start;
        if (MaterialDatePicker.readMaterialCalendarStyleBoolean(16843277, contextThemeWrapper)) {
            i2 = 2131558815;
            i = 1;
        } else {
            i2 = 2131558810;
            i = 0;
        }
        View inflate = cloneInContext.inflate(i2, viewGroup, false);
        Resources resources = requireContext().getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(2131166831) + resources.getDimensionPixelOffset(2131166833) + resources.getDimensionPixelSize(2131166832);
        int dimensionPixelSize = resources.getDimensionPixelSize(2131166816);
        int i3 = MonthAdapter.MAXIMUM_WEEKS;
        inflate.setMinimumHeight(dimensionPixelOffset + dimensionPixelSize + (resources.getDimensionPixelOffset(2131166830) * (i3 - 1)) + (resources.getDimensionPixelSize(2131166811) * i3) + resources.getDimensionPixelOffset(2131166808));
        GridView gridView = (GridView) inflate.findViewById(2131363146);
        ViewCompat.setAccessibilityDelegate(gridView, new AccessibilityDelegateCompat());
        int i4 = this.calendarConstraints.firstDayOfWeek;
        if (i4 <= 0) {
            daysOfWeekAdapter = new DaysOfWeekAdapter();
        }
        gridView.setAdapter(daysOfWeekAdapter);
        gridView.setNumColumns(month.daysInWeek);
        gridView.setEnabled(false);
        this.recyclerView = (RecyclerView) inflate.findViewById(2131363149);
        getContext();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(i) {
            public final void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
                int i = i;
                MaterialCalendar materialCalendar = MaterialCalendar.this;
                if (i == 0) {
                    iArr[0] = materialCalendar.recyclerView.getWidth();
                    iArr[1] = materialCalendar.recyclerView.getWidth();
                    return;
                }
                iArr[0] = materialCalendar.recyclerView.getHeight();
                iArr[1] = materialCalendar.recyclerView.getHeight();
            }

            public final void smoothScrollToPosition(RecyclerView recyclerView, int i) {
                LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
                linearSmoothScroller.mTargetPosition = i;
                startSmoothScroll(linearSmoothScroller);
            }
        });
        this.recyclerView.setTag(MONTHS_VIEW_GROUP_TAG);
        final MonthsPagerAdapter monthsPagerAdapter = new MonthsPagerAdapter(contextThemeWrapper, this.calendarConstraints, new Object() {
        });
        this.recyclerView.setAdapter(monthsPagerAdapter);
        int integer = contextThemeWrapper.getResources().getInteger(2131427534);
        RecyclerView recyclerView2 = (RecyclerView) inflate.findViewById(2131363152);
        this.yearSelector = recyclerView2;
        if (recyclerView2 != null) {
            recyclerView2.mHasFixedSize = true;
            recyclerView2.setLayoutManager(new GridLayoutManager(integer, 0));
            this.yearSelector.setAdapter(new YearGridAdapter(this));
            this.yearSelector.addItemDecoration(new RecyclerView.ItemDecoration() {
                {
                    UtcDates.getUtcCalendarOf((Calendar) null);
                    UtcDates.getUtcCalendarOf((Calendar) null);
                }

                public final void onDraw(Canvas canvas, RecyclerView recyclerView) {
                    if ((recyclerView.mAdapter instanceof YearGridAdapter) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                        YearGridAdapter yearGridAdapter = (YearGridAdapter) recyclerView.mAdapter;
                        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                        MaterialCalendar.this.getClass();
                        throw null;
                    }
                }
            });
        }
        if (inflate.findViewById(2131363074) != null) {
            final MaterialButton materialButton = (MaterialButton) inflate.findViewById(2131363074);
            materialButton.setTag(SELECTOR_TOGGLE_TAG);
            ViewCompat.setAccessibilityDelegate(materialButton, new AccessibilityDelegateCompat() {
                public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    String str;
                    View.AccessibilityDelegate accessibilityDelegate = this.mOriginalDelegate;
                    AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.mInfo;
                    accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                    MaterialCalendar materialCalendar = MaterialCalendar.this;
                    if (materialCalendar.dayFrame.getVisibility() == 0) {
                        str = materialCalendar.requireContext().getResources().getString(2131953324);
                    } else {
                        str = materialCalendar.requireContext().getResources().getString(2131953322);
                    }
                    accessibilityNodeInfo.setHintText(str);
                }
            });
            MaterialButton materialButton2 = (MaterialButton) inflate.findViewById(2131363076);
            materialButton2.setTag(NAVIGATION_PREV_TAG);
            MaterialButton materialButton3 = (MaterialButton) inflate.findViewById(2131363075);
            materialButton3.setTag(NAVIGATION_NEXT_TAG);
            this.yearFrame = inflate.findViewById(2131363152);
            this.dayFrame = inflate.findViewById(2131363145);
            setSelector(CalendarSelector.DAY);
            materialButton.setText(this.current.getLongName());
            this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                public final void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    if (i == 0) {
                        recyclerView.announceForAccessibility(materialButton.getText());
                    }
                }

                public final void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    int i3;
                    MaterialCalendar materialCalendar = MaterialCalendar.this;
                    if (i < 0) {
                        i3 = ((LinearLayoutManager) materialCalendar.recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    } else {
                        i3 = ((LinearLayoutManager) materialCalendar.recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                    }
                    MonthsPagerAdapter monthsPagerAdapter = monthsPagerAdapter;
                    Calendar dayCopy = UtcDates.getDayCopy(monthsPagerAdapter.calendarConstraints.start.firstOfMonth);
                    dayCopy.add(2, i3);
                    materialCalendar.current = new Month(dayCopy);
                    Calendar dayCopy2 = UtcDates.getDayCopy(monthsPagerAdapter.calendarConstraints.start.firstOfMonth);
                    dayCopy2.add(2, i3);
                    materialButton.setText(new Month(dayCopy2).getLongName());
                }
            });
            materialButton.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    MaterialCalendar materialCalendar = MaterialCalendar.this;
                    CalendarSelector calendarSelector = materialCalendar.calendarSelector;
                    CalendarSelector calendarSelector2 = CalendarSelector.YEAR;
                    CalendarSelector calendarSelector3 = CalendarSelector.DAY;
                    if (calendarSelector == calendarSelector2) {
                        materialCalendar.setSelector(calendarSelector3);
                    } else if (calendarSelector == calendarSelector3) {
                        materialCalendar.setSelector(calendarSelector2);
                    }
                }
            });
            materialButton3.setOnClickListener(new View.OnClickListener(this, 0) {
                public final /* synthetic */ MaterialCalendar this$0;

                {
                    this.this$0 = r1;
                }

                public final void onClick(View view) {
                    switch (1) {
                        case 0:
                            int findFirstVisibleItemPosition = ((LinearLayoutManager) this.this$0.recyclerView.getLayoutManager()).findFirstVisibleItemPosition() + 1;
                            if (findFirstVisibleItemPosition < this.this$0.recyclerView.mAdapter.getItemCount()) {
                                MaterialCalendar materialCalendar = this.this$0;
                                Calendar dayCopy = UtcDates.getDayCopy(monthsPagerAdapter.calendarConstraints.start.firstOfMonth);
                                dayCopy.add(2, findFirstVisibleItemPosition);
                                materialCalendar.setCurrentMonth(new Month(dayCopy));
                                return;
                            }
                            return;
                        default:
                            int findLastVisibleItemPosition = ((LinearLayoutManager) this.this$0.recyclerView.getLayoutManager()).findLastVisibleItemPosition() - 1;
                            if (findLastVisibleItemPosition >= 0) {
                                MaterialCalendar materialCalendar2 = this.this$0;
                                Calendar dayCopy2 = UtcDates.getDayCopy(monthsPagerAdapter.calendarConstraints.start.firstOfMonth);
                                dayCopy2.add(2, findLastVisibleItemPosition);
                                materialCalendar2.setCurrentMonth(new Month(dayCopy2));
                                return;
                            }
                            return;
                    }
                }
            });
            materialButton2.setOnClickListener(new View.OnClickListener(this, 1) {
                public final /* synthetic */ MaterialCalendar this$0;

                {
                    this.this$0 = r1;
                }

                public final void onClick(View view) {
                    switch (1) {
                        case 0:
                            int findFirstVisibleItemPosition = ((LinearLayoutManager) this.this$0.recyclerView.getLayoutManager()).findFirstVisibleItemPosition() + 1;
                            if (findFirstVisibleItemPosition < this.this$0.recyclerView.mAdapter.getItemCount()) {
                                MaterialCalendar materialCalendar = this.this$0;
                                Calendar dayCopy = UtcDates.getDayCopy(monthsPagerAdapter.calendarConstraints.start.firstOfMonth);
                                dayCopy.add(2, findFirstVisibleItemPosition);
                                materialCalendar.setCurrentMonth(new Month(dayCopy));
                                return;
                            }
                            return;
                        default:
                            int findLastVisibleItemPosition = ((LinearLayoutManager) this.this$0.recyclerView.getLayoutManager()).findLastVisibleItemPosition() - 1;
                            if (findLastVisibleItemPosition >= 0) {
                                MaterialCalendar materialCalendar2 = this.this$0;
                                Calendar dayCopy2 = UtcDates.getDayCopy(monthsPagerAdapter.calendarConstraints.start.firstOfMonth);
                                dayCopy2.add(2, findLastVisibleItemPosition);
                                materialCalendar2.setCurrentMonth(new Month(dayCopy2));
                                return;
                            }
                            return;
                    }
                }
            });
        }
        if (!MaterialDatePicker.readMaterialCalendarStyleBoolean(16843277, contextThemeWrapper)) {
            new PagerSnapHelper().attachToRecyclerView(this.recyclerView);
        }
        this.recyclerView.scrollToPosition(monthsPagerAdapter.calendarConstraints.start.monthsUntil(this.current));
        return inflate;
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.themeResId);
        bundle.putParcelable("GRID_SELECTOR_KEY", (Parcelable) null);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.calendarConstraints);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.current);
    }

    public final void setCurrentMonth(Month month) {
        boolean z;
        MonthsPagerAdapter monthsPagerAdapter = (MonthsPagerAdapter) this.recyclerView.mAdapter;
        final int monthsUntil = monthsPagerAdapter.calendarConstraints.start.monthsUntil(month);
        int monthsUntil2 = monthsUntil - monthsPagerAdapter.calendarConstraints.start.monthsUntil(this.current);
        boolean z2 = false;
        if (Math.abs(monthsUntil2) > 3) {
            z = true;
        } else {
            z = false;
        }
        if (monthsUntil2 > 0) {
            z2 = true;
        }
        this.current = month;
        if (z && z2) {
            this.recyclerView.scrollToPosition(monthsUntil - 3);
            this.recyclerView.post(new Runnable() {
                public final void run() {
                    MaterialCalendar.this.recyclerView.smoothScrollToPosition(monthsUntil);
                }
            });
        } else if (z) {
            this.recyclerView.scrollToPosition(monthsUntil + 3);
            this.recyclerView.post(new Runnable() {
                public final void run() {
                    MaterialCalendar.this.recyclerView.smoothScrollToPosition(monthsUntil);
                }
            });
        } else {
            this.recyclerView.post(new Runnable() {
                public final void run() {
                    MaterialCalendar.this.recyclerView.smoothScrollToPosition(monthsUntil);
                }
            });
        }
    }

    public final void setSelector(CalendarSelector calendarSelector2) {
        this.calendarSelector = calendarSelector2;
        if (calendarSelector2 == CalendarSelector.YEAR) {
            this.yearSelector.getLayoutManager().scrollToPosition(this.current.year - ((YearGridAdapter) this.yearSelector.mAdapter).materialCalendar.calendarConstraints.start.year);
            this.yearFrame.setVisibility(0);
            this.dayFrame.setVisibility(8);
        } else if (calendarSelector2 == CalendarSelector.DAY) {
            this.yearFrame.setVisibility(8);
            this.dayFrame.setVisibility(0);
            setCurrentMonth(this.current);
        }
    }
}
