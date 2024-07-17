package com.google.android.systemui.smartspace;

import android.content.ComponentName;
import android.content.Context;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.util.concurrency.DelayableExecutor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardMediaViewController {
    public CharSequence artist;
    public final Context context;
    public final ComponentName mediaComponent;
    public final KeyguardMediaViewController$mediaListener$1 mediaListener = new KeyguardMediaViewController$mediaListener$1(this);
    public final NotificationMediaManager mediaManager;
    public final BcSmartspaceDataPlugin plugin;
    public BcSmartspaceDataPlugin.SmartspaceView smartspaceView;
    public CharSequence title;
    public final DelayableExecutor uiExecutor;
    public final UserTracker userTracker;

    public KeyguardMediaViewController(Context context2, UserTracker userTracker2, BcSmartspaceDataPlugin bcSmartspaceDataPlugin, DelayableExecutor delayableExecutor, NotificationMediaManager notificationMediaManager) {
        this.context = context2;
        this.userTracker = userTracker2;
        this.plugin = bcSmartspaceDataPlugin;
        this.uiExecutor = delayableExecutor;
        this.mediaManager = notificationMediaManager;
        this.mediaComponent = new ComponentName(context2, KeyguardMediaViewController.class);
    }

    public static /* synthetic */ void getSmartspaceView$annotations() {
    }
}
