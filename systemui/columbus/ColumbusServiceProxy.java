package com.google.android.systemui.columbus;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColumbusServiceProxy extends Service {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ColumbusServiceProxy$binder$1 binder = new ColumbusServiceProxy$binder$1(this);
    public final List columbusServiceListeners = new ArrayList();

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ColumbusServiceListener implements IBinder.DeathRecipient {
        public IColumbusServiceListener listener;
        public IBinder token;

        public final void binderDied() {
            Log.w("Columbus/ColumbusProxy", "ColumbusServiceListener binder died");
            this.token = null;
            this.listener = null;
        }
    }

    public final IBinder onBind(Intent intent) {
        return this.binder;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }
}
