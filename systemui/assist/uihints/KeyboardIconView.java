package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class KeyboardIconView extends FrameLayout {
    public final int COLOR_DARK_BACKGROUND;
    public final int COLOR_LIGHT_BACKGROUND;
    public ImageView mKeyboardIcon;

    public KeyboardIconView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onFinishInflate() {
        this.mKeyboardIcon = (ImageView) findViewById(2131362787);
    }

    public KeyboardIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public KeyboardIconView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public KeyboardIconView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.COLOR_DARK_BACKGROUND = getResources().getColor(2131100868);
        this.COLOR_LIGHT_BACKGROUND = getResources().getColor(2131100869);
    }
}
