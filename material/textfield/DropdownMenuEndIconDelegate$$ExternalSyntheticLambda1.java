package com.google.android.material.textfield;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1 implements View.OnTouchListener {
    public final /* synthetic */ DropdownMenuEndIconDelegate f$0;

    public /* synthetic */ DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1(DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate) {
        this.f$0 = dropdownMenuEndIconDelegate;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate = this.f$0;
        dropdownMenuEndIconDelegate.getClass();
        if (motionEvent.getAction() == 1) {
            long currentTimeMillis = System.currentTimeMillis() - dropdownMenuEndIconDelegate.dropdownPopupActivatedAt;
            if (currentTimeMillis < 0 || currentTimeMillis > 300) {
                dropdownMenuEndIconDelegate.dropdownPopupDirty = false;
            }
            dropdownMenuEndIconDelegate.showHideDropdown();
            dropdownMenuEndIconDelegate.dropdownPopupDirty = true;
            dropdownMenuEndIconDelegate.dropdownPopupActivatedAt = System.currentTimeMillis();
        }
        return false;
    }
}
