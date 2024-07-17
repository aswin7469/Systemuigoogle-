package com.google.android.setupcompat.internal;

import android.content.res.Resources;
import android.view.ContextThemeWrapper;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class FallbackThemeWrapper extends ContextThemeWrapper {
    public final void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(i, false);
    }
}
