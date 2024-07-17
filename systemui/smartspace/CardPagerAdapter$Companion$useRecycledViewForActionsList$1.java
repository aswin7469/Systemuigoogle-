package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import com.google.android.systemui.smartspace.CardPagerAdapter;
import java.util.List;
import java.util.function.IntPredicate;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
