package com.google.android.setupdesign.items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class AbstractItem extends AbstractItemHierarchy {
    public AbstractItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getCount() {
        return 1;
    }

    public abstract int getLayoutResource();

    public abstract boolean isEnabled();

    public abstract void onBindView(View view);

    public final AbstractItem getItemAt(int i) {
        return this;
    }
}
