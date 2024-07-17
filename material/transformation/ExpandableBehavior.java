package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import java.util.List;
import java.util.WeakHashMap;

@Deprecated
/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ExpandableBehavior extends CoordinatorLayout.Behavior {
    public ExpandableBehavior() {
    }

    public abstract boolean layoutDependsOn(View view, View view2);

    public final boolean onDependentViewChanged(View view, View view2) {
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(view2);
        throw null;
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (!view.isLaidOut()) {
            List dependencies = coordinatorLayout.getDependencies(view);
            int size = dependencies.size();
            for (int i2 = 0; i2 < size; i2++) {
                layoutDependsOn(view, (View) dependencies.get(i2));
            }
        }
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
    }
}
