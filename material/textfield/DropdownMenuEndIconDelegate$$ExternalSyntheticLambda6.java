package com.google.android.material.textfield;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class DropdownMenuEndIconDelegate$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ DropdownMenuEndIconDelegate f$0;

    public /* synthetic */ DropdownMenuEndIconDelegate$$ExternalSyntheticLambda6(DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate) {
        this.f$0 = dropdownMenuEndIconDelegate;
    }

    public final void run() {
        DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate = this.f$0;
        boolean isPopupShowing = dropdownMenuEndIconDelegate.autoCompleteTextView.isPopupShowing();
        dropdownMenuEndIconDelegate.setEndIconChecked(isPopupShowing);
        dropdownMenuEndIconDelegate.dropdownPopupDirty = isPopupShowing;
    }
}
