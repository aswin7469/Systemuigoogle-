package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class AssistUIView extends FrameLayout {
    public AssistUIView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AssistUIView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AssistUIView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public AssistUIView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setClipChildren(false);
    }
}
