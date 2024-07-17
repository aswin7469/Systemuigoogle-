package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SnackbarManager {
    public static SnackbarManager snackbarManager;
    public final Object lock = new Object();

    public SnackbarManager() {
        new Handler(Looper.getMainLooper(), new Handler.Callback() {
            public final boolean handleMessage(Message message) {
                if (message.what != 0) {
                    return false;
                }
                SnackbarManager snackbarManager = SnackbarManager.this;
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(message.obj);
                synchronized (snackbarManager.lock) {
                    throw null;
                }
            }
        });
    }
}
