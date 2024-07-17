package com.google.android.material.elevation;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialAttributes;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ElevationOverlayProvider {
    public static final int OVERLAY_ACCENT_COLOR_ALPHA = ((int) Math.round(5.1000000000000005d));
    public final int colorSurface;
    public final float displayDensity;
    public final int elevationOverlayAccentColor;
    public final int elevationOverlayColor;
    public final boolean elevationOverlayEnabled;

    public ElevationOverlayProvider(Context context) {
        boolean z;
        TypedValue resolve = MaterialAttributes.resolve(2130969047, context);
        if (resolve == null || resolve.type != 18 || resolve.data == 0) {
            z = false;
        } else {
            z = true;
        }
        int color = MaterialColors.getColor(context, 2130969046, 0);
        int color2 = MaterialColors.getColor(context, 2130969045, 0);
        int color3 = MaterialColors.getColor(context, 2130968887, 0);
        float f = context.getResources().getDisplayMetrics().density;
        this.elevationOverlayEnabled = z;
        this.elevationOverlayColor = color;
        this.elevationOverlayAccentColor = color2;
        this.colorSurface = color3;
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
