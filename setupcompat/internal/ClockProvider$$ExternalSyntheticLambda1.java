package com.google.android.setupcompat.internal;

import com.google.android.setupcompat.internal.ClockProvider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ClockProvider$$ExternalSyntheticLambda1 implements Ticker {
    public final /* synthetic */ ClockProvider.Supplier f$0;

    public /* synthetic */ ClockProvider$$ExternalSyntheticLambda1(ClockProvider.Supplier supplier) {
        this.f$0 = supplier;
    }

    public final long read() {
        return ((Long) this.f$0.get()).longValue();
    }
}
