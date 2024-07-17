package com.google.android.systemui.power;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda1(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    public final void run() {
        ContentProviderClient acquireUnstableContentProviderClient;
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl = (PowerNotificationWarningsGoogleImpl) obj;
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
                    break;
                }
                break;
            default:
                try {
                    PowerNotificationWarningsGoogleImpl.this.mContext.getContentResolver().call("com.google.android.flipendo.api", "force_enable_flipendo_method", (String) null, (Bundle) null);
                    return;
                } catch (Exception e2) {
                    Log.e("PowerUtils", "enableFlipendo() failed", e2);
                    return;
                }
        }
        throw th;
    }
}
