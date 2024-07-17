package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LinearProgressIndicator extends BaseProgressIndicator {
    public static final /* synthetic */ int $r8$clinit = 0;

    public LinearProgressIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 2130969467, 2132018815);
        IndeterminateAnimatorDelegate indeterminateAnimatorDelegate;
        Context context2 = getContext();
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) this.spec;
        LinearDrawingDelegate linearDrawingDelegate = new LinearDrawingDelegate(linearProgressIndicatorSpec);
        if (linearProgressIndicatorSpec.indeterminateAnimationType == 0) {
            indeterminateAnimatorDelegate = new LinearIndeterminateContiguousAnimatorDelegate(linearProgressIndicatorSpec);
        } else {
            indeterminateAnimatorDelegate = new LinearIndeterminateDisjointAnimatorDelegate(context2, linearProgressIndicatorSpec);
        }
        setIndeterminateDrawable(new IndeterminateDrawable(context2, linearProgressIndicatorSpec, linearDrawingDelegate, indeterminateAnimatorDelegate));
        Context context3 = getContext();
        LinearProgressIndicatorSpec linearProgressIndicatorSpec2 = (LinearProgressIndicatorSpec) this.spec;
        setProgressDrawable(new DeterminateDrawable(context3, linearProgressIndicatorSpec2, new LinearDrawingDelegate(linearProgressIndicatorSpec2)));
    }

    public final BaseProgressIndicatorSpec createSpec(Context context, AttributeSet attributeSet) {
        return new LinearProgressIndicatorSpec(context, attributeSet);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.spec;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) baseProgressIndicatorSpec;
        boolean z2 = true;
        if (((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorDirection != 1) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            if (!((ViewCompat.Api17Impl.getLayoutDirection(this) == 1 && ((LinearProgressIndicatorSpec) this.spec).indicatorDirection == 2) || (ViewCompat.Api17Impl.getLayoutDirection(this) == 0 && ((LinearProgressIndicatorSpec) this.spec).indicatorDirection == 3))) {
                z2 = false;
            }
        }
        linearProgressIndicatorSpec.drawHorizontallyInverse = z2;
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        int paddingRight = i - (getPaddingRight() + getPaddingLeft());
        int paddingBottom = i2 - (getPaddingBottom() + getPaddingTop());
        IndeterminateDrawable indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null) {
            indeterminateDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
        DeterminateDrawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
    }

    public final void setProgressCompat(int i, boolean z) {
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.spec;
        if (baseProgressIndicatorSpec == null || ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).indeterminateAnimationType != 0 || !isIndeterminate()) {
            super.setProgressCompat(i, z);
        }
    }
}
