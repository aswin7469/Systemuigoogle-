package com.google.android.setupcompat.internal;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ClockProvider {
    public static final ClockProvider$$ExternalSyntheticLambda0 SYSTEM_TICKER;
    public static Ticker ticker;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface Supplier {
        Object get();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.setupcompat.internal.ClockProvider$$ExternalSyntheticLambda0, com.google.android.setupcompat.internal.Ticker, java.lang.Object] */
    static {
        ? obj = new Object();
        SYSTEM_TICKER = obj;
        ticker = obj;
    }

    public static void resetInstance() {
        ticker = SYSTEM_TICKER;
    }

    public static void setInstance(Supplier supplier) {
        ticker = new ClockProvider$$ExternalSyntheticLambda1(supplier);
    }
}
