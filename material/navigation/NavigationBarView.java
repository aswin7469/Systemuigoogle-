package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class NavigationBarView extends FrameLayout {
    public final NavigationBarMenu menu;
    public final SupportMenuInflater menuInflater;
    public final NavigationBarMenuView menuView;
    public final NavigationBarPresenter presenter;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new Object();
        public Bundle menuPresenterState;

        /* renamed from: com.google.android.material.navigation.NavigationBarView$SavedState$1  reason: invalid class name */
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
            this.menuPresenterState = parcel.readBundle(classLoader == null ? SavedState.class.getClassLoader() : classLoader);
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.menuPresenterState);
        }
    }

    /* JADX WARNING: type inference failed for: r12v2, types: [androidx.appcompat.view.menu.MenuPresenter, java.lang.Object, com.google.android.material.navigation.NavigationBarPresenter] */
    public NavigationBarView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, i2), attributeSet, i);
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        ? obj = new Object();
        obj.updateSuspended = false;
        this.presenter = obj;
        Context context2 = getContext();
        TintTypedArray obtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attributeSet, R$styleable.NavigationBarView, i, i2, 10, 9);
        NavigationBarMenu navigationBarMenu = new NavigationBarMenu(context2, getClass(), getMaxItemCount());
        this.menu = navigationBarMenu;
        NavigationBarMenuView createNavigationBarMenuView = createNavigationBarMenuView(context2);
        this.menuView = createNavigationBarMenuView;
        obj.menuView = createNavigationBarMenuView;
        obj.id = 1;
        createNavigationBarMenuView.presenter = obj;
        navigationBarMenu.addMenuPresenter(obj, navigationBarMenu.mContext);
        getContext();
        obj.menuView.menu = navigationBarMenu;
        TypedArray typedArray = obtainTintedStyledAttributes.mWrapped;
        if (typedArray.hasValue(5)) {
            ColorStateList colorStateList = obtainTintedStyledAttributes.getColorStateList(5);
            createNavigationBarMenuView.itemIconTint = colorStateList;
            NavigationBarItemView[] navigationBarItemViewArr = createNavigationBarMenuView.buttons;
            if (navigationBarItemViewArr != null) {
                for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                    navigationBarItemView.iconTint = colorStateList;
                    if (!(navigationBarItemView.itemData == null || (drawable3 = navigationBarItemView.wrappedIconDrawable) == null)) {
                        drawable3.setTintList(colorStateList);
                        navigationBarItemView.wrappedIconDrawable.invalidateSelf();
                    }
                }
            }
        } else {
            ColorStateList createDefaultColorStateList = createNavigationBarMenuView.createDefaultColorStateList();
            createNavigationBarMenuView.itemIconTint = createDefaultColorStateList;
            NavigationBarItemView[] navigationBarItemViewArr2 = createNavigationBarMenuView.buttons;
            if (navigationBarItemViewArr2 != null) {
                for (NavigationBarItemView navigationBarItemView2 : navigationBarItemViewArr2) {
                    navigationBarItemView2.iconTint = createDefaultColorStateList;
                    if (!(navigationBarItemView2.itemData == null || (drawable2 = navigationBarItemView2.wrappedIconDrawable) == null)) {
                        drawable2.setTintList(createDefaultColorStateList);
                        navigationBarItemView2.wrappedIconDrawable.invalidateSelf();
                    }
                }
            }
        }
        int dimensionPixelSize = typedArray.getDimensionPixelSize(4, getResources().getDimensionPixelSize(2131166845));
        createNavigationBarMenuView.itemIconSize = dimensionPixelSize;
        NavigationBarItemView[] navigationBarItemViewArr3 = createNavigationBarMenuView.buttons;
        if (navigationBarItemViewArr3 != null) {
            for (NavigationBarItemView navigationBarItemView3 : navigationBarItemViewArr3) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) navigationBarItemView3.icon.getLayoutParams();
                layoutParams.width = dimensionPixelSize;
                layoutParams.height = dimensionPixelSize;
                navigationBarItemView3.icon.setLayoutParams(layoutParams);
            }
        }
        if (typedArray.hasValue(10)) {
            int resourceId = typedArray.getResourceId(10, 0);
            NavigationBarMenuView navigationBarMenuView = this.menuView;
            navigationBarMenuView.itemTextAppearanceInactive = resourceId;
            NavigationBarItemView[] navigationBarItemViewArr4 = navigationBarMenuView.buttons;
            if (navigationBarItemViewArr4 != null) {
                for (NavigationBarItemView navigationBarItemView4 : navigationBarItemViewArr4) {
                    NavigationBarItemView.setTextAppearanceWithoutFontScaling(resourceId, navigationBarItemView4.smallLabel);
                    navigationBarItemView4.calculateTextScaleFactors(navigationBarItemView4.smallLabel.getTextSize(), navigationBarItemView4.largeLabel.getTextSize());
                    ColorStateList colorStateList2 = navigationBarMenuView.itemTextColorFromUser;
                    if (colorStateList2 != null) {
                        navigationBarItemView4.setTextColor(colorStateList2);
                    }
                }
            }
        }
        if (typedArray.hasValue(9)) {
            int resourceId2 = typedArray.getResourceId(9, 0);
            NavigationBarMenuView navigationBarMenuView2 = this.menuView;
            navigationBarMenuView2.itemTextAppearanceActive = resourceId2;
            NavigationBarItemView[] navigationBarItemViewArr5 = navigationBarMenuView2.buttons;
            if (navigationBarItemViewArr5 != null) {
                for (NavigationBarItemView navigationBarItemView5 : navigationBarItemViewArr5) {
                    NavigationBarItemView.setTextAppearanceWithoutFontScaling(resourceId2, navigationBarItemView5.largeLabel);
                    navigationBarItemView5.calculateTextScaleFactors(navigationBarItemView5.smallLabel.getTextSize(), navigationBarItemView5.largeLabel.getTextSize());
                    ColorStateList colorStateList3 = navigationBarMenuView2.itemTextColorFromUser;
                    if (colorStateList3 != null) {
                        navigationBarItemView5.setTextColor(colorStateList3);
                    }
                }
            }
        }
        if (typedArray.hasValue(11)) {
            ColorStateList colorStateList4 = obtainTintedStyledAttributes.getColorStateList(11);
            NavigationBarMenuView navigationBarMenuView3 = this.menuView;
            navigationBarMenuView3.itemTextColorFromUser = colorStateList4;
            NavigationBarItemView[] navigationBarItemViewArr6 = navigationBarMenuView3.buttons;
            if (navigationBarItemViewArr6 != null) {
                for (NavigationBarItemView textColor : navigationBarItemViewArr6) {
                    textColor.setTextColor(colorStateList4);
                }
            }
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            Drawable background = getBackground();
            if (background instanceof ColorDrawable) {
                materialShapeDrawable.setFillColor(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
            }
            materialShapeDrawable.initializeElevationOverlay(context2);
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api16Impl.setBackground(this, materialShapeDrawable);
        }
        if (typedArray.hasValue(7)) {
            int dimensionPixelSize2 = typedArray.getDimensionPixelSize(7, 0);
            NavigationBarMenuView navigationBarMenuView4 = this.menuView;
            navigationBarMenuView4.itemPaddingTop = dimensionPixelSize2;
            NavigationBarItemView[] navigationBarItemViewArr7 = navigationBarMenuView4.buttons;
            if (navigationBarItemViewArr7 != null) {
                for (NavigationBarItemView navigationBarItemView6 : navigationBarItemViewArr7) {
                    if (navigationBarItemView6.itemPaddingTop != dimensionPixelSize2) {
                        navigationBarItemView6.itemPaddingTop = dimensionPixelSize2;
                        MenuItemImpl menuItemImpl = navigationBarItemView6.itemData;
                        if (menuItemImpl != null) {
                            navigationBarItemView6.setChecked(menuItemImpl.isChecked());
                        }
                    }
                }
            }
        }
        if (typedArray.hasValue(6)) {
            int dimensionPixelSize3 = typedArray.getDimensionPixelSize(6, 0);
            NavigationBarMenuView navigationBarMenuView5 = this.menuView;
            navigationBarMenuView5.itemPaddingBottom = dimensionPixelSize3;
            NavigationBarItemView[] navigationBarItemViewArr8 = navigationBarMenuView5.buttons;
            if (navigationBarItemViewArr8 != null) {
                for (NavigationBarItemView navigationBarItemView7 : navigationBarItemViewArr8) {
                    if (navigationBarItemView7.itemPaddingBottom != dimensionPixelSize3) {
                        navigationBarItemView7.itemPaddingBottom = dimensionPixelSize3;
                        MenuItemImpl menuItemImpl2 = navigationBarItemView7.itemData;
                        if (menuItemImpl2 != null) {
                            navigationBarItemView7.setChecked(menuItemImpl2.isChecked());
                        }
                    }
                }
            }
        }
        if (typedArray.hasValue(1)) {
            setElevation((float) typedArray.getDimensionPixelSize(1, 0));
        }
        getBackground().mutate().setTintList(MaterialResources.getColorStateList(context2, obtainTintedStyledAttributes, 0));
        int integer = typedArray.getInteger(12, -1);
        NavigationBarMenuView navigationBarMenuView6 = this.menuView;
        if (navigationBarMenuView6.labelVisibilityMode != integer) {
            navigationBarMenuView6.labelVisibilityMode = integer;
            this.presenter.updateMenuView(false);
        }
        int resourceId3 = typedArray.getResourceId(3, 0);
        if (resourceId3 != 0) {
            NavigationBarMenuView navigationBarMenuView7 = this.menuView;
            navigationBarMenuView7.itemBackgroundRes = resourceId3;
            NavigationBarItemView[] navigationBarItemViewArr9 = navigationBarMenuView7.buttons;
            if (navigationBarItemViewArr9 != null) {
                for (NavigationBarItemView navigationBarItemView8 : navigationBarItemViewArr9) {
                    if (resourceId3 == 0) {
                        drawable = null;
                    } else {
                        Context context3 = navigationBarItemView8.getContext();
                        Object obj2 = ContextCompat.sLock;
                        drawable = context3.getDrawable(resourceId3);
                    }
                    if (drawable != null) {
                        navigationBarItemView8.getClass();
                        if (drawable.getConstantState() != null) {
                            drawable = drawable.getConstantState().newDrawable().mutate();
                        }
                    }
                    navigationBarItemView8.itemBackground = drawable;
                    navigationBarItemView8.refreshItemBackground();
                }
            }
        } else {
            ColorStateList colorStateList5 = MaterialResources.getColorStateList(context2, obtainTintedStyledAttributes, 8);
            NavigationBarMenuView navigationBarMenuView8 = this.menuView;
            navigationBarMenuView8.itemRippleColor = colorStateList5;
            NavigationBarItemView[] navigationBarItemViewArr10 = navigationBarMenuView8.buttons;
            if (navigationBarItemViewArr10 != null) {
                for (NavigationBarItemView navigationBarItemView9 : navigationBarItemViewArr10) {
                    navigationBarItemView9.itemRippleColor = colorStateList5;
                    navigationBarItemView9.refreshItemBackground();
                }
            }
        }
        int resourceId4 = typedArray.getResourceId(2, 0);
        if (resourceId4 != 0) {
            NavigationBarMenuView navigationBarMenuView9 = this.menuView;
            navigationBarMenuView9.itemActiveIndicatorEnabled = true;
            NavigationBarItemView[] navigationBarItemViewArr11 = navigationBarMenuView9.buttons;
            if (navigationBarItemViewArr11 != null) {
                for (NavigationBarItemView navigationBarItemView10 : navigationBarItemViewArr11) {
                    navigationBarItemView10.activeIndicatorEnabled = true;
                    navigationBarItemView10.refreshItemBackground();
                    View view = navigationBarItemView10.activeIndicatorView;
                    if (view != null) {
                        view.setVisibility(0);
                        navigationBarItemView10.requestLayout();
                    }
                }
            }
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(resourceId4, R$styleable.NavigationBarActiveIndicator);
            int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(1, 0);
            NavigationBarMenuView navigationBarMenuView10 = this.menuView;
            navigationBarMenuView10.itemActiveIndicatorWidth = dimensionPixelSize4;
            NavigationBarItemView[] navigationBarItemViewArr12 = navigationBarMenuView10.buttons;
            if (navigationBarItemViewArr12 != null) {
                for (NavigationBarItemView navigationBarItemView11 : navigationBarItemViewArr12) {
                    navigationBarItemView11.activeIndicatorDesiredWidth = dimensionPixelSize4;
                    navigationBarItemView11.updateActiveIndicatorLayoutParams(navigationBarItemView11.getWidth());
                }
            }
            int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            NavigationBarMenuView navigationBarMenuView11 = this.menuView;
            navigationBarMenuView11.itemActiveIndicatorHeight = dimensionPixelSize5;
            NavigationBarItemView[] navigationBarItemViewArr13 = navigationBarMenuView11.buttons;
            if (navigationBarItemViewArr13 != null) {
                for (NavigationBarItemView navigationBarItemView12 : navigationBarItemViewArr13) {
                    navigationBarItemView12.activeIndicatorDesiredHeight = dimensionPixelSize5;
                    navigationBarItemView12.updateActiveIndicatorLayoutParams(navigationBarItemView12.getWidth());
                }
            }
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(3, 0);
            NavigationBarMenuView navigationBarMenuView12 = this.menuView;
            navigationBarMenuView12.itemActiveIndicatorMarginHorizontal = dimensionPixelOffset;
            NavigationBarItemView[] navigationBarItemViewArr14 = navigationBarMenuView12.buttons;
            if (navigationBarItemViewArr14 != null) {
                for (NavigationBarItemView navigationBarItemView13 : navigationBarItemViewArr14) {
                    navigationBarItemView13.activeIndicatorMarginHorizontal = dimensionPixelOffset;
                    navigationBarItemView13.updateActiveIndicatorLayoutParams(navigationBarItemView13.getWidth());
                }
            }
            ColorStateList colorStateList6 = MaterialResources.getColorStateList(context2, obtainStyledAttributes, 2);
            NavigationBarMenuView navigationBarMenuView13 = this.menuView;
            navigationBarMenuView13.itemActiveIndicatorColor = colorStateList6;
            NavigationBarItemView[] navigationBarItemViewArr15 = navigationBarMenuView13.buttons;
            if (navigationBarItemViewArr15 != null) {
                for (NavigationBarItemView navigationBarItemView14 : navigationBarItemViewArr15) {
                    MaterialShapeDrawable createItemActiveIndicatorDrawable = navigationBarMenuView13.createItemActiveIndicatorDrawable();
                    View view2 = navigationBarItemView14.activeIndicatorView;
                    if (view2 != null) {
                        view2.setBackgroundDrawable(createItemActiveIndicatorDrawable);
                        navigationBarItemView14.refreshItemBackground();
                    }
                }
            }
            ShapeAppearanceModel build = ShapeAppearanceModel.builder(context2, obtainStyledAttributes.getResourceId(4, 0), 0, (CornerSize) new AbsoluteCornerSize((float) 0)).build();
            NavigationBarMenuView navigationBarMenuView14 = this.menuView;
            navigationBarMenuView14.itemActiveIndicatorShapeAppearance = build;
            NavigationBarItemView[] navigationBarItemViewArr16 = navigationBarMenuView14.buttons;
            if (navigationBarItemViewArr16 != null) {
                for (NavigationBarItemView navigationBarItemView15 : navigationBarItemViewArr16) {
                    MaterialShapeDrawable createItemActiveIndicatorDrawable2 = navigationBarMenuView14.createItemActiveIndicatorDrawable();
                    View view3 = navigationBarItemView15.activeIndicatorView;
                    if (view3 != null) {
                        view3.setBackgroundDrawable(createItemActiveIndicatorDrawable2);
                        navigationBarItemView15.refreshItemBackground();
                    }
                }
            }
            obtainStyledAttributes.recycle();
        }
        if (typedArray.hasValue(13)) {
            int resourceId5 = typedArray.getResourceId(13, 0);
            this.presenter.updateSuspended = true;
            if (this.menuInflater == null) {
                this.menuInflater = new SupportMenuInflater(getContext());
            }
            this.menuInflater.inflate(resourceId5, this.menu);
            NavigationBarPresenter navigationBarPresenter = this.presenter;
            navigationBarPresenter.updateSuspended = false;
            navigationBarPresenter.updateMenuView(true);
        }
        obtainTintedStyledAttributes.recycle();
        addView(this.menuView);
        this.menu.mCallback = new MenuBuilder.Callback() {
            public final boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
                NavigationBarView navigationBarView = NavigationBarView.this;
                navigationBarView.getClass();
                navigationBarView.getClass();
                return false;
            }

            public final void onMenuModeChange(MenuBuilder menuBuilder) {
            }
        };
    }

    public abstract NavigationBarMenuView createNavigationBarMenuView(Context context);

    public abstract int getMaxItemCount();

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        this.menu.restorePresenterStates(savedState.menuPresenterState);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.os.Parcelable, com.google.android.material.navigation.NavigationBarView$SavedState, androidx.customview.view.AbsSavedState] */
    public final Parcelable onSaveInstanceState() {
        ? absSavedState = new AbsSavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        absSavedState.menuPresenterState = bundle;
        this.menu.savePresenterStates(bundle);
        return absSavedState;
    }

    public final void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeUtils.setElevation(this, f);
    }
}
