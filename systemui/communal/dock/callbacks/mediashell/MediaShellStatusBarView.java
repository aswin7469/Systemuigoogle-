package com.google.android.systemui.communal.dock.callbacks.mediashell;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.shared.shadow.DoubleShadowTextView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
