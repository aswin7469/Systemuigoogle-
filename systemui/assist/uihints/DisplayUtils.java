package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class DisplayUtils {
    public static Display getDefaultDisplay(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }
}
