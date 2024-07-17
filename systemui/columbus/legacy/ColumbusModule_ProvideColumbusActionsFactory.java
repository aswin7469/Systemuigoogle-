package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.legacy.actions.Action;
import com.google.android.systemui.columbus.legacy.actions.UnpinNotifications;
import com.google.android.systemui.columbus.legacy.actions.UserSelectedAction;
import dagger.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Provider;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.SpreadBuilder;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ColumbusModule_ProvideColumbusActionsFactory implements Provider {
    public static List provideColumbusActions(List list, UnpinNotifications unpinNotifications, UserSelectedAction userSelectedAction) {
        SpreadBuilder spreadBuilder = new SpreadBuilder(3);
        spreadBuilder.addSpread(list.toArray(new Action[0]));
        spreadBuilder.add(unpinNotifications);
        spreadBuilder.add(userSelectedAction);
        ArrayList arrayList = spreadBuilder.list;
        List listOf = CollectionsKt__CollectionsKt.listOf(arrayList.toArray(new Action[arrayList.size()]));
        Preconditions.checkNotNullFromProvides(listOf);
        return listOf;
    }
}
