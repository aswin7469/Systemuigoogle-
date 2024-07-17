package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.RectF;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CutoutDrawable extends MaterialShapeDrawable {
    public final RectF cutoutBounds;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CutoutDrawable(ShapeAppearanceModel shapeAppearanceModel) {
        super(shapeAppearanceModel == null ? new ShapeAppearanceModel() : shapeAppearanceModel);
        this.cutoutBounds = new RectF();
    }

    public final void drawStrokeShape(Canvas canvas) {
        if (this.cutoutBounds.isEmpty()) {
            super.drawStrokeShape(canvas);
            return;
        }
        canvas.save();
        canvas.clipOutRect(this.cutoutBounds);
        super.drawStrokeShape(canvas);
        canvas.restore();
    }

    public final void setCutout(float f, float f2, float f3, float f4) {
        RectF rectF = this.cutoutBounds;
        if (f != rectF.left || f2 != rectF.top || f3 != rectF.right || f4 != rectF.bottom) {
            rectF.set(f, f2, f3, f4);
            invalidateSelf();
        }
    }
}
