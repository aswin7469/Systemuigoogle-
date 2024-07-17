package com.google.android.systemui.smartspace;

import android.media.MediaMetadata;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.util.concurrency.ExecutorImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardMediaViewController$mediaListener$1 implements NotificationMediaManager.MediaListener {
    public final /* synthetic */ KeyguardMediaViewController this$0;

    public KeyguardMediaViewController$mediaListener$1(KeyguardMediaViewController keyguardMediaViewController) {
        this.this$0 = keyguardMediaViewController;
    }

    public final void onPrimaryMetadataOrStateChanged(MediaMetadata mediaMetadata, int i) {
        KeyguardMediaViewController keyguardMediaViewController = this.this$0;
        ((ExecutorImpl) keyguardMediaViewController.uiExecutor).execute(new KeyguardMediaViewController$mediaListener$1$onPrimaryMetadataOrStateChanged$1(keyguardMediaViewController, mediaMetadata, i));
    }
}
