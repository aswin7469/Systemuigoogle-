package com.google.android.systemui.columbus.fetchers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BroadcastFetcher$createDispatchedFlow$1$broadcastReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    public BroadcastFetcher$createDispatchedFlow$1$broadcastReceiver$1(ProducerScope producerScope) {
        this.$$this$conflatedCallbackFlow = producerScope;
    }

    public final void onReceive(Context context, Intent intent) {
        ((ProducerCoroutine) this.$$this$conflatedCallbackFlow).m1780trySendJP2dKIU(intent);
    }
}
