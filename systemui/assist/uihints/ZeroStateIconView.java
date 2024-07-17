package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class ZeroStateIconView extends FrameLayout {
    public final int COLOR_DARK_BACKGROUND;
    public final int COLOR_LIGHT_BACKGROUND;
    public ImageView mZeroStateIcon;

    public ZeroStateIconView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onFinishInflate() {
        this.mZeroStateIcon = (ImageView) findViewById(2131364164);
    }

    public ZeroStateIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ZeroStateIconView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ZeroStateIconView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.COLOR_DARK_BACKGROUND = getResources().getColor(2131100868);
        this.COLOR_LIGHT_BACKGROUND = getResources().getColor(2131100869);
    }
}
