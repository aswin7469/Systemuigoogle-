package com.google.android.systemui.columbus.fetchers;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.systemui.columbus.fetchers.ActionFetcher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ActionFetcher$getFirstAvailableFlow$1 implements Function {
    public final /* synthetic */ ActionFetcher this$0;

    public ActionFetcher$getFirstAvailableFlow$1(ActionFetcher actionFetcher) {
        this.this$0 = actionFetcher;
    }

    public final Object apply(Object obj) {
        List list = ((ActionFetcher.ActionListKey) obj).actions;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list));
        Iterator it = list.iterator();
        if (!it.hasNext()) {
            return FlowKt.stateIn(new ActionFetcher$getFirstAvailableFlow$1$apply$$inlined$combine$1((Flow[]) CollectionsKt.toList(arrayList).toArray(new Flow[0])), this.this$0.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), (Object) null);
        }
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
        throw null;
    }
}
