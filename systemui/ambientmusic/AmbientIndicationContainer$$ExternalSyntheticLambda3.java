package com.google.android.systemui.ambientmusic;

import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
