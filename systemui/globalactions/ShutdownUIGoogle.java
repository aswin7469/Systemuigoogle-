package com.google.android.systemui.globalactions;

import android.content.ContentResolver;
import android.content.Context;
import com.android.systemui.globalactions.ShutdownUi;
import com.android.systemui.statusbar.BlurUtils;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ShutdownUIGoogle extends ShutdownUi {
    public final ContentResolver mContentResolver;

    public ShutdownUIGoogle(Context context, BlurUtils blurUtils, ContentResolver contentResolver) {
        this.mContext = context;
        this.mBlurUtils = blurUtils;
        this.mContentResolver = contentResolver;
    }
}
