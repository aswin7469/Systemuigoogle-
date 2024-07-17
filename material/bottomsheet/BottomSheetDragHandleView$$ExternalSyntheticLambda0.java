package com.google.android.material.bottomsheet;

import android.view.View;
import androidx.core.view.accessibility.AccessibilityViewCommand;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BottomSheetDragHandleView$$ExternalSyntheticLambda0 implements AccessibilityViewCommand {
    public final /* synthetic */ BottomSheetDragHandleView f$0;

    public /* synthetic */ BottomSheetDragHandleView$$ExternalSyntheticLambda0(BottomSheetDragHandleView bottomSheetDragHandleView) {
        this.f$0 = bottomSheetDragHandleView;
    }

    public final boolean perform(View view) {
        int i = BottomSheetDragHandleView.$r8$clinit;
        return this.f$0.toggleBottomSheetIfPossible();
    }
}
