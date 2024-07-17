package com.google.android.setupcompat.internal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ExecutorProvider {
    public static final ExecutorProvider setupCompatServiceInvoker = new ExecutorProvider(createSizeBoundedExecutor("SetupCompatServiceInvoker", 50));
    public final Executor executor;
    public Executor injectedExecutor;

    public ExecutorProvider(Executor executor2) {
        this.executor = executor2;
    }

    public static ExecutorService createSizeBoundedExecutor(String str, int i) {
        return new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue(i), new ExecutorProvider$$ExternalSyntheticLambda0(str));
    }

    public static void resetExecutors() {
        setupCompatServiceInvoker.injectedExecutor = null;
    }

    public void injectExecutor(Executor executor2) {
        this.injectedExecutor = executor2;
    }
}
