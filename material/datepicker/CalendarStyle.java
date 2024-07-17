package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.google.android.material.R$styleable;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CalendarStyle {
    public final CalendarItemStyle invalidDay;
    public final CalendarItemStyle todayYear;
    public final CalendarItemStyle year;

    public CalendarStyle(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(MaterialAttributes.resolveTypedValueOrThrow(context, MaterialCalendar.class.getCanonicalName(), 2130969537).data, R$styleable.MaterialCalendar);
        CalendarItemStyle.create(obtainStyledAttributes.getResourceId(3, 0), context);
        this.invalidDay = CalendarItemStyle.create(obtainStyledAttributes.getResourceId(1, 0), context);
        CalendarItemStyle.create(obtainStyledAttributes.getResourceId(2, 0), context);
        CalendarItemStyle.create(obtainStyledAttributes.getResourceId(4, 0), context);
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, 6);
        this.year = CalendarItemStyle.create(obtainStyledAttributes.getResourceId(8, 0), context);
        CalendarItemStyle.create(obtainStyledAttributes.getResourceId(7, 0), context);
        this.todayYear = CalendarItemStyle.create(obtainStyledAttributes.getResourceId(9, 0), context);
        new Paint().setColor(colorStateList.getDefaultColor());
        obtainStyledAttributes.recycle();
    }
}
