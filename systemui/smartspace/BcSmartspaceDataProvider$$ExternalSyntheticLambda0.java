package com.google.android.systemui.smartspace;

import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BcSmartspaceDataProvider$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ BcSmartspaceDataProvider f$0;

    public /* synthetic */ BcSmartspaceDataProvider$$ExternalSyntheticLambda0(BcSmartspaceDataProvider bcSmartspaceDataProvider) {
        this.f$0 = bcSmartspaceDataProvider;
    }

    public final void accept(Object obj) {
        ((BcSmartspaceDataPlugin.SmartspaceTargetListener) obj).onSmartspaceTargetsUpdated(this.f$0.mSmartspaceTargets);
    }
}
