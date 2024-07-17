package com.google.android.systemui.elmyra.actions;

import android.net.Uri;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class SilenceCall$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ SilenceCall f$0;

    public /* synthetic */ SilenceCall$$ExternalSyntheticLambda0(SilenceCall silenceCall) {
        this.f$0 = silenceCall;
    }

    public final void accept(Object obj) {
        Uri uri = (Uri) obj;
        this.f$0.updatePhoneStateListener();
    }
}
