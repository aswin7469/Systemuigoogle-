package com.google.android.systemui.fingerprint;

import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0;
import com.android.systemui.util.settings.SecureSettingsImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function0 $getCurrentSettingValue;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FingerprintInteractiveToAuthProviderGoogle this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1(FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle, Function0 function0, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fingerprintInteractiveToAuthProviderGoogle;
        this.$getCurrentSettingValue = function0;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1 fingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1 = new FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1(this.this$0, this.$getCurrentSettingValue, continuation);
        fingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1.L$0 = obj;
        return fingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1$callback$1 fingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1$callback$1 = new FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1$callback$1(producerScope, this.$getCurrentSettingValue);
            SecureSettingsImpl secureSettingsImpl = (SecureSettingsImpl) this.this$0.secureSettings;
            secureSettingsImpl.registerContentObserver(secureSettingsImpl.getUriFor("sfps_performant_auth_enabled_v2"), true, fingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1$callback$1);
            Object r3 = producerScope.m1783trySendJP2dKIU(this.$getCurrentSettingValue.invoke());
            if (r3 instanceof ChannelResult.Failed) {
                FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "emitting initial value", " - downstream canceled or failed.", "FingerprintInteractiveToAuthProviderGoogle", ChannelResult.m1775exceptionOrNullimpl(r3));
            }
            final FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle = this.this$0;
            AnonymousClass1 r32 = new Function0() {
                public final Object invoke() {
                    FingerprintInteractiveToAuthProviderGoogle.this.secureSettings.unregisterContentObserver(fingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1$callback$1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, r32, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
