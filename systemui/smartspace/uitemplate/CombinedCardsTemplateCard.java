package com.google.android.systemui.smartspace.uitemplate;

import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.uitemplatedata.BaseTemplateData;
import android.app.smartspace.uitemplatedata.CombinedCardsTemplateData;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.app.viewcapture.data.ViewNode;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.BcSmartspaceCardSecondary;
import com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggerUtil;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class CombinedCardsTemplateCard extends BcSmartspaceCardSecondary {
    public ConstraintLayout mFirstSubCard;
    public ConstraintLayout mSecondSubCard;

    public CombinedCardsTemplateCard(Context context) {
        super(context);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mFirstSubCard = (ConstraintLayout) findViewById(2131362574);
        this.mSecondSubCard = (ConstraintLayout) findViewById(2131363536);
    }

    public final void resetUi() {
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mFirstSubCard, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mSecondSubCard, 8);
    }

    public final boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        BaseTemplateData baseTemplateData;
        reset$1(smartspaceTarget.getSmartspaceTargetId());
        CombinedCardsTemplateData templateData = smartspaceTarget.getTemplateData();
        if (!BcSmartspaceCardLoggerUtil.containsValidTemplateType(templateData) || templateData.getCombinedCardDataList().isEmpty()) {
            Log.w("CombinedCardsTemplateCard", "TemplateData is null or empty or invalid template type");
            return false;
        }
        List combinedCardDataList = templateData.getCombinedCardDataList();
        BaseTemplateData baseTemplateData2 = (BaseTemplateData) combinedCardDataList.get(0);
        if (combinedCardDataList.size() > 1) {
            baseTemplateData = (BaseTemplateData) combinedCardDataList.get(1);
        } else {
            baseTemplateData = null;
        }
        BaseTemplateData baseTemplateData3 = baseTemplateData;
        if (!setupSubCard(this.mFirstSubCard, baseTemplateData2, smartspaceTarget, smartspaceEventNotifier, bcSmartspaceCardLoggingInfo)) {
            return false;
        }
        if (baseTemplateData3 != null) {
            if (!setupSubCard(this.mSecondSubCard, baseTemplateData3, smartspaceTarget, smartspaceEventNotifier, bcSmartspaceCardLoggingInfo)) {
                return false;
            }
        }
        return true;
    }

    public final void setTextColor(int i) {
        if (this.mFirstSubCard.getChildCount() != 0) {
            ((BcSmartspaceCardSecondary) this.mFirstSubCard.getChildAt(0)).setTextColor(i);
        }
        if (this.mSecondSubCard.getChildCount() != 0) {
            ((BcSmartspaceCardSecondary) this.mSecondSubCard.getChildAt(0)).setTextColor(i);
        }
    }

    public final boolean setupSubCard(ConstraintLayout constraintLayout, BaseTemplateData baseTemplateData, SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        int i;
        if (baseTemplateData == null) {
            BcSmartspaceTemplateDataUtils.updateVisibility(constraintLayout, 8);
            Log.w("CombinedCardsTemplateCard", "Sub-card templateData is null or empty");
            return false;
        }
        switch (baseTemplateData.getTemplateType()) {
            case 2:
                i = 2131559037;
                break;
            case 3:
                i = 2131559038;
                break;
            case 4:
                i = 2131559030;
                break;
            case 5:
                i = 2131559035;
                break;
            case 6:
                i = 2131559032;
                break;
            case ViewNode.WIDTH_FIELD_NUMBER:
                i = 2131559036;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            BcSmartspaceTemplateDataUtils.updateVisibility(constraintLayout, 8);
            Log.w("CombinedCardsTemplateCard", "Combined sub-card res is null. Cannot set it up");
            return false;
        }
        BcSmartspaceCardSecondary bcSmartspaceCardSecondary = (BcSmartspaceCardSecondary) LayoutInflater.from(constraintLayout.getContext()).inflate(i, constraintLayout, false);
        bcSmartspaceCardSecondary.setSmartspaceActions(new SmartspaceTarget.Builder(smartspaceTarget.getSmartspaceTargetId(), smartspaceTarget.getComponentName(), smartspaceTarget.getUserHandle()).setTemplateData(baseTemplateData).build(), smartspaceEventNotifier, bcSmartspaceCardLoggingInfo);
        constraintLayout.removeAllViews();
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(-2, getResources().getDimensionPixelSize(2131165880));
        layoutParams.startToStart = 0;
        layoutParams.endToEnd = 0;
        layoutParams.topToTop = 0;
        layoutParams.bottomToBottom = 0;
        BcSmartspaceTemplateDataUtils.updateVisibility(bcSmartspaceCardSecondary, 0);
        constraintLayout.addView(bcSmartspaceCardSecondary, layoutParams);
        BcSmartspaceTemplateDataUtils.updateVisibility(constraintLayout, 0);
        return true;
    }

    public CombinedCardsTemplateCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
