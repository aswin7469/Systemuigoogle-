package com.google.android.systemui.dagger;

import android.content.Context;
import com.google.android.systemui.dreamliner.WirelessChargerImpl;
import dagger.internal.Preconditions;
import java.util.Optional;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class SystemUIGoogleModule_ProvideWirelessChargerFactory implements Provider {
    public static Optional provideWirelessCharger(Context context) {
        Optional optional;
        WirelessChargerImpl wirelessChargerImpl = new WirelessChargerImpl(context);
        if (wirelessChargerImpl.initHALInterface()) {
            optional = Optional.of(wirelessChargerImpl);
        } else {
            optional = Optional.empty();
        }
        Preconditions.checkNotNullFromProvides(optional);
        return optional;
    }
}
