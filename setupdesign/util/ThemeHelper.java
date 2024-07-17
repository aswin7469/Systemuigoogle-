package com.google.android.setupdesign.util;

import android.content.Context;
import com.google.android.setupcompat.util.Logger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ThemeHelper {
    public static final Logger LOG = new Logger("ThemeHelper");

    public static String colorIntToHex(int i, Context context) {
        return String.format("#%06X", new Object[]{Integer.valueOf(context.getResources().getColor(i) & 16777215)});
    }
}
