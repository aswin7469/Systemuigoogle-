package com.google.android.material.textfield;

import android.widget.EditText;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class EditTextUtils {
    public static boolean isEditable(EditText editText) {
        if (editText.getInputType() != 0) {
            return true;
        }
        return false;
    }
}
