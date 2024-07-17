package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.animation.AnimationUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class HideBottomViewOnScrollBehavior extends CoordinatorLayout.Behavior {
    public int additionalHiddenOffsetY = 0;
    public ViewPropertyAnimator currentAnimator;
    public int currentState = 2;
    public int height = 0;
    public final LinkedHashSet onScrollStateChangedListeners = new LinkedHashSet();

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        this.height = view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        return false;
    }

    public final void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
        LinkedHashSet linkedHashSet = this.onScrollStateChangedListeners;
        if (i > 0) {
            if (this.currentState != 1) {
                ViewPropertyAnimator viewPropertyAnimator = this.currentAnimator;
                if (viewPropertyAnimator != null) {
                    viewPropertyAnimator.cancel();
                    view.clearAnimation();
                }
                this.currentState = 1;
                Iterator it = linkedHashSet.iterator();
                if (!it.hasNext()) {
                    int i4 = this.height + this.additionalHiddenOffsetY;
                    this.currentAnimator = view.animate().translationY((float) i4).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setDuration(175).setListener(new AnimatorListenerAdapter() {
                        public final void onAnimationEnd(Animator animator) {
                            HideBottomViewOnScrollBehavior.this.currentAnimator = null;
                        }
                    });
                    return;
                }
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
                throw null;
            }
        } else if (i < 0 && this.currentState != 2) {
            ViewPropertyAnimator viewPropertyAnimator2 = this.currentAnimator;
            if (viewPropertyAnimator2 != null) {
                viewPropertyAnimator2.cancel();
                view.clearAnimation();
            }
            this.currentState = 2;
            Iterator it2 = linkedHashSet.iterator();
            if (!it2.hasNext()) {
                this.currentAnimator = view.animate().translationY((float) 0).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setDuration(225).setListener(new AnimatorListenerAdapter() {
                    public final void onAnimationEnd(Animator animator) {
                        HideBottomViewOnScrollBehavior.this.currentAnimator = null;
                    }
                });
                return;
            }
            WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it2.next());
            throw null;
        }
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
        if (i == 2) {
            return true;
        }
        return false;
    }

    public HideBottomViewOnScrollBehavior() {
    }
}
