package com.google.android.systemui.elmyra.actions;

import android.net.Uri;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class DeskClockAction$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ DeskClockAction f$0;

    public /* synthetic */ DeskClockAction$$ExternalSyntheticLambda0(DeskClockAction deskClockAction) {
        this.f$0 = deskClockAction;
    }

    public final void accept(Object obj) {
        Uri uri = (Uri) obj;
        this.f$0.updateBroadcastReceiver();
    }
}
