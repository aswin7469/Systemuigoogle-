package com.google.android.material.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.resources.MaterialAttributes;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class MaterialTextView extends AppCompatTextView {
    public static int readFirstAvailableDimension(Context context, TypedArray typedArray, int... iArr) {
        int i = -1;
        for (int i2 = 0; i2 < iArr.length && i < 0; i2++) {
            int i3 = iArr[i2];
            TypedValue typedValue = new TypedValue();
            if (!typedArray.getValue(i3, typedValue) || typedValue.type != 2) {
                i = typedArray.getDimensionPixelSize(i3, -1);
            } else {
                TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
                obtainStyledAttributes.recycle();
                i = dimensionPixelSize;
            }
        }
        return i;
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        TypedValue resolve = MaterialAttributes.resolve(2130970195, context);
        if (resolve == null || resolve.type != 18 || resolve.data != 0) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(i, R$styleable.MaterialTextAppearance);
            int readFirstAvailableDimension = readFirstAvailableDimension(getContext(), obtainStyledAttributes, 1, 2);
            obtainStyledAttributes.recycle();
            if (readFirstAvailableDimension >= 0) {
                TextViewCompat.setLineHeight(this, readFirstAvailableDimension);
            }
        }
    }
}
