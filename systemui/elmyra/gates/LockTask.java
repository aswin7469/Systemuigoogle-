package com.google.android.systemui.elmyra.gates;

import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LockTask extends Gate {
    public boolean mIsBlocked;
    public final AnonymousClass1 mTaskStackListener = new TaskStackChangeListener() {
        public final void onLockTaskModeChanged(int i) {
            ExifInterface$$ExternalSyntheticOutline0.m("Mode: ", i, "Elmyra/LockTask");
            LockTask lockTask = LockTask.this;
            if (i == 0) {
                lockTask.mIsBlocked = false;
            } else {
                lockTask.mIsBlocked = true;
            }
            lockTask.notifyListener();
        }
    };

    public LockTask(Executor executor) {
        super(executor);
    }

    public final boolean isBlocked() {
        return this.mIsBlocked;
    }

    public final void onActivate() {
        TaskStackChangeListeners.INSTANCE.registerTaskStackListener(this.mTaskStackListener);
    }

    public final void onDeactivate() {
        TaskStackChangeListeners.INSTANCE.unregisterTaskStackListener(this.mTaskStackListener);
    }
}
