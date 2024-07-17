package com.google.android.systemui.smartspace;

import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.view.View;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.statusbar.NotificationMediaManager;
import java.util.ArrayList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class KeyguardMediaViewController$init$1 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ KeyguardMediaViewController this$0;

    public KeyguardMediaViewController$init$1(KeyguardMediaViewController keyguardMediaViewController) {
        this.this$0 = keyguardMediaViewController;
    }

    public final void onViewAttachedToWindow(View view) {
        int i;
        PlaybackState playbackState;
        KeyguardMediaViewController keyguardMediaViewController = this.this$0;
        keyguardMediaViewController.smartspaceView = (BcSmartspaceDataPlugin.SmartspaceView) view;
        NotificationMediaManager notificationMediaManager = keyguardMediaViewController.mediaManager;
        ArrayList arrayList = notificationMediaManager.mMediaListeners;
        KeyguardMediaViewController$mediaListener$1 keyguardMediaViewController$mediaListener$1 = keyguardMediaViewController.mediaListener;
        arrayList.add(keyguardMediaViewController$mediaListener$1);
        MediaMetadata mediaMetadata = notificationMediaManager.mMediaMetadata;
        MediaController mediaController = notificationMediaManager.mMediaController;
        if (mediaController == null || (playbackState = mediaController.getPlaybackState()) == null) {
            i = 0;
        } else {
            i = playbackState.getState();
        }
        keyguardMediaViewController$mediaListener$1.onPrimaryMetadataOrStateChanged(mediaMetadata, i);
    }

    public final void onViewDetachedFromWindow(View view) {
        KeyguardMediaViewController keyguardMediaViewController = this.this$0;
        keyguardMediaViewController.smartspaceView = null;
        keyguardMediaViewController.mediaManager.mMediaListeners.remove(keyguardMediaViewController.mediaListener);
    }
}
