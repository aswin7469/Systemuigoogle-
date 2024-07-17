package com.google.android.setupdesign.util;

import android.content.Context;
import com.google.android.setupcompat.util.Logger;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ThemeHelper {
    public static final Logger LOG = new Logger("ThemeHelper");

    public static String colorIntToHex(int i, Context context) {
        return String.format("#%06X", new Object[]{Integer.valueOf(context.getResources().getColor(i) & 16777215)});
    }
}
