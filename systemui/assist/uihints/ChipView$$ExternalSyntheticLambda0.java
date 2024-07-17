package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.util.Log;
import android.view.View;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
