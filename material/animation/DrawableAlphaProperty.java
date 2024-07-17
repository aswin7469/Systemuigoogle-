package com.google.android.material.animation;

import android.graphics.drawable.Drawable;
import android.util.Property;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DrawableAlphaProperty extends Property {
    public static final DrawableAlphaProperty DRAWABLE_ALPHA_COMPAT = new DrawableAlphaProperty();

    public DrawableAlphaProperty() {
        super(Integer.class, "drawableAlphaCompat");
        new WeakHashMap();
    }

    public final Object get(Object obj) {
        return Integer.valueOf(((Drawable) obj).getAlpha());
    }

    public final void set(Object obj, Object obj2) {
        ((Drawable) obj).setAlpha(((Integer) obj2).intValue());
    }
}
