package com.google.android.systemui.smartspace;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class DoubleShadowTextView extends TextView {
    public final float mAmbientShadowBlur;
    public final int mAmbientShadowColor;
    public boolean mDrawShadow;
    public final float mKeyShadowBlur;
    public final int mKeyShadowColor;
    public final float mKeyShadowOffsetX;
    public final float mKeyShadowOffsetY;

    public DoubleShadowTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onDraw(Canvas canvas) {
        if (!this.mDrawShadow) {
            getPaint().clearShadowLayer();
            super.onDraw(canvas);
            return;
        }
        getPaint().setShadowLayer(this.mAmbientShadowBlur, 0.0f, 0.0f, this.mAmbientShadowColor);
        super.onDraw(canvas);
        canvas.save();
        canvas.clipRect(getScrollX(), getExtendedPaddingTop() + getScrollY(), getWidth() + getScrollX(), getHeight() + getScrollY());
        getPaint().setShadowLayer(this.mKeyShadowBlur, this.mKeyShadowOffsetX, this.mKeyShadowOffsetY, this.mKeyShadowColor);
        super.onDraw(canvas);
        canvas.restore();
    }

    public final void setTextColor(int i) {
        boolean z;
        super.setTextColor(i);
        if (ColorUtils.calculateLuminance(i) > 0.5d) {
            z = true;
        } else {
            z = false;
        }
        this.mDrawShadow = z;
    }

    public DoubleShadowTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DoubleShadowTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDrawShadow = ColorUtils.calculateLuminance(getCurrentTextColor()) > 0.5d;
        this.mKeyShadowBlur = (float) context.getResources().getDimensionPixelSize(2131165992);
        this.mKeyShadowOffsetX = (float) context.getResources().getDimensionPixelSize(2131165990);
        this.mKeyShadowOffsetY = (float) context.getResources().getDimensionPixelSize(2131165991);
        this.mKeyShadowColor = context.getResources().getColor(2131099905);
        this.mAmbientShadowBlur = (float) context.getResources().getDimensionPixelSize(2131165334);
        this.mAmbientShadowColor = context.getResources().getColor(2131099706);
    }
}
