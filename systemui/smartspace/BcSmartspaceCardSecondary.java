package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceTarget;
import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class BcSmartspaceCardSecondary extends ConstraintLayout {
    public String mPrevSmartspaceTargetId = "";

    public BcSmartspaceCardSecondary(Context context) {
        super(context);
    }

    public final void reset$1(String str) {
        if (!this.mPrevSmartspaceTargetId.equals(str)) {
            this.mPrevSmartspaceTargetId = str;
            resetUi();
        }
    }

    public abstract boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo);

    public abstract void setTextColor(int i);

    public BcSmartspaceCardSecondary(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void resetUi() {
    }
}
