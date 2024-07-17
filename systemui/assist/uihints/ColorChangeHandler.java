package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColorChangeHandler implements NgaMessageHandler.ConfigInfoListener {
    public final Context mContext;
    public boolean mIsDark;
    public PendingIntent mPendingIntent;

    public ColorChangeHandler(Context context) {
        this.mContext = context;
    }

    public final void onConfigInfo(NgaMessageHandler.ConfigInfo configInfo) {
        this.mPendingIntent = configInfo.onColorChanged;
        sendColor();
    }

    public final void sendColor() {
        if (this.mPendingIntent != null) {
            Intent intent = new Intent();
            intent.putExtra("is_dark", this.mIsDark);
            try {
                this.mPendingIntent.send(this.mContext, 0, intent);
            } catch (PendingIntent.CanceledException unused) {
                Log.w("ColorChangeHandler", "SysUI assist UI color changed PendingIntent canceled");
            }
        }
    }
}
