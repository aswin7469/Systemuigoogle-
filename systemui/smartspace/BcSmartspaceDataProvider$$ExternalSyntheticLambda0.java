package com.google.android.systemui.smartspace;

import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class BcSmartspaceDataProvider$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ BcSmartspaceDataProvider f$0;

    public /* synthetic */ BcSmartspaceDataProvider$$ExternalSyntheticLambda0(BcSmartspaceDataProvider bcSmartspaceDataProvider) {
        this.f$0 = bcSmartspaceDataProvider;
    }

    public final void accept(Object obj) {
        ((BcSmartspaceDataPlugin.SmartspaceTargetListener) obj).onSmartspaceTargetsUpdated(this.f$0.mSmartspaceTargets);
    }
}
