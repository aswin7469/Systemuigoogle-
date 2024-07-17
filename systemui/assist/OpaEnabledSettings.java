package com.google.android.systemui.assist;

import android.content.Context;
import android.os.ServiceManager;
import com.android.internal.widget.ILockSettings;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class OpaEnabledSettings {
    public final Context mContext;
    public final ILockSettings mLockSettings = ILockSettings.Stub.asInterface(ServiceManager.getService("lock_settings"));

    public OpaEnabledSettings(Context context) {
        this.mContext = context;
    }
}
