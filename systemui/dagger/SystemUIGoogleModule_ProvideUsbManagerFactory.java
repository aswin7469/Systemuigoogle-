package com.google.android.systemui.dagger;

import android.content.Context;
import android.hardware.usb.UsbManager;
import dagger.internal.Preconditions;
import java.util.Optional;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class SystemUIGoogleModule_ProvideUsbManagerFactory implements Provider {
    public static Optional provideUsbManager(Context context) {
        Optional ofNullable = Optional.ofNullable((UsbManager) context.getSystemService(UsbManager.class));
        Preconditions.checkNotNullFromProvides(ofNullable);
        return ofNullable;
    }
}
