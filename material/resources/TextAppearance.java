package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
import androidx.core.content.res.ResourcesCompat;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.google.android.material.R$styleable;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TextAppearance {
    public Typeface font;
    public final String fontFamily;
    public final int fontFamilyResourceId;
    public boolean fontResolved = false;
    public final boolean hasLetterSpacing;
    public final float letterSpacing;
    public final ColorStateList shadowColor;
    public final float shadowDx;
    public final float shadowDy;
    public final float shadowRadius;
    public ColorStateList textColor;
    public float textSize;
    public final int textStyle;
    public final int typeface;

    public TextAppearance(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.TextAppearance);
        this.textSize = obtainStyledAttributes.getDimension(0, 0.0f);
        this.textColor = MaterialResources.getColorStateList(context, obtainStyledAttributes, 3);
        MaterialResources.getColorStateList(context, obtainStyledAttributes, 4);
        MaterialResources.getColorStateList(context, obtainStyledAttributes, 5);
        this.textStyle = obtainStyledAttributes.getInt(2, 0);
        this.typeface = obtainStyledAttributes.getInt(1, 1);
        int i2 = !obtainStyledAttributes.hasValue(12) ? 10 : 12;
        this.fontFamilyResourceId = obtainStyledAttributes.getResourceId(i2, 0);
        this.fontFamily = obtainStyledAttributes.getString(i2);
        obtainStyledAttributes.getBoolean(14, false);
        this.shadowColor = MaterialResources.getColorStateList(context, obtainStyledAttributes, 6);
        this.shadowDx = obtainStyledAttributes.getFloat(7, 0.0f);
        this.shadowDy = obtainStyledAttributes.getFloat(8, 0.0f);
        this.shadowRadius = obtainStyledAttributes.getFloat(9, 0.0f);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(i, R$styleable.MaterialTextAppearance);
        this.hasLetterSpacing = obtainStyledAttributes2.hasValue(0);
        this.letterSpacing = obtainStyledAttributes2.getFloat(0, 0.0f);
        obtainStyledAttributes2.recycle();
    }

    public final void createFallbackFont() {
        String str;
        Typeface typeface2 = this.font;
        int i = this.textStyle;
        if (typeface2 == null && (str = this.fontFamily) != null) {
            this.font = Typeface.create(str, i);
        }
        if (this.font == null) {
            int i2 = this.typeface;
            if (i2 == 1) {
                this.font = Typeface.SANS_SERIF;
            } else if (i2 == 2) {
                this.font = Typeface.SERIF;
            } else if (i2 != 3) {
                this.font = Typeface.DEFAULT;
            } else {
                this.font = Typeface.MONOSPACE;
            }
            this.font = Typeface.create(this.font, i);
        }
    }

    public Typeface getFont(Context context) {
        Typeface typeface2;
        if (this.fontResolved) {
            return this.font;
        }
        if (!context.isRestricted()) {
            try {
                int i = this.fontFamilyResourceId;
                ThreadLocal threadLocal = ResourcesCompat.sTempTypedValue;
                if (context.isRestricted()) {
                    typeface2 = null;
                } else {
                    typeface2 = ResourcesCompat.loadFont(context, i, new TypedValue(), 0, (ResourcesCompat.FontCallback) null, false, false);
                }
                this.font = typeface2;
                if (typeface2 != null) {
                    this.font = Typeface.create(typeface2, this.textStyle);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e) {
                Log.d("TextAppearance", "Error loading font " + this.fontFamily, e);
            }
        }
        createFallbackFont();
        this.fontResolved = true;
        return this.font;
    }

    public final void getFontAsync(Context context, final TextAppearanceFontCallback textAppearanceFontCallback) {
        if (shouldLoadFontSynchronously(context)) {
            getFont(context);
        } else {
            createFallbackFont();
        }
        int i = this.fontFamilyResourceId;
        if (i == 0) {
            this.fontResolved = true;
        }
        if (this.fontResolved) {
            textAppearanceFontCallback.onFontRetrieved(this.font, true);
            return;
        }
        try {
            AnonymousClass1 r5 = new ResourcesCompat.FontCallback() {
                public final void onFontRetrievalFailed(int i) {
                    TextAppearance.this.fontResolved = true;
                    textAppearanceFontCallback.onFontRetrievalFailed(i);
                }

                public final void onFontRetrieved(Typeface typeface) {
                    TextAppearance textAppearance = TextAppearance.this;
                    textAppearance.font = Typeface.create(typeface, textAppearance.textStyle);
                    textAppearance.fontResolved = true;
                    textAppearanceFontCallback.onFontRetrieved(textAppearance.font, false);
                }
            };
            ThreadLocal threadLocal = ResourcesCompat.sTempTypedValue;
            if (context.isRestricted()) {
                r5.callbackFailAsync(-4);
                return;
            }
            ResourcesCompat.loadFont(context, i, new TypedValue(), 0, r5, false, false);
        } catch (Resources.NotFoundException unused) {
            this.fontResolved = true;
            textAppearanceFontCallback.onFontRetrievalFailed(1);
        } catch (Exception e) {
            Log.d("TextAppearance", "Error loading font " + this.fontFamily, e);
            this.fontResolved = true;
            textAppearanceFontCallback.onFontRetrievalFailed(-3);
        }
    }

    public final boolean shouldLoadFontSynchronously(Context context) {
        Typeface typeface2 = null;
        int i = this.fontFamilyResourceId;
        if (i != 0) {
            ThreadLocal threadLocal = ResourcesCompat.sTempTypedValue;
            if (!context.isRestricted()) {
                typeface2 = ResourcesCompat.loadFont(context, i, new TypedValue(), 0, (ResourcesCompat.FontCallback) null, false, true);
            }
        }
        if (typeface2 != null) {
            return true;
        }
        return false;
    }

    public final void updateDrawState(Context context, TextPaint textPaint, TextAppearanceFontCallback textAppearanceFontCallback) {
        int i;
        int i2;
        updateMeasureState(context, textPaint, textAppearanceFontCallback);
        ColorStateList colorStateList = this.textColor;
        if (colorStateList != null) {
            i = colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor());
        } else {
            i = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
        }
        textPaint.setColor(i);
        ColorStateList colorStateList2 = this.shadowColor;
        if (colorStateList2 != null) {
            i2 = colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor());
        } else {
            i2 = 0;
        }
        textPaint.setShadowLayer(this.shadowRadius, this.shadowDx, this.shadowDy, i2);
    }

    public final void updateMeasureState(final Context context, final TextPaint textPaint, final TextAppearanceFontCallback textAppearanceFontCallback) {
        if (shouldLoadFontSynchronously(context)) {
            updateTextPaintMeasureState(context, textPaint, getFont(context));
            return;
        }
        createFallbackFont();
        updateTextPaintMeasureState(context, textPaint, this.font);
        getFontAsync(context, new TextAppearanceFontCallback() {
            public final void onFontRetrievalFailed(int i) {
                textAppearanceFontCallback.onFontRetrievalFailed(i);
            }

            public final void onFontRetrieved(Typeface typeface, boolean z) {
                TextAppearance.this.updateTextPaintMeasureState(context, textPaint, typeface);
                textAppearanceFontCallback.onFontRetrieved(typeface, z);
            }
        });
    }

    public final void updateTextPaintMeasureState(Context context, TextPaint textPaint, Typeface typeface2) {
        boolean z;
        float f;
        Typeface maybeCopyWithFontWeightAdjustment = TypefaceUtils.maybeCopyWithFontWeightAdjustment(context.getResources().getConfiguration(), typeface2);
        if (maybeCopyWithFontWeightAdjustment != null) {
            typeface2 = maybeCopyWithFontWeightAdjustment;
        }
        textPaint.setTypeface(typeface2);
        int i = (~typeface2.getStyle()) & this.textStyle;
        if ((i & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        textPaint.setFakeBoldText(z);
        if ((i & 2) != 0) {
            f = -0.25f;
        } else {
            f = 0.0f;
        }
        textPaint.setTextSkewX(f);
        textPaint.setTextSize(this.textSize);
        if (this.hasLetterSpacing) {
            textPaint.setLetterSpacing(this.letterSpacing);
        }
    }
}
