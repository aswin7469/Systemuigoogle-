package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class ZeroStateIconView extends FrameLayout {
    public final int COLOR_DARK_BACKGROUND;
    public final int COLOR_LIGHT_BACKGROUND;
    public ImageView mZeroStateIcon;

    public ZeroStateIconView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onFinishInflate() {
        this.mZeroStateIcon = (ImageView) findViewById(2131364126);
    }

    public ZeroStateIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ZeroStateIconView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ZeroStateIconView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.COLOR_DARK_BACKGROUND = getResources().getColor(2131100850);
        this.COLOR_LIGHT_BACKGROUND = getResources().getColor(2131100851);
    }
}
