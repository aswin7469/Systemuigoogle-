package com.google.android.material.internal;

import android.view.SubMenu;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.SubMenuBuilder;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NavigationMenu extends MenuBuilder {
    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        MenuItemImpl addInternal = addInternal(i, i2, i3, charSequence);
        SubMenuBuilder subMenuBuilder = new SubMenuBuilder(this.mContext, this, addInternal);
        addInternal.mSubMenu = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(addInternal.mTitle);
        return subMenuBuilder;
    }
}
