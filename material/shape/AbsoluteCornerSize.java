package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
