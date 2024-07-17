package com.google.android.setupdesign.items;

import android.graphics.Rect;
import android.graphics.drawable.LayerDrawable;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
class RecyclerItemAdapter$PatchedLayerDrawable extends LayerDrawable {
    public final boolean getPadding(Rect rect) {
        if (!super.getPadding(rect) || (rect.left == 0 && rect.top == 0 && rect.right == 0 && rect.bottom == 0)) {
            return false;
        }
        return true;
    }
}
