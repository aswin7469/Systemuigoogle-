package com.google.android.systemui;

import android.util.ArrayMap;
import com.android.systemui.Dependency$$ExternalSyntheticLambda0;
import dagger.Lazy;
import java.util.Objects;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract /* synthetic */ class SystemUIGoogleInitializer$$ExternalSyntheticOutline0 {
    public static void m(Lazy lazy, Lazy lazy2, int i, ArrayMap arrayMap, Class cls) {
        Objects.requireNonNull(lazy);
        arrayMap.put(cls, new Dependency$$ExternalSyntheticLambda0(lazy2, i));
    }
}
