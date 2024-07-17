package com.google.android.systemui.columbus.fetchers;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class GateFetcher {
    public final Map blockingGateMap = new LinkedHashMap();
    public final CoroutineScope coroutineScope;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class GateCollectionKey {
        public final Set gateSet;

        public GateCollectionKey(Collection collection) {
            this.gateSet = CollectionsKt.toSet(collection);
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof GateCollectionKey) {
                Set set = this.gateSet;
                GateCollectionKey gateCollectionKey = (GateCollectionKey) obj;
                if (set.size() == gateCollectionKey.gateSet.size() && set.containsAll(gateCollectionKey.gateSet)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            Iterator it = this.gateSet.iterator();
            if (!it.hasNext()) {
                return 0;
            }
            WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
            throw null;
        }
    }

    public GateFetcher(CoroutineScope coroutineScope2) {
        this.coroutineScope = coroutineScope2;
    }
}
