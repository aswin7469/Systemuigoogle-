package com.google.android.systemui.assist;

import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
