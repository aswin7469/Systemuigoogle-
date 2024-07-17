package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.transformation.FabTransformationBehavior;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

@Deprecated
/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class FabTransformationSheetBehavior extends FabTransformationBehavior {
    public Map importantForAccessibilityMap;

    public FabTransformationSheetBehavior() {
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.Object, com.google.android.material.transformation.FabTransformationBehavior$FabTransformationSpec] */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Object, com.google.android.material.animation.Positioning] */
    public final FabTransformationBehavior.FabTransformationSpec onCreateMotionSpec(Context context, boolean z) {
        int i;
        if (z) {
            i = 2130837555;
        } else {
            i = 2130837554;
        }
        ? obj = new Object();
        obj.timings = MotionSpec.createFromResource(i, context);
        obj.positioning = new Object();
        return obj;
    }

    public final void onExpandedStateChange(View view, View view2, boolean z, boolean z2) {
        boolean z3;
        ViewParent parent = view2.getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                this.importantForAccessibilityMap = new HashMap(childCount);
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (!(childAt.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) || !(((CoordinatorLayout.LayoutParams) childAt.getLayoutParams()).mBehavior instanceof FabTransformationScrimBehavior)) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (childAt != view2 && !z3) {
                    if (!z) {
                        Map map = this.importantForAccessibilityMap;
                        if (map != null && map.containsKey(childAt)) {
                            int intValue = ((Integer) this.importantForAccessibilityMap.get(childAt)).intValue();
                            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                            ViewCompat.Api16Impl.setImportantForAccessibility(childAt, intValue);
                        }
                    } else {
                        this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api16Impl.setImportantForAccessibility(childAt, 4);
                    }
                }
            }
            if (!z) {
                this.importantForAccessibilityMap = null;
            }
        }
        super.onExpandedStateChange(view, view2, z, z2);
    }

    public FabTransformationSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
