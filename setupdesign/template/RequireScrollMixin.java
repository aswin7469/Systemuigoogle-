package com.google.android.setupdesign.template;

import android.os.Handler;
import android.os.Looper;
import com.google.android.setupcompat.template.Mixin;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class RequireScrollMixin implements Mixin {
    public final boolean everScrolledToBottom;
    public final Handler handler;
    public final boolean requiringScrollToBottom;

    public RequireScrollMixin() {
        new Handler(Looper.getMainLooper());
    }
}
