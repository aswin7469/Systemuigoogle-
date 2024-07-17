package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TextAppearanceFontCallback;
import java.lang.ref.WeakReference;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TextDrawableHelper {
    public final WeakReference delegate = new WeakReference((Object) null);
    public final AnonymousClass1 fontCallback = new TextAppearanceFontCallback() {
        public final void onFontRetrievalFailed(int i) {
            TextDrawableHelper textDrawableHelper = TextDrawableHelper.this;
            textDrawableHelper.textWidthDirty = true;
            TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) textDrawableHelper.delegate.get();
            if (textDrawableDelegate != null) {
                textDrawableDelegate.onTextSizeChange();
            }
        }

        public final void onFontRetrieved(Typeface typeface, boolean z) {
            if (!z) {
                TextDrawableHelper textDrawableHelper = TextDrawableHelper.this;
                textDrawableHelper.textWidthDirty = true;
                TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) textDrawableHelper.delegate.get();
                if (textDrawableDelegate != null) {
                    textDrawableDelegate.onTextSizeChange();
                }
            }
        }
    };
    public TextAppearance textAppearance;
    public final TextPaint textPaint = new TextPaint(1);
    public float textWidth;
    public boolean textWidthDirty = true;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface TextDrawableDelegate {
        int[] getState();

        boolean onStateChange(int[] iArr);

        void onTextSizeChange();
    }

    public TextDrawableHelper(TextDrawableDelegate textDrawableDelegate) {
        this.delegate = new WeakReference(textDrawableDelegate);
    }

    public final float getTextWidth(String str) {
        float f;
        if (!this.textWidthDirty) {
            return this.textWidth;
        }
        if (str == null) {
            f = 0.0f;
        } else {
            f = this.textPaint.measureText(str, 0, str.length());
        }
        this.textWidth = f;
        this.textWidthDirty = false;
        return f;
    }

    public final void setTextAppearance(TextAppearance textAppearance2, Context context) {
        if (this.textAppearance != textAppearance2) {
            this.textAppearance = textAppearance2;
            if (textAppearance2 != null) {
                TextPaint textPaint2 = this.textPaint;
                AnonymousClass1 r1 = this.fontCallback;
                textAppearance2.updateMeasureState(context, textPaint2, r1);
                TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) this.delegate.get();
                if (textDrawableDelegate != null) {
                    textPaint2.drawableState = textDrawableDelegate.getState();
                }
                textAppearance2.updateDrawState(context, textPaint2, r1);
                this.textWidthDirty = true;
            }
            TextDrawableDelegate textDrawableDelegate2 = (TextDrawableDelegate) this.delegate.get();
            if (textDrawableDelegate2 != null) {
                textDrawableDelegate2.onTextSizeChange();
                textDrawableDelegate2.onStateChange(textDrawableDelegate2.getState());
            }
        }
    }
}
