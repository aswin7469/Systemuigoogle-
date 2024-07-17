package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButtonImpl;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.AttachedBehavior {
    public static final /* synthetic */ int $r8$clinit = 0;
    public Behavior behavior;
    public int bottomInset;
    public int fabAlignmentMode;
    public final int fabAlignmentModeEndMargin;
    public final int fabAnchorMode;
    public final AnonymousClass1 fabAnimationListener;
    public boolean fabAttached;
    public final int fabOffsetEndMode;
    public final AnonymousClass2 fabTransformationCallback;
    public final boolean hideOnScroll;
    public int leftInset;
    public final MaterialShapeDrawable materialShapeDrawable;
    public final int menuAlignmentMode;
    public boolean menuAnimatingWithFabAlignmentMode;
    public Animator menuAnimator;
    public final Integer navigationIconTint;
    public final boolean paddingBottomSystemWindowInsets;
    public final boolean paddingLeftSystemWindowInsets;
    public final boolean paddingRightSystemWindowInsets;
    public final boolean removeEmbeddedFabElevation;
    public int rightInset;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new Object();
        public int fabAlignmentMode;
        public boolean fabAttached;

        /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$SavedState$1  reason: invalid class name */
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
            boolean z;
            this.fabAlignmentMode = parcel.readInt();
            if (parcel.readInt() != 0) {
                z = true;
            } else {
                z = false;
            }
            this.fabAttached = z;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.fabAlignmentMode);
            parcel.writeInt(this.fabAttached ? 1 : 0);
        }
    }

    public BottomAppBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View findDependentView() {
        /*
            r3 = this;
            android.view.ViewParent r0 = r3.getParent()
            boolean r0 = r0 instanceof androidx.coordinatorlayout.widget.CoordinatorLayout
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            android.view.ViewParent r0 = r3.getParent()
            androidx.coordinatorlayout.widget.CoordinatorLayout r0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r0
            androidx.coordinatorlayout.widget.DirectedAcyclicGraph r0 = r0.mChildDag
            androidx.collection.SimpleArrayMap r0 = r0.mGraph
            java.lang.Object r3 = r0.get(r3)
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            if (r3 != 0) goto L_0x001e
            r0 = r1
            goto L_0x0023
        L_0x001e:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r3)
        L_0x0023:
            if (r0 != 0) goto L_0x0029
            java.util.List r0 = java.util.Collections.emptyList()
        L_0x0029:
            java.util.Iterator r3 = r0.iterator()
        L_0x002d:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0042
            java.lang.Object r0 = r3.next()
            android.view.View r0 = (android.view.View) r0
            boolean r2 = r0 instanceof com.google.android.material.floatingactionbutton.FloatingActionButton
            if (r2 != 0) goto L_0x0041
            boolean r2 = r0 instanceof com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
            if (r2 == 0) goto L_0x002d
        L_0x0041:
            return r0
        L_0x0042:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomappbar.BottomAppBar.findDependentView():android.view.View");
    }

    public final int getActionMenuViewTranslationX(ActionMenuView actionMenuView, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        if (this.menuAlignmentMode != 1 && (i != 1 || !z)) {
            return 0;
        }
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        if (isLayoutRtl) {
            i2 = getMeasuredWidth();
        } else {
            i2 = 0;
        }
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if ((childAt.getLayoutParams() instanceof Toolbar.LayoutParams) && (((Toolbar.LayoutParams) childAt.getLayoutParams()).gravity & 8388615) == 8388611) {
                if (isLayoutRtl) {
                    i2 = Math.min(i2, childAt.getLeft());
                } else {
                    i2 = Math.max(i2, childAt.getRight());
                }
            }
        }
        if (isLayoutRtl) {
            i3 = actionMenuView.getRight();
        } else {
            i3 = actionMenuView.getLeft();
        }
        if (isLayoutRtl) {
            i4 = this.rightInset;
        } else {
            i4 = -this.leftInset;
        }
        return i2 - (i3 + i4);
    }

    public final CoordinatorLayout.Behavior getBehavior() {
        if (this.behavior == null) {
            this.behavior = new Behavior();
        }
        return this.behavior;
    }

    public final float getFabTranslationX$1() {
        int i;
        int i2;
        int i3 = this.fabAlignmentMode;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int i4 = 1;
        if (i3 != 1) {
            return 0.0f;
        }
        View findDependentView = findDependentView();
        if (isLayoutRtl) {
            i = this.leftInset;
        } else {
            i = this.rightInset;
        }
        if (this.fabAlignmentModeEndMargin == -1 || findDependentView == null) {
            i2 = this.fabOffsetEndMode + i;
        } else {
            i2 = (findDependentView.getMeasuredWidth() / 2) + this.fabAlignmentModeEndMargin + i;
        }
        int measuredWidth = (getMeasuredWidth() / 2) - i2;
        if (isLayoutRtl) {
            i4 = -1;
        }
        return (float) (measuredWidth * i4);
    }

    public final BottomAppBarTopEdgeTreatment getTopEdgeTreatment() {
        return (BottomAppBarTopEdgeTreatment) this.materialShapeDrawable.drawableState.shapeAppearanceModel.topEdge;
    }

    public final boolean isFabVisibleOrWillBeShown() {
        FloatingActionButton floatingActionButton;
        View findDependentView = findDependentView();
        if (findDependentView instanceof FloatingActionButton) {
            floatingActionButton = (FloatingActionButton) findDependentView;
        } else {
            floatingActionButton = null;
        }
        if (floatingActionButton != null) {
            FloatingActionButtonImpl impl = floatingActionButton.getImpl();
            if (impl.view.getVisibility() != 0) {
                if (impl.animState == 2) {
                    return true;
                }
            } else if (impl.animState != 1) {
                return true;
            }
        }
        return false;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.materialShapeDrawable);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            Animator animator = this.menuAnimator;
            if (animator != null) {
                animator.cancel();
            }
            setCutoutStateAndTranslateFab();
        }
        setActionMenuViewPosition();
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        this.fabAlignmentMode = savedState.fabAlignmentMode;
        this.fabAttached = savedState.fabAttached;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.os.Parcelable, androidx.customview.view.AbsSavedState, com.google.android.material.bottomappbar.BottomAppBar$SavedState] */
    public final Parcelable onSaveInstanceState() {
        ? absSavedState = new AbsSavedState(super.onSaveInstanceState());
        absSavedState.fabAlignmentMode = this.fabAlignmentMode;
        absSavedState.fabAttached = this.fabAttached;
        return absSavedState;
    }

    public final void setActionMenuViewPosition() {
        ActionMenuView actionMenuView;
        int i = 0;
        while (true) {
            if (i >= getChildCount()) {
                actionMenuView = null;
                break;
            }
            View childAt = getChildAt(i);
            if (childAt instanceof ActionMenuView) {
                actionMenuView = (ActionMenuView) childAt;
                break;
            }
            i++;
        }
        if (actionMenuView != null && this.menuAnimator == null) {
            actionMenuView.setAlpha(1.0f);
            if (!isFabVisibleOrWillBeShown()) {
                translateActionMenuView(actionMenuView, 0, false);
            } else {
                translateActionMenuView(actionMenuView, this.fabAlignmentMode, this.fabAttached);
            }
        }
    }

    public final void setCutoutStateAndTranslateFab() {
        float f;
        getTopEdgeTreatment().horizontalOffset = getFabTranslationX$1();
        MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
        float f2 = 0.0f;
        if (!this.fabAttached || !isFabVisibleOrWillBeShown() || this.fabAnchorMode != 1) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        materialShapeDrawable2.setInterpolation(f);
        View findDependentView = findDependentView();
        if (findDependentView != null) {
            if (this.fabAnchorMode == 1) {
                f2 = -getTopEdgeTreatment().cradleVerticalOffset;
            }
            findDependentView.setTranslationY(f2);
            findDependentView.setTranslationX(getFabTranslationX$1());
        }
    }

    public final void setElevation(float f) {
        this.materialShapeDrawable.setElevation(f);
        MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
        int shadowOffsetY = materialShapeDrawable2.drawableState.shadowCompatRadius - materialShapeDrawable2.getShadowOffsetY();
        if (this.behavior == null) {
            this.behavior = new Behavior();
        }
        Behavior behavior2 = this.behavior;
        behavior2.additionalHiddenOffsetY = shadowOffsetY;
        if (behavior2.currentState == 1) {
            setTranslationY((float) (behavior2.height + shadowOffsetY));
        }
    }

    public final void setNavigationIcon(Drawable drawable) {
        if (!(drawable == null || this.navigationIconTint == null)) {
            drawable = drawable.mutate();
            drawable.setTint(this.navigationIconTint.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public final void translateActionMenuView(ActionMenuView actionMenuView, int i, boolean z) {
        actionMenuView.setTranslationX((float) getActionMenuViewTranslationX(actionMenuView, i, z));
    }

    /* JADX WARNING: type inference failed for: r1v8, types: [com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment, com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v6, types: [java.lang.Object, com.google.android.material.shape.CornerTreatment] */
    /* JADX WARNING: type inference failed for: r6v4, types: [java.lang.Object, com.google.android.material.shape.CornerTreatment] */
    /* JADX WARNING: type inference failed for: r12v1, types: [java.lang.Object, com.google.android.material.shape.CornerTreatment] */
    /* JADX WARNING: type inference failed for: r14v3, types: [java.lang.Object, com.google.android.material.shape.CornerTreatment] */
    /* JADX WARNING: type inference failed for: r4v6, types: [com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v1, types: [com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v2, types: [com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, com.google.android.material.shape.ShapeAppearanceModel] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BottomAppBar(android.content.Context r18, android.util.AttributeSet r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r7 = r19
            r8 = 2130968712(0x7f040088, float:1.7546085E38)
            r9 = 2132018776(0x7f140658, float:1.9675868E38)
            r1 = r18
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r1, r7, r8, r9)
            r0.<init>(r1, r7, r8)
            com.google.android.material.shape.MaterialShapeDrawable r10 = new com.google.android.material.shape.MaterialShapeDrawable
            r10.<init>()
            r0.materialShapeDrawable = r10
            r11 = 0
            r0.menuAnimatingWithFabAlignmentMode = r11
            r12 = 1
            r0.fabAttached = r12
            com.google.android.material.bottomappbar.BottomAppBar$1 r1 = new com.google.android.material.bottomappbar.BottomAppBar$1
            r1.<init>(r0, r11)
            r0.fabAnimationListener = r1
            com.google.android.material.bottomappbar.BottomAppBar$2 r1 = new com.google.android.material.bottomappbar.BottomAppBar$2
            r1.<init>()
            r0.fabTransformationCallback = r1
            android.content.Context r13 = r17.getContext()
            int[] r3 = com.google.android.material.R$styleable.BottomAppBar
            r5 = 2132018776(0x7f140658, float:1.9675868E38)
            int[] r6 = new int[r11]
            r1 = r13
            r2 = r19
            r4 = r8
            android.content.res.TypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r1, r2, r3, r4, r5, r6)
            android.content.res.ColorStateList r2 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r13, (android.content.res.TypedArray) r1, (int) r11)
            r3 = 11
            boolean r4 = r1.hasValue(r3)
            r5 = -1
            if (r4 == 0) goto L_0x0067
            int r3 = r1.getColor(r3, r5)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r0.navigationIconTint = r3
            androidx.appcompat.widget.AppCompatImageButton r3 = r0.mNavButtonView
            if (r3 == 0) goto L_0x0061
            android.graphics.drawable.Drawable r3 = r3.getDrawable()
            goto L_0x0062
        L_0x0061:
            r3 = 0
        L_0x0062:
            if (r3 == 0) goto L_0x0067
            r0.setNavigationIcon(r3)
        L_0x0067:
            int r3 = r1.getDimensionPixelSize(r12, r11)
            r4 = 6
            int r4 = r1.getDimensionPixelOffset(r4, r11)
            float r4 = (float) r4
            r6 = 7
            int r6 = r1.getDimensionPixelOffset(r6, r11)
            float r6 = (float) r6
            r14 = 8
            int r14 = r1.getDimensionPixelOffset(r14, r11)
            float r14 = (float) r14
            r15 = 2
            int r15 = r1.getInt(r15, r11)
            r0.fabAlignmentMode = r15
            r15 = 5
            r1.getInt(r15, r11)
            r15 = 4
            int r8 = r1.getInt(r15, r12)
            r0.fabAnchorMode = r8
            r8 = 15
            boolean r8 = r1.getBoolean(r8, r12)
            r0.removeEmbeddedFabElevation = r8
            r8 = 10
            int r8 = r1.getInt(r8, r11)
            r0.menuAlignmentMode = r8
            r8 = 9
            boolean r8 = r1.getBoolean(r8, r11)
            r0.hideOnScroll = r8
            r8 = 12
            boolean r8 = r1.getBoolean(r8, r11)
            r0.paddingBottomSystemWindowInsets = r8
            r8 = 13
            boolean r8 = r1.getBoolean(r8, r11)
            r0.paddingLeftSystemWindowInsets = r8
            r8 = 14
            boolean r8 = r1.getBoolean(r8, r11)
            r0.paddingRightSystemWindowInsets = r8
            r8 = 3
            int r5 = r1.getDimensionPixelOffset(r8, r5)
            r0.fabAlignmentModeEndMargin = r5
            r1.recycle()
            android.content.res.Resources r1 = r17.getResources()
            r5 = 2131166733(0x7f07060d, float:1.794772E38)
            int r1 = r1.getDimensionPixelOffset(r5)
            r0.fabOffsetEndMode = r1
            com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment r1 = new com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
            r1.<init>()
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            r1.fabCornerSize = r5
            r1.fabMargin = r4
            r1.roundedCornerRadius = r6
            r4 = 0
            int r5 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r5 < 0) goto L_0x0194
            r1.cradleVerticalOffset = r14
            r1.horizontalOffset = r4
            com.google.android.material.shape.RoundedCornerTreatment r5 = new com.google.android.material.shape.RoundedCornerTreatment
            r5.<init>()
            com.google.android.material.shape.RoundedCornerTreatment r6 = new com.google.android.material.shape.RoundedCornerTreatment
            r6.<init>()
            com.google.android.material.shape.RoundedCornerTreatment r12 = new com.google.android.material.shape.RoundedCornerTreatment
            r12.<init>()
            com.google.android.material.shape.RoundedCornerTreatment r14 = new com.google.android.material.shape.RoundedCornerTreatment
            r14.<init>()
            com.google.android.material.shape.AbsoluteCornerSize r15 = new com.google.android.material.shape.AbsoluteCornerSize
            r15.<init>(r4)
            com.google.android.material.shape.AbsoluteCornerSize r8 = new com.google.android.material.shape.AbsoluteCornerSize
            r8.<init>(r4)
            com.google.android.material.shape.AbsoluteCornerSize r11 = new com.google.android.material.shape.AbsoluteCornerSize
            r11.<init>(r4)
            com.google.android.material.shape.AbsoluteCornerSize r9 = new com.google.android.material.shape.AbsoluteCornerSize
            r9.<init>(r4)
            com.google.android.material.shape.EdgeTreatment r4 = new com.google.android.material.shape.EdgeTreatment
            r4.<init>()
            com.google.android.material.shape.EdgeTreatment r7 = new com.google.android.material.shape.EdgeTreatment
            r7.<init>()
            r16 = r2
            com.google.android.material.shape.EdgeTreatment r2 = new com.google.android.material.shape.EdgeTreatment
            r2.<init>()
            com.google.android.material.shape.ShapeAppearanceModel r0 = new com.google.android.material.shape.ShapeAppearanceModel
            r0.<init>()
            r0.topLeftCorner = r5
            r0.topRightCorner = r6
            r0.bottomRightCorner = r12
            r0.bottomLeftCorner = r14
            r0.topLeftCornerSize = r15
            r0.topRightCornerSize = r8
            r0.bottomRightCornerSize = r11
            r0.bottomLeftCornerSize = r9
            r0.topEdge = r1
            r0.rightEdge = r4
            r0.bottomEdge = r7
            r0.leftEdge = r2
            r10.setShapeAppearanceModel(r0)
            r10.setShadowCompatibilityMode()
            android.graphics.Paint$Style r0 = android.graphics.Paint.Style.FILL
            r10.setPaintStyle(r0)
            r10.initializeElevationOverlay(r13)
            float r0 = (float) r3
            r1 = r17
            r1.setElevation(r0)
            r0 = r16
            r10.setTintList(r0)
            java.util.WeakHashMap r0 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api16Impl.setBackground(r1, r10)
            com.google.android.material.bottomappbar.BottomAppBar$2 r0 = new com.google.android.material.bottomappbar.BottomAppBar$2
            r0.<init>()
            android.content.Context r2 = r17.getContext()
            int[] r3 = com.google.android.material.R$styleable.Insets
            r4 = r19
            r5 = 2130968712(0x7f040088, float:1.7546085E38)
            r6 = 2132018776(0x7f140658, float:1.9675868E38)
            android.content.res.TypedArray r2 = r2.obtainStyledAttributes(r4, r3, r5, r6)
            r3 = 0
            r4 = 3
            boolean r4 = r2.getBoolean(r4, r3)
            r5 = 4
            boolean r5 = r2.getBoolean(r5, r3)
            r6 = 5
            boolean r3 = r2.getBoolean(r6, r3)
            r2.recycle()
            com.google.android.material.internal.ViewUtils$2 r2 = new com.google.android.material.internal.ViewUtils$2
            r2.<init>(r4, r5, r3, r0)
            com.google.android.material.internal.ViewUtils.doOnApplyWindowInsets(r1, r2)
            return
        L_0x0194:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "cradleVerticalOffset must be positive."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomappbar.BottomAppBar.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public class Behavior extends HideBottomViewOnScrollBehavior {
        public final Rect fabContentRect = new Rect();
        public final AnonymousClass1 fabLayoutListener = new View.OnLayoutChangeListener() {
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                BottomAppBar bottomAppBar = (BottomAppBar) Behavior.this.viewRef.get();
                if (bottomAppBar == null || !(view instanceof FloatingActionButton)) {
                    view.removeOnLayoutChangeListener(this);
                    return;
                }
                FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                Rect rect = Behavior.this.fabContentRect;
                int i9 = 0;
                rect.set(0, 0, floatingActionButton.getMeasuredWidth(), floatingActionButton.getMeasuredHeight());
                floatingActionButton.offsetRectWithShadow(rect);
                int height = Behavior.this.fabContentRect.height();
                float f = (float) height;
                if (f != bottomAppBar.getTopEdgeTreatment().fabDiameter) {
                    bottomAppBar.getTopEdgeTreatment().fabDiameter = f;
                    bottomAppBar.materialShapeDrawable.invalidateSelf();
                }
                ShapeAppearanceModel shapeAppearanceModel = floatingActionButton.getImpl().shapeAppearance;
                shapeAppearanceModel.getClass();
                float cornerSize = shapeAppearanceModel.topLeftCornerSize.getCornerSize(new RectF(Behavior.this.fabContentRect));
                if (cornerSize != bottomAppBar.getTopEdgeTreatment().fabCornerSize) {
                    bottomAppBar.getTopEdgeTreatment().fabCornerSize = cornerSize;
                    bottomAppBar.materialShapeDrawable.invalidateSelf();
                }
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                if (Behavior.this.originalBottomMargin == 0) {
                    int measuredHeight = (floatingActionButton.getMeasuredHeight() - height) / 2;
                    if (bottomAppBar.fabAnchorMode == 1) {
                        i9 = bottomAppBar.getResources().getDimensionPixelOffset(2131166734);
                    }
                    layoutParams.bottomMargin = bottomAppBar.bottomInset + (i9 - measuredHeight);
                    layoutParams.leftMargin = bottomAppBar.leftInset;
                    layoutParams.rightMargin = bottomAppBar.rightInset;
                    if (ViewUtils.isLayoutRtl(floatingActionButton)) {
                        layoutParams.leftMargin += bottomAppBar.fabOffsetEndMode;
                    } else {
                        layoutParams.rightMargin += bottomAppBar.fabOffsetEndMode;
                    }
                }
            }
        };
        public int originalBottomMargin;
        public WeakReference viewRef;

        public Behavior() {
        }

        public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            BottomAppBar bottomAppBar = (BottomAppBar) view;
            this.viewRef = new WeakReference(bottomAppBar);
            int i2 = BottomAppBar.$r8$clinit;
            View findDependentView = bottomAppBar.findDependentView();
            if (findDependentView != null) {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                if (!ViewCompat.Api19Impl.isLaidOut(findDependentView)) {
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) findDependentView.getLayoutParams();
                    layoutParams.anchorGravity = 17;
                    if (bottomAppBar.fabAnchorMode == 1) {
                        layoutParams.anchorGravity = 49;
                    }
                    this.originalBottomMargin = ((CoordinatorLayout.LayoutParams) findDependentView.getLayoutParams()).bottomMargin;
                    if (findDependentView instanceof FloatingActionButton) {
                        FloatingActionButton floatingActionButton = (FloatingActionButton) findDependentView;
                        if (bottomAppBar.fabAnchorMode == 0 && bottomAppBar.removeEmbeddedFabElevation) {
                            ViewCompat.Api21Impl.setElevation(floatingActionButton, 0.0f);
                            FloatingActionButtonImpl impl = floatingActionButton.getImpl();
                            if (impl.elevation != 0.0f) {
                                impl.elevation = 0.0f;
                                impl.onElevationsChanged(0.0f, impl.hoveredFocusedTranslationZ, impl.pressedTranslationZ);
                            }
                        }
                        if (floatingActionButton.getImpl().showMotionSpec == null) {
                            floatingActionButton.getImpl().showMotionSpec = MotionSpec.createFromResource(2130837553, floatingActionButton.getContext());
                        }
                        if (floatingActionButton.getImpl().hideMotionSpec == null) {
                            floatingActionButton.getImpl().hideMotionSpec = MotionSpec.createFromResource(2130837552, floatingActionButton.getContext());
                        }
                        floatingActionButton.addOnLayoutChangeListener(this.fabLayoutListener);
                        AnonymousClass1 r1 = bottomAppBar.fabAnimationListener;
                        FloatingActionButtonImpl impl2 = floatingActionButton.getImpl();
                        if (impl2.hideListeners == null) {
                            impl2.hideListeners = new ArrayList();
                        }
                        impl2.hideListeners.add(r1);
                        AnonymousClass1 r12 = new AnimatorListenerAdapter(bottomAppBar, 2) {
                            public final /* synthetic */ BottomAppBar this$0;

                            {
                                this.this$0 = r1;
                            }

                            public final void onAnimationEnd(Animator animator) {
                                switch (0) {
                                    case 1:
                                        BottomAppBar bottomAppBar = this.this$0;
                                        bottomAppBar.getClass();
                                        bottomAppBar.menuAnimatingWithFabAlignmentMode = false;
                                        bottomAppBar.menuAnimator = null;
                                        return;
                                    default:
                                        super.onAnimationEnd(animator);
                                        return;
                                }
                            }

                            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: androidx.appcompat.widget.ActionMenuView} */
                            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.google.android.material.floatingactionbutton.FloatingActionButton} */
                            /* JADX WARNING: type inference failed for: r3v0 */
                            /* JADX WARNING: type inference failed for: r3v7 */
                            /* JADX WARNING: type inference failed for: r3v8 */
                            /* JADX WARNING: Multi-variable type inference failed */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public final void onAnimationStart(android.animation.Animator r12) {
                                /*
                                    r11 = this;
                                    r0 = 0
                                    r1 = 1
                                    int r2 = 0
                                    r3 = 0
                                    switch(r2) {
                                        case 0: goto L_0x002e;
                                        case 1: goto L_0x0028;
                                        default: goto L_0x0008;
                                    }
                                L_0x0008:
                                    com.google.android.material.bottomappbar.BottomAppBar r0 = r11.this$0
                                    com.google.android.material.bottomappbar.BottomAppBar$1 r0 = r0.fabAnimationListener
                                    r0.onAnimationStart(r12)
                                    com.google.android.material.bottomappbar.BottomAppBar r12 = r11.this$0
                                    android.view.View r12 = r12.findDependentView()
                                    boolean r0 = r12 instanceof com.google.android.material.floatingactionbutton.FloatingActionButton
                                    if (r0 == 0) goto L_0x001c
                                    r3 = r12
                                    com.google.android.material.floatingactionbutton.FloatingActionButton r3 = (com.google.android.material.floatingactionbutton.FloatingActionButton) r3
                                L_0x001c:
                                    if (r3 == 0) goto L_0x0027
                                    com.google.android.material.bottomappbar.BottomAppBar r11 = r11.this$0
                                    float r11 = r11.getFabTranslationX$1()
                                    r3.setTranslationX(r11)
                                L_0x0027:
                                    return
                                L_0x0028:
                                    com.google.android.material.bottomappbar.BottomAppBar r11 = r11.this$0
                                    r11.getClass()
                                    return
                                L_0x002e:
                                    com.google.android.material.bottomappbar.BottomAppBar r11 = r11.this$0
                                    boolean r12 = r11.menuAnimatingWithFabAlignmentMode
                                    if (r12 != 0) goto L_0x00f2
                                    int r12 = r11.fabAlignmentMode
                                    boolean r2 = r11.fabAttached
                                    java.util.WeakHashMap r4 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
                                    boolean r4 = androidx.core.view.ViewCompat.Api19Impl.isLaidOut(r11)
                                    if (r4 != 0) goto L_0x0044
                                    r11.menuAnimatingWithFabAlignmentMode = r0
                                    goto L_0x00f2
                                L_0x0044:
                                    android.animation.Animator r4 = r11.menuAnimator
                                    if (r4 == 0) goto L_0x004b
                                    r4.cancel()
                                L_0x004b:
                                    java.util.ArrayList r4 = new java.util.ArrayList
                                    r4.<init>()
                                    boolean r5 = r11.isFabVisibleOrWillBeShown()
                                    if (r5 != 0) goto L_0x0058
                                    r12 = r0
                                    r2 = r12
                                L_0x0058:
                                    r5 = r0
                                L_0x0059:
                                    int r6 = r11.getChildCount()
                                    if (r5 >= r6) goto L_0x006d
                                    android.view.View r6 = r11.getChildAt(r5)
                                    boolean r7 = r6 instanceof androidx.appcompat.widget.ActionMenuView
                                    if (r7 == 0) goto L_0x006b
                                    r3 = r6
                                    androidx.appcompat.widget.ActionMenuView r3 = (androidx.appcompat.widget.ActionMenuView) r3
                                    goto L_0x006d
                                L_0x006b:
                                    int r5 = r5 + r1
                                    goto L_0x0059
                                L_0x006d:
                                    if (r3 != 0) goto L_0x0070
                                    goto L_0x00db
                                L_0x0070:
                                    android.content.Context r5 = r11.getContext()
                                    r6 = 2130969610(0x7f04040a, float:1.7547907E38)
                                    r7 = 300(0x12c, float:4.2E-43)
                                    int r5 = com.google.android.material.motion.MotionUtils.resolveThemeDuration(r5, r6, r7)
                                    float r5 = (float) r5
                                    r6 = 1065353216(0x3f800000, float:1.0)
                                    float[] r7 = new float[r1]
                                    r7[r0] = r6
                                    java.lang.String r8 = "alpha"
                                    android.animation.ObjectAnimator r7 = android.animation.ObjectAnimator.ofFloat(r3, r8, r7)
                                    r9 = 1061997773(0x3f4ccccd, float:0.8)
                                    float r9 = r9 * r5
                                    long r9 = (long) r9
                                    r7.setDuration(r9)
                                    float r9 = r3.getTranslationX()
                                    int r10 = r11.getActionMenuViewTranslationX(r3, r12, r2)
                                    float r10 = (float) r10
                                    float r9 = r9 - r10
                                    float r9 = java.lang.Math.abs(r9)
                                    int r9 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
                                    if (r9 <= 0) goto L_0x00d0
                                    float[] r6 = new float[r1]
                                    r9 = 0
                                    r6[r0] = r9
                                    android.animation.ObjectAnimator r6 = android.animation.ObjectAnimator.ofFloat(r3, r8, r6)
                                    r8 = 1045220557(0x3e4ccccd, float:0.2)
                                    float r5 = r5 * r8
                                    long r8 = (long) r5
                                    r6.setDuration(r8)
                                    com.google.android.material.bottomappbar.BottomAppBar$7 r5 = new com.google.android.material.bottomappbar.BottomAppBar$7
                                    r5.<init>(r3, r12, r2)
                                    r6.addListener(r5)
                                    android.animation.AnimatorSet r12 = new android.animation.AnimatorSet
                                    r12.<init>()
                                    r2 = 2
                                    android.animation.Animator[] r2 = new android.animation.Animator[r2]
                                    r2[r0] = r6
                                    r2[r1] = r7
                                    r12.playSequentially(r2)
                                    r4.add(r12)
                                    goto L_0x00db
                                L_0x00d0:
                                    float r12 = r3.getAlpha()
                                    int r12 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
                                    if (r12 >= 0) goto L_0x00db
                                    r4.add(r7)
                                L_0x00db:
                                    android.animation.AnimatorSet r12 = new android.animation.AnimatorSet
                                    r12.<init>()
                                    r12.playTogether(r4)
                                    r11.menuAnimator = r12
                                    com.google.android.material.bottomappbar.BottomAppBar$1 r0 = new com.google.android.material.bottomappbar.BottomAppBar$1
                                    r0.<init>(r11, r1)
                                    r12.addListener(r0)
                                    android.animation.Animator r11 = r11.menuAnimator
                                    r11.start()
                                L_0x00f2:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomappbar.BottomAppBar.AnonymousClass1.onAnimationStart(android.animation.Animator):void");
                            }
                        };
                        FloatingActionButtonImpl impl3 = floatingActionButton.getImpl();
                        if (impl3.showListeners == null) {
                            impl3.showListeners = new ArrayList();
                        }
                        impl3.showListeners.add(r12);
                        AnonymousClass2 r13 = bottomAppBar.fabTransformationCallback;
                        FloatingActionButtonImpl impl4 = floatingActionButton.getImpl();
                        FloatingActionButton.TransformationCallbackWrapper transformationCallbackWrapper = new FloatingActionButton.TransformationCallbackWrapper(r13);
                        if (impl4.transformationCallbacks == null) {
                            impl4.transformationCallbacks = new ArrayList();
                        }
                        impl4.transformationCallbacks.add(transformationCallbackWrapper);
                    }
                    bottomAppBar.setCutoutStateAndTranslateFab();
                }
            }
            coordinatorLayout.onLayoutChild(bottomAppBar, i);
            super.onLayoutChild(coordinatorLayout, bottomAppBar, i);
            return false;
        }

        public final boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            BottomAppBar bottomAppBar = (BottomAppBar) view;
            if (!bottomAppBar.hideOnScroll || !super.onStartNestedScroll(coordinatorLayout, bottomAppBar, view2, view3, i, i2)) {
                return false;
            }
            return true;
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public final void setSubtitle(CharSequence charSequence) {
    }

    public final void setTitle(CharSequence charSequence) {
    }
}
