package com.google.android.systemui.smartspace.uitemplate;

import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.SmartspaceUtils;
import android.app.smartspace.uitemplatedata.SubCardTemplateData;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.BcSmartSpaceUtil;
import com.google.android.systemui.smartspace.BcSmartspaceCardSecondary;
import com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggerUtil;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class SubCardTemplateCard extends BcSmartspaceCardSecondary {
    public ImageView mImageView;
    public TextView mTextView;

    public SubCardTemplateCard(Context context) {
        super(context);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mImageView = (ImageView) findViewById(2131362735);
        this.mTextView = (TextView) findViewById(2131362223);
    }

    public final void resetUi() {
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mImageView, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mTextView, 8);
    }

    public final boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        boolean z;
        SubCardTemplateData templateData = smartspaceTarget.getTemplateData();
        if (!BcSmartspaceCardLoggerUtil.containsValidTemplateType(templateData)) {
            Log.w("SubCardTemplateCard", "SubCardTemplateData is null or invalid template type");
            return false;
        }
        boolean z2 = true;
        if (templateData.getSubCardIcon() != null) {
            BcSmartspaceTemplateDataUtils.setIcon(this.mImageView, templateData.getSubCardIcon());
            BcSmartspaceTemplateDataUtils.updateVisibility(this.mImageView, 0);
            z = true;
        } else {
            z = false;
        }
        if (!SmartspaceUtils.isEmpty(templateData.getSubCardText())) {
            BcSmartspaceTemplateDataUtils.setText(this.mTextView, templateData.getSubCardText());
            BcSmartspaceTemplateDataUtils.updateVisibility(this.mTextView, 0);
        } else {
            z2 = z;
        }
        if (z2 && templateData.getSubCardAction() != null) {
            BcSmartSpaceUtil.setOnClickListener((View) this, smartspaceTarget, templateData.getSubCardAction(), smartspaceEventNotifier, "SubCardTemplateCard", bcSmartspaceCardLoggingInfo);
        }
        return z2;
    }

    public final void setTextColor(int i) {
        this.mTextView.setTextColor(i);
    }

    public SubCardTemplateCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
