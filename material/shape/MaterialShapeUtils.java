package com.google.android.material.shape;

import android.view.View;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class MaterialShapeUtils {
    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, com.google.android.material.shape.CornerTreatment] */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Object, com.google.android.material.shape.CornerTreatment] */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.Object, com.google.android.material.shape.CornerTreatment] */
    public static CornerTreatment createCornerTreatment(int i) {
        if (i == 0) {
            return new Object();
        }
        if (i != 1) {
            return new Object();
        }
        return new Object();
    }

    public static void setParentAbsoluteElevation(View view, MaterialShapeDrawable materialShapeDrawable) {
        ElevationOverlayProvider elevationOverlayProvider = materialShapeDrawable.drawableState.elevationOverlayProvider;
        if (elevationOverlayProvider != null && elevationOverlayProvider.elevationOverlayEnabled) {
            float f = 0.0f;
            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                f += ViewCompat.Api21Impl.getElevation((View) parent);
            }
            MaterialShapeDrawable.MaterialShapeDrawableState materialShapeDrawableState = materialShapeDrawable.drawableState;
            if (materialShapeDrawableState.parentAbsoluteElevation != f) {
                materialShapeDrawableState.parentAbsoluteElevation = f;
                materialShapeDrawable.updateZ();
            }
        }
    }
}
