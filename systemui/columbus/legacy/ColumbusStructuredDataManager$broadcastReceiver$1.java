package com.google.android.systemui.columbus.legacy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColumbusStructuredDataManager$broadcastReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ ColumbusStructuredDataManager this$0;

    public ColumbusStructuredDataManager$broadcastReceiver$1(ColumbusStructuredDataManager columbusStructuredDataManager) {
        this.this$0 = columbusStructuredDataManager;
    }

    public final void onReceive(Context context, Intent intent) {
        String dataString;
        if (intent != null && (dataString = intent.getDataString()) != null) {
            List split$default = StringsKt__StringsKt.split$default(dataString, new String[]{":"}, 0, 6);
            if (split$default.size() != 2) {
                Log.e("Columbus/StructuredData", "Unexpected package name tokens: ".concat(CollectionsKt___CollectionsKt.joinToString$default(split$default, ",", (CharSequence) null, (CharSequence) null, (Function1) null, 62)));
                return;
            }
            String str = (String) split$default.get(1);
            if (!intent.getBooleanExtra("android.intent.extra.REPLACING", false) && this.this$0.allowPackageList.contains(str)) {
                ColumbusStructuredDataManager columbusStructuredDataManager = this.this$0;
                synchronized (columbusStructuredDataManager.lock) {
                    int length = columbusStructuredDataManager.packageStats.length();
                    for (int i = 0; i < length; i++) {
                        if (Intrinsics.areEqual(str, columbusStructuredDataManager.packageStats.getJSONObject(i).getString("packageName"))) {
                            columbusStructuredDataManager.packageStats.remove(i);
                            columbusStructuredDataManager.storePackageStats();
                            return;
                        }
                    }
                }
            }
        }
    }
}
