package com.google.android.setupcompat.util;

import android.os.Build;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class BuildCompatUtils {
    public static boolean isAtLeastU() {
        String str = Build.VERSION.CODENAME;
        if (!str.equals("REL") && (str.equals("REL") || str.compareTo("UpsideDownCake") < 0)) {
            return false;
        }
        return true;
    }
}
