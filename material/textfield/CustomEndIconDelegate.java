package com.google.android.material.textfield;

import android.view.View;
import com.google.android.material.internal.CheckableImageButton;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CustomEndIconDelegate extends EndIconDelegate {
    public final void setUp() {
        EndCompoundLayout endCompoundLayout = this.endLayout;
        endCompoundLayout.endIconOnLongClickListener = null;
        CheckableImageButton checkableImageButton = endCompoundLayout.endIconView;
        checkableImageButton.setOnLongClickListener((View.OnLongClickListener) null);
        IconHelper.setIconClickable(checkableImageButton, (View.OnLongClickListener) null);
    }
}
