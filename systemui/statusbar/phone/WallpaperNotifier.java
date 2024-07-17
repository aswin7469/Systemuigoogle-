package com.google.android.systemui.statusbar.phone;

import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.google.android.collect.Sets;
import java.util.HashSet;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class WallpaperNotifier {
    public static final HashSet NOTIFYABLE_PACKAGES = Sets.newHashSet(new String[]{"com.breel.wallpapers", "com.breel.wallpapers18", "com.google.pixel.livewallpaper"});
    public static final String[] NOTIFYABLE_WALLPAPERS = {"com.breel.wallpapers.imprint", "com.breel.wallpapers18.tactile", "com.breel.wallpapers18.delight", "com.breel.wallpapers18.miniman", "com.google.pixel.livewallpaper.imprint", "com.google.pixel.livewallpaper.tactile", "com.google.pixel.livewallpaper.delight", "com.google.pixel.livewallpaper.miniman"};
    public final BroadcastSender mBroadcastSender;
    public final Context mContext;
    public final CommonNotifCollection mNotifCollection;
    public final AnonymousClass1 mNotifListener = new NotifCollectionListener() {
        public final void onEntryAdded(NotificationEntry notificationEntry) {
            boolean z;
            WallpaperNotifier wallpaperNotifier = WallpaperNotifier.this;
            if (((UserTrackerImpl) wallpaperNotifier.mUserTracker).getUserId() == 0) {
                z = true;
            } else {
                z = false;
            }
            if (wallpaperNotifier.mShouldBroadcastNotifications && z) {
                Intent intent = new Intent();
                intent.setPackage(wallpaperNotifier.mWallpaperPackage);
                intent.setAction("com.breel.wallpapers.NOTIFICATION_RECEIVED");
                intent.putExtra("notification_color", notificationEntry.mSbn.getNotification().color);
                wallpaperNotifier.mBroadcastSender.sendBroadcast(intent, "com.breel.wallpapers.notifications");
            }
        }
    };
    public boolean mShouldBroadcastNotifications;
    public final Executor mUiBgExecutor;
    public final UserTracker mUserTracker;
    public final AnonymousClass2 mWallpaperChangedReceiver = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.WALLPAPER_CHANGED")) {
                WallpaperNotifier.this.mUiBgExecutor.execute(new WallpaperNotifier$2$$ExternalSyntheticLambda0(this));
            }
        }
    };
    public String mWallpaperPackage;

    public WallpaperNotifier(Context context, CommonNotifCollection commonNotifCollection, BroadcastSender broadcastSender, UserTracker userTracker, Executor executor) {
        this.mContext = context;
        this.mNotifCollection = commonNotifCollection;
        this.mBroadcastSender = broadcastSender;
        this.mUserTracker = userTracker;
        this.mUiBgExecutor = executor;
    }

    public final void checkNotificationBroadcastSupport() {
        WallpaperInfo wallpaperInfo;
        this.mShouldBroadcastNotifications = false;
        WallpaperManager wallpaperManager = (WallpaperManager) this.mContext.getSystemService(WallpaperManager.class);
        if (wallpaperManager != null && (wallpaperInfo = wallpaperManager.getWallpaperInfo()) != null) {
            ComponentName component = wallpaperInfo.getComponent();
            String packageName = component.getPackageName();
            if (NOTIFYABLE_PACKAGES.contains(packageName)) {
                this.mWallpaperPackage = packageName;
                String className = component.getClassName();
                String[] strArr = NOTIFYABLE_WALLPAPERS;
                for (int i = 0; i < 8; i++) {
                    if (className.startsWith(strArr[i])) {
                        this.mShouldBroadcastNotifications = true;
                        return;
                    }
                }
            }
        }
    }
}
