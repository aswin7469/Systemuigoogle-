package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class ChipView extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Drawable BACKGROUND_DARK;
    public final Drawable BACKGROUND_LIGHT;
    public final int TEXT_COLOR_DARK;
    public final int TEXT_COLOR_LIGHT;
    public LinearLayout mChip;
    public ImageView mIconView;
    public TextView mLabelView;
    public Space mSpaceView;

    public ChipView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onFinishInflate() {
        LinearLayout linearLayout = (LinearLayout) findViewById(2131362257);
        linearLayout.getClass();
        this.mChip = linearLayout;
        ImageView imageView = (ImageView) findViewById(2131362260);
        imageView.getClass();
        this.mIconView = imageView;
        TextView textView = (TextView) findViewById(2131362261);
        textView.getClass();
        this.mLabelView = textView;
        Space space = (Space) findViewById(2131362259);
        space.getClass();
        this.mSpaceView = space;
    }

    public ChipView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChipView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ChipView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.BACKGROUND_DARK = context.getDrawable(2131232242);
        this.BACKGROUND_LIGHT = context.getDrawable(2131232243);
        this.TEXT_COLOR_DARK = context.getColor(2131099715);
        this.TEXT_COLOR_LIGHT = context.getColor(2131099716);
    }
}
