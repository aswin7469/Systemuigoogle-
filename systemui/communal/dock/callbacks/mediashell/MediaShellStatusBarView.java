package com.google.android.systemui.communal.dock.callbacks.mediashell;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.shared.shadow.DoubleShadowTextView;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class MediaShellStatusBarView extends DoubleShadowTextView {
    public MediaShellStatusBarView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        throw new IllegalStateException("MediaShellInfo must be set before attaching");
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        throw new IllegalStateException("MediaShellInfo must be set before detaching");
    }

    public MediaShellStatusBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaShellStatusBarView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MediaShellStatusBarView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
