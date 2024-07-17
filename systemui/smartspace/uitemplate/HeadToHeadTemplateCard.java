package com.google.android.systemui.smartspace.uitemplate;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.systemui.smartspace.BcSmartspaceCardSecondary;
import com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class HeadToHeadTemplateCard extends BcSmartspaceCardSecondary {
    public ImageView mFirstCompetitorIcon;
    public TextView mFirstCompetitorText;
    public TextView mHeadToHeadTitle;
    public ImageView mSecondCompetitorIcon;
    public TextView mSecondCompetitorText;

    public HeadToHeadTemplateCard(Context context) {
        super(context);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mHeadToHeadTitle = (TextView) findViewById(2131362661);
        this.mFirstCompetitorText = (TextView) findViewById(2131362572);
        this.mSecondCompetitorText = (TextView) findViewById(2131363534);
        this.mFirstCompetitorIcon = (ImageView) findViewById(2131362569);
        this.mSecondCompetitorIcon = (ImageView) findViewById(2131363531);
    }

    public final void resetUi() {
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mHeadToHeadTitle, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mFirstCompetitorText, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mSecondCompetitorText, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mFirstCompetitorIcon, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mSecondCompetitorIcon, 8);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ab, code lost:
        if (r1 != false) goto L_0x00b6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean setSmartspaceActions(android.app.smartspace.SmartspaceTarget r10, com.android.systemui.plugins.BcSmartspaceDataPlugin.SmartspaceEventNotifier r11, com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo r12) {
        /*
            r9 = this;
            android.app.smartspace.uitemplatedata.BaseTemplateData r0 = r10.getTemplateData()
            android.app.smartspace.uitemplatedata.HeadToHeadTemplateData r0 = (android.app.smartspace.uitemplatedata.HeadToHeadTemplateData) r0
            boolean r1 = com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggerUtil.containsValidTemplateType(r0)
            java.lang.String r2 = "HeadToHeadTemplateCard"
            r3 = 0
            if (r1 != 0) goto L_0x0015
            java.lang.String r9 = "HeadToHeadTemplateData is null or invalid template type"
            android.util.Log.w(r2, r9)
            return r3
        L_0x0015:
            android.app.smartspace.uitemplatedata.Text r1 = r0.getHeadToHeadTitle()
            r4 = 1
            if (r1 == 0) goto L_0x0034
            android.app.smartspace.uitemplatedata.Text r1 = r0.getHeadToHeadTitle()
            android.widget.TextView r5 = r9.mHeadToHeadTitle
            if (r5 != 0) goto L_0x002a
            java.lang.String r1 = "No head-to-head title view to update"
            android.util.Log.w(r2, r1)
            goto L_0x0034
        L_0x002a:
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.setText(r5, r1)
            android.widget.TextView r1 = r9.mHeadToHeadTitle
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r1, r3)
            r1 = r4
            goto L_0x0035
        L_0x0034:
            r1 = r3
        L_0x0035:
            android.app.smartspace.uitemplatedata.Text r5 = r0.getHeadToHeadFirstCompetitorText()
            if (r5 == 0) goto L_0x0056
            android.app.smartspace.uitemplatedata.Text r5 = r0.getHeadToHeadFirstCompetitorText()
            android.widget.TextView r6 = r9.mFirstCompetitorText
            if (r6 != 0) goto L_0x004d
            java.lang.String r5 = "No first competitor text view to update"
            android.util.Log.w(r2, r5)
            if (r1 == 0) goto L_0x004b
            goto L_0x0055
        L_0x004b:
            r1 = r3
            goto L_0x0056
        L_0x004d:
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.setText(r6, r5)
            android.widget.TextView r1 = r9.mFirstCompetitorText
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r1, r3)
        L_0x0055:
            r1 = r4
        L_0x0056:
            android.app.smartspace.uitemplatedata.Text r5 = r0.getHeadToHeadSecondCompetitorText()
            if (r5 == 0) goto L_0x0077
            android.app.smartspace.uitemplatedata.Text r5 = r0.getHeadToHeadSecondCompetitorText()
            android.widget.TextView r6 = r9.mSecondCompetitorText
            if (r6 != 0) goto L_0x006e
            java.lang.String r5 = "No second competitor text view to update"
            android.util.Log.w(r2, r5)
            if (r1 == 0) goto L_0x006c
            goto L_0x0076
        L_0x006c:
            r1 = r3
            goto L_0x0077
        L_0x006e:
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.setText(r6, r5)
            android.widget.TextView r1 = r9.mSecondCompetitorText
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r1, r3)
        L_0x0076:
            r1 = r4
        L_0x0077:
            android.app.smartspace.uitemplatedata.Icon r5 = r0.getHeadToHeadFirstCompetitorIcon()
            if (r5 == 0) goto L_0x0098
            android.app.smartspace.uitemplatedata.Icon r5 = r0.getHeadToHeadFirstCompetitorIcon()
            android.widget.ImageView r6 = r9.mFirstCompetitorIcon
            if (r6 != 0) goto L_0x008f
            java.lang.String r5 = "No first competitor icon view to update"
            android.util.Log.w(r2, r5)
            if (r1 == 0) goto L_0x008d
            goto L_0x0097
        L_0x008d:
            r1 = r3
            goto L_0x0098
        L_0x008f:
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.setIcon(r6, r5)
            android.widget.ImageView r1 = r9.mFirstCompetitorIcon
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r1, r3)
        L_0x0097:
            r1 = r4
        L_0x0098:
            android.app.smartspace.uitemplatedata.Icon r5 = r0.getHeadToHeadSecondCompetitorIcon()
            if (r5 == 0) goto L_0x00b8
            android.app.smartspace.uitemplatedata.Icon r5 = r0.getHeadToHeadSecondCompetitorIcon()
            android.widget.ImageView r6 = r9.mSecondCompetitorIcon
            if (r6 != 0) goto L_0x00ae
            java.lang.String r5 = "No second competitor icon view to update"
            android.util.Log.w(r2, r5)
            if (r1 == 0) goto L_0x00b7
            goto L_0x00b6
        L_0x00ae:
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.setIcon(r6, r5)
            android.widget.ImageView r1 = r9.mSecondCompetitorIcon
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r1, r3)
        L_0x00b6:
            r3 = r4
        L_0x00b7:
            r1 = r3
        L_0x00b8:
            if (r1 == 0) goto L_0x00cd
            android.app.smartspace.uitemplatedata.TapAction r2 = r0.getHeadToHeadAction()
            if (r2 == 0) goto L_0x00cd
            android.app.smartspace.uitemplatedata.TapAction r5 = r0.getHeadToHeadAction()
            java.lang.String r7 = "HeadToHeadTemplateCard"
            r3 = r9
            r4 = r10
            r6 = r11
            r8 = r12
            com.google.android.systemui.smartspace.BcSmartSpaceUtil.setOnClickListener((android.view.View) r3, (android.app.smartspace.SmartspaceTarget) r4, (android.app.smartspace.uitemplatedata.TapAction) r5, (com.android.systemui.plugins.BcSmartspaceDataPlugin.SmartspaceEventNotifier) r6, (java.lang.String) r7, (com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo) r8)
        L_0x00cd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.uitemplate.HeadToHeadTemplateCard.setSmartspaceActions(android.app.smartspace.SmartspaceTarget, com.android.systemui.plugins.BcSmartspaceDataPlugin$SmartspaceEventNotifier, com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo):boolean");
    }

    public final void setTextColor(int i) {
        this.mFirstCompetitorText.setTextColor(i);
        this.mSecondCompetitorText.setTextColor(i);
    }

    public HeadToHeadTemplateCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
