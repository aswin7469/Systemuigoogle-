package com.google.android.systemui.columbus.legacy.gates;

import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.broadcast.BroadcastDispatcher;
import dagger.Lazy;
import java.util.concurrent.Executor;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class PowerSaveState extends Gate {
    public boolean batterySaverEnabled;
    public final CoroutineDispatcher bgDispatcher;
    public final BroadcastDispatcher broadcastDispatcher;
    public boolean isDeviceInteractive;
    public final Lazy powerManager;
    public final PowerSaveState$receiver$1 receiver = new PowerSaveState$receiver$1(this);

    public PowerSaveState(BroadcastDispatcher broadcastDispatcher2, Lazy lazy, CoroutineDispatcher coroutineDispatcher) {
        this.broadcastDispatcher = broadcastDispatcher2;
        this.powerManager = lazy;
        this.bgDispatcher = coroutineDispatcher;
    }

    public final void onActivate() {
        IntentFilter intentFilter = new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.receiver, intentFilter, (Executor) null, (UserHandle) null, 0, (String) null, 60);
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new PowerSaveState$refreshStatus$1(this, (Continuation) null), 3);
    }

    public final void onDeactivate() {
        this.broadcastDispatcher.unregisterReceiver(this.receiver);
    }

    public final String toString() {
        String gate = super.toString();
        Object runBlocking = BuildersKt.runBlocking(this.mainDispatcher, new PowerSaveState$toString$1(this, (Continuation) null));
        return gate + runBlocking;
    }
}
