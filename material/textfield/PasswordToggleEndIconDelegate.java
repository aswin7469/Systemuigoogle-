package com.google.android.material.textfield;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class PasswordToggleEndIconDelegate extends EndIconDelegate {
    public EditText editText;
    public final int iconResId = 2131232351;
    public final PasswordToggleEndIconDelegate$$ExternalSyntheticLambda0 onIconClickListener = new PasswordToggleEndIconDelegate$$ExternalSyntheticLambda0(this);

    public PasswordToggleEndIconDelegate(EndCompoundLayout endCompoundLayout, int i) {
        super(endCompoundLayout);
        if (i != 0) {
            this.iconResId = i;
        }
    }

    public final void beforeEditTextChanged() {
        refreshIconState();
    }

    public final int getIconContentDescriptionResId() {
        return 2131953447;
    }

    public final int getIconDrawableResId() {
        return this.iconResId;
    }

    public final View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    public final boolean isIconCheckable() {
        return true;
    }

    public final boolean isIconChecked() {
        boolean z;
        EditText editText2 = this.editText;
        if (editText2 == null || !(editText2.getTransformationMethod() instanceof PasswordTransformationMethod)) {
            z = false;
        } else {
            z = true;
        }
        return !z;
    }

    public final void onEditTextAttached(EditText editText2) {
        this.editText = editText2;
        refreshIconState();
    }

    public final void setUp() {
        EditText editText2 = this.editText;
        if (editText2 == null) {
            return;
        }
        if (editText2.getInputType() == 16 || editText2.getInputType() == 128 || editText2.getInputType() == 144 || editText2.getInputType() == 224) {
            this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public final void tearDown() {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
