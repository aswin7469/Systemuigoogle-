package com.google.android.material.internal;

import android.graphics.Typeface;
import android.text.TextPaint;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TextAppearanceFontCallback;
import java.lang.ref.WeakReference;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class TextDrawableHelper {
    public final WeakReference delegate = new WeakReference((Object) null);
    public final AnonymousClass1 fontCallback = new TextAppearanceFontCallback() {
        public final void onFontRetrievalFailed(int i) {
            TextDrawableHelper textDrawableHelper = TextDrawableHelper.this;
            textDrawableHelper.textWidthDirty = true;
            TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) textDrawableHelper.delegate.get();
            if (textDrawableDelegate != null) {
                ChipDrawable chipDrawable = (ChipDrawable) textDrawableDelegate;
                chipDrawable.onSizeChange();
                chipDrawable.invalidateSelf();
            }
        }

        public final void onFontRetrieved(Typeface typeface, boolean z) {
            if (!z) {
                TextDrawableHelper textDrawableHelper = TextDrawableHelper.this;
                textDrawableHelper.textWidthDirty = true;
                TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) textDrawableHelper.delegate.get();
                if (textDrawableDelegate != null) {
                    ChipDrawable chipDrawable = (ChipDrawable) textDrawableDelegate;
                    chipDrawable.onSizeChange();
                    chipDrawable.invalidateSelf();
                }
            }
        }
    };
    public TextAppearance textAppearance;
    public final TextPaint textPaint = new TextPaint(1);
    public float textWidth;
    public boolean textWidthDirty = true;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public interface TextDrawableDelegate {
        int[] getState();
    }

    public TextDrawableHelper(TextDrawableDelegate textDrawableDelegate) {
        this.delegate = new WeakReference(textDrawableDelegate);
    }
}
