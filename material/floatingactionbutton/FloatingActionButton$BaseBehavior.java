package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Lifecycling$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.material.R$styleable;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class FloatingActionButton$BaseBehavior extends CoordinatorLayout.Behavior {
    public FloatingActionButton$BaseBehavior() {
    }

    public final boolean getInsetDodgeRect(View view) {
        Lifecycling$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
        throw null;
    }

    public final void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        if (layoutParams.dodgeInsetEdges == 0) {
            layoutParams.dodgeInsetEdges = 80;
        }
    }

    public final boolean onDependentViewChanged(View view, View view2) {
        Lifecycling$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
        throw null;
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        Lifecycling$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
        throw null;
    }

    public FloatingActionButton$BaseBehavior(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FloatingActionButton_Behavior_Layout);
        obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
    }

    public void setInternalAutoHideListener(FloatingActionButton$OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener) {
    }
}
