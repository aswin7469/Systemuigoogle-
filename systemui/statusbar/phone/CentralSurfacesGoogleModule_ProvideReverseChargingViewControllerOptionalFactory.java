package com.google.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.policy.BatteryController;
import com.google.android.systemui.reversecharging.ReverseChargingViewController;
import dagger.Lazy;
import dagger.internal.Preconditions;
import java.util.Optional;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
