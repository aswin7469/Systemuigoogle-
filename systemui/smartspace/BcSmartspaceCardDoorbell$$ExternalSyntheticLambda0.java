package com.google.android.systemui.smartspace;

import android.net.Uri;
import java.util.function.Predicate;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BcSmartspaceCardDoorbell$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ BcSmartspaceCardDoorbell f$0;

    public /* synthetic */ BcSmartspaceCardDoorbell$$ExternalSyntheticLambda0(BcSmartspaceCardDoorbell bcSmartspaceCardDoorbell) {
        this.f$0 = bcSmartspaceCardDoorbell;
    }

    public final boolean test(Object obj) {
        return !this.f$0.mUriToDrawable.containsKey((Uri) obj);
    }
}
