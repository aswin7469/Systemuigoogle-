package com.google.android.systemui.power;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ PowerNotificationWarningsGoogleImpl f$0;

    public /* synthetic */ PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda1(PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl) {
        this.f$0 = powerNotificationWarningsGoogleImpl;
    }

    public final void run() {
        ContentProviderClient acquireUnstableContentProviderClient;
        PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl = this.f$0;
        powerNotificationWarningsGoogleImpl.getClass();
        Uri uri = DumpUtils.PROVIDER_URI;
        ContentResolver contentResolver = powerNotificationWarningsGoogleImpl.mContext.getContentResolver();
        try {
            Uri uri2 = DumpUtils.PROVIDER_URI;
            acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri2);
            if (acquireUnstableContentProviderClient == null) {
                Log.w("PowerNotificationWarningsGoogleImpl", "Failed to acquire provider: " + uri2);
                if (acquireUnstableContentProviderClient == null) {
                    return;
                }
            } else {
                acquireUnstableContentProviderClient.call("prepareProviderDump", "", (Bundle) null);
            }
            acquireUnstableContentProviderClient.close();
            return;
        } catch (Exception e) {
            Log.w("PowerNotificationWarningsGoogleImpl", "Failed to call Turbo provider", e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
