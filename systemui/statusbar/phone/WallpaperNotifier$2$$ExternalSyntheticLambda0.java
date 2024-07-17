package com.google.android.systemui.statusbar.phone;

import com.google.android.systemui.statusbar.phone.WallpaperNotifier;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class WallpaperNotifier$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ WallpaperNotifier.AnonymousClass2 f$0;

    public /* synthetic */ WallpaperNotifier$2$$ExternalSyntheticLambda0(WallpaperNotifier.AnonymousClass2 r1) {
        this.f$0 = r1;
    }

    public final void run() {
        WallpaperNotifier.this.checkNotificationBroadcastSupport();
    }
}
