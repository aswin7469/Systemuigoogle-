package com.google.android.systemui.columbus.fetchers;

import android.database.ContentObserver;
import android.os.Handler;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ContentFetcher$getSettingChangesForUser$1$contentObserver$1 extends ContentObserver {
    public final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public final /* synthetic */ int $userId;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContentFetcher$getSettingChangesForUser$1$contentObserver$1(ProducerScope producerScope, int i, Handler handler) {
        super(handler);
        this.$$this$conflatedCallbackFlow = producerScope;
        this.$userId = i;
    }

    public final void onChange(boolean z) {
        ((ProducerCoroutine) this.$$this$conflatedCallbackFlow).m1780trySendJP2dKIU(Integer.valueOf(this.$userId));
    }
}
