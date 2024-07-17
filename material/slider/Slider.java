package com.google.android.material.slider;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class Slider extends BaseSlider {
    public Slider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842788});
        if (obtainStyledAttributes.hasValue(0)) {
            setValues(Float.valueOf(obtainStyledAttributes.getFloat(0, 0.0f)));
        }
        obtainStyledAttributes.recycle();
    }

    public final CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final float getValueFrom() {
        return this.valueFrom;
    }

    public final float getValueTo() {
        return this.valueTo;
    }

    public final boolean pickActiveThumb() {
        if (this.activeThumbIdx != -1) {
            return true;
        }
        this.activeThumbIdx = 0;
        return true;
    }
}
