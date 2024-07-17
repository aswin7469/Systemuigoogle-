package com.google.android.material.appbar;

import android.view.View;
import androidx.core.view.ViewCompat;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ViewOffsetHelper {
    public int layoutLeft;
    public int layoutTop;
    public final int offsetLeft;
    public int offsetTop;
    public final View view;

    public ViewOffsetHelper(View view2) {
        this.view = view2;
    }

    public final void applyOffsets() {
        int i = this.offsetTop;
        View view2 = this.view;
        int top = i - (view2.getTop() - this.layoutTop);
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        view2.offsetTopAndBottom(top);
        view2.offsetLeftAndRight(this.offsetLeft - (view2.getLeft() - this.layoutLeft));
    }

    public final boolean setTopAndBottomOffset(int i) {
        if (this.offsetTop == i) {
            return false;
        }
        this.offsetTop = i;
        applyOffsets();
        return true;
    }
}
