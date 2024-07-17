package com.google.android.systemui.dreamliner;

import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;
import com.google.android.systemui.dreamliner.DockObserver;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class DockObserver$ProtectedBroadcastSender$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DockObserver.ProtectedBroadcastSender f$0;
    public final /* synthetic */ Intent f$1;
    public final /* synthetic */ UserHandle f$2;

    public /* synthetic */ DockObserver$ProtectedBroadcastSender$$ExternalSyntheticLambda0(DockObserver.ProtectedBroadcastSender protectedBroadcastSender, Intent intent, UserHandle userHandle) {
        this.f$0 = protectedBroadcastSender;
        this.f$1 = intent;
        this.f$2 = userHandle;
    }

    public final void run() {
        DockObserver.ProtectedBroadcastSender protectedBroadcastSender = this.f$0;
        Intent intent = this.f$1;
        UserHandle userHandle = this.f$2;
        protectedBroadcastSender.getClass();
        try {
            protectedBroadcastSender.mContext.sendBroadcastAsUser(intent, userHandle);
        } catch (SecurityException e) {
            Log.w("DLObserver", "Send protected broadcast failed. intent= " + intent, e);
        }
    }
}
