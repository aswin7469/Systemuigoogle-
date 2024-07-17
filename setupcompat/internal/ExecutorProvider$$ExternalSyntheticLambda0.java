package com.google.android.setupcompat.internal;

import java.util.concurrent.ThreadFactory;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ExecutorProvider$$ExternalSyntheticLambda0 implements ThreadFactory {
    public final /* synthetic */ String f$0;

    public /* synthetic */ ExecutorProvider$$ExternalSyntheticLambda0(String str) {
        this.f$0 = str;
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.f$0);
    }
}
