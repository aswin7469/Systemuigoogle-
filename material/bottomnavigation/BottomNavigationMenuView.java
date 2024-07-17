package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.ViewCompat;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BottomNavigationMenuView extends NavigationBarMenuView {
    public final int activeItemMaxWidth;
    public final int activeItemMinWidth;
    public final int inactiveItemMaxWidth;
    public final int inactiveItemMinWidth;
    public boolean itemHorizontalTranslationEnabled;
    public final int[] tempChildWidths = new int[5];

    public BottomNavigationMenuView(Context context) {
        super(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        Resources resources = getResources();
        this.inactiveItemMaxWidth = resources.getDimensionPixelSize(2131165719);
        this.inactiveItemMinWidth = resources.getDimensionPixelSize(2131165720);
        this.activeItemMaxWidth = resources.getDimensionPixelSize(2131165713);
        this.activeItemMinWidth = resources.getDimensionPixelSize(2131165714);
    }

    public final NavigationBarItemView createNavigationBarItemView(Context context) {
        return new NavigationBarItemView(context);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                if (ViewCompat.Api17Impl.getLayoutDirection(this) == 1) {
                    int i9 = i5 - i7;
                    childAt.layout(i9 - childAt.getMeasuredWidth(), 0, i9, i6);
                } else {
                    childAt.layout(i7, 0, childAt.getMeasuredWidth() + i7, i6);
                }
                i7 += childAt.getMeasuredWidth();
            }
        }
    }

    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        MenuBuilder menuBuilder = this.menu;
        int size = View.MeasureSpec.getSize(i);
        int size2 = menuBuilder.getVisibleItems().size();
        int childCount = getChildCount();
        int size3 = View.MeasureSpec.getSize(i2);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size3, 1073741824);
        int i5 = 1;
        if (!NavigationBarMenuView.isShifting(this.labelVisibilityMode, size2) || !this.itemHorizontalTranslationEnabled) {
            if (size2 != 0) {
                i5 = size2;
            }
            int min = Math.min(size / i5, this.activeItemMaxWidth);
            int i6 = size - (size2 * min);
            for (int i7 = 0; i7 < childCount; i7++) {
                if (getChildAt(i7).getVisibility() != 8) {
                    int[] iArr = this.tempChildWidths;
                    iArr[i7] = min;
                    if (i6 > 0) {
                        iArr[i7] = min + 1;
                        i6--;
                    }
                } else {
                    this.tempChildWidths[i7] = 0;
                }
            }
        } else {
            View childAt = getChildAt(this.selectedItemPosition);
            int i8 = this.activeItemMinWidth;
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, Integer.MIN_VALUE), makeMeasureSpec);
                i8 = Math.max(i8, childAt.getMeasuredWidth());
            }
            if (childAt.getVisibility() != 8) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            int i9 = size2 - i3;
            int min2 = Math.min(size - (this.inactiveItemMinWidth * i9), Math.min(i8, this.activeItemMaxWidth));
            int i10 = size - min2;
            if (i9 != 0) {
                i5 = i9;
            }
            int min3 = Math.min(i10 / i5, this.inactiveItemMaxWidth);
            int i11 = i10 - (i9 * min3);
            for (int i12 = 0; i12 < childCount; i12++) {
                if (getChildAt(i12).getVisibility() != 8) {
                    int[] iArr2 = this.tempChildWidths;
                    if (i12 == this.selectedItemPosition) {
                        i4 = min2;
                    } else {
                        i4 = min3;
                    }
                    iArr2[i12] = i4;
                    if (i11 > 0) {
                        iArr2[i12] = i4 + 1;
                        i11--;
                    }
                } else {
                    this.tempChildWidths[i12] = 0;
                }
            }
        }
        int i13 = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt2 = getChildAt(i14);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(this.tempChildWidths[i14], 1073741824), makeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                i13 += childAt2.getMeasuredWidth();
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(i13, View.MeasureSpec.makeMeasureSpec(i13, 1073741824), 0), View.resolveSizeAndState(size3, i2, 0));
    }
}
