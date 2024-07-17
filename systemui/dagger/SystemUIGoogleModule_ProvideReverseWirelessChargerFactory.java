package com.google.android.systemui.dagger;

import android.content.Context;
import com.google.android.systemui.reversecharging.ReverseWirelessCharger;
import dagger.internal.Preconditions;
import java.util.Optional;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SystemUIGoogleModule_ProvideReverseWirelessChargerFactory implements Provider {
    public static Optional provideReverseWirelessCharger(Context context) {
        Optional optional;
        if (context.getResources().getBoolean(2131034203)) {
            optional = Optional.of(new ReverseWirelessCharger());
        } else {
            optional = Optional.empty();
        }
        Preconditions.checkNotNullFromProvides(optional);
        return optional;
    }
}
