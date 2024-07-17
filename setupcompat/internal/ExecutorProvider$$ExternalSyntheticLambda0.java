package com.google.android.setupcompat.internal;

import java.util.concurrent.ThreadFactory;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ExecutorProvider$$ExternalSyntheticLambda0 implements ThreadFactory {
    public final /* synthetic */ String f$0;

    public /* synthetic */ ExecutorProvider$$ExternalSyntheticLambda0(String str) {
        this.f$0 = str;
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.f$0);
    }
}
