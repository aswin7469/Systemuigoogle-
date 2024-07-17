package com.google.android.setupcompat.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.setupcompat.util.Logger;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SetupCompatServiceInvoker {
    public static final Logger LOG = new Logger("SetupCompatServiceInvoker");
    public static final long MAX_WAIT_TIME_FOR_CONNECTION_MS = TimeUnit.SECONDS.toMillis(10);
    public static SetupCompatServiceInvoker instance;
    public final Context context;
    public final ExecutorService loggingExecutor;
    public final long waitTimeInMillisForServiceConnection;

    public SetupCompatServiceInvoker(Context context2) {
        this.context = context2;
        ExecutorProvider executorProvider = ExecutorProvider.setupCompatServiceInvoker;
        Executor executor = executorProvider.injectedExecutor;
        this.loggingExecutor = (ExecutorService) (executor == null ? executorProvider.executor : executor);
        this.waitTimeInMillisForServiceConnection = MAX_WAIT_TIME_FOR_CONNECTION_MS;
    }

    public static synchronized SetupCompatServiceInvoker get(Context context2) {
        SetupCompatServiceInvoker setupCompatServiceInvoker;
        synchronized (SetupCompatServiceInvoker.class) {
            try {
                if (instance == null) {
                    instance = new SetupCompatServiceInvoker(context2.getApplicationContext());
                }
                setupCompatServiceInvoker = instance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return setupCompatServiceInvoker;
    }

    public static void setInstanceForTesting(SetupCompatServiceInvoker setupCompatServiceInvoker) {
        instance = setupCompatServiceInvoker;
    }

    public final void logMetricEvent(int i, Bundle bundle) {
        try {
            this.loggingExecutor.execute(new SetupCompatServiceInvoker$$ExternalSyntheticLambda1(this, i, bundle));
        } catch (RejectedExecutionException e) {
            LOG.e(String.format("Metric of type %d dropped since queue is full.", new Object[]{Integer.valueOf(i)}), e);
        }
    }
}
