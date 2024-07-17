package com.google.android.setupdesign.span;

import android.content.Context;
import android.text.TextPaint;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
