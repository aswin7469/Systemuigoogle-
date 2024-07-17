package com.google.android.systemui.elmyra;

import com.google.android.systemui.elmyra.actions.Action;
import com.google.android.systemui.elmyra.gates.Gate;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ElmyraService$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ElmyraService f$0;

    public /* synthetic */ ElmyraService$$ExternalSyntheticLambda0(ElmyraService elmyraService, int i) {
        this.$r8$classId = i;
        this.f$0 = elmyraService;
    }

    public final void accept(Object obj) {
        int i = this.$r8$classId;
        ElmyraService elmyraService = this.f$0;
        switch (i) {
            case 0:
                ((Action) obj).mListener = elmyraService.mActionListener;
                return;
            default:
                ((Gate) obj).mListener = elmyraService.mGateListener;
                return;
        }
    }
}
