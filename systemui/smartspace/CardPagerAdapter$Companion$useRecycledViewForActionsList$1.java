package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import com.google.android.systemui.smartspace.CardPagerAdapter;
import java.util.List;
import java.util.function.IntPredicate;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CardPagerAdapter$Companion$useRecycledViewForActionsList$1 implements IntPredicate {
    public final /* synthetic */ List $newActionsList;
    public final /* synthetic */ List $recycledActionsList;

    public CardPagerAdapter$Companion$useRecycledViewForActionsList$1(List list, List list2) {
        this.$newActionsList = list;
        this.$recycledActionsList = list2;
    }

    public final boolean test(int i) {
        return CardPagerAdapter.Companion.useRecycledViewForAction((SmartspaceAction) this.$newActionsList.get(i), (SmartspaceAction) this.$recycledActionsList.get(i));
    }
}
