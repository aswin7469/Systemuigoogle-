package com.google.android.material.bottomsheet;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.frameworks.stats.VendorAtomValue$1$$ExternalSyntheticOutline0;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.input.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class BottomSheetBehavior extends CoordinatorLayout.Behavior {
    public int activePointerId;
    public final ColorStateList backgroundTint;
    public final ArrayList callbacks = new ArrayList();
    public int childHeight;
    public int collapsedOffset;
    public final AnonymousClass4 dragCallback = new ViewDragHelper.Callback() {
        public final int clampViewPositionHorizontal(View view, int i) {
            return view.getLeft();
        }

        public final int clampViewPositionVertical(View view, int i) {
            int i2;
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int expandedOffset = bottomSheetBehavior.getExpandedOffset();
            if (bottomSheetBehavior.hideable) {
                i2 = bottomSheetBehavior.parentHeight;
            } else {
                i2 = bottomSheetBehavior.collapsedOffset;
            }
            return MathUtils.clamp(i, expandedOffset, i2);
        }

        public final int getViewVerticalDragRange() {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            if (bottomSheetBehavior.hideable) {
                return bottomSheetBehavior.parentHeight;
            }
            return bottomSheetBehavior.collapsedOffset;
        }

        public final void onViewDragStateChanged(int i) {
            if (i == 1) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                if (bottomSheetBehavior.draggable) {
                    bottomSheetBehavior.setStateInternal(1);
                }
            }
        }

        public final void onViewPositionChanged(View view, int i, int i2) {
            BottomSheetBehavior.this.dispatchOnSlide(i2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0068, code lost:
            if (java.lang.Math.abs(r5.getTop() - r4.getExpandedOffset()) < java.lang.Math.abs(r5.getTop() - r4.halfExpandedOffset)) goto L_0x000d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0096, code lost:
            if (java.lang.Math.abs(r6 - r4.halfExpandedOffset) < java.lang.Math.abs(r6 - r4.collapsedOffset)) goto L_0x00d3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b0, code lost:
            if (java.lang.Math.abs(r6 - r4.fitToContentsOffset) < java.lang.Math.abs(r6 - r4.collapsedOffset)) goto L_0x000d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c0, code lost:
            if (r6 < java.lang.Math.abs(r6 - r4.collapsedOffset)) goto L_0x000d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d1, code lost:
            if (java.lang.Math.abs(r6 - r7) < java.lang.Math.abs(r6 - r4.collapsedOffset)) goto L_0x00d3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0019, code lost:
            if (r6 > r4.halfExpandedOffset) goto L_0x00d3;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onViewReleased(android.view.View r5, float r6, float r7) {
            /*
                r4 = this;
                r0 = 0
                int r1 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                r2 = 6
                r3 = 3
                com.google.android.material.bottomsheet.BottomSheetBehavior r4 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                if (r1 >= 0) goto L_0x001d
                boolean r6 = r4.fitToContents
                if (r6 == 0) goto L_0x0010
            L_0x000d:
                r2 = r3
                goto L_0x00d3
            L_0x0010:
                int r6 = r5.getTop()
                java.lang.System.currentTimeMillis()
                int r7 = r4.halfExpandedOffset
                if (r6 <= r7) goto L_0x000d
                goto L_0x00d3
            L_0x001d:
                boolean r1 = r4.hideable
                if (r1 == 0) goto L_0x006b
                boolean r1 = r4.shouldHide(r5, r7)
                if (r1 == 0) goto L_0x006b
                float r6 = java.lang.Math.abs(r6)
                float r0 = java.lang.Math.abs(r7)
                int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
                if (r6 >= 0) goto L_0x0039
                r6 = 1140457472(0x43fa0000, float:500.0)
                int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
                if (r6 > 0) goto L_0x0048
            L_0x0039:
                int r6 = r5.getTop()
                int r7 = r4.parentHeight
                int r0 = r4.getExpandedOffset()
                int r0 = r0 + r7
                int r0 = r0 / 2
                if (r6 <= r0) goto L_0x004b
            L_0x0048:
                r2 = 5
                goto L_0x00d3
            L_0x004b:
                boolean r6 = r4.fitToContents
                if (r6 == 0) goto L_0x0050
                goto L_0x000d
            L_0x0050:
                int r6 = r5.getTop()
                int r7 = r4.getExpandedOffset()
                int r6 = r6 - r7
                int r6 = java.lang.Math.abs(r6)
                int r7 = r5.getTop()
                int r0 = r4.halfExpandedOffset
                int r7 = r7 - r0
                int r7 = java.lang.Math.abs(r7)
                if (r6 >= r7) goto L_0x00d3
                goto L_0x000d
            L_0x006b:
                int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                r1 = 4
                if (r0 == 0) goto L_0x0099
                float r6 = java.lang.Math.abs(r6)
                float r7 = java.lang.Math.abs(r7)
                int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
                if (r6 <= 0) goto L_0x007d
                goto L_0x0099
            L_0x007d:
                boolean r6 = r4.fitToContents
                if (r6 == 0) goto L_0x0083
            L_0x0081:
                r2 = r1
                goto L_0x00d3
            L_0x0083:
                int r6 = r5.getTop()
                int r7 = r4.halfExpandedOffset
                int r7 = r6 - r7
                int r7 = java.lang.Math.abs(r7)
                int r0 = r4.collapsedOffset
                int r6 = r6 - r0
                int r6 = java.lang.Math.abs(r6)
                if (r7 >= r6) goto L_0x0081
                goto L_0x00d3
            L_0x0099:
                int r6 = r5.getTop()
                boolean r7 = r4.fitToContents
                if (r7 == 0) goto L_0x00b4
                int r7 = r4.fitToContentsOffset
                int r7 = r6 - r7
                int r7 = java.lang.Math.abs(r7)
                int r0 = r4.collapsedOffset
                int r6 = r6 - r0
                int r6 = java.lang.Math.abs(r6)
                if (r7 >= r6) goto L_0x0081
                goto L_0x000d
            L_0x00b4:
                int r7 = r4.halfExpandedOffset
                if (r6 >= r7) goto L_0x00c4
                int r7 = r4.collapsedOffset
                int r7 = r6 - r7
                int r7 = java.lang.Math.abs(r7)
                if (r6 >= r7) goto L_0x00d3
                goto L_0x000d
            L_0x00c4:
                int r7 = r6 - r7
                int r7 = java.lang.Math.abs(r7)
                int r0 = r4.collapsedOffset
                int r6 = r6 - r0
                int r6 = java.lang.Math.abs(r6)
                if (r7 >= r6) goto L_0x0081
            L_0x00d3:
                r4.getClass()
                r6 = 1
                r4.startSettling(r2, r5, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass4.onViewReleased(android.view.View, float, float):void");
        }

        public final boolean tryCaptureView(View view, int i) {
            View view2;
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i2 = bottomSheetBehavior.state;
            if (i2 == 1 || bottomSheetBehavior.touchingScrollingChild) {
                return false;
            }
            if (i2 == 3 && bottomSheetBehavior.activePointerId == i) {
                WeakReference weakReference = bottomSheetBehavior.nestedScrollingChildRef;
                if (weakReference != null) {
                    view2 = (View) weakReference.get();
                } else {
                    view2 = null;
                }
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            System.currentTimeMillis();
            WeakReference weakReference2 = bottomSheetBehavior.viewRef;
            if (weakReference2 == null || weakReference2.get() != view) {
                return false;
            }
            return true;
        }
    };
    public final boolean draggable = true;
    public final float elevation = -1.0f;
    public int expandHalfwayActionId = -1;
    public final int expandedOffset;
    public boolean fitToContents = true;
    public int fitToContentsOffset;
    public int gestureInsetBottom;
    public final boolean gestureInsetBottomIgnored;
    public int halfExpandedOffset;
    public final float halfExpandedRatio = 0.5f;
    public final float hideFriction = 0.1f;
    public boolean hideable;
    public boolean ignoreEvents;
    public Map importantForAccessibilityMap;
    public int initialY;
    public int insetBottom;
    public int insetTop;
    public ValueAnimator interpolatorAnimator;
    public boolean isShapeExpanded;
    public int lastNestedScrollDy;
    public final boolean marginLeftSystemWindowInsets;
    public final boolean marginRightSystemWindowInsets;
    public final boolean marginTopSystemWindowInsets;
    public final MaterialShapeDrawable materialShapeDrawable;
    public final int maxHeight = -1;
    public final int maxWidth = -1;
    public final float maximumVelocity;
    public boolean nestedScrolled;
    public WeakReference nestedScrollingChildRef;
    public final boolean paddingBottomSystemWindowInsets;
    public final boolean paddingLeftSystemWindowInsets;
    public final boolean paddingRightSystemWindowInsets;
    public final boolean paddingTopSystemWindowInsets;
    public int parentHeight;
    public int parentWidth;
    public int peekHeight;
    public boolean peekHeightAuto;
    public final int peekHeightGestureInsetBuffer;
    public int peekHeightMin;
    public final int saveFlags = 0;
    public final ShapeAppearanceModel shapeAppearanceModelDefault;
    public boolean skipCollapsed;
    public int state = 4;
    public final StateSettlingTracker stateSettlingTracker = new StateSettlingTracker();
    public boolean touchingScrollingChild;
    public VelocityTracker velocityTracker;
    public ViewDragHelper viewDragHelper;
    public WeakReference viewRef;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class StateSettlingTracker {
        public final AnonymousClass1 continueSettlingRunnable = new Runnable() {
            public final void run() {
                StateSettlingTracker stateSettlingTracker = StateSettlingTracker.this;
                stateSettlingTracker.isContinueSettlingRunnablePosted = false;
                ViewDragHelper viewDragHelper = BottomSheetBehavior.this.viewDragHelper;
                if (viewDragHelper == null || !viewDragHelper.continueSettling()) {
                    StateSettlingTracker stateSettlingTracker2 = StateSettlingTracker.this;
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    if (bottomSheetBehavior.state == 2) {
                        bottomSheetBehavior.setStateInternal(stateSettlingTracker2.targetState);
                        return;
                    }
                    return;
                }
                StateSettlingTracker stateSettlingTracker3 = StateSettlingTracker.this;
                stateSettlingTracker3.continueSettlingToState(stateSettlingTracker3.targetState);
            }
        };
        public boolean isContinueSettlingRunnablePosted;
        public int targetState;

        public StateSettlingTracker() {
        }

        public final void continueSettlingToState(int i) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            WeakReference weakReference = bottomSheetBehavior.viewRef;
            if (weakReference != null && weakReference.get() != null) {
                this.targetState = i;
                if (!this.isContinueSettlingRunnablePosted) {
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api16Impl.postOnAnimation((View) bottomSheetBehavior.viewRef.get(), this.continueSettlingRunnable);
                    this.isContinueSettlingRunnablePosted = true;
                }
            }
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i;
        this.peekHeightGestureInsetBuffer = context.getResources().getDimensionPixelSize(2131166844);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomSheetBehavior_Layout);
        int i2 = 3;
        if (obtainStyledAttributes.hasValue(3)) {
            this.backgroundTint = MaterialResources.getColorStateList(context, obtainStyledAttributes, 3);
        }
        if (obtainStyledAttributes.hasValue(20)) {
            this.shapeAppearanceModelDefault = ShapeAppearanceModel.builder(context, attributeSet, 2130968719, 2132018523).build();
        }
        ShapeAppearanceModel shapeAppearanceModel = this.shapeAppearanceModelDefault;
        if (shapeAppearanceModel != null) {
            MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(shapeAppearanceModel);
            this.materialShapeDrawable = materialShapeDrawable2;
            materialShapeDrawable2.initializeElevationOverlay(context);
            ColorStateList colorStateList = this.backgroundTint;
            if (colorStateList != null) {
                this.materialShapeDrawable.setFillColor(colorStateList);
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(16842801, typedValue, true);
                this.materialShapeDrawable.setTint(typedValue.data);
            }
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.interpolatorAnimator = ofFloat;
        ofFloat.setDuration(500);
        this.interpolatorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                MaterialShapeDrawable materialShapeDrawable = BottomSheetBehavior.this.materialShapeDrawable;
                if (materialShapeDrawable != null) {
                    materialShapeDrawable.setInterpolation(floatValue);
                }
            }
        });
        this.elevation = obtainStyledAttributes.getDimension(2, -1.0f);
        if (obtainStyledAttributes.hasValue(0)) {
            this.maxWidth = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        }
        if (obtainStyledAttributes.hasValue(1)) {
            this.maxHeight = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        }
        TypedValue peekValue = obtainStyledAttributes.peekValue(9);
        if (peekValue == null || (i = peekValue.data) != -1) {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(9, -1));
        } else {
            setPeekHeight(i);
        }
        setHideable(obtainStyledAttributes.getBoolean(8, false));
        this.gestureInsetBottomIgnored = obtainStyledAttributes.getBoolean(12, false);
        boolean z = obtainStyledAttributes.getBoolean(6, true);
        if (this.fitToContents != z) {
            this.fitToContents = z;
            if (this.viewRef != null) {
                calculateCollapsedOffset();
            }
            setStateInternal((!this.fitToContents || this.state != 6) ? this.state : i2);
            updateAccessibilityActions$1();
        }
        this.skipCollapsed = obtainStyledAttributes.getBoolean(11, false);
        this.draggable = obtainStyledAttributes.getBoolean(4, true);
        this.saveFlags = obtainStyledAttributes.getInt(10, 0);
        float f = obtainStyledAttributes.getFloat(7, 0.5f);
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.halfExpandedRatio = f;
        if (this.viewRef != null) {
            this.halfExpandedOffset = (int) ((1.0f - f) * ((float) this.parentHeight));
        }
        TypedValue peekValue2 = obtainStyledAttributes.peekValue(5);
        if (peekValue2 == null || peekValue2.type != 16) {
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(5, 0);
            if (dimensionPixelOffset >= 0) {
                this.expandedOffset = dimensionPixelOffset;
            } else {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
        } else {
            int i3 = peekValue2.data;
            if (i3 >= 0) {
                this.expandedOffset = i3;
            } else {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
        }
        this.paddingBottomSystemWindowInsets = obtainStyledAttributes.getBoolean(16, false);
        this.paddingLeftSystemWindowInsets = obtainStyledAttributes.getBoolean(17, false);
        this.paddingRightSystemWindowInsets = obtainStyledAttributes.getBoolean(18, false);
        this.paddingTopSystemWindowInsets = obtainStyledAttributes.getBoolean(19, true);
        this.marginLeftSystemWindowInsets = obtainStyledAttributes.getBoolean(13, false);
        this.marginRightSystemWindowInsets = obtainStyledAttributes.getBoolean(14, false);
        this.marginTopSystemWindowInsets = obtainStyledAttributes.getBoolean(15, false);
        obtainStyledAttributes.recycle();
        this.maximumVelocity = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public static BottomSheetBehavior from(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).mBehavior;
            if (behavior instanceof BottomSheetBehavior) {
                return (BottomSheetBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    public static int getChildMeasureSpec(int i, int i2, int i3, int i4) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, i2, i4);
        if (i3 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), 1073741824);
        }
        if (size != 0) {
            i3 = Math.min(size, i3);
        }
        return View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
    }

    public final void calculateCollapsedOffset() {
        int calculatePeekHeight = calculatePeekHeight();
        if (this.fitToContents) {
            this.collapsedOffset = Math.max(this.parentHeight - calculatePeekHeight, this.fitToContentsOffset);
        } else {
            this.collapsedOffset = this.parentHeight - calculatePeekHeight;
        }
    }

    public final int calculatePeekHeight() {
        int i;
        int i2;
        int i3;
        if (this.peekHeightAuto) {
            i = Math.min(Math.max(this.peekHeightMin, this.parentHeight - ((this.parentWidth * 9) / 16)), this.childHeight);
            i2 = this.insetBottom;
        } else if (!this.gestureInsetBottomIgnored && !this.paddingBottomSystemWindowInsets && (i3 = this.gestureInsetBottom) > 0) {
            return Math.max(this.peekHeight, i3 + this.peekHeightGestureInsetBuffer);
        } else {
            i = this.peekHeight;
            i2 = this.insetBottom;
        }
        return i + i2;
    }

    public void disableShapeAnimations() {
        this.interpolatorAnimator = null;
    }

    public final void dispatchOnSlide(int i) {
        View view = (View) this.viewRef.get();
        if (view != null) {
            ArrayList arrayList = this.callbacks;
            if (!arrayList.isEmpty()) {
                int i2 = this.collapsedOffset;
                if (i <= i2 && i2 != getExpandedOffset()) {
                    getExpandedOffset();
                }
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    ((BottomSheetCallback) arrayList.get(i3)).onSlide(view);
                }
            }
        }
    }

    public View findScrollingChild(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (ViewCompat.Api21Impl.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i));
                if (findScrollingChild != null) {
                    return findScrollingChild;
                }
            }
        }
        return null;
    }

    public final int getExpandedOffset() {
        int i;
        if (this.fitToContents) {
            return this.fitToContentsOffset;
        }
        int i2 = this.expandedOffset;
        if (this.paddingTopSystemWindowInsets) {
            i = 0;
        } else {
            i = this.insetTop;
        }
        return Math.max(i2, i);
    }

    public int getPeekHeightMin() {
        return this.peekHeightMin;
    }

    public final int getTopOffsetForState(int i) {
        if (i == 3) {
            return getExpandedOffset();
        }
        if (i == 4) {
            return this.collapsedOffset;
        }
        if (i == 5) {
            return this.parentHeight;
        }
        if (i == 6) {
            return this.halfExpandedOffset;
        }
        throw new IllegalArgumentException(VendorAtomValue$1$$ExternalSyntheticOutline0.m("Invalid state to get top offset: ", i));
    }

    public final void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        this.viewRef = null;
        this.viewDragHelper = null;
    }

    public final void onDetachedFromLayoutParams() {
        this.viewRef = null;
        this.viewDragHelper = null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onInterceptTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout r10, android.view.View r11, android.view.MotionEvent r12) {
        /*
            r9 = this;
            boolean r0 = r11.isShown()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x00d8
            boolean r0 = r9.draggable
            if (r0 != 0) goto L_0x000e
            goto L_0x00d8
        L_0x000e:
            int r0 = r12.getActionMasked()
            r3 = 0
            r4 = -1
            if (r0 != 0) goto L_0x0021
            r9.activePointerId = r4
            android.view.VelocityTracker r5 = r9.velocityTracker
            if (r5 == 0) goto L_0x0021
            r5.recycle()
            r9.velocityTracker = r3
        L_0x0021:
            android.view.VelocityTracker r5 = r9.velocityTracker
            if (r5 != 0) goto L_0x002b
            android.view.VelocityTracker r5 = android.view.VelocityTracker.obtain()
            r9.velocityTracker = r5
        L_0x002b:
            android.view.VelocityTracker r5 = r9.velocityTracker
            r5.addMovement(r12)
            r5 = 2
            if (r0 == 0) goto L_0x0044
            if (r0 == r2) goto L_0x0039
            r11 = 3
            if (r0 == r11) goto L_0x0039
            goto L_0x0087
        L_0x0039:
            r9.touchingScrollingChild = r1
            r9.activePointerId = r4
            boolean r11 = r9.ignoreEvents
            if (r11 == 0) goto L_0x0087
            r9.ignoreEvents = r1
            return r1
        L_0x0044:
            float r6 = r12.getX()
            int r6 = (int) r6
            float r7 = r12.getY()
            int r7 = (int) r7
            r9.initialY = r7
            int r7 = r9.state
            if (r7 == r5) goto L_0x0076
            java.lang.ref.WeakReference r7 = r9.nestedScrollingChildRef
            if (r7 == 0) goto L_0x005f
            java.lang.Object r7 = r7.get()
            android.view.View r7 = (android.view.View) r7
            goto L_0x0060
        L_0x005f:
            r7 = r3
        L_0x0060:
            if (r7 == 0) goto L_0x0076
            int r8 = r9.initialY
            boolean r7 = r10.isPointInChildBounds(r7, r6, r8)
            if (r7 == 0) goto L_0x0076
            int r7 = r12.getActionIndex()
            int r7 = r12.getPointerId(r7)
            r9.activePointerId = r7
            r9.touchingScrollingChild = r2
        L_0x0076:
            int r7 = r9.activePointerId
            if (r7 != r4) goto L_0x0084
            int r4 = r9.initialY
            boolean r11 = r10.isPointInChildBounds(r11, r6, r4)
            if (r11 != 0) goto L_0x0084
            r11 = r2
            goto L_0x0085
        L_0x0084:
            r11 = r1
        L_0x0085:
            r9.ignoreEvents = r11
        L_0x0087:
            boolean r11 = r9.ignoreEvents
            if (r11 != 0) goto L_0x0096
            androidx.customview.widget.ViewDragHelper r11 = r9.viewDragHelper
            if (r11 == 0) goto L_0x0096
            boolean r11 = r11.shouldInterceptTouchEvent(r12)
            if (r11 == 0) goto L_0x0096
            return r2
        L_0x0096:
            java.lang.ref.WeakReference r11 = r9.nestedScrollingChildRef
            if (r11 == 0) goto L_0x00a1
            java.lang.Object r11 = r11.get()
            r3 = r11
            android.view.View r3 = (android.view.View) r3
        L_0x00a1:
            if (r0 != r5) goto L_0x00d7
            if (r3 == 0) goto L_0x00d7
            boolean r11 = r9.ignoreEvents
            if (r11 != 0) goto L_0x00d7
            int r11 = r9.state
            if (r11 == r2) goto L_0x00d7
            float r11 = r12.getX()
            int r11 = (int) r11
            float r0 = r12.getY()
            int r0 = (int) r0
            boolean r10 = r10.isPointInChildBounds(r3, r11, r0)
            if (r10 != 0) goto L_0x00d7
            androidx.customview.widget.ViewDragHelper r10 = r9.viewDragHelper
            if (r10 == 0) goto L_0x00d7
            int r10 = r9.initialY
            float r10 = (float) r10
            float r11 = r12.getY()
            float r10 = r10 - r11
            float r10 = java.lang.Math.abs(r10)
            androidx.customview.widget.ViewDragHelper r9 = r9.viewDragHelper
            int r9 = r9.mTouchSlop
            float r9 = (float) r9
            int r9 = (r10 > r9 ? 1 : (r10 == r9 ? 0 : -1))
            if (r9 <= 0) goto L_0x00d7
            r1 = r2
        L_0x00d7:
            return r1
        L_0x00d8:
            r9.ignoreEvents = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.onInterceptTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        final boolean z;
        boolean z2;
        float f;
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (ViewCompat.Api16Impl.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.Api16Impl.getFitsSystemWindows(view)) {
            view.setFitsSystemWindows(true);
        }
        int i2 = 0;
        if (this.viewRef == null) {
            this.peekHeightMin = coordinatorLayout.getResources().getDimensionPixelSize(2131165727);
            if (this.gestureInsetBottomIgnored || this.peekHeightAuto) {
                z = false;
            } else {
                z = true;
            }
            if (this.paddingBottomSystemWindowInsets || this.paddingLeftSystemWindowInsets || this.paddingRightSystemWindowInsets || this.marginLeftSystemWindowInsets || this.marginRightSystemWindowInsets || this.marginTopSystemWindowInsets || z) {
                ViewUtils.doOnApplyWindowInsets(view, new ViewUtils.OnApplyWindowInsetsListener() {
                    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007a, code lost:
                        if (r7 != false) goto L_0x007c;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final androidx.core.view.WindowInsetsCompat onApplyWindowInsets(android.view.View r11, androidx.core.view.WindowInsetsCompat r12, com.google.android.material.internal.ViewUtils.RelativePadding r13) {
                        /*
                            r10 = this;
                            androidx.core.view.WindowInsetsCompat$Impl r0 = r12.mImpl
                            r1 = 7
                            androidx.core.graphics.Insets r1 = r0.getInsets(r1)
                            r2 = 32
                            androidx.core.graphics.Insets r0 = r0.getInsets(r2)
                            int r2 = r1.top
                            com.google.android.material.bottomsheet.BottomSheetBehavior r3 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                            r3.insetTop = r2
                            boolean r2 = com.google.android.material.internal.ViewUtils.isLayoutRtl(r11)
                            int r4 = r11.getPaddingBottom()
                            int r5 = r11.getPaddingLeft()
                            int r6 = r11.getPaddingRight()
                            boolean r7 = r3.paddingBottomSystemWindowInsets
                            if (r7 == 0) goto L_0x0030
                            int r4 = r12.getSystemWindowInsetBottom()
                            r3.insetBottom = r4
                            int r7 = r13.bottom
                            int r4 = r4 + r7
                        L_0x0030:
                            boolean r7 = r3.paddingLeftSystemWindowInsets
                            int r8 = r1.left
                            if (r7 == 0) goto L_0x003e
                            if (r2 == 0) goto L_0x003b
                            int r5 = r13.end
                            goto L_0x003d
                        L_0x003b:
                            int r5 = r13.start
                        L_0x003d:
                            int r5 = r5 + r8
                        L_0x003e:
                            boolean r7 = r3.paddingRightSystemWindowInsets
                            int r9 = r1.right
                            if (r7 == 0) goto L_0x004d
                            if (r2 == 0) goto L_0x0049
                            int r13 = r13.start
                            goto L_0x004b
                        L_0x0049:
                            int r13 = r13.end
                        L_0x004b:
                            int r6 = r13 + r9
                        L_0x004d:
                            android.view.ViewGroup$LayoutParams r13 = r11.getLayoutParams()
                            android.view.ViewGroup$MarginLayoutParams r13 = (android.view.ViewGroup.MarginLayoutParams) r13
                            boolean r2 = r3.marginLeftSystemWindowInsets
                            r7 = 1
                            if (r2 == 0) goto L_0x0060
                            int r2 = r13.leftMargin
                            if (r2 == r8) goto L_0x0060
                            r13.leftMargin = r8
                            r2 = r7
                            goto L_0x0061
                        L_0x0060:
                            r2 = 0
                        L_0x0061:
                            boolean r8 = r3.marginRightSystemWindowInsets
                            if (r8 == 0) goto L_0x006c
                            int r8 = r13.rightMargin
                            if (r8 == r9) goto L_0x006c
                            r13.rightMargin = r9
                            goto L_0x006d
                        L_0x006c:
                            r7 = r2
                        L_0x006d:
                            boolean r2 = r3.marginTopSystemWindowInsets
                            if (r2 == 0) goto L_0x007a
                            int r2 = r13.topMargin
                            int r1 = r1.top
                            if (r2 == r1) goto L_0x007a
                            r13.topMargin = r1
                            goto L_0x007c
                        L_0x007a:
                            if (r7 == 0) goto L_0x007f
                        L_0x007c:
                            r11.setLayoutParams(r13)
                        L_0x007f:
                            int r13 = r11.getPaddingTop()
                            r11.setPadding(r5, r13, r6, r4)
                            boolean r10 = r0
                            if (r10 == 0) goto L_0x008e
                            int r11 = r0.bottom
                            r3.gestureInsetBottom = r11
                        L_0x008e:
                            boolean r11 = r3.paddingBottomSystemWindowInsets
                            if (r11 != 0) goto L_0x0094
                            if (r10 == 0) goto L_0x0097
                        L_0x0094:
                            r3.updatePeekHeight()
                        L_0x0097:
                            return r12
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass3.onApplyWindowInsets(android.view.View, androidx.core.view.WindowInsetsCompat, com.google.android.material.internal.ViewUtils$RelativePadding):androidx.core.view.WindowInsetsCompat");
                    }
                });
            }
            this.viewRef = new WeakReference(view);
            MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
            if (materialShapeDrawable2 != null) {
                ViewCompat.Api16Impl.setBackground(view, materialShapeDrawable2);
                MaterialShapeDrawable materialShapeDrawable3 = this.materialShapeDrawable;
                float f2 = this.elevation;
                if (f2 == -1.0f) {
                    f2 = ViewCompat.Api21Impl.getElevation(view);
                }
                materialShapeDrawable3.setElevation(f2);
                if (this.state == 3) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.isShapeExpanded = z2;
                MaterialShapeDrawable materialShapeDrawable4 = this.materialShapeDrawable;
                if (z2) {
                    f = 0.0f;
                } else {
                    f = 1.0f;
                }
                materialShapeDrawable4.setInterpolation(f);
            } else {
                ColorStateList colorStateList = this.backgroundTint;
                if (colorStateList != null) {
                    ViewCompat.Api21Impl.setBackgroundTintList(view, colorStateList);
                }
            }
            updateAccessibilityActions$1();
            if (ViewCompat.Api16Impl.getImportantForAccessibility(view) == 0) {
                ViewCompat.Api16Impl.setImportantForAccessibility(view, 1);
            }
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = new ViewDragHelper(coordinatorLayout.getContext(), coordinatorLayout, this.dragCallback);
        }
        int top = view.getTop();
        coordinatorLayout.onLayoutChild(view, i);
        this.parentWidth = coordinatorLayout.getWidth();
        this.parentHeight = coordinatorLayout.getHeight();
        int height = view.getHeight();
        this.childHeight = height;
        int i3 = this.parentHeight;
        int i4 = i3 - height;
        int i5 = this.insetTop;
        if (i4 < i5) {
            if (this.paddingTopSystemWindowInsets) {
                this.childHeight = i3;
            } else {
                this.childHeight = i3 - i5;
            }
        }
        this.fitToContentsOffset = Math.max(0, i3 - this.childHeight);
        this.halfExpandedOffset = (int) ((1.0f - this.halfExpandedRatio) * ((float) this.parentHeight));
        calculateCollapsedOffset();
        int i6 = this.state;
        if (i6 == 3) {
            view.offsetTopAndBottom(getExpandedOffset());
        } else if (i6 == 6) {
            view.offsetTopAndBottom(this.halfExpandedOffset);
        } else if (this.hideable && i6 == 5) {
            view.offsetTopAndBottom(this.parentHeight);
        } else if (i6 == 4) {
            view.offsetTopAndBottom(this.collapsedOffset);
        } else if (i6 == 1 || i6 == 2) {
            view.offsetTopAndBottom(top - view.getTop());
        }
        this.nestedScrollingChildRef = new WeakReference(findScrollingChild(view));
        while (true) {
            ArrayList arrayList = this.callbacks;
            if (i2 >= arrayList.size()) {
                return true;
            }
            ((BottomSheetCallback) arrayList.get(i2)).onLayout(view);
            i2++;
        }
    }

    public final boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, this.maxWidth, marginLayoutParams.width), getChildMeasureSpec(i3, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, this.maxHeight, marginLayoutParams.height));
        return true;
    }

    public final boolean onNestedPreFling(View view) {
        WeakReference weakReference = this.nestedScrollingChildRef;
        if (weakReference == null || view != weakReference.get() || this.state == 3) {
            return false;
        }
        return true;
    }

    public final void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
        View view3;
        if (i3 != 1) {
            WeakReference weakReference = this.nestedScrollingChildRef;
            if (weakReference != null) {
                view3 = (View) weakReference.get();
            } else {
                view3 = null;
            }
            if (view2 == view3) {
                int top = view.getTop();
                int i4 = top - i2;
                if (i2 > 0) {
                    if (i4 < getExpandedOffset()) {
                        int expandedOffset2 = top - getExpandedOffset();
                        iArr[1] = expandedOffset2;
                        int i5 = -expandedOffset2;
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        view.offsetTopAndBottom(i5);
                        setStateInternal(3);
                    } else if (this.draggable) {
                        iArr[1] = i2;
                        WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                        view.offsetTopAndBottom(-i2);
                        setStateInternal(1);
                    } else {
                        return;
                    }
                } else if (i2 < 0 && !view2.canScrollVertically(-1)) {
                    int i6 = this.collapsedOffset;
                    if (i4 > i6 && !this.hideable) {
                        int i7 = top - i6;
                        iArr[1] = i7;
                        int i8 = -i7;
                        WeakHashMap weakHashMap3 = ViewCompat.sViewPropertyAnimatorMap;
                        view.offsetTopAndBottom(i8);
                        setStateInternal(4);
                    } else if (this.draggable) {
                        iArr[1] = i2;
                        WeakHashMap weakHashMap4 = ViewCompat.sViewPropertyAnimatorMap;
                        view.offsetTopAndBottom(-i2);
                        setStateInternal(1);
                    } else {
                        return;
                    }
                }
                dispatchOnSlide(view.getTop());
                this.lastNestedScrollDy = i2;
                this.nestedScrolled = true;
            }
        }
    }

    public final void onRestoreInstanceState(View view, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        int i = this.saveFlags;
        if (i != 0) {
            if (i == -1 || (i & 1) == 1) {
                this.peekHeight = savedState.peekHeight;
            }
            if (i == -1 || (i & 2) == 2) {
                this.fitToContents = savedState.fitToContents;
            }
            if (i == -1 || (i & 4) == 4) {
                this.hideable = savedState.hideable;
            }
            if (i == -1 || (i & 8) == 8) {
                this.skipCollapsed = savedState.skipCollapsed;
            }
        }
        int i2 = savedState.state;
        if (i2 == 1 || i2 == 2) {
            this.state = 4;
        } else {
            this.state = i2;
        }
    }

    public final Parcelable onSaveInstanceState(View view) {
        return new SavedState((Parcelable) View.BaseSavedState.EMPTY_STATE, this);
    }

    public final boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        if ((i & 2) != 0) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        if (r4.getTop() <= r2.halfExpandedOffset) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0071, code lost:
        if (java.lang.Math.abs(r3 - r2.fitToContentsOffset) < java.lang.Math.abs(r3 - r2.collapsedOffset)) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0080, code lost:
        if (r3 < java.lang.Math.abs(r3 - r2.collapsedOffset)) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0090, code lost:
        if (java.lang.Math.abs(r3 - r1) < java.lang.Math.abs(r3 - r2.collapsedOffset)) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ac, code lost:
        if (java.lang.Math.abs(r3 - r2.halfExpandedOffset) < java.lang.Math.abs(r3 - r2.collapsedOffset)) goto L_0x00ae;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout r3, android.view.View r4, android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.getExpandedOffset()
            r0 = 3
            if (r3 != r6) goto L_0x000f
            r2.setStateInternal(r0)
            return
        L_0x000f:
            java.lang.ref.WeakReference r3 = r2.nestedScrollingChildRef
            if (r3 == 0) goto L_0x00b5
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto L_0x00b5
            boolean r3 = r2.nestedScrolled
            if (r3 != 0) goto L_0x001f
            goto L_0x00b5
        L_0x001f:
            int r3 = r2.lastNestedScrollDy
            r5 = 6
            if (r3 <= 0) goto L_0x0034
            boolean r3 = r2.fitToContents
            if (r3 == 0) goto L_0x002a
            goto L_0x00af
        L_0x002a:
            int r3 = r4.getTop()
            int r6 = r2.halfExpandedOffset
            if (r3 <= r6) goto L_0x00af
            goto L_0x00ae
        L_0x0034:
            boolean r3 = r2.hideable
            if (r3 == 0) goto L_0x0055
            android.view.VelocityTracker r3 = r2.velocityTracker
            if (r3 != 0) goto L_0x003e
            r3 = 0
            goto L_0x004d
        L_0x003e:
            r6 = 1000(0x3e8, float:1.401E-42)
            float r1 = r2.maximumVelocity
            r3.computeCurrentVelocity(r6, r1)
            android.view.VelocityTracker r3 = r2.velocityTracker
            int r6 = r2.activePointerId
            float r3 = r3.getYVelocity(r6)
        L_0x004d:
            boolean r3 = r2.shouldHide(r4, r3)
            if (r3 == 0) goto L_0x0055
            r0 = 5
            goto L_0x00af
        L_0x0055:
            int r3 = r2.lastNestedScrollDy
            r6 = 4
            if (r3 != 0) goto L_0x0093
            int r3 = r4.getTop()
            boolean r1 = r2.fitToContents
            if (r1 == 0) goto L_0x0074
            int r5 = r2.fitToContentsOffset
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            int r1 = r2.collapsedOffset
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r5 >= r3) goto L_0x0097
            goto L_0x00af
        L_0x0074:
            int r1 = r2.halfExpandedOffset
            if (r3 >= r1) goto L_0x0083
            int r6 = r2.collapsedOffset
            int r6 = r3 - r6
            int r6 = java.lang.Math.abs(r6)
            if (r3 >= r6) goto L_0x00ae
            goto L_0x00af
        L_0x0083:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.collapsedOffset
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L_0x0097
            goto L_0x00ae
        L_0x0093:
            boolean r3 = r2.fitToContents
            if (r3 == 0) goto L_0x0099
        L_0x0097:
            r0 = r6
            goto L_0x00af
        L_0x0099:
            int r3 = r4.getTop()
            int r0 = r2.halfExpandedOffset
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.collapsedOffset
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L_0x0097
        L_0x00ae:
            r0 = r5
        L_0x00af:
            r3 = 0
            r2.startSettling(r0, r4, r3)
            r2.nestedScrolled = r3
        L_0x00b5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    public final boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        int i = this.state;
        if (i == 1 && actionMasked == 0) {
            return true;
        }
        ViewDragHelper viewDragHelper2 = this.viewDragHelper;
        if (viewDragHelper2 != null && (this.draggable || i == 1)) {
            viewDragHelper2.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            this.activePointerId = -1;
            VelocityTracker velocityTracker2 = this.velocityTracker;
            if (velocityTracker2 != null) {
                velocityTracker2.recycle();
                this.velocityTracker = null;
            }
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (this.viewDragHelper != null && ((this.draggable || this.state == 1) && actionMasked == 2 && !this.ignoreEvents)) {
            float abs = Math.abs(((float) this.initialY) - motionEvent.getY());
            ViewDragHelper viewDragHelper3 = this.viewDragHelper;
            if (abs > ((float) viewDragHelper3.mTouchSlop)) {
                viewDragHelper3.captureChildView(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.ignoreEvents;
    }

    public final void setHideable(boolean z) {
        if (this.hideable != z) {
            this.hideable = z;
            if (!z && this.state == 5) {
                setState(4);
            }
            updateAccessibilityActions$1();
        }
    }

    public final void setPeekHeight(int i) {
        if (i == -1) {
            if (!this.peekHeightAuto) {
                this.peekHeightAuto = true;
            } else {
                return;
            }
        } else if (this.peekHeightAuto || this.peekHeight != i) {
            this.peekHeightAuto = false;
            this.peekHeight = Math.max(0, i);
        } else {
            return;
        }
        updatePeekHeight();
    }

    public final void setState(int i) {
        String str;
        final int i2;
        if (i == 1 || i == 2) {
            StringBuilder sb = new StringBuilder("STATE_");
            if (i == 1) {
                str = "DRAGGING";
            } else {
                str = "SETTLING";
            }
            throw new IllegalArgumentException(ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, str, " should not be set externally."));
        } else if (this.hideable || i != 5) {
            if (i != 6 || !this.fitToContents || getTopOffsetForState(i) > this.fitToContentsOffset) {
                i2 = i;
            } else {
                i2 = 3;
            }
            WeakReference weakReference = this.viewRef;
            if (weakReference == null || weakReference.get() == null) {
                setStateInternal(i);
                return;
            }
            final View view = (View) this.viewRef.get();
            AnonymousClass1 r1 = new Runnable() {
                public final void run() {
                    BottomSheetBehavior.this.startSettling(i2, view, false);
                }
            };
            ViewParent parent = view.getParent();
            if (parent != null && parent.isLayoutRequested()) {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                if (ViewCompat.Api19Impl.isAttachedToWindow(view)) {
                    view.post(r1);
                    return;
                }
            }
            r1.run();
        } else {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("Cannot set state: ", i, "BottomSheetBehavior");
        }
    }

    public final void setStateInternal(int i) {
        View view;
        if (this.state != i) {
            this.state = i;
            if (!(i == 4 || i == 3 || i == 6)) {
                boolean z = this.hideable;
            }
            WeakReference weakReference = this.viewRef;
            if (weakReference != null && (view = (View) weakReference.get()) != null) {
                int i2 = 0;
                if (i == 3) {
                    updateImportantForAccessibility(true);
                } else if (i == 6 || i == 5 || i == 4) {
                    updateImportantForAccessibility(false);
                }
                updateDrawableForTargetState(i);
                while (true) {
                    ArrayList arrayList = this.callbacks;
                    if (i2 < arrayList.size()) {
                        ((BottomSheetCallback) arrayList.get(i2)).onStateChanged(view, i);
                        i2++;
                    } else {
                        updateAccessibilityActions$1();
                        return;
                    }
                }
            }
        }
    }

    public final boolean shouldHide(View view, float f) {
        if (this.skipCollapsed) {
            return true;
        }
        if (view.getTop() < this.collapsedOffset) {
            return false;
        }
        int calculatePeekHeight = calculatePeekHeight();
        if (Math.abs(((f * this.hideFriction) + ((float) view.getTop())) - ((float) this.collapsedOffset)) / ((float) calculatePeekHeight) > 0.5f) {
            return true;
        }
        return false;
    }

    public final void startSettling(int i, View view, boolean z) {
        int topOffsetForState = getTopOffsetForState(i);
        ViewDragHelper viewDragHelper2 = this.viewDragHelper;
        if (viewDragHelper2 == null || (!z ? !viewDragHelper2.smoothSlideViewTo(view, view.getLeft(), topOffsetForState) : !viewDragHelper2.settleCapturedViewAt(view.getLeft(), topOffsetForState))) {
            setStateInternal(i);
            return;
        }
        setStateInternal(2);
        updateDrawableForTargetState(i);
        this.stateSettlingTracker.continueSettlingToState(i);
    }

    public final void updateAccessibilityActions$1() {
        View view;
        WeakReference weakReference = this.viewRef;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            ViewCompat.removeActionWithId(view, 524288);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, 0);
            ViewCompat.removeActionWithId(view, 262144);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, 0);
            ViewCompat.removeActionWithId(view, 1048576);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, 0);
            int i = this.expandHalfwayActionId;
            if (i != -1) {
                ViewCompat.removeActionWithId(view, i);
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, 0);
            }
            final int i2 = 6;
            if (!this.fitToContents && this.state != 6) {
                this.expandHalfwayActionId = ViewCompat.addAccessibilityAction(view, view.getResources().getString(2131952110), new AccessibilityViewCommand(6) {
                    public final boolean perform(View view) {
                        BottomSheetBehavior.this.setState(i2);
                        return true;
                    }
                });
            }
            if (this.hideable && this.state != 5) {
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, (CharSequence) null, new AccessibilityViewCommand(5) {
                    public final boolean perform(View view) {
                        BottomSheetBehavior.this.setState(i2);
                        return true;
                    }
                });
            }
            int i3 = this.state;
            if (i3 == 3) {
                if (this.fitToContents) {
                    i2 = 4;
                }
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, (CharSequence) null, new AccessibilityViewCommand() {
                    public final boolean perform(View view) {
                        BottomSheetBehavior.this.setState(i2);
                        return true;
                    }
                });
            } else if (i3 == 4) {
                if (this.fitToContents) {
                    i2 = 3;
                }
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, (CharSequence) null, new AccessibilityViewCommand() {
                    public final boolean perform(View view) {
                        BottomSheetBehavior.this.setState(i2);
                        return true;
                    }
                });
            } else if (i3 == 6) {
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, (CharSequence) null, new AccessibilityViewCommand(4) {
                    public final boolean perform(View view) {
                        BottomSheetBehavior.this.setState(i2);
                        return true;
                    }
                });
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, (CharSequence) null, new AccessibilityViewCommand(3) {
                    public final boolean perform(View view) {
                        BottomSheetBehavior.this.setState(i2);
                        return true;
                    }
                });
            }
        }
    }

    public final void updateDrawableForTargetState(int i) {
        boolean z;
        ValueAnimator valueAnimator;
        float f;
        if (i != 2) {
            if (i == 3) {
                z = true;
            } else {
                z = false;
            }
            if (this.isShapeExpanded != z) {
                this.isShapeExpanded = z;
                if (this.materialShapeDrawable != null && (valueAnimator = this.interpolatorAnimator) != null) {
                    if (valueAnimator.isRunning()) {
                        this.interpolatorAnimator.reverse();
                        return;
                    }
                    if (z) {
                        f = 0.0f;
                    } else {
                        f = 1.0f;
                    }
                    this.interpolatorAnimator.setFloatValues(new float[]{1.0f - f, f});
                    this.interpolatorAnimator.start();
                }
            }
        }
    }

    public final void updateImportantForAccessibility(boolean z) {
        WeakReference weakReference = this.viewRef;
        if (weakReference != null) {
            ViewParent parent = ((View) weakReference.get()).getParent();
            if (parent instanceof CoordinatorLayout) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
                int childCount = coordinatorLayout.getChildCount();
                if (z) {
                    if (this.importantForAccessibilityMap == null) {
                        this.importantForAccessibilityMap = new HashMap(childCount);
                    } else {
                        return;
                    }
                }
                for (int i = 0; i < childCount; i++) {
                    View childAt = coordinatorLayout.getChildAt(i);
                    if (childAt != this.viewRef.get() && z) {
                        this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    }
                }
                if (!z) {
                    this.importantForAccessibilityMap = null;
                }
            }
        }
    }

    public final void updatePeekHeight() {
        View view;
        if (this.viewRef != null) {
            calculateCollapsedOffset();
            if (this.state == 4 && (view = (View) this.viewRef.get()) != null) {
                view.requestLayout();
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new Object();
        public final boolean fitToContents;
        public final boolean hideable;
        public final int peekHeight;
        public final boolean skipCollapsed;
        public final int state;

        /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$SavedState$1  reason: invalid class name */
        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
            this.state = parcel.readInt();
            this.peekHeight = parcel.readInt();
            boolean z = false;
            this.fitToContents = parcel.readInt() == 1;
            this.hideable = parcel.readInt() == 1;
            this.skipCollapsed = parcel.readInt() == 1 ? true : z;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
            parcel.writeInt(this.peekHeight);
            parcel.writeInt(this.fitToContents ? 1 : 0);
            parcel.writeInt(this.hideable ? 1 : 0);
            parcel.writeInt(this.skipCollapsed ? 1 : 0);
        }

        public SavedState(Parcelable parcelable, BottomSheetBehavior bottomSheetBehavior) {
            super(parcelable);
            this.state = bottomSheetBehavior.state;
            this.peekHeight = bottomSheetBehavior.peekHeight;
            this.fitToContents = bottomSheetBehavior.fitToContents;
            this.hideable = bottomSheetBehavior.hideable;
            this.skipCollapsed = bottomSheetBehavior.skipCollapsed;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class BottomSheetCallback {
        public abstract void onSlide(View view);

        public abstract void onStateChanged(View view, int i);

        public void onLayout(View view) {
        }
    }

    public BottomSheetBehavior() {
    }

    public final void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
    }
}
