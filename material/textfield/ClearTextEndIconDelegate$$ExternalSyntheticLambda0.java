package com.google.android.material.textfield;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
