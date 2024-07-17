package com.google.android.systemui.smartspace.logging;

import com.android.systemui.shared.system.SysUiStatsLog;
import com.android.systemui.smartspace.SmartspaceProtoLite$SmartSpaceCardMetadata;
import com.android.systemui.smartspace.SmartspaceProtoLite$SmartSpaceSubcards;
import com.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceCardDimensionalInfo;
import com.google.android.systemui.smartspace.BcSmartspaceEvent;
import com.google.protobuf.nano.MessageNano;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class BcSmartspaceCardLogger {
    public static void log(BcSmartspaceEvent bcSmartspaceEvent, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        byte[] bArr;
        List list;
        BcSmartspaceSubcardLoggingInfo bcSmartspaceSubcardLoggingInfo = bcSmartspaceCardLoggingInfo.mSubcardInfo;
        byte[] bArr2 = null;
        if (bcSmartspaceSubcardLoggingInfo == null || (list = bcSmartspaceSubcardLoggingInfo.mSubcards) == null || list.isEmpty()) {
            bArr = null;
        } else {
            ArrayList arrayList = new ArrayList();
            List list2 = bcSmartspaceSubcardLoggingInfo.mSubcards;
            for (int i = 0; i < list2.size(); i++) {
                BcSmartspaceCardMetadataLoggingInfo bcSmartspaceCardMetadataLoggingInfo = (BcSmartspaceCardMetadataLoggingInfo) list2.get(i);
                SmartspaceProtoLite$SmartSpaceCardMetadata.Builder newBuilder = SmartspaceProtoLite$SmartSpaceCardMetadata.newBuilder();
                int i2 = bcSmartspaceCardMetadataLoggingInfo.mInstanceId;
                newBuilder.copyOnWrite();
                SmartspaceProtoLite$SmartSpaceCardMetadata.m873$$Nest$msetInstanceId((SmartspaceProtoLite$SmartSpaceCardMetadata) newBuilder.instance, i2);
                newBuilder.copyOnWrite();
                SmartspaceProtoLite$SmartSpaceCardMetadata.m872$$Nest$msetCardTypeId((SmartspaceProtoLite$SmartSpaceCardMetadata) newBuilder.instance, bcSmartspaceCardMetadataLoggingInfo.mCardTypeId);
                arrayList.add((SmartspaceProtoLite$SmartSpaceCardMetadata) newBuilder.build());
            }
            SmartspaceProtoLite$SmartSpaceSubcards.Builder newBuilder2 = SmartspaceProtoLite$SmartSpaceSubcards.newBuilder();
            int i3 = bcSmartspaceSubcardLoggingInfo.mClickedSubcardIndex;
            newBuilder2.copyOnWrite();
            SmartspaceProtoLite$SmartSpaceSubcards.m875$$Nest$msetClickedSubcardIndex((SmartspaceProtoLite$SmartSpaceSubcards) newBuilder2.instance, i3);
            newBuilder2.copyOnWrite();
            SmartspaceProtoLite$SmartSpaceSubcards.m874$$Nest$maddAllSubcards((SmartspaceProtoLite$SmartSpaceSubcards) newBuilder2.instance, arrayList);
            bArr = ((SmartspaceProtoLite$SmartSpaceSubcards) newBuilder2.build()).toByteArray();
        }
        SmartspaceProto$SmartspaceCardDimensionalInfo smartspaceProto$SmartspaceCardDimensionalInfo = bcSmartspaceCardLoggingInfo.mDimensionalInfo;
        if (smartspaceProto$SmartspaceCardDimensionalInfo != null) {
            bArr2 = MessageNano.toByteArray(smartspaceProto$SmartspaceCardDimensionalInfo);
        }
        SysUiStatsLog.write(bcSmartspaceEvent.getId(), bcSmartspaceCardLoggingInfo.mInstanceId, bcSmartspaceCardLoggingInfo.mDisplaySurface, bcSmartspaceCardLoggingInfo.mRank, bcSmartspaceCardLoggingInfo.mCardinality, bcSmartspaceCardLoggingInfo.mFeatureType, bcSmartspaceCardLoggingInfo.mUid, 0, 0, bcSmartspaceCardLoggingInfo.mReceivedLatency, bArr, bArr2);
    }
}
