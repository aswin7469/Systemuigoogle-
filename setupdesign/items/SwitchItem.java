package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class SwitchItem extends Item implements CompoundButton.OnCheckedChangeListener {
    public SwitchItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudSwitchItem);
        obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
    }

    public int getDefaultLayoutResource() {
        return 2131559126;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
    }
}
