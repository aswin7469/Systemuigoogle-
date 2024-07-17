package com.google.android.material.textfield;

import android.widget.EditText;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class EditTextUtils {
    public static boolean isEditable(EditText editText) {
        if (editText.getInputType() != 0) {
            return true;
        }
        return false;
    }
}
