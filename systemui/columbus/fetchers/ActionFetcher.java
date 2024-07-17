package com.google.android.systemui.columbus.fetchers;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ActionFetcher {
    public final CoroutineScope coroutineScope;
    public final Map firstAvailableMap = new LinkedHashMap();

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class ActionListKey {
        public final List actions;

        public ActionListKey(List list) {
            this.actions = list;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ActionListKey) {
                List list = this.actions;
                ActionListKey actionListKey = (ActionListKey) obj;
                if (list.size() == actionListKey.actions.size() && list.containsAll(actionListKey.actions)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            Iterator it = this.actions.iterator();
            if (!it.hasNext()) {
                return 0;
            }
            WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
            throw null;
        }
    }

    public ActionFetcher(CoroutineScope coroutineScope2) {
        this.coroutineScope = coroutineScope2;
    }
}
