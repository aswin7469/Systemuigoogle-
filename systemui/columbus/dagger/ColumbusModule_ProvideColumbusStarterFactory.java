package com.google.android.systemui.columbus.dagger;

import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.google.android.systemui.columbus.ColumbusStarter;
import dagger.Lazy;
import javax.inject.Provider;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ColumbusModule_ProvideColumbusStarterFactory implements Provider {
    public static ColumbusStarter provideColumbusStarter(Lazy lazy) {
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        Object obj = lazy.get();
        Intrinsics.checkNotNull(obj);
        return (ColumbusStarter) obj;
    }
}
