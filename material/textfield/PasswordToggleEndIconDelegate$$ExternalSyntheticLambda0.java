package com.google.android.material.textfield;

import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class PasswordToggleEndIconDelegate$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ PasswordToggleEndIconDelegate f$0;

    public /* synthetic */ PasswordToggleEndIconDelegate$$ExternalSyntheticLambda0(PasswordToggleEndIconDelegate passwordToggleEndIconDelegate) {
        this.f$0 = passwordToggleEndIconDelegate;
    }

    public final void onClick(View view) {
        PasswordToggleEndIconDelegate passwordToggleEndIconDelegate = this.f$0;
        EditText editText = passwordToggleEndIconDelegate.editText;
        if (editText != null) {
            int selectionEnd = editText.getSelectionEnd();
            EditText editText2 = passwordToggleEndIconDelegate.editText;
            if (editText2 == null || !(editText2.getTransformationMethod() instanceof PasswordTransformationMethod)) {
                passwordToggleEndIconDelegate.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                passwordToggleEndIconDelegate.editText.setTransformationMethod((TransformationMethod) null);
            }
            if (selectionEnd >= 0) {
                passwordToggleEndIconDelegate.editText.setSelection(selectionEnd);
            }
            passwordToggleEndIconDelegate.refreshIconState();
        }
    }
}
