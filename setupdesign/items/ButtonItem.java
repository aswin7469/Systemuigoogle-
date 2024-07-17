package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class ButtonItem extends AbstractItem implements View.OnClickListener {
    public ButtonItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudButtonItem);
        obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.getText(3);
        obtainStyledAttributes.getResourceId(0, 2132017775);
        obtainStyledAttributes.recycle();
    }

    public final void onClick(View view) {
    }
}
