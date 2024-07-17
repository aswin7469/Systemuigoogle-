package com.google.android.systemui.columbus.fetchers;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import java.util.List;
import kotlin.Unit;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class UserFetcher$currentUserChange$1$userTrackerCallback$1 implements UserTracker.Callback {
    public final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    public UserFetcher$currentUserChange$1$userTrackerCallback$1(ProducerScope producerScope) {
        this.$$this$conflatedCallbackFlow = producerScope;
    }

    public final void onProfilesChanged(List list) {
        ((ProducerCoroutine) this.$$this$conflatedCallbackFlow).m1780trySendJP2dKIU(Unit.INSTANCE);
    }

    public final void onUserChanged(int i, Context context) {
        ((ProducerCoroutine) this.$$this$conflatedCallbackFlow).m1780trySendJP2dKIU(Unit.INSTANCE);
    }
}
