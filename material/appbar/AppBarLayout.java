package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.AttachedBehavior {
    public Behavior behavior;
    public int currentOffset;
    public int downPreScrollRange;
    public int downScrollRange;
    public ValueAnimator elevationOverlayAnimator;
    public boolean haveChildWithInterpolator;
    public WindowInsetsCompat lastInsets;
    public boolean liftOnScroll;
    public final List liftOnScrollListeners;
    public WeakReference liftOnScrollTargetView;
    public final int liftOnScrollTargetViewId;
    public boolean liftable;
    public boolean lifted;
    public List listeners;
    public int pendingAction;
    public final Drawable statusBarForeground;
    public int[] tmpStatesArray;
    public int totalScrollRange;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public class BaseBehavior extends ViewOffsetBehavior {
        public int activePointerId = -1;
        public boolean coordinatorLayoutA11yScrollable;
        public HeaderBehavior$FlingRunnable flingRunnable;
        public boolean isBeingDragged;
        public int lastMotionY;
        public WeakReference lastNestedScrollingChildRef;
        public int lastStartedType;
        public ValueAnimator offsetAnimator;
        public int offsetDelta;
        public SavedState savedState;
        public OverScroller scroller;
        public int touchSlop = -1;
        public VelocityTracker velocityTracker;

        /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
        public final class SavedState extends AbsSavedState {
            public static final Parcelable.Creator CREATOR = new Object();
            public boolean firstVisibleChildAtMinimumHeight;
            public int firstVisibleChildIndex;
            public float firstVisibleChildPercentageShown;
            public boolean fullyExpanded;
            public boolean fullyScrolled;

            /* renamed from: com.google.android.material.appbar.AppBarLayout$BaseBehavior$SavedState$1  reason: invalid class name */
            /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
            public final class AnonymousClass1 implements Parcelable.ClassLoaderCreator {
                public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return new SavedState(parcel, classLoader);
                }

                public final Object[] newArray(int i) {
                    return new SavedState[i];
                }

                public final Object createFromParcel(Parcel parcel) {
                    return new SavedState(parcel, (ClassLoader) null);
                }
            }

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                boolean z;
                boolean z2;
                boolean z3 = false;
                if (parcel.readByte() != 0) {
                    z = true;
                } else {
                    z = false;
                }
                this.fullyScrolled = z;
                if (parcel.readByte() != 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.fullyExpanded = z2;
                this.firstVisibleChildIndex = parcel.readInt();
                this.firstVisibleChildPercentageShown = parcel.readFloat();
                this.firstVisibleChildAtMinimumHeight = parcel.readByte() != 0 ? true : z3;
            }

            public final void writeToParcel(Parcel parcel, int i) {
                super.writeToParcel(parcel, i);
                parcel.writeByte(this.fullyScrolled ? (byte) 1 : 0);
                parcel.writeByte(this.fullyExpanded ? (byte) 1 : 0);
                parcel.writeInt(this.firstVisibleChildIndex);
                parcel.writeFloat(this.firstVisibleChildPercentageShown);
                parcel.writeByte(this.firstVisibleChildAtMinimumHeight ? (byte) 1 : 0);
            }
        }

        public BaseBehavior() {
        }

        public static View findFirstScrollingChild(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if ((childAt instanceof NestedScrollingChild) || (childAt instanceof ListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        public static void updateAppBarLayoutDrawableState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, boolean z) {
            List list;
            View view;
            boolean z2;
            int abs = Math.abs(i);
            int childCount = appBarLayout.getChildCount();
            int i3 = 0;
            int i4 = 0;
            while (true) {
                list = null;
                if (i4 >= childCount) {
                    view = null;
                    break;
                }
                view = appBarLayout.getChildAt(i4);
                if (abs >= view.getTop() && abs <= view.getBottom()) {
                    break;
                }
                i4++;
            }
            if (view != null) {
                int i5 = ((LayoutParams) view.getLayoutParams()).scrollFlags;
                if ((i5 & 1) != 0) {
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    int minimumHeight = view.getMinimumHeight();
                    z2 = true;
                    if (i2 > 0) {
                    }
                }
            }
            z2 = false;
            if (appBarLayout.liftOnScroll) {
                z2 = appBarLayout.shouldLift(findFirstScrollingChild(coordinatorLayout));
            }
            boolean liftedState = appBarLayout.setLiftedState(z2);
            if (!z) {
                if (liftedState) {
                    ArrayList arrayList = (ArrayList) coordinatorLayout.mChildDag.mGraph.get(appBarLayout);
                    if (arrayList != null) {
                        list = new ArrayList(arrayList);
                    }
                    if (list == null) {
                        list = Collections.emptyList();
                    }
                    int size = list.size();
                    while (i3 < size) {
                        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) ((View) list.get(i3)).getLayoutParams()).mBehavior;
                        if (!(behavior instanceof ScrollingViewBehavior)) {
                            i3++;
                        } else if (((ScrollingViewBehavior) behavior).overlayTop == 0) {
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            appBarLayout.jumpDrawablesToCurrentState();
        }

        public final void animateOffsetTo(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, int i) {
            int i2;
            int abs = Math.abs(getTopBottomOffsetForScrollingSibling() - i);
            float abs2 = Math.abs(0.0f);
            if (abs2 > 0.0f) {
                i2 = Math.round((((float) abs) / abs2) * 1000.0f) * 3;
            } else {
                i2 = (int) (((((float) abs) / ((float) appBarLayout.getHeight())) + 1.0f) * 150.0f);
            }
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            if (topBottomOffsetForScrollingSibling == i) {
                ValueAnimator valueAnimator = this.offsetAnimator;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.offsetAnimator.cancel();
                    return;
                }
                return;
            }
            ValueAnimator valueAnimator2 = this.offsetAnimator;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.offsetAnimator = valueAnimator3;
                valueAnimator3.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                this.offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        BaseBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
            } else {
                valueAnimator2.cancel();
            }
            this.offsetAnimator.setDuration((long) Math.min(i2, 600));
            this.offsetAnimator.setIntValues(new int[]{topBottomOffsetForScrollingSibling, i});
            this.offsetAnimator.start();
        }

        public final int getTopBottomOffsetForScrollingSibling() {
            return getTopAndBottomOffset() + this.offsetDelta;
        }

        public boolean isOffsetAnimatorRunning() {
            ValueAnimator valueAnimator = this.offsetAnimator;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                return false;
            }
            return true;
        }

        public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            boolean z;
            View view2;
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
                AppBarLayout appBarLayout = (AppBarLayout) view;
                WeakReference weakReference = this.lastNestedScrollingChildRef;
                if ((weakReference == null || ((view2 = (View) weakReference.get()) != null && view2.isShown() && !view2.canScrollVertically(-1))) && coordinatorLayout.isPointInChildBounds(view, x, y2)) {
                    z = true;
                } else {
                    z = false;
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

        public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            boolean z;
            int i2;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            super.onLayoutChild(coordinatorLayout, appBarLayout, i);
            int i3 = appBarLayout.pendingAction;
            SavedState savedState2 = this.savedState;
            if (savedState2 == null || (i3 & 8) != 0) {
                if (i3 != 0) {
                    if ((i3 & 4) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if ((i3 & 2) != 0) {
                        int i4 = -appBarLayout.getTotalScrollRange();
                        if (z) {
                            animateOffsetTo(coordinatorLayout, appBarLayout, i4);
                        } else {
                            setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, i4);
                        }
                    } else if ((i3 & 1) != 0) {
                        if (z) {
                            animateOffsetTo(coordinatorLayout, appBarLayout, 0);
                        } else {
                            setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, 0);
                        }
                    }
                }
            } else if (savedState2.fullyScrolled) {
                setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange());
            } else if (savedState2.fullyExpanded) {
                setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, 0);
            } else {
                View childAt = appBarLayout.getChildAt(savedState2.firstVisibleChildIndex);
                int i5 = -childAt.getBottom();
                if (this.savedState.firstVisibleChildAtMinimumHeight) {
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    i2 = appBarLayout.getTopInset() + childAt.getMinimumHeight() + i5;
                } else {
                    i2 = Math.round(((float) childAt.getHeight()) * this.savedState.firstVisibleChildPercentageShown) + i5;
                }
                setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, i2);
            }
            appBarLayout.pendingAction = 0;
            this.savedState = null;
            int clamp = MathUtils.clamp(getTopAndBottomOffset(), -appBarLayout.getTotalScrollRange(), 0);
            ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
            if (viewOffsetHelper != null) {
                viewOffsetHelper.setTopAndBottomOffset(clamp);
            } else {
                this.tempTopBottomOffset = clamp;
            }
            updateAppBarLayoutDrawableState(coordinatorLayout, appBarLayout, getTopAndBottomOffset(), 0, true);
            appBarLayout.onOffsetChanged(getTopAndBottomOffset());
            updateAccessibilityActions(coordinatorLayout, appBarLayout);
            return true;
        }

        public final boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).height != -2) {
                return false;
            }
            coordinatorLayout.onMeasureChild(appBarLayout, i, i2, View.MeasureSpec.makeMeasureSpec(0, 0));
            return true;
        }

        public final /* bridge */ /* synthetic */ void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
            onNestedPreScroll(coordinatorLayout, (AppBarLayout) view, view2, i2, iArr);
        }

        public final void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (i3 < 0) {
                CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
                AppBarLayout appBarLayout2 = appBarLayout;
                iArr[1] = setHeaderTopBottomOffset(coordinatorLayout2, appBarLayout2, getTopBottomOffsetForScrollingSibling() - i3, -appBarLayout.getDownNestedScrollRange(), 0);
            }
            if (i3 == 0) {
                updateAccessibilityActions(coordinatorLayout, appBarLayout);
            }
        }

        public final void onRestoreInstanceState(View view, Parcelable parcelable) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (parcelable instanceof SavedState) {
                this.savedState = (SavedState) parcelable;
            } else {
                this.savedState = null;
            }
        }

        public final Parcelable onSaveInstanceState(View view) {
            android.view.AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            SavedState saveScrollState = saveScrollState(absSavedState, (AppBarLayout) view);
            if (saveScrollState == null) {
                return absSavedState;
            }
            return saveScrollState;
        }

        public final boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2) {
            boolean z;
            ValueAnimator valueAnimator;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if ((i & 2) == 0 || (!appBarLayout.liftOnScroll && (appBarLayout.getTotalScrollRange() == 0 || coordinatorLayout.getHeight() - view2.getHeight() > appBarLayout.getHeight()))) {
                z = false;
            } else {
                z = true;
            }
            if (z && (valueAnimator = this.offsetAnimator) != null) {
                valueAnimator.cancel();
            }
            this.lastNestedScrollingChildRef = null;
            this.lastStartedType = i2;
            return z;
        }

        public final void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (this.lastStartedType == 0 || i == 1) {
                snapToChildIfNeeded(coordinatorLayout, appBarLayout);
                if (appBarLayout.liftOnScroll) {
                    appBarLayout.setLiftedState(appBarLayout.shouldLift(view2));
                }
            }
            this.lastNestedScrollingChildRef = new WeakReference(view2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:36:0x00e3  */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00ec  */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x00f3 A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:45:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout r21, android.view.View r22, android.view.MotionEvent r23) {
            /*
                r20 = this;
                r6 = r20
                r1 = r21
                r2 = r22
                r7 = r23
                int r0 = r23.getActionMasked()
                r3 = 0
                r4 = -1
                r8 = 1
                r9 = 0
                if (r0 == r8) goto L_0x0064
                r5 = 2
                if (r0 == r5) goto L_0x0036
                r1 = 3
                if (r0 == r1) goto L_0x00da
                r1 = 6
                if (r0 == r1) goto L_0x001c
                goto L_0x0061
            L_0x001c:
                int r0 = r23.getActionIndex()
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
                goto L_0x0061
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
                r0 = r2
                com.google.android.material.appbar.AppBarLayout r0 = (com.google.android.material.appbar.AppBarLayout) r0
                int r0 = r0.getDownNestedScrollRange()
                int r4 = -r0
                int r0 = r20.getTopBottomOffsetForScrollingSibling()
                int r3 = r0 - r3
                r5 = 0
                r0 = r20
                r1 = r21
                r2 = r22
                r0.setHeaderTopBottomOffset(r1, r2, r3, r4, r5)
            L_0x0061:
                r0 = r9
                goto L_0x00e8
            L_0x0064:
                android.view.VelocityTracker r0 = r6.velocityTracker
                if (r0 == 0) goto L_0x00da
                r0.addMovement(r7)
                android.view.VelocityTracker r0 = r6.velocityTracker
                r5 = 1000(0x3e8, float:1.401E-42)
                r0.computeCurrentVelocity(r5)
                android.view.VelocityTracker r0 = r6.velocityTracker
                int r5 = r6.activePointerId
                float r0 = r0.getYVelocity(r5)
                r5 = r2
                com.google.android.material.appbar.AppBarLayout r5 = (com.google.android.material.appbar.AppBarLayout) r5
                int r10 = r5.getTotalScrollRange()
                int r10 = -r10
                com.google.android.material.appbar.HeaderBehavior$FlingRunnable r11 = r6.flingRunnable
                if (r11 == 0) goto L_0x008b
                r2.removeCallbacks(r11)
                r6.flingRunnable = r3
            L_0x008b:
                android.widget.OverScroller r11 = r6.scroller
                if (r11 != 0) goto L_0x009a
                android.widget.OverScroller r11 = new android.widget.OverScroller
                android.content.Context r12 = r22.getContext()
                r11.<init>(r12)
                r6.scroller = r11
            L_0x009a:
                android.widget.OverScroller r11 = r6.scroller
                int r13 = r20.getTopAndBottomOffset()
                int r15 = java.lang.Math.round(r0)
                r17 = 0
                r19 = 0
                r12 = 0
                r14 = 0
                r16 = 0
                r18 = r10
                r11.fling(r12, r13, r14, r15, r16, r17, r18, r19)
                android.widget.OverScroller r0 = r6.scroller
                boolean r0 = r0.computeScrollOffset()
                if (r0 == 0) goto L_0x00c6
                com.google.android.material.appbar.HeaderBehavior$FlingRunnable r0 = new com.google.android.material.appbar.HeaderBehavior$FlingRunnable
                r0.<init>(r6, r1, r2)
                r6.flingRunnable = r0
                java.util.WeakHashMap r1 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
                r2.postOnAnimation(r0)
                goto L_0x00d8
            L_0x00c6:
                r6.snapToChildIfNeeded(r1, r5)
                boolean r0 = r5.liftOnScroll
                if (r0 == 0) goto L_0x00d8
                android.view.View r0 = findFirstScrollingChild(r21)
                boolean r0 = r5.shouldLift(r0)
                r5.setLiftedState(r0)
            L_0x00d8:
                r0 = r8
                goto L_0x00db
            L_0x00da:
                r0 = r9
            L_0x00db:
                r6.isBeingDragged = r9
                r6.activePointerId = r4
                android.view.VelocityTracker r1 = r6.velocityTracker
                if (r1 == 0) goto L_0x00e8
                r1.recycle()
                r6.velocityTracker = r3
            L_0x00e8:
                android.view.VelocityTracker r1 = r6.velocityTracker
                if (r1 == 0) goto L_0x00ef
                r1.addMovement(r7)
            L_0x00ef:
                boolean r1 = r6.isBeingDragged
                if (r1 != 0) goto L_0x00f7
                if (r0 == 0) goto L_0x00f6
                goto L_0x00f7
            L_0x00f6:
                r8 = r9
            L_0x00f7:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
        }

        /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.material.appbar.AppBarLayout$BaseBehavior$SavedState, androidx.customview.view.AbsSavedState] */
        public final SavedState saveScrollState(Parcelable parcelable, AppBarLayout appBarLayout) {
            boolean z;
            boolean z2;
            int topAndBottomOffset = getTopAndBottomOffset();
            int childCount = appBarLayout.getChildCount();
            boolean z3 = false;
            int i = 0;
            while (i < childCount) {
                View childAt = appBarLayout.getChildAt(i);
                int bottom = childAt.getBottom() + topAndBottomOffset;
                if (childAt.getTop() + topAndBottomOffset > 0 || bottom < 0) {
                    i++;
                } else {
                    if (parcelable == null) {
                        parcelable = AbsSavedState.EMPTY_STATE;
                    }
                    ? absSavedState = new AbsSavedState(parcelable);
                    if (topAndBottomOffset == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    absSavedState.fullyExpanded = z;
                    if (z || (-topAndBottomOffset) < appBarLayout.getTotalScrollRange()) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    absSavedState.fullyScrolled = z2;
                    absSavedState.firstVisibleChildIndex = i;
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    if (bottom == appBarLayout.getTopInset() + childAt.getMinimumHeight()) {
                        z3 = true;
                    }
                    absSavedState.firstVisibleChildAtMinimumHeight = z3;
                    absSavedState.firstVisibleChildPercentageShown = ((float) bottom) / ((float) childAt.getHeight());
                    return absSavedState;
                }
            }
            return null;
        }

        public final void setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, View view, int i) {
            setHeaderTopBottomOffset(coordinatorLayout, view, i, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        public final void snapToChildIfNeeded(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            int paddingTop = appBarLayout.getPaddingTop() + appBarLayout.getTopInset();
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling() - paddingTop;
            int childCount = appBarLayout.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    i = -1;
                    break;
                }
                View childAt = appBarLayout.getChildAt(i);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if ((layoutParams.scrollFlags & 32) == 32) {
                    top -= layoutParams.topMargin;
                    bottom += layoutParams.bottomMargin;
                }
                int i2 = -topBottomOffsetForScrollingSibling;
                if (top <= i2 && bottom >= i2) {
                    break;
                }
                i++;
            }
            if (i >= 0) {
                View childAt2 = appBarLayout.getChildAt(i);
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                int i3 = layoutParams2.scrollFlags;
                if ((i3 & 17) == 17) {
                    int i4 = -childAt2.getTop();
                    int i5 = -childAt2.getBottom();
                    if (i == 0) {
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        if (appBarLayout.getFitsSystemWindows() && childAt2.getFitsSystemWindows()) {
                            i4 -= appBarLayout.getTopInset();
                        }
                    }
                    if ((i3 & 2) == 2) {
                        WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                        i5 += childAt2.getMinimumHeight();
                    } else if ((i3 & 5) == 5) {
                        WeakHashMap weakHashMap3 = ViewCompat.sViewPropertyAnimatorMap;
                        int minimumHeight = childAt2.getMinimumHeight() + i5;
                        if (topBottomOffsetForScrollingSibling < minimumHeight) {
                            i4 = minimumHeight;
                        } else {
                            i5 = minimumHeight;
                        }
                    }
                    if ((i3 & 32) == 32) {
                        i4 += layoutParams2.topMargin;
                        i5 -= layoutParams2.bottomMargin;
                    }
                    if (topBottomOffsetForScrollingSibling < (i5 + i4) / 2) {
                        i4 = i5;
                    }
                    animateOffsetTo(coordinatorLayout, appBarLayout, MathUtils.clamp(i4 + paddingTop, -appBarLayout.getTotalScrollRange(), 0));
                }
            }
        }

        public final void updateAccessibilityActions(CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
            final View view;
            ViewCompat.removeAccessibilityAction(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId());
            ViewCompat.removeActionWithId(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId());
            boolean z = false;
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(coordinatorLayout, 0);
            if (appBarLayout.getTotalScrollRange() != 0) {
                int childCount = coordinatorLayout.getChildCount();
                int i = 0;
                while (true) {
                    if (i >= childCount) {
                        view = null;
                        break;
                    }
                    View childAt = coordinatorLayout.getChildAt(i);
                    if (((CoordinatorLayout.LayoutParams) childAt.getLayoutParams()).mBehavior instanceof ScrollingViewBehavior) {
                        view = childAt;
                        break;
                    }
                    i++;
                }
                if (view != null) {
                    int childCount2 = appBarLayout.getChildCount();
                    for (int i2 = 0; i2 < childCount2; i2++) {
                        if (((LayoutParams) appBarLayout.getChildAt(i2).getLayoutParams()).scrollFlags != 0) {
                            if (ViewCompat.Api29Impl.getAccessibilityDelegate(coordinatorLayout) == null) {
                                ViewCompat.setAccessibilityDelegate(coordinatorLayout, new AccessibilityDelegateCompat() {
                                    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                                        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                                        accessibilityNodeInfoCompat.setScrollable(BaseBehavior.this.coordinatorLayoutA11yScrollable);
                                        accessibilityNodeInfoCompat.setClassName(ScrollView.class.getName());
                                    }
                                });
                            }
                            boolean z2 = true;
                            if (getTopBottomOffsetForScrollingSibling() != (-appBarLayout.getTotalScrollRange())) {
                                ViewCompat.replaceAccessibilityAction(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD, (CharSequence) null, new AccessibilityViewCommand(false) {
                                    public final boolean perform(View view) {
                                        AppBarLayout appBarLayout = AppBarLayout.this;
                                        appBarLayout.getClass();
                                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                                        appBarLayout.setExpanded(true, appBarLayout.isLaidOut(), true);
                                        return true;
                                    }
                                });
                                z = true;
                            }
                            if (getTopBottomOffsetForScrollingSibling() != 0) {
                                if (view.canScrollVertically(-1)) {
                                    final int i3 = -appBarLayout.getDownNestedPreScrollRange();
                                    if (i3 != 0) {
                                        final CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
                                        final AppBarLayout appBarLayout2 = appBarLayout;
                                        ViewCompat.replaceAccessibilityAction(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD, (CharSequence) null, new AccessibilityViewCommand() {
                                            public final boolean perform(View view) {
                                                View view2 = view;
                                                int i = i3;
                                                BaseBehavior.this.onNestedPreScroll(coordinatorLayout2, appBarLayout2, view2, i, new int[]{0, 0});
                                                return true;
                                            }
                                        });
                                    }
                                } else {
                                    ViewCompat.replaceAccessibilityAction(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD, (CharSequence) null, new AccessibilityViewCommand(true) {
                                        public final boolean perform(View view) {
                                            AppBarLayout appBarLayout = AppBarLayout.this;
                                            appBarLayout.getClass();
                                            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                                            appBarLayout.setExpanded(true, appBarLayout.isLaidOut(), true);
                                            return true;
                                        }
                                    });
                                }
                                this.coordinatorLayoutA11yScrollable = z2;
                                return;
                            }
                            z2 = z;
                            this.coordinatorLayoutA11yScrollable = z2;
                            return;
                        }
                    }
                }
            }
        }

        public final void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int[] iArr) {
            int i2;
            int i3;
            if (i != 0) {
                if (i < 0) {
                    i2 = -appBarLayout.getTotalScrollRange();
                    i3 = appBarLayout.getDownNestedPreScrollRange() + i2;
                } else {
                    i2 = -appBarLayout.getTotalScrollRange();
                    i3 = 0;
                }
                int i4 = i2;
                int i5 = i3;
                if (i4 != i5) {
                    iArr[1] = setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, getTopBottomOffsetForScrollingSibling() - i, i4, i5);
                }
            }
            if (appBarLayout.liftOnScroll) {
                appBarLayout.setLiftedState(appBarLayout.shouldLift(view));
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:30:0x008c  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0091  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x009d  */
        /* JADX WARNING: Removed duplicated region for block: B:56:0x013c  */
        /* JADX WARNING: Removed duplicated region for block: B:62:0x015b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final int setHeaderTopBottomOffset(androidx.coordinatorlayout.widget.CoordinatorLayout r11, android.view.View r12, int r13, int r14, int r15) {
            /*
                r10 = this;
                com.google.android.material.appbar.AppBarLayout r12 = (com.google.android.material.appbar.AppBarLayout) r12
                int r0 = r10.getTopBottomOffsetForScrollingSibling()
                r1 = 0
                if (r14 == 0) goto L_0x0161
                if (r0 < r14) goto L_0x0161
                if (r0 > r15) goto L_0x0161
                int r13 = androidx.core.math.MathUtils.clamp(r13, r14, r15)
                if (r0 == r13) goto L_0x0163
                boolean r14 = r12.haveChildWithInterpolator
                if (r14 == 0) goto L_0x0087
                int r14 = java.lang.Math.abs(r13)
                int r15 = r12.getChildCount()
                r2 = r1
            L_0x0020:
                if (r2 >= r15) goto L_0x0087
                android.view.View r3 = r12.getChildAt(r2)
                android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
                com.google.android.material.appbar.AppBarLayout$LayoutParams r4 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r4
                android.view.animation.Interpolator r5 = r4.scrollInterpolator
                int r6 = r3.getTop()
                if (r14 < r6) goto L_0x0084
                int r6 = r3.getBottom()
                if (r14 > r6) goto L_0x0084
                if (r5 == 0) goto L_0x0087
                int r15 = r4.scrollFlags
                r2 = r15 & 1
                if (r2 == 0) goto L_0x0058
                int r2 = r3.getHeight()
                int r6 = r4.topMargin
                int r2 = r2 + r6
                int r4 = r4.bottomMargin
                int r2 = r2 + r4
                r15 = r15 & 2
                if (r15 == 0) goto L_0x0059
                java.util.WeakHashMap r15 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
                int r15 = r3.getMinimumHeight()
                int r2 = r2 - r15
                goto L_0x0059
            L_0x0058:
                r2 = r1
            L_0x0059:
                java.util.WeakHashMap r15 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
                boolean r15 = r3.getFitsSystemWindows()
                if (r15 == 0) goto L_0x0066
                int r15 = r12.getTopInset()
                int r2 = r2 - r15
            L_0x0066:
                if (r2 <= 0) goto L_0x0087
                int r15 = r3.getTop()
                int r14 = r14 - r15
                float r15 = (float) r2
                float r14 = (float) r14
                float r14 = r14 / r15
                float r14 = r5.getInterpolation(r14)
                float r14 = r14 * r15
                int r14 = java.lang.Math.round(r14)
                int r15 = java.lang.Integer.signum(r13)
                int r2 = r3.getTop()
                int r2 = r2 + r14
                int r2 = r2 * r15
                goto L_0x0088
            L_0x0084:
                int r2 = r2 + 1
                goto L_0x0020
            L_0x0087:
                r2 = r13
            L_0x0088:
                com.google.android.material.appbar.ViewOffsetHelper r14 = r10.viewOffsetHelper
                if (r14 == 0) goto L_0x0091
                boolean r14 = r14.setTopAndBottomOffset(r2)
                goto L_0x0094
            L_0x0091:
                r10.tempTopBottomOffset = r2
                r14 = r1
            L_0x0094:
                int r15 = r0 - r13
                int r2 = r13 - r2
                r10.offsetDelta = r2
                r2 = 1
                if (r14 == 0) goto L_0x011d
                r3 = r1
            L_0x009e:
                int r4 = r12.getChildCount()
                if (r3 >= r4) goto L_0x011d
                android.view.View r4 = r12.getChildAt(r3)
                android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
                com.google.android.material.appbar.AppBarLayout$LayoutParams r4 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r4
                com.google.android.material.appbar.AppBarLayout$CompressChildScrollEffect r5 = r4.scrollEffect
                if (r5 == 0) goto L_0x011a
                int r4 = r4.scrollFlags
                r4 = r4 & r2
                if (r4 == 0) goto L_0x011a
                android.view.View r4 = r12.getChildAt(r3)
                int r6 = r10.getTopAndBottomOffset()
                float r6 = (float) r6
                android.graphics.Rect r7 = r5.relativeRect
                r4.getDrawingRect(r7)
                r12.offsetDescendantRectToMyCoords(r4, r7)
                int r8 = r12.getTopInset()
                int r8 = -r8
                r7.offset(r1, r8)
                int r8 = r7.top
                float r8 = (float) r8
                float r6 = java.lang.Math.abs(r6)
                float r8 = r8 - r6
                r6 = 0
                int r9 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r9 > 0) goto L_0x0111
                int r6 = r7.height()
                float r6 = (float) r6
                float r6 = r8 / r6
                float r6 = java.lang.Math.abs(r6)
                float r6 = androidx.core.math.MathUtils.clamp(r6)
                float r8 = -r8
                r9 = 1065353216(0x3f800000, float:1.0)
                float r6 = r9 - r6
                float r6 = r6 * r6
                float r9 = r9 - r6
                int r6 = r7.height()
                float r6 = (float) r6
                r7 = 1050253722(0x3e99999a, float:0.3)
                float r6 = r6 * r7
                float r6 = r6 * r9
                float r8 = r8 - r6
                r4.setTranslationY(r8)
                android.graphics.Rect r5 = r5.ghostRect
                r4.getDrawingRect(r5)
                float r6 = -r8
                int r6 = (int) r6
                r5.offset(r1, r6)
                java.util.WeakHashMap r6 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
                r4.setClipBounds(r5)
                goto L_0x011a
            L_0x0111:
                java.util.WeakHashMap r5 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
                r5 = 0
                r4.setClipBounds(r5)
                r4.setTranslationY(r6)
            L_0x011a:
                int r3 = r3 + 1
                goto L_0x009e
            L_0x011d:
                if (r14 != 0) goto L_0x0152
                boolean r14 = r12.haveChildWithInterpolator
                if (r14 == 0) goto L_0x0152
                androidx.coordinatorlayout.widget.DirectedAcyclicGraph r14 = r11.mChildDag
                androidx.collection.SimpleArrayMap r14 = r14.mGraph
                java.lang.Object r14 = r14.get(r12)
                java.util.ArrayList r14 = (java.util.ArrayList) r14
                if (r14 == 0) goto L_0x0152
                boolean r3 = r14.isEmpty()
                if (r3 != 0) goto L_0x0152
                r3 = r1
            L_0x0136:
                int r4 = r14.size()
                if (r3 >= r4) goto L_0x0152
                java.lang.Object r4 = r14.get(r3)
                android.view.View r4 = (android.view.View) r4
                android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
                androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r5 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r5
                androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r5 = r5.mBehavior
                if (r5 == 0) goto L_0x014f
                r5.onDependentViewChanged(r4, r12)
            L_0x014f:
                int r3 = r3 + 1
                goto L_0x0136
            L_0x0152:
                int r14 = r10.getTopAndBottomOffset()
                r12.onOffsetChanged(r14)
                if (r13 >= r0) goto L_0x015c
                r2 = -1
            L_0x015c:
                updateAppBarLayoutDrawableState(r11, r12, r13, r2, r1)
                r1 = r15
                goto L_0x0163
            L_0x0161:
                r10.offsetDelta = r1
            L_0x0163:
                r10.updateAccessibilityActions(r11, r12)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.setHeaderTopBottomOffset(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, int, int, int):int");
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(0);
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public class Behavior extends BaseBehavior {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class CompressChildScrollEffect {
        public final Rect ghostRect = new Rect();
        public final Rect relativeRect = new Rect();
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class LayoutParams extends LinearLayout.LayoutParams {
        public CompressChildScrollEffect scrollEffect;
        public int scrollFlags;
        public Interpolator scrollInterpolator;
    }

    public AppBarLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.statusBarForeground != null && getTopInset() > 0) {
            int save = canvas.save();
            canvas.translate(0.0f, (float) (-this.currentOffset));
            this.statusBarForeground.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.statusBarForeground;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.view.ViewGroup$LayoutParams, com.google.android.material.appbar.AppBarLayout$LayoutParams, android.widget.LinearLayout$LayoutParams] */
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        ? layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.scrollFlags = 1;
        return layoutParams;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int getDownNestedPreScrollRange() {
        /*
            r9 = this;
            int r0 = r9.downPreScrollRange
            r1 = -1
            if (r0 == r1) goto L_0x0006
            return r0
        L_0x0006:
            int r0 = r9.getChildCount()
            int r0 = r0 + -1
            r1 = 0
            r2 = r1
        L_0x000e:
            if (r0 < 0) goto L_0x0069
            android.view.View r3 = r9.getChildAt(r0)
            int r4 = r3.getVisibility()
            r5 = 8
            if (r4 != r5) goto L_0x001d
            goto L_0x0066
        L_0x001d:
            android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
            com.google.android.material.appbar.AppBarLayout$LayoutParams r4 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r4
            int r5 = r3.getMeasuredHeight()
            int r6 = r4.scrollFlags
            r7 = r6 & 5
            r8 = 5
            if (r7 != r8) goto L_0x0063
            int r7 = r4.topMargin
            int r4 = r4.bottomMargin
            int r7 = r7 + r4
            r4 = r6 & 8
            if (r4 == 0) goto L_0x003f
            java.util.WeakHashMap r4 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            int r4 = r3.getMinimumHeight()
        L_0x003d:
            int r4 = r4 + r7
            goto L_0x004e
        L_0x003f:
            r4 = r6 & 2
            if (r4 == 0) goto L_0x004c
            java.util.WeakHashMap r4 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            int r4 = r3.getMinimumHeight()
            int r4 = r5 - r4
            goto L_0x003d
        L_0x004c:
            int r4 = r7 + r5
        L_0x004e:
            if (r0 != 0) goto L_0x0061
            java.util.WeakHashMap r6 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            boolean r3 = r3.getFitsSystemWindows()
            if (r3 == 0) goto L_0x0061
            int r3 = r9.getTopInset()
            int r5 = r5 - r3
            int r4 = java.lang.Math.min(r4, r5)
        L_0x0061:
            int r2 = r2 + r4
            goto L_0x0066
        L_0x0063:
            if (r2 <= 0) goto L_0x0066
            goto L_0x0069
        L_0x0066:
            int r0 = r0 + -1
            goto L_0x000e
        L_0x0069:
            int r0 = java.lang.Math.max(r1, r2)
            r9.downPreScrollRange = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.getDownNestedPreScrollRange():int");
    }

    public final int getDownNestedScrollRange() {
        int i = this.downScrollRange;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = layoutParams.topMargin + layoutParams.bottomMargin + childAt.getMeasuredHeight();
                int i4 = layoutParams.scrollFlags;
                if ((i4 & 1) == 0) {
                    break;
                }
                i3 += measuredHeight;
                if ((i4 & 2) != 0) {
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    i3 -= childAt.getMinimumHeight();
                    break;
                }
            }
            i2++;
        }
        int max = Math.max(0, i3);
        this.downScrollRange = max;
        return max;
    }

    public final int getTopInset() {
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            return windowInsetsCompat.getSystemWindowInsetTop();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i = this.totalScrollRange;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i4 = layoutParams.scrollFlags;
                if ((i4 & 1) == 0) {
                    break;
                }
                int i5 = measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin + i3;
                if (i2 == 0) {
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    if (childAt.getFitsSystemWindows()) {
                        i5 -= getTopInset();
                    }
                }
                i3 = i5;
                if ((i4 & 2) != 0) {
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    i3 -= childAt.getMinimumHeight();
                    break;
                }
            }
            i2++;
        }
        int max = Math.max(0, i3);
        this.totalScrollRange = max;
        return max;
    }

    public final void invalidateScrollRanges() {
        BaseBehavior.SavedState savedState;
        Behavior behavior2 = this.behavior;
        if (behavior2 == null || this.totalScrollRange == -1 || this.pendingAction != 0) {
            savedState = null;
        } else {
            savedState = behavior2.saveScrollState(AbsSavedState.EMPTY_STATE, this);
        }
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        if (savedState != null) {
            Behavior behavior3 = this.behavior;
            if (behavior3.savedState == null) {
                behavior3.savedState = savedState;
            }
        }
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof MaterialShapeDrawable) {
            MaterialShapeUtils.setParentAbsoluteElevation(this, (MaterialShapeDrawable) background);
        }
    }

    public final int[] onCreateDrawableState(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (this.tmpStatesArray == null) {
            this.tmpStatesArray = new int[4];
        }
        int[] iArr = this.tmpStatesArray;
        int[] onCreateDrawableState = super.onCreateDrawableState(i + iArr.length);
        boolean z = this.liftable;
        if (z) {
            i2 = 2130969965;
        } else {
            i2 = -2130969965;
        }
        iArr[0] = i2;
        if (!z || !this.lifted) {
            i3 = -2130969966;
        } else {
            i3 = 2130969966;
        }
        iArr[1] = i3;
        if (z) {
            i4 = 2130969961;
        } else {
            i4 = -2130969961;
        }
        iArr[2] = i4;
        if (!z || !this.lifted) {
            i5 = -2130969960;
        } else {
            i5 = 2130969960;
        }
        iArr[3] = i5;
        return LinearLayout.mergeDrawableStates(onCreateDrawableState, iArr);
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WeakReference weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.liftOnScrollTargetView = null;
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        boolean z2 = true;
        if (getFitsSystemWindows() && shouldOffsetFirstChild()) {
            int topInset = getTopInset();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                getChildAt(childCount).offsetTopAndBottom(topInset);
            }
        }
        invalidateScrollRanges();
        this.haveChildWithInterpolator = false;
        int childCount2 = getChildCount();
        int i5 = 0;
        while (true) {
            if (i5 >= childCount2) {
                break;
            } else if (((LayoutParams) getChildAt(i5).getLayoutParams()).scrollInterpolator != null) {
                this.haveChildWithInterpolator = true;
                break;
            } else {
                i5++;
            }
        }
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (!this.liftOnScroll) {
            int childCount3 = getChildCount();
            int i6 = 0;
            while (true) {
                if (i6 >= childCount3) {
                    z2 = false;
                    break;
                }
                int i7 = ((LayoutParams) getChildAt(i6).getLayoutParams()).scrollFlags;
                if ((i7 & 1) == 1 && (i7 & 10) != 0) {
                    break;
                }
                i6++;
            }
        }
        if (this.liftable != z2) {
            this.liftable = z2;
            refreshDrawableState();
        }
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            if (getFitsSystemWindows() && shouldOffsetFirstChild()) {
                int measuredHeight = getMeasuredHeight();
                if (mode == Integer.MIN_VALUE) {
                    measuredHeight = MathUtils.clamp(getTopInset() + getMeasuredHeight(), 0, View.MeasureSpec.getSize(i2));
                } else if (mode == 0) {
                    measuredHeight += getTopInset();
                }
                setMeasuredDimension(getMeasuredWidth(), measuredHeight);
            }
        }
        invalidateScrollRanges();
    }

    public final void onOffsetChanged(int i) {
        int i2;
        this.currentOffset = i;
        if (!willNotDraw()) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            postInvalidateOnAnimation();
        }
        List list = this.listeners;
        if (list != null) {
            int size = ((ArrayList) list).size();
            for (int i3 = 0; i3 < size; i3++) {
                CollapsingToolbarLayout.OffsetUpdateListener offsetUpdateListener = (CollapsingToolbarLayout.OffsetUpdateListener) ((ArrayList) this.listeners).get(i3);
                if (offsetUpdateListener != null) {
                    CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
                    collapsingToolbarLayout.currentOffset = i;
                    WindowInsetsCompat windowInsetsCompat = collapsingToolbarLayout.lastInsets;
                    if (windowInsetsCompat != null) {
                        i2 = windowInsetsCompat.getSystemWindowInsetTop();
                    } else {
                        i2 = 0;
                    }
                    int childCount = collapsingToolbarLayout.getChildCount();
                    for (int i4 = 0; i4 < childCount; i4++) {
                        View childAt = collapsingToolbarLayout.getChildAt(i4);
                        CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) childAt.getLayoutParams();
                        ViewOffsetHelper viewOffsetHelper = CollapsingToolbarLayout.getViewOffsetHelper(childAt);
                        int i5 = layoutParams.collapseMode;
                        if (i5 == 1) {
                            viewOffsetHelper.setTopAndBottomOffset(MathUtils.clamp(-i, 0, ((collapsingToolbarLayout.getHeight() - CollapsingToolbarLayout.getViewOffsetHelper(childAt).layoutTop) - childAt.getHeight()) - ((CollapsingToolbarLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin));
                        } else if (i5 == 2) {
                            viewOffsetHelper.setTopAndBottomOffset(Math.round(((float) (-i)) * layoutParams.parallaxMult));
                        }
                    }
                    collapsingToolbarLayout.updateScrimVisibility();
                    if (collapsingToolbarLayout.statusBarScrim != null && i2 > 0) {
                        WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                        collapsingToolbarLayout.postInvalidateOnAnimation();
                    }
                    int height = collapsingToolbarLayout.getHeight();
                    WeakHashMap weakHashMap3 = ViewCompat.sViewPropertyAnimatorMap;
                    int minimumHeight = (height - collapsingToolbarLayout.getMinimumHeight()) - i2;
                    int scrimVisibleHeightTrigger = height - collapsingToolbarLayout.getScrimVisibleHeightTrigger();
                    CollapsingTextHelper collapsingTextHelper = collapsingToolbarLayout.collapsingTextHelper;
                    float f = (float) minimumHeight;
                    float min = Math.min(1.0f, ((float) scrimVisibleHeightTrigger) / f);
                    collapsingTextHelper.fadeModeStartFraction = min;
                    collapsingTextHelper.fadeModeThresholdFraction = AndroidFlingSpline$$ExternalSyntheticOutline0.m(1.0f, min, 0.5f, min);
                    CollapsingTextHelper collapsingTextHelper2 = collapsingToolbarLayout.collapsingTextHelper;
                    collapsingTextHelper2.currentOffsetY = collapsingToolbarLayout.currentOffset + minimumHeight;
                    collapsingTextHelper2.setExpansionFraction(((float) Math.abs(i)) / f);
                }
            }
        }
    }

    public final void setElevation(float f) {
        super.setElevation(f);
        Drawable background = getBackground();
        if (background instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable) background).setElevation(f);
        }
    }

    public final void setExpanded(boolean z, boolean z2, boolean z3) {
        int i;
        int i2;
        if (z) {
            i = 1;
        } else {
            i = 2;
        }
        int i3 = 0;
        if (z2) {
            i2 = 4;
        } else {
            i2 = 0;
        }
        int i4 = i | i2;
        if (z3) {
            i3 = 8;
        }
        this.pendingAction = i4 | i3;
        requestLayout();
    }

    public final boolean setLiftedState(boolean z) {
        float f;
        if (this.lifted == z) {
            return false;
        }
        this.lifted = z;
        refreshDrawableState();
        if (!this.liftOnScroll || !(getBackground() instanceof MaterialShapeDrawable)) {
            return true;
        }
        final MaterialShapeDrawable materialShapeDrawable = (MaterialShapeDrawable) getBackground();
        float dimension = getResources().getDimension(2131165734);
        if (z) {
            f = 0.0f;
        } else {
            f = dimension;
        }
        if (!z) {
            dimension = 0.0f;
        }
        ValueAnimator valueAnimator = this.elevationOverlayAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, dimension});
        this.elevationOverlayAnimator = ofFloat;
        ofFloat.setDuration((long) getResources().getInteger(2131427331));
        this.elevationOverlayAnimator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        this.elevationOverlayAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                materialShapeDrawable.setElevation(floatValue);
                Drawable drawable = AppBarLayout.this.statusBarForeground;
                if (drawable instanceof MaterialShapeDrawable) {
                    ((MaterialShapeDrawable) drawable).setElevation(floatValue);
                }
                Iterator it = AppBarLayout.this.liftOnScrollListeners.iterator();
                if (it.hasNext()) {
                    WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
                    int i = materialShapeDrawable.resolvedTintColor;
                    throw null;
                }
            }
        });
        this.elevationOverlayAnimator.start();
        return true;
    }

    public final void setOrientation(int i) {
        if (i == 1) {
            super.setOrientation(i);
            return;
        }
        throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
    }

    public final void setVisibility(int i) {
        boolean z;
        super.setVisibility(i);
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean shouldLift(android.view.View r5) {
        /*
            r4 = this;
            java.lang.ref.WeakReference r0 = r4.liftOnScrollTargetView
            r1 = -1
            r2 = 0
            if (r0 != 0) goto L_0x0031
            int r0 = r4.liftOnScrollTargetViewId
            if (r0 == r1) goto L_0x0031
            if (r5 == 0) goto L_0x0011
            android.view.View r0 = r5.findViewById(r0)
            goto L_0x0012
        L_0x0011:
            r0 = r2
        L_0x0012:
            if (r0 != 0) goto L_0x0028
            android.view.ViewParent r3 = r4.getParent()
            boolean r3 = r3 instanceof android.view.ViewGroup
            if (r3 == 0) goto L_0x0028
            android.view.ViewParent r0 = r4.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            int r3 = r4.liftOnScrollTargetViewId
            android.view.View r0 = r0.findViewById(r3)
        L_0x0028:
            if (r0 == 0) goto L_0x0031
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference
            r3.<init>(r0)
            r4.liftOnScrollTargetView = r3
        L_0x0031:
            java.lang.ref.WeakReference r4 = r4.liftOnScrollTargetView
            if (r4 == 0) goto L_0x003c
            java.lang.Object r4 = r4.get()
            r2 = r4
            android.view.View r2 = (android.view.View) r2
        L_0x003c:
            if (r2 != 0) goto L_0x003f
            goto L_0x0040
        L_0x003f:
            r5 = r2
        L_0x0040:
            if (r5 == 0) goto L_0x0050
            boolean r4 = r5.canScrollVertically(r1)
            if (r4 != 0) goto L_0x004e
            int r4 = r5.getScrollY()
            if (r4 <= 0) goto L_0x0050
        L_0x004e:
            r4 = 1
            goto L_0x0051
        L_0x0050:
            r4 = 0
        L_0x0051:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.shouldLift(android.view.View):boolean");
    }

    public final boolean shouldOffsetFirstChild() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        if (childAt.getVisibility() == 8) {
            return false;
        }
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (!childAt.getFitsSystemWindows()) {
            return true;
        }
        return false;
    }

    public final boolean verifyDrawable(Drawable drawable) {
        if (super.verifyDrawable(drawable) || drawable == this.statusBarForeground) {
            return true;
        }
        return false;
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130968644);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppBarLayout(android.content.Context r16, android.util.AttributeSet r17, int r18) {
        /*
            r15 = this;
            r0 = r15
            r7 = r17
            r8 = r18
            r1 = 2132018527(0x7f14055f, float:1.9675363E38)
            r2 = r16
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r2, r7, r8, r1)
            r15.<init>(r1, r7, r8)
            r9 = -1
            r0.totalScrollRange = r9
            r0.downPreScrollRange = r9
            r0.downScrollRange = r9
            r10 = 0
            r0.pendingAction = r10
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r0.liftOnScrollListeners = r1
            android.content.Context r11 = r15.getContext()
            r12 = 1
            r15.setOrientation(r12)
            android.view.ViewOutlineProvider r1 = r15.getOutlineProvider()
            android.view.ViewOutlineProvider r2 = android.view.ViewOutlineProvider.BACKGROUND
            if (r1 != r2) goto L_0x0037
            android.view.ViewOutlineProvider r1 = android.view.ViewOutlineProvider.BOUNDS
            r15.setOutlineProvider(r1)
        L_0x0037:
            android.content.Context r13 = r15.getContext()
            int[] r3 = com.google.android.material.appbar.ViewUtilsLollipop.STATE_LIST_ANIM_ATTRS
            int[] r6 = new int[r10]
            r5 = 2132018527(0x7f14055f, float:1.9675363E38)
            r1 = r13
            r2 = r17
            r4 = r18
            android.content.res.TypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r1, r2, r3, r4, r5, r6)
            boolean r2 = r1.hasValue(r10)     // Catch:{ all -> 0x005d }
            if (r2 == 0) goto L_0x0060
            int r2 = r1.getResourceId(r10, r10)     // Catch:{ all -> 0x005d }
            android.animation.StateListAnimator r2 = android.animation.AnimatorInflater.loadStateListAnimator(r13, r2)     // Catch:{ all -> 0x005d }
            r15.setStateListAnimator(r2)     // Catch:{ all -> 0x005d }
            goto L_0x0060
        L_0x005d:
            r0 = move-exception
            goto L_0x019f
        L_0x0060:
            r1.recycle()
            int[] r3 = com.google.android.material.R$styleable.AppBarLayout
            r5 = 2132018527(0x7f14055f, float:1.9675363E38)
            int[] r6 = new int[r10]
            r1 = r11
            r2 = r17
            r4 = r18
            android.content.res.TypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r1, r2, r3, r4, r5, r6)
            android.graphics.drawable.Drawable r2 = r1.getDrawable(r10)
            java.util.WeakHashMap r3 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            r15.setBackground(r2)
            android.graphics.drawable.Drawable r2 = r15.getBackground()
            boolean r2 = r2 instanceof android.graphics.drawable.ColorDrawable
            if (r2 == 0) goto L_0x00a0
            android.graphics.drawable.Drawable r2 = r15.getBackground()
            android.graphics.drawable.ColorDrawable r2 = (android.graphics.drawable.ColorDrawable) r2
            com.google.android.material.shape.MaterialShapeDrawable r3 = new com.google.android.material.shape.MaterialShapeDrawable
            r3.<init>()
            int r2 = r2.getColor()
            android.content.res.ColorStateList r2 = android.content.res.ColorStateList.valueOf(r2)
            r3.setFillColor(r2)
            r3.initializeElevationOverlay(r11)
            r15.setBackground(r3)
        L_0x00a0:
            r2 = 4
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x00ae
            boolean r2 = r1.getBoolean(r2, r10)
            r15.setExpanded(r2, r10, r10)
        L_0x00ae:
            r2 = 3
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x0113
            int r2 = r1.getDimensionPixelSize(r2, r10)
            float r2 = (float) r2
            android.content.res.Resources r3 = r15.getResources()
            r4 = 2131427331(0x7f0b0003, float:1.8476275E38)
            int r3 = r3.getInteger(r4)
            android.animation.StateListAnimator r4 = new android.animation.StateListAnimator
            r4.<init>()
            r5 = 2130969965(0x7f04056d, float:1.7548627E38)
            r6 = -2130969966(0xffffffff80fbfa92, float:-2.3140597E-38)
            r7 = 16842910(0x101009e, float:2.3694E-38)
            int[] r5 = new int[]{r7, r5, r6}
            r6 = 0
            float[] r8 = new float[r12]
            r8[r10] = r6
            java.lang.String r11 = "elevation"
            android.animation.ObjectAnimator r8 = android.animation.ObjectAnimator.ofFloat(r15, r11, r8)
            long r13 = (long) r3
            android.animation.ObjectAnimator r3 = r8.setDuration(r13)
            r4.addState(r5, r3)
            int[] r3 = new int[]{r7}
            float[] r5 = new float[r12]
            r5[r10] = r2
            android.animation.ObjectAnimator r2 = android.animation.ObjectAnimator.ofFloat(r15, r11, r5)
            android.animation.ObjectAnimator r2 = r2.setDuration(r13)
            r4.addState(r3, r2)
            int[] r2 = new int[r10]
            float[] r3 = new float[r12]
            r3[r10] = r6
            android.animation.ObjectAnimator r3 = android.animation.ObjectAnimator.ofFloat(r15, r11, r3)
            r5 = 0
            android.animation.ObjectAnimator r3 = r3.setDuration(r5)
            r4.addState(r2, r3)
            r15.setStateListAnimator(r4)
        L_0x0113:
            r2 = 2
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x0121
            boolean r2 = r1.getBoolean(r2, r10)
            r15.setKeyboardNavigationCluster(r2)
        L_0x0121:
            boolean r2 = r1.hasValue(r12)
            if (r2 == 0) goto L_0x012e
            boolean r2 = r1.getBoolean(r12, r10)
            r15.setTouchscreenBlocksFocus(r2)
        L_0x012e:
            r2 = 5
            boolean r2 = r1.getBoolean(r2, r10)
            r0.liftOnScroll = r2
            r2 = 6
            int r2 = r1.getResourceId(r2, r9)
            r0.liftOnScrollTargetViewId = r2
            r2 = 7
            android.graphics.drawable.Drawable r2 = r1.getDrawable(r2)
            android.graphics.drawable.Drawable r3 = r0.statusBarForeground
            if (r3 == r2) goto L_0x0193
            r4 = 0
            if (r3 == 0) goto L_0x014b
            r3.setCallback(r4)
        L_0x014b:
            if (r2 == 0) goto L_0x0151
            android.graphics.drawable.Drawable r4 = r2.mutate()
        L_0x0151:
            r0.statusBarForeground = r4
            if (r4 == 0) goto L_0x0180
            boolean r2 = r4.isStateful()
            if (r2 == 0) goto L_0x0164
            android.graphics.drawable.Drawable r2 = r0.statusBarForeground
            int[] r3 = r15.getDrawableState()
            r2.setState(r3)
        L_0x0164:
            android.graphics.drawable.Drawable r2 = r0.statusBarForeground
            int r3 = r15.getLayoutDirection()
            r2.setLayoutDirection(r3)
            android.graphics.drawable.Drawable r2 = r0.statusBarForeground
            int r3 = r15.getVisibility()
            if (r3 != 0) goto L_0x0177
            r3 = r12
            goto L_0x0178
        L_0x0177:
            r3 = r10
        L_0x0178:
            r2.setVisible(r3, r10)
            android.graphics.drawable.Drawable r2 = r0.statusBarForeground
            r2.setCallback(r15)
        L_0x0180:
            android.graphics.drawable.Drawable r2 = r0.statusBarForeground
            if (r2 == 0) goto L_0x018b
            int r2 = r15.getTopInset()
            if (r2 <= 0) goto L_0x018b
            r10 = r12
        L_0x018b:
            r2 = r10 ^ 1
            r15.setWillNotDraw(r2)
            r15.postInvalidateOnAnimation()
        L_0x0193:
            r1.recycle()
            com.google.android.material.appbar.AppBarLayout$1 r1 = new com.google.android.material.appbar.AppBarLayout$1
            r1.<init>()
            androidx.core.view.ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(r15, r1)
            return
        L_0x019f:
            r1.recycle()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public class ScrollingViewBehavior extends ViewOffsetBehavior {
        public final int overlayTop;
        public final Rect tempRect1 = new Rect();
        public final Rect tempRect2 = new Rect();
        public int verticalLayoutGap = 0;

        public ScrollingViewBehavior() {
        }

        public static AppBarLayout findFirstDependency(List list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = (View) list.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        public final int getOverlapPixelsForOffset(View view) {
            int i;
            int i2;
            if (this.overlayTop == 0) {
                return 0;
            }
            float f = 0.0f;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).mBehavior;
                if (behavior instanceof BaseBehavior) {
                    i = ((BaseBehavior) behavior).getTopBottomOffsetForScrollingSibling();
                } else {
                    i = 0;
                }
                if ((downNestedPreScrollRange == 0 || totalScrollRange + i > downNestedPreScrollRange) && (i2 = totalScrollRange - downNestedPreScrollRange) != 0) {
                    f = (((float) i) / ((float) i2)) + 1.0f;
                }
            }
            int i3 = this.overlayTop;
            return MathUtils.clamp((int) (f * ((float) i3)), 0, i3);
        }

        public final void layoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            AppBarLayout findFirstDependency = findFirstDependency(coordinatorLayout.getDependencies(view));
            if (findFirstDependency != null) {
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                int paddingLeft = coordinatorLayout.getPaddingLeft() + layoutParams.leftMargin;
                int bottom = findFirstDependency.getBottom() + layoutParams.topMargin;
                int width = (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - layoutParams.rightMargin;
                int height = coordinatorLayout.getHeight();
                Rect rect = this.tempRect1;
                rect.set(paddingLeft, bottom, width, ((findFirstDependency.getBottom() + height) - coordinatorLayout.getPaddingBottom()) - layoutParams.bottomMargin);
                WindowInsetsCompat windowInsetsCompat = coordinatorLayout.mLastInsets;
                if (windowInsetsCompat != null) {
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    if (coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
                        rect.left = windowInsetsCompat.getSystemWindowInsetLeft() + rect.left;
                        rect.right -= windowInsetsCompat.getSystemWindowInsetRight();
                    }
                }
                int i2 = layoutParams.gravity;
                if (i2 == 0) {
                    i2 = 8388659;
                }
                int i3 = i2;
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                Rect rect2 = this.tempRect2;
                Gravity.apply(i3, measuredWidth, measuredHeight, rect, rect2, i);
                int overlapPixelsForOffset = getOverlapPixelsForOffset(findFirstDependency);
                view.layout(rect2.left, rect2.top - overlapPixelsForOffset, rect2.right, rect2.bottom - overlapPixelsForOffset);
                this.verticalLayoutGap = rect2.top - findFirstDependency.getBottom();
                return;
            }
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            View view2 = view;
            coordinatorLayout.onLayoutChild(view, i);
            this.verticalLayoutGap = 0;
        }

        public final boolean layoutDependsOn(View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        public final boolean onDependentViewChanged(View view, View view2) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) view2.getLayoutParams()).mBehavior;
            if (behavior instanceof BaseBehavior) {
                int bottom = (((view2.getBottom() - view.getTop()) + ((BaseBehavior) behavior).offsetDelta) + this.verticalLayoutGap) - getOverlapPixelsForOffset(view2);
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                view.offsetTopAndBottom(bottom);
            }
            if (!(view2 instanceof AppBarLayout)) {
                return false;
            }
            AppBarLayout appBarLayout = (AppBarLayout) view2;
            if (!appBarLayout.liftOnScroll) {
                return false;
            }
            appBarLayout.setLiftedState(appBarLayout.shouldLift(view));
            return false;
        }

        public final void onDependentViewRemoved(CoordinatorLayout coordinatorLayout, View view) {
            if (view instanceof AppBarLayout) {
                ViewCompat.removeAccessibilityAction(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId());
                ViewCompat.removeActionWithId(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId());
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(coordinatorLayout, 0);
                ViewCompat.setAccessibilityDelegate(coordinatorLayout, (AccessibilityDelegateCompat) null);
            }
        }

        public final boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            AppBarLayout findFirstDependency;
            int i4;
            WindowInsetsCompat windowInsetsCompat;
            int i5 = view.getLayoutParams().height;
            if ((i5 != -1 && i5 != -2) || (findFirstDependency = findFirstDependency(coordinatorLayout.getDependencies(view))) == null) {
                return false;
            }
            int size = View.MeasureSpec.getSize(i3);
            if (size > 0) {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                if (findFirstDependency.getFitsSystemWindows() && (windowInsetsCompat = coordinatorLayout.mLastInsets) != null) {
                    size += windowInsetsCompat.getSystemWindowInsetBottom() + windowInsetsCompat.getSystemWindowInsetTop();
                }
            } else {
                size = coordinatorLayout.getHeight();
            }
            int totalScrollRange = (findFirstDependency.getTotalScrollRange() + size) - findFirstDependency.getMeasuredHeight();
            if (i5 == -1) {
                i4 = 1073741824;
            } else {
                i4 = Integer.MIN_VALUE;
            }
            coordinatorLayout.onMeasureChild(view, i, i2, View.MeasureSpec.makeMeasureSpec(totalScrollRange, i4));
            return true;
        }

        public final boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout findFirstDependency = findFirstDependency(coordinatorLayout.getDependencies(view));
            if (findFirstDependency != null) {
                rect.offset(view.getLeft(), view.getTop());
                int width = coordinatorLayout.getWidth();
                int height = coordinatorLayout.getHeight();
                Rect rect2 = this.tempRect1;
                rect2.set(0, 0, width, height);
                if (!rect2.contains(rect)) {
                    findFirstDependency.setExpanded(false, !z, true);
                    return true;
                }
            }
            return false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(0);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ScrollingViewBehavior_Layout);
            this.overlayTop = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [com.google.android.material.appbar.AppBarLayout$LayoutParams, android.widget.LinearLayout$LayoutParams] */
    /* renamed from: generateDefaultLayoutParams  reason: collision with other method in class */
    public final LinearLayout.LayoutParams m910generateDefaultLayoutParams() {
        ? layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.scrollFlags = 1;
        return layoutParams;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.material.appbar.AppBarLayout$LayoutParams, android.widget.LinearLayout$LayoutParams] */
    public final LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        CompressChildScrollEffect compressChildScrollEffect;
        Context context = getContext();
        ? layoutParams = new LinearLayout.LayoutParams(context, attributeSet);
        layoutParams.scrollFlags = 1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AppBarLayout_Layout);
        layoutParams.scrollFlags = obtainStyledAttributes.getInt(1, 0);
        if (obtainStyledAttributes.getInt(0, 0) != 1) {
            compressChildScrollEffect = null;
        } else {
            compressChildScrollEffect = new CompressChildScrollEffect();
        }
        layoutParams.scrollEffect = compressChildScrollEffect;
        if (obtainStyledAttributes.hasValue(2)) {
            layoutParams.scrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(2, 0));
        }
        obtainStyledAttributes.recycle();
        return layoutParams;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.material.appbar.AppBarLayout$LayoutParams, android.widget.LinearLayout$LayoutParams] */
    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.material.appbar.AppBarLayout$LayoutParams, android.widget.LinearLayout$LayoutParams] */
    /* JADX WARNING: type inference failed for: r0v4, types: [com.google.android.material.appbar.AppBarLayout$LayoutParams, android.widget.LinearLayout$LayoutParams] */
    public static LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            ? layoutParams2 = new LinearLayout.LayoutParams((LinearLayout.LayoutParams) layoutParams);
            layoutParams2.scrollFlags = 1;
            return layoutParams2;
        } else if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ? layoutParams3 = new LinearLayout.LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            layoutParams3.scrollFlags = 1;
            return layoutParams3;
        } else {
            ? layoutParams4 = new LinearLayout.LayoutParams(layoutParams);
            layoutParams4.scrollFlags = 1;
            return layoutParams4;
        }
    }
}
