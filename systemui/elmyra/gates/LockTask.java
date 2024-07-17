package com.google.android.systemui.elmyra.gates;

import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LockTask extends Gate {
    public boolean mIsBlocked;
    public final AnonymousClass1 mTaskStackListener = new TaskStackChangeListener() {
        public final void onLockTaskModeChanged(int i) {
            ExifInterface$$ExternalSyntheticOutline0.m("Mode: ", "Elmyra/LockTask", i);
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
