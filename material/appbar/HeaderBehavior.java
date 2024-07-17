package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
abstract class HeaderBehavior extends ViewOffsetBehavior {
    public int activePointerId = -1;
    public FlingRunnable flingRunnable;
    public boolean isBeingDragged;
    public int lastMotionY;
    public OverScroller scroller;
    public int touchSlop = -1;
    public VelocityTracker velocityTracker;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class FlingRunnable implements Runnable {
        public final View layout;
        public final CoordinatorLayout parent;

        public FlingRunnable(CoordinatorLayout coordinatorLayout, View view) {
            this.parent = coordinatorLayout;
            this.layout = view;
        }

        public final void run() {
            OverScroller overScroller;
            if (this.layout != null && (overScroller = HeaderBehavior.this.scroller) != null) {
                if (overScroller.computeScrollOffset()) {
                    HeaderBehavior headerBehavior = HeaderBehavior.this;
                    headerBehavior.setHeaderTopBottomOffset(this.parent, this.layout, headerBehavior.scroller.getCurrY());
                    View view = this.layout;
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api16Impl.postOnAnimation(view, this);
                    return;
                }
                HeaderBehavior.this.onFlingFinished(this.layout, this.parent);
            }
        }
    }

    public HeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean canDragView(View view) {
        return false;
    }

    public int getMaxDragOffset(View view) {
        return -view.getHeight();
    }

    public int getScrollRangeForDragFling(View view) {
        return view.getHeight();
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z;
        int findPointerIndex;
        if (this.touchSlop < 0) {
            this.touchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.isBeingDragged) {
            int i = this.activePointerId;
            if (i == -1 || (findPointerIndex = motionEvent.findPointerIndex(i)) == -1) {
                return false;
            }
            int y = (int) motionEvent.getY(findPointerIndex);
            if (Math.abs(y - this.lastMotionY) > this.touchSlop) {
                this.lastMotionY = y;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.activePointerId = -1;
            int x = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            if (!canDragView(view) || !coordinatorLayout.isPointInChildBounds(view, x, y2)) {
                z = false;
            } else {
                z = true;
            }
            this.isBeingDragged = z;
            if (z) {
                this.lastMotionY = y2;
                this.activePointerId = motionEvent.getPointerId(0);
                if (this.velocityTracker == null) {
                    this.velocityTracker = VelocityTracker.obtain();
                }
                OverScroller overScroller = this.scroller;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.scroller.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00dc A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout r20, android.view.View r21, android.view.MotionEvent r22) {
        /*
            r19 = this;
            r6 = r19
            r1 = r20
            r2 = r21
            r7 = r22
            int r0 = r22.getActionMasked()
            r3 = 0
            r4 = -1
            r8 = 1
            r9 = 0
            if (r0 == r8) goto L_0x0060
            r5 = 2
            if (r0 == r5) goto L_0x0036
            r1 = 3
            if (r0 == r1) goto L_0x00c3
            r1 = 6
            if (r0 == r1) goto L_0x001c
            goto L_0x005d
        L_0x001c:
            int r0 = r22.getActionIndex()
            if (r0 != 0) goto L_0x0024
            r0 = r8
            goto L_0x0025
        L_0x0024:
            r0 = r9
        L_0x0025:
            int r1 = r7.getPointerId(r0)
            r6.activePointerId = r1
            float r0 = r7.getY(r0)
            r1 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 + r1
            int r0 = (int) r0
            r6.lastMotionY = r0
            goto L_0x005d
        L_0x0036:
            int r0 = r6.activePointerId
            int r0 = r7.findPointerIndex(r0)
            if (r0 != r4) goto L_0x003f
            return r9
        L_0x003f:
            float r0 = r7.getY(r0)
            int r0 = (int) r0
            int r3 = r6.lastMotionY
            int r3 = r3 - r0
            r6.lastMotionY = r0
            int r4 = r6.getMaxDragOffset(r2)
            int r0 = r19.getTopBottomOffsetForScrollingSibling()
            int r3 = r0 - r3
            r5 = 0
            r0 = r19
            r1 = r20
            r2 = r21
            r0.setHeaderTopBottomOffset(r1, r2, r3, r4, r5)
        L_0x005d:
            r0 = r9
            goto L_0x00d1
        L_0x0060:
            android.view.VelocityTracker r0 = r6.velocityTracker
            if (r0 == 0) goto L_0x00c3
            r0.addMovement(r7)
            android.view.VelocityTracker r0 = r6.velocityTracker
            r5 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r5)
            android.view.VelocityTracker r0 = r6.velocityTracker
            int r5 = r6.activePointerId
            float r0 = r0.getYVelocity(r5)
            int r5 = r6.getScrollRangeForDragFling(r2)
            int r5 = -r5
            com.google.android.material.appbar.HeaderBehavior$FlingRunnable r10 = r6.flingRunnable
            if (r10 == 0) goto L_0x0084
            r2.removeCallbacks(r10)
            r6.flingRunnable = r3
        L_0x0084:
            android.widget.OverScroller r10 = r6.scroller
            if (r10 != 0) goto L_0x0093
            android.widget.OverScroller r10 = new android.widget.OverScroller
            android.content.Context r11 = r21.getContext()
            r10.<init>(r11)
            r6.scroller = r10
        L_0x0093:
            android.widget.OverScroller r10 = r6.scroller
            int r12 = r19.getTopAndBottomOffset()
            int r14 = java.lang.Math.round(r0)
            r16 = 0
            r18 = 0
            r11 = 0
            r13 = 0
            r15 = 0
            r17 = r5
            r10.fling(r11, r12, r13, r14, r15, r16, r17, r18)
            android.widget.OverScroller r0 = r6.scroller
            boolean r0 = r0.computeScrollOffset()
            if (r0 == 0) goto L_0x00be
            com.google.android.material.appbar.HeaderBehavior$FlingRunnable r0 = new com.google.android.material.appbar.HeaderBehavior$FlingRunnable
            r0.<init>(r1, r2)
            r6.flingRunnable = r0
            java.util.WeakHashMap r1 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api16Impl.postOnAnimation(r2, r0)
            goto L_0x00c1
        L_0x00be:
            r6.onFlingFinished(r2, r1)
        L_0x00c1:
            r0 = r8
            goto L_0x00c4
        L_0x00c3:
            r0 = r9
        L_0x00c4:
            r6.isBeingDragged = r9
            r6.activePointerId = r4
            android.view.VelocityTracker r1 = r6.velocityTracker
            if (r1 == 0) goto L_0x00d1
            r1.recycle()
            r6.velocityTracker = r3
        L_0x00d1:
            android.view.VelocityTracker r1 = r6.velocityTracker
            if (r1 == 0) goto L_0x00d8
            r1.addMovement(r7)
        L_0x00d8:
            boolean r1 = r6.isBeingDragged
            if (r1 != 0) goto L_0x00e0
            if (r0 == 0) goto L_0x00df
            goto L_0x00e0
        L_0x00df:
            r8 = r9
        L_0x00e0:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.HeaderBehavior.onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public final void setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, View view, int i) {
        setHeaderTopBottomOffset(coordinatorLayout, view, i, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        int clamp;
        int topAndBottomOffset = getTopAndBottomOffset();
        if (i2 == 0 || topAndBottomOffset < i2 || topAndBottomOffset > i3 || topAndBottomOffset == (clamp = MathUtils.clamp(i, i2, i3))) {
            return 0;
        }
        setTopAndBottomOffset(clamp);
        return topAndBottomOffset - clamp;
    }

    public void onFlingFinished(View view, CoordinatorLayout coordinatorLayout) {
    }
}
