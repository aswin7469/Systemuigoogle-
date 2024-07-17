package com.google.android.material.shape;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CutCornerTreatment extends CornerTreatment {
    public final void getCornerPath(float f, float f2, ShapePath shapePath) {
        shapePath.reset(0.0f, f2 * f, 180.0f, 90.0f);
        double d = (double) f2;
        double d2 = (double) f;
        shapePath.lineTo((float) (Math.sin(Math.toRadians((double) 90.0f)) * d * d2), (float) (Math.sin(Math.toRadians((double) 0.0f)) * d * d2));
    }
}
