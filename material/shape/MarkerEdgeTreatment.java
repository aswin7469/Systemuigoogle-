package com.google.android.material.shape;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class MarkerEdgeTreatment extends EdgeTreatment {
    public final float radius;

    public MarkerEdgeTreatment(float f) {
        this.radius = f - 0.001f;
    }

    public final void getEdgePath(float f, float f2, float f3, ShapePath shapePath) {
        double d = (double) this.radius;
        float sqrt = (float) ((Math.sqrt(2.0d) * d) / 2.0d);
        float sqrt2 = (float) Math.sqrt(Math.pow(d, 2.0d) - Math.pow((double) sqrt, 2.0d));
        shapePath.reset(f2 - sqrt, ((float) (-((Math.sqrt(2.0d) * d) - d))) + sqrt2, 270.0f, 0.0f);
        shapePath.lineTo(f2, (float) (-((Math.sqrt(2.0d) * d) - d)));
        shapePath.lineTo(f2 + sqrt, ((float) (-((Math.sqrt(2.0d) * d) - d))) + sqrt2);
    }
}
