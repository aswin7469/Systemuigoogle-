package com.google.android.systemui.dreamliner;

import android.view.View;
import androidx.core.view.accessibility.AccessibilityViewCommand;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class DreamlinerA11yAction$$ExternalSyntheticLambda0 implements AccessibilityViewCommand {
    public final /* synthetic */ DreamlinerA11yAction f$0;

    public /* synthetic */ DreamlinerA11yAction$$ExternalSyntheticLambda0(DreamlinerA11yAction dreamlinerA11yAction) {
        this.f$0 = dreamlinerA11yAction;
    }

    public final boolean perform(View view) {
        this.f$0.mCustomAction.run();
        return true;
    }
}
