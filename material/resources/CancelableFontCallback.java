package com.google.android.material.resources;

import android.graphics.Typeface;
import com.google.android.material.internal.CollapsingTextHelper;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CancelableFontCallback extends TextAppearanceFontCallback {
    public final CollapsingTextHelper.AnonymousClass1 applyFont;
    public boolean cancelled;
    public final Typeface fallbackFont;

    public CancelableFontCallback(CollapsingTextHelper.AnonymousClass1 r1, Typeface typeface) {
        this.fallbackFont = typeface;
        this.applyFont = r1;
    }

    public final void onFontRetrievalFailed(int i) {
        if (!this.cancelled) {
            this.applyFont.apply(this.fallbackFont);
        }
    }

    public final void onFontRetrieved(Typeface typeface, boolean z) {
        if (!this.cancelled) {
            this.applyFont.apply(typeface);
        }
    }
}
