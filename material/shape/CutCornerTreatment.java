package com.google.android.material.shape;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CutCornerTreatment extends CornerTreatment {
    public final void getCornerPath(ShapePath shapePath, float f, float f2) {
        shapePath.reset(f2 * f, 180.0f, 90.0f);
        double d = (double) f2;
        double d2 = (double) f;
        shapePath.lineTo((float) (Math.sin(Math.toRadians((double) 90.0f)) * d * d2), (float) (Math.sin(Math.toRadians((double) 0.0f)) * d * d2));
    }
}
