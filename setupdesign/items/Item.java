package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class Item extends AbstractItem {
    public final boolean visible;

    public Item(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudItem);
        obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.getText(4);
        obtainStyledAttributes.getText(5);
        obtainStyledAttributes.getText(6);
        obtainStyledAttributes.getResourceId(2, getDefaultLayoutResource());
        obtainStyledAttributes.getBoolean(3, true);
        obtainStyledAttributes.getColor(8, 0);
        obtainStyledAttributes.getInt(7, 16);
        obtainStyledAttributes.recycle();
    }

    public int getDefaultLayoutResource() {
        return 2131559122;
    }
}
