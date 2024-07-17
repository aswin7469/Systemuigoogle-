package com.google.android.systemui.columbus.dagger;

import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.google.android.systemui.columbus.ColumbusStarter;
import dagger.Lazy;
import javax.inject.Provider;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ColumbusModule_ProvideColumbusStarterFactory implements Provider {
    public static ColumbusStarter provideColumbusStarter(Lazy lazy) {
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        Object obj = lazy.get();
        Intrinsics.checkNotNull(obj);
        return (ColumbusStarter) obj;
    }
}
