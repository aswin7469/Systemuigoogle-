package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class BaseProgressIndicatorSpec {
    public final int hideAnimationBehavior;
    public final int[] indicatorColors = new int[0];
    public final int showAnimationBehavior;
    public final int trackColor;
    public final int trackCornerRadius;
    public final int trackThickness;

    public BaseProgressIndicatorSpec(Context context, AttributeSet attributeSet, int i, int i2) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(2131166875);
        int[] iArr = R$styleable.BaseProgressIndicator;
        ThemeEnforcement.checkCompatibleTheme(context, attributeSet, i, i2);
        ThemeEnforcement.checkTextAppearance(context, attributeSet, iArr, i, i2, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, i2);
        int dimensionPixelSize2 = MaterialResources.getDimensionPixelSize(context, obtainStyledAttributes, 8, dimensionPixelSize);
        this.trackThickness = dimensionPixelSize2;
        this.trackCornerRadius = Math.min(MaterialResources.getDimensionPixelSize(context, obtainStyledAttributes, 7, 0), dimensionPixelSize2 / 2);
        this.showAnimationBehavior = obtainStyledAttributes.getInt(4, 0);
        this.hideAnimationBehavior = obtainStyledAttributes.getInt(1, 0);
        if (!obtainStyledAttributes.hasValue(2)) {
            this.indicatorColors = new int[]{MaterialColors.getColor(context, 2130968878, -1)};
        } else if (obtainStyledAttributes.peekValue(2).type != 1) {
            this.indicatorColors = new int[]{obtainStyledAttributes.getColor(2, -1)};
        } else {
            int[] intArray = context.getResources().getIntArray(obtainStyledAttributes.getResourceId(2, -1));
            this.indicatorColors = intArray;
            if (intArray.length == 0) {
                throw new IllegalArgumentException("indicatorColors cannot be empty when indicatorColor is not used.");
            }
        }
        if (obtainStyledAttributes.hasValue(6)) {
            this.trackColor = obtainStyledAttributes.getColor(6, -1);
        } else {
            this.trackColor = this.indicatorColors[0];
            TypedArray obtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(new int[]{16842803});
            float f = obtainStyledAttributes2.getFloat(0, 0.2f);
            obtainStyledAttributes2.recycle();
            this.trackColor = MaterialColors.compositeARGBWithAlpha(this.trackColor, (int) (f * 255.0f));
        }
        obtainStyledAttributes.recycle();
    }

    public abstract void validateSpec();
}
