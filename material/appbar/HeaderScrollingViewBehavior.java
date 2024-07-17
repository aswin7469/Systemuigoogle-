package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior {
    public int overlayTop;
    public final Rect tempRect1 = new Rect();
    public final Rect tempRect2 = new Rect();
    public int verticalLayoutGap = 0;

    public HeaderScrollingViewBehavior() {
    }

    public abstract AppBarLayout findFirstDependency$1(List list);

    public float getOverlapRatioForOffset(View view) {
        return 1.0f;
    }

    public int getScrollRange(View view) {
        return view.getMeasuredHeight();
    }

    public final void layoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        AppBarLayout findFirstDependency$1 = findFirstDependency$1(coordinatorLayout.getDependencies(view));
        int i2 = 0;
        if (findFirstDependency$1 != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
            int paddingLeft = coordinatorLayout.getPaddingLeft() + layoutParams.leftMargin;
            int bottom = findFirstDependency$1.getBottom() + layoutParams.topMargin;
            int width = (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - layoutParams.rightMargin;
            int height = coordinatorLayout.getHeight();
            Rect rect = this.tempRect1;
            rect.set(paddingLeft, bottom, width, ((findFirstDependency$1.getBottom() + height) - coordinatorLayout.getPaddingBottom()) - layoutParams.bottomMargin);
            WindowInsetsCompat windowInsetsCompat = coordinatorLayout.mLastInsets;
            if (windowInsetsCompat != null) {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                if (ViewCompat.Api16Impl.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.Api16Impl.getFitsSystemWindows(view)) {
                    rect.left = windowInsetsCompat.getSystemWindowInsetLeft() + rect.left;
                    rect.right -= windowInsetsCompat.getSystemWindowInsetRight();
                }
            }
            int i3 = layoutParams.gravity;
            if (i3 == 0) {
                i3 = 8388659;
            }
            int i4 = i3;
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            Rect rect2 = this.tempRect2;
            Gravity.apply(i4, measuredWidth, measuredHeight, rect, rect2, i);
            if (this.overlayTop != 0) {
                float overlapRatioForOffset = getOverlapRatioForOffset(findFirstDependency$1);
                int i5 = this.overlayTop;
                i2 = MathUtils.clamp((int) (overlapRatioForOffset * ((float) i5)), 0, i5);
            }
            view.layout(rect2.left, rect2.top - i2, rect2.right, rect2.bottom - i2);
            this.verticalLayoutGap = rect2.top - findFirstDependency$1.getBottom();
            return;
        }
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        View view2 = view;
        coordinatorLayout.onLayoutChild(view, i);
        this.verticalLayoutGap = 0;
    }

    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        AppBarLayout findFirstDependency$1;
        int i4;
        WindowInsetsCompat windowInsetsCompat;
        int i5 = view.getLayoutParams().height;
        if ((i5 != -1 && i5 != -2) || (findFirstDependency$1 = findFirstDependency$1(coordinatorLayout.getDependencies(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i3);
        if (size > 0) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            if (ViewCompat.Api16Impl.getFitsSystemWindows(findFirstDependency$1) && (windowInsetsCompat = coordinatorLayout.mLastInsets) != null) {
                size += windowInsetsCompat.getSystemWindowInsetBottom() + windowInsetsCompat.getSystemWindowInsetTop();
            }
        } else {
            size = coordinatorLayout.getHeight();
        }
        int scrollRange = (size + getScrollRange(findFirstDependency$1)) - findFirstDependency$1.getMeasuredHeight();
        if (i5 == -1) {
            i4 = 1073741824;
        } else {
            i4 = Integer.MIN_VALUE;
        }
        coordinatorLayout.onMeasureChild(view, i, i2, View.MeasureSpec.makeMeasureSpec(scrollRange, i4));
        return true;
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
