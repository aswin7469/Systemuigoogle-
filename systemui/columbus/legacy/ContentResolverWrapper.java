package com.google.android.systemui.columbus.legacy;

import android.content.ContentResolver;
import android.content.Context;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ContentResolverWrapper {
    public final ContentResolver contentResolver;

    public ContentResolverWrapper(Context context) {
        this.contentResolver = context.getContentResolver();
    }
}
