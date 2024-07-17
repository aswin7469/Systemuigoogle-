package com.google.android.material.textfield;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
