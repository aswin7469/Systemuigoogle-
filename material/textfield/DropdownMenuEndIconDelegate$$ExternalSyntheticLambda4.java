package com.google.android.material.textfield;

import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
