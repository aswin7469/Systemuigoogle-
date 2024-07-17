package com.google.android.material.textfield;

import android.widget.AutoCompleteTextView;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class DropdownMenuEndIconDelegate$$ExternalSyntheticLambda2 implements AutoCompleteTextView.OnDismissListener {
    public final /* synthetic */ DropdownMenuEndIconDelegate f$0;

    public /* synthetic */ DropdownMenuEndIconDelegate$$ExternalSyntheticLambda2(DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate) {
        this.f$0 = dropdownMenuEndIconDelegate;
    }

    public final void onDismiss() {
        DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate = this.f$0;
        dropdownMenuEndIconDelegate.dropdownPopupDirty = true;
        dropdownMenuEndIconDelegate.dropdownPopupActivatedAt = System.currentTimeMillis();
        dropdownMenuEndIconDelegate.setEndIconChecked(false);
    }
}
