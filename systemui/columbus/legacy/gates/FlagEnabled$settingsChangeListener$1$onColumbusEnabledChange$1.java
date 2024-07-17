package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class FlagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $isEnabled;
    int label;
    final /* synthetic */ FlagEnabled this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1(FlagEnabled flagEnabled, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = flagEnabled;
        this.$isEnabled = z;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new FlagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1(this.this$0, this.$isEnabled, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((FlagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            FlagEnabled flagEnabled = this.this$0;
            flagEnabled.columbusEnabled = this.$isEnabled;
            BuildersKt.launch$default(flagEnabled.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new FlagEnabled$updateBlocking$1(flagEnabled, (Continuation) null), 3);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
