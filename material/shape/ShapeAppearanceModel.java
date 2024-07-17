package com.google.android.material.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.google.android.material.R$styleable;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ShapeAppearanceModel {
    public static final RelativeCornerSize PILL = new RelativeCornerSize(0.5f);
    public EdgeTreatment bottomEdge = new Object();
    public CornerTreatment bottomLeftCorner = new Object();
    public CornerSize bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
    public CornerTreatment bottomRightCorner = new Object();
    public CornerSize bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
    public EdgeTreatment leftEdge = new Object();
    public EdgeTreatment rightEdge = new Object();
    public EdgeTreatment topEdge = new Object();
    public CornerTreatment topLeftCorner = new Object();
    public CornerSize topLeftCornerSize = new AbsoluteCornerSize(0.0f);
    public CornerTreatment topRightCorner = new Object();
    public CornerSize topRightCornerSize = new AbsoluteCornerSize(0.0f);

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class Builder {
        public EdgeTreatment bottomEdge = new Object();
        public CornerTreatment bottomLeftCorner = new Object();
        public CornerSize bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
        public CornerTreatment bottomRightCorner = new Object();
        public CornerSize bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
        public EdgeTreatment leftEdge = new Object();
        public EdgeTreatment rightEdge = new Object();
        public EdgeTreatment topEdge = new Object();
        public CornerTreatment topLeftCorner = new Object();
        public CornerSize topLeftCornerSize = new AbsoluteCornerSize(0.0f);
        public CornerTreatment topRightCorner = new Object();
        public CornerSize topRightCornerSize = new AbsoluteCornerSize(0.0f);

        public static void compatCornerTreatmentSize(CornerTreatment cornerTreatment) {
            if (cornerTreatment instanceof RoundedCornerTreatment) {
                RoundedCornerTreatment roundedCornerTreatment = (RoundedCornerTreatment) cornerTreatment;
            } else if (cornerTreatment instanceof CutCornerTreatment) {
                CutCornerTreatment cutCornerTreatment = (CutCornerTreatment) cornerTreatment;
            }
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.google.android.material.shape.ShapeAppearanceModel] */
        public final ShapeAppearanceModel build() {
            ? obj = new Object();
            obj.topLeftCorner = this.topLeftCorner;
            obj.topRightCorner = this.topRightCorner;
            obj.bottomRightCorner = this.bottomRightCorner;
            obj.bottomLeftCorner = this.bottomLeftCorner;
            obj.topLeftCornerSize = this.topLeftCornerSize;
            obj.topRightCornerSize = this.topRightCornerSize;
            obj.bottomRightCornerSize = this.bottomRightCornerSize;
            obj.bottomLeftCornerSize = this.bottomLeftCornerSize;
            obj.topEdge = this.topEdge;
            obj.rightEdge = this.rightEdge;
            obj.bottomEdge = this.bottomEdge;
            obj.leftEdge = this.leftEdge;
            return obj;
        }

        public final void setAllCornerSizes(float f) {
            this.topLeftCornerSize = new AbsoluteCornerSize(f);
            this.topRightCornerSize = new AbsoluteCornerSize(f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(f);
        }
    }

    public static Builder builder(Context context, AttributeSet attributeSet, int i, int i2) {
        return builder(context, attributeSet, i, i2, new AbsoluteCornerSize((float) 0));
    }

    public static CornerSize getCornerSize(TypedArray typedArray, int i, CornerSize cornerSize) {
        TypedValue peekValue = typedArray.peekValue(i);
        if (peekValue == null) {
            return cornerSize;
        }
        int i2 = peekValue.type;
        if (i2 == 5) {
            return new AbsoluteCornerSize((float) TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics()));
        }
        if (i2 == 6) {
            return new RelativeCornerSize(peekValue.getFraction(1.0f, 1.0f));
        }
        return cornerSize;
    }

    public final boolean isRoundRect(RectF rectF) {
        boolean z;
        boolean z2;
        boolean z3;
        Class<EdgeTreatment> cls = EdgeTreatment.class;
        if (!this.leftEdge.getClass().equals(cls) || !this.rightEdge.getClass().equals(cls) || !this.topEdge.getClass().equals(cls) || !this.bottomEdge.getClass().equals(cls)) {
            z = false;
        } else {
            z = true;
        }
        float cornerSize = this.topLeftCornerSize.getCornerSize(rectF);
        if (this.topRightCornerSize.getCornerSize(rectF) == cornerSize && this.bottomLeftCornerSize.getCornerSize(rectF) == cornerSize && this.bottomRightCornerSize.getCornerSize(rectF) == cornerSize) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!(this.topRightCorner instanceof RoundedCornerTreatment) || !(this.topLeftCorner instanceof RoundedCornerTreatment) || !(this.bottomRightCorner instanceof RoundedCornerTreatment) || !(this.bottomLeftCorner instanceof RoundedCornerTreatment)) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (!z || !z2 || !z3) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.google.android.material.shape.ShapeAppearanceModel$Builder] */
    public final Builder toBuilder() {
        ? obj = new Object();
        obj.topLeftCorner = this.topLeftCorner;
        obj.topRightCorner = this.topRightCorner;
        obj.bottomRightCorner = this.bottomRightCorner;
        obj.bottomLeftCorner = this.bottomLeftCorner;
        obj.topLeftCornerSize = this.topLeftCornerSize;
        obj.topRightCornerSize = this.topRightCornerSize;
        obj.bottomRightCornerSize = this.bottomRightCornerSize;
        obj.bottomLeftCornerSize = this.bottomLeftCornerSize;
        obj.topEdge = this.topEdge;
        obj.rightEdge = this.rightEdge;
        obj.bottomEdge = this.bottomEdge;
        obj.leftEdge = this.leftEdge;
        return obj;
    }

    public static Builder builder(Context context, AttributeSet attributeSet, int i, int i2, CornerSize cornerSize) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        return builder(context, resourceId, resourceId2, cornerSize);
    }

    public static Builder builder(Context context, int i, int i2, CornerSize cornerSize) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
        if (i2 != 0) {
            contextThemeWrapper = new ContextThemeWrapper(contextThemeWrapper, i2);
        }
        TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(R$styleable.ShapeAppearance);
        try {
            int i3 = obtainStyledAttributes.getInt(0, 0);
            int i4 = obtainStyledAttributes.getInt(3, i3);
            int i5 = obtainStyledAttributes.getInt(4, i3);
            int i6 = obtainStyledAttributes.getInt(2, i3);
            int i7 = obtainStyledAttributes.getInt(1, i3);
            CornerSize cornerSize2 = getCornerSize(obtainStyledAttributes, 5, cornerSize);
            CornerSize cornerSize3 = getCornerSize(obtainStyledAttributes, 8, cornerSize2);
            CornerSize cornerSize4 = getCornerSize(obtainStyledAttributes, 9, cornerSize2);
            CornerSize cornerSize5 = getCornerSize(obtainStyledAttributes, 7, cornerSize2);
            CornerSize cornerSize6 = getCornerSize(obtainStyledAttributes, 6, cornerSize2);
            Builder builder = new Builder();
            CornerTreatment createCornerTreatment = MaterialShapeUtils.createCornerTreatment(i4);
            builder.topLeftCorner = createCornerTreatment;
            Builder.compatCornerTreatmentSize(createCornerTreatment);
            builder.topLeftCornerSize = cornerSize3;
            CornerTreatment createCornerTreatment2 = MaterialShapeUtils.createCornerTreatment(i5);
            builder.topRightCorner = createCornerTreatment2;
            Builder.compatCornerTreatmentSize(createCornerTreatment2);
            builder.topRightCornerSize = cornerSize4;
            CornerTreatment createCornerTreatment3 = MaterialShapeUtils.createCornerTreatment(i6);
            builder.bottomRightCorner = createCornerTreatment3;
            Builder.compatCornerTreatmentSize(createCornerTreatment3);
            builder.bottomRightCornerSize = cornerSize5;
            CornerTreatment createCornerTreatment4 = MaterialShapeUtils.createCornerTreatment(i7);
            builder.bottomLeftCorner = createCornerTreatment4;
            Builder.compatCornerTreatmentSize(createCornerTreatment4);
            builder.bottomLeftCornerSize = cornerSize6;
            return builder;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
