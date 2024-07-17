package com.google.android.systemui.columbus.legacy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusStructuredDataManager$broadcastReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ ColumbusStructuredDataManager this$0;

    public ColumbusStructuredDataManager$broadcastReceiver$1(ColumbusStructuredDataManager columbusStructuredDataManager) {
        this.this$0 = columbusStructuredDataManager;
    }

    public final void onReceive(Context context, Intent intent) {
        String dataString;
        if (intent != null && (dataString = intent.getDataString()) != null) {
            List split$default = StringsKt.split$default(dataString, new String[]{":"}, 0, 6);
            if (split$default.size() != 2) {
                Log.e("Columbus/StructuredData", "Unexpected package name tokens: ".concat(CollectionsKt.joinToString$default(split$default, ",", (CharSequence) null, (CharSequence) null, (Function1) null, 62)));
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
