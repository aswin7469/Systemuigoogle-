package com.google.android.systemui.columbus.legacy.actions;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.os.Bundle;
import android.os.Trace;
import android.os.UserHandle;
import android.util.Log;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.settings.UserTrackerImpl;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LaunchApp$updateAvailableAppsAndShortcutsAsync$1$2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LaunchApp this$0;

    public /* synthetic */ LaunchApp$updateAvailableAppsAndShortcutsAsync$1$2(LaunchApp launchApp, int i) {
        this.$r8$classId = i;
        this.this$0 = launchApp;
    }

    public final void run() {
        List<ShortcutInfo> list;
        boolean isEnabled;
        switch (this.$r8$classId) {
            case 0:
                this.this$0.updateAvailable$4();
                return;
            default:
                boolean isEnabled2 = Trace.isEnabled();
                if (isEnabled2) {
                    TraceUtilsKt.beginSlice("updateAvailableAppsAndShortcutsAsync");
                }
                try {
                    int currentUser = ActivityManager.getCurrentUser();
                    if (this.this$0.userManager.isUserUnlocked(currentUser)) {
                        this.this$0.availableApps.clear();
                        this.this$0.availableShortcuts.clear();
                        List<LauncherActivityInfo> activityList = this.this$0.launcherApps.getActivityList((String) null, UserHandle.of(currentUser));
                        LaunchApp launchApp = this.this$0;
                        launchApp.getClass();
                        LauncherApps.ShortcutQuery shortcutQuery = new LauncherApps.ShortcutQuery();
                        shortcutQuery.setQueryFlags(9);
                        list = launchApp.launcherApps.getShortcuts(shortcutQuery, UserHandle.of(currentUser));
                        for (LauncherActivityInfo next : activityList) {
                            String str = "getMainActivityLaunchIntent component=" + next.getComponentName();
                            isEnabled = Trace.isEnabled();
                            if (isEnabled) {
                                TraceUtilsKt.beginSlice(str);
                            }
                            PendingIntent mainActivityLaunchIntent = this.this$0.launcherApps.getMainActivityLaunchIntent(next.getComponentName(), (Bundle) null, UserHandle.of(currentUser));
                            if (mainActivityLaunchIntent != null) {
                                Intent intent = new Intent(mainActivityLaunchIntent.getIntent());
                                intent.putExtra("systemui_google_quick_tap_is_source", true);
                                Map map = this.this$0.availableApps;
                                ComponentName componentName = next.getComponentName();
                                LaunchApp launchApp2 = this.this$0;
                                map.put(componentName, PendingIntent.getActivityAsUser(launchApp2.context, 0, intent, 67108864, (Bundle) null, ((UserTrackerImpl) launchApp2.userTracker).getUserHandle()));
                                LaunchApp.access$addShortcutsForApp(this.this$0, next, list);
                            }
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                        LaunchApp launchApp3 = this.this$0;
                        launchApp3.mainHandler.post(new LaunchApp$updateAvailableAppsAndShortcutsAsync$1$2(launchApp3, 0));
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
                    if (isEnabled2) {
                        TraceUtilsKt.endSlice();
                    }
                    throw th;
                }
                if (isEnabled2) {
                    TraceUtilsKt.endSlice();
                    return;
                }
                return;
        }
    }
}
