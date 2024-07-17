package com.google.android.material.navigationrail;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class NavigationRailView extends NavigationBarView {
    public final View headerView;
    public final Boolean paddingBottomSystemWindowInsets = null;
    public final Boolean paddingTopSystemWindowInsets = null;
    public final int topMargin;

    public NavigationRailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 2130969656, 2132018841);
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131166859);
        this.topMargin = dimensionPixelSize;
        TintTypedArray obtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(getContext(), attributeSet, R$styleable.NavigationRailView, 2130969656, 2132018841, new int[0]);
        TypedArray typedArray = obtainTintedStyledAttributes.mWrapped;
        int resourceId = typedArray.getResourceId(0, 0);
        if (resourceId != 0) {
            View inflate = LayoutInflater.from(getContext()).inflate(resourceId, this, false);
            View view = this.headerView;
            if (view != null) {
                removeView(view);
                this.headerView = null;
            }
            this.headerView = inflate;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 49;
            layoutParams.topMargin = dimensionPixelSize;
            addView(inflate, 0, layoutParams);
        }
        int i = typedArray.getInt(2, 49);
        NavigationRailMenuView navigationRailMenuView = (NavigationRailMenuView) this.menuView;
        FrameLayout.LayoutParams layoutParams2 = navigationRailMenuView.layoutParams;
        if (layoutParams2.gravity != i) {
            layoutParams2.gravity = i;
            navigationRailMenuView.setLayoutParams(layoutParams2);
        }
        if (typedArray.hasValue(1)) {
            int dimensionPixelSize2 = typedArray.getDimensionPixelSize(1, -1);
            NavigationRailMenuView navigationRailMenuView2 = (NavigationRailMenuView) this.menuView;
            if (navigationRailMenuView2.itemMinimumHeight != dimensionPixelSize2) {
                navigationRailMenuView2.itemMinimumHeight = dimensionPixelSize2;
                navigationRailMenuView2.requestLayout();
            }
        }
        if (typedArray.hasValue(4)) {
            this.paddingTopSystemWindowInsets = Boolean.valueOf(typedArray.getBoolean(4, false));
        }
        if (typedArray.hasValue(3)) {
            this.paddingBottomSystemWindowInsets = Boolean.valueOf(typedArray.getBoolean(3, false));
        }
        obtainTintedStyledAttributes.recycle();
        ViewUtils.doOnApplyWindowInsets(this, new ViewUtils.OnApplyWindowInsetsListener() {
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
                boolean z;
                boolean z2;
                NavigationRailView navigationRailView = NavigationRailView.this;
                Boolean bool = navigationRailView.paddingTopSystemWindowInsets;
                if (bool != null) {
                    z = bool.booleanValue();
                } else {
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    z = ViewCompat.Api16Impl.getFitsSystemWindows(navigationRailView);
                }
                WindowInsetsCompat.Impl impl = windowInsetsCompat.mImpl;
                if (z) {
                    relativePadding.top += impl.getInsets(7).top;
                }
                Boolean bool2 = navigationRailView.paddingBottomSystemWindowInsets;
                if (bool2 != null) {
                    z2 = bool2.booleanValue();
                } else {
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    z2 = ViewCompat.Api16Impl.getFitsSystemWindows(navigationRailView);
                }
                if (z2) {
                    relativePadding.bottom += impl.getInsets(7).bottom;
                }
                WeakHashMap weakHashMap3 = ViewCompat.sViewPropertyAnimatorMap;
                boolean z3 = true;
                if (ViewCompat.Api17Impl.getLayoutDirection(view) != 1) {
                    z3 = false;
                }
                int systemWindowInsetLeft = windowInsetsCompat.getSystemWindowInsetLeft();
                int systemWindowInsetRight = windowInsetsCompat.getSystemWindowInsetRight();
                int i = relativePadding.start;
                if (z3) {
                    systemWindowInsetLeft = systemWindowInsetRight;
                }
                int i2 = i + systemWindowInsetLeft;
                relativePadding.start = i2;
                ViewCompat.Api17Impl.setPaddingRelative(view, i2, relativePadding.top, relativePadding.end, relativePadding.bottom);
                return windowInsetsCompat;
            }
        });
    }

    public final NavigationBarMenuView createNavigationBarMenuView(Context context) {
        return new NavigationRailMenuView(context);
    }

    public final int getMaxItemCount() {
        return 7;
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        super.onLayout(z, i, i2, i3, i4);
        NavigationRailMenuView navigationRailMenuView = (NavigationRailMenuView) this.menuView;
        View view = this.headerView;
        int i5 = 0;
        if (view == null || view.getVisibility() == 8) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z2) {
            int bottom = this.headerView.getBottom() + this.topMargin;
            int top = navigationRailMenuView.getTop();
            if (top < bottom) {
                i5 = bottom - top;
            }
        } else if ((navigationRailMenuView.layoutParams.gravity & 112) == 48) {
            i5 = this.topMargin;
        }
        if (i5 > 0) {
            navigationRailMenuView.layout(navigationRailMenuView.getLeft(), navigationRailMenuView.getTop() + i5, navigationRailMenuView.getRight(), navigationRailMenuView.getBottom() + i5);
        }
    }

    public final void onMeasure(int i, int i2) {
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        if (View.MeasureSpec.getMode(i) != 1073741824 && suggestedMinimumWidth > 0) {
            int paddingLeft = getPaddingLeft();
            i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), getPaddingRight() + paddingLeft + suggestedMinimumWidth), 1073741824);
        }
        super.onMeasure(i, i2);
        View view = this.headerView;
        if (view != null && view.getVisibility() != 8) {
            measureChild((NavigationRailMenuView) this.menuView, i, View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - this.headerView.getMeasuredHeight()) - this.topMargin, Integer.MIN_VALUE));
        }
    }
}
