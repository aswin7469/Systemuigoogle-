package com.google.android.systemui.assist.uihints;

import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;
import com.google.android.systemui.assist.uihints.TranscriptionController;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class GreetingView extends TextView implements TranscriptionController.TranscriptionSpaceView {
    public final int TEXT_COLOR_DARK;
    public final int TEXT_COLOR_LIGHT;
    public final AnimatorSet mAnimatorSet;

    public GreetingView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final ListenableFuture hide(boolean z) {
        if (this.mAnimatorSet.isRunning()) {
            this.mAnimatorSet.cancel();
        }
        setVisibility(8);
        return ImmediateFuture.NULL;
    }

    public final void onFontSizeChanged() {
        setTextSize(0, this.mContext.getResources().getDimension(2131167791));
    }

    public final void setHasDarkBackground(boolean z) {
        int i;
        if (z) {
            i = this.TEXT_COLOR_DARK;
        } else {
            i = this.TEXT_COLOR_LIGHT;
        }
        setTextColor(i);
        Color.alpha(getCurrentTextColor());
    }

    public GreetingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GreetingView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public GreetingView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        new SpannableStringBuilder();
        new ArrayList();
        this.mAnimatorSet = new AnimatorSet();
        this.TEXT_COLOR_DARK = getResources().getColor(2131100870);
        this.TEXT_COLOR_LIGHT = getResources().getColor(2131100871);
        getResources().getDimension(2131165351);
        Color.alpha(getCurrentTextColor());
    }
}
