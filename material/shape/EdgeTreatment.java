package com.google.android.material.shape;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class EdgeTreatment {
    public boolean forceIntersection() {
        return this instanceof MarkerEdgeTreatment;
    }

    public void getEdgePath(float f, float f2, float f3, ShapePath shapePath) {
        shapePath.lineTo(f, 0.0f);
    }
}
