package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.widget.ViewDragHelper;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class SwipeDismissBehavior extends CoordinatorLayout.Behavior {
    public float alphaEndSwipeDistance = 0.5f;
    public float alphaStartSwipeDistance = 0.0f;
    public final AnonymousClass1 dragCallback = new ViewDragHelper.Callback() {
        public int activePointerId = -1;
        public int originalCapturedViewLeft;

        public final int clampViewPositionHorizontal(View view, int i) {
            boolean z;
            int i2;
            int i3;
            int width;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            if (view.getLayoutDirection() == 1) {
                z = true;
            } else {
                z = false;
            }
            int i4 = SwipeDismissBehavior.this.swipeDirection;
            if (i4 != 0) {
                if (i4 != 1) {
                    i3 = this.originalCapturedViewLeft - view.getWidth();
                    i2 = this.originalCapturedViewLeft + view.getWidth();
                } else if (z) {
                    i3 = this.originalCapturedViewLeft;
                    width = view.getWidth();
                } else {
                    i3 = this.originalCapturedViewLeft - view.getWidth();
                    i2 = this.originalCapturedViewLeft;
                }
                return Math.min(Math.max(i3, i), i2);
            } else if (z) {
                i3 = this.originalCapturedViewLeft - view.getWidth();
                i2 = this.originalCapturedViewLeft;
                return Math.min(Math.max(i3, i), i2);
            } else {
                i3 = this.originalCapturedViewLeft;
                width = view.getWidth();
            }
            i2 = width + i3;
            return Math.min(Math.max(i3, i), i2);
        }

        public final int clampViewPositionVertical(View view, int i) {
            return view.getTop();
        }

        public final int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        public final void onViewCaptured(View view, int i) {
            this.activePointerId = i;
            this.originalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        public final void onViewDragStateChanged(int i) {
            SwipeDismissBehavior.this.getClass();
        }

        public final void onViewPositionChanged(View view, int i, int i2) {
            SwipeDismissBehavior swipeDismissBehavior = SwipeDismissBehavior.this;
            float width = (((float) view.getWidth()) * swipeDismissBehavior.alphaStartSwipeDistance) + ((float) this.originalCapturedViewLeft);
            float width2 = (((float) view.getWidth()) * swipeDismissBehavior.alphaEndSwipeDistance) + ((float) this.originalCapturedViewLeft);
            float f = (float) i;
            if (f <= width) {
                view.setAlpha(1.0f);
            } else if (f >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(Math.min(Math.max(0.0f, 1.0f - ((f - width) / (width2 - width))), 1.0f));
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
            if (java.lang.Math.abs(r9.getLeft() - r8.originalCapturedViewLeft) >= java.lang.Math.round(((float) r9.getWidth()) * r3.dragDismissThreshold)) goto L_0x0052;
         */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0075  */
        /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onViewReleased(android.view.View r9, float r10, float r11) {
            /*
                r8 = this;
                r11 = -1
                r8.activePointerId = r11
                int r11 = r9.getWidth()
                r0 = 0
                int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                r2 = 1
                com.google.android.material.behavior.SwipeDismissBehavior r3 = com.google.android.material.behavior.SwipeDismissBehavior.this
                r4 = 0
                if (r1 == 0) goto L_0x0039
                java.util.WeakHashMap r5 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
                int r5 = r9.getLayoutDirection()
                if (r5 != r2) goto L_0x001a
                r5 = r2
                goto L_0x001b
            L_0x001a:
                r5 = r4
            L_0x001b:
                int r6 = r3.swipeDirection
                r7 = 2
                if (r6 != r7) goto L_0x0021
                goto L_0x0052
            L_0x0021:
                if (r6 != 0) goto L_0x002d
                if (r5 == 0) goto L_0x002a
                int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r1 >= 0) goto L_0x0066
                goto L_0x0052
            L_0x002a:
                if (r1 <= 0) goto L_0x0066
                goto L_0x0052
            L_0x002d:
                if (r6 != r2) goto L_0x0066
                if (r5 == 0) goto L_0x0034
                if (r1 <= 0) goto L_0x0066
                goto L_0x0052
            L_0x0034:
                int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r1 >= 0) goto L_0x0066
                goto L_0x0052
            L_0x0039:
                int r1 = r9.getLeft()
                int r5 = r8.originalCapturedViewLeft
                int r1 = r1 - r5
                int r5 = r9.getWidth()
                float r5 = (float) r5
                float r6 = r3.dragDismissThreshold
                float r5 = r5 * r6
                int r5 = java.lang.Math.round(r5)
                int r1 = java.lang.Math.abs(r1)
                if (r1 < r5) goto L_0x0066
            L_0x0052:
                int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r10 < 0) goto L_0x0061
                int r10 = r9.getLeft()
                int r0 = r8.originalCapturedViewLeft
                if (r10 >= r0) goto L_0x005f
                goto L_0x0061
            L_0x005f:
                int r0 = r0 + r11
                goto L_0x0069
            L_0x0061:
                int r8 = r8.originalCapturedViewLeft
                int r0 = r8 - r11
                goto L_0x0069
            L_0x0066:
                int r0 = r8.originalCapturedViewLeft
                r2 = r4
            L_0x0069:
                androidx.customview.widget.ViewDragHelper r8 = r3.viewDragHelper
                int r10 = r9.getTop()
                boolean r8 = r8.settleCapturedViewAt(r0, r10)
                if (r8 == 0) goto L_0x007f
                com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable r8 = new com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable
                r8.<init>(r9, r2)
                java.util.WeakHashMap r10 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
                r9.postOnAnimation(r8)
            L_0x007f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
        }

        public final boolean tryCaptureView(View view, int i) {
            int i2 = this.activePointerId;
            if ((i2 == -1 || i2 == i) && SwipeDismissBehavior.this.canSwipeDismissView(view)) {
                return true;
            }
            return false;
        }
    };
    public final float dragDismissThreshold = 0.5f;
    public boolean interceptingEvents;
    public int swipeDirection = 2;
    public ViewDragHelper viewDragHelper;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public interface OnDismissListener {
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class SettleRunnable implements Runnable {
        public final boolean dismiss;
        public final View view;

        public SettleRunnable(View view2, boolean z) {
            this.view = view2;
            this.dismiss = z;
        }

        public final void run() {
            ViewDragHelper viewDragHelper = SwipeDismissBehavior.this.viewDragHelper;
            if (viewDragHelper != null && viewDragHelper.continueSettling()) {
                View view2 = this.view;
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                view2.postOnAnimation(this);
            } else if (this.dismiss) {
                SwipeDismissBehavior.this.getClass();
            }
        }
    }

    public boolean canSwipeDismissView(View view) {
        return true;
    }

    public OnDismissListener getListener() {
        return null;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z = this.interceptingEvents;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z = coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.interceptingEvents = z;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.interceptingEvents = false;
        }
        if (!z) {
            return false;
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = new ViewDragHelper(coordinatorLayout.getContext(), coordinatorLayout, this.dragCallback);
        }
        return this.viewDragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
            ViewCompat.removeActionWithId(view, 1048576);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, 0);
            if (canSwipeDismissView(view)) {
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, (CharSequence) null, new AccessibilityViewCommand() {
                    public final boolean perform(View view) {
                        int i;
                        SwipeDismissBehavior swipeDismissBehavior = SwipeDismissBehavior.this;
                        boolean z = false;
                        if (!swipeDismissBehavior.canSwipeDismissView(view)) {
                            return false;
                        }
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        if (view.getLayoutDirection() == 1) {
                            z = true;
                        }
                        int i2 = swipeDismissBehavior.swipeDirection;
                        if ((i2 != 0 || !z) && (i2 != 1 || z)) {
                            i = view.getWidth();
                        } else {
                            i = -view.getWidth();
                        }
                        view.offsetLeftAndRight(i);
                        view.setAlpha(0.0f);
                        return true;
                    }
                });
            }
        }
        return false;
    }

    public final boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper2 = this.viewDragHelper;
        if (viewDragHelper2 == null) {
            return false;
        }
        viewDragHelper2.processTouchEvent(motionEvent);
        return true;
    }
}
