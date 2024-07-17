package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
