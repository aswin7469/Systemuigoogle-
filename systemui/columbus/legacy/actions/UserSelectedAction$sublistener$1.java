package com.google.android.systemui.columbus.legacy.actions;

import com.google.android.systemui.columbus.legacy.actions.Action;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
