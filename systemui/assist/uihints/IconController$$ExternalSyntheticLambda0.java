package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.util.Log;
import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class IconController$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ IconController f$0;

    public /* synthetic */ IconController$$ExternalSyntheticLambda0(IconController iconController, int i) {
        this.$r8$classId = i;
        this.f$0 = iconController;
    }

    public final void onClick(View view) {
        int i = this.$r8$classId;
        IconController iconController = this.f$0;
        switch (i) {
            case 0:
                PendingIntent pendingIntent = iconController.mOnKeyboardIconTap;
                if (pendingIntent != null) {
                    try {
                        pendingIntent.send();
                        return;
                    } catch (PendingIntent.CanceledException e) {
                        Log.e("IconController", "Pending intent cancelled", e);
                        return;
                    }
                } else {
                    return;
                }
            default:
                PendingIntent pendingIntent2 = iconController.mOnZerostateIconTap;
                if (pendingIntent2 != null) {
                    try {
                        pendingIntent2.send();
                        return;
                    } catch (PendingIntent.CanceledException e2) {
                        Log.e("IconController", "Pending intent cancelled", e2);
                        return;
                    }
                } else {
                    return;
                }
        }
    }
}
