package com.google.android.material.navigation;

import android.content.Context;
import android.view.SubMenu;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NavigationBarMenu extends MenuBuilder {
    public final int maxItemCount;
    public final Class viewClass;

    public NavigationBarMenu(Context context, Class cls, int i) {
        super(context);
        this.viewClass = cls;
        this.maxItemCount = i;
    }

    public final MenuItemImpl addInternal(int i, int i2, int i3, CharSequence charSequence) {
        if (this.mItems.size() + 1 <= this.maxItemCount) {
            stopDispatchingItemsChanged();
            MenuItemImpl addInternal = super.addInternal(i, i2, i3, charSequence);
            addInternal.setExclusiveCheckable(true);
            startDispatchingItemsChanged();
            return addInternal;
        }
        String simpleName = this.viewClass.getSimpleName();
        StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Maximum number of items supported by ", simpleName, " is ");
        m.append(this.maxItemCount);
        m.append(". Limit can be checked with ");
        m.append(simpleName);
        m.append("#getMaxItemCount()");
        throw new IllegalArgumentException(m.toString());
    }

    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        throw new UnsupportedOperationException(this.viewClass.getSimpleName().concat(" does not support submenus"));
    }
}
