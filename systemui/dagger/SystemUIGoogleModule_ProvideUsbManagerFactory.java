package com.google.android.systemui.dagger;

import android.content.Context;
import android.hardware.usb.UsbManager;
import dagger.internal.Preconditions;
import java.util.Optional;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SystemUIGoogleModule_ProvideUsbManagerFactory implements Provider {
    public static Optional provideUsbManager(Context context) {
        Optional ofNullable = Optional.ofNullable((UsbManager) context.getSystemService(UsbManager.class));
        Preconditions.checkNotNullFromProvides(ofNullable);
        return ofNullable;
    }
}
