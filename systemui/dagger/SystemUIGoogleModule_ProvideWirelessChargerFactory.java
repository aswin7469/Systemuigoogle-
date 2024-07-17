package com.google.android.systemui.dagger;

import com.google.android.systemui.dreamliner.WirelessChargerImpl;
import dagger.internal.Preconditions;
import java.util.Optional;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SystemUIGoogleModule_ProvideWirelessChargerFactory implements Provider {
    public static Optional provideWirelessCharger() {
        Optional optional;
        WirelessChargerImpl wirelessChargerImpl = new WirelessChargerImpl();
        if (wirelessChargerImpl.initHALInterface()) {
            optional = Optional.of(wirelessChargerImpl);
        } else {
            optional = Optional.empty();
        }
        Preconditions.checkNotNullFromProvides(optional);
        return optional;
    }
}
