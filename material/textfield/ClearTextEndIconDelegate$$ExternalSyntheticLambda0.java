package com.google.android.material.textfield;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ClearTextEndIconDelegate$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ ClearTextEndIconDelegate f$0;

    public /* synthetic */ ClearTextEndIconDelegate$$ExternalSyntheticLambda0(ClearTextEndIconDelegate clearTextEndIconDelegate) {
        this.f$0 = clearTextEndIconDelegate;
    }

    public final void onClick(View view) {
        ClearTextEndIconDelegate clearTextEndIconDelegate = this.f$0;
        EditText editText = clearTextEndIconDelegate.editText;
        if (editText != null) {
            Editable text = editText.getText();
            if (text != null) {
                text.clear();
            }
            clearTextEndIconDelegate.refreshIconState();
        }
    }
}
