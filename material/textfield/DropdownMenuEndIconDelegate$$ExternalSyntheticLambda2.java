package com.google.android.material.textfield;

import android.widget.AutoCompleteTextView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
