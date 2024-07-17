package com.google.android.systemui.smartspace.logging;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.uitemplatedata.BaseTemplateData;
import android.os.Bundle;
import com.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceCardDimensionalInfo;
import com.android.systemui.smartspace.nano.SmartspaceProto$SmartspaceFeatureDimension;
import com.google.android.systemui.smartspace.InstanceId;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class BcSmartspaceCardLoggerUtil {
    public static boolean containsValidTemplateType(BaseTemplateData baseTemplateData) {
        if (baseTemplateData == null || baseTemplateData.getTemplateType() == 0 || baseTemplateData.getTemplateType() == 8) {
            return false;
        }
        return true;
    }

    public static SmartspaceProto$SmartspaceCardDimensionalInfo createDimensionalLoggingInfo(BaseTemplateData baseTemplateData) {
        if (baseTemplateData == null || baseTemplateData.getPrimaryItem() == null || baseTemplateData.getPrimaryItem().getTapAction() == null) {
            return null;
        }
        Bundle extras = baseTemplateData.getPrimaryItem().getTapAction().getExtras();
        ArrayList arrayList = new ArrayList();
        if (extras != null && !extras.isEmpty()) {
            ArrayList<Integer> integerArrayList = extras.getIntegerArrayList("ss_card_dimension_ids");
            ArrayList<Integer> integerArrayList2 = extras.getIntegerArrayList("ss_card_dimension_values");
            if (!(integerArrayList == null || integerArrayList2 == null || integerArrayList.size() != integerArrayList2.size())) {
                for (int i = 0; i < integerArrayList.size(); i++) {
                    SmartspaceProto$SmartspaceFeatureDimension smartspaceProto$SmartspaceFeatureDimension = new SmartspaceProto$SmartspaceFeatureDimension();
                    smartspaceProto$SmartspaceFeatureDimension.featureDimensionId = integerArrayList.get(i).intValue();
                    smartspaceProto$SmartspaceFeatureDimension.featureDimensionValue = integerArrayList2.get(i).intValue();
                    arrayList.add(smartspaceProto$SmartspaceFeatureDimension);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        SmartspaceProto$SmartspaceCardDimensionalInfo smartspaceProto$SmartspaceCardDimensionalInfo = new SmartspaceProto$SmartspaceCardDimensionalInfo();
        smartspaceProto$SmartspaceCardDimensionalInfo.featureDimensions = (SmartspaceProto$SmartspaceFeatureDimension[]) arrayList.toArray(new SmartspaceProto$SmartspaceFeatureDimension[arrayList.size()]);
        return smartspaceProto$SmartspaceCardDimensionalInfo;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.systemui.smartspace.logging.BcSmartspaceCardMetadataLoggingInfo$Builder, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v10, types: [java.lang.Object, com.google.android.systemui.smartspace.logging.BcSmartspaceSubcardLoggingInfo] */
    public static BcSmartspaceSubcardLoggingInfo createSubcardLoggingInfo(SmartspaceTarget smartspaceTarget) {
        if (smartspaceTarget == null || smartspaceTarget.getBaseAction() == null || smartspaceTarget.getBaseAction().getExtras() == null || smartspaceTarget.getBaseAction().getExtras().isEmpty() || smartspaceTarget.getBaseAction().getExtras().getInt("subcardType", -1) == -1) {
            return null;
        }
        SmartspaceAction baseAction = smartspaceTarget.getBaseAction();
        int create = InstanceId.create(baseAction.getExtras().getString("subcardId"));
        int i = baseAction.getExtras().getInt("subcardType");
        ? obj = new Object();
        obj.mInstanceId = create;
        obj.mCardTypeId = i;
        BcSmartspaceCardMetadataLoggingInfo bcSmartspaceCardMetadataLoggingInfo = new BcSmartspaceCardMetadataLoggingInfo(obj);
        ArrayList arrayList = new ArrayList();
        arrayList.add(bcSmartspaceCardMetadataLoggingInfo);
        ? obj2 = new Object();
        obj2.mSubcards = arrayList;
        obj2.mClickedSubcardIndex = 0;
        return obj2;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.systemui.smartspace.logging.BcSmartspaceCardMetadataLoggingInfo$Builder, java.lang.Object] */
    public static void createSubcardLoggingInfoHelper(List list, BaseTemplateData.SubItemInfo subItemInfo) {
        if (subItemInfo != null && subItemInfo.getLoggingInfo() != null) {
            BaseTemplateData.SubItemLoggingInfo loggingInfo = subItemInfo.getLoggingInfo();
            ? obj = new Object();
            obj.mCardTypeId = loggingInfo.getFeatureType();
            obj.mInstanceId = loggingInfo.getInstanceId();
            list.add(new BcSmartspaceCardMetadataLoggingInfo(obj));
        }
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [com.google.android.systemui.smartspace.logging.BcSmartspaceCardMetadataLoggingInfo$Builder, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v3, types: [java.lang.Object, com.google.android.systemui.smartspace.logging.BcSmartspaceSubcardLoggingInfo] */
    public static void tryForcePrimaryFeatureTypeAndInjectWeatherSubcard(BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo, SmartspaceTarget smartspaceTarget) {
        if (bcSmartspaceCardLoggingInfo.mFeatureType == 1) {
            bcSmartspaceCardLoggingInfo.mFeatureType = 39;
            bcSmartspaceCardLoggingInfo.mInstanceId = InstanceId.create("date_card_794317_92634");
            if (smartspaceTarget != null && !"date_card_794317_92634".equals(smartspaceTarget.getSmartspaceTargetId())) {
                if (bcSmartspaceCardLoggingInfo.mSubcardInfo == null) {
                    ArrayList arrayList = new ArrayList();
                    ? obj = new Object();
                    obj.mSubcards = arrayList;
                    obj.mClickedSubcardIndex = 0;
                    bcSmartspaceCardLoggingInfo.mSubcardInfo = obj;
                }
                BcSmartspaceSubcardLoggingInfo bcSmartspaceSubcardLoggingInfo = bcSmartspaceCardLoggingInfo.mSubcardInfo;
                if (bcSmartspaceSubcardLoggingInfo.mSubcards == null) {
                    bcSmartspaceSubcardLoggingInfo.mSubcards = new ArrayList();
                }
                if (bcSmartspaceCardLoggingInfo.mSubcardInfo.mSubcards.size() == 0 || !(bcSmartspaceCardLoggingInfo.mSubcardInfo.mSubcards.get(0) == null || ((BcSmartspaceCardMetadataLoggingInfo) bcSmartspaceCardLoggingInfo.mSubcardInfo.mSubcards.get(0)).mCardTypeId == 1)) {
                    List list = bcSmartspaceCardLoggingInfo.mSubcardInfo.mSubcards;
                    ? obj2 = new Object();
                    obj2.mInstanceId = InstanceId.create(smartspaceTarget);
                    obj2.mCardTypeId = 1;
                    list.add(0, new BcSmartspaceCardMetadataLoggingInfo(obj2));
                    BcSmartspaceSubcardLoggingInfo bcSmartspaceSubcardLoggingInfo2 = bcSmartspaceCardLoggingInfo.mSubcardInfo;
                    int i = bcSmartspaceSubcardLoggingInfo2.mClickedSubcardIndex;
                    if (i > 0) {
                        bcSmartspaceSubcardLoggingInfo2.mClickedSubcardIndex = i + 1;
                    }
                }
            }
        }
    }

    public static void tryForcePrimaryFeatureTypeOrUpdateLogInfoFromTemplateData(BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo, BaseTemplateData baseTemplateData) {
        if (bcSmartspaceCardLoggingInfo.mFeatureType == 1) {
            bcSmartspaceCardLoggingInfo.mFeatureType = 39;
            bcSmartspaceCardLoggingInfo.mInstanceId = InstanceId.create("date_card_794317_92634");
        } else if (baseTemplateData != null && baseTemplateData.getPrimaryItem() != null && baseTemplateData.getPrimaryItem().getLoggingInfo() != null) {
            int featureType = baseTemplateData.getPrimaryItem().getLoggingInfo().getFeatureType();
            if (featureType > 0) {
                bcSmartspaceCardLoggingInfo.mFeatureType = featureType;
            }
            int instanceId = baseTemplateData.getPrimaryItem().getLoggingInfo().getInstanceId();
            if (instanceId > 0) {
                bcSmartspaceCardLoggingInfo.mInstanceId = instanceId;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v3, types: [java.lang.Object, com.google.android.systemui.smartspace.logging.BcSmartspaceSubcardLoggingInfo] */
    public static BcSmartspaceSubcardLoggingInfo createSubcardLoggingInfo(BaseTemplateData baseTemplateData) {
        if (baseTemplateData == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        createSubcardLoggingInfoHelper(arrayList, baseTemplateData.getSubtitleItem());
        createSubcardLoggingInfoHelper(arrayList, baseTemplateData.getSubtitleSupplementalItem());
        createSubcardLoggingInfoHelper(arrayList, baseTemplateData.getSupplementalLineItem());
        if (arrayList.isEmpty()) {
            return null;
        }
        ? obj = new Object();
        obj.mSubcards = arrayList;
        obj.mClickedSubcardIndex = 0;
        return obj;
    }
}
