package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.actions.Action;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SetupWizard$actionListener$1 implements Action.Listener {
    public final /* synthetic */ SetupWizard this$0;

    public SetupWizard$actionListener$1(SetupWizard setupWizard) {
        this.this$0 = setupWizard;
    }

    public final void onActionAvailabilityChanged(Action action) {
        SetupWizard setupWizard = this.this$0;
        BuildersKt.launch$default(setupWizard.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new SetupWizard$actionListener$1$onActionAvailabilityChanged$1(setupWizard, (Continuation) null), 3);
    }
}
