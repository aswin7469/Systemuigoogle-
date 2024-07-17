package com.google.android.material.shape;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class OffsetEdgeTreatment extends EdgeTreatment {
    public final float offset;
    public final EdgeTreatment other;

    public OffsetEdgeTreatment(MarkerEdgeTreatment markerEdgeTreatment, float f) {
        this.other = markerEdgeTreatment;
        this.offset = f;
    }

    public final boolean forceIntersection() {
        return this.other.forceIntersection();
    }

    public final void getEdgePath(float f, float f2, float f3, ShapePath shapePath) {
        this.other.getEdgePath(f, f2 - this.offset, f3, shapePath);
    }
}
