package com.google.android.systemui.assist.uihints;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader;
import android.text.SpannableStringBuilder;
import android.text.style.ReplacementSpan;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.TextView;
import androidx.core.math.MathUtils;
import com.android.systemui.assist.DeviceConfigHelper;
import com.google.android.systemui.assist.uihints.TranscriptionController;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class TranscriptionView extends TextView implements TranscriptionController.TranscriptionSpaceView {
    public static final PathInterpolator INTERPOLATOR_SCROLL = new PathInterpolator(0.17f, 0.17f, 0.67f, 1.0f);
    public final float BUMPER_DISTANCE_END_PX;
    public final float BUMPER_DISTANCE_START_PX;
    public final float FADE_DISTANCE_END_PX;
    public final float FADE_DISTANCE_START_PX;
    public final int TEXT_COLOR_DARK;
    public final int TEXT_COLOR_LIGHT;
    public DeviceConfigHelper mDeviceConfigHelper;
    public int mDisplayWidthPx;
    public boolean mHasDarkBackground;
    public SettableFuture mHideFuture;
    public final Matrix mMatrix;
    public float[] mStops;
    public ValueAnimator mTranscriptionAnimation;
    public TranscriptionAnimator mTranscriptionAnimator;
    public final SpannableStringBuilder mTranscriptionBuilder;
    public AnimatorSet mVisibilityAnimators;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class TranscriptionAnimator implements ValueAnimator.AnimatorUpdateListener {
        public float mDistance;
        public final List mSpans = new ArrayList();
        public float mStartX;

        public TranscriptionAnimator() {
        }

        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (Math.abs(floatValue - this.mStartX) < Math.abs(this.mDistance)) {
                TranscriptionView.this.setX(floatValue);
                TranscriptionView transcriptionView = TranscriptionView.this;
                PathInterpolator pathInterpolator = TranscriptionView.INTERPOLATOR_SCROLL;
                transcriptionView.updateColor();
            }
            ((ArrayList) this.mSpans).forEach(new TranscriptionView$TranscriptionAnimator$$ExternalSyntheticLambda0(valueAnimator));
            TranscriptionView.this.invalidate();
        }
    }

    public TranscriptionView(Context context) {
        this(context, (AttributeSet) null);
    }

    public static float interpolate(long j, long j2, float f) {
        return (((float) (j2 - j)) * f) + ((float) j);
    }

    public long getAdaptiveDuration(float f, float f2) {
        this.mDeviceConfigHelper.getClass();
        long j = DeviceConfigHelper.getLong(4, "assist_transcription_duration_per_px_regular");
        this.mDeviceConfigHelper.getClass();
        float interpolate = interpolate(j, DeviceConfigHelper.getLong(3, "assist_transcription_duration_per_px_fast"), f / f2);
        this.mDeviceConfigHelper.getClass();
        long j2 = DeviceConfigHelper.getLong(400, "assist_transcription_max_duration");
        this.mDeviceConfigHelper.getClass();
        return Math.min(j2, Math.max(DeviceConfigHelper.getLong(20, "assist_transcription_min_duration"), (long) (f * interpolate)));
    }

    public final float getFullyVisibleDistance(float f) {
        int i = this.mDisplayWidthPx;
        float f2 = this.BUMPER_DISTANCE_END_PX;
        float f3 = this.FADE_DISTANCE_END_PX;
        if (f < ((float) i) - (((this.BUMPER_DISTANCE_START_PX + f2) + f3) + this.FADE_DISTANCE_START_PX)) {
            return (((float) i) - f) / 2.0f;
        }
        return ((((float) i) - f) - f3) - f2;
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Object, com.google.common.util.concurrent.SettableFuture] */
    public final ListenableFuture hide(boolean z) {
        SettableFuture settableFuture = this.mHideFuture;
        if (settableFuture != null && !settableFuture.isDone()) {
            return this.mHideFuture;
        }
        this.mHideFuture = new Object();
        final TranscriptionView$$ExternalSyntheticLambda0 transcriptionView$$ExternalSyntheticLambda0 = new TranscriptionView$$ExternalSyntheticLambda0(this);
        if (!z) {
            if (this.mVisibilityAnimators.isRunning()) {
                this.mVisibilityAnimators.end();
            } else {
                transcriptionView$$ExternalSyntheticLambda0.run();
            }
            return ImmediateFuture.NULL;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        this.mVisibilityAnimators = animatorSet;
        animatorSet.play(ObjectAnimator.ofFloat(this, View.ALPHA, new float[]{getAlpha(), 0.0f}).setDuration(400));
        this.mVisibilityAnimators.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, new float[]{getTranslationY(), (float) (getHeight() * -1)}).setDuration(700));
        this.mVisibilityAnimators.addListener(new AnimatorListenerAdapter() {
            public final void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                transcriptionView$$ExternalSyntheticLambda0.run();
            }
        });
        this.mVisibilityAnimators.start();
        return this.mHideFuture;
    }

    public void initializeDeviceConfigHelper(DeviceConfigHelper deviceConfigHelper) {
        this.mDeviceConfigHelper = deviceConfigHelper;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        String spannableStringBuilder = this.mTranscriptionBuilder.toString();
        setTranscription("");
        this.mTranscriptionAnimator = new TranscriptionAnimator();
        setTranscription(spannableStringBuilder);
    }

    public final void onFontSizeChanged() {
        setTextSize(0, this.mContext.getResources().getDimension(2131167791));
    }

    public final void setHasDarkBackground(boolean z) {
        if (z != this.mHasDarkBackground) {
            this.mHasDarkBackground = z;
            updateColor();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: com.google.android.systemui.assist.uihints.TranscriptionView$TranscriptionSpan} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setTranscription(java.lang.String r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r7 = 2
            r8 = 0
            r0.setVisibility(r8)
            r17.updateDisplayWidth()
            android.animation.ValueAnimator r2 = r0.mTranscriptionAnimation
            r9 = 1
            if (r2 == 0) goto L_0x0019
            boolean r2 = r2.isRunning()
            if (r2 == 0) goto L_0x0019
            r10 = r9
            goto L_0x001a
        L_0x0019:
            r10 = r8
        L_0x001a:
            if (r10 == 0) goto L_0x0021
            android.animation.ValueAnimator r2 = r0.mTranscriptionAnimation
            r2.cancel()
        L_0x0021:
            android.text.SpannableStringBuilder r2 = r0.mTranscriptionBuilder
            java.lang.String r2 = r2.toString()
            boolean r11 = r2.isEmpty()
            android.text.SpannableStringBuilder r2 = r0.mTranscriptionBuilder
            java.lang.String r2 = r2.toString()
            if (r2 == 0) goto L_0x00ac
            boolean r3 = r2.isEmpty()
            if (r3 == 0) goto L_0x003b
            goto L_0x00ac
        L_0x003b:
            if (r1 == 0) goto L_0x00ac
            boolean r3 = r18.isEmpty()
            if (r3 == 0) goto L_0x0045
            goto L_0x00ac
        L_0x0045:
            java.lang.String r3 = r2.toLowerCase()
            java.lang.String r4 = r18.toLowerCase()
            int r5 = r3.length()
            int r6 = r4.length()
            int[] r12 = new int[r7]
            r12[r9] = r6
            r12[r8] = r5
            java.lang.Class r5 = java.lang.Integer.TYPE
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r5, r12)
            r6 = r5
            int[][] r6 = (int[][]) r6
            r5 = r8
        L_0x0065:
            int r12 = r3.length()
            if (r5 >= r12) goto L_0x009b
            char r12 = r3.charAt(r5)
            r13 = r8
        L_0x0070:
            int r14 = r4.length()
            if (r13 >= r14) goto L_0x0099
            char r14 = r4.charAt(r13)
            if (r12 != r14) goto L_0x0097
            r14 = 32
            if (r12 != r14) goto L_0x0082
            r14 = r8
            goto L_0x0083
        L_0x0082:
            r14 = r9
        L_0x0083:
            if (r5 == 0) goto L_0x0091
            if (r13 != 0) goto L_0x0088
            goto L_0x0091
        L_0x0088:
            int r15 = r5 + -1
            r15 = r6[r15]
            int r16 = r13 + -1
            r15 = r15[r16]
            goto L_0x0092
        L_0x0091:
            r15 = r8
        L_0x0092:
            r16 = r6[r5]
            int r15 = r15 + r14
            r16[r13] = r15
        L_0x0097:
            int r13 = r13 + r9
            goto L_0x0070
        L_0x0099:
            int r5 = r5 + r9
            goto L_0x0065
        L_0x009b:
            int r3 = r2.length()
            int r5 = r18.length()
            r2 = 0
            r4 = 0
            r1 = r18
            com.google.android.systemui.assist.uihints.StringUtils$StringStabilityInfo r1 = com.google.android.systemui.assist.uihints.StringUtils.getRightMostStabilityInfoLeaf(r1, r2, r3, r4, r5, r6)
            goto L_0x00b2
        L_0x00ac:
            com.google.android.systemui.assist.uihints.StringUtils$StringStabilityInfo r2 = new com.google.android.systemui.assist.uihints.StringUtils$StringStabilityInfo
            r2.<init>(r1)
            r1 = r2
        L_0x00b2:
            android.text.SpannableStringBuilder r2 = r0.mTranscriptionBuilder
            r2.clear()
            android.text.SpannableStringBuilder r2 = r0.mTranscriptionBuilder
            java.lang.String r3 = r1.stable
            r2.append(r3)
            android.text.SpannableStringBuilder r2 = r0.mTranscriptionBuilder
            java.lang.String r3 = r1.unstable
            r2.append(r3)
            android.text.TextPaint r2 = r17.getPaint()
            android.text.SpannableStringBuilder r4 = r0.mTranscriptionBuilder
            java.lang.String r4 = r4.toString()
            float r2 = r2.measureText(r4)
            double r4 = (double) r2
            double r4 = java.lang.Math.ceil(r4)
            int r2 = (int) r4
            android.view.ViewGroup$LayoutParams r4 = r17.getLayoutParams()
            if (r4 == 0) goto L_0x00e4
            r4.width = r2
            r0.setLayoutParams(r4)
        L_0x00e4:
            r17.updateColor()
            r4 = 0
            java.lang.String r1 = r1.stable
            if (r11 != 0) goto L_0x01b2
            boolean r5 = r1.isEmpty()
            if (r5 == 0) goto L_0x00f4
            goto L_0x01b2
        L_0x00f4:
            int r2 = r1.length()
            if (r10 == 0) goto L_0x012d
            java.lang.String r5 = " "
            boolean r6 = r1.endsWith(r5)
            if (r6 != 0) goto L_0x012d
            boolean r3 = r3.startsWith(r5)
            if (r3 != 0) goto L_0x012d
            java.lang.String r3 = "\\s+"
            java.lang.String[] r1 = r1.split(r3)
            int r3 = r1.length
            if (r3 <= 0) goto L_0x011a
            int r3 = r1.length
            int r3 = r3 - r9
            r1 = r1[r3]
            int r1 = r1.length()
            int r2 = r2 - r1
        L_0x011a:
            com.google.android.systemui.assist.uihints.TranscriptionView$TranscriptionAnimator r1 = r0.mTranscriptionAnimator
            java.util.List r1 = r1.mSpans
            boolean r3 = r1.isEmpty()
            if (r3 != 0) goto L_0x012d
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            java.lang.Object r1 = androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0.m(r1, r9)
            r4 = r1
            com.google.android.systemui.assist.uihints.TranscriptionView$TranscriptionSpan r4 = (com.google.android.systemui.assist.uihints.TranscriptionView.TranscriptionSpan) r4
        L_0x012d:
            r0.setUpSpans(r2, r4)
            com.google.android.systemui.assist.uihints.TranscriptionView$TranscriptionAnimator r1 = r0.mTranscriptionAnimator
            com.google.android.systemui.assist.uihints.TranscriptionView r2 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            android.text.TextPaint r2 = r2.getPaint()
            com.google.android.systemui.assist.uihints.TranscriptionView r3 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            android.text.SpannableStringBuilder r3 = r3.mTranscriptionBuilder
            java.lang.String r3 = r3.toString()
            float r2 = r2.measureText(r3)
            com.google.android.systemui.assist.uihints.TranscriptionView r3 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            float r3 = r3.getX()
            r1.mStartX = r3
            com.google.android.systemui.assist.uihints.TranscriptionView r3 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            float r3 = r3.getFullyVisibleDistance(r2)
            float r4 = r1.mStartX
            float r3 = r3 - r4
            r1.mDistance = r3
            com.google.android.systemui.assist.uihints.TranscriptionView r3 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            r3.updateColor()
            com.google.android.systemui.assist.uihints.TranscriptionView r3 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            float r4 = r1.mDistance
            float r4 = java.lang.Math.abs(r4)
            com.google.android.systemui.assist.uihints.TranscriptionView r5 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            int r5 = r5.mDisplayWidthPx
            float r5 = (float) r5
            long r3 = r3.getAdaptiveDuration(r4, r5)
            com.google.android.systemui.assist.uihints.TranscriptionView r5 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            int r6 = r5.mDisplayWidthPx
            float r6 = (float) r6
            float r5 = r5.getX()
            float r6 = r6 - r5
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x018c
            com.google.android.systemui.assist.uihints.TranscriptionView r2 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            com.android.systemui.assist.DeviceConfigHelper r2 = r2.mDeviceConfigHelper
            r2.getClass()
            java.lang.String r2 = "assist_transcription_fade_in_duration"
            r5 = 50
            long r5 = com.android.systemui.assist.DeviceConfigHelper.getLong(r5, r2)
            long r5 = r5 + r3
            goto L_0x018d
        L_0x018c:
            r5 = r3
        L_0x018d:
            float r2 = r1.mDistance
            float r10 = (float) r5
            float r3 = (float) r3
            float r10 = r10 / r3
            float r10 = r10 * r2
            float r2 = r1.mStartX
            float r10 = r10 + r2
            float[] r3 = new float[r7]
            r3[r8] = r2
            r3[r9] = r10
            android.animation.ValueAnimator r2 = android.animation.ValueAnimator.ofFloat(r3)
            android.animation.ValueAnimator r2 = r2.setDuration(r5)
            android.view.animation.PathInterpolator r3 = INTERPOLATOR_SCROLL
            r2.setInterpolator(r3)
            r2.addUpdateListener(r1)
            r0.mTranscriptionAnimation = r2
            r2.start()
            goto L_0x01c9
        L_0x01b2:
            int r1 = r1.length()
            int r3 = r3.length()
            int r3 = r3 + r1
            r0.setUpSpans(r3, r4)
            float r1 = (float) r2
            float r1 = r0.getFullyVisibleDistance(r1)
            r0.setX(r1)
            r17.updateColor()
        L_0x01c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.assist.uihints.TranscriptionView.setTranscription(java.lang.String):void");
    }

    public final void setUpSpans(int i, TranscriptionSpan transcriptionSpan) {
        TranscriptionSpan transcriptionSpan2;
        this.mTranscriptionAnimator.mSpans.clear();
        String spannableStringBuilder = this.mTranscriptionBuilder.toString();
        String substring = spannableStringBuilder.substring(i);
        if (substring.length() > 0) {
            int indexOf = spannableStringBuilder.indexOf(substring, i);
            int length = substring.length() + indexOf;
            if (transcriptionSpan == null) {
                transcriptionSpan2 = new TranscriptionSpan();
            } else {
                transcriptionSpan2 = new TranscriptionSpan(transcriptionSpan);
            }
            this.mTranscriptionBuilder.setSpan(transcriptionSpan2, indexOf, length, 33);
            this.mTranscriptionAnimator.mSpans.add(transcriptionSpan2);
        }
        setText(this.mTranscriptionBuilder, TextView.BufferType.SPANNABLE);
        updateColor();
    }

    public final void updateColor() {
        int i;
        if (this.mHasDarkBackground) {
            i = this.TEXT_COLOR_DARK;
        } else {
            i = this.TEXT_COLOR_LIGHT;
        }
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, (float) this.mDisplayWidthPx, 0.0f, new int[]{0, i, i, 0}, this.mStops, Shader.TileMode.CLAMP);
        this.mMatrix.setTranslate(-getTranslationX(), 0.0f);
        linearGradient.setLocalMatrix(this.mMatrix);
        getPaint().setShader(linearGradient);
        invalidate();
    }

    public final void updateDisplayWidth() {
        Display defaultDisplay = DisplayUtils.getDefaultDisplay(this.mContext);
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        int i = point.x;
        this.mDisplayWidthPx = i;
        float f = this.BUMPER_DISTANCE_START_PX;
        float f2 = (float) i;
        this.mStops = new float[]{f / f2, (f + this.FADE_DISTANCE_START_PX) / f2, ((f2 - this.FADE_DISTANCE_END_PX) - this.BUMPER_DISTANCE_END_PX) / f2, 1.0f};
        updateColor();
    }

    public TranscriptionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class TranscriptionSpan extends ReplacementSpan {
        public float mCurrentFraction = 0.0f;
        public final float mStartFraction = 0.0f;

        public TranscriptionSpan() {
        }

        public final void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            float f2 = this.mStartFraction;
            float f3 = 1.0f;
            if (f2 != 1.0f) {
                f3 = MathUtils.clamp((((1.0f - f2) / 1.0f) * this.mCurrentFraction) + f2);
            }
            Paint paint2 = paint;
            paint2.setAlpha((int) Math.ceil((double) (f3 * 255.0f)));
            canvas.drawText(charSequence, i, i2, f, (float) i4, paint2);
        }

        public final int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            return (int) Math.ceil((double) TranscriptionView.this.getPaint().measureText(charSequence, 0, charSequence.length()));
        }

        public TranscriptionSpan(TranscriptionSpan transcriptionSpan) {
            this.mStartFraction = MathUtils.clamp(transcriptionSpan.mCurrentFraction);
        }
    }

    public TranscriptionView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    /* JADX WARNING: type inference failed for: r2v6, types: [com.android.systemui.assist.DeviceConfigHelper, java.lang.Object] */
    public TranscriptionView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mTranscriptionBuilder = new SpannableStringBuilder();
        this.mVisibilityAnimators = new AnimatorSet();
        this.mHideFuture = null;
        this.mHasDarkBackground = false;
        this.mMatrix = new Matrix();
        this.mDisplayWidthPx = 0;
        this.mTranscriptionAnimator = new TranscriptionAnimator();
        initializeDeviceConfigHelper(new Object());
        this.BUMPER_DISTANCE_START_PX = context.getResources().getDimension(2131167901) + context.getResources().getDimension(2131167899);
        this.BUMPER_DISTANCE_END_PX = context.getResources().getDimension(2131166032) + context.getResources().getDimension(2131166030);
        this.FADE_DISTANCE_START_PX = context.getResources().getDimension(2131167900);
        this.FADE_DISTANCE_END_PX = context.getResources().getDimension(2131166031) / 2.0f;
        this.TEXT_COLOR_DARK = context.getResources().getColor(2131100870);
        this.TEXT_COLOR_LIGHT = context.getResources().getColor(2131100871);
        updateDisplayWidth();
        setHasDarkBackground(!this.mHasDarkBackground);
    }
}
