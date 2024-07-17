package com.google.android.setupdesign.template;

import android.os.Handler;
import android.os.Looper;
import com.google.android.setupcompat.template.Mixin;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class RequireScrollMixin implements Mixin {
    public final boolean everScrolledToBottom;
    public final Handler handler;
    public final boolean requiringScrollToBottom;

    public RequireScrollMixin() {
        new Handler(Looper.getMainLooper());
    }
}
