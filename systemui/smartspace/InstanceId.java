package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceTarget;
import java.util.UUID;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class InstanceId {
    public static int create(SmartspaceTarget smartspaceTarget) {
        if (smartspaceTarget == null) {
            return SmallHash.hash(UUID.randomUUID().toString());
        }
        String smartspaceTargetId = smartspaceTarget.getSmartspaceTargetId();
        if (smartspaceTargetId == null || smartspaceTargetId.isEmpty()) {
            return SmallHash.hash(String.valueOf(smartspaceTarget.getCreationTimeMillis()));
        }
        return SmallHash.hash(smartspaceTargetId);
    }

    public static int create(String str) {
        if (str == null || str.isEmpty()) {
            return SmallHash.hash(UUID.randomUUID().toString());
        }
        return SmallHash.hash(str);
    }
}
