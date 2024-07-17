package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TouchOutsideHandler implements NgaMessageHandler.ConfigInfoListener {
    public PendingIntent mTouchOutside;

    public final void onConfigInfo(NgaMessageHandler.ConfigInfo configInfo) {
        this.mTouchOutside = configInfo.onTouchOutside;
    }
}
