package com.google.android.systemui.assist;

import android.content.Context;
import android.os.ServiceManager;
import com.android.internal.widget.ILockSettings;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class OpaEnabledSettings {
    public final Context mContext;
    public final ILockSettings mLockSettings = ILockSettings.Stub.asInterface(ServiceManager.getService("lock_settings"));

    public OpaEnabledSettings(Context context) {
        this.mContext = context;
    }
}
