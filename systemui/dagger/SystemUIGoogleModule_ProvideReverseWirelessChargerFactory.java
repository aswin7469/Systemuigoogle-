package com.google.android.systemui.dagger;

import android.content.Context;
import com.google.android.systemui.reversecharging.ReverseWirelessCharger;
import dagger.internal.Preconditions;
import java.util.Optional;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class SystemUIGoogleModule_ProvideReverseWirelessChargerFactory implements Provider {
    public static Optional provideReverseWirelessCharger(Context context) {
        Optional optional;
        if (context.getResources().getBoolean(2131034203)) {
            optional = Optional.of(new ReverseWirelessCharger(context));
        } else {
            optional = Optional.empty();
        }
        Preconditions.checkNotNullFromProvides(optional);
        return optional;
    }
}
