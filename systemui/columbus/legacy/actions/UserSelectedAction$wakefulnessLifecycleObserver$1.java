package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.keyguard.WakefulnessLifecycle;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class UserSelectedAction$wakefulnessLifecycleObserver$1 implements WakefulnessLifecycle.Observer {
    public final /* synthetic */ UserSelectedAction this$0;

    public UserSelectedAction$wakefulnessLifecycleObserver$1(UserSelectedAction userSelectedAction) {
        this.this$0 = userSelectedAction;
    }

    public final void onFinishedGoingToSleep$1() {
        this.this$0.updateAvailable$7();
    }

    public final void onStartedWakingUp() {
        this.this$0.updateAvailable$7();
    }
}
