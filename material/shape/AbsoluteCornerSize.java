package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AbsoluteCornerSize implements CornerSize {
    public final float size;

    public AbsoluteCornerSize(float f) {
        this.size = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbsoluteCornerSize)) {
            return false;
        }
        if (this.size == ((AbsoluteCornerSize) obj).size) {
            return true;
        }
        return false;
    }

    public final float getCornerSize(RectF rectF) {
        return this.size;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.size)});
    }
}
