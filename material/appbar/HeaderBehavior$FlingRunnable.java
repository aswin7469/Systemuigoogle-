package com.google.android.material.appbar;

import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HeaderBehavior$FlingRunnable implements Runnable {
    public final View layout;
    public final CoordinatorLayout parent;
    public final /* synthetic */ AppBarLayout.BaseBehavior this$0;

    public HeaderBehavior$FlingRunnable(AppBarLayout.BaseBehavior baseBehavior, CoordinatorLayout coordinatorLayout, View view) {
        this.this$0 = baseBehavior;
        this.parent = coordinatorLayout;
        this.layout = view;
    }

    public final void run() {
        OverScroller overScroller;
        if (this.layout != null && (overScroller = this.this$0.scroller) != null) {
            if (overScroller.computeScrollOffset()) {
                AppBarLayout.BaseBehavior baseBehavior = this.this$0;
                baseBehavior.setHeaderTopBottomOffset(this.parent, this.layout, baseBehavior.scroller.getCurrY());
                this.layout.postOnAnimation(this);
                return;
            }
            AppBarLayout.BaseBehavior baseBehavior2 = this.this$0;
            CoordinatorLayout coordinatorLayout = this.parent;
            View view = this.layout;
            baseBehavior2.getClass();
            AppBarLayout appBarLayout = (AppBarLayout) view;
            baseBehavior2.snapToChildIfNeeded(coordinatorLayout, appBarLayout);
            if (appBarLayout.liftOnScroll) {
                appBarLayout.setLiftedState(appBarLayout.shouldLift(AppBarLayout.BaseBehavior.findFirstScrollingChild(coordinatorLayout)));
            }
        }
    }
}
