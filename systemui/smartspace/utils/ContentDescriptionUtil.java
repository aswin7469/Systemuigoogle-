package com.google.android.systemui.smartspace.utils;

import android.util.Log;
import android.view.View;
import java.util.Arrays;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ContentDescriptionUtil {
    public static final void setFormattedContentDescription(String str, View view, CharSequence charSequence, CharSequence charSequence2) {
        CharSequence charSequence3;
        if (charSequence == null || charSequence.length() == 0) {
            charSequence3 = charSequence2;
        } else if (charSequence2 == null || charSequence2.length() == 0) {
            charSequence3 = charSequence;
        } else {
            charSequence3 = view.getContext().getString(2131952601, new Object[]{charSequence2, charSequence});
        }
        Log.i(str, String.format("setFormattedContentDescription: text=%s, iconDescription=%s, contentDescription=%s", Arrays.copyOf(new Object[]{charSequence, charSequence2, charSequence3}, 3)));
        view.setContentDescription(charSequence3);
    }
}
