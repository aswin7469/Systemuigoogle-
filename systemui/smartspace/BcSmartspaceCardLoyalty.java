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
public class BcSmartspaceCardLoyalty extends BcSmartspaceCardGenericImage {
    public TextView mCardPromptView;
    public ImageView mLoyaltyProgramLogoView;
    public TextView mLoyaltyProgramNameView;

    public BcSmartspaceCardLoyalty(Context context) {
        super(context);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mLoyaltyProgramLogoView = (ImageView) findViewById(2131362945);
        this.mLoyaltyProgramNameView = (TextView) findViewById(2131362946);
        this.mCardPromptView = (TextView) findViewById(2131362223);
    }

    public final void resetUi() {
        super.resetUi();
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mImageView, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mLoyaltyProgramLogoView, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mLoyaltyProgramNameView, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mCardPromptView, 8);
    }

    public final void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.mLoyaltyProgramLogoView.setImageBitmap(bitmap);
    }

    public final boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        Bundle bundle;
        super.setSmartspaceActions(smartspaceTarget, smartspaceEventNotifier, bcSmartspaceCardLoggingInfo);
        SmartspaceAction baseAction = smartspaceTarget.getBaseAction();
        if (baseAction == null) {
            bundle = null;
        } else {
            bundle = baseAction.getExtras();
        }
        if (bundle == null) {
            return false;
        }
        boolean containsKey = bundle.containsKey("imageBitmap");
        if (bundle.containsKey("cardPrompt")) {
            String string = bundle.getString("cardPrompt");
            TextView textView = this.mCardPromptView;
            if (textView == null) {
                Log.w("BcSmartspaceCardLoyalty", "No card prompt view to update");
            } else {
                textView.setText(string);
            }
            BcSmartspaceTemplateDataUtils.updateVisibility(this.mCardPromptView, 0);
            if (containsKey) {
                BcSmartspaceTemplateDataUtils.updateVisibility(this.mImageView, 0);
            }
            return true;
        } else if (bundle.containsKey("loyaltyProgramName")) {
            String string2 = bundle.getString("loyaltyProgramName");
            TextView textView2 = this.mLoyaltyProgramNameView;
            if (textView2 == null) {
                Log.w("BcSmartspaceCardLoyalty", "No loyalty program name view to update");
            } else {
                textView2.setText(string2);
            }
            BcSmartspaceTemplateDataUtils.updateVisibility(this.mLoyaltyProgramNameView, 0);
            if (containsKey) {
                BcSmartspaceTemplateDataUtils.updateVisibility(this.mLoyaltyProgramLogoView, 0);
            }
            return true;
        } else {
            if (containsKey) {
                BcSmartspaceTemplateDataUtils.updateVisibility(this.mLoyaltyProgramLogoView, 0);
            }
            return containsKey;
        }
    }

    public final void setTextColor(int i) {
        this.mLoyaltyProgramNameView.setTextColor(i);
        this.mCardPromptView.setTextColor(i);
    }

    public BcSmartspaceCardLoyalty(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
