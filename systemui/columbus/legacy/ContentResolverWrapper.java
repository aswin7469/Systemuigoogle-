package com.google.android.systemui.columbus.legacy;

import android.content.ContentResolver;
import android.content.Context;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ContentResolverWrapper {
    public final ContentResolver contentResolver;

    public ContentResolverWrapper(Context context) {
        this.contentResolver = context.getContentResolver();
    }
}
