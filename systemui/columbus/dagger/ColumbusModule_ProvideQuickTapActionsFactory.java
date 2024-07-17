package com.google.android.systemui.columbus.dagger;

import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ColumbusModule_ProvideQuickTapActionsFactory implements Provider {
    public static List provideQuickTapActions() {
        EmptyList emptyList = EmptyList.INSTANCE;
        Preconditions.checkNotNullFromProvides(emptyList);
        return emptyList;
    }
}
