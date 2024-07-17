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

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class SetupWizard$provisionedListener$1$updateSetupComplete$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ SetupWizard this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SetupWizard$provisionedListener$1$updateSetupComplete$1(SetupWizard setupWizard, Continuation continuation) {
        super(2, continuation);
        this.this$0 = setupWizard;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SetupWizard$provisionedListener$1$updateSetupComplete$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SetupWizard$provisionedListener$1$updateSetupComplete$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        SetupWizard setupWizard;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SetupWizard setupWizard2 = this.this$0;
            this.L$0 = setupWizard2;
            this.label = 1;
            Object access$isSetupComplete = SetupWizard.access$isSetupComplete(setupWizard2, this);
            if (access$isSetupComplete == coroutineSingletons) {
                return coroutineSingletons;
            }
            setupWizard = setupWizard2;
            obj = access$isSetupComplete;
        } else if (i == 1) {
            setupWizard = (SetupWizard) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        setupWizard.setupComplete = ((Boolean) obj).booleanValue();
        SetupWizard setupWizard3 = this.this$0;
        BuildersKt.launch$default(setupWizard3.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new SetupWizard$updateBlocking$1(setupWizard3, (Continuation) null), 3);
        return Unit.INSTANCE;
    }
}
