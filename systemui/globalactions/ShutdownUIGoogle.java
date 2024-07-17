package com.google.android.systemui.globalactions;

import android.content.ContentResolver;
import android.content.Context;
import com.android.systemui.globalactions.ShutdownUi;
import com.android.systemui.statusbar.BlurUtils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ShutdownUIGoogle extends ShutdownUi {
    public final ContentResolver mContentResolver;

    public ShutdownUIGoogle(Context context, BlurUtils blurUtils, ContentResolver contentResolver) {
        this.mContext = context;
        this.mBlurUtils = blurUtils;
        this.mContentResolver = contentResolver;
    }
}
