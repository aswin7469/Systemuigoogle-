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

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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

    public final void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f, RectF rectF, MaterialShapeDrawable.AnonymousClass1 r24, Path path) {
        int i;
        Matrix[] matrixArr;
        float[] fArr;
        Matrix[] matrixArr2;
        ShapePath[] shapePathArr;
        EdgeTreatment edgeTreatment;
        CornerSize cornerSize;
        CornerTreatment cornerTreatment;
        int i2;
        ShapeAppearancePathProvider shapeAppearancePathProvider = this;
        ShapeAppearanceModel shapeAppearanceModel2 = shapeAppearanceModel;
        RectF rectF2 = rectF;
        MaterialShapeDrawable.AnonymousClass1 r3 = r24;
        Path path2 = path;
        path.rewind();
        Path path3 = shapeAppearancePathProvider.overlappedEdgePath;
        path3.rewind();
        Path path4 = shapeAppearancePathProvider.boundsPath;
        path4.rewind();
        path4.addRect(rectF2, Path.Direction.CW);
        int i3 = 0;
        while (true) {
            i = 4;
            matrixArr = shapeAppearancePathProvider.edgeTransforms;
            fArr = shapeAppearancePathProvider.scratch;
            matrixArr2 = shapeAppearancePathProvider.cornerTransforms;
            shapePathArr = shapeAppearancePathProvider.cornerPaths;
            if (i3 >= 4) {
                break;
            }
            if (i3 == 1) {
                cornerSize = shapeAppearanceModel2.bottomRightCornerSize;
            } else if (i3 == 2) {
                cornerSize = shapeAppearanceModel2.bottomLeftCornerSize;
            } else if (i3 != 3) {
                cornerSize = shapeAppearanceModel2.topRightCornerSize;
            } else {
                cornerSize = shapeAppearanceModel2.topLeftCornerSize;
            }
            if (i3 == 1) {
                cornerTreatment = shapeAppearanceModel2.bottomRightCorner;
            } else if (i3 == 2) {
                cornerTreatment = shapeAppearanceModel2.bottomLeftCorner;
            } else if (i3 != 3) {
                cornerTreatment = shapeAppearanceModel2.topRightCorner;
            } else {
                cornerTreatment = shapeAppearanceModel2.topLeftCorner;
            }
            ShapePath shapePath2 = shapePathArr[i3];
            cornerTreatment.getClass();
            cornerTreatment.getCornerPath(shapePath2, f, cornerSize.getCornerSize(rectF2));
            int i4 = i3 + 1;
            float f2 = (float) (i4 * 90);
            matrixArr2[i3].reset();
            PointF pointF2 = shapeAppearancePathProvider.pointF;
            if (i3 == 1) {
                i2 = i4;
                pointF2.set(rectF2.right, rectF2.bottom);
            } else if (i3 == 2) {
                i2 = i4;
                pointF2.set(rectF2.left, rectF2.bottom);
            } else if (i3 != 3) {
                i2 = i4;
                pointF2.set(rectF2.right, rectF2.top);
            } else {
                i2 = i4;
                pointF2.set(rectF2.left, rectF2.top);
            }
            matrixArr2[i3].setTranslate(pointF2.x, pointF2.y);
            matrixArr2[i3].preRotate(f2);
            ShapePath shapePath3 = shapePathArr[i3];
            fArr[0] = shapePath3.endX;
            fArr[1] = shapePath3.endY;
            matrixArr2[i3].mapPoints(fArr);
            matrixArr[i3].reset();
            matrixArr[i3].setTranslate(fArr[0], fArr[1]);
            matrixArr[i3].preRotate(f2);
            i3 = i2;
        }
        int i5 = 0;
        while (i5 < i) {
            ShapePath shapePath4 = shapePathArr[i5];
            fArr[0] = shapePath4.startX;
            fArr[1] = shapePath4.startY;
            matrixArr2[i5].mapPoints(fArr);
            if (i5 == 0) {
                path2.moveTo(fArr[0], fArr[1]);
            } else {
                path2.lineTo(fArr[0], fArr[1]);
            }
            shapePathArr[i5].applyToPath(matrixArr2[i5], path2);
            if (r3 != null) {
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
            float f3 = shapePath7.startX;
            float[] fArr2 = shapeAppearancePathProvider.scratch2;
            fArr2[0] = f3;
            fArr2[1] = shapePath7.startY;
            matrixArr2[i7].mapPoints(fArr2);
            int i8 = i6;
            float max = Math.max(((float) Math.hypot((double) (fArr[0] - fArr2[0]), (double) (fArr[1] - fArr2[1]))) - 0.001f, 0.0f);
            ShapePath shapePath8 = shapePathArr[i5];
            fArr[0] = shapePath8.endX;
            fArr[1] = shapePath8.endY;
            matrixArr2[i5].mapPoints(fArr);
            if (i5 == 1 || i5 == 3) {
                Math.abs(rectF.centerX() - fArr[0]);
            } else {
                Math.abs(rectF.centerY() - fArr[1]);
            }
            ShapePath shapePath9 = shapeAppearancePathProvider.shapePath;
            shapePath9.reset(0.0f, 270.0f, 0.0f);
            if (i5 == 1) {
                edgeTreatment = shapeAppearanceModel2.bottomEdge;
            } else if (i5 == 2) {
                edgeTreatment = shapeAppearanceModel2.leftEdge;
            } else if (i5 != 3) {
                edgeTreatment = shapeAppearanceModel2.rightEdge;
            } else {
                edgeTreatment = shapeAppearanceModel2.topEdge;
            }
            edgeTreatment.getClass();
            shapePath9.lineTo(max, 0.0f);
            Path path5 = shapeAppearancePathProvider.edgePath;
            path5.reset();
            shapePath9.applyToPath(matrixArr[i5], path5);
            if (!shapeAppearancePathProvider.edgeIntersectionCheckEnabled || (!shapeAppearancePathProvider.pathOverlapsCorner(i5, path5) && !shapeAppearancePathProvider.pathOverlapsCorner(i7, path5))) {
                shapePath9.applyToPath(matrixArr[i5], path2);
            } else {
                path5.op(path5, path4, Path.Op.DIFFERENCE);
                fArr[0] = shapePath9.startX;
                fArr[1] = shapePath9.startY;
                matrixArr[i5].mapPoints(fArr);
                path3.moveTo(fArr[0], fArr[1]);
                shapePath9.applyToPath(matrixArr[i5], path3);
            }
            if (r3 != null) {
                Matrix matrix2 = matrixArr[i5];
                MaterialShapeDrawable materialShapeDrawable2 = MaterialShapeDrawable.this;
                materialShapeDrawable2.containsIncompatibleShadowOp.set(i5 + 4, false);
                ShapePath.ShadowCompatOperation[] shadowCompatOperationArr2 = materialShapeDrawable2.edgeShadowOperation;
                shapePath9.addConnectingShadowIfNecessary(shapePath9.endShadowAngle);
                shadowCompatOperationArr2[i5] = new ShapePath.ShadowCompatOperation(new ArrayList(shapePath9.shadowCompatOperations), new Matrix(matrix2)) {
                    public final /* synthetic */ List val$operations;
                    public final /* synthetic */ Matrix val$transformCopy;

                    public final void draw(
/*
Method generation error in method: com.google.android.material.shape.ShapePath.1.draw(android.graphics.Matrix, com.google.android.material.shadow.ShadowRenderer, int, android.graphics.Canvas):void, dex: classes2.dex
                    jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.google.android.material.shape.ShapePath.1.draw(android.graphics.Matrix, com.google.android.material.shadow.ShadowRenderer, int, android.graphics.Canvas):void, class status: UNLOADED
                    	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.util.ArrayList.forEach(ArrayList.java:1259)
                    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                    	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                    	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:250)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.util.ArrayList.forEach(ArrayList.java:1259)
                    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                    	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                    	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    
*/
                };
            }
            i = 4;
            shapeAppearancePathProvider = this;
            i5 = i8;
            RectF rectF3 = rectF;
        }
        path.close();
        path3.close();
        if (!path3.isEmpty()) {
            path2.op(path3, Path.Op.UNION);
        }
    }

    public final boolean pathOverlapsCorner(int i, Path path) {
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
