package com.google.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.policy.BatteryController;
import com.google.android.systemui.reversecharging.ReverseChargingViewController;
import dagger.Lazy;
import dagger.internal.Preconditions;
import java.util.Optional;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class CentralSurfacesGoogleModule_ProvideReverseChargingViewControllerOptionalFactory implements Provider {
    public static Optional provideReverseChargingViewControllerOptional(BatteryController batteryController, Lazy lazy) {
        Optional optional;
        if (batteryController.isReverseSupported()) {
            optional = Optional.of((ReverseChargingViewController) lazy.get());
        } else {
            optional = Optional.empty();
        }
        Preconditions.checkNotNullFromProvides(optional);
        return optional;
    }
}
