package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LinearProgressIndicatorSpec extends BaseProgressIndicatorSpec {
    public boolean drawHorizontallyInverse;
    public final int indeterminateAnimationType;
    public final int indicatorDirection;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LinearProgressIndicatorSpec(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 2130969467, 2132018815);
        int i = LinearProgressIndicator.$r8$clinit;
        int[] iArr = R$styleable.LinearProgressIndicator;
        boolean z = false;
        ThemeEnforcement.checkCompatibleTheme(context, attributeSet, 2130969467, 2132018815);
        ThemeEnforcement.checkTextAppearance(context, attributeSet, iArr, 2130969467, 2132018815, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 2130969467, 2132018815);
        this.indeterminateAnimationType = obtainStyledAttributes.getInt(0, 1);
        int i2 = obtainStyledAttributes.getInt(1, 0);
        this.indicatorDirection = i2;
        obtainStyledAttributes.recycle();
        validateSpec();
        this.drawHorizontallyInverse = i2 == 1 ? true : z;
    }

    public final void validateSpec() {
        if (this.indeterminateAnimationType != 0) {
            return;
        }
        if (this.trackCornerRadius > 0) {
            throw new IllegalArgumentException("Rounded corners are not supported in contiguous indeterminate animation.");
        } else if (this.indicatorColors.length < 3) {
            throw new IllegalArgumentException("Contiguous indeterminate animation must be used with 3 or more indicator colors.");
        }
    }
}
