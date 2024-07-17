package com.google.android.systemui.columbus.legacy.gates;

import android.provider.Settings;
import com.android.systemui.settings.UserTrackerImpl;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class SilenceAlertsDisabled$onActivate$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ SilenceAlertsDisabled this$0;

    /* renamed from: com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled$onActivate$1$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ColumbusSettings columbusSettings = SilenceAlertsDisabled.this.columbusSettings;
                boolean z = true;
                if (Settings.Secure.getIntForUser(columbusSettings.contentResolver, "columbus_silence_alerts", 1, ((UserTrackerImpl) columbusSettings.userTracker).getUserId()) == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SilenceAlertsDisabled$onActivate$1(SilenceAlertsDisabled silenceAlertsDisabled, Continuation continuation) {
        super(2, continuation);
        this.this$0 = silenceAlertsDisabled;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SilenceAlertsDisabled$onActivate$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SilenceAlertsDisabled$onActivate$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        SilenceAlertsDisabled silenceAlertsDisabled;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final SilenceAlertsDisabled silenceAlertsDisabled2 = this.this$0;
            CoroutineDispatcher coroutineDispatcher = silenceAlertsDisabled2.bgDispatcher;
            AnonymousClass1 r4 = new AnonymousClass1((Continuation) null);
            this.L$0 = silenceAlertsDisabled2;
            this.label = 1;
            Object withContext = BuildersKt.withContext(coroutineDispatcher, r4, this);
            if (withContext == coroutineSingletons) {
                return coroutineSingletons;
            }
            SilenceAlertsDisabled silenceAlertsDisabled3 = silenceAlertsDisabled2;
            obj = withContext;
            silenceAlertsDisabled = silenceAlertsDisabled3;
        } else if (i == 1) {
            silenceAlertsDisabled = (SilenceAlertsDisabled) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        BuildersKt.launch$default(silenceAlertsDisabled.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new SilenceAlertsDisabled$updateSilenceAlertsEnabled$1(silenceAlertsDisabled, ((Boolean) obj).booleanValue(), (Continuation) null), 3);
        return Unit.INSTANCE;
    }
}
