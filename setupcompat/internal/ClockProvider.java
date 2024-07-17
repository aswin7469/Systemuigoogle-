package com.google.android.setupcompat.internal;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ClockProvider {
    public static final ClockProvider$$ExternalSyntheticLambda0 SYSTEM_TICKER;
    public static Ticker ticker;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
