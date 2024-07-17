package com.google.android.systemui.columbus.fetchers;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.systemui.columbus.fetchers.GateFetcher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Function;
import kotlin.collections.CollectionsKt__IteratorsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class GateFetcher$getBlockingGateFlow$1 implements Function {
    public final /* synthetic */ GateFetcher this$0;

    public GateFetcher$getBlockingGateFlow$1(GateFetcher gateFetcher) {
        this.this$0 = gateFetcher;
    }

    public final Object apply(Object obj) {
        Iterable iterable = ((GateFetcher.GateCollectionKey) obj).gateSet;
        ArrayList arrayList = new ArrayList(CollectionsKt__IteratorsJVMKt.collectionSizeOrDefault(iterable));
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return FlowKt.stateIn(new GateFetcher$getBlockingGateFlow$1$apply$$inlined$combine$1((Flow[]) CollectionsKt___CollectionsKt.toList(arrayList).toArray(new Flow[0])), this.this$0.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), (Object) null);
        }
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
        throw null;
    }
}
