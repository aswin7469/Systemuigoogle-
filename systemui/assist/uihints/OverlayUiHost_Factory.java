package com.google.android.systemui.assist.uihints;

import android.content.Context;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class OverlayUiHost_Factory implements Provider {
    public static OverlayUiHost newInstance(Context context) {
        return new OverlayUiHost(context);
    }
}
