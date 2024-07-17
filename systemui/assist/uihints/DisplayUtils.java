package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class DisplayUtils {
    public static Display getDefaultDisplay(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }
}
