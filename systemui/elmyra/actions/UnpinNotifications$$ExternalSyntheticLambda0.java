package com.google.android.systemui.elmyra.actions;

import android.net.Uri;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class UnpinNotifications$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ UnpinNotifications f$0;

    public /* synthetic */ UnpinNotifications$$ExternalSyntheticLambda0(UnpinNotifications unpinNotifications) {
        this.f$0 = unpinNotifications;
    }

    public final void accept(Object obj) {
        Uri uri = (Uri) obj;
        this.f$0.updateHeadsUpListener();
    }
}
