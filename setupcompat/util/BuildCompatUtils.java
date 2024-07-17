package com.google.android.setupcompat.util;

import android.os.Build;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class BuildCompatUtils {
    public static boolean isAtLeastU() {
        String str = Build.VERSION.CODENAME;
        if (!str.equals("REL") && (str.equals("REL") || str.compareTo("UpsideDownCake") < 0)) {
            return false;
        }
        return true;
    }
}
