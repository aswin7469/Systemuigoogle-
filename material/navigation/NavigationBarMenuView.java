package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class NavigationBarMenuView extends ViewGroup implements MenuView {
    public static final int[] CHECKED_STATE_SET = {16842912};
    public static final int[] DISABLED_STATE_SET = {-16842910};
    public final SparseArray badgeDrawables = new SparseArray(5);
    public NavigationBarItemView[] buttons;
    public ColorStateList itemActiveIndicatorColor;
    public boolean itemActiveIndicatorEnabled;
    public int itemActiveIndicatorHeight;
    public int itemActiveIndicatorMarginHorizontal;
    public boolean itemActiveIndicatorResizeable = false;
    public ShapeAppearanceModel itemActiveIndicatorShapeAppearance;
    public int itemActiveIndicatorWidth;
    public int itemBackgroundRes;
    public int itemIconSize;
    public ColorStateList itemIconTint;
    public int itemPaddingBottom = -1;
    public int itemPaddingTop = -1;
    public final Pools$SynchronizedPool itemPool = new Pools$SynchronizedPool(5);
    public ColorStateList itemRippleColor;
    public int itemTextAppearanceActive;
    public int itemTextAppearanceInactive;
    public final ColorStateList itemTextColorDefault = createDefaultColorStateList();
    public ColorStateList itemTextColorFromUser;
    public int labelVisibilityMode;
    public MenuBuilder menu;
    public final AnonymousClass1 onClickListener;
    public final SparseArray onTouchListeners = new SparseArray(5);
    public NavigationBarPresenter presenter;
    public int selectedItemId = 0;
    public int selectedItemPosition = 0;
    public final AutoTransition set;

    public NavigationBarMenuView(Context context) {
        super(context);
        if (isInEditMode()) {
            this.set = null;
        } else {
            AutoTransition autoTransition = new AutoTransition();
            this.set = autoTransition;
            autoTransition.setOrdering(0);
            autoTransition.setDuration((long) MotionUtils.resolveThemeDuration(getContext(), 2130969609, getResources().getInteger(2131427511)));
            autoTransition.setInterpolator(MotionUtils.resolveThemeInterpolator(getContext(), 2130969629, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            autoTransition.addTransition(new Transition());
        }
        this.onClickListener = new View.OnClickListener() {
            public final void onClick(View view) {
                MenuItemImpl menuItemImpl = ((NavigationBarItemView) view).itemData;
                NavigationBarMenuView navigationBarMenuView = NavigationBarMenuView.this;
                if (!navigationBarMenuView.menu.performItemAction(menuItemImpl, navigationBarMenuView.presenter, 0)) {
                    menuItemImpl.setChecked(true);
                }
            }
        };
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api16Impl.setImportantForAccessibility(this, 1);
    }

    public static boolean isShifting(int i, int i2) {
        if (i == -1) {
            if (i2 <= 3) {
                return false;
            }
        } else if (i != 0) {
            return false;
        }
        return true;
    }

    public final void buildMenuView() {
        Drawable drawable;
        BadgeDrawable badgeDrawable;
        int i;
        Drawable drawable2;
        FrameLayout frameLayout;
        FrameLayout frameLayout2;
        removeAllViews();
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView != null) {
                    this.itemPool.release(navigationBarItemView);
                    ImageView imageView = navigationBarItemView.icon;
                    if (navigationBarItemView.badgeDrawable != null) {
                        if (imageView != null) {
                            navigationBarItemView.setClipChildren(true);
                            navigationBarItemView.setClipToPadding(true);
                            BadgeDrawable badgeDrawable2 = navigationBarItemView.badgeDrawable;
                            if (badgeDrawable2 != null) {
                                WeakReference weakReference = badgeDrawable2.customBadgeParentRef;
                                if (weakReference != null) {
                                    frameLayout = (FrameLayout) weakReference.get();
                                } else {
                                    frameLayout = null;
                                }
                                if (frameLayout != null) {
                                    WeakReference weakReference2 = badgeDrawable2.customBadgeParentRef;
                                    if (weakReference2 != null) {
                                        frameLayout2 = (FrameLayout) weakReference2.get();
                                    } else {
                                        frameLayout2 = null;
                                    }
                                    frameLayout2.setForeground((Drawable) null);
                                } else {
                                    imageView.getOverlay().remove(badgeDrawable2);
                                }
                            }
                        }
                        navigationBarItemView.badgeDrawable = null;
                    }
                    navigationBarItemView.itemData = null;
                    navigationBarItemView.activeIndicatorProgress = 0.0f;
                    navigationBarItemView.initialized = false;
                }
            }
        }
        if (this.menu.mItems.size() == 0) {
            this.selectedItemId = 0;
            this.selectedItemPosition = 0;
            this.buttons = null;
            return;
        }
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < this.menu.mItems.size(); i2++) {
            hashSet.add(Integer.valueOf(this.menu.getItem(i2).getItemId()));
        }
        for (int i3 = 0; i3 < this.badgeDrawables.size(); i3++) {
            int keyAt = this.badgeDrawables.keyAt(i3);
            if (!hashSet.contains(Integer.valueOf(keyAt))) {
                this.badgeDrawables.delete(keyAt);
            }
        }
        this.buttons = new NavigationBarItemView[this.menu.mItems.size()];
        boolean isShifting = isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
        for (int i4 = 0; i4 < this.menu.mItems.size(); i4++) {
            this.presenter.updateSuspended = true;
            this.menu.getItem(i4).setCheckable(true);
            this.presenter.updateSuspended = false;
            NavigationBarItemView navigationBarItemView2 = (NavigationBarItemView) this.itemPool.acquire();
            if (navigationBarItemView2 == null) {
                navigationBarItemView2 = createNavigationBarItemView(getContext());
            }
            this.buttons[i4] = navigationBarItemView2;
            ColorStateList colorStateList = this.itemIconTint;
            navigationBarItemView2.iconTint = colorStateList;
            if (!(navigationBarItemView2.itemData == null || (drawable2 = navigationBarItemView2.wrappedIconDrawable) == null)) {
                drawable2.setTintList(colorStateList);
                navigationBarItemView2.wrappedIconDrawable.invalidateSelf();
            }
            int i5 = this.itemIconSize;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) navigationBarItemView2.icon.getLayoutParams();
            layoutParams.width = i5;
            layoutParams.height = i5;
            navigationBarItemView2.icon.setLayoutParams(layoutParams);
            navigationBarItemView2.setTextColor(this.itemTextColorDefault);
            NavigationBarItemView.setTextAppearanceWithoutFontScaling(this.itemTextAppearanceInactive, navigationBarItemView2.smallLabel);
            navigationBarItemView2.calculateTextScaleFactors(navigationBarItemView2.smallLabel.getTextSize(), navigationBarItemView2.largeLabel.getTextSize());
            NavigationBarItemView.setTextAppearanceWithoutFontScaling(this.itemTextAppearanceActive, navigationBarItemView2.largeLabel);
            navigationBarItemView2.calculateTextScaleFactors(navigationBarItemView2.smallLabel.getTextSize(), navigationBarItemView2.largeLabel.getTextSize());
            navigationBarItemView2.setTextColor(this.itemTextColorFromUser);
            int i6 = this.itemPaddingTop;
            if (!(i6 == -1 || navigationBarItemView2.itemPaddingTop == i6)) {
                navigationBarItemView2.itemPaddingTop = i6;
                MenuItemImpl menuItemImpl = navigationBarItemView2.itemData;
                if (menuItemImpl != null) {
                    navigationBarItemView2.setChecked(menuItemImpl.isChecked());
                }
            }
            int i7 = this.itemPaddingBottom;
            if (!(i7 == -1 || navigationBarItemView2.itemPaddingBottom == i7)) {
                navigationBarItemView2.itemPaddingBottom = i7;
                MenuItemImpl menuItemImpl2 = navigationBarItemView2.itemData;
                if (menuItemImpl2 != null) {
                    navigationBarItemView2.setChecked(menuItemImpl2.isChecked());
                }
            }
            navigationBarItemView2.activeIndicatorDesiredWidth = this.itemActiveIndicatorWidth;
            navigationBarItemView2.updateActiveIndicatorLayoutParams(navigationBarItemView2.getWidth());
            navigationBarItemView2.activeIndicatorDesiredHeight = this.itemActiveIndicatorHeight;
            navigationBarItemView2.updateActiveIndicatorLayoutParams(navigationBarItemView2.getWidth());
            navigationBarItemView2.activeIndicatorMarginHorizontal = this.itemActiveIndicatorMarginHorizontal;
            navigationBarItemView2.updateActiveIndicatorLayoutParams(navigationBarItemView2.getWidth());
            MaterialShapeDrawable createItemActiveIndicatorDrawable = createItemActiveIndicatorDrawable();
            View view = navigationBarItemView2.activeIndicatorView;
            if (view != null) {
                view.setBackgroundDrawable(createItemActiveIndicatorDrawable);
                navigationBarItemView2.refreshItemBackground();
            }
            navigationBarItemView2.activeIndicatorResizeable = this.itemActiveIndicatorResizeable;
            boolean z = this.itemActiveIndicatorEnabled;
            navigationBarItemView2.activeIndicatorEnabled = z;
            navigationBarItemView2.refreshItemBackground();
            View view2 = navigationBarItemView2.activeIndicatorView;
            if (view2 != null) {
                if (z) {
                    i = 0;
                } else {
                    i = 8;
                }
                view2.setVisibility(i);
                navigationBarItemView2.requestLayout();
            }
            int i8 = this.itemBackgroundRes;
            if (i8 == 0) {
                drawable = null;
            } else {
                Context context = navigationBarItemView2.getContext();
                Object obj = ContextCompat.sLock;
                drawable = context.getDrawable(i8);
            }
            if (!(drawable == null || drawable.getConstantState() == null)) {
                drawable = drawable.getConstantState().newDrawable().mutate();
            }
            navigationBarItemView2.itemBackground = drawable;
            navigationBarItemView2.refreshItemBackground();
            navigationBarItemView2.itemRippleColor = this.itemRippleColor;
            navigationBarItemView2.refreshItemBackground();
            if (navigationBarItemView2.isShifting != isShifting) {
                navigationBarItemView2.isShifting = isShifting;
                MenuItemImpl menuItemImpl3 = navigationBarItemView2.itemData;
                if (menuItemImpl3 != null) {
                    navigationBarItemView2.setChecked(menuItemImpl3.isChecked());
                }
            }
            navigationBarItemView2.setLabelVisibilityMode(this.labelVisibilityMode);
            MenuItemImpl menuItemImpl4 = (MenuItemImpl) this.menu.getItem(i4);
            navigationBarItemView2.initialize(menuItemImpl4);
            int i9 = menuItemImpl4.mId;
            navigationBarItemView2.setOnTouchListener((View.OnTouchListener) this.onTouchListeners.get(i9));
            navigationBarItemView2.setOnClickListener(this.onClickListener);
            int i10 = this.selectedItemId;
            if (i10 != 0 && i9 == i10) {
                this.selectedItemPosition = i4;
            }
            int id = navigationBarItemView2.getId();
            if (!(id == -1 || (badgeDrawable = (BadgeDrawable) this.badgeDrawables.get(id)) == null)) {
                navigationBarItemView2.setBadge(badgeDrawable);
            }
            addView(navigationBarItemView2);
        }
        int min = Math.min(this.menu.mItems.size() - 1, this.selectedItemPosition);
        this.selectedItemPosition = min;
        this.menu.getItem(min).setChecked(true);
    }

    public final ColorStateList createDefaultColorStateList() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(16842808, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = ContextCompat.getColorStateList(typedValue.resourceId, getContext());
        if (!getContext().getTheme().resolveAttribute(2130968878, typedValue, true)) {
            return null;
        }
        int i = typedValue.data;
        int defaultColor = colorStateList.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(iArr, defaultColor), i, defaultColor});
    }

    public final MaterialShapeDrawable createItemActiveIndicatorDrawable() {
        if (this.itemActiveIndicatorShapeAppearance == null || this.itemActiveIndicatorColor == null) {
            return null;
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.itemActiveIndicatorShapeAppearance);
        materialShapeDrawable.setFillColor(this.itemActiveIndicatorColor);
        return materialShapeDrawable;
    }

    public abstract NavigationBarItemView createNavigationBarItemView(Context context);

    public final void initialize(MenuBuilder menuBuilder) {
        this.menu = menuBuilder;
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.menu.getVisibleItems().size(), 1).mInfo);
    }
}
