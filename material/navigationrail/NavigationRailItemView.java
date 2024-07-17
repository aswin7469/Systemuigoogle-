package com.google.android.material.navigationrail;

import android.view.View;
import com.google.android.material.navigation.NavigationBarItemView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NavigationRailItemView extends NavigationBarItemView {
    public final int getItemDefaultMarginResId() {
        return 2131166857;
    }

    public final int getItemLayoutResId() {
        return 2131558812;
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i2) == 0) {
            setMeasuredDimension(getMeasuredWidthAndState(), View.resolveSizeAndState(Math.max(getMeasuredHeight(), View.MeasureSpec.getSize(i2)), i2, 0));
        }
    }
}
