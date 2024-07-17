package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import java.util.ArrayList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
