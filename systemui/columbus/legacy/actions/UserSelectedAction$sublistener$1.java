package com.google.android.systemui.columbus.legacy.actions;

import com.google.android.systemui.columbus.legacy.actions.Action;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UserSelectedAction$sublistener$1 implements Action.Listener {
    public final /* synthetic */ UserSelectedAction this$0;

    public UserSelectedAction$sublistener$1(UserSelectedAction userSelectedAction) {
        this.this$0 = userSelectedAction;
    }

    public final void onActionAvailabilityChanged(Action action) {
        UserSelectedAction userSelectedAction = this.this$0;
        if (Intrinsics.areEqual(userSelectedAction.currentAction, action)) {
            userSelectedAction.updateAvailable$7();
        }
    }
}
