package com.google.android.systemui.smartspace.logging;

import com.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceCardDimensionalInfo;
import java.util.Objects;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BcSmartspaceCardLoggingInfo {
    public final int mCardinality;
    public final SmartspaceProto$SmartspaceCardDimensionalInfo mDimensionalInfo;
    public final int mDisplaySurface;
    public int mFeatureType;
    public int mInstanceId;
    public final int mRank;
    public final int mReceivedLatency;
    public BcSmartspaceSubcardLoggingInfo mSubcardInfo;
    public final int mUid;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class Builder {
        public int mCardinality;
        public SmartspaceProto$SmartspaceCardDimensionalInfo mDimensionalInfo;
        public int mDisplaySurface = 1;
        public int mFeatureType;
        public int mInstanceId;
        public int mRank;
        public int mReceivedLatency;
        public BcSmartspaceSubcardLoggingInfo mSubcardInfo;
        public int mUid;

        public final BcSmartspaceCardLoggingInfo build() {
            return new BcSmartspaceCardLoggingInfo(this);
        }
    }

    public BcSmartspaceCardLoggingInfo(Builder builder) {
        this.mInstanceId = builder.mInstanceId;
        this.mDisplaySurface = builder.mDisplaySurface;
        this.mRank = builder.mRank;
        this.mCardinality = builder.mCardinality;
        this.mFeatureType = builder.mFeatureType;
        this.mReceivedLatency = builder.mReceivedLatency;
        this.mUid = builder.mUid;
        this.mSubcardInfo = builder.mSubcardInfo;
        this.mDimensionalInfo = builder.mDimensionalInfo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BcSmartspaceCardLoggingInfo)) {
            return false;
        }
        BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo = (BcSmartspaceCardLoggingInfo) obj;
        if (this.mInstanceId == bcSmartspaceCardLoggingInfo.mInstanceId && this.mDisplaySurface == bcSmartspaceCardLoggingInfo.mDisplaySurface && this.mRank == bcSmartspaceCardLoggingInfo.mRank && this.mCardinality == bcSmartspaceCardLoggingInfo.mCardinality && this.mFeatureType == bcSmartspaceCardLoggingInfo.mFeatureType && this.mReceivedLatency == bcSmartspaceCardLoggingInfo.mReceivedLatency && this.mUid == bcSmartspaceCardLoggingInfo.mUid && Objects.equals(this.mSubcardInfo, bcSmartspaceCardLoggingInfo.mSubcardInfo) && Objects.equals(this.mDimensionalInfo, bcSmartspaceCardLoggingInfo.mDimensionalInfo)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.mInstanceId), Integer.valueOf(this.mDisplaySurface), Integer.valueOf(this.mRank), Integer.valueOf(this.mCardinality), Integer.valueOf(this.mFeatureType), Integer.valueOf(this.mReceivedLatency), Integer.valueOf(this.mUid), this.mSubcardInfo});
    }

    public final String toString() {
        return "instance_id = " + this.mInstanceId + ", feature type = " + this.mFeatureType + ", display surface = " + this.mDisplaySurface + ", rank = " + this.mRank + ", cardinality = " + this.mCardinality + ", receivedLatencyMillis = " + this.mReceivedLatency + ", uid = " + this.mUid + ", subcardInfo = " + this.mSubcardInfo + ", dimensionalInfo = " + this.mDimensionalInfo;
    }
}
