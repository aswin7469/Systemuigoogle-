package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Lifecycling$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.material.R$styleable;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior extends CoordinatorLayout.Behavior {
    public ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior() {
    }

    public final /* bridge */ /* synthetic */ boolean getInsetDodgeRect(View view) {
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

    public ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
        obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
    }

    public void setInternalAutoHideCallback(ExtendedFloatingActionButton$OnChangedCallback extendedFloatingActionButton$OnChangedCallback) {
    }

    public void setInternalAutoShrinkCallback(ExtendedFloatingActionButton$OnChangedCallback extendedFloatingActionButton$OnChangedCallback) {
    }
}
