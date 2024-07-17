package com.google.android.setupdesign.span;

import android.content.Context;
import android.text.TextPaint;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BoldLinkSpan extends LinkSpan {
    static final int BOLD_TEXT_ADJUSTMENT = 300;
    public final Context context;

    public BoldLinkSpan(Context context2) {
        this.context = context2;
    }

    public final void updateDrawState(TextPaint textPaint) {
        boolean z;
        super.updateDrawState(textPaint);
        if (this.context.getResources().getConfiguration().fontWeightAdjustment == BOLD_TEXT_ADJUSTMENT) {
            z = true;
        } else {
            z = false;
        }
        textPaint.setFakeBoldText(z);
        textPaint.setUnderlineText(true);
    }
}
