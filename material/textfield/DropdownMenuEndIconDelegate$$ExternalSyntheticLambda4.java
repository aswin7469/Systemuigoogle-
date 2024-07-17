package com.google.android.material.textfield;

import android.view.View;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class DropdownMenuEndIconDelegate$$ExternalSyntheticLambda4 implements View.OnFocusChangeListener {
    public final /* synthetic */ DropdownMenuEndIconDelegate f$0;

    public /* synthetic */ DropdownMenuEndIconDelegate$$ExternalSyntheticLambda4(DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate) {
        this.f$0 = dropdownMenuEndIconDelegate;
    }

    public final void onFocusChange(View view, boolean z) {
        DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate = this.f$0;
        dropdownMenuEndIconDelegate.editTextHasFocus = z;
        dropdownMenuEndIconDelegate.refreshIconState();
        if (!z) {
            dropdownMenuEndIconDelegate.setEndIconChecked(false);
            dropdownMenuEndIconDelegate.dropdownPopupDirty = false;
        }
    }
}
