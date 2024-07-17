package com.google.android.material.internal;

import androidx.appcompat.view.menu.SubMenuBuilder;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NavigationSubMenu extends SubMenuBuilder {
    public final void onItemsChanged(boolean z) {
        super.onItemsChanged(z);
        this.mParentMenu.onItemsChanged(z);
    }
}
