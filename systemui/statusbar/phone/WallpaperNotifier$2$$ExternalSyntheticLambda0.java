package com.google.android.systemui.statusbar.phone;

import com.google.android.systemui.statusbar.phone.WallpaperNotifier;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class WallpaperNotifier$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ WallpaperNotifier.AnonymousClass2 f$0;

    public /* synthetic */ WallpaperNotifier$2$$ExternalSyntheticLambda0(WallpaperNotifier.AnonymousClass2 r1) {
        this.f$0 = r1;
    }

    public final void run() {
        WallpaperNotifier.this.checkNotificationBroadcastSupport();
    }
}
