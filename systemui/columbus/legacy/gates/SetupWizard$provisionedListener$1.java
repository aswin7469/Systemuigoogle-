package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SetupWizard$provisionedListener$1 implements DeviceProvisionedController.DeviceProvisionedListener {
    public final /* synthetic */ SetupWizard this$0;

    public SetupWizard$provisionedListener$1(SetupWizard setupWizard) {
        this.this$0 = setupWizard;
    }

    public final void onDeviceProvisionedChanged() {
        SetupWizard setupWizard = this.this$0;
        BuildersKt.launch$default(setupWizard.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new SetupWizard$provisionedListener$1$updateSetupComplete$1(setupWizard, (Continuation) null), 3);
    }

    public final void onUserSetupChanged() {
        SetupWizard setupWizard = this.this$0;
        BuildersKt.launch$default(setupWizard.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new SetupWizard$provisionedListener$1$updateSetupComplete$1(setupWizard, (Continuation) null), 3);
    }
}
