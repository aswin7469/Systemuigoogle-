package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ElasticTabIndicatorInterpolator extends TabIndicatorInterpolator {
    public final void updateIndicatorForOffset(TabLayout tabLayout, View view, View view2, float f, Drawable drawable) {
        float f2;
        float f3;
        RectF calculateIndicatorWidthForTab = TabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout, view);
        RectF calculateIndicatorWidthForTab2 = TabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout, view2);
        if (calculateIndicatorWidthForTab.left < calculateIndicatorWidthForTab2.left) {
            double d = (((double) f) * 3.141592653589793d) / 2.0d;
            f2 = (float) (1.0d - Math.cos(d));
            f3 = (float) Math.sin(d);
        } else {
            double d2 = (((double) f) * 3.141592653589793d) / 2.0d;
            f2 = (float) Math.sin(d2);
            f3 = (float) (1.0d - Math.cos(d2));
        }
        drawable.setBounds(AnimationUtils.lerp((int) calculateIndicatorWidthForTab.left, f2, (int) calculateIndicatorWidthForTab2.left), drawable.getBounds().top, AnimationUtils.lerp((int) calculateIndicatorWidthForTab.right, f3, (int) calculateIndicatorWidthForTab2.right), drawable.getBounds().bottom);
    }
}
