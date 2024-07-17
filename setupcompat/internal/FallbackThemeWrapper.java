package com.google.android.setupcompat.internal;

import android.content.res.Resources;
import android.view.ContextThemeWrapper;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class FallbackThemeWrapper extends ContextThemeWrapper {
    public final void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(i, false);
    }
}
