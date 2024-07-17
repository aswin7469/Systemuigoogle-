package com.google.android.material.navigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.MenuItem;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.internal.ParcelableSparseArray;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NavigationBarPresenter implements MenuPresenter {
    public int id;
    public NavigationBarMenuView menuView;
    public boolean updateSuspended;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Object();
        public ParcelableSparseArray badgeSavedStates;
        public int selectedItemId;

        /* renamed from: com.google.android.material.navigation.NavigationBarPresenter$SavedState$1  reason: invalid class name */
        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class AnonymousClass1 implements Parcelable.Creator {
            /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, com.google.android.material.navigation.NavigationBarPresenter$SavedState] */
            public final Object createFromParcel(Parcel parcel) {
                ? obj = new Object();
                obj.selectedItemId = parcel.readInt();
                obj.badgeSavedStates = (ParcelableSparseArray) parcel.readParcelable(SavedState.class.getClassLoader());
                return obj;
            }

            public final Object[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.selectedItemId);
            parcel.writeParcelable(this.badgeSavedStates, 0);
        }
    }

    public final boolean collapseItemActionView(MenuItemImpl menuItemImpl) {
        return false;
    }

    public final boolean expandItemActionView(MenuItemImpl menuItemImpl) {
        return false;
    }

    public final boolean flagActionItems() {
        return false;
    }

    public final int getId() {
        return this.id;
    }

    public final void initForMenu(Context context, MenuBuilder menuBuilder) {
        this.menuView.menu = menuBuilder;
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            NavigationBarMenuView navigationBarMenuView = this.menuView;
            SavedState savedState = (SavedState) parcelable;
            int i = savedState.selectedItemId;
            int size = navigationBarMenuView.menu.mItems.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                MenuItem item = navigationBarMenuView.menu.getItem(i2);
                if (i == item.getItemId()) {
                    navigationBarMenuView.selectedItemId = i;
                    navigationBarMenuView.selectedItemPosition = i2;
                    item.setChecked(true);
                    break;
                }
                i2++;
            }
            Context context = this.menuView.getContext();
            ParcelableSparseArray parcelableSparseArray = savedState.badgeSavedStates;
            SparseArray sparseArray = new SparseArray(parcelableSparseArray.size());
            int i3 = 0;
            while (i3 < parcelableSparseArray.size()) {
                int keyAt = parcelableSparseArray.keyAt(i3);
                BadgeState.State state = (BadgeState.State) parcelableSparseArray.valueAt(i3);
                if (state != null) {
                    sparseArray.put(keyAt, new BadgeDrawable(context, state));
                    i3++;
                } else {
                    throw new IllegalArgumentException("BadgeDrawable's savedState cannot be null");
                }
            }
            NavigationBarMenuView navigationBarMenuView2 = this.menuView;
            navigationBarMenuView2.getClass();
            for (int i4 = 0; i4 < sparseArray.size(); i4++) {
                int keyAt2 = sparseArray.keyAt(i4);
                if (navigationBarMenuView2.badgeDrawables.indexOfKey(keyAt2) < 0) {
                    navigationBarMenuView2.badgeDrawables.append(keyAt2, (BadgeDrawable) sparseArray.get(keyAt2));
                }
            }
            NavigationBarItemView[] navigationBarItemViewArr = navigationBarMenuView2.buttons;
            if (navigationBarItemViewArr != null) {
                for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                    navigationBarItemView.setBadge((BadgeDrawable) navigationBarMenuView2.badgeDrawables.get(navigationBarItemView.getId()));
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.os.Parcelable, java.lang.Object, com.google.android.material.navigation.NavigationBarPresenter$SavedState] */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.util.SparseArray, com.google.android.material.internal.ParcelableSparseArray] */
    public final Parcelable onSaveInstanceState() {
        ? obj = new Object();
        NavigationBarMenuView navigationBarMenuView = this.menuView;
        obj.selectedItemId = navigationBarMenuView.selectedItemId;
        SparseArray sparseArray = navigationBarMenuView.badgeDrawables;
        ? sparseArray2 = new SparseArray();
        int i = 0;
        while (i < sparseArray.size()) {
            int keyAt = sparseArray.keyAt(i);
            BadgeDrawable badgeDrawable = (BadgeDrawable) sparseArray.valueAt(i);
            if (badgeDrawable != null) {
                sparseArray2.put(keyAt, badgeDrawable.state.overridingState);
                i++;
            } else {
                throw new IllegalArgumentException("badgeDrawable cannot be null");
            }
        }
        obj.badgeSavedStates = sparseArray2;
        return obj;
    }

    public final boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    public final void updateMenuView(boolean z) {
        AutoTransition autoTransition;
        if (!this.updateSuspended) {
            if (z) {
                this.menuView.buildMenuView();
                return;
            }
            NavigationBarMenuView navigationBarMenuView = this.menuView;
            MenuBuilder menuBuilder = navigationBarMenuView.menu;
            if (menuBuilder != null && navigationBarMenuView.buttons != null) {
                int size = menuBuilder.mItems.size();
                if (size != navigationBarMenuView.buttons.length) {
                    navigationBarMenuView.buildMenuView();
                    return;
                }
                int i = navigationBarMenuView.selectedItemId;
                for (int i2 = 0; i2 < size; i2++) {
                    MenuItem item = navigationBarMenuView.menu.getItem(i2);
                    if (item.isChecked()) {
                        navigationBarMenuView.selectedItemId = item.getItemId();
                        navigationBarMenuView.selectedItemPosition = i2;
                    }
                }
                if (!(i == navigationBarMenuView.selectedItemId || (autoTransition = navigationBarMenuView.set) == null)) {
                    TransitionManager.beginDelayedTransition(navigationBarMenuView, autoTransition);
                }
                boolean isShifting = NavigationBarMenuView.isShifting(navigationBarMenuView.labelVisibilityMode, navigationBarMenuView.menu.getVisibleItems().size());
                for (int i3 = 0; i3 < size; i3++) {
                    navigationBarMenuView.presenter.updateSuspended = true;
                    navigationBarMenuView.buttons[i3].setLabelVisibilityMode(navigationBarMenuView.labelVisibilityMode);
                    NavigationBarItemView navigationBarItemView = navigationBarMenuView.buttons[i3];
                    if (navigationBarItemView.isShifting != isShifting) {
                        navigationBarItemView.isShifting = isShifting;
                        MenuItemImpl menuItemImpl = navigationBarItemView.itemData;
                        if (menuItemImpl != null) {
                            navigationBarItemView.setChecked(menuItemImpl.isChecked());
                        }
                    }
                    navigationBarMenuView.buttons[i3].initialize((MenuItemImpl) navigationBarMenuView.menu.getItem(i3));
                    navigationBarMenuView.presenter.updateSuspended = false;
                }
            }
        }
    }

    public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
    }
}
