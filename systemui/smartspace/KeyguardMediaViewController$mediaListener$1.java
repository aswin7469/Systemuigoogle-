package com.google.android.systemui.smartspace;

import android.media.MediaMetadata;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.util.concurrency.ExecutorImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
