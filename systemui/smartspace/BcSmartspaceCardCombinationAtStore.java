package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class BcSmartspaceCardCombinationAtStore extends BcSmartspaceCardCombination {
    public BcSmartspaceCardCombinationAtStore(Context context) {
        super(context);
    }

    public final boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        SmartspaceAction smartspaceAction;
        boolean z;
        boolean z2;
        List actionChips = smartspaceTarget.getActionChips();
        if (actionChips == null || actionChips.isEmpty() || (smartspaceAction = (SmartspaceAction) actionChips.get(0)) == null) {
            return false;
        }
        ConstraintLayout constraintLayout = this.mFirstSubCard;
        if (!(constraintLayout instanceof BcSmartspaceCardShoppingList) || !((BcSmartspaceCardShoppingList) constraintLayout).setSmartspaceActions(smartspaceTarget, smartspaceEventNotifier, bcSmartspaceCardLoggingInfo)) {
            z = false;
        } else {
            z = true;
        }
        ConstraintLayout constraintLayout2 = this.mSecondSubCard;
        if (constraintLayout2 == null || !fillSubCard(constraintLayout2, smartspaceTarget, smartspaceAction, smartspaceEventNotifier, bcSmartspaceCardLoggingInfo)) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z) {
            this.mFirstSubCard.setBackgroundResource(2131232264);
        }
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    public BcSmartspaceCardCombinationAtStore(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
