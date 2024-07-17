package com.google.android.material.appbar;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ViewOffsetBehavior extends CoordinatorLayout.Behavior {
    public int tempTopBottomOffset = 0;
    public ViewOffsetHelper viewOffsetHelper;

    public ViewOffsetBehavior() {
    }

    public final int getTopAndBottomOffset() {
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        if (viewOffsetHelper2 != null) {
            return viewOffsetHelper2.offsetTop;
        }
        return 0;
    }

    public void layoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        coordinatorLayout.onLayoutChild(view, i);
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        layoutChild(coordinatorLayout, view, i);
        if (this.viewOffsetHelper == null) {
            this.viewOffsetHelper = new ViewOffsetHelper(view);
        }
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        View view2 = viewOffsetHelper2.view;
        viewOffsetHelper2.layoutTop = view2.getTop();
        viewOffsetHelper2.layoutLeft = view2.getLeft();
        this.viewOffsetHelper.applyOffsets();
        int i2 = this.tempTopBottomOffset;
        if (i2 == 0) {
            return true;
        }
        this.viewOffsetHelper.setTopAndBottomOffset(i2);
        this.tempTopBottomOffset = 0;
        return true;
    }

    public ViewOffsetBehavior(int i) {
    }
}
