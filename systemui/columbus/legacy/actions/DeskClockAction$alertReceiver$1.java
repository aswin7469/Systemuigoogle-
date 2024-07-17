package com.google.android.systemui.columbus.legacy.actions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DeskClockAction$alertReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ DeskClockAction this$0;

    public DeskClockAction$alertReceiver$1(DeskClockAction deskClockAction) {
        this.this$0 = deskClockAction;
    }

    public final void onReceive(Context context, Intent intent) {
        String str;
        String str2 = null;
        if (intent != null) {
            str = intent.getAction();
        } else {
            str = null;
        }
        if (Intrinsics.areEqual(str, this.this$0.getAlertAction())) {
            this.this$0.alertFiring = true;
        } else {
            if (intent != null) {
                str2 = intent.getAction();
            }
            if (Intrinsics.areEqual(str2, this.this$0.getDoneAction())) {
                this.this$0.alertFiring = false;
            }
        }
        DeskClockAction deskClockAction = this.this$0;
        deskClockAction.setAvailable(deskClockAction.alertFiring);
    }
}
