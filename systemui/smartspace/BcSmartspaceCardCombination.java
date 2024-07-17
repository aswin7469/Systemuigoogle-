package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class BcSmartspaceCardCombination extends BcSmartspaceCardSecondary {
    public ConstraintLayout mFirstSubCard;
    public ConstraintLayout mSecondSubCard;

    public BcSmartspaceCardCombination(Context context) {
        super(context);
    }

    public final boolean fillSubCard(ConstraintLayout constraintLayout, SmartspaceTarget smartspaceTarget, SmartspaceAction smartspaceAction, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        boolean z;
        CharSequence charSequence;
        ConstraintLayout constraintLayout2 = constraintLayout;
        TextView textView = (TextView) constraintLayout.findViewById(2131363735);
        ImageView imageView = (ImageView) constraintLayout.findViewById(2131363734);
        if (textView == null) {
            Log.w("BcSmartspaceCardCombination", "No sub-card text field to update");
            return false;
        } else if (imageView == null) {
            Log.w("BcSmartspaceCardCombination", "No sub-card image field to update");
            return false;
        } else {
            BcSmartSpaceUtil.setOnClickListener((View) constraintLayout, smartspaceTarget, smartspaceAction, smartspaceEventNotifier, "BcSmartspaceCardCombination", bcSmartspaceCardLoggingInfo, 0);
            Icon icon = smartspaceAction.getIcon();
            Context context = getContext();
            Drawable iconDrawableWithCustomSize = BcSmartSpaceUtil.getIconDrawableWithCustomSize(icon, context, context.getResources().getDimensionPixelSize(2131165919));
            boolean z2 = true;
            if (iconDrawableWithCustomSize == null) {
                BcSmartspaceTemplateDataUtils.updateVisibility(imageView, 8);
                z = false;
            } else {
                imageView.setImageDrawable(iconDrawableWithCustomSize);
                BcSmartspaceTemplateDataUtils.updateVisibility(imageView, 0);
                z = true;
            }
            CharSequence title = smartspaceAction.getTitle();
            if (TextUtils.isEmpty(title)) {
                BcSmartspaceTemplateDataUtils.updateVisibility(textView, 8);
                z2 = z;
            } else {
                textView.setText(title);
                BcSmartspaceTemplateDataUtils.updateVisibility(textView, 0);
            }
            if (z2) {
                charSequence = smartspaceAction.getContentDescription();
            } else {
                charSequence = null;
            }
            constraintLayout.setContentDescription(charSequence);
            if (z2) {
                BcSmartspaceTemplateDataUtils.updateVisibility(constraintLayout, 0);
            } else {
                BcSmartspaceTemplateDataUtils.updateVisibility(constraintLayout, 8);
            }
            return z2;
        }
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mFirstSubCard = (ConstraintLayout) findViewById(2131362593);
        this.mSecondSubCard = (ConstraintLayout) findViewById(2131363564);
    }

    public final void resetUi() {
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mFirstSubCard, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mSecondSubCard, 8);
    }

    public boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        SmartspaceAction smartspaceAction;
        boolean z;
        boolean z2;
        boolean z3;
        List actionChips = smartspaceTarget.getActionChips();
        if (actionChips == null || actionChips.size() < 1 || (smartspaceAction = (SmartspaceAction) actionChips.get(0)) == null) {
            return false;
        }
        ConstraintLayout constraintLayout = this.mFirstSubCard;
        if (constraintLayout == null || !fillSubCard(constraintLayout, smartspaceTarget, smartspaceAction, smartspaceEventNotifier, bcSmartspaceCardLoggingInfo)) {
            z = false;
        } else {
            z = true;
        }
        if (actionChips.size() <= 1 || actionChips.get(1) == null) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z2) {
            z3 = fillSubCard(this.mSecondSubCard, smartspaceTarget, (SmartspaceAction) actionChips.get(1), smartspaceEventNotifier, bcSmartspaceCardLoggingInfo);
        } else {
            z3 = true;
        }
        if (getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
            if (!z2 || !z3) {
                layoutParams.weight = 1.0f;
            } else {
                layoutParams.weight = 3.0f;
            }
            setLayoutParams(layoutParams);
        }
        if (!z || !z3) {
            return false;
        }
        return true;
    }

    public BcSmartspaceCardCombination(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void setTextColor(int i) {
    }
}
