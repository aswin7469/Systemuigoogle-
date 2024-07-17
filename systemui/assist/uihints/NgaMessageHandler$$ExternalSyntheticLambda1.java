package com.google.android.systemui.assist.uihints;

import android.os.Bundle;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
