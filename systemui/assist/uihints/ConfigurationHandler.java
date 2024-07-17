package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import java.util.ArrayList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ConfigurationHandler implements NgaMessageHandler.ConfigInfoListener {
    public final Context mContext;

    public ConfigurationHandler(Context context) {
        this.mContext = context;
    }

    public final void onConfigInfo(NgaMessageHandler.ConfigInfo configInfo) {
        PendingIntent pendingIntent = configInfo.configurationCallback;
        if (pendingIntent != null) {
            Intent intent = new Intent();
            ArrayList arrayList = new ArrayList();
            arrayList.add("go_back");
            arrayList.add("take_screenshot");
            arrayList.add("half_listening_full");
            arrayList.add("input_chips");
            arrayList.add("actions_without_ui");
            arrayList.add("global_actions");
            intent.putCharSequenceArrayListExtra("flags", arrayList);
            intent.putExtra("version", 3);
            try {
                pendingIntent.send(this.mContext, 0, intent);
            } catch (PendingIntent.CanceledException e) {
                Log.e("ConfigurationHandler", "Pending intent canceled", e);
            }
        }
    }
}
