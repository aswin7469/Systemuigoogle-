package com.google.android.systemui.assist.uihints;

import android.os.Bundle;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class NgaMessageHandler$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ NgaMessageHandler f$0;
    public final /* synthetic */ Bundle f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ NgaMessageHandler$$ExternalSyntheticLambda1(NgaMessageHandler ngaMessageHandler, Bundle bundle, Runnable runnable) {
        this.f$0 = ngaMessageHandler;
        this.f$1 = bundle;
        this.f$2 = runnable;
    }

    public final void run() {
        this.f$0.processBundle(this.f$1, this.f$2);
    }
}
