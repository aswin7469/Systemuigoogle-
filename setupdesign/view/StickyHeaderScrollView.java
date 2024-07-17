package com.google.android.setupdesign.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class StickyHeaderScrollView extends BottomScrollView {
    public int statusBarInset = 0;
    public View sticky;
    public View stickyContainer;

    public StickyHeaderScrollView(Context context) {
        super(context);
    }

    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        if (!getFitsSystemWindows()) {
            return windowInsets;
        }
        this.statusBarInset = windowInsets.getSystemWindowInsetTop();
        return windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), 0, windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.sticky == null) {
            this.sticky = findViewWithTag("sticky");
            this.stickyContainer = findViewWithTag("stickyContainer");
        }
        updateStickyHeaderPosition();
    }

    public final void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        updateStickyHeaderPosition();
    }

    public final void updateStickyHeaderPosition() {
        View view;
        int i;
        View view2 = this.sticky;
        if (view2 != null) {
            View view3 = this.stickyContainer;
            if (view3 != null) {
                view = view3;
            } else {
                view = view2;
            }
            if (view3 != null) {
                i = view2.getTop();
            } else {
                i = 0;
            }
            if ((view.getTop() - getScrollY()) + i < this.statusBarInset || !view.isShown()) {
                view.setTranslationY((float) (getScrollY() - i));
            } else {
                view.setTranslationY(0.0f);
            }
        }
    }

    public StickyHeaderScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StickyHeaderScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
