package com.google.android.material.textfield;

import android.view.View;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
