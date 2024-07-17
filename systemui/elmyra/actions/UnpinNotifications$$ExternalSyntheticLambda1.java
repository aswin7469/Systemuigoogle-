package com.google.android.systemui.elmyra.actions;

import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class UnpinNotifications$$ExternalSyntheticLambda1 implements Consumer {
    public final void accept(Object obj) {
        ((BaseHeadsUpManager) ((HeadsUpManager) obj)).unpinAll();
    }
}
