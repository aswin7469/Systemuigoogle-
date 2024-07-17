package com.google.android.setupdesign.items;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class ItemGroup extends AbstractItemHierarchy {
    public final List children = new ArrayList();
    public final int count = 0;
    public final SparseIntArray hierarchyStart = new SparseIntArray();

    public ItemGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
