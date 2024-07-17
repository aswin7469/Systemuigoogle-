package com.google.android.settingslib.dcservice;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DcServiceClientImpl implements DcServiceClient {
    public static final Uri PROXY_AUTHORITY = new Uri.Builder().scheme("content").authority("com.google.android.settings.intelligence.provider.dcservice").build();
    public final ContentResolver contentResolver;

    public DcServiceClientImpl(Context context, LocalBluetoothManager localBluetoothManager) {
        this.contentResolver = context.getContentResolver();
    }
}
