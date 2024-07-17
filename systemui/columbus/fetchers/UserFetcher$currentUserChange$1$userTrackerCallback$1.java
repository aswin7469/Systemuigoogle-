package com.google.android.systemui.columbus.fetchers;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import java.util.List;
import kotlin.Unit;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UserFetcher$currentUserChange$1$userTrackerCallback$1 implements UserTracker.Callback {
    public final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    public UserFetcher$currentUserChange$1$userTrackerCallback$1(ProducerScope producerScope) {
        this.$$this$conflatedCallbackFlow = producerScope;
    }

    public final void onProfilesChanged(List list) {
        ((ProducerCoroutine) this.$$this$conflatedCallbackFlow).m1738trySendJP2dKIU(Unit.INSTANCE);
    }

    public final void onUserChanged(int i, Context context) {
        ((ProducerCoroutine) this.$$this$conflatedCallbackFlow).m1738trySendJP2dKIU(Unit.INSTANCE);
    }
}
