package com.google.android.setupdesign.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class CheckableLinearLayout extends LinearLayout implements Checkable {
    public boolean checked = false;

    public CheckableLinearLayout(Context context) {
        super(context);
        setFocusable(true);
    }

    public final boolean isChecked() {
        return this.checked;
    }

    public final int[] onCreateDrawableState(int i) {
        if (this.checked) {
            return LinearLayout.mergeDrawableStates(super.onCreateDrawableState(i + 1), new int[]{16842912});
        }
        return super.onCreateDrawableState(i);
    }

    public final void setChecked(boolean z) {
        this.checked = z;
        refreshDrawableState();
    }

    public final void toggle() {
        setChecked(!this.checked);
    }

    public CheckableLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFocusable(true);
    }

    public CheckableLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setFocusable(true);
    }

    public CheckableLinearLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setFocusable(true);
    }
}
