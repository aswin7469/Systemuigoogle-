package com.google.android.setupcompat.internal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
