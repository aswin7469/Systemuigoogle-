package com.google.android.systemui.elmyra.actions;

import android.net.Uri;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
