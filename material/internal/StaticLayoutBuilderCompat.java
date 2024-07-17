package com.google.android.material.internal;

import android.graphics.text.LineBreakConfig;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import com.android.settingslib.collapsingtoolbar.widget.CollapsingCoordinatorLayout;
import com.android.settingslib.collapsingtoolbar.widget.CollapsingCoordinatorLayout$$ExternalSyntheticLambda0;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class StaticLayoutBuilderCompat {
    public Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
    public TextUtils.TruncateAt ellipsize = null;
    public int end;
    public int hyphenationFrequency = 1;
    public boolean includePad = true;
    public boolean isRtl;
    public float lineSpacingAdd = 0.0f;
    public float lineSpacingMultiplier = 1.0f;
    public int maxLines = Integer.MAX_VALUE;
    public final TextPaint paint;
    public CharSequence source;
    public StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer;
    public final int width;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    abstract class StaticLayoutBuilderCompatException extends Exception {
    }

    public StaticLayoutBuilderCompat(CharSequence charSequence, TextPaint textPaint, int i) {
        this.source = charSequence;
        this.paint = textPaint;
        this.width = i;
        this.end = charSequence.length();
    }

    public final StaticLayout build() {
        TextDirectionHeuristic textDirectionHeuristic;
        if (this.source == null) {
            this.source = "";
        }
        int max = Math.max(0, this.width);
        CharSequence charSequence = this.source;
        int i = this.maxLines;
        TextPaint textPaint = this.paint;
        if (i == 1) {
            charSequence = TextUtils.ellipsize(charSequence, textPaint, (float) max, this.ellipsize);
        }
        int min = Math.min(charSequence.length(), this.end);
        this.end = min;
        if (this.isRtl && this.maxLines == 1) {
            this.alignment = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder obtain = StaticLayout.Builder.obtain(charSequence, 0, min, textPaint, max);
        obtain.setAlignment(this.alignment);
        obtain.setIncludePad(this.includePad);
        if (this.isRtl) {
            textDirectionHeuristic = TextDirectionHeuristics.RTL;
        } else {
            textDirectionHeuristic = TextDirectionHeuristics.LTR;
        }
        obtain.setTextDirection(textDirectionHeuristic);
        TextUtils.TruncateAt truncateAt = this.ellipsize;
        if (truncateAt != null) {
            obtain.setEllipsize(truncateAt);
        }
        obtain.setMaxLines(this.maxLines);
        float f = this.lineSpacingAdd;
        if (!(f == 0.0f && this.lineSpacingMultiplier == 1.0f)) {
            obtain.setLineSpacing(f, this.lineSpacingMultiplier);
        }
        if (this.maxLines > 1) {
            obtain.setHyphenationFrequency(this.hyphenationFrequency);
        }
        StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer2 = this.staticLayoutBuilderConfigurer;
        if (staticLayoutBuilderConfigurer2 != null) {
            ((CollapsingCoordinatorLayout$$ExternalSyntheticLambda0) staticLayoutBuilderConfigurer2).getClass();
            int i2 = CollapsingCoordinatorLayout.$r8$clinit;
            obtain.setLineBreakConfig(new LineBreakConfig.Builder().setLineBreakWordStyle(1).build());
        }
        return obtain.build();
    }
}
