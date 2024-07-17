package com.google.android.systemui.columbus.fetchers;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.broadcast.BroadcastDispatcher;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BroadcastFetcher {
    public final BroadcastDispatcher broadcastDispatcher;
    public final Map cachedFlows = new LinkedHashMap();
    public final Context context;
    public final CoroutineScope coroutineScope;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class BroadcastKey {
        public final Integer flags = null;
        public final IntentFilter intentFilter;
        public final String permission = null;
        public final UserHandle userHandle = null;
        public final boolean usingDispatcher;

        public BroadcastKey(IntentFilter intentFilter2, boolean z) {
            this.intentFilter = intentFilter2;
            this.usingDispatcher = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BroadcastKey)) {
                return false;
            }
            BroadcastKey broadcastKey = (BroadcastKey) obj;
            if (Intrinsics.areEqual(this.intentFilter, broadcastKey.intentFilter) && this.usingDispatcher == broadcastKey.usingDispatcher && Intrinsics.areEqual(this.userHandle, broadcastKey.userHandle) && Intrinsics.areEqual(this.permission, broadcastKey.permission) && Intrinsics.areEqual(this.flags, broadcastKey.flags)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            int i;
            int i2;
            int hashCode = this.intentFilter.hashCode() * 31;
            boolean z = this.usingDispatcher;
            if (z) {
                z = true;
            }
            int i3 = (hashCode + (z ? 1 : 0)) * 31;
            int i4 = 0;
            UserHandle userHandle2 = this.userHandle;
            if (userHandle2 == null) {
                i = 0;
            } else {
                i = userHandle2.hashCode();
            }
            int i5 = (i3 + i) * 31;
            String str = this.permission;
            if (str == null) {
                i2 = 0;
            } else {
                i2 = str.hashCode();
            }
            int i6 = (i5 + i2) * 31;
            Integer num = this.flags;
            if (num != null) {
                i4 = num.hashCode();
            }
            return i6 + i4;
        }

        public final String toString() {
            return "BroadcastKey(intentFilter=" + this.intentFilter + ", usingDispatcher=" + this.usingDispatcher + ", userHandle=" + this.userHandle + ", permission=" + this.permission + ", flags=" + this.flags + ")";
        }
    }

    public BroadcastFetcher(CoroutineScope coroutineScope2, Context context2, BroadcastDispatcher broadcastDispatcher2) {
        this.coroutineScope = coroutineScope2;
        this.context = context2;
        this.broadcastDispatcher = broadcastDispatcher2;
    }
}
