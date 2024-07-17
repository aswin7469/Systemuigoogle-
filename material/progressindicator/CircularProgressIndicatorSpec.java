package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CircularProgressIndicatorSpec extends BaseProgressIndicatorSpec {
    public final int indicatorDirection;
    public final int indicatorInset;
    public final int indicatorSize;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CircularProgressIndicatorSpec(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 2130968819, 2132018803);
        int i = CircularProgressIndicator.$r8$clinit;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(2131166869);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(2131166864);
        int[] iArr = R$styleable.CircularProgressIndicator;
        ThemeEnforcement.checkCompatibleTheme(context, attributeSet, 2130968819, 2132018803);
        ThemeEnforcement.checkTextAppearance(context, attributeSet, iArr, 2130968819, 2132018803, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 2130968819, 2132018803);
        this.indicatorSize = Math.max(MaterialResources.getDimensionPixelSize(context, obtainStyledAttributes, 2, dimensionPixelSize), this.trackThickness * 2);
        this.indicatorInset = MaterialResources.getDimensionPixelSize(context, obtainStyledAttributes, 1, dimensionPixelSize2);
        this.indicatorDirection = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
    }

    public final void validateSpec() {
    }
}
