package com.google.android.systemui.columbus.fetchers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BroadcastFetcher$createDispatchedFlow$1$broadcastReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    public BroadcastFetcher$createDispatchedFlow$1$broadcastReceiver$1(ProducerScope producerScope) {
        this.$$this$conflatedCallbackFlow = producerScope;
    }

    public final void onReceive(Context context, Intent intent) {
        ((ProducerCoroutine) this.$$this$conflatedCallbackFlow).m1738trySendJP2dKIU(intent);
    }
}
