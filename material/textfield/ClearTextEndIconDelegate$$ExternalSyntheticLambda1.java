package com.google.android.material.textfield;

import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ClearTextEndIconDelegate$$ExternalSyntheticLambda1 implements View.OnFocusChangeListener {
    public final /* synthetic */ ClearTextEndIconDelegate f$0;

    public /* synthetic */ ClearTextEndIconDelegate$$ExternalSyntheticLambda1(ClearTextEndIconDelegate clearTextEndIconDelegate) {
        this.f$0 = clearTextEndIconDelegate;
    }

    public final void onFocusChange(View view, boolean z) {
        ClearTextEndIconDelegate clearTextEndIconDelegate = this.f$0;
        clearTextEndIconDelegate.animateIcon(clearTextEndIconDelegate.shouldBeVisible());
    }
}
