package com.google.android.systemui.smartspace;

import android.net.Uri;
import java.util.function.Predicate;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class BcSmartspaceCardDoorbell$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ BcSmartspaceCardDoorbell f$0;

    public /* synthetic */ BcSmartspaceCardDoorbell$$ExternalSyntheticLambda0(BcSmartspaceCardDoorbell bcSmartspaceCardDoorbell) {
        this.f$0 = bcSmartspaceCardDoorbell;
    }

    public final boolean test(Object obj) {
        return !this.f$0.mUriToDrawable.containsKey((Uri) obj);
    }
}
