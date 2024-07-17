package com.google.android.systemui.columbus.legacy.actions;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Trace;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.settings.UserTrackerImpl;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LaunchApp$updateAvailableAppsAndShortcutsAsync$$inlined$traceRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object $tag;
    public final /* synthetic */ LaunchApp this$0;

    public LaunchApp$updateAvailableAppsAndShortcutsAsync$$inlined$traceRunnable$1(LaunchApp launchApp) {
        this.$r8$classId = 0;
        this.$tag = "updateAvailableAppsAndShortcutsAsync";
        this.this$0 = launchApp;
    }

    public final void run() {
        List<ShortcutInfo> list;
        boolean isTagEnabled;
        switch (this.$r8$classId) {
            case 0:
                String str = (String) this.$tag;
                boolean isTagEnabled2 = Trace.isTagEnabled(4096);
                if (isTagEnabled2) {
                    Trace.traceBegin(4096, str);
                }
                try {
                    int currentUser = ActivityManager.getCurrentUser();
                    if (this.this$0.userManager.isUserUnlocked(currentUser)) {
                        this.this$0.availableApps.clear();
                        this.this$0.availableShortcuts.clear();
                        Bundle bundle = null;
                        List<LauncherActivityInfo> activityList = this.this$0.launcherApps.getActivityList((String) null, UserHandle.of(currentUser));
                        LaunchApp launchApp = this.this$0;
                        launchApp.getClass();
                        LauncherApps.ShortcutQuery shortcutQuery = new LauncherApps.ShortcutQuery();
                        shortcutQuery.setQueryFlags(9);
                        list = launchApp.launcherApps.getShortcuts(shortcutQuery, UserHandle.of(currentUser));
                        for (LauncherActivityInfo next : activityList) {
                            String str2 = "getMainActivityLaunchIntent component=" + next.getComponentName();
                            isTagEnabled = Trace.isTagEnabled(4096);
                            if (isTagEnabled) {
                                Trace.traceBegin(4096, str2);
                            }
                            PendingIntent mainActivityLaunchIntent = this.this$0.launcherApps.getMainActivityLaunchIntent(next.getComponentName(), bundle, UserHandle.of(currentUser));
                            if (mainActivityLaunchIntent != null) {
                                Intent intent = new Intent(mainActivityLaunchIntent.getIntent());
                                intent.putExtra("systemui_google_quick_tap_is_source", true);
                                Map map = this.this$0.availableApps;
                                ComponentName componentName = next.getComponentName();
                                LaunchApp launchApp2 = this.this$0;
                                map.put(componentName, PendingIntent.getActivityAsUser(launchApp2.context, 0, intent, 67108864, (Bundle) null, ((UserTrackerImpl) launchApp2.userTracker).getUserHandle()));
                                LaunchApp.access$addShortcutsForApp(this.this$0, next, list);
                            }
                            if (isTagEnabled) {
                                Trace.traceEnd(4096);
                            }
                            bundle = null;
                        }
                        LaunchApp launchApp3 = this.this$0;
                        launchApp3.mainHandler.post(new LaunchApp$updateAvailableAppsAndShortcutsAsync$1$2(launchApp3));
                    } else {
                        Log.d("Columbus/LaunchApp", "Did not update apps and shortcuts, user " + currentUser + " not unlocked");
                    }
                } catch (RuntimeException unused) {
                } catch (Exception e) {
                    if (!(e instanceof SecurityException)) {
                        if (!(e instanceof IllegalStateException)) {
                            throw e;
                        }
                    }
                    Log.e("Columbus/LaunchApp", "Failed to query for shortcuts", e);
                    list = null;
                } catch (Throwable th) {
                    if (isTagEnabled2) {
                        Trace.traceEnd(4096);
                    }
                    throw th;
                }
                if (isTagEnabled2) {
                    Trace.traceEnd(4096);
                    return;
                }
                return;
            default:
                this.this$0.statusBarKeyguardViewManager.setKeyguardMessage(((Context) this.$tag).getString(2131952225), ColorStateList.valueOf(-1));
                return;
        }
    }

    public LaunchApp$updateAvailableAppsAndShortcutsAsync$$inlined$traceRunnable$1(LaunchApp launchApp, Context context) {
        this.$r8$classId = 1;
        this.this$0 = launchApp;
        this.$tag = context;
    }
}
