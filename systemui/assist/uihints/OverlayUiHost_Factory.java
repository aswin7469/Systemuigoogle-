package com.google.android.systemui.assist.uihints;

import android.content.Context;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class OverlayUiHost_Factory implements Provider {
    public static OverlayUiHost newInstance(Context context, Object obj) {
        return new OverlayUiHost(context, (TouchOutsideHandler) obj);
    }
}
