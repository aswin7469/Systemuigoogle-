package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Calendar;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class MaterialCalendarGridView extends GridView {
    public final boolean nestedScrollable;

    /* renamed from: com.google.android.material.datepicker.MaterialCalendarGridView$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass1 extends AccessibilityDelegateCompat {
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
            accessibilityNodeInfoCompat.setCollectionInfo((AccessibilityNodeInfoCompat.RangeInfoCompat) null);
        }
    }

    public MaterialCalendarGridView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final Adapter getAdapter() {
        return (MonthAdapter) super.getAdapter();
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((MonthAdapter) super.getAdapter()).notifyDataSetChanged();
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        MonthAdapter monthAdapter = (MonthAdapter) super.getAdapter();
        monthAdapter.getClass();
        int max = Math.max(monthAdapter.firstPositionInMonth(), getFirstVisiblePosition());
        int min = Math.min(monthAdapter.lastPositionInMonth(), getLastVisiblePosition());
        monthAdapter.getItem(max);
        monthAdapter.getItem(min);
        throw null;
    }

    public final void onFocusChanged(boolean z, int i, Rect rect) {
        if (!z) {
            super.onFocusChanged(false, i, rect);
        } else if (i == 33) {
            setSelection(((MonthAdapter) super.getAdapter()).lastPositionInMonth());
        } else if (i == 130) {
            setSelection(((MonthAdapter) super.getAdapter()).firstPositionInMonth());
        } else {
            super.onFocusChanged(true, i, rect);
        }
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!super.onKeyDown(i, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= ((MonthAdapter) super.getAdapter()).firstPositionInMonth()) {
            return true;
        }
        if (19 != i) {
            return false;
        }
        setSelection(((MonthAdapter) super.getAdapter()).firstPositionInMonth());
        return true;
    }

    public final void onMeasure(int i, int i2) {
        if (this.nestedScrollable) {
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
            getLayoutParams().height = getMeasuredHeight();
            return;
        }
        super.onMeasure(i, i2);
    }

    public final void setSelection(int i) {
        if (i < ((MonthAdapter) super.getAdapter()).firstPositionInMonth()) {
            super.setSelection(((MonthAdapter) super.getAdapter()).firstPositionInMonth());
        } else {
            super.setSelection(i);
        }
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: getAdapter  reason: collision with other method in class */
    public final ListAdapter m912getAdapter() {
        return (MonthAdapter) super.getAdapter();
    }

    public final void setAdapter(ListAdapter listAdapter) {
        if (listAdapter instanceof MonthAdapter) {
            super.setAdapter(listAdapter);
            return;
        }
        throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", new Object[]{MaterialCalendarGridView.class.getCanonicalName(), MonthAdapter.class.getCanonicalName()}));
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        UtcDates.getUtcCalendarOf((Calendar) null);
        if (MaterialDatePicker.readMaterialCalendarStyleBoolean(16843277, getContext())) {
            setNextFocusLeftId(2131362215);
            setNextFocusRightId(2131362297);
        }
        this.nestedScrollable = MaterialDatePicker.readMaterialCalendarStyleBoolean(2130969661, getContext());
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat());
    }

    /* renamed from: getAdapter  reason: collision with other method in class */
    public final MonthAdapter m913getAdapter() {
        return (MonthAdapter) super.getAdapter();
    }
}
