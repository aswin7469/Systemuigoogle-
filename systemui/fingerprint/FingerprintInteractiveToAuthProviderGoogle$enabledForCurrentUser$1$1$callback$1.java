package com.google.android.systemui.fingerprint;

import android.database.ContentObserver;
import android.os.Handler;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1$callback$1 extends ContentObserver {
    public final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public final /* synthetic */ Function0 $getCurrentSettingValue;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1$callback$1(ProducerScope producerScope, Function0 function0) {
        super((Handler) null);
        this.$$this$conflatedCallbackFlow = producerScope;
        this.$getCurrentSettingValue = function0;
    }

    public final void onChange(boolean z) {
        Object r3 = this.$$this$conflatedCallbackFlow.m1783trySendJP2dKIU(this.$getCurrentSettingValue.invoke());
        if (r3 instanceof ChannelResult.Failed) {
            FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "setting value updated", " - downstream canceled or failed.", "FingerprintInteractiveToAuthProviderGoogle", ChannelResult.m1775exceptionOrNullimpl(r3));
        }
    }
}
