package com.google.android.systemui.elmyra;

import com.google.android.systemui.elmyra.actions.Action;
import com.google.android.systemui.elmyra.gates.Gate;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
