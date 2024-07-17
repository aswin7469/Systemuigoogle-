package com.google.android.systemui.assist.uihints;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.util.Log;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TaskStackNotifier implements NgaMessageHandler.ConfigInfoListener {
    public PendingIntent mIntent;
    public final AnonymousClass1 mListener = new TaskStackChangeListener() {
        public final void onTaskCreated(ComponentName componentName) {
            PendingIntent pendingIntent = TaskStackNotifier.this.mIntent;
            if (pendingIntent != null) {
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    Log.e("TaskStackNotifier", "could not send intent", e);
                }
            }
        }

        public final void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
            PendingIntent pendingIntent = TaskStackNotifier.this.mIntent;
            if (pendingIntent != null) {
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    Log.e("TaskStackNotifier", "could not send intent", e);
                }
            }
        }
    };
    public boolean mListenerRegistered = false;
    public final TaskStackChangeListeners mListeners = TaskStackChangeListeners.INSTANCE;

    public final void onConfigInfo(NgaMessageHandler.ConfigInfo configInfo) {
        PendingIntent pendingIntent = configInfo.onTaskChange;
        this.mIntent = pendingIntent;
        AnonymousClass1 r0 = this.mListener;
        TaskStackChangeListeners taskStackChangeListeners = this.mListeners;
        if (pendingIntent != null && !this.mListenerRegistered) {
            taskStackChangeListeners.registerTaskStackListener(r0);
            this.mListenerRegistered = true;
        } else if (pendingIntent == null && this.mListenerRegistered) {
            taskStackChangeListeners.unregisterTaskStackListener(r0);
            this.mListenerRegistered = false;
        }
    }
}
