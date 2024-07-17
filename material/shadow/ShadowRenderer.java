package com.google.android.material.shadow;

import android.graphics.Paint;
import android.graphics.Path;
import androidx.core.graphics.ColorUtils;
import com.android.systemui.plugins.DarkIconDispatcher;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ShadowRenderer {
    public static final int[] cornerColors = new int[4];
    public static final float[] cornerPositions = {0.0f, 0.0f, 0.5f, 1.0f};
    public static final int[] edgeColors = new int[3];
    public static final float[] edgePositions = {0.0f, 0.5f, 1.0f};
    public final Paint cornerShadowPaint;
    public final Paint edgeShadowPaint;
    public final Path scratch = new Path();
    public int shadowEndColor;
    public int shadowMiddleColor;
    public final Paint shadowPaint;
    public int shadowStartColor;
    public final Paint transparentPaint;

    public ShadowRenderer() {
        Paint paint = new Paint();
        this.transparentPaint = paint;
        Paint paint2 = new Paint();
        this.shadowPaint = paint2;
        this.shadowStartColor = ColorUtils.setAlphaComponent(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT, 68);
        this.shadowMiddleColor = ColorUtils.setAlphaComponent(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT, 20);
        this.shadowEndColor = ColorUtils.setAlphaComponent(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT, 0);
        paint2.setColor(this.shadowStartColor);
        paint.setColor(0);
        Paint paint3 = new Paint(4);
        this.cornerShadowPaint = paint3;
        paint3.setStyle(Paint.Style.FILL);
        this.edgeShadowPaint = new Paint(paint3);
    }
}
