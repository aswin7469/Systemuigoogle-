package com.google.android.material.textfield;

import android.view.View;
import com.google.android.material.internal.CheckableImageButton;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CustomEndIconDelegate extends EndIconDelegate {
    public final void setUp() {
        EndCompoundLayout endCompoundLayout = this.endLayout;
        endCompoundLayout.endIconOnLongClickListener = null;
        CheckableImageButton checkableImageButton = endCompoundLayout.endIconView;
        checkableImageButton.setOnLongClickListener((View.OnLongClickListener) null);
        IconHelper.setIconClickable(checkableImageButton, (View.OnLongClickListener) null);
    }
}
