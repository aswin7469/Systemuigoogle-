package com.google.android.material.shadow;

import android.graphics.Paint;
import android.graphics.Path;
import androidx.core.graphics.ColorUtils;
import com.android.systemui.plugins.DarkIconDispatcher;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
        this.shadowPaint = new Paint();
        setShadowColor(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT);
        paint.setColor(0);
        Paint paint2 = new Paint(4);
        this.cornerShadowPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.edgeShadowPaint = new Paint(paint2);
    }

    public final void setShadowColor(int i) {
        this.shadowStartColor = ColorUtils.setAlphaComponent(i, 68);
        this.shadowMiddleColor = ColorUtils.setAlphaComponent(i, 20);
        this.shadowEndColor = ColorUtils.setAlphaComponent(i, 0);
        this.shadowPaint.setColor(this.shadowStartColor);
    }
}
