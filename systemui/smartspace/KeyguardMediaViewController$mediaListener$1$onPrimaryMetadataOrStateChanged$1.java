package com.google.android.systemui.smartspace;

import android.media.MediaMetadata;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class KeyguardMediaViewController$mediaListener$1$onPrimaryMetadataOrStateChanged$1 implements Runnable {
    public final /* synthetic */ MediaMetadata $metadata;
    public final /* synthetic */ int $state;
    public final /* synthetic */ KeyguardMediaViewController this$0;

    public KeyguardMediaViewController$mediaListener$1$onPrimaryMetadataOrStateChanged$1(KeyguardMediaViewController keyguardMediaViewController, MediaMetadata mediaMetadata, int i) {
        this.this$0 = keyguardMediaViewController;
        this.$metadata = mediaMetadata;
        this.$state = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r6 = this;
            com.google.android.systemui.smartspace.KeyguardMediaViewController r0 = r6.this$0
            android.media.MediaMetadata r1 = r6.$metadata
            int r6 = r6.$state
            r0.getClass()
            boolean r6 = com.android.systemui.statusbar.NotificationMediaManager.isPlayingState(r6)
            r2 = 0
            if (r6 != 0) goto L_0x001d
            r0.title = r2
            r0.artist = r2
            com.android.systemui.plugins.BcSmartspaceDataPlugin$SmartspaceView r6 = r0.smartspaceView
            if (r6 == 0) goto L_0x00b5
            r6.setMediaTarget(r2)
            goto L_0x00b5
        L_0x001d:
            if (r1 == 0) goto L_0x0039
            java.lang.String r6 = "android.media.metadata.TITLE"
            java.lang.CharSequence r6 = r1.getText(r6)
            boolean r3 = android.text.TextUtils.isEmpty(r6)
            if (r3 == 0) goto L_0x003a
            android.content.Context r6 = r0.context
            android.content.res.Resources r6 = r6.getResources()
            r3 = 2131953335(0x7f1306b7, float:1.9543138E38)
            java.lang.String r6 = r6.getString(r3)
            goto L_0x003a
        L_0x0039:
            r6 = r2
        L_0x003a:
            if (r1 == 0) goto L_0x0043
            java.lang.String r3 = "android.media.metadata.ARTIST"
            java.lang.CharSequence r1 = r1.getText(r3)
            goto L_0x0044
        L_0x0043:
            r1 = r2
        L_0x0044:
            java.lang.CharSequence r3 = r0.title
            boolean r3 = android.text.TextUtils.equals(r3, r6)
            if (r3 == 0) goto L_0x0055
            java.lang.CharSequence r3 = r0.artist
            boolean r3 = android.text.TextUtils.equals(r3, r1)
            if (r3 == 0) goto L_0x0055
            goto L_0x00b5
        L_0x0055:
            r0.title = r6
            r0.artist = r1
            if (r6 == 0) goto L_0x00a7
            android.app.smartspace.SmartspaceAction$Builder r1 = new android.app.smartspace.SmartspaceAction$Builder
            java.lang.String r3 = "deviceMediaTitle"
            java.lang.String r6 = r6.toString()
            r1.<init>(r3, r6)
            java.lang.CharSequence r6 = r0.artist
            android.app.smartspace.SmartspaceAction$Builder r6 = r1.setSubtitle(r6)
            com.android.systemui.statusbar.NotificationMediaManager r1 = r0.mediaManager
            android.graphics.drawable.Icon r1 = r1.getMediaIcon()
            android.app.smartspace.SmartspaceAction$Builder r6 = r6.setIcon(r1)
            android.app.smartspace.SmartspaceAction r6 = r6.build()
            com.android.systemui.settings.UserTracker r1 = r0.userTracker
            com.android.systemui.settings.UserTrackerImpl r1 = (com.android.systemui.settings.UserTrackerImpl) r1
            int r1 = r1.getUserId()
            android.os.UserHandle r1 = android.os.UserHandle.of(r1)
            android.app.smartspace.SmartspaceTarget$Builder r3 = new android.app.smartspace.SmartspaceTarget$Builder
            java.lang.String r4 = "deviceMedia"
            android.content.ComponentName r5 = r0.mediaComponent
            r3.<init>(r4, r5, r1)
            r1 = 41
            android.app.smartspace.SmartspaceTarget$Builder r1 = r3.setFeatureType(r1)
            android.app.smartspace.SmartspaceTarget$Builder r6 = r1.setHeaderAction(r6)
            android.app.smartspace.SmartspaceTarget r6 = r6.build()
            com.android.systemui.plugins.BcSmartspaceDataPlugin$SmartspaceView r1 = r0.smartspaceView
            if (r1 == 0) goto L_0x00a7
            r1.setMediaTarget(r6)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            goto L_0x00a8
        L_0x00a7:
            r6 = r2
        L_0x00a8:
            if (r6 != 0) goto L_0x00b5
            r0.title = r2
            r0.artist = r2
            com.android.systemui.plugins.BcSmartspaceDataPlugin$SmartspaceView r6 = r0.smartspaceView
            if (r6 == 0) goto L_0x00b5
            r6.setMediaTarget(r2)
        L_0x00b5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.KeyguardMediaViewController$mediaListener$1$onPrimaryMetadataOrStateChanged$1.run():void");
    }
}
