package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class SetupWizard$isSetupComplete$isDeviceProvisioned$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ SetupWizard this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SetupWizard$isSetupComplete$isDeviceProvisioned$1(SetupWizard setupWizard, Continuation continuation) {
        super(2, continuation);
        this.this$0 = setupWizard;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SetupWizard$isSetupComplete$isDeviceProvisioned$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SetupWizard$isSetupComplete$isDeviceProvisioned$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boolean.valueOf(((DeviceProvisionedControllerImpl) ((DeviceProvisionedController) this.this$0.provisionedController.get())).deviceProvisioned.get());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
