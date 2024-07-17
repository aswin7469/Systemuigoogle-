package com.google.android.material.bottomappbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Lifecycling$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import java.lang.ref.WeakReference;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class BottomAppBar$Behavior extends HideBottomViewOnScrollBehavior {
    public final AnonymousClass1 fabLayoutListener;
    public final WeakReference viewRef;

    public BottomAppBar$Behavior() {
        new View.OnLayoutChangeListener() {
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(BottomAppBar$Behavior.this.viewRef.get());
                view.removeOnLayoutChangeListener(this);
            }
        };
        new Rect();
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        Lifecycling$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
        throw null;
    }

    public final boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2) {
        Lifecycling$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
        throw null;
    }

    public BottomAppBar$Behavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        new View.OnLayoutChangeListener() {
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(BottomAppBar$Behavior.this.viewRef.get());
                view.removeOnLayoutChangeListener(this);
            }
        };
        new Rect();
    }
}
