package com.google.android.systemui.smartspace.logging;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import java.util.Objects;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BcSmartspaceCardMetadataLoggingInfo {
    public final int mCardTypeId;
    public final int mInstanceId;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class Builder {
        public int mCardTypeId;
        public int mInstanceId;
    }

    public BcSmartspaceCardMetadataLoggingInfo(Builder builder) {
        this.mInstanceId = builder.mInstanceId;
        this.mCardTypeId = builder.mCardTypeId;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BcSmartspaceCardMetadataLoggingInfo)) {
            return false;
        }
        BcSmartspaceCardMetadataLoggingInfo bcSmartspaceCardMetadataLoggingInfo = (BcSmartspaceCardMetadataLoggingInfo) obj;
        if (this.mInstanceId == bcSmartspaceCardMetadataLoggingInfo.mInstanceId && this.mCardTypeId == bcSmartspaceCardMetadataLoggingInfo.mCardTypeId) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.mInstanceId), Integer.valueOf(this.mCardTypeId)});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BcSmartspaceCardMetadataLoggingInfo{mInstanceId=");
        sb.append(this.mInstanceId);
        sb.append(", mCardTypeId=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.mCardTypeId, '}');
    }
}
