package com.google.android.material.datepicker;

import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearSmoothScroller;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SmoothCalendarLayoutManager$1 extends LinearSmoothScroller {
    public final float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 100.0f / ((float) displayMetrics.densityDpi);
    }
}
