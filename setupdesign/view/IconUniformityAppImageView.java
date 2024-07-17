package com.google.android.setupdesign.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class IconUniformityAppImageView extends ImageView {
    public int backdropColorResId = 0;
    public final GradientDrawable backdropDrawable = new GradientDrawable();

    public IconUniformityAppImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        super.onDraw(canvas);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.backdropColorResId = 2131100823;
        GradientDrawable gradientDrawable = this.backdropDrawable;
        Context context = getContext();
        int i = this.backdropColorResId;
        Object obj = ContextCompat.sLock;
        gradientDrawable.setColor(context.getColor(i));
    }
}
