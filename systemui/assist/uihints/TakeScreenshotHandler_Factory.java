package com.google.android.systemui.assist.uihints;

import android.content.Context;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class TakeScreenshotHandler_Factory implements Provider {
    public static TakeScreenshotHandler newInstance(Context context) {
        return new TakeScreenshotHandler(context);
    }
}
