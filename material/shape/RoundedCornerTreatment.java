package com.google.android.material.shape;

import com.google.android.material.shape.ShapePath;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class RoundedCornerTreatment extends CornerTreatment {
    public final void getCornerPath(ShapePath shapePath, float f, float f2) {
        shapePath.reset(f2 * f, 180.0f, 90.0f);
        float f3 = f2 * 2.0f * f;
        ShapePath.PathArcOperation pathArcOperation = new ShapePath.PathArcOperation(0.0f, 0.0f, f3, f3);
        pathArcOperation.startAngle = 180.0f;
        pathArcOperation.sweepAngle = 90.0f;
        shapePath.operations.add(pathArcOperation);
        ShapePath.ArcShadowOperation arcShadowOperation = new ShapePath.ArcShadowOperation(pathArcOperation);
        shapePath.addConnectingShadowIfNecessary(180.0f);
        shapePath.shadowCompatOperations.add(arcShadowOperation);
        shapePath.currentShadowAngle = 270.0f;
        float f4 = (0.0f + f3) * 0.5f;
        float f5 = (f3 - 0.0f) / 2.0f;
        double d = (double) 270.0f;
        shapePath.endX = (((float) Math.cos(Math.toRadians(d))) * f5) + f4;
        shapePath.endY = (f5 * ((float) Math.sin(Math.toRadians(d)))) + f4;
    }
}
