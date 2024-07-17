package com.google.android.systemui.elmyra.actions;

import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class UnpinNotifications$$ExternalSyntheticLambda1 implements Consumer {
    public final void accept(Object obj) {
        ((BaseHeadsUpManager) ((HeadsUpManager) obj)).unpinAll();
    }
}
