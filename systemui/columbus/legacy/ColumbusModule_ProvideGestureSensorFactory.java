package com.google.android.systemui.columbus.legacy;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.settings.UserTrackerImpl;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;
import javax.inject.Provider;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ColumbusModule_ProvideGestureSensorFactory implements Provider {
    public static GestureSensor provideGestureSensor(Context context, ColumbusSettings columbusSettings, FeatureFlags featureFlags, Lazy lazy, Lazy lazy2, Lazy lazy3) {
        boolean z = false;
        if (Settings.Secure.getIntForUser(columbusSettings.contentResolver, "columbus_ap_sensor", 0, ((UserTrackerImpl) columbusSettings.userTracker).getUserId()) != 0) {
            z = true;
        }
        if (z || !context.getPackageManager().hasSystemFeature("android.hardware.context_hub")) {
            Log.i("Columbus/Module", "Creating AP sensor");
            Object obj = lazy3.get();
            Intrinsics.checkNotNull(obj);
            return (GestureSensor) obj;
        }
        if (((FeatureFlagsClassicRelease) featureFlags).isEnabled(Flags.QUICK_TAP_IN_PCC)) {
            Log.i("Columbus/Module", "Creating CHRE sensor delegator");
            Object obj2 = lazy2.get();
            Intrinsics.checkNotNull(obj2);
            return (GestureSensor) obj2;
        }
        Log.i("Columbus/Module", "Creating CHRE sensor");
        Object obj3 = lazy.get();
        Intrinsics.checkNotNull(obj3);
        return (GestureSensor) obj3;
    }
}
