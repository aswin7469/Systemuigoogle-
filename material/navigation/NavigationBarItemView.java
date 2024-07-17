package com.google.android.material.navigation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.ripple.RippleUtils;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class NavigationBarItemView extends FrameLayout implements MenuView.ItemView {
    public static final ActiveIndicatorTransform ACTIVE_INDICATOR_LABELED_TRANSFORM = new Object();
    public static final ActiveIndicatorUnlabeledTransform ACTIVE_INDICATOR_UNLABELED_TRANSFORM = new Object();
    public static final int[] CHECKED_STATE_SET = {16842912};
    public ValueAnimator activeIndicatorAnimator;
    public int activeIndicatorDesiredHeight = 0;
    public int activeIndicatorDesiredWidth = 0;
    public boolean activeIndicatorEnabled = false;
    public int activeIndicatorMarginHorizontal = 0;
    public float activeIndicatorProgress = 0.0f;
    public boolean activeIndicatorResizeable = false;
    public ActiveIndicatorTransform activeIndicatorTransform = ACTIVE_INDICATOR_LABELED_TRANSFORM;
    public final View activeIndicatorView;
    public BadgeDrawable badgeDrawable;
    public final ImageView icon;
    public final FrameLayout iconContainer;
    public ColorStateList iconTint;
    public boolean initialized = false;
    public boolean isShifting;
    public Drawable itemBackground;
    public MenuItemImpl itemData;
    public int itemPaddingBottom;
    public int itemPaddingTop;
    public ColorStateList itemRippleColor;
    public final ViewGroup labelGroup;
    public int labelVisibilityMode;
    public final TextView largeLabel;
    public Drawable originalIconDrawable;
    public float scaleDownFactor;
    public float scaleUpFactor;
    public float shiftAmount;
    public final TextView smallLabel;
    public Drawable wrappedIconDrawable;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public class ActiveIndicatorTransform {
        public float calculateScaleY(float f, float f2) {
            return 1.0f;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ActiveIndicatorUnlabeledTransform extends ActiveIndicatorTransform {
        public final float calculateScaleY(float f, float f2) {
            return AnimationUtils.lerp(0.4f, 1.0f, f);
        }
    }

    public NavigationBarItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(getItemLayoutResId(), this, true);
        this.iconContainer = (FrameLayout) findViewById(2131363151);
        this.activeIndicatorView = findViewById(2131363150);
        ImageView imageView = (ImageView) findViewById(2131363152);
        this.icon = imageView;
        ViewGroup viewGroup = (ViewGroup) findViewById(2131363153);
        this.labelGroup = viewGroup;
        TextView textView = (TextView) findViewById(2131363155);
        this.smallLabel = textView;
        TextView textView2 = (TextView) findViewById(2131363154);
        this.largeLabel = textView2;
        setBackgroundResource(2131233301);
        this.itemPaddingTop = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        this.itemPaddingBottom = viewGroup.getPaddingBottom();
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api16Impl.setImportantForAccessibility(textView, 2);
        ViewCompat.Api16Impl.setImportantForAccessibility(textView2, 2);
        setFocusable(true);
        calculateTextScaleFactors(textView.getTextSize(), textView2.getTextSize());
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    if (NavigationBarItemView.this.icon.getVisibility() == 0) {
                        NavigationBarItemView navigationBarItemView = NavigationBarItemView.this;
                        ImageView imageView = navigationBarItemView.icon;
                        BadgeDrawable badgeDrawable = navigationBarItemView.badgeDrawable;
                        if (badgeDrawable != null) {
                            Rect rect = new Rect();
                            imageView.getDrawingRect(rect);
                            badgeDrawable.setBounds(rect);
                            badgeDrawable.updateBadgeCoordinates(imageView, (FrameLayout) null);
                        }
                    }
                }
            });
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setTextAppearanceWithoutFontScaling(int r4, android.widget.TextView r5) {
        /*
            r5.setTextAppearance(r4)
            android.content.Context r0 = r5.getContext()
            r1 = 0
            if (r4 != 0) goto L_0x000c
        L_0x000a:
            r4 = r1
            goto L_0x004c
        L_0x000c:
            int[] r2 = com.google.android.material.R$styleable.TextAppearance
            android.content.res.TypedArray r4 = r0.obtainStyledAttributes(r4, r2)
            android.util.TypedValue r2 = new android.util.TypedValue
            r2.<init>()
            boolean r3 = r4.getValue(r1, r2)
            r4.recycle()
            if (r3 != 0) goto L_0x0021
            goto L_0x000a
        L_0x0021:
            int r4 = r2.getComplexUnit()
            r3 = 2
            if (r4 != r3) goto L_0x003e
            int r4 = r2.data
            float r4 = android.util.TypedValue.complexToFloat(r4)
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            float r0 = r0.density
            float r4 = r4 * r0
            int r4 = java.lang.Math.round(r4)
            goto L_0x004c
        L_0x003e:
            int r4 = r2.data
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            int r4 = android.util.TypedValue.complexToDimensionPixelSize(r4, r0)
        L_0x004c:
            if (r4 == 0) goto L_0x0052
            float r4 = (float) r4
            r5.setTextSize(r1, r4)
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.setTextAppearanceWithoutFontScaling(int, android.widget.TextView):void");
    }

    public static void setViewScaleValues(float f, float f2, int i, View view) {
        view.setScaleX(f);
        view.setScaleY(f2);
        view.setVisibility(i);
    }

    public static void setViewTopMarginAndGravity(View view, int i, int i2) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i;
        layoutParams.bottomMargin = i;
        layoutParams.gravity = i2;
        view.setLayoutParams(layoutParams);
    }

    public static void updateViewPaddingBottom(View view, int i) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i);
    }

    public final void calculateTextScaleFactors(float f, float f2) {
        this.shiftAmount = f - f2;
        this.scaleUpFactor = (f2 * 1.0f) / f;
        this.scaleDownFactor = (f * 1.0f) / f2;
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        FrameLayout frameLayout = this.iconContainer;
        if (frameLayout != null && this.activeIndicatorEnabled) {
            frameLayout.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final View getIconOrContainer() {
        FrameLayout frameLayout = this.iconContainer;
        if (frameLayout != null) {
            return frameLayout;
        }
        return this.icon;
    }

    public final MenuItemImpl getItemData() {
        return this.itemData;
    }

    public abstract int getItemDefaultMarginResId();

    public abstract int getItemLayoutResId();

    public final int getSuggestedMinimumHeight() {
        int i;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.labelGroup.getLayoutParams();
        BadgeDrawable badgeDrawable2 = this.badgeDrawable;
        if (badgeDrawable2 != null) {
            i = badgeDrawable2.getMinimumHeight() / 2;
        } else {
            i = 0;
        }
        int max = Math.max(i, ((FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams()).topMargin);
        return this.labelGroup.getMeasuredHeight() + this.icon.getMeasuredWidth() + max + i + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public final int getSuggestedMinimumWidth() {
        int i;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.labelGroup.getLayoutParams();
        int measuredWidth = this.labelGroup.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
        BadgeDrawable badgeDrawable2 = this.badgeDrawable;
        if (badgeDrawable2 == null) {
            i = 0;
        } else {
            i = badgeDrawable2.getMinimumWidth() - this.badgeDrawable.state.currentState.horizontalOffsetWithoutText.intValue();
        }
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams();
        int max = Math.max(i, layoutParams2.leftMargin);
        return Math.max(Math.max(i, layoutParams2.rightMargin) + this.icon.getMeasuredWidth() + max, measuredWidth);
    }

    public final void initialize(MenuItemImpl menuItemImpl) {
        CharSequence charSequence;
        int i;
        this.itemData = menuItemImpl;
        menuItemImpl.getClass();
        refreshDrawableState();
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        Drawable icon2 = menuItemImpl.getIcon();
        if (icon2 != this.originalIconDrawable) {
            this.originalIconDrawable = icon2;
            if (icon2 != null) {
                Drawable.ConstantState constantState = icon2.getConstantState();
                if (constantState != null) {
                    icon2 = constantState.newDrawable();
                }
                icon2 = icon2.mutate();
                this.wrappedIconDrawable = icon2;
                ColorStateList colorStateList = this.iconTint;
                if (colorStateList != null) {
                    icon2.setTintList(colorStateList);
                }
            }
            this.icon.setImageDrawable(icon2);
        }
        CharSequence charSequence2 = menuItemImpl.mTitle;
        this.smallLabel.setText(charSequence2);
        this.largeLabel.setText(charSequence2);
        MenuItemImpl menuItemImpl2 = this.itemData;
        if (menuItemImpl2 == null || TextUtils.isEmpty(menuItemImpl2.mContentDescription)) {
            setContentDescription(charSequence2);
        }
        MenuItemImpl menuItemImpl3 = this.itemData;
        if (menuItemImpl3 != null && !TextUtils.isEmpty(menuItemImpl3.mTooltipText)) {
            charSequence2 = this.itemData.mTooltipText;
        }
        setTooltipText(charSequence2);
        setId(menuItemImpl.mId);
        if (!TextUtils.isEmpty(menuItemImpl.mContentDescription)) {
            setContentDescription(menuItemImpl.mContentDescription);
        }
        if (!TextUtils.isEmpty(menuItemImpl.mTooltipText)) {
            charSequence = menuItemImpl.mTooltipText;
        } else {
            charSequence = menuItemImpl.mTitle;
        }
        setTooltipText(charSequence);
        if (menuItemImpl.isVisible()) {
            i = 0;
        } else {
            i = 8;
        }
        setVisibility(i);
        this.initialized = true;
    }

    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.itemData.isChecked()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        Context context;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        BadgeDrawable badgeDrawable2 = this.badgeDrawable;
        if (badgeDrawable2 != null && badgeDrawable2.isVisible()) {
            MenuItemImpl menuItemImpl = this.itemData;
            CharSequence charSequence = menuItemImpl.mTitle;
            if (!TextUtils.isEmpty(menuItemImpl.mContentDescription)) {
                charSequence = this.itemData.mContentDescription;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(charSequence);
            sb.append(", ");
            BadgeDrawable badgeDrawable3 = this.badgeDrawable;
            Object obj = null;
            if (badgeDrawable3.isVisible()) {
                if (!badgeDrawable3.hasNumber()) {
                    obj = badgeDrawable3.state.currentState.contentDescriptionNumberless;
                } else if (!(badgeDrawable3.state.currentState.contentDescriptionQuantityStrings == 0 || (context = (Context) badgeDrawable3.contextRef.get()) == null)) {
                    int number = badgeDrawable3.getNumber();
                    int i = badgeDrawable3.maxBadgeNumber;
                    obj = number <= i ? context.getResources().getQuantityString(badgeDrawable3.state.currentState.contentDescriptionQuantityStrings, badgeDrawable3.getNumber(), new Object[]{Integer.valueOf(badgeDrawable3.getNumber())}) : context.getString(badgeDrawable3.state.currentState.contentDescriptionExceedsMaxBadgeNumberRes, new Object[]{Integer.valueOf(i)});
                }
            }
            sb.append(obj);
            accessibilityNodeInfo.setContentDescription(sb.toString());
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        int indexOfChild = viewGroup.indexOfChild(this);
        int i2 = 0;
        for (int i3 = 0; i3 < indexOfChild; i3++) {
            View childAt = viewGroup.getChildAt(i3);
            if ((childAt instanceof NavigationBarItemView) && childAt.getVisibility() == 0) {
                i2++;
            }
        }
        accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(0, 1, i2, 1, false, isSelected()).mInfo);
        if (isSelected()) {
            accessibilityNodeInfo.setClickable(false);
            accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK.mAction);
        }
        accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getResources().getString(2131952704));
    }

    public final void onSizeChanged(final int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new Runnable() {
            public final void run() {
                NavigationBarItemView.this.updateActiveIndicatorLayoutParams(i);
            }
        });
    }

    public final void refreshItemBackground() {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3 = this.itemBackground;
        RippleDrawable rippleDrawable = null;
        boolean z = true;
        if (this.itemRippleColor != null) {
            View view = this.activeIndicatorView;
            if (view == null) {
                drawable = null;
            } else {
                drawable = view.getBackground();
            }
            if (this.activeIndicatorEnabled) {
                View view2 = this.activeIndicatorView;
                if (view2 == null) {
                    drawable2 = null;
                } else {
                    drawable2 = view2.getBackground();
                }
                if (!(drawable2 == null || this.iconContainer == null || drawable == null)) {
                    rippleDrawable = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.itemRippleColor), (Drawable) null, drawable);
                    z = false;
                }
            }
            if (drawable3 == null) {
                drawable3 = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(this.itemRippleColor), (Drawable) null, (Drawable) null);
            }
        }
        FrameLayout frameLayout = this.iconContainer;
        if (frameLayout != null) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api16Impl.setBackground(frameLayout, rippleDrawable);
        }
        WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api16Impl.setBackground(this, drawable3);
        setDefaultFocusHighlightEnabled(z);
    }

    public final void setActiveIndicatorProgress(float f, float f2) {
        float f3;
        float f4;
        View view = this.activeIndicatorView;
        if (view != null) {
            ActiveIndicatorTransform activeIndicatorTransform2 = this.activeIndicatorTransform;
            activeIndicatorTransform2.getClass();
            view.setScaleX(AnimationUtils.lerp(0.4f, 1.0f, f));
            view.setScaleY(activeIndicatorTransform2.calculateScaleY(f, f2));
            int i = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
            if (i == 0) {
                f3 = 0.8f;
            } else {
                f3 = 0.0f;
            }
            if (i == 0) {
                f4 = 1.0f;
            } else {
                f4 = 0.2f;
            }
            view.setAlpha(AnimationUtils.lerp(0.0f, 1.0f, f3, f4, f));
        }
        this.activeIndicatorProgress = f;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.widget.FrameLayout} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setBadge(com.google.android.material.badge.BadgeDrawable r5) {
        /*
            r4 = this;
            com.google.android.material.badge.BadgeDrawable r0 = r4.badgeDrawable
            if (r0 != r5) goto L_0x0005
            return
        L_0x0005:
            r1 = 0
            if (r0 == 0) goto L_0x004e
            android.widget.ImageView r0 = r4.icon
            if (r0 == 0) goto L_0x004e
            java.lang.String r0 = "NavigationBar"
            java.lang.String r2 = "Multiple badges shouldn't be attached to one item."
            android.util.Log.w(r0, r2)
            android.widget.ImageView r0 = r4.icon
            com.google.android.material.badge.BadgeDrawable r2 = r4.badgeDrawable
            if (r2 == 0) goto L_0x004e
            if (r0 == 0) goto L_0x004c
            r2 = 1
            r4.setClipChildren(r2)
            r4.setClipToPadding(r2)
            com.google.android.material.badge.BadgeDrawable r2 = r4.badgeDrawable
            if (r2 != 0) goto L_0x0027
            goto L_0x004c
        L_0x0027:
            java.lang.ref.WeakReference r3 = r2.customBadgeParentRef
            if (r3 == 0) goto L_0x0032
            java.lang.Object r3 = r3.get()
            android.widget.FrameLayout r3 = (android.widget.FrameLayout) r3
            goto L_0x0033
        L_0x0032:
            r3 = r1
        L_0x0033:
            if (r3 == 0) goto L_0x0045
            java.lang.ref.WeakReference r0 = r2.customBadgeParentRef
            if (r0 == 0) goto L_0x0040
            java.lang.Object r0 = r0.get()
            android.widget.FrameLayout r0 = (android.widget.FrameLayout) r0
            goto L_0x0041
        L_0x0040:
            r0 = r1
        L_0x0041:
            r0.setForeground(r1)
            goto L_0x004c
        L_0x0045:
            android.view.ViewOverlay r0 = r0.getOverlay()
            r0.remove(r2)
        L_0x004c:
            r4.badgeDrawable = r1
        L_0x004e:
            r4.badgeDrawable = r5
            android.widget.ImageView r0 = r4.icon
            if (r0 == 0) goto L_0x0091
            if (r5 == 0) goto L_0x0091
            r5 = 0
            r4.setClipChildren(r5)
            r4.setClipToPadding(r5)
            com.google.android.material.badge.BadgeDrawable r4 = r4.badgeDrawable
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            r0.getDrawingRect(r5)
            r4.setBounds(r5)
            r4.updateBadgeCoordinates(r0, r1)
            java.lang.ref.WeakReference r5 = r4.customBadgeParentRef
            if (r5 == 0) goto L_0x0078
            java.lang.Object r5 = r5.get()
            android.widget.FrameLayout r5 = (android.widget.FrameLayout) r5
            goto L_0x0079
        L_0x0078:
            r5 = r1
        L_0x0079:
            if (r5 == 0) goto L_0x008a
            java.lang.ref.WeakReference r5 = r4.customBadgeParentRef
            if (r5 == 0) goto L_0x0086
            java.lang.Object r5 = r5.get()
            r1 = r5
            android.widget.FrameLayout r1 = (android.widget.FrameLayout) r1
        L_0x0086:
            r1.setForeground(r4)
            goto L_0x0091
        L_0x008a:
            android.view.ViewOverlay r5 = r0.getOverlay()
            r5.add(r4)
        L_0x0091:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.setBadge(com.google.android.material.badge.BadgeDrawable):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0132  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setChecked(boolean r10) {
        /*
            r9 = this;
            r0 = 1
            r1 = 0
            android.widget.TextView r2 = r9.largeLabel
            int r3 = r2.getWidth()
            r4 = 2
            int r3 = r3 / r4
            float r3 = (float) r3
            r2.setPivotX(r3)
            android.widget.TextView r2 = r9.largeLabel
            int r3 = r2.getBaseline()
            float r3 = (float) r3
            r2.setPivotY(r3)
            android.widget.TextView r2 = r9.smallLabel
            int r3 = r2.getWidth()
            int r3 = r3 / r4
            float r3 = (float) r3
            r2.setPivotX(r3)
            android.widget.TextView r2 = r9.smallLabel
            int r3 = r2.getBaseline()
            float r3 = (float) r3
            r2.setPivotY(r3)
            r2 = 1065353216(0x3f800000, float:1.0)
            if (r10 == 0) goto L_0x0033
            r3 = r2
            goto L_0x0034
        L_0x0033:
            r3 = 0
        L_0x0034:
            boolean r5 = r9.activeIndicatorEnabled
            if (r5 == 0) goto L_0x0099
            boolean r5 = r9.initialized
            if (r5 == 0) goto L_0x0099
            java.util.WeakHashMap r5 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            boolean r5 = androidx.core.view.ViewCompat.Api19Impl.isAttachedToWindow(r9)
            if (r5 != 0) goto L_0x0045
            goto L_0x0099
        L_0x0045:
            android.animation.ValueAnimator r5 = r9.activeIndicatorAnimator
            if (r5 == 0) goto L_0x004f
            r5.cancel()
            r5 = 0
            r9.activeIndicatorAnimator = r5
        L_0x004f:
            float r5 = r9.activeIndicatorProgress
            float[] r6 = new float[r4]
            r6[r1] = r5
            r6[r0] = r3
            android.animation.ValueAnimator r5 = android.animation.ValueAnimator.ofFloat(r6)
            r9.activeIndicatorAnimator = r5
            com.google.android.material.navigation.NavigationBarItemView$3 r6 = new com.google.android.material.navigation.NavigationBarItemView$3
            r6.<init>(r3)
            r5.addUpdateListener(r6)
            android.animation.ValueAnimator r3 = r9.activeIndicatorAnimator
            android.content.Context r5 = r9.getContext()
            androidx.interpolator.view.animation.FastOutSlowInInterpolator r6 = com.google.android.material.animation.AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR
            r7 = 2130969626(0x7f04041a, float:1.754794E38)
            android.animation.TimeInterpolator r5 = com.google.android.material.motion.MotionUtils.resolveThemeInterpolator(r5, r7, r6)
            r3.setInterpolator(r5)
            android.animation.ValueAnimator r3 = r9.activeIndicatorAnimator
            android.content.Context r5 = r9.getContext()
            android.content.res.Resources r6 = r9.getResources()
            r7 = 2131427511(0x7f0b00b7, float:1.847664E38)
            int r6 = r6.getInteger(r7)
            r7 = 2130969610(0x7f04040a, float:1.7547907E38)
            int r5 = com.google.android.material.motion.MotionUtils.resolveThemeDuration(r5, r7, r6)
            long r5 = (long) r5
            r3.setDuration(r5)
            android.animation.ValueAnimator r3 = r9.activeIndicatorAnimator
            r3.start()
            goto L_0x009c
        L_0x0099:
            r9.setActiveIndicatorProgress(r3, r3)
        L_0x009c:
            int r3 = r9.labelVisibilityMode
            r5 = -1
            r6 = 17
            r7 = 49
            r8 = 4
            if (r3 == r5) goto L_0x0132
            if (r3 == 0) goto L_0x0101
            if (r3 == r0) goto L_0x00c5
            if (r3 == r4) goto L_0x00ae
            goto L_0x01a0
        L_0x00ae:
            android.view.View r0 = r9.getIconOrContainer()
            int r1 = r9.itemPaddingTop
            setViewTopMarginAndGravity(r0, r1, r6)
            android.widget.TextView r0 = r9.largeLabel
            r1 = 8
            r0.setVisibility(r1)
            android.widget.TextView r0 = r9.smallLabel
            r0.setVisibility(r1)
            goto L_0x01a0
        L_0x00c5:
            android.view.ViewGroup r0 = r9.labelGroup
            int r3 = r9.itemPaddingBottom
            updateViewPaddingBottom(r0, r3)
            if (r10 == 0) goto L_0x00ea
            android.view.View r0 = r9.getIconOrContainer()
            int r3 = r9.itemPaddingTop
            float r3 = (float) r3
            float r4 = r9.shiftAmount
            float r3 = r3 + r4
            int r3 = (int) r3
            setViewTopMarginAndGravity(r0, r3, r7)
            android.widget.TextView r0 = r9.largeLabel
            setViewScaleValues(r2, r2, r1, r0)
            android.widget.TextView r0 = r9.smallLabel
            float r1 = r9.scaleUpFactor
            setViewScaleValues(r1, r1, r8, r0)
            goto L_0x01a0
        L_0x00ea:
            android.view.View r0 = r9.getIconOrContainer()
            int r3 = r9.itemPaddingTop
            setViewTopMarginAndGravity(r0, r3, r7)
            android.widget.TextView r0 = r9.largeLabel
            float r3 = r9.scaleDownFactor
            setViewScaleValues(r3, r3, r8, r0)
            android.widget.TextView r0 = r9.smallLabel
            setViewScaleValues(r2, r2, r1, r0)
            goto L_0x01a0
        L_0x0101:
            if (r10 == 0) goto L_0x0119
            android.view.View r0 = r9.getIconOrContainer()
            int r2 = r9.itemPaddingTop
            setViewTopMarginAndGravity(r0, r2, r7)
            android.view.ViewGroup r0 = r9.labelGroup
            int r2 = r9.itemPaddingBottom
            updateViewPaddingBottom(r0, r2)
            android.widget.TextView r0 = r9.largeLabel
            r0.setVisibility(r1)
            goto L_0x012c
        L_0x0119:
            android.view.View r0 = r9.getIconOrContainer()
            int r2 = r9.itemPaddingTop
            setViewTopMarginAndGravity(r0, r2, r6)
            android.view.ViewGroup r0 = r9.labelGroup
            updateViewPaddingBottom(r0, r1)
            android.widget.TextView r0 = r9.largeLabel
            r0.setVisibility(r8)
        L_0x012c:
            android.widget.TextView r0 = r9.smallLabel
            r0.setVisibility(r8)
            goto L_0x01a0
        L_0x0132:
            boolean r0 = r9.isShifting
            if (r0 == 0) goto L_0x0167
            if (r10 == 0) goto L_0x014e
            android.view.View r0 = r9.getIconOrContainer()
            int r2 = r9.itemPaddingTop
            setViewTopMarginAndGravity(r0, r2, r7)
            android.view.ViewGroup r0 = r9.labelGroup
            int r2 = r9.itemPaddingBottom
            updateViewPaddingBottom(r0, r2)
            android.widget.TextView r0 = r9.largeLabel
            r0.setVisibility(r1)
            goto L_0x0161
        L_0x014e:
            android.view.View r0 = r9.getIconOrContainer()
            int r2 = r9.itemPaddingTop
            setViewTopMarginAndGravity(r0, r2, r6)
            android.view.ViewGroup r0 = r9.labelGroup
            updateViewPaddingBottom(r0, r1)
            android.widget.TextView r0 = r9.largeLabel
            r0.setVisibility(r8)
        L_0x0161:
            android.widget.TextView r0 = r9.smallLabel
            r0.setVisibility(r8)
            goto L_0x01a0
        L_0x0167:
            android.view.ViewGroup r0 = r9.labelGroup
            int r3 = r9.itemPaddingBottom
            updateViewPaddingBottom(r0, r3)
            if (r10 == 0) goto L_0x018b
            android.view.View r0 = r9.getIconOrContainer()
            int r3 = r9.itemPaddingTop
            float r3 = (float) r3
            float r4 = r9.shiftAmount
            float r3 = r3 + r4
            int r3 = (int) r3
            setViewTopMarginAndGravity(r0, r3, r7)
            android.widget.TextView r0 = r9.largeLabel
            setViewScaleValues(r2, r2, r1, r0)
            android.widget.TextView r0 = r9.smallLabel
            float r1 = r9.scaleUpFactor
            setViewScaleValues(r1, r1, r8, r0)
            goto L_0x01a0
        L_0x018b:
            android.view.View r0 = r9.getIconOrContainer()
            int r3 = r9.itemPaddingTop
            setViewTopMarginAndGravity(r0, r3, r7)
            android.widget.TextView r0 = r9.largeLabel
            float r3 = r9.scaleDownFactor
            setViewScaleValues(r3, r3, r8, r0)
            android.widget.TextView r0 = r9.smallLabel
            setViewScaleValues(r2, r2, r1, r0)
        L_0x01a0:
            r9.refreshDrawableState()
            r9.setSelected(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.setChecked(boolean):void");
    }

    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        this.smallLabel.setEnabled(z);
        this.largeLabel.setEnabled(z);
        this.icon.setEnabled(z);
        if (z) {
            PointerIcon systemIcon = PointerIcon.getSystemIcon(getContext(), 1002);
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api24Impl.setPointerIcon(this, systemIcon);
            return;
        }
        WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api24Impl.setPointerIcon(this, (PointerIcon) null);
    }

    public final void setLabelVisibilityMode(int i) {
        if (this.labelVisibilityMode != i) {
            this.labelVisibilityMode = i;
            if (!this.activeIndicatorResizeable || i != 2) {
                this.activeIndicatorTransform = ACTIVE_INDICATOR_LABELED_TRANSFORM;
            } else {
                this.activeIndicatorTransform = ACTIVE_INDICATOR_UNLABELED_TRANSFORM;
            }
            updateActiveIndicatorLayoutParams(getWidth());
            MenuItemImpl menuItemImpl = this.itemData;
            if (menuItemImpl != null) {
                setChecked(menuItemImpl.isChecked());
            }
        }
    }

    public final void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.smallLabel.setTextColor(colorStateList);
            this.largeLabel.setTextColor(colorStateList);
        }
    }

    public final void updateActiveIndicatorLayoutParams(int i) {
        int i2;
        if (this.activeIndicatorView != null) {
            int min = Math.min(this.activeIndicatorDesiredWidth, i - (this.activeIndicatorMarginHorizontal * 2));
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.activeIndicatorView.getLayoutParams();
            if (!this.activeIndicatorResizeable || this.labelVisibilityMode != 2) {
                i2 = this.activeIndicatorDesiredHeight;
            } else {
                i2 = min;
            }
            layoutParams.height = i2;
            layoutParams.width = min;
            this.activeIndicatorView.setLayoutParams(layoutParams);
        }
    }
}
