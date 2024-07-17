package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.android.internal.util.ScreenshotHelper;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TakeScreenshotHandler {
    public final Context mContext;
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public final ScreenshotHelper mScreenshotHelper;

    public TakeScreenshotHandler(Context context) {
        this.mContext = context;
        this.mScreenshotHelper = new ScreenshotHelper(context);
    }
}
