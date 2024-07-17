package com.google.android.systemui.dreamliner;

import android.view.View;
import androidx.core.view.accessibility.AccessibilityViewCommand;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
