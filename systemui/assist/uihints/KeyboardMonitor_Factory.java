package com.google.android.systemui.assist.uihints;

import android.content.Context;
import java.util.Optional;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class KeyboardMonitor_Factory implements Provider {
    public static KeyboardMonitor newInstance(Context context, Optional optional) {
        return new KeyboardMonitor(context, optional);
    }
}
