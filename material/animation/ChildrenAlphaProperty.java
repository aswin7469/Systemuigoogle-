package com.google.android.material.animation;

import android.util.Property;
import android.view.ViewGroup;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ChildrenAlphaProperty extends Property {
    public static final ChildrenAlphaProperty CHILDREN_ALPHA = new Property(Float.class, "childrenAlpha");

    public final Object get(Object obj) {
        Float f = (Float) ((ViewGroup) obj).getTag(2131363128);
        if (f != null) {
            return f;
        }
        return Float.valueOf(1.0f);
    }

    public final void set(Object obj, Object obj2) {
        ViewGroup viewGroup = (ViewGroup) obj;
        Float f = (Float) obj2;
        float floatValue = f.floatValue();
        viewGroup.setTag(2131363128, f);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            viewGroup.getChildAt(i).setAlpha(floatValue);
        }
    }
}
