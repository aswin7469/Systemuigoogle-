package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class BcSmartspaceCardSports extends BcSmartspaceCardSecondary {
    public ImageView mFirstCompetitorLogo;
    public TextView mFirstCompetitorScore;
    public ImageView mSecondCompetitorLogo;
    public TextView mSecondCompetitorScore;
    public TextView mSummaryView;

    public BcSmartspaceCardSports(Context context) {
        super(context);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mSummaryView = (TextView) findViewById(2131362972);
        this.mFirstCompetitorScore = (TextView) findViewById(2131362591);
        this.mSecondCompetitorScore = (TextView) findViewById(2131363562);
        this.mFirstCompetitorLogo = (ImageView) findViewById(2131362590);
        this.mSecondCompetitorLogo = (ImageView) findViewById(2131363561);
    }

    public final void resetUi() {
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mSummaryView, 4);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mFirstCompetitorScore, 4);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mSecondCompetitorScore, 4);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mFirstCompetitorLogo, 4);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mSecondCompetitorLogo, 4);
    }

    public final boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        Bundle bundle;
        boolean z;
        SmartspaceAction baseAction = smartspaceTarget.getBaseAction();
        if (baseAction == null) {
            bundle = null;
        } else {
            bundle = baseAction.getExtras();
        }
        if (bundle == null) {
            return false;
        }
        if (bundle.containsKey("matchTimeSummary")) {
            String string = bundle.getString("matchTimeSummary");
            TextView textView = this.mSummaryView;
            if (textView == null) {
                Log.w("BcSmartspaceCardSports", "No match time summary view to update");
            } else {
                BcSmartspaceTemplateDataUtils.updateVisibility(textView, 0);
                this.mSummaryView.setText(string);
            }
            z = true;
        } else {
            z = false;
        }
        if (bundle.containsKey("firstCompetitorScore")) {
            String string2 = bundle.getString("firstCompetitorScore");
            TextView textView2 = this.mFirstCompetitorScore;
            if (textView2 == null) {
                Log.w("BcSmartspaceCardSports", "No first competitor logo view to update");
            } else {
                BcSmartspaceTemplateDataUtils.updateVisibility(textView2, 0);
                this.mFirstCompetitorScore.setText(string2);
            }
            z = true;
        }
        if (bundle.containsKey("secondCompetitorScore")) {
            String string3 = bundle.getString("secondCompetitorScore");
            TextView textView3 = this.mSecondCompetitorScore;
            if (textView3 == null) {
                Log.w("BcSmartspaceCardSports", "No second competitor logo view to update");
            } else {
                BcSmartspaceTemplateDataUtils.updateVisibility(textView3, 0);
                this.mSecondCompetitorScore.setText(string3);
            }
            z = true;
        }
        if (bundle.containsKey("firstCompetitorLogo")) {
            Bitmap bitmap = (Bitmap) bundle.get("firstCompetitorLogo");
            ImageView imageView = this.mFirstCompetitorLogo;
            if (imageView == null) {
                Log.w("BcSmartspaceCardSports", "No first competitor logo view to update");
            } else {
                BcSmartspaceTemplateDataUtils.updateVisibility(imageView, 0);
                this.mFirstCompetitorLogo.setImageBitmap(bitmap);
            }
            z = true;
        }
        if (!bundle.containsKey("secondCompetitorLogo")) {
            return z;
        }
        Bitmap bitmap2 = (Bitmap) bundle.get("secondCompetitorLogo");
        ImageView imageView2 = this.mSecondCompetitorLogo;
        if (imageView2 == null) {
            Log.w("BcSmartspaceCardSports", "No second competitor logo view to update");
        } else {
            BcSmartspaceTemplateDataUtils.updateVisibility(imageView2, 0);
            this.mSecondCompetitorLogo.setImageBitmap(bitmap2);
        }
        return true;
    }

    public final void setTextColor(int i) {
        this.mSummaryView.setTextColor(i);
        this.mFirstCompetitorScore.setTextColor(i);
        this.mSecondCompetitorScore.setTextColor(i);
    }

    public BcSmartspaceCardSports(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
