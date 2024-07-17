package com.google.android.systemui.ambientmusic;

import android.view.View;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class AmbientIndicationContainer$$ExternalSyntheticLambda3 implements View.OnLayoutChangeListener {
    public final /* synthetic */ AmbientIndicationContainer f$0;

    public /* synthetic */ AmbientIndicationContainer$$ExternalSyntheticLambda3(AmbientIndicationContainer ambientIndicationContainer) {
        this.f$0 = ambientIndicationContainer;
    }

    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        AmbientIndicationContainer ambientIndicationContainer = this.f$0;
        int i9 = AmbientIndicationContainer.$r8$clinit;
        ambientIndicationContainer.updateBottomSpacing();
    }
}
