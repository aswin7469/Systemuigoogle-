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

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
