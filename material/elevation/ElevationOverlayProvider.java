package com.google.android.material.elevation;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialAttributes;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ElevationOverlayProvider {
    public static final int OVERLAY_ACCENT_COLOR_ALPHA = ((int) Math.round(5.1000000000000005d));
    public final int colorSurface;
    public final float displayDensity;
    public final int elevationOverlayAccentColor;
    public final int elevationOverlayColor;
    public final boolean elevationOverlayEnabled;

    public ElevationOverlayProvider(Context context) {
        boolean z;
        int i;
        int i2;
        TypedValue resolve = MaterialAttributes.resolve(2130969047, context);
        int i3 = 0;
        if (resolve == null || resolve.type != 18 || resolve.data == 0) {
            z = false;
        } else {
            z = true;
        }
        TypedValue resolve2 = MaterialAttributes.resolve(2130969046, context);
        if (resolve2 != null) {
            int i4 = resolve2.resourceId;
            if (i4 != 0) {
                i = context.getColor(i4);
            } else {
                i = resolve2.data;
            }
        } else {
            i = 0;
        }
        TypedValue resolve3 = MaterialAttributes.resolve(2130969045, context);
        if (resolve3 != null) {
            int i5 = resolve3.resourceId;
            if (i5 != 0) {
                i2 = context.getColor(i5);
            } else {
                i2 = resolve3.data;
            }
        } else {
            i2 = 0;
        }
        TypedValue resolve4 = MaterialAttributes.resolve(2130968887, context);
        if (resolve4 != null) {
            int i6 = resolve4.resourceId;
            if (i6 != 0) {
                i3 = context.getColor(i6);
            } else {
                i3 = resolve4.data;
            }
        }
        float f = context.getResources().getDisplayMetrics().density;
        this.elevationOverlayEnabled = z;
        this.elevationOverlayColor = i;
        this.elevationOverlayAccentColor = i2;
        this.colorSurface = i3;
        this.displayDensity = f;
    }

    public final int compositeOverlayIfNeeded(int i, float f) {
        float f2;
        int i2;
        if (!this.elevationOverlayEnabled || ColorUtils.setAlphaComponent(i, 255) != this.colorSurface) {
            return i;
        }
        float f3 = this.displayDensity;
        if (f3 <= 0.0f || f <= 0.0f) {
            f2 = 0.0f;
        } else {
            f2 = Math.min(((((float) Math.log1p((double) (f / f3))) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        }
        int alpha = Color.alpha(i);
        int layer = MaterialColors.layer(ColorUtils.setAlphaComponent(i, 255), f2, this.elevationOverlayColor);
        if (f2 > 0.0f && (i2 = this.elevationOverlayAccentColor) != 0) {
            layer = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(i2, OVERLAY_ACCENT_COLOR_ALPHA), layer);
        }
        return ColorUtils.setAlphaComponent(layer, alpha);
    }
}
