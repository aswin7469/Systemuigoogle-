package com.google.android.systemui.elmyra.actions;

import com.google.android.systemui.elmyra.ElmyraService;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class Action$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Action f$0;

    public /* synthetic */ Action$$ExternalSyntheticLambda0(Action action, int i) {
        this.$r8$classId = i;
        this.f$0 = action;
    }

    public final void run() {
        int i = this.$r8$classId;
        Action action = this.f$0;
        switch (i) {
            case 0:
                ElmyraService.AnonymousClass1 r2 = action.mListener;
                if (r2 != null) {
                    ElmyraService.this.updateSensorListener$1();
                    return;
                }
                return;
            default:
                action.updateFeedbackEffects(0, 0.0f);
                return;
        }
    }
}
