package com.google.android.systemui.assist;

import android.view.View;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class OpaLayout$$ExternalSyntheticLambda1 implements View.OnLongClickListener {
    public final /* synthetic */ OpaLayout f$0;
    public final /* synthetic */ View.OnLongClickListener f$1;

    public /* synthetic */ OpaLayout$$ExternalSyntheticLambda1(OpaLayout opaLayout, View.OnLongClickListener onLongClickListener) {
        this.f$0 = opaLayout;
        this.f$1 = onLongClickListener;
    }

    public final boolean onLongClick(View view) {
        return this.f$1.onLongClick(this.f$0.mHome);
    }
}
