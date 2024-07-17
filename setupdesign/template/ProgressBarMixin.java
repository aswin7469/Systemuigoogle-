package com.google.android.setupdesign.template;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.template.Mixin;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ProgressBarMixin implements Mixin {
    public final TemplateLayout templateLayout;
    public final boolean useBottomProgressBar;

    public ProgressBarMixin(TemplateLayout templateLayout2, AttributeSet attributeSet, int i) {
        int i2;
        this.templateLayout = templateLayout2;
        boolean z = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = templateLayout2.getContext().obtainStyledAttributes(attributeSet, R$styleable.SudProgressBarMixin, i, 0);
            z = obtainStyledAttributes.hasValue(0) ? obtainStyledAttributes.getBoolean(0, false) : z;
            obtainStyledAttributes.recycle();
            boolean z2 = this.useBottomProgressBar;
            ProgressBar peekProgressBar = peekProgressBar();
            if (peekProgressBar != null) {
                if (z2) {
                    i2 = 4;
                } else {
                    i2 = 8;
                }
                peekProgressBar.setVisibility(i2);
            }
        }
        this.useBottomProgressBar = z;
    }

    public final ProgressBar peekProgressBar() {
        int i;
        if (this.useBottomProgressBar) {
            i = 2131363756;
        } else {
            i = 2131363778;
        }
        return (ProgressBar) this.templateLayout.findViewById(i);
    }
}
