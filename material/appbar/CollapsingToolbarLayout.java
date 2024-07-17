package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class CollapsingToolbarLayout extends FrameLayout {
    public final CollapsingTextHelper collapsingTextHelper;
    public final boolean collapsingTitleEnabled;
    public Drawable contentScrim;
    public int currentOffset;
    public boolean drawCollapsingTitle;
    public View dummyView;
    public final int expandedMarginBottom;
    public final int expandedMarginEnd;
    public final int expandedMarginStart;
    public final int expandedMarginTop;
    public int extraMultilineHeight;
    public final boolean extraMultilineHeightEnabled;
    public final boolean forceApplySystemWindowInsetTop;
    public WindowInsetsCompat lastInsets;
    public OffsetUpdateListener onOffsetChangedListener;
    public boolean refreshToolbar;
    public int scrimAlpha;
    public final long scrimAnimationDuration;
    public ValueAnimator scrimAnimator;
    public final int scrimVisibleHeightTrigger;
    public boolean scrimsAreShown;
    public final Drawable statusBarScrim;
    public final int titleCollapseMode;
    public final Rect tmpRect;
    public ViewGroup toolbar;
    public View toolbarDirectChild;
    public final int toolbarId;
    public int topInsetApplied;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class LayoutParams extends FrameLayout.LayoutParams {
        public int collapseMode;
        public float parallaxMult;
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class OffsetUpdateListener {
        public OffsetUpdateListener() {
        }
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public static ViewOffsetHelper getViewOffsetHelper(View view) {
        ViewOffsetHelper viewOffsetHelper = (ViewOffsetHelper) view.getTag(2131364043);
        if (viewOffsetHelper != null) {
            return viewOffsetHelper;
        }
        ViewOffsetHelper viewOffsetHelper2 = new ViewOffsetHelper(view);
        view.setTag(2131364043, viewOffsetHelper2);
        return viewOffsetHelper2;
    }

    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void draw(Canvas canvas) {
        int i;
        Drawable drawable;
        super.draw(canvas);
        ensureToolbar();
        if (this.toolbar == null && (drawable = this.contentScrim) != null && this.scrimAlpha > 0) {
            drawable.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas);
        }
        if (this.collapsingTitleEnabled && this.drawCollapsingTitle) {
            if (this.toolbar != null && this.contentScrim != null && this.scrimAlpha > 0 && this.titleCollapseMode == 1) {
                CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
                if (collapsingTextHelper2.expandedFraction < collapsingTextHelper2.fadeModeThresholdFraction) {
                    int save = canvas.save();
                    canvas.clipRect(this.contentScrim.getBounds(), Region.Op.DIFFERENCE);
                    this.collapsingTextHelper.draw(canvas);
                    canvas.restoreToCount(save);
                }
            }
            this.collapsingTextHelper.draw(canvas);
        }
        if (this.statusBarScrim != null && this.scrimAlpha > 0) {
            WindowInsetsCompat windowInsetsCompat = this.lastInsets;
            if (windowInsetsCompat != null) {
                i = windowInsetsCompat.getSystemWindowInsetTop();
            } else {
                i = 0;
            }
            if (i > 0) {
                this.statusBarScrim.setBounds(0, -this.currentOffset, getWidth(), i - this.currentOffset);
                this.statusBarScrim.mutate().setAlpha(this.scrimAlpha);
                this.statusBarScrim.draw(canvas);
            }
        }
    }

    public final boolean drawChild(Canvas canvas, View view, long j) {
        boolean z;
        View view2;
        Drawable drawable = this.contentScrim;
        if (drawable == null || this.scrimAlpha <= 0 || ((view2 = this.toolbarDirectChild) == null || view2 == this ? view != this.toolbar : view != view2)) {
            z = false;
        } else {
            int width = getWidth();
            int height = getHeight();
            if (this.titleCollapseMode == 1 && view != null && this.collapsingTitleEnabled) {
                height = view.getBottom();
            }
            drawable.setBounds(0, 0, width, height);
            this.contentScrim.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas);
            z = true;
        }
        if (super.drawChild(canvas, view, j) || z) {
            return true;
        }
        return false;
    }

    public final void drawableStateChanged() {
        boolean z;
        ColorStateList colorStateList;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.statusBarScrim;
        boolean z2 = false;
        if (drawable == null || !drawable.isStateful()) {
            z = false;
        } else {
            z = drawable.setState(drawableState);
        }
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != null && drawable2.isStateful()) {
            z |= drawable2.setState(drawableState);
        }
        CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
        if (collapsingTextHelper2 != null) {
            collapsingTextHelper2.state = drawableState;
            ColorStateList colorStateList2 = collapsingTextHelper2.collapsedTextColor;
            if ((colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = collapsingTextHelper2.expandedTextColor) != null && colorStateList.isStateful())) {
                collapsingTextHelper2.recalculate(false);
                z2 = true;
            }
            z |= z2;
        }
        if (z) {
            invalidate();
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void ensureToolbar() {
        /*
            r7 = this;
            boolean r0 = r7.refreshToolbar
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 0
            r7.toolbar = r0
            r7.toolbarDirectChild = r0
            int r1 = r7.toolbarId
            r2 = -1
            if (r1 == r2) goto L_0x002f
            android.view.View r1 = r7.findViewById(r1)
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r7.toolbar = r1
            if (r1 == 0) goto L_0x002f
            android.view.ViewParent r3 = r1.getParent()
        L_0x001d:
            if (r3 == r7) goto L_0x002d
            if (r3 == 0) goto L_0x002d
            boolean r4 = r3 instanceof android.view.View
            if (r4 == 0) goto L_0x0028
            r1 = r3
            android.view.View r1 = (android.view.View) r1
        L_0x0028:
            android.view.ViewParent r3 = r3.getParent()
            goto L_0x001d
        L_0x002d:
            r7.toolbarDirectChild = r1
        L_0x002f:
            android.view.ViewGroup r1 = r7.toolbar
            r3 = 0
            if (r1 != 0) goto L_0x0050
            int r1 = r7.getChildCount()
            r4 = r3
        L_0x0039:
            if (r4 >= r1) goto L_0x004e
            android.view.View r5 = r7.getChildAt(r4)
            boolean r6 = r5 instanceof androidx.appcompat.widget.Toolbar
            if (r6 != 0) goto L_0x004b
            boolean r6 = r5 instanceof android.widget.Toolbar
            if (r6 == 0) goto L_0x0048
            goto L_0x004b
        L_0x0048:
            int r4 = r4 + 1
            goto L_0x0039
        L_0x004b:
            r0 = r5
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
        L_0x004e:
            r7.toolbar = r0
        L_0x0050:
            boolean r0 = r7.collapsingTitleEnabled
            if (r0 != 0) goto L_0x0067
            android.view.View r0 = r7.dummyView
            if (r0 == 0) goto L_0x0067
            android.view.ViewParent r0 = r0.getParent()
            boolean r1 = r0 instanceof android.view.ViewGroup
            if (r1 == 0) goto L_0x0067
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            android.view.View r1 = r7.dummyView
            r0.removeView(r1)
        L_0x0067:
            boolean r0 = r7.collapsingTitleEnabled
            if (r0 == 0) goto L_0x008d
            android.view.ViewGroup r0 = r7.toolbar
            if (r0 == 0) goto L_0x008d
            android.view.View r0 = r7.dummyView
            if (r0 != 0) goto L_0x007e
            android.view.View r0 = new android.view.View
            android.content.Context r1 = r7.getContext()
            r0.<init>(r1)
            r7.dummyView = r0
        L_0x007e:
            android.view.View r0 = r7.dummyView
            android.view.ViewParent r0 = r0.getParent()
            if (r0 != 0) goto L_0x008d
            android.view.ViewGroup r0 = r7.toolbar
            android.view.View r1 = r7.dummyView
            r0.addView(r1, r2, r2)
        L_0x008d:
            r7.refreshToolbar = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.ensureToolbar():void");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [android.view.ViewGroup$LayoutParams, com.google.android.material.appbar.CollapsingToolbarLayout$LayoutParams, android.widget.FrameLayout$LayoutParams] */
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        ? layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.collapseMode = 0;
        layoutParams.parallaxMult = 0.5f;
        return layoutParams;
    }

    public final int getScrimVisibleHeightTrigger() {
        int i;
        int i2 = this.scrimVisibleHeightTrigger;
        if (i2 >= 0) {
            return i2 + this.topInsetApplied + this.extraMultilineHeight;
        }
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            i = windowInsetsCompat.getSystemWindowInsetTop();
        } else {
            i = 0;
        }
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        int minimumHeight = getMinimumHeight();
        if (minimumHeight > 0) {
            return Math.min((minimumHeight * 2) + i, getHeight());
        }
        return getHeight() / 3;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            if (this.titleCollapseMode == 1) {
                appBarLayout.liftOnScroll = false;
            }
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            setFitsSystemWindows(appBarLayout.getFitsSystemWindows());
            if (this.onOffsetChangedListener == null) {
                this.onOffsetChangedListener = new OffsetUpdateListener();
            }
            OffsetUpdateListener offsetUpdateListener = this.onOffsetChangedListener;
            if (appBarLayout.listeners == null) {
                appBarLayout.listeners = new ArrayList();
            }
            if (offsetUpdateListener != null && !appBarLayout.listeners.contains(offsetUpdateListener)) {
                appBarLayout.listeners.add(offsetUpdateListener);
            }
            ViewCompat.Api20Impl.requestApplyInsets(this);
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.collapsingTextHelper.maybeUpdateFontWeightAdjustment(configuration);
    }

    public final void onDetachedFromWindow() {
        List list;
        ViewParent parent = getParent();
        OffsetUpdateListener offsetUpdateListener = this.onOffsetChangedListener;
        if (!(offsetUpdateListener == null || !(parent instanceof AppBarLayout) || (list = ((AppBarLayout) parent).listeners) == null || offsetUpdateListener == null)) {
            list.remove(offsetUpdateListener);
        }
        super.onDetachedFromWindow();
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                if (!childAt.getFitsSystemWindows() && childAt.getTop() < systemWindowInsetTop) {
                    childAt.offsetTopAndBottom(systemWindowInsetTop);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i6 = 0; i6 < childCount2; i6++) {
            ViewOffsetHelper viewOffsetHelper = getViewOffsetHelper(getChildAt(i6));
            View view = viewOffsetHelper.view;
            viewOffsetHelper.layoutTop = view.getTop();
            viewOffsetHelper.layoutLeft = view.getLeft();
        }
        updateTextBounds(false, i, i2, i3, i4);
        updateTitleFromToolbarIfNeeded();
        updateScrimVisibility();
        int childCount3 = getChildCount();
        for (int i7 = 0; i7 < childCount3; i7++) {
            getViewOffsetHelper(getChildAt(i7)).applyOffsets();
        }
    }

    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        ensureToolbar();
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            i3 = windowInsetsCompat.getSystemWindowInsetTop();
        } else {
            i3 = 0;
        }
        if ((mode == 0 || this.forceApplySystemWindowInsetTop) && i3 > 0) {
            this.topInsetApplied = i3;
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + i3, 1073741824));
        }
        if (this.extraMultilineHeightEnabled && this.collapsingTextHelper.maxLines > 1) {
            updateTitleFromToolbarIfNeeded();
            updateTextBounds(true, 0, 0, getMeasuredWidth(), getMeasuredHeight());
            CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
            int i6 = collapsingTextHelper2.expandedLineCount;
            if (i6 > 1) {
                TextPaint textPaint = collapsingTextHelper2.tmpPaint;
                textPaint.setTextSize(collapsingTextHelper2.expandedTextSize);
                textPaint.setTypeface(collapsingTextHelper2.expandedTypeface);
                textPaint.setLetterSpacing(collapsingTextHelper2.expandedLetterSpacing);
                int i7 = i6 - 1;
                this.extraMultilineHeight = i7 * Math.round(textPaint.descent() + (-textPaint.ascent()));
                super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + this.extraMultilineHeight, 1073741824));
            }
        }
        ViewGroup viewGroup = this.toolbar;
        if (viewGroup != null) {
            View view = this.toolbarDirectChild;
            if (view == null || view == this) {
                ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i4 = viewGroup.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                } else {
                    i4 = viewGroup.getMeasuredHeight();
                }
                setMinimumHeight(i4);
                return;
            }
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
                i5 = view.getMeasuredHeight() + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
            } else {
                i5 = view.getMeasuredHeight();
            }
            setMinimumHeight(i5);
        }
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Drawable drawable = this.contentScrim;
        if (drawable != null) {
            ViewGroup viewGroup = this.toolbar;
            if (this.titleCollapseMode == 1 && viewGroup != null && this.collapsingTitleEnabled) {
                i2 = viewGroup.getBottom();
            }
            drawable.setBounds(0, 0, i, i2);
        }
    }

    public final void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != drawable) {
            Drawable drawable3 = null;
            if (drawable2 != null) {
                drawable2.setCallback((Drawable.Callback) null);
            }
            if (drawable != null) {
                drawable3 = drawable.mutate();
            }
            this.contentScrim = drawable3;
            if (drawable3 != null) {
                int width = getWidth();
                int height = getHeight();
                ViewGroup viewGroup = this.toolbar;
                if (this.titleCollapseMode == 1 && viewGroup != null && this.collapsingTitleEnabled) {
                    height = viewGroup.getBottom();
                }
                drawable3.setBounds(0, 0, width, height);
                this.contentScrim.setCallback(this);
                this.contentScrim.setAlpha(this.scrimAlpha);
            }
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            postInvalidateOnAnimation();
        }
    }

    public final void setVisibility(int i) {
        boolean z;
        super.setVisibility(i);
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        Drawable drawable = this.statusBarScrim;
        if (!(drawable == null || drawable.isVisible() == z)) {
            this.statusBarScrim.setVisible(z, false);
        }
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != null && drawable2.isVisible() != z) {
            this.contentScrim.setVisible(z, false);
        }
    }

    public final void updateScrimVisibility() {
        boolean z;
        ViewGroup viewGroup;
        TimeInterpolator timeInterpolator;
        if (this.contentScrim != null || this.statusBarScrim != null) {
            int i = 0;
            boolean z2 = true;
            if (getHeight() + this.currentOffset < getScrimVisibleHeightTrigger()) {
                z = true;
            } else {
                z = false;
            }
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            if (!isLaidOut() || isInEditMode()) {
                z2 = false;
            }
            if (this.scrimsAreShown != z) {
                if (z2) {
                    if (z) {
                        i = 255;
                    }
                    ensureToolbar();
                    ValueAnimator valueAnimator = this.scrimAnimator;
                    if (valueAnimator == null) {
                        ValueAnimator valueAnimator2 = new ValueAnimator();
                        this.scrimAnimator = valueAnimator2;
                        if (i > this.scrimAlpha) {
                            timeInterpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
                        } else {
                            timeInterpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
                        }
                        valueAnimator2.setInterpolator(timeInterpolator);
                        this.scrimAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                ViewGroup viewGroup;
                                CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
                                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                if (intValue != collapsingToolbarLayout.scrimAlpha) {
                                    if (!(collapsingToolbarLayout.contentScrim == null || (viewGroup = collapsingToolbarLayout.toolbar) == null)) {
                                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                                        viewGroup.postInvalidateOnAnimation();
                                    }
                                    collapsingToolbarLayout.scrimAlpha = intValue;
                                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                                    collapsingToolbarLayout.postInvalidateOnAnimation();
                                }
                            }
                        });
                    } else if (valueAnimator.isRunning()) {
                        this.scrimAnimator.cancel();
                    }
                    this.scrimAnimator.setDuration(this.scrimAnimationDuration);
                    this.scrimAnimator.setIntValues(new int[]{this.scrimAlpha, i});
                    this.scrimAnimator.start();
                } else {
                    if (z) {
                        i = 255;
                    }
                    if (i != this.scrimAlpha) {
                        if (!(this.contentScrim == null || (viewGroup = this.toolbar) == null)) {
                            viewGroup.postInvalidateOnAnimation();
                        }
                        this.scrimAlpha = i;
                        postInvalidateOnAnimation();
                    }
                }
                this.scrimsAreShown = z;
            }
        }
    }

    public final void updateTextBounds(boolean z, int i, int i2, int i3, int i4) {
        View view;
        boolean z2;
        boolean z3;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z4 = z;
        if (this.collapsingTitleEnabled && (view = this.dummyView) != null) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            int i11 = 0;
            if (!view.isAttachedToWindow() || this.dummyView.getVisibility() != 0) {
                z2 = false;
            } else {
                z2 = true;
            }
            this.drawCollapsingTitle = z2;
            if (z2 || z4) {
                if (getLayoutDirection() == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                View view2 = this.toolbarDirectChild;
                if (view2 == null) {
                    view2 = this.toolbar;
                }
                int height = ((getHeight() - getViewOffsetHelper(view2).layoutTop) - view2.getHeight()) - ((LayoutParams) view2.getLayoutParams()).bottomMargin;
                DescendantOffsetUtils.getDescendantRect(this, this.dummyView, this.tmpRect);
                ViewGroup viewGroup = this.toolbar;
                if (viewGroup instanceof Toolbar) {
                    Toolbar toolbar2 = (Toolbar) viewGroup;
                    i11 = toolbar2.mTitleMarginStart;
                    i6 = toolbar2.mTitleMarginEnd;
                    i5 = toolbar2.mTitleMarginTop;
                    i7 = toolbar2.mTitleMarginBottom;
                } else if (viewGroup instanceof android.widget.Toolbar) {
                    android.widget.Toolbar toolbar3 = (android.widget.Toolbar) viewGroup;
                    i11 = toolbar3.getTitleMarginStart();
                    i6 = toolbar3.getTitleMarginEnd();
                    i5 = toolbar3.getTitleMarginTop();
                    i7 = toolbar3.getTitleMarginBottom();
                } else {
                    i7 = 0;
                    i6 = 0;
                    i5 = 0;
                }
                CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
                Rect rect = this.tmpRect;
                int i12 = rect.left;
                if (z3) {
                    i8 = i6;
                } else {
                    i8 = i11;
                }
                int i13 = i12 + i8;
                int i14 = rect.top + height + i5;
                int i15 = rect.right;
                if (!z3) {
                    i11 = i6;
                }
                int i16 = i15 - i11;
                int i17 = (rect.bottom + height) - i7;
                Rect rect2 = collapsingTextHelper2.collapsedBounds;
                if (!(rect2.left == i13 && rect2.top == i14 && rect2.right == i16 && rect2.bottom == i17)) {
                    rect2.set(i13, i14, i16, i17);
                    collapsingTextHelper2.boundsChanged = true;
                }
                CollapsingTextHelper collapsingTextHelper3 = this.collapsingTextHelper;
                if (z3) {
                    i9 = this.expandedMarginEnd;
                } else {
                    i9 = this.expandedMarginStart;
                }
                int i18 = this.tmpRect.top + this.expandedMarginTop;
                int i19 = i3 - i;
                if (z3) {
                    i10 = this.expandedMarginStart;
                } else {
                    i10 = this.expandedMarginEnd;
                }
                int i20 = i19 - i10;
                int i21 = (i4 - i2) - this.expandedMarginBottom;
                Rect rect3 = collapsingTextHelper3.expandedBounds;
                if (!(rect3.left == i9 && rect3.top == i18 && rect3.right == i20 && rect3.bottom == i21)) {
                    rect3.set(i9, i18, i20, i21);
                    collapsingTextHelper3.boundsChanged = true;
                }
                this.collapsingTextHelper.recalculate(z);
            }
        }
    }

    public final void updateTitleFromToolbarIfNeeded() {
        CharSequence charSequence;
        if (this.toolbar != null && this.collapsingTitleEnabled && TextUtils.isEmpty(this.collapsingTextHelper.text)) {
            ViewGroup viewGroup = this.toolbar;
            CharSequence charSequence2 = null;
            if (viewGroup instanceof Toolbar) {
                charSequence = ((Toolbar) viewGroup).mTitleText;
            } else if (viewGroup instanceof android.widget.Toolbar) {
                charSequence = ((android.widget.Toolbar) viewGroup).getTitle();
            } else {
                charSequence = null;
            }
            CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
            if (charSequence == null || !TextUtils.equals(collapsingTextHelper2.text, charSequence)) {
                collapsingTextHelper2.text = charSequence;
                collapsingTextHelper2.textToDraw = null;
                Bitmap bitmap = collapsingTextHelper2.expandedTitleTexture;
                if (bitmap != null) {
                    bitmap.recycle();
                    collapsingTextHelper2.expandedTitleTexture = null;
                }
                collapsingTextHelper2.recalculate(false);
            }
            if (this.collapsingTitleEnabled) {
                charSequence2 = this.collapsingTextHelper.text;
            }
            setContentDescription(charSequence2);
        }
    }

    public final boolean verifyDrawable(Drawable drawable) {
        if (super.verifyDrawable(drawable) || drawable == this.contentScrim || drawable == this.statusBarScrim) {
            return true;
        }
        return false;
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130968851);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.material.appbar.CollapsingToolbarLayout$LayoutParams, android.widget.FrameLayout$LayoutParams] */
    public final FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        ? layoutParams = new FrameLayout.LayoutParams(context, attributeSet);
        layoutParams.collapseMode = 0;
        layoutParams.parallaxMult = 0.5f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CollapsingToolbarLayout_Layout);
        layoutParams.collapseMode = obtainStyledAttributes.getInt(0, 0);
        layoutParams.parallaxMult = obtainStyledAttributes.getFloat(1, 0.5f);
        obtainStyledAttributes.recycle();
        return layoutParams;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CollapsingToolbarLayout(android.content.Context r17, android.util.AttributeSet r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r7 = r18
            r8 = r19
            r1 = 2132018530(0x7f140562, float:1.967537E38)
            r2 = r17
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r2, r7, r8, r1)
            r0.<init>(r1, r7, r8)
            r9 = 1
            r0.refreshToolbar = r9
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r0.tmpRect = r1
            r10 = -1
            r0.scrimVisibleHeightTrigger = r10
            r11 = 0
            r0.topInsetApplied = r11
            r0.extraMultilineHeight = r11
            android.content.Context r12 = r16.getContext()
            com.google.android.material.internal.CollapsingTextHelper r13 = new com.google.android.material.internal.CollapsingTextHelper
            r13.<init>(r0)
            r0.collapsingTextHelper = r13
            android.animation.TimeInterpolator r1 = com.google.android.material.animation.AnimationUtils.DECELERATE_INTERPOLATOR
            r13.textSizeInterpolator = r1
            r13.recalculate(r11)
            r13.isRtlTextDirectionHeuristicsEnabled = r11
            com.google.android.material.elevation.ElevationOverlayProvider r14 = new com.google.android.material.elevation.ElevationOverlayProvider
            r14.<init>(r12)
            int[] r15 = com.google.android.material.R$styleable.CollapsingToolbarLayout
            int[] r6 = new int[r11]
            r5 = 2132018530(0x7f140562, float:1.967537E38)
            com.google.android.material.internal.ThemeEnforcement.checkCompatibleTheme(r12, r7, r8, r5)
            r1 = r12
            r2 = r18
            r3 = r15
            r4 = r19
            r17 = r5
            com.google.android.material.internal.ThemeEnforcement.checkTextAppearance(r1, r2, r3, r4, r5, r6)
            r1 = r17
            android.content.res.TypedArray r1 = r12.obtainStyledAttributes(r7, r15, r8, r1)
            r2 = 4
            r3 = 8388691(0x800053, float:1.175506E-38)
            int r2 = r1.getInt(r2, r3)
            int r3 = r13.expandedTextGravity
            if (r3 == r2) goto L_0x0069
            r13.expandedTextGravity = r2
            r13.recalculate(r11)
        L_0x0069:
            r2 = 8388627(0x800013, float:1.175497E-38)
            int r2 = r1.getInt(r11, r2)
            int r3 = r13.collapsedTextGravity
            if (r3 == r2) goto L_0x0079
            r13.collapsedTextGravity = r2
            r13.recalculate(r11)
        L_0x0079:
            r2 = 5
            int r2 = r1.getDimensionPixelSize(r2, r11)
            r0.expandedMarginBottom = r2
            r0.expandedMarginEnd = r2
            r0.expandedMarginTop = r2
            r0.expandedMarginStart = r2
            r2 = 8
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x0094
            int r2 = r1.getDimensionPixelSize(r2, r11)
            r0.expandedMarginStart = r2
        L_0x0094:
            r2 = 7
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x00a1
            int r2 = r1.getDimensionPixelSize(r2, r11)
            r0.expandedMarginEnd = r2
        L_0x00a1:
            r2 = 9
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x00af
            int r2 = r1.getDimensionPixelSize(r2, r11)
            r0.expandedMarginTop = r2
        L_0x00af:
            r2 = 6
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x00bc
            int r2 = r1.getDimensionPixelSize(r2, r11)
            r0.expandedMarginBottom = r2
        L_0x00bc:
            r2 = 20
            boolean r2 = r1.getBoolean(r2, r9)
            r0.collapsingTitleEnabled = r2
            r2 = 18
            java.lang.CharSequence r2 = r1.getText(r2)
            r3 = 0
            if (r2 == 0) goto L_0x00d5
            java.lang.CharSequence r4 = r13.text
            boolean r4 = android.text.TextUtils.equals(r4, r2)
            if (r4 != 0) goto L_0x00e5
        L_0x00d5:
            r13.text = r2
            r13.textToDraw = r3
            android.graphics.Bitmap r2 = r13.expandedTitleTexture
            if (r2 == 0) goto L_0x00e2
            r2.recycle()
            r13.expandedTitleTexture = r3
        L_0x00e2:
            r13.recalculate(r11)
        L_0x00e5:
            boolean r2 = r0.collapsingTitleEnabled
            if (r2 == 0) goto L_0x00ec
            java.lang.CharSequence r2 = r13.text
            goto L_0x00ed
        L_0x00ec:
            r2 = r3
        L_0x00ed:
            r0.setContentDescription(r2)
            r2 = 2132017982(0x7f14033e, float:1.9674258E38)
            r13.setExpandedTextAppearance(r2)
            r2 = 2132017928(0x7f140308, float:1.9674148E38)
            r13.setCollapsedTextAppearance(r2)
            r2 = 10
            boolean r4 = r1.hasValue(r2)
            if (r4 == 0) goto L_0x010b
            int r2 = r1.getResourceId(r2, r11)
            r13.setExpandedTextAppearance(r2)
        L_0x010b:
            boolean r2 = r1.hasValue(r9)
            if (r2 == 0) goto L_0x0118
            int r2 = r1.getResourceId(r9, r11)
            r13.setCollapsedTextAppearance(r2)
        L_0x0118:
            r2 = 22
            boolean r4 = r1.hasValue(r2)
            r5 = 3
            if (r4 == 0) goto L_0x013b
            int r2 = r1.getInt(r2, r10)
            if (r2 == 0) goto L_0x0134
            if (r2 == r9) goto L_0x0131
            if (r2 == r5) goto L_0x012e
            android.text.TextUtils$TruncateAt r2 = android.text.TextUtils.TruncateAt.END
            goto L_0x0136
        L_0x012e:
            android.text.TextUtils$TruncateAt r2 = android.text.TextUtils.TruncateAt.MARQUEE
            goto L_0x0136
        L_0x0131:
            android.text.TextUtils$TruncateAt r2 = android.text.TextUtils.TruncateAt.MIDDLE
            goto L_0x0136
        L_0x0134:
            android.text.TextUtils$TruncateAt r2 = android.text.TextUtils.TruncateAt.START
        L_0x0136:
            r13.titleTextEllipsize = r2
            r13.recalculate(r11)
        L_0x013b:
            r2 = 11
            boolean r4 = r1.hasValue(r2)
            if (r4 == 0) goto L_0x0150
            android.content.res.ColorStateList r2 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r12, (android.content.res.TypedArray) r1, (int) r2)
            android.content.res.ColorStateList r4 = r13.expandedTextColor
            if (r4 == r2) goto L_0x0150
            r13.expandedTextColor = r2
            r13.recalculate(r11)
        L_0x0150:
            r2 = 2
            boolean r4 = r1.hasValue(r2)
            if (r4 == 0) goto L_0x015e
            android.content.res.ColorStateList r2 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r12, (android.content.res.TypedArray) r1, (int) r2)
            r13.setCollapsedTextColor(r2)
        L_0x015e:
            r2 = 16
            int r2 = r1.getDimensionPixelSize(r2, r10)
            r0.scrimVisibleHeightTrigger = r2
            r2 = 14
            boolean r4 = r1.hasValue(r2)
            if (r4 == 0) goto L_0x0184
            int r2 = r1.getInt(r2, r9)
            int r4 = r13.maxLines
            if (r2 == r4) goto L_0x0184
            r13.maxLines = r2
            android.graphics.Bitmap r2 = r13.expandedTitleTexture
            if (r2 == 0) goto L_0x0181
            r2.recycle()
            r13.expandedTitleTexture = r3
        L_0x0181:
            r13.recalculate(r11)
        L_0x0184:
            r2 = 21
            boolean r4 = r1.hasValue(r2)
            if (r4 == 0) goto L_0x0199
            int r2 = r1.getResourceId(r2, r11)
            android.view.animation.Interpolator r2 = android.view.animation.AnimationUtils.loadInterpolator(r12, r2)
            r13.positionInterpolator = r2
            r13.recalculate(r11)
        L_0x0199:
            r2 = 15
            r4 = 600(0x258, float:8.41E-43)
            int r2 = r1.getInt(r2, r4)
            long r6 = (long) r2
            r0.scrimAnimationDuration = r6
            android.graphics.drawable.Drawable r2 = r1.getDrawable(r5)
            r0.setContentScrim(r2)
            r2 = 17
            android.graphics.drawable.Drawable r2 = r1.getDrawable(r2)
            android.graphics.drawable.Drawable r4 = r0.statusBarScrim
            if (r4 == r2) goto L_0x01fd
            if (r4 == 0) goto L_0x01ba
            r4.setCallback(r3)
        L_0x01ba:
            if (r2 == 0) goto L_0x01c0
            android.graphics.drawable.Drawable r3 = r2.mutate()
        L_0x01c0:
            r0.statusBarScrim = r3
            if (r3 == 0) goto L_0x01f8
            boolean r2 = r3.isStateful()
            if (r2 == 0) goto L_0x01d3
            android.graphics.drawable.Drawable r2 = r0.statusBarScrim
            int[] r3 = r16.getDrawableState()
            r2.setState(r3)
        L_0x01d3:
            android.graphics.drawable.Drawable r2 = r0.statusBarScrim
            java.util.WeakHashMap r3 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            int r3 = r16.getLayoutDirection()
            r2.setLayoutDirection(r3)
            android.graphics.drawable.Drawable r2 = r0.statusBarScrim
            int r3 = r16.getVisibility()
            if (r3 != 0) goto L_0x01e8
            r3 = r9
            goto L_0x01e9
        L_0x01e8:
            r3 = r11
        L_0x01e9:
            r2.setVisible(r3, r11)
            android.graphics.drawable.Drawable r2 = r0.statusBarScrim
            r2.setCallback(r0)
            android.graphics.drawable.Drawable r2 = r0.statusBarScrim
            int r3 = r0.scrimAlpha
            r2.setAlpha(r3)
        L_0x01f8:
            java.util.WeakHashMap r2 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            r16.postInvalidateOnAnimation()
        L_0x01fd:
            r2 = 19
            int r2 = r1.getInt(r2, r11)
            r0.titleCollapseMode = r2
            if (r2 != r9) goto L_0x0209
            r2 = r9
            goto L_0x020a
        L_0x0209:
            r2 = r11
        L_0x020a:
            r13.fadeModeEnabled = r2
            android.view.ViewParent r3 = r16.getParent()
            boolean r4 = r3 instanceof com.google.android.material.appbar.AppBarLayout
            if (r4 == 0) goto L_0x021c
            com.google.android.material.appbar.AppBarLayout r3 = (com.google.android.material.appbar.AppBarLayout) r3
            int r4 = r0.titleCollapseMode
            if (r4 != r9) goto L_0x021c
            r3.liftOnScroll = r11
        L_0x021c:
            if (r2 == 0) goto L_0x023b
            android.graphics.drawable.Drawable r2 = r0.contentScrim
            if (r2 != 0) goto L_0x023b
            android.content.res.Resources r2 = r16.getResources()
            r3 = 2131165734(0x7f070226, float:1.7945693E38)
            float r2 = r2.getDimension(r3)
            int r3 = r14.colorSurface
            int r2 = r14.compositeOverlayIfNeeded(r3, r2)
            android.graphics.drawable.ColorDrawable r3 = new android.graphics.drawable.ColorDrawable
            r3.<init>(r2)
            r0.setContentScrim(r3)
        L_0x023b:
            r2 = 23
            int r2 = r1.getResourceId(r2, r10)
            r0.toolbarId = r2
            r2 = 13
            boolean r2 = r1.getBoolean(r2, r11)
            r0.forceApplySystemWindowInsetTop = r2
            r2 = 12
            boolean r2 = r1.getBoolean(r2, r11)
            r0.extraMultilineHeightEnabled = r2
            r1.recycle()
            r0.setWillNotDraw(r11)
            com.google.android.material.appbar.CollapsingToolbarLayout$1 r1 = new com.google.android.material.appbar.CollapsingToolbarLayout$1
            r1.<init>()
            java.util.WeakHashMap r2 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.material.appbar.CollapsingToolbarLayout$LayoutParams, android.widget.FrameLayout$LayoutParams] */
    /* renamed from: generateDefaultLayoutParams  reason: collision with other method in class */
    public final FrameLayout.LayoutParams m911generateDefaultLayoutParams() {
        ? layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.collapseMode = 0;
        layoutParams.parallaxMult = 0.5f;
        return layoutParams;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [android.view.ViewGroup$LayoutParams, com.google.android.material.appbar.CollapsingToolbarLayout$LayoutParams, android.widget.FrameLayout$LayoutParams] */
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ? layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.collapseMode = 0;
        layoutParams2.parallaxMult = 0.5f;
        return layoutParams2;
    }
}
