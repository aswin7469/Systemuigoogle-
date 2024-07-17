package com.google.android.systemui.columbus.fetchers;

import android.database.ContentObserver;
import android.os.Handler;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
        ((ProducerCoroutine) this.$$this$conflatedCallbackFlow).m1738trySendJP2dKIU(Integer.valueOf(this.$userId));
    }
}
