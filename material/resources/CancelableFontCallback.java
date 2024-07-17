package com.google.android.material.resources;

import android.graphics.Typeface;
import com.google.android.material.internal.CollapsingTextHelper;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CancelableFontCallback extends TextAppearanceFontCallback {
    public final ApplyFont applyFont;
    public boolean cancelled;
    public final Typeface fallbackFont;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface ApplyFont {
    }

    public CancelableFontCallback(CollapsingTextHelper.AnonymousClass1 r1, Typeface typeface) {
        this.fallbackFont = typeface;
        this.applyFont = r1;
    }

    public final void onFontRetrievalFailed(int i) {
        updateIfNotCancelled(this.fallbackFont);
    }

    public final void onFontRetrieved(Typeface typeface, boolean z) {
        updateIfNotCancelled(typeface);
    }

    public final void updateIfNotCancelled(Typeface typeface) {
        if (!this.cancelled) {
            CollapsingTextHelper.AnonymousClass1 r2 = (CollapsingTextHelper.AnonymousClass1) this.applyFont;
            int i = 1;
            CollapsingTextHelper collapsingTextHelper = r2.this$0;
            switch (i) {
                case 0:
                    if (collapsingTextHelper.setCollapsedTypefaceInternal(typeface)) {
                        collapsingTextHelper.recalculate(false);
                        return;
                    }
                    return;
                default:
                    if (collapsingTextHelper.setExpandedTypefaceInternal(typeface)) {
                        collapsingTextHelper.recalculate(false);
                        return;
                    }
                    return;
            }
        }
    }
}
