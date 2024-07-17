package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceTarget;
import java.util.UUID;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
