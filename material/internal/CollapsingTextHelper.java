package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.resources.CancelableFontCallback;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TypefaceUtils;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CollapsingTextHelper {
    public boolean boundsChanged;
    public final Rect collapsedBounds;
    public float collapsedDrawX;
    public float collapsedDrawY;
    public CancelableFontCallback collapsedFontCallback;
    public float collapsedLetterSpacing;
    public ColorStateList collapsedShadowColor;
    public float collapsedShadowDx;
    public float collapsedShadowDy;
    public float collapsedShadowRadius;
    public float collapsedTextBlend;
    public ColorStateList collapsedTextColor;
    public int collapsedTextGravity = 16;
    public float collapsedTextSize = 15.0f;
    public float collapsedTextWidth;
    public Typeface collapsedTypeface;
    public Typeface collapsedTypefaceBold;
    public Typeface collapsedTypefaceDefault;
    public final RectF currentBounds;
    public float currentDrawX;
    public float currentDrawY;
    public float currentLetterSpacing;
    public int currentOffsetY;
    public int currentShadowColor;
    public float currentShadowDx;
    public float currentShadowDy;
    public float currentShadowRadius;
    public float currentTextSize;
    public Typeface currentTypeface;
    public final Rect expandedBounds;
    public float expandedDrawX;
    public float expandedDrawY;
    public CancelableFontCallback expandedFontCallback;
    public float expandedFraction;
    public float expandedLetterSpacing;
    public int expandedLineCount;
    public ColorStateList expandedShadowColor;
    public float expandedShadowDx;
    public float expandedShadowDy;
    public float expandedShadowRadius;
    public float expandedTextBlend;
    public ColorStateList expandedTextColor;
    public int expandedTextGravity = 16;
    public float expandedTextSize = 15.0f;
    public Bitmap expandedTitleTexture;
    public Typeface expandedTypeface;
    public Typeface expandedTypefaceBold;
    public Typeface expandedTypefaceDefault;
    public boolean fadeModeEnabled;
    public float fadeModeStartFraction;
    public float fadeModeThresholdFraction;
    public final int hyphenationFrequency = 1;
    public boolean isRtl;
    public boolean isRtlTextDirectionHeuristicsEnabled = true;
    public final float lineSpacingMultiplier = 1.0f;
    public int maxLines = 1;
    public TimeInterpolator positionInterpolator;
    public float scale;
    public int[] state;
    public CharSequence text;
    public StaticLayout textLayout;
    public final TextPaint textPaint;
    public TimeInterpolator textSizeInterpolator;
    public CharSequence textToDraw;
    public CharSequence textToDrawCollapsed;
    public TextUtils.TruncateAt titleTextEllipsize = TextUtils.TruncateAt.END;
    public final TextPaint tmpPaint;
    public final View view;

    public CollapsingTextHelper(View view2) {
        this.view = view2;
        TextPaint textPaint2 = new TextPaint(129);
        this.textPaint = textPaint2;
        this.tmpPaint = new TextPaint(textPaint2);
        this.collapsedBounds = new Rect();
        this.expandedBounds = new Rect();
        this.currentBounds = new RectF();
        float f = this.fadeModeStartFraction;
        this.fadeModeThresholdFraction = AndroidFlingSpline$$ExternalSyntheticOutline0.m(1.0f, f, 0.5f, f);
        maybeUpdateFontWeightAdjustment(view2.getContext().getResources().getConfiguration());
    }

    public static int blendARGB(int i, float f, int i2) {
        float f2 = 1.0f - f;
        return Color.argb(Math.round((((float) Color.alpha(i2)) * f) + (((float) Color.alpha(i)) * f2)), Math.round((((float) Color.red(i2)) * f) + (((float) Color.red(i)) * f2)), Math.round((((float) Color.green(i2)) * f) + (((float) Color.green(i)) * f2)), Math.round((((float) Color.blue(i2)) * f) + (((float) Color.blue(i)) * f2)));
    }

    public static float lerp(float f, float f2, float f3, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f3 = timeInterpolator.getInterpolation(f3);
        }
        TimeInterpolator timeInterpolator2 = AnimationUtils.LINEAR_INTERPOLATOR;
        return AndroidFlingSpline$$ExternalSyntheticOutline0.m(f2, f, f3, f);
    }

    public final boolean calculateIsRtl(CharSequence charSequence) {
        TextDirectionHeuristicsCompat.TextDirectionHeuristicInternal textDirectionHeuristicInternal;
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        boolean z = true;
        if (this.view.getLayoutDirection() != 1) {
            z = false;
        }
        if (!this.isRtlTextDirectionHeuristicsEnabled) {
            return z;
        }
        if (z) {
            textDirectionHeuristicInternal = TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
        } else {
            textDirectionHeuristicInternal = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        }
        return textDirectionHeuristicInternal.isRtl(charSequence.length(), charSequence);
    }

    public final void calculateUsingTextSize(float f, boolean z) {
        boolean z2;
        float f2;
        float f3;
        Typeface typeface;
        boolean z3;
        Layout.Alignment alignment;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        if (this.text != null) {
            float width = (float) this.collapsedBounds.width();
            float width2 = (float) this.expandedBounds.width();
            if (Math.abs(f - 1.0f) < 1.0E-5f) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                f3 = this.collapsedTextSize;
                f2 = this.collapsedLetterSpacing;
                this.scale = 1.0f;
                typeface = this.collapsedTypeface;
            } else {
                float f4 = this.expandedTextSize;
                float f5 = this.expandedLetterSpacing;
                Typeface typeface2 = this.expandedTypeface;
                if (Math.abs(f - 0.0f) < 1.0E-5f) {
                    this.scale = 1.0f;
                } else {
                    this.scale = lerp(this.expandedTextSize, this.collapsedTextSize, f, this.textSizeInterpolator) / this.expandedTextSize;
                }
                float f6 = this.collapsedTextSize / this.expandedTextSize;
                float f7 = width2 * f6;
                if (!z && f7 > width) {
                    width = Math.min(width / f6, width2);
                } else {
                    width = width2;
                }
                f3 = f4;
                f2 = f5;
                typeface = typeface2;
            }
            int i = (width > 0.0f ? 1 : (width == 0.0f ? 0 : -1));
            TextPaint textPaint2 = this.textPaint;
            if (i > 0) {
                if (this.currentTextSize != f3) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (this.currentLetterSpacing != f2) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (this.currentTypeface != typeface) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                StaticLayout staticLayout = this.textLayout;
                if (staticLayout == null || width == ((float) staticLayout.getWidth())) {
                    z7 = false;
                } else {
                    z7 = true;
                }
                if (z4 || z5 || z7 || z6 || this.boundsChanged) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.currentTextSize = f3;
                this.currentLetterSpacing = f2;
                this.currentTypeface = typeface;
                this.boundsChanged = false;
                if (this.scale != 1.0f) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                textPaint2.setLinearText(z8);
            } else {
                z3 = false;
            }
            if (this.textToDraw == null || z3) {
                textPaint2.setTextSize(this.currentTextSize);
                textPaint2.setTypeface(this.currentTypeface);
                textPaint2.setLetterSpacing(this.currentLetterSpacing);
                boolean calculateIsRtl = calculateIsRtl(this.text);
                this.isRtl = calculateIsRtl;
                int i2 = this.maxLines;
                if (i2 <= 1 || (calculateIsRtl && !this.fadeModeEnabled)) {
                    i2 = 1;
                }
                if (i2 == 1) {
                    alignment = Layout.Alignment.ALIGN_NORMAL;
                } else {
                    int absoluteGravity = Gravity.getAbsoluteGravity(this.expandedTextGravity, calculateIsRtl ? 1 : 0) & 7;
                    if (absoluteGravity == 1) {
                        alignment = Layout.Alignment.ALIGN_CENTER;
                    } else if (absoluteGravity != 5) {
                        if (this.isRtl) {
                            alignment = Layout.Alignment.ALIGN_OPPOSITE;
                        } else {
                            alignment = Layout.Alignment.ALIGN_NORMAL;
                        }
                    } else if (this.isRtl) {
                        alignment = Layout.Alignment.ALIGN_NORMAL;
                    } else {
                        alignment = Layout.Alignment.ALIGN_OPPOSITE;
                    }
                }
                StaticLayoutBuilderCompat staticLayoutBuilderCompat = new StaticLayoutBuilderCompat(this.text, textPaint2, (int) width);
                staticLayoutBuilderCompat.ellipsize = this.titleTextEllipsize;
                staticLayoutBuilderCompat.isRtl = calculateIsRtl;
                staticLayoutBuilderCompat.alignment = alignment;
                staticLayoutBuilderCompat.includePad = false;
                staticLayoutBuilderCompat.maxLines = i2;
                float f8 = this.lineSpacingMultiplier;
                staticLayoutBuilderCompat.lineSpacingAdd = 0.0f;
                staticLayoutBuilderCompat.lineSpacingMultiplier = f8;
                staticLayoutBuilderCompat.hyphenationFrequency = this.hyphenationFrequency;
                StaticLayout build = staticLayoutBuilderCompat.build();
                build.getClass();
                this.textLayout = build;
                this.textToDraw = build.getText();
            }
        }
    }

    public final void draw(Canvas canvas) {
        int save = canvas.save();
        if (this.textToDraw != null) {
            RectF rectF = this.currentBounds;
            if (rectF.width() > 0.0f && rectF.height() > 0.0f) {
                TextPaint textPaint2 = this.textPaint;
                textPaint2.setTextSize(this.currentTextSize);
                float f = this.currentDrawX;
                float f2 = this.currentDrawY;
                float f3 = this.scale;
                if (f3 != 1.0f && !this.fadeModeEnabled) {
                    canvas.scale(f3, f3, f, f2);
                }
                if (this.maxLines <= 1 || ((this.isRtl && !this.fadeModeEnabled) || (this.fadeModeEnabled && this.expandedFraction <= this.fadeModeThresholdFraction))) {
                    canvas.translate(f, f2);
                    this.textLayout.draw(canvas);
                } else {
                    int alpha = textPaint2.getAlpha();
                    canvas.translate(this.currentDrawX - ((float) this.textLayout.getLineStart(0)), f2);
                    float f4 = (float) alpha;
                    textPaint2.setAlpha((int) (this.expandedTextBlend * f4));
                    float f5 = this.currentShadowRadius;
                    float f6 = this.currentShadowDx;
                    float f7 = this.currentShadowDy;
                    int i = this.currentShadowColor;
                    textPaint2.setShadowLayer(f5, f6, f7, ColorUtils.setAlphaComponent(i, (Color.alpha(i) * textPaint2.getAlpha()) / 255));
                    this.textLayout.draw(canvas);
                    textPaint2.setAlpha((int) (this.collapsedTextBlend * f4));
                    float f8 = this.currentShadowRadius;
                    float f9 = this.currentShadowDx;
                    float f10 = this.currentShadowDy;
                    int i2 = this.currentShadowColor;
                    textPaint2.setShadowLayer(f8, f9, f10, ColorUtils.setAlphaComponent(i2, (Color.alpha(i2) * textPaint2.getAlpha()) / 255));
                    int lineBaseline = this.textLayout.getLineBaseline(0);
                    CharSequence charSequence = this.textToDrawCollapsed;
                    float f11 = (float) lineBaseline;
                    canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f11, textPaint2);
                    textPaint2.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, this.currentShadowColor);
                    if (!this.fadeModeEnabled) {
                        String trim = this.textToDrawCollapsed.toString().trim();
                        if (trim.endsWith("…")) {
                            trim = trim.substring(0, trim.length() - 1);
                        }
                        String str = trim;
                        textPaint2.setAlpha(alpha);
                        canvas.drawText(str, 0, Math.min(this.textLayout.getLineEnd(0), str.length()), 0.0f, f11, textPaint2);
                    }
                }
                canvas.restoreToCount(save);
            }
        }
    }

    public final float getCollapsedTextHeight() {
        TextPaint textPaint2 = this.tmpPaint;
        textPaint2.setTextSize(this.collapsedTextSize);
        textPaint2.setTypeface(this.collapsedTypeface);
        textPaint2.setLetterSpacing(this.collapsedLetterSpacing);
        return -textPaint2.ascent();
    }

    public final int getCurrentColor(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.state;
        if (iArr != null) {
            return colorStateList.getColorForState(iArr, 0);
        }
        return colorStateList.getDefaultColor();
    }

    public final void maybeUpdateFontWeightAdjustment(Configuration configuration) {
        Typeface typeface = this.collapsedTypefaceDefault;
        if (typeface != null) {
            this.collapsedTypefaceBold = TypefaceUtils.maybeCopyWithFontWeightAdjustment(configuration, typeface);
        }
        Typeface typeface2 = this.expandedTypefaceDefault;
        if (typeface2 != null) {
            this.expandedTypefaceBold = TypefaceUtils.maybeCopyWithFontWeightAdjustment(configuration, typeface2);
        }
        Typeface typeface3 = this.collapsedTypefaceBold;
        if (typeface3 == null) {
            typeface3 = this.collapsedTypefaceDefault;
        }
        this.collapsedTypeface = typeface3;
        Typeface typeface4 = this.expandedTypefaceBold;
        if (typeface4 == null) {
            typeface4 = this.expandedTypefaceDefault;
        }
        this.expandedTypeface = typeface4;
        recalculate(true);
    }

    public final void recalculate(boolean z) {
        float f;
        float f2;
        int i;
        float f3;
        float f4;
        StaticLayout staticLayout;
        boolean z2 = z;
        View view2 = this.view;
        if ((view2.getHeight() > 0 && view2.getWidth() > 0) || z2) {
            calculateUsingTextSize(1.0f, z2);
            CharSequence charSequence = this.textToDraw;
            TextPaint textPaint2 = this.textPaint;
            if (!(charSequence == null || (staticLayout = this.textLayout) == null)) {
                this.textToDrawCollapsed = TextUtils.ellipsize(charSequence, textPaint2, (float) staticLayout.getWidth(), this.titleTextEllipsize);
            }
            CharSequence charSequence2 = this.textToDrawCollapsed;
            if (charSequence2 != null) {
                this.collapsedTextWidth = textPaint2.measureText(charSequence2, 0, charSequence2.length());
            } else {
                this.collapsedTextWidth = 0.0f;
            }
            int absoluteGravity = Gravity.getAbsoluteGravity(this.collapsedTextGravity, this.isRtl ? 1 : 0);
            int i2 = absoluteGravity & 112;
            Rect rect = this.collapsedBounds;
            if (i2 == 48) {
                this.collapsedDrawY = (float) rect.top;
            } else if (i2 != 80) {
                this.collapsedDrawY = ((float) rect.centerY()) - ((textPaint2.descent() - textPaint2.ascent()) / 2.0f);
            } else {
                this.collapsedDrawY = textPaint2.ascent() + ((float) rect.bottom);
            }
            int i3 = absoluteGravity & 8388615;
            if (i3 == 1) {
                this.collapsedDrawX = ((float) rect.centerX()) - (this.collapsedTextWidth / 2.0f);
            } else if (i3 != 5) {
                this.collapsedDrawX = (float) rect.left;
            } else {
                this.collapsedDrawX = ((float) rect.right) - this.collapsedTextWidth;
            }
            calculateUsingTextSize(0.0f, z2);
            StaticLayout staticLayout2 = this.textLayout;
            if (staticLayout2 != null) {
                f = (float) staticLayout2.getHeight();
            } else {
                f = 0.0f;
            }
            StaticLayout staticLayout3 = this.textLayout;
            if (staticLayout3 == null || this.maxLines <= 1) {
                CharSequence charSequence3 = this.textToDraw;
                if (charSequence3 != null) {
                    f2 = textPaint2.measureText(charSequence3, 0, charSequence3.length());
                } else {
                    f2 = 0.0f;
                }
            } else {
                f2 = (float) staticLayout3.getWidth();
            }
            StaticLayout staticLayout4 = this.textLayout;
            if (staticLayout4 != null) {
                i = staticLayout4.getLineCount();
            } else {
                i = 0;
            }
            this.expandedLineCount = i;
            int absoluteGravity2 = Gravity.getAbsoluteGravity(this.expandedTextGravity, this.isRtl ? 1 : 0);
            int i4 = absoluteGravity2 & 112;
            Rect rect2 = this.expandedBounds;
            if (i4 == 48) {
                this.expandedDrawY = (float) rect2.top;
            } else if (i4 != 80) {
                this.expandedDrawY = ((float) rect2.centerY()) - (f / 2.0f);
            } else {
                this.expandedDrawY = textPaint2.descent() + (((float) rect2.bottom) - f);
            }
            int i5 = absoluteGravity2 & 8388615;
            if (i5 == 1) {
                this.expandedDrawX = ((float) rect2.centerX()) - (f2 / 2.0f);
            } else if (i5 != 5) {
                this.expandedDrawX = (float) rect2.left;
            } else {
                this.expandedDrawX = ((float) rect2.right) - f2;
            }
            Bitmap bitmap = this.expandedTitleTexture;
            if (bitmap != null) {
                bitmap.recycle();
                this.expandedTitleTexture = null;
            }
            setInterpolatedTextSize(this.expandedFraction);
            float f5 = this.expandedFraction;
            boolean z3 = this.fadeModeEnabled;
            RectF rectF = this.currentBounds;
            if (z3) {
                if (f5 < this.fadeModeThresholdFraction) {
                    rect = rect2;
                }
                rectF.set(rect);
            } else {
                rectF.left = lerp((float) rect2.left, (float) rect.left, f5, this.positionInterpolator);
                rectF.top = lerp(this.expandedDrawY, this.collapsedDrawY, f5, this.positionInterpolator);
                rectF.right = lerp((float) rect2.right, (float) rect.right, f5, this.positionInterpolator);
                rectF.bottom = lerp((float) rect2.bottom, (float) rect.bottom, f5, this.positionInterpolator);
            }
            if (!this.fadeModeEnabled) {
                this.currentDrawX = lerp(this.expandedDrawX, this.collapsedDrawX, f5, this.positionInterpolator);
                this.currentDrawY = lerp(this.expandedDrawY, this.collapsedDrawY, f5, this.positionInterpolator);
                setInterpolatedTextSize(f5);
                f3 = f5;
            } else if (f5 < this.fadeModeThresholdFraction) {
                this.currentDrawX = this.expandedDrawX;
                this.currentDrawY = this.expandedDrawY;
                setInterpolatedTextSize(0.0f);
                f3 = 0.0f;
            } else {
                this.currentDrawX = this.collapsedDrawX;
                this.currentDrawY = this.collapsedDrawY - ((float) Math.max(0, this.currentOffsetY));
                setInterpolatedTextSize(1.0f);
                f3 = 1.0f;
            }
            FastOutSlowInInterpolator fastOutSlowInInterpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
            this.collapsedTextBlend = 1.0f - lerp(0.0f, 1.0f, 1.0f - f5, fastOutSlowInInterpolator);
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            view2.postInvalidateOnAnimation();
            this.expandedTextBlend = lerp(1.0f, 0.0f, f5, fastOutSlowInInterpolator);
            view2.postInvalidateOnAnimation();
            ColorStateList colorStateList = this.collapsedTextColor;
            ColorStateList colorStateList2 = this.expandedTextColor;
            if (colorStateList != colorStateList2) {
                textPaint2.setColor(blendARGB(getCurrentColor(colorStateList2), f3, getCurrentColor(this.collapsedTextColor)));
            } else {
                textPaint2.setColor(getCurrentColor(colorStateList));
            }
            float f6 = this.collapsedLetterSpacing;
            float f7 = this.expandedLetterSpacing;
            if (f6 != f7) {
                textPaint2.setLetterSpacing(lerp(f7, f6, f5, fastOutSlowInInterpolator));
            } else {
                textPaint2.setLetterSpacing(f6);
            }
            this.currentShadowRadius = lerp(this.expandedShadowRadius, this.collapsedShadowRadius, f5, (TimeInterpolator) null);
            this.currentShadowDx = lerp(this.expandedShadowDx, this.collapsedShadowDx, f5, (TimeInterpolator) null);
            this.currentShadowDy = lerp(this.expandedShadowDy, this.collapsedShadowDy, f5, (TimeInterpolator) null);
            int blendARGB = blendARGB(getCurrentColor(this.expandedShadowColor), f5, getCurrentColor(this.collapsedShadowColor));
            this.currentShadowColor = blendARGB;
            textPaint2.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, blendARGB);
            if (this.fadeModeEnabled) {
                int alpha = textPaint2.getAlpha();
                float f8 = this.fadeModeThresholdFraction;
                if (f5 <= f8) {
                    f4 = AnimationUtils.lerp(1.0f, 0.0f, this.fadeModeStartFraction, f8, f5);
                } else {
                    f4 = AnimationUtils.lerp(0.0f, 1.0f, f8, 1.0f, f5);
                }
                textPaint2.setAlpha((int) (f4 * ((float) alpha)));
            }
            view2.postInvalidateOnAnimation();
        }
    }

    public final void setCollapsedTextAppearance(int i) {
        View view2 = this.view;
        TextAppearance textAppearance = new TextAppearance(view2.getContext(), i);
        ColorStateList colorStateList = textAppearance.textColor;
        if (colorStateList != null) {
            this.collapsedTextColor = colorStateList;
        }
        float f = textAppearance.textSize;
        if (f != 0.0f) {
            this.collapsedTextSize = f;
        }
        ColorStateList colorStateList2 = textAppearance.shadowColor;
        if (colorStateList2 != null) {
            this.collapsedShadowColor = colorStateList2;
        }
        this.collapsedShadowDx = textAppearance.shadowDx;
        this.collapsedShadowDy = textAppearance.shadowDy;
        this.collapsedShadowRadius = textAppearance.shadowRadius;
        this.collapsedLetterSpacing = textAppearance.letterSpacing;
        CancelableFontCallback cancelableFontCallback = this.collapsedFontCallback;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancelled = true;
        }
        AnonymousClass1 r2 = new Object(this, 0) {
            public final /* synthetic */ CollapsingTextHelper this$0;

            {
                this.this$0 = r1;
            }

            public final void apply(Typeface typeface) {
                switch (1) {
                    case 0:
                        CollapsingTextHelper collapsingTextHelper = this.this$0;
                        if (collapsingTextHelper.setCollapsedTypefaceInternal(typeface)) {
                            collapsingTextHelper.recalculate(false);
                            return;
                        }
                        return;
                    default:
                        CollapsingTextHelper collapsingTextHelper2 = this.this$0;
                        if (collapsingTextHelper2.setExpandedTypefaceInternal(typeface)) {
                            collapsingTextHelper2.recalculate(false);
                            return;
                        }
                        return;
                }
            }
        };
        textAppearance.createFallbackFont();
        this.collapsedFontCallback = new CancelableFontCallback(r2, textAppearance.font);
        textAppearance.getFontAsync(view2.getContext(), this.collapsedFontCallback);
        recalculate(false);
    }

    public final void setCollapsedTextColor(ColorStateList colorStateList) {
        if (this.collapsedTextColor != colorStateList) {
            this.collapsedTextColor = colorStateList;
            recalculate(false);
        }
    }

    public final boolean setCollapsedTypefaceInternal(Typeface typeface) {
        CancelableFontCallback cancelableFontCallback = this.collapsedFontCallback;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancelled = true;
        }
        if (this.collapsedTypefaceDefault == typeface) {
            return false;
        }
        this.collapsedTypefaceDefault = typeface;
        Typeface maybeCopyWithFontWeightAdjustment = TypefaceUtils.maybeCopyWithFontWeightAdjustment(this.view.getContext().getResources().getConfiguration(), typeface);
        this.collapsedTypefaceBold = maybeCopyWithFontWeightAdjustment;
        if (maybeCopyWithFontWeightAdjustment == null) {
            maybeCopyWithFontWeightAdjustment = this.collapsedTypefaceDefault;
        }
        this.collapsedTypeface = maybeCopyWithFontWeightAdjustment;
        return true;
    }

    public final void setExpandedTextAppearance(int i) {
        View view2 = this.view;
        TextAppearance textAppearance = new TextAppearance(view2.getContext(), i);
        ColorStateList colorStateList = textAppearance.textColor;
        if (colorStateList != null) {
            this.expandedTextColor = colorStateList;
        }
        float f = textAppearance.textSize;
        if (f != 0.0f) {
            this.expandedTextSize = f;
        }
        ColorStateList colorStateList2 = textAppearance.shadowColor;
        if (colorStateList2 != null) {
            this.expandedShadowColor = colorStateList2;
        }
        this.expandedShadowDx = textAppearance.shadowDx;
        this.expandedShadowDy = textAppearance.shadowDy;
        this.expandedShadowRadius = textAppearance.shadowRadius;
        this.expandedLetterSpacing = textAppearance.letterSpacing;
        CancelableFontCallback cancelableFontCallback = this.expandedFontCallback;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancelled = true;
        }
        AnonymousClass1 r2 = new Object(this, 1) {
            public final /* synthetic */ CollapsingTextHelper this$0;

            {
                this.this$0 = r1;
            }

            public final void apply(Typeface typeface) {
                switch (1) {
                    case 0:
                        CollapsingTextHelper collapsingTextHelper = this.this$0;
                        if (collapsingTextHelper.setCollapsedTypefaceInternal(typeface)) {
                            collapsingTextHelper.recalculate(false);
                            return;
                        }
                        return;
                    default:
                        CollapsingTextHelper collapsingTextHelper2 = this.this$0;
                        if (collapsingTextHelper2.setExpandedTypefaceInternal(typeface)) {
                            collapsingTextHelper2.recalculate(false);
                            return;
                        }
                        return;
                }
            }
        };
        textAppearance.createFallbackFont();
        this.expandedFontCallback = new CancelableFontCallback(r2, textAppearance.font);
        textAppearance.getFontAsync(view2.getContext(), this.expandedFontCallback);
        recalculate(false);
    }

    public final boolean setExpandedTypefaceInternal(Typeface typeface) {
        CancelableFontCallback cancelableFontCallback = this.expandedFontCallback;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancelled = true;
        }
        if (this.expandedTypefaceDefault == typeface) {
            return false;
        }
        this.expandedTypefaceDefault = typeface;
        Typeface maybeCopyWithFontWeightAdjustment = TypefaceUtils.maybeCopyWithFontWeightAdjustment(this.view.getContext().getResources().getConfiguration(), typeface);
        this.expandedTypefaceBold = maybeCopyWithFontWeightAdjustment;
        if (maybeCopyWithFontWeightAdjustment == null) {
            maybeCopyWithFontWeightAdjustment = this.expandedTypefaceDefault;
        }
        this.expandedTypeface = maybeCopyWithFontWeightAdjustment;
        return true;
    }

    public final void setExpansionFraction(float f) {
        float f2;
        float f3;
        float clamp = MathUtils.clamp(f);
        if (clamp != this.expandedFraction) {
            this.expandedFraction = clamp;
            boolean z = this.fadeModeEnabled;
            RectF rectF = this.currentBounds;
            Rect rect = this.collapsedBounds;
            Rect rect2 = this.expandedBounds;
            if (z) {
                if (clamp < this.fadeModeThresholdFraction) {
                    rect = rect2;
                }
                rectF.set(rect);
            } else {
                rectF.left = lerp((float) rect2.left, (float) rect.left, clamp, this.positionInterpolator);
                rectF.top = lerp(this.expandedDrawY, this.collapsedDrawY, clamp, this.positionInterpolator);
                rectF.right = lerp((float) rect2.right, (float) rect.right, clamp, this.positionInterpolator);
                rectF.bottom = lerp((float) rect2.bottom, (float) rect.bottom, clamp, this.positionInterpolator);
            }
            if (!this.fadeModeEnabled) {
                this.currentDrawX = lerp(this.expandedDrawX, this.collapsedDrawX, clamp, this.positionInterpolator);
                this.currentDrawY = lerp(this.expandedDrawY, this.collapsedDrawY, clamp, this.positionInterpolator);
                setInterpolatedTextSize(clamp);
                f2 = clamp;
            } else if (clamp < this.fadeModeThresholdFraction) {
                this.currentDrawX = this.expandedDrawX;
                this.currentDrawY = this.expandedDrawY;
                setInterpolatedTextSize(0.0f);
                f2 = 0.0f;
            } else {
                this.currentDrawX = this.collapsedDrawX;
                this.currentDrawY = this.collapsedDrawY - ((float) Math.max(0, this.currentOffsetY));
                setInterpolatedTextSize(1.0f);
                f2 = 1.0f;
            }
            FastOutSlowInInterpolator fastOutSlowInInterpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
            this.collapsedTextBlend = 1.0f - lerp(0.0f, 1.0f, 1.0f - clamp, fastOutSlowInInterpolator);
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            View view2 = this.view;
            view2.postInvalidateOnAnimation();
            this.expandedTextBlend = lerp(1.0f, 0.0f, clamp, fastOutSlowInInterpolator);
            view2.postInvalidateOnAnimation();
            ColorStateList colorStateList = this.collapsedTextColor;
            ColorStateList colorStateList2 = this.expandedTextColor;
            TextPaint textPaint2 = this.textPaint;
            if (colorStateList != colorStateList2) {
                textPaint2.setColor(blendARGB(getCurrentColor(colorStateList2), f2, getCurrentColor(this.collapsedTextColor)));
            } else {
                textPaint2.setColor(getCurrentColor(colorStateList));
            }
            float f4 = this.collapsedLetterSpacing;
            float f5 = this.expandedLetterSpacing;
            if (f4 != f5) {
                textPaint2.setLetterSpacing(lerp(f5, f4, clamp, fastOutSlowInInterpolator));
            } else {
                textPaint2.setLetterSpacing(f4);
            }
            this.currentShadowRadius = lerp(this.expandedShadowRadius, this.collapsedShadowRadius, clamp, (TimeInterpolator) null);
            this.currentShadowDx = lerp(this.expandedShadowDx, this.collapsedShadowDx, clamp, (TimeInterpolator) null);
            this.currentShadowDy = lerp(this.expandedShadowDy, this.collapsedShadowDy, clamp, (TimeInterpolator) null);
            int blendARGB = blendARGB(getCurrentColor(this.expandedShadowColor), clamp, getCurrentColor(this.collapsedShadowColor));
            this.currentShadowColor = blendARGB;
            textPaint2.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, blendARGB);
            if (this.fadeModeEnabled) {
                int alpha = textPaint2.getAlpha();
                float f6 = this.fadeModeThresholdFraction;
                if (clamp <= f6) {
                    f3 = AnimationUtils.lerp(1.0f, 0.0f, this.fadeModeStartFraction, f6, clamp);
                } else {
                    f3 = AnimationUtils.lerp(0.0f, 1.0f, f6, 1.0f, clamp);
                }
                textPaint2.setAlpha((int) (f3 * ((float) alpha)));
            }
            view2.postInvalidateOnAnimation();
        }
    }

    public final void setInterpolatedTextSize(float f) {
        calculateUsingTextSize(f, false);
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        this.view.postInvalidateOnAnimation();
    }
}
