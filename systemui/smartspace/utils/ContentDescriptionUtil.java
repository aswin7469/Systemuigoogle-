package com.google.android.systemui.smartspace.utils;

import android.util.Log;
import android.view.View;
import java.util.Arrays;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ContentDescriptionUtil {
    public static final void setFormattedContentDescription(String str, View view, CharSequence charSequence, CharSequence charSequence2) {
        CharSequence charSequence3;
        if (charSequence == null || charSequence.length() == 0) {
            charSequence3 = charSequence2;
        } else if (charSequence2 == null || charSequence2.length() == 0) {
            charSequence3 = charSequence;
        } else {
            charSequence3 = view.getContext().getString(2131952576, new Object[]{charSequence2, charSequence});
        }
        Log.i(str, String.format("setFormattedContentDescription: text=%s, iconDescription=%s, contentDescription=%s", Arrays.copyOf(new Object[]{charSequence, charSequence2, charSequence3}, 3)));
        view.setContentDescription(charSequence3);
    }
}
