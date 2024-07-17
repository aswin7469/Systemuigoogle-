package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class TakeScreenshotHandler$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ TakeScreenshotHandler f$0;
    public final /* synthetic */ PendingIntent f$1;

    public /* synthetic */ TakeScreenshotHandler$$ExternalSyntheticLambda0(TakeScreenshotHandler takeScreenshotHandler, PendingIntent pendingIntent) {
        this.f$0 = takeScreenshotHandler;
        this.f$1 = pendingIntent;
    }

    public final void accept(Object obj) {
        boolean z;
        TakeScreenshotHandler takeScreenshotHandler = this.f$0;
        PendingIntent pendingIntent = this.f$1;
        Uri uri = (Uri) obj;
        takeScreenshotHandler.getClass();
        if (pendingIntent != null) {
            try {
                Intent intent = new Intent();
                if (uri != null) {
                    z = true;
                } else {
                    z = false;
                }
                intent.putExtra("success", z);
                intent.putExtra("uri", uri);
                pendingIntent.send(takeScreenshotHandler.mContext, 0, intent);
            } catch (PendingIntent.CanceledException unused) {
                Log.w("TakeScreenshotHandler", "Intent was cancelled");
            }
        }
    }
}
