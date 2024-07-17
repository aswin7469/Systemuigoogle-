package com.google.android.setupdesign.template;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.ProgressBar;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.template.Mixin;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ProgressBarMixin implements Mixin {
    public ColorStateList color;
    public final TemplateLayout templateLayout;
    public final boolean useBottomProgressBar;

    public ProgressBarMixin(TemplateLayout templateLayout2, AttributeSet attributeSet, int i) {
        boolean z;
        this.templateLayout = templateLayout2;
        boolean z2 = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = templateLayout2.getContext().obtainStyledAttributes(attributeSet, R$styleable.SudProgressBarMixin, i, 0);
            if (obtainStyledAttributes.hasValue(0)) {
                z = obtainStyledAttributes.getBoolean(0, false);
            } else {
                z = false;
            }
            obtainStyledAttributes.recycle();
            setShown(false);
            z2 = z;
        }
        this.useBottomProgressBar = z2;
    }

    public final ProgressBar peekProgressBar() {
        int i;
        if (this.useBottomProgressBar) {
            i = 2131363722;
        } else {
            i = 2131363744;
        }
        return (ProgressBar) this.templateLayout.findManagedViewById(i);
    }

    public final void setShown(boolean z) {
        int i;
        boolean z2 = this.useBottomProgressBar;
        if (z) {
            if (peekProgressBar() == null && !z2) {
                ViewStub viewStub = (ViewStub) this.templateLayout.findManagedViewById(2131363746);
                if (viewStub != null) {
                    viewStub.inflate();
                }
                ColorStateList colorStateList = this.color;
                this.color = colorStateList;
                ProgressBar peekProgressBar = peekProgressBar();
                if (peekProgressBar != null) {
                    peekProgressBar.setIndeterminateTintList(colorStateList);
                    peekProgressBar.setProgressBackgroundTintList(colorStateList);
                }
            }
            ProgressBar peekProgressBar2 = peekProgressBar();
            if (peekProgressBar2 != null) {
                peekProgressBar2.setVisibility(0);
                return;
            }
            return;
        }
        ProgressBar peekProgressBar3 = peekProgressBar();
        if (peekProgressBar3 != null) {
            if (z2) {
                i = 4;
            } else {
                i = 8;
            }
            peekProgressBar3.setVisibility(i);
        }
    }
}
