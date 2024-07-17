package com.google.android.systemui.assist.uihints;

import com.google.android.systemui.assist.AssistManagerGoogle;
import dagger.Lazy;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class TouchInsideHandler$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Lazy f$0;

    public /* synthetic */ TouchInsideHandler$$ExternalSyntheticLambda0(Lazy lazy) {
        this.f$0 = lazy;
    }

    public final void run() {
        ((AssistManagerGoogle) this.f$0.get()).hideAssist();
    }
}
