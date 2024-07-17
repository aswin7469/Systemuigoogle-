package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapePath;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ShapeAppearancePathProvider {
    public final Path boundsPath = new Path();
    public final Path cornerPath = new Path();
    public final ShapePath[] cornerPaths = new ShapePath[4];
    public final Matrix[] cornerTransforms = new Matrix[4];
    public final boolean edgeIntersectionCheckEnabled = true;
    public final Path edgePath = new Path();
    public final Matrix[] edgeTransforms = new Matrix[4];
    public final Path overlappedEdgePath = new Path();
    public final PointF pointF = new PointF();
    public final float[] scratch = new float[2];
    public final float[] scratch2 = new float[2];
    public final ShapePath shapePath = new ShapePath();

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class Lazy {
        public static final ShapeAppearancePathProvider INSTANCE = new ShapeAppearancePathProvider();
    }

    public ShapeAppearancePathProvider() {
        for (int i = 0; i < 4; i++) {
            this.cornerPaths[i] = new ShapePath();
            this.cornerTransforms[i] = new Matrix();
            this.edgeTransforms[i] = new Matrix();
        }
    }

    public final void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f, RectF rectF, MaterialShapeDrawable.AnonymousClass1 r25, Path path) {
        int i;
        Matrix[] matrixArr;
        float[] fArr;
        Matrix[] matrixArr2;
        ShapePath[] shapePathArr;
        float f2;
        EdgeTreatment edgeTreatment;
        Path path2;
        CornerSize cornerSize;
        CornerTreatment cornerTreatment;
        Path path3;
        ShapeAppearancePathProvider shapeAppearancePathProvider = this;
        ShapeAppearanceModel shapeAppearanceModel2 = shapeAppearanceModel;
        float f3 = f;
        RectF rectF2 = rectF;
        MaterialShapeDrawable.AnonymousClass1 r4 = r25;
        Path path4 = path;
        path.rewind();
        Path path5 = shapeAppearancePathProvider.overlappedEdgePath;
        path5.rewind();
        Path path6 = shapeAppearancePathProvider.boundsPath;
        path6.rewind();
        path6.addRect(rectF2, Path.Direction.CW);
        int i2 = 0;
        while (true) {
            i = 4;
            matrixArr = shapeAppearancePathProvider.edgeTransforms;
            fArr = shapeAppearancePathProvider.scratch;
            matrixArr2 = shapeAppearancePathProvider.cornerTransforms;
            shapePathArr = shapeAppearancePathProvider.cornerPaths;
            if (i2 >= 4) {
                break;
            }
            if (i2 == 1) {
                cornerSize = shapeAppearanceModel2.bottomRightCornerSize;
            } else if (i2 == 2) {
                cornerSize = shapeAppearanceModel2.bottomLeftCornerSize;
            } else if (i2 != 3) {
                cornerSize = shapeAppearanceModel2.topRightCornerSize;
            } else {
                cornerSize = shapeAppearanceModel2.topLeftCornerSize;
            }
            if (i2 == 1) {
                cornerTreatment = shapeAppearanceModel2.bottomRightCorner;
            } else if (i2 == 2) {
                cornerTreatment = shapeAppearanceModel2.bottomLeftCorner;
            } else if (i2 != 3) {
                cornerTreatment = shapeAppearanceModel2.topRightCorner;
            } else {
                cornerTreatment = shapeAppearanceModel2.topLeftCorner;
            }
            ShapePath shapePath2 = shapePathArr[i2];
            cornerTreatment.getClass();
            cornerTreatment.getCornerPath(f3, cornerSize.getCornerSize(rectF2), shapePath2);
            int i3 = i2 + 1;
            float f4 = (float) (i3 * 90);
            matrixArr2[i2].reset();
            PointF pointF2 = shapeAppearancePathProvider.pointF;
            int i4 = i3;
            if (i2 == 1) {
                path3 = path5;
                pointF2.set(rectF2.right, rectF2.bottom);
            } else if (i2 == 2) {
                path3 = path5;
                pointF2.set(rectF2.left, rectF2.bottom);
            } else if (i2 != 3) {
                path3 = path5;
                pointF2.set(rectF2.right, rectF2.top);
            } else {
                path3 = path5;
                pointF2.set(rectF2.left, rectF2.top);
            }
            matrixArr2[i2].setTranslate(pointF2.x, pointF2.y);
            matrixArr2[i2].preRotate(f4);
            ShapePath shapePath3 = shapePathArr[i2];
            fArr[0] = shapePath3.endX;
            fArr[1] = shapePath3.endY;
            matrixArr2[i2].mapPoints(fArr);
            matrixArr[i2].reset();
            matrixArr[i2].setTranslate(fArr[0], fArr[1]);
            matrixArr[i2].preRotate(f4);
            i2 = i4;
            path5 = path3;
        }
        Path path7 = path5;
        int i5 = 0;
        while (i5 < i) {
            ShapePath shapePath4 = shapePathArr[i5];
            fArr[0] = shapePath4.startX;
            fArr[1] = shapePath4.startY;
            matrixArr2[i5].mapPoints(fArr);
            if (i5 == 0) {
                path4.moveTo(fArr[0], fArr[1]);
            } else {
                path4.lineTo(fArr[0], fArr[1]);
            }
            shapePathArr[i5].applyToPath(matrixArr2[i5], path4);
            if (r4 != null) {
                ShapePath shapePath5 = shapePathArr[i5];
                Matrix matrix = matrixArr2[i5];
                MaterialShapeDrawable materialShapeDrawable = MaterialShapeDrawable.this;
                BitSet bitSet = materialShapeDrawable.containsIncompatibleShadowOp;
                shapePath5.getClass();
                bitSet.set(i5, false);
                ShapePath.ShadowCompatOperation[] shadowCompatOperationArr = materialShapeDrawable.cornerShadowOperation;
                shapePath5.addConnectingShadowIfNecessary(shapePath5.endShadowAngle);
                shadowCompatOperationArr[i5] = new ShapePath.ShadowCompatOperation(new ArrayList(shapePath5.shadowCompatOperations), new Matrix(matrix)) {
                    public final /* synthetic */ List val$operations;
                    public final /* synthetic */ Matrix val$transformCopy;

                    {
                        this.val$operations = r1;
                        this.val$transformCopy = r2;
                    }

                    public final void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i, Canvas canvas) {
                        for (ShadowCompatOperation draw : this.val$operations) {
                            draw.draw(this.val$transformCopy, shadowRenderer, i, canvas);
                        }
                    }
                };
            }
            int i6 = i5 + 1;
            int i7 = i6 % 4;
            ShapePath shapePath6 = shapePathArr[i5];
            fArr[0] = shapePath6.endX;
            fArr[1] = shapePath6.endY;
            matrixArr2[i5].mapPoints(fArr);
            ShapePath shapePath7 = shapePathArr[i7];
            float f5 = shapePath7.startX;
            float[] fArr2 = shapeAppearancePathProvider.scratch2;
            fArr2[0] = f5;
            fArr2[1] = shapePath7.startY;
            matrixArr2[i7].mapPoints(fArr2);
            int i8 = i6;
            float max = Math.max(((float) Math.hypot((double) (fArr[0] - fArr2[0]), (double) (fArr[1] - fArr2[1]))) - 0.001f, 0.0f);
            ShapePath shapePath8 = shapePathArr[i5];
            fArr[0] = shapePath8.endX;
            fArr[1] = shapePath8.endY;
            matrixArr2[i5].mapPoints(fArr);
            if (i5 == 1 || i5 == 3) {
                f2 = Math.abs(rectF.centerX() - fArr[0]);
            } else {
                f2 = Math.abs(rectF.centerY() - fArr[1]);
            }
            ShapePath shapePath9 = shapeAppearancePathProvider.shapePath;
            shapePath9.reset(0.0f, 0.0f, 270.0f, 0.0f);
            if (i5 == 1) {
                edgeTreatment = shapeAppearanceModel2.bottomEdge;
            } else if (i5 == 2) {
                edgeTreatment = shapeAppearanceModel2.leftEdge;
            } else if (i5 != 3) {
                edgeTreatment = shapeAppearanceModel2.rightEdge;
            } else {
                edgeTreatment = shapeAppearanceModel2.topEdge;
            }
            edgeTreatment.getEdgePath(max, f2, f3, shapePath9);
            Path path8 = shapeAppearancePathProvider.edgePath;
            path8.reset();
            shapePath9.applyToPath(matrixArr[i5], path8);
            if (!shapeAppearancePathProvider.edgeIntersectionCheckEnabled || (!edgeTreatment.forceIntersection() && !shapeAppearancePathProvider.pathOverlapsCorner(path8, i5) && !shapeAppearancePathProvider.pathOverlapsCorner(path8, i7))) {
                path2 = path7;
                shapePath9.applyToPath(matrixArr[i5], path4);
            } else {
                path8.op(path8, path6, Path.Op.DIFFERENCE);
                fArr[0] = shapePath9.startX;
                fArr[1] = shapePath9.startY;
                matrixArr[i5].mapPoints(fArr);
                path2 = path7;
                path2.moveTo(fArr[0], fArr[1]);
                shapePath9.applyToPath(matrixArr[i5], path2);
            }
            MaterialShapeDrawable.AnonymousClass1 r3 = r25;
            if (r3 != null) {
                Matrix matrix2 = matrixArr[i5];
                MaterialShapeDrawable materialShapeDrawable2 = MaterialShapeDrawable.this;
                materialShapeDrawable2.containsIncompatibleShadowOp.set(i5 + 4, false);
                ShapePath.ShadowCompatOperation[] shadowCompatOperationArr2 = materialShapeDrawable2.edgeShadowOperation;
                shapePath9.addConnectingShadowIfNecessary(shapePath9.endShadowAngle);
                shadowCompatOperationArr2[i5] = new ShapePath.ShadowCompatOperation(new ArrayList(shapePath9.shadowCompatOperations), new Matrix(matrix2)) {
                    public final /* synthetic */ List val$operations;
                    public final /* synthetic */ Matrix val$transformCopy;

                    {
                        this.val$operations = r1;
                        this.val$transformCopy = r2;
                    }

                    public final void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i, Canvas canvas) {
                        for (ShadowCompatOperation draw : this.val$operations) {
                            draw.draw(this.val$transformCopy, shadowRenderer, i, canvas);
                        }
                    }
                };
            }
            shapeAppearancePathProvider = this;
            shapeAppearanceModel2 = shapeAppearanceModel;
            r4 = r3;
            path7 = path2;
            i5 = i8;
            i = 4;
            RectF rectF3 = rectF;
        }
        Path path9 = path7;
        path.close();
        path9.close();
        if (!path9.isEmpty()) {
            path4.op(path9, Path.Op.UNION);
        }
    }

    public final boolean pathOverlapsCorner(Path path, int i) {
        Path path2 = this.cornerPath;
        path2.reset();
        this.cornerPaths[i].applyToPath(this.cornerTransforms[i], path2);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        path2.computeBounds(rectF, true);
        path.op(path2, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (!rectF.isEmpty()) {
            return true;
        }
        if (rectF.width() <= 1.0f || rectF.height() <= 1.0f) {
            return false;
        }
        return true;
    }
}
