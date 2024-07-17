package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.util.Log;
import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ChipView$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ PendingIntent f$0;

    public /* synthetic */ ChipView$$ExternalSyntheticLambda0(PendingIntent pendingIntent) {
        this.f$0 = pendingIntent;
    }

    public final void onClick(View view) {
        PendingIntent pendingIntent = this.f$0;
        int i = ChipView.$r8$clinit;
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            Log.w("ChipView", "Pending intent cancelled", e);
        }
    }
}
