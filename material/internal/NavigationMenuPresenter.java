package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NavigationMenuPresenter implements MenuPresenter {
    public NavigationMenuAdapter adapter;
    public int dividerInsetEnd;
    public int dividerInsetStart;
    public boolean hasCustomItemIconSize;
    public LinearLayout headerLayout;
    public ColorStateList iconTintList;
    public int id;
    public boolean isBehindStatusBar = true;
    public Drawable itemBackground;
    public RippleDrawable itemForeground;
    public int itemHorizontalPadding;
    public int itemIconPadding;
    public int itemIconSize;
    public int itemMaxLines;
    public int itemVerticalPadding;
    public LayoutInflater layoutInflater;
    public MenuBuilder menu;
    public NavigationMenuView menuView;
    public final AnonymousClass1 onClickListener = new View.OnClickListener() {
        public final void onClick(View view) {
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            NavigationMenuAdapter navigationMenuAdapter = navigationMenuPresenter.adapter;
            boolean z = true;
            if (navigationMenuAdapter != null) {
                navigationMenuAdapter.updateSuspended = true;
            }
            MenuItemImpl menuItemImpl = navigationMenuItemView.itemData;
            boolean performItemAction = navigationMenuPresenter.menu.performItemAction(menuItemImpl, navigationMenuPresenter, 0);
            if (menuItemImpl == null || !menuItemImpl.isCheckable() || !performItemAction) {
                z = false;
            } else {
                NavigationMenuPresenter.this.adapter.setCheckedItem(menuItemImpl);
            }
            NavigationMenuPresenter navigationMenuPresenter2 = NavigationMenuPresenter.this;
            NavigationMenuAdapter navigationMenuAdapter2 = navigationMenuPresenter2.adapter;
            if (navigationMenuAdapter2 != null) {
                navigationMenuAdapter2.updateSuspended = false;
            }
            if (z) {
                navigationMenuPresenter2.updateMenuView(false);
            }
        }
    };
    public int overScrollMode = -1;
    public int paddingSeparator;
    public int paddingTopDefault;
    public ColorStateList subheaderColor;
    public int subheaderInsetStart;
    public int subheaderTextAppearance = 0;
    public int textAppearance = 0;
    public ColorStateList textColor;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class HeaderViewHolder extends ViewHolder {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public HeaderViewHolder(int i, LayoutInflater layoutInflater, RecyclerView recyclerView) {
            super(layoutInflater.inflate(2131558565, recyclerView, false));
            if (i != 2) {
            } else {
                super(layoutInflater.inflate(2131558566, recyclerView, false));
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class NavigationMenuAdapter extends RecyclerView.Adapter {
        public MenuItemImpl checkedItem;
        public final ArrayList items = new ArrayList();
        public boolean updateSuspended;

        public NavigationMenuAdapter() {
            prepareMenuItems();
        }

        public final int getItemCount() {
            return this.items.size();
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final int getItemViewType(int i) {
            NavigationMenuItem navigationMenuItem = (NavigationMenuItem) this.items.get(i);
            if (navigationMenuItem instanceof NavigationMenuSeparatorItem) {
                return 2;
            }
            if (navigationMenuItem instanceof NavigationMenuHeaderItem) {
                return 3;
            }
            if (!(navigationMenuItem instanceof NavigationMenuTextItem)) {
                throw new RuntimeException("Unknown item type.");
            } else if (((NavigationMenuTextItem) navigationMenuItem).menuItem.hasSubMenu()) {
                return 1;
            } else {
                return 0;
            }
        }

        public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
            Drawable drawable;
            int itemViewType = getItemViewType(i);
            ArrayList arrayList = this.items;
            View view = ((ViewHolder) viewHolder).itemView;
            boolean z = true;
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            if (itemViewType == 0) {
                NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
                ColorStateList colorStateList = navigationMenuPresenter.iconTintList;
                navigationMenuItemView.iconTintList = colorStateList;
                if (colorStateList == null) {
                    z = false;
                }
                navigationMenuItemView.hasIconTintList = z;
                MenuItemImpl menuItemImpl = navigationMenuItemView.itemData;
                if (menuItemImpl != null) {
                    navigationMenuItemView.setIcon(menuItemImpl.getIcon());
                }
                int i2 = navigationMenuPresenter.textAppearance;
                if (i2 != 0) {
                    navigationMenuItemView.textView.setTextAppearance(i2);
                }
                ColorStateList colorStateList2 = navigationMenuPresenter.textColor;
                if (colorStateList2 != null) {
                    navigationMenuItemView.textView.setTextColor(colorStateList2);
                }
                Drawable drawable2 = navigationMenuPresenter.itemBackground;
                if (drawable2 != null) {
                    drawable = drawable2.getConstantState().newDrawable();
                } else {
                    drawable = null;
                }
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                ViewCompat.Api16Impl.setBackground(navigationMenuItemView, drawable);
                RippleDrawable rippleDrawable = navigationMenuPresenter.itemForeground;
                if (rippleDrawable != null) {
                    navigationMenuItemView.setForeground(rippleDrawable.getConstantState().newDrawable());
                }
                NavigationMenuTextItem navigationMenuTextItem = (NavigationMenuTextItem) arrayList.get(i);
                navigationMenuItemView.needsEmptyIcon = navigationMenuTextItem.needsEmptyIcon;
                int i3 = navigationMenuPresenter.itemHorizontalPadding;
                int i4 = navigationMenuPresenter.itemVerticalPadding;
                navigationMenuItemView.setPadding(i3, i4, i3, i4);
                navigationMenuItemView.textView.setCompoundDrawablePadding(navigationMenuPresenter.itemIconPadding);
                if (navigationMenuPresenter.hasCustomItemIconSize) {
                    navigationMenuItemView.iconSize = navigationMenuPresenter.itemIconSize;
                }
                navigationMenuItemView.textView.setMaxLines(navigationMenuPresenter.itemMaxLines);
                navigationMenuItemView.initialize(navigationMenuTextItem.menuItem);
                ViewCompat.setAccessibilityDelegate(navigationMenuItemView, new AccessibilityDelegateCompat(false) {
                    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                        NavigationMenuPresenter navigationMenuPresenter;
                        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                        int i = i;
                        int i2 = 0;
                        int i3 = i;
                        while (true) {
                            navigationMenuPresenter = NavigationMenuPresenter.this;
                            if (i2 >= i) {
                                break;
                            }
                            if (navigationMenuPresenter.adapter.getItemViewType(i2) == 2) {
                                i3--;
                            }
                            i2++;
                        }
                        if (navigationMenuPresenter.headerLayout.getChildCount() == 0) {
                            i3--;
                        }
                        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(i3, 1, 1, 1, false, view.isSelected()));
                    }
                });
            } else if (itemViewType == 1) {
                TextView textView = (TextView) view;
                textView.setText(((NavigationMenuTextItem) arrayList.get(i)).menuItem.mTitle);
                int i5 = navigationMenuPresenter.subheaderTextAppearance;
                if (i5 != 0) {
                    textView.setTextAppearance(i5);
                }
                textView.setPadding(navigationMenuPresenter.subheaderInsetStart, textView.getPaddingTop(), 0, textView.getPaddingBottom());
                ColorStateList colorStateList3 = navigationMenuPresenter.subheaderColor;
                if (colorStateList3 != null) {
                    textView.setTextColor(colorStateList3);
                }
                ViewCompat.setAccessibilityDelegate(textView, new AccessibilityDelegateCompat(true) {
                    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                        NavigationMenuPresenter navigationMenuPresenter;
                        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                        int i = i;
                        int i2 = 0;
                        int i3 = i;
                        while (true) {
                            navigationMenuPresenter = NavigationMenuPresenter.this;
                            if (i2 >= i) {
                                break;
                            }
                            if (navigationMenuPresenter.adapter.getItemViewType(i2) == 2) {
                                i3--;
                            }
                            i2++;
                        }
                        if (navigationMenuPresenter.headerLayout.getChildCount() == 0) {
                            i3--;
                        }
                        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(i3, 1, 1, 1, false, view.isSelected()));
                    }
                });
            } else if (itemViewType == 2) {
                NavigationMenuSeparatorItem navigationMenuSeparatorItem = (NavigationMenuSeparatorItem) arrayList.get(i);
                view.setPadding(navigationMenuPresenter.dividerInsetStart, navigationMenuSeparatorItem.paddingTop, navigationMenuPresenter.dividerInsetEnd, navigationMenuSeparatorItem.paddingBottom);
            } else if (itemViewType == 3) {
                ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat(true) {
                    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                        NavigationMenuPresenter navigationMenuPresenter;
                        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                        int i = i;
                        int i2 = 0;
                        int i3 = i;
                        while (true) {
                            navigationMenuPresenter = NavigationMenuPresenter.this;
                            if (i2 >= i) {
                                break;
                            }
                            if (navigationMenuPresenter.adapter.getItemViewType(i2) == 2) {
                                i3--;
                            }
                            i2++;
                        }
                        if (navigationMenuPresenter.headerLayout.getChildCount() == 0) {
                            i3--;
                        }
                        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(i3, 1, 1, 1, false, view.isSelected()));
                    }
                });
            }
        }

        public final RecyclerView.ViewHolder onCreateViewHolder(int i, RecyclerView recyclerView) {
            RecyclerView.ViewHolder viewHolder;
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            if (i == 0) {
                View inflate = navigationMenuPresenter.layoutInflater.inflate(2131558563, recyclerView, false);
                viewHolder = new RecyclerView.ViewHolder(inflate);
                inflate.setOnClickListener(navigationMenuPresenter.onClickListener);
            } else if (i == 1) {
                viewHolder = new HeaderViewHolder(2, navigationMenuPresenter.layoutInflater, recyclerView);
            } else if (i == 2) {
                viewHolder = new HeaderViewHolder(1, navigationMenuPresenter.layoutInflater, recyclerView);
            } else if (i != 3) {
                return null;
            } else {
                viewHolder = new RecyclerView.ViewHolder(navigationMenuPresenter.headerLayout);
            }
            return viewHolder;
        }

        public final void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
            ViewHolder viewHolder2 = (ViewHolder) viewHolder;
            if (viewHolder2 instanceof NormalViewHolder) {
                NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) viewHolder2.itemView;
                FrameLayout frameLayout = navigationMenuItemView.actionArea;
                if (frameLayout != null) {
                    frameLayout.removeAllViews();
                }
                navigationMenuItemView.textView.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            }
        }

        public final void prepareMenuItems() {
            boolean z;
            if (!this.updateSuspended) {
                this.updateSuspended = true;
                ArrayList arrayList = this.items;
                arrayList.clear();
                arrayList.add(new Object());
                NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
                int size = navigationMenuPresenter.menu.getVisibleItems().size();
                boolean z2 = false;
                int i = -1;
                int i2 = 0;
                boolean z3 = false;
                int i3 = 0;
                while (i2 < size) {
                    MenuItemImpl menuItemImpl = (MenuItemImpl) navigationMenuPresenter.menu.getVisibleItems().get(i2);
                    if (menuItemImpl.isChecked()) {
                        setCheckedItem(menuItemImpl);
                    }
                    if (menuItemImpl.isCheckable()) {
                        menuItemImpl.setExclusiveCheckable(z2);
                    }
                    if (menuItemImpl.hasSubMenu()) {
                        SubMenuBuilder subMenuBuilder = menuItemImpl.mSubMenu;
                        if (subMenuBuilder.hasVisibleItems()) {
                            if (i2 != 0) {
                                arrayList.add(new NavigationMenuSeparatorItem(navigationMenuPresenter.paddingSeparator, z2 ? 1 : 0));
                            }
                            arrayList.add(new NavigationMenuTextItem(menuItemImpl));
                            int size2 = subMenuBuilder.mItems.size();
                            int i4 = z2;
                            int i5 = i4;
                            while (i4 < size2) {
                                MenuItemImpl menuItemImpl2 = (MenuItemImpl) subMenuBuilder.getItem(i4);
                                if (menuItemImpl2.isVisible()) {
                                    if (i5 == 0 && menuItemImpl2.getIcon() != null) {
                                        i5 = 1;
                                    }
                                    if (menuItemImpl2.isCheckable()) {
                                        menuItemImpl2.setExclusiveCheckable(z2);
                                    }
                                    if (menuItemImpl.isChecked()) {
                                        setCheckedItem(menuItemImpl);
                                    }
                                    arrayList.add(new NavigationMenuTextItem(menuItemImpl2));
                                }
                                i4++;
                                z2 = false;
                            }
                            if (i5 != 0) {
                                int size3 = arrayList.size();
                                for (int size4 = arrayList.size(); size4 < size3; size4++) {
                                    ((NavigationMenuTextItem) arrayList.get(size4)).needsEmptyIcon = true;
                                }
                            }
                        }
                        z = true;
                    } else {
                        int i6 = menuItemImpl.mGroup;
                        if (i6 != i) {
                            i3 = arrayList.size();
                            if (menuItemImpl.getIcon() != null) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if (i2 != 0) {
                                i3++;
                                int i7 = navigationMenuPresenter.paddingSeparator;
                                arrayList.add(new NavigationMenuSeparatorItem(i7, i7));
                            }
                        } else if (!z3 && menuItemImpl.getIcon() != null) {
                            int size5 = arrayList.size();
                            for (int i8 = i3; i8 < size5; i8++) {
                                ((NavigationMenuTextItem) arrayList.get(i8)).needsEmptyIcon = true;
                            }
                            z = true;
                            z3 = true;
                            NavigationMenuTextItem navigationMenuTextItem = new NavigationMenuTextItem(menuItemImpl);
                            navigationMenuTextItem.needsEmptyIcon = z3;
                            arrayList.add(navigationMenuTextItem);
                            i = i6;
                        }
                        z = true;
                        NavigationMenuTextItem navigationMenuTextItem2 = new NavigationMenuTextItem(menuItemImpl);
                        navigationMenuTextItem2.needsEmptyIcon = z3;
                        arrayList.add(navigationMenuTextItem2);
                        i = i6;
                    }
                    i2++;
                    boolean z4 = z;
                    z2 = false;
                }
                this.updateSuspended = z2;
            }
        }

        public final void setCheckedItem(MenuItemImpl menuItemImpl) {
            if (this.checkedItem != menuItemImpl && menuItemImpl.isCheckable()) {
                MenuItemImpl menuItemImpl2 = this.checkedItem;
                if (menuItemImpl2 != null) {
                    menuItemImpl2.setChecked(false);
                }
                this.checkedItem = menuItemImpl;
                menuItemImpl.setChecked(true);
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class NavigationMenuHeaderItem implements NavigationMenuItem {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface NavigationMenuItem {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class NavigationMenuSeparatorItem implements NavigationMenuItem {
        public final int paddingBottom;
        public final int paddingTop;

        public NavigationMenuSeparatorItem(int i, int i2) {
            this.paddingTop = i;
            this.paddingBottom = i2;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class NavigationMenuTextItem implements NavigationMenuItem {
        public final MenuItemImpl menuItem;
        public boolean needsEmptyIcon;

        public NavigationMenuTextItem(MenuItemImpl menuItemImpl) {
            this.menuItem = menuItemImpl;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class NavigationMenuViewAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {
        public NavigationMenuViewAccessibilityDelegate(NavigationMenuView navigationMenuView) {
            super(navigationMenuView);
        }

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int i;
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            if (navigationMenuPresenter.headerLayout.getChildCount() == 0) {
                i = 0;
            } else {
                i = 1;
            }
            for (int i2 = 0; i2 < navigationMenuPresenter.adapter.items.size(); i2++) {
                int itemViewType = navigationMenuPresenter.adapter.getItemViewType(i2);
                if (itemViewType == 0 || itemViewType == 1) {
                    i++;
                }
            }
            accessibilityNodeInfoCompat.mInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(i, 1, false));
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class NormalViewHolder extends ViewHolder {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class ViewHolder extends RecyclerView.ViewHolder {
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
        this.layoutInflater = LayoutInflater.from(context);
        this.menu = menuBuilder;
        this.paddingSeparator = context.getResources().getDimensionPixelOffset(2131165743);
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        MenuItemImpl menuItemImpl;
        View actionView;
        ParcelableSparseArray parcelableSparseArray;
        MenuItemImpl menuItemImpl2;
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.menuView.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                NavigationMenuAdapter navigationMenuAdapter = this.adapter;
                navigationMenuAdapter.getClass();
                int i = bundle2.getInt("android:menu:checked", 0);
                ArrayList arrayList = navigationMenuAdapter.items;
                if (i != 0) {
                    navigationMenuAdapter.updateSuspended = true;
                    int size = arrayList.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            break;
                        }
                        NavigationMenuItem navigationMenuItem = (NavigationMenuItem) arrayList.get(i2);
                        if ((navigationMenuItem instanceof NavigationMenuTextItem) && (menuItemImpl2 = ((NavigationMenuTextItem) navigationMenuItem).menuItem) != null && menuItemImpl2.mId == i) {
                            navigationMenuAdapter.setCheckedItem(menuItemImpl2);
                            break;
                        }
                        i2++;
                    }
                    navigationMenuAdapter.updateSuspended = false;
                    navigationMenuAdapter.prepareMenuItems();
                }
                SparseArray sparseParcelableArray2 = bundle2.getSparseParcelableArray("android:menu:action_views");
                if (sparseParcelableArray2 != null) {
                    int size2 = arrayList.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        NavigationMenuItem navigationMenuItem2 = (NavigationMenuItem) arrayList.get(i3);
                        if (!(!(navigationMenuItem2 instanceof NavigationMenuTextItem) || (menuItemImpl = ((NavigationMenuTextItem) navigationMenuItem2).menuItem) == null || (actionView = menuItemImpl.getActionView()) == null || (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray2.get(menuItemImpl.mId)) == null)) {
                            actionView.restoreHierarchyState(parcelableSparseArray);
                        }
                    }
                }
            }
            SparseArray sparseParcelableArray3 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray3 != null) {
                this.headerLayout.restoreHierarchyState(sparseParcelableArray3);
            }
        }
    }

    public final Parcelable onSaveInstanceState() {
        View view;
        Bundle bundle = new Bundle();
        if (this.menuView != null) {
            SparseArray sparseArray = new SparseArray();
            this.menuView.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        NavigationMenuAdapter navigationMenuAdapter = this.adapter;
        if (navigationMenuAdapter != null) {
            navigationMenuAdapter.getClass();
            Bundle bundle2 = new Bundle();
            MenuItemImpl menuItemImpl = navigationMenuAdapter.checkedItem;
            if (menuItemImpl != null) {
                bundle2.putInt("android:menu:checked", menuItemImpl.mId);
            }
            SparseArray sparseArray2 = new SparseArray();
            ArrayList arrayList = navigationMenuAdapter.items;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                NavigationMenuItem navigationMenuItem = (NavigationMenuItem) arrayList.get(i);
                if (navigationMenuItem instanceof NavigationMenuTextItem) {
                    MenuItemImpl menuItemImpl2 = ((NavigationMenuTextItem) navigationMenuItem).menuItem;
                    if (menuItemImpl2 != null) {
                        view = menuItemImpl2.getActionView();
                    } else {
                        view = null;
                    }
                    if (view != null) {
                        SparseArray sparseArray3 = new SparseArray();
                        view.saveHierarchyState(sparseArray3);
                        sparseArray2.put(menuItemImpl2.mId, sparseArray3);
                    }
                }
            }
            bundle2.putSparseParcelableArray("android:menu:action_views", sparseArray2);
            bundle.putBundle("android:menu:adapter", bundle2);
        }
        if (this.headerLayout != null) {
            SparseArray sparseArray4 = new SparseArray();
            this.headerLayout.saveHierarchyState(sparseArray4);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray4);
        }
        return bundle;
    }

    public final boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    public final void updateMenuView(boolean z) {
        NavigationMenuAdapter navigationMenuAdapter = this.adapter;
        if (navigationMenuAdapter != null) {
            navigationMenuAdapter.prepareMenuItems();
            navigationMenuAdapter.notifyDataSetChanged();
        }
    }

    public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
    }
}
